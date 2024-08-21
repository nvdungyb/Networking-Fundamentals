package org.example;

import java.io.*;

public class ReadFileExample {
    public static void main(String[] args) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\acer\\OneDrive - ptit.edu.vn\\Desktop\\Network Programming\\source code\\networking\\stream\\src\\main\\resources\\inputsource.txt"));

//        // Không nên sử dụng theo cách này.
//        long start = System.currentTimeMillis();
//        byte[] data = new byte[1024];
//        int byteRead;
//        while ((byteRead = in.read(data)) != -1) {
//            System.out.println(new String(data, 0, byteRead));
//        }
//        System.out.println("Time to completed the jobs: " + (System.currentTimeMillis() - start));

        // Nên sử dụng từ các lớp hỗ trợ sẵn.
        long start = System.currentTimeMillis();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("Time to completed the jobs: " + (System.currentTimeMillis() - start));
    }
}

/*
    1) BufferedInputStream: Wraps an InputStream to buffer the input, improving reading performance by reducing the number of I/O operations (which are expensive operations).
    2) byte[] data: A byte array used to store chunks of data read from the stream.
    3) read(data): Reads up to data.length bytes into the data array and return the number of bytes read.
 */