/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.general;

import com.chess.pieces.Blank;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.chess.pieces.pieces.Pawn;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author migue
 */
public class BoardIterator implements Iterator<DisplayableCell> {
        private DisplayableCell[][] board;
        public int row = 0;
        public int column = 0;

        public BoardIterator(DisplayableCell[][] tabla) {
            this.board = tabla;
        }

        @Override
        public boolean hasNext() {
            // Si aún hay filas, y dentro de la fila hay columnas por recorrer
            return row < board.length && column < board[row].length;
        }

        @Override
        public DisplayableCell next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            DisplayableCell valor = board[column][row]; // Obtener el valor actual

            // Moverse a la siguiente celda
            row++;
            if (row >= board[column].length) { // Si llegamos al final de la fila
                row = 0;
                column++;
            }

            return valor;
        }
        
        public boolean isBlank(){
            return board[row][column].blankCell();
        }
        
        public DisplayableCell getCell(){
            return board[row][column];
        }
        
        public Blank getBlank(){
            if(!isBlank()) return null;
            return (Blank) board[row][column];
        }
        
        public Piece getPiece(){
            if(isBlank()) return null;
            return (Piece) board[row][column];
        }
        
        public boolean isPawn(){
            if(isBlank()) return false;
            return board[row][column].getClass() == Pawn.class;
        }
        
        public Pawn getPawn(){
            if(isBlank()) return null;
            if(!isPawn()) return null;
            
            return (Pawn) board[row][column];
        }
        
    }