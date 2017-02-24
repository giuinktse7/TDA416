package collection;

import java.util.*;

public class NewArrayList<E> implements List<E> {

    private static final int STARTING_SIZE = 10;

    private Object[] array;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public NewArrayList() {
        array = new Object[STARTING_SIZE];
        size = 0;
        capacity = STARTING_SIZE;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; ++i)
                if (array[i] == null)
                    return true;
        } else {
            for (int i = 0; i < size; ++i)
                if (o.equals(array[i]))
                    return true;
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(array, size);
    }

    /** Creates a bigger array when the elements can no longer fit in the current one. */
    private void allocateSpace() {
        int newCapacity = capacity + (capacity >> 1);

        if (newCapacity > capacity) {
            array = Arrays.copyOf(array, newCapacity);
            capacity = newCapacity;
        }
    }

    @Override
    public boolean add(E e) {
        if (size == capacity)
            allocateSpace();

        array[size++] = e;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1) {
            fastRemove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Set<?> set = new TreeSet<>(c);
        for (Object o : set)
            if (indexOf(o) == -1)
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c)
            add(e);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkRange(index);

        int elementCount = collection.size();
        if (size + elementCount > capacity)
            allocateSpace();

        System.arraycopy(array, index, array, index + elementCount, size - index);

        int insertionIndex = index;
        for (E e : collection)
            array[insertionIndex++] = e;

        size += elementCount;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int oldSize = size;

        for (Object o : c)
            remove(o);

        return oldSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int oldSize = size;
        final Object[] array = this.array;
        int r = 0, w = 0;
        try {
            //Get all matches
            for (; r < size; ++r) {
                if (c.contains(array[r]))
                    array[w++] = array[r];
            }
        } finally {
            //Safeguard for if contains() throws an exception
            if (r != size) {
                System.arraycopy(array, r, array, w, size - r);
                w += size - r;
            }

            if (w != size) {
                //Let gc clean up
                for (int i = w; i < size; ++i)
                    array[i] = null;
                    size = w;
            }
        }

        return size != oldSize;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i)
            array[i] = null;

        size = 0;
    }

    @Override
    public E get(int index) {
        checkRange(index);

        return (E) array[index];
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMessage(index));
    }

    @Override
    public E set(int index, E element) {
        checkRange(index);

        E old = elementData(index);
        array[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; ++i) {
                //Found it?
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                //Found it?
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; --i) {
                //Found it?
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; --i) {
                //Found it?
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(array, index + 1, array, index, numMoved);

        array[--size] = null;
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) array[index];
    }

    private String outOfBoundsMessage(int index) {
        return "Index: " + index + ", size: " + size;
    }
}
