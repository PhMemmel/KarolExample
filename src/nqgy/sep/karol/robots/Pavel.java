package nqgy.sep.karol.robots;

import java.util.Random;
import javakarol.Roboter;
import javakarol.Welt;

/**
 * Karol's brother Pavel.
 */
public class Pavel extends Roboter {

  Random random = new Random();

  public Pavel(int startX, int startY, char startBlickrichtung, Welt inWelt) {
    super(startX, startY, startBlickrichtung, inWelt);
  }

  public Pavel(Welt inWelt) {
    super(inWelt);
  }

  public void Umdrehen() {
    LinksDrehen();
    LinksDrehen();
  }

  @Override
  public void Hinlegen() {
    super.Hinlegen("blau");
  }

  @Override
  public void Schritt() {
    for (int i = 0; i < random.nextInt(1, 3); i++) {
      if (!IstWand() && !IstRoboter()) {
        super.Schritt();
      }
    }
  }
}
