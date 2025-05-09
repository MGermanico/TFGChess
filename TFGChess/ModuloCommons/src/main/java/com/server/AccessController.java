/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import com.connutils.Request;
import com.connutils.RequestBuilder;
import com.db.DatabaseManager;
import com.db.dbUtils.PasswordUtils;
import com.db.pojo.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.utils.ServerCode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class AccessController extends Thread {

    private Socket socket;

    private BufferedReader in;
    private PrintWriter out;

    public AccessController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        int code;
        Request request;
        String username;
        String pass;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            request = Request.fromJSON(in.readLine());

            System.out.println("PETICION DE UNIRSE DE JUGADOR:\n\n"  + request);
            username = request.get("username");
            pass = request.get("password");
            
            System.out.println(username);
            System.out.println(pass);
            
            if (username == null || pass == null) {
                exit(ServerCode.JSON_MALFORMED);
            }
            
            if (request.getHeader().equals("login")) {
                
                login(username, pass);
                
            } else if (request.getHeader().equals("register")) {
                
                register(username, pass);
                
            } else {
                
                exit(ServerCode.UNSUPPORTED_OPERATION);
                
            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void login(String username, String pass) {
        int code;
        code = correctLogin(username, pass);
        if (code == ServerCode.OK) {
            code = Server.addPlayer(username, socket);
            if(code != ServerCode.OK){
                exit(code);
            }
        } else {
            exit(code);
        }
    }
    
    private int correctLogin(String username, String pass) {
        if (!DatabaseManager.isUsernameTaken(username)) {
            return ServerCode.NOT_FOUND_PLAYER;
        }
        Player player = DatabaseManager.getPlayerByUsername(username);
        if (player == null) {
            return ServerCode.NOT_FOUND_PLAYER;
        }
        if (!PasswordUtils.verifyPassword(pass, player.getPasswordHash())) {
            return ServerCode.INCORRECT_PASSWORD;
        }
        return ServerCode.OK;
    }
    
    private void register(String username, String pass) {
        int code;
        code = correctRegister(username, pass);
        if (code == ServerCode.OK) {
            Server.addPlayer(username, socket);
        } else {
            exit(code);
        }
    }

    private int correctRegister(String username, String pass) {
        if (!DatabaseManager.registerPlayer(username, pass)) {
            return ServerCode.USED_USERNAME;
        }
        return ServerCode.OK;
    }

    private void exit(int code) {
        out.println(
                RequestBuilder
                .createRequest("exit")
                .put("code", Integer.toString(code))
                .build()
                .toJSON()
        );
    }
}
