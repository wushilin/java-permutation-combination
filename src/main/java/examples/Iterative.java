package examples;

import net.wushilin.combperm.CombinationIterable;
import net.wushilin.combperm.PermutationIterable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Iterative {
    public static void main(String[] args) {
        List<Integer> candidates = Arrays.asList(1,2,3,4,5);
        CombinationIterable<Integer> comb = new CombinationIterable(candidates, 3);
        Iterator<List<Integer>> iter = comb.iterator();
        System.out.println("Combinations:");
        while(iter.hasNext()) {
            // iter.next() always return the same list, with different content, don't re-use it in your code.
            // Content will be cleared as soon as you call iter.next()! The iterator is super memory efficient
            // It runs 400M iterations without any additional memory!
            System.out.println(iter.next());
        }

        System.out.println("Permutations:");
        PermutationIterable<Integer> perm = new PermutationIterable<>(candidates, 3);
        iter = perm.iterator();
        while(iter.hasNext()) {
            // iter.next() always return the same list, with different content, don't re-use it in your code.
            // Content will be cleared as soon as you call iter.next()! The iterator is super memory efficient
            // It runs 400M iterations without any additional memory!
            System.out.println(iter.next());
        }
    }
}
