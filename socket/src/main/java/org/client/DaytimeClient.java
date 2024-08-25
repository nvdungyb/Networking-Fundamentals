package org.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DaytimeClient {

    public static Date toDateTime(String s) {
        String[] arr = s.split(" ");
        String dateTime = arr[1] + " " + arr[2];
        Date currentTime = null;
        try {
            DateFormat df = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
            currentTime = df.parse(dateTime);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return currentTime;
    }

    public static void main(String[] args) {
        String hostname = args.length > 0 ? args[0] : "time.nist.gov";
        Socket socket = null;
        try {
            socket = new Socket(hostname, 13);
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
