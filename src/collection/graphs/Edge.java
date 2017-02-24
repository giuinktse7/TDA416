package collection.graphs;

public class Edge {
    private int dest, source;
    private double weight;

    private static final int DEFAULT_WEIGHT = 1;

    public Edge(int source, int dest) {
        this(source, dest, DEFAULT_WEIGHT);
    }

    public Edge(int source, int dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass())
            return false;

        Edge that = (Edge) other;
        return this.source == that.source && this.dest == that.dest && this.weight == that.weight;
    }

    public int getDest() {
        return dest;
    }

    public int getSource() {
        return source;
    }

    public double getWeight() { return weight; }
    public int hashCode() {
        return dest * 23 + source * 41;
    }

    public String toString() {
        return String.format("Graph.Edge {from: %d, to: %d, weight: %f}", source, dest, weight);
    }
}
