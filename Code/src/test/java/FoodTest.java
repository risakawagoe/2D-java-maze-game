import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {
  private MainCharacter a;
  private Food food;
  private GameFrame gameFrame;
  private inputKey key;
  @BeforeEach
  void setUp() throws IOException {
    gameFrame = new GameFrame(1,1,1);
    key = null;
    a = new MainCharacter(gameFrame,key);
    food = new Food(gameFrame,0,0);
  }

  @Test
  void increaseHPTest() {
    a.setHP(2);
    int original = a.getHP();
    food.increaseHP(a);
    int changed = a.getHP();
    assertEquals(changed, original+1);
  }
}
