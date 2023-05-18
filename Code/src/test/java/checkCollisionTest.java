import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.awt.*;
import java.io.IOException;


public class checkCollisionTest {
    @Test
    public void checkTileTest() throws IOException {
        int colm = 16;
        int rows = 12;
        int pacSize = 48; //industry standard 48x48

        //Make GameFrame class
        GameFrame testGF = new GameFrame(colm, rows, pacSize);

        //Make Dynamic Character
        DynamicCharacter dcTest = new MainCharacter();

        //Make checkCollision class and send GameFrame class in
        checkCollision ccTest = new checkCollision(testGF);

        //hit area
        dcTest.hitAreaStatic = new Rectangle( 0,0,24,  24);

        //Corner tests
        //0,0
        dcTest.collision = false;
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collision);

        //48,48
        dcTest.x = 768/2;
        dcTest.y = 576/2;
        dcTest.collision = false;
        dcTest.direction = "up";
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collision);


        //0,48
        dcTest.x = 0;
        dcTest.y = 576/2;
        dcTest.collision = false;
        dcTest.direction = "down";
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collision);


        //48,0
        dcTest.x = 768/2;
        dcTest.y = 0;
        dcTest.direction = "left";
        dcTest.collision = false;
        ccTest.checkTile(dcTest);
        Assertions.assertTrue(dcTest.collision);


        //Outside walls
        dcTest.x = 144;
        dcTest.y = 24;
        dcTest.collision = false;
        dcTest.direction = "right";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collision);


        //Top touching wall
        dcTest.x = 48;
        dcTest.y = 24;
        dcTest.collision = false;
        dcTest.direction = "up";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collision);


        //Bottom touching wall
        dcTest.x = 48;
        dcTest.y = 276;
        dcTest.collision = false;
        dcTest.direction = "down";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collision);


        //Right touching wall
        dcTest.x = 168;
        dcTest.y = 518;
        dcTest.collision = false;
        dcTest.direction = "right";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collision);

        //left touching wall
        dcTest.x = 238;
        dcTest.y = 518;
        dcTest.collision = false;
        dcTest.direction = "left";
        ccTest.checkTile(dcTest);
        Assertions.assertFalse(dcTest.collision);
    }

}
