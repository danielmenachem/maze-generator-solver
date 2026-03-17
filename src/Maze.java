import java.util.ArrayList;
import java.util.List; 

public class Maze {

    private final int rows;
    private final int cols; 
    private final char[][] grid; 

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols]; 
        initializeWalls();
    }

    private void initializeWalls() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = '#'; 
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols; 
    }
    
    // returns true if cell is in the bounds of the grid of this maze, and false otherwise. 
    public boolean inBounds(Cell cell) {
        return cell.row >= 0 && cell.row < rows && 
               cell.col >= 0 && cell.col < cols; 
    }

    // returns true if cell is a wall, false otherwise
    public boolean isWall(Cell cell) {
        if (!inBounds(cell)) {
            return false; 
        }
        return grid[cell.row][cell.col] == '#'; 
    }

    // sets cell to be a passage cell on this maze 
    public void setPassage(Cell cell) {
        if (!inBounds(cell)) {
            throw new IllegalArgumentException("Cell " + cell + " is out of maze bounds"); 
        }
        grid[cell.row][cell.col] = ' '; 
    }

    public char getCell(Cell cell) {
        if (!inBounds(cell)) {
            throw new IllegalArgumentException("Cell " + cell + " is out of maze bounds"); 
        }
        return grid[cell.row][cell.col];
    }

    public List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();

        Cell up = new Cell(cell.row - 1, cell.col);
        Cell down = new Cell(cell.row + 1, cell.col); 
        Cell right = new Cell(cell.row, cell.col + 1); 
        Cell left = new Cell(cell.row, cell.col - 1); 

        if (this.inBounds(up) && !this.isWall(up)) {
            neighbors.add(up); 
        }

        if (this.inBounds(down) && !this.isWall(down)) {
            neighbors.add(down); 
        }

        if (this.inBounds(right) && !this.isWall(right)) {
            neighbors.add(right); 
        }

        if (this.inBounds(left) && !this.isWall(left)) {
            neighbors.add(left); 
        }

        return neighbors; 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sb.append(grid[row][col]);
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
