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

  Roboter karol;
  Roboter pavel;

  /** Call this method to run this game. */
  public void start() {
    Welt welt = new Welt(WIDTH, LENGTH, HEIGHT);
    karol = new Roboter(1, 1, 'S', welt);
    pavel = new Pavel(WIDTH, LENGTH, 'N', welt);
    pavel.VerzoegerungSetzen(VERZOEGERUNG);
    karol.VerzoegerungSetzen(VERZOEGERUNG);

    Thread karolThread =
        new Thread(
            () -> {
              while (!getroffen()) {
                macheZufallsSchritt(karol);
                karol.MarkeSetzen("rot");
              }
              endMessage();
            });
    Thread pavelThread =
        new Thread(
            () -> {
              while (!getroffen()) {
                macheZufallsSchritt(pavel);
                pavel.MarkeSetzen("blau");
              }
            });

    karolThread.start();
    pavelThread.start();
  }

  private void endMessage() {
    if (karol.IstRoboter()) {
      karol.MeldungAusgeben("Gefunden!");
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
    if (!roboter.IstRoboter()) {
      roboter.Schritt();
    }
  }

  private boolean getroffen() {
    return karol.IstRoboter() || pavel.IstRoboter();
  }
}
