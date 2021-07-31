package examples;

import net.wushilin.combperm.PermutationIterable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PossibleWords {
    public static void main(String[] args) {
        List<Character> candidates = Arrays.asList('A', 'B', 'C', 'D','E');
        PermutationIterable<Character> permutations = new PermutationIterable(candidates, 3);
        System.out.println(permutations.stream().count() + " ways possible!");
        System.out.println("Word list:");
        permutations.stream().map(PossibleWords::listToString).filter(PossibleWords::isWord).forEach(System.out::println);
    }

    private static String listToString(List<Character> tokens) {
        return tokens.stream().map(String::valueOf).collect(Collectors.joining());
    }
    private static boolean isWord(String what) {
        // Check if what is a word!
        return "bed".equalsIgnoreCase(what) || "bad".equalsIgnoreCase(what);
    }
}
