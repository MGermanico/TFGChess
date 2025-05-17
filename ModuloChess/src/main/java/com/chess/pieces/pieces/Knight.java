/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.pieces;

import com.chess.general.Position;
import com.chess.pieces.movements.KnightMovement;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 * @author migue
 */
@JsonTypeName("knight")
public class Knight extends Piece{

    public Knight(Position position, boolean white) {
        super(position, white);
    }

    @Override
    public void addMovements() {
        KnightMovement knightMovement = new KnightMovement();
        
        getMovements().add(knightMovement);
    }

    @Override
    public DisplayableCell clone() {
        return new Knight(position, white);
    }

    @Override
    public String toString() {
        return "KN";
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public Knight() {
    }
    // </editor-fold> 

    
}
