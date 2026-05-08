package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class DrawingPanel extends JPanel implements Serializable {
    private Cell[][] grid;
    private int rows, cols;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (grid == null) return;
                int cellW = getWidth() / cols;
                int cellH = getHeight() / rows;
                int j = e.getX() / cellW;
                int i = e.getY() / cellH;

                if (i >= 0 && i < rows && j >= 0 && j < cols) {
                    int xInCell = e.getX() % cellW;
                    int yInCell = e.getY() % cellH;

                    int dTop = yInCell;
                    int dBottom = cellH - yInCell;
                    int dLeft = xInCell;
                    int dRight = cellW - xInCell;

                    int min = Math.min(Math.min(dTop, dBottom), Math.min(dLeft, dRight));

                    Cell c = grid[i][j];
                    if (min == dTop) c.setTopWall(!c.hasTopWall());
                    else if (min == dBottom) c.setBottomWall(!c.hasBottomWall());
                    else if (min == dLeft) c.setLeftWall(!c.hasLeftWall());
                    else if (min == dRight) c.setRightWall(!c.hasRightWall());

                    repaint();
                }
            }
        });
    }

    public void initGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
        repaint();
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
        if (grid != null) {
            this.rows = grid.length;
            this.cols = grid[0].length;
        }
        repaint();
    }

    public Cell[][] getGrid() {
        return grid;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (grid == null) return;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));

        int cellW = getWidth() / cols;
        int cellH = getHeight() / rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = grid[i][j];
                int x = j * cellW;
                int y = i * cellH;

                if (c.hasTopWall()) g2d.drawLine(x, y, x + cellW, y);
                if (c.hasBottomWall()) g2d.drawLine(x, y + cellH, x + cellW, y + cellH);
                if (c.hasLeftWall()) g2d.drawLine(x, y, x, y + cellH);
                if (c.hasRightWall()) g2d.drawLine(x + cellW, y, x + cellW, y + cellH);
            }
        }
    }
}