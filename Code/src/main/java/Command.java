import java.util.HashMap;
import java.util.Map;

public class Command {
    protected Map<String, Integer> commandOption = new HashMap<String, Integer>();
    protected int commandNum;
    Command() {
        commandNum = 0;
    }
    /**
     * add command to command list with its description name as parameter
     * @param newCmd
     */
    protected void addCommand(String newCmd) {
        commandOption.put(newCmd, commandOption.size());
    }
    public int getNumOfCommands() {
        return commandOption.size();
    }
    /**
     * sets the current command if parameter found in the command list
     * @param n
     */
    public void setCommandNum(int n) {
        if(find(n)) {
            commandNum = n;
        }else {// exception: set the default value 0
            commandNum = 0;
        }
    }

    public int getCommandNum() {
        return commandNum;
    }

    protected boolean find(int n) {
        if(commandOption.containsValue(n)) {
            return true;
        }
        return false;
    }
    /**
     * increments command by 1, if exceeds max commandNum returns to 0
     */
    public void incCommand() {
        commandNum++;
        if(commandNum >= commandOption.size()) {
            commandNum = 0;
        }else if(commandNum < 0) {// exception
            commandNum = commandOption.size() - 1;
        }
    }
    /**
     * decrements command by 1, if negative sets to 0
     */
    public void decCommand() {
        commandNum--;
        if(commandNum < 0) {
            commandNum = commandOption.size() - 1;
        }else if(commandNum >= commandOption.size() - 1) {// exception
            commandNum = 0;
        }
    }
}
