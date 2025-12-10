/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Handler {
    private LinkedList<String> messageQueue;
    private List<Socket> clients;
    private ArrayList<String> usernames;
    public Handler(List<Socket> users, LinkedList<String> incoming, ArrayList<String> usernames) {
        this.messageQueue = incoming;
        this.clients = users;
        this.usernames = usernames;
    }
    
    public void process(Socket client, List<Socket> clients, ArrayList<String> usernames) throws java.io.IOException {
        DataInputStream fromClient = null;
        
        try {
            fromClient = new DataInputStream(client.getInputStream());
            toClient = new DataOutputStream(client.getOutputStream());

            while (true) {
                KLV.KLVMessage message = KLV.readKLVFromSocket(fromClient);
                if (message == null) {
                    break; // Connection closed
                }
                // When a person joins the chatroom
                if (message.key.equals("JOIN")) {
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);

                    System.out.println("Received: " + message.key + ":" + valueStr);
                    synchronized(this.messageQueue) {
                        this.messageQueue.add(valueStr);
                    }
                }

                // A message was sent in the chatroom 
                if (message.key.equals("MSG")) { // \0 is NULL padding and striped in the encorder so doesn't matter here (cries)
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                    synchronized(this.messageQueue) {
                        this.messageQueue.add(valueStr);
                    }
                }

                // Last 20 messages in chat history

                if (message.key.equals("READ")) { 
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                    // needs to be to just the one client who clicked READ. not sure yet
                    toClient.writeBytes("------Chat History Requested------\n");
                    int full = this.messageQueue.size();
                    int history;
                    if (full >= 20) {
                        history = this.messageQueue.size() - 20;
                    } else {
                        history = 0;
                    }
                    if (full != 0) {
                        for (int i = history; i < full; i++) {
                            toClient.writeBytes(this.messageQueue.get(i) + "\n");
                            toClient.flush();
                        }
                    } else {
                        toClient.writeBytes("No Chat History\n");
                    }
                    
                }

                // A person has left the chatroom
                if (message.key.equals("EXIT")) { 
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                    valueStr = "------Goodbye " + valueStr + "------";
                    synchronized(this.messageQueue) {
                        this.messageQueue.add(valueStr);
                    }
                }

                // Adding some of the KLV messages we need. Working on implementing them
                // Server Response (I think this is error codes and such so this will change) (haven't started)
                if (message.key.equals("RESP")) { 
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                }
            }
            
        } catch (IOException ioe) {} 
    }
}