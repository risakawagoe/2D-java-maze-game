import jdk.jshell.Snippet;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class GameFrameTest {

    //Create GameFrame class to test
    private int colm = 16;
    private int rows = 12;
    private int pacSize = 48; //industry standard 48x48

    GameFrame gfTest = new GameFrame(colm, rows, pacSize);
    inputKey key = new inputKey(gfTest);

    public GameFrameTest() throws IOException {
    }

    /**
     * Test correct return of cellSize variable
     */
    @Test
    public void getCellSizeTest(){
        Assert.assertEquals(gfTest.getCellSize(), 48);
    }

    /**
     *Test for correct set of values when game is running inside thread
     */
    @Test
    public void runGameTest(){
        gfTest.mc.setVaccines(20);

        gfTest.runGame();

        Assert.assertEquals(gfTest.gameResult,0 );
        Assert.assertEquals(gfTest.state.getGameState(), 0);

        gfTest.mc.x = 30;
        gfTest.mc.y = 540;

        gfTest.runGame();

        Assert.assertEquals(gfTest.gameResult,1 );
        Assert.assertEquals(gfTest.state.getGameState(), 3);

        gfTest.state.toTitleState();
        gfTest.gameResult = 0;
        gfTest.mc.setVaccines(5);
        Assert.assertEquals(gfTest.gameResult,0 );
        Assert.assertEquals(gfTest.state.getGameState(), 0);

        gfTest.state.toTitleState();
        gfTest.gameResult = 0;
        gfTest.mc.setVaccines(1);
        Assert.assertEquals(gfTest.gameResult,0 );
        Assert.assertEquals(gfTest.state.getGameState(), 0);

    }

    /**
     * Test for correct reset of character positioning
     */
    @Test
    public void restGameTest(){
        gfTest.mc.setHP(2);
        gfTest.mc.setVaccines(2);

        //call method to test
        gfTest.resetGame(0);

        //main character test
        Assert.assertEquals(gfTest.mc.getHP(), 1);
        Assert.assertEquals(gfTest.mc.getVaccines(), 0);
        Assert.assertEquals(gfTest.mc.x, 365);
        Assert.assertEquals(gfTest.mc.y, 0);

    }

    /**
     * Test for correct game status of music when it is paused
     */
    @Test
    public void playBGMTest() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        gfTest.musicState = 0;
        gfTest.playBGM(-1);
        Assert.assertEquals(gfTest.musicState, 0);
        gfTest.musicState = 0;
        gfTest.playBGM(4);
        Assert.assertEquals(gfTest.musicState, 1);
    }

    /**
     * Test for correct game status of music when it is playing
     */
    @Test
    public void stopBGMTest(){
        gfTest.musicState = 1;
        gfTest.stopBGM(1);
        Assert.assertEquals(gfTest.musicState, 0);
    }
}
