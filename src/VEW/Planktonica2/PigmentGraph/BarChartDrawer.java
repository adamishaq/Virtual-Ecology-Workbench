package VEW.Planktonica2.PigmentGraph;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import VEW.Planktonica2.ControllerStructure.GraphModel;

public class BarChartDrawer extends JPanel {

	private static final long serialVersionUID = 1936730250947297049L;
	private GraphModel data;
	private int graphWidth;
	private int graphHeight;
	private int leftIndent = 50;
	private int bottomIndent = 40;
	private int rightIndent = 10;
	private int topIndent = 10;
	private Color axisColor = Color.BLACK;
	
	public BarChartDrawer(GraphModel graphData, int graphWidth, int graphHeight) {
		super();
		this.data = graphData;
		this.graphWidth = graphWidth;
		this.graphHeight = graphHeight;
	}
	
	public void setGraphModel(GraphModel graphData) {
		if (graphData == null) {
			throw new NullPointerException("There was no graph data given to the setGraphModel method in the drawer.");
		}
		this.data = graphData;
	}
	
	
	@Override
	public void paint (Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(0, 0, graphWidth, graphHeight);
		
		g.setColor(axisColor);
		drawXAxis(g);
		
		int rowHeight = (int) Math.floor((graphHeight-20)/data.yAxisSize());
		drawYAxis(g, rowHeight);
		
		int colWidth = (int) Math.floor((graphWidth-30)/ data.xAxisSize());
		
		
		
		
		
	}

	private void drawYAxis(Graphics g, int rowHeight) {
		
		int bottom = graphHeight - bottomIndent;
		
		g.drawLine(leftIndent, topIndent, leftIndent, bottom);
		
		int markerPosition = rowHeight;
		for (int i = 0; i < data.yAxisSize(); i++) {
			String message = data.getYAxisValue(i);
			if (!message.equals("")) {
				g.drawString(data.getYAxisValue(i), leftIndent, topIndent);
			}
		}
		
		
	}

	private void drawXAxis(Graphics g) {

		g.drawLine(leftIndent, graphHeight - bottomIndent, graphWidth - rightIndent, graphHeight - bottomIndent);
		
	}

	
	

}
