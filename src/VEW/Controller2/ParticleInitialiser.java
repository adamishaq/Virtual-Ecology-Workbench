package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class ParticleInitialiser extends JPanel {
  JComboBox speciesCombo = new JComboBox();
  JComboBox stateCombo = new JComboBox();
  VariableTableModel variableTableModel = new VariableTableModel();
  JTable variableTable = new JTable(variableTableModel);
  JButton addPlankton = new JButton("Add Plankton");
  JButton addYearly = new JButton("Add Plankton Yearly");
  DistributionTableModel distributionsTableModel = new DistributionTableModel();
  JTable distributionsTable = new JTable(distributionsTableModel);
  JButton removePlankton = new JButton("Remove Plankton from Column");
  JButton overwriteDistribution = new JButton("Overwrite Distribution");
  JButton setDate = new JButton("Date");
  JComboBox topDepthCombo = new JComboBox();
  JComboBox bottomDepthCombo = new JComboBox();
  JTextField agentPerMetre = new JTextField(10);
  JTextField indiPerAgent = new JTextField(10);
  DateDialog dd;
  GregorianCalendar activationDate = new GregorianCalendar();
  GregorianCalendar startSim= new GregorianCalendar();
  GregorianCalendar endSim= new GregorianCalendar();
  EventHandler eh = new EventHandler();
  XMLTag theModel;
  int lockEvents = 0;
  boolean changedValues = false;
  private VEWController2 vc2 = null;
  private YearDialog yd = null;
  
  public boolean greenLight(boolean fix) {
    lockEvents++;
    boolean ok = true;
    MiscUtils.findDateLimits(theModel, startSim, endSim);
    
    StringBuffer errorString = new StringBuffer();
    XMLTag initTag = theModel.getTag("initialplankton");
    if (initTag==null) {
      if (fix) {
        initTag = new XMLTag("initialplankton");
        theModel.addTag(initTag);
        errorString.append("Created plankton distribution table\n");
      } else ok=false; 
    }
    if (ok) {
      XMLTag[] distribs = initTag.getTags("distribution");
      for (int i=0; i<distribs.length; i++) {
        XMLTag distrib = distribs[i];
        String stage = distrib.getAttribute("stage");
        String species = distrib.getAttribute("species");
        long milliTime = Long.parseLong(distrib.getAttribute("date"));
        if (milliTime<startSim.getTimeInMillis()) { // fix start time if necessary
          if (fix) {
            distrib.setAttribute("date",String.valueOf(startSim.getTimeInMillis()));
            errorString.append("Date for "+stage+" "+species+" was before simulation start\n");
          } else {
            ok=false;
            i=distribs.length;
          }
        } else if (milliTime>endSim.getTimeInMillis()) { // fix end time if necessary
          if (fix) {
            distrib.setAttribute("date",String.valueOf(endSim.getTimeInMillis()));
            errorString.append("Date for "+stage+" "+species+" was after simulation end\n");
          } else {
            ok=false;
            i=distribs.length;
          }
        }
        
        if (theModel.getTag("species").getTagWhere("species","@name",species)==null) { // Check species
          if (fix) {
            distrib.removeAllChildren();
            distrib.removeFromParent();
            errorString.append("Removed initialisation for "+stage+" "+species+" - species not found\n");
          } else {
            ok=false;
            i=distribs.length;
          }
        } else { // Species is ok.
          String fg = null;
          XMLTag fgTag = null;
          if (ok) {
            fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
            fgTag = theModel.getTagWhere("functionalgroup","name",fg);
            if (fgTag.getTagWhere("stage","name",stage)==null) { // Check stage
              if (fix) {
                distrib.removeAllChildren();
                distrib.removeFromParent();
                errorString.append("Removed initialisation for "+stage+" "+species+" - stage not found\n");
              } else {
                ok=false;
                i=distribs.length;
              }
            } else { // species and stage ok. Now check variables.
              if (ok) {
                XMLTag[] vars = distrib.getTags("var");
                for (int j=0; j<vars.length; j++) {
                  String varName = vars[j].getAttribute("name");
                  if (varName.indexOf("$")>=0) {
                    if (varName.indexOf(":")>=0) { // Variety Var
                      String theVarName = varName;
                      String theVar = theVarName.substring(0,theVarName.indexOf("$"));
                      theVarName = theVarName.substring(theVarName.indexOf("$")+1);
                      String preySpecies = theVarName.substring(0,theVarName.indexOf(":"));
                      if (theModel.getTag("species").getTagWhere("species","@name",preySpecies)==null) {
                        XMLTag[] realSpecies = theModel.getTag("species").getTags("species");
                        int k=0;
                        while (k<realSpecies.length) {
                          if (StringTools.nonSpace(realSpecies[k].getAttribute("name")).equals(preySpecies)) {
                            preySpecies=realSpecies[k].getAttribute("name");
                            k=realSpecies.length;
                          } else k++;
                        }
                      }
                      
                      String preyStage = theVarName.substring(theVarName.indexOf(":")+1);
                      XMLTag preySpeciesTag = theModel.getTag("species").getTagWhere("species","@name",preySpecies);
                      if (preySpeciesTag==null) {
                        if (fix) {
                          vars[j].removeFromParent();
                          errorString.append("Removed "+varName+" from "+stage+" "+species+" - "+preySpecies+" species not found\n");
                        } else {
                          ok=false;
                          j=vars.length;
                        }
                      } else {
                        XMLTag stageTag = theModel.getTagWhere("functionalgroup","name",preySpeciesTag.getAttribute("fg")).getTagWhere("stage","name",preyStage);
                        if (stageTag==null) {
                         if (fix) {
                            vars[j].removeFromParent();
                            errorString.append("Removed "+varName+" from "+stage+" "+species+" - "+preyStage+" stage not found\n");
                          } else {
                            ok=false;
                            j=vars.length;
                          }
                        } else {
                          XMLTag predSpecies = theModel.getTag("species").getTagWhere("species","@name",species);
                          XMLTag varTag = theModel.getTagWhere("functionalgroup","name",predSpecies.getAttribute("fg")).getTagWhere("varietyvariable","name",theVar);
                          if (varTag==null) {
                            if (fix) {
                              vars[j].removeFromParent();
                              errorString.append("Removed "+varName+" from "+stage+" "+species+" - "+theVar+" not found\n");
                            } else {
                              ok=false;
                              j=vars.length;
                            }
                          }
                        }
                      }
                    } else { // Chemical
                    
                      String chemName = varName.substring(0,varName.indexOf("$"));
                      if (theModel.getTagWhere("chemical","name",chemName)==null) {
                        if (fix) {
                          vars[j].removeFromParent();
                          errorString.append("Removed "+varName+" from "+stage+" "+species+" - chemical not found\n");
                        } else {
                          ok=false;
                          j = vars.length;
                        }
                      }
                    }
                  } else {
                    if (fgTag.getTagWhere("variable","name",varName)==null)  {
                      if (fix) {
                        vars[j].removeFromParent();
                        errorString.append("Removed "+varName+" from "+stage+" "+species+" - biological property not found\n");
                      } else {
                        ok=false;
                        j = vars.length;
                      }
                    }
                  }
                }
              }
              XMLTag[] chems = theModel.getTags("chemical");
              for (int j=0; j<chems.length; j++) {
                String chemVar = chems[j].getValue("name")+"$Pool";
                if (distrib.getTagWhere("var","@name",chemVar)==null) {
                  if (fix) {
                    XMLTag newVar = new XMLTag("var");
                    newVar.setAttribute("val","0");
                    newVar.setAttribute("rnd","0");
                    newVar.setAttribute("name",chemVar);
                    newVar.setAttribute("desc",chems[j].getValue("name")+" Pool");
                    distrib.addTag(newVar);
                    XMLTag.sortTagList(distrib,"var","@name");
                    errorString.append("Added entry for "+chems[j].getValue("name")+" Pool in "+distrib.getAttribute("stage")+" "+distrib.getAttribute("species")+"\n");
                  } else {
                    ok=false;
                    j=chems.length;
                  }
                }
              }
              XMLTag[] vvs = fgTag.getTags("varietyvariable");
              for (int j=0; j<vvs.length; j++) {
                String varName = vvs[j].getValue("name");
                if (!varName.equals("IngestedCells")) {
                  String link = vvs[j].getAttribute("link");
                  String foodSet = species+" : "+link;
                  XMLTag theFoodSet = theModel.getTag("foodsets").getTagWhere("foodset","@name",foodSet);
                  XMLTag[] foods = theFoodSet.getTags("food");
                  for (int k=0; k<foods.length; k++) {
                    String newVarName = varName+"$"+foods[k].getAttribute("species")+":"+foods[k].getAttribute("stage");
                    if (distrib.getTagWhere("var","@name",newVarName)==null) {
                      if (fix) {
                        XMLTag newVar = new XMLTag("var");
                        newVar.setAttribute("val",vvs[j].getValue("value"));
                        newVar.setAttribute("rnd","0");
                        newVar.setAttribute("name",newVarName);
                        newVar.setAttribute("desc",varName+" for "+foods[k].getAttribute("stage")+" "+foods[k].getAttribute("species"));
                        distrib.addTag(newVar);
                        XMLTag.sortTagList(distrib,"var","@name");                        
                        errorString.append("Added entry for "+varName+" for "+foods[k].getAttribute("stage")+" "+foods[k].getAttribute("species")+" in "+distrib.getAttribute("stage")+" "+distrib.getAttribute("species")+"\n");    
                      } else {
                        ok=false;
                        k=foods.length;
                        j=vvs.length;
                      }
                    }
                  }
                }
              }
              XMLTag[] statevars = fgTag.getTags("variable");
              for (int j=0; j<statevars.length; j++) {
                String stateVar = statevars[j].getValue("name");
                if (distrib.getTagWhere("var","@name",stateVar)==null) {
                  if (fix) {
                    XMLTag newVar = new XMLTag("var");
                    newVar.setAttribute("val",statevars[j].getValue("value"));
                    newVar.setAttribute("rnd","0");
                    newVar.setAttribute("name",stateVar);
                    newVar.setAttribute("desc",statevars[j].getValue("desc"));
                    distrib.addTag(newVar);
                    XMLTag.sortTagList(distrib,"var","@name");
                    errorString.append("Added entry for "+stateVar+" in "+distrib.getAttribute("stage")+" "+distrib.getAttribute("species")+"\n");
                  } else {
                    ok=false;
                    j=statevars.length;
                  }
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
        
  
  
  public void initialise() {
    lockEvents++;
    dd.setEarliest(startSim);
    dd.setLatest(endSim);
    activationDate.setTimeInMillis(startSim.getTimeInMillis());
    setDate.setText(DateDialog.getString(startSim));
    populateSpeciesCombo();      
    XMLTag init = theModel.getTag("initialplankton");
    if (init!=null) {
      for (int i=distributionsTableModel.getRowCount()-1; i>=0; i--) distributionsTableModel.removeRow(i);
      XMLTag[] dists = init.getTags("distribution");
      for (int i=0; i<dists.length; i++) {
        final long dateLong = Long.parseLong(dists[i].getAttribute("date"));
        activationDate.setTimeInMillis(dateLong);
        final String dateString = DateDialog.getString(activationDate);
        
        distributionsTableModel.addRow(new Object[] {dists[i].getAttribute("species")+" : "+dists[i].getAttribute("stage"),
            dists[i].getAttribute("permetre"),dists[i].getAttribute("popsize"),dists[i].getAttribute("top"),
            dists[i].getAttribute("bottom"),dateString});
      }
    }
    lockEvents--;
  }
  
  public boolean checkAgentsExist() {
    if (distributionsTableModel.getRowCount()==0) {
      int result = JOptionPane.showConfirmDialog(vc2,"You haven't added any distributions of plankton - ok to continue?");
      return (result==JOptionPane.YES_OPTION);
    } else return true;
  }
  
  public ParticleInitialiser(JDialog jd, XMLTag _theModel) {
    yd = new YearDialog(jd);
    theModel = _theModel;
    vc2 = (VEWController2) jd;
    dd = new DateDialog(vc2,1800);
    for (int i=0; i<500; i++) {
      topDepthCombo.addItem(String.valueOf(i));
      bottomDepthCombo.addItem(String.valueOf(i));
    }
    topDepthCombo.setSelectedIndex(0);
    bottomDepthCombo.setSelectedIndex(499);
    setLayout(new BorderLayout());
    JPanel selectionPanel = new JPanel(new BorderLayout());
      JPanel planktonTypePanel = new JPanel(new FlowLayout());
      planktonTypePanel.add(speciesCombo);
      planktonTypePanel.add(stateCombo);
      selectionPanel.add(planktonTypePanel,BorderLayout.NORTH);
      variableTableModel.setColumnCount(4);
      variableTableModel.setColumnIdentifiers(new String[] {"Name","Description","Value (lo)","+random(x)"});
      variableTable.getColumnModel().getColumn(0).setPreferredWidth(150);
      variableTable.getColumnModel().getColumn(1).setPreferredWidth(250);
      variableTable.getColumnModel().getColumn(2).setPreferredWidth(100);
      variableTable.getColumnModel().getColumn(3).setPreferredWidth(100);      
      JScrollPane variableTableScroller = new JScrollPane(variableTable);
      variableTableScroller.setPreferredSize(new Dimension(620,200));
      selectionPanel.add(variableTableScroller, BorderLayout.CENTER);
      JPanel datePanel = new JPanel(new FlowLayout());
      datePanel.add(new JLabel("Add plankton at: "));
      datePanel.add(setDate);
      datePanel.add(new JLabel("between"));
      datePanel.add(topDepthCombo);
      datePanel.add(new JLabel("and"));
      datePanel.add(bottomDepthCombo);
      datePanel.add(new JLabel("m"));
      JPanel agentPanel = new JPanel(new FlowLayout());
      agentPanel.add(new JLabel("Agents per metre: "));
      agentPanel.add(agentPerMetre);
      agentPanel.add(new JLabel("Individuals per agent: "));
      agentPanel.add(indiPerAgent);
      agentPerMetre.setText("1");
      indiPerAgent.setText("1");
      
      JPanel addSelectionPanel = new JPanel(new FlowLayout());
      addSelectionPanel.add(addPlankton);
      addSelectionPanel.add(addYearly);
      addSelectionPanel.add(overwriteDistribution);
      JPanel extraPanelForDate = new JPanel(new BorderLayout());
      extraPanelForDate.add(agentPanel,BorderLayout.CENTER);
      extraPanelForDate.add(addSelectionPanel,BorderLayout.SOUTH);
      extraPanelForDate.add(datePanel,BorderLayout.NORTH);
      selectionPanel.add(extraPanelForDate,BorderLayout.SOUTH);
      add(selectionPanel, BorderLayout.NORTH);
    JPanel distributionPanel = new JPanel(new BorderLayout());
      distributionsTableModel.setColumnCount(6);
      distributionsTable.getColumnModel().getColumn(0).setPreferredWidth(280);
      distributionsTable.getColumnModel().getColumn(1).setPreferredWidth(80);
      distributionsTable.getColumnModel().getColumn(2).setPreferredWidth(60);
      distributionsTable.getColumnModel().getColumn(3).setPreferredWidth(50);
      distributionsTable.getColumnModel().getColumn(4).setPreferredWidth(50);            
      distributionsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
      distributionsTableModel.setColumnIdentifiers(new String[] {"Name","Agents/metre","Individuals/Agent","Top depth (m)","Bottom depth (m)","Date/Time"});    
      JScrollPane distributionsTableScroller = new JScrollPane(distributionsTable);
      distributionsTableScroller.setPreferredSize(new Dimension(620,200));      
      JLabel distributionLabel = new JLabel("Sets of plankton distributed in the column:");
      JPanel dLabelPanel = new JPanel(new FlowLayout());
      dLabelPanel.add(distributionLabel);
      distributionPanel.add(dLabelPanel,BorderLayout.NORTH);
      distributionPanel.add(distributionsTableScroller,BorderLayout.CENTER);
      JPanel removeDistributionPanel = new JPanel(new FlowLayout());
      removeDistributionPanel.add(removePlankton);
      distributionPanel.add(removeDistributionPanel,BorderLayout.SOUTH);
      add(distributionPanel, BorderLayout.CENTER);
    
    topDepthCombo.addActionListener(eh);
    bottomDepthCombo.addActionListener(eh);
    removePlankton.setEnabled(false);
    overwriteDistribution.setEnabled(false);
    speciesCombo.addActionListener(eh);
    addPlankton.addActionListener(eh);
    addYearly.addActionListener(eh);
    removePlankton.addActionListener(eh);
    agentPerMetre.addCaretListener(eh);
    indiPerAgent.addCaretListener(eh);
    distributionsTable.getSelectionModel().addListSelectionListener(eh);
    distributionsTable.getColumnModel().getSelectionModel().addListSelectionListener(eh);

    overwriteDistribution.addActionListener(eh);
    variableTableModel.addTableModelListener(eh);
    distributionsTableModel.addTableModelListener(eh);
    setDate.addActionListener(eh);
  }
  
  public void populateSpeciesCombo() {
    speciesCombo.removeAllItems();
    if (theModel.getTag("species")!=null) {
      XMLTag[] species = theModel.getTag("species").getTags("species");
      for (int i=0; i<species.length; i++) speciesCombo.addItem(species[i].getAttribute("name"));
      if (speciesCombo.getItemCount()>0) {
        lockEvents++;
        speciesCombo.setSelectedIndex(0);
        populateStateCombo();
        populateVariableTable();
        lockEvents--;
      }
    }
  }
  
  public void populateStateCombo() {
    stateCombo.removeAllItems();
    String species = speciesCombo.getSelectedItem().toString();
    String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
    XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",fg).getTags("stage");
    for (int i=0; i<stages.length; i++) 
      stateCombo.addItem(stages[i].getValue("name"));
  }
  
  public void populateVariableTable() {
    for (int i=variableTableModel.getRowCount()-1; i>=0; i--) variableTableModel.removeRow(i);
    String species = speciesCombo.getSelectedItem().toString();
    String fg = theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg");
    XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",fg);
    XMLTag getAllVariables = new XMLTag("allvars");
    XMLTag[] variables = fgTag.getTags("variable");
    for (int i=0; i<variables.length; i++) getAllVariables.addTag((XMLTag)variables[i].clone());
    XMLTag[] chemicals = theModel.getTags("chemical");
    for (int i=0; i<chemicals.length; i++) {
      XMLTag chemPool = new XMLTag("variable");
      chemPool.addTag(new XMLTag("name",chemicals[i].getValue("name")+"$Pool"));
      chemPool.addTag(new XMLTag("desc",chemicals[i].getValue("name")+" Pool"));
      chemPool.addTag(new XMLTag("value","0.0"));
      getAllVariables.addTag(chemPool);
    }
    XMLTag[] varietyVars = fgTag.getTags("varietyvariable");
    for (int i=0; i<varietyVars.length; i++) {
      String name = varietyVars[i].getValue("name");
      if (!name.equals("IngestedCells")) {
        String link = varietyVars[i].getAttribute("link");
        String vconcName = species+" : "+link;
        XMLTag foodTypes = theModel.getTag("foodsets").getTagWhere("foodset","@name",vconcName);
        XMLTag[] foods = foodTypes.getTags("food");
        for (int j=0; j<foods.length; j++) {
          XMLTag newVar = new XMLTag("variable");
          newVar.addTag(new XMLTag("name",name+"$"+foods[j].getAttribute("species")+":"+foods[j].getAttribute("stage")));
          newVar.addTag(new XMLTag("desc",name+" for "+foods[j].getAttribute("stage")+" "+foods[j].getAttribute("species")));
          newVar.addTag(new XMLTag("value",varietyVars[i].getValue("value")));
          getAllVariables.addTag(newVar);
        }
      }
    }
    
    XMLTag[] theVariables = getAllVariables.getTags("variable");
    theVariables = XMLTag.sortTags(theVariables,"name");
    for (int i=0; i<theVariables.length; i++) {
      variableTableModel.addRow(new Object[] {theVariables[i].getValue("name").toString(), theVariables[i].getValue("desc").toString(), theVariables[i].getValue("value").toString(), "0.0"});
    }
  }
  
  class VariableTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) {
      return ((col==2) || (col==3));
    }
  }

  class DistributionTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) {
      return (col<5);
    }
  }
  
  class EventHandler implements ActionListener, TableModelListener, ListSelectionListener, CaretListener {
    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
      if ((e.getSource()==speciesCombo) && (lockEvents==0)) {
        lockEvents++;
        populateStateCombo();
        populateVariableTable();
        lockEvents--;
      
      } else if ((e.getSource()==addPlankton) && (lockEvents==0)) {
        lockEvents++;
        addTag();
        int i = distributionsTable.getRowCount()-1;
        distributionsTable.setRowSelectionInterval(i,i);
        removePlankton.setEnabled(true);
        changedValues=false;
        vc2.unsaved(false);
        lockEvents--;
        
      } else if ((e.getSource()==addYearly) && (lockEvents==0)) {
        lockEvents++;
        yd.showDialog(startSim.get(GregorianCalendar.YEAR),endSim.get(GregorianCalendar.YEAR));
        if (yd.perform()) {
          int y1 = yd.firstYear();
          int y2 = yd.lastYear();
          for (int i=y1; i<=y2; i++) {
            activationDate.set(GregorianCalendar.YEAR,i);
            setDate.setText(DateDialog.getString(activationDate));
            addTag();
          }
          int i = distributionsTable.getRowCount()-1;
          distributionsTable.setRowSelectionInterval(i,i);
          removePlankton.setEnabled(true);
          changedValues=false;
          vc2.unsaved(false);
        }
        lockEvents--;
      
      } else if ((e.getSource()==overwriteDistribution) && (lockEvents==0)) {
        lockEvents++;
        overwriteTag();
        distributionsTable.update(distributionsTable.getGraphics());
        overwriteDistribution.setEnabled(false);
        changedValues=false;
        lockEvents--;
        vc2.unsaved(false);
        
      } else if ((e.getSource()==removePlankton) && (lockEvents==0)) {
        lockEvents++;
        int[] removeThese = distributionsTable.getSelectedRows(); 
        for (int j=removeThese.length-1; j>=0; j--) {
          theModel.getTag("initialplankton").getTags("distribution")[removeThese[j]].removeFromParent();
          distributionsTableModel.removeRow(removeThese[j]);
        }
        distributionsTable.update(distributionsTable.getGraphics());
        removePlankton.setEnabled(false);
        vc2.unsaved(false);
        lockEvents--;
     
      } else if ((e.getSource()==topDepthCombo) && (lockEvents==0)) {
        changedValues=true;
        if (distributionsTable.getSelectedRowCount()==1) overwriteDistribution.setEnabled(true);
        
      } else if ((e.getSource()==bottomDepthCombo) && (lockEvents==0)) {
        changedValues=true;
        if (distributionsTable.getSelectedRowCount()==1) overwriteDistribution.setEnabled(true);
      
      } else if ((e.getSource()==setDate) && (lockEvents==0)) {
        lockEvents++;
        dd.show(startSim,endSim,activationDate);
        activationDate=dd.getDate();
        setDate.setText(DateDialog.getString(activationDate));
        overwriteDistribution.setEnabled(true);
        changedValues=true;
        lockEvents--;
        vc2.unsaved(false);
      }
    }

    public void showTag() {
      XMLTag dist = theModel.getTag("initialplankton").getTags("distribution")[distributionsTable.getSelectedRow()];
      lockEvents++;
      StringTools.setStringItem(speciesCombo,dist.getAttribute("species"));
      populateStateCombo();
      populateVariableTable();
      StringTools.setStringItem(stateCombo,dist.getAttribute("stage"));
      indiPerAgent.setText(dist.getAttribute("popsize"));
      agentPerMetre.setText(dist.getAttribute("permetre"));
      StringTools.setStringItem(topDepthCombo,dist.getAttribute("top"));
      StringTools.setStringItem(bottomDepthCombo,dist.getAttribute("bottom"));
      activationDate.setTimeInMillis(Long.parseLong(dist.getAttribute("date")));
      setDate.setText(DateDialog.getString(activationDate));
      for (int i=0; i<variableTable.getRowCount(); i++) {
        String varName = variableTableModel.getValueAt(i,0).toString();
        XMLTag varTag = dist.getTagWhere("var","@name",varName);
        variableTableModel.setValueAt(varTag.getAttribute("desc").toString(),i,1);
        variableTableModel.setValueAt(varTag.getAttribute("val").toString(),i,2);
        variableTableModel.setValueAt(varTag.getAttribute("rnd").toString(),i,3);
      }
      lockEvents--;
    }
    
    public void overwriteTag() {
      XMLTag dist = theModel.getTag("initialplankton").getTags("distribution")[distributionsTable.getSelectedRow()];
      dist.setAttribute("species",speciesCombo.getSelectedItem().toString());
      dist.setAttribute("stage",stateCombo.getSelectedItem().toString());
      dist.setAttribute("popsize",indiPerAgent.getText());
      dist.setAttribute("permetre",agentPerMetre.getText());
      dist.setAttribute("top",topDepthCombo.getSelectedItem().toString());
      dist.setAttribute("bottom",bottomDepthCombo.getSelectedItem().toString());
      dist.setAttribute("date",String.valueOf(activationDate.getTimeInMillis()));
      for (int i=0; i<variableTable.getRowCount(); i++) {
        String varName = variableTableModel.getValueAt(i,0).toString();
        XMLTag varTag = dist.getTagWhere("var","@name",varName);
        varTag.setAttribute("desc",String.valueOf(variableTableModel.getValueAt(i,1)));
        varTag.setAttribute("val",String.valueOf(variableTableModel.getValueAt(i,2)));
        varTag.setAttribute("rnd",String.valueOf(variableTableModel.getValueAt(i,3)));
      }
      int row = distributionsTable.getSelectedRow();
      distributionsTableModel.setValueAt(speciesCombo.getSelectedItem().toString()+" : "
          +stateCombo.getSelectedItem().toString(),row,0);
      distributionsTableModel.setValueAt(agentPerMetre.getText(),row,1);
      distributionsTableModel.setValueAt(indiPerAgent.getText(),row,2);
      distributionsTableModel.setValueAt(topDepthCombo.getSelectedItem().toString(),row,3);
      distributionsTableModel.setValueAt(bottomDepthCombo.getSelectedItem().toString(),row,4);
      distributionsTableModel.setValueAt(setDate.getText(),row,5);
    }
    
    public void addTag() {
      XMLTag dist = new XMLTag("distribution");
      dist.setAttribute("species",speciesCombo.getSelectedItem().toString());
      dist.setAttribute("stage",stateCombo.getSelectedItem().toString());
      dist.setAttribute("popsize",indiPerAgent.getText());
      dist.setAttribute("permetre",agentPerMetre.getText());
      dist.setAttribute("top",topDepthCombo.getSelectedItem().toString());
      dist.setAttribute("bottom",bottomDepthCombo.getSelectedItem().toString());
      dist.setAttribute("date",String.valueOf(activationDate.getTimeInMillis()));
      for (int i=0; i<variableTable.getRowCount(); i++) {
        XMLTag varTag = new XMLTag("var");
        varTag.setAttribute("name",String.valueOf(variableTableModel.getValueAt(i,0)));
        varTag.setAttribute("desc",String.valueOf(variableTableModel.getValueAt(i,1)));
        varTag.setAttribute("val",String.valueOf(variableTableModel.getValueAt(i,2)));
        varTag.setAttribute("rnd",String.valueOf(variableTableModel.getValueAt(i,3)));
        dist.addTag(varTag);
      }
      theModel.getTag("initialplankton").addTag(dist);
      distributionsTableModel.addRow(new Object[] {speciesCombo.getSelectedItem().toString()+" : "
          +stateCombo.getSelectedItem().toString(), 
          agentPerMetre.getText(), indiPerAgent.getText(),topDepthCombo.getSelectedItem().toString(),
          bottomDepthCombo.getSelectedItem().toString(), setDate.getText()});
    }

    
    public void clickDistributionsTable() {
      overwriteDistribution.setEnabled((distributionsTable.getSelectedRows().length==1) && (changedValues));
      removePlankton.setEnabled(distributionsTable.getSelectedRows().length>=1);
      boolean okToContinue = true;
      if (distributionsTable.getSelectedRows().length==1) {
        if (changedValues) {
          int result = JOptionPane.showConfirmDialog(ParticleInitialiser.this, "Changes made will be lost. Ok to continue?");
          if (result!=JOptionPane.YES_OPTION) okToContinue=false;
        }
        if (okToContinue)  {
          showTag();
          variableTable.update(variableTable.getGraphics());
          overwriteDistribution.setEnabled(false);
          changedValues=false;
        }
      }
    }
    
    
    public void tableChanged(TableModelEvent e) {
      if ((lockEvents==0) && (e.getSource()==distributionsTableModel)) {
        lockEvents++;
        int i = distributionsTable.getSelectedRow();
        XMLTag dist = theModel.getTag("initialplankton").getTags("distribution")[i];
        dist.setAttribute("permetre",distributionsTableModel.getValueAt(i,1).toString());
        dist.setAttribute("popsize",distributionsTableModel.getValueAt(i,2).toString());
        dist.setAttribute("top",distributionsTableModel.getValueAt(i,3).toString());
        dist.setAttribute("bottom",distributionsTableModel.getValueAt(i,4).toString());
        agentPerMetre.setText(dist.getAttribute("permetre"));
        indiPerAgent.setText(dist.getAttribute("popsize"));
        StringTools.setStringItem(topDepthCombo,dist.getAttribute("top"));
        StringTools.setStringItem(bottomDepthCombo,dist.getAttribute("bottom"));
        lockEvents--;
      
      } else if ((lockEvents==0) && (e.getSource()==variableTableModel)) {
        changedValues=true;
        vc2.unsaved(false);
        if (distributionsTable.getSelectedRowCount()==1) overwriteDistribution.setEnabled(true);
      }
    }

    public void valueChanged(ListSelectionEvent e) {
      if ((lockEvents==0) && (((e.getSource()==distributionsTable.getSelectionModel() && distributionsTable.getRowSelectionAllowed()) ||
          (e.getSource()==distributionsTable.getColumnModel().getSelectionModel() && distributionsTable.getColumnSelectionAllowed())))) {
        clickDistributionsTable();
      }
      
    }

    public void caretUpdate(CaretEvent e) {
      if ((e.getSource()==agentPerMetre) && (lockEvents==0)) {
        changedValues=true;
        if (distributionsTable.getSelectedRowCount()==1) overwriteDistribution.setEnabled(true);
      
      } else if ((e.getSource()==indiPerAgent) && (lockEvents==0)) {
        changedValues=true;
        if (distributionsTable.getSelectedRowCount()==1) overwriteDistribution.setEnabled(true);
      }
    }
  }
  
  class YearDialog extends JDialog {
    private JComboBox year1 = new JComboBox();
    private JComboBox year2 = new JComboBox();
    private JButton okButton = new JButton("Ok");
    private JButton cancelButton = new JButton("Cancel");
    private boolean performAction;
    private int lockYearEvents = 0;
    
    public boolean perform() { return performAction; }
    public int firstYear() { return Integer.parseInt(year1.getSelectedItem().toString()); }
    public int lastYear() { return Integer.parseInt(year2.getSelectedItem().toString()); }
        
    public YearDialog(JDialog parent) {
      super(parent,"Select Years",true);
      YearEventHandler ye = new YearEventHandler();
      JPanel yearFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
      yearFlow.add(new JLabel("From"));
      yearFlow.add(year1);
      yearFlow.add(new JLabel("To"));
      yearFlow.add(year2);
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(yearFlow,BorderLayout.CENTER);
      JPanel buttonFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
      buttonFlow.add(okButton);
      buttonFlow.add(cancelButton);
      getContentPane().add(buttonFlow,BorderLayout.SOUTH);
      okButton.addActionListener(ye);
      cancelButton.addActionListener(ye);
      year1.addActionListener(ye);
      year2.addActionListener(ye);
    }
    
    public void showDialog(int y1, int y2) {
      lockYearEvents++;
      performAction=false;
      year1.removeAllItems();
      year2.removeAllItems();
      for (int i=y1; i<=y2; i++) {
        year1.addItem(String.valueOf(i));
        year2.addItem(String.valueOf(i));
      }
      year1.setSelectedIndex(0);
      year2.setSelectedIndex(year2.getItemCount()-1);
      lockYearEvents--;
      pack();
      setVisible(true);
    }
    
    class YearEventHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancelButton) {
          performAction=false;
          setVisible(false);
        } else if (e.getSource()==okButton) {
          performAction=true;
          setVisible(false);
        } else if (((e.getSource()==year1) || (e.getSource()==year2)) && (lockYearEvents==0)) {
          lockYearEvents++;
          if (year1.getSelectedIndex()>year2.getSelectedIndex()) {
            final int y = year1.getSelectedIndex();
            year1.setSelectedIndex(year2.getSelectedIndex());
            year2.setSelectedIndex(y);
          }
          lockYearEvents--;
        }
      }
    }
  }
}
