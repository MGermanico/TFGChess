/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import com.chess.general.ChessManager;
import com.db.DatabaseManager;
import com.db.pojo.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.utils.ServerCode;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.connutils.Request;
import com.connutils.RequestBuilder;

/**
 *
 * @author migue
 */
public class Server {
    
    static{
        try(Scanner sc = new Scanner(new File("src/main/resources/Configuration.txt"))){
            PORT = Integer.parseInt(sc.nextLine());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static int PORT;
    
    private static Map<String, ClientConn> players = new HashMap<>();
    private static Map<String, Game> games = new HashMap<>();
    
    public static void main(String[] args) {
//        DatabaseManager.addFriendRequestByUsernames("Pepe", "jose");
//        DatabaseManager.addFriendRequestByUsernames("jose", "Pepe");
         try {
             ServerSocket serverSocket = new ServerSocket(5000);
             Socket actualSocket;
             
             for (;;) {
                actualSocket = serverSocket.accept();
                
                AccessController accessController = new AccessController(actualSocket);
                accessController.start();
                 
             }
         } catch (IOException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    public static Optional<ClientConn> getPlayer(String username){
        return Optional.ofNullable(players.getOrDefault(username, null));
    }
    
    public static int addPlayer(String username, Socket socket) {
        synchronized (players) {
            if (!players.containsKey(username) || !players.get(username).isConnected()) {
                ClientConn client = new ClientConn(username, socket);
                players.put(username, client);
                client.sendRequest(RequestBuilder.createRequest("loged").build());
                updateFriendsLists(username);
            } else {
                return ServerCode.ALREADY_CONNECTED_ACCOUNT;
            }
        }
        return ServerCode.OK;
    }
    
    public static void removePlayer(ClientConn con){
        try {
            con.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized (players) {
            players.remove(con.getUsername());
            System.out.println("cerrada " + players.size());
        }
        updateFriendsLists(con.getUsername());
    }

    static boolean createGame(String username, Request request) {
        System.out.println("CREAR PARTIDA");
        ClientConn client;
        boolean ownerIsWhite;
        try {
            String name = request.get("name");
            ownerIsWhite = request.getOrDefault("ownerIsWhite", "true").equals("true");
            String password = request.get("password");
            client = players.get(username);
            Game game = new Game(name, password, client, ownerIsWhite, ChessManager.fromJSON(request.get("chess")));
            games.put(name, game);
            
            client.setGame(game);
            return true;
        } catch (JsonProcessingException ex) {
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
           return false;
        }
    }

    static boolean joinGame(String username, Request request) {
        System.out.println("UNIRSE PARTIDA");
        ClientConn client;
        Game game;
        String name;
        
        name = request.get("name");
        if(name == null || !games.containsKey(name)) return false;
        client = players.get(username);
        game = games.get(name);
        if(!game.setSecondPlayer(client)) return false;
        client.setGame(game);
        return true;
    }

    private static void updateFriendsLists(String username) {
        for (Player player : DatabaseManager.getFriendsByUsername(username)) {
            Optional<ClientConn> optConn = getPlayer(player.getUsername());
            if (!optConn.isEmpty()) {
                optConn.get().sendFriends();
            }
        }
    }
}
