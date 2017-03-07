package collection.sorting;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HeapSort {
    public static void main(String[] args) {
        int[] array = SorterUtils.randomInts(10, 0, 100);

        SorterUtils.printArray(array);

        heapsort(array);

        System.out.println();
        System.out.println();
        SorterUtils.printArray(array);
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private static void heapify(int[] array, int parent, int size) {
        int next = IntStream.of(parent * 2 + 1, parent * 2 + 2)
                .reduce(parent, (result, index) -> index <= size && array[index] > array[result] ? index : result);

        if (next != parent) {
            swap(array, next, parent);
            heapify(array, next, size);
        }
    }

    private static void heapsort(int[] array) {
        int end = array.length - 1;
        descendingInts(end / 2).forEach(i -> heapify(array, i, end));

        descendingInts(end).forEach(i -> {
            swap(array, 0, i);
            heapify(array, 0, i - 1);
        });
    }

    private static Stream<Integer> descendingInts(int startInclusive) {
        int[] v = { startInclusive };
        return Stream.generate(() -> v[0]--).limit(startInclusive + 1);
    }
}
