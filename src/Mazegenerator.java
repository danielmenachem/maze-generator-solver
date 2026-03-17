import java.util.Arrays;
import java.util.Random;
import java.util.List; 
import java.util.Collections; 

public class MazeGenerator {

    private final Random random = new Random(); 

    public Maze generate(int rows, int cols) {
        if (rows % 2 == 0 || cols % 2 == 0) {
            throw new IllegalArgumentException(
                "Maze dimensions must be odd numbers. Receieved: " + rows + " x " + cols
            ); 
        }

        if (rows < 3 || cols < 3) {
            throw new IllegalArgumentException(
                "Maze dimensions must be at least 3x3. Receieved: " + rows + " x " + cols
            ); 
        }

        Maze maze = new Maze(rows, cols); 
        
        Cell start = new Cell(1,1); 
        maze.setPassage(start);
        carve(maze, start); 

        return maze; 
    }

    private void carve(Maze maze, Cell current) {
        // generate a list of 4 possible directions 
        List<int[]> directions = Arrays.asList(
            new int[] {-1, 0},           // up
            new int[] {1, 0},            // down 
            new int[] {0, -1},           // left
            new int[] {0,1}              // right
        ); 

        // shuffle the list randomly, to create a random maze
        Collections.shuffle(directions, random); 

        for (int[] dir : directions) {
            int dr = dir[0];
            int dc = dir[1]; 

            // finds the next cell in the given dir. move 2 cells to skip the wall between them
            Cell next = new Cell(
                current.row + 2 * dr, 
                current.col + 2 * dc
            ); 

            // if the next cell is a wall, carves a passage that leads to it, and continue recursively 
            if (maze.inBounds(next) && maze.isWall(next)) {
                
                Cell wall = new Cell(
                    current.row + dr,
                    current.col + dc
                ); 

                maze.setPassage(wall);
                maze.setPassage(next);

                carve(maze, next); 
            }
        }
    }
}