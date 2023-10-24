package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size, capacity, nextFirst, nextLast;
    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Object[8];

    public ArrayDeque() {
        size = 0;
        capacity = 8;
        nextLast = capacity / 2;
        nextFirst = capacity / 2 - 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (isEmpty() || index >= size || index < 0) {
            return null;
        }
        return items[nextFirst + 1 + index];
    }

    //wonder if I can balance the array
    //(F+L-C)/2 > C/6
    public void checkResize() {
        if (nextFirst == -1 || nextLast == capacity) {
            resize(capacity * 2);
        } else if (capacity > 16 && size < capacity / 4) {
            resize(capacity / 4);
        } else if (size >= 64) {
            double d = Math.abs(nextFirst + nextLast - capacity) * 3;
            if (d > (double) capacity) {
                resize(capacity);
            }
        }
    }

    public void resize(int newCapacity) {
        @SuppressWarnings("unchecked")
        T[] newItems = (T[]) new Object[newCapacity];
        System.arraycopy(items, nextFirst + 1, newItems, (newCapacity - size) / 2, size);
        nextFirst = (newCapacity - size) / 2 - 1;
        nextLast = (newCapacity - size) / 2 + size;
        capacity = newCapacity;
        items = newItems;
    }

    @Override
    public void addFirst(T item) {
        checkResize();
        items[nextFirst--] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        checkResize();
        items[nextLast++] = item;
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T retItem = get(0);
        items[++nextFirst] = null;
        size--;
        checkResize();
        return retItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T retItem = get(size - 1);
        items[--nextLast] = null;
        size--;
        checkResize();
        return retItem;
    }

    @Override
    public void printDeque() {
        StringBuilder sb = new StringBuilder();
        for (T i : this) {
            sb.append(i).append(' ');
        }
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        System.out.println(sb.toString());
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<T> {
        private int iteratorIndex;

        ArrayDequeIterator() {
            iteratorIndex = nextFirst + 1;
        }

        public boolean hasNext() {
            return iteratorIndex < nextLast;
        }

        public T next() {
            return items[iteratorIndex++];
        }
    }
}
