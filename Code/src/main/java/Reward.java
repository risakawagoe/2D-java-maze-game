import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * class to implement and manage reward objects
 */
abstract class Reward extends GameObject{

  /**
   * set appear status
   * @param t
   */
  public void setAppear(boolean t){
    appearStatus = t;
  }

  /**
   * get appeat status
   * @return
   */
  public boolean getAppear(){
    return appearStatus;
  }

}
