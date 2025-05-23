/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.gui.register;

import com.gui.principalframe.PrincipalFrame;
import com.conn.Client;
import com.connutils.Action;
import com.connutils.Request;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gui.general.Requestable;
import com.utils.GUIUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class Register extends javax.swing.JPanel implements Requestable{

    /**
     * Creates new form Login
     */
    
    private PrincipalFrame principalFrame;
    private List<Action> actions = new ArrayList<Action>(){{
        add(new ActionExit());
        add(new ActionLoged(Register.this));
    }};
    
    public Register(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        
        initComponents();
        GUIUtils.configureDefaultMessageOnTextFields(List.of(this.passwordText, this.usernameText, this.confirmPasswordText));
        
        hidePassword();
        this.showPassButton.setFocusable(false);
    }

    private void hidePassword(){
        this.passwordText.setEchoChar('•');
        this.confirmPasswordText.setEchoChar('•');
        this.showPassButton.setIcon(GUIUtils.resizeImageIcon(new ImageIcon("src/main/resources/icons/general/dontShowPassword.png"), 22, 22));
    }
    private void showPassword(){
        this.passwordText.setEchoChar((char) 0);
        this.confirmPasswordText.setEchoChar((char) 0);
        this.showPassButton.setIcon(GUIUtils.resizeImageIcon(new ImageIcon("src/main/resources/icons/general/showPassword.png"), 22, 22));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();
        showPassButton = new javax.swing.JButton();
        confirmPasswordText = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGÍSTRATE");

        jLabel2.setText("Password");

        jLabel3.setText("Username");

        usernameText.setText("Nombre de Usuario");
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        registerButton.setText("Crear");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        loginButton.setText("Iniciar sesión");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passwordText.setText("Contraseña");

        showPassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPassButtonActionPerformed(evt);
            }
        });

        confirmPasswordText.setText("Confirmacion");
        confirmPasswordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordTextActionPerformed(evt);
            }
        });

        jLabel4.setText("Confirm");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(175, 175, 175)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(registerButton)
                                        .addGap(18, 18, 18)
                                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(confirmPasswordText, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(confirmPasswordText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(showPassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String username = this.usernameText.getText();
        String password = this.passwordText.getText();
        String passwordConfirmation = this.confirmPasswordText.getText();
        
        if (
                !username.isEmpty() &&
                !password.isEmpty() &&
                !passwordConfirmation.isEmpty() &&
                password.equals(passwordConfirmation)
                ) {
            try {
                Client.register(username, password);
            } catch (JsonProcessingException ex) {
                JOptionPane.showMessageDialog(this, "NOMBRE O USUARIO NO VÁLIDO");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "NO SE PUDO CONECTAR CON EL SERVIDOR");
            }
        }else{
            JOptionPane.showMessageDialog(this, "RELLENA CORRECTAMENTE LOS CAMPOS PARA CONTINUAR");
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        principalFrame.setUp(PrincipalFrame.SETUP.SETUP_LOGIN);
    }//GEN-LAST:event_loginButtonActionPerformed

    private void showPassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPassButtonActionPerformed
        if (this.passwordText.getEchoChar() == '•') {
            showPassword();
        }else{
            hidePassword();
        }
    }//GEN-LAST:event_showPassButtonActionPerformed

    private void confirmPasswordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordTextActionPerformed
        registerButtonActionPerformed(evt);
    }//GEN-LAST:event_confirmPasswordTextActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPasswordText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton showPassButton;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables

    @Override
    public void gotRequest(Request request) {
        actions.stream().filter(action -> action.getType().equals(request.getHeader())).forEach(action -> action.execute(request));
    }

    void loged() {
        principalFrame.setUp(PrincipalFrame.SETUP.SETUP_MENU);
    }
}
