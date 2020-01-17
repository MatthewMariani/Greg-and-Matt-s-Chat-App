
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//PRESS Q THEN ENTER IN CONSOLE TO KILL PROGRAM. KILLING TERMINAL IN VSCODE FIRST BREAKS VSCODE.
class main {
    //public static ArrayList<Socket> currentSockets;
    //public static SocketHandler[] handlers;
    public static volatile ArrayList<ClientConnection> connectionBuffer;
    public static boolean running=true;
    public static volatile Map<String,String> URIDictionary;
    public static volatile Map<String,String> typeDictionary;
    //public static volatile String test = "empty";
    public static void main(String[] args){
        //currentSockets= new ArrayList<Socket>();
        connectionBuffer=new ArrayList<ClientConnection>();
        URIDictionary = new HashMap<String,String>();
        typeDictionary = new HashMap<String,String>();
        try {
            Scanner URIputter = new Scanner(Paths.get("Chat App/files/manifest.txt"));
            while(URIputter.hasNextLine())
            {
                String key = URIputter.nextLine();
                String value = URIputter.nextLine();
                String type = URIputter.nextLine();
                URIDictionary.put(key,value);
                typeDictionary.put(value,type);
            }
            URIputter.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println(URIDictionary);
        try{

        System.out.println("started");
        ServerSocket serv = new ServerSocket(8009);
        CustomListener listener2 = new CustomListener(serv);
        Thread listener1 = new Thread(listener2);
        SocketHandler handler = new SocketHandler();
        SocketHandler handler2 = new SocketHandler();
        Thread handlerT = new Thread(handler);
        Thread handler2T = new Thread(handler2);
        handlerT.start();
        //handler2T.start();
        //handlers= new SocketHandler[1];
        //handlers[0]=handler;
        
        listener1.start();
        
        
        String input="";
        Scanner quit = new Scanner(System.in);
        while(!input.equals("q"))
        {
            //System.out.println(handler1.getState().toString());
            if(System.in.available()>0)
                input=quit.next();
        }
        
        quit.close();
        running=false;
        serv.close();
        } catch(IOException e) {e.printStackTrace();}
        

    }

}
