/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connutils.chatlog;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author migue
 */
public class ChatLog {
    private LinkedList<Message> log;
    
    public ChatLog() {
        log = new LinkedList<Message>();
    }
    
    public void receivedMessage(String by, String message){
        log.add(new Message(false, by, message));
    }
    public void sendedMessage(String to, String message){
        log.add(new Message(true, to, message));
    }
    public List<Message> getMessagesWith(String username){
       List<Message> list = new LinkedList<Message>(log.stream()
                .filter(message -> message.getUsername().equals(username))
                .toList());
       Collections.sort(list);
       return list;
    }
    
}

