package main.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestThread extends Thread {
    private String filename;

    public DigestThread(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);

            while (din.read() != -1) ;
            din.close();
            byte[] digest = sha.digest();

            StringBuilder result = new StringBuilder("result");
            result.append(": ");
            result.append(toHex(digest));
            System.out.println(result);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Theo nguyên lý nào?
    // Không hiểu tại sao từ mảng byte có thể chuyển về kiểu Hex như trên lại đúng?
    private String toHex(byte[] digest) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(b & 0xff);
            if (hex.length() == 1) {
                hexString.append("0");
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        String filename = "C:\\Users\\acer\\OneDrive - ptit.edu.vn\\Desktop\\Network Programming\\source code\\networking\\threads\\src\\main\\resources\\sha-resource";
        DigestThread t = new DigestThread(filename);
        t.start();
    }
}
