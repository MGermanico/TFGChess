/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db;

import com.db.impl.PlayerFriendImpl;
import com.db.impl.PlayerFriendRequestImpl;
import com.db.impl.PlayerImpl;
import com.db.pojo.Player;
import com.db.service.PlayerFriendRequestService;
import com.db.service.PlayerFriendService;
import com.db.service.PlayerService;
import java.util.List;

/**
 *
 * @author migue
 */
public class DatabaseManager {
    public static Player getPlayerByUsername(String username) {
        PlayerService playerService = new PlayerImpl();
        return playerService.getPlayerByUsername(username);
    }
    
    public static List<Player> getFriendsByUsername(String username){
        PlayerFriendService playerService = new PlayerFriendImpl();
        return playerService.getFriendsByUsername(username);
    }
    public static List<Player> getFriendRequestsReceivedByUsername(String username){
        PlayerFriendRequestService playerService = new PlayerFriendRequestImpl();
        return playerService.getFriendRequestsReceivedByUsername(username);
    }
    public static List<Player> getFriendRequestsByUsername(String username){
        PlayerFriendRequestService playerService = new PlayerFriendRequestImpl();
        return playerService.getFriendRequestsReceivedByUsername(username);
    }
    
    public synchronized static boolean registerPlayer(String username, String password) {
        if (isUsernameTaken(username)) {
            return false;
        }
        
        PlayerService playerService = new PlayerImpl();
        return playerService.registerPlayer(username, password);
    }

    public static boolean isUsernameTaken(String username) {
        PlayerService playerService = new PlayerImpl();
        return playerService.isUsernameTaken(username);
    }
    public static boolean removeFriendByUsernames(String username, String friendUsername){
        PlayerFriendService playerService = new PlayerFriendImpl();
        return playerService.removeFriendByUsernames(username, friendUsername);
    }
    public static boolean addFriendByUsernames(String username, String friendUsername){
        PlayerFriendService playerService = new PlayerFriendImpl();
        return playerService.addFriendByUsernames(username, friendUsername) & 
               playerService.addFriendByUsernames(friendUsername, username);
    }
    
    public static boolean removeFriendRequestByUsernames(String username, String friendUsername){
        PlayerFriendRequestService playerService = new PlayerFriendRequestImpl();
        return playerService.removeFriendRequestByUsernames(username, friendUsername);
    }
    public static boolean addFriendRequestByUsernames(String username, String friendUsername){
        List<Player> friends = getFriendsByUsername(username);
        if (friends.contains(new Player(friendUsername, null))) 
            return false;
        
        List<Player> friendRequests = getFriendRequestsReceivedByUsername(username);
        if (friendRequests.contains(new Player(friendUsername, null))) {
            removeFriendRequestByUsernames(friendUsername, username);
            return addFriendByUsernames(username, friendUsername);
        }else{
            PlayerFriendRequestService playerService = new PlayerFriendRequestImpl();
            return playerService.addFriendRequestByUsernames(username, friendUsername);
        }
    }
}
