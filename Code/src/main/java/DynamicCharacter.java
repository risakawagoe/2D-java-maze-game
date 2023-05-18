import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * class to manage dynamic characters: Zombies and MainCharacter
 */
public abstract class DynamicCharacter extends GameObject{
    /**
     * The speed that the character move
     * The x,y means the position of the Dynamic character
     * The width and height is the image's size
     */
    public int speed;
//
//    public BufferedImage up1, up2, up3, up4,
//                         left1, left2, left3, left4,
//                         right1, right2, right3, right4,
//                         down1, down2, down3, down4;

    public int spriteCounter = 0;
    public int spriteNum = 1; // 1--image1, 2--image2, 3--image3, 4--image4
}
