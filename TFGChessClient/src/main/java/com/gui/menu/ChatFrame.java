/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.gui.menu;

import com.gui.menu.Menu;
import com.conn.Client;
import com.connutils.RequestBuilder;
import com.connutils.chatlog.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gui.general.ChatPanel;
import com.gui.principalframe.PrincipalFrame;
import com.gui.lobby.Lobby;
import com.utils.GUIUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author migue
 */
public class ChatFrame extends javax.swing.JFrame {

    /**
     * Creates new form ChatFrame
     * @param title
     */
    private PrincipalFrame principalFrame;
    private ChatPanel chatPanel;
    private boolean closeable;
    
    public ChatFrame(PrincipalFrame principalFrame, Menu menu, String title) {
        this.principalFrame = principalFrame;
        this.closeable = false;
        
        this.setUndecorated(true);
        
        initComponents();
        this.jLabel1.setText(title);
        GUIUtils.configureDefaultMessageOnTextFields(List.<JTextField>of(this.textLabel));
        chatPanel = new ChatPanel();
        
        setSizes();

        this.jPanel1.add(chatPanel);
        
        this.setVisible(true);
        SwingUtilities.invokeLater(() -> {
            this.toFront();                // Lo trae al frente
            this.requestFocus();           // Pide el foco para el frame
            this.requestFocusInWindow();   // Alternativa mejor en algunos casos
        });
        
        new javax.swing.Timer(500, e -> closeable = true).start();
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                if (closeable) {
                    menu.chat = null;
                    ChatFrame.this.dispose();
                }
            }
        });
    }
    
    public void updateMessages(List<Message> messages){
        this.chatPanel.clear();
        messages.stream().forEach(message -> addMessage(message.isSelf(), message.getMessage()));
    }
    
    private void addMessage(boolean self, String text){
        String sender;
        Color selfColor = Color.LIGHT_GRAY;
        Color otherColor = principalFrame.getThemeColor();
            
        Color messageColor;
        Color foregroundColor;
        if (self) {
            messageColor = selfColor;
            foregroundColor = otherColor;
            sender = Client.getUsername();
        }else{
            messageColor = otherColor;
            foregroundColor = selfColor;
            sender = this.jLabel1.getText();
        }
        this.chatPanel.addText(sender, text, messageColor, foregroundColor);
        this.chatPanel.validate();
    }
    
    private void sendMessage(String text){
        try {
            Client.sendRequest(
                    RequestBuilder.createRequest("sendmessage")
                            .put("receiver", jLabel1.getText())
                            .put("message", text)
                            .build()
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textLabel = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        textLabel.setText("Escribe tu mensaje...                                   [ENTER]");
        textLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLabelActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLabelActionPerformed
        sendMessage(this.textLabel.getText());
        this.textLabel.setText("");
    }//GEN-LAST:event_textLabelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField textLabel;
    // End of variables declaration//GEN-END:variables

    private void setSizes() {
        Dimension d = new Dimension(300, 450);
        this.chatPanel.setSize(d);
        this.chatPanel.setPreferredSize(d);
        this.chatPanel.setMaximumSize(d);
        this.chatPanel.setMinimumSize(d);
        this.jPanel1.setSize(d);
        this.jPanel1.setPreferredSize(d);
        this.jPanel1.setMaximumSize(d);
        this.jPanel1.setMinimumSize(d);
    }

    String getOtherUsername() {
        return this.jLabel1.getText();
    }
}
