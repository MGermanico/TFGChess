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
public interface PlayerFriendService {
    public List<Player> getFriendsByUsername(String username);
    
    public boolean removeFriendByUsernames(String username, String friendUsername);
    public boolean addFriendByUsernames(String username, String friendUsername);
}
