/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.general;

import com.chess.pieces.Blank;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Moved;
import com.chess.pieces.Piece;
import com.chess.pieces.pieces.Bishop;
import com.chess.pieces.pieces.King;
import com.chess.pieces.pieces.Knight;
import com.chess.pieces.pieces.Pawn;
import com.chess.pieces.pieces.Queen;
import com.chess.pieces.pieces.Rook;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.utils.ChessCode;
import com.utils.MathUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chessboard {
    private DisplayableCell[][] board;
    private Position passantPosition = null;
    private Position whiteKingPosition;
    private Position blackKingPosition;
    private Position promote = null;

    public Chessboard(DisplayableCell[][] board) {
        this.board = board;
        findKings();
        assignPositionsAndMovements();
    }
    
    public void assignPositionsAndMovements() {
        BoardIterator it = new BoardIterator(board);
        while(it.hasNext()){
            it.getCell().setPosition(new Position(it.row, it.column));
            if (!it.isBlank()) {
                it.getPiece().setMovements();
            }
            it.next();
        }
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[column][row].setPosition(new Position(row, column));
                
            }
        }
    }
    
    public int move(Position from, Position to){
        if(promote != null) return ChessCode.HAVE_TO_PROMOTE;
        
        int code;
        Piece piece;
        
        piece = (Piece) getPiece(from);
        
        if (piece instanceof King && checkCastling((King)piece, to)) return ChessCode.OK;
        if (piece instanceof Pawn && checkPassantMove((Pawn)piece, to)) return ChessCode.OK;
        
        code = piece.move(to, board);
        if(code != ChessCode.OK) return code;

        movePiece(piece, to);

        piece = (Piece)getPiece(to);

        if(passantPosition != null) passantPosition = null;
        if (piece instanceof Moved) {
            Moved movedPiece = (Moved)piece;
            if(!movedPiece.hasMoved()){
                if (piece instanceof Pawn) {
                    setPassantPosition(from, to);
                }
                movedPiece.setMoved(true);
            }
        }
        
        if (piece instanceof Pawn) {
            checkPromotion((Pawn)piece);
        }
        
        return ChessCode.OK;
    }
    
    private void movePiece(Piece piece, Position to) { 
        if (piece instanceof King) {
            if (piece.isWhite()) {
                whiteKingPosition = to.clone();
            }else{
                blackKingPosition = to.clone();
            }
        }
        
        board[to.getColumn()][to.getRow()]
            = board[piece.getPosition().getColumn()][piece.getPosition().getRow()].clone();
        
        board[to.getColumn()][to.getRow()].setPosition(to.clone());
        
        setBlank(piece.getPosition());
    }
    
    public DisplayableCell getPiece(Position position){
        return board[position.getColumn()][position.getRow()];
    }
    
    public int checkIfMove(Position from, Position to){
//        System.out.println(whiteKingPosition + " black = " + blackKingPosition + " :::::: from " + from + " to" + to);
        DisplayableCell[][] instance = cloneBoard();
        Position whiteKingPosition = this.whiteKingPosition.clone();
        Position blackKingPosition = this.blackKingPosition.clone();
        DisplayableCell cell = getPiece(from);
        if (!cell.blankCell()) {
            Piece piece = (Piece) cell;
            if (piece instanceof King) {
                if (piece.isWhite()) {
                    whiteKingPosition = to.clone();
                }else{
                    blackKingPosition = to.clone();
                }
            }
        }
        
        instance[to.getColumn()][to.getRow()]
            = instance[from.getColumn()][from.getRow()].clone();
        
        instance[to.getColumn()][to.getRow()].setPosition(to.clone());
        
        instance[from.getColumn()][from.getRow()]
            = new Blank(from.clone());
        
//        System.out.println("TABLERO DEL MOVIMIENTO ANTICIPADO:");
//        System.out.println(whiteKingPosition + " black = " + blackKingPosition);
//        
//        System.out.println(toString(instance));
        return checkIf(instance, whiteKingPosition, blackKingPosition);
    }
    
    public static int checkIf(DisplayableCell[][] boardInstance, Position whiteKingPosition, Position blackKingPosition){
        boolean whiteCheck = checkIf( boardInstance, whiteKingPosition,  blackKingPosition, true);
        boolean blackCheck = checkIf( boardInstance, whiteKingPosition,  blackKingPosition, false);
        if(whiteCheck && blackCheck) return ChessCode.BOTH_CHECK;
        if(whiteCheck) return ChessCode.WHITE_CHECK;
        if(blackCheck) return ChessCode.BLACK_CHECK;
        return ChessCode.OK;
    }
    
    public static boolean checkIf(DisplayableCell[][] boardInstance, Position whiteKingPosition, Position blackKingPosition, boolean whiteCheck){
        Set<Position> aimedPositions;
        Set<Position> kingCell = new HashSet();
        if (whiteCheck) {
            kingCell.add(whiteKingPosition);
        }else{
            kingCell.add(blackKingPosition);
        }
        aimedPositions = anyAimingTo(boardInstance, kingCell, !whiteCheck);
        
        return !aimedPositions.isEmpty();
    }
    
    public static Set<Position> anyAimingTo(DisplayableCell[][] boardInstance, Set<Position> cells, boolean whiteTeamAiming){
        Set<Position> attackCells = new HashSet<>();
        BoardIterator it = new BoardIterator(boardInstance);
        Piece actualPiece;
        Set<Position> actualAttackCells = new HashSet<>();
        while(it.hasNext()){
            if (!it.isBlank()) {
                actualPiece = it.getPiece();
//                System.out.println("--- piece " + actualPiece);
                actualAttackCells = actualPiece.whereCanIAttack(boardInstance);
                actualAttackCells.addAll(actualPiece.whereCanIMove(boardInstance));
//                System.out.println("   --- movements " + actualAttackCells + " act : " + actualPiece.isWhite() + " , aiming : " + whiteTeamAiming);
                if (actualPiece.isWhite() == whiteTeamAiming) {
                    attackCells.addAll(actualAttackCells);
                }
            }
            it.next();
        }
        
        Set aimedPositions = new HashSet();
//        System.out.println("aimed: " + attackCells);
//        System.out.println("test : " + cells);
        for (Position cell : cells) {
            if (attackCells.contains(cell)) aimedPositions.add(cell);
        }
        return aimedPositions;
    }
    
    public int anyCheck(){
        return checkIf(cloneBoard(), whiteKingPosition, blackKingPosition);
    }
    
    public boolean anyMove(boolean white){
//        System.out.println("------------- checkmate ? ----------------");
        Set<Position> actualMovements;
        BoardIterator it = new BoardIterator(board);
        Piece actualPiece;
        int checkCode;
        boolean whiteCheck;
        boolean blackCheck;
        while(it.hasNext()){
            if (!it.isBlank()) {
//                System.out.print("---piece ");
                actualPiece = it.getPiece();
//                System.out.println(actualPiece.getPosition());
                if (actualPiece.isWhite() == white) {
                    actualMovements = actualPiece.whereCanIMove(board);
                    actualMovements.addAll(actualPiece.whereCanIAttack(board));
                    
                    for (Position actualMovement : actualMovements) {
//                        System.out.println("   ---movement to " + actualMovement);
                        checkCode = checkIfMove(actualPiece.getPosition(), actualMovement);
                        whiteCheck = checkCode == ChessCode.WHITE_CHECK || checkCode == ChessCode.BOTH_CHECK;
                        blackCheck = checkCode == ChessCode.BLACK_CHECK || checkCode == ChessCode.BOTH_CHECK;
                        if(
                            (white && !whiteCheck) ||
                            (!white && !blackCheck)
                        ) return false;
                    }
                }else{
//                    System.out.println("x");
                }
            }
            it.next();
        }
        return true;
    }
    
    public DisplayableCell[][] cloneBoard(){
        if(board == null || board.length == 0) return null;
        if(board[0] == null || board[0].length == 0) return null;
        DisplayableCell[][] instance = new DisplayableCell[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                instance[i][j] = board[i][j].clone();
            }
        }
        return instance;
    }
    
    public boolean isItInside(Position position){
        return Chessboard.isItInside(position, board);
    }
    
    public static boolean isItInside(Position position, DisplayableCell[][] board){
        if(board == null || board.length == 0) return false;
        if(board[0] == null || board[0].length == 0) return false;
        if(position.getColumn() < 0) return false;
        if(position.getRow() < 0) return false;
        return board.length-1 >= position.getRow() && board[0].length-1 >= position.getColumn();
    }
    

    @Override
    public String toString() {
        return this.toString(board);
    }
    
    public String toString(DisplayableCell[][] board) {
        String t = "";
        
        for (int i = 0; i < board.length+1; i++) {
            for (int j = 0; j < board[0].length+1; j++) {
                if (!(i == 0 && j == 0)) {
                    if (i == 0) {
                        t += " " + j + "  ";
                    }else if (j == 0) {
                        t += " " + (9-i) + "  ";
                    }else{
                        t += board[j-1][9-i-1].toString() + "  ";
                    }
                }else{
                    t += "    ";
                }
            }
            t += "\n";
        }
        return t;
    }

    private void findKings() {
        BoardIterator it = new BoardIterator(board);
        Piece piece;
        while(it.hasNext()){
            if (!it.isBlank()) {
                piece = it.getPiece();
                if (piece instanceof King) {
                    if (piece.isWhite()) {
                        whiteKingPosition = piece.getPosition().clone();
                    }else{
                        blackKingPosition = piece.getPosition().clone();
                    }
                }
            }
            it.next();
        }
    }

    private boolean checkCastling(King king, Position to) {
        if(king.hasMoved()) return false;
        
        Position kingPosition = king.getPosition();
        if(kingPosition.getRow() != to.getRow()) return false;
        
        Position rookPosition;
        DisplayableCell rookCell;
        
        boolean moveToRight;
        Set<Position> walkOfKing = new HashSet<>();
        walkOfKing.add(kingPosition.clone());
        if (kingPosition.isToTheLeftOf(to)) {
            if(to.getColumn() != kingPosition.getColumn() + 2) return false;
            moveToRight = true;
            walkOfKing.add(kingPosition.positionFromHere(0, 1));
            walkOfKing.add(kingPosition.positionFromHere(0, 2));
            rookPosition = new Position(kingPosition.getRow(), 7);
        }else if (kingPosition.isToTheRightOf(to)) {
            if(to.getColumn() != kingPosition.getColumn() - 2) return false;
            moveToRight = false;
            walkOfKing.add(kingPosition.positionFromHere(0, -1));
            walkOfKing.add(kingPosition.positionFromHere(0, -2));
            rookPosition = new Position(kingPosition.getRow(), 0);
        }else return false;
        
        rookCell = getPiece(rookPosition);
        if(!(rookCell instanceof Rook)) return false;
        
        Rook rook = (Rook) rookCell;
        if(rook.hasMoved()) return false;
        
//        System.out.println(":::::::::::::::::::::::::::---------------------------------");
//        System.out.println(":: " +anyAimingTo(getBoardInstance(), walkOfKing, !king.isWhite()));
//        System.out.println(":::::::::::::::::::::::::::---------------------------------");
        if(!anyAimingTo(cloneBoard(), walkOfKing, !king.isWhite()).isEmpty()) return false;        
        
        movePiece(king, to);
        if (moveToRight) {
            movePiece(rook, to.positionFromHere(0, -1));
        }else{
            movePiece(rook, to.positionFromHere(0, 1));
        }
        
        return true;
    }

    public void setPassantPosition(Position from, Position to) {
        if (MathUtils.distance(from.getRow(), to.getRow()) == 2) {
            if (from.isUpperOf(to)) {
                passantPosition = from.clone().positionFromHere(-1, 0);
            }else if(from.isUnderOf(to)){
                passantPosition = from.positionFromHere(1, 0);
            }
        }
    }

    private boolean checkPassantMove(Pawn pawn, Position to) {
        if(passantPosition == null) return false;
        
        if(!passantPosition.equals(to)) return false;
        
        Position pawnPosition = pawn.getPosition();
        boolean toUp;
        if (pawnPosition.isUnderOf(to)) {
            toUp = true;
        }else if (pawnPosition.isUpperOf(to)) {
            toUp = false;
        }else return false;
        
        if(MathUtils.distance(pawnPosition.getRow(), to.getRow()) != 1) return false;
        if(MathUtils.distance(pawnPosition.getColumn(), to.getColumn()) != 1) return false;
        
        if(pawn.isWhite() == !toUp) return false;
        
        movePiece(pawn, to);
        
        if (toUp) {
            setBlank(to.positionFromHere(-1, 0));
        }else{
            setBlank(to.positionFromHere(1, 0));
        }
        return true;
    }
    
    private void setBlank(Position position){
        setCell(position, new Blank(position.clone()));
    }
    
    private void setCell(Position position, DisplayableCell cell){
        board[position.getColumn()][position.getRow()] = cell;
    }

    private void checkPromotion(Pawn pawn) {
        int lastRow = 0;
        if (pawn.isWhite()) {
            lastRow = board[0].length-1;
        }
        if (pawn.getPosition().getRow() == lastRow) {
            System.out.println("PROMOTE");
            promote = pawn.getPosition().clone();
        }
    }
    
    public <T extends Piece> boolean setPromotion(Class<T> cl, boolean whitePromotion){
        if (promote == null) return false;
            Piece piece;
            if (Knight.class == cl) {
                piece = new Knight(promote.clone(), whitePromotion);
            }else if (Queen.class == cl) {
                piece = new Queen(promote.clone(), whitePromotion);
            }else if (Rook.class == cl) {
                piece = new Rook(promote.clone(), whitePromotion);
            }else if (Bishop.class == cl) {
                piece = new Bishop(promote.clone(), whitePromotion);
            }else{
                return false;
            }
            setCell(promote.clone(), piece);
            promote = null;
        return true;
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    
    
    public Chessboard(DisplayableCell[][] board, Position whiteKingPosition, Position blackKingPosition) {
        this.board = board;
        this.whiteKingPosition = whiteKingPosition;
        this.blackKingPosition = blackKingPosition;
    }

    public Chessboard() {
    }
    
    public DisplayableCell[][] getBoard() {
        return board;
    }

    public Position getPassantPosition() {
        return passantPosition;
    }

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public Position getPromote() {
        return promote;
    }
    // </editor-fold>  

    
    
}
