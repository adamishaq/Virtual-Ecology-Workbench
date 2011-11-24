package VEW.Planktonica2;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class PigmentPanel extends JPanel {


	private static final long serialVersionUID = 1040209636264981879L;
	
	private int lockEvents = 0;
	private DefaultTableModel dataTableModel = new DefaultTableModel();
	private JTable dataTable = new JTable(dataTableModel);
	private JComboBox graphType;
	public JCheckBox doPigments = new JCheckBox("Chemical has pigmentation?");
	private PigmentGraph pigmentGraph;
	private XMLTag thePigment;
	private Planktonica thePlank;
	public static final String Pigment_CHI = "Chi";
	public static final String Pigment_e = "e";
	protected static final int graphHeight = 260;
	protected static final int graphWidth = 580;
	private static final String[] colHeaders = new String[] {"Min W", "Max W", "Value"};


  public PigmentPanel() {
	  // TODO: pigment Panel
  }
  
  public PigmentPanel(Planktonica p) {
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
    final JScrollPane dataScroller = new JScrollPane(dataTable);
    dataScroller.setPreferredSize(new Dimension(150,250));
    add(dataScroller,"West");
    add(pigmentGraph,"Center");

    graphType.addActionListener(eh);
    doPigments.addActionListener(eh);
    dataTableModel.addTableModelListener(eh);

  }

  public void show(XMLTag pigment, XMLFile model) {
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

  class PigmentGraph extends JPanel {
    private double[] values;
    public double[] wavelengths = new double[] {300,357.5,387.5,412.5,437.5,462.5,487.5,512.5,537.5,562.5,587.5,612.5,
                                            637.5,662.5,687.5,712.5,737.5,787.5,900,1100,1300,1500,1700,1900,2100,2300};

    public Color RGBForWaveLength(double w) {
      double r=0;
      double b=0;
      double g=0;
      if ((w>=380) && (w<440)) { r=(440-w)/60; g=0; b=1; }
      else if ((w>=440) && (w<490)) { r=0; g=(w-440)/50; b=1; }
      else if ((w>=490) && (w<510)) { b=(510-w)/20; r=0; g=1; }
      else if ((w>=510) && (w<580)) { b=0; g=1; r=(w-510)/70; }
      else if ((w>=580) && (w<645)) { r=1; g=(645-w)/65; b=0; }
      else if ((w>=645) && (w<780)) { r=1; g=0; b=0; }
      if (w>700) r=r*(0.3+(0.7*((780-w)/80)));
      if ((w<420) && (w>=380)) { r = r*(0.3+(0.7*(w-380)/40)); b = (0.3+(0.7*(w-380)/40)); }
      r = r*255;
      b = b*255;
      g = g*255;
      if (!PigmentPanel.this.doPigments.isSelected()) {
        double avg = (r+b+g)/3;
        return new Color((int)avg,(int)avg,(int)avg);
      } else return new Color((int)r,(int)g,(int)b);
    }

    public PigmentGraph() { }
    public PigmentGraph(double[] d) { setValues(d); }
  
    public void setValues(double[] d) {
      values = new double[d.length];
      for (int i=0; i<d.length; i++) values[i]=d[i];
    }

    public void paint(Graphics g) {
      if (values!=null) {
        if (values.length>0) {
          g.setColor(Color.white);
          g.fillRect(0,0,PigmentPanel.graphWidth,PigmentPanel.graphHeight);
          int colWidth = (int) Math.floor(((PigmentPanel.graphWidth-30)/values.length));
          double min = values[0];
          double max = values[0];
 
        
          for (int i=1; i<values.length; i++) {
            if (values[i]<min) min=values[i];
            if (values[i]>max) max=values[i];
          }
          double yscaler = (PigmentPanel.graphHeight-20)/(max-min);
   
          for (int i=0; i<values.length; i++) {
            for (int x=0; x<colWidth; x++) {
              g.setColor(RGBForWaveLength(wavelengths[i]+((wavelengths[i+1]-wavelengths[i])*x/colWidth)));
              g.drawLine(20+x+(i*colWidth),(PigmentPanel.graphHeight-10)-(int)(yscaler*(values[i]-min)),20+x+(i*colWidth),(PigmentPanel.graphHeight-10));
            }
          }  
          g.setColor(Color.black);
          g.drawLine(15,(PigmentPanel.graphHeight-10),PigmentPanel.graphWidth-15,(PigmentPanel.graphHeight-10));
          g.drawLine(15,15,15,(PigmentPanel.graphHeight-10));
        }
      }
    }
  }
}

