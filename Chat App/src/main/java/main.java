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
    public static SocketHandler[] handlers;
    public static void main(String[] args) throws IOException {
        currentSockets= new ArrayList<Socket>();
        System.out.println("started");
        ServerSocket serv = new ServerSocket(8009);
        CustomListener listener = new CustomListener(serv);
        Thread listener1 = new Thread(listener);
        SocketHandler handler = new SocketHandler();
        //Thread handler1 = new Thread(handler);
        //handlers= new SocketHandler[1];
        //handlers[0]=handler;
        
        listener1.start();
        //handler1.start();

    }

}
