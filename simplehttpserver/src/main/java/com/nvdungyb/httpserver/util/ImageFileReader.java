package com.nvdungyb.httpserver.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFileReader implements FileReader {
    @Override
    public BufferedImage readFile(String filePath) {
        File file = new File(filePath);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException("Can not read image: " + filePath);
        }
    }
}
