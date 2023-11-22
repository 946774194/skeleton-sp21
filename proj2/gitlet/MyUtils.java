package gitlet;

import java.io.File;

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
}
