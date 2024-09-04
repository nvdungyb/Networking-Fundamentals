package com.nvdungyb.httpserver.http;

import java.util.Arrays;

public class HttpRequest extends HttpMessage {
    private HttpMethod method;
    private String requestTarget;
    private String httpVersion;

    HttpRequest() {
    }

    public HttpMethod getMethod() {
        return method;
    }

    void setMethod(String methodName) throws HttpParsingException {
        HttpMethod method = Arrays.stream(HttpMethod.values())
                .filter(element -> element.name().equals(methodName))
                .findFirst()
                .orElseThrow(() -> new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED));

        this.method = method;
    }
}
