package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    final private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        T res = this.get(0);
        for (T i : this) {
            if (comparator.compare(i, res) > 0) {
                res = i;
            }
        }
        return res;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T res = this.get(0);
        for (T i : this) {
            if (c.compare(i, res) > 0) {
                res = i;
            }
        }
        return res;
    }
}
