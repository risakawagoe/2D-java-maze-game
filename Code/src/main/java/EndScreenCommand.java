public class EndScreenCommand extends Command {
    public final int cmdRetry = 0;
    public final int cmdReturn = 1;

    EndScreenCommand() {
        super();
        addCommand("retry");
        addCommand("return");
    }
}
