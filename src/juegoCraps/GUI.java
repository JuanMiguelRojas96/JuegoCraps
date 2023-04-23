package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *  This class is used as View Craps Class
 *  @autor Juan Miguel Rojas
 *  @version v.1.0.0 date:23/04/2023
 *
 */

public class GUI extends JFrame {
  public static final String MENSAJE_INICIO =" Bienvenido a Craps \n"
      + "Oprime el botón lanzar para iniciar el juego"
      + "\nSi tu tiro de salida es 7 u 11 ganas con Natural"
      + "\nSi tu tiro de salida es 2, 3 o 12 pierdes con Craps"
      + "\nSi sacas cualquier otro valor establecerás el Punto"
      + "\nEstado en punto podrás seguir lanzando los dados"
      + "\npero ahora ganarás si sacas nuevamente el valor del Punto"
      + "\nsin que previamente sacado 7";


  private Header headerProject;
  private JLabel dado1,dado2;
  private JButton lanzar;
  private JPanel panelDados, panelResultados;
  private ImageIcon imageDado;
  private JTextArea resultados;
  private Escucha escucha;
  private ModelCraps modelCraps;


  /**
   * Constructor of GUI class
   */
  public GUI(){
    initGUI();

    //Default JFrame configuration

    this.setTitle("Juego Craps");
    //this.setSize(800,600);
    this.pack();
    this.setResizable(true);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void initGUI() {
    // Set up JFrame Container's Layout
    //Create Listener Object or Control Object
    escucha = new Escucha();
    modelCraps = new ModelCraps();
    //Set up JComponents
    headerProject = new Header("Mesa Juego Craps", Color.BLACK);
    this.add(headerProject,BorderLayout.NORTH);

    imageDado = new ImageIcon(getClass().getResource("/resourses/dado.png"));
    dado1 = new JLabel(imageDado);
    dado2 = new JLabel(imageDado);

    lanzar = new JButton("Lanzar");
    lanzar.addActionListener(escucha);


    panelDados =new JPanel();
    panelDados.setPreferredSize(new Dimension(300,180));
    panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
    panelDados.add(dado1);
    panelDados.add(dado2);
    panelDados.add(lanzar);


    this.add(panelDados,BorderLayout.CENTER);


    resultados = new JTextArea(7,31);
    resultados.setText(MENSAJE_INICIO);
    resultados.setBorder(BorderFactory.createTitledBorder("Qué debes hacer"));
    JScrollPane scroll = new JScrollPane(resultados);
    this.add(scroll,BorderLayout.EAST);



  }
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        GUI myGUI = new GUI();
      }
    });
  }



  private class Escucha implements ActionListener{


    @Override
    public void actionPerformed(ActionEvent e) {
      modelCraps.calcularTiro();
      int[] caras = modelCraps.getCaras();
      imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
      dado1.setIcon(imageDado);
      imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
      dado2.setIcon(imageDado);


    }
  }
}