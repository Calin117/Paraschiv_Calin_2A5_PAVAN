package org.example;
import java.util.Random;

public class Bunny implements Runnable {
    private final Maze maze;
    private int r, c;
    private final Random rand = new Random();

    public Bunny(Maze maze, int r, int c) {
        this.maze = maze;
        this.r = r;
        this.c = c;
        maze.getCell(r, c).setHasBunny(true);
    }

    @Override
    public void run() {
        while (!maze.isGameFinished()) {
            if (r == 0 && c == 0) {
                System.out.println("Bunny escaped!");
                maze.setGameFinished(true);
                break;
            }
            int move = rand.nextInt(4);
            int nr = r, nc = c;
            if (move == 0 && r > 0) nr--;
            else if (move == 1 && r < maze.getRows() - 1) nr++;
            else if (move == 2 && c > 0) nc--;
            else if (move == 3 && c < maze.getCols() - 1) nc++;

            if (nr != r || nc != c) {
                maze.getCell(r, c).setHasBunny(false);
                r = nr;
                c = nc;
                maze.getCell(r, c).setHasBunny(true);
                
                if (maze.getCell(r, c).hasRobot()) {
                    System.out.println("Bunny was caught by a robot!");
                    maze.setGameFinished(true);
                    break;
                }
            }

            try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}
