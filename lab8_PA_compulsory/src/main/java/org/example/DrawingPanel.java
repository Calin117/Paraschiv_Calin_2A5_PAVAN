package org.example;
import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private Cell[][] grid;
    private int rows, cols;

    public DrawingPanel() {
        setBackground(Color.WHITE);
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
                if (c.hasRightWall()) g2d.drawLine(x + cellW, y, x + cellW, y + cellH);}
        }
    }
}