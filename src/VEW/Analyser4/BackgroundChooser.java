package VEW.Analyser4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import VEW.Common.StringTools;
//import VEW.Common.XML.*;

public class BackgroundChooser extends JDialog {

  private JButton okButton = new JButton("OK");
  private JButton cancelButton = new JButton("Cancel");
  private JComboBox colourFilter = new JComboBox();
  private PreviewPanel colourPreview = new PreviewPanel();
  private JComboBox typeFilter = new JComboBox();
  private JComboBox topDepth = new JComboBox();
  private JComboBox bottomDepth = new JComboBox();
  private JComboBox grads = new JComboBox();
  private JButton backColour = new JButton();
  private JComboBox noContours = new JComboBox();
  private JCheckBox fillContours = new JCheckBox("Fill Contours?");
  private JCheckBox drawContourLines = new JCheckBox("Show Lines?");
  
  private DefaultListModel availableOptionsList = new DefaultListModel();
  private JList availableOptions = new JList(availableOptionsList);
  private DefaultListModel chosenOptionsList = new DefaultListModel();  
  private JList chosenOptions = new JList(chosenOptionsList);
  private ArrayList theChoices = new ArrayList();
  
  private JRadioButton sumOptions = new JRadioButton("Sum");
  private JRadioButton mulOptions = new JRadioButton("Product"); 
  private ButtonGroup optionsGroup = new ButtonGroup();
  private JButton addOption = new JButton("Add");
  private JButton removeOption = new JButton("Remove");
  
  
  private ArrayList layer = new ArrayList();
  private JCheckBox plotContours = new JCheckBox("Draw Contours?");
  private JCheckBox autoScale = new JCheckBox("Auto scale?");
  private JCheckBox invertY = new JCheckBox("Invert Y-Axis?");
  private JCheckBox plotLog = new JCheckBox("Plot Log?");  
  private JTextField minValue = new JTextField("0.0");
  private JTextField maxValue = new JTextField("0.0");
//  private static XMLTag dataFormats;
  private Document dataFormats;
  private boolean lock;
  private boolean gotData = false;
  private int type = -1;
  private int graphType = -1;
  private int topDepthVal = -1;
  private int bottomDepthVal = -1;
  private EventHandler eh = new EventHandler();
  private double minVal = 0.0;
  private double maxVal = 0.0;
  private ArrayList options = new ArrayList();
  private ArrayList XUnits = new ArrayList();
  private ArrayList YUnits = new ArrayList();
  private ArrayList ZUnits = new ArrayList();
  private ArrayList XTitles = new ArrayList();
  private ArrayList YTitles = new ArrayList();
  private ArrayList ZTitles = new ArrayList();
  private int oldTypeFilter = -2;  
  
  public static final byte DEFINED_LAYER = 0;  
  public static final byte PHYSICS_LAYER = 1;
  public static final byte BIOLOGICAL_LAYER = 2;
  public static final byte OP_ADD = 1;
  public static final byte OP_MUL = 2;
  public static int op = OP_ADD;
  private Analyser4 a4 = null;

  public Node convertToTag() {
//    XMLTag back = new XMLTag("background");
    Element back = DocumentFactory.getInstance().createElement("background");
    if (okToSave()) {
      back.addElement("type").addText(getFieldName());
      Node[] ids = getGraphOptions();
      for (int i=0; i<ids.length; i++)
        back.add(ids[i]);
      back.addElement("op").addText(String.valueOf(getOp()));
      back.addElement("back").addText(String.valueOf(getPlainColour().getRGB()));
      back.addElement("topdepth").addText(String.valueOf(getTopDepth()));
      back.addElement("bottomdepth").addText(String.valueOf(getBottomDepth()));
      back.addElement("auto").addText(String.valueOf(getAuto()));
      back.addElement("inverty").addText(String.valueOf(getInvert()));
      back.addElement("log").addText(String.valueOf(getLog()));
      back.addElement("max").addText(String.valueOf(getMax()));
      back.addElement("min").addText(String.valueOf(getMin()));
      back.addElement("palette").addText(getColourPalette());
      back.addElement("grads").addText(getGrads());
      back.addElement("contours").addText(String.valueOf(useContours()));
      back.addElement("contourcount").addText(String.valueOf(getNoContours()));
      back.addElement("fillcontours").addText(String.valueOf(getFillContours()));
      back.addElement("showlines").addText(String.valueOf(getDrawContours()));
    }
    return back;
  }
  
