  package VEW.Analyser4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileFilter;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import VEW.Common.DateDialog;
import VEW.Common.StringTools;
import VEW.Lifespan.LifespanDialog;

public class Analyser4 extends JFrame {
  public GraphPanel theGraph;
  private JPanel lineKeyPanel;
  private JPanel keyPanel;

  public JButton fromDate = new JButton();
  public JButton toDate = new JButton();
  private JButton setBackground = new JButton("Background");
  private JButton setLines = new JButton("Lines");
  public JTextField numFreq = new JTextField("1");
  public JComboBox numType = new JComboBox();
  public JButton goPlot = new JButton("Plot");
  private JButton exit = new JButton("Exit");
  private JButton lifeSpan = new JButton("Lifespan");
  private JButton export = new JButton("Export Graph");
  private JButton importDS = new JButton("Import Dataset");
  private JButton saveGraph = new JButton("Save Graph");
  private JButton loadGraph = new JButton("Load Graph");
  private JButton selectNone = new JButton("<html><font size='-2'>Tick None</font></html>");
  private JButton selectAll = new JButton("<html><font size='-2'>Tick All</font></html>");
  private EventHandler eh = new EventHandler(0, 0);
  private ExportDialog exportDialog;
  private DateDialog dd;
  public BackgroundChooser bc;
  public LineChooser2 lc2;
  private boolean StandAlone = false;
  private TickDialog td = new TickDialog();
  public final GregorianCalendar startOfSim = new GregorianCalendar(DateDialog.GMTTimeZone);
  public final GregorianCalendar endOfSim = new GregorianCalendar(DateDialog.GMTTimeZone);
  public final GregorianCalendar startOfPlot = new GregorianCalendar(DateDialog.GMTTimeZone);
  public final GregorianCalendar endOfPlot = new GregorianCalendar(DateDialog.GMTTimeZone);
  public float timeStepHours;
  public long timeStepMillis;    
  public long startSimMillis;
  public long endSimMillis;
  public String modelFilePath;
  public String formatFilePath;
  public Document modelFile;
  public Document formatFile;
  public Plotter thePlotter;
  public String dataPath = "";
  public ColourScale csc;
  private JPopupMenu leftAxisMenu;
  private JPopupMenu rightAxisMenu;
  private JPopupMenu topAxisMenu;
  private JPopupMenu bottomAxisMenu;

  private JMenuItem leftLabel = new JMenuItem("Label");
  private JMenuItem rightLabel = new JMenuItem("Label");
  private JMenuItem topLabel = new JMenuItem("Label");
  private JMenuItem bottomLabel = new JMenuItem("Label");
  private JMenuItem leftTicks = new JMenuItem("Tick marks");
  private JMenuItem rightTicks = new JMenuItem("Tick marks");
  private JMenuItem topTicks = new JMenuItem("Tick marks");
  private JMenuItem bottomTicks = new JMenuItem("Tick marks");

  private JMenu leftDataSource;
  private JMenu rightDataSource;
  private JMenu topDataSource;
  private JMenu bottomDataSource;
  private JMenu leftFormat;
  private JMenu rightFormat;
  private JMenu topFormat;
  private JMenu bottomFormat;
  private JMenu leftOrientation;
  private JMenu rightOrientation;
  private JMenu topOrientation;
  private JMenu bottomOrientation;

  public AxisDefinition leftAxisDef;
  public AxisDefinition rightAxisDef;
  public AxisDefinition topAxisDef;
  public AxisDefinition bottomAxisDef;
  public static DXMLFileFilter dxml = new DXMLFileFilter();

  private int lockEvents = 0;
  
  public final byte TIMESTEP = 0;
  public final byte HOUR = 1;
  public final byte DAY = 2;
  public final byte WEEK = 3;
  private GraphLoadSaveDialog glsd;
  public GraphPanel getGraphPanel() {
    return theGraph;
  }
  
  private void setPath(String path) {
    dataPath = path;
  }
  
  public String getPath() {
    return dataPath;
  }

  public void plotButtons(boolean b) {
    goPlot.setEnabled(b);
    lifeSpan.setEnabled(b);
    export.setEnabled(b);
    importDS.setEnabled(b);
    saveGraph.setEnabled(b);
    loadGraph.setEnabled(b);
    toDate.setEnabled(b);
    fromDate.setEnabled(b);
  }
  
  
  private Color getNonClash(Color c) {
    double r = c.getRed();
    double g = c.getGreen();
    double b = c.getBlue();
    if (((r+g+b)/3.0)>128) return Color.black;
    else return Color.white;
  }
  
  private void updateLineKey() {
    int itemCount = 0;
    while (itemCount<lineKeyPanel.getComponentCount()) {
      if (lineKeyPanel.getComponent(itemCount) instanceof JCheckBox) {
        JCheckBox jcb = (JCheckBox) lineKeyPanel.getComponent(itemCount);
        ActionListener[] als = jcb.getActionListeners();
        for (int i=0; i<als.length; i++) jcb.removeActionListener(als[i]);
        lineKeyPanel.remove(jcb);
      } else itemCount++;
    }
    lineKeyPanel.removeAll();
    lineKeyPanel.setLayout(new GridLayout(lc2.getLineCount(), 1));
    for (int i = 0; i < lc2.getLineCount(); i++) {
      JCheckBox jcb = new JCheckBox("<html><font size='-2'>"+lc2.getLineDef(i).name+"</font></html>");
      jcb.setPreferredSize(new Dimension(100,40));
      jcb.setBackground(lc2.getLineDef(i).colour);
      jcb.setForeground(getNonClash(lc2.getLineDef(i).colour));
      jcb.setSelected(lc2.getLineDef(i).visible);
      jcb.addActionListener(new EventHandler(EventHandler.CheckBox, i));
      lineKeyPanel.add(jcb);
    }
    lineKeyPanel.repaint();
    lineKeyPanel.paint(lineKeyPanel.getGraphics());
    keyPanel.repaint();
    keyPanel.paint(keyPanel.getGraphics());
    pack();
  }

