package org.example;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(5, 5);
        Bunny bunny = new Bunny(maze, 4, 4);
        Robot r1 = new Robot(maze, 1, 0, 4);
        Robot r2 = new Robot(maze, 2, 4, 0);

        Thread bt = new Thread(bunny);
        Thread rt1 = new Thread(r1);
        Thread rt2 = new Thread(r2);

        bt.start();
        rt1.start();
        rt2.start();

        Thread displayThread = new Thread(() -> {
            while (!maze.isGameFinished()) {
                maze.displayState();
                try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
            maze.displayState();
        });
        displayThread.start();
    }
}
