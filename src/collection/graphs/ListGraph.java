package collection.graphs;

import java.util.*;
import java.util.stream.IntStream;

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

    @SuppressWarnings("all")
    public Iterator<Integer> DijkstraMaxWeight(int startNode, double totalWeight) {
        double[] distance = new double[edges.length];
        IntStream.range(0, distance.length).forEach(i -> distance[i] = Double.POSITIVE_INFINITY);
        distance[startNode] = 0;

        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>((a, b) -> (int) Math.signum(a.getWeight() - b.getWeight()));

        int visitedCount = 1;

        edges[startNode].forEach(edgeQueue::add);
        while (!edgeQueue.isEmpty() && visitedCount++ != edges.length) {
            Edge edge = edgeQueue.poll();

            if (distance[edge.getDest()] == Double.POSITIVE_INFINITY)
                edges[edge.getDest()].forEach(edgeQueue::add);

            double newDistance = distance[edge.getSource()] + edge.getWeight();

            if (newDistance < distance[edge.getDest()])
                if (newDistance > totalWeight)
                    newDistance = -1;

            distance[edge.getDest()] = newDistance;
        }

        return IntStream
                .range(0, edges.length)
                .map(i -> distance[i] == -1 || distance[i] == Double.POSITIVE_INFINITY ? -1 : i)
                .filter(i -> i != -1)
                .iterator();
    }

    @SuppressWarnings("all")
    public ListIterator<Edge>[] dijkstra(int fromNode) {
        int size = edges.length;
        int[] prev = new int[size];
        double[] cost = new double[size];
        boolean[] visited = new boolean[size];
        PriorityQueue<QueueElement> queue = new PriorityQueue<>((a, b) -> (int) Math.signum(a.cost - b.cost));

        Arrays.fill(prev, -1);
        Arrays.fill(cost, Double.POSITIVE_INFINITY);

        cost[fromNode] = 0;
        queue.add(new QueueElement(fromNode, cost[fromNode]));

        while (!queue.isEmpty()) {
            final int from = queue.poll().id;
            if (visited[from])
                continue;

            visited[from] = true;

            edges[from].forEach(edge -> {
                int to = edge.getDest();
                double newWeight = cost[from] + edge.getWeight();
                if (newWeight < cost[to]) {
                    prev[to] = from;
                    cost[to] = newWeight;
                    queue.add(new QueueElement(to, newWeight));
                }
            });
        }

        ListIterator<Edge>[] result = new ListIterator[size];

        IntStream.range(0, size).forEach(i -> {
            LinkedList<Edge> edges = new LinkedList<>();
            int pos = i;
            while (prev[pos] != -1) {
                edges.add(new Edge(prev[pos], pos));
                pos = prev[pos];
            }

            result[i] = edges.listIterator(edges.size());
        });

        return result;
    }

    private class QueueElement {
        private int id;
        private double cost;

        public QueueElement(int id, double cost) {
            this.id = id;
            this.cost = cost;
        }
    }
}
