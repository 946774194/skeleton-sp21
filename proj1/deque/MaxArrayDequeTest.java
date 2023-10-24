package deque;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {
    public static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static class StringComparator implements Comparator<String> {
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    public void simplyCompare() {
        MaxArrayDeque<Integer> d1 = new MaxArrayDeque<>(new IntegerComparator());
        MaxArrayDeque<String> d2 = new MaxArrayDeque<>(new StringComparator());
        d1.addLast(3);
        d1.addLast(1);
        d1.addLast(4);
        d1.addLast(9);
        d1.addLast(5);
        Assert.assertEquals(9, (int) d1.max());
        d2.addLast("222");
        d2.addLast("111");
        d2.addLast("ccc");
        d2.addLast("aaa");
        Assert.assertEquals("ccc", d2.max());
    }
}
