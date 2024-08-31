package org.server;

import org.util.ParseDateFormat;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;

public class DaytimeTask implements Callable<Void> {
    public Socket socket;

    DaytimeTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Void call() {
        try {
            Writer writer = new OutputStreamWriter(this.socket.getOutputStream());

            Date now = new Date();
            writer.write(ParseDateFormat.format(now));
            writer.flush();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
