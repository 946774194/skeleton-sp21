package gitlet;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static gitlet.Utils.*;
import static gitlet.MyUtils.*;

public class Capers {
    static final File CWD = new File(System.getProperty("user.dir"));
    static final File GITLET_FOLDER = join(".gitlet/");
    static final File STAGE_FOLDER = join(GITLET_FOLDER, "stage/");
    static final File OBJECT_FOLDER = join(GITLET_FOLDER, "object/");
    static final File STATUS_FOLDER = join(GITLET_FOLDER, "status/");
    static final List<File> folderList = new ArrayList<>(Arrays.asList(
            GITLET_FOLDER, STAGE_FOLDER, OBJECT_FOLDER, STATUS_FOLDER
    ));

    public static void init(){
        if(GITLET_FOLDER.isDirectory()){
            Main.exitWithMsg("A Gitlet version-control system already exists in the current directory.");
        }
        for(File file:folderList)creatFolders(file);
        Status.init();
        String s  = new Commit(0, "initial commit", "").save().getSha1();
        Status.HEAD.write(s);
        Main.log("current head:", Status.HEAD.get());
    }

    public static void add(String name){
        File file = join(name);
        if(!file.exists()){
            Main.exitWithMsg("File does not exist.");
        }

        Set<String> s = Status.addList.get();
        s.add(name);
        Status.addList.write(s);
        Commit head = Commit.read(Status.HEAD.get());
        if(head.map.containsKey(name)){
            if(head.map.get(name).equals(getSHA1OfFile(file))){
                Stage.delete(name);
                return;
            }
        }
        Stage.copy(name);
    }

    public static void commit(String msg){
        Commit head = Commit.read(Status.HEAD.get());
        new Commit(TS(), msg, Status.HEAD.get()).setMap(null).save();
    }
}
