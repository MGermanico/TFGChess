/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general;

import com.connutils.RequestBuilder;
import com.conn.Client;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

/**
 *
 * @author migue
 */
public class FriendRequestFrame extends JDialog{

    public FriendRequestFrame(JFrame parent, List<String> friendRequests, Point position) {
        super(parent, "Mi Modal", true);
        
        this.friendRequests = friendRequests;
        this.back = Box.createVerticalBox();
        this.requestList = new JPanel();
        
        
        this.add(back);
        
        this.setUndecorated(true);
        this.setSize(400, 400);
        
        updateList();
        
        addTitle();
        back.add(requestList);
        addClose();
        
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        position.setLocation(position.getX() - this.getWidth()/2, position.getY());
        
        this.setLocation(position);
        
        this.setVisible(true);
    }
    
    public List<String> friendRequests;
    
    Box back;
    JPanel requestList;

    public void updateList() {
        requestList.removeAll();
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        for (String friendRequestUsername : friendRequests) {
            contentPanel.add(
                    DisplayableFriendRequestBuilder.create(friendRequestUsername)
                    .addAceptarJButton(this)
                    .addEliminarJButton(this)
                    .build()
            );
//            contentPanel.add(new JSeparator());
        }
        JScrollPane scrollPane = new JScrollPane(
                contentPanel, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        
        requestList.add(scrollPane);
        back.validate();
        this.repaint();
    }

    private void addTitle() {
        JLabel title = new JLabel("PETICIONES DE AMISTAD");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        JPanel titPanel = new JPanel();
        titPanel.add(title);
        titPanel.setMaximumSize(new Dimension(600, 70));
        back.add(titPanel);
    }

    private void addClose() {
        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });
        closeButton.setBackground(Color.WHITE);
        
        JPanel closePanel = new JPanel();
        closePanel.add(closeButton);
        back.add(closePanel);
    }
    
    private void close() {
        this.dispose();
    }
}

class DisplayableFriendRequestBuilder {

    private String username;
    private JPanel horizontal;

    public DisplayableFriendRequestBuilder(String username) {
        this.username = username;
        this.horizontal = new JPanel();
        horizontal.setLayout(new BoxLayout(horizontal, BoxLayout.X_AXIS));
        horizontal.setBackground(Color.LIGHT_GRAY);
        horizontal.setBorder(BorderFactory.createRaisedBevelBorder());
        
        JLabel usernameLabel = new JLabel("  " + username);
        usernameLabel.setForeground(Color.BLACK);
        
        Dimension labelDimension = new Dimension(180,50);
        usernameLabel.setMaximumSize(labelDimension);
        usernameLabel.setMinimumSize(labelDimension);
        usernameLabel.setPreferredSize(labelDimension);
        
        horizontal.add(usernameLabel);
    }

    public static DisplayableFriendRequestBuilder create(String friendRequestUsername) {
        return new DisplayableFriendRequestBuilder(friendRequestUsername);
    }
    
    public DisplayableFriendRequestBuilder addJButton(FriendRequestFrame owner, String buttonText, ActionListener actionListener){
        JButton button = new JButton(buttonText);
        button.setMaximumSize(new Dimension(50,50));
        button.setMinimumSize(new Dimension(50,50));
        button.setPreferredSize(new Dimension(50,50));
        button.setBackground(Color.WHITE);
        button.addActionListener(actionListener);
        horizontal.add(button);
        return this;
    }
    
    public DisplayableFriendRequestBuilder addAceptarJButton(FriendRequestFrame owner){
        return addJButton(
                owner, 
                "V", 
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Client.sendRequest(
                                    RequestBuilder.createRequest("addfriend")
                                    .put("username", username)
                                    .build()
                            );
                        } catch (JsonProcessingException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        owner.friendRequests.remove(username);
                        owner.updateList();
                    }
                }
        );
    }
    
    public DisplayableFriendRequestBuilder addEliminarJButton(FriendRequestFrame owner){
        return addJButton(
                owner, 
                "X", 
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Client.sendRequest(
                                    RequestBuilder.createRequest("removefriendrequest")
                                    .put("username", username)
                                    .build()
                            );
                        } catch (JsonProcessingException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        owner.friendRequests.remove(username);
                        owner.updateList();
                    }
                }
        );
    }

    public JPanel build() {
        return horizontal;
    }
}