  private void setFile(String modelFilename, String formatFilename) {
    try {
      SAXReader reader = new SAXReader();
      modelFilePath = dataPath + File.separator + modelFilename;
      if (!new File(modelFilePath).exists()) {
        java.util.jar.JarFile JF = new java.util.jar.JarFile(dataPath+File.separator+"VEW.jar");
        java.util.jar.JarEntry JE = JF.getJarEntry("Model.xml");
        FileOutputStream os = new FileOutputStream(new File(dataPath+File.separator+modelFilename));
        StringTools.copyStream(JF.getInputStream(JE),os);
      }
      modelFile = reader.read(new FileReader(modelFilePath));      
      formatFilePath = dataPath + File.separator + formatFilename;
      formatFile = reader.read(new FileReader(formatFilePath));

    } catch (Exception e) { e.printStackTrace(); }

    String version = (modelFile.valueOf("/model/version"));
    if (version.length()==0) version="3.0";
        
    if (version.equals("3.0")) {
      Node scTag = modelFile.selectSingleNode("/model/scenario/column");
      int StartYear = Integer.parseInt(scTag.valueOf("startyear"));
      int StartMonth = Integer.parseInt(scTag.valueOf("startmonth"));
      int StartDay = Integer.parseInt(scTag.valueOf("startday"));
      String StartTime = scTag.valueOf("starttime");
      int StartHour = Integer.parseInt(StartTime.substring(0, StartTime.indexOf(":")));
      int StartMinute = 0;
      if (StartTime.endsWith("30")) StartMinute = 30;
      startOfSim.set(GregorianCalendar.YEAR,StartYear);
      startOfSim.set(GregorianCalendar.MONTH,StartMonth);
      startOfSim.set(GregorianCalendar.DAY_OF_MONTH,StartDay+1);
      startOfSim.set(GregorianCalendar.HOUR_OF_DAY,StartHour);
      startOfSim.set(GregorianCalendar.MINUTE,StartMinute); 
      startOfSim.set(GregorianCalendar.MILLISECOND,0);
      startOfSim.set(GregorianCalendar.SECOND,0);
      int duration = Integer.parseInt(scTag.valueOf("duration"));
      int secPerStep = Integer.parseInt(scTag.valueOf("steplength"));
      endOfSim.setTimeInMillis(startOfSim.getTimeInMillis());
      endOfSim.add(GregorianCalendar.HOUR, (int) (duration * (secPerStep / 3600.0f)));
      timeStepHours = Float.parseFloat(modelFile.valueOf("/model/scenario/column/steplength")) / 3600.0f;
      
    } else if (version.equals("3.1")) {
      startOfSim.setTimeInMillis(Long.parseLong(modelFile.selectSingleNode("/model/track").valueOf("start")));
      endOfSim.setTimeInMillis(Long.parseLong(modelFile.selectSingleNode("/model/track").valueOf("end")));
      timeStepHours = Float.parseFloat(modelFile.valueOf("/model/track/secstep")) / 3600.0f;
    }
    startSimMillis = startOfSim.getTimeInMillis();
    endSimMillis = endOfSim.getTimeInMillis();
    
    timeStepMillis = (long) (3600*1000*timeStepHours);
    fromDate.setText("From: " + DateDialog.getString(startOfSim));
    toDate.setText("To: " + DateDialog.getString(endOfSim));
    
    numFreq.setText("1");
    numType.addItem("Timestep");
    numType.addItem("Hour");
    numType.addItem("Day");
    numType.addItem("Week");
    numType.setSelectedIndex(0);

    startOfPlot.setTimeInMillis(startOfSim.getTimeInMillis());
    endOfPlot.setTimeInMillis(endOfSim.getTimeInMillis());
    this.setTitle("Analysing - "+dataPath + File.separator + modelFilename);
  }

