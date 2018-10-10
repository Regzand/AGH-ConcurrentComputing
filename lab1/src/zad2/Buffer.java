package zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final Lock lock = new ReentrantLock();
    private final Condition empty = lock.newCondition();
    private final Condition full = lock.newCondition();

    private String data;

    public void put(String data){
        lock.lock();

        try {
            while (this.data != null)
                empty.await();

            this.data = data;
            full.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String take(){
        lock.lock();

        String out = null;

        try {
            while (this.data == null)
                full.await();

            out = this.data;
            this.data = null;

            empty.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return out;
    }

}
