package VEW.Analyser4;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dom4j.Document;
import org.dom4j.Node;

import VEW.Common.DateDialog;
import VEW.Common.ParticleChooser.ParticleChooserDialog;

public class LineDialog extends JDialog {
  /* Data */
  
  private byte editing = 0;
  private String theName = "";
  private String[] sources;
  private String[] files;
  private byte[] layers;
  private boolean[] zips;
  private ArrayList[] vars;
  private GraphPanel gp = null;
  private final GregorianCalendar startPlot = new GregorianCalendar(DateDialog.GMTTimeZone);
  private final GregorianCalendar endPlot = new GregorianCalendar(DateDialog.GMTTimeZone);
  private final GregorianCalendar anyDate = new GregorianCalendar(DateDialog.GMTTimeZone);  
  private boolean physics;
  private int[] depthIndices;
  private byte multi; // Result for operator of choose depths/id. 
  private Color[] colours; // Colours for each depth/id
  private boolean randomColours;
  private long[] ids; // IDs for each, er, id. 
  private ParticleChooserDialog pcd;
  private LineDefinition ldef;
  private static final byte EDITING_SINGLE = 1;
  private static final byte EDITING_MULTIPLE = 2;  
  private static final byte NOT_EDITING = 0;  
  private Analyser4 a4=null;
  
  

  public static int lockEvents = 0;
  public LineDefinition getLineDef() { return ldef; }
  
  /* GUI */
  
  private JComboBox dataSource = new JComboBox();
  private JComboBox lineType = new JComboBox();
  private JPanel cardPanel = new JPanel(new CardLayout());
  private String nowShowing = TIME_SERIES;
  
  /* For Time Series */
  
  private JPanel timeSeriesPanel = new JPanel(new FlowLayout());
  private JComboBox TSvarName = new JComboBox();
  private JButton TSidOrDepths = new JButton("ID/Depth");
  private static final String TIME_SERIES = new String("Time Series");
  
  /* For the X-Y plot */
  
  private JPanel XYPlotPanel = new JPanel(new FlowLayout());
  private static final String XY_PLOT = new String("XY Plot"); 
  private DefaultListModel XYVar1ListModel = new DefaultListModel();
  private DefaultListModel XYVar2ListModel = new DefaultListModel();
  private DefaultListModel XYVar1ChosenModel = new DefaultListModel();
  private DefaultListModel XYVar2ChosenModel = new DefaultListModel();
  private JList XYVar1List = new JList(XYVar1ListModel);
  private JList XYVar2List = new JList(XYVar2ListModel);
  private JList XYVar1Chosen = new JList(XYVar1ChosenModel);
  private JList XYVar2Chosen = new JList(XYVar2ChosenModel);
  private JButton XYVar1Add = new JButton("Add");
  private JButton XYVar2Add = new JButton("Add");  
  private JButton XYVar1Remove = new JButton("Remove");
  private JButton XYVar2Remove = new JButton("Remove"); 
  private JCheckBox XYConnectPoints = new JCheckBox("Connect Points");
  private JCheckBox XYAutoScale = new JCheckBox("Auto-scale X-Data");
  private JCheckBox XYLogX = new JCheckBox("Log X-Data");  
  private JCheckBox XYInvertX = new JCheckBox("Invert X-Axis");  
  private JCheckBox XYAccX = new JCheckBox("Accumulate X-Data");  
  private JTextField XYMinX = new JTextField("0.0");
  private JTextField XYMaxX = new JTextField("0.0");
  private JLabel maxTimeX = new JLabel("Unknown");
  private JLabel minTimeX = new JLabel("Unknown");  
  private JButton XYFGID = new JButton("Choose IDs");
  
  private JRadioButton XYmulBits = new JRadioButton("Product");
  private JRadioButton XYaddBits = new JRadioButton("Sum");
  private ButtonGroup XYopGroup = new ButtonGroup();
  private ArrayList XYvars1 = new ArrayList();
  private ArrayList XYvars2 = new ArrayList();
  
  /* For the compound plot */
  
  private JPanel compoundPlotPanel = new JPanel(new FlowLayout());
  private DefaultListModel CPvarListModel = new DefaultListModel();
  private JList CPvarName = new JList(CPvarListModel);
  private DefaultListModel CPlistModel = new DefaultListModel();
  private JList CPvarList = new JList(CPlistModel);
  private JButton CPaddVarType = new JButton("Add");
  private JButton CPremoveVarType = new JButton("Remove");
  private JRadioButton CPmulBits = new JRadioButton("Product");
  private JRadioButton CPaddBits = new JRadioButton("Sum");
  private JRadioButton CPsepBits = new JRadioButton("Separate");
  private JButton CPdepthsIDs = new JButton("Choose Depths/IDs");
  private ButtonGroup CPopGroup = new ButtonGroup();
  private ArrayList CPvars = new ArrayList();
  private static final String COMPOUND_PLOT= new String("Compound Plot");  
    
  /* For the depth profile */
    
  private DefaultListModel DPvarListModel = new DefaultListModel();
  private JList DPvarName = new JList(DPvarListModel);
  private DefaultListModel DPlistModel = new DefaultListModel();
  private JList DPvarList = new JList(DPlistModel);
  private JButton DPaddVarType = new JButton("Add");
  private JButton DPremoveVarType = new JButton("Remove");
  private ButtonGroup DPopGroup = new ButtonGroup();  
  private ArrayList DPvars = new ArrayList();  
  private JRadioButton DPmulBits = new JRadioButton("Product");
  private JRadioButton DPaddBits = new JRadioButton("Sum");
  private JPanel depthProfilePanel = new JPanel(new BorderLayout());
  
  private JButton DPtimeStep = new JButton("TimeStep");
  private JComboBox DPtopDepth = new JComboBox();
  private JComboBox DPbottomDepth = new JComboBox();
  private JComboBox DPxPos = new JComboBox();
  private JCheckBox DPfill = new JCheckBox("Fill graph");
  private GregorianCalendar DPdate = new GregorianCalendar(DateDialog.GMTTimeZone);  
  private JCheckBox DPxTime = new JCheckBox("Use timestep for x-position");
  private static final String DEPTH_PROFILE = new String("Depth Profile");  
  
  private JCheckBox accumulate = new JCheckBox("Accumulate Y-Data");
  private JCheckBox log = new JCheckBox("Log Y-Data");
  private JCheckBox invert = new JCheckBox("Invert Y-Axis");
  private JCheckBox autoscale = new JCheckBox("Autoscale Y-Data");
  private JTextField max = new JTextField("0.0");
  private JTextField min = new JTextField("0.0");
  private JLabel maxTime = new JLabel("Unknown");
  private JLabel minTime = new JLabel("Unknown");  
  private JButton colour = new JButton("Colour");
  
  private JButton oK = new JButton("Ok");
  private JButton cancel = new JButton("Cancel");
  private double[] dx;
  private double[] dy;
  
  private boolean autoChanged;
  private boolean maxChanged;
  private boolean minChanged;
  private boolean colourChanged;
  private boolean logChanged;
  private boolean accChanged;
  private boolean invertChanged;
  private boolean autoXChanged;
  private boolean maxXChanged;
  private boolean minXChanged;
  private boolean logXChanged;
  private boolean accXChanged;
  private boolean invertXChanged;
  private boolean connectChanged;
  
  private long rememberID;
  
  public boolean didAutoChange() { return autoChanged; }
  public boolean didMaxChange() { return maxChanged; }  
  public boolean didMinChange() { return minChanged; }  
  public boolean didColourChange() { return colourChanged; }  
  public boolean didLogChange() { return logChanged; }  
  public boolean didAccChange() { return accChanged; }  
  public boolean didInvertChange() { return invertChanged; }
  public boolean didAutoXChange() { return autoXChanged; }
  public boolean didMaxXChange() { return maxXChanged; }  
  public boolean didMinXChange() { return minXChanged; }  
  public boolean didLogXChange() { return logXChanged; }  
  public boolean didAccXChange() { return accXChanged; }  
  public boolean didInvertXChange() { return invertXChanged; }
  public boolean didConnectChange() { return connectChanged; }
  
  private Document format;
  private Document model;
  
  public void setDefaults() {
  }
  
  public LineDefinition getNewLineDef() { return new LineDefinition(); }
  
  public void setColour(Color c) {
    colour.setBackground(c);
  }
  
