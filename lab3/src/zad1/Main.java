package zad1;

public class Main {

    public static void main(String[] args){

        BoundedBuffer buffer = new BoundedBuffer();

        new Thread(new Consumer(buffer)).start();
        new Thread(new Producer(buffer)).start();

    }

}
