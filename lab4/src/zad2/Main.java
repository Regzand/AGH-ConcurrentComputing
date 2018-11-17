package zad2;

import java.util.logging.Logger;

public class Main {

    public static final int M = 20;
    public static final int P = 3;
    public static final int K = 3;

    public static final boolean LOG = true;

    public static void main(String[] args){

        Buffer buffer = new Buffer(M);

        for(int i = 0; i < P; i++)
            new Thread(new Producer(i, buffer, i+"-")).start();

        for(int i = 0; i < K; i++)
            new Thread(new Consumer(i, buffer)).start();
    }

}
