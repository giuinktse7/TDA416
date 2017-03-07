package collection.graphs;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        ListGraph graph = new ListGraph(7, false);
        String data = "0 1 2\n0 2 5\n1 3 3\n1 3 8\n3 5 32\n2 5 10\n4 5 7\n2 4 13\n4 6 6\n0 6 2\n1 6 7";

        Arrays.stream(data.split("\\n"))
                .map(line -> line.split(" "))
                .map(lineData -> new Edge(asInt(lineData[0]), asInt(lineData[1]), asDouble(lineData[2])))
                .forEach(graph::insert);

        ListIterator<Edge>[] result = graph.dijkstra(0);

        forEach(result, (index, iterator) -> {
            System.out.printf("Node: %d\n\t", index);
            while (iterator.hasPrevious()) {
                Edge edge = iterator.previous();
                if (iterator.hasPrevious())
                    System.out.printf("%d -> ", edge.getSource());
                else
                    System.out.printf("%d -> %d\n", edge.getSource(), edge.getDest());
            }
        });
    }

    private static <E> void forEach(E[] array, IndexFunction<E> function) {
        IntStream.range(0, array.length).forEach(i -> function.apply(i, array[i]));
    }

    private static int asInt(String s) {
        return Integer.parseInt(s);
    }

    private static double asDouble(String s) {
        return Double.parseDouble(s);
    }

    public interface IndexFunction<E> {
        void apply(int i, E e);
    }

}
