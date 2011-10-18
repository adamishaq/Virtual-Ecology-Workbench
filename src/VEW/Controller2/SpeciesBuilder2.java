package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class SpeciesBuilder2 extends JPanel {
  DefaultListModel fgListModel = new DefaultListModel();
  JList fgList = new JList(fgListModel);
  DefaultListModel speciesListModel = new DefaultListModel();
  JList specList = new JList(speciesListModel);
  ParameterTableModel parameterTableModel = new ParameterTableModel();
  JTable parameterTable = new JTable(parameterTableModel);
  JButton removeSpecies = new JButton("Remove Species");
  JButton renameSpecies = new JButton("Rename Species");  
  JButton addSpecies = new JButton("Add Species");
  JButton overwriteSpecies = new JButton("Overwrite Species");  
  JButton addMany = new JButton("Add Many Species");
  JComboBox pickParamX = new JComboBox();
  JTextField xValue = new JTextField(8);
  XMLTag theModel = null;
  EventHandler eh = new EventHandler();
  int lockEvents = 0;
  boolean tableChanged = false;
  VEWController2 vc2;
  
  
  public boolean greenLight(boolean fix) {
    lockEvents++;
    StringBuffer errorString = new StringBuffer();
    boolean ok = true;
    if (theModel.getTag("species")==null) {
      if (fix) {
        theModel.addTag(new XMLTag("species"));
        errorString.append("Creating species\n");
      } else ok = false;
    }
    if (ok) {
      XMLTag speciesTag = theModel.getTag("species");
      XMLTag[] fgs = theModel.getTags("functionalgroup");
      
      for (int i=0; i<fgs.length; i++) {
        if (speciesTag.getTagWhere("species","@fg",fgs[i].getValue("name"))==null) {
          if (fix) {
            XMLTag newSpecies = new XMLTag("species");
            newSpecies.setAttribute("fg",fgs[i].getValue("name"));
            newSpecies.setAttribute("name",fgs[i].getValue("name")+" Species");  
            newSpecies.setAttribute("x","0.0");
            speciesTag.addTag(newSpecies);
            errorString.append("Created "+fgs[i].getValue("name")+" Species\n");
          } else {
            ok = false;
            i = fgs.length;
          }
        }
      }
  
      if (ok) {
        XMLTag[] speciesTags = speciesTag.getTags("species");
      
        for (int i=0; i<speciesTags.length; i++) {
          XMLTag theSpecies = speciesTags[i];
          String fgName = theSpecies.getAttribute("fg");
          if (theModel.getTagWhere("functionalgroup","name",fgName)==null) {
            if (fix) {
              errorString.append("Removing "+theSpecies.getAttribute("name")+" - parent functional group "+fgName+" not found\n");
              theSpecies.removeFromParent();  
            } else {
              ok = false;
              i = speciesTags.length;
            }
          } else {
            XMLTag theFG = theModel.getTagWhere("functionalgroup","name",fgName);
            XMLTag[] params = theFG.getTags("parameter");
            for (int j=0; j<params.length; j++) {
              XMLTag speciesParam = theSpecies.getTagWhere("param","@name",params[j].getValue("name"));
              if (speciesParam==null) {
                if (fix) {
                  errorString.append("Adding default value for parameter "+params[j].getValue("name")+", for "+theSpecies.getAttribute("name")+"\n");
                  XMLTag newParam = new XMLTag("param");
                  newParam.setAttribute("name",params[j].getValue("name"));
                  newParam.setAttribute("a",params[j].getValue("value"));
                  newParam.setAttribute("b","0.0");
                  theSpecies.addTag(newParam);
                  XMLTag.sortTagList(theSpecies,"param","@name");
                } else {
                  ok = false;
                  j = params.length;
                }
              }
            }
            if (ok) {
              XMLTag[] speciesParams = theSpecies.getTags("param");
              for (int j=0; j<speciesParams.length; j++) {
                if (theFG.getTagWhere("parameter","name",speciesParams[j].getAttribute("name"))==null) {
                  if (fix) {
                    errorString.append("Removing "+speciesParams[j].getAttribute("name")+" - not found in "+theSpecies.getAttribute("fg")+"\n");
                    speciesParams[j].removeFromParent();
                  } else {
                    ok = false;
                    j = speciesParams.length;
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
    }
    lockEvents--;
    loadExistingData();
    return ok;
  }
  
  public void loadExistingData() {
    lockEvents++;
    fgListModel.clear();
    XMLTag[] fgs = theModel.getTags("functionalgroup");
    for (int i=0; i<fgs.length; i++) 
      fgListModel.addElement(fgs[i].getValue("name"));
    if (fgListModel.getSize()>0) fgList.setSelectedIndex(0);
    speciesListModel.clear();
    XMLTag spec = theModel.getTag("species");
    if (spec!=null) {
      XMLTag[] specs = spec.getTags("species");
      XMLTag.sortTags(specs,"@name");
      for (int i=0; i<specs.length; i++)
        speciesListModel.addElement(specs[i].getAttribute("name"));
    }
    initialiseCombo();
    if (fgListModel.getSize()>0) initialiseTable();
    updateButtons();
    lockEvents--;
        
  }
    
  public void initialiseTable() {
    lockEvents++;
    for (int i=parameterTableModel.getRowCount()-1; i>=0; i--) parameterTableModel.removeRow(i);
    if (fgList.getSelectedValue()!=null) {
      XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",fgList.getSelectedValue().toString());
      XMLTag[] params = XMLTag.sortTags(fgTag.getTags("parameter"),"name");
      for (int i=0; i<params.length; i++)
        parameterTableModel.addRow(new Object[] {params[i].getValue("name"),params[i].getValue("desc"),new Double(Double.parseDouble(params[i].getValue("value"))),new Double(0.0),new Double(Double.parseDouble(params[i].getValue("value")))});
      tableChanged = false;
    }
    lockEvents--;
  }

 public void initialiseCombo() {
    lockEvents++;
    String remember = null;
    if (pickParamX.getSelectedItem()!=null) remember = pickParamX.getSelectedItem().toString();
    String fg = null;
    if (fgList.getSelectedValue()!=null) fg = fgList.getSelectedValue().toString();
    pickParamX.removeAllItems();
    if (fg!=null) {
      XMLTag theFG = theModel.getTagWhere("functionalgroup","name",fg);
      if (theFG!=null) {
        XMLTag[] params = theFG.getTags("parameter");
        params = XMLTag.sortTags(params,"name");
        for (int i=0; i<params.length; i++) {
          final String name = params[i].getValue("name");
          String desc = params[i].getValue("desc");
          if (desc==null) desc="";
          pickParamX.addItem(name+" ("+desc+")");
        }
      }
    }
    pickParamX.setSelectedIndex(-1);
    if (remember!=null) StringTools.setStringItem(pickParamX,remember);
    lockEvents--;
  }
  
  public void updateButtons() {
    removeSpecies.setEnabled(specList.getSelectedIndices().length>=1);
    renameSpecies.setEnabled(specList.getSelectedIndices().length==1);
    overwriteSpecies.setEnabled(tableChanged && specList.getSelectedIndices().length==1);
    addSpecies.setEnabled(fgList.getSelectedIndices().length==1);
    addMany.setEnabled(fgList.getSelectedIndices().length==1);
  }
  
  public SpeciesBuilder2(JDialog parent, XMLTag model) {
    theModel = model;
    vc2 = (VEWController2) parent;
    setLayout(new BorderLayout());
    JPanel freqSpeciesPanel = new JPanel(new BorderLayout());
      JPanel fgPanel = new JPanel(new BorderLayout());
        JScrollPane fgScroller = new JScrollPane(fgList);
        fgPanel.add(fgScroller,BorderLayout.CENTER);  
        fgPanel.add(new JLabel("Functional Groups"),BorderLayout.NORTH);
        freqSpeciesPanel.add(fgPanel,BorderLayout.WEST);
        fgScroller.setPreferredSize(new Dimension(200,200));
      JPanel specPanel = new JPanel(new BorderLayout());
        JScrollPane specScroller = new JScrollPane(specList);
        specScroller.setPreferredSize(new Dimension(200,200));
        specPanel.add(new JLabel("Species"),BorderLayout.NORTH);        
        specPanel.add(specScroller, BorderLayout.CENTER);
        JPanel editRemoveSpecies = new JPanel(new FlowLayout());
        editRemoveSpecies.add(removeSpecies);
        editRemoveSpecies.add(renameSpecies);
        specPanel.add(editRemoveSpecies, BorderLayout.SOUTH);
        freqSpeciesPanel.add(specPanel,BorderLayout.EAST);
      add(freqSpeciesPanel,BorderLayout.NORTH);
    
    JPanel paramTablePanel = new JPanel(new BorderLayout());
      JPanel tablePanel = new JPanel(new BorderLayout());
        JScrollPane paramScroller = new JScrollPane(parameterTable);
        paramScroller.setPreferredSize(new Dimension(600,200));
        paramTablePanel.add(paramScroller,BorderLayout.CENTER);  
        JPanel xParamPanel = new JPanel(new FlowLayout());
        xParamPanel.add(new JLabel("X-Value: "));
        xParamPanel.add(xValue);
        xParamPanel.add(new JLabel("Pick value of"));
        xParamPanel.add(pickParamX);
        tablePanel.add(paramScroller,BorderLayout.CENTER);
        tablePanel.add(xParamPanel,BorderLayout.SOUTH);
      paramTablePanel.add(tablePanel,BorderLayout.CENTER);
      JPanel paramButtons = new JPanel(new FlowLayout());
      paramButtons.add(addSpecies);
      paramButtons.add(addMany);
      paramButtons.add(overwriteSpecies);
      paramTablePanel.add(paramButtons,BorderLayout.SOUTH);
      add(paramTablePanel,BorderLayout.CENTER);
    
    parent.pack();
    xValue.setText("0.0");
    fgList.addListSelectionListener(eh);
    specList.addListSelectionListener(eh);
    pickParamX.addActionListener(eh);
    pickParamX.setPreferredSize(new Dimension(450,30));
    addSpecies.addActionListener(eh);
    renameSpecies.addActionListener(eh);
    addMany.addActionListener(eh);
    parameterTableModel.addTableModelListener(eh);
    xValue.addActionListener(eh);
    overwriteSpecies.addActionListener(eh);
    removeSpecies.addActionListener(eh);
    parameterTableModel.setColumnIdentifiers(new String[] {"Name", "Description", "A", "B", "Value"});
    tableChanged=false;
    TableColumnModel tcb = parameterTable.getColumnModel();
    tcb.getColumn(0).setPreferredWidth(125);
    tcb.getColumn(1).setPreferredWidth(225);
    tcb.getColumn(2).setPreferredWidth(70);
    tcb.getColumn(3).setPreferredWidth(70);
    tcb.getColumn(4).setPreferredWidth(110);
  }
    
  public boolean duplicateName(String name) {
    int i=0;
    boolean found = false;
    while (i<speciesListModel.size()) {
      if (speciesListModel.getElementAt(i).toString().equals(name)) {
        found=true;
        i=speciesListModel.size();
      }
      i++;
    }
    return found;
  }
  
  public void updateTable() {
    lockEvents++;
    for (int i=0; i<parameterTableModel.getRowCount(); i++) {
      double a = Double.parseDouble(parameterTable.getValueAt(i,2).toString());
      double b = Double.parseDouble(parameterTable.getValueAt(i,3).toString());
      double x = Double.parseDouble(xValue.getText());
      double value = a*Math.pow(x,b);
      parameterTable.setValueAt(String.valueOf(a), i, 2);
      parameterTable.setValueAt(String.valueOf(b), i, 3);
      parameterTable.setValueAt(String.valueOf(value), i,4);
      xValue.setText(String.valueOf(x));
    }
    lockEvents--;
  }
  
  class ParameterTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) {
      return ((col==2) || (col==3));
    }
  }
  
  class EventHandler implements ActionListener, ListSelectionListener, TableModelListener  {
    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
     
     if ((e.getSource()==pickParamX) && (lockEvents==0)) {
       lockEvents++;
       String fgName = "";
       if (fgList.getSelectedValue()!=null) {
         fgName = fgList.getSelectedValue().toString();
         String paramName = "";
         if (pickParamX.getSelectedItem()!=null) {
           paramName=pickParamX.getSelectedItem().toString();
           paramName = paramName.substring(0,paramName.indexOf("(")-1);
           XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",fgName);  
           XMLTag paramTag = fgTag.getTagWhere("parameter","name",paramName);
           xValue.setText(paramTag.getValue("value"));
           updateTable();
         }
         //pickParamX.setSelectedIndex(-1);
       }
       lockEvents--;
        
      } else if (e.getSource()==xValue) {
        lockEvents++;
        updateTable();
        pickParamX.setSelectedIndex(-1);
        lockEvents--;
      
      } else if (e.getSource()==addSpecies) {
        lockEvents++;
        String fgName = fgList.getSelectedValue().toString();
        String name = JOptionPane.showInputDialog(SpeciesBuilder2.this,"Type name for species", fgName);
        if(name!=null) {
          boolean okToGo = true;
          if (duplicateName(name)) {
            JOptionPane.showMessageDialog(SpeciesBuilder2.this, "A species of that name already exists.");
            okToGo=false;
          }
          if (okToGo) {
            speciesListModel.addElement(name);
            XMLTag newSpecies = new XMLTag("species");
            newSpecies.setAttribute("name",name);
            newSpecies.setAttribute("fg",fgList.getSelectedValue().toString());
            newSpecies.setAttribute("x",xValue.getText());
            int rowCount = parameterTable.getRowCount();
            for (int i=0; i<rowCount; i++) {
              XMLTag newParam = new XMLTag("param");
              newParam.setAttribute("name",parameterTable.getValueAt(i,0).toString());
              newParam.setAttribute("a",parameterTable.getValueAt(i,2).toString());
              newParam.setAttribute("b",parameterTable.getValueAt(i,3).toString());
              newSpecies.addTag(newParam);
            }
            theModel.getTag("species").addTag(newSpecies);
            vc2.unsaved(true);
          }
        }
        lockEvents--;
      
      } else if (e.getSource()==overwriteSpecies) {
        lockEvents++;
        String speciesName = specList.getSelectedValue().toString();
        XMLTag theSpecies = theModel.getTag("species").getTagWhere("species","@name",speciesName);
        theSpecies.setAttribute("x",xValue.getText());
        int rowCount = parameterTable.getRowCount();
        for (int i=0; i<rowCount; i++) {
          String param = parameterTable.getValueAt(i,0).toString();
          XMLTag theParam = theSpecies.getTagWhere("param","@name",param);
          theParam.setAttribute("a",parameterTable.getValueAt(i,2).toString());
          theParam.setAttribute("b",parameterTable.getValueAt(i,3).toString());
        }
        tableChanged=false;
        updateButtons();
        StringTools.setListItem(specList,speciesName);
        vc2.unsaved(true);
        lockEvents--;
      
      } else if (e.getSource()==addMany) {
        lockEvents++;        
        ManySpeciesDialog msd = new ManySpeciesDialog(vc2,fgList.getSelectedValue().toString());
        msd.setVisible(true);
        ArrayList results = msd.getSpeciesArray();
        if (results!=null) {
          for (int i=0; i<results.size(); i++) {
            String[] entry = (String[]) results.get(i);
            String name = entry[0];
            double xVal = Double.parseDouble(entry[1]);
            XMLTag newSpecies = new XMLTag("species");
            newSpecies.setAttribute("name",name);
            newSpecies.setAttribute("fg",fgList.getSelectedValue().toString());
            newSpecies.setAttribute("x",String.valueOf(xVal));
            int rowCount = parameterTable.getRowCount();
            for (int j=0; j<rowCount; j++) {
              XMLTag newParam = new XMLTag("param");
              newParam.setAttribute("name",parameterTable.getValueAt(j,0).toString());
              newParam.setAttribute("a",parameterTable.getValueAt(j,2).toString());
              newParam.setAttribute("b",parameterTable.getValueAt(j,3).toString());
              newSpecies.addTag(newParam);
            }
            theModel.getTag("species").addTag(newSpecies);
            speciesListModel.addElement(name);
          }
          vc2.unsaved(true);
        }
        lockEvents--;

      } else if (e.getSource()==removeSpecies) {
        lockEvents++;
        Object[] selected = specList.getSelectedValues();
        int[] selectedNos = specList.getSelectedIndices();
        for (int i=selected.length-1; i>=0; i--) {
          String speciesName = selected[i].toString();
          XMLTag removeMe = theModel.getTag("species").getTagWhere("species","@name",speciesName);
          removeMe.removeFromParent();
          speciesListModel.removeElementAt(selectedNos[i]);
        }
        removeSpecies.setEnabled(false);
        specList.clearSelection();
        fgList.clearSelection();
        for (int i=parameterTableModel.getRowCount()-1; i>=0; i--) parameterTableModel.removeRow(i);
        updateButtons();       
        vc2.unsaved(true);
        lockEvents--;
        
      } else if (e.getSource()==renameSpecies) {
        lockEvents++;
        String currentName = specList.getSelectedValue().toString();
        int index = specList.getSelectedIndex();
        String NewName = JOptionPane.showInputDialog("Please enter the new name for " + currentName, currentName);
        if ((NewName!=null) && (!NewName.equals(currentName))) {
          if (duplicateName(NewName)) {
            JOptionPane.showMessageDialog(SpeciesBuilder2.this,"That name already exists");
          } else {
            XMLTag theSpecies = theModel.getTag("species").getTagWhere("species","@name",currentName);
            theSpecies.setAttribute("name",NewName);
            speciesListModel.setElementAt(NewName,index); 
            vc2.unsaved(true);
          }
        }
        lockEvents--;
      }
    }

    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==fgList) && (lockEvents==0)) {
        lockEvents++;
        specList.clearSelection();
        int okToContinue = JOptionPane.YES_OPTION;
        if (tableChanged)
          okToContinue = JOptionPane.showConfirmDialog(SpeciesBuilder2.this, "There are unsaved changes in the parameter table. Ok to continue?",null,JOptionPane.YES_NO_OPTION);
        if (okToContinue == JOptionPane.YES_OPTION) {
          initialiseCombo();
          initialiseTable();
          
        }
        updateButtons();
        lockEvents--;
      
      } else if ((e.getSource()==specList) && (lockEvents==0)) {
        lockEvents++;
        updateButtons();
        int okToContinue = JOptionPane.YES_OPTION;
        if (tableChanged)
          okToContinue = JOptionPane.showConfirmDialog(SpeciesBuilder2.this, "There are unsaved changes in the parameter table. Ok to continue?",null,JOptionPane.YES_NO_OPTION);
        if (okToContinue == JOptionPane.YES_OPTION) {
          if (specList.getSelectedIndices().length==1) {
            String speciesName = specList.getSelectedValue().toString();
            XMLTag theSpecies = theModel.getTag("species").getTagWhere("species","@name",speciesName);
            StringTools.setListItem(fgList, theSpecies.getAttribute("fg"));
            int rowCount = parameterTableModel.getRowCount();
            for (int i=rowCount-1; i>=0; i--) parameterTableModel.removeRow(i);
            XMLTag[] theParams = theSpecies.getTags("param");
            theParams = XMLTag.sortTags(theParams,"@name");
            double theXValue = Double.parseDouble(theSpecies.getAttribute("x"));
            String fgName = theSpecies.getAttribute("fg");
            for (int i=0; i<theParams.length; i++) {
              double aValue = Double.parseDouble(theParams[i].getAttribute("a"));
              double bValue = Double.parseDouble(theParams[i].getAttribute("b"));

              XMLTag theFgParam = theModel.getTagWhere("functionalgroup","name",fgName).getTagWhere("parameter","name",theParams[i].getAttribute("name"));
              double value = aValue*Math.pow(theXValue,bValue);
              parameterTableModel.addRow(new Object[] {theParams[i].getAttribute("name"), theFgParam.getValue("desc"),theParams[i].getAttribute("a"),theParams[i].getAttribute("b"), new Double(value)});
            }
            xValue.setText(String.valueOf(theXValue));
            tableChanged=false;
            initialiseCombo();
          }
        } else {
          for (int i=parameterTableModel.getRowCount()-1; i>=0; i--) 
            parameterTableModel.removeRow(i);
          tableChanged=false;
        }
        updateButtons();
        lockEvents--;
      }
    }

    public void tableChanged(TableModelEvent e) {
      if ((e.getSource()==parameterTableModel) && (e.getType()==TableModelEvent.UPDATE) && (lockEvents==0)) {
        lockEvents++;
        updateTable();
        tableChanged = true;
        updateButtons();
        lockEvents--;
      }
    }
  }
}

class ManySpeciesDialog extends JDialog {
  DefaultTableModel xTableModel = new DefaultTableModel();
  JTable xTable = new JTable(xTableModel);
  JButton okButton = new JButton("OK");
  JButton cancelButton = new JButton("Cancel");
  JButton addButton = new JButton("Add");
  JButton removeButton = new JButton("Remove");
  MSEventHandler mseh = new MSEventHandler();
  String fgName = "";
  ArrayList species = new ArrayList();
  int countNew = 1;
  
  public ArrayList getSpeciesArray() { 
   return species;
  }
  
  public ManySpeciesDialog(JDialog parent, String _fgName) {
    super(parent,"Add Many Species",true);
    fgName=_fgName;
    JScrollPane tableScroller = new JScrollPane(xTable);
    tableScroller.setPreferredSize(new Dimension(400,200));
    xTableModel.setColumnIdentifiers(new String[] {"name", "X-Value"});
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(tableScroller,BorderLayout.WEST);
    JPanel buttonPanel = new JPanel(new BorderLayout());
    JPanel addPanel = new JPanel(new FlowLayout());
    addPanel.add(addButton);
    buttonPanel.add(addPanel,BorderLayout.NORTH);
    JPanel removePanel = new JPanel(new FlowLayout());
    removePanel.add(removeButton);
    buttonPanel.add(removePanel,BorderLayout.SOUTH);
    getContentPane().add(buttonPanel, BorderLayout.EAST);
    JPanel endButtons = new JPanel(new FlowLayout());
    endButtons.add(okButton);
    endButtons.add(cancelButton);
    getContentPane().add(endButtons,BorderLayout.SOUTH);
    pack();
    okButton.addActionListener(mseh);
    cancelButton.addActionListener(mseh);
    addButton.addActionListener(mseh);
    removeButton.addActionListener(mseh);
    xTableModel.addTableModelListener(mseh);
    removeButton.setEnabled(false);
  }
  
  class MSEventHandler implements ActionListener, TableModelListener {
    public MSEventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
      
      if (e.getSource()==cancelButton) {
        setVisible(false);
      
      } else if (e.getSource()==addButton) {
        xTableModel.addRow(new String[] {fgName+" "+(countNew++), "0.0"});
      
      } else if (e.getSource()==removeButton) {
        int[] sel = xTable.getSelectedRows();
        for (int i=sel.length-1; i>=0; i--) xTableModel.removeRow(sel[i]);
        removeButton.setEnabled(false);
      
      } else if (e.getSource()==okButton) {
        species.clear();
        for (int i=0; i<xTable.getRowCount(); i++) {
          String[] entry = new String[2];
          entry[0] = xTable.getValueAt(i,0).toString();
          entry[1] = xTable.getValueAt(i,1).toString();
          species.add(entry);
        }
        setVisible(false);
      }
    }

    public void tableChanged(TableModelEvent e) {
      if (e.getSource()==xTableModel) {
        removeButton.setEnabled(xTable.getSelectedRowCount()>=0);
      }
    }
  }
}

