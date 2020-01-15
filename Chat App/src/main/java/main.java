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
//PRESS Q THEN ENTER IN CONSOLE TO KILL PROGRAM. KILLING TERMINAL IN VSCODE FIRST BREAKS VSCODE.
class main {
    public static ArrayList<Socket> currentSockets;
    public static SocketHandler[] handlers;
    public static boolean running=true;
    public static void main(String[] args) throws IOException {
        currentSockets= new ArrayList<Socket>();
        System.out.println("started");
        ServerSocket serv = new ServerSocket(8009);
        final CustomListener listener = new CustomListener(serv);
        final Thread listener1 = new Thread(listener);
        final SocketHandler handler = new SocketHandler();
        final Thread handler1 = new Thread(handler);
        handler1.start();
        handlers= new SocketHandler[1];
        handlers[0]=handler;
        
        listener1.start();
        
        String input="";
        Scanner quit = new Scanner(System.in);
        while(!input.equals("q"))
        {
            //System.out.println(handler1.getState().toString());
            //if(System.in.available()>0)
                input=quit.next();
        }
        quit.close();
        running=false;
        serv.close();
        

    }

}
