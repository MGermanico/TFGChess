package exec.chess;


import com.chess.general.ChessManager;
import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.chess.pieces.Blank;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.chess.pieces.pieces.Bishop;
import com.chess.pieces.pieces.King;
import com.chess.pieces.pieces.Knight;
import com.chess.pieces.pieces.Pawn;
import com.chess.pieces.pieces.Queen;
import com.chess.pieces.pieces.Rook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author migue
 */
public class testJSON {
    public static void main(String[] args) {
        DisplayableCell[][] b = new DisplayableCell[8][8];
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (row == 0) {
                    if (column == 0 || column == 7) {
                        b[column][row] = new Rook(new Position(row, column), true);
                    }else if (column == 1 || column == 6) {
                        b[column][row] = new Knight(new Position(row, column), true);
                    }else if (column == 2 || column == 5) {
                        b[column][row] = new Bishop(new Position(row, column), true);
                    }else if (column == 3) {
                        b[column][row] = new Queen(new Position(row, column), true);
                    }else{
                        b[column][row] = new King(new Position(row, column), true);
                    }
                }else if (row == 1) {
                    b[column][row] = new Pawn(new Position(row, column), true);
                }else if (row == 6) {
                    b[column][row] = new Pawn(new Position(row, column), false);
                }else if (row == 7) {
                    if (column == 0 || column == 7) {
                        b[column][row] = new Rook(new Position(row, column), false);
                    }else if (column == 1 || column == 6) {
                        b[column][row] = new Knight(new Position(row, column), false);
                    }else if (column == 2 || column == 5) {
                        b[column][row] = new Bishop(new Position(row, column), false);
                    }else if (column == 3) {
                        b[column][row] = new Queen(new Position(row, column), false);
                    }else{
                        b[column][row] = new King(new Position(row, column), false);
                    }
                }else{
                    b[column][row] = new Blank(new Position(row, column));
                }
            }
        }
        Chessboard board = new Chessboard(b);
        ChessManager chess = new ChessManager();
        chess.setChessboard(board);
        System.out.println(board);
//        chess.move(new Position(1, 0), new Position(3, 0));
//        System.out.println(board);
        
        ObjectMapper objectMapper = new ObjectMapper();
            
        try {
            // Serializar el objeto a JSON
            String json = chess.toJSON();
            System.out.println(json);
            
            File f = new File("src/main/resources/NewGame.json");
            BufferedWriter fw = new BufferedWriter(new FileWriter(f));
            fw.write(json);
            fw.flush();
            
            ChessManager chess2 = ChessManager.fromJSON(json);
            System.out.println(chess2.getChessboard());
            
            System.out.println(chess2.getChessboard().getPiece(new Position(2, 3)).getPosition());
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(testJSON.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(testJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
