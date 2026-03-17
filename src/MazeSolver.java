import java.util.LinkedList;
import java.util.List;
import java.util.Queue; 
import java.util.Set; 
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MazeSolver {
    
    public List<Cell> solve(Maze maze, Cell start, Cell goal) {
        Queue<Cell> queue = new LinkedList<>();  
        Set<Cell> visited = new HashSet<>(); 
        Map<Cell, Cell> parent = new HashMap<>(); 

        queue.add(start); 
        visited.add(start); 

        while(!queue.isEmpty()) {
            Cell current = queue.remove(); 

            // if we reached the goal cell, reconstruct the path  
            if (current.equals(goal)) {
                return reconstructPath(start, goal, parent); 
            }

            for (Cell neighbor : maze.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);              // mark neighbor as visited
                    parent.put(neighbor, current);      // set current as the parent of neighbor 
                    queue.add(neighbor);                // add neighbor to queue
                }
            }
        }

        return null;                                    // never reach this point
    }

    private List<Cell> reconstructPath(Cell start, Cell goal, Map<Cell, Cell> parent) {
        List<Cell> path = new ArrayList<>(); 
        Cell current = goal; 

        while (!current.equals(start)) {
            path.add(current); 
            current = parent.get(current); 
        }

        path.add(start); 

        Collections.reverse(path);

        return path; 
    }
}
