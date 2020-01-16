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
    ArrayList<ClientConnection> connections;
    ArrayList<ClientConnection> connectionBuffer;
    
    public SocketHandler()
    {
        connections= new ArrayList<ClientConnection>();
        connectionBuffer = new ArrayList<ClientConnection>();
        numConnections=connections.size();
    }

    public int getNumConnections() {
        return numConnections;
    }

    // establish new connections
    void addConnection(ClientConnection e)
    {
        connectionBuffer.add(e);
        //send to http handler
        //send httpmessage
    }
    
    // correctly handle removed connections
    void flushDeadConnections()
    {
        for (int i = 0; i < connections.size(); i++) {
            if(!connections.get(i).isAlive())
            {
                connections.get(i).close();
                connections.remove(i);
                i--;
                
            }
        }
    }

    @Override
    public void run() {
        System.out.println("SocketHandler running");
        
        while(main.running)
        {

            //System.out.println("socket handler work:"+connections.size());
            //dont remove this system.out.print("") below. It shouldn't break the program, but it does, for some odd reason. I tried to figure it out but I can't give up. 
            System.out.print("");

            for (ClientConnection clientConnection : connections) {
                //System.out.println(clientConnection);
                try {
                
                int aval = clientConnection.getInputStream().available();
                //System.out.println(aval);
                if(aval>0)
                {
                        //System.out.println("HALT1");
                        //get the message
                        byte[] temp = new byte[aval];
                        clientConnection.getInputStream().read(temp);
                        
                        //System.out.println("HALT1.5");
                        //do I need ot clear a bufferdinputstream after being read/how do inputstreams work or read
                        //System.out.println(temp);
                        String buffer = new String(temp);
                        //System.out.println(buffer);
                        HttpReader message = new HttpReader(buffer);
                        String path = main.URIDictionary.get(message.headerContents.get("URI"));
                        //System.out.println("HALT2");
                        System.out.println(path);
                        if(path==null)
                            path=main.URIDictionary.get("/");
                        System.out.println(message.headerContents.get("URI"));
                        String req = message.headerContents.get("request");
                        //System.out.println("HALT3");
                        //System.out.println(req);
                        
                        switch(req)
                        {
                            case "GET":
                                System.out.println("GET response to: "+clientConnection.toString());
                                clientConnection.sendHTTP(path);
                                break;
                            default:
                                System.out.println("ERROR");
                                clientConnection.sendHTTP("Chat App\\files\\404Error.html");
                                break;
                        }
                        
                    
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("diagnosis");
            }
            }
            connections.addAll(main.connectionBuffer);
            flushDeadConnections();
            main.connectionBuffer.clear();
        }


    }
}