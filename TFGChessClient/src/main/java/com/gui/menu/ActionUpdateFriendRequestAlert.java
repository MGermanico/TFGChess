/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.menu;

import com.connutils.Action;
import com.connutils.Request;
import java.awt.Color;
import javax.swing.BorderFactory;

/**
 *
 * @author migue
 */
class ActionUpdateFriendRequestAlert implements Action{
    private Menu menu;
    
    public ActionUpdateFriendRequestAlert(Menu menu) {
        this.menu = menu;
    }
    
    @Override
    public void execute(Request request) {
        this.menu.setFriendRequestAlert(request.getOrDefault("active", "false").equals("true"));
    }

    @Override
    public String getType() {
        return "updatefriendrequestalert";
    }
}
