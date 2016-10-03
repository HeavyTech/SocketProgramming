import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.SocketHandler;

/**
 * Created by Heavytech on 10/3/2016.
 */
public class Chat {


    public static void main(String[] args) throws IOException{


        Scanner input = new Scanner(System.in);

        //This will hold the Message what the user wants to do.  'Help','connect'..etc
        String[] message;


        //This will hold all connections of Sockets.
        ArrayList<Socket> socketsConnected = new ArrayList<>();

        //Messages that we will send to eachother.
        PrintWriter writer = null;


        while(true){
            System.out.println(">>");
            message = input.nextLine().split(" ");
            if(message[0].equalsIgnoreCase("Help")){
                help();
            }
            else if(message[0].equalsIgnoreCase("my ip")){
                myIP();
            }

        }


    }

    public static void help() throws IOException{

        try(BufferedReader br = new BufferedReader(new FileReader("Help.txt"))){
            String line = "";
            while(line != null){
                line = br.readLine();
                System.out.println(line);
            }
        }

    }

    public static void myIP(){
        try{
            System.out.print("Working");
            System.out.println(">> IP Address: "+ InetAddress.getLocalHost().getHostAddress());

        }catch(UnknownHostException e){
            e.printStackTrace();
        }

    }


}
