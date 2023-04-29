package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {

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
  private JButton lanzar,ayuda,salir;
  private JPanel panelDados;
  private ImageIcon imageDado;
  private JTextArea mensajesSalida,resultadosDados;
  private Escucha escucha;
  private ModelCraps modelCraps;
  
  public GUIGridBagLayout(){
    initGUI();

    //Default JFrame configuration

    this.setTitle("Juego Craps");
    this.setUndecorated(true);
    this.pack();
    this.setResizable(true);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void initGUI() {
    // Set up JFrame Container's Layout
    this.getContentPane().setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    //Create Listener Object or Control Object
    escucha = new Escucha();
    modelCraps = new ModelCraps();
    //Set up JComponents
    headerProject = new Header("Mesa Juego Craps", Color.BLACK);
    constraints.gridx=0;
    constraints.gridy=0;
    constraints.gridwidth=2;
    constraints.fill=GridBagConstraints.HORIZONTAL;
    this.add(headerProject,constraints);

    ayuda = new JButton(" ? ");
    ayuda.addActionListener(escucha);
    constraints.gridx=0;
    constraints.gridy=1;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.LINE_START;
    this.add(ayuda,constraints);

    salir = new JButton("Salir");
    salir.addActionListener(escucha);
    constraints.gridx=1;
    constraints.gridy=1;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.LINE_END;
    this.add(salir,constraints);


    imageDado = new ImageIcon(getClass().getResource("/resourses/dado.png"));
    dado1 = new JLabel(imageDado);
    dado2 = new JLabel(imageDado);

    panelDados =new JPanel();
    panelDados.setPreferredSize(new Dimension(300,180));
    panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
    panelDados.add(dado1);
    panelDados.add(dado2);

    constraints.gridx=0;
    constraints.gridy=2;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.BOTH;
    constraints.anchor=GridBagConstraints.CENTER;
    this.add(panelDados,constraints);

    resultadosDados = new JTextArea(4,31);
    resultadosDados.setEditable(false);
    resultadosDados.setOpaque(false);
    resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
    resultadosDados.setText("Debes lanzar los dados");

    constraints.gridx=1;
    constraints.gridy=2;
    constraints.gridwidth=1;
    constraints.fill=GridBagConstraints.BOTH;
    constraints.anchor=GridBagConstraints.CENTER;
    this.add(resultadosDados,constraints);

    lanzar = new JButton("Lanzar");
    lanzar.addActionListener(escucha);

    constraints.gridx=0;
    constraints.gridy=3;
    constraints.gridwidth=2;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.CENTER;
    this.add(lanzar,constraints);


    mensajesSalida = new JTextArea(4,50);
    mensajesSalida.setText("Usa el botón (?) para ver las reglas del juego");
    mensajesSalida.setEditable(false);
    mensajesSalida.setOpaque(false);
    mensajesSalida.setBorder(BorderFactory.createTitledBorder("Mensajes"));

    constraints.gridx=0;
    constraints.gridy=4;
    constraints.gridwidth=2;
    constraints.fill=GridBagConstraints.NONE;
    constraints.anchor=GridBagConstraints.CENTER;
    this.add(mensajesSalida,constraints);




  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        GUIGridBagLayout myGUI = new GUIGridBagLayout();
      }
    });
  }

  private class Escucha implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==lanzar){
        modelCraps.calcularTiro();
        int[] caras = modelCraps.getCaras();
        imageDado = new ImageIcon(getClass().getResource("/resourses/"+caras[0]+".png"));
        dado1.setIcon(imageDado);
        imageDado = new ImageIcon(getClass().getResource("/resourses/"+caras[1]+".png"));
        dado2.setIcon(imageDado);
        modelCraps.determinarJuego();

        resultadosDados.setText(modelCraps.getEstadoToString()[0]);
        mensajesSalida.setText(modelCraps.getEstadoToString()[1]);
        revalidate();
        repaint();
      } else{
          if (e.getSource()==ayuda){
            JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
          }
          if (e.getSource()==salir){
            System.exit(0);
          }

      }


    }
  }

}
