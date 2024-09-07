package com.nvdungyb.httpserver.writers;

import com.nvdungyb.httpserver.config.HttpConfigurationAndResources;
import com.nvdungyb.httpserver.http.HttpMethod;
import com.nvdungyb.httpserver.http.HttpRequest;
import com.nvdungyb.httpserver.util.ResponseUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

public class ResponseWriter {
    private static Logger logger = Logger.getLogger(ResponseWriter.class.getName());
    static final String CRLF = "\r\n";

    public static void write(OutputStream outputStream, HttpRequest request, HttpConfigurationAndResources configurationAndResources) {
        try {
            HttpMethod method = request.getMethod();
            String requestTarget = request.getRequestTarget().substring(1);
            if (!requestTarget.equals("favicon.ico") && method.name().equals(HttpMethod.GET.name())) {
                Object object = ResponseUtil.readFile(requestTarget, configurationAndResources.getTargetResources());
                if (object instanceof String) {
                    String fileContents = ((String) object).replace("\n", "<br>");     // replace newline "\n" in file text to <br> tag in file html.

                    String html = "<html> <head> <body> <h1>This page was served using my simple http server.<h1>" +
                            "<h2> With method: " + request.getMethod() + ", and target uri: " + request.getRequestTarget()
                            + " With content: " + fileContents
                            + "</h2> </body> </head> </html>";

                    String response = "HTTP/1.1 200 OK"                                               // Status Line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            + CRLF +
                            "Content-Type: text/html; charset=UTF-8" + CRLF +
                            "Content-Length: " + html.getBytes().length + CRLF                        // Header
                            + CRLF
                            + html
                            + CRLF + CRLF;

                    outputStream.write(response.getBytes());

                } else if (object instanceof BufferedImage) {
                    String responseHeader = "HTTP/1.1 200 OK" + CRLF +
                            "Content-Type: image/jpeg" + CRLF +
                            CRLF;
                    outputStream.write(responseHeader.getBytes());

                    ImageIO.write((BufferedImage) object, "JPG", outputStream);            // Write image to output stream
                }
                outputStream.flush();
            } else {
                logger.info("/favicon.ico doesn't excepted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
