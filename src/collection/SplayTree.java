package collection;

import java.util.stream.IntStream;

public class SplayTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    @Override
    public boolean add(E e) {
        if (root == null)
            root = new Node<>(e, null);
        else
            add(e, root);

        return true;
    }

    public E getFirst() {
        return root.e;
    }

    private void add(E e, Node<E> node) {
        int diff = e.compareTo(node.e);

        if (diff < 0) {
            if (node.left == null) {
                node.left = new Node<>(e, node);
                splay(node.left);
            }
            else
                add(e, node.left);
        }
        else if (diff == 0) {
            node.e = e;
            get(e);
        }
        else if (diff > 0) {
            if (node.right == null) {
                node.right = new Node<>(e, node);
                splay(node.right);
            }
            else
                add(e, node.right);
        }
    }

    private Node<E> splay(Node<E> node) {
        if (node == null)
            return null;

        if (node.parent == null) {
            setRoot(node);
            return root;
        }

        Node<E> parent = node.parent;
        Node<E> grandparent = parent.parent;

        if (grandparent == null) {
            if (node == parent.left) return splay(zig(node));
            else return splay(zag(node));
        }


        //Connect to grandparent's parent
        if (node.parent.parent.parent != null) {
            if (node.parent.parent == node.parent.parent.parent.left)
                node.parent.parent.parent.left = node;
            else
                node.parent.parent.parent.right = node;
        }

        if (grandparent.left == parent) {
            if (parent.left == node) return splay(zigzig(node));
            else return splay(zigzag(node));
        } else {
            if (parent.right == node) return splay(zagzag(node));
            else return splay(zagzig(node));
        }
    }

    /**
     *
     * @param node the Entry<E> we are splaying up
     */
    private Node<E> zag(Node<E> node) {
        Node<E> temp = node.left;
        root.setRight(null);
        node.parent = null;
        node.setLeft(root);
        node.left.setRight(temp);

        return node;
    }

    private Node<E> zig(Node<E> node) {
        Node<E> temp = node.right;
        root.left = null;
        node.parent = null;
        node.setRight(root);
        node.right.setLeft(temp);

        return node;
    }

    private Node<E> zagzag(Node<E> node) {
        Node<E> temp = node.left;
        Node<E> oldParent = node.parent;
        oldParent.setRight(null);
        node.parent = oldParent.parent.parent;

        node.setLeft(oldParent.parent);
        node.left.right.setRight(temp);

        return node;
    }

    private Node<E> zigzig(Node<E> node) {
        Node<E> temp = node.right;

        Node<E> oldParent = node.parent;
        oldParent.setLeft(null);
        node.parent = oldParent.parent.parent;

        node.setRight(oldParent.parent);
        node.right.left.setLeft(temp);

        return node;
    }

    private Node<E> zigzag(Node<E> node) {
        Node<E> temp1 = node.left;
        Node<E> temp2 = node.right;

        Node<E> oldParent = node.parent;
        node.parent = oldParent.parent.parent;
        oldParent.setRight(null);
        oldParent.parent.setLeft(null);

        node.setRight(oldParent.parent);
        node.setLeft(oldParent);
        node.right.setLeft(temp2);
        node.left.setRight(temp1);

        return node;
    }

    private Node<E> zagzig(Node<E> node) {
        Node<E> temp1 = node.right;
        Node<E> temp2 = node.left;

        Node<E> oldParent = node.parent;
        oldParent.setLeft(null);
        node.parent = null;

        node.parent = oldParent.parent.parent;

        node.setLeft(oldParent.parent);
        node.setRight(oldParent);
        node.right.setLeft(temp1);
        node.left.setRight(temp2);

        return node;
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

    public E get(E e) {
        Node<E> result = find(e, root);

        splay(result);
        return result == null ? null : root.e;
    }

    private Node<E> find(E target, Node<E> currentNode) {
        int diff = target.compareTo(currentNode.e);
        if (diff == 0)
            return currentNode;

        return find(target, diff < 0 ? currentNode.left : currentNode.right);
    }

    private void setRoot(Node<E> node) {
        root = node;
        root.parent = null;
    }
}
