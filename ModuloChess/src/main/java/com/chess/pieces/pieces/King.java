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
import com.chess.pieces.Moved;
import com.chess.pieces.Piece;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 *
 * @author migue
 */
@JsonTypeName("king")
public class King extends Piece implements Moved{

    public King(Position position, boolean white) {
        super(position, white);
    }

    @Override
    public void addMovements() {
        DiagonalMovement upRightDiagonalMovement = new DiagonalMovement(true, true, 1);
        DiagonalMovement upLeftDiagonalMovement = new DiagonalMovement(true, false, 1);
        DiagonalMovement downRightDiagonalMovement = new DiagonalMovement(false, true, 1);
        DiagonalMovement downLeftDiagonalMovement = new DiagonalMovement(false, false, 1);
        VerticalMovement upMove = new VerticalMovement(true, 1);
        VerticalMovement downMove = new VerticalMovement(false, 1);
        HorizontalMovement rightMove = new HorizontalMovement(true, 1);
        HorizontalMovement leftMove = new HorizontalMovement(false, 1);
        
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
        King king = new King(position, white);
        king.setMoved(hasMoved);
        return king;
    }

    @Override
    public String toString() {
        return "KG";
    }
    
    boolean hasMoved = false;
    
    @Override
    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public King() {
    }
    public boolean isHasMoved() {
        return hasMoved;
    }
    // </editor-fold>
    
}
