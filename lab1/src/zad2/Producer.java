package zad2;

public class Producer implements Runnable {

    private static final int OPERATIONS = 10000;

    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for (int i = 0; i < OPERATIONS; i++) {
            buffer.put("message " + i);
            System.out.println("> message " + i);
        }

    }
}