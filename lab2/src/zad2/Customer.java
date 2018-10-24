package zad2;

public class Customer implements Runnable {

    public static final int VISITS = 30;

    private final int id;
    private final Semaphore shop;

    public Customer(int id, Semaphore shop) {
        this.id = id;
        this.shop = shop;
    }

    @Override
    public void run() {
        for (int i = 0; i < VISITS; i++) {
            try {
                System.out.println("[" + this.id + "] Enters shop");

                shop.enter();

                System.out.println("[" + this.id + "] Takes basket");

                Thread.sleep((long) (Math.random() * 500));

                System.out.println("[" + this.id + "] Puts down basket");

                shop.leave();

                System.out.println("[" + this.id + "] Leaves shop");

                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException ignore) {
            }
        }
    }
}
