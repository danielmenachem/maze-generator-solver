public class Cell {
    public final int row;
    public final int col; 

    public Cell (int row, int col) {
        this.row = row;
        this.col = col; 
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")"; 
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true; 
        }

        if (!(object instanceof Cell)) {
            return false; 
        }

        Cell other = (Cell) object;
        return this.row == other.row && this.col == other.col; 
    }

    @Override 
    public int hashCode() {
        return 31 * row + col; 
    }
    
}
