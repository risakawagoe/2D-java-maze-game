import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * class to implement character which increases player HP
 */
public class KindSurvivor extends StaticCharacter{
    /**
     * The attributes of kind survivor: the door he can open
     */
    private int[][] map;
    private int door_x, door_y;

    /**
     * The constructor for KindSurvivor
     */
    public KindSurvivor(){
        // Just for test
    }

    /**
     * The constructor for KindSurvivor
     * The parameters are:
     *      gameFrame--The frame that show the game
     *      key -- The key input
     *      mc -- the main character
     *      x -- the x position of the kind survivor
     *      y -- the y position of the kind survivor
     *      Board -- the GameMap
     *      door_x -- the x position of the door that kind survivor can open
     *      door_y -- the y position of the door that kind survivor can open
     */
    public KindSurvivor(GameFrame gameFrame, inputKey key, MainCharacter mc, int x, int y, int[][] Board,int door_x, int door_y){
        this.frame = gameFrame;
        this.key = key;
        this.mc = mc;
        this.map = Board;
        this.width = 30;
        this.height = 30;

        getSurvivorImages();
        setDefaultValue(x,y,door_x,door_y);
    }

    /**
     * reset maze
     * @param Board
     */
    public void resetBoard(int[][] Board){
      this.map = Board;
    }

    /**
     * set starting point and attributes for good character and their dialog message box
     * @param x
     * @param y
     * @param door_x
     * @param door_y
     */
    public void setDefaultValue(int x, int y,int door_x, int door_y){
        this.direction = "down";
        this.x = x;
        this.y = y;
        this.door_x = door_x;
        this.door_y = door_y;
        this.speakMessageShow = false;
        this.message_x = x;
        this.message_y = y - images[4].getHeight();
        this.message_width = images[4].getWidth();
        this.message_height = images[4].getHeight();
    }

    /**
     * Set exit wall to floor for player to exit
     */
    public void openDoor() {
        this.map[door_x][door_y] = 0;
    }

    /**
     * Hide exit when resetting game (game finish)
     */
    public void closeDoor() { this.map[door_x][door_y] = 1; }

    public int[][] getMap(){
        return this.map;
    }

    /**
     * read and import character image
     */
    @Override
    public void getSurvivorImages(){
        super.getSurvivorImages();
        try{
            images[4] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_goodPerson.png"));
            images[5] = ImageIO.read(new File("src/main/java/picture/Character/Character_goodOrBadPerson/Message_goodPerson2.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * check if player pressed F while in close proximity with static enemy
     * if close then display message box and open door if all required vaccines are collected by player
     */
    @Override
    public void update(){
        if(appearStatus && (this.x < (mc.x + 40)) && (this.x > (mc.x - 40)) && (this.y < (mc.y + 40)) && (this.y > (mc.y - 40))){
//            System.out.println("near the mystery character");
            if(key.pressF == true) {
                int x_distance = this.x - mc.x;
                int y_distance = this.y - mc.y;
                if (x_distance > 0) {
                    // left
                    direction = "left";
                    if (x_distance < y_distance) {
                        // up
                        direction = "up";
                    } else if (x_distance < -y_distance) {
                        // down
                        direction = "down";
                    }
                } else {
                    // right
                    direction = "right";
                    if (-x_distance < y_distance) {
                        // up
                        direction = "up";
                    } else if (-x_distance < -y_distance) {
                        // down
                        direction = "down";
                    }
                }

                speakMessageShow = true;
            }
        }

        if (speakMessageShow == true){
            speakTimeCounter++;
            if(speakTimeCounter >200){
                speakMessageShow = false;
                speakTimeCounter = 0;
                if(mc.getVaccines() >= frame.settings.getNumOfVaccines()){
                    openDoor();
                    appearStatus = false;
                }
            }
        }
    }

    /**
     * draw character
     * @param g2
     */
    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(speakMessageShow){
            if (message_x+message_width >= 768){
                message_x = 768 - (message_width+10);
            }
            if (mc.getVaccines() >= frame.settings.getNumOfVaccines()){
                g2.drawImage(images[4],message_x,message_y,message_width,message_height,null);
            } else {
                g2.drawImage(images[5],message_x,message_y,message_width,message_height,null);
            }
        }
    }
}
