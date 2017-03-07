package collection.sorting;

import java.util.*;
import java.util.stream.IntStream;

public class MergeSort {
    public static void main(String[] args) {
        int amount = 50;
        int[] myInts = new int[amount];
        int[] theirInts = new int[amount];
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        //random.ints(amount, 0, 100).forEach(list::add);
        for (int i = 0; i < amount; ++i) {
            myInts[i] = random.nextInt(100);
            theirInts[i] = myInts[i];
        }

        for (int i : myInts) System.out.print(i + ", ");
        sort(myInts);

        System.out.println();
        System.out.println();

        for (int i : myInts) System.out.print(i + ", ");

    }

    public static <E extends Comparable<E>> void sort(List<E> list) {
        sort(list, 0, list.size() - 1);
    }

    public static void sort(int[] list) {
        int[] copy = new int[list.length];
        sort(list, copy, 0, list.length - 1);
    }

    @SuppressWarnings("unchecked")
    private static <E extends Comparable<E>> void sort(List<E> list, int startIndex, int endIndex) {
        int size = endIndex - startIndex + 1;

        if (size >= 2) {
            int middle = (startIndex + endIndex) / 2 - size % 2;

            sort(list, startIndex, middle);
            sort(list, middle + 1, endIndex);

            Object[] copy = new Object[size];

            for (int i = 0; i < copy.length; ++i)
                copy[i] = list.get(startIndex + i);

            int leftCursor = 0, rightCursor = size / 2;

            while (startIndex <= endIndex) {
                boolean fromLeft = leftCursor < size / 2 && (rightCursor >= size || ((E) copy[leftCursor]).compareTo((E) copy[rightCursor]) < 0);

                list.set(startIndex++, (E) (fromLeft ? copy[leftCursor++] : copy[rightCursor++]));
            }
        }
    }


    private static void sort(int[] array, int[] copy, int low, int high) {
        int size = high - low + 1;

        if (size >= 2) {
            int middle = (low + high) / 2 - size % 2;

            sort(array, copy, low, middle);
            sort(array, copy, middle + 1, high);

            System.arraycopy(array, low, copy, 0, size);

            int left = 0;
            int right = size / 2;

            while (low <= high) {
                boolean fromLeft = left < size / 2 && (right >= size || copy[left] < copy[right]);

                array[low++] = fromLeft ? copy[left++] : copy[right++];
            }
        }
    }
}
