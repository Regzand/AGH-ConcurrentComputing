package zad2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QueuedBuffer extends Buffer {

    private final Queue<Thread> threadQueue = new LinkedList<>();

    public QueuedBuffer(int size) {
        super(size);
    }

    @Override
    public String[] take(int count) {
        lock.lock();

        threadQueue.add(Thread.currentThread());

        try {
            while(getUsedSpace() < count && Thread.currentThread() == threadQueue.peek())
                newData.await();

            threadQueue.remove();
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
