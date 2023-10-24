package deque;

import org.junit.Assert.*;
import org.junit.Test;


public class ArrayDequeTest {
    @Test
    public void simplyAddAndRemoveFirstTest() {
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

    @Test
    public void balanceTest() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 10000; i += 2) {
            deque.addLast(i);
            deque.addLast(i + 1);
            deque.removeFirst();
        }
        for (int i = 0; i < 32; i++) {
            deque.removeFirst();
        }
    }
}
