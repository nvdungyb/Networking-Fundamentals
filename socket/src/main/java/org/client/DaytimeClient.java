package org.client;

import org.util.ParseDateFormat;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.util.Date;

public class DaytimeClient {
    private static final String HOST = "localhost";
    private static final int PORT = 13;

    public static Date toDateTime(String s) {
        Date currentTime = null;
        try {
            currentTime = ParseDateFormat.parse(s);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return currentTime;
    }

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
            socket.setSoTimeout(15000);

            StringBuilder builder = new StringBuilder();
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in, "ASCII");
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }

            System.out.println(toDateTime(builder.toString()));

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
