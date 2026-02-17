package gameboard.state;

import gameboard.boardcells.*;
import gameboard.matrixcoordinates.Coordinate;
import gameboard.player.Player;

import java.util.*;

public class Board implements Comparable<Board> {

    private byte row;
    private byte col;
    private NormalPiece[] pieceArr;
    private Player player;
    private Board previousState;
    private short score;

    /* ===================== Constructors ===================== */

    public Board() {
        this.previousState = null;
        this.score = 0;
    }

    public Board(byte row, byte col, NormalPiece[] pieceArr, Player player) {
        this.row = row;
        this.col = col;
        this.pieceArr = pieceArr;
        this.player = player;
        this.previousState = null;
        this.score = 0;
    }

    /* ===================== Getters & Setters ===================== */

    public short getScore() {
        return score;
    }

    public void setScore(short score) {
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public byte getRow() {
        return row;
    }

    public void setRow(byte row) {
        this.row = row;
    }

    public byte getCol() {
        return col;
    }

    public void setCol(byte col) {
        this.col = col;
    }

    public NormalPiece[] getPieceArr() {
        return pieceArr;
    }

    public void setPieceArr(NormalPiece[] pieceArr) {
        this.pieceArr = pieceArr;
    }

    public Board getPreviousState() {
        return previousState;
    }

    public void setPreviousState(Board previousState) {
        this.previousState = previousState;
    }

    /* ===================== Movement Logic ===================== */

    public boolean canMove(char dir) {
        if (player == null || player.getPosition() == null) {
            return false;
        }

        byte x = player.getPosition().getX();
        byte y = player.getPosition().getY();

        switch (dir) {
            case 'w' -> x--;
            case 's' -> x++;
            case 'a' -> y--;
            case 'd' -> y++;
            default -> { return false; }
        }

        if (x < 0 || y < 0 || x >= row || y >= col) {
            return false;
        }

        NormalPiece target = null;
        if (pieceArr == null) return false;

        for (NormalPiece p : pieceArr) {
            if (p != null && p.getCoordinate() != null &&
                    p.getCoordinate().getX() == x && p.getCoordinate().getY() == y) {
                target = p;
                break;
            }
        }

        if (target == null) return false;

        if (target instanceof LockedPiece locked) {
            byte needed = locked.getRequiredKeyID();
            boolean hasKey = false;
            if (player.getKeysID() != null) {
                for (byte k : player.getKeysID()) {
                    if (k == needed) {
                        hasKey = true;
                        break;
                    }
                }
            }
            if (!hasKey) return false;
        }

        return true;
    }

    public boolean move(char dir) {
        if (!canMove(dir)) return false;

        byte oldX = player.getPosition().getX();
        byte oldY = player.getPosition().getY();
        byte newX = oldX;
        byte newY = oldY;

        switch (dir) {
            case 'w' -> newX--;
            case 's' -> newX++;
            case 'a' -> newY--;
            case 'd' -> newY++;
        }

        NormalPiece targetPiece = null;
        for (NormalPiece p : pieceArr) {
            if (p != null && p.getCoordinate() != null &&
                    p.getCoordinate().getX() == newX && p.getCoordinate().getY() == newY) {
                targetPiece = p;
                break;
            }
        }

        if (targetPiece == null) return false;

        if (targetPiece instanceof LockedPiece locked) locked.setIsLocked(false);

        if (targetPiece instanceof UnlockerPiece key && !key.getIsUsed()) {
            byte newKeyID = key.getKeyID();
            if (player.getKeysID() == null) {
                player.setKeysID(new byte[]{newKeyID});
            } else {
                byte[] oldKeys = player.getKeysID();
                boolean already = false;
                for (byte k : oldKeys) if (k == newKeyID) already = true;
                if (!already) {
                    byte[] newArr = Arrays.copyOf(oldKeys, oldKeys.length + 1);
                    newArr[newArr.length - 1] = newKeyID;
                    player.setKeysID(newArr);
                }
            }
            key.setIsUsed(true);
        }

        for (NormalPiece p : pieceArr) {
            if (p != null && p.getCoordinate() != null &&
                    p.getCoordinate().getX() == oldX && p.getCoordinate().getY() == oldY) {
                p.setHasPlayer(false);
                break;
            }
        }

        targetPiece.setHasPlayer(true);
        player.setPosition(new Coordinate(newX, newY));

        if (targetPiece instanceof FinalPiece) player.setIsWinner(true);

        return true;
    }

    /* ===================== State Cloning ===================== */

    public Board cloneState() {
        NormalPiece[] newArr = new NormalPiece[pieceArr.length];

        for (int i = 0; i < pieceArr.length; i++) {
            NormalPiece p = pieceArr[i];
            if (p instanceof StartPiece sp) newArr[i] = new StartPiece(sp);
            else if (p instanceof FinalPiece fp) newArr[i] = new FinalPiece(fp);
            else if (p instanceof LockedPiece lp) newArr[i] = new LockedPiece(lp);
            else if (p instanceof UnlockerPiece up) newArr[i] = new UnlockerPiece(up);
            else if (p instanceof MultiPassPiece mp) newArr[i] = new MultiPassPiece(mp);
            else newArr[i] = new NormalPiece(p);
        }

        Player newPlayer = new Player(player);
        Board b = new Board(row, col, newArr, newPlayer);
        b.previousState = this;
        return b;
    }

    public Board moveState(char dir) {
        if (!canMove(dir)) return null;

        Board next = this.cloneState();
        next.move(dir);

        NormalPiece movedToPiece = null;
        for (NormalPiece p : next.pieceArr) {
            if (p.getCoordinate().getX() == next.player.getPosition().getX() &&
                    p.getCoordinate().getY() == next.player.getPosition().getY()) {
                movedToPiece = p;
                break;
            }
        }

        if (movedToPiece != null) next.score = (short) (this.score + movedToPiece.getCost());
        return next;
    }

    public List<Board> getSuccessors() {
        List<Board> list = new ArrayList<>();
        for (char d : new char[]{'w', 's', 'a', 'd'}) {
            Board next = moveState(d);
            if (next != null) list.add(next);
        }
        return list;
    }

    public boolean isGoal() {
        NormalPiece playerCell = null;
        for (NormalPiece p : pieceArr) {
            if (p.getHasPlayer()) {
                playerCell = p;
                break;
            }
        }
        return playerCell instanceof FinalPiece;
    }

    /* ===================== Printing ===================== */

    public void printBoard() {
        System.out.println("Score: " + score);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                NormalPiece target = null;
                for (NormalPiece p : pieceArr) {
                    if (p != null && p.getCoordinate() != null &&
                            p.getCoordinate().getX() == i && p.getCoordinate().getY() == j) {
                        target = p;
                        break;
                    }
                }
                if (target != null) {
                    char symbol = target.getPieceSymbol();
                    int cost = target.getCost();
                    boolean hasPlayer = target.getHasPlayer();
                    if (hasPlayer) System.out.print(symbol + "*[" + cost + "] ");
                    else System.out.print(symbol + "[" + cost + "]   ");
                } else {
                    System.out.print(".     ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printPathFromStart() {
        int length = 0;
        Board current = this;
        while (current != null) {
            length++;
            current = current.previousState;
        }

        Board[] path = new Board[length];
        current = this;
        int index = length - 1;
        while (current != null) {
            path[index] = current;
            index--;
            current = current.previousState;
        }

        System.out.println("\nPath from start:\n");
        for (Board b : path) {
            System.out.println("Score: " + b.getScore());
            for (int i = 0; i < b.row; i++) {
                for (int j = 0; j < b.col; j++) {
                    char symbol = '.';
                    int cost = 0;
                    boolean hasPlayer = false;
                    for (NormalPiece p : b.pieceArr) {
                        if (p.getCoordinate().getX() == i && p.getCoordinate().getY() == j) {
                            symbol = p.getPieceSymbol();
                            cost = p.getCost();
                            hasPlayer = p.getHasPlayer();
                            break;
                        }
                    }
                    if (hasPlayer) System.out.print(symbol + "*[" + cost + "] ");
                    else if (symbol != '.') System.out.print(symbol + "[" + cost + "]  ");
                    else System.out.print(".     ");
                }
                System.out.println();
            }
            System.out.println("------------------------------------------------------");
        }
    }

    /* ===================== Equals & HashCode ===================== */

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Board b)) return false;
        if (!player.getPosition().equals(b.player.getPosition())) return false;
        return Arrays.equals(player.getKeysID(), b.player.getKeysID());
    }

    @Override
    public int hashCode() {
        int h = 7;
        h = h * 31 + player.getPosition().getX();
        h = h * 31 + player.getPosition().getY();
        h = h * 31 + Arrays.hashCode(player.getKeysID());
        return h;
    }

    /* ===================== Heuristic & Comparable ===================== */

    public int heuristic() {
        NormalPiece goal = null;
        for (NormalPiece p : pieceArr) {
            if (p instanceof FinalPiece) {
                goal = p;
                break;
            }
        }
        if (goal == null) return 0;

        int dx = Math.abs(player.getPosition().getX() - goal.getCoordinate().getX());
        int dy = Math.abs(player.getPosition().getY() - goal.getCoordinate().getY());

        return dx + dy;
    }

    @Override
    public int compareTo(Board other) {
        int fThis = this.score + this.heuristic();
        int fOther = other.score + other.heuristic();
        return Integer.compare(fThis, fOther);
    }
}