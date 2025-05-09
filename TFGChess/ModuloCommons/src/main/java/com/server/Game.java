/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import com.chess.general.ChessManager;
import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.utils.ChessCode;
import com.utils.ServerCode;
import java.io.File;
import com.connutils.Action;
import com.connutils.Request;
import com.connutils.RequestBuilder;

/**
 *
 * @author migue
 */
public class Game {

    private String name;
    private String password;
    
    private boolean ownerIsWhite = true;
    private ClientConn ownerPlayer;
    private ClientConn secondPlayer = null;
    private ChessManager chess;
    
    public Game(String name, String password, ClientConn ownerPlayer, boolean ownerIsWhite, ChessManager chess) throws Exception {
        this.name = name;
        this.ownerPlayer = ownerPlayer;
        if (chess == null) {
            this.chess = ChessManager.fromJSON(new File("src/main/resources/NewGame.json"));
        }else{
            this.chess = chess;
        }
        ownerPlayer.sendRequest(
                RequestBuilder
                .createRequest("joined")
                .put("name", name)
                .build()
        );
    }

    public Game(String name, String password, ClientConn ownerPlayer, boolean ownerIsWhite) throws Exception {
        this(name, password, ownerPlayer, ownerIsWhite, null);
    }
    
    public boolean startGame(){
        if(secondPlayer == null || secondPlayer.isClosed() ||
           ownerPlayer == null || ownerPlayer.isClosed()) return false;
        return true;
    }
    
    public boolean move(ClientConn client, Position from, Position to){
        System.out.println("\n\n\n\n");
        System.out.println("turno de blancas? " + chess.isWhiteTurn());
        System.out.println("\n\n\n\n");
        
        if(client != ownerPlayer && client != secondPlayer) return false;
        
        boolean ownerTurn = ownerIsWhite == this.chess.isWhiteTurn();
        
        if(ownerTurn && client == secondPlayer) return false;
        if(!ownerTurn && client == ownerPlayer) return false;
        
        if (chess.move(from, to) != ChessCode.OK) return false;
        
        String jsonChess;
        try {
            jsonChess = chess.toJSON();
        } catch (JsonProcessingException ex) {
            return false;
        }
        
        return updateBoard();
    }

    public boolean setSecondPlayer(ClientConn secondPlayer) {
        if (this.secondPlayer != null) return false;
        this.secondPlayer = secondPlayer;
        ownerPlayer.sendRequest(
                RequestBuilder
                .createRequest("playerjoined")
                .put("username", secondPlayer.getUsername())
                .build()
        );
        
        secondPlayer.sendRequest(
                RequestBuilder
                .createRequest("joined")
                .put("name", name)
                .put("ownerusername", ownerPlayer.getUsername())
                .build()
        );
        
        return updateBoard();
//        if(startGame()){
//            Request startRequest = RequestBuilder.createRequest("")
//        }
    }

    public String getName() {
        return name;
    }

    private boolean updateBoard() {
        String jsonChess;
        try {
            jsonChess = chess.toJSON();
        } catch (JsonProcessingException ex) {
            return false;
        }
        
        RequestBuilder boardRequest = RequestBuilder
                .createRequest("updatedBoard")
                .put("board", jsonChess);
        
        secondPlayer.sendRequest(
                boardRequest.put("white", Boolean.toString(!ownerIsWhite))
                .build()
        );
        
        ownerPlayer.sendRequest(
                boardRequest.put("white", Boolean.toString(ownerIsWhite))
                .build()
        );
        
        return true;
    }

    void sendMessage(ClientConn sender, String message) {
        RequestBuilder builder =  RequestBuilder.createRequest("gamemessage");
        ClientConn reciever;
        if (sender.equals(ownerPlayer)) {
            reciever = secondPlayer;
        }else{
            reciever = ownerPlayer;
        }
        reciever.sendRequest(builder.put("text", message).build());
    }
    
    
    
}
