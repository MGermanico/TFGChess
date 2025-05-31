/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.register;

import com.connutils.Action;
import com.connutils.Request;

/**
 *
 * @author migue
 */

class ActionLoged implements Action{
    private final Register register;
    
    public ActionLoged(Register register) {
        this.register = register;
    }

    @Override
    public void execute(Request request) {
        this.register.loged();
    }

    @Override
    public String getType() {
        return "loged";
    }
}
