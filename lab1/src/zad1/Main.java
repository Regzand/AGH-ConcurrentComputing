package zad1;

public class Main {

    private static final int OPERATIONS = 100000000;

    public static void main(String[] args) throws Exception {

        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
                for(int i = 0; i < OPERATIONS; i++)
                    counter.increase();
        });

        Thread thread2 = new Thread(() -> {
                for(int i = 0; i < OPERATIONS; i++)
                    counter.decrease();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.printf("Result: %d", counter.value());
    }

}
