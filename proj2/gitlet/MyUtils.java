package gitlet;

import java.io.File;
import java.util.Date;

import static gitlet.Utils.*;

public class MyUtils {
    static long TS(){ return System.currentTimeMillis(); }
    public static void creatFolders(File... files){
        for(File file:files){
            if(!file.isDirectory()){
                try {
                    file.mkdirs();
                }catch (Exception e){ Main.log(e.toString()); }
            }
        }
    }

    public static String getSHA1(File file){
        return sha1((Object) readContents(file));
    }

    public static String getSHA1(String name){
        return sha1((Object) readContents(join(name)));
    }

    public static void printCommit(Commit commit){
        System.out.println("===");
        System.out.println("commit " + commit.getSha1());
        if(!commit.secondParent.isEmpty()){
            System.out.println("Merge: "
                    + commit.parent.substring(0,7)
                    + " "
                    + commit.secondParent.substring(0,7));
        }
        Date date = new Date(commit.timestamp);
        System.out.println("Date: " + date.toString());
        System.out.println(commit.msg);
        System.out.println();
    }
}
