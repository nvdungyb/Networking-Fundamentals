package com.nvdungyb.httpserver.core;

import com.nvdungyb.httpserver.config.HttpConfiguration;
import com.nvdungyb.httpserver.config.HttpConfigurationAndResources;
import com.nvdungyb.httpserver.config.TargetResources;
import com.nvdungyb.httpserver.http.HttpMethod;
import com.nvdungyb.httpserver.http.HttpParser;
import com.nvdungyb.httpserver.http.HttpParsingException;
import com.nvdungyb.httpserver.http.HttpRequest;
import com.nvdungyb.httpserver.util.ResponseUtil;

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

            HttpParser httpParser = new HttpParser();
            HttpRequest request = httpParser.parseHttpRequest(inputStream);

            HttpMethod method = request.getMethod();
            String requestTarget = request.getRequestTarget().substring(1);
            if (!requestTarget.equals("favicon.ico") && method.name().equals(HttpMethod.GET.name())) {
                String fileContents = ResponseUtil.readFile(requestTarget, configurationAndResources.getTargetResources()).replace("\n", "<br>");           // replace newline "\n" in file text to <br> tag in file html.

                String html = "<html> <head> <body> <h1>This page was served using my simple http server.<h1>" +
                        "<h2> With method: " + request.getMethod() + ", and target uri: " + request.getRequestTarget()
                        + " With content: " + fileContents
                        + "</h2> </body> </head> </html>";

                final String CRLF = "\r\n";                                         // 13, 10
                String response = "HTTP/1.1 200 OK"                                 // Status Line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                        + CRLF +
                        "Content-Type: text/html; charset=UTF-8" + CRLF +
                        "Content-Length: " + html.getBytes().length + CRLF          // Header
                        + CRLF
                        + html
                        + CRLF + CRLF;

                outputStream.write(response.getBytes());
            } else {
                logger.info("/favicon.ico doesn't excepted.");
            }
            logger.info("Connection was closed!");
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
                } catch (IOException e) {
                }
            }
        }
    }
}
