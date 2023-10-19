package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove(){
      AListNoResizing<Integer> L1 = new AListNoResizing<>();
      BuggyAList<Integer> L2 = new BuggyAList<>();
      L1.addLast(1);
      L1.addLast(2);
      L1.addLast(3);
      L2.addLast(1);
      L2.addLast(2);
      L2.addLast(3);
      assertEquals(L1.size(),L2.size());
      assertEquals(L1.removeLast(),L2.removeLast());
      assertEquals(L1.removeLast(),L2.removeLast());
      assertEquals(L1.removeLast(),L2.removeLast());
    }
    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> Lgood = new AListNoResizing<>();
        BuggyAList<Integer> Lbad = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                Lgood.addLast(randVal);
                Lbad.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
                assertEquals(Lgood.size(), Lbad.size());
            } else if (operationNumber == 1) {
                // size
                int size = Lgood.size();
//                System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // removeLast
                if (Lgood.size() == 0)continue;
//                System.out.println("removeLast()"+ Lgood.size());
                assertEquals(Lgood.removeLast(), Lbad.removeLast());
                assertEquals(Lgood.size(), Lbad.size());
            }
        }
    }

}
