package main.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.*;

public class DigestThread implements Callable<String> {
    private String filename;
    private byte[] digest;

    public DigestThread(String filename) {
        this.filename = filename;
    }

    public String call() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);

            while (din.read() != -1) ;
            din.close();
            this.digest = sha.digest();
            return toHex(digest);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getDigest() {
        return digest;
    }

    // Theo nguyên lý nào?
    // Không hiểu tại sao từ mảng byte có thể chuyển về kiểu Hex như trên lại đúng?
    private static String toHex(byte[] digest) {
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

    public static void print(String filename) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        DigestThread digestThread = new DigestThread(filename);

        Future<String> future = service.submit(digestThread);

        StringBuilder result = new StringBuilder("result: ");
        result.append(filename);
        result.append(": ");
        result.append(future.get());
        System.out.println(result);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        String filename1 = "C:\\Users\\acer\\OneDrive - ptit.edu.vn\\Desktop\\Network Programming\\source code\\networking\\threads\\src\\main\\resources\\sha-resource";
        String filename2 = "C:\\Users\\acer\\OneDrive - ptit.edu.vn\\Desktop\\Network Programming\\source code\\networking\\threads\\src\\main\\resources\\sha-1-resource";

        print(filename1);
        print(filename2);
    }
}
