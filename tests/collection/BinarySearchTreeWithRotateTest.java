package collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeWithRotateTest {
    @Test
    public void rotateRight() throws Exception {
        BinarySearchTreeWithRotate<Integer> tree = new BinarySearchTreeWithRotate<>();
        tree.add(20);
        tree.add(17);
        tree.add(24);
        tree.add(14);
        tree.add(18);
        tree.add(23);
        tree.add(30);

        tree.root = tree.rotateLeft(tree.root);
        tree.root = tree.rotateRight(tree.root);

        System.out.println(tree);
    }

}