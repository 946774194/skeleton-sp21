package deque;

import org.junit.Assert.*;
import org.junit.Test;


public class ArrayDequeTest {
    @Test
    public void simplyAddTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 50; i++) {
            deque.removeFirst();
        }
        deque.printDeque();
    }

    @Test
    public void simplyAddAndRemoveLastTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 100; i++) {
            deque.removeLast();
        }
    }
}
