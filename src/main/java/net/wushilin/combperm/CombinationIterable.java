package net.wushilin.combperm;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Create a combination iterable. It is standard java iterable.
 * Iterates over all possible combination based on the candidates given.
 *
 * The results are in lists, and order is using the original candidates order.
 *
 * The result lists are reused, there is no new lists allocated!
 * @param <T> The type of each candidates
 */
public class CombinationIterable<T> extends CombPermBase implements Iterable<List<T>>{
    private List<T> candidates;
    private int choose;

    /**
     * Create an iterable using the given candidates, and the number of elements to choose
     * @param candidates Candidate objects. It must be unique
     * @param choose Number of elements to choose. It has to be greater or equal to 0, and less or equal to candidate size
     */
    public CombinationIterable(List<T> candidates, int choose) {
        this.candidates = candidates;
        this.choose = choose;
        if(candidates == null || choose < 0 || choose > candidates.size()) {
            throw new IllegalArgumentException("Candidates can't be null, choose must between [0, candidates.size]");
        }
        checkDistinct(candidates);
    }

    /**
     * Return the iterator so you can use in loop
     * @return The iterator. It is a new iterator of the same configuration everytime you called it.
     */
    @Override
    public Iterator<List<T>> iterator() {
        return new CombinationIterator().init(candidates, choose);
    }


    /**
     * Return a new stream of list of type T.
     * Note that you are not supposed to cache the lists because they are all the same list.
     *
     * If you cache it (e.g. collect the stream to a list), the list of lists are all the same element!
     *
     * You should process it one by one.
     *
     * @return A new stream of list of type T.
     */
    public Stream<List<T>> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        this.iterator(),
                        Spliterator.ORDERED)
                , false);
    }
}
