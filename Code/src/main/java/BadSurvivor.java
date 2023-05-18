import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class for enemy static character which lowers Main Character's HP when in contact
 */
public class BadSurvivor extends StaticCharacter{
    /**
     * Class Constructor without parameters
     */
    public BadSurvivor(){
        // Just for test
    }

    /**
     * Class Constructor
     * @param gameFrame
     * @param key - Keyboard input
     * @param mc
     * @param x
     * @param y
     */
    public BadSurvivor(GameFrame gameFrame, inputKey key, MainCharacter mc, int x, int y){
        this.frame = gameFrame;
        this.key = key;
        this.mc = mc;
        this.width = 30;
        this.height = 30;

        getSurvivorImages();
        setDefaultValue(x,y);
    }

    /**
     * set starting point for static enemies
     * set up starting values for their dialog boxes
     * @param x
     * @param y
     */
    public void setDefaultValue(int x, int y){
        this.direction = "down";
        this.x = x;
        this.y = y;
        this.speakMessageShow = false;
        this.message_x = x;
        this.message_y = y - images[4].getHeight();
        this.message_width = images[4].getWidth();
        this.message_height = images[4].getHeight();
    }

    /**
     * read and import character image
     */
    @Override
    public void getSurvivorImages(){
        super.getSurvivorImages();
        try{
            images[4] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_badPerson.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * check if player pressed F while in close proximity with static enemy
     * if close then display message box and lower player's HP
     */
    @Override
    public void update() {
        if(appearStatus && (this.x < (mc.x + 40)) && (this.x > (mc.x - 40)) && (this.y < (mc.y + 40)) && (this.y > (mc.y - 40))){
            if(key.pressF){
                int x_distance = this.x - mc.x;
                int y_distance = this.y - mc.y;
                if(x_distance>0){
                    // left
                    direction = "left";
                    if(x_distance<y_distance){
                        // up
                        direction = "up";
                    }
                    else if(x_distance < -y_distance){
                        // down
                        direction = "down";
                    }
                }
                else{
                    // right
                    direction = "right";
                    if(-x_distance < y_distance){
                        // up
                        direction = "up";
                    }
                    else if(-x_distance < -y_distance){
                        // down
                        direction = "down";
                    }
                }
                speakMessageShow = true;
            }
        }

        if(speakMessageShow){
            speakTimeCounter++;
            if(speakTimeCounter > 100){
                // damage
                mc.setHP(mc.getHP()-1);
                appearStatus = false;
            }
        }
    }

    /**
     * draw static enemy character
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(speakMessageShow){
            if (message_x+message_width >= 768){
                message_x = 768 - (message_width+10);
            }
            g2.drawImage(images[4],message_x,message_y,message_width,message_height,null);
        }
    }
}
