package main.callback;

public class InstanceCallbackDigestInterface {
    private byte[] digest;
    private String filename;

    public InstanceCallbackDigestInterface(String filename) {
        this.filename = filename;
    }

    public void caculateDigest() {
        InstanceCallbackDigest callbackDigest = new InstanceCallbackDigest(this.filename, this);
        Thread t = new Thread(callbackDigest);
        t.start();
    }

    public void receiveDigest(byte[] digest) {
        this.digest = digest;
        System.out.println(this);
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Result: ");
        builder.append(toHex(this.digest));
        return builder.toString();
    }

    public static void main(String[] args) {
        String filename = "C:\\Users\\acer\\OneDrive - ptit.edu.vn\\Desktop\\Network Programming\\source code\\networking\\threads\\src\\main\\resources\\sha-resource";
        InstanceCallbackDigestInterface instance = new InstanceCallbackDigestInterface(filename);

        instance.caculateDigest();
    }
}
