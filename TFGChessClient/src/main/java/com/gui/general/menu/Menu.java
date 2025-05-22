/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.gui.general.menu;

import com.gui.general.principalframe.PrincipalFrame;
import com.connutils.Request;
import com.connutils.RequestBuilder;
import com.conn.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.connutils.Action;
import com.connutils.chatlog.Message;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gui.general.FriendRequestFrame;
import com.gui.general.Requestable;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class Menu extends javax.swing.JPanel implements Requestable{
    
    PrincipalFrame principalFrame;
    ChatFrame chat;
    FriendTableCellRenderer renderer;
    private Set<String> printedBordersUsernames;
          
    private List<Action> actions = new ArrayList<Action>(){{
        add(new PutFriendsAction(Menu.this));
        add(new PutFriendRequestsAction(Menu.this));
        add(new UpdateFriendRequestAlert(Menu.this));
        add(new ChatLogAction(Menu.this));
    }};
    
    public Menu(PrincipalFrame principalFrame) {
        this.principalFrame = principalFrame;
        initComponents();
        updateFriends();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        friendTableBackPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        friendTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        friendRequestButton = new javax.swing.JButton();
        showFriendRequestButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));

        friendTableBackPanel.setBackground(new java.awt.Color(51, 51, 51));

        friendTable.setBackground(new java.awt.Color(153, 153, 153));
        friendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Amigos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(friendTable);
        if (friendTable.getColumnModel().getColumnCount() > 0) {
            friendTable.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout friendTableBackPanelLayout = new javax.swing.GroupLayout(friendTableBackPanel);
        friendTableBackPanel.setLayout(friendTableBackPanelLayout);
        friendTableBackPanelLayout.setHorizontalGroup(
            friendTableBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendTableBackPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        friendTableBackPanelLayout.setVerticalGroup(
            friendTableBackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendTableBackPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Cuenta");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Historial");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("UNIRSE A SALA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("CREAR SALA");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        friendRequestButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        friendRequestButton.setText("AÑADIR AMIGO");
        friendRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendRequestButtonActionPerformed(evt);
            }
        });

        showFriendRequestButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        showFriendRequestButton.setText("VER SOLICITUDES DE AMISTAD");
        showFriendRequestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showFriendRequestButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(showFriendRequestButton, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(friendRequestButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendRequestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showFriendRequestButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(159, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(friendTableBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(friendTableBackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        principalFrame.setUp(PrincipalFrame.SETUP.SETUP_CREATE_GAME);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        principalFrame.setUp(PrincipalFrame.SETUP.SETUP_JOIN_GAME);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void friendRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendRequestButtonActionPerformed
        String friendName = JOptionPane.showInputDialog("Username");
        try {
            Client.sendRequest(
                    RequestBuilder.createRequest("addfriend")
                    .put("username", friendName)
                    .build()
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_friendRequestButtonActionPerformed

    private void showFriendRequestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFriendRequestButtonActionPerformed
        try {
            Client.sendRequest(
                    RequestBuilder.createRequest("getfriendrequests")
                    .build()
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_showFriendRequestButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton friendRequestButton;
    private javax.swing.JTable friendTable;
    private javax.swing.JPanel friendTableBackPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton showFriendRequestButton;
    // End of variables declaration//GEN-END:variables

    private void updateFriends(){
        try {
            Client.sendRequest(
                    RequestBuilder.createRequest("getfriends")
                    .build()
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    void putFriends(List<Friend> friends){
        renderer = new FriendTableCellRenderer(friendTable, friends);
        
        friendTable.setDefaultRenderer(Object.class, renderer);
        
        friendTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = friendTable.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    openChat(friendTable.getValueAt(row, 0).toString());
                    friendTable.clearSelection();
                }
            }
        });
        
    }
    
    @Override
    public void gotRequest(Request request) {
        for (Action action : actions) {
            if(action.getType().equals(request.getHeader()))
                action.execute(request);
        }
    }

    private void openChat(String title) {
        if(chat != null) chat.dispose();
        removePrintedRendererBorder(title);
        chat = new ChatFrame(principalFrame, this, title);
        Point friendTableLocation = this.principalFrame.getLocationOnScreen();
        friendTableLocation.translate(this.friendTableBackPanel.getX() - chat.getWidth() + 35, this.friendTableBackPanel.getY());
        chat.setLocation(friendTableLocation);
        
        try {
            Client.sendRequest(
                    new RequestBuilder("getmessages")
                            .put("with", title)
                            .build()
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void printRendererBorder(String otherUsername) {
        Menu.this.renderer.printBorder(otherUsername);
        friendTable.clearSelection();
        friendTable.repaint();
    }
    void removePrintedRendererBorder(String otherUsername) {
        Menu.this.renderer.removePrintedBorder(otherUsername);
        friendTable.clearSelection();
        friendTable.repaint();
    }
    void setFriendRequestAlert(boolean active){
        if (active) {
            showFriendRequestButton.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3));
        } else {
            showFriendRequestButton.setBorder(null);
        }
    }
    void openFriendRequest(List<String> playerUsernames){
        new FriendRequestFrame(principalFrame, playerUsernames, java.awt.MouseInfo.getPointerInfo().getLocation());
        updateFriends();
    }
    
    
}
