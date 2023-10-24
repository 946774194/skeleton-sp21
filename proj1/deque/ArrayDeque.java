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

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        ArrayDeque<T> ad = (ArrayDeque<T>) o;
        if (ad.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!ad.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
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
        T[] a;
        if (size == capacity) {
            a = (T[]) new Object[capacity * 2];
        } else {
            a = (T[]) new Object[capacity];
        }
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
        T[] a;

        if (capacity >= 16 && size - 1 < capacity / 4) {
            a = (T[]) new Object[capacity / 4];
        } else {
            a = (T[]) new Object[capacity];
        }
        System.arraycopy(items, 1, a, 0, --size);
        items = a;

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

    private class ArrayDequeIterator implements Iterator<T> {
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
