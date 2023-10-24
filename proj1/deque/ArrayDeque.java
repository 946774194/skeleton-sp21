package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private int capacity;
    @SuppressWarnings("unchecked")
    private T[] items = (T[]) new Object[8];

    public ArrayDeque() {
        size = 0;
        capacity = 8;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[index];
    }

    private void resize(int newCapacity) {
        @SuppressWarnings("unchecked")
        T[] a = (T[]) new Object[newCapacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        capacity = newCapacity;
    }

    @Override
    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[size++] = item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T res = items[size - 1];
        items[--size] = null;
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 4);
        }
        return res;
    }

    @Override
    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        @SuppressWarnings("unchecked")
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 1, size++);
        a[0] = item;
        items = a;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T res = items[0];
        @SuppressWarnings("unchecked")
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 1, a, 0, --size);
        items = a;
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 4);
        }
        return res;
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
            iteratorIndex = 0;
        }

        public boolean hasNext() {
            return iteratorIndex < size;
        }

        public T next() {
            return items[iteratorIndex++];
        }
    }

}
