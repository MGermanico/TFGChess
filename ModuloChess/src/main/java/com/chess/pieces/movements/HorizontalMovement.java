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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author migue
 */
public class HorizontalMovement extends RectilinealMovement{

    public HorizontalMovement(boolean toRight, Integer maxSteps) {
        super(null, toRight, maxSteps);
    }

    @Override
    protected boolean isCorrectWay(Position from, Position to) {
        return from.isToTheLeftOf(to) == getToRight();
    }

    @Override
    protected int isCorrectMove(Position from, Position to) {
        if(from.getRow()!= to.getRow())return ChessCode.IS_NOT_HORIZONTAL;
        
        return ChessCode.OK;
    }
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public HorizontalMovement() {
    }
    
    // </editor-fold> 
}
