public class ChangeLevelCommand extends Command {
    public final int cmdEasy = 0;
    public final int cmdIntermediate = 1;
    public final int cmdChallenge = 2;

    ChangeLevelCommand() {
//        super();
        addCommand("easy");
        addCommand("intermediate");
        addCommand("challenge");
    }
}
