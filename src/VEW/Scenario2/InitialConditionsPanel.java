package VEW.Scenario2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.VEWUtilities;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.VEWController2;

public class InitialConditionsPanel extends JPanel  {
  private static VEWController2 vc2;
  private DepthTableModel depthValTableModel = new DepthTableModel();
  private JTable depthValTable = new JTable(depthValTableModel);
  private JButton fillBetween = new JButton("Interpolate");
  private DepthGraphPanel dgp = new DepthGraphPanel();
  private DefaultListModel depthEntriesModel = new DefaultListModel();  
  private JList depthEntryList = new JList(depthEntriesModel);
  private DefaultListModel importModel = new DefaultListModel();  
  private JList importList = new JList(importModel);
  private JToggleButton previewData = new JToggleButton("Preview");
  private JButton useData = new JButton("Use Data");
  private JTextField columnDepth = new JTextField("500");

  private JComboBox availableDatasets = new JComboBox();
  private XMLTag theModel;
  private EventHandler eh = new EventHandler();
  private int lockEvents = 0;
  
  public void addVoidFieldTag(String name) {
    XMLTag fieldTag = new XMLTag("field");
    fieldTag.setAttribute("name", name);
    fieldTag.setAttribute("source","Zero");
    int colSize=Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
    StringBuffer data = new StringBuffer();
    for (int i=0; i<colSize-1; i++) data.append("0,");
    data.append("0");
    fieldTag.setValue(data.toString());
    theModel.getTag("initialconditions").addTag(fieldTag);
  }
  
  public boolean checkTag(String name, boolean physics, boolean fix, StringBuffer errorString) {
    boolean ok = true;
    if (theModel.getTag("initialconditions").getTagWhere("field","@name",name)==null) {
      if (fix) {
        addVoidFieldTag(name);
        errorString.append("Created Zero "+name+" Profile\n");
      } else ok = false;
    }
    return ok;
  }
  
