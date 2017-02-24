package collection;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class CircularLinkedListTest {

    private CircularLinkedList<Integer> list;

    @Before
    public void init() {
        list = new CircularLinkedList<>();
    }

    @Test
    public void add() throws Exception {
        list.add(2);

        assertTrue(list.size() == 1);

        list.add(5);
        list.add(5);

        assertTrue(list.size() == 3);
    }

    @Test
    public void remove() throws Exception {
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        assertTrue(list.remove(10) == 10);
        assertTrue(list.remove(15) == null);
        assertTrue(list.remove(9) == 9);
        assertTrue(list.remove(6) == 6);
        assertTrue(list.remove(7) == 7);
        assertTrue(list.remove(10) == null);
        assertTrue(list.size() == 1);
    }

    @Test
    public void iteration() throws Exception {
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);

        Iterator<Integer> iterator = list.iterator();
        int loops = 2;
        int stopAt = list.size() * loops;
        int i = 0;
        while (iterator.hasNext() && i++ < stopAt)
            System.out.println(iterator.next());
    }

    @Test
    public void removeAt() {
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.println(list.removeAt(4));
    }

    @Test
    public void playGame() {
        Integer[] k = {1, 2, 3, 4, 5, 6, 7};
        list.addAll(k);

        Iterator<Integer> it = list.iterator();
        int everyX = 3;
        int current = 0;
        while (it.hasNext()) {
            int value = it.next();
            if (++current % everyX == 0) {
                it.remove();
                System.out.println(value);
            }
        }
    }

}