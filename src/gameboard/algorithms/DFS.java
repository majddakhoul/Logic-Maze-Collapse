package gameboard.algorithms;

import gameboard.state.Board;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS {

    public static Board DFS(Board start) {

        Stack<Board> stack = new Stack<>();
        Set<Board> visited = new HashSet<>();
        int count = 0;

        stack.push(start);

        while (!stack.isEmpty()) {

            Board c = stack.pop();
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
                    stack.add(next);
                }
            }
        }

        return null;
    }
}
