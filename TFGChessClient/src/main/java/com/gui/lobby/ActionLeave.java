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
class ActionLeave extends ActionLobby {

    public ActionLeave(Lobby lobby) {
        super(lobby);
    }

    @Override
    public void execute(Request request) {
        if (request.getOrDefault("won", "false").equals("true")) {
            this.lobby.win();
        } else {
            this.lobby.loose();
        }
    }

    @Override
    public String getType() {
        return "leave";
    }
}
