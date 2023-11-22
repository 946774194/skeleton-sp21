package gitlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static gitlet.Capers.STATUS_FOLDER;
import static gitlet.Utils.*;


public class Status {
    public interface StatusType<T>{
         T get();
         void write(T o);
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
    }

    static class StatusTypeList<T> implements StatusType<List<T>>{
        private final String s;
        StatusTypeList(String str){ s = str; }

        @Override
        @SuppressWarnings("unchecked")
        public List<T> get(){
            return (List<T>) readObject(join(STATUS_FOLDER, this.s), ArrayList.class);
        }

        @Override
        public void write(List<T> o){
            writeObject(join(STATUS_FOLDER, this.s), (ArrayList<T>) o);
        }
    }

    static class StatusTypeSet<T> implements StatusType<Set<T>>{
        private final String s;
        StatusTypeSet(String str){ s = str; }

        @Override
        @SuppressWarnings("unchecked")
        public Set<T> get(){
            return (Set<T>) readObject(join(STATUS_FOLDER, this.s), ArrayList.class);
        }

        @Override
        public void write(Set<T> o){
            writeObject(join(STATUS_FOLDER, this.s), (TreeSet<T>) o);
        }
    }

    static StatusTypeString HEAD = new StatusTypeString("HEAD");
    static StatusTypeSet<String> addList = new StatusTypeSet<>("addList");
    static StatusTypeSet<String> removedList = new StatusTypeSet<>("removedList");
    static void init(){
        HEAD.write("");
        addList.write(new TreeSet<>());
    }
}
