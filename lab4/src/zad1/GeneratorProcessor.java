package zad1;

public class GeneratorProcessor extends Processor {

    public GeneratorProcessor(Buffer buffer, int inState, int outState) {
        super(buffer, inState, outState);
    }

    @Override
    void process(Buffer buffer, int i) {
        buffer.data[i] = "> ";
    }
}
