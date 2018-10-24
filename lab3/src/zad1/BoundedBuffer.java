package zad1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {

    private final Lock lock = new ReentrantLock();
    private final Condition notFull  = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    private String data = null;

    public void put(String data) {

        lock.lock();

        try {
            while (this.data != null)
                notFull.await();

            this.data = data;

            notEmpty.signal();
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

    public String take() {

        String out = null;

        lock.lock();

        try {
            while (this.data == null)
                notEmpty.await();

            out = this.data;
            this.data = null;

            notFull.signal();

        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }

        return out;
    }
}
