package zad3;

import zad1.BinarySemaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final BinarySemaphore empty = new BinarySemaphore(true);
    private final BinarySemaphore full = new BinarySemaphore(false);

    private String data;

    public void put(String data){
        empty.enter();

        this.data = data;

        full.leave();
    }

    public String take(){
        full.enter();

        String out = this.data;

        empty.leave();

        return out;
    }

}
