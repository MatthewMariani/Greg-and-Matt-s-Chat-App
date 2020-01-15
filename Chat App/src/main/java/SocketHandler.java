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
                System.out.println(clientConnection);
                if(clientConnection.firstTime())
                {
                    try {
                        clientConnection.sendHTTP("Chat App\\files\\index.html");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        System.out.println("diagnosis");
                    }
                }
            }
            connections.addAll(connectionBuffer);
            //flushDeadConnections();
            connectionBuffer.clear();
        }


    }
}