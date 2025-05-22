/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import com.chess.general.Position;
import com.connutils.Action;
import com.connutils.Request;
import com.connutils.RequestBuilder;
import com.connutils.chatlog.Message;
import com.db.DatabaseManager;
import com.db.pojo.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.ServerCode;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class ClientConn implements Closeable{
    
    private String username;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private HearThread hearThread = null;
    
    private Game game = null;

    private List<Action> actions = new LinkedList<Action>(){{
        add(new CreateAction());
        add(new JoinAction());
        add(new MoveAction());
        add(new AddFriendAction());
        add(new GetFriendsAction());
        add(new GetFriendRequestsAction());
        add(new RemoveFriendRequestAction());
        add(new SendGameMessage());
        add(new LeaveAction());
        add(new SendMessage());
        add(new GetMessages());
    }};
    
    public ClientConn(String username, Socket socket) {
        this.username = username;
        this.socket = socket;
        try {
            this.out = getWriter();
        } catch (Exception ex) {
            exit(ServerCode.KO);
        }
        hearThread = new HearThread(this);
        hearThread.start();
    }
    
    public void gotRequest(Request request){
        System.out.println("-----------------------------");
        System.out.println(" > leido (" + username + "):\n > " + request.toString().replaceAll("\n", "\n > "));
        System.out.println("-----------------------------");
        
        for (Action action : actions) 
            if(action.getType().equals(request.getHeader()))
                action.execute(request);   
    }
    
    public void sendRequest(Request request){
        if (out != null) {
            System.out.println("ENVIANDO " + request);
            out.println(request.toJSON());
        }
    }
    
    public boolean isConnected() {
        return socket != null &&
                !socket.isClosed();
    }
    
    public void exit(int code){
        if (out != null) {
            sendRequest(
                    RequestBuilder
                    .createRequest("exit")
                    .put("code", Integer.toString(code))
                    .build()
            );
        }
        Server.removePlayer(this);
        try {
            this.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isClosed(){
        return socket.isClosed();
    }

    public PrintWriter getWriter() throws IOException{
        if (socket.isClosed()) return null;
        if (out != null) return out;
        out = new PrintWriter(socket.getOutputStream(), true);
        return out;
    }
    
    public BufferedReader getReader() throws IOException{
        if (socket.isClosed()) return null;
        if (in != null) return in;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return in;
    }
    
    public String getUsername() {
        return username;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (out != null) {
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }
    
    public void sendFriends(){
        RequestBuilder builder =  RequestBuilder.createRequest("putfriends");
        int i = 1;
        for (Player player : DatabaseManager.getFriendsByUsername(username)) {
            String playerState = "offline";
            Optional<ClientConn> optPlayer = Server.getPlayer(player.getUsername());
            System.out.println(optPlayer);
            if(!optPlayer.isEmpty() && optPlayer.get().isConnected()) 
                playerState = "online";
            builder.put(new StringBuilder("friend ").append(i).toString(),
                    RequestBuilder.createRequest("friend")
                            .put("username", player.getUsername())
                            .put("state", playerState)
                            .build()
                            .toJSON()
            );
            i++;
        }
        sendRequest(builder.build());
    }

    private void sendUpdateFriendRequestAlert() {
        boolean anyFriendRequest
            = DatabaseManager.getFriendRequestsReceivedByUsername(this.getUsername()).isEmpty();
        this.sendRequest(
                RequestBuilder.createRequest("updatefriendrequestalert")
                        .put("active", Boolean.toString(!anyFriendRequest))
                        .build()
        );
    }
    
    public void sendAllMessages(String withUsername){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            sendRequest(
                    RequestBuilder.createRequest("chatlog")
                            .put("with", withUsername)
                            .put("chatlog", objectMapper.writeValueAsString(Server.logs.get(this.getUsername()).getMessagesWith(withUsername)))
                            .build()
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ClientConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private abstract class FailableAction implements Action{
        protected String errorMessage = null;
        
        @Override
        public void execute(Request request) {
            if (comprobation(request)) {
                success();
            }else{
                failed();
            }
        }
        protected abstract boolean comprobation(Request request);
        
        protected void success(){
            sendRequest(
                    RequestBuilder.createRequest("ok")
                    .put("on", getType())
                    .build()
            );
        }
        protected void failed(){
            RequestBuilder requestBuilder = 
                    RequestBuilder.createRequest("failed")
                    .put("on", getType());
            if (errorMessage != null) {
                requestBuilder.put("message", errorMessage);
            }
            sendRequest(requestBuilder.build());
        }
    }
    
    class CreateAction extends FailableAction{
        public void setErrorMessage(String errorMessage){
            this.errorMessage = errorMessage;
        }
        @Override
        protected boolean comprobation(Request request) {
            return Server.createGame(username, request, this);
        }
        @Override
        public String getType() {
            return "create";
        }
    }
    private class JoinAction extends FailableAction{
        @Override
        protected boolean comprobation(Request request) {
            return Server.joinGame(username, request);
        }
        @Override
        public String getType() {
            return "join";
        }
    }
    private class MoveAction extends FailableAction{
        @Override
        protected boolean comprobation(Request request) {
            String lobbyName = request.get("name");
            try {
                Position from = Position.fromJSON(request.get("from"));
                Position to = Position.fromJSON(request.get("to"));
                return !((game != null &&
                     lobbyName != null &&
                     lobbyName.equals(game.getName())) 
                    || 
                    !game.move(ClientConn.this, from, to));
            } catch (JsonProcessingException ex) {
                return false;
            }
            
        }
        @Override
        public String getType() {
            return "move";
        }
    }
    private class AddFriendAction extends FailableAction{
        @Override
        protected boolean comprobation(Request request) {
            boolean ok = request.contains("username") 
                    && DatabaseManager.addFriendRequestByUsernames(username, request.get("username"));
            if (ok) {
                Optional<ClientConn> friendOpt = Server.getPlayer(request.get("username"));
                if (!friendOpt.isEmpty()) {
                    friendOpt.get().sendFriends();
                    friendOpt.get().sendUpdateFriendRequestAlert();
                }
                sendUpdateFriendRequestAlert();
                sendFriends();
            }
            return ok;
        }
        @Override
        public String getType() {
            return "addfriend";
        }
    }
    private class RemoveFriendRequestAction extends FailableAction{
        @Override
        protected boolean comprobation(Request request) {
            return request.contains("username") 
                   && DatabaseManager.removeFriendRequestByUsernames(request.get("username"), username);
        }
        @Override
        public String getType() {
            return "removefriendrequest";
        }
    }
    private class SendGameMessage extends FailableAction{

        @Override
        protected boolean comprobation(Request request) {
            if(game == null)
                return false;
            return game.sendMessage(ClientConn.this, request.getOrDefault("message", null));
        }
        @Override
        public String getType() {
            return "sendgamemessage";
        }
        
    }
    private class SendMessage extends FailableAction{

        @Override
        protected boolean comprobation(Request request) {
            if (!Server.logs.containsKey(ClientConn.this.username)) 
                return false;
            if (!Server.logs.containsKey(request.get("receiver"))) 
                return false;
            if (!Server.players.containsKey(request.get("receiver"))) 
                return false;
            if (!request.contains("receiver")) 
                return false;
            if (!request.contains("message")) 
                return false;
            Server.logs.get(ClientConn.this.username).sendedMessage(request.get("receiver"), request.get("message"));
            Server.logs.get(request.get("receiver")).receivedMessage(ClientConn.this.username, request.get("message"));
            ClientConn.this.sendAllMessages(request.get("receiver"));
            Server.players.get(request.get("receiver")).sendAllMessages(username);
            //enviar mensajes y alerta...
            //y recibir en titlelabel
            return true;
        }
        @Override
        public String getType() {
            return "sendmessage";
        }
        
    }
    private class GetMessages extends FailableAction{
        
        @Override
        protected boolean comprobation(Request request) {
            if(!request.contains("with"))
                return false;
            sendAllMessages(request.get("with"));
            return true;
        }
        
        @Override
        public String getType() {
            return "getmessages";
        }
        
    }
    private class GetFriendsAction implements Action{

        @Override
        public void execute(Request request) {
            sendFriends();
            sendUpdateFriendRequestAlert();
        }

        @Override
        public String getType() {
            return "getfriends";
        }
        
    }
    private class GetFriendRequestsAction implements Action{

        @Override
        public void execute(Request request) {
            RequestBuilder builder =  RequestBuilder.createRequest("putfriendrequests");
            int i = 1;
            for (Player player : DatabaseManager.getFriendRequestsReceivedByUsername(username)) {
                builder.put(new StringBuilder("friendrequest ").append(i).toString(), player.getUsername());
                i++;
            }
            sendRequest(builder.build());
        }

        @Override
        public String getType() {
            return "getfriendrequests";
        }
        
    }
    private class LeaveAction implements Action{

        @Override
        public void execute(Request request) {
            if (game != null) {
                if (request.getOrDefault("drawrequest", "false").equals("true")) {
                    if (game.drawRequest(ClientConn.this)) {
                        System.out.println("TABLAS");
                        game.endGame(null);
                        Server.removeGame(game);
                        game = null;
                    }
                }else{
                    System.out.println("PIERDE: " + ClientConn.this.getUsername());
                    game.endGame(ClientConn.this);
                    Server.removeGame(game);
                    game = null;
                }
            }
        }

        @Override
        public String getType() {
            return "leave";
        }
        
    }
    
}
