package collection.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class HeapSort {

    private static int endIndex;

    public static void main(String[] args) {
        int[] array = new int[10];
        List<Integer> ints = SorterUtils.randomInts(10, 0, 100, 5371);
        IntStream.range(0, 10).forEach(i -> array[i] = ints.get(i));
        new HeapSort().sort(array);

        Arrays.stream(array).forEach(System.out::println);
    }

    private void sort(int[] array) {
        makeHeap(array);

        for (int i = endIndex; i > 0; --i) {
            swap(array, 0, i);
            --endIndex;
            cascade(array, 0);
        }
    }

    private void makeHeap(int[] array) {
        endIndex = array.length - 1;

        for (int i = endIndex / 2; i >= 0; --i)
            cascade(array, i);
    }

    private void cascade(int[] array, int i) {
        int left = i * 2;
        int right = left + 1;
        int max = i;
        if (left <= endIndex && array[left] > array[i])
            max = left;
        if (right <= endIndex && array[right] > array[max])
            max = right;

        if (max != i) {
            swap(array, i, max);
            cascade(array, max);
        }
    }

    private static void swap(int arr[], int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }



}
