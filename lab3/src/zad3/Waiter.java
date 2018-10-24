package zad3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Waiter {

    private final Lock lock = new ReentrantLock();
    private final Condition freeTable  = lock.newCondition();
    private final Condition visitedTable  = lock.newCondition();
//
//    private int seatsTaken = 0;
//    private int waitingFor = -1;
//    private boolean visited = false;
    private List<Integer> waiting = new ArrayList<>();

    private int reservedFor = -1;
    private int seatsTaken = 0;
    private boolean visited = false;

    public void reserveTable(int clientId, int dateId){
        lock.lock();

        try {

            waiting.add(clientId);

            while (true){

                if (reservedFor == clientId) {
                    reservedFor = -1;
                    seatsTaken += 1;
                    visited = true;
                    visitedTable.signalAll();
                    break;
                }

                if (seatsTaken == 0 && waiting.contains(dateId)) {
                    reservedFor = dateId;
                    seatsTaken += 1;
                    visited = false;
                    freeTable.signalAll();
                    break;
                }

                freeTable.await();
            }

            waiting.remove(waiting.indexOf(clientId));

        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

    public void freeTable(){
        lock.lock();

        try {

            while (!visited)
                visitedTable.await();

            seatsTaken -= 1;
            freeTable.signalAll();

        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

}
