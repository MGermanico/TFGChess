/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.lobby;

import com.chess.general.ChessManager;
import com.connutils.Action;
import com.connutils.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

/**
 *
 * @author migue
 */
class ActionUpdateBoard extends ActionLobby{

    public ActionUpdateBoard(Lobby lobby) {
        super(lobby);
    }
        @Override
        public void execute(Request request) {
            try {
                this.lobby.updateBoardMove(ChessManager.fromJSON(request.get("board")), request.get("white").equals("true"));
            } catch (JsonProcessingException ex) {
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public String getType() {
            return "updatedBoard";
        }
    }
