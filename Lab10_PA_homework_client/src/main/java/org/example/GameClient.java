package org.example;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int PORT = 8100;
        
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
             
            Thread listenerThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = in.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    System.out.println("Connection closed.");
                }
            });
            listenerThread.start();

            while (true) {
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    break;
                }
                out.println(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
