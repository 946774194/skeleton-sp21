package deque;

import java.util.Iterator;

interface ArrayDequeInterface<T> extends Iterable<T> {
    public void addFirst(T item);

    public void addLast(T item);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public T removeFirst();

    public T removeLast();

    public T get(int index);

    public Iterator<T> iterator();

    public boolean equals(Object o);
}

public class ArrayDeque<T> implements ArrayDequeInterface<T> {
    private int size;
    private int capacity;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return items[index];
    }

    private void resize(int newCapacity) {
        T[] a = (T[]) new Object[newCapacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        capacity = newCapacity;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        items[size++] = item;
    }

    public T removeLast() {
        T res = items[size - 1];
        items[--size] = null;
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 4);
        }
        return res;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 2);
        }
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 1, size++);
        a[0] = item;
        items = a;
    }

    public T removeFirst() {
        if (size == 0) return null;
        T res = items[0];
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 1, a, 0, --size);
        items = a;
        if (capacity >= 16 && size < capacity / 4) {
            resize(capacity / 4);
        }
        return res;
    }

    public void printDeque() {
        StringBuilder sb = new StringBuilder();
        for (T i : this) sb.append(i).append(' ');
        if (!sb.isEmpty()) sb.deleteCharAt(sb.length() - 1);
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
