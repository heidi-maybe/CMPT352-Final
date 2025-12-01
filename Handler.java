/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */

import java.io.*;
import java.net.*;

public class Handler {
    
    public void process(Socket client) throws java.io.IOException {
        DataInputStream fromClient = null;
        try {
            fromClient = new DataInputStream(client.getInputStream());

            while (true) {
                int data = fromClient.read();
                if (data == -1) {
                    break;
                }
            }
            
        } catch (IOException ioe) {
        } finally {
            if (fromClient != null)
            fromClient.close();
        }
    }
}