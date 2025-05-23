/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.creategame;

import com.connutils.Action;
import com.connutils.Request;

/**
 *
 * @author migue
 */
class ActionJoined implements Action{
    private CreateGame createGame;

    public ActionJoined(CreateGame createGame) {
        this.createGame = createGame;
    }
    
    @Override
    public void execute(Request request) {
        this.createGame.joined(request);
    }

    @Override
    public String getType() {
        return "joined";
    }
}
