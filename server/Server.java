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


    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;

        try{
            sock = new ServerSocket(DEFAULT_PORT);
            
            Thread broadcastThread = new Thread(new Broadcast(clients, messageQueue, usernames));
            broadcastThread.setDaemon(true);
            broadcastThread.start();

            while(true){
               //listen for new connections and service them in a separate thread
               Socket client = sock.accept();
               clients.add((client));
               
               
               Runnable task = new Connection(client, clients, messageQueue, usernames);
               exec.execute(task);

           }
          
           
            

            //establish the socket
            

        }
        catch (IOException ioe) { System.err.println(ioe); }
		finally {
			if (sock != null)
				sock.close();

        }
    }
}