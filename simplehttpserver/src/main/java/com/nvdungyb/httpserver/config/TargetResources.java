package com.nvdungyb.httpserver.config;

import java.util.HashMap;

public class TargetResources {
    private static TargetResources targetResources;
    public static HashMap<String, String> resources;

    private TargetResources() {
    }

    public static TargetResources getInstance() {
        if (targetResources == null)
            targetResources = new TargetResources();
        return targetResources;
    }

    public void insertResource(String target, String uri) throws TargetResourcesException {
        if (!resources.containsKey(target)) {
            resources.put(target, uri);
        } else {
            throw new TargetResourcesException("This " + target + " already exists!");
        }
    }

    public HashMap<String, String> getResources() {
        return resources;
    }

    public void setResources(HashMap<String, String> resources) {
        this.resources = resources;
    }
}
