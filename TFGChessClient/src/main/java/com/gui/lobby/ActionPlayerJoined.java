/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.lobby;

import com.connutils.Action;
import com.connutils.Request;

/**
 *
 * @author migue
 */
 class ActionPlayerJoined extends ActionLobby{

    public ActionPlayerJoined(Lobby lobby) {
        super(lobby);
    }
    
    @Override
    public void execute(Request request) {
        this.lobby.playerJoined(request.getOrDefault("username", "J2 (no-name)"));
    }
    @Override
    public String getType() {
        return "playerjoined";
    }
}
