package gitlet;

import java.io.File;
import static gitlet.Utils.*;
public class Test {
    public static void main(String[] a){
        File file = join("ab.txt");
        writeContents(file, "123");
    }
}
