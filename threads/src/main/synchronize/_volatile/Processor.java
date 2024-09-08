package main.synchronize._volatile;

public class Processor extends Thread {
    /* volatile
    : đảm bảo rằng giá trị của một biến luôn được đọc từ bộ nhớ chính, thay vì bộ nhớ đệm của thread.
        + Một biến được khai báo là volatile, mọi thay đổi của biến này sẽ được các thread khác nhìn thấy ngay lập tức.
        + Đảm bảo giá trị của biến luôn được đọc từ bọ nhớ chính, không phải là bộ đệm của thread.
     */
    private volatile boolean running = true;

    public void run() {
        int i = 0;
        while (running) {
            System.out.println("Print: " + i++);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}
