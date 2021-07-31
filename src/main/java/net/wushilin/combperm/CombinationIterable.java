package net.wushilin.combperm;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CombinationIterable<T> extends CombPermBase implements Iterable<List<T>>{
    private List<T> candidates;
    private int choose;
    public CombinationIterable(List<T> candidates, int choose) {
        this.candidates = candidates;
        this.choose = choose;
        if(candidates == null || choose < 0 || choose > candidates.size()) {
            throw new IllegalArgumentException("Candidates can't be null, choose must between [0, candidates.size]");
        }
        checkDistinct(candidates);
    }
    @Override
    public Iterator<List<T>> iterator() {
        return new CombinationIterator().init(candidates, choose);
    }


    public Stream<List<T>> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        this.iterator(),
                        Spliterator.ORDERED)
                , false);
    }
}
