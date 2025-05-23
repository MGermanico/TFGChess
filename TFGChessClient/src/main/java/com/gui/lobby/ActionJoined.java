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
class ActionJoined extends ActionLobby{
    public ActionJoined(Lobby lobby) {
        super(lobby);
    }
    @Override
    public void execute(Request request) {
        this.lobby.joined(request.getOrDefault("ownerusername", "J2 (no-name)"));
    }

    @Override
    public String getType() {
        return "joined";
    }
}
