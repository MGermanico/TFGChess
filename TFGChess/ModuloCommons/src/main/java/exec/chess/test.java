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
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author migue
 */
public class test {
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
                        b[column][row] = new King(new Position(row, column), true);
                    }else{
                        b[column][row] = new Queen(new Position(row, column), true);
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
                        b[column][row] = new King(new Position(row, column), false);
                    }else{
                        b[column][row] = new Queen(new Position(row, column), false);
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
        
        Scanner sc = new Scanner(System.in);
        String[] metaPart, parts, parts2;
        for (;;) {
            metaPart = sc.nextLine().split(" ");
            
            parts = metaPart[0].split("");
//            System.out.println(parts.length);
            parts[1] = Integer.toString(Integer.parseInt(parts[1])-1);
            parts[0] = pasarANumero(parts[0]);
            
            parts2 = metaPart[1].split("");
            parts2[1] = Integer.toString(Integer.parseInt(parts2[1])-1);
            parts2[0] = pasarANumero(parts2[0]);
            
//            System.out.println("moviendo: " + parts[0] + "," + parts[1] + " ->"
//                                             + parts2[0] + "," + parts2[1]);
            
            int code = chess.move(new Position(Integer.parseInt(parts[1]), Integer.parseInt(parts[0])),
                    new Position(Integer.parseInt(parts2[1]), Integer.parseInt(parts2[0])));
            
            System.out.println("FINAL CODE: " + code);
            System.out.println("TABLERO REAL:");
            System.out.println(board);
            
        }
    }

    private static String pasarANumero(String part) {
        char c = part.charAt(0);
        int n = c - 97;
        return Integer.toString(n);
    }
}
