package VEW.Scenario2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import VEW.Analyser4.AxisDefinition;
import VEW.Analyser4.BackgroundColour;
import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.VEWUtilities;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.VEWController2;

public class ScenarioPanel2 extends JPanel {
  
  private static final int[] standardDepths = new int[] {0,10,20,30,50,75,100,125,150,200,250,300,400,500};
  private static final String IconRoot = "Data/Graphics/icons/";
  private XMLTag theModel;
  private int lockEvents=0;
  private DecimalFormat dfLL = new DecimalFormat("0.00");
  
  public JTextField setLatitude  = new JTextField("0", 5);
  public JTextField setLongitude = new JTextField("0", 5);
  private JToggleButton zoomInButton = new JToggleButton(new ImageIcon(IconRoot + "zoomin.gif"));
  private JToggleButton zoomOutButton = new JToggleButton(new ImageIcon(IconRoot + "zoomout.gif"));
  private JToggleButton crossHairButton = new JToggleButton(new ImageIcon(IconRoot+ "crosshair.gif"));
  private JComboBox mapSelector = new JComboBox(); 
  private JComboBox trackTypeSelector = new JComboBox();
  private JComboBox velocityFieldSelector = new JComboBox();
  private EventHandler eh = new EventHandler();
  private MapPanel oceanMap = null;     // The panel containing the map for reading the coordinates etc.
  private VEWController2 vc2;
  private GregorianCalendar startSim = new GregorianCalendar();
  private GregorianCalendar endSim = new GregorianCalendar();  
  private JButton startDate = new JButton("Start");
  private JButton endDate = new JButton("End");
  public JButton generateButton  = new JButton("Generate");
  private DateDialog dd;
  private JLabel latLonLabel = new JLabel("");
  private JTextField timeStepSize = new JTextField("1800",6);
  private DataViewerScale dvScale;
  private DepthScale dpScale;
  private JCheckBoxMenuItem backgroundScale = new JCheckBoxMenuItem("Show Background Scale");
  private JCheckBoxMenuItem depthScale = new JCheckBoxMenuItem("Show Depth Scale");
  private JCheckBoxMenuItem mapMask = new JCheckBoxMenuItem("Show Landmasses");
  private JMenuItem showGoogle = new JMenuItem("View in GoogleEarth");
  private final String googleEarthPath = new String("\"C:"+File.separator+"Program Files"+File.separator+"Google"+File.separator+"Google Earth"+File.separator+"googleearth.exe\"");
  
  
  public final static String BUNKER = new String("Bunker");
  public final static String ERA40_SYNOPTIC = new String("ERA40 Synoptic");
  public final static String ERA40_SINGLE = new String("ERA40 Single Year");
  public final static String ERA40_MEAN_YEAR = new String("ERA40 Mean Year");
  public final static String ERA40_MONTHLY_AVG = new String("ERA40 Monthly Average");
  public final static String ERA40_READ_FLUX = new String("Read");
  public final static String ERA40_CALCULATE_FLUX = new String("Calculate");
  
  public final static String B_CC = new String("Cloud Cover");
  public final static String B_EK = new String("Ekman");
  public final static String B_HF = new String("Heat Flux");
  public final static String B_WS = new String("Wind Speed");
  
  public final static String E_2T = new String("Air Temperature");
  public final static String E_OHL = new String("Oceanic Heat Loss");
  public final static String E_R = new String("Relative Humidity");
  public final static String E_SLHF = new String("Surface Latent Heat Flux");
  public final static String E_SSHF = new String("Surface Sensible Heat Flux");
  public final static String E_STR = new String("Surface Thermal Radiation");
  public final static String E_TCC = new String("Total Cloud Cover");
  public final static String E_WS = new String("Wind Speed");
  
  
  public final static String LEVITUS = new String("Levitus");
  public final static String L_D = new String("Density");
  public final static String L_MLD = new String("Mixed Layer Depth");
  public final static String L_N = new String("Nitrate");
  public final static String L_P = new String("Phosphate");
  public final static String L_S = new String("Salinity");
  public final static String L_Si = new String("Silicate");
  public final static String L_T = new String("Temperature");
  
  public final static String OCCAM = new String("OCCAM");
  
   
  private JComboBox datasetCombo = new JComboBox();
  private JComboBox ERA40Year = new JComboBox();
  private JRadioButton calculateFlux = new JRadioButton("Calculate");
  private JRadioButton readFlux = new JRadioButton("Use ERA40");
  private ButtonGroup fluxGroup = new ButtonGroup();
  private JRadioButtonMenuItem bathRadio = new JRadioButtonMenuItem("Bathymetry");
  
  private static String currentDataSource;
  private static String currentVariable;
  private static int currentLayer;
  private double[][][] currentData;
    
  // Data Viewer re-coded as a pop up menu
  
  private JPopupMenu dvMenu = new JPopupMenu(); 
  
  public void leavePage() {
    dvMenu.setVisible(false);
    dvScale.setVisible(false);
    dpScale.setVisible(false);
    depthScale.setSelected(false);
    backgroundScale.setSelected(false);
  }
  
