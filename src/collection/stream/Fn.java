package collection.stream;

import java.util.stream.IntStream;

public class Fn {

    public static <E> void forEach(E[] array, IndexFunction<E> function) {
        IntStream.range(0, array.length).forEach(i -> function.apply(i, array[i]));
    }

    public static void forEach(int[] array, IndexFunction<Integer> function) {
        IntStream.range(0, array.length).forEach(i -> function.apply(i, array[i]));
    }

    public interface IndexFunction<E> {
        void apply(int i, E e);
    }
}
