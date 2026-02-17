package gameboard.boardcells;

import gameboard.matrixcoordinates.Coordinate;

public class FinalPiece extends NormalPiece {

    /* ===================== Constructors ===================== */

    public FinalPiece() {
    }

    public FinalPiece(FinalPiece other) {
        super(other);
    }

    public FinalPiece(byte cost, Coordinate coordinate) {
        super(cost, coordinate);
        this.hasPlayer = false;
        this.pieceSymbol = 'F';
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "FinalPiece = {" +
                "cost = " + cost +
                ", pieceSymbol = " + pieceSymbol +
                ", hasPlayer = " + hasPlayer +
                ", " + coordinate.toString() +
                '}';
    }
}