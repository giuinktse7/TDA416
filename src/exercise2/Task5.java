package exercise2;

import java.util.HashSet;

public class Task5 {

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5, 6};
        int target = 5;
        System.out.println(String.format("Got it? %b", exactSumIn(test, target)));
    }

    static boolean exactSumIn(int[] array, int target) {
        HashSet<Integer> lookup = new HashSet<>();

        for (int value : array) {
            if (lookup.contains(target - value))
                return true;

            lookup.add(value);
        }

        return false;
    }
}