  public boolean greenLight(boolean fix) {
    boolean ok = true;
    lockEvents++;
    StringBuffer errorString = new StringBuffer();
    if (theModel.getTag("track")==null) {
      if (fix) {
        GregorianCalendar gc = new GregorianCalendar(1958,GregorianCalendar.MARCH,1,6,0,0);
        gc.set(GregorianCalendar.MILLISECOND,0);
        XMLTag defaultTrackTag = new XMLTag("track");
        defaultTrackTag.addTag(new XMLTag("latitude","41.0"));
        defaultTrackTag.addTag(new XMLTag("longitude","27.0"));
        defaultTrackTag.addTag(new XMLTag("start",String.valueOf(gc.getTimeInMillis())));
        gc.add(GregorianCalendar.YEAR,1);
        defaultTrackTag.addTag(new XMLTag("end",String.valueOf(gc.getTimeInMillis())));
        defaultTrackTag.addTag(new XMLTag("trackmode",Generator.INT_FIX));
        defaultTrackTag.addTag(new XMLTag("velocityfield","0-20m"));
        defaultTrackTag.addTag(new XMLTag("secstep","1800"));        
        defaultTrackTag.addTag(new XMLTag("map",MapPanel.MAP_WORLD));        
        theModel.addTag(defaultTrackTag);
        errorString.append("Added default track\n");
      } else ok = false;
    }
    if (ok) {
      if (theModel.getTag("boundaryconditions")==null) {
        if (fix) {
          XMLTag bcTag = new XMLTag("boundaryconditions");
          bcTag.setValue("climatedata",BUNKER);
          bcTag.setValue("heatflux",ERA40_READ_FLUX);
          theModel.addTag(bcTag);
        } else {
          errorString.append("Boundary Conditions Created\n");
          ok=false;
        }
      }
    }
    if (fix) {
      if (errorString.length()>5) {
        MessageBox.showMessage(errorString.toString(),this);
        vc2.unsaved(true);
      }
      loadExistingData();
    }
    lockEvents--;
    return ok;
  }
  
  public void updateData() {
    XMLTag bcTag = theModel.getTag("boundaryconditions");
    while (bcTag.getTag("climatedata")!=null) bcTag.getTag("climatedata").removeFromParent();
    bcTag.addTag(new XMLTag("climatedata",datasetCombo.getSelectedItem().toString()));
    while (bcTag.getTag("heatflux")!=null) bcTag.getTag("heatflux").removeFromParent();    
    if (readFlux.isSelected()) bcTag.addTag(new XMLTag("heatflux",ERA40_READ_FLUX));
    else bcTag.addTag(new XMLTag("heatflux",ERA40_CALCULATE_FLUX));
    while (bcTag.getTag("year")!=null) bcTag.getTag("year").removeFromParent();
    bcTag.addTag(new XMLTag("year",ERA40Year.getSelectedItem().toString()));
  }
  
  public void loadExistingData() {
    lockEvents++;
    //currentDataSource=null;
    //currentVariable=null;
    StringTools.setStringItem(mapSelector,theModel.getValue("track/map"));
    oceanMap.changeRegion(mapSelector.getSelectedItem().toString());
    StringTools.setStringItem(velocityFieldSelector,theModel.getValue("track/velocityfield"));
    StringTools.setStringItem(trackTypeSelector,theModel.getValue("track/trackmode"));
    startSim.setTimeInMillis(Long.parseLong(theModel.getValue("track/start")));
    startDate.setText(DateDialog.getString(startSim));
    endSim.setTimeInMillis(Long.parseLong(theModel.getValue("track/end")));
    endDate.setText(DateDialog.getString(endSim));
    setLongitude.setText(theModel.getValue("track/longitude"));
    setLatitude.setText(theModel.getValue("track/latitude"));
    timeStepSize.setText(theModel.getValue("track/secstep"));
    oceanMap.addPoint(Double.parseDouble(setLongitude.getText()),Double.parseDouble(setLatitude.getText()));
    String climData = theModel.getValue("boundaryconditions/climatedata");
    String fluxType = theModel.getValue("boundaryconditions/heatflux");
    StringTools.setStringItem(datasetCombo,climData);
    if (fluxType.equals("Read")) readFlux.setSelected(true);
    else calculateFlux.setSelected(true);
    String year="1958";
    if (theModel.getTag("boundaryconditions/year")!=null) year = theModel.getValue("boundaryconditions/year");
    StringTools.setStringItem(ERA40Year,year);
    updateButtons();
    //updateBackground();
    lockEvents--;
  }
  
  private void addEventMenu(JMenu m, String s, String parent, ButtonGroup bg) {
    JRadioButtonMenuItem jc = new JRadioButtonMenuItem(s);
    jc.setName(parent);
    jc.addActionListener(eh);
    bg.add(jc);
    m.add(jc);
  }
  
  private void addE40(JMenu m, String p, ButtonGroup bg) {
    addEventMenu(m,E_2T,p, bg);
    addEventMenu(m,E_OHL,p, bg);
    addEventMenu(m,E_R,p, bg);
    addEventMenu(m,E_SLHF,p, bg);
    addEventMenu(m,E_SSHF,p, bg);
    addEventMenu(m,E_STR,p, bg);
    addEventMenu(m,E_TCC,p, bg);
    addEventMenu(m,E_WS,p, bg);
  }
  
  private void addBunker(JMenu m,String p, ButtonGroup bg) {
    addEventMenu(m,B_CC,p,bg);
    addEventMenu(m,B_EK,p,bg);
    addEventMenu(m,B_HF,p,bg);
    addEventMenu(m,B_WS,p,bg);
  }
  
