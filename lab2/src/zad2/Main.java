package zad2;

public class Main {

    public static final int CUSTOMERS = 10;
    public static final int BASKETS = 5;

    public static void main(String[] args) {

        Semaphore shop = new Semaphore(BASKETS);

        for (int i = 0; i < CUSTOMERS; i++)
            new Thread(new Customer(i, shop)).start();

    }

}