  public void setFromTag(Node back) {
    setFieldName(back.valueOf("type"));
    List ids = back.selectNodes("id");
    setGraphOptions(ids);
    setPlainColour(new Color(Integer.parseInt(back.valueOf("back"))));
    setOp(Integer.parseInt(back.valueOf("op")));    
    setTopDepth(Double.parseDouble(back.valueOf("topdepth")));
    setBottomDepth(Double.parseDouble(back.valueOf("bottomdepth")));
    setAuto(StringTools.parseBoolean(back.valueOf("auto")));
    setInvert(StringTools.parseBoolean(back.valueOf("inverty")));
    setLog(StringTools.parseBoolean(back.valueOf("log")));
    setMinMax(Double.parseDouble(back.valueOf("min")),Double.parseDouble(back.valueOf("max")));
    setUseContours(StringTools.parseBoolean(back.valueOf("contours")));
    setContourCount(back.valueOf("contourcount"));
    setGrads(back.valueOf("grads"));              
    setFillContours(StringTools.parseBoolean(back.valueOf("fillcontours")));
    setDrawContours(StringTools.parseBoolean(back.valueOf("showlines")));
    setColourPalette(back.valueOf("palette"));
  }
  
  
  
  private void initialize() {
    this.setSize(new java.awt.Dimension(152,34));
  }

  public void paint(Graphics g) {
    super.paint(g);
    colourPreview.repaint();
  }

  public boolean useScale() { return (typeFilter.getSelectedIndex()>0); }
  public boolean usePlain() { return (typeFilter.getSelectedIndex()<=0); }
  public Color getPlainColour() { return backColour.getBackground(); }
  public void setPlainColour(Color c) { backColour.setBackground(c); }
  public boolean getData() { return gotData; }
  public void setData(boolean b) { gotData = b; }
  public void setOp(int i) {
    if (i==OP_ADD) {
      sumOptions.setSelected(true);
      op=OP_ADD;
    } else if (i==OP_MUL) {
      mulOptions.setSelected(true);
      op=OP_MUL;
    }
  }
  
  public int getOp() {
    if (sumOptions.isSelected()) return OP_ADD;
    else if (mulOptions.isSelected()) return OP_MUL;
    else return -1;
  }
  
  public void init(Document formatFile) {
    dataFormats = formatFile;
  }
  
  public void show(Document formatFile) {
    init(formatFile);
    if (graphType != -1)
      typeFilter.setSelectedIndex(graphType);
    if ((topDepth.getItemCount()>0) && (topDepthVal != -1)) topDepth.setSelectedIndex(topDepthVal);
    if ((bottomDepth.getItemCount()>0) && (bottomDepthVal != -1)) bottomDepth.setSelectedIndex(bottomDepthVal);
    populateCombos();
    chosenOptionsList.removeAllElements();
    if (theChoices.size()>0)
      for (int i=0; i<theChoices.size(); i++)
        chosenOptionsList.addElement(availableOptionsList.getElementAt(((Integer)theChoices.get(i)).intValue()));
    pack();
    if (colourFilter.getSelectedIndex() == -1)
      colourFilter.setSelectedIndex(0);
    BackgroundColour.setType(colourFilter.getSelectedIndex());
    updateButtons();
    setVisible(true);
  }

