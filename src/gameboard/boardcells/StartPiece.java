package gameboard.boardcells;

import gameboard.matrixcoordinates.Coordinate;

public class StartPiece extends NormalPiece {

    /* ===================== Constructors ===================== */

    public StartPiece() {
    }

    public StartPiece(StartPiece other) {
        super(other);
    }

    public StartPiece(byte cost, Coordinate coordinate) {
        super(cost, coordinate);
        this.hasPlayer = true;
        this.pieceSymbol = 'S';
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "StartPiece = {" +
                "cost = " + cost +
                ", pieceSymbol = " + pieceSymbol +
                ", hasPlayer = " + hasPlayer +
                ", " + coordinate.toString() +
                '}';
    }
}
