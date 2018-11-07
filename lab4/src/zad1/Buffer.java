package zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final Lock lock = new ReentrantLock();
    private final Condition stateChange = lock.newCondition();

    public final int n;

    public final String[] data;

    private final int[] state;
    private final boolean[] locked;

    public Buffer(int n) {
        this.n = n;
        this.data = new String[n];
        this.state = new int[n];
        this.locked = new boolean[n];
    }

    public void lock(int index, int expectedState){
        lock.lock();

        try {
            while (locked[index] || state[index] != expectedState)
                stateChange.await();

            locked[index] = true;
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }

    public void unlock(int index, int newState){
        lock.lock();
        state[index] = newState;
        locked[index] = false;
        stateChange.signalAll();
        lock.unlock();
    }
}
