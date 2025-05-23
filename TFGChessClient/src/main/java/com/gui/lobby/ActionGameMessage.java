/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.lobby;

import com.connutils.Request;

/**
 *
 * @author migue
 */
class ActionGameMessage extends ActionLobby {

    public ActionGameMessage(Lobby lobby) {
        super(lobby);
    }

    @Override
    public void execute(Request request) {
        if (request.contains("message")) {
            this.lobby.addGameMessage(false, request.get("message"));
        }
    }

    @Override
    public String getType() {
        return "gamemessage";
    }
}
