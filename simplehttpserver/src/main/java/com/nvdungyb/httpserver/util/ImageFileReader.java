package com.nvdungyb.httpserver.util;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageFileReader implements FileReader {
    @Override
    public ImageResponse readFile(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        String fileExtension = filePath.substring(lastIndexOfDot + 1);

        File file = new File(filePath);
        try {
            if (fileExtension.equals("gif")) {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                return new ImageResponse(fileContent, fileExtension);
            }

            return new ImageResponse(ImageIO.read(file), fileExtension);
        } catch (IOException e) {
            throw new RuntimeException("Can not read image: " + filePath);
        }
    }
}
