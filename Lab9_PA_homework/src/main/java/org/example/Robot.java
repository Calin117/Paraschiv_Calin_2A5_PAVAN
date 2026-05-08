package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Robot implements Runnable {
    private final Maze maze;
    private final int id;
    private int r, c;
    private final boolean[][] visited;
    private long speed = 700;
    private boolean paused = false;

    public Robot(Maze maze, int id, int r, int c) {
        this.maze = maze;
        this.id = id;
        this.r = r;
        this.c = c;
        this.visited = new boolean[maze.getRows()][maze.getCols()];
        this.visited[r][c] = true;
        maze.getCell(r, c).setHasRobot(true);
    }

    public synchronized void setSpeed(long speed) { this.speed = speed; }
    public synchronized void setPaused(boolean paused) { this.paused = paused; }

    @Override
    public void run() {
        while (!maze.isGameFinished()) {
            synchronized (this) {
                while (paused && !maze.isGameFinished()) {
                    try { wait(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                }
            }
            if (maze.isGameFinished()) break;

            int[] dr = {-1, 1, 0, 0};
            int[] dc = {0, 0, -1, 1};
            List<Integer> validMoves = new ArrayList<>();
            List<Integer> unvisitedMoves = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (nr >= 0 && nr < maze.getRows() && nc >= 0 && nc < maze.getCols()) {
                    validMoves.add(i);
                    if (!visited[nr][nc]) {
                        unvisitedMoves.add(i);
                    }
                }
            }

            int move;
            if (!unvisitedMoves.isEmpty()) {
                move = unvisitedMoves.get(new Random().nextInt(unvisitedMoves.size()));
            } else if (!validMoves.isEmpty()) {
                move = validMoves.get(new Random().nextInt(validMoves.size()));
            } else {
                continue;
            }
            int nr = r + dr[move];
            int nc = c + dc[move];

            if (!maze.getCell(nr, nc).hasRobot()) {
                maze.getCell(r, c).setHasRobot(false);
                r = nr;
                c = nc;
                visited[r][c] = true;
                maze.getCell(r, c).setHasRobot(true);

                if (maze.getCell(r, c).hasBunny()) {
                    System.out.println("Robot " + id + " caught the bunny");
                    maze.setGameFinished(true);
                    break;
                }
            }
            try { Thread.sleep(speed); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}
