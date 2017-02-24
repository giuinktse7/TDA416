package collection.graphs;

public class Main {

    public static void main(String[] args) {
        MatrixGraph graph = (MatrixGraph) AbstractGraph.createGraph("src/GraphTest.txt", false, AbstractGraph.Type.MATRIX);
        graph.allEdgesIterator().forEachRemaining(System.out::println);
    }
}
