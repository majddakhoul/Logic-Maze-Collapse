package gameboard.algorithms;

import gameboard.state.Board;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public static Board BFS(Board start) {

        Set<Board> visited = new HashSet<>();
        Queue<Board> q = new LinkedList<>();

        int count = 0;

        q.add(start);

        while (!q.isEmpty()) {

            Board c = q.poll();
            c.printBoard();

            if (visited.contains(c)) {
                continue;
            }

            visited.add(c);

            if (c.isGoal()) {
                System.out.println("Counter of visited state: " + visited.size());
                System.out.println("All state :" + count);
                return c;
            }

            for (Board next : c.getSuccessors()) {
                if (!visited.contains(next)) {
                    count++;
                    q.add(next);
                }
            }
        }

        return null;
    }
}