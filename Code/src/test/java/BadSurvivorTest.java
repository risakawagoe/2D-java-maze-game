import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BadSurvivorTest {
    @Test
    public void testGetSurvivorImages() throws IOException {
        BadSurvivor badSurvivor = new BadSurvivor();
        badSurvivor.getSurvivorImages();

        BufferedImage[] img = new BufferedImage[5];

        img[0] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToUp.png"));
        img[1] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToDown.png"));
        img[2] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToRight.png"));
        img[3] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToLeft.png"));
        img[4] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_badPerson.png"));

        for(int i = 0; i < 5; i++) {
            String[] badSurvivorImg = null;
            badSurvivorImg = badSurvivor.images[i].toString().split(" ");
            for (int j = 1; j < badSurvivorImg.length; j++)
                Assertions.assertEquals(img[i].toString().split(" ")[j], badSurvivorImg[j]);
        }
    }

    @Test
    public void testSetDefaultValue(){
        BadSurvivor badSurvivor = new BadSurvivor();
        badSurvivor.getSurvivorImages();
        badSurvivor.setDefaultValue(10,20);

        Assert.assertEquals("down",badSurvivor.direction);
        Assert.assertEquals(10,badSurvivor.x);
        Assert.assertEquals(20,badSurvivor.y);
        Assert.assertFalse(badSurvivor.speakMessageShow);
        Assert.assertEquals(10, badSurvivor.message_x);
        int width = badSurvivor.images[4].getWidth(), height = badSurvivor.images[4].getHeight();
        Assert.assertEquals(20 - height, badSurvivor.message_y);
        Assert.assertEquals(width, badSurvivor.message_width);
        Assert.assertEquals(height,badSurvivor.message_height);
    }

    @Test
    public void testSpeak1() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);
        int[] startPoint = {50,250};
        mc.setDefaultValue(startPoint);
        BadSurvivor badSurvivor = new BadSurvivor(gf, key, mc, 10, 10);

        Assert.assertTrue(badSurvivor.appearStatus);
        Assert.assertEquals("down", badSurvivor.direction);

        // if MainCharacter is closed to bad survivor at right and press F
        int[] startPoint1 = {15, 10};
        mc.setDefaultValue(startPoint1);
        key.pressF = true;
        badSurvivor.update();
        Assert.assertEquals("right", badSurvivor.direction);
        Assert.assertTrue(badSurvivor.appearStatus); // It is still true because it should wait for the speakTimeCounter >100

        key.pressF = false;
        badSurvivor.speakMessageShow = true;
        badSurvivor.speakTimeCounter = 100;
        badSurvivor.update();
        Assert.assertFalse(badSurvivor.appearStatus); // It should be false right now
    }

    @Test
    public void testSpeak2() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);
        int[] startPoint = {50,250};
        mc.setDefaultValue(startPoint);
        BadSurvivor badSurvivor = new BadSurvivor(gf, key, mc, 10, 10);

        Assert.assertTrue(badSurvivor.appearStatus);
        Assert.assertEquals("down", badSurvivor.direction);

        // if MainCharacter is not closed to bad survivor at right and press F
        int[] startPoint2 = {150, 10};
        mc.setDefaultValue(startPoint2);
        key.pressF = true;
        badSurvivor.update();
        Assert.assertEquals("down", badSurvivor.direction);
        Assert.assertTrue(badSurvivor.appearStatus);
    }

    @Test
    public void testSpeak3() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);
        int[] startPoint = {50,250};
        mc.setDefaultValue(startPoint);
        BadSurvivor badSurvivor = new BadSurvivor(gf, key, mc, 10, 10);

        Assert.assertTrue(badSurvivor.appearStatus);
        Assert.assertEquals("down", badSurvivor.direction);

        // if MainCharacter is closed to bad survivor at left but not press F
        int[] startPoint3 = {0, 10};
        mc.setDefaultValue(startPoint3);
        key.pressF = false;
        badSurvivor.update();
        Assert.assertEquals("down", badSurvivor.direction);
        Assert.assertTrue(badSurvivor.appearStatus);
    }

    @Test
    void drawTest() throws InterruptedException, IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);
        int[] startPoint = {50,250};
        mc.setDefaultValue(startPoint);
        BadSurvivor badSurvivor = new BadSurvivor(gf, key, mc, 10, 10);

        class drawTest extends JPanel {
            private Frame frame = new Frame("BadSurvivorTest");
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                badSurvivor.draw(g2);
            }

            public void init() {
                drawTest Draw = new drawTest();
                Draw.repaint();
                Draw.setPreferredSize(new Dimension(30,70));
                frame.add(Draw);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                frame.pack();
                frame.setVisible(true);
            }
        }
        drawTest test = new drawTest();
        test.init();
        Thread.sleep(1000);
    }
}
