package net.wushilin.combperm;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Permutation iterator
 * @param <T> The parameter type
 */
public class PermutationIterator<T>  extends CombPermBase implements Iterator<List<T>>{
    private CombinationIterator<T> outerIterator;
    private FullPermutationIterator<T> innerIterator;

    /**
     * Create a new dummy object. You have to call init (if you intend to use it directly).
     * If not, null pointer exception will be thrown.
     *
     * Why? We want this to be reusable so we don't waste memory.
     */
    public PermutationIterator() {

    }

    /**
     * Reinitialize this iterator with new configuration to save memory.
     * @param elements Candidates to choose from
     * @param choose Number of elements to choose
     * @return itself.
     */
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

    /**
     * Test if there are more permutations available
     * @return true if such permutation is found
     */
    @Override
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

    /**
     * Get the next permutation
     * @return The next permutation
     */
    @Override
    public List<T> next() {
        return innerIterator.next();
    }

}
