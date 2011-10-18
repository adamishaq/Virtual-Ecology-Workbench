package VEW.Analyser4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

import javax.swing.JPanel;

public class ColourScale extends JPanel {
  private int orientation;
  private int pixWidth;
  private int pixHeight;
  private BackgroundChooser bc;
    
  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;
  
  public ColourScale(int _orientation, int _pixWidth, int _pixHeight, BackgroundChooser _bc) {
    orientation=_orientation;
    pixHeight = _pixHeight;
    pixWidth = _pixWidth;
    bc = _bc;
    setPreferredSize(new Dimension(this.pixWidth,this.pixHeight));
  }
    
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(this.getBackground());
    g2d.fillRect(0,0,pixWidth,pixHeight);
    if ((bc!=null) && (bc.useScale())) {
      if (orientation==HORIZONTAL) {
        BackgroundColour.plotPreview(g,10,10,150,20,BackgroundColour.HORIZONTAL);
        g2d.setColor(Color.black);    
        g2d.drawRect(10,10,150,20);
      } else {
        BackgroundColour.plotPreview(g,10,10,20,150,BackgroundColour.VERTICAL);
        g2d.setColor(Color.black);      
        g2d.drawRect(10,10,20,150);
      }
    
      Font smallFont = new Font("Default",Font.PLAIN,9);
      g2d.setFont(smallFont);
      DecimalFormat engf = new DecimalFormat("##0.##E0");
      DecimalFormat stdf = new DecimalFormat("###0.0");      
      
    
      double min = bc.getMin();
      double max = bc.getMax();
      double delta = (150-0)/10;    
      if (orientation==HORIZONTAL) {
        for (int i=0; i<=10; i++) {
          g2d.drawLine(10+(int)(i*delta),25,10+(int)(i*delta),35);
          double val = min+((i/10.0)*(max-min));
          if (((val>0) && (val<0.001)) || (val>=1000)) g2d.drawString(engf.format(val),(int)(i*delta),40);
          else g2d.drawString(stdf.format(val),(int)(i*delta),40);
        }  
      } else {
        for (int i=0; i<=10; i++) {
          g2d.drawLine(25,10+(int)(i*delta),35,10+(int)(i*delta));
          double val = min+((i/10.0)*(max-min));
          if (((val>0) && (val<0.001)) || (val>=1000)) g2d.drawString(engf.format(val),40, 13+(int)(i*delta));
          else g2d.drawString(stdf.format(val),40, 13+(int)(i*delta));
        }
      }
    }
  }
}
