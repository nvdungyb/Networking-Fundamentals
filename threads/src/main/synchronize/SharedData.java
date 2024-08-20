package main.synchronize;

public class SharedData {
    int data;
    boolean produced = false;

    public synchronized void produce(int value) {
        if (produced) {
            try {
                this.wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        this.data = value;
        System.out.println("Produce: " + this.data);
        this.produced = true;
        notify();
    }

    public synchronized void consume() {
        if (!produced) {
            try {
                this.wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        System.out.println("Consume: " + this.data);
        produced = false;
        this.notify();
    }
}
