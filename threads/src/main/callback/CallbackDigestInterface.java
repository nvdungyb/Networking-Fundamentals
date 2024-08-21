package main.callback;

public class CallbackDigestInterface {
    public static void receiveDigest(byte[] digest, String filename) {
        StringBuilder builder = new StringBuilder();
        builder.append("result: ");
        builder.append(toHex(digest));
        System.out.println(builder.toString());
    }

    public static String toHex(byte[] digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest) {
            String ans = Integer.toHexString(b & 0xff);
            if (ans.length() == 1)
                builder.append("0");
            builder.append(ans);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String filename = "C:\\Users\\acer\\OneDrive - ptit.edu.vn\\Desktop\\Network Programming\\source code\\networking\\threads\\src\\main\\resources\\sha-resource";
        CallbackDigest callbackDigest = new CallbackDigest(filename);
        Thread t = new Thread(callbackDigest);
        t.start();
    }
}
