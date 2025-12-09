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

public class Connection implements Runnable {
    private Socket	client;
	private Handler handler;
	private List<Socket> clients;
	private LinkedList<String> messageQueue;
	
	public Connection(Socket client, List<Socket> clients, LinkedList<String> messageQueue) {
		this.client = client;
		this.clients = clients;
		this.messageQueue = messageQueue;
		this.handler = new Handler();
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {
			handler.process(client);
		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		} finally {
			clients.remove(client);
			
			try {
				if (!client.isClosed())
				client.close();
			} catch (IOException e) { }
		}
	}


}