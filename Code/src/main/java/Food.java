import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Class to manage Food Reward which increases player's HP
 */
public class Food extends Reward {
  private int HP_increase;

  /**
   * Class Constructor
   * @param frame
   * @param x
   * @param y
   * @throws IOException
   */
  public Food(GameFrame frame,int x,int y) throws IOException {
    setFrame(frame);
    this.x = x;
    this.y = y;
    images[0] = ImageIO.read(new File("src/main/java/picture/Rewards/Food2_24x24.png"));
    this.width = 25;
    this.height = 25;
    hitArea = new Rectangle(this.x,this.y-10,this.width,this.height);
  }

  /**
   * increase Player HP
   * @param m
   */
  public void increaseHP(MainCharacter m){
    m.setHP(m.getHP()+1);
  }

  /**
   * Draw Food
   * @param g
   */
  @Override
  public void draw(Graphics2D g){
    if(appearStatus)
      g.drawImage(images[0],x,y,images[0].getWidth(), images[0].getHeight(), null);
  }

  @Override
  public void update() {

  }

}
