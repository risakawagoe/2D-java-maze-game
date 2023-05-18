import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * class to implement and manage player character
 */
public class MainCharacter extends DynamicCharacter{
    /**
     * The attributes of main character: the number of vaccines, the HP
     */
    private int vaccines = 0;
    private int HP = 1;
    private BufferedImage HP_image;
    private BufferedImage VaccineImage;

    /**
     * class constructor with no parameter
     */
    public MainCharacter(){
        // Just use for test
    }

    /**
     * class constructor
     * @param gameFrame
     * @param key
     */
    public MainCharacter(GameFrame gameFrame, inputKey key){
        this.frame = gameFrame;
        this.key = key;
        this.width = 20;
        this.height = 20;
        this.lvl = 0;

        setDefaultValue(frame.tileFrame.getStartPoints(lvl));
        getMCImages();
        hitAreaStatic = new Rectangle( 1,45,frame.getCellSize()/2,  frame.getCellSize());
    }

    /**
     * set starting points and speed for player
     * @param startPoints
     */
    public void setDefaultValue(int[] startPoints){
        x = startPoints[0];
        y = startPoints[1];
        speed = 2 ;
    }


    /**
     * Return the number of vaccine
     */
    public int getVaccines() {
        return vaccines;
    }

    /**
     * Return the HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * Set the number of vaccines
     */
    public void setVaccines(int vaccines) {
        this.vaccines = vaccines;
    }

    /**
     * Set the HP
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * Read and import character images
     */
    public void getMCImages(){
        try{
            int i = 0;
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp1.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp2.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp3.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkUp4.png"));

            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown1.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown2.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown3.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkDown4.png"));

            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight1.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight2.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight3.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkRight4.png"));

            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft1.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft2.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft3.png"));
            images[i++] = ImageIO.read(new File("src/main/java/picture/Character/Character_boy/boy_walkLeft4.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Update player position based on user keyboard inputs
     */
    @Override
    public void update(){
        if(key.pressedUp || key.pressedDown || key.pressedLeft || key.pressedRight) {
            if (key.pressedUp) {
                direction = "up";
            } else if (key.pressedDown) {
                direction = "down";
            } else if (key.pressedLeft) {
                direction = "left";
            } else if (key.pressedRight) {
                direction = "right";
            }

            collision = false;
            frame.check_collision.checkTile(this);
//            System.out.println(collision);

            if (collision == false) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum = spriteNum + 1;
                if(spriteNum > 4){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{
            spriteNum = 1;
        }
    }

    /**
     * Draw player character
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
        g2.drawImage(image,x,y, (frame.getCellSize()/2), frame.getCellSize(), null);
    }

    /**
     * Read and import score images for player
     */
    private void getScoreImg() {
        try{
        VaccineImage = ImageIO.read(new File("src/main/java/picture/GUI_image/Vaccine_panel.png"));
        HP_image = ImageIO.read(new File("src/main/java/picture/GUI_image/HP_panel.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draw player score board
     * @param g2
     * @param x
     * @param y
     * @param maxVaccine
     */
    public void drawScore(Graphics2D g2,int x,int y, int maxVaccine){
      getScoreImg();
      g2.drawImage(VaccineImage,x,y,60,40,null);
      g2.drawImage(HP_image,x+60,y, 60,40,null);
      g2.setColor(Color.black);
      g2.setFont(new Font("default", Font.BOLD, 15));
      g2.drawString(""+getVaccines()+"/" + maxVaccine, x+18,y+25);
      g2.drawString(""+getHP(), x+90,y+25);
    }

    public void resetAttributesMC() {
        vaccines = 0;
        HP = 1;
        lvl = 0;
        direction = "down";
        collision = false;
    }
}
