package zad3;

public class Consumer implements Runnable {

    private static final int OPERATIONS = 10000;

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for(int i = 0;  i < OPERATIONS;   i++)
            System.out.println("< " + buffer.take());

    }
}
