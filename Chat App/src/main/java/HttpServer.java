import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

class HttpServer implements Runnable {
    Socket socket;

    public HttpServer() {

    }
    @Override
    public void run() {
        try {
            String rawInput = new String(socket.getInputStream().readAllBytes());
            StringTokenizer input = new StringTokenizer(rawInput);
            String method = input.nextToken();
            if(!method.equals("GET")&&!method.equals("HEAD"))
            {
                //return error page
            }
            else
            {
                //hangdle response
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}