  public Analyser4(String path, String modelName, String fileName) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("VEW Analyser 4");
    theGraph = new GraphPanel(720, 720, this);
    thePlotter = new Plotter(this);
    setPath(path);
    setFile(modelName, fileName);
    glsd = new GraphLoadSaveDialog(this,dataPath,formatFilePath, formatFile);
    dd = new DateDialog(this,1800);
    getContentPane().setLayout(new BorderLayout(0, 0));
    getContentPane().add(theGraph, BorderLayout.WEST);
    JPanel topButtons = new JPanel(new GridLayout(5, 1));
    topButtons.add(fromDate);
    topButtons.add(toDate);
    JPanel backOrLine = new JPanel(new BorderLayout(0, 0));
    backOrLine.add(setBackground, BorderLayout.WEST);
    backOrLine.add(setLines, BorderLayout.CENTER);
    JPanel freqLine = new JPanel(new BorderLayout(5, 0));
    freqLine.add(new JLabel("Sample every "), BorderLayout.WEST);
    freqLine.add(numFreq, BorderLayout.CENTER);
    freqLine.add(numType, BorderLayout.EAST);
    topButtons.add(freqLine);
    topButtons.add(new JPanel());
    topButtons.add(backOrLine);
    topButtons.setBorder(new EtchedBorder());
    JPanel progressPanel = new JPanel(new BorderLayout(2, 2));
    JPanel moreOptions = new JPanel(new BorderLayout(2, 2));
    moreOptions.add(lifeSpan, BorderLayout.WEST);
    moreOptions.add(importDS, BorderLayout.EAST);
    JPanel saveLoad = new JPanel(new BorderLayout(2, 2));
    saveLoad.add(loadGraph, BorderLayout.WEST);
    saveLoad.add(saveGraph, BorderLayout.CENTER);
    moreOptions.add(saveLoad, BorderLayout.NORTH);
    progressPanel.add(moreOptions, BorderLayout.NORTH);
    progressPanel.add(goPlot, BorderLayout.WEST);
    progressPanel.add(export, BorderLayout.CENTER);
    progressPanel.add(exit, BorderLayout.EAST);
    progressPanel.setBorder(new EtchedBorder());
    JPanel rightPanel = new JPanel(new BorderLayout(2, 2));
    rightPanel.add(progressPanel, BorderLayout.SOUTH);
    rightPanel.add(topButtons, BorderLayout.NORTH);
    keyPanel = new JPanel(new BorderLayout(2, 2));
    rightPanel.setPreferredSize(new Dimension(260, 600));
    bc = new BackgroundChooser(this);
    theGraph.setBackgroundChooser(bc);
    lc2 = new LineChooser2(this, formatFile, modelFile, theGraph, startOfPlot, endOfPlot, timeStepHours);
    exportDialog = new ExportDialog(this, lc2, theGraph, bc);
    csc = new ColourScale(ColourScale.VERTICAL, 250, 200, bc);
    keyPanel.add(csc, BorderLayout.SOUTH);
    JPanel keySelections = new JPanel(new BorderLayout());
    lineKeyPanel = new JPanel(new GridLayout(1, 1));
    keySelections.add(lineKeyPanel,BorderLayout.NORTH);
    JPanel keyButtons = new JPanel(new FlowLayout());
    keyButtons.add(selectAll);
    keyButtons.add(selectNone);
    selectAll.addActionListener(eh);
    selectNone.addActionListener(eh);
    keySelections.add(keyButtons,BorderLayout.SOUTH);
    keyPanel.add(keySelections, BorderLayout.NORTH);
    rightPanel.add(new JScrollPane(keyPanel), BorderLayout.CENTER);
    getContentPane().add(rightPanel, BorderLayout.EAST);
    pack();
    fromDate.addActionListener(eh);
    toDate.addActionListener(eh);
    setBackground.addActionListener(eh);
    setLines.addActionListener(eh);
    goPlot.addActionListener(eh);
    exit.addActionListener(eh);
    lifeSpan.addActionListener(eh);
    numFreq.addActionListener(eh);
    numType.addActionListener(eh);
    export.addActionListener(eh);
    importDS.addActionListener(eh);
    saveGraph.addActionListener(eh);
    loadGraph.addActionListener(eh);
    createAxisMenus();
    getContentPane().addMouseListener(eh);

  }

  /* Function used for the four axes */

  public void initOrientation(JMenu x, int axis) {
    addSubMenuItem(x, "Upwards", axis, AxisDefinition.TEXT_270);
    addSubMenuItem(x, "Up/Right", axis, AxisDefinition.TEXT_315);
    addSubMenuItem(x, "Horizontal", axis, AxisDefinition.TEXT_0);
    addSubMenuItem(x, "Down/Right", axis, AxisDefinition.TEXT_45);
    addSubMenuItem(x, "Downwards", axis, AxisDefinition.TEXT_90);
  }

  public void createAxisMenus() {
    leftAxisMenu = new JPopupMenu();
    leftAxisMenu.add(new String("Left Axis"));
    leftAxisMenu.addSeparator();
    leftDataSource = new JMenu("Data Source");
    leftAxisMenu.add(leftDataSource);
    leftFormat = new JMenu("Format");
    leftAxisMenu.add(leftFormat);
    leftOrientation = new JMenu("Orientation");
    leftAxisMenu.add(leftOrientation);
    leftAxisMenu.add(leftLabel);
    leftLabel.addActionListener(new EventHandler(EventHandler.LABEL,EventHandler.LEFT_AXIS));
    leftAxisMenu.add(leftTicks);
    leftTicks.addActionListener(new EventHandler(EventHandler.TICKS,EventHandler.LEFT_AXIS));
    initOrientation(leftOrientation, EventHandler.LEFT_AXIS_ORIENTATION);

    rightAxisMenu = new JPopupMenu();
    rightAxisMenu.add(new String("Right Axis"));
    rightAxisMenu.addSeparator();
    rightDataSource = new JMenu("Data Source");
    rightAxisMenu.add(rightDataSource);
    rightFormat = new JMenu("Format");
    rightAxisMenu.add(rightFormat);
    rightOrientation = new JMenu("Orientation");
    rightAxisMenu.add(rightOrientation);
    rightAxisMenu.add(rightLabel);
    rightLabel.addActionListener(new EventHandler(EventHandler.LABEL,EventHandler.RIGHT_AXIS));
    rightAxisMenu.add(rightTicks);
    rightTicks.addActionListener(new EventHandler(EventHandler.TICKS,EventHandler.RIGHT_AXIS));
    initOrientation(rightOrientation, EventHandler.RIGHT_AXIS_ORIENTATION);

    topAxisMenu = new JPopupMenu();
    topAxisMenu.add(new String("Top Axis"));
    topAxisMenu.addSeparator();
    topDataSource = new JMenu("Data Source");
    topAxisMenu.add(topDataSource);
    topFormat = new JMenu("Format");
    topAxisMenu.add(topFormat);
    topOrientation = new JMenu("Orientation");
    topAxisMenu.add(topOrientation);
    topAxisMenu.add(topLabel);
    topLabel.addActionListener(new EventHandler(EventHandler.LABEL,EventHandler.TOP_AXIS));
    topAxisMenu.add(topTicks);
    topTicks.addActionListener(new EventHandler(EventHandler.TICKS,EventHandler.TOP_AXIS));
    initOrientation(topOrientation, EventHandler.TOP_AXIS_ORIENTATION);

    bottomAxisMenu = new JPopupMenu();
    bottomAxisMenu.add(new String("Bottom Axis"));
    bottomAxisMenu.addSeparator();
    bottomDataSource = new JMenu("Data Source");
    bottomAxisMenu.add(bottomDataSource);
    bottomFormat = new JMenu("Format");
    bottomAxisMenu.add(bottomFormat);
    bottomOrientation = new JMenu("Orientation");
    bottomAxisMenu.add(bottomOrientation);
    bottomAxisMenu.add(bottomLabel);
    bottomLabel.addActionListener(new EventHandler(EventHandler.LABEL,EventHandler.BOTTOM_AXIS));
    bottomAxisMenu.add(bottomTicks);
    bottomTicks.addActionListener(new EventHandler(EventHandler.TICKS,EventHandler.BOTTOM_AXIS));
    initOrientation(bottomOrientation, EventHandler.BOTTOM_AXIS_ORIENTATION);

    populateDataSourceChoices();
    defaultAxes();
  }

  public void populateDataSourceChoices() {
    leftDataSource.removeAll();
    rightDataSource.removeAll();
    topDataSource.removeAll();
    bottomDataSource.removeAll();
    JMenuItem noItemL = new JMenuItem("No Axis");
    noItemL.addActionListener(new EventHandler(EventHandler.LEFT_AXIS,EventHandler.NO_AXIS));
    leftDataSource.add(noItemL);
    JMenuItem noItemR = new JMenuItem("No Axis");
    noItemR.addActionListener(new EventHandler(EventHandler.RIGHT_AXIS,EventHandler.NO_AXIS));
    rightDataSource.add(noItemR);
    JMenuItem noItemT = new JMenuItem("No Axis");
    noItemT.addActionListener(new EventHandler(EventHandler.TOP_AXIS,EventHandler.NO_AXIS));
    topDataSource.add(noItemT);
    JMenuItem noItemB = new JMenuItem("No Axis");
    noItemB.addActionListener(new EventHandler(EventHandler.BOTTOM_AXIS,EventHandler.NO_AXIS));
    bottomDataSource.add(noItemB);

    if (!bc.usePlain()) {
      JMenuItem backItemL = new JMenuItem("The Background");
      backItemL.addActionListener(new EventHandler(EventHandler.LEFT_AXIS,EventHandler.BACKGROUND_AXIS));
      leftDataSource.add(backItemL);
      JMenuItem backItemR = new JMenuItem("The Background");
      backItemR.addActionListener(new EventHandler(EventHandler.RIGHT_AXIS,EventHandler.BACKGROUND_AXIS));
      rightDataSource.add(backItemR);
    }
    JMenuItem timeB = new JMenuItem("Time");
    timeB.addActionListener(new EventHandler(EventHandler.BOTTOM_AXIS,EventHandler.TIME_AXIS));
    bottomDataSource.add(timeB);
    JMenuItem timeT = new JMenuItem("Time");
    timeT.addActionListener(new EventHandler(EventHandler.TOP_AXIS,EventHandler.TIME_AXIS));
    topDataSource.add(timeT);

    for (int i = 0; i < lc2.getLineCount(); i++) {
      LineDefinition ldef = lc2.getLineDef(i);
      if (ldef.type == LineDefinition.XY_PLOT) {
        JMenuItem xChoiceB = new JMenuItem(ldef.name);
        xChoiceB.addActionListener(new EventHandler(EventHandler.BOTTOM_AXIS, i));
        bottomDataSource.add(xChoiceB);
        JMenuItem xChoiceT = new JMenuItem(ldef.name);
        xChoiceT.addActionListener(new EventHandler(EventHandler.TOP_AXIS, i));
        topDataSource.add(xChoiceT);
      }
      JMenuItem yChoiceL = new JMenuItem(ldef.name);
      yChoiceL.addActionListener(new EventHandler(EventHandler.LEFT_AXIS, i));
      leftDataSource.add(yChoiceL);
      JMenuItem yChoiceR = new JMenuItem(ldef.name);
      yChoiceR.addActionListener(new EventHandler(EventHandler.RIGHT_AXIS, i));
      rightDataSource.add(yChoiceR);
    }
  }

  public void addSubMenuItem(JMenu parent, String name, int code1, int code2) {
    JMenuItem jmi = new JMenuItem(name);
    jmi.addActionListener(new EventHandler(code1, code2));
    parent.add(jmi);
  }

  public void formatMenu(JPopupMenu parent, boolean time) {
    JMenu format = null;
    int axisCode = 0;
    if (parent == leftAxisMenu) {
      axisCode = EventHandler.LEFT_AXIS_FORMAT;
      format = leftFormat;
    } else if (parent == rightAxisMenu) {
      axisCode = EventHandler.RIGHT_AXIS_FORMAT;
      format = rightFormat;
    } else if (parent == topAxisMenu) {
      axisCode = EventHandler.TOP_AXIS_FORMAT;
      format = topFormat;
    } else if (parent == bottomAxisMenu) {
      axisCode = EventHandler.BOTTOM_AXIS_FORMAT;
      format = bottomFormat;
    }
    format.removeAll();
    if (time) {
      addSubMenuItem(format, "01 Jan", axisCode, AxisDefinition.DD_MMM);
      addSubMenuItem(format, "01 Jan 2006", axisCode, AxisDefinition.DD_MMM_YYYY);
      addSubMenuItem(format, "Jan 2006", axisCode, AxisDefinition.MMM_YYYY);
      addSubMenuItem(format, "Jan", axisCode, AxisDefinition.MMM);
      addSubMenuItem(format, "2006", axisCode, AxisDefinition.YYYY);
      addSubMenuItem(format, "01 (Day of month)", axisCode, AxisDefinition.DD);
      addSubMenuItem(format, "Timestep", axisCode, AxisDefinition.TIMESTEP);
      addSubMenuItem(format, "01 Jan 2004, 13:00", axisCode, AxisDefinition.DD_MMM_YYYY_HH_MM);
      addSubMenuItem(format, "01 Jan, 13:00", axisCode, AxisDefinition.DD_MMM_HH_MM);
      addSubMenuItem(format, "01, 13:00", axisCode, AxisDefinition.DD_HH_MM);
      addSubMenuItem(format, "13:00", axisCode, AxisDefinition.HH_MM);
    } else {
      addSubMenuItem(format, "No format", axisCode, AxisDefinition.F_NONE);
      addSubMenuItem(format, "X.0e0", axisCode, AxisDefinition.F_0_0E0);
      addSubMenuItem(format, "X.00e0", axisCode, AxisDefinition.F_0_00E0);
      addSubMenuItem(format, "X.000e0", axisCode, AxisDefinition.F_0_000E0);
      addSubMenuItem(format, "X", axisCode, AxisDefinition.F_0);
      addSubMenuItem(format, "X.0", axisCode, AxisDefinition.F_0_0);
      addSubMenuItem(format, "X.00", axisCode, AxisDefinition.F_0_00);
      addSubMenuItem(format, "X.000", axisCode, AxisDefinition.F_0_000);
      addSubMenuItem(format, "No format", axisCode, AxisDefinition.F_NONE);
    }
  }

  public void defaultAxes() {
    bottomAxisDef = new AxisDefinition();
    topAxisDef = new AxisDefinition();
    leftAxisDef = new AxisDefinition();
    rightAxisDef = new AxisDefinition();
    bottomAxisDef.name = "Bottom Axis";
    bottomAxisDef.axis_orientation = AxisDefinition.HORIZONTAL_AXIS;
    bottomAxisDef.text_placement = AxisDefinition.TEXT_BELOW;
    bottomAxisDef.text_rotation = AxisDefinition.TEXT_0;
    bottomAxisDef.size = theGraph.getGraphWidth();
    bottomAxisDef.xPos = 0;
    bottomAxisDef.yPos = theGraph.getGraphHeight() + 10;
    bottomAxisDef.colour = Color.black;
    bottomAxisDef.useTicks = true;
    bottomAxisDef.noTicks = 5;
    bottomAxisDef.type = 1;
    bottomAxisDef.format = AxisDefinition.F_NONE;
    bottomAxisDef.hps = timeStepHours;
    bottomAxisDef.visible = false;
    bottomAxisDef.sourceIndex = -1;
    leftAxisDef.name = "Left Axis";
    leftAxisDef.axis_orientation = AxisDefinition.VERTICAL_AXIS;
    leftAxisDef.text_placement = AxisDefinition.TEXT_LEFT;
    leftAxisDef.text_rotation = AxisDefinition.TEXT_0;
    leftAxisDef.size = theGraph.getGraphHeight();
    leftAxisDef.sourceIndex = -1;
    leftAxisDef.xPos = -10;
    leftAxisDef.yPos = 0;
    leftAxisDef.useTicks = true;
    leftAxisDef.noTicks = 5;
    leftAxisDef.colour = Color.black;
    leftAxisDef.type = 0;
    leftAxisDef.format = AxisDefinition.F_NONE;
    leftAxisDef.visible = false;
    rightAxisDef.name = "Right Axis";
    rightAxisDef.axis_orientation = AxisDefinition.VERTICAL_AXIS;
    rightAxisDef.text_placement = AxisDefinition.TEXT_RIGHT;
    rightAxisDef.text_rotation = AxisDefinition.TEXT_0;
    rightAxisDef.size = theGraph.getGraphHeight();
    rightAxisDef.xPos = theGraph.getGraphWidth() + 10;
    rightAxisDef.yPos = 0;
    rightAxisDef.useTicks = true;
    rightAxisDef.noTicks = 5;
    rightAxisDef.colour = Color.black;
    rightAxisDef.type = 0;
    rightAxisDef.format = AxisDefinition.F_NONE;
    rightAxisDef.visible = false;
    rightAxisDef.sourceIndex = -1;
    topAxisDef.name = "Top Axis";
    topAxisDef.axis_orientation = AxisDefinition.HORIZONTAL_AXIS;
    topAxisDef.text_placement = AxisDefinition.TEXT_ABOVE;
    topAxisDef.text_rotation = AxisDefinition.TEXT_0;
    topAxisDef.size = theGraph.getGraphWidth();
    topAxisDef.xPos = 0;
    topAxisDef.yPos = -10;
    topAxisDef.useTicks = true;
    topAxisDef.noTicks = 5;
    topAxisDef.colour = Color.black;
    topAxisDef.hps = timeStepHours;
    topAxisDef.type = 0;
    topAxisDef.format = AxisDefinition.F_NONE;
    topAxisDef.visible = false;
    topAxisDef.sourceIndex = -1;
  }

  public void setAxis(JPopupMenu jm, AxisDefinition ad, int source) {
    if (source == EventHandler.NO_AXIS) {
      ad.visible = false;
      ad.sourceIndex=-1;
      
    } else if (source == EventHandler.TIME_AXIS) {
      ad.visible = true;
      ad.t0 = new GregorianCalendar(DateDialog.GMTTimeZone);
      ad.t0.setTimeInMillis(startOfPlot.getTimeInMillis());
      ad.min = (float) (((startOfPlot.getTimeInMillis()) / 3600000.0) / timeStepHours);
      ad.max = (float) (((endOfPlot.getTimeInMillis()) / 3600000.0) / timeStepHours);
      ad.type = 1;
      ad.sourceIndex = source;
      formatMenu(jm, true);

    } else if (source == EventHandler.BACKGROUND_AXIS) {
      ad.visible = true;
      ad.max = (float) bc.getBottomDepth();
      ad.min = (float) bc.getTopDepth();
      ad.invert_axis = !bc.getInvert();
      ad.type = 2;
      ad.sourceIndex = source;
      formatMenu(jm, false);

    } else if ((source >= 0) && (source < lc2.getLineCount())) {
      ad.visible = true;
      LineDefinition ld = lc2.getLineDef(source);
      ad.t0 = new GregorianCalendar(DateDialog.GMTTimeZone);
      ad.t0.setTimeInMillis(startOfPlot.getTimeInMillis());
      if ((ld.type==LineDefinition.XY_PLOT) && ((ad==bottomAxisDef) || (ad==topAxisDef))) {
        ad.invert_axis = ld.invertX;
        ad.min = (float) ld.minX;
        ad.max = (float) ld.maxX;
      } else {
        ad.invert_axis = !ld.invertY;
        ad.min = (float) ld.min;
        ad.max = (float) ld.max;
      }
      ad.name = ld.name;
      ad.type = 2;
      ad.sourceIndex = source;
      formatMenu(jm, false);
    } else {
      ad.visible = false;
    }
  }

  public void resetAxes() {
    theGraph.resetAxes();
    theGraph.addAxis(bottomAxisDef);
    theGraph.addAxis(topAxisDef);
    theGraph.addAxis(rightAxisDef);
    theGraph.addAxis(leftAxisDef);
    theGraph.plotAxes();
    theGraph.paint(theGraph.getGraphics());
  }

  public void refreshAxes() {
    setAxis(leftAxisMenu, leftAxisDef, leftAxisDef.sourceIndex);
    setAxis(rightAxisMenu, rightAxisDef, rightAxisDef.sourceIndex);
    setAxis(topAxisMenu, topAxisDef, topAxisDef.sourceIndex);
    setAxis(bottomAxisMenu, bottomAxisDef, bottomAxisDef.sourceIndex);
    resetAxes();
  }

  /* End of axis stuff */

  public static void main(String[] args) {
    String path = "";
    if (args.length > 0) path = args[0].trim();
    if (path.equals("%1")) path="";
    if ((path==null) || (path.equals(""))) {
      JFileChooser jfc = new JFileChooser();
      
      jfc.setFileFilter(dxml);
      // must exist
      path = null;
      while ((path==null) || (!new File(path+File.separator+"DataFormats.xml").exists())) { 
        if (jfc.showOpenDialog(jfc) == JFileChooser.APPROVE_OPTION) {
          path = jfc.getCurrentDirectory().toString();
        } else System.exit(0);
      }
    }
    if ((path!=null) && (path.length()>0)) {
      Analyser4 A = new Analyser4(path, "Model.xml", "DataFormats.xml");
      A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      A.StandAlone = true;
      A.setVisible(true);
    } else {
      System.exit(0);
    }
  }
  
  
  class EventHandler implements ActionListener, MouseListener {
    int id, no;

    public static final int CheckBox = 1;
    public static final int NO_AXIS = -20;
    public static final int BACKGROUND_AXIS = -19;
    public static final int TIME_AXIS = -18;
    public static final int LEFT_AXIS = -5;
    public static final int RIGHT_AXIS = -4;
    public static final int TOP_AXIS = -3;
    public static final int BOTTOM_AXIS = -2;
    public static final int LEFT_AXIS_FORMAT = -9;
    public static final int RIGHT_AXIS_FORMAT = -8;
    public static final int TOP_AXIS_FORMAT = -7;
    public static final int BOTTOM_AXIS_FORMAT = -6;
    public static final int LEFT_AXIS_ORIENTATION = -13;
    public static final int RIGHT_AXIS_ORIENTATION = -12;
    public static final int TOP_AXIS_ORIENTATION = -11;
    public static final int BOTTOM_AXIS_ORIENTATION = -10;
    public static final int LABEL = -14;
    public static final int TICKS = -15;

    public EventHandler(int _id, int _no) {
      id = _id;
      no = _no;
    }

    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == fromDate) {
        dd.show(startOfSim, endOfPlot, startOfPlot);
        startOfPlot.setTimeInMillis(dd.getDate().getTimeInMillis());
        fromDate.setText("From: " + DateDialog.getString(startOfPlot));
        refreshAxes();         
        theGraph.replotGraph(lc2,startOfPlot,endOfPlot);
        theGraph.repaint();
      } else if (e.getSource() == toDate) {
        dd.show(startOfPlot, endOfSim, endOfPlot);
        endOfPlot.setTimeInMillis(dd.getDate().getTimeInMillis());
        toDate.setText("To: " + DateDialog.getString(endOfPlot));
        refreshAxes(); 
        theGraph.replotGraph(lc2,startOfPlot,endOfPlot);
        theGraph.repaint();
      } else if (e.getSource() == setBackground) {
        bc.show(formatFile);
        populateDataSourceChoices();        
        refreshAxes();
        theGraph.replotGraph(lc2, startOfPlot,endOfPlot);
        csc.repaint();
        theGraph.repaint();
                
      } else if (e.getSource() == setLines) {
        lc2.setVisible(true);
        populateDataSourceChoices();        
        updateLineKey();
        refreshAxes(); 
        theGraph.replotGraph(lc2,startOfPlot,endOfPlot);
        theGraph.repaint();
                
      } else if (e.getSource() == exit) {
        setVisible(false);
        if (StandAlone) System.exit(0);
      
      } else if (e.getSource() == selectNone) {
        lockEvents++;  
        for (int i=0; i<lineKeyPanel.getComponentCount(); i++) {
          if (lineKeyPanel.getComponent(i) instanceof JCheckBox) {
            JCheckBox jcb = (JCheckBox) lineKeyPanel.getComponent(i);
            lc2.getLineDef(i).visible=false;
            jcb.setSelected(false);
            jcb.paint(jcb.getGraphics());
          }
        }
        lockEvents--;
        theGraph.replotGraph(lc2,startOfPlot,endOfPlot);
        theGraph.repaint();

      
      } else if (e.getSource() == selectAll) {
        lockEvents++;  
        for (int i=0; i<lineKeyPanel.getComponentCount(); i++) {
          if (lineKeyPanel.getComponent(i) instanceof JCheckBox) {
            JCheckBox jcb = (JCheckBox) lineKeyPanel.getComponent(i);
            jcb.setSelected(true);
            lc2.getLineDef(i).visible=true;
          }
        }
        lockEvents--;
        theGraph.replotGraph(lc2,startOfPlot,endOfPlot);
        theGraph.repaint();

      } else if (e.getSource() == goPlot) {
        plotButtons(false);
        resetAxes();
                
        int i=1;
        try {
          i = Integer.parseInt(numFreq.getText());
        } catch (Exception ex) { i = 1; numFreq.setText("1");}
        if (numType.getSelectedIndex()==TIMESTEP) thePlotter.setFreq(i);
        else if (numType.getSelectedIndex()==HOUR) thePlotter.setFreq(i*(int)(Math.floor(1.0/timeStepHours)));        
        else if (numType.getSelectedIndex()==DAY) thePlotter.setFreq(i*(int)(Math.floor(24.0/timeStepHours)));        
        else if (numType.getSelectedIndex()==WEEK) thePlotter.setFreq(i*(int)(Math.floor(168.0/timeStepHours)));        
        thePlotter.plot(dataPath, theGraph, bc, lc2, startOfPlot.getTimeInMillis(), endOfPlot.getTimeInMillis(), formatFile, Analyser4.this, null);
        theGraph.replotGraph(lc2,startOfPlot,endOfPlot);
        refreshAxes();        
        
        csc.repaint();
        
      } else if (e.getSource()==lifeSpan) {
        LifespanDialog lsd = new LifespanDialog(Analyser4.this, new File(dataPath));
        lsd.setModal(true);
        lsd.setVisible(true);
        bc.populateCombos();
        // lc.initSources();
      
      } else if (e.getSource()==numType) {
        int i=1;
        try {
          i = Integer.parseInt(numFreq.getText());
        } catch (Exception ex) { i = 1; numFreq.setText("1"); }
        if (numType.getSelectedIndex()==TIMESTEP) thePlotter.setFreq(i);
        else if (numType.getSelectedIndex()==HOUR) thePlotter.setFreq(i*(int)(Math.floor(1.0/timeStepHours)));        
        else if (numType.getSelectedIndex()==DAY) thePlotter.setFreq(i*(int)(Math.floor(24.0/timeStepHours)));        
        else if (numType.getSelectedIndex()==WEEK) thePlotter.setFreq(i*(int)(Math.floor(168.0/timeStepHours)));

      } else if (e.getSource()==importDS) {
        DataImporter di = new DataImporter(Analyser4.this);
        di.showDialog(formatFile, modelFile, dataPath);
                
      } else if (e.getSource()==export) {
        exportDialog.setDimensions(theGraph.getWidth()-(2*theGraph.getAxisWidth()),theGraph.getGraphHeight());
        exportDialog.setPath(dataPath);
        exportDialog.setVisible(true);
        
      } else if (e.getSource()==loadGraph) {
        Node savedGraphTag = formatFile.selectSingleNode("/dataformat/savedgraphs");
        if (savedGraphTag==null) {
          JOptionPane.showMessageDialog(Analyser4.this, new String("No graphs have been saved in this dataset"));
        } else {
          List savedGraphs = savedGraphTag.selectNodes("graph");
          if (savedGraphs.size()==0) {
            JOptionPane.showMessageDialog(Analyser4.this, new String("No graphs have been saved in this dataset"));
          } else {
            glsd.showLoadDialog();
            populateDataSourceChoices();        
            updateLineKey();
            refreshAxes(); 
          }
        }
                      
      } else if (e.getSource()==saveGraph) {
        Element savedGraphTag = (Element) formatFile.selectSingleNode("/dataformat/savedgraphs");
        if (savedGraphTag==null) {
          savedGraphTag = formatFile.getRootElement().addElement("savedgraphs");
        }
        glsd.showSaveDialog("Type name for graph");
      } else if ((id==CheckBox) && (lockEvents==0)) {
        lc2.getLineDef(no).visible=((JCheckBox) e.getSource()).isSelected();
        theGraph.replotGraph(lc2,startOfPlot,endOfPlot);
        theGraph.repaint();
      } else if (id==LEFT_AXIS) {
        setAxis(leftAxisMenu,leftAxisDef,no);
        resetAxes();        
      } else if (id==RIGHT_AXIS) { 
        setAxis(rightAxisMenu,rightAxisDef,no);
        resetAxes();        
      } else if (id==TOP_AXIS) { 
        setAxis(topAxisMenu,topAxisDef,no);
        resetAxes();        
      } else if (id==BOTTOM_AXIS) { 
        setAxis(bottomAxisMenu,bottomAxisDef,no);
        resetAxes();
      } else if (id==LEFT_AXIS_FORMAT) {
        leftAxisDef.format=no;
        resetAxes();
      } else if (id==RIGHT_AXIS_FORMAT) {
        rightAxisDef.format=no;
        resetAxes();
      } else if (id==TOP_AXIS_FORMAT) {
        rightAxisDef.format=no;
        resetAxes();
      } else if (id==BOTTOM_AXIS_FORMAT) {
        bottomAxisDef.format=no;
        resetAxes();
      } else if (id==LEFT_AXIS_ORIENTATION) {
        leftAxisDef.text_rotation=(byte)no;
        resetAxes();        
      } else if (id==RIGHT_AXIS_ORIENTATION) {
        rightAxisDef.text_rotation=(byte)no;
        resetAxes();        
      } else if (id==TOP_AXIS_ORIENTATION) {
        topAxisDef.text_rotation=(byte)no;
        resetAxes();        
      } else if (id==BOTTOM_AXIS_ORIENTATION) { 
        bottomAxisDef.text_rotation=(byte)no;
        resetAxes();
      } else if (id==LABEL) {
        String s = "";
        if (no==TOP_AXIS) s = topAxisDef.title;
        else if (no==BOTTOM_AXIS) s = bottomAxisDef.title;
        else if (no==LEFT_AXIS) s = leftAxisDef.title;
        else if (no==RIGHT_AXIS) s = rightAxisDef.title;
        Object o = JOptionPane.showInputDialog(Analyser4.this,s);
        if (o!=null) s = o.toString();
        if (no==TOP_AXIS) topAxisDef.title=s;
        else if (no==BOTTOM_AXIS) bottomAxisDef.title=s;
        else if (no==LEFT_AXIS) leftAxisDef.title=s;
        else if (no==RIGHT_AXIS) rightAxisDef.title=s;
        resetAxes();
      } else if (id==TICKS) {
        if (no==TOP_AXIS) td.showTickDialog(topAxisDef);
        else if (no==BOTTOM_AXIS) td.showTickDialog(bottomAxisDef);
        else if (no==LEFT_AXIS) td.showTickDialog(leftAxisDef);
        else if (no==RIGHT_AXIS) td.showTickDialog(rightAxisDef);
        resetAxes();
      }
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) { showPopup(e); }
    public void mouseReleased(MouseEvent e) { showPopup(e); }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void showPopup(MouseEvent e) {
      if (e.isPopupTrigger()) {
        if (e.getX() < theGraph.getAxisWidth())
          leftAxisMenu.show(e.getComponent(), e.getX(), e.getY());
        else if (e.getY() > theGraph.getAxisHeight() + theGraph.getGraphHeight())
          bottomAxisMenu.show(e.getComponent(), e.getX(), e.getY());
        else if (e.getY() < theGraph.getAxisHeight())
          topAxisMenu.show(e.getComponent(), e.getX(), e.getY());
        else if (e.getX() > theGraph.getAxisWidth() + theGraph.getGraphWidth())
          rightAxisMenu.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }

  class TickDialog extends JDialog {
    JRadioButton fixTicks = new JRadioButton("Fixed No");
    JRadioButton regular = new JRadioButton("Regular Ticks");
    JTextField noTicks = new JTextField(5);
    JTextField regularFreq = new JTextField(5);
    JTextField sampleReg = new JTextField(5);
    JLabel freqLabel = new JLabel("Spacing");
    JLabel sampleLabel = new JLabel("Including");
    JLabel minMaxLabel = new JLabel();
    ButtonGroup choice = new ButtonGroup();
    JButton okButton = new JButton("Ok");
    JButton cancelButton = new JButton("Cancel");
    EventHandler teh = new EventHandler();
    AxisDefinition a;

    public void showTickDialog(AxisDefinition ad) {
      a = ad;
      if (ad.useTicks) {
        fixTicks.setSelected(true);
        noTicks.setEnabled(true);
        regularFreq.setEnabled(false);
        sampleReg.setEnabled(false);
      } else {
        regular.setSelected(true);
        noTicks.setEnabled(false);
        regularFreq.setEnabled(true);
        sampleReg.setEnabled(true);
      }
      noTicks.setText(String.valueOf(ad.noTicks));
      regularFreq.setText(String.valueOf(ad.interval));
      sampleReg.setText(String.valueOf(ad.firstTick));
      minMaxLabel.setText("Min: " + String.valueOf(ad.min) + ", Max: "
          + String.valueOf(ad.max));
      setVisible(true);
    }

    public TickDialog() {
      super(Analyser4.this, "Tick Marks", true);
      setResizable(false);
      choice.add(fixTicks);
      choice.add(regular);
      JPanel topPart = new JPanel(new FlowLayout());
      topPart.add(fixTicks);
      topPart.add(noTicks);
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(topPart, BorderLayout.NORTH);
      JPanel middlePart = new JPanel(new BorderLayout());
      middlePart.setPreferredSize(new Dimension(300, 100));
      JPanel topMiddle = new JPanel(new FlowLayout());
      topMiddle.add(regular);
      topMiddle.add(freqLabel);
      topMiddle.add(regularFreq);
      middlePart.add(topMiddle, BorderLayout.NORTH);
      JPanel middle = new JPanel(new FlowLayout());
      middle.add(sampleLabel);
      middle.add(sampleReg);
      middlePart.add(middle, BorderLayout.CENTER);
      JPanel labelFlow = new JPanel(new FlowLayout());
      labelFlow.add(minMaxLabel);
      middlePart.add(labelFlow, BorderLayout.SOUTH);
      getContentPane().add(middlePart, BorderLayout.CENTER);
      JPanel bottomPart = new JPanel(new FlowLayout());
      bottomPart.add(cancelButton);
      bottomPart.add(okButton);
      getContentPane().add(bottomPart, BorderLayout.SOUTH);
      okButton.addActionListener(teh);
      cancelButton.addActionListener(teh);
      fixTicks.addActionListener(teh);
      regular.addActionListener(teh);
      setSize(300, 150);
      setLocation(400, 400);
      pack();
    }

    class EventHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
          a.useTicks = fixTicks.isSelected();
          a.noTicks = Integer.parseInt(noTicks.getText());
          a.interval = Float.parseFloat(regularFreq.getText());
          a.firstTick = Float.parseFloat(sampleReg.getText());
          setVisible(false);
        } else if (e.getSource() == cancelButton) {
          setVisible(false);
        } else if (e.getSource() == fixTicks) {
          fixTicks.setSelected(true);
          noTicks.setEnabled(true);
          regularFreq.setEnabled(false);
          sampleReg.setEnabled(false);
        } else if (e.getSource() == regular) {
          regular.setSelected(true);
          noTicks.setEnabled(false);
          regularFreq.setEnabled(true);
          sampleReg.setEnabled(true);
        }
      }
    }
  }
}

class DXMLFileFilter extends FileFilter {
  public boolean accept(java.io.File arg0) {
    return ((arg0.getName().toUpperCase().endsWith("DATAFORMATS.XML")) || (arg0.isDirectory()));
  }

  public String getDescription() {
    return new String("DataFormats XML Description");
  }
}
