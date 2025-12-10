/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.swing.*;

public class MyGUI implements ActionListener, KeyListener{
    private String host;
    private int port;
    private String username;

	private JButton exitButton;
    private JButton readButton;
	private JTextField sendText;
	private JTextArea displayArea;
    private JButton joinButton;
    private JTextField usernameField;
    private JTextField portField;
    private JTextField hostField;
    private ArrayList<String> myStringList = new ArrayList<>();
    
    
    private InputStream in;
    private OutputStream out;
    private Socket connect;

    public MyGUI(){
        //make a frame
        JFrame frame = new JFrame("GUI");
        frame.setSize(new Dimension(400,400));
        frame.setLayout(new GridBagLayout());
        GridBagConstraints pc = new GridBagConstraints();
        
        
        //make a message panel
        displayArea = new JTextArea(15,40);
		displayArea.setEditable(false);
		displayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane messagePanel = new JScrollPane(displayArea);

        //constraints for the panel on pc
            pc.gridx = 0;
            pc.gridy = 0;
            pc.gridwidth = 1;
            pc.gridwidth = 1;
            pc.fill = GridBagConstraints.BOTH;
            pc.weightx = 1;
            pc.weighty = 0.7;
            pc.insets = new Insets(10,10,10,10);

            //modify the panel
            messagePanel.setBackground(Color.lightGray);
            
            //add the panel to the frame
            frame.add(messagePanel, pc);
            
            //add the mesage display to the panel
            



        
        //make a buttons panel
        JPanel buttonsPanel = new JPanel();
            //constraints for the panel on pc 
            pc.gridx = 0;
            pc.gridy = 1;
            pc.gridwidth = 1;
            pc.fill = GridBagConstraints.BOTH;
            pc.weightx = 1;
            pc.weighty = 0.3;
            pc.insets = new Insets(0,10,10,10);
            frame.add(buttonsPanel, pc);
            buttonsPanel.setBackground(Color.gray);
            buttonsPanel.setLayout(new GridBagLayout());


            //modify the panel
            buttonsPanel.setBackground(Color.gray);
            buttonsPanel.setLayout(new GridBagLayout());
            GridBagConstraints bpc = new GridBagConstraints();


            //add a panel for the type bar
            JPanel typeBarPanel = new JPanel();
                bpc.gridx = 0;
                bpc.gridy = 0;
                bpc.gridwidth = 1;
                bpc.fill = GridBagConstraints.BOTH;
                bpc.weightx = 0.7;
                bpc.weighty = 1;
                bpc.insets = new Insets(10,10,10,0);
                typeBarPanel.setBackground(Color.LIGHT_GRAY);
                buttonsPanel.add(typeBarPanel, bpc);
                typeBarPanel.setLayout(new GridBagLayout());
                
                //constrain and add the type bar
                GridBagConstraints tbpc = new GridBagConstraints();
                tbpc.gridx = 0;
                tbpc.gridy = 0;
                tbpc.gridwidth = 1;
                tbpc.fill = GridBagConstraints.HORIZONTAL;
                tbpc.weightx = 1;
                tbpc.weighty = 0.1;
                tbpc.insets = new Insets(10,10,10,10);
                tbpc.anchor = GridBagConstraints.NORTHWEST; 

                sendText = new JTextField(20);
                typeBarPanel.add(sendText, tbpc);


                




            
            //add the join panel
            JPanel joinPanel = new JPanel();
                bpc.gridx = 1;
                bpc.gridy = 0;
                bpc.gridwidth = 1;
                bpc.fill = GridBagConstraints.BOTH;
                bpc.weightx = 0.3;
                bpc.weighty = 1;
                bpc.insets = new Insets(10,10,10,10);
                Color customGreen = new Color(165,224,144);
                joinPanel.setBackground(customGreen);
                buttonsPanel.add(joinPanel, bpc);
                joinPanel.setLayout(new GridBagLayout());

                //add and contrain the host text box on the join panel
                GridBagConstraints jpc = new GridBagConstraints();
                jpc.gridx = 0;
                jpc.gridy = 0;
                jpc.gridwidth = 1;
                jpc.fill = GridBagConstraints.HORIZONTAL;
                jpc.weightx = 1;
                jpc.weighty = 0.1;
                jpc.anchor = GridBagConstraints.NORTH;
                jpc.insets = new Insets(10,10,10,10);
                hostField = new JTextField("localhost",20);
                joinPanel.add(hostField,jpc);

                //add and contrain the port text box on the join panel
                GridBagConstraints jpc1 = new GridBagConstraints();
                jpc1.gridx = 0;
                jpc1.gridy = 1;
                jpc1.gridwidth = 1;
                jpc1.fill = GridBagConstraints.HORIZONTAL;
                jpc1.weightx = 1;
                jpc1.weighty = 0.1;
                jpc1.anchor = GridBagConstraints.NORTH;
                jpc1.insets = new Insets(0,10,0,10);
                portField = new JTextField("8080",20);
                joinPanel.add(portField,jpc1);

                //add and contrain the username text box on the join panel
                GridBagConstraints jpc2 = new GridBagConstraints();
                jpc2.gridx = 0;
                jpc2.gridy = 2;
                jpc2.gridwidth = 1;
                jpc2.fill = GridBagConstraints.HORIZONTAL;
                jpc2.weightx = 1;
                jpc2.weighty = 0.1;
                jpc2.anchor = GridBagConstraints.NORTH;
                jpc2.insets = new Insets(0,10,0,10);
                usernameField = new JTextField("username",20);
                joinPanel.add(usernameField,jpc2);

                //Join Button
                GridBagConstraints jpc3 = new GridBagConstraints();
                jpc3.gridx = 0;
                jpc3.gridy = 3;
                jpc3.gridwidth = 1;
                jpc3.fill = GridBagConstraints.HORIZONTAL;
                jpc3.weightx = 1;
                jpc3.weighty = 0.1;
                jpc3.anchor = GridBagConstraints.NORTH;
                jpc3.insets = new Insets(10,10,10,10);
                joinButton = new JButton("Click here to Join");
                joinPanel.add(joinButton,jpc3);
                joinButton.addActionListener(this);

                //Exit Button
                GridBagConstraints jpc4 = new GridBagConstraints();
                jpc4.gridx = 0;
                jpc4.gridy = 5;
                jpc4.gridwidth = 1;
                jpc4.fill = GridBagConstraints.HORIZONTAL;
                jpc4.weightx = 1;
                jpc4.weighty = 0.1;
                jpc4.anchor = GridBagConstraints.NORTH;
                jpc4.insets = new Insets(10,10,10,10);
                exitButton = new JButton("Click here to Exit");
                joinPanel.add(exitButton,jpc4);
                exitButton.addActionListener(this);

                GridBagConstraints jpc5 = new GridBagConstraints();
                jpc5.gridx = 0;
                jpc5.gridy = 4;
                jpc5.gridwidth = 1;
                jpc5.fill = GridBagConstraints.HORIZONTAL;
                jpc5.weightx = 1;
                jpc5.weighty = 0.1;
                jpc5.anchor = GridBagConstraints.NORTH;
                jpc5.insets = new Insets(10,10,10,10);
                readButton = new JButton("Click here to READ");
                joinPanel.add(readButton,jpc5);
                readButton.addActionListener(this);


        //make frame visible
        frame.setVisible(true);
        sendText.addActionListener(e -> {
            try {
                displayText();
            } catch (Exception ex) {
                System.getLogger(MyGUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        sendText.requestFocus();

    }

    public void displayMessage(String message) {
		displayArea.append(message + "\n");
        System.out.println("used message");
	}

    public void displayText() throws Exception {
		String message = this.username+ ": " + sendText.getText().trim();
		// displayArea.append(message + "\n");
		sendText.setText("");
		sendText.requestFocus();
        System.out.println("used text");


        // Still needs work. For debugging for now should see a message from the running server "Recieved: MSG:....."
        byte[] msgKVL = KLV.encodeKLV("MSG\0", message.getBytes(StandardCharsets.UTF_8));
        out.write(msgKVL);
        
	}


    /**
	 * This method responds to action events .... i.e. button clicks
	 * and fulfills the contract of the ActionListener interface.
	 */
    public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source == joinButton){
                    try {
                        join();
                    } catch (IOException ex) {
                    }
        }
        if (source == exitButton){
                    try {
                        exit();
                    } catch (Exception ex) {
                        System.getLogger(MyGUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
        }
        if (source == readButton) {
                    try {
                        read();
                    } catch (Exception ex) {
                        System.getLogger(MyGUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
        }
	}

    /**
	 * This is invoked when the user presses
	 * the ENTER key.
	 */
	public void keyPressed(KeyEvent e) { 
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			try {
                            displayText();
                } catch (Exception ex) {
                    System.getLogger(MyGUI.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
	}

    public void keyReleased(KeyEvent e) { }

	/** Not implemented */
	public void keyTyped(KeyEvent e) {  }

    public void join() throws IOException{
        this.host = hostField.getText();
        this.port = Integer.parseInt(portField.getText());
        this.username = usernameField.getText();
        serverConnection();
        
        //}
        //catch (UnknownHostException uhe) { System.out.println(uhe); }
    }

    public void exit() throws Exception{
        System.out.println("exit");
        byte[] message = KLV.encodeKLV("EXIT", this.username.getBytes(StandardCharsets.UTF_8));
        this.out.write(message); 

        this.out.close();
        this.in.close();
        displayArea.setText("You have EXITED the chatroom");
    }

    public void read() throws Exception {
        System.out.println("READ");
        byte[] message = KLV.encodeKLV("READ", this.username.getBytes(StandardCharsets.UTF_8));
        this.out.write(message); 
    }


    public void serverConnection() {
       try {
            System.out.println("Connecting to " + this.host + ":" + this.port + " as " + this.username);
            this.connect = new Socket(this.host, this.port);
            this.in = connect.getInputStream();
            this.out = connect.getOutputStream();

            byte[] message = KLV.encodeKLV("JOIN", this.username.getBytes(StandardCharsets.UTF_8));
            this.out.write(message);
            
            // Needed for reading messages being sent by the Server -> Handler -> Broadcast (path of the message I think)
            Thread ReaderThread = new Thread(new ReaderThread(connect, this));
            ReaderThread.start();

        }
        catch (UnknownHostException uhe) { System.out.println("Unknown Host: " + uhe); }
        catch (IOException ioe) { System.out.println("Connection I/O Error: " + ioe); }
        catch (Exception e) {}
    }
    public static void main(String[] args){
        MyGUI win = new MyGUI();
    }
    
}
