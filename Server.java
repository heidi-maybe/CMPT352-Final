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
    private static final Vector<String> messageQueue = new Vector<>();


    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;

        try{
            Broadcast broadcaster = new Broadcast(clients, messageQueue);
            Thread broadcastThread = new Thread(broadcaster);
            broadcastThread.setDaemon(true);
			broadcastThread.start();

            //establish the socket
            sock = new ServerSocket(DEFAULT_PORT);

            while(true){
                //listen for new connections and service them in a separate thread
                Socket client = sock.accept();
				clients.add(client);
				Runnable task = new Connection(client, clients, messageQueue);
				exec.execute(task);


            }

        }
        catch (IOException ioe) { System.err.println(ioe); }
		finally {
			if (sock != null)
				sock.close();

        }
    }
}