package school.sorokin.multithreading2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {

    private ExecutorService executorService;
    private AtomicInteger count = new AtomicInteger();
    private AtomicInteger activeTasks = new AtomicInteger();
    private Map<String, Future<Integer>> resultMap = new HashMap<>();
    private static final Object lock = new Object();

    public DataProcessor() {
        this.executorService = Executors.newFixedThreadPool(20);
    }

    public void submitTask(List<Integer> numbers) throws ExecutionException, InterruptedException {
        String taskName = "task" + count.incrementAndGet();
        CalculateSumTask task = new CalculateSumTask(numbers, taskName, activeTasks);

        Future<Integer> resultFuture = executorService.submit(task);

//        int resultSum = resultFuture.get();

        synchronized (lock) {
            resultMap.put(taskName, resultFuture);
        }
    }

    public int getActiveTaskCount() {
        return activeTasks.get();
    }

    public Optional<Future<Integer>> getResult(String taskName) {
        synchronized (lock) {
            return Optional.ofNullable(resultMap.get(taskName));
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
