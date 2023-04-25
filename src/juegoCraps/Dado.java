package juegoCraps;

import java.util.Random;

public class Dado {
  private int cara;
  /**
   * getCara returns a number between 1 and 6
   * @return
   */
  public int getCara() {
    Random aleatorio = new Random();
    cara = aleatorio.nextInt(6)+1;

    return cara;
  }
}
