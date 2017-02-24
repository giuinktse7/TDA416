package collection.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelectionSort {

    public static void main(String[] args) {
        int amount = 50;
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < amount; ++i) {
            //myInts[i] = random.nextInt(amount * 2);
            list.add(random.nextInt(amount * 2));
        }

        sort(list);

        list.forEach(System.out::println);
    }

    public static <E extends Comparable<E>> void sort(List<E> list) {
        for (int i = 0; i < list.size(); ++i) {
            int minIndex = i;
            E minvalue = list.get(minIndex);
            for (int k = i; k < list.size(); ++k) {
                if (list.get(k).compareTo(minvalue) < 0) {
                    minIndex = k;
                    minvalue = list.get(k);
                }
            }

            list.set(minIndex, list.get(i));
            list.set(i, minvalue);
        }
    }
}
