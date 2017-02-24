package collection.sorting;

import java.util.List;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        List<Integer> list = SorterUtils.randomInts(1000, 0, 1000);

        System.out.println("List: " + list);
        sorter.sort(list);

        System.out.println("\n");
        System.out.println(list);
    }

    public <E extends Comparable<E>> void sort(List<E> list) {
        sort(list, 0, list.size() - 1);
    }

    private <E extends Comparable<E>> void sort(List<E> list, int low, int high) {
        int i = low, j = high;

        E pivot = list.get((low + high) / 2);

        while (i <= j) {
            while (list.get(i).compareTo(pivot) < 0) ++i;
            while (list.get(j).compareTo(pivot) > 0) --j;

            if (i <= j) {
                swap(list, i, j);
                ++i;
                --j;
            }
        }

        if (low < j) sort(list, low, j);
        if (i < high) sort(list, i, high);
    }

    private <E> void swap(List<E> list, int a, int b) {
        E temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }
}
