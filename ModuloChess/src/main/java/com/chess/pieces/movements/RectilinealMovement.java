/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chess.pieces.movements;

import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.chess.pieces.DisplayableCell;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.utils.ChessCode;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author migue
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,  // Usamos el nombre de la subclase
    include = JsonTypeInfo.As.PROPERTY,  // Incluimos la propiedad `movementType` en el JSON
    property = "movementType"  // El campo en el JSON que contendrá el tipo
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HorizontalMovement.class, name = "horizontalMovement"),
    @JsonSubTypes.Type(value = VerticalMovement.class, name = "verticalMovement"),
    @JsonSubTypes.Type(value = DiagonalMovement.class, name = "diagonalMovement"),
})
public abstract class RectilinealMovement extends Movement{

    protected Boolean toUp;
    protected Boolean toRight;
    protected Integer maxSteps;

    public RectilinealMovement(Boolean toUp, Boolean toRight, Integer maxSteps) {
        this.toUp = toUp;
        this.toRight = toRight;
        this.maxSteps = maxSteps;
    }
    
    @Override
    public Set<Position> whereCanI(Position from, DisplayableCell[][] boardInstance, boolean attack, boolean imWhite) {
        Set<Position> positions = new HashSet<>();
        boolean keepGoing = true;
        int actualCode;
        DisplayableCell actualCell;
        int verticalDirection = 0;
        if (toUp != null) {
            verticalDirection = 1;
            if(!toUp) verticalDirection = -1;
        }
        int horizontalDirection = 0;
        if (toRight != null) {
            horizontalDirection = 1;
            if(!toRight) horizontalDirection = -1;
        }
        Position lastPosition = from;
        Position actualPosition;
        int steps = 0;
        do {
            steps ++;
            actualPosition = lastPosition.clone().positionFromHere(verticalDirection, horizontalDirection);
            lastPosition = actualPosition;
            if ((maxSteps == null || steps <= maxSteps) && Chessboard.isItInside(lastPosition, boardInstance)) {
                actualCell = boardInstance[actualPosition.getColumn()][actualPosition.getRow()];
                actualCode = checkIfCanI(attack, actualCell, imWhite);
//                System.out.print(actualCode);
                if (actualCode == ChessCode.OK) {
                    positions.add(actualPosition);
                }else if(actualCode == ChessCode.EATEN){
                    positions.add(actualPosition);
                    keepGoing = false;
                }else{
                    if (attack) {
                        if(actualCode == ChessCode.EATING_MATES) keepGoing = false;
                    }else{
                        keepGoing = false;
                    }
                }
                
            }else{
                keepGoing = false;
            }
            
        } while (keepGoing);
//        System.out.println();
        return positions;
    }
    
    @Override
    public int canIMove(Position from, Position to, DisplayableCell[][] boardInstance) {
        int code;
        
        code = isCorrectMove(from, to);
        if(code != ChessCode.OK) return code;
        
        if(!isCorrectWay(from, to)) return ChessCode.IS_NOT_THAT_WAY;
        
        int distance;
        if (toUp != null) {
            distance = super.distance(from.getRow(), to.getRow());
        }else{
            distance = super.distance(from.getColumn(), to.getColumn());
        }
        
        if(maxSteps != null && distance > maxSteps) return ChessCode.IS_SO_FAR;
        
        if (!isInstantMove()) {
            code = checkPath(from, to, distance, boardInstance);
            if(code != ChessCode.OK) return code;
        }
        
        DisplayableCell toCell = boardInstance[to.getColumn()][to.getRow()];
        if(this.isHaveToEat() && toCell.blankCell()) return ChessCode.CANT_MOVE_WITHOUT_EATING;
        if(this.isHaveToNotEat() && !toCell.blankCell()) return ChessCode.CANT_MOVE_AND_EAT;

        return ChessCode.OK;
    }
    
    protected int checkPath(Position from, Position to, int distance, DisplayableCell[][] boardInstance) {
        int verticalDirection = 0;
        if (toUp != null) {
            verticalDirection = 1;
            if(!toUp) verticalDirection = -1;
        }
        int horizontalDirection = 0;
        if (toRight != null) {
            horizontalDirection = 1;
            if(!toRight) horizontalDirection = -1;
        }
        DisplayableCell actualCell;
        for (int i = 1; i < distance; i++) {
            actualCell = boardInstance[ from.getColumn()+ (i*horizontalDirection)][ from.getRow() + (i*verticalDirection) ];
            if(!actualCell.blankCell())return ChessCode.PIECE_ON_THE_WAY;
        }
        return ChessCode.OK;
    }
    
    protected abstract int isCorrectMove(Position from, Position to);
    
    protected abstract boolean isCorrectWay(Position from, Position to);
    
    // <editor-fold defaultstate="collapsed" desc="JSonMapperDependencies">
    
    public RectilinealMovement() {
    }
    
    
    public Boolean getToUp() {
        return toUp;
    }

    public Boolean getToRight() {
        return toRight;
    }

    public Integer getMaxSteps() {
        return maxSteps;
    }
    
    // </editor-fold> 

    

    

    
    
}
