package com.nvdungyb.httpserver.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextFileReader implements FileReader {
    public String readFile(String filePath) {
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
