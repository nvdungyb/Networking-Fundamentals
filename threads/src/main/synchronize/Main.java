package main.synchronize;

public class Main {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        new Producer(sharedData);
        new Consumer(sharedData);
    }
}
