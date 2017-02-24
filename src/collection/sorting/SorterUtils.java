package collection.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SorterUtils {

    public static List<Integer> randomInts(int amount, int startInclusive, int endExclusive, int seed) {
        List<Integer> result = new ArrayList<>();
        Random random = seed == -1 ? new Random() : new Random(seed);
        random.ints(amount, startInclusive, endExclusive).forEach(result::add);
        return result;
    }

    public static List<Integer> randomInts(int amount, int startInclusive, int endExclusive) {
        return randomInts(amount, startInclusive, endExclusive, -1);
    }
}
