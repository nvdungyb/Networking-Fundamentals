package main.synchronize._volatile;

import java.util.Scanner;

public class CacheValue {
    public static void main(String[] args) {
        Processor pc = new Processor();
        pc.start();

        System.out.println("Enter 0 to exit.");
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            input = sc.nextInt();
            if (input == 0)
                pc.shutdown();
        } while (input != 0);
    }
}
