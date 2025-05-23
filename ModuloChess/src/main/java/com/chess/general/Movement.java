/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.general;

import com.chess.pieces.Piece;
import com.chess.pieces.pieces.Bishop;
import com.chess.pieces.pieces.Pawn;
import com.chess.pieces.pieces.Rook;

/**
 *
 * @author migue
 */
public class Movement {

    private Position from;
    private Position to;
    private boolean whiteMove;
    private boolean eating;
    private Piece piece;

    public Movement() {
    }

    public Movement(Position from, Position to, boolean whiteMove, boolean eating, Piece piece) {
        this.from = from;
        this.to = to;
        this.whiteMove = whiteMove;
        this.eating = eating;
        this.piece = piece;
    }

    public boolean isEating() {
        return eating;
    }

    public boolean isWhiteMove() {
        return whiteMove;
    }

    public Piece getPiece() {
        return piece;
    }
    
    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public void setEating(boolean eating) {
        this.eating = eating;
    }
    
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    
    public void setFrom(Position from) {
        this.from = from;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    public void setWhiteMove(boolean whiteMove) {
        this.whiteMove = whiteMove;
    }

    @Override
    public String toString() {
        String str = "";
        
        if (piece.getClass() != Pawn.class) {
            str += this.piece.toName().charAt(0) + "";
        }
        if (eating) {
            if (piece.getClass() == Pawn.class) {
                str += from.toStringColumn();
            }
            str += "x";
        }
        str += this.to.toString();
        
        return str;
    }
    
    public static void main(String[] args) {
        Position p1 = new Position(0, 1);
        Position p2 = new Position(0, 2);
        
        Movement mov = new Movement(p1, p2, true, false, new Pawn(p1.clone(), true));
        
        System.out.println(mov);
    }
}
