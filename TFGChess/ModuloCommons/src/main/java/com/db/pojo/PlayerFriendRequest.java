/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.pojo;

import com.db.pojo.keys.PlayerFriendKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

/**
 *
 * @author migue
 */

@Entity
public class PlayerFriendRequest {
    
    @EmbeddedId
    private PlayerFriendKey id;
    
    public PlayerFriendRequest() {
    }

    public PlayerFriendRequest(PlayerFriendKey id) {
        this.id = id;
    }

    public PlayerFriendKey getId() {
        return id;
    }
    
}
