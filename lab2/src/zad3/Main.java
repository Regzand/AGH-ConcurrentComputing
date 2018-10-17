package zad3;

public class Main {

    public static void main(String[] args){

        Buffer buffer = new Buffer();

        new Thread(new Consumer(buffer)).start();
        new Thread(new Producer(buffer)).start();

    }

}
