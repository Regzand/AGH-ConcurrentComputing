package zad1;

public class PrintProcessor extends Processor {

    public PrintProcessor(Buffer buffer, int inState, int outState) {
        super(buffer, inState, outState);
    }

    @Override
    void process(Buffer buffer, int i) {
        System.out.println(i+":\t"+buffer.data[i]);
    }
}