  public void setDefinition(LineDefinition ld) {
    ldef = new LineDefinition();
    ldef.copyFrom(ld);
    theName = ldef.name;
    max.setText(String.valueOf(ld.max));
    min.setText(String.valueOf(ld.min));
    anyDate.setTimeInMillis(ld.timeMin);
    minTime.setText(DateDialog.getString(anyDate));
    anyDate.setTimeInMillis(ld.timeMax);
    maxTime.setText(DateDialog.getString(anyDate));    
    dx=ld.dataX;
    dy=ld.dataY;
    colour.setBackground(ld.colour);
    autoscale.setSelected(ld.autoscale);
    max.setEnabled(!ld.autoscale);
    min.setEnabled(!ld.autoscale);    
    log.setSelected(ld.log);
    accumulate.setSelected(ld.accumulate);
    invert.setSelected(ld.invertY);
    lineType.setSelectedIndex(ld.type);
    updateLineType();
    dataSource.setSelectedIndex(ld.scope);
    rememberID = ld.thisId;
    populateVarBoxes();
    populateDepthBoxes();
    
        
    if (ld.type==LineDefinition.TIME_SERIES) {
      if (ld.compoundVars1!=null) TSvarName.setSelectedIndex(ld.compoundVars1[0]);
      if (TSidOrDepths.isVisible()) {
        if (TSidOrDepths.getText().equals("Choose ID/s")) {
          ids = new long[ld.ids.length];
          for (int i=0; i<ld.ids.length; i++) ids[i]=ld.ids[i];
        } else if (TSidOrDepths.getText().equals("Choose Depth/s")) {
          depthIndices = new int[ld.depthIndices.length];
          for (int i=0; i<ld.depthIndices.length; i++) depthIndices[i]=ld.depthIndices[i];
        }
      } else {
        ids = null;
        depthIndices = null;
      }
            
            
    } else if (ld.type==LineDefinition.COMPOUND_PLOT) {
      CPmulBits.setSelected(ld.operator==LineDefinition.OP_MUL);
      CPaddBits.setSelected(ld.operator==LineDefinition.OP_ADD);
      CPsepBits.setSelected(ld.operator==LineDefinition.OP_IND);
      CPvars.clear();
      for (int i=0; i<ld.compoundVars1.length; i++)
        CPvars.add(new Integer(ld.compoundVars1[i]));
      populateChoiceLists();
      
    } else if (ld.type==LineDefinition.DEPTH_PROFILE) {
      DPmulBits.setSelected(ld.operator==LineDefinition.OP_MUL);
      DPvars.clear();
      for (int i=0; i<ld.compoundVars1.length; i++)
        DPvars.add(new Integer(ld.compoundVars1[i]));
      populateChoiceLists();
      DPtopDepth.setSelectedIndex(ld.topDepthIndex);
      DPbottomDepth.setSelectedIndex(ld.bottomDepthIndex);
      DPxPos.setSelectedIndex(ld.xpos);
      DPxTime.setSelected(ld.xauto);
      DPxPos.setEnabled(!DPxTime.isSelected());
      DPdate.setTimeInMillis(ld.timestep.getTimeInMillis());
      DPtimeStep.setText(DateDialog.getString(DPdate));
      DPfill.setSelected(ld.dpfill);
      if (ld.operator==LineDefinition.OP_ADD) DPaddBits.setSelected(true);
      else if (ld.operator==LineDefinition.OP_MUL) DPmulBits.setSelected(true);
      
    } else if (ld.type==LineDefinition.XY_PLOT) {
      XYmulBits.setSelected(ld.operator==LineDefinition.OP_MUL);
      XYvars1.clear();
      XYvars2.clear();
      XYConnectPoints.setSelected(ld.xyconnect);
      XYAutoScale.setSelected(ld.xauto);
      XYLogX.setSelected(ld.logx);
      XYAccX.setSelected(ld.accumulatex);
      XYInvertX.setSelected(ld.invertX);
      XYMinX.setText(String.valueOf(ld.minX));
      XYMaxX.setText(String.valueOf(ld.maxX));
      anyDate.setTimeInMillis(ld.timeMaxX);
      maxTimeX.setText(DateDialog.getString(anyDate));
      anyDate.setTimeInMillis(ld.timeMinX);
      minTimeX.setText(DateDialog.getString(anyDate));
      
      if (XYFGID.isVisible()) {
        ids = new long[ld.ids.length];
        for (int i=0; i<ld.ids.length; i++) ids[i]=ld.ids[i];
      } else ids = null;
     
      for (int i=0; i<ld.compoundVars1.length; i++)
        XYvars1.add(new Integer(ld.compoundVars1[i]));        
      for (int i=0; i<ld.compoundVars2.length; i++)
        XYvars2.add(new Integer(ld.compoundVars2[i]));        
    }
  }
  
 
  public void enableComponents(boolean b) {
    XYVar1List.setEnabled(b);
    XYVar2List.setEnabled(b);
    XYVar1Chosen.setEnabled(b);
    XYVar2Chosen.setEnabled(b);
    XYVar1Add.setEnabled(b);
    XYConnectPoints.setEnabled(b);
    XYMinX.setEnabled(b && !XYAutoScale.isSelected());
    XYMaxX.setEnabled(b && !XYAutoScale.isSelected());
    XYAutoScale.setEnabled(b);
    XYLogX.setEnabled(b);
    XYAccX.setEnabled(b);
    XYInvertX.setEnabled(b);
    XYVar2Add.setEnabled(b );
    XYVar1Remove.setEnabled(b);
    XYVar2Remove.setEnabled(b);
    XYmulBits.setEnabled(b);
    XYaddBits.setEnabled(b);
    CPvarName.setEnabled(b);
    CPvarList.setEnabled(b);
    CPaddVarType.setEnabled(b);
    CPdepthsIDs.setEnabled(b);
    CPremoveVarType.setEnabled(b);
    CPmulBits.setEnabled(b);
    CPaddBits.setEnabled(b);
    CPsepBits.setEnabled(b);
    DPfill.setEnabled(b);
    DPaddVarType.setEnabled(b);
    DPremoveVarType.setEnabled(b);
    DPmulBits.setEnabled(b);
    DPaddBits.setEnabled(b);
    DPvarName.setEnabled(b);
    DPtimeStep.setEnabled(b);
    DPtopDepth.setEnabled(b);
    DPbottomDepth.setEnabled(b);
    DPxPos.setEnabled(b && (!DPxTime.isSelected()));
    DPxTime.setEnabled(b);
    TSvarName.setEnabled(b);
  }
    
    
    
  
  public void prepareForEditSingle() {
    editing = EDITING_SINGLE;
    enableComponents(true);
    TSidOrDepths.setEnabled(false);
    dataSource.setEnabled(false);
    lineType.setEnabled(false);
    
  }
  
  public void prepareForEditMulti(double miny, double maxy,double minx,double maxx) {
    editing = EDITING_MULTIPLE;
    enableComponents(false);
    TSidOrDepths.setEnabled(false);
    dataSource.setEnabled(false);
    lineType.setEnabled(false);
    autoChanged=false;
    colourChanged=false;
    logChanged=false;
    accChanged=false;
    invertChanged=false;
    autoXChanged=false;
    XYMinX.setText(String.valueOf(minx));
    XYMaxX.setText(String.valueOf(maxx));
    min.setText(String.valueOf(miny));
    max.setText(String.valueOf(maxy));
    minTime.setText("");
    maxTime.setText("");
    minTimeX.setText("");
    maxTimeX.setText("");
    maxXChanged=false;
    maxChanged=false;
    minChanged=false;
    minXChanged=false;
    logXChanged=false;
    accXChanged=false;
    invertXChanged=false;
    connectChanged=false;
          
  }
  
  
  public void prepareForAdd() {
    editing = NOT_EDITING;
    enableComponents(true);
    TSidOrDepths.setEnabled(true);
    dataSource.setEnabled(true);
    lineType.setEnabled(true);
    CPlistModel.clear();
    CPvars.clear();
    DPvars.clear();
    DPlistModel.clear();
       
  }    
  
