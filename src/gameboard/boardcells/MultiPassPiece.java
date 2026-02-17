package gameboard.boardcells;

import gameboard.matrixcoordinates.Coordinate;

public class MultiPassPiece extends NormalPiece {

    private boolean[] passes;

    /* ===================== Constructors ===================== */

    public MultiPassPiece() {
    }

    public MultiPassPiece(byte cost, Coordinate coordinate) {
        super(cost, coordinate);
        this.passes = new boolean[2]; // false by default
        this.pieceSymbol = '2';
        this.hasPlayer = false;
    }

    public MultiPassPiece(MultiPassPiece other) {
        super(other);
        this.passes = (other.passes != null)
                ? other.passes.clone()
                : new boolean[]{false, false};
        this.pieceSymbol = other.pieceSymbol;
        this.hasPlayer = other.hasPlayer;
    }

    /* ===================== Getters & Setters ===================== */

    public boolean[] getPasses() {
        return passes;
    }

    public void setPasses(boolean[] passes) {
        this.passes = passes;
    }

    /* ===================== Game Logic ===================== */

    public void setPieceSymbol() {
        if (!this.passes[0] && !this.passes[1]) {
            this.pieceSymbol = '2';
        } else if (this.passes[0] || this.passes[1]) {
            this.pieceSymbol = '1';
        } else {
            this.pieceSymbol = '0';
        }
    }

    public void stepOn() {
        if (!passes[0]) {
            passes[0] = true;
            setPieceSymbol();
            return;
        }

        if (!passes[1]) {
            passes[1] = true;
            setPieceSymbol();
        }
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "MultiPassPiece = {" +
                "cost = " + cost +
                ", pieceSymbol = " + pieceSymbol +
                ", hasPlayer = " + hasPlayer +
                ", " + coordinate.toString() +
                ", passes = [" + passes[0] + ", " + passes[1] + "]" +
                '}';
    }
}
