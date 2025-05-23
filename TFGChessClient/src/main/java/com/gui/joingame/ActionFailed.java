/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.joingame;

import com.gui.creategame.*;
import com.connutils.Action;
import com.connutils.Request;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
class ActionFailed implements Action {

    @Override
    public void execute(Request request) {
        String errorMessage = request.getOrDefault("message", "Error desconocido");
        JOptionPane.showMessageDialog(null, errorMessage);
    }

    @Override
    public String getType() {
        return "failed";
    }
}
