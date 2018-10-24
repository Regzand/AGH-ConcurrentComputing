package zad2;

public class Semaphore {

    private int counter;

    public Semaphore(int initial) {
        this.counter = initial;
    }

    public synchronized void enter() {
        try {
            while (this.counter <= 0)
                wait();

            this.counter--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void leave() {
        this.counter++;
        notifyAll();
    }


}
