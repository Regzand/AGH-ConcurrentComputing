package zad2;

public class Consumer implements Runnable {

    private final int id;
    private final Buffer buffer;

    public Consumer(int id, Buffer buffer) {
        this.id = id;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true){

            long startTime = System.nanoTime();

            // get data
            String[] data = buffer.take((int) (Math.random() * Main.M/2));

            long duration = System.nanoTime() - startTime;
            System.out.printf("take,%d,%d\n", data.length, duration);

            // log
            if(Main.LOG)
                System.err.printf("Consumer %d\t< [%d] = {%s}\n", id, data.length, String.join(",", data));

            // sleep
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException ignore) {}

        }
    }
}
