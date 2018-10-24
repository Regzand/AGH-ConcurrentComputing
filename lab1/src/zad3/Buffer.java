package zad3;

public class Buffer {

    private String data;

    public synchronized void put(String data) {

        try {

            while (this.data != null)
                wait();

            this.data = data;

            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized String take() {

        String out = null;

        try {

            while (this.data == null)
                wait();


            out = this.data;
            this.data = null;

            notifyAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return out;
    }

}
