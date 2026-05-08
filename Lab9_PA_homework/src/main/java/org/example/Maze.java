package org.example;

public class Maze {
    private final Cell[][] grid;
    private final int rows;
    private final int cols;
    private boolean gameFinished = false;

    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public Cell getCell(int r, int c) { return grid[r][c]; }

    public synchronized boolean isGameFinished() {
        return gameFinished;
    }

    public synchronized void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public synchronized void displayState() {
        if (gameFinished) return;
        System.out.println("Maze State:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j].hasBunny() && grid[i][j].hasRobot()) {
                    System.out.print("[X]");
                } else if (grid[i][j].hasBunny()) {
                    System.out.print("[B]");
                } else if (grid[i][j].hasRobot()) {
                    System.out.print("[R]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
        System.out.println("----------");
    }
}
