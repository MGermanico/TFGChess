/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.pieces;

import com.chess.general.ChessManager;
import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.chess.pieces.movements.Movement;
import com.chess.pieces.movements.RectilinealMovement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author migue
 */
public class test {
    public static void main(String[] args) throws JsonProcessingException {
        Bishop p = new Bishop(new Position(2,2), true);
        Chessboard board = new Chessboard(new DisplayableCell[][]{{p}}, new Position(0, 0), new Position(0, 0));
        ChessManager chess = new ChessManager(board);
        
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(p);
        
        String json = chess.toJSON();

        System.out.println(json);
        
        chess = ChessManager.fromJSON(json);
        
        Bishop p2 = (Bishop) chess.getChessboard().getPiece(new Position(0,0));
        for (Movement movement : p2.getMovements()) {
            System.out.println(movement);
        }
        
    }
}
