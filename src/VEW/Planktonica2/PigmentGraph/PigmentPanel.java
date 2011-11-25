package VEW.Planktonica2.PigmentGraph;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.ControllerStructure.GraphModel;
import VEW.Planktonica2.Model.NullSpectrum;
import VEW.Planktonica2.Model.Spectrum;

public class PigmentPanel extends JPanel {


	private static final long serialVersionUID = 1040209636264981879L;

	private JComboBox graphType;
	
	public static final String Pigment_CHI = "Chi";
	public static final String Pigment_e = "e";
	
	public final String defaultStartName = Pigment_CHI;
	
	protected final int graphHeight = 260;
	protected final int graphWidth = 580;
	private static final String[] colHeaders = new String[] {"Min W", "Max W", "Value"};


	private ChemicalController controller;

	public PigmentPanel(ChemicalController controller) {
		this.controller = controller;

		initilizeGUI();
	}

	private void initilizeGUI() {

		setLayout(new BorderLayout(2,2));

		graphType = new JComboBox();
		graphType.addItem(Pigment_CHI);
		graphType.addItem(Pigment_e);
		graphType.setSelectedIndex(0);
		
		Spectrum toDraw = new NullSpectrum();
		
		
		GraphModel graphData = new SpectrumGraph(toDraw, 10); 
		BarChartDrawer pigmentGraph = new BarChartDrawer(graphData, this.graphWidth, this.graphHeight);

		JCheckBox doPigments = new JCheckBox("Chemical has pigmentation?");
		doPigments.addItemListener(new ChemicalHasPigmentListener(this.controller));
		
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(doPigments);
		topPanel.add(graphType);
		add(topPanel,"North");

		JScrollPane dataScroller = new JScrollPane();
		dataScroller.setPreferredSize(new Dimension(150,250));
		add(dataScroller,"West");
		add(pigmentGraph,"Center");
	}

	/*public PigmentPanel(Planktonica p) {
    EventHandler eh = new EventHandler();
    thePlank = p;
    graphType = new JComboBox();
    graphType.addItem(Pigment_CHI);
    graphType.addItem(Pigment_e);
    graphType.setSelectedIndex(0);
    setLayout(new BorderLayout(2,2));
    dataTableModel.setRowCount(25);
    dataTableModel.setColumnCount(3);
    dataTableModel.setColumnIdentifiers(colHeaders);
    pigmentGraph = new PigmentGraph();
    pigmentGraph.setSize(new Dimension(graphWidth,graphHeight));
    for (int i=0; i<=24; i++) {
      dataTable.setValueAt(String.valueOf(pigmentGraph.wavelengths[i]),i,0);
      dataTable.setValueAt(String.valueOf(pigmentGraph.wavelengths[i+1]),i,1);
    }
    JPanel topPanel = new JPanel(new FlowLayout());
    topPanel.add(doPigments);
    topPanel.add(graphType);
    add(topPanel,"North");
    JScrollPane dataScroller = new JScrollPane(dataTable);
    dataScroller.setPreferredSize(new Dimension(150,250));
    add(dataScroller,"West");
    add(pigmentGraph,"Center");

    graphType.addActionListener(eh);
    doPigments.addActionListener(eh);
    dataTableModel.addTableModelListener(eh);

  }*/
/*
	
	public void show(XMLTag pigment, XMLFile model) {
		// 
		
		thePigment = pigment;
		if (graphType.getSelectedIndex()==-1) graphType.setSelectedIndex(0);
		//doPigments.setSelected((thePlank.getCurrentInstance()!=null) && 
		//                        (thePlank.getCurrentInstance().getValue("pigment")!=null) &&
		//                      (thePlank.getCurrentInstance().getValue("pigment").equals("true")));
		setGraph();
		updatePig();
	}

	public void updatePig() {
		graphType.setEnabled(doPigments.isSelected());
		dataTable.setEnabled(doPigments.isSelected());
	}

	public void setGraph() {
		if (thePigment!=null) {
			if ((graphType.getSelectedItem()!=null) && 
					(thePigment.getTagWhere("spectrum","name",graphType.getSelectedItem().toString())!=null) &&
					(thePigment.getTag("pigment")!=null) && (thePigment.getValue("pigment").equals("true"))) {
				String theEq = thePigment.getTagWhere("spectrum","name",graphType.getSelectedItem().toString()).getValue("equation/eq");
				theEq = StringTools.spit(theEq,"\\graphvals{");
				lockEvents++;
				double[] d = new double[25];
				for (int i=0; i<25; i++) {
					String bit = theEq.substring(1,theEq.indexOf("}"));
					String right = bit.substring(bit.indexOf(",")+1);
					if (i<24) theEq = theEq.substring(theEq.indexOf("}")+1);
					dataTable.setValueAt(right,i,2);
					d[i]=Double.parseDouble(right);
				}
				lockEvents--;
				pigmentGraph.setValues(d);
				pigmentGraph.paint(pigmentGraph.getGraphics());
			} else {
				double[] d = new double[25];
				lockEvents++;
				for (int i=0; i<25; i++) {
					dataTable.setValueAt("0.0",i,2);
					d[i]=0.0;
				}
				lockEvents--;
				pigmentGraph.setValues(d);
				pigmentGraph.paint(pigmentGraph.getGraphics());
			}
		}
	}

	class EventHandler implements ActionListener, TableModelListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==graphType) setGraph();
			else if (e.getSource()==doPigments) {
				// thePlank.getCurrentInstance().getTag("pigment").setValue(String.valueOf(doPigments.isSelected()));
				updatePig();
				setGraph();
				thePlank.vc2.unsaved(false);
			}
		}

		public void tableChanged(TableModelEvent e) {
			if (e.getSource()==dataTableModel) {
				if (lockEvents==0) {
					lockEvents++;
					String s = "\\graphvals{";
					for (int i=0; i<25; i++) {
						dataTable.setValueAt(String.valueOf(pigmentGraph.wavelengths[i]),i,0);
						dataTable.setValueAt(String.valueOf(pigmentGraph.wavelengths[i+1]),i,1);
						s+="{"+i+",";
						double d = 0.0;
						try {
							d = Double.parseDouble((String)dataTable.getValueAt(i,2));
						} catch (Exception ex) { ex.printStackTrace();}
						s+=String.valueOf(d)+"}";
						if (i<24) s+=",";
					}
					s+="}";

					if (graphType.getSelectedItem()!=null) {
						if (thePigment.getTagWhere("spectrum","name",graphType.getSelectedItem().toString())==null) {
							XMLTag newSpec = new XMLTag("spectrum");
							newSpec.addTag(new XMLTag("name",graphType.getSelectedItem().toString()));
							XMLTag newEq = new XMLTag("equation");
							newEq.addTag(new XMLTag("name",graphType.getSelectedItem().toString()));
							newEq.addTag(new XMLTag("eq","overwrite"));
							newSpec.addTag(newEq);
							thePigment.addTag(newSpec);
						}
						thePigment.getTagWhere("spectrum","name",graphType.getSelectedItem().toString()).getTag("equation/eq").setValue(s);
						thePlank.vc2.unsaved(false);
					}
					setGraph();
					thePlank.vc2.setEnabled(true);
					lockEvents--;
				}
			}
		}
	}
*/

}

