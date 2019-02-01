package inf112.skeleton.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.net.InetAddress;

    //prøver å etablere lan connection tingene
public class connectionSetup {

    private int decidedPort=9090;
    private String multiplayerRole;
    private String server="primary";
    private String client="secondary";
    private InetAddress serverIP;

    public connectionSetup(String multiplayerRole) {
        Scanner reader = new Scanner(System.in);
        System.out.println("if client write secondary, if server write primary: ");
        multiplayerRole = reader.next();
        reader.close();

        //TODO etablere en strøm av informasjon ut/inn
        if(multiplayerRole.equals(server)){
            try {
                ServerSocket ServerSide = new ServerSocket(decidedPort);
            } catch (IOException e) { e.printStackTrace(); }

            try {
                serverIP=InetAddress.getLocalHost();
            } catch (UnknownHostException e) { e.printStackTrace(); }

        }


        else if(multiplayerRole.equals(client)){
            try {
                Socket ClientSide = new Socket(serverIP, decidedPort);
            } catch (IOException e) { e.printStackTrace(); }

        }
    }

}
