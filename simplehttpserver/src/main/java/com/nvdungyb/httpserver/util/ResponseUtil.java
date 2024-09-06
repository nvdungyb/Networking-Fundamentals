package com.nvdungyb.httpserver.util;

import com.nvdungyb.httpserver.config.TargetResources;
import com.nvdungyb.httpserver.http.HttpStatusCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResponseUtil {
    public static String readFile(String requestTarget, TargetResources targetResources) {
        String filePath = null;
        if (targetResources.getResources().containsKey(requestTarget)) {
            filePath = targetResources.getResources().get(requestTarget);
        } else {
            throw new RuntimeException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);
        }

        int lastIndexOfDot = filePath.lastIndexOf('.');
        String fileType = filePath.substring(lastIndexOfDot + 1);

        if (fileType.equals("txt")) {
            return readTextFile(filePath);
        }

        return "";
    }

    public static String readTextFile(String filePath) {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath));
            StringBuffer sb = new StringBuffer();

            int character;
            while ((character = reader.read()) != -1) {
                sb.append((char) character);
            }

            return sb.toString();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new RuntimeException("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
