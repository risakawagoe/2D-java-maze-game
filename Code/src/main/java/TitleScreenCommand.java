public class TitleScreenCommand extends Command {
    public final int cmdStart = 0;
    public final int cmdChangeLevel = 1;
    public final int cmdExit = 2;

    TitleScreenCommand() {
        super();
        addCommand("start");
        addCommand("changeLevel");
        addCommand("exit");
    }
}
