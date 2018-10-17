package zad2;

import java.util.Random;

public class Customer implements Runnable {

    public static final int VISITS = 30;
    private static final Random ran = new Random();

    private final int id;
    private final Semaphore shop;

    public Customer(int id, Semaphore shop) {
        this.id = id;
        this.shop = shop;
    }

    @Override
    public void run() {
        for(int i = 0; i<VISITS; i++){
            System.out.println("["+this.id+"] Enters shop");

            shop.enter();

            System.out.println("["+this.id+"] Takes basket");

            try {
                Thread.sleep(Math.abs(ran.nextInt()%500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("["+this.id+"] Puts down basket");

            shop.leave();

            System.out.println("["+this.id+"] Leaves shop");

            try {
                Thread.sleep(Math.abs(ran.nextInt()%500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
