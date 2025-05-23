/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.joingame;

import com.connutils.Action;
import com.connutils.Request;

/**
 *
 * @author migue
 */
public class ActionJoined implements Action{

    private JoinGame joinGame;

    public ActionJoined(JoinGame joinGame) {
        this.joinGame = joinGame;
    }
    
    @Override
    public void execute(Request request) {
        this.joinGame.joined(request);
    }

    @Override
    public String getType() {
        return "joined";
    }
    
}
