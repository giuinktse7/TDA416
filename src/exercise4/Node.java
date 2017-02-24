package exercise4;

public class Node<E> {

    public E element;
    protected Node<E> left, right, parent;

    public Node(E element, Node<E> left, Node<E> right, Node<E> parent) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public Node(E element, Node<E> parent) {
        this(element, null, null, parent);
    }
    public Node(E element ) {
        this(element, null, null, null);
    }

    public String toString() {
        return element.toString();
    }
}