package VEW.Common.Graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class BarChartDrawer extends JPanel {

	private static final long serialVersionUID = 1936730250947297049L;
	private GraphModel data;
	private int graphWidth;
	private int graphHeight;
	private int leftIndent = 60;
	private int bottomIndent = 40;
	private int rightIndent = 10;
	private int topIndent = 10;
	private Color axisColor = Color.BLACK;
	private int charWidth = 7;
	private int charHeight = 10;
	
	public BarChartDrawer(GraphModel graphData, int graphWidth, int graphHeight) {
		super();
		this.data = graphData;
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
	}
	
	public void setGraphModel(GraphModel graphData) {
		this.data = graphData;
	}
	
	
	@Override
	public void paint (Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(0, 0, graphWidth, graphHeight);
		
		g.setColor(axisColor);
		
		
		int rowHeight = (int) Math.floor((graphHeight-bottomIndent-topIndent)/data.yAxisSize());
		drawYAxis(g, rowHeight);
		
		int colWidth = (int) Math.floor((graphWidth - leftIndent - rightIndent)/ data.xAxisSize());
		drawXAxis(g, colWidth);
		
		
		plotPoints();
		
		
	}

	private void plotPoints() {
		
		
		
	}

	private void drawYAxis(Graphics g, int rowHeight) {
		
		int bottom = graphHeight - bottomIndent;
		
		g.drawLine(leftIndent, topIndent, leftIndent, bottom);
		
		for (int i = 0; i < data.yAxisSize(); i++) {
			String message = data.getYAxisValue(i);
			if (!message.equals("")) {
				g.drawString(message, leftIndent - message.length()*charWidth, bottom - i * rowHeight);
			}
		}
		
		
	}

	private void drawXAxis(Graphics g, int colWidth) {

		int bottom = graphHeight - bottomIndent;
		
		g.drawLine(leftIndent, graphHeight - bottomIndent, graphWidth - rightIndent, bottom);
		
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform orig = g2d.getTransform();
		
		int y = bottom + charHeight;
		
		for (int i = 0; i < data.xAxisSize(); i++) {
			String message = data.getXAxisValue(i);
			if (!message.equals("")) {
				int x = leftIndent + colWidth * i;
				drawVerticalString(message, x, y, g, Math.PI/2);
			}
		}
		
		g2d.setTransform(orig);
	}
	
	private void drawVerticalString (String message, int x, int y, Graphics g, double angle) {
		Graphics2D g2D = (Graphics2D)g;


		// Create a rotation transformation for the font.
		AffineTransform fontAT = new AffineTransform();


		// get the current font
		Font theFont = g2D.getFont();


		// Derive a new font using a rotatation transform
		fontAT.rotate(angle);
		Font theDerivedFont = theFont.deriveFont(fontAT);


		// set the derived font in the Graphics2D context
		g2D.setFont(theDerivedFont);


		// Render a string using the derived font
		g2D.drawString(message, x, y - 5);
		// put the original font back
		g2D.setFont(theFont);
	}


}
