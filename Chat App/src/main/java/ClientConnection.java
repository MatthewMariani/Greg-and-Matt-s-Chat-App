import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * ClientConnection
 */
public class ClientConnection {
    Socket socket;
    BufferedInputStream streamInput;
    String protocol;
    boolean firstContact;

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

    public void send(String in) throws IOException {
        byte[] messageBlob = in.getBytes();
        socket.getOutputStream().write(messageBlob);
        socket.getOutputStream().flush();
        firstContact=false;
    }

    public void sendHTTP(String webpagePath, HashMap<String, String> args) throws IOException
    {
        String content = Files.readString(Paths.get(webpagePath));
        String header = "HTTP/1.0 200 OK\r\n"
        +"Content-Type: text/html\r\n"
        +"Content-Length: ";
        header+=content.length()+"\r\n";
        for (Entry<String, String> string : args.entrySet()) {
            header+=string.getKey()+": "+string.getValue()+"\r\n";
        }
        header+="\r\n";
        send(header+content);
        
    }
    public void sendHTTP(String webpagePath) throws IOException
    {
        String content = Files.readString(Paths.get(webpagePath));
        String header = "HTTP/1.0 200 OK\r\n"
        +"Content-Type: text/html\r\n"
        +"Content-Length: ";
        header+=content.length()+"\r\n";
        header+="\r\n";
        send(header+content);
        
    }
    
}