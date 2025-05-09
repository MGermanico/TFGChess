/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.movements;

import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.chess.pieces.DisplayableCell;
import com.utils.ChessCode;
import com.utils.MathUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author migue
 */
public class DiagonalMovement extends RectilinealMovement{

    public DiagonalMovement(boolean toUp, boolean toRight, Integer maxSteps) {
        super(toUp, toRight, maxSteps);
    }

    @Override
    protected boolean isCorrectWay(Position from, Position to) {
        return !(from.isUnderOf(to) != toUp ||
                from.isToTheLeftOf(to) != toRight);
    }
    
    @Override
    protected int isCorrectMove(Position from, Position to) {
        if(
                MathUtils.distance(from.getColumn(), to.getColumn()) !=
                MathUtils.distance(from.getRow(), to.getRow())
                )return ChessCode.IS_NOT_DIAGONAL;
        
        return ChessCode.OK;
    }
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public DiagonalMovement() {
    }
    
    // </editor-fold> 
}
