package gameboard.boardcells;

import gameboard.matrixcoordinates.Coordinate;

public class UnlockerPiece extends NormalPiece {

    private byte keyID;
    private boolean isUsed;

    /* ===================== Constructors ===================== */

    public UnlockerPiece() {
    }

    public UnlockerPiece(UnlockerPiece other) {
        super(other);

        if (other != null) {
            this.keyID = other.keyID;
            this.isUsed = other.isUsed;
        }
    }

    public UnlockerPiece(byte keyID, byte cost, Coordinate coordinate) {
        super(cost, coordinate);
        this.keyID = keyID;
        this.isUsed = false;
        this.hasPlayer = false;
        this.pieceSymbol = 'K';
    }

    /* ===================== Getters & Setters ===================== */

    public byte getKeyID() {
        return keyID;
    }

    public void setKeyID(byte keyID) {
        this.keyID = keyID;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "UnlockerPiece = {" +
                "cost = " + cost +
                ", pieceSymbol = " + pieceSymbol +
                ", hasPlayer = " + hasPlayer +
                ", isUsed = " + isUsed +
                ", keyID = " + keyID +
                ", " + coordinate.toString() +
                '}';
    }
}