package gitlet;

import java.io.File;
import java.util.List;

import static gitlet.Capers.STAGE_FOLDER;
import static gitlet.Capers.OBJECT_FOLDER;
import static gitlet.Utils.*;

public class Stage {
    static boolean contains(String name){
        return false;
    }

    static void copy(String name){
        writeContents(join(STAGE_FOLDER, name), (Object) readContents(join(name)));
    }

    static void delete(String name){
        join(STAGE_FOLDER, name).delete();
    }

    static void saveStage(String sha1){
        File dir = join(OBJECT_FOLDER, sha1.substring(0,2), sha1);
        dir.mkdirs();
        List<String> ls = plainFilenamesIn(STAGE_FOLDER);
        if(ls!=null)
            for(String s : ls){
                File file = join(STAGE_FOLDER, s);
                writeContents(join(dir, s), (Object) readContents(file));
                file.delete();
            }
    }

    static void cleanStage(){
        List<String> ls = plainFilenamesIn(STAGE_FOLDER);
        if(ls!=null)
            for(String s : ls){
                join(STAGE_FOLDER, s).delete();
            }
    }
}
