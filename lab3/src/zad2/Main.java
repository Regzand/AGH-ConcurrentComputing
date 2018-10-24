package zad2;

public class Main {

    private static final int PRINTERS = 5;
    private static final int CUSTOMERS = 10;
    private static final int PRINTS = 10;

    public static void main(String[] args) {

        PrinterMonitor printerMonitor = new PrinterMonitor(PRINTERS);

        for (int i = 0; i < CUSTOMERS; i++)
            new Thread(new Customer(i, PRINTS, printerMonitor)).start();
    }

}
