package gameboard.player;

import gameboard.matrixcoordinates.Coordinate;

public class Player {

    private boolean isWinner;
    private Coordinate position;
    private byte[] keysID = null;

    /* ===================== Constructors ===================== */

    public Player() {
    }

    public Player(Player other) {
        if (other == null) return;

        this.isWinner = other.isWinner;

        if (other.position != null) {
            this.position = new Coordinate(other.position);
        } else {
            this.position = null;
        }

        if (other.keysID != null) {
            this.keysID = other.keysID.clone();
        } else {
            this.keysID = null;
        }
    }

    public Player(Coordinate position) {
        this.isWinner = false;
        this.position = position;
    }

    /* ===================== Getters & Setters ===================== */

    public boolean getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public Coordinate getPosition() {
        return position;
    }

    public void setPosition(Coordinate position) {
        this.position = position;
    }

    public byte[] getKeysID() {
        return keysID;
    }

    public void setKeysID(byte[] keysID) {
        this.keysID = keysID;
    }

    /* ===================== Utility Methods ===================== */

    public void printArrKeysID() {
        if (keysID == null || keysID.length == 0) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        for (int i = 0; i < keysID.length; i++) {
            System.out.print(keysID[i]);
            if (i < keysID.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "Player = {" +
                "isWinner = " + isWinner +
                ", position = " + position +
                '}';
    }
}
