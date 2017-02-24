package exercise4;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class CompleteBinaryTree<E> implements Tree<E> {

    private Node<E> root;

    public CompleteBinaryTree(List<E> items) {
        for (E item : items) {
            addItem(item);
        }
    }

    public CompleteBinaryTree() {
        root = null;
    }

    /**
     * Adds item, such the tree is a complete binary tree.
     *
     * @param item the item to be added.
     */
    public void addItem(E item) {

        if (root == null) {
            root = new Node<>(item);
            return;
        }

        final Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            final Node currNode = queue.poll();

            if (currNode.left == null) {
                currNode.left = new Node<>(item);
                return;
            }

            if (currNode.right == null) {
                currNode.right = new Node<>(item);
                return;
            }

            queue.add(currNode.left);
            queue.add(currNode.right);
        }
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder builder) {
        IntStream.range(1, depth++).forEach(i -> builder.append(".   "));

        if (node == null)
            builder.append("?\n");
        else {
            System.out.println("Adding: " + node.toString());
            builder.append(node.toString());
            builder.append('\n');
            preOrderTraverse(node.left, depth, builder);
            preOrderTraverse(node.right, depth, builder);
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        preOrderTraverse(root, 1, builder);
        return builder.toString();
    }
}