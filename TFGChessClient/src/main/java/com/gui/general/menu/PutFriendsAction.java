/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.menu;

import com.connutils.Action;
import com.connutils.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
class PutFriendsAction implements Action {

    private Menu menu;

    public PutFriendsAction(Menu menu) {
        this.menu = menu;
    }
    
    @Override
    public void execute(Request request) {
        List<Friend> playerUsernames = new ArrayList<>();
        for (Map.Entry<String, String> entry : request.getBody().entrySet()) {
            if (entry.getKey().contains("friend")) {
                Request friendRequest;
                try {
                    friendRequest = Request.fromJSON(entry.getValue());
                    playerUsernames.add(
                            new Friend(
                                    friendRequest.getOrDefault("username", "DESCONOCIDO ?"),
                                    friendRequest.getOrDefault("state", "offline")
                            )
                    );
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(1);
                }
            }
        }
        this.menu.putFriends(playerUsernames);
    }

    @Override
    public String getType() {
        return "putfriends";
    }
}
