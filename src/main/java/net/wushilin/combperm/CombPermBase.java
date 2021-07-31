package net.wushilin.combperm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CombPermBase {
    public <T> void checkDistinct(Collection<T> what) {
        if(what == null) {
            throw new IllegalArgumentException("Null candidates");
        }

        Set<T> test = new HashSet<T>();
        test.addAll(what);
        if(test.size()!=what.size()) {
            throw new IllegalArgumentException("Candidates are not unique");
        }
    }
}
