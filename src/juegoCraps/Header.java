package juegoCraps;

import javax.swing.*;
import java.awt.*;

public class Header extends JLabel {
  public Header(String title,Color backgroundColor) {
    this.setText(title);
    this.setBackground(backgroundColor);
    this.setForeground(Color.WHITE);
    this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
    this.setHorizontalAlignment(JLabel.CENTER);
    this.setOpaque(true);
  }

  }
