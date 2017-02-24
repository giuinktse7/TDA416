package collection;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {

    private Node<E> head, tail;
    private int size;

    private int modCount = 0;

    public CircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public CircularLinkedList(E head) {
        size = 0;
        add(head);
    }

    public CircularLinkedList(Collection<E> collection) {
        this();
        addAll(collection);
    }

    public void addAll(Collection<E> collection) {
        collection.forEach(this::add);
    }

    public void addAll(E[] array) {
        for (E e : array)
            add(e);
    }

    public void add(E e) {
        Node<E> node = new Node<>(e);

        if (size == 0) {
            head = node;
            tail = node;
            node.next = node;
        } else {
            node.next = head;
            tail.next = node;
            tail = node;
        }

        ++size;
    }

    public int size() {
        return this.size;
    }

    private E removeNode(Node<E> current, E target, int count) {
        if (count == size || size == 0)
            return null;

        Node<E> nextNode = current.next;

        boolean foundTarget = target == null ? nextNode.data == null : target.equals(nextNode.data);
        return foundTarget ? removeNext(current) : removeNode(nextNode, target, ++count);
    }

    public E removeAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " not in range.");

        Node<E> current = tail;
        for (int i = 0; i < index; ++i)
            current = current.next;

        return removeNext(current);
    }

    private E removeNext(Node<E> node) {
        Node<E> next = node.next;
        E removed = next.data;

        node.next = next.next;
        next.next = null;

        //Handle case where head is removed
        if (next.equals(head))
            head = size == 1 ? null : node.next;

        //Handle case where tail is removed
        if (next.equals(tail))
            tail = size == 1 ? null : node;

        --size;
        ++modCount;
        return removed;
    }

    public E remove(E e) {
        return removeNode(head, e, 0);
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {

        private Node<E> nextNode;
        private Node<E> previousNode;

        private int currentIndex;

        //Amount of times the collection has been modified by this iterator
        private int modCount;

        public ListIterator() {
            nextNode = head;
            previousNode = null;
            currentIndex = -1;
            modCount = 0;
        }

        @Override
        public boolean hasNext() {
            return nextNode.next != null;
        }

        @Override
        public E next() throws ConcurrentModificationException {
            if (CircularLinkedList.this.modCount != this.modCount)
                throw new ConcurrentModificationException("Collection has been modified outside of this iterator.");

            if (!hasNext())
                throw new IllegalStateException("No remaining elements in the iterator.");

            previousNode = nextNode;
            nextNode = nextNode.next;

            if (previousNode.equals(head))
                currentIndex = 0;
            else
                ++currentIndex;

            return previousNode.data;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (previousNode == null)
                throw new IllegalStateException("Each call to remove() must be preceded by a call to next().");

            removeAt(currentIndex--);
            previousNode = null;
            ++modCount;
        }
    }

    private static class Node<E> {
        private Node<E> next;
        private E data;

        private Node(E data) {
            this(data, null);
        }

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
}
