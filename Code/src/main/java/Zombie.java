import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

/**
 * class to implement and manage dynamic enemy
 */
public class Zombie extends DynamicCharacter{
    /**
     * The attributes of zombie: the number of HP decrease from MainCharacter (damage)
     */

    private MainCharacter aim;

    /**
     * class constructor
     */
    public Zombie(){
        // just use for test
    }

    /**
     * class constructor
     * @param gameFrame
     * @param x
     * @param y
     * @param mc
     */
    public Zombie(GameFrame gameFrame, int x, int y, MainCharacter mc){
        this.frame = gameFrame;
        this.aim = mc;

        setDefaultValue(x,y);
        getZombieImages();
        hitArea = new Rectangle(this.x,this.y,5,5);
        hitAreaStatic = new Rectangle((int) 1,45,frame.getCellSize()/2, (int) (frame.getCellSize()));
    }

    /**
     * Read and import images for character
     */
    public void getZombieImages() {
        try{
            images[0] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp1.png"));
            images[1] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp2.png"));
            images[2] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp3.png"));
            images[3] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkUp4.png"));

            images[4] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown1.png"));
            images[5] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown2.png"));
            images[6] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown3.png"));
            images[7] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkDown4.png"));

            images[8] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight1.png"));
            images[9] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight2.png"));
            images[10] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight3.png"));
            images[11] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkRight4.png"));

            images[12] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft1.png"));
            images[13] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft2.png"));
            images[14] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft3.png"));
            images[15] = ImageIO.read(new File("src/main/java/picture/Character/Character_zombie/zombie_walkLeft4.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * set starting points and speed of character
     * @param x
     * @param y
     */
    private void setDefaultValue(int x, int y) {
        this.x = x;
        this.y = y;
        speed = 1;
    }

    /**
     * update character positioning each update
     */
    public void update(){
        collision = false;
        frame.check_collision.checkTile(this);

        if (collision == false) {
            switch (direction){
                case "up":
                    y -= speed;
                    break;
                case "down":
                    y += speed;
                    break;
                case "left":
                    x -= speed;
                    break;
                case "right":
                    x += speed;
                    break;
            }
            spriteCounter++;
            if (spriteCounter > 30) {
                spriteNum = spriteNum + 1;
                if(spriteNum > 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if(!closedToAim()){
            spriteCounter++;
            if(spriteCounter > 120){
                Random random = new Random();
                int randomNum = random.nextInt(100)+1;
                if(randomNum <= 25){
                    direction = "up";
                }
                else if(randomNum > 25 && randomNum <= 50){
                    direction = "down";
                }
                else if(randomNum > 50 && randomNum <= 75){
                    direction = "left";
                }
                else{
                    direction = "right";
                }
                spriteCounter = 0;
            }
        }
        else{
            int x_distance = this.x - aim.x;
            int y_distance = this.y - aim.y;
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
        }
      hitArea = new Rectangle(this.x,this.y,5,5);
    }

    /**
     * check if enemy is close to player
     * @return
     */
    public boolean closedToAim() {
        if((this.x < (aim.x + 150)) && (this.x > (aim.x - 150)) && (this.y < (aim.y + 150)) && (this.y > (aim.y - 150))){
            return true;
        }
        else
            return false;
    }

    /**
     * draw character
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1)
                    image = images[0];
                else if(spriteNum == 2)
                    image = images[1];
                else if(spriteNum == 3)
                    image = images[2];
                else
                    image = images[3];
                break;
            case "down":
                if(spriteNum == 1)
                    image = images[4];
                else if(spriteNum == 2)
                    image = images[5];
                else if(spriteNum == 3)
                    image = images[6];
                else
                    image = images[7];
                break;
            case "right":
                if(spriteNum == 1)
                    image = images[8];
                else if(spriteNum == 2)
                    image = images[9];
                else if(spriteNum == 3)
                    image = images[10];
                else
                    image = images[11];
                break;
            case "left":
                if(spriteNum == 1)
                    image = images[12];
                else if(spriteNum == 2)
                    image = images[13];
                else if(spriteNum == 3)
                    image = images[14];
                else
                    image = images[15];
                break;

        }
        g2.drawImage(image,x,y,image.getWidth(),image.getHeight(),null);
    }
}
