package collection.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
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
        return new GraphIterator();
    }

    public Iterator<Edge> allEdgesIterator() {
        return new GraphIterator();
    }


    private class GraphIterator implements Iterator<Edge> {
        private int[] visits;
        private int vertexCount;
        LinkedList<Integer> nextList;

        private int source, dest;

        public GraphIterator() {
            vertexCount = edges.length;
            visits = new int[vertexCount];
            source = 0;
            dest = 0;
            nextList = new LinkedList<>();
        }

        @Override
        public boolean hasNext() {
            return source != -1;
        }

        public Edge next() {
            if (!hasNext())
                throw new NoSuchElementException("There is no next e in the iterator.");

            while (visits[dest] == 1 || !isEdge(source, dest))
                if (++dest == vertexCount) updateSource();

            Edge result = new Edge(source, dest, edges[source][dest]);

            if (!nextList.contains(dest))
                nextList.add(dest);

            //Required in order for hasNext() to work properly. Otherwise it will not correctly
            // call out whether there is a next e.
            if (++dest == vertexCount)
                updateSource();

            return result;
        }

        private void updateSource() {
            dest = 0;
            visits[source] = 1;
            //We can't walk further! (No more edges to traverse in our immediate vicinity)
            // Quick, escape to the first node that hasn't been visited!
            if (nextList.isEmpty())
                for (source = 0; visits[source] == 1 && source < vertexCount; ++source) ;
            else //There are more vertices to visit. Phew! Keep on sauntering~
                source = nextList.removeFirst();

            //There are no remaining nodes. Indicated by source = -1. Maybe remove -1?
            if (source == vertexCount - 1)
                source = -1;
        }
    }
}
