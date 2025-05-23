/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.lobby;

import com.connutils.Request;
import com.utils.GUIUtils;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
class ActionDrawAnswer extends ActionLobby{

    public ActionDrawAnswer(Lobby lobby) {
        super(lobby);
    }

    @Override
    public void execute(Request request) {
        if (request.getOrDefault("accepteddraw", "false").equals("true")) {
            this.lobby.draw();
        } else {
            String message = this.lobby.getOtherUsername() + " ha denegado las tablas";
            JOptionPane.showMessageDialog(null, GUIUtils.biggerTextHTML(message));
        }
    }

    @Override
    public String getType() {
        return "drawanswer";
    }
}
