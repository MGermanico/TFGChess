/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.chess.pieces.pieces.*;
import javax.swing.ImageIcon;

/**
 *
 * @author migue
 */
public class ChessUtils {
    public static String getPieceName(Piece piece){
        if (piece.getClass() == Pawn.class) {
            return "Peon";
        }else if (piece.getClass() == Rook.class) {
            return "Torre";
        }else if (piece.getClass() == Knight.class) {
            return "Caballo";
        }else if (piece.getClass() == Bishop.class) {
            return "Alfil";
        }else if (piece.getClass() == King.class) {
            return "Rey";
        }else if (piece.getClass() == Queen.class) {
            return "Dama";
        }else {
            return "vacio";
        }
    }
    public static String getPieceColor(Piece piece){
        if (piece.isWhite()) {
            return "Blanco";
        }else{
            return "Negro";
        }
    }
    public static ImageIcon getCellIcon(DisplayableCell cell, int width, int height){
        ImageIcon pieceIcon;
        if (!cell.blankCell()) {
            Piece piece = (Piece) cell;
            pieceIcon = GUIUtils.resizeImageIcon(new ImageIcon("src/main/resources/icons/iconPieces/" + ChessUtils.getPieceName(piece) + ChessUtils.getPieceColor(piece) + ".png"), width, height);
        } else {
            pieceIcon = new ImageIcon(" ");
        }
        return pieceIcon;
    }
}
