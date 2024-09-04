package com.nvdungyb.httpserver.http;

import java.util.Arrays;

public enum HttpMethod {
    GET, HEAD;

    public static final int MAX_LENGTH;

    static {
        MAX_LENGTH = Arrays.stream(HttpMethod.values())
                .map(method -> method.name().length())
                .max(Integer::compareTo)
                .orElse(0);
    }
}
