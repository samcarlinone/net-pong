package com.samcarlinone.netpong.networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by CARLINSE1 on 1/31/2017.
 */
public class TCPLink {
    public Boolean isHost = false;

    private ServerSocket server_socket;
    private Socket socket;
    private BufferedReader read;
    private DataOutputStream write;

    static private int PORT = 23685;

    public TCPLink(String host) throws IOException {
        if(host == "") {
            server_socket = new ServerSocket(PORT);
            socket = server_socket.accept();

            isHost = true;
        } else {
            socket = new Socket(host, PORT);
        }

        read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        write = new DataOutputStream(socket.getOutputStream());
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