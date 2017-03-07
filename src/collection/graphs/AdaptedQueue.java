package collection.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.IntStream;

public class AdaptedQueue<E> {

    private ArrayList<E> list;
    private Comparator<E> comparator;

    private HashMap<E, Integer> index;

    public AdaptedQueue() {
        list = new ArrayList<>();
    }

    public AdaptedQueue(Comparator<E> comparator) {
        list = new ArrayList<>();
        this.index = new HashMap<>();
        this.comparator = comparator;
    }

    public boolean push(E item) {
        list.add(item);
        index.put(item, list.size() - 1);
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

    public void requestUpdate(E item) {
        requestUpdate(index.get(item));
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
        // Get index of biggest of current, left & right
        int next = IntStream.of(index, leftChild(index), rightChild(index))
                .reduce(index, (result, i) -> i < list.size() && compare(i, result) > 0 ? i : result);

        if (next != index) {
            swap(next, index);
            cascadeDown(next);
        }
    }

    private void swap(int first, int second) {
        E temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);

        index.put(list.get(first), first);
        index.put(list.get(second), second);
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
