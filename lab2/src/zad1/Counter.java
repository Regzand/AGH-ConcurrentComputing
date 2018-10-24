package zad1;

public class Counter {

    private int count = 0;

    public synchronized void increase() {
        this.count++;
    }

    public synchronized void decrease() {
        this.count--;
    }

    public synchronized int value() {
        return count;
    }
}
