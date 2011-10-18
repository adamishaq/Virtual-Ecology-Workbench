package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import VEW.Common.JCheckBoxList;
import VEW.Common.MessageBox;
import VEW.Common.XML.XMLTag;

public class FoodTypeDialog extends JPanel {
  JLabel foodSetsLabel = new JLabel("Food sets");
  DefaultListModel foodSetsListModel = new DefaultListModel();
  JList foodSetsList = new JList(foodSetsListModel);
  JLabel foodTypesLabel = new JLabel("Available Food Types");
  DefaultListModel foodTypesListModel = new DefaultListModel();
  JCheckBoxList foodTypesList = new JCheckBoxList(foodTypesListModel);
  ReadOnlyTableModel varDescTableModel = new ReadOnlyTableModel();
  JTable varDescTable = new JTable(varDescTableModel);
  ParameterTableModel foodParamTableModel = new ParameterTableModel();
  JTable foodParamTable = new JTable(foodParamTableModel);
  JLabel foodParamTitle = new JLabel("Food-Specific Parameter Values");
  JButton applyAllFood = new JButton("Apply to all food types");
  JLabel varDescTitle = new JLabel("Parameters");
  EventHandler eh = new EventHandler();
  XMLTag theModel = null;
  int lockEvents = 0;
  
  private VEWController2 vc2;
  
