package school.sorokin.multithreading2;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {

    private List<Integer> numbers;
    private String taskName;

    public CalculateSumTask(List<Integer> numbers, String taskName) {
        this.numbers = numbers;
        this.taskName = taskName;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Имя задачи: " + taskName + ". Имя текущего потока: " + Thread.currentThread().getName());
        int sum = 0;
        for (Integer number : numbers) {
            Thread.sleep(200);
            sum += number;
        }
        return sum;
    }
}
