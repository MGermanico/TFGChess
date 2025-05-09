/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chess.pieces;

import com.chess.general.Position;
import com.chess.pieces.pieces.Bishop;
import com.chess.pieces.pieces.King;
import com.chess.pieces.pieces.Knight;
import com.chess.pieces.pieces.Pawn;
import com.chess.pieces.pieces.Queen;
import com.chess.pieces.pieces.Rook;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "type_cell"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Piece.class, name = "piece"),
    @JsonSubTypes.Type(value = Blank.class, name = "blank"),
    // Agrega otras subclases según sea necesario
})
public abstract class DisplayableCell {
    
    @JsonIgnore
    protected Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
    public abstract boolean blankCell();
    public abstract DisplayableCell clone();
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">

    public DisplayableCell(Position position) {
        this.position = position;
    }
    public DisplayableCell() {
    }

    // </editor-fold>
    
}