  public boolean greenLight(boolean fix) {
    lockEvents++;
    StringBuffer errorString = new StringBuffer();
    boolean ok = true;
    if (theModel.getTag("initialconditions")==null) {
      if (fix) {
        theModel.addTag(new XMLTag("initialconditions"));
        theModel.getTag("initialconditions").setAttribute("size","500");
        errorString.append("Creating Initial Conditions\n");
      } else ok = false;
    }
    if (ok) ok = checkTag("Density",true,fix,errorString);
    if (ok) ok = checkTag("Salinity",true,fix,errorString);
    if (ok) ok = checkTag("Temperature",true,fix,errorString);
    XMLTag[] chems = theModel.getTags("chemical");
    for (int i=0; i<chems.length; i++) {
      if (ok) ok = checkTag(chems[i].getValue("name"),false,fix,errorString);
    }
    if (ok) {
      if (theModel.getTag("initialconditions").getTag("turbocline")==null) {
        if (fix) {
          theModel.getTag("initialconditions").addTag(new XMLTag("turbocline","0.0"));
          errorString.append("Created Zero Initial Turbocline\n");
        }
      }
    }
    if (ok) {
      XMLTag[] fieldChems = theModel.getTag("initialconditions").getTags("field");
      for (int i=0; i<fieldChems.length; i++) {
        String chem = fieldChems[i].getAttribute("name");
        if (!((chem.equals("Density")) || (chem.equals("Salinity")) || (chem.equals("Temperature")))) {
          XMLTag findChem = theModel.getTagWhere("chemical","name",chem);
          if (findChem==null) {
            if (fix) {
              fieldChems[i].removeFromParent();
              errorString.append("Removed profile - "+chem+" not found in model.\n");
            } else {
              ok=false;
              i=fieldChems.length;
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
      populateDepthList();
      initialiseAvailableImports();
      depthEntryList.setSelectedIndex(0);
      initialiseTable();
      columnDepth.setText(theModel.getTag("initialconditions").getAttribute("size"));
      updateImportList();
    }
    lockEvents--;
    return ok;
  }
  
  public void initialiseAvailableImports() {
    availableDatasets.removeAllItems();
    availableDatasets.addItem("Levitus");
  }
  
  public void updateImportList() {
    lockEvents++;
    importModel.clear();
    String s = depthEntryList.getSelectedValue().toString();
    if ((s.equals("Temperature")) || (s.equals("Density")) || (s.equals("Salinity"))) {
      importModel.addElement("Density");
      importModel.addElement("Salinity");
      importModel.addElement("Temperature");
    } else if (theModel.getTagWhere("chemical","name",s)!=null) {
      importModel.addElement("Nitrate");
      importModel.addElement("Phosphate");
      importModel.addElement("Silicate");
    } else if (s.equals("Turbocline")) {
      importModel.addElement("Turbocline");
    }
    StringTools.setListItem(importList,s);
    updateButtons();
    lockEvents--;
  }
  
  
  public void populateDepthList() {
    depthEntriesModel.clear();
    ArrayList a = new ArrayList();
    a.add("Turbocline");
    a.add("Temperature");
    a.add("Salinity");
    a.add("Density");
    XMLTag[] chems = theModel.getTags("chemical");
    for (int i=0; i<chems.length; i++) {
      a.add(chems[i].getValue("name"));
    }
    while (a.size()>0) {
      String s = StringTools.nonSpace(a.get(0).toString()).toUpperCase();
      int best = 0;
      for (int i=1; i<a.size(); i++) {
        if (StringTools.nonSpace(a.get(i).toString()).toUpperCase().compareTo(s)<0) {
          s = StringTools.nonSpace(a.get(i).toString()).toUpperCase();
          best = i;
        }
      }
      depthEntriesModel.addElement(a.get(best).toString());
      a.remove(best);
    }
    a = null;
  }
  
  public void initialiseTable() {
    while (depthValTableModel.getRowCount()>0) depthValTableModel.removeRow(0);
    String s = depthEntryList.getSelectedValue().toString();
    if ((s.equals("Density")) || (s.equals("Temperature")) || (s.equals("Salinity")) || 
         (theModel.getTagWhere("chemical","name",s)!=null)) {
      StringBuffer sb = new StringBuffer(theModel.getTag("initialconditions").getTagWhere("field","@name",s).getValue());
      sb.append(",");
      int colSize = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
      for (int i=0; i<colSize; i++) {
        double d = Double.parseDouble(sb.substring(0,sb.indexOf(",")));
        sb.delete(0,sb.indexOf(",")+1);
        depthValTableModel.addRow(new String[] {i+".0",String.valueOf(d)});
      }
            
    } else if (s.equals("Turbocline")) { 
      double d = Double.parseDouble(theModel.getValue("initialconditions/turbocline"));  
      depthValTableModel.addRow(new String[] {"Depth /m",String.valueOf(d)});
      
    }
  }
  
  public void showPreview() {
    lockEvents++;
    String pathName = "Levitus"+File.separator;
    //String dataSet = availableDatasets.getSelectedItem().toString();
    String importName = importList.getSelectedValue().toString();
    // Find date, longitude, latitude.
    
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTimeInMillis(Long.parseLong(theModel.getValue("track/start")));
    int StartMonth = gc.get(GregorianCalendar.MONTH);
    int StartDayOfYear = gc.get(GregorianCalendar.DAY_OF_YEAR);
    double longitude = Double.parseDouble(theModel.getValue("track/longitude"));
    double latitude = Double.parseDouble(theModel.getValue("track/latitude"));
    double[] ProfileData = new double[0];
    boolean physics = ((importName.equals("Density")) || (importName.equals("Salinity")) || 
                       (importName.equals("Temperature")));
    boolean turbocline = (importName.equals("Turbocline"));
    if (turbocline) importName = "MixedLayerDepth";
    boolean chemistry = (importName.equals("Nitrate")) || (importName.equals("Phosphate")) ||
                        (importName.equals("Silicate"));
    if ((physics) || (turbocline)) {
      int SecondMonth = StartMonth;
      double[] Weights = new double[2];
      if ((StartDayOfYear * 2) > VEWUtilities.StandardMonthLength[StartMonth]) { // If StartDay is in 2nd half of month
        SecondMonth = (SecondMonth + 1) % 12; // Get the following month 
        Weights[0] = StartDayOfYear - (VEWUtilities.StandardMonthMiddle[StartMonth] - VEWUtilities.StandardMonthStart[StartMonth]);
        int MiddleGap = VEWUtilities.StandardMonthMiddle[SecondMonth] - VEWUtilities.StandardMonthMiddle[StartMonth];
        if (MiddleGap < 0) MiddleGap += 365;
        Weights[0] /= MiddleGap;
        Weights[1] = 1 - Weights[0];
      } else { // StartDay is in first half of month
        StartMonth = (StartMonth + 11) % 12; // Get previous month number
        Weights[1] = (VEWUtilities.StandardMonthMiddle[SecondMonth] - VEWUtilities.StandardMonthStart[SecondMonth]) - StartDayOfYear;
        int MiddleGap = VEWUtilities.StandardMonthMiddle[SecondMonth] - VEWUtilities.StandardMonthMiddle[StartMonth];
        if (MiddleGap < 0) MiddleGap += 365;
        Weights[1] /= MiddleGap;
        Weights[0] = 1 - Weights[1];
      }

      int layers=1;
      if (physics) layers=14;
      String theText = importName+ VEWUtilities.LongMonthName[StartMonth]+".vsi";
      ProfileData = FileIO.LoadProfile(latitude, longitude, 1, 1, layers, layers, pathName+theText);
      theText = importName+VEWUtilities.LongMonthName[SecondMonth]+".vsi";
      double[] ProfileData2 = FileIO.LoadProfile(latitude,longitude,1,1,layers,layers,pathName+theText);
      for (int i = 0; i < ProfileData.length; i++)
        ProfileData[i] = (ProfileData[i] * Weights[1]) + (ProfileData2[i] * Weights[0]);
    
    } else if (chemistry) { // Assume the annual variant.
      ProfileData = FileIO.LoadProfile(latitude,longitude, 1,1,14, 14, pathName+importName+".vsi");
    
    } 
    
    if ((chemistry) || (physics)) {
      int[] standardDepths = new int[] {0,10,20,30,50,75,100,125,150,200,250,300,400,500};
      for (int i=0; i<13; i++) {
        int depth1 = standardDepths[i];
        int depth2 = standardDepths[i+1];
        double value1 = ProfileData[i];
        double value2 = ProfileData[i+1];
        for (double j=depth1; j<depth2; j+=1.0) {
          double value = value1+((value2-value1)*((j-depth1)/(depth2-depth1)));
          depthValTableModel.setValueAt(String.valueOf(value),(int)j,1);
        }
      }
      dgp.paint(dgp.getGraphics());
    } else if (turbocline) {
      depthValTableModel.setValueAt(String.valueOf(ProfileData[0]),0,1);
    }
    lockEvents--;
    
  }
  
  public InitialConditionsPanel(VEWController2 parent, XMLTag model) {
    vc2 = parent;
    theModel=model;
    setLayout(new BorderLayout());
    JPanel tablePanel = new JPanel(new BorderLayout());
      JPanel graphTablePanel = new JPanel(new FlowLayout());
      JScrollPane dgpScroller = new JScrollPane(dgp);
        dgp.setPreferredSize(new Dimension(170,550));
        graphTablePanel.add(dgpScroller);
        JScrollPane depthValTableScroller = new JScrollPane(depthValTable);
        depthValTableScroller.setPreferredSize(new Dimension(200,550));
        graphTablePanel.add(depthValTableScroller);
        tablePanel.add(graphTablePanel,BorderLayout.CENTER);
      JPanel buttonPanel = new JPanel(new FlowLayout());
        
        buttonPanel.add(new JLabel("Size of column: "));
        buttonPanel.add(columnDepth);
        columnDepth.setPreferredSize(new Dimension(80,25));
        buttonPanel.add(new JLabel("metres"));
        buttonPanel.add(new JLabel("   "));
        buttonPanel.add(fillBetween);

        tablePanel.add(buttonPanel,BorderLayout.SOUTH);
    JPanel dataChoicePanel = new JPanel(new BorderLayout());
    dataChoicePanel.setPreferredSize(new Dimension(300,550));
      JPanel itemsToSet = new JPanel(new BorderLayout());
        itemsToSet.add(new JLabel("Items that require initialisation:-",JLabel.CENTER),BorderLayout.NORTH);
          JScrollPane depthItemsScroller = new JScrollPane(depthEntryList);
          depthItemsScroller.setPreferredSize(new Dimension(150,200));
          itemsToSet.add(depthItemsScroller,BorderLayout.CENTER);
      dataChoicePanel.add(itemsToSet,BorderLayout.NORTH);
      JPanel availableData = new JPanel(new BorderLayout());
        availableData.setPreferredSize(new Dimension(300,550));
        availableData.add(new JLabel("Data sets available for import:-",JLabel.CENTER),BorderLayout.NORTH);
        JPanel importBits = new JPanel(new BorderLayout());
        importBits.add(availableDatasets,BorderLayout.NORTH);
        JScrollPane importScroller = new JScrollPane(importList);
        importBits.add(importScroller,BorderLayout.CENTER);
        availableData.add(importBits,BorderLayout.CENTER);
        JPanel importButtonHolder = new JPanel(new BorderLayout());
          JPanel importButtons = new JPanel(new GridLayout(2,1));
            importButtons.add(previewData,BorderLayout.SOUTH);
            importButtons.add(useData,BorderLayout.NORTH);
            importButtonHolder.add(importButtons,BorderLayout.SOUTH);
          availableData.add(importButtonHolder,BorderLayout.EAST);
      dataChoicePanel.add(itemsToSet,BorderLayout.NORTH);
      dataChoicePanel.add(availableData,BorderLayout.WEST);
    JPanel spacer = new JPanel();
    spacer.setPreferredSize(new Dimension(10,550));
    add(spacer,BorderLayout.CENTER);
    add(dataChoicePanel,BorderLayout.WEST);
    add(tablePanel,BorderLayout.EAST);
    depthValTableModel.setColumnCount(2);
    depthValTableModel.setColumnIdentifiers(new String[] {"Depth /m","Value"});
    depthEntryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    importList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    
    
    columnDepth.addKeyListener(eh);
    columnDepth.addFocusListener(eh);
    depthEntryList.addListSelectionListener(eh);
    importList.addListSelectionListener(eh);
    depthValTableModel.addTableModelListener(eh);
    depthValTable.addMouseListener(eh);
    fillBetween.addActionListener(eh);
    availableDatasets.addActionListener(eh);
    previewData.addActionListener(eh);
    useData.addActionListener(eh);
    fillBetween.setEnabled(false);
    previewData.setEnabled(false);
    
  }
  
  public void updateButtons() {
    fillBetween.setEnabled(depthValTable.getSelectedRows().length>1);
    previewData.setEnabled(importList.getSelectedIndices().length>0);
  }
  
  public void updateData() {
    String name = depthEntryList.getSelectedValue().toString();
    if (name.equals("Turbocline"))
      theModel.getTag("initialconditions").setValue("turbocline",depthValTable.getValueAt(0,1).toString());
      
    else {
      XMLTag theField = theModel.getTag("initialconditions").getTagWhere("field","@name",name);
      StringBuffer data = new StringBuffer();
      int colSize = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
      for (int i=0; i<colSize-1; i++) {
        data.append(depthValTable.getValueAt(i,1).toString());
        data.append(',');
      }
      data.append(depthValTable.getValueAt(499,1).toString());
      theField.setValue(data.toString());
    }
    vc2.unsaved(false);    
  }
    
  public void updateColDepth() {
    int colSize=500;
    try { 
      colSize = Integer.parseInt(columnDepth.getText());
    } catch (Exception ex) {}
    if (colSize<500) {
      colSize=500;
      columnDepth.setText("500");
    }
    theModel.getTag("initialconditions").setAttribute("size",String.valueOf(colSize));
    int i = depthValTable.getRowCount();
    while (colSize>i) {
      depthValTableModel.addRow(new String[] {String.valueOf(i)+".0",depthValTable.getValueAt(i-1,1).toString()});
      i++;
    }
    while (i>colSize) {
      depthValTableModel.removeRow(i-1);
      i--;
    }
    XMLTag[] fields = theModel.getTag("initialconditions").getTags("field");
    for (i=0; i<fields.length; i++) {
      StringBuffer data = new StringBuffer(fields[i].getValue());
      double[] vals = new double[colSize];
      int j=0;
      while (data.length()>0) {
        if (data.indexOf(",")>=0) {
          if (j<vals.length) vals[j] = Double.parseDouble(data.substring(0,data.indexOf(",")));
          data.delete(0,data.indexOf(",")+1);
        } else {
          if (j<vals.length) vals[j] = Double.parseDouble(data.toString());
          data.delete(0,data.length());
        }
        j++;
      }
      for (int k=j; k<vals.length; k++) vals[k]=vals[j-1];
      for (int k=0; k<vals.length; k++) {
        if (k<vals.length-1) data.append(String.valueOf(vals[k])+",");
        else data.append(String.valueOf(vals[k]));
      }
      fields[i].setValue(data.toString());
    }
    updateData();
    if (dgp.getGraphics()!=null) dgp.paint(dgp.getGraphics());
    vc2.unsaved(true);
  }
  
  
  
  class EventHandler implements ActionListener, ListSelectionListener, TableModelListener, MouseListener, FocusListener, KeyListener {
    public EventHandler() {}

    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==fillBetween) {
        lockEvents++;
        int[] selection = depthValTable.getSelectedRows();
        int max = selection[0];
        int min = selection[0];
        for (int i=0; i<selection.length; i++) {
          if (selection[i]>max) max = selection[i];
          if (selection[i]<min) min = selection[i];
        }
        double val1 = Double.parseDouble(depthValTableModel.getValueAt(min,1).toString());
        double val2 = Double.parseDouble(depthValTableModel.getValueAt(max,1).toString());        
        double depth1 = Double.parseDouble(depthValTableModel.getValueAt(min,0).toString());
        double depth2 = Double.parseDouble(depthValTableModel.getValueAt(max,0).toString());
        for (int i=min+1; i<=max-1; i++) {
          double instdepth = Double.parseDouble(depthValTableModel.getValueAt(i,0).toString());
          double newVal = val1+(((instdepth-depth1)/(depth2-depth1))*(val2-val1));
          depthValTableModel.setValueAt(String.valueOf(newVal),i,1);
        }
        lockEvents--;
        updateData();
        dgp.paint(dgp.getGraphics());
      
      } else if ((e.getSource()==availableDatasets) && (lockEvents==0)) {
        lockEvents++;
        updateImportList();
        lockEvents--;
        
      } else if ((e.getSource()==previewData) && (lockEvents==0)) {
        lockEvents++;
        boolean previewing = previewData.isSelected();
        depthValTable.setEnabled(!previewing);
        if (previewing) showPreview();
        else {
          initialiseTable();
          dgp.paint(dgp.getGraphics());
        }
        lockEvents--;
     
      } else if (e.getSource()==useData) {
        lockEvents++;
        showPreview();
        updateData();
        previewData.setSelected(false);
        depthValTable.setEnabled(true);
        lockEvents--;
        
      }
    }
    
    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==depthEntryList) && (lockEvents==0)) {
        lockEvents++;
        // Check for unsaved changes
        initialiseTable();
        updateImportList();
        previewData.setSelected(false);
        lockEvents--;
        dgp.paint(dgp.getGraphics());
      
      } else if ((e.getSource()==importList) && (lockEvents==0)) {
        lockEvents++;
        if (previewData.isSelected()) {
          showPreview();
        }
        updateButtons();
        lockEvents--;
      }
    }