  public LineDialog(Document df, Document _model, JDialog parent, Analyser4 _a4, GraphPanel graph, GregorianCalendar start, GregorianCalendar end, float stepInHours) {
    super(parent,"Select Line",true);
    a4 = _a4;
    this.format = df;
    model = _model;
    pcd = new ParticleChooserDialog(this, a4,a4.dataPath, start, end, stepInHours);
    gp = graph;
    startPlot.setTimeInMillis(start.getTimeInMillis());
    endPlot.setTimeInMillis(end.getTimeInMillis());
    DPdate.setTimeInMillis(start.getTimeInMillis());
    lockEvents++;
    lineType.addItem("Time Series");
    lineType.addItem("Compound Time Series");
    lineType.addItem("Depth Profile");
    lineType.addItem("X-Y Plot");
    cardPanel.setBorder(new EtchedBorder());
    EventHandler eh = new EventHandler();    
        
    JPanel mainPanel = new JPanel(new BorderLayout());
      JPanel lineChoices = new JPanel(new BorderLayout());
        JPanel topLine = new JPanel(new FlowLayout());
        topLine.add(new JLabel("Line type:"));
        topLine.add(lineType);
        topLine.add(new JLabel("Data source:"));
        topLine.add(dataSource);
        lineChoices.add(topLine,BorderLayout.NORTH);
 
      /* Initialise Time Series Panel */
      
      timeSeriesPanel.add(TSvarName);
      timeSeriesPanel.add(TSidOrDepths);
      TSidOrDepths.addActionListener(eh);
      cardPanel.add(timeSeriesPanel,TIME_SERIES);
      
      /* Initialise Compound Panel */
      JPanel leftSide = new JPanel(new BorderLayout());
      JPanel rightSide = new JPanel(new BorderLayout());
      
      
      JScrollPane varScroller = new JScrollPane(CPvarName);
      varScroller.setPreferredSize(new Dimension(450,200));
      leftSide.add(varScroller,BorderLayout.NORTH);
            
      JScrollPane listScroller = new JScrollPane(CPvarList);
      listScroller.setPreferredSize(new Dimension(450,200));
      CPvarList.setBorder(new EtchedBorder());
      CPvarList.addListSelectionListener(eh);
      leftSide.add(listScroller,BorderLayout.SOUTH);
      
      compoundPlotPanel.add(leftSide);
      rightSide.add(CPaddVarType,BorderLayout.NORTH);
      JPanel operators = new JPanel(new FlowLayout());
      operators.add(CPmulBits);
      operators.add(CPaddBits);
      operators.add(CPsepBits);
      CPdepthsIDs.setVisible(false);
      CPopGroup.add(CPmulBits);
      CPopGroup.add(CPaddBits);
      CPopGroup.add(CPsepBits);
      CPaddBits.setSelected(true);
      CPmulBits.setSelected(false);
      CPsepBits.setSelected(false);
      CPremoveVarType.setEnabled(false);
      rightSide.add(operators,BorderLayout.CENTER);
      JPanel bottomTwoButtons = new JPanel(new BorderLayout());
      bottomTwoButtons.add(CPremoveVarType,BorderLayout.NORTH);
      bottomTwoButtons.add(CPdepthsIDs,BorderLayout.SOUTH);
      CPdepthsIDs.addActionListener(eh);
      rightSide.add(bottomTwoButtons,BorderLayout.SOUTH);
      compoundPlotPanel.add(rightSide);
      cardPanel.add(compoundPlotPanel,COMPOUND_PLOT);
      CPaddVarType.addActionListener(eh);
      CPremoveVarType.addActionListener(eh); 
    
      /* Initialise Depth Profile */
      
      JPanel middlePart = new JPanel(new FlowLayout());
      JPanel bottomMiddle = new JPanel(new FlowLayout());      
      JPanel bottomPart = new JPanel(new FlowLayout());
      JPanel dpCompound = new JPanel(new BorderLayout());

      JScrollPane varDpScroller = new JScrollPane(DPvarName);
      varDpScroller.setPreferredSize(new Dimension(350,200));
                  
      dpCompound.add(varDpScroller,BorderLayout.WEST);
      JScrollPane varDp2Scroller = new JScrollPane(DPvarList);
      varDp2Scroller.setPreferredSize(new Dimension(350,200));
      
      dpCompound.add(varDp2Scroller,BorderLayout.CENTER);
      JPanel dpCompoundOptions = new JPanel(new BorderLayout());
      dpCompoundOptions.add(DPaddVarType,BorderLayout.NORTH);
      dpCompoundOptions.add(DPremoveVarType,BorderLayout.SOUTH);
      JPanel dpOps = new JPanel(new FlowLayout());
      dpOps.add(DPmulBits);
      dpOps.add(DPaddBits);
      DPaddBits.setSelected(true);
      DPmulBits.setSelected(false);      
      dpCompoundOptions.add(dpOps,BorderLayout.CENTER);
      JPanel DPrightSide = new JPanel(new BorderLayout());
      DPrightSide.add(dpCompoundOptions,BorderLayout.NORTH);
      DPrightSide.add(DPtimeStep,BorderLayout.SOUTH);
      dpCompound.add(DPrightSide,BorderLayout.EAST);
            
      DPopGroup.add(DPmulBits);
      DPopGroup.add(DPaddBits);
      DPtimeStep.setText(DateDialog.getString(DPdate));
      middlePart.add(new JLabel("From"));
      middlePart.add(DPtopDepth);
      middlePart.add(new JLabel("to"));
      middlePart.add(DPbottomDepth);
      middlePart.add(new JLabel("metres"));
      
      bottomMiddle.add(DPfill);
      bottomPart.add(DPxTime);
      JPanel middleMain = new JPanel(new BorderLayout());
      middleMain.add(middlePart,BorderLayout.CENTER);
      middleMain.add(bottomMiddle,BorderLayout.SOUTH);
      DPxTime.setSelected(true);
      DPxPos.setEnabled(false);
      bottomPart.add(new JLabel("Place profile at x-position"));
      bottomPart.add(DPxPos);
      depthProfilePanel.add(dpCompound,BorderLayout.NORTH);
      depthProfilePanel.add(middleMain,BorderLayout.CENTER);
      depthProfilePanel.add(bottomPart,BorderLayout.SOUTH);
      cardPanel.add(depthProfilePanel,DEPTH_PROFILE);
      DPtopDepth.addActionListener(eh);
      DPbottomDepth.addActionListener(eh);
      DPxTime.addActionListener(eh);
      DPtimeStep.addActionListener(eh);
      DPaddVarType.addActionListener(eh);
      DPremoveVarType.addActionListener(eh); 
          
      int wid = gp.getGraphWidth();
      for (int i=0; i<wid; i++)
        DPxPos.addItem(String.valueOf(i));
      
      
      /* Initialise X-Y Plot */
      
      JPanel var1Panel = new JPanel(new BorderLayout());
      JPanel var2Panel = new JPanel(new BorderLayout());
      JScrollPane v1aScroller = new JScrollPane(XYVar1List);
      JScrollPane v1bScroller = new JScrollPane(XYVar1Chosen);      
      JScrollPane v2aScroller = new JScrollPane(XYVar2List);      
      JScrollPane v2bScroller = new JScrollPane(XYVar2Chosen);
      v1aScroller.setPreferredSize(new Dimension(350,200));
      v1bScroller.setPreferredSize(new Dimension(350,200));
      v2aScroller.setPreferredSize(new Dimension(350,200));
      v2bScroller.setPreferredSize(new Dimension(350,200));      
  
      
      
      var1Panel.add(new JLabel("X-Variable: "),BorderLayout.NORTH);
      var1Panel.add(v1aScroller,BorderLayout.CENTER);
      JPanel var1Buttons = new JPanel(new FlowLayout());
      var1Buttons.add(XYVar1Add);
      var1Buttons.add(XYVar1Remove);
      JPanel var1Bottom = new JPanel(new BorderLayout());
      var1Bottom.add(var1Buttons,BorderLayout.CENTER);
      var1Bottom.add(v1bScroller,BorderLayout.SOUTH);
      var1Panel.add(var1Bottom,BorderLayout.SOUTH);
      
      
      var2Panel.add(new JLabel("Y-Variable: "),BorderLayout.NORTH);
      var2Panel.add(v2aScroller,BorderLayout.CENTER);
      JPanel var2Buttons = new JPanel(new FlowLayout());
      var2Buttons.add(XYVar2Add);
      var2Buttons.add(XYVar2Remove);
      JPanel var2Bottom = new JPanel(new BorderLayout());
      var2Bottom.add(var2Buttons,BorderLayout.CENTER);
      var2Bottom.add(v2bScroller,BorderLayout.SOUTH);
      var2Panel.add(var2Bottom,BorderLayout.SOUTH);
      JPanel operatorPanel = new JPanel(new FlowLayout());
      operatorPanel.add(XYmulBits);
      operatorPanel.add(XYaddBits);
      JPanel upperPanel = new JPanel(new BorderLayout());
      upperPanel.add(operatorPanel,BorderLayout.NORTH);
      JPanel logInvAccPanel = new JPanel(new BorderLayout());
      logInvAccPanel.add(XYInvertX,BorderLayout.NORTH);
      logInvAccPanel.add(XYLogX,BorderLayout.CENTER);
      logInvAccPanel.add(XYAccX,BorderLayout.SOUTH);
      upperPanel.add(logInvAccPanel,BorderLayout.CENTER);
      
      JPanel lowPanel = new JPanel(new BorderLayout());
      lowPanel.add(upperPanel,BorderLayout.NORTH);
      lowPanel.add(XYConnectPoints,BorderLayout.CENTER);
      
      JPanel minxPanel1 = new JPanel(new FlowLayout());
      minxPanel1.add(new JLabel("Min"));
      minxPanel1.add(XYMinX);
      JPanel minxPanel2 = new JPanel(new FlowLayout());
      minxPanel2.add(new JLabel("at "));
      minxPanel2.add(minTimeX);
      JPanel minxPanel = new JPanel(new BorderLayout());
      minxPanel.add(minxPanel1,BorderLayout.NORTH);
      minxPanel.add(minxPanel2,BorderLayout.SOUTH);

      JPanel maxxPanel1 = new JPanel(new FlowLayout());
      maxxPanel1.add(new JLabel("Max"));
      maxxPanel1.add(XYMaxX);
      JPanel maxxPanel2 = new JPanel(new FlowLayout());
      maxxPanel2.add(new JLabel("at "));
      maxxPanel2.add(maxTimeX);
      JPanel maxxPanel = new JPanel(new BorderLayout());
      maxxPanel.add(maxxPanel1,BorderLayout.NORTH);
      maxxPanel.add(maxxPanel2,BorderLayout.SOUTH);
      
      XYMinX.setPreferredSize(new Dimension(160,20));
      XYMaxX.setPreferredSize(new Dimension(160,20));      
      JPanel lowerPanel = new JPanel(new BorderLayout());
      lowerPanel.add(minxPanel,BorderLayout.NORTH);
      lowerPanel.add(maxxPanel,BorderLayout.CENTER);
      JPanel lowestPanel = new JPanel(new BorderLayout());
      lowestPanel.add(XYFGID,BorderLayout.SOUTH);
      lowestPanel.add(XYAutoScale,BorderLayout.NORTH);
      lowestPanel.add(lowerPanel,BorderLayout.CENTER);
      lowPanel.add(lowestPanel,BorderLayout.SOUTH);
      
      JPanel middlePanel = new JPanel(new BorderLayout());
      middlePanel.add(lowPanel,BorderLayout.SOUTH);
      XYopGroup.add(XYmulBits);
      XYopGroup.add(XYaddBits);
      XYaddBits.setSelected(true);
      XYConnectPoints.addActionListener(eh);
      XYAutoScale.addActionListener(eh);
      XYMinX.addKeyListener(eh);
      XYMaxX.addKeyListener(eh);
      XYLogX.addActionListener(eh);
      XYAccX.addActionListener(eh);
      XYInvertX.addActionListener(eh);      
      XYAutoScale.setSelected(true);
      XYMinX.setEnabled(false);
      XYMaxX.setEnabled(false);
      XYFGID.addActionListener(eh);
      
      XYPlotPanel.add(var1Panel);
      XYPlotPanel.add(middlePanel);
      XYPlotPanel.add(var2Panel);
      cardPanel.add(XYPlotPanel,XY_PLOT);
      
      XYVar1Add.addActionListener(eh);
      XYVar2Add.addActionListener(eh);      
      XYVar1Remove.addActionListener(eh);      
      XYVar2Remove.addActionListener(eh);
      XYVar1List.addListSelectionListener(eh);
      XYVar2List.addListSelectionListener(eh);      
      XYVar1Chosen.addListSelectionListener(eh);      
      XYVar2Chosen.addListSelectionListener(eh);      
            
      /* Initialise error panel */
      
      lineChoices.add(cardPanel,BorderLayout.CENTER);

        JPanel simpleOptions = new JPanel(new FlowLayout());
          simpleOptions.add(invert);
          invert.addActionListener(eh);
          simpleOptions.add(log);
          log.addActionListener(eh);
          simpleOptions.add(accumulate);
          accumulate.addActionListener(eh);
          simpleOptions.add(colour);
        //JPanel scaleOptoins = new JPanel(new BorderLayout());
        JPanel scaleOptions = new JPanel(new BorderLayout());
        JPanel scaleOptions1 = new JPanel(new FlowLayout());        
        scaleOptions1.add(autoscale);  
          scaleOptions1.add(new JLabel("Min:"));
          scaleOptions1.add(min);
          scaleOptions1.add(new JLabel(" at "));
          scaleOptions1.add(minTime);
          min.addKeyListener(eh);
        JPanel scaleOptions2 = new JPanel(new FlowLayout());
          scaleOptions2.add(new JLabel("Max:"));
          scaleOptions2.add(max);
          scaleOptions2.add(new JLabel(" at "));
          scaleOptions2.add(maxTime);
          max.addKeyListener(eh);
          min.setPreferredSize(new Dimension(160,20));
          max.setPreferredSize(new Dimension(160,20));
        scaleOptions.add(scaleOptions1,BorderLayout.NORTH);
        scaleOptions.add(scaleOptions2,BorderLayout.SOUTH);
                    
       lineChoices.add(simpleOptions,BorderLayout.SOUTH); 
       mainPanel.add(lineChoices,BorderLayout.NORTH);
         JPanel buttonPanel = new JPanel(new FlowLayout());
         buttonPanel.add(cancel);
         buttonPanel.add(oK);
       mainPanel.add(scaleOptions,BorderLayout.CENTER);
       mainPanel.add(buttonPanel,BorderLayout.SOUTH);
     getContentPane().add(mainPanel,BorderLayout.CENTER);
     pack();
    
     oK.addActionListener(eh);
     cancel.addActionListener(eh);
     lineType.addActionListener(eh);
     dataSource.addActionListener(eh);
     autoscale.addActionListener(eh);
     colour.addActionListener(eh);
     max.setEnabled(false);
     min.setEnabled(false);
     colour.setEnabled(true);
     colour.setVisible(true);     
     autoscale.setSelected(true);
     initScopes();
     populateScopeBox();
     populateVarBoxes();
     populateChoiceLists();    
     pack();
     lockEvents--;
         
  }
  
