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
public interface PlayerService {
    public Player getPlayerByUsername(String username);
    
    public boolean registerPlayer(String username, String password);
    public boolean isUsernameTaken(String username);
}
