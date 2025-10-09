package school.sorokin.multithreading2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class CalculateSumTask implements Callable<Integer> {

    private List<Integer> numbers;
    private String taskName;
    private final AtomicInteger activeTasks;

    public CalculateSumTask(List<Integer> numbers, String taskName, AtomicInteger activeTask) {
        this.numbers = numbers;
        this.taskName = taskName;
        this.activeTasks = activeTask;
    }

    @Override
    public Integer call() throws Exception {
        int active = activeTasks.incrementAndGet();
//        System.out.println("[" + taskName + "] запущена в потоке " + Thread.currentThread().getName() +
//                " | Активных задач: " + active);

        System.out.println("Имя задачи: " + taskName + ". Имя текущего потока: " + Thread.currentThread().getName());
        int sum = 0;
        for (Integer number : numbers) {
            Thread.sleep(200);
            sum += number;
        }
        active = activeTasks.decrementAndGet();
//        System.out.println("[" + taskName + "] завершена | Активных задач: " + active);
        return sum;
    }
}
