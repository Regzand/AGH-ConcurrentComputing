package zad1;

public class Main {

    private static final int OPERATIONS = 1000000;

    public static void main(String[] args) throws Exception {

        Counter counter = new Counter();
        BinarySemaphore semaphore = new BinarySemaphore();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < OPERATIONS; i++) {
                semaphore.enter();
                counter.increase();
                semaphore.leave();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < OPERATIONS; i++) {
                semaphore.enter();
                counter.decrease();
                semaphore.leave();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("Result: %d", counter.value());
    }

}
