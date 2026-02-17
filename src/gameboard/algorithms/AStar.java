package gameboard.algorithms;

import gameboard.state.Board;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar {

    public static Board AStar(Board s) {

        PriorityQueue<Board> pq = new PriorityQueue<>();
        Set<Board> v = new HashSet<>();

        int count = 0;

        pq.add(s);

        while (!pq.isEmpty()) {

            Board c = pq.poll();

            if (!v.contains(c)) {
                v.add(c);
            }

            c.printBoard();

            if (c.isGoal()) {
                System.out.println("Counter of visited state: " + v.size());
                System.out.println("All state :" + count);
                return c;
            }

            for (Board n : c.getSuccessors()) {
                if (!pq.contains(n) && !v.contains(n)) {
                    count++;
                    pq.add(n);
                }
            }
        }

        return null;
    }
}