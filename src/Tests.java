public class Tests {
    public static void main (String[] args) {
        getNeighborsTest();
    }

    public static void mazeTest() {
        Maze maze = new Maze(5, 7); 
        System.out.println("initial maze:");
        System.out.println(maze);

        Cell c1 = new Cell(1,1); 
        Cell c2 = new Cell(2,3); 

        maze.setPassage(c1);
        maze.setPassage(c2);

        System.out.println("after carving:");
        System.out.println(maze);

        System.out.println("Is (1,1) a wall? " + maze.isWall(c1));
        System.out.println("Is (0,0) a wall? " + maze.isWall(new Cell(0,0)));

        System.out.println("Is (10,10) in of bounds? " + !maze.inBounds(new Cell(10,10))); 
        System.out.println("Is (1,1) out of bounds? " + !maze.inBounds(c1));

        // in order to test Exception: 
        // maze.setPassage(new Cell(10,10)); 
    }

    public static void mazeGeneratorTest() {
        MazeGenerator generator = new MazeGenerator(); 
        Maze maze = generator.generate(11, 11); 
        System.out.println(maze);
    }

    public static void cellTest() {
        Cell a = new Cell(1,2);
        Cell b = new Cell(1,2); 

        System.out.println(a.equals(b));
    }

    public static void getNeighborsTest() {
        MazeGenerator generator = new MazeGenerator(); 
        Maze maze = generator.generate(11, 11);
        System.out.println("The maze:");
        System.out.println(maze);
        Cell c1 = new Cell(1,1); 
        System.out.println("Neighbors of " + c1 + " :");
        System.out.println(maze.getNeighbors(c1));
    }
    
}
