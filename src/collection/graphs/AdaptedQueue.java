package collection.graphs;

import java.util.ArrayList;
import java.util.Comparator;

public class AdaptedQueue<E> {

    private ArrayList<E> list;
    private Comparator<E> comparator;

    public AdaptedQueue() {
        list = new ArrayList<>();
    }

    public AdaptedQueue(Comparator<E> comparator) {
        list = new ArrayList<>();
        this.comparator = comparator;
    }

    public boolean push(E item) {
        list.add(item);
        requestUpdate(list.size() - 1);

        return true;
    }

    public E poll() {
        if (list.isEmpty())
            return null;

        if (list.size() == 1)
            return list.remove(0);

        E result = list.get(0);
        list.set(0, list.remove(list.size() - 1));
        requestUpdate(0);

        return result;
    }

    public E peek() {
        return list.isEmpty() ? null : list.get(0);
    }

    public E get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int indexOf(E target) {
        return find(0, target);
    }

    public int find(int currentIndex, E target) {
        if (currentIndex >= list.size() || compare(list.get(currentIndex), target) > 0)
            return 0;

        if (list.get(currentIndex).equals(target))
            return currentIndex;

        return find(leftChild(currentIndex), target) + find(rightChild(currentIndex), target);
    }

    public void requestUpdate(int index) {
        if (index < 0 || index > list.size() - 1)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");


        if (index == 0 || compare(parent(index), index) < 0)
            cascadeDown(index);
        else
            cascadeUp(index);
    }

    private void cascadeUp(int index) {
        int parent = parent(index);
        if (parent >= 0 && compare(parent, index) > 0) { //Top bigger
            swap(parent, index);
            cascadeUp(parent);
        }
    }

    /** Swap downwards beginning from index */
    private void cascadeDown(int index) {
        int smallestChild = smallestChild(index);
        if (smallestChild != -1 && compare(index, smallestChild) > 0) {
            swap(index, smallestChild);
            cascadeDown(smallestChild);
        }
    }

    private void swap(int first, int second) {
        E temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    private int smallestChild(int index) {
        //There are no children
        if (index * 2 + 1 > list.size() - 1)
            return -1;

        //Only the left child is present
        if (index * 2 + 2 > list.size() - 1)
            return leftChild(index);

        boolean leftSmaller = compare(leftChild(index), rightChild(index)) < 0;
        return leftSmaller ? rightChild(index) : leftChild(index);
    }

    private int compare(int firstIndex, int secondIndex) {
        return compare(list.get(firstIndex), list.get(secondIndex));
    }

    private int compare(E a, E b) {
        if (comparator == null)
            return asComparable(a).compareTo(b);

        return comparator.compare(a, b);
    }

    @SuppressWarnings("unchecked")
    private Comparable<E> asComparable(E e) {
        return (Comparable<E>) e;
    }

    /* Left child is always odd (2n + 1 where n is parent)
    * Right child always even (2n + 2) */
    private int parent(int index) {
        return (index % 2 == 0 ? (index - 2) : index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return leftChild(index) + 1;
    }
}
