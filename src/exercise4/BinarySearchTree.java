package exercise4;

import lab2.LinkedStack;
import lab2.Stacks;

import java.util.*;
import java.util.stream.IntStream;

/**
 *  A simple binary searchtree ordered by the
 *  method compareTo for the elements.
 * @author (Bror Bjerner)
 * @version (2010)
 * @author EH
 * @version (2017)
// old: findRefToMostRight, removeLeaf, removeThis
// new: removeThis, liftRightSubtree, swapWithRightMostInLeftTree
 */

public class BinarySearchTree<E extends Comparable<? super E>>
        extends AbstractCollection<E>
        implements Iterable<E>, Tree<E> {

    protected Node<E> root;
    protected int size;

    /**
     * The constructor creates the empty tree.
     */
    public BinarySearchTree() {
        super();
        root = null;
        size = 0;
    }

    /**
     * The number of objects in this collection.
     *
     * @return the number of elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Create an iterator for elements in the tree in inorder.
     *
     * @return the created iterator.
     */
    public Iterator<E> iterator() {
        return new BST_Iterator();
    }

    protected void addIn(E newElem, Node<E> t) {
        if (newElem.compareTo(t.element) < 0)
            if (t.left == null)
                t.left = new Node<>(newElem, t);
            else
                addIn(newElem, t.left);
        else if (t.right == null)
            t.right = new Node<>(newElem, t);
        else
            addIn(newElem, t.right);
    }

    public boolean add(E elem) {
        if (root == null)
            root = new Node<>(elem, null);
        else
            addIn(elem, root);
        size++;
        return true;
    }

    protected Node find(E elem, Node<E> t) {
        if (t == null)
            return null;
        else {
            int jfr = elem.compareTo(t.element);
            if (jfr < 0)
                return find(elem, t.left);
            else if (jfr > 0)
                return find(elem, t.right);
            else
                return t;
        }
    }

    /**
     * Check if the e is in the the tree.
     *
     * @param elem The e to check
     * @returns true if the e is contained in the tree,
     * otherwise false is returned.
     */
    public boolean contains(E elem) {
        return find(elem, root) != null;
    }

    /**
     * Removes all of the elements from this tree
     */
    public void clear() {
        root = null;
        size = 0;
    }

    protected void removeThis(Node<E> t) {
        if (t.left == null)
            liftRightSubtree(t);
        else
            swapWithRightMostInLeftTree(t);
    }

    protected void liftRightSubtree(Node<E> node) {
        if (node.right != null)
            node.right.parent = node.parent;

        if (node.parent == null)
            root = node.right;
        else if (node.parent.left == node)
            node.parent.left = node.right;
        else
            node.parent.right = node.right;
    }

    protected void swapWithRightMostInLeftTree(Node node) {
        if (node.left.right == null) {
            node.element = node.left.element;
            node.left = node.left.left;
            if (node.left != null)
                node.left.parent = node;
        } else {
            Node temp = node.left;
            while (temp.right.right != null)
                temp = temp.right;
            node.element = temp.right.element;
            temp.right = temp.right.left;
            if (temp.right != null)
                temp.right.parent = temp;
        }
    }

    /**
     * Remove the first occurrence of an e for which
     * compareTo with the argument yields 0. If no e
     * is removed false is returned, otherwise true.
     *
     * @param elem e of Comparable
     * @return true if the tree has changed, otherwise false.
     */
    public boolean remove(E elem) {
        Node remElem = find(elem, root);
        if (remElem == null)
            return false;
        else {
            removeThis(remElem);
            return true;
        }
    }

    @Override
    public Node<E> getRoot() {
        return root;
    }

    protected class BST_Iterator implements Iterator<E> {
        private Stacks<Node<E>> nextOnTop = new LinkedStack<>();
        private Node<E> lastNext = null;

        protected BST_Iterator() {
            for (Node<E> p = root; p != null; p = p.left)
                nextOnTop.push(p);
        } // constructor BST_Iterator

        public boolean hasNext() {
            return !nextOnTop.isEmpty();
        } // hasNext

        public E next() {
            lastNext = nextOnTop.pop();

            for (Node<E> p = lastNext.right; p != null; p = p.left)
                nextOnTop.push(p);
            return lastNext.element;
        } // next

        public void remove() {
            if (lastNext != null) {
                removeThis(lastNext);
                lastNext = null;
            } else
                throw new IllegalStateException();
        }
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
