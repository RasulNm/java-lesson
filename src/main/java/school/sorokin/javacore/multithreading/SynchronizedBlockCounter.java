package school.sorokin.javacore.multithreading;

public class SynchronizedBlockCounter implements SiteVisitCounter {

    private Integer counter = 0;
    private static final Object lock = new Object();

    @Override
    public void incrementVisitCount() {
        synchronized (lock) {
            try {
                Thread.sleep(100);
                counter++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
