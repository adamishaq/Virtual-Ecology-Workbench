package VEW.Scenario2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;


import VEW.Common.DateDialog;
import VEW.Common.JCheckBoxList;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.MiscUtils;
import VEW.Controller2.VEWController2;

public class ChemicalConservationDialog extends JPanel {
  private JCheckBox useCC = new JCheckBox("Use Chemical Recycling");
  public JButton startDate = new JButton();
  public JRadioButton autoDetectTime = new JRadioButton("Auto-detect quiet season for adjustment");
  public JRadioButton yearlyUpdate = new JRadioButton("Update yearly from start time");  
  public JRadioButton continualUpdate = new JRadioButton("Contiuous Adjustment");
  public ButtonGroup timeDetection = new ButtonGroup();
  public JRadioButton autoDetectDepth = new JRadioButton("Auto-detect start-depth");
  public JRadioButton fixedDepth = new JRadioButton("Use chemical below");
  public JCheckBox resetBelow = new JCheckBox("Reset values below recycle point");
  public JComboBox fixDepth = new JComboBox();
  public ButtonGroup depthDetection = new ButtonGroup();
  
  public JRadioButton deepestEver = new JRadioButton("Deepest Ever");
  public JRadioButton deepestYearly = new JRadioButton("Deepest Each Year");
  public ButtonGroup deepDetection = new ButtonGroup();
  
  private DateDialog dd;
  private GregorianCalendar theDate = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar startSim = new GregorianCalendar(DateDialog.GMTTimeZone); 
  private GregorianCalendar endSim = new GregorianCalendar(DateDialog.GMTTimeZone);
  private DefaultListModel chemListModel = new DefaultListModel();
  private JCheckBoxList chemList = new JCheckBoxList(chemListModel);
  private JCheckBox[] chems;
  private EventHandler eh = new EventHandler();
  private XMLTag theModel = null;
  private VEWController2 vc2;
  private int lockEvents=0;
  
