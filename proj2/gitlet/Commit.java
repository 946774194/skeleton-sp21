package gitlet;

import java.io.Serializable;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static gitlet.Utils.*;
import static gitlet.Capers.OBJECT_FOLDER;


/** Represents a gitlet commit object.
 *  does at a high level.
 *
 *  @author me
 */
public class Commit implements Serializable {
    final long timestamp;
    final String msg;
    final String parent;
    final String secondParent;
    private final String sha1;
    Map<String, String> map = new HashMap<>();

    Commit(long timestamp, String msg, String parent){
        this(timestamp, msg, parent, "");
    }

    Commit(long timestamp, String msg, String parent, String secondParent){
        this.timestamp = timestamp;
        this.msg = msg;
        this.parent = parent;
        this.secondParent = secondParent;
        this.sha1 = sha1(Long.toString(timestamp), msg, parent, secondParent);
    }

    String getSha1(){ return sha1;}

    Commit save(){
        Main.log("saving commit:", sha1, timestamp, msg, parent, secondParent);
        File dir = join(OBJECT_FOLDER, sha1.substring(0,2));
        dir.mkdirs();
        File file = join(dir, sha1.substring(2));
        writeObject(file, this);

        Set<String> set = Status.commitList.get();
        set.add(sha1);
        Status.commitList.write(set);

        Map<String, String> m = Status.branches.get();
        m.put(Status.curBranch.get(), sha1);
        Status.branches.write(m);
        return this;
    }
    Commit setMap(Map<String, String> map){
        this.map = map;
        return this;
    }

    public static Commit read(String sha1){
        File file = join(OBJECT_FOLDER, sha1.substring(0,2), sha1.substring(2));
        if(!file.exists()){
            Main.exitWithMsg("commit not exist");
        }
        Commit c = readObject(file, Commit.class);
        Main.log("reading commit:" + sha1, sha1.equals(c.sha1),
                c.timestamp, c.msg, c.parent, c.secondParent);
        return c;
    }
}
