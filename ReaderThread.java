import java.io.*;
import java.net.*;
import javax.swing.*;

public class ReaderThread implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	ChatScreen screen;

	public ReaderThread(Socket server, ChatScreen screen) {
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
						screen.displayMessage(msg);
					}
				});
			}
		}
		catch (IOException ioe) { System.out.println(ioe); }

	}
}