package gameboard.matrixcoordinates;

public class Coordinate {

    private byte x;
    private byte y;

    /* ===================== Constructors ===================== */

    public Coordinate() {
    }

    public Coordinate(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate other) {
        this.x = other.x;
        this.y = other.y;
    }

    /* ===================== Getters & Setters ===================== */

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return y;
    }

    public void setY(byte y) {
        this.y = y;
    }

    /* ===================== Object Overrides ===================== */

    @Override
    public String toString() {
        return "Coordinates = {" +
                "x = " + x +
                ", y = " + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate other)) return false;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
