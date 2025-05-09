/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.movements;

import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
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
public class VerticalMovement extends RectilinealMovement{
    
    public VerticalMovement(boolean toUp, Integer maxSteps) {
        super(toUp, null, maxSteps);
    }

    public void setMaxSteps(Integer maxSteps) {
        this.maxSteps = maxSteps;
    }

    @Override
    protected boolean isCorrectWay(Position from, Position to) {
        return from.isUnderOf(to) == getToUp();
    }
    
    @Override
    protected int isCorrectMove(Position from, Position to) {
        if(from.getColumn() != to.getColumn())return ChessCode.IS_NOT_VERTICAL;
        
        return ChessCode.OK;
    }
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public VerticalMovement() {
    }
    
    // </editor-fold> 

    
}
