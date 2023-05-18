import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/*
>> keyPressed
1. titleState (UP, DOWN, ENTER)
2. changeLevelState (UP, DOWN, ENTER)
3. playState (UP, DOWN, LEFT, RIGHT, F, ESCAPE) *escape is not used in system (should not be used)
4. tutorialState (LEFT, RIGHT, ENTER)
5. narrationState (LEFT, RIGHT, ENTER)
6. endState (LEFT, RIGHT, ENTER)

>> keyReleased (mainly for playState)
UP, DOWN, LEFT, RIGHT, F
 */

public class inputKeyTest {

    // 1. titleState (UP, DOWN, ENTER)
    @Test
    public void upPressedInTitleState() throws IOException, AWTException {
        System.out.println("[upPressedInTitleState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.cmdTitle.getNumOfCommands() - 1;

        // [key pressed] UP:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');

        // commandNum: 0 -> 2
        testGameFrame.cmdTitle.setCommandNum(0);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: 1 -> 0
        testGameFrame.cmdTitle.setCommandNum(1);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: 2 -> 1
        testGameFrame.cmdTitle.setCommandNum(2);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(1, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: -2 -> 0 (exception)
        testGameFrame.cmdTitle.setCommandNum(-2);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: 5 -> 0 (exception)
        testGameFrame.cmdTitle.setCommandNum(5);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());
    }

    @Test
    public void downPressedInTitleState() throws IOException, AWTException {
        System.out.println("[downPressedInTitleState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.cmdTitle.getNumOfCommands() - 1;

        // [key pressed] DOWN:
        KeyEvent downKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        // commandNum: 0 -> 1
        testGameFrame.cmdTitle.setCommandNum(0);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(1, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: 1 -> 2
        testGameFrame.cmdTitle.setCommandNum(1);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: 2 -> 0
        testGameFrame.cmdTitle.setCommandNum(2);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: -2 -> 0 (exception)
        testGameFrame.cmdTitle.setCommandNum(-2);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(2, testGameFrame.cmdTitle.getCommandNum());

        // commandNum: 5 -> 0 (exception)
        testGameFrame.cmdTitle.setCommandNum(5);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());
    }

    @Test
    public void enterPressedInTitleState() throws IOException, AWTException {
        System.out.println("[enterPressedInTitleState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

//        int maxCommandNum = testGameFrame.numOfCommands - 1;

        // [key pressed] ENTER
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // enter key pressed with commandNum: 0 (title -> tutorial)
//        testGameFrame.gameState = testGameFrame.titleState;
        testGameFrame.state.toTitleState();
        testGameFrame.cmdTitle.setCommandNum(0);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.tutorialState, testGameFrame.state.getGameState());

        // enter key pressed with commandNum: 1 (title -> change level)
        testGameFrame.state.toTitleState();
        testGameFrame.cmdTitle.setCommandNum(1);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.state.getGameState(), testGameFrame.cmdTitle.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.changeLevelState, testGameFrame.state.getGameState());

        // enter key pressed with commandNum: -3 (exception)
        testGameFrame.state.toTitleState();
        testGameFrame.cmdTitle.setCommandNum(-3);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.titleState, testGameFrame.state.getGameState());

        // enter key pressed with commandNum: 5 (exception)
        testGameFrame.state.toTitleState();
        testGameFrame.cmdTitle.setCommandNum(5);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.titleState, testGameFrame.state.getGameState());
    }



    // 2. changeLevelState (UP, DOWN, ENTER)
    @Test
    public void upPressedInChangeLevelState() throws IOException {
        System.out.println("[upPressedInChangeLevelState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.cmdChangeLevel.getNumOfCommands() - 1;
        testGameFrame.state.toChangeLevelState();

        // [key pressed] UP:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');

        // commandNum: 0 -> 2
        testGameFrame.cmdChangeLevel.setCommandNum(0);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.cmdChangeLevel.getCommandNum());


        // commandNum: 1 -> 0
        testGameFrame.cmdChangeLevel.setCommandNum(1);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());

        // commandNum: -2 -> 2
        testGameFrame.cmdChangeLevel.setCommandNum(-2);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.cmdChangeLevel.getCommandNum());

        // commandNum: 5 -> 0
        testGameFrame.cmdChangeLevel.setCommandNum(5);
        testKey.keyPressed(upKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());

    }
    @Test
    public void downPressedInChangeLevelState() throws IOException {
        System.out.println("[downPressedInChangeLevelState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        int maxCommandNum = testGameFrame.cmdChangeLevel.getNumOfCommands() - 1;
        testGameFrame.state.toChangeLevelState();

        // [key pressed] DOWN:
        KeyEvent downKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        // commandNum: 0 -> 1
        testGameFrame.cmdChangeLevel.setCommandNum(0);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(1, testGameFrame.cmdChangeLevel.getCommandNum());


        // commandNum: 2 -> 0
        testGameFrame.cmdChangeLevel.setCommandNum(2);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());

        // commandNum: -2 -> 2
        testGameFrame.cmdChangeLevel.setCommandNum(-2);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(maxCommandNum, testGameFrame.cmdChangeLevel.getCommandNum());

        // commandNum: 5 -> 0
        testGameFrame.cmdChangeLevel.setCommandNum(5);
        testKey.keyPressed(downKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());
    }
    @Test
    public void enterPressedInChangeLevelState() throws IOException {
        System.out.println("[enterPressedInChangeLevelState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // ENTER pressed with commandNum: 0 gameLevel: 0 -> 0
        testGameFrame.cmdChangeLevel.setCommandNum(0);
        testGameFrame.state.toChangeLevelState();
        testGameFrame.settings.setGameLevel(testGameFrame.settings.levelEasy);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.titleState, testGameFrame.state.getGameState());
        Assertions.assertEquals(testGameFrame.settings.levelEasy, testGameFrame.settings.getGameLevel());
        Assertions.assertEquals(5, testGameFrame.settings.getNumOfVaccines());

        // ENTER pressed with commandNum: 0 gameLevel: 1 -> 0
        testGameFrame.cmdChangeLevel.setCommandNum(0);
        testGameFrame.state.toChangeLevelState();
        testGameFrame.settings.setGameLevel(testGameFrame.settings.levelIntermediate);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.titleState, testGameFrame.state.getGameState());
        Assertions.assertEquals(testGameFrame.settings.levelEasy, testGameFrame.settings.getGameLevel());
        Assertions.assertEquals(5, testGameFrame.settings.getNumOfVaccines());


        // ENTER pressed with commandNum: 1 gameLevel: 2 -> 1
        testGameFrame.cmdChangeLevel.setCommandNum(1);
        testGameFrame.state.toChangeLevelState();
        testGameFrame.settings.setGameLevel(testGameFrame.settings.levelChallenge);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.titleState, testGameFrame.state.getGameState());
        Assertions.assertEquals(testGameFrame.settings.levelIntermediate, testGameFrame.settings.getGameLevel());
        Assertions.assertEquals(7, testGameFrame.settings.getNumOfVaccines());

        // ENTER pressed with commandNum: 2 gameLevel: 0 -> 2
        testGameFrame.cmdChangeLevel.setCommandNum(2);
        testGameFrame.state.toChangeLevelState();
        testGameFrame.settings.setGameLevel(testGameFrame.settings.levelEasy);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.cmdChangeLevel.getCommandNum());
        Assertions.assertEquals(testGameFrame.state.titleState, testGameFrame.state.getGameState());
        Assertions.assertEquals(testGameFrame.settings.levelChallenge, testGameFrame.settings.getGameLevel());
        Assertions.assertEquals(10, testGameFrame.settings.getNumOfVaccines());

    }


    // 3. playState (UP, DOWN, LEFT, RIGHT, F, ESCAPE) *escape not used
    @Test
    public void upPressedInPlayState() throws IOException {
        System.out.println("[upPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toPlayState();

        // [key pressed] UP:
        KeyEvent upKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP,'Z');

        // [keyPressed()] pressedUp: false -> true
        testKey.pressedUp = false;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(true, testKey.pressedUp);

        // [keyPressed()] pressedUp: true -> true (exception)
        testKey.pressedUp = true;
        testKey.keyPressed(upKey);
        Assertions.assertEquals(true, testKey.pressedUp);

        // [keyReleased()] pressedUp: true -> false
        testKey.pressedUp = true;
        testKey.keyReleased(upKey);
        Assertions.assertEquals(false, testKey.pressedUp);

        // [keyReleased()] pressedUp: false -> false (exception)
        testKey.pressedUp = false;
        testKey.keyReleased(upKey);
        Assertions.assertEquals(false, testKey.pressedUp);
    }
    @Test
    public void downPressedInPlayState() throws IOException {
        System.out.println("[downPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toPlayState();

        // [key pressed] DOWN:
        KeyEvent downKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN,'Z');

        // [keyPressed()] pressedDown: false -> true
        testKey.pressedDown = false;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(true, testKey.pressedDown);

        // [keyPressed()] pressedDown: true -> true (exception)
        testKey.pressedDown = true;
        testKey.keyPressed(downKey);
        Assertions.assertEquals(true, testKey.pressedDown);

        // [keyReleased()] pressedDown: true -> false
        testKey.pressedDown = true;
        testKey.keyReleased(downKey);
        Assertions.assertEquals(false, testKey.pressedDown);

        // [keyReleased()] pressedDown: false -> false (exception)
        testKey.pressedDown = false;
        testKey.keyReleased(downKey);
        Assertions.assertEquals(false, testKey.pressedDown);
    }
    @Test
    public void leftPressedInPlayState() throws IOException {
        System.out.println("[leftPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toPlayState();


        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // [keyPressed()] pressedLeft: false -> true
        testKey.pressedDown = false;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(true, testKey.pressedLeft);

        // [keyPressed()] pressedLeft: true -> true (exception)
        testKey.pressedDown = true;
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(true, testKey.pressedLeft);

        // [keyReleased()] pressedLeft: true -> false
        testKey.pressedDown = true;
        testKey.keyReleased(leftKey);
        Assertions.assertEquals(false, testKey.pressedLeft);

        // [keyReleased()] pressedLeft: false -> false (exception)
        testKey.pressedDown = false;
        testKey.keyReleased(leftKey);
        Assertions.assertEquals(false, testKey.pressedLeft);
    }
    @Test
    public void rightPressedInPlayState() throws IOException {
        System.out.println("[rightPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toPlayState();


        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // [keyPressed()] pressedRight: false -> true
        testKey.pressedRight = false;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(true, testKey.pressedRight);

        // [keyPressed()] pressedRight: true -> true (exception)
        testKey.pressedRight = true;
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(true, testKey.pressedRight);

        // [keyReleased()] pressedRight: true -> false
        testKey.pressedRight= true;
        testKey.keyReleased(rightKey);
        Assertions.assertEquals(false, testKey.pressedRight);

        // [keyReleased()] pressedRight: false -> false (exception)
        testKey.pressedRight= false;
        testKey.keyReleased(rightKey);
        Assertions.assertEquals(false, testKey.pressedRight);
    }
    @Test
    public void fPressedInPlayState() throws IOException {
        System.out.println("[fPressedInPlayState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toPlayState();

        // [key pressed] F:
        KeyEvent fKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_F,'Z');

        // [keyPressed()] pressF: false -> true
        testKey.pressF = false;
        testKey.keyPressed(fKey);
        Assertions.assertEquals(true, testKey.pressF);

        // [keyPressed()] pressF: true -> true (exception)
        testKey.pressF = true;
        testKey.keyPressed(fKey);
        Assertions.assertEquals(true, testKey.pressF);

        // [keyReleased()] pressF: true -> false
        testKey.pressF = true;
        testKey.keyReleased(fKey);
        Assertions.assertEquals(false, testKey.pressF);

        // [keyReleased()] pressF: false -> false (exception)
        testKey.pressF = false;
        testKey.keyReleased(fKey);
        Assertions.assertEquals(false, testKey.pressF);
    }

    // 4. tutorialState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInTutorialState() throws IOException {
        System.out.println("[leftPressedInTutorialState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);


        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // tutorialState page: 0 -> 0 (no change)
        testGameFrame.state.toTutorialState();
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(0, testGameFrame.state.getPage());
        Assertions.assertEquals(testGameFrame.state.tutorialState, testGameFrame.state.getGameState());

        // tutorialState page: 1 -> 0
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(1, testGameFrame.state.getPage());
        Assertions.assertEquals(testGameFrame.state.tutorialState, testGameFrame.state.getGameState());
    }
    @Test
    public void rightPressedInTutorialState() throws IOException {
        System.out.println("[rightPressedInTutorialState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toTutorialState();

        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // tutorialState: 0 -> 1
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(1, testGameFrame.state.getPage());
        Assertions.assertEquals(testGameFrame.state.tutorialState, testGameFrame.state.getGameState());

    }
    @Test
    public void enterPressedInTutorialState() throws IOException {
        System.out.println("[enterPressedInTutorialState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toTutorialState();

        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // tutorialState: 0 -> 0
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.state.getPage());
        Assertions.assertEquals(testGameFrame.state.narrationState, testGameFrame.state.getGameState());

    }


    // 5. narrationState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInNarrationState() throws IOException {
        System.out.println("[leftPressedInNarrationState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toNarrationState();

        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // narrationState: 0 -> 0
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(0, testGameFrame.state.getPage());
        Assertions.assertEquals(testGameFrame.state.narrationState, testGameFrame.state.getGameState());

    }
    @Test
    public void rightPressedInNarrationState() throws IOException {
        System.out.println("[rightPressedInNarrationState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toNarrationState();

        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // narrationState: 0 -> 1
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(1, testGameFrame.state.getPage());
        Assertions.assertEquals(testGameFrame.state.narrationState, testGameFrame.state.getGameState());


    }
    @Test
    public void enterPressedInNarrationState() throws IOException {
        System.out.println("[enterPressedInNarrationState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toNarrationState();

        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // narrationState: 0 -> play
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(0, testGameFrame.state.getPage());
        Assertions.assertEquals(testGameFrame.state.playState, testGameFrame.state.getGameState());

    }


    // 6. endState (LEFT, RIGHT, ENTER)
    @Test
    public void leftPressedInEndState() throws IOException {
        System.out.println("[leftPressedInEndState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toEndState();

        // [key pressed] LEFT:
        KeyEvent leftKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');

        // commandNum: 0 -> 1
        testGameFrame.cmdEnd.setCommandNum(0);
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(1, testGameFrame.cmdEnd.getCommandNum());

        // commandNum: 1 -> 0
        testGameFrame.cmdEnd.setCommandNum(1);
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(0, testGameFrame.cmdEnd.getCommandNum());

        // commandNum: 7 -> 0 (exception)
        testGameFrame.cmdEnd.setCommandNum(7);
        testKey.keyPressed(leftKey);
        Assertions.assertEquals(0, testGameFrame.cmdEnd.getCommandNum());
    }
    @Test
    public void rightPressedInEndState() throws IOException {
        System.out.println("[rightPressedInEndState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);

        testGameFrame.state.toEndState();

        // [key pressed] RIGHT:
        KeyEvent rightKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT,'Z');

        // commandNum: 0 -> 1
        testGameFrame.cmdEnd.setCommandNum(0);
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(1, testGameFrame.cmdEnd.getCommandNum());

        // commandNum: 1 -> 0
        testGameFrame.cmdEnd.setCommandNum(1);
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(0, testGameFrame.cmdEnd.getCommandNum());

        // commandNum: 7 -> 0 (exception)
        testGameFrame.cmdEnd.setCommandNum(7);
        testKey.keyPressed(rightKey);
        Assertions.assertEquals(0, testGameFrame.cmdEnd.getCommandNum());
    }
    @Test
    public void enterPressedInEndState() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        System.out.println("[enterPressedInEndState/inputKeyTest]");

        GameFrame testGameFrame = new GameFrame(16, 12, 48);
        inputKey testKey = new inputKey(testGameFrame);


        // [key pressed] ENTER:
        KeyEvent enterKey = new KeyEvent(testGameFrame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_ENTER,'Z');

        // [retry: return to tutorial screen, restart game with same level]
        // commandNum: 0
        // gameLevel: intermediate
        // numOfVaccines: 7
        // gameResult: fail
        testGameFrame.state.toEndState();
        testGameFrame.cmdEnd.setCommandNum(0);
        testGameFrame.settings.setGameLevel(testGameFrame.settings.levelIntermediate);
        testGameFrame.gameResult = testGameFrame.fail;
        testGameFrame.playBGM(testGameFrame.track3_playState);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.state.tutorialState, testGameFrame.state.getGameState());
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());
        Assertions.assertEquals(7, testGameFrame.settings.getNumOfVaccines());
        Assertions.assertEquals(testGameFrame.settings.levelIntermediate, testGameFrame.settings.getGameLevel());
        Assertions.assertEquals(-1, testGameFrame.musicTrack);

        // [return to title screen]
        // commandNum: 1
        // gameLevel: challenge
        // numOfVaccines: 10
        // gameResult: win
        testGameFrame.state.toEndState();
        testGameFrame.cmdEnd.setCommandNum(1);
        testGameFrame.settings.setGameLevel(testGameFrame.settings.levelChallenge);
        testGameFrame.gameResult = testGameFrame.win;
        testGameFrame.playBGM(testGameFrame.track3_playState);
        testKey.keyPressed(enterKey);
        Assertions.assertEquals(testGameFrame.state.titleState, testGameFrame.state.getGameState());
        Assertions.assertEquals(0, testGameFrame.cmdTitle.getCommandNum());
        Assertions.assertEquals(5, testGameFrame.settings.getNumOfVaccines());
        Assertions.assertEquals(testGameFrame.settings.levelEasy, testGameFrame.settings.getGameLevel());
        Assertions.assertEquals(-1, testGameFrame.musicTrack);

    }




}
