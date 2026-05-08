package org.example;

public class Cell {
    private int row;
    private int col;
    private boolean hasBunny = false;
    private boolean hasRobot = false;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public synchronized boolean hasBunny() { return hasBunny; }
    public synchronized void setHasBunny(boolean hasBunny) { this.hasBunny = hasBunny; }

    public synchronized boolean hasRobot() { return hasRobot; }
    public synchronized void setHasRobot(boolean hasRobot) { this.hasRobot = hasRobot; }
}
