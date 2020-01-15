import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

// work as medium for send/requesting info, handle new ones
class SocketHandler implements Runnable {
    int numConnections;
    ArrayList<Socket> connections;
    
    public SocketHandler()
    {
        connections= new ArrayList<Socket>();
        numConnections=connections.size();
    }

    public int getNumConnections() {
        return numConnections;
    }

    // establish new connections
    void addConnection(Socket e)
    {
        connections.add(e);
        //send to http handler
        //send httpmessage
    }
    
    // correctly handle removed connections
    void flushDeadConnections()
    {
        for (int i = 0; i < connections.size(); i++) {
            if(!connections.get(i).isConnected())
            {
                try {
                connections.get(i).close();
                } catch (IOException e) {}
                connections.remove(i);
                i--;
                
            }
        }
    }

    @Override
    public void run() {
        //System.out.println("SocketHandler running");
        boolean allActive = true;
        while(true)
        {

            //System.out.println("socket handler work:"+connections.size());
            System.out.println(connections.size());
            for (int i = 0; i < connections.size();i++) {
                System.out.println("working on: "+connections.get(i).getInetAddress()+":"+connections.get(i).getPort());
                try{

                    //InputStream input = socket.getInputStream();
                   // if(input.available()>0)
                    //{
                        //String in = new String(input.readAllBytes());
                        //HttpReader request = new HttpReader(in);
                        //if(request.headerContents.get("request").equals("GET"))
                        //{
                            String content = Files.readString(Paths.get("Chat App\\files\\index.html"));
                            String header = "HTTP/1.0 200 OK\r\n"
                            +"Content-Type: text/html\r\n"
                            +"Content-Length: "+content.length()+"\r\n"
                            +"\r\n";
                            header+=content;
                            byte[] output = header.getBytes();
                            connections.get(i).getOutputStream().write(output);
                            connections.get(i).close();
                            connections.remove(i);
                            i--;
                            
                       // }
                    //}


                }catch(Exception e){
                    e.printStackTrace();
                    connections.remove(i);
                    i--;
                    
                    
                }
            }
        }


    }
}