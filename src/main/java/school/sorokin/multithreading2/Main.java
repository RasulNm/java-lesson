package school.sorokin.multithreading2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        DataProcessor processor = new DataProcessor();

        for(int i = 0; i < 100; i++) {
            List<Integer> numbers = Arrays.asList(i + 10, i + 20);
            processor.submitTask(numbers);
        }

        while (true) {
            System.out.println("Ожидание завершения задач. Активных: " + processor.getActiveTaskCount());
            if(processor.getActiveTaskCount() == 0) {
                break;
            }
            Thread.sleep(500);
        }


        System.out.println("--------------------------------------------");
        System.out.println("Результат всех задач:");
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            processor.getResult("task" + i).ifPresent(v -> {
                try {
                    System.out.println("task" + finalI + " = " + v.get());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        processor.shutdown();
    }
}
