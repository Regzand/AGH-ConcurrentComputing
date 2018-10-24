package zad2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMonitor {

    private final Lock lock = new ReentrantLock();
    private final Condition printerAvailable = lock.newCondition();

    private final Queue<Integer> freePrinters = new LinkedList<>();

    public PrinterMonitor(int printers) {
        for (int i = 0; i < printers; i++)
            freePrinters.add(i);
    }

    public int reservePrinter() {
        int number = -1;

        lock.lock();

        try {
            while (freePrinters.isEmpty())
                printerAvailable.await();

            number = freePrinters.remove();
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }

        return number;
    }

    public void freePrinter(int number) {
        lock.lock();
        freePrinters.add(number);
        lock.unlock();
    }

}
