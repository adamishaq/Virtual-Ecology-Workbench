package VEW.Planktonica2.UIComponents;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

/**
 * A <code>JLabel</code> which can render and display <code>LaTeX</code>
 */
public class LatexPreview extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LatexPreview() {
		super();
	}
	
	/**
	 * Interprets and displays a given <code>LaTeX<code> string if it is valid
	 * @param latex - String containing <code>LaTeX</code> to render
	 */
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
			// Paint a new image on this JLabel
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
