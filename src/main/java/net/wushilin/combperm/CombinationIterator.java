package net.wushilin.combperm;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class CombinationIterator<T> extends CombPermBase implements Iterator<List<T>> {
    private List<T> candidates;
    private BitSet bitSet;
    private List<T> resultList;
    private boolean hasResult = true;

    public CombinationIterator() {

    }

    public CombinationIterator<T> init(List<T> candidates, int choose) {
        if(candidates == null || choose < 0 || choose > candidates.size()) {
            throw new IllegalArgumentException("Candidates can't be null, choose must between [0, candidates.size]");
        }
        int candidateSize = candidates.size();

        this.candidates = new ArrayList<T>(candidates.size());
        this.candidates.addAll(candidates);
        bitSet = new BitSet(candidateSize);
        int count = 0;
        while (count < choose) {
            bitSet.set(candidateSize - count - 1);
            count++;
        }
        resultList = new ArrayList(choose);
        hasResult = true;
        checkDistinct(candidates);

        return this;
    }



    private int searchForLast01() {
        int lastCheckedSetBit = candidates.size() - 1;
        while (true) {
            int nextSetBit = bitSet.previousSetBit(lastCheckedSetBit);
            if (nextSetBit < 1) {
                return -1;
            }
            if (!bitSet.get(nextSetBit - 1)) {
                return nextSetBit - 1;
            }
            lastCheckedSetBit = nextSetBit - 1;
            if (lastCheckedSetBit < 1) {
                return -1;
            }
        }
    }

    private List<T> getCurrentResult() {
        resultList.clear();
        for (int i = bitSet.nextSetBit(0); i != -1; i = bitSet.nextSetBit(i + 1)) {
            resultList.add(candidates.get(i));
        }
        return resultList;
    }

    /**
     * Shift all set bits to the right hand side.
     *
     * @param index
     */
    private void minimize(int index) {
        if (index >= candidates.size()) {
            return;
        }

        int count = 0;
        for (int i = bitSet.nextSetBit(index); i != -1; i = bitSet.nextSetBit(i + 1)) {
            bitSet.clear(i);
            count++;
        }
        int i = 0;
        while (i < count) {
            bitSet.set(candidates.size() - i - 1);
            i++;
        }
    }

    /*
    Flip the last found 01 to 10 in the bit set (the smallest ever increment that maintains the set number of 1s)
    Then shift all set bits to the right-hand side
     */
    private void flip01(int index) {
        bitSet.set(index);
        bitSet.clear(index + 1);
        minimize(index + 2);
    }

    /**
     * If there is a next, it must have already been found!
     *
     * @return The next result yes or no
     */
    public boolean hasNext() {
        return hasResult;
    }

    /**
     * Return the current result, and find the next result
     * The list is reused! Don't do multi threading! It will be cleared by the next() call!
     *
     * @return Next possible combination.
     */
    public List<T> next() {
        if (!hasResult) {
            throw new NoSuchElementException();
        }
        List<T> result = getCurrentResult();
        int index = searchForLast01();
        if (index == -1) {
            hasResult = false;
        } else {
            flip01(index);
            hasResult = true;
        }
        return result;
    }
}
