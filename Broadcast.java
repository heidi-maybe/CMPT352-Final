import java.io.*;
import java.net.*;
import java.util.*;

public class Broadcast implements Runnable {
    private List<Socket> clients;
    private Vector<String> messageQueue;

    public Broadcast(List<Socket> clients, Vector<String> messageQueue) {
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

            String testMessage = "[Test Message" + count + "]\n";

            for (Socket client : clients) {
                try {
                    DataOutputStream toClient = new DataOutputStream(client.getOutputStream());
                    toClient.writeBytes(testMessage);
                } catch (IOException ioe) {}
            }

            count++;
        }
    }
}