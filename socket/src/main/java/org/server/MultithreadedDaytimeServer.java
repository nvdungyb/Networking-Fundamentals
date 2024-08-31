package org.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedDaytimeServer {
    public static final int PORT = 13;
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(50);

        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Connecting to the server!");
            while (true) {
                Socket socket = server.accept();
                Callable<Void> task = new DaytimeTask(socket);
                service.submit(task);
            }
        } catch (IOException exception) {
        }
    }
}
