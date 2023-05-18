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

public class KindSurvivorTest {
    @Test
    public void testGetSurvivorImages() throws IOException {
        KindSurvivor kindSurvivor = new KindSurvivor();
        kindSurvivor.getSurvivorImages();

        BufferedImage[] img = new BufferedImage[6];

        img[0] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToUp.png"));
        img[1] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToDown.png"));
        img[2] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToRight.png"));
        img[3] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToLeft.png"));
        img[4] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_goodPerson.png"));
        img[5] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_goodPerson2.png"));
        for(int i = 0; i < 6; i++) {
            String[] kindSurvivorImg = null;
            kindSurvivorImg = kindSurvivor.images[i].toString().split(" ");

            for (int j = 1; j < kindSurvivorImg.length; j++)
                Assertions.assertEquals(img[i].toString().split(" ")[j], kindSurvivorImg[j]);
        }
    }

    @Test
    public void testSetDefaultValue(){
        KindSurvivor kindSurvivor = new KindSurvivor();
        kindSurvivor.getSurvivorImages();
        kindSurvivor.setDefaultValue(10,30, 100, 100);

        Assert.assertEquals("down",kindSurvivor.direction);
        Assert.assertEquals(10,kindSurvivor.x);
        Assert.assertEquals(30,kindSurvivor.y);
        Assert.assertFalse(kindSurvivor.speakMessageShow);
        Assert.assertEquals(10, kindSurvivor.message_x);
        int width = kindSurvivor.images[4].getWidth(), height = kindSurvivor.images[4].getHeight();
        Assert.assertEquals(30 - height, kindSurvivor.message_y);
        Assert.assertEquals(width, kindSurvivor.message_width);
        Assert.assertEquals(height,kindSurvivor.message_height);
    }

    @Test
    public void testSpeak1() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);
        int[] startPoint = {30,135};
        mc.setDefaultValue(startPoint);
        int[][] board = {
                {1,1,1,1},
                {1,0,0,1},
                {1,0,0,1},
                {1,1,1,1},
        };
        int doorX = 3, doorY = 3;
        KindSurvivor kindSurvivor = new KindSurvivor(gf, key, mc, 1, 1, board, doorX, doorY);

        Assert.assertTrue(kindSurvivor.appearStatus);
        Assert.assertEquals("down", kindSurvivor.direction);

        // if MainCharacter who did not collect all vaccines is closed to bad survivor at right and press F
        int[] startPoint1 = {20, 0};
        mc.setDefaultValue(startPoint1);
        key.pressF = true;
        kindSurvivor.update();
        Assert.assertEquals("right", kindSurvivor.direction);
        Assert.assertTrue(kindSurvivor.appearStatus);

        // if MainCharacter collected all vaccines and is closed to bad survivor at right and press F
        key.pressF = false;
        kindSurvivor.speakMessageShow = true;
        kindSurvivor.speakTimeCounter = 200;
        mc.setVaccines(5);
//        gf.numOfVaccines = 5;
        kindSurvivor.update();
        Assert.assertFalse(kindSurvivor.appearStatus); // It should be false right now
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
        int[][] board = {
                {1,1,1,1},
                {1,0,0,1},
                {1,0,0,1},
                {1,1,1,1},
        };
        int doorX = 3, doorY = 3;
        KindSurvivor kindSurvivor = new KindSurvivor(gf, key, mc, 1, 1, board, doorX, doorY);

        Assert.assertTrue(kindSurvivor.appearStatus);
        Assert.assertEquals("down", kindSurvivor.direction);

        // if MainCharacter is not closed to bad survivor at right and press F
        int[] startPoint2 = {150, 10};
        mc.setDefaultValue(startPoint2);
        key.pressF = true;
        kindSurvivor.update();
        Assert.assertEquals("down", kindSurvivor.direction);
        Assert.assertTrue(kindSurvivor.appearStatus);
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
        int[][] board = {
                {1,1,1,1},
                {1,0,0,1},
                {1,0,0,1},
                {1,1,1,1},
        };
        int doorX = 3, doorY = 3;
        KindSurvivor kindSurvivor = new KindSurvivor(gf, key, mc, 1, 1, board, doorX, doorY);

        Assert.assertTrue(kindSurvivor.appearStatus);
        Assert.assertEquals("down", kindSurvivor.direction);

        // if MainCharacter is closed to bad survivor at left but not press F
        int[] startPoint3 = {0, 10};
        mc.setDefaultValue(startPoint3);
        key.pressF = false;
        kindSurvivor.update();
        Assert.assertEquals("down", kindSurvivor.direction);
        Assert.assertTrue(kindSurvivor.appearStatus);
    }

    @Test
    public void testOpenDoor() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);
        int[] startPoint = {50,250};
        mc.setDefaultValue(startPoint);
        int[][] board = {
                {1,1,1,1},
                {1,0,0,1},
                {1,0,0,1},
                {1,1,1,1},
        };
        int doorX = 3, doorY = 3;
        KindSurvivor kindSurvivor = new KindSurvivor(gf, key, mc, 1, 1, board, doorX, doorY);
        Assert.assertEquals(1, kindSurvivor.getMap()[3][3]);
        kindSurvivor.openDoor();
        Assert.assertEquals(0, kindSurvivor.getMap()[3][3]);
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
        int[][] board = {
                {1,1,1,1},
                {1,0,0,1},
                {1,0,0,1},
                {1,1,1,1},
        };
        int doorX = 3, doorY = 3;
        KindSurvivor kindSurvivor = new KindSurvivor(gf, key, mc, 1, 1, board, doorX, doorY);
        class drawTest extends JPanel {
            private Frame frame = new Frame("BadSurvivorTest");
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                kindSurvivor.draw(g2);
            }

            public void init() {
                drawTest Draw = new drawTest();
                Draw.repaint();
                Draw.setPreferredSize(new Dimension(20,70));
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