  public String getFieldName() { 
    if (typeFilter.getSelectedItem()!=null) return typeFilter.getSelectedItem().toString();
    else return null;
  }
      
  
  public void setFieldName(String s) {
    StringTools.setStringItem(typeFilter,s); 
    populateType(); 
  }
  public int getGraphType() { return graphType; }
  public byte getLayerType() { return ((Byte) layer.get(typeFilter.getSelectedIndex())).byteValue(); }   
  public Node[] getGraphOptions() { 
    Node[] ids = new Node[theChoices.size()];
    DocumentFactory factory = DocumentFactory.getInstance();
    for (int i=0; i<theChoices.size(); i++) 
      ids[i]=factory.createElement("id").addText(String.valueOf(theChoices.get(i)));
    return ids;
  }
  
  public int[] getGraphOptionInts() { 
    int[] graphOptions = new int[theChoices.size()];
    for (int i=0; i<graphOptions.length; i++) graphOptions[i] = ((Integer) theChoices.get(i)).intValue();
    return graphOptions;
  } 
  
  public void setGraphOptions(List ids) { 
    chosenOptionsList.removeAllElements();
    theChoices.clear();
    for (Iterator i = ids.iterator(); i.hasNext();) {
      Node n = (Node) i.next();
      Integer choice = Integer.valueOf(n.getStringValue());
      theChoices.add(choice);
      chosenOptionsList.addElement(availableOptionsList.getElementAt(choice.intValue()));
    }
//    for (int j=0; j<ids.length; j++) {
//      theChoices.add(new Integer(Integer.parseInt(ids[j].getValue())));
//      chosenOptionsList.addElement(availableOptionsList.getElementAt(Integer.parseInt(ids[j].getValue())));
//    }
  }
     
  public int getTopDepthIndex() { return topDepthVal; }
  public int getBottomDepthIndex() { return bottomDepthVal; }
  public boolean okToSave() { return (topDepth.getSelectedItem()!=null); } 
  public double getTopDepth() { return Double.parseDouble(topDepth.getSelectedItem().toString()); }
  public double getBottomDepth() { return Double.parseDouble(bottomDepth.getSelectedItem().toString()); }
  public void setTopDepth(double d) { 
    StringTools.setDoubleItem(topDepth,d); 
    topDepthVal = topDepth.getSelectedIndex(); 
  }
  public void setBottomDepth(double d) { 
    StringTools.setDoubleItem(bottomDepth,d); 
    bottomDepthVal = bottomDepth.getSelectedIndex();
  }  
  public boolean useContours() { return plotContours.isSelected(); }
  public void setUseContours(boolean b) { 
    plotContours.setSelected(b);
    drawContourLines.setEnabled(b);
    noContours.setEnabled(b);
    fillContours.setEnabled(b);
  }
  public void setFillContours(boolean b) { fillContours.setSelected(b); }
  public void setDrawContours(boolean b) { drawContourLines.setSelected(b); }
  public void setContourCount(String s) { StringTools.setStringItem(noContours,s); } 
  public int getNoContours() { return noContours.getSelectedIndex()+2; }
  public boolean getFillContours() { return fillContours.isSelected(); }
  public boolean getDrawContours() { return drawContourLines.isSelected(); }
  public String getColourPalette() { return colourFilter.getSelectedItem().toString(); }
  public String getGrads() { return grads.getSelectedItem().toString(); }
  public void setGrads(String s) { StringTools.setStringItem(grads,s); }
  public void setColourPalette(String s) { 
    StringTools.setStringItem(colourFilter,s); 
    BackgroundColour.setType(colourFilter.getSelectedIndex());
    colourPreview.repaint();
  }
  public void setMinMax(double min, double max) { 
    minVal = min;
    minValue.setText(String.valueOf(min));
    maxVal = max;
    maxValue.setText(String.valueOf(max));
  }
  
  public double getMin() { return minVal; }
  public double getMax() { return maxVal; }

