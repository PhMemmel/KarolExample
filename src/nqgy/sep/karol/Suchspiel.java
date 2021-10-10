package nqgy.sep.karol;

import java.util.Random;
import javakarol.Roboter;
import javakarol.Welt;
import nqgy.sep.karol.robots.Pavel;

/** Suchspiel. Run start() to run the searching game. */
public class Suchspiel {

  private static final int LENGTH = 20;
  private static final int WIDTH = 20;
  private static final int HEIGHT = 10;
  private static final int VERZOEGERUNG = 10; // in ms

  /** Call this method to run this game. */
  public void start() {
    Welt welt = new Welt(WIDTH, LENGTH, HEIGHT);
    Roboter karol = new Roboter(1, 1, 'S', welt);
    Roboter pavel = new Pavel(WIDTH, LENGTH, 'N', welt);
    pavel.VerzoegerungSetzen(VERZOEGERUNG);
    karol.VerzoegerungSetzen(VERZOEGERUNG);

    while (!karol.IstRoboter() && !pavel.IstRoboter()) {
      macheZufallsSchritt(karol);
      karol.MarkeSetzen("rot");
      macheZufallsSchritt(pavel);
      pavel.MarkeSetzen("blau");
    }

    if (karol.IstRoboter()) {
      karol.MeldungAusgeben("Gefunden");
    } else {
      pavel.MeldungAusgeben("Gefunden!");
    }
  }

  private void macheZufallsSchritt(Roboter roboter) {
    Random random = new Random();
    for (int i = 0; i < random.nextInt(0, 4); i++) {
      roboter.LinksDrehen();
    }
    while (roboter.IstWand()) {
      roboter.LinksDrehen();
    }
    roboter.Schritt();
  }
}
