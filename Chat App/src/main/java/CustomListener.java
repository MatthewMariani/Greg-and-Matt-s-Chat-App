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
        while(true)
        {
        try {
            if (active) {
                Socket temp = socket.accept();
                System.out.println("new connection: "+temp.getInetAddress()+":"+temp.getPort());
                String content = Files.readString(Paths.get("Chat App\\files\\index.html"));
                String header = "HTTP/1.0 200 OK\r\n"
                +"Content-Type: text/html\r\n"
                +"Content-Length: "+content.length()+"\r\n"
                +"\r\n";
                header+=content;
                byte[] output = header.getBytes();
                temp.getOutputStream().write(output);
                temp.getOutputStream().flush();
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