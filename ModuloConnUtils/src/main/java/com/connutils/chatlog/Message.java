/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connutils.chatlog;

import java.time.LocalDateTime;

/**
 *
 * @author migue
 */
public class Message implements Comparable<Message>{
    private boolean self;
    private String username;
    private String message;
    
    @com.fasterxml.jackson.annotation.JsonIgnore
    private LocalDateTime date;

    public Message() {
    }
    
    public Message(boolean self, String username, String message) {
        this.self = self;
        this.username = username;
        this.message = message;
        date = LocalDateTime.now();
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public int compareTo(Message o) {
        return this.date.compareTo(o.date);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSelf() {
        return self;
    }
    
}
