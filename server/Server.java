/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */


import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;



public class Server {

    // Default port
    public static final int DEFAULT_PORT = 8080;

    // thread handler
    private static final Executor exec = Executors.newVirtualThreadPerTaskExecutor();

    // List tracking all connected Clients
    private static final List<Socket> clients = new CopyOnWriteArrayList<>();

    // Message queue for broadcasting thread
    private static final LinkedList<String> messageQueue = new LinkedList<>();
    private static final ArrayList<String> usernames = new ArrayList<String>();
    private static final Socket client = null;


    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;

        try{
            sock = new ServerSocket(DEFAULT_PORT);

            

            //establish the socket
            

        }
        catch (IOException ioe) { System.err.println(ioe); }
		finally {
			if (sock != null)
				sock.close();

        }
    }
}