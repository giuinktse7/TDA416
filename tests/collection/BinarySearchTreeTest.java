package collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    @Test
    public void add() throws Exception {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(2);
        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>(555, tree, null);
        tree.add(1);
        tree.add(5);
        tree.delete(5);
        tree.add(4);
        tree.add(7);
        System.out.println(tree2);
    }

    @Test
    public void find() throws Exception {

    }

    @Test
    public void contains() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void remove() throws Exception {

    }

}