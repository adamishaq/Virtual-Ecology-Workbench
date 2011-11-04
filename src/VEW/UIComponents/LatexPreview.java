package VEW.UIComponents;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

@SuppressWarnings("serial")
public class LatexPreview extends JLabel{
	
	public LatexPreview() {
		super();
	}
	
	public void update_preview(String latex) {
		// If the user has not written anything, don't bother
		if (latex .equals("")) {
			return;
		}
		try {
			TeXFormula display = new TeXFormula(latex);
			TeXIcon icon = display.createTeXIcon(TeXConstants.STYLE_DISPLAY,18);
			// Make sure the width and height are non-zero
			int width = 1;
			int height = 1;
			if (icon.getIconWidth() > 0) 
				width = icon.getIconWidth();
			if (icon.getIconHeight() > 0) 
				height = icon.getIconHeight();
			BufferedImage b = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
			icon.paintIcon(new JPanel(), b.getGraphics(), 0, 0);
			this.setIcon(new ImageIcon(b));
		}
		catch (Exception e) {
			// Error in latex string
			return;
		}
	}

}
