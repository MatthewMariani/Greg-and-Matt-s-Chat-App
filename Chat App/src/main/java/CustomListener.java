import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
        
        try {
            if (active) {
                Socket temp = socket.accept();
                //hand off to a handler
                
            }
        } catch (IOException e) {
        
            e.printStackTrace();
        }
            
        

    }
    public void deactivate()
    {
        active = false;
    }

    
}