package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomOperationTest() {
        StringBuffer sb = new StringBuffer();
        StudentArrayDeque<Integer> d2 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> d1 = new ArrayDequeSolution<>();
        for (int i = 0; i < 10000; i++) {
            int op = StdRandom.uniform(6);
            switch (op) {
                case 0:
                    d1.addFirst(i);
                    d2.addFirst(i);
                    sb = new StringBuffer();
                    sb.append("addFirst(").append(i).append(")");
                    System.out.println(sb);
                    assertEquals(sb.toString(), d1.get(0), d2.get(0));
                    break;
                case 1:
                    d1.addLast(i);
                    d2.addLast(i);
                    sb = new StringBuffer().append("addLast(").append(i).append(")");
                    System.out.println(sb);
                    assertEquals(sb.toString(), d1.get(0), d2.get(0));
                    break;
                case 2:
                    sb = new StringBuffer().append("removeFirst(").append(")");
                    System.out.println(sb);
                    assertEquals("77777", d1.removeFirst(), d2.removeFirst());
                    break;
                case 3:
                    sb = new StringBuffer().append("removeLast(").append(")");
                    System.out.println(sb);
                    assertEquals(d1.removeLast(), d2.removeLast());
                    break;
                case 4:
                    if (d1.isEmpty()) {
                        break;
                    }
                    int index = StdRandom.uniform(d1.size());
                    sb = new StringBuffer().append("get(").append(index).append(")");
                    System.out.println(sb);
                    assertEquals(sb.toString(), d1.get(index), d2.get(index));
                    break;
            }
        }
    }
}
