import javax.swing.*;
import java.awt.*;
import java.util.List; 

public class MazePanel extends JPanel {

    private final Maze maze;
    private final List<Cell> path;
    private final int cellSize; 

    private int revealedSteps = 0;

    public MazePanel(Maze maze, List<Cell> path, int cellSize) {
        this.maze = maze;
        this.path = path;
        this.cellSize = cellSize; 

        setPreferredSize(new Dimension(
            maze.getCols() * cellSize,
            maze.getRows() * cellSize
        ));
    }

    public void animateSolution(int delay) {
        Timer timer = new Timer(delay, e -> {
            if (revealedSteps < path.size()) {
                revealedSteps++; 
                repaint(); 
            } else {
                ((Timer) e.getSource()).stop();
            }
        }); 

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw maze
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                Cell cell = new Cell(row, col); 
                
                if(maze.getCell(cell) == '#') {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }

                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }

        // mark startv and goal
        Cell start = path.get(0);
        Cell goal = path.get(path.size() - 1); 

        g.setColor(Color.GRAY);
        g.fillRect(start.col * cellSize, start.row * cellSize, cellSize, cellSize);
        g.setColor(Color.WHITE);
        g.setFont(new Font(".ApplesystemUIFont", Font.BOLD, (int) (cellSize * 0.6)));
        g.drawString("S", start.col * cellSize + 7, start.row * cellSize + 17);

        g.setColor(Color.GRAY);
        g.fillRect(goal.col * cellSize, goal.row * cellSize, cellSize, cellSize);
        g.setColor(Color.WHITE);
        g.drawString("G", goal.col * cellSize + 7, goal.row * cellSize + 17);

        // draw solution gradually
        g.setColor(Color.PINK);

        for (int i = 1; i < revealedSteps - 1; i++) {
            Cell cell = path.get(i); 

            g.fillRect(cell.col * cellSize, cell.row * cellSize, cellSize, cellSize);
        }
    }
}
