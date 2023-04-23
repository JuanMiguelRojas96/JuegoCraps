package juegoCraps;

/**
 * ModelCraps apply craps rules
 * estado = 1 NaturaL Winner
 * estado = 2 Craps Looser
 * estado = 3 Stablish Punto
 * estado = 4 Punto Winner
 * estado = 5 Punto Looser
 * @author Juan Miguel Rojas
 * @version V.1.0.0 date 22/04/2023
 */
public class ModelCraps {
  private Dado dado1, dado2;
  private int tiro, punto, estado, flag;
  private String estadoToString;
  private int[] caras;


  /**
   * Class Constructor
   */
  public ModelCraps(){
    dado1 = new Dado();
    dado2 = new Dado();
    caras = new int[2];
    flag = 0;
  }

  /**
   * Establish the tiro value according to each dice
   */
  public void calcularTiro(){
    caras[0]=dado1.getCara();
    caras[1]=dado2.getCara();
    tiro = caras[0] + caras[1];

  }

  /**
   * Establish game state according to estado atribute value
   * estado = 1 NaturaL Winner
   * estado = 2 Craps Looser
   * estado = 3 Stablish Punto
   */
  public void determinarJuego(){
    if (flag ==0){
      if (tiro == 7 || tiro == 11){
        estado = 1;
      }
      else {
        if (tiro == 3 || tiro == 2 || tiro == 12){
          estado=2;
        }
        else {
          estado=3;
          punto=tiro;
          flag = 1;
        }
      }
    }
    else{
      //ronda punto
      rondaPunto();
    }


  }

  /**
   *  Establish game state to estado atribute value
   *  estado = 4 Punto Winner
   *  estado = 5 Punto Looser
   */
  private void rondaPunto() {
    if(tiro==punto){
      estado=4;
      flag=0;
    }
    if (tiro==7){
      estado=5;
      flag=0;
    }
  }

  public int getTiro() {
    return tiro;
  }

  public int getPunto() {
    return punto;
  }


  /**
   * Establish message game state according to estado atribute value
   * @return  Message for the View class
   */
  public String getEstadoToString() {
    switch(estado){
      case  1: estadoToString ="Sacaste Natural, has ganado!!";
        break;
      case  2: estadoToString ="Sacaste Craps, has perdido!!";
        break;
      case  3: estadoToString ="Estableciste Punto en " + punto +
                                  " Debes seguir lanzando!!"+
                                  "\n pero si sacas 7 antes que " + punto +
                                  " perderas";
        break;
      case  4: estadoToString ="Volviste a sacar " + punto + " has ganado!!";
        break;
      case  5: estadoToString ="Sacaste 7 antes que " + punto + "has perdido!!";
        break;

    }
    return estadoToString;
  }

  public int[] getCaras() {
    return caras;
  }
}
