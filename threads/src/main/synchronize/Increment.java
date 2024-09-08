package main.synchronize;

public class Increment {
    private int number = 0;

    public synchronized void increment() {
        number++;           // This is not atomic operation: number = number + 1;
    }

    public void doWork() {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 10000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
        }
        System.out.println(number);
    }

    public static void main(String[] args) {
        Increment inc = new Increment();
        inc.doWork();
    }
}
