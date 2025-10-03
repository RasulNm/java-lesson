package school.sorokin.javacore.multithreading;

public interface SiteVisitCounter {
   // увеличить счетчик посещени
    public void incrementVisitCount();

    // получить текущее значение счетчика
    public int getVisitCount();
}
