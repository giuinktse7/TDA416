package lab1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class AdaptedQueueTest {

    private AdaptedQueue<Integer> queue;

    @Before
    public void setup() {
        queue = new AdaptedQueue<>();
    }

    @Test
    public void offer() throws Exception {
        Random rand = new Random();
        for (int i = 0; i < 50000; ++i) {
            int k = rand.nextInt(50000);
            queue.offer(k);
            System.out.println(k);
        }

        System.out.println("Hmm? " + queue.poll());
        System.out.println("Hmm? " + queue.poll());
        System.out.println("Hmm? " + queue.poll());
        System.out.println("Hmm? " + queue.poll());
        System.out.println("Hmm? " + queue.poll());
    }

    @Test
    public void find() {
        queue.offer(12);
        queue.offer(8);
        queue.offer(3);
        queue.offer(4);
        queue.offer(2);

        System.out.println(queue.indexOf(4));
    }

}