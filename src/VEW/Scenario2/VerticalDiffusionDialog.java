package VEW.Scenario2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.MiscUtils;
import VEW.Controller2.VEWController2;

public class VerticalDiffusionDialog extends JPanel {
  private JCheckBox useVD = new JCheckBox("Use Vertical diffusion");
  private EventHandler eh = new EventHandler();
  private XMLTag theModel = null;
  private VEWController2 vc2;
  private int lockEvents=0;
  private DateDialog dd;
  private GregorianCalendar startSim = new GregorianCalendar();
  private GregorianCalendar endSim = new GregorianCalendar();
  
  /* Timing panel */
  
  private GregorianCalendar startTime = new GregorianCalendar(); 
  private GregorianCalendar endTime = new GregorianCalendar();
  private JButton startButton = new JButton("Start");
  private JButton endButton = new JButton("End");
  private JCheckBox yearlyRepeat = new JCheckBox("Repeat yearly");
  
  /* Depth panel */
  
  private JRadioButton fixTop = new JRadioButton("Fixed Top Depth"); 
  private JComboBox fixTopValue = new JComboBox();
  private JRadioButton automaticTop = new JRadioButton("Use deepest turbocline");
  private JComboBox automaticDefault = new JComboBox();
  private JComboBox bottomDepth = new JComboBox();
  private JTextField rate = new JTextField();
  
  /* Chemistry table */
  
  private ChemistryModel chemistryModel = new ChemistryModel();
  private JTable chemistryTable = new JTable(chemistryModel);

