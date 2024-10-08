package com.nvdungyb.httpserver;

import com.nvdungyb.httpserver.config.ConfigurationManager;
import com.nvdungyb.httpserver.config.HttpConfiguration;
import com.nvdungyb.httpserver.config.HttpConfigurationAndResources;
import com.nvdungyb.httpserver.config.TargetResources;
import com.nvdungyb.httpserver.core.ServerListenerThread;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Driver class for Http server.
 */
public class HttpServer {
    private final static Logger logger = Logger.getLogger(HttpServer.class.getName());

    public static void main(String[] args) {
        logger.info("Server starting...");

        HttpConfiguration httpConfiguration = ConfigurationManager.getInstance().loadConfigurationFile("simplehttpserver/src/main/resources/http.json", HttpConfiguration.class);
        HashMap<String, String> resources = ConfigurationManager.getInstance().loadConfigurationFile("simplehttpserver/src/main/resources/request_target.json", HashMap.class);
        TargetResources targetResources = new TargetResources(resources);

        HttpConfigurationAndResources httpConfigurationAndResources = HttpConfigurationAndResources.getInstance();
        httpConfigurationAndResources.setHttpConfiguration(httpConfiguration);
        httpConfigurationAndResources.setTargetResources(targetResources);

        System.out.println(httpConfigurationAndResources);

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(httpConfiguration.getPort(), httpConfiguration.getWebroot());
            serverListenerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
