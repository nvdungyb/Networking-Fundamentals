package org.example;

import java.io.*;

public class ReadFileExample {
    public static void main(String[] args) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\acer\\OneDrive - ptit.edu.vn\\Desktop\\Network Programming\\source code\\networking\\stream\\src\\main\\resources\\source.txt"));

        byte[] data = new byte[1024];
        int byteRead;
        while ((byteRead = in.read(data)) != -1) {
            System.out.println(new String(data, 0, byteRead));
        }
    }
}

/*
1) BufferedInputStream: Wraps an InputStream to buffer the input, improving reading performance by reducing the number of I/O operations.
2) byte[] data: A byte array used to store chunks of data read from the stream.
2) read(data): Reads up to data.length bytes into the data array and return the number of bytes read.
 */