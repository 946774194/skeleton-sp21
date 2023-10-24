package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class Node<T> {
        private final T item;
        private Node<T> next;
        private Node<T> previous;

        private Node(T p1, Node<T> p2, Node<T> p3) {
            item = p1;
            next = p2;
            previous = p3;
        }
    }

    private int size;
    private final Node<T> sentinel = new Node<>(null, null, null);

    public LinkedListDeque() {
        sentinel.previous = sentinel.next = sentinel;
        size = 0;
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
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        @SuppressWarnings("unchecked") LinkedListDeque<T> lld = (LinkedListDeque<T>) o;
        if (lld.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!lld.get(i).equals(get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void addFirst(T item) {
        Node<T> tmp = new Node<>(item, sentinel.next, sentinel);
        tmp.next.previous = tmp;
        tmp.previous.next = tmp;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> tmp = new Node<>(item, sentinel, sentinel.previous);
        tmp.next.previous = tmp;
        tmp.previous.next = tmp;
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T retItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size--;
        return retItem;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T retItem = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size--;
        return retItem;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node<T> i = sentinel.next;
        while (index-- > 0) {
            i = i.next;
        }
        return i.item;
    }

    public T getRecursive(int index) {
        return get(index);
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
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node<T> nodeIterator;

        LinkedListDequeIterator() {
            nodeIterator = sentinel;
        }

        public boolean hasNext() {
            return nodeIterator.next != sentinel;
        }

        public T next() {
            T retItem = nodeIterator.next.item;
            nodeIterator = nodeIterator.next;
            return retItem;
        }
    }
}
