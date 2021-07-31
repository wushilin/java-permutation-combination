package net.wushilin.combperm;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A full permutation iterator, it is P(N,N) always.
 * @param <T> The type parameter
 */
public class FullPermutationIterator<T>  extends CombPermBase implements Iterator<List<T>> {
    private List<T> candidates;
    private int[] indexes;
    private boolean hasNext;
    private List<T> resultList;
    public FullPermutationIterator() {
    }

    /**
     * Re-initialize this to be a full permutation iterator. It is designed to be reused so we don't
     * allocate extra memory.
     * @param candidates The candidates of Type T to choose from
     * @return itself.
     */
    public FullPermutationIterator init(List<T> candidates) {
        if(candidates == null) {
            throw new IllegalArgumentException("Candidates can't be null");
        }
        int candidateSize = candidates.size();
        this.candidates = candidates;
        indexes = new int[candidateSize];
        for(int i = 0; i < candidateSize; i++) {
            indexes[i] = i;
        }
        hasNext = true;
        resultList = new ArrayList<T>(candidateSize);
        checkDistinct(candidates);
        return this;
    }

    /**
     * Test if more elements are available
     * @return true if there are more to this iterator
     */
    public boolean hasNext() {
        return hasNext;
    }

    /**
     * Convert int array to list
     * @param input the input
     * @return The list
     */
    private List<Integer> toList(int[] input) {
        List<Integer> result = new ArrayList();
        for(int next:input) {
            result.add(next);
        }
        return result;
    }

    /**
     * Get Current result
     * @return The list of selections
     */
    private List<T> getCurrentList() {
        resultList.clear();
        for(int next:indexes) {
            resultList.add(candidates.get(next));
        }
        return resultList;
    }

    /**
     * Find the longest decreasing sequence from right
     * @return The start of the sequence
     */
    private int findLongestDecreasingSequence() {
        int currentIndex = candidates.size() - 1;
        if(currentIndex < 0) {
            return -1;
        }
        while(true) {
            int currentValue = indexes[currentIndex];
            if(currentIndex - 1 < 0) {
                return currentIndex;
            }
            int prev = indexes[currentIndex - 1];
            if(prev < currentValue) {
                return currentIndex;
            }
            currentIndex--;
        }
    }

    /**
     * Swap the index values in the internal state
     * @param idx1 Index of first element
     * @param idx2 Index of second element
     */
    private void swap(int idx1, int idx2) {
        int tmp = indexes[idx1];
        indexes[idx1] = indexes[idx2];
        indexes[idx2] = tmp;
    }

    /**
     * Search for next available combination
     * @return true if found, false otherwise
     */
    private boolean findNext() {
        // find longest decreasing sequence from right to left
        // Find a digit that is larger than the the left digit
        // Swap them
        // Sort the right hand side.
        int longestSequence = findLongestDecreasingSequence();
        if(longestSequence <= 0) {
            return false;
        }
        int leftValue = indexes[longestSequence-1]; //2
        int largerIndex = longestSequence;
        while(indexes[largerIndex] > leftValue) {
            largerIndex++;
            if(largerIndex >= candidates.size()) {
                break;
            }
        }
        largerIndex--;
        swap(longestSequence - 1, largerIndex);
        Arrays.sort(indexes, longestSequence, candidates.size());
        return true;
    }

    /**
     * Get the current available combination, and search for next one.
     *
     * If none available, the next call to hasNext() will be false
     * @return The next combination
     */
    public List<T> next() {
        if(!hasNext) {
            throw new NoSuchElementException();
        }
        List<T> result = getCurrentList();
        hasNext = findNext();

        return result;
    }
}
