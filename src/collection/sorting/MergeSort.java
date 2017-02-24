package collection.sorting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class MergeSort {
    public static void main(String[] args) {
        int amount = 50000000;
        int[] myInts = new int[amount];
        int[] theirInts = new int[amount];
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        //random.ints(amount, 0, 100).forEach(list::add);
        for (int i = 0; i < amount; ++i) {
            myInts[i] = random.nextInt(10000);
            theirInts[i] = myInts[i];
        }


        //list.forEach(System.out::println);

        System.out.println("Mine: ");
        long time = new Date().getTime();
        sort(myInts);
        System.out.println(new Date().getTime() - time);

        System.out.println("Theirs: ");
        long time2 = new Date().getTime();
        mergeSort(theirInts);
        System.out.println(new Date().getTime() - time2);
    }

    public static <E extends Comparable<E>> void sort(List<E> list) {
        sort(list, 0, list.size() - 1);
    }

    public static <E extends Comparable<E>> void sort(int[] list) {
        sort(list, 0, list.length - 1);
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

    @SuppressWarnings("unchecked")
    private static void sort(int[] list, int startIndex, int endIndex) {
        int size = endIndex - startIndex + 1;

        if (size >= 2) {
            int middle = (startIndex + endIndex) / 2 - size % 2;

            sort(list, startIndex, middle);
            sort(list, middle + 1, endIndex);

            int[] copy = new int[size];

            for (int i = 0; i < copy.length; ++i)
                copy[i] = list[startIndex + i];

            int leftCursor = 0, rightCursor = size / 2;

            while (startIndex <= endIndex) {
                boolean fromLeft = leftCursor < size / 2 && (rightCursor >= size || copy[leftCursor] < copy[rightCursor]);

                list[startIndex++] = (fromLeft ? copy[leftCursor++] : copy[rightCursor++]);
            }
        }
    }

    private static int[] mergeSort(int[] f) {
        boolean uddaAntal = f.length % 2 != 0;
        int lengthf1 = f.length / 2;
        int lengthf2 = lengthf1 + (uddaAntal ? 1 : 0);
        if (f.length >= 2) {
            int[] f1 = new int[lengthf1];
            int[] f2 = new int[lengthf2];
            for (int i = 0; i < lengthf1; i++) {
                f1[i] = f[i];
                f2[i] = f[lengthf1+i];
            }

            if (uddaAntal)
                f2[f2.length-1] = f[f.length-1];

            return merge(mergeSort(f1), mergeSort(f2));
        }

        return f;
    }

    public static int[] merge(int[] f1, int[] f2) {
        int f1high = f1.length - 1;
        int f2high = f2.length - 1;
        int[] res = new int[f1.length + f2.length];
        int resIndex = 0;
        int f1index = 0;
        int f2index = 0;
        boolean f1slut = f1index > f1.length;
        boolean f2slut = f2index > f2.length;

        while (!f1slut && !f2slut) {
            if (f1[f1index] < f2[f2index]) {
                res[resIndex] = f1[f1index];
                f1index = f1index + 1;
                f1slut = f1index > f1high;
            } else {
                res[resIndex] = f2[f2index];
                f2index = f2index + 1;
                f2slut = f2index > f2high;
            }

            resIndex = resIndex + 1;
        }

        if (f1slut) {
            for (; resIndex < res.length; ++resIndex) {
                res[resIndex] = f2[f2index];
                f2index++;
            }
        } else {
            for (; resIndex < res.length; ++resIndex) {
                res[resIndex] = f1[f1index];
                f1index++;
            }
        }

        return res;
    }
}