  public void setAuto(boolean b) { autoScale.setSelected(b); }
  public void setInvert(boolean b) { invertY.setSelected(b); }
  public void setLog(boolean b) { plotLog.setSelected(b); }
  public boolean getAuto() { return autoScale.isSelected(); }
  public boolean getInvert() { return invertY.isSelected(); }
  public boolean getLog() { return plotLog.isSelected(); }
  public String getXTitle() { if (typeFilter.getSelectedIndex()==-1) return ""; else return (String)XTitles.get(typeFilter.getSelectedIndex()); }
  public String getYTitle() { if (typeFilter.getSelectedIndex()==-1) return ""; else return (String)YTitles.get(typeFilter.getSelectedIndex());}  
  public String getZTitle() { if (typeFilter.getSelectedIndex()==-1) return ""; else return (String)ZTitles.get(typeFilter.getSelectedIndex());}  
  public String getXUnit() { if (typeFilter.getSelectedIndex()==-1) return ""; else return (String)XUnits.get(typeFilter.getSelectedIndex());}
  public String getYUnit() { if (typeFilter.getSelectedIndex()==-1) return ""; else return (String)YUnits.get(typeFilter.getSelectedIndex()); }  
  public String getZUnit() { if (typeFilter.getSelectedIndex()==-1) return ""; else return (String)ZUnits.get(typeFilter.getSelectedIndex());}  
  
  public void populateCombos() {
    if (dataFormats!=null) {
      int rememberType = typeFilter.getSelectedIndex();
      int rememberTopDepth = topDepth.getSelectedIndex();
      int rememberBottomDepth = bottomDepth.getSelectedIndex();
      int rememberColour = colourFilter.getSelectedIndex();
      lock = true;
      typeFilter.removeAllItems();
      layer.clear();
      typeFilter.addItem("- Plain Colour -");
      layer.add(new Byte((byte)-1));
      options.add(new ArrayList());
      XUnits.add("");
      YUnits.add("");
      XTitles.add("");
      YTitles.add("");
//      XMLTag[] fields = dataFormats.getTags("field");
      List fields = dataFormats.selectNodes("/dataformat/field");
      for (Iterator i = fields.iterator(); i.hasNext(); ) {
        Node field = (Node) i.next();
//        XMLTag field = fields[i];
//        XMLTag[] vars = field.getTags("var");
        List vars = field.selectNodes("var");
        if (vars.size()>0) {
          typeFilter.addItem(field.valueOf("name"));
          List temp = new ArrayList();
          for (Iterator j = vars.iterator(); j.hasNext();) {
            Node n = (Node) j.next();
            temp.add(n.valueOf("desc"));
          }
        
          options.add(temp);
//          XMLTag dim1 = field.getTag("dimensions").getTag("dim",1);
          Node dim1 = field.selectSingleNode("dimensions/dim[1]");
          XUnits.add(dim1.valueOf("@units"));
          XTitles.add(dim1.valueOf("@label"));
        
//          XMLTag dim2 = field.getTag("dimensions").getTag("dim",2);
          Node dim2 = field.selectSingleNode("dimensions/dim[2]");
          YUnits.add(dim2.valueOf("@units"));
          YTitles.add(dim2.valueOf("@label"));
                
          String layerName = dim2.valueOf("@layer");
          if (layerName==null) layerName="";
          if (layerName.equals("physics")) layer.add(new Byte(PHYSICS_LAYER));
          else if (layerName.equals("biological")) layer.add(new Byte(BIOLOGICAL_LAYER));        
          else layer.add(new Byte(DEFINED_LAYER));
        }
      }
    
      typeFilter.setSelectedIndex(rememberType);
      topDepth.setSelectedIndex(rememberTopDepth);
      bottomDepth.setSelectedIndex(rememberBottomDepth);
      colourFilter.setSelectedIndex(rememberColour);

      if (typeFilter.getSelectedIndex() == -1) {
        typeFilter.setSelectedIndex(0);
        addOption.setEnabled(false);
        removeOption.setEnabled(false);
        availableOptions.setEnabled(false);
        sumOptions.setEnabled(false);
        mulOptions.setEnabled(false);
        chosenOptions.setEnabled(false);
        topDepth.setEnabled(false);
        bottomDepth.setEnabled(false);
        colourFilter.setEnabled(false);
        autoScale.setEnabled(false);
        invertY.setEnabled(true);
        plotLog.setEnabled(true);
        backColour.setEnabled(true);
        grads.setEnabled(false);
        minValue.setEnabled(false);
        maxValue.setEnabled(false);
        plotContours.setEnabled(false);
        noContours.setEnabled(false);
        fillContours.setEnabled(false);
        drawContourLines.setEnabled(false);
      }
      populateType();
      lock = false;
    }
  }

