package collection;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class HashTableChainTest {
    HashTableChain<Integer, Integer> map;

    @Before
    public void setup() {
        map = new HashTableChain<>();
    }

    @Test
    public void get() throws Exception {
        map.put(5, 5);
        map.put(3, 3);
        map.put(7, 7);
        map.put(13, 13);

        assertTrue(map.get(5) == 5);
        assertTrue(map.get(17) == null);
    }

    @Test
    public void isEmpty() throws Exception {
        map.put(5, 5);
        map.put(3, 3);
        map.put(7, 7);
        map.put(13, 13);

        assertFalse(map.isEmpty());
    }

    @Test
    public void put() throws Exception {

    }

    @Test
    public void remove() throws Exception {
        IntStream.range(0, 50).forEach(k -> map.put(k, k));
        map.put(27, 27);
        map.put(27, 27);

        assertTrue(map.remove(27) == 27);
        assertTrue(map.remove(27) == null);
    }

    @Test
    public void size() throws Exception {
        IntStream.range(0, 50).forEach(k -> map.put(k, k));
        assertTrue(map.size() == 50);
    }

}