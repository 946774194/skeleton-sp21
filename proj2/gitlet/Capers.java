package gitlet;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
        Status.curBranch.write("master");
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
        Commit head = Commit.read(Status.HEAD.get());
        if(head.map.containsKey(name)){
            if(head.map.get(name).equals(getSHA1(file))){
                Stage.delete(name);
                if(s.remove(name))
                    Status.addList.write(s);
                return;
            }
        }
        if(s.add(name))
            Status.addList.write(s);
        Stage.copy(name);
    }

    public static void commit(String msg){
        Set<String> addSet = Status.addList.get();
        Set<String> removedSet = Status.removedList.get();
        if(addSet.isEmpty() && removedSet.isEmpty()){
            Main.exitWithMsg("No changes added to the commit.");
        }
        Map<String, String> map = Commit.read(Status.HEAD.get()).map;
        for(String s:addSet){
            map.put(s, getSHA1(s));
        }
        for (String s : removedSet){
            map.remove(s);
        }
        String s = new Commit(TS(), msg, Status.HEAD.get()).setMap(map).save().getSha1();
        Status.HEAD.write(s);
        Stage.saveStage(s);

        Status.addList.clean();
        Status.removedList.clean();
    }

    public static void rm(String name){
        Set<String> addSet = Status.addList.get();
        Set<String> removedSet = Status.removedList.get();
        Map<String, String> map = Commit.read(Status.HEAD.get()).map;
        boolean b = addSet.contains(name);
        if(b){
            Stage.delete(name);
            addSet.remove(name);
        }
        if(map.containsKey(name) || b){
            removedSet.add(name);
            join(name).delete();
        }else{
            Main.exitWithMsg("No reason to remove the file.");
        }
    }

    public static void log(){
        String s = Status.HEAD.get();
        Commit c;
        do {
            c = Commit.read(s);
            printCommit(c);
            s = c.parent;
        }while (!c.msg.equals("initial commit"));
    }

    public static void globalLog(){
        Set<String> s = Status.commitList.get();
        for(String str:s){
            printCommit(Commit.read(str));
        }
    }

    public static void find(String m){
        boolean flag = false;
        Set<String> s = Status.commitList.get();
        for (String str:s){
            Commit c = Commit.read(str);
            if(c.msg.equals(m)){
                System.out.println(c.getSha1());
                flag = true;
            }
        }
        if(!flag){
            Main.exitWithMsg("Found no commit with that message.");
        }
    }

    public static void status(){
        System.out.println("=== Branches ===");
        String cb = Status.curBranch.get();
        System.out.println("*" + cb);
        Map<String, String> m = Status.branches.get();
        String[] arr = m.keySet().stream().sorted(String::compareTo).toArray(String[]::new);
        for(String s:arr){
            if(!s.equals(cb)){
                System.out.println(s);
            }
        }
        System.out.println();

        System.out.println("=== Staged Files ===");
        Set<String> addSet = Status.addList.get();
        arr = addSet.stream().sorted(String::compareTo).toArray(String[]::new);
        for(String s:arr){
            System.out.println(s);
        }
        System.out.println();

        System.out.println("=== Removed Files ===");
        Set<String> removedSet = Status.removedList.get();
        arr = removedSet.stream().sorted(String::compareTo).toArray(String[]::new);
        for(String s:arr){
            System.out.println(s);
        }
        System.out.println();

        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println();

        System.out.println("=== Untracked Files ===");
        System.out.println();
    }
}
