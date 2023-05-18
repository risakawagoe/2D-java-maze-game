import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * class to handle user keyboard inputs
 */
public class inputKey implements KeyListener {
    GameFrame gf;
    public boolean pressedUp, pressedDown, pressedLeft, pressedRight;
    public boolean pressF;

    /**
     * class constructor
     * @param gf
     */
    public inputKey(GameFrame gf) {
        this.gf = gf;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Dont use this
    }

    /**
     * handle keyboard inputs
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        try {
            if(gf.state.getGameState() == gf.state.titleState) { // key input in title screen to move cursor

                if(key == KeyEvent.VK_UP) {
                    gf.playSoundEffect(0);
                    gf.cmdTitle.decCommand();
                }else if(key == KeyEvent.VK_DOWN) {
//                    System.out.println("DOWN pressed in title screen");
                    gf.playSoundEffect(0);
                    gf.cmdTitle.incCommand();
                }else if(key == KeyEvent.VK_ENTER) {
//                    System.out.println("enter pressed in title screen");
                    performActionWithEnter(gf.cmdTitle);
                }
            }else if(gf.state.getGameState() == gf.state.changeLevelState) { // key input in change level screen to move cursor
//                System.out.println("key pressed in change level screen");
                if(key == KeyEvent.VK_UP) {
                    gf.playSoundEffect(0);
                    gf.cmdChangeLevel.decCommand();
                }else if(key == KeyEvent.VK_DOWN) {
                    gf.playSoundEffect(0);
                    gf.cmdChangeLevel.incCommand();
                }else if(key == KeyEvent.VK_ENTER) {
                    gf.playSoundEffect(0);
                    performActionWithEnter(gf.cmdChangeLevel);
//                    System.out.println("enter pressed in change level screen");
                }
            } else if(gf.state.getGameState() == gf.state.playState) { // key input during play state
                if(key== KeyEvent.VK_F){
                    pressF = true;
                }else if(key == KeyEvent.VK_UP){
                    pressedUp = true;
                }else if(key == KeyEvent.VK_DOWN){
                    pressedDown = true;
                }else if(key == KeyEvent.VK_LEFT){
                    pressedLeft = true;
                }else if(key == KeyEvent.VK_RIGHT){
                    pressedRight = true;
                }

            }else if(gf.state.getGameState() == gf.state.tutorialState) {
                if(key== KeyEvent.VK_LEFT){
                    gf.state.prevPage();
                    gf.playSoundEffect(0);
                }else if(key == KeyEvent.VK_RIGHT){
                    gf.state.nextPage();
                    gf.playSoundEffect(0);
                }else if(key == KeyEvent.VK_ENTER){
                    gf.state.skipTutorial();
                    gf.playSoundEffect(0);
                }
            }else if(gf.state.getGameState() == gf.state.narrationState) {
                if(key== KeyEvent.VK_LEFT){
                    gf.state.prevPage();
                    gf.playSoundEffect(0);
                }else if(key == KeyEvent.VK_RIGHT){
                    gf.state.nextPage();
                    gf.playSoundEffect(0);
                }else if(key == KeyEvent.VK_ENTER){
                    gf.state.skipNarration();
                    gf.stopBGM(4);
                }
            }else if(gf.state.getGameState() == gf.state.endState) {
                if(key == KeyEvent.VK_LEFT) {
                    gf.playSoundEffect(0);
                    gf.cmdEnd.decCommand();
                }else if(key == KeyEvent.VK_RIGHT) {
                    gf.playSoundEffect(0);
                    gf.cmdEnd.incCommand();
                }else if(key == KeyEvent.VK_ENTER) {
                    performActionWithEnter(gf.cmdEnd);
                }
            }
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * handle when keys are released
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key== KeyEvent.VK_F){
            pressF = false;
        }
        if(key == KeyEvent.VK_UP){
            pressedUp = false;
        }else if(key == KeyEvent.VK_DOWN){
            pressedDown = false;
        }else if(key == KeyEvent.VK_LEFT){
            pressedLeft = false;
        }else if(key == KeyEvent.VK_RIGHT){
            pressedRight = false;
        }
    }

    private void performActionWithEnter(TitleScreenCommand cmd) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if(cmd.getCommandNum() == cmd.cmdStart) {
            gf.state.toTutorialState();
            cmd.setCommandNum(0);
        }else if(cmd.getCommandNum() == cmd.cmdChangeLevel) {
            gf.playSoundEffect(0);
            gf.state.toChangeLevelState();
        }else if(cmd.getCommandNum() == cmd.cmdExit) {
            gf.playSoundEffect(2);
            System.exit(0);
        }else {// exception: reset commandNum to 0
            cmd.setCommandNum(0);
        }
        cmd.setCommandNum(0);
    }
    private void performActionWithEnter(ChangeLevelCommand cmd) {
        if(cmd.getCommandNum() == cmd.cmdEasy) {
            gf.settings.setGameLevel(gf.settings.levelEasy);
            gf.mc.setDefaultValue(gf.tileFrame.getStartPoints(gf.settings.levelEasy));
            gf.state.toTitleState();
        }else if(cmd.getCommandNum() == cmd.cmdIntermediate) {
            gf.settings.setGameLevel(gf.settings.levelIntermediate);
            gf.mc.setDefaultValue(gf.tileFrame.getStartPoints(gf.settings.levelIntermediate));
            gf.state.toTitleState();
        }else if(cmd.getCommandNum() == cmd.cmdChallenge) {
            gf.settings.setGameLevel(gf.settings.levelChallenge);
            gf.mc.setDefaultValue(gf.tileFrame.getStartPoints(gf.settings.levelChallenge));
            gf.state.toTitleState();
        }else {
            gf.settings.setGameLevel(gf.settings.levelEasy);
            gf.state.toChangeLevelState();
        }
    }
    private void performActionWithEnter(EndScreenCommand cmd) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        System.out.println("[inputKey] before enter key pressed, game state: " + gf.gameState);
        if(cmd.getCommandNum() == cmd.cmdRetry) {// restart game at same level: return to tutorial screen
            gf.playSoundEffect(0);
            gf.stopBGM(3);
            gf.resetGame(cmd.getCommandNum());// partial reset(game level unchanged)
            gf.state.toTutorialState();
        }else if(cmd.getCommandNum() == cmd.cmdReturn) {// reset game attributes: return to title screen
            gf.playSoundEffect(0);
            gf.stopBGM(3);
            gf.resetGame(cmd.getCommandNum());// full reset
            gf.state.toTitleState();
        }
//        System.out.println("[inputKey] after enter key pressed, game state: " + gf.gameState);
        cmd.setCommandNum(0);
    }
}
