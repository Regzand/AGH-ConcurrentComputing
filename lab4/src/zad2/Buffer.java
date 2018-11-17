package zad2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    final Lock lock = new ReentrantLock();
    final Condition freeSpace = lock.newCondition();
    final Condition newData = lock.newCondition();

    private final int size;

    private int bufferHead;
    private int bufferTail;

    private String[] data;

    public Buffer(int size) {
        this.size = size;
        this.data = new String[size];
        this.bufferHead = 0;
        this.bufferTail = 0;
    }

    public int getSize() {
        return size;
    }

    public int getUsedSpace() {
        return bufferHead - bufferTail;
    }

    public int getFreeSpace() {
        return getSize() - getUsedSpace();
    }

    void putData(String[] d){
        for(int i = 0; i < d.length; i++)
            this.data[(this.bufferHead + i) % size] = d[i];

        this.bufferHead += d.length;
    }

    String[] takeData(int count){
        String[] out = new String[count];

        for(int i = 0; i < count; i++)
            out[i] = this.data[(this.bufferTail + i) % size];

        this.bufferTail += count;

        return out;
    }

    public void put(String[] d) {
        lock.lock();

        try {
            while(getFreeSpace() < d.length)
                freeSpace.await();

            putData(d);
            newData.signalAll();

        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }
    }

    public String[] take(int count) {
        lock.lock();

        try {
            while(getUsedSpace() < count)
                newData.await();

            String[] out = takeData(count);
            freeSpace.signalAll();

            return out;
        } catch (InterruptedException ignore) {
        } finally {
            lock.unlock();
        }

        return null;
    }
}
