package org.example;

public class ManagerThread extends Thread {
    private final Maze maze;
    private final long timeLimitMs;
    private final long startTime;

    public ManagerThread(Maze maze, long timeLimitMs) {
        this.maze = maze;
        this.timeLimitMs = timeLimitMs;
        this.startTime = System.currentTimeMillis();
        setDaemon(true);
    }

    @Override
    public void run() {
        while (!maze.isGameFinished()) {
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.println("Time elapsed: " + (elapsed / 1000) + "s / " + (timeLimitMs / 1000) + "s");
            maze.displayState();

            if (elapsed > timeLimitMs) {
                System.out.println("Time limit exceeded. Stopping the game.");
                maze.setGameFinished(true);
                break;
            }

            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}
