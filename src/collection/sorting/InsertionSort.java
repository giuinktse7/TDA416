package collection.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsertionSort {
    public static void main(String[] args) {
        int amount = 10;
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < amount; ++i) {
            //myInts[i] = random.nextInt(amount * 2);
            list.add(random.nextInt(amount * 2));
        }

        list.forEach(System.out::println);

        sort(list);

        System.out.println("hmm..");

        list.forEach(System.out::println);
    }

    @SuppressWarnings("unchecked")
    public static <E extends Comparable<E>> void sort(List<E> list) {
        for (int i = 1; i < list.size(); ++i) {
            E x = list.get(i);
            int k = i;
            while (--k >= 0 && x.compareTo(list.get(k)) < 0)
                list.set(k + 1, list.get(k));

            list.set(++k, x);
        }
    }
}
