package main.callback_java5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadedMaxFinder {
    public static Integer max(Integer[] data) throws ExecutionException, InterruptedException {
        if (data.length == 1)
            return data[0];
        else if (data.length == 0)
            throw new IllegalArgumentException();

        FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);

        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future1 = service.submit(task1);
        Future<Integer> future2 = service.submit(task2);

        return Math.max(future1.get(), future2.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Integer[] data = RetriveData.get();

        long timeStart = System.currentTimeMillis();
        System.out.println(max(data));
        long timeEnd = System.currentTimeMillis();
        System.out.println("Time to completed the task in synchronized: " + (timeEnd - timeStart));
    }
}
