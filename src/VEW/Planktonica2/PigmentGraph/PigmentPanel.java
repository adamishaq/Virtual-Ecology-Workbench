package VEW.Planktonica2.PigmentGraph;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import VEW.Common.Graph.BarChartDrawer;
import VEW.Common.Graph.GraphModel;
import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.Model.NullSpectrum;
import VEW.Planktonica2.Model.Spectrum;

public class PigmentPanel extends JPanel implements Observer, TableModelListener, ItemListener {


	private static final long serialVersionUID = 1040209636264981879L;

	private JComboBox graphType;
	private BarChartDrawer pigmentGraph;
	private JCheckBox doPigments;
	
	public static final String Pigment_CHI = "Chi";
	public static final String Pigment_e = "e";
	
	public final String defaultStartName = Pigment_CHI;
	
	protected final int graphHeight = 260;
	protected final int graphWidth = 580;
	


	private ChemicalController controller;

	private PigmentTableModel dataTableModel;

	public PigmentPanel(ChemicalController controller) {
		this.controller = controller;
		controller.addObserver(this);
		initilizeGUI();
	}

	private void initilizeGUI() {

		setLayout(new BorderLayout(2,2));

		// sets up the graph type drop down.
		graphType = new JComboBox();
		graphType.addItem(Pigment_CHI);
		graphType.addItem(Pigment_e);
		graphType.setSelectedIndex(0);
		graphType.addItemListener(this);
		
		dataTableModel = new PigmentTableModel();
	    
	    JTable dataTable = new JTable(dataTableModel);
	    
	    JScrollPane dataScroller = new JScrollPane(dataTable);
	    dataScroller.setPreferredSize(new Dimension(150,250));
		
		Spectrum toDraw = new NullSpectrum();
		
		drawNewGraph(toDraw);
		
		dataTableModel.addTableModelListener(this);

		doPigments = new JCheckBox("Chemical has pigmentation?");
		ItemListener i = new ChemicalHasPigmentListener(this.controller, this);
		doPigments.addItemListener(i);
		
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(doPigments);
		topPanel.add(graphType);
		add(topPanel,"North");

		
		add(dataScroller,"West");
		add(pigmentGraph,"Center");
	}
	
	/**
	 * Draws a graph of the given spectrum.
	 * @param s the spectrum to be drawn
	 */
	public void drawNewGraph (Spectrum s) {
		if (s == null) {
			s = new NullSpectrum();
		}
		
		this.dataTableModel.setSpectrum(s);
		GraphModel graphData = new SpectrumGraph(s, 10);
		if (pigmentGraph == null) {
			pigmentGraph = new BarChartDrawer(graphData, this.graphWidth, this.graphHeight);
		}
		
		pigmentGraph.setGraphModel(graphData);
		if (doPigments != null) {
			doPigments.setSelected(s.hasPigment());
		}
		
		this.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		if (o == controller) {
			drawNewGraph(controller.getSelectedChemicalSpetrum((String) graphType.getSelectedItem()));
			
		}
		
	}

	
	@Override
	public void tableChanged(TableModelEvent e) {
		
		this.repaint();
		
	}

	
	@Override
	public void itemStateChanged(ItemEvent item) {
		
		String name = (String) item.getItem();
		
		Spectrum s = controller.getSelectedChemical().getSpectrum(name);
		
		drawNewGraph(s);
		
	}

	public void redrawGraph() {
		this.pigmentGraph.repaint();
		
	}

	

}

