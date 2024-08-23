package main.callback_java5;

import java.util.concurrent.Callable;

public class FindMaxTask implements Callable<Integer> {
    private Integer[] data;
    private int start;
    private int end;

    public FindMaxTask(Integer[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        Integer result = this.data[this.start];
        for (int i = this.start; i < this.end; i++) {
            if (data[i] > result)
                result = data[i];
        }
        return result;
    }
}
