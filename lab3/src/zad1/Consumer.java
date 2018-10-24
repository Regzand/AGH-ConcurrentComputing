package zad1;

public class Consumer implements Runnable {

    private static final int OPERATIONS = 10000;

    private BoundedBuffer buffer;

    public Consumer(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        for (int i = 0; i < OPERATIONS; i++)
            System.out.println("< " + buffer.take());
    }
}
