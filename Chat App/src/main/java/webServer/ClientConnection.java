package webServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ClientConnection
 */
public class ClientConnection {
    Socket socket;
    BufferedInputStream streamInput;
    String protocol;
    boolean firstContact;
    backend.User user;
    HttpReader lastRequest;
    backend.Session session;
//create a session
    public ClientConnection(Socket socket) {
        firstContact = true;
        this.socket = socket;
        protocol = "HTTP";
        try {
            streamInput = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String toString() {
        return socket.getInetAddress() + ":" + socket.getPort();
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    public BufferedInputStream getInputStream() throws IOException {
        return streamInput;
    }

    public boolean isAlive() {
        boolean out = !socket.isClosed()||socket.isConnected();
        return out;
    }

    public boolean firstTime()
    {
        return firstContact;
    }

    public boolean hasData() throws IOException
    {
        return socket.getInputStream().available()>0;
    }
    public void close() {

    }

    public void send2(String in) throws IOException 
    {
        byte[] messageBlob = in.getBytes();
        send2(messageBlob);
        
    }

    public void send2(byte[] in) throws IOException
    {
        socket.getOutputStream().write(in);
        socket.getOutputStream().flush();
        firstContact=false;
    }

    public void sendWebpage(String webpagePath, HashMap<String, String> args) throws IOException
    {
        if(args==null)
            args = new HashMap<String, String>();
        //addArgs(args);
        String content = "";

        try {
            content=Files.readString(Paths.get(webpagePath));
        } catch(IOException e) {
            content=errorPage("404");
        }

        System.out.println(user);
        if(user!=null)
            content=String.format(content, this.user.getID());
        else
        {
            content=String.format(content, "new User");
            content+="<script>document.cookie=\"indentification=\"+prompt(\"enter ID:\");</script>";
        }
        
        String header = "HTTP/1.1 200 OK\r\n"
        +"Content-Type: text/html\r\n";
        AtomicReference<String> temp = new AtomicReference<String>();
        
        header+=temp.get();
        header+="Content-Length: ";
        header+=content.length()+"\r\n";
        if(args.size()>0)
            args.forEach((k,v) -> temp.set(k+": "+v+"\r\n"));
        if(user!=null)
            header+="Set-Cookie: indentification="+user.getID()+"\r\n";
        header+="\r\n";
        send2(header+content);
        
    }
    public void sendWebpage(String webpagePath) throws IOException
    {
        sendWebpage(webpagePath, null);
        
    }
    public void sendICO(String webpagePath) throws IOException
    {
        //File image = new File(webpagePath);
        byte[] imageData = Files.readAllBytes(Paths.get(webpagePath));
        String header = "HTTP/1.0 200 OK\r\n"
        +"Content-Type: "+main.typeDictionary.get(webpagePath)+"\r\n"
        +"Content-Length: "+
        imageData.length+"\r\n\r\n";
        int aLen = header.length();
        int bLen = imageData.length;
        byte[] output = new byte[aLen+bLen];
        System.arraycopy(header.getBytes(), 0, output, 0, aLen);
        System.arraycopy(imageData, 0, output, aLen, bLen);
        send2(output);
    }
    public void sendHTTP(String webpagePath) throws IOException
    {
        switch(main.typeDictionary.get(webpagePath))
        {
            case "image/apng":
                sendICO(webpagePath);
                break;
            case "image/webp":
                sendICO(webpagePath);
                break;
            case "text/html":
                sendWebpage(webpagePath);
                break;
            
                

        }
    }
    private void addArgs(HashMap<String,String> args)
    {
        // String cookie = lastRequest.headerContents.get("Cookie");
        // String[] cookies = cookie.split("; ");
        // Map<String,String> args1 = new HashMap<String, String>();
        // for (String string : cookies) {
        //     String[] temp = string.split("=");
        //     args1.put(temp[0],temp[1]);
        // }
        args.put("Set-Cookies","yeetyeet=shrekwazowski");
        // if(user!=null&&args1.containsKey("indentification")&&args1.get("indentification")!=user.getID())
        //     args.put("indentification",user.getID());

    }
    public void setLastHttp(HttpReader in)
    {
        lastRequest = in;
    }
    public static void main(String[] args) {

    }

    public static String errorPage(String errorCode)
    {   
        String desc = "";
        //is hardcoding really the best option here?
        switch(errorCode)
        {
            case "404":
                desc = "Page not found";
                break;
            case "403":
                desc = "Forbidden";
                break;
            case "405":
                desc = "Method Not Allowed";
                break;
            case "423":
                desc = "Webpage Locked";
                break;
            case "429":
                desc = "Too Many Requests";
                break;
            case "501":
                desc = "Method Not Implemented";
                break;
                
        }
        String out = "<!doctype html>\n\n<html lang=\"en\"\n<head>\n<meta charset=\"utf-8\">\n<title>"+errorCode+" "+desc+"</title>\n</head>\n<body><h1>Error "+errorCode+"</h1>\n<p>"+desc+"</p>\n</body>\n</html>";
        return out;
    }
}