package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author me
 */
public class Main {
    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */

    static final boolean DEBUG = true;
    public static void main(String[] args) {
        validateArgs(args);
        switch(args[0]) {
            case "init":
                Capers.init();break;
            case "add":
                Capers.add(args[1]);break;
            case "commit":
                Capers.commit(args[1]);break;
            case "rm":
                Capers.rm(args[1]);break;
            case "log":
                Capers.log();break;
            case "global-log":
                Capers.globalLog();break;
            case "find":
                Capers.find(args[1]);break;
            case "status":
                Capers.status();break;
        }
    }

    public static void validateArgs(String[] args){
        if (args.length == 0) {
            exitWithMsg("Please enter a command.");
        }
        String cmd = args[0];
        int n = args.length;
        switch (cmd){
            case "init", "log", "global-log", "status":
                if(n != 1){ exitWithMsg("Incorrect operands."); }
                break;
            case "add", "rm", "find":
                if(n != 2){ exitWithMsg("Incorrect operands."); }
                break;
            case "commit":
                if(n == 1){ exitWithMsg("Please enter a commit message."); }
                if(n != 2){ exitWithMsg("Incorrect operands."); }
                break;
            default:
                exitWithMsg("No command with that name exists.");
        }
    }

    public static void exitWithMsg(String msg){
        System.out.println(msg);
        System.exit(0);
    }

    public static void log(String type, Object... list){
        if(!DEBUG)return;
        System.out.println(type);
        for(Object o : list){
            System.out.print(o.toString() + "\t");
        }
        System.out.println();
    }
}