  private void populateType() {
    if (type != typeFilter.getSelectedIndex()) {
      lock = true;
      type = typeFilter.getSelectedIndex();
      typeFilter.setSelectedIndex(type);
      availableOptionsList.removeAllElements();
      ArrayList theType = (ArrayList) options.get(type);
      for (int i = 0; i < theType.size(); i++) availableOptionsList.addElement(theType.get(i));
      populateDepth(type);
      lock = false;
    }
  }

  private void populateDepth(int _type) {
    topDepth.removeAllItems();
    bottomDepth.removeAllItems();
    
    if (_type>0) {
      String query = "/dataformat/field[name='"+ typeFilter.getSelectedItem().toString()+ "']/dimensions/dim[2]";
      Node depthDim = dataFormats.selectSingleNode(query);
    
      double topLayer = Double.parseDouble(depthDim.valueOf("@start"));
      double bottomLayer = Double.parseDouble(depthDim.valueOf("@end"));
      double step = Double.parseDouble(depthDim.valueOf("@step"));
      byte layerType = ((Byte)layer.get(_type)).byteValue();
      String topString = (String) topDepth.getSelectedItem();
      String bottomString = (String) bottomDepth.getSelectedItem();
      
      if ((layerType == BIOLOGICAL_LAYER) || (layerType == PHYSICS_LAYER)) {
        topDepth.addItem(String.valueOf(0.0f));
        bottomDepth.addItem(String.valueOf(0.0f));
      }
  
      
      if (layerType == PHYSICS_LAYER) {
        float power = -2;
        for (int i = 0; i < 20; i++) {
          topDepth.addItem(String.valueOf(Math.pow(10, power)));
          bottomDepth.addItem(String.valueOf(Math.pow(10, power)));
          power = power + 0.1f;
        }
      }
      
      if ((layerType == BIOLOGICAL_LAYER) || (layerType == PHYSICS_LAYER)) {
        for (int i = 1; i < 500; i++) {
          topDepth.addItem(String.valueOf(i));
          bottomDepth.addItem(String.valueOf(i));
        }
      }
     
      if (layerType == DEFINED_LAYER) {
        double y = topLayer;
        while (y<=bottomLayer) {
          topDepth.addItem(String.valueOf(y));
          bottomDepth.addItem(String.valueOf(y));
          y = y + step;
        }
      }
      
      /* Filter depths that weren't plotted in the sim */
  
      if ((layerType == BIOLOGICAL_LAYER) || (layerType == PHYSICS_LAYER)) {
      
        Node format = dataFormats.selectSingleNode("/dataformat/field[name='" + typeFilter.getSelectedItem().toString() + "']");
        if (format!=null) {
          Node dim2 = format.selectSingleNode("dimensions/dim[2]");
          int tD,bD;
          if (!dataFormats.valueOf("/dataformat/version").equals(Plotter.V1_1)) {
            tD = Integer.parseInt(dim2.valueOf("@start"));
            bD = Integer.parseInt(dim2.valueOf("@end"));
          } else {
            final double dtop = Double.parseDouble(dim2.valueOf("@start"));
            final double dbottom = Double.parseDouble(dim2.valueOf("@end"));          
            tD = (int) Math.floor(dtop);
            bD = (int) Math.floor(dbottom);          
            if (format.valueOf("name").equals("Physical Environment")) {
              while (Plotter.physicalDepths[tD]<dtop) tD++;
              while (Plotter.physicalDepths[bD]<dbottom) bD++;
            }
          }
          
          for (int i = bottomDepth.getItemCount() - 1; i > bD; i--) {
            bottomDepth.removeItemAt(i);
            topDepth.removeItemAt(i);
          }
          for (int i = tD - 1; i >= 0; i--) {
            bottomDepth.removeItemAt(i);
            topDepth.removeItemAt(i);
          }
        }
  
        topDepth.setSelectedItem(topString);
        bottomDepth.setSelectedItem(bottomString);
      }
      if (topDepth.getSelectedIndex() == -1)
        topDepth.setSelectedIndex(0);
      if (bottomDepth.getSelectedIndex() <= 0)
        bottomDepth.setSelectedIndex(bottomDepth.getItemCount() - 1);
    }
    pack();
    paint(getGraphics());
  }

