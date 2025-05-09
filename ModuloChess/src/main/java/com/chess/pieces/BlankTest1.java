/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces;

import com.chess.general.Position;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
@JsonTypeName("blank1")
public class BlankTest1 extends DisplayableCell{

    public BlankTest1(Position position) {
        this.position = position;
    }

    @Override
    public DisplayableCell clone() {
        return new BlankTest1(position);
    }

    @Override
    public boolean blankCell() {
        return true;
    }
    
    @Override
    public String toString() {
        return "OO";
    }
}