  public void populateChoiceLists() {
    CPlistModel.clear();
    DPlistModel.clear();
    for (int i=0; i<CPvars.size(); i++) 
      CPlistModel.addElement(CPvarListModel.get(((Integer) CPvars.get(i)).intValue()));
    for (int i=0; i<DPvars.size(); i++) 
      DPlistModel.addElement(DPvarListModel.get(((Integer) DPvars.get(i)).intValue()));
  }
  
  public void showDialog() {
    setVisible(true);
  }
  
  private void populateDepthBoxes() {
    lockEvents++;
    int sourceNo = 0;
    if (dataSource.getSelectedItem()!=null) 
      while (!sources[sourceNo].equals(dataSource.getSelectedItem().toString())) sourceNo++;
    ArrayList depths = new ArrayList();
    depths.add(String.valueOf(0.0f));
    physics = false;
    if (layers[sourceNo]==LineDefinition.PHYSICAL) {
      physics = true;
      float power = -2;
      for (int i = 0; i < 20; i++) {
        depths.add(String.valueOf(Math.pow(10, power)));
        power += 0.1f;
      }
    }
    for (int i = 1; i < 500; i++)
      depths.add(String.valueOf(i));
    
    String td = "0";
    if (DPtopDepth.getSelectedItem()!=null) td = DPtopDepth.getSelectedItem().toString();
    String bd = "0";
    if (DPbottomDepth.getSelectedItem()!=null) bd = DPbottomDepth.getSelectedItem().toString();
    
    DPtopDepth.removeAllItems();
    DPbottomDepth.removeAllItems();
    for (int i=0; i<depths.size(); i++) {
      DPtopDepth.addItem(depths.get(i));
      DPbottomDepth.addItem(depths.get(i));
    }
    int i=0;
    while (i<depths.size()) {
      if (depths.get(i).toString().equals(td)) {
        DPtopDepth.setSelectedIndex(i);
        i=depths.size();
      }
      i++;
    }
    i=0;
    if ((td.equals("0")) && (bd.equals("0"))) {
      DPbottomDepth.setSelectedIndex(DPbottomDepth.getItemCount()-1);
      DPtopDepth.setSelectedIndex(0);
    } else {
      while (i<depths.size()) {
        if (depths.get(i).toString().equals(bd)) {
          DPbottomDepth.setSelectedIndex(i);
          i=depths.size();
        }
        i++;
      }
    }
    lockEvents--;
  }
    
    
  
