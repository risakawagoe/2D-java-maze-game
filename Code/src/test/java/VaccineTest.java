import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class VaccineTest {
  private MainCharacter a;
  private Vaccine va;
  private Random r = new Random();
  private GameFrame gameFrame;
  private inputKey key;
  @BeforeEach
  void setUp() throws IOException {
    gameFrame = new GameFrame(1,1,1);
    key = null;
    a = new MainCharacter(gameFrame,key);
    va = new Vaccine(gameFrame,0,0);
  }

  @Test
  void increaseVaccineTest() {
    a.setVaccines(5);
    int original = a.getVaccines();
    va.increaseVaccine(a);
    int changed = a.getVaccines();
    assertEquals(changed, original+1);
  }
}
