import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TimerClockTest {

    private TimerClock tcTest = new TimerClock();

    /**
     * Test correct image import in Class constructor
     */
    @Test
    public void getTimerImgTest() throws IOException {
        BufferedImage testImg = ImageIO.read(new File("src/main/java/picture/GUI_image/Time_panel.png"));
        String actImg = (tcTest.timerImg).toString();
        Assertions.assertEquals(actImg.substring(actImg.indexOf("type")), testImg.toString().substring((testImg.toString()).indexOf("type")));
    }

    /**
     * Test for correct timer start values
     */
    @Test
    public void startTimerTest(){
        tcTest.startTimer();
       Assertions.assertEquals(tcTest.getSecond(), 0);
        Assertions.assertEquals(tcTest.getMinute(), 0);
        Assertions.assertEquals(tcTest.getFormattedMinute(), "00");
        Assertions.assertEquals(tcTest.getFormattedSecond(), "00");
    }

    /**
     * Test correct return of Seconds
     */
    @Test
    public void getSecondTest(){
        Assertions.assertEquals(tcTest.getSecond(), 0);
    }

    /**
     * Test for correct time increment
     */
    @Test
    public void increaseTimeTest(){
        tcTest.increaseTime();
        Assertions.assertEquals(tcTest.getSecond(), 1);
        Assertions.assertEquals(tcTest.getMinute(), 0);
        Assertions.assertEquals(tcTest.getFormattedSecond(), "01");
        Assertions.assertEquals(tcTest.getFormattedMinute(), null);

        for (int i=0; i<59; i++){
            tcTest.increaseTime();
        }

        Assertions.assertEquals(tcTest.getSecond(), 0);
        Assertions.assertEquals(tcTest.getMinute(), 1);
        Assertions.assertEquals(tcTest.getFormattedSecond(), "00");
        Assertions.assertEquals(tcTest.getFormattedMinute(), "01");

        tcTest.increaseTime();
        Assertions.assertEquals(tcTest.getSecond(), 1);
        Assertions.assertEquals(tcTest.getMinute(), 1);
        Assertions.assertEquals(tcTest.getFormattedSecond(), "01");
        Assertions.assertEquals(tcTest.getFormattedMinute(), "01");
    }
}
