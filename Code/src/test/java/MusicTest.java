import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MusicTest {
    // test setFile(int i)
    @Test
    public void testSetFileWithZero() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        System.out.println("testSetFileWithZero");
        Music testBGM = new Music();
        boolean result = testBGM.setFile(0);

        Assertions.assertTrue(testBGM.clip[0].isOpen());
        Assertions.assertTrue(result);
    }
    @Test
    public void testSetFileWithThree() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        System.out.println("testSetFileWithThree");
        Music testBGM = new Music();
        boolean result = testBGM.setFile(3);

        Assertions.assertTrue(testBGM.clip[3].isOpen());
        Assertions.assertTrue(result);
    }
    @Test
    public void testSetFileWithFour() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        System.out.println("testSetFileWithFour");
        Music testBGM = new Music();
        boolean result = testBGM.setFile(4);

        Assertions.assertTrue(testBGM.clip[4].isOpen());
        Assertions.assertTrue(result);
    }
    @Test
    public void testSetFileWithInvalidTrackNumberFive() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        System.out.println("testSetFileWithInvalidTrackNumberFive");
        Music testBGM = new Music();
        boolean result = testBGM.setFile(5);// invalid (max index is 4)

        Assertions.assertFalse(result);
    }
    @Test
    public void testSetFileWithInvalidTrackNumberNegativeOne() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        System.out.println("testSetFileWithInvalidTrackNumberNegativeOne");
        Music testBGM = new Music();
        boolean result = testBGM.setFile(-1);// invalid (max index is 4)

        Assertions.assertFalse(result);
    }

    // test play(int i)
    @Test
    public void testPlayWithThree() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
//        System.out.println("testPlayWithThree");
        Music testBGM = new Music();

        File file = new File("src/main/java/music/bgm_mysteriousSound.wav");
        AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
        testBGM.clip[3] = AudioSystem.getClip();
        testBGM.clip[3].open(bgm);

        Assertions.assertTrue(testBGM.play(3));
    }
    @Test
    public void testPlayWithFour() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("testPlayWithFour");
        Music testBGM = new Music();

        File file = new File("src/main/java/music/bgm_darkCaveSound.wav");
        AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
        testBGM.clip[4] = AudioSystem.getClip();
        testBGM.clip[4].open(bgm);

        Assertions.assertTrue(testBGM.play(4));
    }
    @Test
    public void testPlayWithInvalidTrackNumber() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("testPlayWithInvalidTrackNumber");
        Music testBGM = new Music();
        boolean result = testBGM.play(5);
        Assertions.assertFalse(result);
    }
    @Test
    public void testPlayWithClipNotSet() {
//        System.out.println("testPlayWithClipNotSet");
        Music testBGM = new Music();
        boolean result = testBGM.play(3);
        Assertions.assertFalse(result);
    }

    // test loop(int i)
    @Test
    public void testLoopWithZero() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("testLoopWithZero");
        Music testBGM = new Music();

        File file = new File("src/main/java/music/Click_music_1.wav");
        AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
        testBGM.clip[0] = AudioSystem.getClip();
        testBGM.clip[0].open(bgm);
        testBGM.clip[0].start();

        Assertions.assertTrue(testBGM.loop(0));
    }
    @Test
    public void testLoopWithInvalidTrackNumber() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("testLoopWithInvalidTrackNumber");
        Music testBGM = new Music();
        Assertions.assertFalse(testBGM.loop(5));
    }

    // test stop(int i)
    @Test
    public void testStopWithFour() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
//        System.out.println("testStopWithFour");
        Music testBGM = new Music();

        File file = new File("src/main/java/music/bgm_darkCaveSound.wav");
        AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
        testBGM.clip[4] = AudioSystem.getClip();
        testBGM.clip[4].open(bgm);
        testBGM.clip[4].start();
        testBGM.clip[4].loop(Clip.LOOP_CONTINUOUSLY);

        Assertions.assertTrue(testBGM.stop(4));
    }
    @Test
    public void testStopWithInvalidTrackNumber() {
//        System.out.println("testStopWithInvalidTrackNumber");
        Music testBGM = new Music();
        Assertions.assertFalse(testBGM.stop(5));
    }

}