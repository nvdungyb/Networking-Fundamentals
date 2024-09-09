package com.nvdungyb.httpserver.util;

public class TextResponse {
    private String fileContent;
    private String fileExtension;

    TextResponse(String fileContent, String fileExtension) {
        this.fileContent = fileContent;
        this.fileExtension = fileExtension;
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
