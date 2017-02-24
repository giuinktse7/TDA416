package exercise4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final char RIGHT_NULL = 'R';
    private static final char LEFT_NULL = 'L';

    public static void main(String[] args) {
        CompleteBinaryTree<Integer> tree = new CompleteBinaryTree<>();
        BinarySearchTree<Integer> binaryTree = new BinarySearchTree<>();
        ArrayList<Integer> items = new ArrayList<>();
        Integer[] k = new Integer[45];

        Random random = new Random();
        for (int i = 0; i < k.length; ++i)
            k[i] = Math.abs(random.nextInt(65));

        items.addAll(Arrays.asList(k));

        items.forEach(tree::addItem);
        items.forEach(binaryTree::add);

        System.out.println(getAllDepths(binaryTree.getRoot(), 0));
    }

    private static boolean isComplete(Tree tree) {
        Node root = tree.getRoot();

        String depths = getAllDepths(root, 0);
        int rightNullIndex = depths.indexOf(RIGHT_NULL);
        int leftNullIndex = depths.indexOf(LEFT_NULL);

        if (leftNullIndex != -1) return false; //A right is not allowed to be non-null without left being non-null
        if (rightNullIndex == -1) return true; //Full tree

        int depthToFill = (int) depths.charAt(rightNullIndex - 1) - 1;
        depths = depths.substring(rightNullIndex + 1);
        boolean foundFirstValue = false;
        for (Character c : depths.toCharArray()) {
            if (foundFirstValue && (c == RIGHT_NULL || (int) c > depthToFill)) return false;
            if (c != RIGHT_NULL)
                foundFirstValue = true;
        }

        return true;
    }

    private static String getAllDepths(Node node, int depth) {
        ++depth;

        if (node == null) return "";
        if (node.left == null && node.right == null) return Integer.toString(depth);
        if (node.left == null) return Character.toString(LEFT_NULL);
        if (node.right == null) return getAllDepths(node.left, depth) + RIGHT_NULL;

        return getAllDepths(node.left, depth) + getAllDepths(node.right, depth);
    }
}
