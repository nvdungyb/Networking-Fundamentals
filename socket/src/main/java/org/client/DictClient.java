package org.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class DictClient {
    public static final String SERVER = "dict.org";
    public static final int PORT = 2628;
    public static final int TIMEOUT = 30000;

    public static void define(String word, Writer writer, BufferedReader reader) throws IOException {
        writer.write("DEFINE gcide " + word + "\r\n");
        writer.flush();

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (line.startsWith("250 ")) {
                return;
            } else if (line.startsWith("552 ")) {
                System.out.println("No definition found for " + word);
                return;
            } else if (line.matches("\\d\\d\\d .*")) continue;
            else if (line.trim().equals(".")) continue;
            else System.out.println(line);
        }
    }

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(SERVER, PORT);
            socket.setSoTimeout(TIMEOUT);

            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out, "UTF-8");
            writer = new BufferedWriter(writer);

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Enter a word to define (or type 'exit' to quit): ");
                String word = sc.nextLine();
                if (word.equalsIgnoreCase("exit"))
                    break;

                define(word, writer, reader);
            }

            writer.flush();
            writer.write("quit\r\n");
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
