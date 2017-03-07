package collection.graphs;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static collection.graphs.AbstractGraph.Type.*;

public abstract class AbstractGraph implements Graph {

    public enum Type { LIST, MATRIX }

    private boolean directed;
    private int vertexCount;

    public AbstractGraph(int vertexCount, boolean directed) {
        this.directed = directed;
        this.vertexCount = vertexCount;
    }

    public int getVertexCount() { return vertexCount; }
    public boolean isDirected() { return directed; }

    /**
     * Format: If line contains single integer, treat as vertexCount.
     * Two integers: source, dest
     * Three integers: source, dest, weight
     * @param scanner Scanner of lines from the file.
     */
    public void loadEdgesFromFile(Scanner scanner) {

        while(scanner.hasNextLine()) {
            String[] values = scanner.nextLine().trim().split("\\s+");

            if (values.length >= 2) {
                int source = Integer.parseInt(values[0]);
                int dest = Integer.parseInt(values[1]);
                double weight = 1;
                if (values.length == 3)
                    weight = Double.parseDouble(values[2]);

                insert(new Edge(source, dest, weight));
            }
        }
    }

    public static Graph createGraph(String filePath, boolean isDirected, Type type) {
        try {
            return type == MATRIX ? MatrixGraph.fromFile(filePath, isDirected) : null;
        } catch (FileNotFoundException e) { e.printStackTrace(); }

        return null;
    }
}
