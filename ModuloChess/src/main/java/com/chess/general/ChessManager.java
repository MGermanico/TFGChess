/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.general;

import com.chess.pieces.Piece;
import com.chess.pieces.pieces.Bishop;
import com.chess.pieces.pieces.Knight;
import com.chess.pieces.pieces.Queen;
import com.chess.pieces.pieces.Rook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.utils.ChessCode;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class ChessManager {
    private Chessboard chessboard;
    private boolean whiteTurn = true;
    private int state = STATE_PLAYING;
    
    public static final int STATE_PLAYING = 0;
    public static final int STATE_DRAW = 1;
    public static final int STATE_WHITE_WON = 2;
    public static final int STATE_BLACK_WON = 3;
    
    public ChessManager() {
    }
    
    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    
    public int move(Position from, Position to){
        
         // entrada sin nulls
        if(from == null || to == null) return ChessCode.NULL_VALUE;
        
         // entrada 1 y 2 no son la misma posición
        if(from.isTheSame(to)) return ChessCode.NOT_MOVING;
        
         //entrada 1 dentro del tablero
        if(!chessboard.isItInside(from)) return ChessCode.OUT_OF_BOARD;
        
         //entrada 2 dentro del tablero
        if(!chessboard.isItInside(to)) return ChessCode.OUT_OF_BOARD;
        
         //entrada 1 no está vacío
        if(chessboard.getPiece(from).blankCell()) return ChessCode.MOVING_INCORRECT_PIECE;
        
         //entrada 1 del mismo color que el jugador que la mueve.
        if(((Piece)chessboard.getPiece(from)).isWhite() != whiteTurn) return ChessCode.MOVING_INCORRECT_PIECE;
        
         //entrada 2 NO ES del mismo color que el jugador que la mueve.
        if(!chessboard.getPiece(to).blankCell() && ((Piece)chessboard.getPiece(to)).isWhite() == whiteTurn) return ChessCode.EATING_MATES;
        
        int checkCode = chessboard.checkIfMove(from, to);
        
        if(
                checkCode == ChessCode.BOTH_CHECK ||
                (checkCode == ChessCode.WHITE_CHECK && whiteTurn) ||
                (checkCode == ChessCode.BLACK_CHECK && !whiteTurn)
                ) return ChessCode.KING_EXPOSED;
        
        int code = chessboard.move(from, to);
        System.out.println("code: " + code);
        
        if(code != ChessCode.OK) return code;
        
        if (checkCode != ChessCode.OK) {
            boolean whiteCheckmate = true;
            if(checkCode == ChessCode.BLACK_CHECK) whiteCheckmate = false;
            if(chessboard.anyMove(whiteCheckmate)) {
                if (whiteCheckmate) {
                    state = STATE_BLACK_WON;
                }else{
                    state = STATE_WHITE_WON;
                }
                return ChessCode.CHECKMATE;
            }
            return checkCode;
        }else{
            if(chessboard.anyMove(!whiteTurn)) {
                state = STATE_DRAW;
                return ChessCode.STALEMATE;
            }
        }
        whiteTurn = !whiteTurn;
        return ChessCode.OK;
    }
    
    public <T extends Piece> boolean setPromotion(Class<T> cl){
        return chessboard.setPromotion(cl, !whiteTurn);
    }
    
    public static ChessManager fromJSON(String json) throws JsonProcessingException{
        if(json == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        ChessManager chess = objectMapper.readValue(json, ChessManager.class);
        chess.getChessboard().assignPositionsAndMovements();
        return chess;
    }
    public static ChessManager fromJSON(File jsonFile) throws Exception{
        String json = "";
        ObjectMapper objectMapper = new ObjectMapper();
        ChessManager chess;
        
        try(Scanner sc = new Scanner(jsonFile)){
            json = sc.nextLine();
            return fromJSON(json);
        } catch (JsonProcessingException ex) {
            throw ex;
        }
        
    }
    
    public String toJSON() throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public ChessManager(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public int getState() {
        return state;
    }

    public Chessboard getChessboard() {
        return chessboard;
    }
    // </editor-fold>  

    
}
