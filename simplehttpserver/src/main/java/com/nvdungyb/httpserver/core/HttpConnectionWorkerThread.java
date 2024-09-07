package com.nvdungyb.httpserver.core;

import com.nvdungyb.httpserver.config.HttpConfigurationAndResources;
import com.nvdungyb.httpserver.http.HttpParser;
import com.nvdungyb.httpserver.http.HttpParsingException;
import com.nvdungyb.httpserver.http.HttpRequest;
import com.nvdungyb.httpserver.writers.ResponseWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

public class HttpConnectionWorkerThread extends Thread {
    private Socket socket;
    private HttpConfigurationAndResources configurationAndResources;
    private final static Logger logger = Logger.getLogger(HttpConnectionWorkerThread.class.getName());

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
        this.configurationAndResources = HttpConfigurationAndResources.getInstance();
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            HttpRequest request = new HttpParser().parseHttpRequest(inputStream);
            ResponseWriter.write(outputStream, request, configurationAndResources);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpParsingException e) {
            throw new RuntimeException(e);
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
                    logger.info("Connection was closed!");
                } catch (IOException e) {
                }
            }
        }
    }
}
