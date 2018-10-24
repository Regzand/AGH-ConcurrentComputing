package zad2;

public class Customer implements Runnable {

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
        for (int i = 0; i < prints; i++) {
            try {

                Thread.sleep((long) (Math.random() * 500));
                String msg = String.format("\"This is my #%d print ~Customer%d\"", i, id);

                int printer = printerMonitor.reservePrinter();
                System.out.printf("%s Reserved printer %d\n", this, printer);

                Thread.sleep((long) (Math.random() * 500));
                System.out.printf("%d > %s\n", printer, msg);

                printerMonitor.freePrinter(printer);
                System.out.printf("%s Returned printer %d\n", this, printer);

            } catch (InterruptedException ignored) {
            }
        }
    }

    @Override
    public String toString() {
        return String.format("[Customer %02d]", this.id);
    }
}