  private void addLayerMenu(JMenu m, String s, String parent, ButtonGroup bg) {
    JMenu newMenu = new JMenu(s);
    for (int i=0; i<standardDepths.length; i++) {
      JRadioButtonMenuItem jc = new JRadioButtonMenuItem(String.valueOf(standardDepths[i]+" m"));
      jc.setName(parent+":"+s);
      jc.addActionListener(eh);
      bg.add(jc);
      newMenu.add(jc);  
    }
    m.add(newMenu);
  }
  
  private void addLevitus(JMenu m, String p, ButtonGroup bg) {
    addLayerMenu(m,L_D,p,bg);
    addEventMenu(m,L_MLD,p,bg);
    addLayerMenu(m,L_N,p,bg);
    addLayerMenu(m,L_P,p,bg);
    addLayerMenu(m,L_S,p,bg);
    addLayerMenu(m,L_Si,p,bg);
    addLayerMenu(m,L_T,p,bg);
  }
  
  private void addOccam(JMenu m, String p, ButtonGroup bg) {
    for (int i=0; i<Generator.velRanges.length; i++) {
      addEventMenu(m, "Velocity "+Generator.velRanges[i],p,bg); 
    }
  }
  
  private void initialiseDVMenu() {
    ButtonGroup bg = new ButtonGroup();
    dvMenu.add(new String("Background Options"));
    dvMenu.addSeparator();
    bathRadio.setSelected(true);
    bg.add(bathRadio);
    bathRadio.addActionListener(eh);
    dvMenu.add(bathRadio);
    dvMenu.addSeparator();
    JMenu bunkerMenu = new JMenu(BUNKER);
    dvMenu.add(bunkerMenu);
    dvMenu.addSeparator();
    JMenu E40MYMenu= new JMenu(ERA40_MEAN_YEAR);
    dvMenu.add(E40MYMenu);
    JMenu E40MonMenu = new JMenu(ERA40_MONTHLY_AVG);
    dvMenu.add(E40MonMenu);
    JMenu E40SynMenu = new JMenu(ERA40_SYNOPTIC);
    dvMenu.add(E40SynMenu);
    dvMenu.addSeparator();
    JMenu LevMenu = new JMenu(LEVITUS);
    dvMenu.add(LevMenu);
    dvMenu.addSeparator();
    JMenu OccMenu = new JMenu(OCCAM);
    dvMenu.add(OccMenu);
    dvMenu.addSeparator();
    dvMenu.add(backgroundScale);
    dvMenu.add(depthScale);
    dvMenu.add(mapMask);
    dvMenu.add(showGoogle);
    addE40(E40MYMenu,ERA40_MEAN_YEAR,bg);
    addE40(E40MonMenu,ERA40_MONTHLY_AVG,bg);
    addE40(E40SynMenu,ERA40_SYNOPTIC,bg);
    addLevitus(LevMenu,LEVITUS,bg);
    addOccam(OccMenu,OCCAM,bg);
    addBunker(bunkerMenu,BUNKER,bg);
  }
  
