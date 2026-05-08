package org.example;
import java.util.Random;

public class Robot implements Runnable {
    private final Maze maze;
    private final int id;
    private int r, c;
    private final Random rand = new Random();

    public Robot(Maze maze, int id, int r, int c) {
        this.maze = maze;
        this.id = id;
        this.r = r;
        this.c = c;
        maze.getCell(r, c).setHasRobot(true);
    }

    @Override
    public void run() {
        while (!maze.isGameFinished()) {
            int move = rand.nextInt(4);
            int nr = r, nc = c;
            if (move == 0 && r > 0) nr--;
            else if (move == 1 && r < maze.getRows() - 1) nr++;
            else if (move == 2 && c > 0) nc--;
            else if (move == 3 && c < maze.getCols() - 1) nc++;

            if (nr != r || nc != c) {
               if (!maze.getCell(nr, nc).hasRobot()) {
                    maze.getCell(r, c).setHasRobot(false);
                    r = nr;
                    c = nc;
                    maze.getCell(r, c).setHasRobot(true);

                   if (maze.getCell(r, c).hasBunny()) {
                        System.out.println("Robot " + id + " caught the bunny");
                        maze.setGameFinished(true);
                        break;
                    }
                }
            }

            try { Thread.sleep(700); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}
