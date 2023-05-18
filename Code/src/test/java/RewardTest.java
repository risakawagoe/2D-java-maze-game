import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class RewardTest {
  private Reward reward;
  private MainCharacter a;
  private GameFrame gameFrame;
  private inputKey key;

  @BeforeEach
  void setUp() throws IOException {
    gameFrame = new GameFrame(1,1,1);
    key = null;
    a = new MainCharacter(gameFrame,key);
    reward = new Food(gameFrame,20,20);
  }


  @Test
  void setAppearTest() {
    boolean original = true;
    reward.setAppear(original);
    assertEquals(original,reward.getAppear());

    original = false;
    reward.setAppear(original);
    assertEquals(original,reward.getAppear());
  }

  @Test
  void getAppearTest() {
    boolean original = true;
    reward.setAppear(original);
    assertEquals(original,reward.getAppear());

    original = false;
    reward.setAppear(original);
    assertEquals(original,reward.getAppear());
  }

  @Test
  void draw() throws InterruptedException {
    class drawTest extends JPanel {
      private Frame frame = new Frame("test");
      public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        reward.draw(g2);
      }

      public void init() {
        drawTest Draw = new drawTest();
        Draw.repaint();
        Draw.setPreferredSize(new Dimension(20,20));
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

  @Test
  void check() {
    Rectangle mc = new Rectangle(a.x,a.y,a.width,a.height);
    boolean original = reward.hitArea.intersects(mc);
    assertEquals(original,reward.check(mc));
  }
}
