package zad1;

public abstract class Processor implements Runnable {

    private final Buffer buffer;
    private final int inState;
    private final int outState;

    public Processor(Buffer buffer, int inState, int outState) {
        this.buffer = buffer;
        this.inState = inState;
        this.outState = outState;
    }

    abstract void process(Buffer buffer, int i);

    @Override
    public void run() {
        for(int i = 0; i < buffer.n; i++) {

            buffer.lock(i, inState);

            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException ignore) {}

            process(buffer, i);


            buffer.unlock(i, outState);

        }
    }

}
