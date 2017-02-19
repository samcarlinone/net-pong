package com.samcarlinone.netpong.networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by CARLINSE1 on 1/31/2017.
 */
public class TCPThread extends Thread {
    public Boolean isHost = false;

    private ServerSocket server_socket;
    private Socket socket;
    private BufferedReader read;
    private DataOutputStream write;
    private String host;

    static private int PORT = 23685;

    public ArrayBlockingQueue<String> tx_queue;
    public ArrayBlockingQueue<String> rx_queue;
    public ArrayBlockingQueue<String> command_queue;

    public TCPThread(String host) {
        super("MulticastDiscoveryThread");

        this.host = host;
        if(host == "") {
            isHost = true;
        }

        tx_queue = new ArrayBlockingQueue<String>(128, true);
        rx_queue = new ArrayBlockingQueue<String>(128, true);
        command_queue = new ArrayBlockingQueue<String>(128, true);
    }

    public void run() {
        try {
            if (host == "") {
                server_socket = new ServerSocket(PORT);
                socket = server_socket.accept();
            } else {
                socket = new Socket(host, PORT);
            }

            read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            write = new DataOutputStream(socket.getOutputStream());
        } catch(IOException e) {
            System.err.println("\n\nTCPThread error\n\n");
        }

        while(true) {
            if(command_queue.peek() != null)
                break;

            if(canRead()) {
                try {
                    rx_queue.put(read());
                } catch (InterruptedException e) {
                    System.err.println("TCPThread read error");
                }
            }

            if(tx_queue.peek() != null) {
                write(tx_queue.poll());
            }

            try {
                sleep(1L);
            } catch (InterruptedException e) { }
        }
    }

    /**
     * Get the first message from connected TCPLink
     * @return String containing message
     */
    public String read() {
        try {
            return read.readLine();
        } catch(IOException e) {
            System.out.println("TCP Recieve Failed");
            return "";
        }
    }

    public boolean canRead() {
        try {
            return read.ready();
        } catch(IOException e) {
            System.out.println("TCP Ready Check Failed");
            return false;
        }
    }

    /**
     * Sends a message to connected TCPLink
     * @param tx String containing message, does not need to be newline terminated
     */
    public void write(String tx) {
        if(!tx.endsWith("\n"))
            tx = tx+"\n";

        try {
            write.writeBytes(tx);
        } catch(IOException e) {
            System.out.println("TCP Send Failed");
            return;
        }
    }
}