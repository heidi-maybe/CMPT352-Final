/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */


import java.net.*;
import java.io.*;
import java.util.concurrent.*;



public class Server {

    //port
    public static final int DEFAULT_PORT = 8080;

    //thread handler
    private static final Executor exec = Executors.newVirtualThreadPerTaskExecutor();

    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;

        try{
            //establish the socket
            sock = new ServerSocket(DEFAULT_PORT);

            while(true){
                //listen for new connections and service them in a separate thread
                Runnable task =new Connection(sock.accept());
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