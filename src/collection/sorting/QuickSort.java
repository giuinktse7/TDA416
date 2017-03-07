package collection.sorting;

import java.util.List;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        List<Integer> list = SorterUtils.randomIntsList(50, 0, 100);
        /*List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(0);*/

        System.out.println("List: " + list);
        sorter.sort(list, 0, list.size() - 1);

        System.out.println("\n");
        System.out.println(list);
    }

    private <E extends Comparable<E>> void sort(List<E> list, int from, int to) {
        if (from >= to)
            return;

        swap(list, from, getPivotIndex(list, from, to));
        E pivot = list.get(from);

        int low = from + 1;
        int high = from + 1;

        while (high <= to) {
            if (list.get(high).compareTo(pivot) < 0)
                swap(list, low++, high);

            ++high;
        }

        swap(list, from, low - 1);

        sort(list, from, low - 2);
        sort(list, low, to);
    }

    private <E extends Comparable<E>> int getPivotIndex(List<E> list, int from, int to) {
        return new Random().nextInt(to - from + 1) + from;
    }

    private <E> void swap(List<E> list, int a, int b) {
        E temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }
}
