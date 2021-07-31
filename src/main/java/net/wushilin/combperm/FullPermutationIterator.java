package net.wushilin.combperm;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class FullPermutationIterator<T>  extends CombPermBase implements Iterator<List<T>> {
    private List<T> candidates;
    private int[] indexes;
    private boolean hasNext;
    private List<T> resultList;
    public FullPermutationIterator() {
    }

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

    public boolean hasNext() {
        return hasNext;
    }

    private List<Integer> toList(int[] input) {
        List<Integer> result = new ArrayList();
        for(int next:input) {
            result.add(next);
        }
        return result;
    }

    public List<T> getCurrentList() {
        resultList.clear();
        for(int next:indexes) {
            resultList.add(candidates.get(next));
        }
        return resultList;
    }

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

    private void swap(int idx1, int idx2) {
        int tmp = indexes[idx1];
        indexes[idx1] = indexes[idx2];
        indexes[idx2] = tmp;
    }

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

    public List<T> next() {
        if(!hasNext) {
            throw new NoSuchElementException();
        }
        List<T> result = getCurrentList();
        hasNext = findNext();

        return result;
    }
}
