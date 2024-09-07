package com.nvdungyb.httpserver.util;

import lombok.Data;

import java.awt.image.BufferedImage;

@Data
public class ImageResponse {
    private String fileExtension;
    private BufferedImage bufferedImage;            // For normal image file: jpeg, png
    private byte[] gifData;                         // For Gif file.
    private boolean isGif;

    public ImageResponse(BufferedImage bufferedImage, String fileExtension) {
        this.bufferedImage = bufferedImage;
        this.fileExtension = fileExtension;
        this.isGif = false;
    }

    public ImageResponse(byte[] gifData, String fileExtension) {
        this.gifData = gifData;
        this.fileExtension = fileExtension;
        this.isGif = true;
    }
}
