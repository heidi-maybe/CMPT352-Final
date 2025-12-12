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
	private ArrayList<String> usernames;
	
	public Connection(Socket client, List<Socket> clients, LinkedList<String> messageQueue, ArrayList<String> usernames) {
		this.client = client;
		this.clients = clients;
		this.messageQueue = messageQueue;
		this.usernames = usernames;

		this.usernames = usernames;
		this.handler = new Handler(clients, messageQueue, usernames);
		
	
	}

    /**
     * This method runs in a separate thread.
     */	
	public void run() { 
		try {

			handler.process(client, clients, usernames);

		}
		catch (java.io.IOException ioe) {
			System.err.println(ioe);
		} catch (Exception ex) {
            System.getLogger(Connection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } finally {
			clients.remove(client);
			
			try {
				if (!client.isClosed())
				client.close();
			} catch (IOException e) { }
		}
	}


}