  public void updateButtons() {
    velocityFieldSelector.setEnabled(!trackTypeSelector.getSelectedItem().toString().equals(Generator.INT_FIX));
    boolean flux = !(datasetCombo.getSelectedItem().toString().equals(BUNKER));
    readFlux.setEnabled(flux);
    calculateFlux.setEnabled(flux);
    ERA40Year.setEnabled(datasetCombo.getSelectedItem().toString().equals(ERA40_SINGLE));
  }
  
  
  public ScenarioPanel2(JFrame vewController2, XMLTag _ModelFile) {
    vc2 = (VEWController2) vewController2;
    dd = new DateDialog(vc2,1800);
    dvScale = new DataViewerScale(vc2);
    dpScale = new DepthScale(vc2);
    initialiseDVMenu();
    theModel = _ModelFile;
    setLayout(new BorderLayout());
    JPanel mapPanel = new JPanel(new BorderLayout());
    oceanMap = new MapPanel(this);
    mapPanel.add(oceanMap,BorderLayout.CENTER);
    JPanel mapTools = new JPanel(new FlowLayout(FlowLayout.LEFT));
      mapTools.add(new JLabel("Region:"));
      mapTools.add(mapSelector);
      mapTools.add(new JLabel("Mode:"));
      mapTools.add(crossHairButton);
      mapTools.add(zoomInButton);
      mapTools.add(zoomOutButton);
      mapTools.add(new JLabel("Lat: "));
      mapTools.add(setLatitude);
      mapTools.add(new JLabel("Long: "));
      mapTools.add(setLongitude);
      mapTools.add(latLonLabel);
      mapTools.setMinimumSize(new Dimension(oceanMap.getWidth(),40));
      mapPanel.add(mapTools,BorderLayout.SOUTH);
    add(mapPanel,BorderLayout.CENTER);
    
    JPanel trackAndDate = new JPanel(new FlowLayout());
    
    JPanel datePanel = new JPanel(new BorderLayout());
      datePanel.setName("Date Settings");
      datePanel.setBorder(new EtchedBorder());
      JPanel fromFlow = new JPanel(new FlowLayout());
        fromFlow.add(new JLabel("Start at:"));
          fromFlow.add(startDate);
          datePanel.add(fromFlow,BorderLayout.NORTH);
        JPanel toFlow = new JPanel(new FlowLayout());
          toFlow.add(new JLabel("End at:"));
          toFlow.add(endDate);
          datePanel.add(toFlow,BorderLayout.CENTER);
        JPanel stepFlow = new JPanel(new FlowLayout());
          stepFlow.add(new JLabel("Timestep (s):"));
          stepFlow.add(timeStepSize);
          datePanel.add(stepFlow,BorderLayout.SOUTH);
        
      
    JPanel trackPanel = new JPanel(new BorderLayout());
    trackPanel.setBorder(new EtchedBorder());
      JPanel trackModeFlow = new JPanel(new FlowLayout());
        trackModeFlow.add(new JLabel("Tracking Mode"));
        trackModeFlow.add(trackTypeSelector);
        trackPanel.add(trackModeFlow,BorderLayout.NORTH);
      JPanel velocityFieldFlow = new JPanel(new FlowLayout());
        velocityFieldFlow.add(new JLabel("Velocity Field:"));
        velocityFieldFlow.add(velocityFieldSelector);
        trackPanel.add(velocityFieldFlow,BorderLayout.CENTER);
      JPanel generateFlow = new JPanel(new FlowLayout());
        generateFlow.add(generateButton);
        trackPanel.add(generateFlow,BorderLayout.SOUTH);
        
    JPanel boundaryPanel = new JPanel(new BorderLayout());
      JPanel datasetLine = new JPanel(new FlowLayout());
      datasetLine.add(new JLabel("Dataset:"));
      datasetLine.add(datasetCombo);
      boundaryPanel.add(datasetLine,BorderLayout.NORTH);
      JPanel fluxPanel = new JPanel(new FlowLayout());
      fluxPanel.add(new JLabel("Heat flux: "));
      fluxPanel.add(calculateFlux);
      fluxPanel.add(readFlux);
      fluxGroup.add(calculateFlux);
      fluxGroup.add(readFlux);
      JPanel yearPanel = new JPanel(new FlowLayout());
      yearPanel.add(new JLabel("Use ERA40 Year"));
      yearPanel.add(ERA40Year);
      boundaryPanel.add(yearPanel,BorderLayout.CENTER);
      boundaryPanel.add(fluxPanel,BorderLayout.SOUTH);
      boundaryPanel.setBorder(new EtchedBorder());
    
    trackAndDate.add(datePanel);
    trackAndDate.add(trackPanel);
    trackAndDate.add(boundaryPanel);
    add(trackAndDate,BorderLayout.SOUTH);
    
    
    
    // Initiliase starting state
    
    initialiseComboBoxes();
    oceanMap.setMode(MapPanel.AddPointMode);
    zoomInButton.setSelected(false);
    zoomOutButton.setSelected(false);
    crossHairButton.setSelected(true);
    ERA40Year.setEnabled(false);
    
    // Add events
    
    mapSelector.addActionListener(eh);
    trackTypeSelector.addActionListener(eh);
    velocityFieldSelector.addActionListener(eh);
    crossHairButton.addActionListener(eh);
    zoomInButton.addActionListener(eh);
    zoomOutButton.addActionListener(eh);
    oceanMap.addMouseListener(eh);
    oceanMap.addMouseMotionListener(eh);
    generateButton.addActionListener(eh);
    startDate.addActionListener(eh);
    endDate.addActionListener(eh);
    timeStepSize.addCaretListener(eh);
    setLongitude.addCaretListener(eh);
    setLatitude.addCaretListener(eh);
    calculateFlux.addActionListener(eh);
    readFlux.addActionListener(eh);
    datasetCombo.addActionListener(eh);
    ERA40Year.addActionListener(eh);
    backgroundScale.addActionListener(eh);
    depthScale.addActionListener(eh);
    mapMask.addActionListener(eh);
    showGoogle.addActionListener(eh);
    depthScale.setEnabled(false);
    backgroundScale.setEnabled(false);
    mapMask.setEnabled(false);
    showGoogle.setEnabled(false);
  }
  
  protected void initialiseComboBoxes() {
    mapSelector.removeAllItems();
    trackTypeSelector.removeAllItems();
    velocityFieldSelector.removeAllItems();
    MapPanel.addMaps(mapSelector);
    Generator.addIntegrationMethods(trackTypeSelector);
    Generator.addVelocityFields(velocityFieldSelector);
    datasetCombo.removeAllItems();
    datasetCombo.addItem(BUNKER);
    datasetCombo.addItem(ERA40_SYNOPTIC);
    datasetCombo.addItem(ERA40_SINGLE);
    datasetCombo.addItem(ERA40_MEAN_YEAR);
    datasetCombo.addItem(ERA40_MONTHLY_AVG);
    ERA40Year.removeAllItems();
    for (int i=1958; i<2002; i++) ERA40Year.addItem(String.valueOf(i));
  }
  
