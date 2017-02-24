package collection;

import java.util.stream.IntStream;

public class AvlTree<E extends Comparable<E>> {

    /** The tree root. */
    private AvlNode<E> root;

    private E removedData;

    public AvlTree() {
        root = null;
    }

    public void insert(E item) {
        root = insert(item, root);
    }

    public E remove(E item) {
        if (item == null)
            throw new IllegalArgumentException("item = null is not allowed.");

        root = remove(item, root);
        return removedData;
    }

    public int size() {
        return size(root);
    }

    private int size(AvlNode<E> node) {
        if (node == null)
            return 0;

        return 1 + size(node.left) + size(node.right);
    }

    private AvlNode<E> remove(E data, AvlNode<E> currentNode) {
        if (currentNode == null) {
            removedData = null;
            return null;
        }

        int diff = data.compareTo(currentNode.data);

        if (diff < 0) currentNode.left = remove(data, currentNode.left);
        else if (diff > 0) currentNode.right = remove(data, currentNode.right);
        else currentNode = deleteNode(currentNode);

        currentNode = balance(data, currentNode);

        if (currentNode != null) currentNode.height = computeHeight(currentNode);

        return currentNode;
    }

    private AvlNode<E> deleteNode(AvlNode<E> node) {
        int childCount = node.immediateChildrenCount();
        removedData = node.data;

        if (childCount == 0) return null;

        else if (childCount == 1) return node.left == null ? node.right : node.left;

        else {
            AvlNode<E> temp = removeMax(node.left);
            if (!temp.data.equals(node.left.data))
                temp.left = node.left;

            temp.right = node.right;
            return temp;
        }
    }

    private AvlNode<E> removeMax(AvlNode<E> currentNode) {
        if (currentNode.right == null)
            return currentNode;

        if (currentNode.right.right == null) {
            AvlNode<E> temp = currentNode.right;
            currentNode.right = null;
            return temp;
        }

        return removeMax(currentNode.right);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public E find(E target) {
        if (target == null)
            throw new IllegalArgumentException("target may not be null.");

        return find(target, root);
    }

    private E find(E target, AvlNode<E> currentNode) {
        if (currentNode == null)
            return null;

        int diff = target.compareTo(currentNode.data);

        if (diff == 0)
            return currentNode.data;
        else if (diff < 0)
            return find(target, currentNode.left);
        else
            return find(target, currentNode.right);
    }

    private AvlNode<E> insert(E data, AvlNode<E> node) {
        if (node == null)
            node = new AvlNode<>(data);
        else if (data.compareTo(node.data) < 0)
            node.left = insert(data, node.left);
        else if (data.compareTo(node.data) > 0)
            node.right = insert(data, node.right);

        node = balance(data, node);
        node.height = computeHeight(node);
        return node;
    }

    private AvlNode<E> balance(E data, AvlNode<E> node) {
        if (node == null)
            return null;

        int heightDifference = height(node.left) - height(node.right);

        if (Math.abs(heightDifference) == 2) {
            //Need to rotate with left child
            if (heightDifference > 0)
                return data.compareTo(node.left.data) < 0 ? rotateWithLeftChild(node) : doubleWithLeftChild(node);
            else
                return data.compareTo(node.right.data) > 0 ? rotateWithRightChild(node) : doubleWithRightChild(node);
        }

        return node;
    }

    //Right child to top, top to left
    private AvlNode<E> rotateWithRightChild(AvlNode<E> root) {
        AvlNode<E> result = root.right;
        root.right = result.left;
        result.left = root;

        root.height = computeHeight(root);
        result.height = computeHeight(result);
        return result;
    }

    private AvlNode<E> rotateWithLeftChild(AvlNode<E> root) {
        AvlNode<E> result = root.left;
        root.left = result.right;
        result.right = root;

        root.height = computeHeight(root);
        result.height = computeHeight(result);
        return result;
    }

    private AvlNode<E> doubleWithLeftChild(AvlNode<E> node) {
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);
    }

    private AvlNode<E> doubleWithRightChild(AvlNode<E> node) {
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }

    private int computeHeight(AvlNode<E> node) {
        return max( height(node.left), height(node.right) ) + 1;
    }

    private static int max(int a, int b) {
        return a > b ? a : b;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        preOrderTraverse(root, 1, builder);
        return builder.toString();
    }

    private void preOrderTraverse(AvlNode<E> node, int depth, StringBuilder builder) {
        IntStream.range(1, depth++).forEach(i -> builder.append(".   "));

        if (node == null)
            builder.append("?\n");
        else {
            builder.append(node.toString());
            builder.append('\n');
            preOrderTraverse(node.left, depth, builder);
            preOrderTraverse(node.right, depth, builder);
        }
    }

    private int height(AvlNode<E> node) {
        return node == null ? -1 : node.height;
    }

    private static class AvlNode<E> {

        private E data;
        private AvlNode<E> left, right;
        int height;

        AvlNode (E data) {
            this(data, null, null);
        }

        AvlNode(E data, AvlNode<E> left, AvlNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.height = 0;
        }

        public String toString() { return data.toString(); }

        public int immediateChildrenCount() {
            int count = 0;
            if (left != null) ++count;
            if (right != null) ++count;

            return count;
        }
    }
}