/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ReaderThread implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	MyGUI screen;

	public ReaderThread(Socket server, MyGUI screen) {
		this.server = server;
		this.screen = screen;
	}

	public void run() {
		try {
			
            fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			while (true) {
				String message = fromServer.readLine();

				// now display it on the display area
				// Use invokeLater to update GUI from non-GUI thread
				final String msg = message;
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						if (!msg.equals(null)) {
							screen.displayMessage(msg);
						}
						
					}
				});
			}
		}
		catch (IOException ioe) { System.out.println(ioe); }

	}
}