/**
 * CMPT 352 Fall 2025
 * Final Project - Chat Room
 *      
 *
 * @author Jack Brinkman & Heidi Andre 
 */

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Handler {
    //thread manager
    private static final Executor execClient = Executors.newVirtualThreadPerTaskExecutor();

    //process
    public void process(Socket client) throws java.io.IOException {
        ChatScreen chatscreen = null;

        Runnable task = new ReaderThread(client, chatscreen);
        execClient.execute(task);

    }
}