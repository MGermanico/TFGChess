/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chess.pieces.movements;

import com.chess.general.Position;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.utils.ChessCode;
import java.util.List;
import java.util.Set;

/**
 *
 * @author migue
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, // Usa el nombre de la subclase en el JSON
    include = JsonTypeInfo.As.PROPERTY,  // Usa una propiedad para indicar el tipo
    property = "movementType"  // Nombre de la propiedad en el JSON que indicará el tipo
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = KnightMovement.class, name = "knightMovement"),
    @JsonSubTypes.Type(value = RectilinealMovement.class, name = "rectilinealMovement"),
})
public abstract class Movement {
    private boolean instantMove = false;
    private boolean haveToEat = false;
    private boolean haveToNotEat = false;
    
    public int canIMove(Position from, Position to, DisplayableCell[][] boardInstance){
        throw new UnsupportedOperationException();
    }
    public Set<Position> whereCanIMove(Position from, DisplayableCell[][] boardInstance, boolean imWhite){
        return whereCanI(from, boardInstance, false, imWhite);
    }
    public Set<Position> whereCanAttack(Position from, DisplayableCell[][] boardInstance, boolean imWhite){
        return whereCanI(from, boardInstance, true, imWhite);
    }
    public abstract Set<Position> whereCanI(Position from, DisplayableCell[][] boardInstance, boolean attack, boolean imWhite);

    protected int checkIfCanI(boolean attack, DisplayableCell cell, boolean imWhite) {
        if(cell.blankCell()){
//            System.out.print("blank" + cell.getPosition());
            if(haveToEat || attack)return ChessCode.CANT_MOVE_WITHOUT_EATING;
        }else{
//            System.out.print("piece");
            if(!attack) return ChessCode.CANT_MOVE_AND_EAT;//
            Piece piece = (Piece) cell;
            if(haveToNotEat) return ChessCode.CANT_MOVE_AND_EAT;
            if(piece.isWhite() == imWhite) return ChessCode.EATING_MATES;
        }
        if(attack){
            return ChessCode.EATEN;
        }
        return ChessCode.OK;
    }
    
    public void setHaveToEat(boolean haveToEat) {
        this.haveToEat = haveToEat;
    }

    public void setHaveToNotEat(boolean haveToNotEat) {
        this.haveToNotEat = haveToNotEat;
    }

    public void setInstantMove(boolean instantMove) {
        this.instantMove = instantMove;
    }
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    
    public Movement() {
    }

    public boolean isInstantMove() {
        return instantMove;
    }

    public boolean isHaveToEat() {
        return haveToEat;
    }

    public boolean isHaveToNotEat() {
        return haveToNotEat;
    }
    // </editor-fold> 

    
}
