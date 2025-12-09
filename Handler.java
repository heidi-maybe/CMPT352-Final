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

public class Handler {
    
    public void process(Socket client) throws java.io.IOException {
        DataInputStream fromClient = null;
        try {
            fromClient = new DataInputStream(client.getInputStream());
            // Needs a connection to Broadcast somehow

            while (true) {
                KLV.KLVMessage message = KLV.readKLVFromSocket(fromClient);
                if (message == null) {
                    break; // Connection closed
                }
                // When a person joins the chatroom
                if (message.key.equals("JOIN")) {
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
                }

                // A message was sent in the chatroom 
                if (message.key.equals("MSG")) { // \0 is NULL padding and striped in the encorder so doesn't matter here (cries)
                    String valueStr = new String(message.value, StandardCharsets.UTF_8);
                    System.out.println("Received: " + message.key + ":" + valueStr);
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