  public boolean greenLight(boolean fix) {
    lockEvents++;
    StringBuffer errorString = new StringBuffer();
    MiscUtils.findDateLimits(theModel,startSim,endSim);
    
    boolean ok = true;
    if (theModel.getTag("chemrecycle")==null) {
      if (fix) {
        XMLTag newTag = new XMLTag("chemrecycle");
        newTag.addTag(new XMLTag("useCC","false"));
        newTag.addTag(new XMLTag("detecttime","false"));
        newTag.addTag(new XMLTag("yearlyupdate","true"));
        newTag.addTag(new XMLTag("detectdepth","true"));
        newTag.addTag(new XMLTag("deepever","true"));
        newTag.addTag(new XMLTag("depthtouse","100"));        
        newTag.addTag(new XMLTag("deepyear","false"));
        newTag.addTag(new XMLTag("fixdepth","false"));        
        newTag.addTag(new XMLTag("continuous","false"));
        newTag.addTag(new XMLTag("start",theModel.getValue("track/start")));
        theModel.addTag(newTag);
        errorString.append("Set up default chemical recycling\n");
      } else {
        ok = false;
      }
    }
    if (ok) {
      long date = Long.parseLong(theModel.getTag("chemrecycle").getValue("start"));
      if ((date<startSim.getTimeInMillis()) || (date>endSim.getTimeInMillis())) {
        if (fix) {
          theModel.getTag("chemrecycle/start").setValue(String.valueOf(startSim.getTimeInMillis()));
          errorString.append("Corrected invalid date for chemical recycling\n");
        } else {
          ok=false;
        }
      }
    }
    
    if (ok) {
      XMLTag[] _chems = theModel.getTag("chemrecycle").getTags("chem");
      for (int i=0; i<_chems.length; i++) {
        if (theModel.getTagWhere("chemical","name",_chems[i].getValue())==null) {
          if (fix) {
            errorString.append("Removed unknown chemical "+_chems[i].getValue()+" from recycling\n");
            _chems[i].removeTagFromParent();
          } else ok=false;
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
    return ok;
  }
  
  
  public ChemicalConservationDialog(JFrame vewController2, XMLTag model) {
    theModel = model;
    vc2 = (VEWController2) vewController2;
    dd = new DateDialog(vewController2,1800);
    JPanel mainPanel = new JPanel(new BorderLayout(2,2));
    JPanel switchOptions = new JPanel(new FlowLayout());
    switchOptions.add(useCC);
    useCC.addActionListener(eh);
    useCC.setEnabled(true);
    useCC.setSelected(false);
    autoDetectTime.setEnabled(false);
    yearlyUpdate.setEnabled(false);
    continualUpdate.setEnabled(false);
    fixedDepth.setEnabled(false);
    deepestEver.setEnabled(false);
    deepestYearly.setEnabled(false);
    fixDepth.setEnabled(false);
    autoDetectDepth.setEnabled(false);    
    
    
    switchOptions.add(new JLabel("from"));
    switchOptions.add(startDate);
    startDate.setText(DateDialog.getString(new GregorianCalendar()));
    
    timeDetection.add(autoDetectTime);
    timeDetection.add(yearlyUpdate);
    timeDetection.add(continualUpdate);
    depthDetection.add(autoDetectDepth);
    depthDetection.add(fixedDepth);
    
    
    deepDetection.add(deepestEver);
    deepDetection.add(deepestYearly);
    
    JPanel topOptions = new JPanel(new BorderLayout());
    switchOptions.setBorder(new EtchedBorder());
    topOptions.add(switchOptions,BorderLayout.NORTH);
    
    JPanel timeDetect = new JPanel(new GridLayout(3,1));
    timeDetect.add(autoDetectTime);
    timeDetect.add(yearlyUpdate);
    timeDetect.add(continualUpdate);
    timeDetect.setBorder(new EtchedBorder());
    topOptions.add(timeDetect,BorderLayout.CENTER);
    
    JPanel depthDetect = new JPanel(new BorderLayout());
    JPanel topDepthDetect = new JPanel(new FlowLayout());
    topDepthDetect.add(autoDetectDepth);
    topDepthDetect.add(deepestEver);
    topDepthDetect.add(deepestYearly);
    depthDetect.add(topDepthDetect,BorderLayout.NORTH);
    JPanel bottomDepthDetect = new JPanel(new FlowLayout());
    bottomDepthDetect.add(fixedDepth);
    bottomDepthDetect.add(fixDepth);
    bottomDepthDetect.add(resetBelow);
    depthDetect.add(bottomDepthDetect,BorderLayout.SOUTH);
    depthDetect.setBorder(new EtchedBorder());
    topOptions.add(depthDetect,BorderLayout.SOUTH);
    
    fixDepth.addItem(String.valueOf(0.0f));
    float power = -2;
    for (int i = 0; i < 20; i++) {
      fixDepth.addItem(String.valueOf(Math.pow(10, power)));
      power += 0.1f;
    }
    for (int i=1; i<500; i++)
      fixDepth.addItem(String.valueOf(i));
    autoDetectTime.setSelected(true);
    autoDetectDepth.setSelected(true);
    deepestYearly.setSelected(true);
    fixDepth.setEnabled(false);
    autoDetectDepth.addActionListener(eh);
    fixedDepth.addActionListener(eh);
    resetBelow.addActionListener(eh);
    startDate.setEnabled(false);
    startDate.addActionListener(eh);
    mainPanel.add(topOptions,BorderLayout.NORTH);
    JPanel chemOptions = new JPanel(new BorderLayout());
    chemOptions.add(new JLabel("Collect these chemicals"),BorderLayout.NORTH);
    chemOptions.add(chemList,BorderLayout.CENTER);
    mainPanel.add(chemOptions,BorderLayout.CENTER);
    chemOptions.setPreferredSize(new Dimension(400,300));
    setLayout(new BorderLayout(2,2));
    add(mainPanel,BorderLayout.CENTER);
    chemList.addMouseListener(eh);
  }
  
  public void init() {
    XMLTag conserveTag = theModel.getTag("chemrecycle");
    useCC.setSelected(StringTools.parseBoolean(conserveTag.getValue("useCC")));
    autoDetectTime.setSelected(StringTools.parseBoolean(conserveTag.getValue("detecttime")));
    yearlyUpdate.setSelected(StringTools.parseBoolean(conserveTag.getValue("yearlyupdate")));
    autoDetectDepth.setSelected(StringTools.parseBoolean(conserveTag.getValue("detectdepth")));
    deepestEver.setSelected(StringTools.parseBoolean(conserveTag.getValue("deepever")));
    deepestYearly.setSelected(StringTools.parseBoolean(conserveTag.getValue("deepyear")));
    fixedDepth.setSelected(StringTools.parseBoolean(conserveTag.getValue("fixdepth")));
    if (conserveTag.getValue("resetbelow")==null) {
      conserveTag.addTag(new XMLTag("resetbelow","false"));
    }
    resetBelow.setSelected(StringTools.parseBoolean(conserveTag.getValue("resetbelow")));
    continualUpdate.setSelected(StringTools.parseBoolean(conserveTag.getValue("continuous")));
    fixDepth.setSelectedIndex(Integer.parseInt(conserveTag.getValue("depthtouse")));
    theDate.setTimeInMillis(Long.parseLong(conserveTag.getValue("start")));
    startDate.setText(DateDialog.getString(theDate));
    chemListModel.removeAllElements();
    XMLTag[] theChems = theModel.getTags("chemical");
    chems = new JCheckBox[theChems.length];
    for (int i=0; i<theChems.length; i++) {
      chems[i] = new JCheckBox(theChems[i].getValue("name"));
      chemListModel.addElement(chems[i]);
      chems[i].addActionListener(eh);
    }
    
    for (int i=0; i<chems.length; i++) chems[i].setSelected(false);
    XMLTag[] chemTags = theModel.getTags("chemical");
    XMLTag[] chemsSelected = conserveTag.getTags("chemical");
    for (int i=0; i<chemTags.length; i++) {
      String chem = chemTags[i].getValue("name");
      boolean found=false;
      int j=0;
      while ((j<chemsSelected.length) && (!found)) {
        found = (chemsSelected[j++].getValue().equals(chem));
      }
      chems[i].setSelected(found);
    }
    updateButtons();
    
  }
  
  public void updateButtons() {
    boolean b = useCC.isSelected();
    startDate.setEnabled(b);
    for (int i=0; i<chems.length; i++) chems[i].setEnabled(b);
    autoDetectTime.setEnabled(b);
    yearlyUpdate.setEnabled(b);
    continualUpdate.setEnabled(b);
    fixedDepth.setEnabled(b);
    deepestEver.setEnabled(b && autoDetectDepth.isSelected());
    deepestYearly.setEnabled(b && autoDetectDepth.isSelected());
    fixDepth.setEnabled(b && fixedDepth.isSelected());
    autoDetectDepth.setEnabled(b);
  }
  
  public void saveChemRecycle() {
    XMLTag conserveTag = theModel.getTag("chemrecycle");
    conserveTag.getTag("useCC").setValue(String.valueOf(useCC.isSelected()));
    conserveTag.getTag("detecttime").setValue(String.valueOf(autoDetectTime.isEnabled() && autoDetectTime.isSelected()));        
    conserveTag.getTag("yearlyupdate").setValue(String.valueOf(yearlyUpdate.isEnabled() && yearlyUpdate.isSelected()));        
    conserveTag.getTag("detectdepth").setValue(String.valueOf(autoDetectDepth.isEnabled() && autoDetectDepth.isSelected()));        
    conserveTag.getTag("deepever").setValue(String.valueOf(deepestEver.isEnabled() && deepestEver.isSelected()));        
    conserveTag.getTag("deepyear").setValue(String.valueOf(deepestYearly.isEnabled() && deepestYearly.isSelected()));        
    conserveTag.getTag("fixdepth").setValue(String.valueOf(fixedDepth.isEnabled() && fixedDepth.isSelected()));        
    conserveTag.getTag("resetbelow").setValue(String.valueOf(resetBelow.isSelected()));
    conserveTag.getTag("depthtouse").setValue(String.valueOf(fixDepth.getSelectedIndex()));
    conserveTag.getTag("continuous").setValue(String.valueOf(continualUpdate.isEnabled() && continualUpdate.isSelected()));
    conserveTag.getTag("start").setValue(String.valueOf(theDate.getTimeInMillis()));
    while (conserveTag.getTag("chemical")!=null) conserveTag.getTag("chemical").removeFromParent();
    for (int i=0; i<chems.length; i++) {
      if (chems[i].isSelected()) {
        conserveTag.addTag(new XMLTag("chemical",theModel.getTags("chemical")[i].getValue("name")));
      }
    }
    vc2.unsaved(false);
  }
    
  
  class EventHandler implements ActionListener, MouseListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==startDate) {
        dd.show(startSim,endSim,theDate);
        theDate = dd.getDate();
        startDate.setText(DateDialog.getString(theDate));
        saveChemRecycle();
        
        
      } else if (e.getSource()==useCC) {
        updateButtons();
        saveChemRecycle();
        
      
      } else if (e.getSource()==autoDetectDepth) {
        updateButtons();
        saveChemRecycle();
        
      
      } else if (e.getSource()==fixedDepth) {
        updateButtons();
        saveChemRecycle();
        
      } else if (e.getSource()==resetBelow) {
        saveChemRecycle();
        
      } else if ((e.getSource() instanceof JCheckBox) && (lockEvents==0)) {
        saveChemRecycle();
        
      } 
    }

    public void mouseReleased(MouseEvent e) {
      if ((e.getSource()==chemList) && (lockEvents==0)) {
        lockEvents++;
        updateButtons();
        saveChemRecycle();
        lockEvents--;
      }
    }
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}

  }    
}
