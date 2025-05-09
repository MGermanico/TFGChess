/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.conn;

import com.connutils.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class HearThread extends Thread{

    private Socket socket;

    public HearThread(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run() {
        BufferedReader in = null;
        try {
            String msg;
            Request request;
            in = Client.getReader();
            do {
                msg = in.readLine();
                request = Request.fromJSON(msg);
                System.out.println("Server msg: " + msg);
                Client.gotRequest(request);
                if (request.getHeader().equals("exit")) {
                    System.out.println("codigo de error: " + request.get("code"));
                    break;
                }
            } while (!socket.isClosed());
            System.out.println("OUT");
            Client.closeConnection();
        } catch (IOException ex) {
            Client.closeConnection();
            Logger.getLogger(HearThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(HearThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
