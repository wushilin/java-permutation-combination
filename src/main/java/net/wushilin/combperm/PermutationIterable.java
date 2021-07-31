package net.wushilin.combperm;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * The iterable class iterates based on permutations P(M, N)
 * @param <T> The type of elements
 */
public class PermutationIterable<T> extends CombPermBase implements Iterable<List<T>>{
    private List<T> candidates;
    private int choose;

    /**
     * Create a new full permutation iterable: P(M, M) : it should have M! iterations.
     * @param candidates Candidates to choose from. It must be unique
     */
    public PermutationIterable(List<T> candidates) {
        this(candidates, candidates.size());
    }

    /**
     * Create a new partial permutation iterable: P(M, N)
     * @param candidates Candidates to choose from. It must be unique
     * @param choose Number of elements to choose
     */
    public PermutationIterable(List<T> candidates, int choose) {
        this.candidates = candidates;
        this.choose = choose;
        if(candidates == null || choose < 0 || choose > candidates.size()) {
            throw new IllegalArgumentException("Candidates can't be null, choose must between [0, candidates.size]");
        }
        checkDistinct(candidates);
    }

    /**
     * Get an iterator of the constructed configuration
     * @return The iterator. Every call returns a new iterator. It is ready only. Elements are generated on demand, memory
     * is reused.
     */
    @Override
    public Iterator<List<T>> iterator() {
        return new PermutationIterator<T>().init(candidates, choose);
    }

    /**
     * Convert this to a stream of List of type T. Each stream() call will generate a new stream and is
     * independent of each other.
     * @return The stream that can be used in filter, forEach etc.
     */
    public Stream<List<T>> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        this.iterator(),
                        Spliterator.ORDERED)
                , false);
    }
}
