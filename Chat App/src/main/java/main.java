import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

class main {

    public static void main(String[] args) {
        System.out.println("works");
        // does it really
        
        try {
            ServerSocket serv = new ServerSocket(50000);
            Socket usr = serv.accept();
            InputStream input = usr.getInputStream();
            byte[] message = new byte[25];
            while(true)
            {
                
                //System.out.println(input.available());
                if(input.available()>0)
                {
                    input.read(message);
                    String inMessage = new String(message);
                    System.out.println(inMessage);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}