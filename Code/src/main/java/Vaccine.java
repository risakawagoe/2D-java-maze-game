import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * class to implement and manage vaccine rewards
 */
public class Vaccine extends Reward {
  public int coordinates[][] = new int[10][2];
  private int size = 0;
  private void addCoordinates(int x, int y) {
    if(size < 10) {
      coordinates[size][0] = x;
      coordinates[size][1] = y;
    }
  }
  public int getNumOfVaccines() {
    return size;
  }

  /**
   * increase vaccine count that player has collected
   * @param m
   */
  public void increaseVaccine(MainCharacter m) {
    m.setVaccines(m.getVaccines() + 1);
  }


  /**
   * class constructor
   * @param frame
   * @param x
   * @param y
   * @throws IOException
   */
  public Vaccine(GameFrame frame, int x, int y) throws IOException {
    setFrame(frame);
    this.x = x;
    this.y = y;
    images[0] = ImageIO.read(new File("src/main/java/picture/Rewards/Vaccine.png"));
    this.width = 25;
    this.height = 25;
    hitArea = new Rectangle(this.x,this.y-10,this.width,this.height);

    // set vaccine coordinates
    addCoordinates(218,138);
    addCoordinates(485,48);
    addCoordinates(383,382);
    addCoordinates(340,512);
    addCoordinates(386,159);
    addCoordinates(102,383);
    addCoordinates(52,240);
    addCoordinates(330,353);
    addCoordinates(645,217);
    addCoordinates(695,453);
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