  public void updateBackground() {
    if (currentDataSource==null) {
      oceanMap.setOverlay(null,false);
    } else {
      BackgroundColour.setGraduations(16);
      if (currentVariable.equals(E_2T)) BackgroundColour.setType(BackgroundColour.TEMPERATURE);
      else if (currentVariable.equals(E_OHL)) BackgroundColour.setType(BackgroundColour.HEAT_FLUX);      
      else if (currentVariable.equals(E_R)) BackgroundColour.setType(BackgroundColour.BLUE_SCALE);
      else if (currentVariable.equals(E_SSHF)) BackgroundColour.setType(BackgroundColour.HEAT_FLUX);
      else if (currentVariable.equals(E_SLHF)) BackgroundColour.setType(BackgroundColour.LATENT_HEAT);
      else if (currentVariable.equals(E_STR)) BackgroundColour.setType(BackgroundColour.THERMAL_RADIATION);
      else if (currentVariable.equals(E_WS)) BackgroundColour.setType(BackgroundColour.WIND_SPEED);
      else if (currentVariable.equals(E_TCC)) BackgroundColour.setType(BackgroundColour.TOTAL_CLOUD_COVER);
      else if (currentVariable.equals(B_CC)) BackgroundColour.setType(BackgroundColour.CLOUD_COVER);
      else if (currentVariable.equals(B_HF)) BackgroundColour.setType(BackgroundColour.HEAT_FLUX);
      else if (currentVariable.equals(B_WS)) BackgroundColour.setType(BackgroundColour.WIND_SPEED);
      else if (currentVariable.equals(B_EK)) BackgroundColour.setType(BackgroundColour.EKMAN);
      else if (currentVariable.equals(L_D)) BackgroundColour.setType(BackgroundColour.DENSITY);
      else if (currentVariable.equals(L_MLD)) BackgroundColour.setType(BackgroundColour.MLD);
      else if (currentVariable.equals(L_N)) BackgroundColour.setType(BackgroundColour.NITRATE);
      else if (currentVariable.equals(L_P)) BackgroundColour.setType(BackgroundColour.PHOSPHATE);
      else if (currentVariable.equals(L_S)) BackgroundColour.setType(BackgroundColour.SALINITY);
      else if (currentVariable.equals(L_Si)) BackgroundColour.setType(BackgroundColour.SILICATE);
      else if (currentVariable.equals(L_T))
        BackgroundColour.setType(BackgroundColour.TEMPERATURE);
      else if (currentDataSource.equals(OCCAM)) BackgroundColour.setType(BackgroundColour.VELOCITY);
      
      ArrayList minmax = null;
      
      if (currentDataSource.equals(LEVITUS)) {
        if (currentVariable.equals(L_MLD)) {
          currentLayer=0;
          currentData = FileIO.LoadGlobalProfile(FileIO.L_MLD,startSim);
          dvScale.setData(currentDataSource+" : "+currentVariable);
          minmax = oceanMap.setOverlay(currentData[0],mapMask.isSelected());
          oceanMap.paintComponent(oceanMap.getGraphics());
        } else {
          int layer=0;
          currentData = FileIO.LoadGlobalProfile(currentVariable,startSim);
          dvScale.setData(currentDataSource+" : "+currentVariable+", "+standardDepths[currentLayer]+" m");
          dpScale.setDataName(currentDataSource+" : "+currentVariable);        
          minmax = oceanMap.setOverlay(currentData[layer],mapMask.isSelected());
          oceanMap.paintComponent(oceanMap.getGraphics());
          dpScale.setVisible(true);
          depthScale.setSelected(true);
        }
      } else if (currentDataSource.equals(OCCAM)) {
        String layer=currentVariable.substring(9);
        int i=0;
        while (!Generator.velRanges[i].equals(layer)) i++;
        currentData = FileIO.getVelocityData(i,startSim);
        minmax = oceanMap.setOccamOverlay(currentData[0],currentData[1],mapMask.isSelected());
        dvScale.setData(OCCAM+" : "+currentVariable);
        
      } else {
        currentLayer=0;
        currentData = BoundaryData.getDataArray(currentDataSource,currentVariable,startSim);
        dvScale.setData(currentDataSource+" : "+currentVariable);
        if (currentData!=null) {
          if (currentData.length==1) minmax = oceanMap.setOverlay(currentData[0],mapMask.isSelected());
          else minmax = oceanMap.setOverlay(currentData[0],currentData[1],mapMask.isSelected());
        } else {
          currentDataSource=null;
          lockEvents++;
          bathRadio.setSelected(true);
          sortMap();
          lockEvents--;
        }
      }
        
      dvScale.setMax(((Double)minmax.get(0)).floatValue());
      dvScale.setMin(((Double)minmax.get(1)).floatValue());
      dvScale.repaint();
      dvScale.setVisible(true);
      showGoogle.setEnabled(true);
      backgroundScale.setSelected(true);
      
    }
    oceanMap.paint(oceanMap.getGraphics());
    
  }
  
  public void sortMap() {
    if (currentDataSource!=null) {
      if (currentDataSource.startsWith(LEVITUS+":")) {
        currentLayer=0;
        while (!currentVariable.equals(standardDepths[currentLayer]+" m")) currentLayer++;
        currentVariable = currentDataSource.substring(8);
        currentDataSource = LEVITUS;
        depthScale.setEnabled(true);
        backgroundScale.setEnabled(true);
        mapMask.setEnabled(true);
      } else {
        backgroundScale.setEnabled(true);
        mapMask.setEnabled(true);
        depthScale.setEnabled(false);
      }
    } else if (currentDataSource==null) {
      dvScale.setVisible(false);
      showGoogle.setEnabled(false);
      dpScale.setVisible(false);
      backgroundScale.setSelected(false);
      depthScale.setSelected(false);
      backgroundScale.setEnabled(false);
      mapMask.setSelected(false);
      mapMask.setEnabled(false);
      depthScale.setEnabled(false);
    }       
    updateBackground();
  }
  
