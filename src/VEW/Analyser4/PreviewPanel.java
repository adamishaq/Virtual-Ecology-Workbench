package VEW.Analyser4;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PreviewPanel extends JPanel {
  public PreviewPanel() { super(); }
  public void paintComponent(Graphics g) {
    BackgroundColour.plotPreview(g,0,0,100,20,BackgroundColour.HORIZONTAL);
  } 
}