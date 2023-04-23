package juegoCraps;

import java.util.Random;

public class Dado {
  private int cara;

  public int getCara() {
    Random aleatorio = new Random();
    cara = aleatorio.nextInt(6)+1;

    return cara;
  }
}
