/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;  

public class MyGUI {

    String[] messageDisplayArray = new String[1000];

    public MyGUI(){
        //make a frame
        JFrame frame = new JFrame("GUI");
        frame.setSize(new Dimension(400,400));
        frame.setLayout(new GridBagLayout());
        frame.setVisible(true);
        
        //make a message panel
        JScrollPane messagePanel = new JScrollPane();

            //constraints for the panel
            GridBagConstraints pc = new GridBagConstraints();
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
            //add the mesage display
            JList<String> messageList = new JList<>(messageDisplayArray);
            messagePanel.add(messageList);
            
            //add the panel to the frame
            frame.add(messagePanel, pc);



        
        //make a message panel
        JPanel buttonsPanel = new JPanel();
            GridBagConstraints pc1 = new GridBagConstraints();
            pc1.gridx = 0;
            pc1.gridy = 1;
            pc1.gridwidth = 1;
            pc1.gridwidth = 1;
            pc1.fill = GridBagConstraints.BOTH;
            pc1.weightx = 1;
            pc1.weighty = 0.3;
            pc.insets = new Insets(0,10,10,10);

            //modify the panel
            buttonsPanel.setBackground(Color.gray);
            
            //add the panel to the frame
            frame.add(buttonsPanel, pc1);

        //make frame visible
        frame.setVisible(true);

        //check

    }


    public static void main(String[] args){
        SwingUtilities.invokeLater(MyGUI::new);
    }

}
