package VEW.Common;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JPanel;

public class ImagePanel extends JPanel
{
   BufferedImage BackgroundPicture;
	public ImagePanel(String FileName) 
	{
      try
      {
         BackgroundPicture = javax.imageio.ImageIO.read(new File(FileName));
      }
      catch(Exception e)
      {
      }
	}

   public void paintComponent(Graphics g)
   {
      if(BackgroundPicture != null)
      {
         g.drawImage(BackgroundPicture, 0, 0, getWidth(), getHeight(), null);
      }
   }
}
