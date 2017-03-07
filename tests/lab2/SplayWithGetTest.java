package lab2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class SplayWithGetTest {

    SplayWithGet<Integer> tree = new SplayWithGet<>();

    @Before
    public void setup() {
        tree = new SplayWithGet<>();
        tree.enableSplay = true;
    }

    public void randomInts(int amount, int max) {
        new Random().ints(amount, 0, max).forEach(tree::add);
    }

    public void randomInts(int amount, int max, int seed) {
        System.out.println("Seeded numbers: ");
        ArrayList<Integer> ints = new ArrayList<>();
        new Random(seed).ints(amount, 0, max).forEach(i -> {
            System.out.println(i);
            ints.add(i);
        });

        ints.forEach(tree::add);
    }

    @Test
    public void add() throws Exception {

    }

    @Test
    public void splay() throws Exception {
        tree.add(22);
        //randomIntsList(50, 50, 34621);
        randomInts(100, 500);
        tree.get(22);
        assertTrue(tree.getFirst() == 22);
    }

    @Test
    public void zag() {
        tree.enableSplay = false;
        tree.add(2);
        tree.add(1);
        tree.add(4);
        tree.add(3);
        tree.add(5);

        int nodesBefore = nodeCount();

        System.out.println(tree);

        tree.root = tree.zag(tree.root.right);

        System.out.println(tree);
        assertTrue("Each node has the correct node as parent.", correctParents());
        assertTrue("Entry count is the same.", nodesBefore == nodeCount());
    }

    @Test
    public void zig() {
        //Only zig
       /* tree.add(4);
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(5);

        int nodesBefore = nodeCount();

        tree.zig(tree.root.left);

        assertTrue("Each node has the correct node as parent.", correctParents());
        assertTrue("Entry count is the same.", nodesBefore == nodeCount());*/

        setup();

        tree.add(5);
        System.out.println(tree);
        tree.add(2);
        System.out.println(tree);
        tree.add(1);
        System.out.println(tree);
        tree.add(0);

        System.out.println("Result: \n" + tree);

        assertTrue("Each node has the correct node as parent.", correctParents());

        tree.enableSplay = false;
    }

    @Test
    public void zagzag() throws Exception {
        tree.enableSplay = false;
        tree.add(8);
        tree.add(7);
        tree.add(12);
        tree.add(9);
        tree.add(16);
        tree.add(14);
        tree.add(17);

        System.out.println(tree);

        int nodesBefore = nodeCount();

        tree.root = tree.zagzag(tree.root.right.right);

        System.out.println(tree);

        assertTrue("Each node has the correct node as parent.", correctParents());
        int nodesAfter = nodeCount();
        assertTrue(String.format("Entry count is not the same: %d != %d", nodesBefore, nodesAfter), nodesBefore == nodesAfter);
    }

    @Test
    public void zigzig() throws Exception {
        tree.enableSplay = false;
        tree.add(8);
        tree.add(6);
        tree.add(4);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(9);

        System.out.println(tree);

        int nodesBefore = nodeCount();

        tree.root = tree.zigzig(tree.root.left.left);

        System.out.println(tree);

        assertTrue("Each node has the correct node as parent.", correctParents());
        int nodesAfter = nodeCount();
        assertTrue(String.format("Entry count is not the same: %d != %d", nodesBefore, nodesAfter), nodesBefore == nodesAfter);
    }

    @Test
    public void zigzag() throws Exception {
        tree.add(8);
        tree.add(2);
        tree.add(1);
        tree.add(5);
        tree.add(4);
        tree.add(6);
        tree.add(9);

        System.out.println(tree);

        int nodesBefore = nodeCount();

        //tree.root = tree.zigzag(tree.root.left.right);

        //System.out.println(tree);

        assertTrue("Each node has the correct node as parent.", correctParents());
        int nodesAfter = nodeCount();
        assertTrue(String.format("Entry count is not the same: %d != %d", nodesBefore, nodesAfter), nodesBefore == nodesAfter);
    }

    @Test
    public void zagzig() throws Exception {
        tree.enableSplay = false;
        tree.add(1);
        tree.add(0);
        tree.add(6);
        tree.add(4);
        tree.add(3);
        tree.add(5);
        tree.add(7);

        System.out.println(tree);

        int nodesBefore = nodeCount();

        tree.root = tree.zagzig(tree.root.right.left);

        System.out.println(tree);

        assertTrue("Each node has the correct node as parent.", correctParents());
        int nodesAfter = nodeCount();
        assertTrue(String.format("Entry count is not the same: %d != %d", nodesBefore, nodesAfter), nodesBefore == nodesAfter);
    }

    @Test
    public void get() throws Exception {

    }

    private boolean correctParents() {
        return correctParents(tree.root);
    }

    private boolean correctParents(BinarySearchTree.Entry currentNode) {
        if (currentNode == null)
            return true;

        boolean leftOK = currentNode.left == null || currentNode.left.parent == currentNode;
        boolean rightOK = currentNode.right == null || currentNode.right.parent == currentNode;
        return leftOK && rightOK && correctParents(currentNode.left) && correctParents(currentNode.right);
    }

    private int nodeCount() {
        return nodeCount(tree.root);
    }

    private int nodeCount(BinarySearchTree.Entry currentNode) {
        if (currentNode == null)
            return 0;

        return 1 + nodeCount(currentNode.left) + nodeCount(currentNode.right);
    }

}