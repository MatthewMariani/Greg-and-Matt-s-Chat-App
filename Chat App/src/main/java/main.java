import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class main {
    public static ArrayList<Socket> currentSockets;
    public static void main(String[] args) throws IOException {
        currentSockets= new ArrayList<Socket>();
        System.out.println("works");
        final ServerSocket serv = new ServerSocket(8009);
        while(true){
        final Socket usr = serv.accept();
        System.out.println("connected");
        final OutputStream outputStream = usr.getOutputStream();
        final PrintWriter out = new PrintWriter(outputStream);
        File index = new File("Chat App\\files\\index.html");
        out.println("HTTP/1.0 200 OK");
        //out.print("Content-Type: text/html");
        String content = Files.readString(Paths.get("Chat App\\files\\index.html"));
        out.println("Content-Length: "+content.length());
        out.println("");
        
        System.out.println(content);
        out.println(content);
        out.flush();
        System.out.println("sent");
    }

    }

}
