/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.movements;

import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.chess.pieces.DisplayableCell;
import com.utils.ChessCode;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author migue
 */
public class KnightMovement extends Movement{

    @Override
    public int canIMove(Position from, Position to, DisplayableCell[][] boardInstance) {
        int c1 = from.getColumn();
        int c2 = to.getColumn();
        int r1 = from.getRow();
        int r2 = to.getRow();
        
        boolean twoUp = r2 == r1+2;
        boolean oneUp = r2 == r1+1;
        boolean oneDown = r2 == r1-1;
        boolean twoDown = r2 == r1-2;
        
        boolean twoRight = c2 == c1+2;
        boolean oneRight = c2 == c1+1;
        boolean oneLeft = c2 == c1-1;
        boolean twoLeft = c2 == c1-2;
        
        if (
                (twoUp && (oneLeft || oneRight)) ||
                (oneUp && (twoLeft || twoRight)) ||
                (oneDown && (twoLeft || twoRight)) ||
                (twoDown && (oneLeft || oneRight))
                ) return ChessCode.OK;
        return ChessCode.CANT_MOVE_THERE;
    }

    @Override
    public Set<Position> whereCanI(Position from, DisplayableCell[][] boardInstance, boolean attack, boolean imWhite) {
        Set<Position> positions = new HashSet<>();
        
        Set<Position> checkingPositions = new HashSet<>();
        checkingPositions.add(from.clone().positionFromHere(2, 1));
        checkingPositions.add(from.clone().positionFromHere(2, -1));
        checkingPositions.add(from.clone().positionFromHere(1, 2));
        checkingPositions.add(from.clone().positionFromHere(1, -2));
        checkingPositions.add(from.clone().positionFromHere(-1, 2));
        checkingPositions.add(from.clone().positionFromHere(-1, -2));
        checkingPositions.add(from.clone().positionFromHere(-2, 1));
        checkingPositions.add(from.clone().positionFromHere(-2, -1));
        
        int actualCode;
        for (Position checkingPos : checkingPositions) {
            if(Chessboard.isItInside(checkingPos, boardInstance)){
                actualCode = checkIfCanI(attack, boardInstance[checkingPos.getColumn()][checkingPos.getRow()], imWhite);
                if(actualCode == ChessCode.OK || actualCode == ChessCode.EATEN) positions.add(checkingPos);
            }
        }
        
        return positions;
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public KnightMovement() {
    }
    
    // </editor-fold> 
    
}
