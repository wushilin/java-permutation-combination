package net.wushilin.combperm;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class PermutationIterator<T>  extends CombPermBase implements Iterator<List<T>>{
    private CombinationIterator<T> outerIterator;
    private FullPermutationIterator<T> innerIterator;

    public PermutationIterator() {

    }

    public PermutationIterator<T> init(List<T> elements, int choose) {
        if(elements == null || choose < 0 || choose > elements.size()) {
            throw new IllegalArgumentException("Candidates can't be null, choose must between [0, candidates.size]");
        }
        outerIterator = new CombinationIterator().init(elements, choose);
        List<T> firstBatch = outerIterator.next();
        innerIterator = new FullPermutationIterator<T>().init(firstBatch);
        checkDistinct(elements);
        return this;
    }

    public boolean hasNext() {
        if (innerIterator.hasNext()) {
            return true;
        } else {
            if (outerIterator.hasNext()) {
                List<T> selection = outerIterator.next();
                innerIterator.init(selection);
                return innerIterator.hasNext();
            } else {
                return false;
            }
        }
    }

    public List<T> next() {
        return innerIterator.next();
    }

}