  public boolean greenLight(boolean fix) {
    lockEvents++;
    StringBuffer errorString = new StringBuffer();
    boolean ok = true;
    if (theModel.getTag("VerticalDiffusion")==null) {
      if (fix) {
        theModel.addTag(new XMLTag("VerticalDiffusion"));
        startButton.setText(DateDialog.getString(startSim));
        endButton.setText(DateDialog.getString(endSim));
        startTime.setTimeInMillis(startSim.getTimeInMillis());
        endTime.setTimeInMillis(endSim.getTimeInMillis());
        final XMLTag newTag = theModel.getTag("VerticalDiffusion");
        newTag.addTag(new XMLTag("useVD","false"));
        newTag.addTag(new XMLTag("fixTop","true"));
        newTag.addTag(new XMLTag("autoTop","false"));
        newTag.addTag(new XMLTag("autoDefault","150"));
        newTag.addTag(new XMLTag("bottomDepth","499"));
        newTag.addTag(new XMLTag("fixTopValue","150"));
        newTag.addTag(new XMLTag("startTime",String.valueOf(startTime.getTimeInMillis())));
        newTag.addTag(new XMLTag("endTime",String.valueOf(endTime.getTimeInMillis())));
        newTag.addTag(new XMLTag("yearlyRepeat","false"));
        newTag.addTag(new XMLTag("rate","0.1"));
        
        errorString.append("Set up default vertical diffusion\n");
      } else {
        ok = false;
      }
    }     
    
    if (ok) {
      final XMLTag vdTag = theModel.getTag("VerticalDiffusion");
      final long startTim = Long.parseLong(vdTag.getValue("startTime"));
      if ((startTim<startSim.getTimeInMillis()) || (startTim>endSim.getTimeInMillis())) {
        if (fix) {
          vdTag.getTag("startTime").setValue(String.valueOf(startSim.getTimeInMillis()));
          errorString.append("Fixed: Invalid start date for vertical diffusion\n");
        } else ok=false;
      }
    }
    
    if (ok) {
      final XMLTag vdTag = theModel.getTag("VerticalDiffusion");
      final long endTim = Long.parseLong(vdTag.getValue("startTime"));
      if ((endTim<startSim.getTimeInMillis()) || (endTim>endSim.getTimeInMillis())) {
        if (fix) {
          vdTag.getTag("endTime").setValue(String.valueOf(endSim.getTimeInMillis()));
          errorString.append("Fixed: Invalid end date for vertical diffusion\n");
        } else ok=false;
      }
    }

    // Check invalid depths
    
    if (ok) {
      final XMLTag vdTag = theModel.getTag("VerticalDiffusion");
      final int bottom = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
      final double fixtop = Double.parseDouble(vdTag.getValue("fixTopValue"));
      //bottomDepth, autoDefault
      if (fixtop>bottom) {
        if (fix) {
          vdTag.getTag("fixTopValue").setValue(String.valueOf(bottom));
          errorString.append("Fixed invalid depth for top of vertical diffusion\n");
        } else ok=false;
      }
      
      if (ok) {
        final double bd = Double.parseDouble(vdTag.getValue("bottomDepth"));
        //bottomDepth, autoDefault
        if (bd>bottom) {
          if (fix) {
            vdTag.getTag("bottomDepth").setValue(String.valueOf(bottom));
            errorString.append("Fixed invalid depth for bottom of vertical diffusion\n");
          } else ok=false;
        }
      }
      
      if (ok) {
        final double bd = Double.parseDouble(vdTag.getValue("autoDefault"));
        if (bd>bottom) {
          if (fix) {
            vdTag.getTag("autoDefault").setValue(String.valueOf(bottom));
            errorString.append("Fixed invalid default value for automatic depth detection\n");
          } else ok=false;
        }
      }
    }
    
    if (ok) {
      final XMLTag vdTag = theModel.getTag("VerticalDiffusion");
      int i=0;
      while ((i<vdTag.getTags("chem").length) && (ok)) {
        final String chem = vdTag.getTags("chem")[i].getAttribute("name");
        if (theModel.getTagWhere("chemical","name",chem)==null) {
          if (fix) {
            vdTag.getTags("chem")[i].removeFromParent();
            errorString.append("Removed "+chem+" - chemical not found\n");
          } else ok=false;
        } else i++;
      }
      if (ok) {
        final XMLTag[] chems = theModel.getTags("chemical");
        i=0;
        while ((i<chems.length) && (ok)) {
          if (vdTag.getTagWhere("chem","@name",chems[i].getValue("name"))==null) {
            if (fix) {
              XMLTag nc = new XMLTag("chem");
              final String chemName = theModel.getTags("chemical")[i].getValue("name");
              errorString.append("Adding default fix for "+chemName+"\n");
              nc.setAttribute("name",chemName);
              nc.setAttribute("fix","true");
              final String defaultVal = theModel.getTag("initialconditions").getTagWhere("field","@name",chemName).getValue();
              int comma = defaultVal.length();
              while (defaultVal.charAt(comma-1)!=',') comma--;
              nc.setAttribute("value",defaultVal.substring(comma));
              vdTag.addTag(nc);
            } else ok=false;
          }
          i++;
        }
      }
    }
    
    
    if (fix) {
      if (errorString.length()>5) {
        MessageBox.showMessage(errorString.toString(),this);
        vc2.unsaved(true);
      }
      init();
    }
    lockEvents--;
    if (fix) init();
    return ok;
  }
  
  
  public VerticalDiffusionDialog(JFrame vewController2, XMLTag model) {
    lockEvents++;
    theModel = model;
    MiscUtils.findDateLimits(theModel, startSim, endSim);
    vc2 = (VEWController2) vewController2;
    dd = new DateDialog(vewController2,1800);
    JPanel selectMePanel = new JPanel(new BorderLayout(2,2));
      JPanel powerSwitch = new JPanel(new FlowLayout());
      powerSwitch.add(useVD);
      selectMePanel.add(powerSwitch,BorderLayout.NORTH);
    JPanel mainPanel = new JPanel(new BorderLayout(2,2));
    mainPanel.setBorder(new EtchedBorder());
    final JPanel timeSwitches = new JPanel(new FlowLayout());
    timeSwitches.add(new JLabel("From"));
    timeSwitches.add(startButton);
    timeSwitches.add(new JLabel("to"));
    timeSwitches.add(endButton);
    timeSwitches.add(yearlyRepeat);
    mainPanel.add(timeSwitches,BorderLayout.NORTH);
    
    final JPanel depthSelection = new JPanel(new FlowLayout());
      final JPanel depthSelection2 = new JPanel(new GridLayout(2,1));
        depthSelection2.setBorder(new EtchedBorder());
        depthSelection2.add(automaticTop);
        final JPanel defaultVal = new JPanel(new FlowLayout());
          defaultVal.add(new JLabel("When > "));
          defaultVal.add(automaticDefault);
          defaultVal.add(new JLabel(" m "));
          depthSelection2.add(defaultVal,BorderLayout.SOUTH);
        depthSelection.add(depthSelection2);
        final JPanel depthSelection1 = new JPanel(new GridLayout(2,1));
          depthSelection1.add(fixTop);
          final JPanel topValPanel = new JPanel(new FlowLayout());
          topValPanel.add(fixTopValue);
          topValPanel.add(new JLabel(" m "));
          depthSelection1.setBorder(new EtchedBorder());
          depthSelection1.add(topValPanel);
          depthSelection.add(depthSelection1,BorderLayout.WEST);


      final JPanel depthSelection3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        depthSelection3.add(new JLabel("Bottom depth:"));
        depthSelection3.add(bottomDepth);
        depthSelection3.add(new JLabel("Proportion of difference per timestep:"));
        depthSelection3.add(rate);
        rate.setPreferredSize(new Dimension(80,20));
      
      final JPanel depthPanel = new JPanel(new BorderLayout());
        depthPanel.add(depthSelection,BorderLayout.CENTER);
        depthPanel.add(depthSelection3,BorderLayout.SOUTH);
      mainPanel.add(depthPanel,BorderLayout.CENTER);
      
      final JPanel chemSelection = new JPanel(new FlowLayout());
        final JScrollPane jsp = new JScrollPane(chemistryTable);
        jsp.setPreferredSize(new Dimension(500,350));
        chemSelection.add(jsp);
      mainPanel.add(chemSelection,BorderLayout.SOUTH);
      
    chemistryModel.setColumnCount(3);
    chemistryModel.setColumnIdentifiers(new String[] {"Chemical", "Value","Apply fix"});
    
    setLayout(new BorderLayout(2,2));
    selectMePanel.add(mainPanel,BorderLayout.CENTER);
    add(selectMePanel,BorderLayout.CENTER);
    final ButtonGroup depthChoice = new ButtonGroup();
    depthChoice.add(automaticTop);
    depthChoice.add(fixTop);
    fixTop.addActionListener(eh);
    automaticTop.addActionListener(eh);
    useVD.addActionListener(eh);
    startButton.addActionListener(eh);
    endButton.addActionListener(eh);
    rate.addCaretListener(eh);
    chemistryTable.addFocusListener(eh);
    lockEvents--;
  }
  
