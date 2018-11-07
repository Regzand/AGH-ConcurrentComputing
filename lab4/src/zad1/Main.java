package zad1;

public class Main {

    private static final int N = 20;

    public static void main(String[] args){

        Buffer buffer = new Buffer(N);

        new Thread(new GeneratorProcessor(buffer, 0, 1)).start();

        new Thread(new SuffixProcessor(buffer, 1, 2, "A")).start();
        new Thread(new SuffixProcessor(buffer, 2, 3, "B")).start();
        new Thread(new SuffixProcessor(buffer, 3, 4, "C")).start();
        new Thread(new SuffixProcessor(buffer, 4, 5, "D")).start();

        new Thread(new PrintProcessor(buffer, 5, -1)).start();

    }

}
