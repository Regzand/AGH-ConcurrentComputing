package zad3;

public class Main {

    private static final int PAIRS = 3;

    public static void main(String[] args) {

        Waiter waiter = new Waiter();

        for (int i = 0; i < PAIRS; i++) {
            new Thread(new Customer(2 * i, 2 * i + 1, waiter)).start();
            new Thread(new Customer(2 * i + 1, 2 * i, waiter)).start();
        }

    }

}
