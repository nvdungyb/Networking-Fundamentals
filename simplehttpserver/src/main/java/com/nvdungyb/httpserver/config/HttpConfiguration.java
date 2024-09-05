package com.nvdungyb.httpserver.config;

import lombok.Data;

@Data
public class HttpConfiguration {
    private int port;
    private String webroot;
}