  public void init() {
    lockEvents++;
    fixTopValue.removeAllItems();
    bottomDepth.removeAllItems();
    automaticDefault.removeAllItems();
    final int layers = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
    
    for (int i=0; i<layers; i++) {
      fixTopValue.addItem(String.valueOf(i));
      bottomDepth.addItem(String.valueOf(i));
      automaticDefault.addItem(String.valueOf(i));
    }
    final XMLTag[] chems = theModel.getTags("chemical");
    final XMLTag vdTag = theModel.getTag("VerticalDiffusion");
    while (chemistryModel.getRowCount()>0) chemistryModel.removeRow(0);
    for (int i=0; i<chems.length; i++) {
      final XMLTag chemTag = vdTag.getTagWhere("chem","@name",chems[i].getValue("name"));
      chemistryModel.addRow(new Object[] {chemTag.getAttribute("name"),chemTag.getAttribute("value"),new Boolean(chemTag.getAttribute("fix").equals("true"))});
    }
    
    fixTop.setSelected(vdTag.getValue("fixTop").equals("true"));
    StringTools.setStringItem(automaticDefault,vdTag.getValue("autoDefault"));
    StringTools.setStringItem(fixTopValue,vdTag.getValue("fixTopValue"));
    StringTools.setStringItem(bottomDepth,vdTag.getValue("bottomDepth"));
    startTime.setTimeInMillis(Long.parseLong(vdTag.getValue("startTime")));
    endTime.setTimeInMillis(Long.parseLong(vdTag.getValue("endTime")));
    startButton.setText(DateDialog.getString(startTime));
    endButton.setText(DateDialog.getString(endTime));
    yearlyRepeat.setSelected(vdTag.getValue("yearlyRepeat").equals("true"));
    rate.setText(vdTag.getValue("rate"));
    useVD.setSelected(vdTag.getValue("useVD").equals("true"));
    updateButtons();
    lockEvents--;
  }
  
  public void updateButtons() {
    lockEvents++;
    boolean b = useVD.isSelected();
    fixTopValue.setEnabled(b && fixTop.isSelected());
    chemistryTable.setEnabled(b);
    bottomDepth.setEnabled(b);
    fixTop.setEnabled(b);
    automaticTop.setEnabled(b);
    startButton.setEnabled(b);
    endButton.setEnabled(b);
    yearlyRepeat.setEnabled(b);
    automaticDefault.setEnabled(b);
    rate.setEnabled(b);
    lockEvents--;
  }
  
  public void saveVD() {
    final XMLTag vdTag = theModel.getTag("VerticalDiffusion");
    vdTag.getTag("fixTop").setValue(String.valueOf(fixTop.isSelected()));
    vdTag.getTag("autoTop").setValue(String.valueOf(automaticTop.isSelected()));
    vdTag.getTag("autoDefault").setValue(automaticDefault.getSelectedItem().toString());
    vdTag.getTag("fixTopValue").setValue(fixTopValue.getSelectedItem().toString());
    vdTag.getTag("bottomDepth").setValue(bottomDepth.getSelectedItem().toString());
    vdTag.getTag("startTime").setValue(String.valueOf(startTime.getTimeInMillis()));
    vdTag.getTag("endTime").setValue(String.valueOf(endTime.getTimeInMillis()));
    vdTag.getTag("yearlyRepeat").setValue(String.valueOf(yearlyRepeat.isSelected()));
    vdTag.getTag("rate").setValue(String.valueOf(rate.getText()));
    vdTag.getTag("useVD").setValue(String.valueOf(useVD.isSelected()));
    for (int i=0; i<chemistryTable.getRowCount(); i++) {
      final XMLTag theChem = vdTag.getTagWhere("chem","@name",chemistryTable.getValueAt(i,0).toString());
      theChem.setAttribute("fix",String.valueOf(chemistryTable.getValueAt(i,2)));
      theChem.setAttribute("value",String.valueOf(chemistryTable.getValueAt(i,1)));
    }
    vc2.unsaved(false);
  }
  
