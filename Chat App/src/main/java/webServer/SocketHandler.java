package webServer;

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
            //dont remove this system.out.print("") below. It shouldn't break the program, but it does, for some odd reason. I tried to figure it out but I gave up. 
            System.out.print("");

            for (ClientConnection clientConnection : connections) {
                //System.out.println(clientConnection);
                try {
                
                int aval = clientConnection.getInputStream().available();
                //System.out.println(aval);
                if(aval>0)
                {
                        byte[] temp = new byte[aval];
                        clientConnection.getInputStream().read(temp);
                        String buffer = new String(temp);
                        HttpReader message = new HttpReader(buffer);
                        String path = main.URIDictionary.get(message.headerContents.get("URI"));
                        if(path==null)
                            path=main.URIDictionary.get("ERROR");
                       // System.out.println("COOKIE: "+message.getCookie("identification")); 
                        try{
                            System.out.println("COOKIE: "+message.getCookie("identification"));
                            clientConnection.user=User.userBuilder(message.getCookie("identification"));
                        } catch(IOException e2) {clientConnection.user=null; System.out.println("failed");}
                        System.out.println(message.headerContents.get("URI"));
                        String req = message.headerContents.get("request");
                        switch(req)
                        {
                            case "GET":
                                System.out.println("GET response to: "+clientConnection.toString());
                                System.out.println(path);
                                switch(main.typeDictionary.get(path))
                                {
                                    case "image/apng":
                                        clientConnection.sendICO(path);
                                        break;
                                    default:
                                        clientConnection.sendWebpage(path);
                                        break;
                                }
                                //System.out.println("done");
                                
                                break;
                            default:
                                System.out.println("ERROR");
                                clientConnection.sendWebpage("Chat App\\files\\404Error.html");
                                break;
                        }
                        //System.out.println("done2");
                        
                    
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