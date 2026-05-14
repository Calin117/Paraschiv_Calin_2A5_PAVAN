package org.example;
import java.io.*;
import java.net.Socket;

public class ClientThread implements Runnable {
    private Socket socket;
    private GameServer server;
    private Game game;
    private PrintWriter out;

    public ClientThread(Socket socket, GameServer server, Game game) {
        this.socket = socket;
        this.server = server;
        this.game = game;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            
            String request;
            while ((request = in.readLine()) != null) {
                if (request.startsWith("join ")) {
                    game.addPlayer(this, request.substring(5));
                } else if (request.equals("start")) {
                    game.start();
                } else if (request.equals("stop")) {
                    out.println("Server stopped");
                    server.stopServer();
                    break;
                } else {
                    game.submitAnswer(this, request);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public void sendMessage(String msg) {
        if (out != null) out.println(msg);
    }
}
