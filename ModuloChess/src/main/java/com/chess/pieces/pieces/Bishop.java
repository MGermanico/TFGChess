/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.pieces;

import com.chess.general.Position;
import com.chess.pieces.movements.DiagonalMovement;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 * @author migue
 */
@JsonTypeName("bishop")
public class Bishop extends Piece{

    public Bishop(Position position, boolean white) {
        super(position, white);
    }

    @Override
    public void addMovements() {
        DiagonalMovement upRightDiagonalMovement = new DiagonalMovement(true, true, null);
        DiagonalMovement upLeftDiagonalMovement = new DiagonalMovement(true, false, null);
        DiagonalMovement downRightDiagonalMovement = new DiagonalMovement(false, true, null);
        DiagonalMovement downLeftDiagonalMovement = new DiagonalMovement(false, false, null);
        
        getMovements().add(upRightDiagonalMovement);
        getMovements().add(upLeftDiagonalMovement);
        getMovements().add(downRightDiagonalMovement);
        getMovements().add(downLeftDiagonalMovement);
    }

    @Override
    public DisplayableCell clone() {
        return new Bishop(position, white);
    }

    @Override
    public String toString() {
        return "BH";
    }
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public Bishop() {
    }
    // </editor-fold> 

    
}
