/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.principalframe;

import com.gui.lobby.Lobby;
import com.gui.joingame.JoinGame;
import com.gui.creategame.CreateGame;
import com.connutils.Request;
import com.formdev.flatlaf.FlatDarkLaf;
import com.gui.login.Login;
import com.gui.menu.Menu;
import com.gui.register.Register;
import com.gui.general.Requestable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Optional;
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
        SETUP_JOIN_GAME,
        SETUP_ACCOUNT
    };
    public static enum SIZE {
        SIZE_SMALL,
        SIZE_MEDIUM,
        SIZE_FULLSCREEN
    };
    
    JPanel back;
    Color themeColor;
    SIZE boardSize;
    
    public PrincipalFrame() throws HeadlessException, IOException {
        back = new JPanel();
        themeColor = new Color(0, 0, 51);
        setBoardSize(SIZE.SIZE_FULLSCREEN);
        
        this.setVisible(true);
        this.add(back);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new Color(51, 51, 51));
        back.setBackground(new Color(51, 51, 51));
//        this.setResizable(false);
        setUp(SETUP.SETUP_LOGIN);
        this.setLocationRelativeTo(null);
    }
    
    public void setUp(SETUP type) {
        Optional<SetUpCommand> setUpCommandOptional = SetUpFactory.getSetUpCommand(type);
        if (!setUpCommandOptional.isEmpty()) {
            setUpCommandOptional.get().execute(this);
        }
    }
    
    public void setUpGame(Lobby lobby){
        back.removeAll();
        back.add(lobby);
        this.pack();
//        this.setLocationRelativeTo(null);
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

    public SIZE getBoardSize() {
       return this.boardSize;
    }
    public Dimension getBoardDimension() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        if (boardSize == SIZE.SIZE_SMALL) {
            d.setSize(d.getWidth()*0.5 + 150, d.getHeight()*0.25 + 325);
        }else if (boardSize == SIZE.SIZE_MEDIUM) {
            d.setSize(d.getWidth()*0.55 + 300, d.getHeight()*0.5 + 250);
        }
        return d;
    }

    public void setBoardSize(SIZE boardSize) {
        this.boardSize = boardSize;
    }
    
    public Color getThemeColor() {
        return themeColor;
    }
    
    public void setThemeColor(Color color) {
        this.themeColor = color;
    }
    
}
