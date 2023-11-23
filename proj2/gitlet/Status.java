package gitlet;

import java.util.*;

import static gitlet.Capers.STATUS_FOLDER;
import static gitlet.Utils.*;


public class Status {
    public interface StatusType<T>{
         T get();
         void write(T o);
         void clean();
    }

    static class StatusTypeString implements StatusType<String>{
        private final String s;

        StatusTypeString(String str){ s = str; }

        @Override
        public String get(){
            return readContentsAsString(join(STATUS_FOLDER, this.s));
        }

        @Override
        public void write(String o){
            writeContents(join(STATUS_FOLDER, this.s), o);
        }

        @Override
        public void clean(){write("");}
    }

    static class StatusTypeMap<T> implements StatusType<Map<T, String>>{
        private final String s;
        StatusTypeMap(String str){ s = str; }

        @Override
        @SuppressWarnings("unchecked")
        public Map<T, String> get(){
            return (Map<T, String>) readObject(join(STATUS_FOLDER, this.s), HashMap.class);
        }

        @Override
        public void write(Map<T, String> o){
            writeObject(join(STATUS_FOLDER, this.s), (HashMap<T, String>) o);
        }

        @Override
        public void clean(){write(new HashMap<>());}
    }

    static class StatusTypeSet<T> implements StatusType<Set<T>>{
        private final String s;
        StatusTypeSet(String str){ s = str; }

        @Override
        @SuppressWarnings("unchecked")
        public Set<T> get(){
            return (Set<T>) readObject(join(STATUS_FOLDER, this.s), TreeSet.class);
        }

        @Override
        public void write(Set<T> o){
            writeObject(join(STATUS_FOLDER, this.s), (TreeSet<T>) o);
        }

        @Override
        public void clean(){ write(new TreeSet<>()); }
    }

    static StatusTypeString HEAD = new StatusTypeString("HEAD");
    static StatusTypeString curBranch = new StatusTypeString("curBranch");
    static StatusTypeSet<String> addList = new StatusTypeSet<>("addList");
    static StatusTypeSet<String> removedList = new StatusTypeSet<>("removedList");
    static StatusTypeSet<String> commitList = new StatusTypeSet<>("commitList");
    static StatusTypeMap<String> branches = new StatusTypeMap<>("branches");
    static void init(){
        HEAD.clean();
        curBranch.clean();
        addList.clean();
        removedList.clean();
        commitList.clean();
        branches.clean();
    }
}