    public void tableChanged(TableModelEvent e) {
      if ((e.getSource()==depthValTableModel) && (lockEvents==0)) {
        lockEvents++;
        updateData();
        dgp.paint(dgp.getGraphics());
        lockEvents--;
      }
    }

    public void mouseClicked(MouseEvent e) {
      if (e.getSource()==depthValTable) updateButtons();
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void focusGained(FocusEvent e) {}

    public void focusLost(FocusEvent e) {
      if ((e.getSource()==columnDepth) && (lockEvents==0)) {
        lockEvents++;
        updateColDepth();
        lockEvents--;
      }
    }

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {
      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
        lockEvents++;
        updateColDepth();
        lockEvents--;
      }

      
    }

    public void keyTyped(KeyEvent e) {}
  }
  
  class DepthGraphPanel extends JPanel {
    Font smallFont = new Font("Arial",Font.PLAIN,9);
    public void paint(Graphics g) {
      
      g.setColor(getBackground());
      g.fillRect(0,0,181,551);
      g.setColor(Color.WHITE);
      g.fillRect(30,30,120,500);
      g.setColor(Color.black);
      g.drawLine(20,30,20,530);
      g.setFont(smallFont);
      int colSize = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
      float step = colSize/20f; 
      for (float i=0; i<=colSize; i+=step) {
        int pix = (int) ((i/colSize*1.0f)*500);
        g.drawLine(18,30+pix,22,30+pix);
        int width = g.getFontMetrics(smallFont).stringWidth(String.valueOf((int)i));
        g.drawString(String.valueOf((int)i),16-width,33+pix);
      }
      g.setColor(new Color(128,0,0));
      if (depthValTableModel.getRowCount()>0) {      
        if (!depthValTableModel.getValueAt(0,0).toString().equals("Depth /m")) {
          int rows = depthValTableModel.getRowCount();
          double depth, val;
          double max=0;
          double min=0;
          int lastX=0;
          int lastY=0;
          int newX=0;
          int newY=0;
          for (int i=0; i<rows; i++) {
            val = Double.parseDouble(depthValTableModel.getValueAt(i,1).toString());
            if (i==0) { max=val; min = val; }
            else {
              if (val>max) max=val;
              if (val<min) min=val;
            }
          }
          for (int i=0; i<rows; i++) {
            depth = Double.parseDouble(depthValTableModel.getValueAt(i,0).toString());
            val = Double.parseDouble(depthValTableModel.getValueAt(i,1).toString());
            if (i==0) {
              lastX= (int) (((val-min)/(max-min))*120);
              lastY = (int) (500*((depth)/(1.0f*colSize)));
            } else {
              newX= (int) (((val-min)/(max-min))*120);
              newY = (int) (500*((depth)/(1.0f*colSize)));
              g.drawLine(30+lastX,30+lastY,30+newX,30+newY);
              lastX=newX;
              lastY=newY;
            }
          }
        } else {
          double turboDepth = Double.parseDouble(depthValTableModel.getValueAt(0,1).toString());
          turboDepth = (int) (500*(turboDepth/(1.0f*colSize)));
          g.drawLine(30,30+(int)turboDepth,149,30+(int)turboDepth);
        }
      }
    }
    
    public DepthGraphPanel() {
      super();
    }
  }
  
  class DepthTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) { return (col==1); }
  }
  
}