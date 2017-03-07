package collection.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class MatrixGraph extends AbstractGraph {
    double[][] edges;

    public MatrixGraph(int vertexCount, boolean directed) {
        super(vertexCount, directed);
        edges = new double[vertexCount][vertexCount];

        for (int x = 0; x < vertexCount; ++x)
            for (int y = 0; y < vertexCount; ++y)
                edges[x][y] = Double.POSITIVE_INFINITY;
    }

    public static MatrixGraph fromFile(String path, boolean directed) throws FileNotFoundException {
        try {
            Scanner scanner = new Scanner(new File(path));
            int vertices = scanner.nextInt();
            MatrixGraph graph = new MatrixGraph(vertices, directed);
            graph.loadEdgesFromFile(scanner);
            return graph;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new FileNotFoundException("We could not nodeAt a file with path: " + path);
    }

    @Override
    public void insert(Edge edge) {
        edges[edge.getSource()][edge.getDest()] = edge.getWeight();

        if (!isDirected())
            edges[edge.getDest()][edge.getSource()] = edge.getWeight();
    }

    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source][dest] != Double.POSITIVE_INFINITY;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        return new Edge(source, dest, edges[source][dest]);
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return null;
    }

}
