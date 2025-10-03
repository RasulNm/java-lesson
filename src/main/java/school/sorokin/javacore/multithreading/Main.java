package school.sorokin.javacore.multithreading;

public class Main {
    public static void main(String[] args) {
        SiteVisitCounter unsynchronizedCounter = new UnsynchronizedCounter();
        SiteVisitCounter volatileCounter = new VolatileCounter();
        SiteVisitCounter synchronizedBlockCounter = new SynchronizedBlockCounter();
        SiteVisitCounter atomicIntegerCounter = new AtomicIntegerCounter();
        SiteVisitCounter reentrantLockCounter = new ReentrantLockCounter();

        //Скорость: Максимальная, так как нет синхронизации.
        //Правильность: Неверная — возможна гонка данных и потеря обновлений при инкременте.
        System.out.println("UnsynchronizedCounter");
        MultithreadingSiteVisitor multithreadingSiteVisitor1 = new MultithreadingSiteVisitor(unsynchronizedCounter);
        multithreadingSiteVisitor1.visitMultithread(10);
        multithreadingSiteVisitor1.waitUntilAllVisited();
        System.out.println("Общее время обработки: " + multithreadingSiteVisitor1.getTotalTimeOfHandling() + " мс.");
        System.out.println("Текущее значение счетчика: " + unsynchronizedCounter.getVisitCount());
        System.out.println("----------------------------------");


        //Скорость: Быстрая, почти как у несинхронизированного.
        //Правильность: Неправильная. Хотя volatile обеспечивает видимость между потоками, он не делает операции инкремента атомарными.
        //Это может привести к потере данных при конкурентном доступе.
        System.out.println("VolatileCounter");
        MultithreadingSiteVisitor multithreadingSiteVisitor2 = new MultithreadingSiteVisitor(volatileCounter);
        multithreadingSiteVisitor2.visitMultithread(10);
        multithreadingSiteVisitor2.waitUntilAllVisited();
        System.out.println("Общее время обработки: " + multithreadingSiteVisitor2.getTotalTimeOfHandling() + " мс.");
        System.out.println("Текущее значение счетчика: " + volatileCounter.getVisitCount());
        System.out.println("----------------------------------");

        //Скорость: Медленнее из-за затрат на блокировку, особенно при высокой конкуренции.
        //Правильность: Правильная — synchronized гарантирует атомарность операций и корректный доступ к общему ресурсу.
        System.out.println("SynchronizedBlockCounter");
        MultithreadingSiteVisitor multithreadingSiteVisitor3 = new MultithreadingSiteVisitor(synchronizedBlockCounter);
        multithreadingSiteVisitor3.visitMultithread(10);
        multithreadingSiteVisitor3.waitUntilAllVisited();
        System.out.println("Общее время обработки: " + multithreadingSiteVisitor3.getTotalTimeOfHandling() + " мс.");
        System.out.println("Текущее значение счетчика: " + synchronizedBlockCounter.getVisitCount());
        System.out.println("----------------------------------");

        //Скорость: Быстрее synchronized — использует CAS без блокировок
        //Корректность: Правильно, гарантирует атомарность и видимость
        System.out.println("AtomicIntegerCounter");
        MultithreadingSiteVisitor multithreadingSiteVisitor4 = new MultithreadingSiteVisitor(atomicIntegerCounter);
        multithreadingSiteVisitor4.visitMultithread(10);
        multithreadingSiteVisitor4.waitUntilAllVisited();
        System.out.println("Общее время обработки: " + multithreadingSiteVisitor4.getTotalTimeOfHandling() + " мс.");
        System.out.println("Текущее значение счетчика: " + atomicIntegerCounter.getVisitCount());
        System.out.println("----------------------------------");

        //Скорость: как у синхронизированного блока
        //Корректность: Правильная. ReentrantLock гарантирует взаимное исключение и обеспечивает корректную работу с общим ресурсом.
        System.out.println("ReentrantLockCounter");
        MultithreadingSiteVisitor multithreadingSiteVisitor5 = new MultithreadingSiteVisitor(reentrantLockCounter);
        multithreadingSiteVisitor5.visitMultithread(10);
        multithreadingSiteVisitor5.waitUntilAllVisited();
        System.out.println("Общее время обработки: " + multithreadingSiteVisitor5.getTotalTimeOfHandling() + " мс.");
        System.out.println("Текущее значение счетчика: " + reentrantLockCounter.getVisitCount());
        System.out.println("----------------------------------");
    }
}
