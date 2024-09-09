package com.nvdungyb.httpserver.util;

import lombok.Data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

@Data
public class TextFileReader implements FileReader {
    public TextResponse readFile(String filePath) {
        int lastIndexOfDot = filePath.lastIndexOf('.');
        String fileExtension = filePath.substring(lastIndexOfDot + 1);

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filePath));
            StringBuffer sb = new StringBuffer();

            int character;
            while ((character = reader.read()) != -1) {
                sb.append((char) character);
            }

            return new TextResponse(sb.toString(), fileExtension);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new RuntimeException("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