  class EventHandler implements ActionListener, MouseListener, MouseMotionListener, CaretListener {
    boolean rightClick = false;
    public EventHandler() {}

   
    public void actionPerformed(ActionEvent e) {
      if ((e.getSource()==generateButton) && (lockEvents==0)) {
        lockEvents++;
        int StartMonth = 0;
        int StartDay = VEWUtilities.StandardMonthStart[StartMonth];
        double RunDaysDouble = (endSim.getTimeInMillis()-startSim.getTimeInMillis())/(1000*60*60*24);
        if (RunDaysDouble-Math.floor(RunDaysDouble)>0) RunDaysDouble+=1;
        int RunDays = (int) Math.floor(RunDaysDouble);
        
        String IntegrationMethod = trackTypeSelector.getSelectedItem().toString();
        if (IntegrationMethod.equals(Generator.INT_BWD)) {
          StartMonth = endSim.get(GregorianCalendar.MONTH);
          StartDay  += endSim.get(GregorianCalendar.DAY_OF_MONTH);
        } else {
          StartMonth = startSim.get(GregorianCalendar.MONTH);
          StartDay  += startSim.get(GregorianCalendar.DAY_OF_MONTH);
        }

        if (RunDays != 0) {
          generateButton.setEnabled(false);
          setLongitude.setEnabled(false);
          setLatitude.setEnabled(false);
          oceanMap.setLock(true);
          oceanMap.GenerateTrack(StartMonth, StartDay, RunDays, IntegrationMethod, velocityFieldSelector.getSelectedItem().toString());
        } 
        lockEvents--;
              
      } else if ((e.getSource()==zoomInButton) && (lockEvents==0)) {
        lockEvents++;
        oceanMap.setMode(MapPanel.ZoomInMode);
        zoomOutButton.setSelected(false);
        crossHairButton.setSelected(false);
        lockEvents--;
        
      } else if ((e.getSource()==zoomOutButton) && (lockEvents==0)) {
        lockEvents++;
        oceanMap.setMode(MapPanel.ZoomOutMode);
        zoomInButton.setSelected(false);
        crossHairButton.setSelected(false);
        lockEvents--;
        
      } else if ((e.getSource()==crossHairButton) && (lockEvents==0)) {
        lockEvents++;
        oceanMap.setMode(MapPanel.AddPointMode);
        zoomInButton.setSelected(false);
        zoomOutButton.setSelected(false);
        lockEvents--;
        
      } else if ((e.getSource()==mapSelector) && (lockEvents==0)) {
        lockEvents++;
        oceanMap.changeRegion(mapSelector.getSelectedItem().toString());
        theModel.setValue("track/map",mapSelector.getSelectedItem().toString());
        vc2.unsaved(false);
        lockEvents--;
        
      } else if ((e.getSource()==trackTypeSelector) && (lockEvents==0)) {
        lockEvents++;
        oceanMap.GoForth(trackTypeSelector.getSelectedIndex() != Generator.ReverseIntegrate);
        updateButtons();
        theModel.setValue("track/trackmode",trackTypeSelector.getSelectedItem().toString());
        vc2.unsaved(false);
        lockEvents--;
     
      } else if ((e.getSource()==startDate) && (lockEvents==0)) {
        lockEvents++;
        final long rememberStart = startSim.getTimeInMillis();
        dd.setEarliest(null);
        dd.setLatest(null);
        dd.show(startSim);
        startSim.setTimeInMillis(dd.getDate().getTimeInMillis());
        startDate.setText(DateDialog.getString(startSim));
        theModel.setValue("track/start",String.valueOf(startSim.getTimeInMillis()));
        if (endSim.getTimeInMillis()<startSim.getTimeInMillis()) {
         endSim.setTimeInMillis(startSim.getTimeInMillis()+(24*3600*1000));
         endDate.setText(DateDialog.getString(endSim));
         theModel.setValue("track/end",String.valueOf(endSim.getTimeInMillis()));
        }
        vc2.unsaved(true);
        if (rememberStart!=startSim.getTimeInMillis()) {
          if (currentDataSource!=null) sortMap();
        }
        lockEvents--;
        
      } else if ((e.getSource()==endDate) && (lockEvents==0)) {
        lockEvents++;
        dd.setEarliest(null);
        dd.setLatest(null);
        dd.show(endSim);
        endSim.setTimeInMillis(dd.getDate().getTimeInMillis());
        endDate.setText(DateDialog.getString(endSim));        
        theModel.setValue("track/end",String.valueOf(endSim.getTimeInMillis()));
        if (startSim.getTimeInMillis()>endSim.getTimeInMillis()) {
          startSim.setTimeInMillis(endSim.getTimeInMillis()-(24*3600*1000));
          startDate.setText(DateDialog.getString(startSim));
          theModel.setValue("track/start",String.valueOf(startSim.getTimeInMillis()));
         }
        vc2.unsaved(true);
        lockEvents--;
      
      } else if ((e.getSource()==velocityFieldSelector) && (lockEvents==0)) {
        lockEvents++;
        theModel.setValue("track/velocityfield",velocityFieldSelector.getSelectedItem().toString());
        vc2.unsaved(false);
        lockEvents--;
      
      } else if ((e.getSource()==datasetCombo) && (lockEvents==0)) {
        updateButtons();
        updateData();
        vc2.unsaved(false);
        
      } else if (e.getSource()==calculateFlux) {
        updateData();
        vc2.unsaved(false);
        
      } else if (e.getSource()==readFlux) {
        updateData();
        vc2.unsaved(false);
        
      } else if (e.getSource()==ERA40Year) {
        updateData();
        vc2.unsaved(false);
      
      } else if (e.getSource() instanceof JRadioButtonMenuItem) {
        currentVariable = ((JRadioButtonMenuItem)e.getSource()).getText();
        currentDataSource = ((JRadioButtonMenuItem)e.getSource()).getName();
        sortMap();
      
      } else if (e.getSource()==backgroundScale) {
        dvScale.setVisible(backgroundScale.isSelected());
        
      } else if (e.getSource()==depthScale) {
        dpScale.setVisible(depthScale.isSelected());
      
      } else if (e.getSource()==mapMask) {
        updateBackground();
      
      } else if (e.getSource()==showGoogle) {
        if (currentDataSource.equals(LEVITUS)) {
          if (currentVariable.equals(L_MLD)) MapPanel.showGoogleEarth(currentData[0],"VEWTemp",mapMask.isSelected());
          else MapPanel.showGoogleEarth(currentData[currentLayer],"VEWTemp",mapMask.isSelected());
        } else if (currentDataSource.equals(OCCAM)) MapPanel.showGoogleEarthOCCAM(currentData[0],currentData[1],"VEWTemp",mapMask.isSelected());
        else {
          if (currentData.length==1) MapPanel.showGoogleEarth(currentData[0],"VEWTemp",mapMask.isSelected());
          else MapPanel.showGoogleEarth(currentData[0],currentData[1],"VEWTemp",mapMask.isSelected()); 
        }
        String absolutePath = new File("VEWTemp"+File.separator+"vew.kmz").getAbsolutePath();
        try {
          Runtime.getRuntime().exec(googleEarthPath+" \""+absolutePath+"\"");
        } catch (Exception ex) { ex.printStackTrace(); }
        
      }
    }

