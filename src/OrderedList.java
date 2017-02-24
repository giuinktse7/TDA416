import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class OrderedList<E extends Comparable<E>> implements Iterable<E> {

    private LinkedList<E> list = new LinkedList<>();

    public OrderedList() {

    }

    public void add(E e) {
        ListIterator<E> iterator = list.listIterator();

        while(iterator.hasNext()) {
            if (e.compareTo(iterator.next()) < 0) {
                iterator.previous();
                iterator.add(e);
                return;
            }
        }

        iterator.add(e);
    }

    public Iterator<E> iterator() {
        return list.iterator();
    }

    public E get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean remove(E e) {
        return list.remove(e);
    }



}
