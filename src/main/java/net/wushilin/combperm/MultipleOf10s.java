package net.wushilin.combperm;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultipleOf10s {
    public static void main(String[] args) {
        List<Integer> ints = IntStream.range(1, 50).boxed().collect(Collectors.toList());
        CombinationIterable<Integer> cmb = new CombinationIterable<>(ints, 3);
        cmb.stream().map(MultipleOf10s::sum).filter(MultipleOf10s::isMod10).forEach(
                it -> {
                    System.out.println(it.subList(0, it.size() - 1) + ", sum = " + it.get(it.size() - 1) +" is multiple of 10s!");
                }
        );
    }

    public static boolean isMod10(List<Integer> n) {
        return n.get(n.size() - 1) % 17 == 0;
    }
    public static List<Integer> sum(List<Integer> list) {
        int sum = 0;
        for(int next:list) {
            sum+=next;
        }
        list.add(sum);
        return list;
    }
}
