package collection.graphs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListGraph extends AbstractGraph {
    private List<Edge>[] edges;

    public ListGraph(int vertexCount, boolean directed) {
        super(vertexCount, directed);
        edges = new List[vertexCount];
        for (int i = 0; i < edges.length; ++i)
            edges[i] = new LinkedList<>();
    }

    @Override
    public void insert(Edge edge) {
        edges[edge.getSource()].add(edge);
        if (!isDirected())
            edges[edge.getDest()].add(new Edge(
                    edge.getDest(),
                    edge.getSource(),
                    edge.getWeight()));
    }

    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source].contains(new Edge(source, dest));
    }

    @Override
    public Edge getEdge(int source, int dest) {
        for (Edge edge : edges[source])
            if (edge.getSource() == source && edge.getDest() == dest)
                return edge;

        return new Edge(source, dest, Double.POSITIVE_INFINITY);
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return edges[source].iterator();
    }
}
