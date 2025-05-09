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
@JsonTypeName("pawn")
public class Pawn extends Piece implements Moved{
    
    public Pawn(Position position, boolean white) {
        super(position, white);
    }
    
    private VerticalMovement verticalMove;
    
    @Override
    public void setMovements() {
        verticalMove = new VerticalMovement(white, 2);
        DiagonalMovement diagonalRightMove = new DiagonalMovement(white, true, 1);
        DiagonalMovement diagonalLeftMove = new DiagonalMovement(white, false, 1);
        
        verticalMove.setHaveToNotEat(true);
        diagonalRightMove.setHaveToEat(true);
        diagonalLeftMove.setHaveToEat(true);
        
        super.getMovements().add(verticalMove);
        super.getMovements().add(diagonalRightMove);
        super.getMovements().add(diagonalLeftMove);
    }

    @Override
    public DisplayableCell clone() {
//        System.out.println("creo instancia de peon: " + position + " w = " + white + " hasmoved = " + hasMoved);
        Pawn pawn = new Pawn(position, white);
        pawn.setMoved(hasMoved);
        return pawn;
    }

    @Override
    public String toString() {
//        return position.getRow()+""+position.getColumn();
        return "PN";
    }
    
    private boolean hasMoved = false;
    
    @Override
    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public void setMoved(boolean moved) {
        if(moved) verticalMove.setMaxSteps(1);
        this.hasMoved = moved;
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public Pawn() {
    }
    public boolean isHasMoved() {
        return hasMoved;
    }
    // </editor-fold> 

    

    
}
