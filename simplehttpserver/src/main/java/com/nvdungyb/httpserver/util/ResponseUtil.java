package com.nvdungyb.httpserver.util;

import com.nvdungyb.httpserver.config.TargetResources;
import com.nvdungyb.httpserver.http.HttpStatusCode;

import java.io.*;

public class ResponseUtil {
    public static ObjectResponse readFile(String requestTarget, TargetResources targetResources) {
        String filePath;
        String fileExtension;
        if (targetResources.getResources().containsKey(requestTarget)) {
            filePath = targetResources.getResources().get(requestTarget);
            fileExtension = getFileExtension(filePath);
        } else {
            throw new RuntimeException(HttpStatusCode.CLIENT_ERROR_400_BAD_REQUEST.MESSAGE);
        }

        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            return new ObjectResponse(byteArrayOutputStream, fileExtension);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFileExtension(String target) {
        int lastIndexOfDot = target.lastIndexOf('.');
        return target.substring(lastIndexOfDot + 1);
    }
}