  private void updateButtons() {
    boolean otherOptions = (typeFilter.getSelectedIndex()>0);
    oldTypeFilter = typeFilter.getSelectedIndex();
    addOption.setEnabled(otherOptions && (availableOptions.getSelectedIndices().length>0));
    removeOption.setEnabled(otherOptions && (chosenOptions.getSelectedIndices().length>0));
    availableOptions.setEnabled(otherOptions);
    chosenOptions.setEnabled(otherOptions);
    sumOptions.setEnabled(otherOptions);
    mulOptions.setEnabled(otherOptions);
    topDepth.setEnabled(otherOptions);
    bottomDepth.setEnabled(otherOptions);
    colourFilter.setEnabled(otherOptions);    
    autoScale.setEnabled(otherOptions);
    backColour.setEnabled(!otherOptions);
    grads.setEnabled(otherOptions);
    minValue.setEnabled(otherOptions && (!autoScale.isSelected()));
    maxValue.setEnabled(otherOptions && (!autoScale.isSelected()));
    plotContours.setEnabled(otherOptions);
    noContours.setEnabled(otherOptions && plotContours.isSelected());
    fillContours.setEnabled(otherOptions && plotContours.isSelected());
    drawContourLines.setEnabled(otherOptions && plotContours.isSelected());
    if (otherOptions) populateType();
    colourPreview.repaint();
    okButton.setEnabled(chosenOptionsList.size()>0 || (typeFilter.getSelectedIndex()==0));
  }
  
