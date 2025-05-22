/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.gui.general;

import com.gui.general.principalframe.PrincipalFrame;
import com.conn.Client;
import com.connutils.Request;
import com.utils.ServerCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.connutils.Action;
import com.utils.GUIUtils;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author migue
 */
public class Login extends javax.swing.JPanel implements Requestable{

    /**
     * Creates new form Login
     */
    
    private PrincipalFrame principalFrame;
    
    private List<Action> actions = new ArrayList<Action>(){{
        add(new ExitAction());
        add(new LogedAction());
    }};
    
    public Login(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        
        initComponents();
        GUIUtils.configureDefaultMessageOnTextFields(List.of(this.passwordText, this.usernameText));
        
        hidePassword();
        this.showPassButton.setFocusable(false);
    }
    
    private void hidePassword(){
        this.passwordText.setEchoChar('•');
        this.showPassButton.setIcon(GUIUtils.resizeImageIcon(new ImageIcon("src/main/resources/icons/general/dontShowPassword.png"), 22, 22));
    }
    private void showPassword(){
        this.passwordText.setEchoChar((char) 0);
        this.showPassButton.setIcon(GUIUtils.resizeImageIcon(new ImageIcon("src/main/resources/icons/general/showPassword.png"), 22, 22));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        showPassButton = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INICIA SESIÓN");

        jLabel2.setText("Password");

        jLabel3.setText("Username");

        usernameText.setText("Nombre de Usuario");
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        loginButton.setText("Acceder");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Registrarse");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        showPassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassButtonActionPerformed(evt);
            }
        });

        passwordText.setText("Contraseña");
        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(loginButton)
                                .addGap(18, 18, 18)
                                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(259, 259, 259)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(114, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addComponent(jLabel3)
                    .addContainerGap(398, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(showPassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton)
                    .addComponent(loginButton))
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(121, 121, 121)
                    .addComponent(jLabel3)
                    .addContainerGap(201, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextActionPerformed
        loginButtonActionPerformed(evt);
    }//GEN-LAST:event_usernameTextActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = this.usernameText.getText();
        String password = this.passwordText.getText();
        if (
                !username.isEmpty() &&
                !password.isEmpty()
                ) {
            try {
                Client.login(username, password);
            } catch (JsonProcessingException ex) {
                JOptionPane.showMessageDialog(this, "NOMBRE O USUARIO NO VÁLIDO");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "NO SE PUDO CONECTAR CON EL SERVIDOR");
            }
        }else{
            JOptionPane.showMessageDialog(this, "RELLENA LOS CAMPOS PARA CONTINUAR");
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        principalFrame.setUp(PrincipalFrame.SETUP.SETUP_REGISTER);
    }//GEN-LAST:event_registerButtonActionPerformed

    private void showPassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassButtonActionPerformed
        if (this.passwordText.getEchoChar() == '•') {
            showPassword();
        }else{
            hidePassword();
        }
    }//GEN-LAST:event_showPassButtonActionPerformed

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextActionPerformed
        loginButtonActionPerformed(evt);
    }//GEN-LAST:event_passwordTextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton showPassButton;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables

    @Override
    public void gotRequest(Request request) {
        for (Action action : actions) {
            if(action.getType().equals(request.getHeader()))
                action.execute(request);
        }
    }
    
    private class ExitAction implements Action{
        @Override
        public void execute(Request request) {
            String codeStr = request.get("code");
            if (codeStr != null && codeStr.matches("\\d+")) {
                int code = Integer.parseInt(codeStr);
                
                String message = "Error desconocido";
                if (code == ServerCode.ALREADY_CONNECTED_ACCOUNT) {
                    message = "Cuenta ya iniciada";
                }else if (code == ServerCode.INCORRECT_PASSWORD) {
                    message = "Contraseña incorrecta";
                }else if (code == ServerCode.NOT_FOUND_PLAYER) {
                    message = "No existe ese usuario";
                }else if (code == ServerCode.USED_USERNAME) {
                    message = "Ese nombre de usuario ya está en uso";
                }else if (code == ServerCode.OK) {
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
    private class LogedAction implements Action{
        @Override
        public void execute(Request request) {
            principalFrame.setUp(PrincipalFrame.SETUP.SETUP_MENU);
        }
        @Override
        public String getType() {
            return "loged";
        }
    }
    
}