    public void mouseClicked(MouseEvent e) {}
    
    public void mouseMoved(MouseEvent e) {
      if ((e.getSource()==oceanMap) && (lockEvents==0)) {
        double longit = oceanMap.getLongitude();
        double latit = oceanMap.getLatitude();
        latLonLabel.setText("Lat: "+dfLL.format(latit)+" Long: "+dfLL.format(longit));
        if ((currentData!=null) && (currentDataSource!=null)) {
          double longCo = 0;
          if (longit<0) longCo= -longit;
          else longCo= (360-longit);
          double latCo =  (90-latit);
          if (longCo>359) longCo=359;
          if (latCo>179) latCo=179;
          if (longCo<0) longCo=0;
          if (latCo<0) latCo=0;
          if (currentDataSource.equals(OCCAM)) {
            longCo*=4;
            latCo*=4;
            dvScale.setVal(String.valueOf(currentData[0][(int)longCo][(int)latCo])+" angle "+String.valueOf(currentData[1][(int)longCo][(int)latCo]));
            
          } else if (currentDataSource.equals(LEVITUS)) {
            dvScale.setVal(String.valueOf(currentData[currentLayer][(int)longCo][(int)latCo]));
            dpScale.update((int)longCo,(int)latCo);
          }
          else if (currentData.length==2) {
            dvScale.setVal(String.valueOf(currentData[0][(int)longCo][(int)latCo])+" angle "+
                String.valueOf(currentData[1][(int)longCo][(int)latCo]));
            
          } else dvScale.setVal(String.valueOf(currentData[0][(int)longCo][(int)latCo]));
          
        }
      
      }
    }
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) { 
      if (e.isPopupTrigger()) rightClick=true;
    }
    public void mouseReleased(MouseEvent e) { 
      if (e.isPopupTrigger()) rightClick=true;
      handleAllClicks(e);
    }    
    
