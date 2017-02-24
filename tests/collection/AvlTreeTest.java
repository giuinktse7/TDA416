package collection;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class AvlTreeTest {

    private AvlTree<Integer> tree;
    private int[] numbers;

    @Before
    public void setup() {
        tree = new AvlTree<>();
       numbers = new Random().ints(20, 1, 100).toArray();

        Arrays.stream(numbers).forEach(i -> {
            System.out.println("Inserting " + i);
            tree.insert(i);
        });
    }

    @Test
    public void insert() throws Exception {
        System.out.println(tree);
    }

    @Test
    public void remove() throws Exception {
        for (int k = 0; k < 3; ++k) {
            System.out.println("Removing " + numbers[k]);
            assertTrue(tree.remove(numbers[k]) == numbers[k]);
        }
        System.out.println("Size: " + tree.size());
        System.out.println(tree);
    }

    @Test
    public void find() {
        assertTrue(tree.find(4) != null);
    }

}