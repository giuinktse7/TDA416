package lab3;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DirectedGraphTest {

    DirectedGraph<Edge> graph;

    @Before
    public void setup() {
        graph = new DirectedGraph<>(5);
    }

    @Test
    public void shortestPath() throws Exception {
        graph.addEdge(new BusEdge(0, 1, 7, "Track"));
        graph.addEdge(new BusEdge(0, 2, 2, "Track"));
        graph.addEdge(new BusEdge(0, 4, 80, "Track"));
        graph.addEdge(new BusEdge(2, 1, 1, "Track"));
        graph.addEdge(new BusEdge(1, 3, 2, "Track"));
        graph.addEdge(new BusEdge(2, 3, 14, "Track"));
        graph.addEdge(new BusEdge(2, 4, 4, "Track"));
        graph.addEdge(new BusEdge(3, 4, 26, "Track"));

        Iterator<Edge> it = graph.shortestPath(0, 3);
        int sum = 0;
        while (it.hasNext()) {
            sum += it.next().getWeight();
        }

        assertTrue(sum == 5);
    }

}