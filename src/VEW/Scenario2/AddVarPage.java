package VEW.Scenario2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class AddVarPage extends JDialog {
  private int opMode;

  private JTextField avwName;
  private JTextField avwDesc;
  private JTextField avwVal;
  private JTextField avwHist;
  private JComboBox avwType;
  private JComboBox avwVarConcTie;
  private String avwUnit;
  private JButton unitButton;
  private JEditorPane avwEqPanel = new JEditorPane();
  private JButton avwOK = new JButton("OK");
  private JButton avwCancel = new  JButton("Cancel");
  private UnitEditor theUnitEditor = null;
  private XMLTag theModel = null;
  private XMLTag theFunction = null;
  private XMLTag theGroup = null;
  private XMLTag theTag = null;

  public static final int EDITING = 1;
  public static final int ADDING = 2;
  public String theSelection = "";
  public final static int VARIABLE = 1;
  public final static int PARAMETER = 2;
  public final static int LOCALVAR = 4;
  public final static int VARPARAM = 16;
  public final static int VARCONC = 32;
  public final static int VARVAR = 64;
  public final static int VARLOCAL = 128;
  public final static int ALL = 255;
  
  private final static String GROUP_VARIABLE = new String("Group Variable");
  private final static String GROUP_PARAMETER = new String("Group Parameter");
  private final static String LOCAL_VAR = new String("Local Variable");
  private final static String VARIETY_PARAM = new String("Food-based Parameter");
  private final static String VARIETY_CONC = new String("Food Set/Concentration");
  private final static String VARIETY_STATE = new String("Food-based Variable");
  private final static String VARIETY_LOCAL = new String("Food-based Local");
  
  public EventHandler eh = new EventHandler();
  
  
  public AddVarPage(JDialog jd) {
    super(jd,"Add Variable",true);
    init();
  }

  private void init() {
    theUnitEditor = new UnitEditor(this);
    getContentPane().setLayout(new BorderLayout(2,2));
    final JPanel labelsGrid = new JPanel(new GridLayout(8,2));
    avwName = new JTextField(15);
    avwDesc = new JTextField(15);
    avwVal = new JTextField(15);
    avwHist = new JTextField(15);
    avwType = new JComboBox();
    avwVarConcTie = new JComboBox();
    unitButton = new JButton("Edit Units");
    labelsGrid.add(new JLabel("Type/Scope"));
    labelsGrid.add(avwType);
    labelsGrid.add(new JLabel("Variable Name"));
    labelsGrid.add(avwName);
    labelsGrid.add(new JLabel("Description"));
    labelsGrid.add(avwDesc);
    labelsGrid.add(new JLabel("Initial Value"));
    labelsGrid.add(avwVal);
    labelsGrid.add(new JLabel("History Size"));
    labelsGrid.add(avwHist);
    labelsGrid.add(new JLabel("Food-Set Link"));
    labelsGrid.add(avwVarConcTie);
    labelsGrid.add(unitButton);
    avwEqPanel.setContentType("text/html");
    avwEqPanel.setEditable(false);
    labelsGrid.add(avwEqPanel);
    labelsGrid.add(avwCancel);
    labelsGrid.add(avwOK);
    getContentPane().add(labelsGrid,"Center");
    pack();
    avwOK.setEnabled(false);
    unitButton.addActionListener(eh); 
    avwName.addCaretListener(eh);
    avwVal.addCaretListener(eh);
    avwDesc.addCaretListener(eh);
    avwHist.addCaretListener(eh);
    avwCancel.addActionListener(eh);
    avwOK.addActionListener(eh);
    avwType.addActionListener(eh);
    avwVarConcTie.addActionListener(eh);
  }

  public String getVName() { return avwName.getText(); }
  public String getDesc() { return avwDesc.getText(); }
  public String getValue() { return avwVal.getText(); }
  public String getUnits() { return avwUnit; }
  public String getTypeItem() { return (String) avwType.getSelectedItem(); }
  public void setUnits(String s) { 
    avwUnit = s;
    avwEqPanel.setText("<html>dimensionless</html>");
  }
  
  public void checkButtons() {
    String s;
    if (avwType.getSelectedItem()==null) s = "";
    else s = (String) avwType.getSelectedItem();
    avwVarConcTie.setEnabled((s.equals(VARIETY_PARAM))||(s.equals(VARIETY_STATE))||(s.equals(VARIETY_LOCAL)));
    avwHist.setEnabled((!s.equals(VARIETY_PARAM)) && (!s.equals(GROUP_PARAMETER)) && (!s.equals(LOCAL_VAR)) && (!s.equals(VARIETY_LOCAL))
                        && (!s.equals(VARIETY_CONC)));        
    
    avwVal.setEnabled(!s.equals(LOCAL_VAR) && (!s.equals(VARIETY_CONC)) && (!s.equals(VARIETY_LOCAL)));
    boolean textbits = ((avwName.getText().length()*avwDesc.getText().length())>0);
    boolean historyok = ((avwHist.getText().length()>0) || (!avwHist.isEnabled()));
    boolean valok = ((avwVal.getText().length()>0) || (!avwVal.isEnabled()));
    avwOK.setEnabled((textbits) && (historyok) && (valok) && ((!avwVarConcTie.isEnabled() || (avwVarConcTie.getSelectedIndex()>=0))));
  }


  public void populateVarConc() {
    avwVarConcTie.removeAllItems();
    int i=1;
    XMLTag t = theGroup.getTag("varietyconcentration",i);
    while (t!=null) {
      if (!t.getValue("name").equals("Ingestion")) avwVarConcTie.addItem(t.getValue("name"));
      t = theGroup.getTag("varietyconcentration",++i);
    }
  }

  
  public void addNewVar(XMLTag model, XMLTag function, XMLTag group, int type, String defName) {
    theModel = model;
    theFunction = function;
    theGroup = group;
    opMode = ADDING;
    avwType.removeAllItems();
    if ((type & VARIABLE) > 0) avwType.addItem(GROUP_VARIABLE);
    if ((type & PARAMETER) > 0) avwType.addItem(GROUP_PARAMETER);
    if ((type & LOCALVAR) > 0) avwType.addItem(LOCAL_VAR);
    if ((type & VARPARAM) > 0) avwType.addItem(VARIETY_PARAM);
    if ((type & VARCONC) > 0) avwType.addItem(VARIETY_CONC);
    if ((type & VARVAR) > 0) avwType.addItem(VARIETY_STATE);
    if ((type & VARLOCAL) > 0) avwType.addItem(VARIETY_LOCAL);
    setTitle("Add Variable");
    avwName.setText(defName);
    avwName.setEditable(true);
    avwDesc.setText(defName);
    avwVal.setText("0");
    avwHist.setText("1");
    avwUnit = "0,0,0";
    avwEqPanel.setText("<html>dimensionless</html>");
    populateVarConc();
    setVisible(true);
    repaint();
  }

  public void editThisVar(XMLTag model, XMLTag function, XMLTag group, XMLTag tag, int type) {
    opMode = EDITING;
    theModel = model;
    theFunction = function;
    theGroup = group;
    avwType.removeAllItems();
    if ((type & VARIABLE) > 0) avwType.addItem(GROUP_VARIABLE);
    if ((type & PARAMETER) > 0) avwType.addItem(GROUP_PARAMETER);
    if ((type & LOCALVAR) > 0) avwType.addItem(new String(LOCAL_VAR));
    if ((type & VARPARAM) > 0) avwType.addItem(new String(VARIETY_PARAM));
    if ((type & VARCONC) > 0) avwType.addItem(new String(VARIETY_CONC));
    if ((type & VARVAR) > 0) avwType.addItem(new String(VARIETY_STATE));
    if ((type & VARLOCAL) > 0) avwType.addItem(new String(VARIETY_LOCAL));
    setTitle("Edit Variable");
    theTag = tag;
    avwName.setText(theTag.getValue("name"));
    avwName.setEditable(false);
    avwDesc.setText(theTag.getValue("desc"));
    avwVal.setText(theTag.getValue("value"));
    avwHist.setText(theTag.getValue("hist"));
    if (avwVal.getText().equals("")) avwVal.setText("0");
    if (avwHist.getText().equals("")) avwHist.setText("0");
    avwUnit = theTag.getValue("unit");
    if (theTag.getName().equals("variable")) StringTools.setStringItem(avwType,GROUP_VARIABLE);
    else if (theTag.getName().equals("parameter")) StringTools.setStringItem(avwType,GROUP_PARAMETER);
    else if (theTag.getName().equals("local")) StringTools.setStringItem(avwType,LOCAL_VAR);
    else if (theTag.getName().equals("varietyparameter")) StringTools.setStringItem(avwType,VARIETY_PARAM);
    else if (theTag.getName().equals("varietyconcentration")) StringTools.setStringItem(avwType,VARIETY_CONC);
    else if (theTag.getName().equals("varietyvariable")) StringTools.setStringItem(avwType,VARIETY_STATE);
    else if (theTag.getName().equals("varietylocal")) StringTools.setStringItem(avwType,VARIETY_LOCAL);
    
    avwVarConcTie.setEnabled((theTag.getName().equals("varietyparameter"))||(theTag.getName().equals("varietyvariable")));
    populateVarConc();
    avwVarConcTie.setSelectedItem(theTag.getAttribute("link"));
    avwEqPanel.setText("<html>"+UnitEditor.HTMLFor(avwUnit)+"</html>");
    setVisible(true);
    repaint();
  }
  
  class EventHandler implements ActionListener, CaretListener {
    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {

      if (e.getSource()==unitButton) {
        theUnitEditor.setUnitEditor(avwUnit,theModel);
        theUnitEditor.setVisible(true);
        avwUnit = theUnitEditor.getUnit();
        avwEqPanel.setText(UnitEditor.HTMLFor(avwUnit));

      } else if ((e.getSource()==avwType) || (e.getSource()==avwVarConcTie)) {
        checkButtons();
     
      } else if (e.getSource()==avwCancel) {
        setVisible(false);
     
      } else if (e.getSource()==avwOK) {
        XMLTag newVarTag = null;
        boolean found = false;
        
        if (theFunction!=null) {
          XMLTag[] vars = theFunction.getTags();
          for (int i=0; i<vars.length; i++) {
            if ((vars[i].getTag("name")!=null) && (vars[i].getValue("name").equals(avwName.getText()))) found = true;
          }
        }
        if (theGroup!=null) {
          XMLTag[] vars = theGroup.getTags();
          for (int i=0; i<vars.length; i++) {
            if ((vars[i].getTag("name")!=null) && (vars[i].getValue("name").equals(avwName.getText()))) found = true;
          }
        }
        if ((found) && (opMode!=EDITING)) {
          JOptionPane.showMessageDialog(AddVarPage.this,"That name is already in use. Please choose another");
        } else {
          String type = (String) avwType.getSelectedItem();
          if (type.equals(GROUP_VARIABLE)) newVarTag = new XMLTag("variable");
          else if (type.equals(GROUP_PARAMETER)) newVarTag = new XMLTag("parameter");
          else if (type.equals(LOCAL_VAR)) newVarTag = new XMLTag("local");
          else if (type.equals(VARIETY_PARAM)) newVarTag = new XMLTag("varietyparameter");
          else if (type.equals(VARIETY_CONC)) newVarTag = new XMLTag("varietyconcentration");
          else if (type.equals(VARIETY_STATE)) newVarTag = new XMLTag("varietyvariable");
          else if (type.equals(VARIETY_LOCAL)) newVarTag = new XMLTag("varietylocal");
          newVarTag.addTag("name",avwName.getText());
          newVarTag.addTag("desc",avwDesc.getText());
          newVarTag.addTag("value",avwVal.getText());
          newVarTag.addTag("hist",avwHist.getText());
          newVarTag.addTag("unit",avwUnit);
          if ((avwVarConcTie.isEnabled()) && (avwVarConcTie.getSelectedItem()!=null))
            newVarTag.setAttribute("link",(String)avwVarConcTie.getSelectedItem());
          if (opMode==EDITING) theTag.removeFromParent();
          if (type.equals(GROUP_VARIABLE)) theGroup.addTag(newVarTag);        
          else if (type.equals(GROUP_PARAMETER)) theGroup.addTag(newVarTag);
          else if (type.equals(LOCAL_VAR)) theGroup.addTag(newVarTag);
          else if (type.equals(VARIETY_PARAM)) theGroup.addTag(newVarTag);
          else if (type.equals(VARIETY_CONC)) theGroup.addTag(newVarTag);
          else if (type.equals(VARIETY_STATE)) theGroup.addTag(newVarTag);
          else if (type.equals(VARIETY_LOCAL)) theGroup.addTag(newVarTag);
          theSelection=type+"/"+avwName.getText();
          setVisible(false);
        }
      }
    }

    public void caretUpdate(CaretEvent e) {
      if ((e.getSource()==avwName) || (e.getSource()==avwVal) || (e.getSource()==avwDesc) || (e.getSource()==avwHist)) {
        checkButtons();
      }
    }
  }
}