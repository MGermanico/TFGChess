/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.lobby;

import com.connutils.Action;

/**
 *
 * @author migue
 */
public abstract class ActionLobby implements Action{
    protected Lobby lobby;

    public ActionLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}
