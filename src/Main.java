import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Main <rows> <cols>");
        }
        
        int rows = Integer.parseInt(args[0]); 
        int cols = Integer.parseInt(args[1]); 

        MazeGenerator generator = new MazeGenerator(); 
        Maze maze = generator.generate(rows, cols); 

        Cell start = new Cell(1,1); 
        Cell goal = new Cell(rows - 2, cols - 2); 

        MazeSolver solver = new MazeSolver(); 
        List<Cell> path = solver.solve(maze, start, goal); 

        System.out.println("Generated maze:");
        System.out.println(maze);

        System.out.println("\n");

        System.out.println("Solution:");
        System.out.println(maze.solution(path));
    }
}
