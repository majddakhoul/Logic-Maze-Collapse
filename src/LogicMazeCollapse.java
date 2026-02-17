
import static gameboard.algorithms.AStar.AStar;
import static gameboard.algorithms.DFS.DFS;
import static gameboard.algorithms.BFS.BFS;
import static gameboard.algorithms.UCS.UCS;

import gameboard.boardcells.*;
import gameboard.matrixcoordinates.Coordinate;
import gameboard.player.Player;
import gameboard.state.Board;

public class LogicMazeCollapse {

    // Initialize the first board with pieces and player's starting position
    public static Board initBoard1() {
        NormalPiece[] boardPieces = {
                new FinalPiece((byte) 1, new Coordinate((byte) 0, (byte) 4)),
                new NormalPiece((byte) 1, new Coordinate((byte) 0, (byte) 1)),
                new NormalPiece((byte) 1, new Coordinate((byte) 1, (byte) 0)),
                new NormalPiece((byte) 10, new Coordinate((byte) 0, (byte) 2)),
                new NormalPiece((byte) 10, new Coordinate((byte) 0, (byte) 3)),
                new NormalPiece((byte) 1, new Coordinate((byte) 0, (byte) 1)),
                new NormalPiece((byte) 10, new Coordinate((byte) 1, (byte) 1)),
                new NormalPiece((byte) 3, new Coordinate((byte) 1, (byte) 4)),
                new StartPiece((byte) 1, new Coordinate((byte) 0, (byte) 0)),
                new NormalPiece((byte) 1, new Coordinate((byte) 2, (byte) 0)),
                new NormalPiece((byte) 10, new Coordinate((byte) 2, (byte) 1)),
                new NormalPiece((byte) 10, new Coordinate((byte) 2, (byte) 2)),
                new NormalPiece((byte) 10, new Coordinate((byte) 2, (byte) 3)),
                new NormalPiece((byte) 1, new Coordinate((byte) 2, (byte) 4)),
                new NormalPiece((byte) 3, new Coordinate((byte) 3, (byte) 0)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 1)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 2)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 3)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 4)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 2))
        };

        Coordinate playerStart = new Coordinate((byte) 0, (byte) 0);
        Player player = new Player(playerStart);

        Board board = new Board((byte) 5, (byte) 5, boardPieces, player);
        return board;
    }

    // Initialize a second board configuration
    public static Board initBoard2() {
        NormalPiece[] boardPieces = {
                new FinalPiece((byte) 1, new Coordinate((byte) 0, (byte) 4)),
                new NormalPiece((byte) 1, new Coordinate((byte) 0, (byte) 1)),
                new NormalPiece((byte) 1, new Coordinate((byte) 0, (byte) 3)),

                new NormalPiece((byte) 1, new Coordinate((byte) 1, (byte) 0)),
                new NormalPiece((byte) 1, new Coordinate((byte) 1, (byte) 1)),
                new NormalPiece((byte) 1, new Coordinate((byte) 1, (byte) 3)),
                new NormalPiece((byte) 1, new Coordinate((byte) 1, (byte) 4)),

                new StartPiece((byte) 1, new Coordinate((byte) 0, (byte) 0)),

                new NormalPiece((byte) 1, new Coordinate((byte) 2, (byte) 0)),
                new NormalPiece((byte) 1, new Coordinate((byte) 2, (byte) 1)),
                new NormalPiece((byte) 1, new Coordinate((byte) 2, (byte) 3)),
                new NormalPiece((byte) 1, new Coordinate((byte) 2, (byte) 4)),

                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 0)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 1)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 2)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 3)),
                new NormalPiece((byte) 1, new Coordinate((byte) 3, (byte) 4))
        };

        Coordinate playerStart = new Coordinate((byte) 0, (byte) 0);
        Player player = new Player(playerStart);

        Board board = new Board((byte) 5, (byte) 5, boardPieces, player);
        return board;
    }

    public static void main(String[] args) {
        Board board = initBoard1();

        // Solve the board using A* search
        Board solution = AStar(board);
        if (solution != null) {
            solution.printPathFromStart();
            System.out.println("You won");
        } else {
            System.out.println("No solution found");
        }

        // Uncomment to use DFS
        /*
        Board solution = DFS(board);
        if (solution != null) {
            solution.printPathFromStart();
            System.out.println("You won");
        } else {
            System.out.println("No solution found");
        }
        */

        // Uncomment to use BFS
        /*
        Board solution = BFS(board);
        if (solution != null) {
            solution.printPathFromStart();
            System.out.println("You won");
        } else {
            System.out.println("No solution found");
        }
        */

        // Uncomment to use UCS
        /*
        Board solution = UCS(board);
        if (solution != null) {
            solution.printPathFromStart();
            System.out.println("You won");
        } else {
            System.out.println("No solution found");
        }
        */

        // Manual play mode (commented)
        /*
        Scanner input = new Scanner(System.in);

        while (true) {
            board.printBoard();
            System.out.print("Enter move (W/A/S/D): ");
            char c = input.next().toLowerCase().charAt(0);

            if (c != 'w' && c != 'a' && c != 's' && c != 'd') {
                System.out.println("Invalid input! Please enter W, A, S, or D only.");
                continue;
            }

            Board next = board.moveState(c);
            if (next == null) {
                System.out.println("Cannot move in that direction!");
                continue;
            }

            board = next;

            if (board.getPlayer().getIsWinner()) {
                System.out.println("You won!");
                board.printPathFromStart();
                break;
            }
        }
        input.close();
        */
    }
}