package org.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DayTimeServer {
    private static int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Server has been created! Waiting for clients!");
                try (Socket connection = server.accept()) {
                    Writer out = new OutputStreamWriter(connection.getOutputStream());

                    Date now = new Date();
                    out.write(now.toString() + "\r\n");
                    out.flush();

                } catch (IOException e) {
                    System.out.println("Can not connect to the server!");
                }
            }
        } catch (IOException exception) {
            System.out.println("Can not create the server!");
        }
    }
}
