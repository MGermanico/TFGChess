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
@JsonTypeName("blank")
public class Blank extends DisplayableCell {


    public Blank(Position position) {
        this.position = position;
    }

    @Override
    public DisplayableCell clone() {
        return new Blank(position);
    }

    @Override
    public boolean blankCell() {
        return true;
    }

    @Override
    public String toString() {
        return "  ";
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public Blank() {
    }
    // </editor-fold>


}
