/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.db.service;

import com.db.pojo.Player;
import java.util.List;

/**
 *
 * @author migue
 */
public interface PlayerFriendRequestService {
    public List<Player> getFriendRequestsByUsername(String username);
    public List<Player> getFriendRequestsReceivedByUsername(String username);
    
    public boolean removeFriendRequestByUsernames(String username, String friendUsername);
    public boolean addFriendRequestByUsernames(String username, String friendUsername);
}
