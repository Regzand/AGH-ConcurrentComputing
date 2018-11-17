package zad2;

import java.util.logging.Level;

public class Producer implements Runnable {

    private final int id;

    private final Buffer buffer;
    private final String prefix;

    private int index = 0;

    public Producer(int id, Buffer buffer, String prefix) {
        this.id = id;
        this.buffer = buffer;
        this.prefix = prefix;
    }

    @Override
    public void run() {
        while(true){

            // generate message
            int count = (int) (Math.random() * Main.M/2);
            String[] data = new String[count];
            for (int i = 0; i < count; i++)
                data[i] = this.prefix + this.index++;

            long startTime = System.nanoTime();

            // send data
            buffer.put(data);

            long duration = System.nanoTime() - startTime;
            System.out.printf("put,%d,%d\n", data.length, duration);

            // log
            if(Main.LOG)
                System.err.printf("Producer %d\t> [%d] = {%s}\n", id, count, String.join(",", data));

            // sleep
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException ignore) {}

        }
    }
}
