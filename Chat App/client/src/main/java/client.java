import java.io.IOException;
import java.net.*;

class client {
    public static void main(String[] args) {
        try {
            boolean running = true;
            int i=0;
            while(running)
            {
                Socket socket = new Socket("localhost", 8009);
                String req = "GET / HTTP/1.1\r\n" + "Host: localhost:8009\r\n" + "Connection: keep-alive\r\n"
                + "Cache-Control: max-age=0\r\n" + "Upgrade-Insecure-Requests: 1\r\n"
                + "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36\r\n"
                + "Sec-Fetch-User: ?1\r\n"
                + "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n"
                + "Sec-Fetch-Site: none\r\n" + "Sec-Fetch-Mode: navigate\r\n" + "Accept-Encoding: gzip, deflate, br\r\n"
                + "Accept-Language: en-US,en;q=0.9\r\n" + "\r\n";
                socket.getOutputStream().write(req.getBytes());
                
                System.out.println(i+": "+socket.getInputStream().available());
                socket.getInputStream().read();
                //socket.getInputStream().read();
                i++;
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}