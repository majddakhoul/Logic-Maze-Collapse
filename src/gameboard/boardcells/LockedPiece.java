package gameboard.boardcells;

import gameboard.matrixcoordinates.Coordinate;

public class LockedPiece extends NormalPiece {

    private byte requiredKeyID;
    private boolean isLocked;

    /* ===================== Constructors ===================== */

    public LockedPiece() {
    }

    public LockedPiece(LockedPiece other) {
        super(other);

        if (other != null) {
            this.requiredKeyID = other.requiredKeyID;
            this.isLocked = other.isLocked;
        }
    }

    public LockedPiece(byte requiredKeyID, byte cost, Coordinate coordinate) {
        super(cost, coordinate);
        this.isLocked = true;
        this.requiredKeyID = requiredKeyID;
        this.hasPlayer = false;
        this.pieceSymbol = 'L';
    }

    /* ===================== Getters & Setters ===================== */

    public byte getRequiredKeyID() {
        return requiredKeyID;
    }

    public void setRequiredKeyID(byte requiredKeyID) {
        this.requiredKeyID = requiredKeyID;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "LockedPiece = {" +
                "cost = " + cost +
                ", pieceSymbol = " + pieceSymbol +
                ", hasPlayer = " + hasPlayer +
                ", isLocked = " + isLocked +
                ", requiredKeyID = " + requiredKeyID +
                ", " + coordinate.toString() +
                '}';
    }
}