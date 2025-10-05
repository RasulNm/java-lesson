package school.sorokin.javacore.multithreading;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingSiteVisitor {
    private SiteVisitCounter siteVisitCounter;

    private long startTime;
    private long endTime;
    private List<Thread> threadList = new ArrayList<>();

    public MultithreadingSiteVisitor(SiteVisitCounter siteVisitCounter) {
        this.siteVisitCounter = siteVisitCounter;
    }

    public void visitMultithread(int numOfThreads) {
        startTime = System.currentTimeMillis();
        for(int i = 0; i < numOfThreads; i++) {
            Thread thread = new Thread(() -> {
                siteVisitCounter.incrementVisitCount();
            });
            threadList.add(thread);
            thread.start();
        }
    }

    public void waitUntilAllVisited() {
        for(Thread thread: threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endTime = System.currentTimeMillis();
    }

    public long getTotalTimeOfHandling() {
        return (endTime - startTime);
    }
}
