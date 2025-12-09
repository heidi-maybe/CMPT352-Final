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

            while (true) {
                KLV.KLVMessage message = KLV.readKLVFromSocket(fromClient);
                    if (message == null) {
                        break; // Connection closed
                    }
                if (message.key.equals("JOIN")) {
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