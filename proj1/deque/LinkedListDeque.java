package deque;


import java.util.Iterator;

interface LinkedListDequeInterface<T> extends Iterable<T> {
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

public class LinkedListDeque<T> implements LinkedListDequeInterface<T> {
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
    private final Node<T> sentinel = new Node<T>(null, null, null);

    public LinkedListDeque() {
        sentinel.previous = sentinel.next = sentinel;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;

        if (!(o instanceof LinkedListDeque)) return false;
        LinkedListDeque<?> lld = (LinkedListDeque<?>) o;
        if (lld.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

    public void addFirst(T item) {
        Node<T> tmp = new Node<T>(item, sentinel.next, sentinel);
        tmp.next.previous = tmp;
        tmp.previous.next = tmp;
        size++;
    }

    public void addLast(T item) {
        Node<T> tmp = new Node<T>(item, sentinel, sentinel.previous);
        tmp.next.previous = tmp;
        tmp.previous.next = tmp;
        size++;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> tmp = sentinel.next;
        T retItem = tmp.item;
        sentinel.next = tmp.next;
        tmp.next.previous = tmp.previous;
        size--;
        tmp = null;
        return retItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> tmp = sentinel.previous;
        T retItem = tmp.item;
        sentinel.previous = tmp.previous;
        tmp.previous.next = tmp.next;
        size--;
        tmp = null;
        return retItem;
    }

    public T get(int index) {
        if (index > size - 1) {
            System.out.println("out of bound");

            return null;
        }
        Node<T> i = sentinel.next;
        while (index-- > 0) {
            i = i.next;
        }
        return i.item;
    }

    public void printDeque() {
//        if(size == 0){
//            return;
//        }
//        Node<T> i = sentinel.next;
//        if (i != sentinel){
//            System.out.printf(i.item.toString());
//            i = i.next;
//        }
//        while (i != sentinel){
//            System.out.printf(" " + i.item.toString());
//            i = i.next;
//        }
        StringBuilder sb = new StringBuilder();
        for(T i : this)sb.append(i).append(' ');
        if(!sb.isEmpty()) sb.deleteCharAt(sb.length()-1);
        System.out.println(sb.toString());
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        Node<T> nodeIterator;

        public LinkedListDequeIterator() {
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
