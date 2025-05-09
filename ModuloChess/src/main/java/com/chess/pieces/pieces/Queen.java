/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.pieces;

import com.chess.general.Position;
import com.chess.pieces.movements.DiagonalMovement;
import com.chess.pieces.movements.HorizontalMovement;
import com.chess.pieces.movements.VerticalMovement;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 * @author migue
 */
@JsonTypeName("queen")
public class Queen extends Piece{

    public Queen(Position position, boolean white) {
        super(position, white);
    }

    @Override
    public void setMovements() {
        DiagonalMovement upRightDiagonalMovement = new DiagonalMovement(true, true, null);
        DiagonalMovement upLeftDiagonalMovement = new DiagonalMovement(true, false, null);
        DiagonalMovement downRightDiagonalMovement = new DiagonalMovement(false, true, null);
        DiagonalMovement downLeftDiagonalMovement = new DiagonalMovement(false, false, null);
        VerticalMovement upMove = new VerticalMovement(true, null);
        VerticalMovement downMove = new VerticalMovement(false, null);
        HorizontalMovement rightMove = new HorizontalMovement(true, null);
        HorizontalMovement leftMove = new HorizontalMovement(false, null);
        
        getMovements().add(upRightDiagonalMovement);
        getMovements().add(upLeftDiagonalMovement);
        getMovements().add(downRightDiagonalMovement);
        getMovements().add(downLeftDiagonalMovement);
        getMovements().add(upMove);
        getMovements().add(downMove);
        getMovements().add(rightMove);
        getMovements().add(leftMove);
    }

    @Override
    public DisplayableCell clone() {
        return new Queen(position, white);
    }

    @Override
    public String toString() {
        return "QU";
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public Queen() {
    }
    // </editor-fold> 

    
}