  public BackgroundChooser(JFrame jd) {
    super(jd, "Choose Background", true);
    a4=(Analyser4) jd;
		initialize();
    setSize(400, 200);
    setResizable(false);
    getContentPane().setLayout(new BorderLayout());
    
    
    JPanel holder = new JPanel(new BorderLayout());
        
    /* Data selection */
    JPanel selectVar = new JPanel(new BorderLayout());
    selectVar.setBorder(new BevelBorder(BevelBorder.RAISED));    
    
    JPanel selectVar1 = new JPanel(new FlowLayout());
    selectVar1.add(typeFilter);
    selectVar1.add(backColour);    
    
    JPanel choicePanel = new JPanel(new BorderLayout());
    JScrollPane availableScroller = new JScrollPane(availableOptions);
    JScrollPane chosenScroller = new JScrollPane(chosenOptions);    
    choicePanel.add(availableScroller,BorderLayout.NORTH);
    JPanel choiceButtons = new JPanel(new FlowLayout());
    choiceButtons.add(addOption);
    choiceButtons.add(removeOption);
    choiceButtons.add(sumOptions);
    choiceButtons.add(mulOptions);
    sumOptions.setSelected(true);
    op=OP_ADD;
    addOption.setEnabled(false);
    removeOption.setEnabled(false);
    choicePanel.add(choiceButtons,BorderLayout.CENTER);
    choicePanel.add(chosenScroller,BorderLayout.SOUTH);
    
    
    
    backColour.setText("Background");
    backColour.setBackground(Color.black);
    backColour.setForeground(Color.white);
    JPanel selectVar2 = new JPanel(new FlowLayout());
    selectVar2.add(new JLabel("between"));
    selectVar2.add(topDepth);
    selectVar2.add(new JLabel("and"));
    selectVar2.add(bottomDepth);
    selectVar2.add(new JLabel("metres"));
    selectVar.add(selectVar1,BorderLayout.NORTH);
    selectVar.add(choicePanel,BorderLayout.CENTER);
    selectVar.add(selectVar2,BorderLayout.SOUTH);
    holder.add(selectVar,BorderLayout.NORTH);
    
    optionsGroup.add(sumOptions);
    optionsGroup.add(mulOptions);
    
    /* Scaling panel */
    
    JPanel scalePanel = new JPanel(new BorderLayout());
    scalePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
    JPanel scale1 = new JPanel(new FlowLayout());
    scale1.add(autoScale);
    scale1.add(invertY);
    scale1.add(plotLog);
    invertY.setSelected(true);
    JPanel scale2 = new JPanel(new FlowLayout());
    scale2.add(new JLabel("Min:"));
    scale2.add(minValue);
    scale2.add(new JLabel("Max:"));
    scale2.add(maxValue);
    scalePanel.add(scale1,BorderLayout.NORTH);
    scalePanel.add(scale2,BorderLayout.SOUTH);
    holder.add(scalePanel,BorderLayout.CENTER);
    
    /* Colour and Contours */
    
    JPanel colourPanel = new JPanel(new BorderLayout());
    JPanel colour1 = new JPanel(new FlowLayout());
    colour1.add(new JLabel("Palette:"));
    colour1.add(colourFilter);
    colour1.add(colourPreview);
    colour1.add(new JLabel("Graduations:"));
    colour1.add(grads);
    colourPanel.add(colour1,BorderLayout.NORTH);
    BackgroundColour.addColours(colourFilter);
    JPanel contourPanel = new JPanel(new FlowLayout());
    contourPanel.add(plotContours);
    contourPanel.add(new JLabel("Number of contours:"));
    contourPanel.add(noContours);
    JPanel contourOptions = new JPanel(new FlowLayout());
    contourOptions.add(fillContours);
    contourOptions.add(drawContourLines);
    
    colourPanel.add(contourPanel,BorderLayout.CENTER);
    colourPanel.add(contourOptions,BorderLayout.SOUTH);
        
    holder.add(colourPanel,BorderLayout.SOUTH);
    
        
    for (int i = 2; i < 256; i++) {
      grads.addItem(String.valueOf(i));
      noContours.addItem(String.valueOf(i));
    }
    noContours.setSelectedIndex(8);
    grads.setSelectedIndex(grads.getItemCount() - 1);
    colourFilter.setSelectedIndex(0);
    colourPreview.setPreferredSize(new Dimension(100, 20));
    colourPreview.setBorder(new EtchedBorder());
    
    autoScale.setSelected(true);
    minValue.setPreferredSize(new Dimension(120,25));
    maxValue.setPreferredSize(new Dimension(120,25));
    
    maxValue.setEnabled(false);
    minValue.setEnabled(false);
    grads.addActionListener(eh);
    BackgroundColour.setType(BackgroundColour.AMMONIUM, 255);
    
    JPanel bottomButtons = new JPanel(new FlowLayout());
    bottomButtons.add(okButton);
    bottomButtons.add(cancelButton);
    
    getContentPane().add(holder,BorderLayout.CENTER);
    getContentPane().add(bottomButtons, BorderLayout.SOUTH);
    pack();
    typeFilter.addActionListener(eh);
    okButton.addActionListener(eh);
    cancelButton.addActionListener(eh);
    colourFilter.addActionListener(eh);
    autoScale.addActionListener(eh);
    backColour.addActionListener(eh);
    plotContours.addActionListener(eh);
    addOption.addActionListener(eh);
    removeOption.addActionListener(eh);
    availableOptions.addListSelectionListener(eh);
    chosenOptions.addListSelectionListener(eh); 
    sumOptions.addActionListener(eh);
    mulOptions.addActionListener(eh);
  }

