package com.nvdungyb.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class HttpConnectionWorkerThread extends Thread {
    private Socket socket;
    private final static Logger logger = Logger.getLogger(HttpConnectionWorkerThread.class.getName());

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            String html = "<html> <head> <body> <h1>This page was served using my simple http server.<h1> </body> </head> </html>";

            final String CRLF = "\r\n";                         //13, 10
            String response = "HTTP/1.1 200 OK"                 //Status Line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                    + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF + CRLF
                    + html + CRLF + CRLF;

            outputStream.write(response.getBytes());

            logger.info("Connection was closed!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
            try {
                outputStream.close();
            } catch (IOException e) {
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
