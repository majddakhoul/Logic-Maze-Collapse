package gameboard.boardcells;

import gameboard.matrixcoordinates.Coordinate;

public class NormalPiece {

    protected byte cost;
    protected char pieceSymbol;
    protected boolean hasPlayer;
    protected Coordinate coordinate;

    /* ===================== Constructors ===================== */

    public NormalPiece() {
    }

    public NormalPiece(byte cost, Coordinate coordinate) {
        this.cost = cost;
        this.pieceSymbol = 'N';
        this.hasPlayer = false;
        this.coordinate = coordinate;
    }

    public NormalPiece(NormalPiece other) {
        if (other == null) {
            return;
        }

        this.cost = other.cost;
        this.pieceSymbol = other.pieceSymbol;
        this.hasPlayer = other.hasPlayer;
        this.coordinate = new Coordinate(other.coordinate);
    }

    /* ===================== Getters & Setters ===================== */

    public byte getCost() {
        return cost;
    }

    public void setCost(byte cost) {
        this.cost = cost;
    }

    public char getPieceSymbol() {
        return pieceSymbol;
    }

    public void setPieceSymbol(char pieceSymbol) {
        this.pieceSymbol = pieceSymbol;
    }

    public boolean getHasPlayer() {
        return hasPlayer;
    }

    public void setHasPlayer(boolean hasPlayer) {
        this.hasPlayer = hasPlayer;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "BoardPiece = {" +
                "cost = " + cost +
                ", pieceSymbol = " + pieceSymbol +
                ", hasPlayer = " + hasPlayer +
                ", " + coordinate.toString() +
                '}';
    }
}