  public boolean greenLight(boolean fix) {
    lockEvents++;
    StringBuffer errorString = new StringBuffer();
    boolean ok = true;
    if (theModel.getTag("foodsets")==null) {
      if (fix) {
        theModel.addTag(new XMLTag("foodsets"));
        errorString.append("Adding Food-set Table\n");
      } else{
        ok=false;
      }
    }
    if (ok) { // See if there are any varconcs in a fg, where there's no foodset in species.
      XMLTag[] fgs = theModel.getTags("functionalgroup");
      for (int fgNo=0; fgNo<fgs.length; fgNo++) {
        XMLTag[] varconcs = fgs[fgNo].getTags("varietyconcentration");
        XMLTag[] species = theModel.getTag("species").getTagsWhere("species","@fg",fgs[fgNo].getValue("name"));
        for (int spNo=0; spNo<species.length; spNo++) {
          for (int vcNo=0; vcNo<varconcs.length; vcNo++) {
            if (!varconcs[vcNo].getValue("name").toUpperCase().equals("INGESTION")) {
              String foodsetName = species[spNo].getAttribute("name")+" : "+varconcs[vcNo].getValue("name");
              if (theModel.getTag("foodsets").getTagWhere("foodset","@name",foodsetName)==null) {
                if (fix) {
                  XMLTag newfoodset = new XMLTag("foodset");
                  newfoodset.setAttribute("name",foodsetName);
                  theModel.getTag("foodsets").addTag(newfoodset);
                  errorString.append("Adding food-set "+foodsetName+"\n");
                } else {
                  ok=false;
                }
              }
            }
          }
        }
      }
    }
    
    if (ok) {
      XMLTag foodSetTag = theModel.getTag("foodsets");
      XMLTag[] foodsets = foodSetTag.getTags("foodset");
      for (int i=0; i<foodsets.length; i++) {
        String species = foodsets[i].getAttribute("name");
        String set = species.substring(species.indexOf(":")+2);
        species = species.substring(0,species.indexOf(":")-1);
        XMLTag speciesTag = theModel.getTag("species");
        XMLTag theSpecies = speciesTag.getTagWhere("species","@name",species);
        if (theSpecies==null) { // Check species tag for the predator is ok.
          if (fix) {  // VIOLATION: We have X_Species:P in foodsets, but no X_Species.
            foodsets[i].removeAllChildren();
            foodsets[i].removeFromParent();
            errorString.append("Removing "+foodsets[i].getAttribute("name")+" - "+species+" not found\n");
          } else {
            ok = false;
            i = foodsets.length;
          }
        } else { // X_Species belongs to X_fg. Check for varconc in X_fg. (VIOL: P not in X_Fg)
          XMLTag fgPredatorTag = theModel.getTagWhere("functionalgroup","name",theSpecies.getAttribute("fg"));
          if (fgPredatorTag.getTagsWhere("varietyconcentration","name",set)==null) {
            if (fix) {
              foodsets[i].removeFromParent();
              errorString.append("Removing "+foodsets[i].getAttribute("name")+" - "+set+" not found\n");
            } else {
              ok = false;
              i = foodsets.length;
            }
          }
        }
        if (ok) {
          XMLTag[] foods = foodsets[i].getTags("food");
          for (int j=0; j<foods.length; j++) { 
            String foodSpecies = foods[j].getAttribute("species");
            String foodStage = foods[j].getAttribute("stage");
            XMLTag foodSpeciesTag = speciesTag.getTagWhere("species","@name",foodSpecies);
            if (foodSpeciesTag==null) { // Food species wasn't recognised.
              if (fix) {
                foods[j].removeFromParent();
                errorString.append("Removing "+foods[j].getAttribute("stage")+" "+foods[j].getAttribute("species")+" from "+foodsets[i].getAttribute("name")+" - "+foodSpecies+" not found\n");
              } else {
                ok = false; 
              }
            } else {
              XMLTag fgPredatorTag = theModel.getTagWhere("functionalgroup","name",theSpecies.getAttribute("fg"));
              XMLTag fgPreyTag = theModel.getTagWhere("functionalgroup","name",foodSpeciesTag.getAttribute("fg"));
              if (fgPreyTag.getTagsWhere("stage","name",foodStage)==null) {
                if (fix) {
                  foods[j].removeFromParent();
                  errorString.append("Removing "+foods[j].getAttribute("stage")+" "+foods[j].getAttribute("species")+" from "+foodsets[i].getAttribute("name")+" - "+foodStage+" not found\n");
                } else {
                  ok = false; // Stage not found.
                }
              } else {
                XMLTag[] varparams = fgPredatorTag.getTagsWhere("varietyparameter","@link",set);
                for (int k=0; k<varparams.length; k++) { // Check for any missing params.
                  if (foods[j].getTagWhere("param","@name",varparams[k].getValue("name"))==null) {
                    if (fix) {
                      errorString.append("Adding default for parameter "+varparams[k].getValue("name")+" in "+foodsets[i].getAttribute("name")+", "+foods[j].getAttribute("stage")+" "+foods[j].getAttribute("species")+"\n");
                      XMLTag newParam = new XMLTag("param");
                      newParam.setAttribute("a",varparams[k].getValue("value"));
                      newParam.setAttribute("b","0.0");
                      newParam.setAttribute("name",varparams[k].getValue("name"));
                      foods[j].addTag(newParam);
                      XMLTag.sortTagList(foods[j],"param","@name");
                    } else {
                      ok = false;
                    }
                  }
                }
                XMLTag[] foodparams = foods[j].getTags("param");
                for (int k=0; k<foodparams.length; k++) { // Check for extra params.
                  XMLTag theParam = fgPredatorTag.getTagWhere("varietyparameter","name",foodparams[k].getAttribute("name"));
                  if (theParam==null) {
                    if (fix) {
                      errorString.append("Removing extra parameter "+foodparams[k].getAttribute("name")+" from "+foodsets[i].getAttribute("name")+", "+foods[j].getAttribute("stage")+" "+foods[j].getAttribute("species")+"\n");
                      foodparams[k].removeFromParent();
                    } else {
                      ok = false;
                    }
                  } else if (!theParam.getAttribute("link").equals(set)) {
                    if (fix) {
                      errorString.append("Removing parameter "+foodparams[k].getAttribute("name")+" from "+foodsets[i].getAttribute("name")+", "+foods[j].getAttribute("stage")+" "+foods[j].getAttribute("species")+" - not linked with "+set+"\n");
                      foodparams[k].removeFromParent();
                    } else {
                      ok=false;
                    }
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
      populateFoodSets();
      populateFoodTypes();
    }
    lockEvents--;
    return ok;
  }
  
   public void populateFoodSets() {
    foodSetsListModel.removeAllElements();
    XMLTag[] foodsets = theModel.getTag("foodsets").getTags("foodset");
    for (int i=0; i<foodsets.length; i++) {
      String foodSetName = foodsets[i].getAttribute("name");
      foodSetsListModel.addElement(foodSetName);
    }  
  }
  
  public void populateFoodTypes() {
    foodTypesListModel.removeAllElements();
    XMLTag[] species = theModel.getTag("species").getTags("species");
    for (int i=0; i<species.length; i++) {
      XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
      for (int j=0; j<stages.length; j++)
        foodTypesListModel.addElement(new JCheckBox(species[i].getAttribute("name")+" : "+stages[j].getValue("name")));
    }
  }
    
  public void populateFoodParameters() {
    for (int i=varDescTableModel.getRowCount()-1; i>=0; i--) varDescTableModel.removeRow(i);
    if (foodSetsList.getSelectedValue()!=null) {
      String foodSet = foodSetsList.getSelectedValue().toString();
      String species = foodSet.substring(0,foodSet.indexOf(":")-1);
      foodSet = foodSet.substring(foodSet.indexOf(":")+2);
      XMLTag speciesTag = theModel.getTag("species").getTagWhere("species","@name",species);
      XMLTag[] params = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg")).getTagsWhere("varietyparameter","@link",foodSet);
      for (int i=0; i<params.length; i++)
        varDescTableModel.addRow(new String[] {params[i].getValue("name"),params[i].getValue("desc")});
    }
  }
  
  public void populateParamABTable() {
    for (int i=foodParamTableModel.getRowCount()-1; i>=0; i--) foodParamTableModel.removeRow(i);
    XMLTag foodSet = theModel.getTag("foodsets").getTags("foodset")[foodSetsList.getSelectedIndex()];
    for (int i=0; i<foodTypesListModel.size(); i++) 
      ((JCheckBox) foodTypesListModel.getElementAt(i)).setSelected(false);
    
    XMLTag[] foods = foodSet.getTags("food");
    for (int i=0; i<foods.length; i++) {
      String checkBoxString = foods[i].getAttribute("species")+" : "+foods[i].getAttribute("stage");
      int j=0;
      while (j<foodTypesListModel.size()) {
        JCheckBox jcb = (JCheckBox) foodTypesListModel.getElementAt(j);
        if (jcb.getText().equals(checkBoxString)) {
          jcb.setSelected(true);
          j = foodTypesListModel.size();
        }
        j++;
      }
      
    }
    foodTypesList.update(foodTypesList.getGraphics());
    
    int r = varDescTable.getSelectedRow();
    if (r>=0) {
      String currentParam = varDescTableModel.getValueAt(r,0).toString();
      for (int i=0; i<foods.length; i++) {
        String predatorSpecies = foodSetsList.getSelectedValue().toString();
        predatorSpecies = predatorSpecies.substring(0,predatorSpecies.indexOf(":")-1);
        String species = foods[i].getAttribute("species");
        String stage = foods[i].getAttribute("stage");
        String checkBoxString = species+" : "+stage;
        XMLTag param = foods[i].getTagWhere("param","@name",currentParam);
        double aValue = Double.parseDouble(param.getAttribute("a"));
        double bValue = Double.parseDouble(param.getAttribute("b"));
        double xValue = Double.parseDouble(theModel.getTag("species").getTagWhere("species","@name",predatorSpecies).getAttribute("x"));
        double theValue = aValue*Math.pow(bValue,xValue);
        foodParamTableModel.addRow(new Object[] {checkBoxString, new Double(aValue), new Double(bValue), new Double(theValue)});
      }
    }
  }
    
  public void alterFoodTypeSelection() {
    lockEvents++;
    for (int i=0; i<foodTypesListModel.size(); i++) {
      JCheckBox jcb = (JCheckBox) foodTypesListModel.elementAt(i);
      String foodStage = jcb.getText();
      String foodSpecies = foodStage.substring(0,foodStage.indexOf(":")-1);
      foodStage = foodStage.substring(foodStage.indexOf(":")+2);
      String predatorSpecies = foodSetsList.getSelectedValue().toString();
      predatorSpecies = predatorSpecies.substring(0,predatorSpecies.indexOf(":")-1);
      XMLTag foodSet = theModel.getTag("foodsets").getTags("foodset")[foodSetsList.getSelectedIndex()];
      XMLTag[] selectedFoods = foodSet.getTags("food");
      boolean foodPresent=false;
      int foodIndex=0;
      while ((foodIndex<selectedFoods.length) && (!foodPresent)) {
        if ((selectedFoods[foodIndex].getAttribute("species").equals(foodSpecies)) && (selectedFoods[foodIndex].getAttribute("stage").equals(foodStage))) {
          foodPresent=true;
        }
        if (!foodPresent) foodIndex++;
      }
      if ((jcb.isSelected()) && (!foodPresent)) { // A checkbox wasn't selected, but now it is. 
        XMLTag newFood = new XMLTag("food");
        newFood.setAttribute("species",foodSpecies);
        newFood.setAttribute("stage",foodStage);
        XMLTag theSpecies = theModel.getTag("species").getTagWhere("species","@name",predatorSpecies);
        XMLTag fg = theModel.getTagWhere("functionalgroup","name",theSpecies.getAttribute("fg"));
        XMLTag[] varParams = fg.getTags("varietyparameter");
        for (int k=0; k<varParams.length; k++) {
          XMLTag newParam = new XMLTag("param");
          newParam.setAttribute("name",varParams[k].getValue("name"));
          newParam.setAttribute("a",varParams[k].getValue("value"));
          newParam.setAttribute("b","0.0");
          newFood.addTag(newParam);
        }
        foodSet.addTag(newFood);
      } else if (!(jcb.isSelected()) && (foodPresent)){ // A checkbox was selected, but now it isn't.
        selectedFoods[foodIndex].removeFromParent();
      }
    }
    populateParamABTable();
    lockEvents--;
  }
  
  public void updateInternalABTags() {
    if (foodParamTable.getSelectedRow()>=0) {
      String foodSet = foodSetsList.getSelectedValue().toString();
      int foodRow = foodParamTable.getSelectedRow();
      String foodType = foodParamTableModel.getValueAt(foodRow,0).toString();
      String aValue = foodParamTableModel.getValueAt(foodRow,1).toString();
      String bValue = foodParamTableModel.getValueAt(foodRow,2).toString();
      String paramName = varDescTableModel.getValueAt(varDescTable.getSelectedRow(),0).toString();
      double a = Double.parseDouble(aValue);
      double b = Double.parseDouble(bValue);
      String predatorSpecies = foodSet.substring(0,foodSet.indexOf(":")-1);
      String xValue = theModel.getTag("species").getTagWhere("species","@name",predatorSpecies).getAttribute("x");
      double x = Double.parseDouble(xValue);
      double theVal = a*Math.pow(b,x);
      String foodStage = foodType;
      String foodSpecies = foodStage.substring(0,foodStage.indexOf(":")-1);
      foodStage = foodStage.substring(foodStage.indexOf(":")+2);
      XMLTag foodSetTag = theModel.getTag("foodsets").getTagWhere("foodset","@name",foodSet);
      XMLTag[] foods = foodSetTag.getTags("food");
      int i=0;
      boolean found = false;
      while (!found) {
        if ((foods[i].getAttribute("species").equals(foodSpecies)) && (foods[i].getAttribute("stage").equals(foodStage))) 
          found = true;
        else i++;
      }
      XMLTag theParam = foods[i].getTagWhere("param","@name",paramName);
      theParam.setAttribute("a",aValue);
      theParam.setAttribute("b",bValue);
      foodParamTableModel.setValueAt(new Double(theVal), foodRow,3);
      foodParamTable.update(foodParamTable.getGraphics());
    }
  }
  
  public FoodTypeDialog(JDialog jd, XMLTag model) {
    vc2 = (VEWController2) jd;
    theModel = model;
    varDescTableModel.setColumnIdentifiers(new String[] {"Name","Description"});
    foodParamTableModel.setColumnIdentifiers(new String[] {"Food type", "A", "B", "Value"});
    setLayout(new BorderLayout());
    JPanel specFood = new JPanel(new FlowLayout());
    
    JPanel foodSetsPanel = new JPanel(new BorderLayout());
      foodSetsPanel.add(foodSetsLabel,BorderLayout.NORTH);
      JScrollPane foodSetScroller = new JScrollPane(foodSetsList);
      foodSetsPanel.add(foodSetScroller,BorderLayout.CENTER);
      specFood.add(foodSetsPanel);
    JPanel foodTypesPanel = new JPanel(new BorderLayout());
      foodTypesPanel.add(foodTypesLabel,BorderLayout.NORTH);
      JScrollPane foodTypesScroller = new JScrollPane(foodTypesList);
      foodTypesPanel.add(foodTypesScroller,BorderLayout.CENTER);
      specFood.add(foodTypesPanel);
    add(specFood,BorderLayout.NORTH);
    
    JPanel theTables = new JPanel(new FlowLayout());
    
    JPanel paramPanel = new JPanel(new BorderLayout());
      paramPanel.add(varDescTitle,BorderLayout.NORTH);
      JScrollPane paramScroller = new JScrollPane(varDescTable);
      paramScroller.setPreferredSize(new Dimension(300,200));
      JPanel spacePanel = new JPanel(new FlowLayout());
      spacePanel.setPreferredSize(new Dimension(300,36));
      paramPanel.add(paramScroller,BorderLayout.CENTER);
      paramPanel.add(spacePanel,BorderLayout.SOUTH);
      TableColumnModel tcm1 = varDescTable.getColumnModel();
      tcm1.getColumn(0).setPreferredWidth(70);
      theTables.add(paramPanel);
    
    JPanel foodParamPanel = new JPanel(new BorderLayout());
      foodParamPanel.add(foodParamTitle,BorderLayout.NORTH);
      JScrollPane foodParamScroller = new JScrollPane(foodParamTable);
      foodParamScroller.setPreferredSize(new Dimension(300,200));      
      foodParamPanel.add(foodParamScroller,BorderLayout.CENTER);
      JPanel paramButtons = new JPanel(new FlowLayout());
      paramButtons.add(applyAllFood);
      foodParamPanel.add(paramButtons,BorderLayout.SOUTH);
      theTables.add(foodParamPanel);
      TableColumnModel tcm = foodParamTable.getColumnModel();
      tcm.getColumn(0).setPreferredWidth(370);
      
    
    add(theTables, BorderLayout.CENTER);
    JPanel finalButtons = new JPanel(new FlowLayout());
    add(finalButtons,BorderLayout.SOUTH);

        
    foodSetsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    foodSetsList.addListSelectionListener(eh);
    foodTypesList.addListSelectionListener(eh);
    foodTypesList.addMouseListener(eh);
    varDescTable.getSelectionModel().addListSelectionListener(eh);
    varDescTable.getColumnModel().getSelectionModel().addListSelectionListener(eh);
    foodParamTableModel.addTableModelListener(eh);
    foodParamTable.getSelectionModel().addListSelectionListener(eh);
    foodParamTable.getColumnModel().getSelectionModel().addListSelectionListener(eh);
    applyAllFood.addActionListener(eh);
    foodTypesList.setEnabled(false);
    applyAllFood.setEnabled(false);
  }
  
  class EventHandler implements ActionListener, ListSelectionListener, TableModelListener, MouseListener {
    
    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==applyAllFood) {
        int row = foodParamTable.getSelectedRow();
        String aValue = foodParamTableModel.getValueAt(row,1).toString();
        String bValue = foodParamTableModel.getValueAt(row,2).toString();
        String value = foodParamTableModel.getValueAt(row,3).toString();
        lockEvents++;
        for (int i=0; i<foodParamTableModel.getRowCount(); i++) {
          if (i!=row) {
            foodParamTableModel.setValueAt(aValue,i,1);
            foodParamTableModel.setValueAt(bValue,i,2);
            foodParamTableModel.setValueAt(value,i,3);
          }
        }
        lockEvents--;
        vc2.unsaved(false);
      }
    }
    
    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==foodSetsList) && (lockEvents==0)) {
        lockEvents++;
        populateFoodParameters();
        populateParamABTable();
        foodTypesList.setEnabled(foodSetsList.getSelectedIndices().length==1);
        lockEvents--;
      
      } else if ((e.getSource()==foodTypesList) && (lockEvents==0)) {
      
      } else if ((e.getSource()==varDescTable.getSelectionModel() && varDescTable.getRowSelectionAllowed()) ||
                (e.getSource()==varDescTable.getColumnModel().getSelectionModel() && varDescTable.getColumnSelectionAllowed())) {
        lockEvents++;
        populateParamABTable();
        lockEvents--;
      } else if ((e.getSource()==foodParamTable.getSelectionModel() && varDescTable.getRowSelectionAllowed()) ||
          (e.getSource()==foodParamTable.getColumnModel().getSelectionModel() && varDescTable.getColumnSelectionAllowed())) {
        applyAllFood.setEnabled(foodParamTable.getSelectedRows().length==1);
      }
    }

    public void tableChanged(TableModelEvent e) {
      if ((e.getSource()==foodParamTableModel) && (lockEvents==0)) {
        lockEvents++;
        updateInternalABTags();
        applyAllFood.setEnabled(foodParamTable.getSelectedRows().length==1);
        vc2.unsaved(false);
        lockEvents--;
      }
    }

    public void mouseReleased(MouseEvent e) {
      if ((e.getSource()==foodTypesList) && (lockEvents==0)) {
        lockEvents++;
        alterFoodTypeSelection();
        vc2.unsaved(false);
        lockEvents--;
      }
      
    }

    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
  }

  
  class ParameterTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) {
      return ((col==1) || (col==2));
    }
  }
  
  class ReadOnlyTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) { return false; }
  }
}
