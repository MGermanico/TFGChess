/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.general;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;

/**
 *
 * @author migue
 */
public class Position {
    private int row;
    private int column;
    
    public Position positionFromHere(int verticalSteps, int horizontalSteps){
        return new Position(
                row + verticalSteps,
                column + horizontalSteps);
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    public boolean isUnderOf(Position position){
        return this.getRow() < position.getRow();
    }
    public boolean isUpperOf(Position position){
        return this.getRow() > position.getRow();
    }
    public boolean isToTheRightOf(Position position){
        return this.getColumn() > position.getColumn();
    }
    public boolean isToTheLeftOf(Position position){
        return this.getColumn() < position.getColumn();
    }
    
    public boolean isTheSame(Position position){
        return position.getRow() == this.getRow() && 
                position.getColumn() == this.getColumn();
    }
    
    public Position clone(){
        return new Position(row, column);
    }

    @Override
    public String toString() {
        return "[" + this.column + " , " + this.row + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Position pos = (Position) obj;
        return row == pos.row && column == pos.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public Position() {
    }
    // </editor-fold>  

    public static Position fromJSON(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Position pos = objectMapper.readValue(json, Position.class);
        return pos;
    }
    
    public String toJSON() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    
}
