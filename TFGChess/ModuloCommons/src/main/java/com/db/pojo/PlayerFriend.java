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
public class PlayerFriend {
    
    @EmbeddedId
    private PlayerFriendKey id;
    
    public PlayerFriend() {
    }

    public PlayerFriend(PlayerFriendKey id) {
        this.id = id;
    }

    public PlayerFriendKey getId() {
        return id;
    }
    
}
