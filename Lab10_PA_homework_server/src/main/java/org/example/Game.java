package org.example;
import java.io.*;
import java.util.*;

public class Game {
    private List<String> questions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private Map<ClientThread, Player> players = new HashMap<>();
    private boolean isRunning = false;
    private int currentQuestionIndex = 0;
    private long questionStartTime;
    private static final long TIME_LIMIT = 10000;

    public Game() {
        try (BufferedReader br = new BufferedReader(new FileReader("questions.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    questions.add(parts[0]);
                    answers.add(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addPlayer(ClientThread client, String name) {
        players.put(client, new Player(name));
        broadcast("Player " + name + " joined the game!");
    }

    public synchronized void start() {
        if (players.size() < 2 || questions.isEmpty()) {
            broadcast("Not enough players or questions to start.");
            return;
        }
        isRunning = true;
        currentQuestionIndex = 0;
        broadcast("Game started!");
        askQuestion();
    }

    private void askQuestion() {
        if (currentQuestionIndex < questions.size()) {
            broadcast("Question: " + questions.get(currentQuestionIndex));
            questionStartTime = System.currentTimeMillis();
        } else {
            endGame();
        }
    }

    public synchronized void submitAnswer(ClientThread client, String answer) {
        if (!isRunning) return;
        long timeTaken = System.currentTimeMillis() - questionStartTime;
        Player p = players.get(client);
        
        if (timeTaken > TIME_LIMIT) {
            client.sendMessage("Time's up!");
        } else if (answer.equalsIgnoreCase(answers.get(currentQuestionIndex))) {
            p.addScore(1);
            p.addTime(timeTaken);
            broadcast(p.getName() + " answered correctly in " + timeTaken + "ms!");
        } else {
            client.sendMessage("Wrong answer!");
        }
        
        currentQuestionIndex++;
        askQuestion();
    }

    private void endGame() {
        isRunning = false;
        broadcast("Game Over!");
        Player winner = null;
        for (Player p : players.values()) {
            if (winner == null) {
                winner = p;
            } else if (p.getScore() > winner.getScore()) {
                winner = p;
            } else if (p.getScore() == winner.getScore() && p.getTotalResponseTime() < winner.getTotalResponseTime()) {
                winner = p;
            }
        }
        if (winner != null) {
            broadcast("Winner is " + winner.getName() + " with score " + winner.getScore() + " and total time " + winner.getTotalResponseTime() + "ms.");
        }
    }

    public synchronized void broadcast(String message) {
        for (ClientThread c : players.keySet()) {
            c.sendMessage(message);
        }
    }
}
