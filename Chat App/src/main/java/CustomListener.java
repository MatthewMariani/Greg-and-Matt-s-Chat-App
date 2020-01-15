import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * listener
 */
public class CustomListener implements Runnable {
    boolean active = true;
    ServerSocket socket;

    public CustomListener(ServerSocket connection) {
        socket = connection;
    }

    @Override
    public void run() {
        while(main.running)
        {
        try {
            if (active) {
                Socket temp = socket.accept();
                ClientConnection connection = new ClientConnection(temp);
                System.out.println("new connection: "+connection.toString());
                main.handlers[0].addConnection(connection);
                System.out.println("added connection");

                //main.currentSockets.add(temp);
                //hand off to a handler
                //main.handlers[0].addConnection(temp);
            }
        } catch (IOException e) {
        
            e.printStackTrace();
        }
    }
            
        

    }

    public void deactivate()
    {
        active = false;
    }

    
}