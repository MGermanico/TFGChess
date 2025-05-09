/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.pieces;

import com.chess.general.Position;
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
@JsonTypeName("rook")
public class Rook extends Piece  implements Moved{

    public Rook(Position position, boolean white) {
        super(position, white);
    }

    @Override
    public void setMovements() {
        VerticalMovement upMove = new VerticalMovement(true, null);
        VerticalMovement downMove = new VerticalMovement(false, null);
        HorizontalMovement rightMove = new HorizontalMovement(true, null);
        HorizontalMovement leftMove = new HorizontalMovement(false, null);
        
        getMovements().add(upMove);
        getMovements().add(downMove);
        getMovements().add(rightMove);
        getMovements().add(leftMove);
    }

    @Override
    public DisplayableCell clone() {
        return new Rook(position, white);
    }           

    @Override
    public String toString() {
        return "TR";
    }
    
    private boolean hasMoved = false;

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }

    @Override
    public void setMoved(boolean moved) {
        this.hasMoved = moved;
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public Rook() {
    }

    public boolean isHasMoved() {
        return hasMoved;
    }
    // </editor-fold>

    
    
}