    public void handleAllClicks(MouseEvent e) {
      if (e.getSource()==oceanMap) {
        if (rightClick) {
          rightClick=false;
          dvMenu.show(e.getComponent(), e.getX(), e.getY());
        } else {
          if (dvMenu.isVisible()) dvMenu.setVisible(false); 
          else {
            lockEvents++;
            double longit = oceanMap.getLongitude();
            double latit = oceanMap.getLatitude();
            setLatitude.setText(dfLL.format(latit));
            setLongitude.setText(dfLL.format(longit));
            theModel.setValue("track/longitude",String.valueOf(dfLL.format(latit)));
            theModel.setValue("track/latitude",String.valueOf(dfLL.format(latit)));
            vc2.unsaved(false);
            lockEvents--;
          }
        }
      
      }
      
    }
    public void mouseDragged(MouseEvent e) {}

    
    public void caretUpdate(CaretEvent e) {
      if ((e.getSource()==timeStepSize) && (lockEvents==0)) {
        lockEvents++;
        int tss=0;
        try { tss = Integer.parseInt(timeStepSize.getText()); }
        catch (NumberFormatException nfe) {}
        theModel.setValue("track/secstep",String.valueOf(tss));
        vc2.unsaved(false);
        lockEvents--;
      
      } else if (((e.getSource()==setLongitude) || (e.getSource()==setLatitude)) && (lockEvents==0)) {
        lockEvents++;
        double longit=0;
        double latit=0;
        try { longit = Double.parseDouble(setLongitude.getText()); } catch (NumberFormatException nfe) {}
        try { latit = Double.parseDouble(setLatitude.getText()); } catch (NumberFormatException nfe) {}
        latLonLabel.setText("Lat: "+dfLL.format(latit)+" Long: "+dfLL.format(longit));
        theModel.setValue("track/longitude",String.valueOf(dfLL.format(longit)));
        theModel.setValue("track/latitude",String.valueOf(dfLL.format(latit)));
        oceanMap.removeAllPoints();
        oceanMap.addPoint(longit,latit);
        vc2.unsaved(false);
        lockEvents--;
      }
    }
  }
  
  class DataViewerScale extends JDialog {
    private float minNumber = 0.0f;
    private float maxNumber = 0.0f;
    private ScalePanel scalePanel;
    private JLabel titleLabel = new JLabel("");
    private JLabel valLabel = new JLabel("");
    
    public void setMax(float max) { maxNumber = max; }
    public void setMin(float min) { minNumber = min; }
    public void setData(String lab) { titleLabel.setText("   Data showing: "+lab);}
    public void setVal(String val) { 
      valLabel.setText("   Value under cursor: "+val);
    }
    
    public DataViewerScale(JFrame vc2) {
      super(vc2,"Scale",false);
      setSize(450,150);
      getContentPane().setLayout(new BorderLayout());
      JPanel scaleBit = new JPanel(new FlowLayout(FlowLayout.CENTER));
      scalePanel=new ScalePanel();
      scalePanel.setPreferredSize(new Dimension(340,80));
      scaleBit.add(scalePanel);
      scalePanel.setBorder(new EtchedBorder());
      JPanel underBits = new JPanel(new BorderLayout());
      underBits.add(titleLabel,BorderLayout.NORTH);
      underBits.add(valLabel,BorderLayout.SOUTH);
      getContentPane().add(underBits,BorderLayout.SOUTH);
      getContentPane().add(scaleBit,BorderLayout.CENTER);
      addWindowListener(new DVCloseEvent());
    }
    
    class ScalePanel extends JPanel {
      public ScalePanel() {super(); }
      public void paintComponent(Graphics g) {
        for (int i=0; i<=16; i++) {
          float f = minNumber+((i/16f)*(maxNumber-minNumber));
          if (i<16) {
            g.setColor(BackgroundColour.getColour(f,minNumber,maxNumber));
            g.fillRect(10+(i*20),0,20,20);
          }  
          g.setColor(Color.black);
          String theNumber = "";
          if ((Math.abs(f)>1E3) || (Math.abs(f)<1E-3))
            theNumber = new DecimalFormat("0.0E0").format(f);
          else theNumber = new DecimalFormat("0.00").format(f);
          AxisDefinition.plotRotatedText((Graphics2D)g,4+(i*20),26,90,theNumber);
          g.drawLine(10+(i*20),0,10+(i*20),24);
        }
        g.drawLine(10,0,330,0);
        g.drawLine(10,20,330,20);
      }
    }
    
    class DVCloseEvent implements WindowListener {
      public void windowActivated(WindowEvent e) {}
      public void windowClosed(WindowEvent e) {}
      public void windowClosing(WindowEvent e) { backgroundScale.setSelected(false); }
      public void windowDeactivated(WindowEvent e) {}
      public void windowDeiconified(WindowEvent e) {}
      public void windowIconified(WindowEvent e) {}
      public void windowOpened(WindowEvent e) {}
    }
  }
  
  
  class DepthScale extends JDialog {
    DefaultTableModel depthTableModel = new DefaultTableModel();
    JTable depthTable = new JTable(depthTableModel);
    JLabel dataName = new JLabel("");
    
    public DepthScale(JFrame vc2) {
      super(vc2,"Depths",false);
      depthTableModel.setColumnCount(2);
      depthTableModel.setColumnIdentifiers(new String[] {"Depth","Value"});
      for (int i=0; i<14; i++) {
        depthTableModel.addRow(new String[] {String.valueOf(standardDepths[i]+" m"),"0.0"});
      }
      depthTable.getColumnModel().getColumn(0).setPreferredWidth(40);
      depthTable.getColumnModel().getColumn(1).setPreferredWidth(80);
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(dataName,BorderLayout.NORTH);
      JScrollPane depthScroller = new JScrollPane(depthTable);
      depthScroller.setPreferredSize(new Dimension(120,250));
      getContentPane().add(depthScroller,BorderLayout.CENTER);
      setSize(150,300);
      addWindowListener(new DPCloseEvent());
      
    }
    
    public void setDataName(String s) { dataName.setText(s); }
    public void update(int x, int y) {
      for (int i=0; i<14; i++) {
        depthTableModel.setValueAt(String.valueOf(currentData[i][x][y]),i,1);
      }
    }
  }
  
  class DPCloseEvent implements WindowListener {
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) { depthScale.setSelected(false); }
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
  }
}
