package org.example;
import java.util.Scanner;

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

        ManagerThread manager = new ManagerThread(maze, 60000);
        manager.start();

        Thread inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!maze.isGameFinished()) {
                if (scanner.hasNextLine()) {
                    String cmd = scanner.nextLine();
                    if (cmd.equals("fast")) {
                        bunny.setSpeed(200);
                        r1.setSpeed(300);
                        r2.setSpeed(300);
                    } else if (cmd.equals("slow")) {
                        bunny.setSpeed(1000);
                        r1.setSpeed(1500);
                        r2.setSpeed(1500);
                    } else if (cmd.equals("stop")) {
                        bunny.setPaused(true);
                        r1.setPaused(true);
                        r2.setPaused(true);
                    } else if (cmd.equals("resume")) {
                        bunny.setPaused(false);
                        r1.setPaused(false);
                        r2.setPaused(false);
                    }
                }
            }
        });
        inputThread.setDaemon(true);
        inputThread.start();
    }
}
