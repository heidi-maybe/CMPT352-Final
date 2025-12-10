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

public class Broadcast implements Runnable {
    private List<Socket> clients;
    private LinkedList<String> messageQueue;

    public Broadcast(List<Socket> clients, LinkedList<String> messageQueue) {
		this.clients = clients;
		this.messageQueue = messageQueue;
	}

    public void run() {
        int count = 0;

        while (true) {
            
            // 5 second sleep
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignore) {}

            // broadcast debugging
            String testMessage = "[Test Message" + count + "]\n";

            LinkedList<String> sending = new LinkedList<>();
            int next = messageQueue.size();

            if (count < next) {
                for (int i = count; count < next; i++) {
                    sending.add(messageQueue.get(i));
                }
                count = next;
            } else {
                continue;
            }
            // if count not caught up
                // loop at count to next
                    // adds i messageQueue to sending sending.add(messageQeueu.get(i));
                // count to next
            // else continue

            for (Socket client : clients) {
                try {
                    DataOutputStream toClient = new DataOutputStream(client.getOutputStream());
                    // messages in sending SEND
                    for (String send : sending) {
                        toClient.writeBytes(send);
                    }
                    
                } catch (IOException ioe) {}
            }
            // delete in a sec
            //count++;
        }
    }
}