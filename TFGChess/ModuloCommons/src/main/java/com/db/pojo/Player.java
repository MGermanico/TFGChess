/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author migue
 */

@Entity
public class Player {
    @Id
    @Column(columnDefinition = "VARCHAR(20) COLLATE utf8_bin")
    private String username;
    
    @Column(nullable = false)
    private String passwordHash;

    public Player() {
    }

    public Player(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return this.username;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) ||
                ((Player)obj).username.equals(username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
    
}
