/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.pojo.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author migue
 */
@Embeddable
public class PlayerFriendKey implements Serializable {
    
    @Column(length = 20)
    private String username;
    
    @Column(length = 20)
    private String friendUsername;

    public PlayerFriendKey() {}

    public PlayerFriendKey(String username, String friendUsername) {
        this.username = username;
        this.friendUsername = friendUsername;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass() == PlayerFriendKey.class)) return false;
        PlayerFriendKey that = (PlayerFriendKey) o;
        return username.equals(that.username)
            && friendUsername.equals(that.friendUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, friendUsername);
    }
}
