package school.sorokin.javacore.multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements SiteVisitCounter {

    int counter;
    ReentrantLock lock = new ReentrantLock();

    @Override
    public void incrementVisitCount() {
        lock.lock();
        try {
            Thread.sleep(100);
            counter++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
