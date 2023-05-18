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

public class ZombieTest {
    @Test
    public void testSetDefaultValue() throws IOException {
        Zombie zombie = new Zombie();
        zombie.getZombieImages();

        BufferedImage[] img = new BufferedImage[16];

        img[0] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp1.png"));
        img[1] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp2.png"));
        img[2] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp3.png"));
        img[3] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp4.png"));

        img[4] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown1.png"));
        img[5] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown2.png"));
        img[6] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown3.png"));
        img[7] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown4.png"));

        img[8] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight1.png"));
        img[9] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight2.png"));
        img[10] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight3.png"));
        img[11] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight4.png"));

        img[12] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft1.png"));
        img[13] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft2.png"));
        img[14] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft3.png"));
        img[15] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft4.png"));
        for(int i = 0; i < 16; i++){
            String[] zombieImg = null;
            zombieImg = zombie.images[i].toString().split(" ");
            for(int j = 1; j < zombieImg.length; j++)
                Assertions.assertEquals(img[i].toString().split(" ")[j], zombieImg[j]);
        }
    }

    @Test
    public void testUpdateZombie() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);

        Zombie zombie = new Zombie(gf, 15, 200, mc);

        zombie.update();
        Assert.assertEquals(15, zombie.x);
        Assert.assertEquals(200, zombie.y);
    }

    @Test
    public void testClosedToAim() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);

        // Closed to
        int[] startPoint = {50,250};
        mc.setDefaultValue(startPoint);
        Zombie zombie = new Zombie(gf, 15, 200, mc);
        boolean result = zombie.closedToAim();
        Assert.assertTrue(result);

        // Not closed to
        int[] startPoint2 = {245,360};
        mc.setDefaultValue(startPoint2);
        Zombie zombie2 = new Zombie(gf, 15, 200, mc);
        boolean result2 = zombie2.closedToAim();
        Assert.assertFalse(result2);
    }

    @Test
    public void testCheck(){
        Zombie zombie = new Zombie();
        zombie.x = 5;
        zombie.y = 10;
        zombie.height = 10;
        zombie.width = 10;
        zombie.hitArea = new Rectangle(5,10,5,5);

        Zombie zombie2 = new Zombie();
        zombie2.x = 100;
        zombie2.y = 100;
        zombie2.height = 10;
        zombie2.width = 10;
        zombie2.hitArea = new Rectangle(100,100,5,5);

        Rectangle rec = new Rectangle(5,5,50,50);

        Assert.assertTrue(zombie.check(rec));
        Assert.assertFalse(zombie2.check(rec));
    }

    @Test
    void drawTest() throws InterruptedException, IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48;
        GameFrame gf = new GameFrame(colm, rows, pacSize);
        inputKey key = new inputKey(gf);
        MainCharacter mc = new MainCharacter(gf, key);

        // Closed to
        int[] startPoint = {50,250};
        mc.setDefaultValue(startPoint);
        Zombie zombie = new Zombie(gf, 10, 10, mc);
        class drawTest extends JPanel {
            private Frame frame = new Frame("BadSurvivorTest");
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                zombie.draw(g2);
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
