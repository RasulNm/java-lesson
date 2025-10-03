package school.sorokin.javacore.multithreading;

public class VolatileCounter implements SiteVisitCounter {

    private volatile int counter;

    @Override
    public void incrementVisitCount() {
        try {
            Thread.sleep(100);
            counter++;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getVisitCount() {
        return counter;
    }
}
