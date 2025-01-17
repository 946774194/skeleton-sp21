package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        Stopwatch sw;
        SLList<Integer> L;
        AList<Integer> N = new AList<>();
        AList<Double> D = new AList<>();
        AList<Integer> O = new AList<>();
        N.addLast(1000);
        N.addLast(2000);
        N.addLast(4000);
        N.addLast(8000);
        N.addLast(16000);
        N.addLast(32000);
        N.addLast(64000);
        N.addLast(128000);
        for(int i = 1;i <= 8;++i)O.addLast(10000);

        for(int i = 0;i <= 7;++i){
            L = new SLList<>();
            int max = N.get(i);
            for(int j = 1;j <= max; ++j){
                L.addLast(j);
            }
            sw = new Stopwatch();
            for(int j = 1;j <= 10000;++j){
                L.getLast();
            }
            D.addLast(sw.elapsedTime());
        }
        printTimingTable(N, D, O);
    }

}
