package collection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {

    BinaryTree<Integer> tree;

    @Before
    public void setup() {
        tree = new BinaryTree<>();
    }

    @Test
    public void toStringTest() throws Exception {
        BinaryTree<Integer> t8 = new BinaryTree<>(8, null, null);
        BinaryTree<Integer> t9 = new BinaryTree<>(9, null, null);
        BinaryTree<Integer> t4 = new BinaryTree<>(4, t8, t9);

        BinaryTree<Integer> t2 = new BinaryTree<>(2, t4, null);
        BinaryTree<Integer> t10 = new BinaryTree<>(10, null, null);
        BinaryTree<Integer> t6 = new BinaryTree<>(6, t10, null);
        BinaryTree<Integer> t7 = new BinaryTree<>(7, null, null);
        BinaryTree<Integer> t3 = new BinaryTree<>(3, t6, t7);
        BinaryTree<Integer> t1 = new BinaryTree<>(1, t2, t3);





        System.out.println(t1);
        System.out.println(t1.numberOfNodes());
    }

}