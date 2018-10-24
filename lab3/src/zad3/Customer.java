package zad3;

import java.util.Random;

public class Customer implements Runnable {

    private static final Random rand = new Random();

    private static final int VISITS = 10;

    private final int customerId;
    private final int dateId;
    private final Waiter waiter;

    public Customer(int customerId, int dateId, Waiter waiter) {
        this.customerId = customerId;
        this.dateId = dateId;
        this.waiter = waiter;
    }

    @Override
    public void run() {
        for (int i = 0; i < VISITS; i++){
            try {

                Thread.sleep(Math.abs(rand.nextInt() % 500));

                System.out.printf("%s Waiting for table\n", this);
                waiter.reserveTable(customerId, dateId);
                System.out.printf("%s Sit at the table\n", this);

                Thread.sleep(Math.abs(rand.nextInt() % 500));

                waiter.freeTable();
                System.out.printf("%s Leaves table\n", this);

            } catch (InterruptedException igonre) {}
        }
    }

    @Override
    public String toString() {
        return String.format("[Customer #%02d (%02d)]", customerId, dateId);
    }
}