  private void populateVarBoxes() {
    // Put available vars into... TSvarName, CPvarListModel, XYVar1ListModel, XYVar2ListModel, DPvarName
    lockEvents++;
    int sourceNo = 0;
    if (dataSource.getSelectedItem()!=null)
      while (!sources[sourceNo].equals(dataSource.getSelectedItem().toString())) sourceNo++;
    ArrayList v = vars[sourceNo];
    int layerType = layers[sourceNo];
    int theType = lineType.getSelectedIndex();
    if (theType==LineDefinition.TIME_SERIES) {
      if (layerType==LineDefinition.SINGLE) {
        TSidOrDepths.setVisible(false);
      } else if (layerType==LineDefinition.FUNCTIONALGROUP) {
        TSidOrDepths.setVisible(true);
        TSidOrDepths.setText("Choose ID/s");
      } else {
        TSidOrDepths.setVisible(true);
        TSidOrDepths.setText("Choose Depth/s");
      
      }
    } else if (theType==LineDefinition.DEPTH_PROFILE) {

    } else if (theType==LineDefinition.COMPOUND_PLOT) {
      CPsepBits.setEnabled(true);
      if ((layerType==LineDefinition.BIOLOGICAL) || (layerType==LineDefinition.PHYSICAL)) { 
        //CPsepBits.setEnabled(false);
        //if (CPsepBits.isSelected()) CPaddBits.setSelected(true);
        CPdepthsIDs.setText("Choose Depths");
        CPdepthsIDs.setVisible(true);
      } else if (layerType==LineDefinition.FUNCTIONALGROUP) {
        //CPsepBits.setEnabled(false);
        //if (CPsepBits.isSelected()) CPaddBits.setSelected(true);
        CPdepthsIDs.setText("Choose IDs");
        CPdepthsIDs.setVisible(true);
      } else {
        CPdepthsIDs.setVisible(false);
        
      }
    } else if (theType==LineDefinition.XY_PLOT) {
      if (layerType==LineDefinition.FUNCTIONALGROUP)
        XYFGID.setVisible(true);
      else 
    	XYFGID.setVisible(false);
    
    }
    
    int _tsvar = TSvarName.getSelectedIndex();
    int _cpvar = CPvarName.getSelectedIndex();    
    XYVar1ListModel.removeAllElements();
    XYVar2ListModel.removeAllElements();
    TSvarName.removeAllItems();
    CPvarListModel.removeAllElements();    
    DPvarListModel.removeAllElements();    
    for (int i=0; i<v.size(); i++) {
      TSvarName.addItem(v.get(i));
      CPvarListModel.addElement(v.get(i));
      XYVar1ListModel.addElement(v.get(i));
      XYVar2ListModel.addElement(v.get(i));      
      DPvarListModel.addElement(v.get(i));
    }
    if ((_tsvar>=0) && (_tsvar<v.size())) TSvarName.setSelectedIndex(_tsvar);    
    if ((_cpvar>=0) && (_cpvar<v.size())) CPvarName.setSelectedIndex(_cpvar);    
    
    lockEvents--;
  }
  
  private void populateScopeBox() {
    // Show available scopes.
    // TIME_SERIES: all scopes ok. (Single, Biological, Physical, FunctionalGroup)
    // COMPOUND: Single, Biological, Physical.
    // XY: Single at present. (Need Functional Group too, to plot uptake vs ambient conc eg)
    // DP: Biological/Physical. (Field data).
    
    lockEvents++;
    dataSource.removeAllItems();
    for (int i=0; i<sources.length; i++) {
      if (sources[i].length()>0) {
        boolean showMe = false;
        if (nowShowing.equals(TIME_SERIES)) {
          showMe = true;
        } else if (nowShowing.equals(COMPOUND_PLOT)) {
          if (layers[i]==LineDefinition.SINGLE) showMe = true;
          if ((layers[i]==LineDefinition.BIOLOGICAL) || (layers[i]==LineDefinition.PHYSICAL)) showMe = true;
          if (layers[i]==LineDefinition.FUNCTIONALGROUP) showMe=true;
        } else if (nowShowing.equals(XY_PLOT)) {
          if (layers[i]==LineDefinition.SINGLE) showMe = true;
          else if (layers[i]==LineDefinition.FUNCTIONALGROUP) showMe = true;
        } else if (nowShowing.equals(DEPTH_PROFILE)) {
          if ((layers[i]==LineDefinition.BIOLOGICAL) || (layers[i]==LineDefinition.PHYSICAL)) showMe = true;
        }
        if (showMe) dataSource.addItem(sources[i]);
      }
    }
    lockEvents--;
  }
  
  private void initScopes() {
    // This is purely for the initial data structures.
    // sources = list of names for environments, functionalgroups, fields.
    // layers = SINGLE/FUNCTIONAL GROUP/BIOLOGICAL/PHYSICAL for each source
    // files = file for each source
    // zips = zip status for each source
    // vars = array-list of variable names for each source.

    List environments = format.selectNodes("/dataformat/environment");
    List fields = format.selectNodes("/dataformat/field");
    List functionalgroups = format.selectNodes("/dataformat/functionalgroup");
    int size = environments.size() + fields.size() + functionalgroups.size();
    int sourceCount = 0;
    sources = new String[size];
    layers = new byte[size];
    files = new String[size];
    zips = new boolean[size];
    vars = new ArrayList[size];
    for (Iterator i = environments.iterator(); i.hasNext(); ) {
      Node env = (Node) i.next();
      List descriptions = env.selectNodes("var/desc");
      if (descriptions.size() > 0) {
        sources[sourceCount] = env.valueOf("name");
        layers[sourceCount] = LineDefinition.SINGLE;
        files[sourceCount] = env.valueOf("data");
        zips[sourceCount] = env.valueOf("zip").equals("true");
        vars[sourceCount] = new ArrayList(descriptions.size());
        for (Iterator j = descriptions.iterator(); j.hasNext();) {
          Node n = (Node) j.next();
          String varName = n.getStringValue();
          vars[sourceCount].add(varName);
        }
        sourceCount++;
      }
    }
    for (Iterator i = fields.iterator(); i.hasNext();) {
      Node field = (Node) i.next();
      List descriptions = field.selectNodes("var/desc");
      String layer = field.valueOf("dimensions/dim[2]/@layer");
      if ((descriptions.size()>0) && (layer.length()>0)) {
        sources[sourceCount] = field.valueOf("name");
        if (layer.equals("biological"))
          layers[sourceCount] = LineDefinition.BIOLOGICAL;        
        else if (layer.equals("physics"))
          layers[sourceCount] = LineDefinition.PHYSICAL;
        files[sourceCount] = field.valueOf("data");
        zips[sourceCount] = field.valueOf("zip").equals("true");
        vars[sourceCount] = new ArrayList(descriptions.size());
        for (Iterator j = descriptions.iterator(); j.hasNext();) {
          Node n = (Node) j.next();
          vars[sourceCount].add(n.getStringValue());
        }
        sourceCount++;
      } 
    }

    for (Iterator i = functionalgroups.iterator(); i.hasNext();) {
      Node fg = (Node) i.next();
      List descriptions = fg.selectNodes("var/desc"); // Only selects vars with descriptions
      if (descriptions.size() > 0) {
        sources[sourceCount] = fg.valueOf("name");
        layers[sourceCount] = LineDefinition.FUNCTIONALGROUP;
        files[sourceCount] = fg.valueOf("data");
        zips[sourceCount] = fg.valueOf("zip").equals("true");
        vars[sourceCount] = new ArrayList(descriptions.size());
        for (Iterator j = descriptions.iterator(); j.hasNext();) {
          Node n = (Node) j.next();
          String varName = n.getStringValue();
          if (varName.startsWith("ingested[")) {
            varName = varName.substring(9);
            varName = varName.substring(0,varName.indexOf("."));
            varName = varName+" gained (ingest/uptake)";
          } else if (varName.startsWith("pool[")) {
            varName = varName.substring(5);
            varName = varName.substring(0,varName.indexOf("."));
            varName = varName+" pool";
          } else if (varName.equals("State")) {
            varName = "Biological State (stage)";
          } else if (varName.equals("c")) {
            varName = "Sub-population size (c)";
          } else if (varName.equals("z")) {
            varName = "Depth (z)";
          } else {
            Node fgNode = model.selectSingleNode("/model/functionalgroup/variable[name='"+varName+"']");
            if (fgNode==null) fgNode = model.selectSingleNode("/model/functionalgroup/local[name='"+varName+"']"); 
            if (fgNode==null) fgNode = model.selectSingleNode("/model/functionalgroup/localvar[name='"+varName+"']");            
            if (fgNode==null) fgNode = model.selectSingleNode("/model/functionalgroup/exportvar[name='"+varName+"']");            
            if (fgNode==null) fgNode = model.selectSingleNode("/model/functionalgroup/varietyvariable[name='"+varName+"']");            
            if (fgNode==null) fgNode = model.selectSingleNode("/model/functionalgroup/varietylocal[name='"+varName+"']");            
            if (fgNode!=null) varName = fgNode.valueOf("desc")+" ("+varName+")";
            
          }            
          vars[sourceCount].add(varName);
        }
        sourceCount++;
      }
    }
    for (int i=sourceCount; i<sources.length; i++) sources[i]="";
    
  }
  public void chooseIDs() {
    String variety = dataSource.getSelectedItem().toString();
    pcd.setVariety(variety);
    pcd.setModal(true);
    pcd.setAnalyserUsage(true);
    pcd.setVisible(true);
    if (pcd.isAccepted()) {
      ids = pcd.getParticles();
      colours = pcd.getColours();
      randomColours = pcd.getRandomColours();
    }
  }
  