  public static void compile(PrintWriter PW, XMLTag vdTag) {
    if (vdTag!=null) {
      if (vdTag.getValue("useVD").equals("true")) {
        PW.println("  /****************************************************/");
        PW.println("  /* Code here generated by: Vertical Diffusion Module /");
        PW.println("  /****************************************************/");
        PW.println("");
        PW.println("  public static void interpolate(WaterCol W, int chemical, int layer1, int layer2) {");
        PW.println("    for (int i=layer1; i<layer2; i++) {");
        PW.println("      final double diff = (W.B_Layer[i+1].Chemicals[chemical].concentration.getLatest()");
        PW.println("                         - W.B_Layer[i].Chemicals[chemical].concentration.getLatest())");
        PW.println("                          * "+vdTag.getValue("rate")+";");
        PW.println("      W.B_Layer[i].Chemicals[chemical].concentration.setDelta(diff);");
        PW.println("      W.B_Layer[i+1].Chemicals[chemical].concentration.setDelta(-diff);");
        PW.println("    }");
        PW.println("  }");
        PW.println("");
        PW.println("  public static void VerticalDiffusion(WaterCol _W) {");
        if (vdTag.getValue("yearlyRepeat").equals("true")) 
          PW.println("    if (Logging.isYearlyTime(1,"+vdTag.getValue("startTime")+"L,"+vdTag.getValue("endTime")+"L)) {");
        else
          PW.println("    if (Logging.isTime(1,"+vdTag.getValue("startTime")+"L,"+vdTag.getValue("endTime")+"L)) {");
        String topLayer;
        if (vdTag.getValue("fixTop").equals("true")) topLayer = vdTag.getValue("fixTopValue");
        else topLayer = "_W.deepestTurboclineEver.getLatest()";
        String bottomLayer = vdTag.getValue("bottomDepth");
        final XMLTag[] chems = vdTag.getTags("chem");
        for (int i=0; i<chems.length; i++) {
          if (chems[i].getAttribute("fix").equals("true")) {
            String chemName = StringTools.nonSpace(chems[i].getAttribute("name"));
            PW.print("      _W.B_Layer["+bottomLayer+"].Chemicals["+chemName+".ID_"+chemName+"].");
            PW.println("concentration.setNow("+chems[i].getAttribute("value")+");");
            PW.println("      interpolate(_W,"+chemName+".ID_"+chemName+", "+topLayer+", "+bottomLayer+");");
            PW.print("      _W.B_Layer["+bottomLayer+"].Chemicals["+chemName+".ID_"+chemName+"].");
            PW.println("concentration.setNow("+chems[i].getAttribute("value")+");");
          }
        }
        PW.println("    }");
        PW.println("  }");
      }
    }
  }
    
  
  class EventHandler implements ActionListener, CaretListener, FocusListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      final Object src = e.getSource();
      if ((src==automaticTop) || (src==fixTop) || (src==useVD)) {
        updateButtons();
        saveVD();
      } else if (src==startButton) {
        dd.show(startSim,endTime,startTime);
        startTime.setTimeInMillis(dd.getDate().getTimeInMillis());
        startButton.setText(DateDialog.getString(startTime));
        saveVD();
        
        
      } else if (src==endButton) {
        dd.show(startTime,endSim,endTime);
        startTime.setTimeInMillis(dd.getDate().getTimeInMillis());
        endButton.setText(DateDialog.getString(endTime));
        saveVD();
        
      }
    }

    public void caretUpdate(CaretEvent e) {
      if ((e.getSource()==rate) && (lockEvents==0)) {
        saveVD();
      }
    }
    public void focusGained(FocusEvent e) {}
    public void focusLost(FocusEvent e) {
      if ((e.getSource()==chemistryTable) && (lockEvents==0)) {
        for (int i=0; i<chemistryTable.getRowCount(); i++) {
          try { Double.parseDouble(chemistryTable.getValueAt(i,1).toString()); }
          catch (Exception ex) { chemistryTable.setValueAt("0.0",i,1); }
        }
        saveVD();
      }
    }
  }
  
  class ChemistryModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) { return (col>=1); }
    public Class getColumnClass(int c) { 
      if (c<=1) return String.class;
      else return Boolean.class;
    }
  }
}
