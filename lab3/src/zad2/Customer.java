package zad2;

import java.util.Random;

public class Customer implements Runnable {

    private static final Random rand = new Random();

    private final int id;
    private final int prints;
    private final PrinterMonitor printerMonitor;

    public Customer(int id, int prints, PrinterMonitor printerMonitor) {
        this.id = id;
        this.prints = prints;
        this.printerMonitor = printerMonitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < prints; i++){
            try {

                Thread.sleep(Math.abs(rand.nextInt() % 500));
                String msg = String.format("\"This is my #%d print ~Customer%d\"", i, id);

                int printer = printerMonitor.reservePrinter();
                System.out.printf("[Customer %d] Reserved printer %d\n", id, printer);

                Thread.sleep(Math.abs(rand.nextInt() % 500));
                System.out.printf("%d > %s\n", printer, msg);

                printerMonitor.freePrinter(printer);
                System.out.printf("[Customer %d] Returned printer %d\n", id, printer);

            } catch (InterruptedException ignored) {}
        }
    }
}
