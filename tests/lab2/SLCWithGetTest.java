package lab2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static org.junit.Assert.*;

public class SLCWithGetTest {

    SLCWithGet<Integer> list = new SLCWithGet<>();
    @Before
    public void setup() {
        list = new SLCWithGet<>();
    }

    public void randomInts(int amount, int max) {
        new Random().ints(amount, 0, max).forEach(list::add);
    }

    public void randomInts(int amount, int max, int seed) {
        System.out.println("Seeded numbers: ");
        ArrayList<Integer> ints = new ArrayList<>();
        new Random(seed).ints(amount, 0, max).forEach(i -> {
            System.out.println(i);
            ints.add(i);
        });

        ints.forEach(list::add);
    }

    @Test
    public void add() throws Exception {
        randomInts(5000, 100);

        Iterator<Integer> it = list.iterator();

        while(it.hasNext())
            System.out.println(it.next());
    }
}