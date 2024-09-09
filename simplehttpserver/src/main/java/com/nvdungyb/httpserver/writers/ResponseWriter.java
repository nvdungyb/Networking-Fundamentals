package com.nvdungyb.httpserver.writers;

import com.nvdungyb.httpserver.config.HttpConfigurationAndResources;
import com.nvdungyb.httpserver.http.HttpMethod;
import com.nvdungyb.httpserver.http.HttpRequest;
import com.nvdungyb.httpserver.util.ImageResponse;
import com.nvdungyb.httpserver.util.ResponseUtil;
import com.nvdungyb.httpserver.util.TextFileReader;
import com.nvdungyb.httpserver.util.TextResponse;

import javax.imageio.ImageIO;
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
                if (object instanceof TextResponse) {
                    TextResponse fileReader = (TextResponse) object;
                    String fileExtension = fileReader.getFileExtension();

                    String content;
                    if (fileExtension.equals("txt")) {
                        String fileContents = fileReader.getFileContent().replace("\n", "<br>");     // replace newline "\n" in file text to <br> tag in file html.

                        content = "<html> <head> <body> <h1>This page was served using my simple http server.<h1>" +
                                "<h2> With method: " + request.getMethod() + ", and target uri: " + request.getRequestTarget()
                                + " With content: " + fileContents
                                + "</h2> </body> </head> </html>";
                    } else {
                        // This is the html file content.
                        content = fileReader.getFileContent();
                    }

                    String response = "HTTP/1.1 200 OK"                                               // Status Line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            + CRLF +
                            "Content-Type: text/html; charset=UTF-8" + CRLF +
                            "Content-Length: " + content.getBytes().length + CRLF                        // Header
                            + CRLF
                            + content
                            + CRLF + CRLF;

                    outputStream.write(response.getBytes());

                } else if (object instanceof ImageResponse) {
                    String fileExtension = ((ImageResponse) object).getFileExtension();

                    String contentType;
                    if (fileExtension.equals("png")) {
                        contentType = "Content-Type: image/png";
                    } else if (fileExtension.equals("jpg")) {
                        contentType = "Content-Type: image/jpeg";
                    } else if (fileExtension.equals("gif")) {
                        contentType = "Content-Type: image/gif";
                    } else {
                        throw new RuntimeException("Can not response with this file extension: " + fileExtension);
                    }

                    String responseHeader = "HTTP/1.1 200 OK" + CRLF +
                            contentType + CRLF + CRLF;

                    outputStream.write(responseHeader.getBytes());

                    if (fileExtension.equals("gif")) {
                        outputStream.write(((ImageResponse) object).getGifData());
                    } else {
                        ImageIO.write(((ImageResponse) object).getBufferedImage(), fileExtension, outputStream);            // Write image to output stream
                    }
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