package gitlet;

import static gitlet.Capers.STAGE_FOLDER;
import static gitlet.Utils.*;

public class Stage {
    static boolean contains(String name){
        return false;
    }

    static void copy(String name){
        writeContents(join(STAGE_FOLDER, name), (Object) readContents(join(name)));
    }

    static void delete(String name){
        restrictedDelete(join(STAGE_FOLDER, name));
    }
}
