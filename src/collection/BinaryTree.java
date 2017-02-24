package collection;

import java.util.stream.IntStream;

public class BinaryTree<E> {
    protected Node<E> root;

    public BinaryTree() { root = null; }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data, null);
        root.left = leftTree == null ? null : leftTree.root;
        root.right = rightTree == null ? null : rightTree.root;
    }

    public BinaryTree<E> getLeftSubTree() {
        return root != null && root.left != null ? new BinaryTree<>(root.left) : null;
    }

    public BinaryTree<E> getRightSubTree() {
        return root != null && root.right != null ? new BinaryTree<>(root.right) : null;
    }

    public boolean isLeaf() {
        return root != null && root.left == null && root.right == null;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        preOrderTraverse(root, 1, builder);
        return builder.toString();
    }

    private void preOrderTraverse(Node<E> node, int depth, StringBuilder builder) {
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

    public int numberOfNodes() {
        return numberOfNodes(root);
    }

    private int numberOfNodes(Node<E> node) {
        return node == null ? 0 : 1 + numberOfNodes(node.left) + numberOfNodes(node.right);
    }

    public class Node<E> {

        public E e;
        protected Node<E> left, right, parent;

        public Node(E e, Node<E> left, Node<E> right, Node<E> parent) {
            this.e = e;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(E e, Node<E> parent) {
            this(e, null, null, parent);
        }

        private boolean debug = false;

        public String toString() {
            return debug ? String.format("[%s, p: %s] ", this.e, parent) : e.toString();
        }



        public void setLeft(Node<E> left) {
            this.left = left;
            if (left != null)
                left.parent = this;
        }

        public void setRight(Node<E> right) {
            this.right = right;
            if (right != null)
                right.parent = this;
        }

        public boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        public boolean isRightChild() {
            return parent != null && parent.right == this;
        }
    }
}