  public boolean handleIDs() {
    if (ids==null) {
      JOptionPane.showMessageDialog(LineDialog.this,"You need to choose IDs for the particles first.");
      return false;
    } else {
      ldef.ids = new long[ids.length];
      ldef.colours = new Color[ids.length];
      for (int i=0; i<ids.length; i++) {
        ldef.ids[i]=ids[i];
        if (randomColours) ldef.colours[i]=BackgroundColour.nextRandomColour();
        else ldef.colours[i]=colours[i];
      }
      return true;
    }
  }

  
  public void updateLineType() {
    CardLayout cl = (CardLayout)(cardPanel.getLayout());
    if (lineType.getSelectedIndex()==LineDefinition.TIME_SERIES) {
      cl.show(cardPanel,TIME_SERIES);
      nowShowing=TIME_SERIES;
    } else if (lineType.getSelectedIndex()==LineDefinition.COMPOUND_PLOT) {
      cl.show(cardPanel,COMPOUND_PLOT);
      nowShowing=COMPOUND_PLOT;          
    } else if (lineType.getSelectedIndex()==LineDefinition.DEPTH_PROFILE) {
      cl.show(cardPanel,DEPTH_PROFILE);
      nowShowing=DEPTH_PROFILE;          
    } else if (lineType.getSelectedIndex()==LineDefinition.XY_PLOT) {
      cl.show(cardPanel,XY_PLOT);
      nowShowing=XY_PLOT;          
    }
    populateScopeBox();
    populateVarBoxes();
    populateDepthBoxes();
    pack();
  }
  
  
  class EventHandler implements ActionListener, ListSelectionListener, KeyListener {
    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==cancel) {
        ldef = null;
        setVisible(false);
      
      } else if (e.getSource()==oK) {
        boolean nameNotDone=true;
        int result = JOptionPane.YES_OPTION;
        if (editing==NOT_EDITING) BackgroundColour.nextRandomColour();
        String changes = "";
        if (autoChanged) changes+="Auto-scale(y), ";
        if (maxChanged) changes+="Max(y), ";
        if (minChanged) changes+="Min(y), ";
        if (accChanged) changes+="Accumulate(y), ";
        if (logChanged) changes+="Log(y), ";        
        if (invertChanged) changes+="Invert(y), ";
        if (autoXChanged) changes+="Auto-scale(x), ";
        if (maxXChanged) changes+="Max(x), ";
        if (minXChanged) changes+="Min(x), ";
        if (accXChanged) changes+="Accumulate(x), ";
        if (logXChanged) changes+="Log(x), ";        
        if (invertXChanged) changes+="Invert(x), ";
        if (connectChanged) changes+="Connect points, ";
        
        if (colourChanged) changes+="Colour, ";        
        if (changes.equals("")) changes = "none, ";
        changes=changes.substring(0,changes.length()-2);
        if (editing==EDITING_MULTIPLE) result = JOptionPane.showConfirmDialog(LineDialog.this,"The lines you selected will have the following applied: "+changes+". Ok?", "Edit All?", JOptionPane.YES_NO_OPTION);
        if (result==JOptionPane.YES_OPTION) {
          boolean okToProceed = true;
          int sourceNo = 0;
          while (!sources[sourceNo].equals(dataSource.getSelectedItem().toString())) sourceNo++;
          ldef = new LineDefinition();
          String name = theName;
          ldef.dataX = dx;
          ldef.dataY = dy;
          ldef.thisId = rememberID;
          ldef.accumulate = accumulate.isSelected();
          ldef.autoscale = autoscale.isSelected();
          ldef.invertY = invert.isSelected();
          ldef.log = log.isSelected();
          ldef.scope = dataSource.getSelectedIndex();
          ldef.max = Double.parseDouble(max.getText());
          ldef.min = Double.parseDouble(min.getText());
          
          ldef.colour = colour.getBackground();
          ldef.file = files[sourceNo];
          ldef.zip = zips[sourceNo];
          ldef.layer = layers[sourceNo];
          if (format.valueOf("/dataformat/format").equals(""))
            ldef.useFloat=true;
          else ldef.useFloat = format.valueOf("/dataformat/format").equals("float");
        
          if (nowShowing.equals(TIME_SERIES)) {
            ldef.type = LineDefinition.TIME_SERIES;
            ldef.compoundVars1 = new int[1];
            ldef.compoundVars1[0] = TSvarName.getSelectedIndex();
            if (name.equals("")) name=TSvarName.getSelectedItem().toString()+" Time Series";
            if (TSidOrDepths.isVisible()) {
              if (TSidOrDepths.getText().equals("Choose ID/s")) {
                okToProceed=handleIDs();
                
              } else if (TSidOrDepths.getText().equals("Choose Depth/s")) {
                ldef.operator = multi;
                ldef.depthIndices = new int[depthIndices.length];
                ldef.colours = new Color[depthIndices.length];
                for (int i=0; i<depthIndices.length; i++) {
                  ldef.depthIndices[i]=depthIndices[i];
                  ldef.colours[i]=colours[i];
                }
              }
            } else {
              ldef.depthIndices=null;
              ldef.ids=null;
            }
          } else if (nowShowing.equals(COMPOUND_PLOT)) {
            ldef.type = LineDefinition.COMPOUND_PLOT;
            if (CPmulBits.isSelected()) ldef.operator = LineDefinition.OP_MUL;
            else if (CPaddBits.isSelected()) ldef.operator = LineDefinition.OP_ADD;
            else if (CPsepBits.isSelected()) {
              ldef.operator = LineDefinition.OP_IND;
              ldef.names = new String[CPvars.size()];
              ldef.colours = new Color[CPvars.size()];
              nameNotDone=false;
              for (int i=0; i<CPvars.size(); i++) {
                ldef.names[i]=CPlistModel.getElementAt(i).toString();
                ldef.colours[i]=BackgroundColour.nextRandomColour();
              }
            }
            ldef.compoundVars1 = new int[CPvars.size()];
            for (int i=0; i<CPvars.size(); i++)
              ldef.compoundVars1[i]=((Integer)CPvars.get(i)).intValue();
            
              
            if (CPdepthsIDs.isVisible()) {
              if (CPdepthsIDs.getText().equals("Choose Depths")) {
                ldef.CPdepthOp = multi;
                if (depthIndices==null) {
                  JOptionPane.showMessageDialog(LineDialog.this,"You need to specify depths for this field data first");
                  okToProceed=false;
                } else {
                  ldef.depthIndices = new int[depthIndices.length];
                  ldef.colours = new Color[depthIndices.length];
                  for (int i=0; i<depthIndices.length; i++) {
                    ldef.depthIndices[i]=depthIndices[i];
                    ldef.colours[i]=colours[i];
                  }
                }
              } else { // FG
                okToProceed=handleIDs();
              }
            } else ldef.depthIndices=null;
       
          } else if (nowShowing.equals(DEPTH_PROFILE)) {
            //if (name.equals("")) name=DPvarName.getSelectedItem().toString()+" Depth Profile";
            ldef.type = LineDefinition.DEPTH_PROFILE;
            ldef.compoundVars1 = new int[DPvars.size()];
            for (int i=0; i<DPvars.size(); i++)
              ldef.compoundVars1[i]=((Integer)DPvars.get(i)).intValue();
            ldef.topDepthIndex = DPtopDepth.getSelectedIndex();
            ldef.bottomDepthIndex = DPbottomDepth.getSelectedIndex();
            ldef.xpos = DPxPos.getSelectedIndex();
            ldef.xauto = DPxTime.isSelected();
            ldef.timestep = new GregorianCalendar(DateDialog.GMTTimeZone);
            ldef.timestep.setTimeInMillis(DPdate.getTimeInMillis());
            ldef.dpfill = DPfill.isSelected();
            if (DPmulBits.isSelected()) ldef.operator = LineDefinition.OP_MUL;
            else if (DPaddBits.isSelected()) ldef.operator = LineDefinition.OP_ADD;
            
          } else if (nowShowing.equals(XY_PLOT)) {
            ldef.type = LineDefinition.XY_PLOT;
            if (XYmulBits.isSelected()) ldef.operator = LineDefinition.OP_MUL;
            else if (XYaddBits.isSelected()) ldef.operator = LineDefinition.OP_ADD;
            ldef.compoundVars1 = new int[XYvars1.size()];
            for (int i=0; i<XYvars1.size(); i++)
              ldef.compoundVars1[i]=((Integer)XYvars1.get(i)).intValue();
            ldef.compoundVars2 = new int[XYvars2.size()];
            for (int i=0; i<XYvars2.size(); i++)
              ldef.compoundVars2[i]=((Integer)XYvars2.get(i)).intValue();
            ldef.xyconnect = XYConnectPoints.isSelected();
            ldef.xauto = XYAutoScale.isSelected();
            ldef.minX = Double.parseDouble(XYMinX.getText());
            ldef.maxX = Double.parseDouble(XYMaxX.getText()); 
            ldef.logx = XYLogX.isSelected();
            ldef.invertX = XYInvertX.isSelected();
            ldef.accumulatex = XYAccX.isSelected();
            if (ldef.layer==LineDefinition.FUNCTIONALGROUP) okToProceed=handleIDs();            
          }
          if (okToProceed) {
            if ((editing==NOT_EDITING) || (editing==EDITING_SINGLE)) {
              if (nameNotDone) {
                String newName = (String)JOptionPane.showInputDialog(LineDialog.this, "Type name for the line", "Choose Name", JOptionPane.PLAIN_MESSAGE, null, null, name); 
                if (newName!=null) {
                  ldef.name = newName;
                  setVisible(false);
                }
              } else {
                ldef.name="Compound";
                setVisible(false);
              }
            } else if (editing==EDITING_MULTIPLE) setVisible(false);
          }
        }
      
      } else if ((e.getSource()==lineType) && (lockEvents==0)) {
        updateLineType();
         
      } else if ((e.getSource()==dataSource) && (lockEvents==0)) {
        
        CPvars.clear();
        CPlistModel.clear();
        DPvars.clear();
        DPlistModel.clear();
        XYVar1ListModel.clear();
        XYVar2ListModel.clear();
        populateVarBoxes();
        populateDepthBoxes();
     
      } else if (e.getSource()==CPaddVarType) {
        int[] indices = CPvarName.getSelectedIndices();
        for (int i=0; i<indices.length; i++) {
          CPlistModel.addElement(CPvarName.getSelectedValues()[i]);
          CPvars.add(new Integer(indices[i]));
        }
        //oK.setEnabled(CPvars.size()>0);
        
      } else if (e.getSource()==CPremoveVarType) {
        int[] indices = CPvarList.getSelectedIndices();
        for (int i=indices.length-1; i>=0; i--) {
          CPvars.remove(indices[i]);
          CPlistModel.removeElementAt(indices[i]);
        }
        //oK.setEnabled(CPvars.size()>0);

      } else if (e.getSource()==DPaddVarType) {
        int[] indices =DPvarName.getSelectedIndices();
        for (int i=0; i<indices.length; i++) {
          DPlistModel.addElement(DPvarName.getSelectedValues()[i]);
          DPvars.add(new Integer(indices[i]));
        }
        
      } else if (e.getSource()==DPremoveVarType) {
        int[] indices = DPvarList.getSelectedIndices();
        for (int i=indices.length-1; i>=0; i--) {
          DPvars.remove(indices[i]);
          DPlistModel.removeElementAt(indices[i]);
        }
          
      } else if (e.getSource()==CPdepthsIDs) {
        if (CPdepthsIDs.getText().equals("Choose Depths")) {
          DepthChooser dc = new DepthChooser(LineDialog.this,physics);
          if (depthIndices!=null) dc.setContents(depthIndices);
          dc.setMulti(multi);
          dc.setVisible(true);
          depthIndices = dc.getIndices();
          colours = dc.getColours();
          multi = dc.getMulti();
        } else { // fg
          chooseIDs();
        }
     
      } else if ((lockEvents==0) && ((e.getSource()==DPbottomDepth) || (e.getSource()==DPtopDepth))) {
        if (DPtopDepth.getSelectedIndex()>DPbottomDepth.getSelectedIndex()) {
          int i = DPbottomDepth.getSelectedIndex();
          DPbottomDepth.setSelectedIndex(DPtopDepth.getSelectedIndex());
          DPtopDepth.setSelectedIndex(i);
        }
      }  else if (e.getSource()==DPxTime) {
        DPxPos.setEnabled(!DPxTime.isSelected());
      
      } else if (e.getSource()==DPtimeStep) {
        DateDialog dd = new DateDialog(LineDialog.this,1800);
        dd.show(startPlot,endPlot,DPdate);
        DPdate.setTimeInMillis(dd.getDate().getTimeInMillis());
        DPtimeStep.setText(DateDialog.getString(DPdate));        
      
      } else if (e.getSource()==TSidOrDepths) {
        if (TSidOrDepths.getText().equals("Choose ID/s")) chooseIDs();
        else if (TSidOrDepths.getText().equals("Choose Depth/s")) {
          DepthChooser dc = new DepthChooser(LineDialog.this,physics);
          if (depthIndices!=null) dc.setContents(depthIndices);
          dc.setMulti(multi);
          dc.setVisible(true);
          depthIndices = dc.getIndices();
          colours = dc.getColours();
          multi = dc.getMulti();
        }
      } else if (e.getSource()==autoscale) {
        max.setEnabled(!autoscale.isSelected());
        min.setEnabled(!autoscale.isSelected());
        autoChanged=true;
        maxChanged=true;
        minChanged=true;
        
      } else if (e.getSource()==XYAutoScale) {
        autoXChanged=true;
        maxXChanged=true;
        minXChanged=true;
        XYMinX.setEnabled(!XYAutoScale.isSelected());
        XYMaxX.setEnabled(!XYAutoScale.isSelected());
      
      } else if (e.getSource()==invert) invertChanged = true;
      else if (e.getSource()==accumulate) accChanged = true;
      else if (e.getSource()==log) logChanged = true;
      else if (e.getSource()==XYInvertX) invertXChanged = true;
      else if (e.getSource()==XYAccX) accXChanged = true;
      else if (e.getSource()==XYLogX) logXChanged = true;
      else if (e.getSource()==XYFGID) chooseIDs();
      else if (e.getSource()==XYConnectPoints) connectChanged = true;
      else if (e.getSource()==XYVar1Add) {
        int[] indices = XYVar1List.getSelectedIndices();
        for (int i=0; i<indices.length; i++) {
          XYVar1ChosenModel.addElement(XYVar1List.getSelectedValues()[i]);
          XYvars1.add(new Integer(indices[i]));
        }
      } else if (e.getSource()==XYVar1Remove) {
        int[] indices = XYVar1Chosen.getSelectedIndices();
        for (int i=indices.length-1; i>=0; i--) {
          XYvars1.remove(indices[i]);
          XYVar1ChosenModel.removeElementAt(indices[i]);
        }
      } else if (e.getSource()==XYVar2Add) {
        int[] indices = XYVar2List.getSelectedIndices();
        for (int i=0; i<indices.length; i++) {
          XYVar2ChosenModel.addElement(XYVar2List.getSelectedValues()[i]);
          XYvars2.add(new Integer(indices[i]));
        }
      } else if (e.getSource()==XYVar2Remove) {
        int[] indices = XYVar2Chosen.getSelectedIndices();
        for (int i=indices.length-1; i>=0; i--) {
          XYvars2.remove(indices[i]);
          XYVar2ChosenModel.removeElementAt(indices[i]);
        }
      } else if (e.getSource()==colour) {
        Color c = JColorChooser.showDialog(LineDialog.this, "Choose colour",colour.getBackground());
        if (c!=null) {
          colour.setBackground(c);
          colourChanged = true;
        }
      }
        
    }
    
    public void valueChanged(ListSelectionEvent e) {
      if (e.getSource()==CPvarList) {
        CPremoveVarType.setEnabled(CPvarList.getSelectedIndices().length>0);

      } else if (e.getSource()==CPvarName) {
        CPaddVarType.setEnabled(CPvarName.getSelectedIndices().length>0);        
      
      } else if (e.getSource()==XYVar1List) {
        XYVar1Add.setEnabled(XYVar1List.getSelectedIndices().length>0);
        
      } else if (e.getSource()==XYVar2List) {
        XYVar2Add.setEnabled(XYVar2List.getSelectedIndices().length>0);
        
      } else if (e.getSource()==XYVar1Chosen) {
        XYVar1Remove.setEnabled(XYVar1Chosen.getSelectedIndices().length>0);
        
      } else if (e.getSource()==XYVar2Chosen) {
        XYVar2Remove.setEnabled(XYVar2Chosen.getSelectedIndices().length>0);
      }
    }

    public void keyPressed(KeyEvent e) {
      if (e.getSource()==min) minChanged = true;
      else if (e.getSource()==max) maxChanged = true;
      else if (e.getSource()==XYMinX) minXChanged = true;
      else if (e.getSource()==XYMaxX) maxXChanged = true;
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
  }
  
  class DepthChooser extends JDialog {
    
    private DefaultListModel depthListModel = new DefaultListModel(); 
    private JList depths = new JList(depthListModel);
    private JButton ok = new JButton("Ok");
    private JButton cancelDepth = new JButton("Cancel");
    private JRadioButton useGradient = new JRadioButton("Use Gradient");
    private JRadioButton useRandom = new JRadioButton("Use Random Colours");
    private ButtonGroup colourRadio = new ButtonGroup();
    private ButtonGroup funcGroup = new ButtonGroup();
    private JRadioButton sumParts = new JRadioButton("Sum");
    private JRadioButton mulParts = new JRadioButton("Product");
    private JRadioButton individual = new JRadioButton("Plot Each Separately");
    private JComboBox colourChoice = new JComboBox();
    private PreviewPanel colourPreview = new PreviewPanel();
    private EventHandler eh = new EventHandler();
    private int[] indices;
    private Color[] depthColours;
    private byte depthMulti = 0;
    
    public byte getMulti() { return depthMulti; }
    public void setMulti(byte b) { 
      depthMulti = b;
      if (b==LineDefinition.OP_ADD) sumParts.setSelected(true);
      else if (b==LineDefinition.OP_MUL) mulParts.setSelected(true);
      else if (b==LineDefinition.OP_IND) individual.setSelected(true);
    }
    public int[] getIndices() { return indices; }
    public Color[] getColours() { return depthColours; }
    
    public void setContents(int[] _indices) {
      depths.setSelectedIndices(_indices);
    }
    
    public DepthChooser(JDialog parent, boolean _physics) {
      super(parent,"Choose Depth/s",true);
      BackgroundColour.addColours(colourChoice);
      depthListModel.addElement(String.valueOf(0.0f));
      if (_physics) {
        float power = -2;
        for (int i = 0; i < 20; i++) {
          depthListModel.addElement(String.valueOf(Math.pow(10, power)));
          power += 0.1f;
        }
      }
      for (int i = 1; i < 500; i++)
        depthListModel.addElement(String.valueOf(i));
      JScrollPane depthScroller = new JScrollPane(depths);
      depthScroller.setPreferredSize(new Dimension(150,200));
      getContentPane().setLayout(new BorderLayout());
      
      JPanel bottomPanel = new JPanel(new BorderLayout());
      
      JPanel whatToDo = new JPanel(new FlowLayout());
      whatToDo.add(individual);
      whatToDo.add(sumParts);
      whatToDo.add(mulParts);
      funcGroup.add(individual);
      funcGroup.add(sumParts);      
      funcGroup.add(mulParts);
      whatToDo.setBorder(new EtchedBorder());
      bottomPanel.add(whatToDo,BorderLayout.NORTH);
      individual.setSelected(true);
      
      JPanel colourChooser = new JPanel(new BorderLayout());
      JPanel colourPanel = new JPanel(new FlowLayout());
      colourPanel.add(useGradient);
      colourPanel.add(colourChoice);
      colourPanel.add(colourPreview);
      colourPreview.setPreferredSize(new Dimension(100, 20));
      colourPreview.setBorder(new EtchedBorder());
      colourRadio.add(useGradient);
      colourRadio.add(useRandom);
      useRandom.setSelected(true);
      colourChoice.setEnabled(false);
      
      colourChooser.add(useRandom,BorderLayout.NORTH);
      colourChooser.add(colourPanel,BorderLayout.CENTER);
      bottomPanel.add(colourChooser,BorderLayout.CENTER);
      colourChooser.setBorder(new EtchedBorder());
      
      JPanel buttonPanel = new JPanel(new FlowLayout());
      buttonPanel.add(cancelDepth);
      buttonPanel.add(ok);
      ok.setEnabled(false);
      colourChoice.addActionListener(eh);
      useGradient.addActionListener(eh);
      useRandom.addActionListener(eh);
      
      bottomPanel.add(buttonPanel,BorderLayout.SOUTH);
      
      getContentPane().add(bottomPanel,BorderLayout.SOUTH);
      getContentPane().add(depthScroller,BorderLayout.CENTER);
      pack();
      ok.addActionListener(eh);
      cancelDepth.addActionListener(eh);
      depths.addListSelectionListener(eh);
    }
    
    class EventHandler implements ActionListener, ListSelectionListener {
      public EventHandler() {}
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancelDepth) {
          indices = null;
          setVisible(false);
        
        } else if (e.getSource()==ok) {
          indices = depths.getSelectedIndices();
          if (individual.isSelected()) depthMulti=LineDefinition.OP_IND;
          if (sumParts.isSelected()) depthMulti=LineDefinition.OP_ADD;
          if (mulParts.isSelected()) depthMulti=LineDefinition.OP_MUL;          
          depthColours = new Color[indices.length];
          if (useRandom.isSelected()) {
            for (int i=0; i<indices.length; i++) {
              depthColours[i] = BackgroundColour.nextRandomColour();
            }
            
          } else {
            for (int i=0; i<indices.length; i++) {
              BackgroundColour.setGraduations(255);
              depthColours[i] = BackgroundColour.getColour(i,0,indices.length);
            }
          }
          setVisible(false);
        
        } else if (e.getSource()==colourChoice) {
          BackgroundColour.setType(colourChoice.getSelectedIndex());
          colourPreview.repaint();
        
        } else if (e.getSource()==useGradient) {
          colourChoice.setEnabled(true);
          
        } else if (e.getSource()==useRandom) {
          colourChoice.setEnabled(false);          
          
        }
          
          
      }
      public void valueChanged(ListSelectionEvent e) {
        if (e.getSource()==depths) {
          ok.setEnabled(depths.getSelectedIndices().length>0);
        }
      }
    }
  }
}        
