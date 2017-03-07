package collection.sorting;

import java.util.*;

public class SorterUtils {

    public static List<Integer> randomIntsList(int amount, int startInclusive, int endExclusive, int seed) {
        List<Integer> result = new ArrayList<>();
        Random random = seed == -1 ? new Random() : new Random(seed);
        random.ints(amount, startInclusive, endExclusive).forEach(result::add);
        return result;
    }

    public static int[] randomInts(int amount, int startInclusive, int endExclusive, int seed) {
        return (seed == -1 ? new Random() : new Random(seed))
                .ints(amount, startInclusive, endExclusive).toArray();
    }

    public static int[] randomInts(int amount, int startInclusive, int endExclusive) {
        return randomInts(amount, startInclusive, endExclusive, -1);
    }

    public static List<Integer> randomIntsList(int amount, int startInclusive, int endExclusive) {
        return randomIntsList(amount, startInclusive, endExclusive, -1);
    }

    public static void printArray(int[] array) {
        Arrays.stream(array, 0, array.length - 1).forEach(i -> {
            System.out.printf("%d, ", i);
        });

        System.out.printf("%d\n", array[array.length - 1]);
    }
}
