import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * class to manage static objects
 */
public abstract class StaticCharacter extends GameObject {
    /**
     * The x,y means the position of the static character
     * The message_x, message_y means the position of the message box of this static character
     * The width and height is the image's size
     * The message_width and message_height is the message box image's size
     */
    public int message_x, message_y, message_width, message_height;
    public MainCharacter mc;
    public boolean speakMessageShow;
    public int speakTimeCounter;
//    public BufferedImage up, left, right, down, message_image, message_image_incomplete;

    /**
     * read and import character image
     */
    public void getSurvivorImages(){
        try{
            images[0] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToUp.png"));
            images[1] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToDown.png"));
            images[2] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToRight.png"));
            images[3] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/staticCharacter_faceToLeft.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        update();
        switch (direction){
            case "up":
                image = images[0];
                break;
            case "down":
                image = images[1];
                break;
            case "right":
                image = images[2];
                break;
            case "left":
                image = images[3];
                break;
        }
        g2.drawImage(image,x,y,image.getWidth(),image.getHeight(),null);
    }
}
