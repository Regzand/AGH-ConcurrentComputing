package zad1;

public class BinarySemaphore {

    private boolean open;

    public BinarySemaphore() {
        this(true);
    }

    public BinarySemaphore(boolean open) {
        this.open = open;
    }

    public synchronized void enter(){
        try {
            while (!open)
                wait();

            this.open = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void leave(){
        this.open = true;
        notifyAll();
    }

}
