import java.io.IOException;
import java.net.*;
import java.util.Scanner;

class client {
    public static void main(String[] args) {
        try {
            Socket soccer = new Socket("127.0.0.1", 50000);
            Scanner scan = new Scanner(System.in);
            String message = "";
            while(true)
            {
                message = scan.nextLine();
                byte[] blob = message.getBytes();
                soccer.getOutputStream().write(blob);
                //hi
                if(false)
                    break;
            }
            scan.close();
            soccer.close();;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}