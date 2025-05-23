/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.login;

import com.connutils.Action;
import com.connutils.Request;
import com.utils.ServerCode;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */

class ActionLoged implements Action{
    private Login login;
    
    public ActionLoged(Login login) {
        this.login = login;
    }

    @Override
    public void execute(Request request) {
        this.login.loged();
    }

    @Override
    public String getType() {
        return "loged";
    }
}
