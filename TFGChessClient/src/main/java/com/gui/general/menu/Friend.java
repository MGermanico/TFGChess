/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.menu;

import java.awt.Color;

/**
 *
 * @author migue
 */
public class Friend implements Comparable<Friend>{
    String username;
    String state;

    public Friend(String username, String state) {
        this.username = username;
        this.state = state;
    }

    public Color getColor() {
        if (state.equals("online")) {
            return new Color(0, 100, 0);
        }else{
            return new Color(100, 0, 0);
        }
    }

    @Override
    public int compareTo(Friend o) {
        if (this.getColor().getRed() != o.getColor().getRed()) {
            return this.getColor().getRed() - o.getColor().getRed();
        }
        return this.username.compareTo(o.username);
    }


}
