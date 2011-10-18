package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class ParticleManager2 extends JPanel {
  RuleTableModel ruleModel = new RuleTableModel();
  JTable ruleTable = new JTable(ruleModel);
  JButton removeRule = new JButton("Remove Rule");
  XMLTag model = null;
  EventHandler eh = new EventHandler();
  int lockEvents = 0;
  JComboBox species = new JComboBox();
  JComboBox stage = new JComboBox();
  JComboBox rule = new JComboBox();
  JLabel ruleDescription = new JLabel();
  JTextField splitMergeValue = new JTextField(8);
  JComboBox maintainRule = new JComboBox();
  JButton addRule = new JButton("Add Rule");
  JButton overwriteRule = new JButton("Overwrite Rule");  
  JRadioButton inColumn = new JRadioButton("in Column");
  JRadioButton perMetre = new JRadioButton("per metre");
  JRadioButton inML = new JRadioButton("avg per metre in Mixing Layer");
  JCheckBox continuous = new JCheckBox("Continuous Management");
  JButton fromDate = new JButton("From Date");
  JButton toDate = new JButton("To Date");
  JCheckBox allDepths = new JCheckBox("Whole of column");
  JComboBox fromDepth = new JComboBox();
  JComboBox toDepth = new JComboBox();
  JCheckBox fromTurbocline = new JCheckBox("Use Turbocline");
  JCheckBox toTurbocline = new JCheckBox("Use Turbocline");
  JCheckBox everyYear = new JCheckBox("execute every year");
  ButtonGroup pmScope = new ButtonGroup();
  ArrayList functions = new ArrayList();
  ArrayList ruleList = new ArrayList();
  private GregorianCalendar startDate = new GregorianCalendar();
  private GregorianCalendar endDate = new GregorianCalendar();
  private GregorianCalendar startSim = new GregorianCalendar();
  private GregorianCalendar endSim = new GregorianCalendar();
  private GregorianCalendar anyDate = new GregorianCalendar();  
  private VEWController2 vc2;
  private DateDialog dd;
  
  public static final String RULE_MAINTAIN = new String("Maintain");
  public static final String RULE_SPLIT = new String("Split");
  public static final String RULE_MERGE = new String("Merge");
  public static final String RULE_LAYER = new String("Layer");
  public static final String RULE_COLUMN = new String("Column");  
  public static final String RULE_MIXING = new String("Mixing");
  

  public boolean greenLight(boolean fix) {
    lockEvents++;
    MiscUtils.findDateLimits(model, startSim, endSim);
    boolean ok = true;
    StringBuffer errorString = new StringBuffer();
    if (model.getTag("particlemanagement")==null) {
      if (fix) {
        model.addTag(new XMLTag("particlemanagement",""));
        errorString.append("Added particle management table\n");
      } else {
        ok=false;
      }
    }
    if (ok) {
      XMLTag[] rules = model.getTag("particlemanagement").getTags("rule");
      for (int i=0; i<rules.length; i++) {
        String _species = rules[i].getAttribute("species");
        String _stage = rules[i].getAttribute("stage");
        XMLTag theSpecies = model.getTag("species").getTagWhere("species","@name",_species);
        if (theSpecies==null) {
          if (fix) {
            errorString.append("Removing "+rules[i].getAttribute("type")+" rule for "+_stage+" "+_species+" - species not found\n");
            rules[i].removeFromParent();
          } else {
            ok=false;
            i=rules.length;
          }
        } else {
          if (ok) {
            XMLTag fg = model.getTagWhere("functionalgroup","name",theSpecies.getAttribute("fg"));
            if ((!rules[i].getAttribute("type").equals(RULE_MAINTAIN)) && (fg.getTagWhere("stage","name",_stage)==null)) {
              if (fix) {
                errorString.append("Removing "+rules[i].getAttribute("type")+" rule for "+_stage+" "+_species+" - stage not found\n");
                rules[i].removeFromParent();
                
              } else {
                ok=false;
                i=rules.length;
              }
            }
          }
          if (ok) {
            if (rules[i].getAttribute("alltime").equals("false")) {
              long startMillis = Long.parseLong(rules[i].getAttribute("start"));
              long endMillis = Long.parseLong(rules[i].getAttribute("end"));
              long startSimMillis = startSim.getTimeInMillis();
              long endSimMillis = endSim.getTimeInMillis();
              if ((startMillis<startSimMillis) || (startMillis>endSimMillis)) {
                if (fix) {
                  rules[i].setAttribute("start",String.valueOf(startSimMillis));
                  errorString.append("Fix start date in "+rules[i].getAttribute("type")+" rule for "+_stage+" "+_species+"\n");
                } else {
                  ok=false;
                  i=rules.length;
                }
              }
              if ((endMillis<startSimMillis) || (endMillis>endSimMillis)) {
                if (fix) {
                  rules[i].setAttribute("end",String.valueOf(endSimMillis));
                  errorString.append("Fix end date in "+rules[i].getAttribute("type")+" rule for "+_stage+" "+_species+"\n");
                } else {
                  ok=false;
                  i=rules.length;
                }
              }
            }
          }
        }   
      }
    }
    if (fix) {
      if (errorString.length()>5) {
        MessageBox.showMessage(errorString.toString(),this);
        vc2.unsaved(true);
      }
      initialise();
    }
    lockEvents--;
    return ok;
  }
    
  
  public ParticleManager2(JDialog jd, XMLTag _model) {
    vc2 = (VEWController2) jd;
    dd = new DateDialog(jd,1800); // 1800 only a temp.
    model = _model;
    setLayout(new BorderLayout());
    ruleModel.setColumnCount(8);
    ruleModel.setColumnIdentifiers(new String[] { "Plankton Type", "Rule", "Criteria", "Scope","From","To","Top","Bottom"});
    ruleTable.getColumnModel().getColumn(0).setPreferredWidth(100);
    ruleTable.getColumnModel().getColumn(1).setPreferredWidth(40);
    ruleTable.getColumnModel().getColumn(3).setPreferredWidth(40);
    ruleTable.getColumnModel().getColumn(4).setPreferredWidth(100);
    ruleTable.getColumnModel().getColumn(5).setPreferredWidth(100);
    ruleTable.getColumnModel().getColumn(6).setPreferredWidth(30);
    ruleTable.getColumnModel().getColumn(7).setPreferredWidth(30);
    JScrollPane ruleScroller = new JScrollPane(ruleTable);
    ruleScroller.setPreferredSize(new Dimension(400, 200));
    JPanel rulePanel = new JPanel(new BorderLayout());
    JPanel allRuleInfo = new JPanel(new BorderLayout());
    JPanel ruleSpeciesStage = new JPanel(new FlowLayout());
    ruleSpeciesStage.add(rule);
    ruleSpeciesStage.add(species);
    ruleSpeciesStage.add(stage);
    allRuleInfo.add(ruleSpeciesStage,BorderLayout.NORTH);
        JPanel valuePanel = new JPanel(new FlowLayout());
    valuePanel.add(ruleDescription);
    valuePanel.add(maintainRule);
    valuePanel.add(splitMergeValue);
    JPanel scopePanel = new JPanel(new BorderLayout());
    scopePanel.add(inColumn,BorderLayout.NORTH);
    scopePanel.add(inML,BorderLayout.CENTER);
    scopePanel.add(perMetre,BorderLayout.SOUTH);
    inColumn.setSelected(true);
    valuePanel.add(scopePanel);
    pmScope.add(inColumn);
    pmScope.add(perMetre);
    pmScope.add(inML);
    allRuleInfo.add(valuePanel,BorderLayout.CENTER);
    
    
    JPanel dateAndDepth = new JPanel(new BorderLayout());
    JPanel dateWindow = new JPanel(new FlowLayout());
    dateWindow.add(continuous);
    dateWindow.add(new JLabel("or, apply from "));
    dateWindow.add(fromDate);
    dateWindow.add(new JLabel("to "));
    dateWindow.add(toDate);
    dateWindow.add(everyYear);
    dateAndDepth.add(dateWindow,BorderLayout.NORTH);    
    JPanel depthWindow = new JPanel(new FlowLayout());
    depthWindow.add(allDepths);
    depthWindow.add(new JLabel("or, between depths "));
    depthWindow.add(fromDepth);
    depthWindow.add(fromTurbocline);
    depthWindow.add(new JLabel(" and "));
    depthWindow.add(toDepth);
    depthWindow.add(toTurbocline);
    dateAndDepth.add(depthWindow,BorderLayout.SOUTH);
    dateAndDepth.setBorder(new EtchedBorder());
    
    allRuleInfo.add(dateAndDepth,BorderLayout.SOUTH);
        
    rulePanel.add(allRuleInfo, BorderLayout.CENTER);
    JPanel saveExitPanel = new JPanel(new FlowLayout());
    saveExitPanel.add(addRule);
    saveExitPanel.add(overwriteRule);
    rulePanel.add(saveExitPanel, BorderLayout.SOUTH);
       
    add(rulePanel,BorderLayout.NORTH);
    add(ruleScroller, BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(removeRule);
    add(buttonPanel, BorderLayout.SOUTH);
    removeRule.setEnabled(false);
    removeRule.addActionListener(eh);
    ruleTable.getSelectionModel().addListSelectionListener(eh);
    ruleTable.getColumnModel().getSelectionModel().addListSelectionListener(eh);
    addRule.setEnabled(false);
    overwriteRule.setEnabled(false);
    rule.addActionListener(eh);
    species.addActionListener(eh);
    stage.addActionListener(eh);
    addRule.addActionListener(eh);
    overwriteRule.addActionListener(eh);
    splitMergeValue.addCaretListener(eh);
    toTurbocline.addActionListener(eh);
    fromTurbocline.addActionListener(eh);
    allDepths.addActionListener(eh);
    continuous.addActionListener(eh);
    fromDate.addActionListener(eh);
    toDate.addActionListener(eh);
    everyYear.addActionListener(eh);
  }


  public void initialise() {
    int secstep = Integer.parseInt(model.getValue("track/secstep"));
    dd.setSecStep(secstep);    
    lockEvents++;
    dd.setEarliest(startSim);
    dd.setLatest(endSim);
    XMLTag[] speciesTags = model.getTag("species").getTags("species");
    species.removeAllItems();
    for (int i = 0; i < speciesTags.length; i++) {
     species.addItem(speciesTags[i].getAttribute("name"));
    }
    rule.removeAllItems();
    startDate.setTimeInMillis(startSim.getTimeInMillis());
    endDate.setTimeInMillis(endSim.getTimeInMillis());
    fromDate.setText(DateDialog.getString(startSim));
    toDate.setText(DateDialog.getString(endSim)); 
    for (int i=0; i<500; i++) {
      fromDepth.addItem(String.valueOf(i));
      toDepth.addItem(String.valueOf(i));
    }
    fromDepth.setSelectedIndex(0);
    toDepth.setSelectedIndex(toDepth.getItemCount()-1);
    rule.addItem("Split");
    rule.addItem("Merge");
    rule.addItem("Maintain");
    updateTable();
    updateButtons();        
    speciesListClicked();
    ruleListClicked();
    lockEvents--;
  }

  public void addRuleToTable(XMLTag pmr) {
    String[] data = new String[8];
    data[0] = "";
    String ruleType = pmr.getAttribute("type");
    
    if (!ruleType.equals(RULE_MAINTAIN)) data[0] = pmr.getAttribute("stage")+ " ";
    data[0] = data[0] + pmr.getAttribute("species");
    data[1] = ruleType;
    if (ruleType.equals(RULE_MAINTAIN)) {
      data[2] = pmr.getAttribute("rule");
      data[3] = "N/A";
    } else {
      data[2] = pmr.getAttribute("value");
      data[3] = pmr.getAttribute("scope");
    }
    if (pmr.getAttribute("alltime").equals("true")) anyDate.setTimeInMillis(startSim.getTimeInMillis());
    else anyDate.setTimeInMillis(Long.parseLong(pmr.getAttribute("start")));
    data[4] = DateDialog.getString(anyDate);
    if (pmr.getAttribute("alltime").equals("true")) anyDate.setTimeInMillis(endSim.getTimeInMillis());
    else anyDate.setTimeInMillis(Long.parseLong(pmr.getAttribute("end")));
    data[5] = DateDialog.getString(anyDate);
    if (pmr.getAttribute("alldepth")!=null) {
      if (pmr.getAttribute("alldepth").equals("true")) {
        data[6] = "0";
        data[7] = "499";
      } else {
        data[6] = pmr.getAttribute("top");
        data[7] = pmr.getAttribute("bottom");
        if (pmr.getAttribute("toturbo").equals("true")) data[7]="Turbocline";
        if (pmr.getAttribute("fromturbo").equals("true")) data[6]="Turbocline";        
      }
    }
    ruleModel.addRow(data);
  }

  public void updateTable() {
    lockEvents++;
    XMLTag[] rules = model.getTag("particlemanagement").getTags("rule");
    while (ruleModel.getRowCount()>0) ruleModel.removeRow(0);
    for (int i = 0; i < rules.length; i++) addRuleToTable(rules[i]);
    updateButtons();
    lockEvents--;
  }

  public void updateButtons() {
    lockEvents++;
    int rows = ruleTable.getSelectedRowCount();
    if (rows>1) removeRule.setText("Remove Rules");
    if (rows==1) removeRule.setText("Remove Rule");
    removeRule.setEnabled(rows >= 1);  
    boolean readyToAdd = false;
    boolean gotNumber = true;

    try {
      Integer.parseInt(splitMergeValue.getText());
    } catch (NumberFormatException e) {
      gotNumber = false;
    }
    String ruleName = rule.getSelectedItem().toString();
    if ((ruleName.equals(RULE_SPLIT)) || (ruleName.equals(RULE_MERGE))) {
      if ((species.getSelectedIndex() >= 0) && (stage.getSelectedIndex() >= 0) && (gotNumber)) readyToAdd = true;
    } else if ((species.getSelectedIndex() >= 0) && (maintainRule.getSelectedIndex() >= 0)) readyToAdd = true;

    addRule.setEnabled(readyToAdd);
    overwriteRule.setEnabled((ruleTable.getSelectedRowCount()==1) && readyToAdd);
    toDepth.setEnabled(!toTurbocline.isSelected() && (!allDepths.isSelected()));
    fromDepth.setEnabled(!fromTurbocline.isSelected() && (!allDepths.isSelected()));
    toDate.setEnabled(!continuous.isSelected());
    everyYear.setEnabled(!continuous.isSelected());
    fromDate.setEnabled(!continuous.isSelected());
    toTurbocline.setEnabled(!allDepths.isSelected() && (!fromTurbocline.isSelected()));
    fromTurbocline.setEnabled(!allDepths.isSelected() && (!toTurbocline.isSelected()));   
    if (toTurbocline.isSelected()) fromTurbocline.setSelected(false);
    if (fromTurbocline.isSelected()) toTurbocline.setSelected(false);    
    lockEvents--;
  }
  
  public XMLTag[] getStagesForSpecies(String _species) {
    return getFGForSpecies(_species).getTags("stage");
  }
  
  public XMLTag getFGForSpecies(String _species) {
    String fg = model.getTag("species").getTagWhere("species","@name",_species).getAttribute("fg");
    return model.getTagWhere("functionalgroup","name",fg);
  }

  
  public void speciesListClicked() {
    lockEvents++;
    if (species.getSelectedItem() != null) {
      String stageSelect = "";
      if (stage.getSelectedItem() != null)
        stageSelect = stage.getSelectedItem().toString();
      XMLTag[] stages = getStagesForSpecies(species.getSelectedItem().toString()); 
      stage.removeAllItems();
      for (int i = 0; i < stages.length; i++)
        stage.addItem(stages[i].getValue("name"));
      if (!stageSelect.equals("")) {
        for (int i = 0; i < stages.length; i++)
          if (stages[i].getValue("name").equals(stageSelect))
            stage.setSelectedIndex(i);
      }
    } else
      stage.removeAllItems();
    populateChangeFunctions();
    lockEvents--;
  }

  public void ruleListClicked() {
    lockEvents++;
    String ruleName = rule.getSelectedItem().toString();
    if (ruleName.equals(RULE_SPLIT)) {
      ruleDescription.setText("Split largest agents if agent count < ");
      splitMergeValue.setVisible(true);
      maintainRule.setVisible(false);
      stage.setEnabled(true);
      perMetre.setVisible(true);
      inColumn.setVisible(true);
      inML.setVisible(true);
    } else if (ruleName.equals(RULE_MERGE)) {
      ruleDescription.setText("Merge smallest agents if agent count > ");
      splitMergeValue.setVisible(true);
      maintainRule.setVisible(false);
      stage.setEnabled(true);
      perMetre.setVisible(true);
      inColumn.setVisible(true);
      inML.setVisible(true);
    } else if (ruleName.equals(RULE_MAINTAIN)) {
      ruleDescription.setText("When possible, replaces losses caused by ");
      splitMergeValue.setVisible(false);
      maintainRule.setVisible(true);
      perMetre.setVisible(false);
      inColumn.setVisible(false);
      inML.setVisible(false);
      stage.setEnabled(false);
      
    }
    updateButtons();
    lockEvents--;
  }
  
  public void populateChangeHelper(XMLTag[] fs) {
    lockEvents++;
    for (int i = 0; i < fs.length; i++) {
      XMLTag[] rules = fs[i].getTags("equation");
      for (int j = 0; j < rules.length; j++) {
        if (rules[j].getValue("eq").indexOf("\\change") >= 0) {
          maintainRule.addItem(fs[i].getValue("name") + " : " + rules[j].getValue("name"));
          functions.add(fs[i].getValue("name"));
          ruleList.add(rules[j].getValue("name"));
        }
      }
    }
    lockEvents--;
  }
  
  public void populateChangeFunctions() {
    lockEvents++;
    if (species.getSelectedItem()!=null) {
      String fgName = getFGForSpecies(species.getSelectedItem().toString()).getValue("name");
      String rememberEntry = "";
      functions.clear();
      ruleList.clear();
      if (maintainRule.getSelectedItem() != null) rememberEntry = maintainRule.getSelectedItem().toString();
      maintainRule.removeAllItems();
      XMLTag[] funcs = model.getTagWhere("functionalgroup", "name", fgName).getTags("function");
      populateChangeHelper(funcs);
      if (!rememberEntry.equals("")) {
        int i = 0;
        while (i < maintainRule.getItemCount()) {
          if (maintainRule.getItemAt(i).equals(rememberEntry)) {
            maintainRule.setSelectedIndex(i);
            i = maintainRule.getItemCount() + 1;
          }
          i++;
        }
      }
    }
    lockEvents--;
  }

  public void editRule(XMLTag pmr) {
    lockEvents++;
    String ruleType = pmr.getAttribute("type");
    StringTools.setStringItem(rule,ruleType);
    
    StringTools.setStringItem(species,pmr.getAttribute("species"));
    speciesListClicked();
    
    if (ruleType.equals(RULE_MAINTAIN)) {
      StringTools.setStringItem(maintainRule,pmr.getAttribute("function")+" : "+pmr.getAttribute("rule"));
    } else {
      splitMergeValue.setText(String.valueOf(pmr.getAttribute("value")));
      StringTools.setStringItem(stage,pmr.getAttribute("stage"));
    }
    if (pmr.getAttribute("scope")!=null) {
      if (pmr.getAttribute("scope").equals(RULE_COLUMN)) inColumn.setSelected(true);
      else if (pmr.getAttribute("scope").equals(RULE_LAYER)) perMetre.setSelected(true);
      else if (pmr.getAttribute("scope").equals(RULE_MIXING)) inML.setSelected(true);
    }
    if (pmr.getAttribute("turbo")!=null) {
      pmr.setAttribute("toturbo",pmr.getAttribute("turbo"));
      pmr.removeAttribute("turbo");
    }
    toTurbocline.setSelected((pmr.getAttribute("toturbo")!=null) && (pmr.getAttribute("toturbo").equals("true")));
    fromTurbocline.setSelected((pmr.getAttribute("fromturbo")!=null) && (pmr.getAttribute("fromturbo").equals("true")));
    allDepths.setSelected((pmr.getAttribute("alldepth")!=null) && (pmr.getAttribute("alldepth").equals("true")));    
    continuous.setSelected((pmr.getAttribute("alltime")!=null) && (pmr.getAttribute("alltime").equals("true")));
    if (continuous.isSelected()) {
      startDate.setTimeInMillis(startSim.getTimeInMillis());
      endDate.setTimeInMillis(endSim.getTimeInMillis());
    } else {
      startDate.setTimeInMillis(Long.parseLong(pmr.getAttribute("start")));
      endDate.setTimeInMillis(Long.parseLong(pmr.getAttribute("end")));
    }
    fromDate.setText(DateDialog.getString(startDate));
    toDate.setText(DateDialog.getString(endDate));
    everyYear.setSelected((pmr.getAttribute("yearly")!=null) && (pmr.getAttribute("yearly").equals("true")));
    if (!allDepths.isSelected()) {
      if (pmr.getAttribute("top")!=null) StringTools.setStringItem(fromDepth,pmr.getAttribute("top"));
      if (pmr.getAttribute("bottom")!=null) StringTools.setStringItem(fromDepth,pmr.getAttribute("bottom"));
    } else {
      StringTools.setStringItem(fromDepth,"0");
      StringTools.setStringItem(fromDepth,"500");
    }
    ruleListClicked();
    updateButtons();
    lockEvents--;
  }

  class EventHandler implements ActionListener, ListSelectionListener, CaretListener {
    public EventHandler() { }
   
    public void actionPerformed(ActionEvent e) {
      if ((e.getSource() == removeRule) && (lockEvents==0)) {
        lockEvents++;
        int[] selected = ruleTable.getSelectedRows();
        for (int i = selected.length - 1; i >= 0; i--) {
          ruleModel.removeRow(selected[i]);
          model.getTag("particlemanagement").getTags("rule")[selected[i]].removeFromParent();
        }
        updateTable();
        vc2.unsaved(false);
        lockEvents--;
      
      } else if ((e.getSource() == species) && (lockEvents==0)) {
        lockEvents++;
        speciesListClicked();
        lockEvents--;
     
      } else if ((e.getSource() == rule) && (lockEvents==0)) {
        lockEvents++;
        ruleListClicked();
        lockEvents--;
       
      } else if ((e.getSource() == addRule) || (e.getSource() == overwriteRule)) {
        lockEvents++;
        if (e.getSource()==overwriteRule) 
          model.getTag("particlemanagement").getTags("rule")[ruleTable.getSelectedRow()].removeFromParent();
        
        String ruleName = rule.getSelectedItem().toString();
        String speciesName = species.getSelectedItem().toString();
        String fgName = getFGForSpecies(species.getSelectedItem().toString()).getValue("name");
        XMLTag newRule = new XMLTag("rule");
        newRule.setAttribute("type",ruleName);
        newRule.setAttribute("species",speciesName);
        newRule.setAttribute("fg",fgName);
        if ((ruleName.equals(RULE_MERGE)) || (ruleName.equals(RULE_SPLIT))) {
          newRule.setAttribute("stage",stage.getSelectedItem().toString());
          int value = 1;
          try {value = Integer.parseInt(splitMergeValue.getText()); }
          catch (NumberFormatException nfe) {}
          splitMergeValue.setText(String.valueOf(value));
          newRule.setAttribute("value",String.valueOf(value));
          if (perMetre.isSelected()) newRule.setAttribute("scope",RULE_LAYER);
          else if (inColumn.isSelected()) newRule.setAttribute("scope",RULE_COLUMN);
          else if (inML.isSelected()) newRule.setAttribute("scope",RULE_MIXING);
          newRule.setAttribute("toturbo",String.valueOf(toTurbocline.isSelected()));
          newRule.setAttribute("fromturbo",String.valueOf(fromTurbocline.isSelected()));
          newRule.setAttribute("alldepth",String.valueOf(allDepths.isSelected()));
          if (!allDepths.isSelected()) {
            newRule.setAttribute("top",fromDepth.getSelectedItem().toString());
            if (!toTurbocline.isSelected()) newRule.setAttribute("bottom",toDepth.getSelectedItem().toString());
          }
          newRule.setAttribute("alltime",String.valueOf(continuous.isSelected()));
          if (!continuous.isSelected()) {
            newRule.setAttribute("yearly",String.valueOf(everyYear.isSelected()));
            newRule.setAttribute("start",String.valueOf(startDate.getTimeInMillis()));
            newRule.setAttribute("end",String.valueOf(endDate.getTimeInMillis()));
          }  
                    
        } else {
          int mr = maintainRule.getSelectedIndex();
          newRule.setAttribute("function",functions.get(mr).toString());
          newRule.setAttribute("rule",ruleList.get(mr).toString());
        }
        
        newRule.setAttribute("alltime",String.valueOf(continuous.isSelected()));
        if (!continuous.isSelected()) {
          newRule.setAttribute("start",String.valueOf(startDate.getTimeInMillis()));
          newRule.setAttribute("end",String.valueOf(endDate.getTimeInMillis()));
        }
        
        model.getTag("particlemanagement").addTag(newRule);
        updateTable();
        vc2.unsaved(false);
        lockEvents--;
       
       } else if (e.getSource()==toTurbocline) {
         updateButtons();
       } else if (e.getSource()==fromTurbocline) {
         updateButtons();
       } else if (e.getSource()==allDepths) {
         updateButtons();
       } else if (e.getSource()==continuous) {
         updateButtons();
       } else if (e.getSource()==fromDate) {
         dd.show(startDate);
         startDate.setTimeInMillis(dd.getDate().getTimeInMillis());
         fromDate.setText(DateDialog.getString(startDate));
       } else if (e.getSource()==toDate) {
         dd.show(endDate);
         endDate.setTimeInMillis(dd.getDate().getTimeInMillis());
         toDate.setText(DateDialog.getString(endDate));
       }
     }
     
     public void caretUpdate(CaretEvent e) {
       if (e.getSource() == splitMergeValue) {
         updateButtons();
       }
     }

    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==ruleTable.getSelectionModel() && ruleTable.getRowSelectionAllowed()) 
         || (e.getSource() == ruleTable.getColumnModel().getSelectionModel() && ruleTable.getColumnSelectionAllowed())) {
        updateButtons();
        if (ruleTable.getSelectedRowCount()==1) {
          XMLTag ruleTag = model.getTag("particlemanagement").getTags("rule")[ruleTable.getSelectedRow()];
          editRule(ruleTag);
        }
        
      }
    }

  }
}

class RuleTableModel extends DefaultTableModel {
  public boolean isCellEditable(int row, int col) { return false; }
}

