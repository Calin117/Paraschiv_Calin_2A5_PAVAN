package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
    public static final int PORT = 8100;
    private boolean running = true;
    private ServerSocket serverSocket;
    private Game game = new Game();
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public GameServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);
            while (running) {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket, this, game);
                threadPool.execute(client);
            }
        } catch (IOException e) {
            if (running) e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GameServer();
    }
}
