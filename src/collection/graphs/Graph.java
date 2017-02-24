package collection.graphs;

import java.util.Iterator;

public interface Graph {

    int getVertexCount();

    boolean isDirected();

    void insert(Edge edge);

    boolean isEdge(int source, int dest);

    Edge getEdge(int source, int dest);

    Iterator<Edge> edgeIterator(int source);
}
