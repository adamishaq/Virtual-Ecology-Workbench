package VEW.Common.Graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class BarChartDrawer extends JPanel implements TableModelListener {

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
	
	/**
	 * creates a new bar chart which can be drawn into an area.
	 * 
	 * @param graphData the data with thich 
	 * @param graphWidth the width of the graph panel
	 * @param graphHeight the height of the graph panel
	 * @param topIndent the gap between the top of the panel and top of the y axis 
	 * @param rightIndent the gap between the end of the x axis and the right of the panel
	 * @param bottomIndent the gap between the bottom of the panel and the x axis. Should be enough to fit the x axis labels.
	 * @param leftIndent the gap between the left of the panel and the y axis. Should be enough to fit the y axis lables.
	 */
	public BarChartDrawer(GraphModel graphData, int graphWidth, int graphHeight, int topIndent, int rightIndent, int bottomIndent, int leftIndent) {
		this.data = graphData;
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
		this.topIndent = topIndent;
		this.rightIndent = rightIndent;
		this.bottomIndent = bottomIndent;
		this.leftIndent = leftIndent;
	}
	
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
		
		
		plotPoints(g, colWidth);
		
		
	}

	private void plotPoints(Graphics g, int colWidth) {
		
		Graphics2D g2d = (Graphics2D) g;
		// Stores the color so it can be restored on method exit.
		Color origCol = g2d.getColor();
		
		int max = data.xAxisSize();
		
		
		
		for (int x = 0; x < max; x++) {
			double y = data.getValue(x);
			
			// make rectangle
			int leftPointOfBar = this.leftIndent + x * colWidth;
			int barHeight = (int) Math.ceil((graphHeight - topIndent - bottomIndent) * y);
			Rectangle bar = new Rectangle(leftPointOfBar, graphHeight - bottomIndent - barHeight, colWidth, barHeight);
			
			
			g2d.setColor(data.getColumnColor(x));
			g2d.fill(bar);
			g2d.setColor(Color.BLACK);
			g2d.draw(bar);
			
		}
		
		g2d.setColor(origCol);
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
		
		Graphics2D g2D = (Graphics2D) g;


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

	
	@Override
	public void tableChanged(TableModelEvent arg0) {
		
		this.repaint();
		
	}


}
