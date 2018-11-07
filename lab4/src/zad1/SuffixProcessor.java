package zad1;

public class SuffixProcessor extends Processor {

    private final String suffix;

    public SuffixProcessor(Buffer buffer, int inState, int outState, String suffix) {
        super(buffer, inState, outState);
        this.suffix = suffix;
    }

    @Override
    void process(Buffer buffer, int i) {
        buffer.data[i] += this.suffix;
    }
}
