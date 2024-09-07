package com.nvdungyb.httpserver.util;

import com.nvdungyb.httpserver.config.TargetResources;
import com.nvdungyb.httpserver.http.HttpStatusCode;

public class ResponseUtil {
    public static Object readFile(String requestTarget, TargetResources targetResources) {
        String filePath = null;
        if (targetResources.getResources().containsKey(requestTarget)) {
            filePath = targetResources.getResources().get(requestTarget);
        } else {
            throw new RuntimeException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);
        }

        int lastIndexOfDot = filePath.lastIndexOf('.');
        String fileType = filePath.substring(lastIndexOfDot + 1);

        if (fileType.equals("txt")) {
            return new TextFileReader().readFile(filePath);
        } else if (fileType.equals("jpg")) {
            return new ImageFileReader().readFile(filePath);
        }

        return "";
    }
}
