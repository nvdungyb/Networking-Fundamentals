package com.nvdungyb.httpserver;

import com.nvdungyb.httpserver.config.Configuration;
import com.nvdungyb.httpserver.config.ConfigurationManager;

import java.io.IOException;

/**
 * Driver class for Http server.
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("simplehttpserver/src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println(conf.getPort());
        System.out.println(conf.getWebroot());
    }
}
