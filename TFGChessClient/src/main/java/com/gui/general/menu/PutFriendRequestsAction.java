/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.menu;

import com.connutils.Action;
import com.connutils.Request;
import com.gui.general.FriendRequestFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author migue
 */
class PutFriendRequestsAction implements Action{
    
    private Menu menu;
    
    public PutFriendRequestsAction(Menu menu) {
        this.menu = menu;
    }
    
    @Override
    public void execute(Request request) {
        List<String> playerUsernames = new ArrayList<>();
        for (Map.Entry<String, String> entry : request.getBody().entrySet()) {
            if (entry.getKey().contains("friendrequest")) {
                playerUsernames.add(entry.getValue());
            }
        }
        this.menu.openFriendRequest(playerUsernames);
    }

    @Override
    public String getType() {
        return "putfriendrequests";
    }
}
