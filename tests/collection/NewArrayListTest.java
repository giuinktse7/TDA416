package collection;

import collection.NewArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.*;

public class NewArrayListTest {

    private NewArrayList<Integer> list;
    private Random random = new Random();

    @Rule
    public ExpectedException thrown;

    @org.junit.Before
    public void setUp() throws Exception {
        list = new NewArrayList<>();
        thrown = ExpectedException.none();
    }

    private void randomlyFill(Collection<Integer> collection, int amount) {
        for (int i = 0; i < amount; ++i)
            list.add(random.nextInt());
    }

    @org.junit.Test
    public void size() throws Exception {
        int[] amounts = {0, 1, 100, 1000};

        for (int amount : amounts) {
            randomlyFill(list, amount);
            assertTrue(list.size() == amount);
            setUp();
        }
    }

    @org.junit.Test
    public void isEmpty() throws Exception {
        //Is a newly created list considered empty?
        assertTrue(list.isEmpty());

        //Does add work as intended?
        list.add(random.nextInt());
        assertFalse(list.isEmpty());

        //Does remove work as intended?
        list.remove(list.size() - 1);
        assertTrue(list.isEmpty());
    }

    @org.junit.Test
    public void contains() throws Exception {
        int includedNumber = random.nextInt();
        int notIncludedNumber = includedNumber - 1;

        list.add(includedNumber);

        assertTrue(list.contains(includedNumber));
        assertFalse(list.contains(notIncludedNumber));
    }

    @org.junit.Test
    public void iterator() throws Exception {
        assertTrue(false);
    }

    @org.junit.Test
    public void toArray() throws Exception {
        randomlyFill(list, 10);
        Object[] array = list.toArray();
        assertTrue(array.length == list.size());
    }

    @org.junit.Test
    public void toGenericArray() throws Exception {
        Integer[] array = list.toArray(new Integer[0]);
        assertTrue(array.length == list.size());
    }

    @org.junit.Test
    public void add() throws Exception {
        int size = 15;
        Integer[] numbers = new Integer[size];

        while(--size >= 0) {
            int number = random.nextInt();
            list.add(number);
            numbers[size] = number;
        }

        for (Integer number : numbers)
            assertTrue(list.contains(number));

        assertTrue(list.size() == numbers.length);
    }

    @org.junit.Test
    public void remove() throws Exception {
        Integer number = random.nextInt();
        list.add(number);

        assertTrue(list.remove(number));
        assertFalse(list.contains(number));

        list.add(number);
        assertTrue(list.remove(0) != null);
    }

    @org.junit.Test
    public void containsAll() throws Exception {
        Set<Integer> set = new TreeSet<>();
        int numberCount = 10;

        for (int i = 0; i < numberCount; ++i)
            set.add(random.nextInt());

        assertFalse(list.containsAll(set));

        list.addAll(set);
        assertTrue(list.containsAll(set));
    }

    @org.junit.Test
    public void removeAll() throws Exception {
        Set<Integer> set = new TreeSet<>();
        int numberCount = 10;

        for (int i = 0; i < numberCount; ++i)
            set.add(random.nextInt());

        list.addAll(set);
        list.removeAll(set);
        assertTrue(list.isEmpty());
    }

    @org.junit.Test
    public void retainAll() throws Exception {
        ArrayList<Integer> otherList = new ArrayList<>();
        otherList.add(27);
        otherList.add(14);

        list.add(14);
        list.add(27);
        list.add(27);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        System.out.println(list.size());
        list.retainAll(otherList);
        System.out.println(list.size());
        assertTrue(list.size() == 3);
    }

    @org.junit.Test
    public void addAll() throws Exception {
        ArrayList<Integer> otherList = new ArrayList<>();
        randomlyFill(otherList, 25);

        list.addAll(otherList);
        assertTrue(list.containsAll(otherList));
    }

    @org.junit.Test
    public void clear() throws Exception {
        randomlyFill(list, 50);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @org.junit.Test
    public void get() throws Exception {
        int number = random.nextInt();
        list.add(number);
        assertTrue(list.get(0).equals(number));
    }

    @org.junit.Test
    public void set() throws Exception {
        int number1 = random.nextInt();
        int number2 = random.nextInt();
        list.add(number1);

        list.set(0, number2);
        assertTrue(number2 == list.get(0));
    }

    @org.junit.Test
    public void indexOf() throws Exception {
        Integer[] numbers = new Integer[4];
        for (int i = 0; i < numbers.length; ++i)
            numbers[i] = random.nextInt();

        list.add(numbers[0]);
        list.add(numbers[1]);
        list.add(numbers[2]);
        list.set(1, numbers[numbers.length - 1]);

        assertTrue(list.indexOf(numbers[0]) == 0);
        assertTrue(list.indexOf(numbers[numbers.length - 1]) == 1);
        assertTrue(list.indexOf(numbers[2]) == 2);
        assertTrue(list.indexOf(numbers[1]) == -1);
    }

    @org.junit.Test
    public void lastIndexOf() throws Exception {
        list.add(5);
        randomlyFill(list, 20);
        list.add(5);
        assertTrue(list.lastIndexOf(5) == list.size() - 1);
    }

    @org.junit.Test
    public void listIterator() throws Exception {
        assertTrue(false);
    }

    @org.junit.Test
    public void listIterator1() throws Exception {
        assertTrue(false);
    }

    @org.junit.Test
    public void subList() throws Exception {
        assertTrue(false);
    }
}
