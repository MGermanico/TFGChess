/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.login;

import com.connutils.Action;
import com.connutils.Request;
import com.gui.principalframe.PrincipalFrame;
import com.utils.ServerCode;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
class ActionExit implements Action {

    @Override
    public void execute(Request request) {
        String codeStr = request.get("code");
        if (codeStr != null && codeStr.matches("\\d+")) {
            int code = Integer.parseInt(codeStr);

            String message = "Error desconocido";
            if (code == ServerCode.ALREADY_CONNECTED_ACCOUNT) {
                message = "Cuenta ya iniciada";
            } else if (code == ServerCode.INCORRECT_PASSWORD) {
                message = "Contraseña incorrecta";
            } else if (code == ServerCode.NOT_FOUND_PLAYER) {
                message = "No existe ese usuario";
            } else if (code == ServerCode.USED_USERNAME) {
                message = "Ese nombre de usuario ya está en uso";
            } else if (code == ServerCode.OK) {
                message = "OK ?????";
            }

            JOptionPane.showMessageDialog(null, message);
        }
    }

    @Override
    public String getType() {
        return "exit";
    }
}
