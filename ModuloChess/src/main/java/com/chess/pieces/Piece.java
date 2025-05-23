/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces;

import com.chess.general.Position;
import com.chess.pieces.movements.Movement;
import com.chess.pieces.pieces.Bishop;
import com.chess.pieces.pieces.King;
import com.chess.pieces.pieces.Knight;
import com.chess.pieces.pieces.Pawn;
import com.chess.pieces.pieces.Queen;
import com.chess.pieces.pieces.Rook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.utils.ChessCode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "type_piece"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Rook.class, name = "rook"),
    @JsonSubTypes.Type(value = Bishop.class, name = "bishop"),
    @JsonSubTypes.Type(value = King.class, name = "king"),
    @JsonSubTypes.Type(value = Queen.class, name = "queen"),
    @JsonSubTypes.Type(value = Knight.class, name = "knight"),
    @JsonSubTypes.Type(value = Pawn.class, name = "pawn")
})
public abstract class Piece extends DisplayableCell{

    protected boolean white;
    
    @JsonIgnore
    private List<Movement> movements = new ArrayList<>();

    public Piece(Position position, boolean white) {
        this.position = position;
        this.white = white;
        addMovements();
    }
    
    
    public abstract void addMovements();
    public abstract String toName();
    
    public int move(Position position, DisplayableCell[][] boardInstance){
        int code;
        for (Movement movement : movements) {
            code = movement.canIMove(this.position, position, boardInstance);
            if(code == ChessCode.OK)return ChessCode.OK;
//            System.out.println(code);
        }
        return ChessCode.CANT_MOVE_THERE;
    }
    
    public Set<Position> whereCanIMove(DisplayableCell[][] boardInstance){
        Set<Position> positions = new HashSet<>();
        for (Movement movement : movements) {
            positions.addAll(movement.whereCanIMove(position, boardInstance, white));
        }
        return positions;
    }
    
    public Set<Position> whereCanIAttack(DisplayableCell[][] boardInstance){
        Set<Position> positions = new HashSet<>();
        for (Movement movement : movements) {
            positions.addAll(movement.whereCanAttack(position, boardInstance, white));
        }
        return positions;
    }

    public boolean isWhite() {
        return white;
    }
    
    @Override
    public boolean blankCell() {
        return false;
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">

    public Piece(boolean white, Position position) {
        super(position);
        this.white = white;
    }
    public Piece() {
    }

    public List<Movement> getMovements() {
        return movements;
    }
    // </editor-fold>

    
    
    
}