  class EventHandler implements ActionListener, ListSelectionListener {
    public void actionPerformed(ActionEvent e) {
      if ((e.getSource() == typeFilter) && (!lock)) {
        
        if (typeFilter.getSelectedIndex()!=oldTypeFilter) {
          availableOptionsList.removeAllElements();
          chosenOptionsList.removeAllElements();
          theChoices.clear();
        }
        updateButtons();

      } else if (e.getSource() == okButton) {
        setVisible(false);
        if (typeFilter.getSelectedIndex()!=graphType) gotData = false;
        graphType = typeFilter.getSelectedIndex();
        topDepthVal = topDepth.getSelectedIndex();
        bottomDepthVal = bottomDepth.getSelectedIndex();
        if (!autoScale.isSelected()) {
          maxVal = Double.parseDouble(maxValue.getText());
          minVal = Double.parseDouble(minValue.getText());
        } 
        a4.theGraph.setContourLines(fillContours.isSelected(),drawContourLines.isSelected());
      } else if (e.getSource() == cancelButton) {
        setVisible(false);

      } else if (e.getSource() == grads) {
        BackgroundColour.setGraduations(grads.getSelectedIndex() + 2);
        BackgroundColour.setType(colourFilter.getSelectedIndex());
        colourPreview.repaint();
        
      } else if (e.getSource() == addOption) {
        int[] selections = availableOptions.getSelectedIndices();
        for (int i=0; i<selections.length; i++) {
          chosenOptionsList.addElement(availableOptionsList.elementAt(selections[i]));
          theChoices.add(new Integer(selections[i]));
        }
        updateButtons();
        
      } else if (e.getSource() == removeOption) {
        int[] selections = chosenOptions.getSelectedIndices();
        for (int i=selections.length-1; i>=0; i--) {
          chosenOptionsList.removeElementAt(selections[i]);
          theChoices.remove(i);
        }
        updateButtons();
        
      } else if (e.getSource() == sumOptions) {
        op = OP_ADD;
        
      } else if (e.getSource() == mulOptions) {
        op = OP_MUL;
      
      } else if (e.getSource() == colourFilter) {
        BackgroundColour.setType(colourFilter.getSelectedIndex());
        colourPreview.repaint();
        
      } else if (e.getSource() == autoScale) {
        minValue.setEnabled(!autoScale.isSelected());
        maxValue.setEnabled(!autoScale.isSelected());
        
      } else if (e.getSource() == backColour) {
        Color c = JColorChooser.showDialog((JButton) e.getSource(), new String("Choose Colour"), ((JButton) e.getSource()).getBackground());
        ((JButton) e.getSource()).setBackground(c);
        Color c2 = new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue());
        ((JButton)e.getSource()).setForeground(c2);
      
      } else if (e.getSource() == plotContours) {
        boolean b = plotContours.isSelected();
        drawContourLines.setEnabled(b);
        fillContours.setEnabled(b);
        noContours.setEnabled(b);
        
      
      }   
    }

    public void valueChanged(ListSelectionEvent e) {
      if (e.getValueIsAdjusting()) {
        if (e.getSource() == availableOptions) addOption.setEnabled(availableOptions.getSelectedIndices().length>0);
        else if (e.getSource() == chosenOptions) removeOption.setEnabled(chosenOptions.getSelectedIndices().length>0);
      }
    }
  }
}  

