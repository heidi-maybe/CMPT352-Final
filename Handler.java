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
        DataOutputStream toClient = null;
        
        try {
            fromClient = new DataInputStream(client.getInputStream());

            while (true) {
                KLV.KLVMessage message = KLV.readKLVFromSocket(fromClient);
                if (message == null) {
                    break; // Connection closed
                }
                // When a person joins the chatroom
                if (message.key.equals("JOIN")) {
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Made it to JOIN");
                    for(int i = 0; i < usernames.size(); i++){
                        if (usernames.get(i).equals(valueStr)){
                            toClient = new DataOutputStream(client.getOutputStream());
                            String returnMessage = "Invalid Username";
                            byte[] byteArray = returnMessage.getBytes();
                            toClient.write(byteArray);
                            fromClient.close();
                            toClient.close();
                            clients.remove(client);
                            System.out.println("Not Valid Username");
                            
                        } 
                        
                        
                    }
                    System.out.println("Valid Username");
                    usernames.add(valueStr);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                }

                // A message was sent in the chatroom 
                if (message.key.equals("MSG")) { // \0 is NULL padding and striped in the encorder so doesn't matter here (cries)
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                    synchronized(this.messageQueue) {
                        this.messageQueue.add(message.key + valueStr);
                    }
                }

                // Adding some of the KLV messages we need. Working on implementing them


                // A person has left the chatroom (haven't started)
                if (message.key.equals("EXIT")) { 
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                }
                // Server Response (I think this is error codes and such so this will change) (haven't started)
                if (message.key.equals("RESP")) { 
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                }
            }
            
        } catch (IOException ioe) {
        } finally {
            if (fromClient != null)
            fromClient.close();
        }
    }
}