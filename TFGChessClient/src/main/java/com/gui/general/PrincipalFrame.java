/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general;

import com.gui.lobby.Lobby;
import com.gui.lobby.JoinGame;
import com.gui.lobby.CreateGame;
import com.connutils.Request;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author migue
 */
public class PrincipalFrame extends JFrame implements Requestable{
    
    public static enum SETUP {
        SETUP_LOGIN,
        SETUP_REGISTER,
        SETUP_MENU,
        SETUP_CREATE_GAME,
        SETUP_JOIN_GAME
    };
    
    private JPanel back;
    private Color themeColor;
    
    public PrincipalFrame() throws HeadlessException, IOException {
        back = new JPanel();
        themeColor = new Color(0, 0, 51);
        
        this.setVisible(true);
        this.add(back);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(51, 51, 51));
        back.setBackground(new Color(51, 51, 51));
//        this.setResizable(false);
        setUp(SETUP.SETUP_LOGIN);
    }
    
    public void setUp(SETUP type) {
        Dimension frameSize = new Dimension(1000, 700);
        back.removeAll();
        if (type == SETUP.SETUP_LOGIN) {
            frameSize = new Dimension(550, 338);
            Login login = new Login(this);
            back.add(login);
        }else if (type == SETUP.SETUP_REGISTER) {
            frameSize = new Dimension(550, 338);
            Register register = new Register(this);
            back.add(register);
        } else if (type == SETUP.SETUP_MENU) {
            Menu menu = new Menu(this);
            back.add(menu);
        }else if (type == SETUP.SETUP_CREATE_GAME) {
            CreateGame createGame = new CreateGame(this);
            back.add(createGame);
        }else if (type == SETUP.SETUP_JOIN_GAME) {
            JoinGame joinGame = new JoinGame(this);
            back.add(joinGame);
        }
        this.setSize(frameSize);
        this.setLocationRelativeTo(null);
        back.validate();
        back.repaint();
    }
    
    public void setUpGame(Lobby lobby){
        back.removeAll();
        back.add(lobby);
        this.pack();
        this.setLocationRelativeTo(null);
        back.validate();
        back.repaint();
    }

    @Override
    public void gotRequest(Request request) {
        Requestable requestable;
        for (Component component : back.getComponents()) {
            if (component instanceof Requestable) {
                requestable = (Requestable) component;
                requestable.gotRequest(request);
            }
        }
    }

    public Color getThemeColor() {
        return themeColor;
    }
}
