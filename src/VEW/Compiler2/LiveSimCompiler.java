package VEW.Compiler2;

import java.io.PrintWriter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class LiveSimCompiler {
  
  public static void writeLiveSimFG(XMLTag theModel, XMLTag fg, PrintWriter FGPW) {
    FGPW.println("  public void updateLiveSim(boolean force) {");
    FGPW.println("    LiveSim.updateFlag();");
    FGPW.println("    LiveSim.stateTable.setID(id);");
    FGPW.println("    LiveSim.stateTable.setStage(_stageNames[_CurrentStage]);");
    FGPW.println("    LiveSim.stateTable.setC(c[0],c[1]);");
    FGPW.println("    LiveSim.stateTable.setZ(z[1],z[1]);");
    int speciesStageID = 0;
    XMLTag[] speciesTags = theModel.getTag("species").getTags("species");
    for (int i=0; i<speciesTags.length; i++) {
      XMLTag stages[] = theModel.getTagWhere("functionalgroup","name",speciesTags[i].getAttribute("fg")).getTags("stage");
      for (int j = 0; j < stages.length; j++) {
        FGPW.println("    LiveSim.intPoolTable.setBioIng("+i+","+j+", IngestedCells["+speciesStageID+"]);");
        speciesStageID++;
      }
    }    
    XMLTag locals[] = XMLTag.sortTags(fg.getTags("local"),"name");
    for (int i=0; i<locals.length; i++) {
      FGPW.println("    LiveSim.locTable.set("+i+", (force?Double.NaN:"+locals[i].getValue("name")+"));");
    }
    XMLTag states[] = XMLTag.sortTags(fg.getTags("variable"),"name");
    for (int i=0; i<states.length; i++) {
      FGPW.println("    LiveSim.stateTable.set("+i+", "+states[i].getValue("name")+"[0],"+states[i].getValue("name")+"[1]);");
    }

    for (int _sp=0; _sp<speciesTags.length; _sp++) {
      if (speciesTags[_sp].getAttribute("fg").equals(fg.getValue("name"))) {
        FGPW.println("    if (params._type=="+_sp+") {");
        XMLTag[] vls = XMLTag.sortTags(fg.getTags("varietylocal"),"name");
        int id=0;
        for (int i=0; i<vls.length; i++) {
          if (!vls[i].getValue("name").equals("IngestedCells")) {
            String link = vls[i].getAttribute("link");
            XMLTag[] foodSets = theModel.getTag("foodsets").getTags("foodset");
            for (int j=0; j<foodSets.length; j++) {
              XMLTag foodSet = foodSets[j];
              String setName = foodSet.getAttribute("name");
              String predatorSpeciesName = setName.substring(0,setName.indexOf(":")-1);
              setName = setName.substring(setName.indexOf(":")+2);
              if ((setName.equals(link)) && (predatorSpeciesName.equals(speciesTags[_sp].getAttribute("name"))))  {
                XMLTag[] typeMembers = foodSet.getTags("food");
                for (int k=0; k<typeMembers.length; k++) {
                  FGPW.println("      LiveSim.locTable.setFoodBased(params._type, "+id+", "+vls[i].getValue("name")+"[params._speciesOfFG]["+k+"]);");
                  id++;
                }
              }
            }
          }
        }    
        FGPW.println("    }");
        XMLTag[] vvs = XMLTag.sortTags(fg.getTags("varietyvariable"),"name");
        id=0;
        for (int i=0; i<vvs.length; i++) {
          if (!vvs[i].getValue("name").equals("IngestedCells")) {
            String link = vvs[i].getAttribute("link");
            XMLTag[] foodSets = theModel.getTag("foodsets").getTags("foodset");
            for (int j=0; j<foodSets.length; j++) {
              XMLTag foodSet = foodSets[j];
              String setName = foodSet.getAttribute("name");
              String predatorSpeciesName = setName.substring(0,setName.indexOf(":")-1);
              setName = setName.substring(setName.indexOf(":")+2);
              if ((setName.equals(link)) && (predatorSpeciesName.equals(speciesTags[_sp].getAttribute("name"))))  {
                XMLTag[] typeMembers = foodSet.getTags("food");
                for (int k=0; k<typeMembers.length; k++) {
                  FGPW.println("    LiveSim.stateTable.setFoodBased(params._type," + id+", "+vvs[i].getValue("name")+"["+j+"][0], "+vvs[i].getValue("name")+"["+k+"][1]);");
                  id++;
                }
              }
            }
          }
        }
      
      }
      
    }
    
    
    
   
    for (int i = 0; i < speciesTags.length; i++) {
      XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",speciesTags[i].getAttribute("fg")).getTags("stage");
      for (int j = 0; j< stages.length; j++) {
        FGPW.println("    LiveSim.ambBioTable.setAmbientBio("+i+","+j+",blayer.SpeciesConcentration["+i+"]["+j+"],blayer.AgentCounts["+i+"]["+j+"]);");
      }
    }

    final XMLTag[] chems = theModel.getTags("chemical");
    final boolean[] chemsInUse = BlackBox.getChemPoolUsage(fg,theModel);
    final boolean[] chemIngUse = BlackBox.getChemIngUsage(fg,theModel);
    for (int i=0; i<chems.length; i++) {
      final String chemName = StringTools.nonSpace(chems[i].getValue("name"));
      if (chemsInUse[i]) {
        FGPW.print("    LiveSim.intPoolTable.setChemPool("+chems[i].getValue("name")+".ID_"+chems[i].getValue("name")+",");
        FGPW.println("(force?Double.NaN:_"+chemName+"_Pool_New),_"+chemName+"_Pool_Old);");
      }
      if (chemIngUse[i]) {
        FGPW.print("    LiveSim.intPoolTable.setChemIng("+chems[i].getValue("name")+".ID_"+chems[i].getValue("name")+",");
        FGPW.println("(force?Double.NaN:_"+chemName+"_Ing));");
      }
    }

     
    for (int i=0; i<chems.length; i++) {
      String chemical = StringTools.nonSpace(chems[i].getValue("name"));
      FGPW.println("    LiveSim.ambChemTable.setAmbientChem("+chemical+".ID_"+chemical+",blayer.solution_chem["+chemical+".ID_"+chemical+"],blayer.particulate_chem["+i+"]);");
      XMLTag[] species = theModel.getTag("species").getTags("species");
    }

    FGPW.println("    LiveSim.ambPhyTable.set(AmbientPhysicsTable.DENSITY,Utils.getDensity(z[1]));");
    FGPW.println("    LiveSim.ambPhyTable.set(AmbientPhysicsTable.SALINITY,Utils.getSalinity(z[1]));");
    FGPW.println("    LiveSim.ambPhyTable.set(AmbientPhysicsTable.TEMPERATURE,Utils.getTemperature(z[1]));");
    FGPW.println("    LiveSim.ambPhyTable.set(AmbientPhysicsTable.VIS_IRRAD,Utils.getVisIrrad(z[1]));");
    FGPW.println("    LiveSim.ambPhyTable.set(AmbientPhysicsTable.FULL_IRRAD,Utils.getFullIrrad(z[1]));");    
    FGPW.println("  }");
    FGPW.println("");    
       /** ****************************************************************************************** */
  
  }
  
  public static void writeLiveSimJava(String fileName, XMLTag model) {
    try {
      final int speciesCount = model.getTag("species").getTags("species").length;
      PrintWriter PW = StringTools.OpenOutputFile(fileName);
      XMLTag[] fgs = model.getTags("functionalgroup");
      PW.println("package VEW.Sim;");
      PW.println("");
      PW.println("import java.awt.BorderLayout;");
      PW.println("import java.awt.Color;");
      PW.println("import java.awt.Dimension;");
      PW.println("import java.awt.FlowLayout;");
      PW.println("import java.awt.font.FontRenderContext;");
      PW.println("import java.awt.Graphics;");
      PW.println("import java.awt.Graphics2D;");
      PW.println("import java.awt.GridLayout;");
      PW.println("import java.awt.event.ActionEvent;");
      PW.println("import java.awt.event.ActionListener;");
      PW.println("import java.awt.event.ComponentEvent;");
      PW.println("import java.awt.event.ComponentListener;");
      PW.println("import java.awt.event.FocusEvent;");
      PW.println("import java.awt.event.FocusListener;");
      PW.println("import java.awt.event.ItemEvent;");
      PW.println("import java.awt.event.ItemListener;");
      PW.println("import java.awt.event.KeyEvent;");
      PW.println("import java.awt.event.KeyListener;");
      
      PW.println("import java.io.File;");
      PW.println("import java.io.ObjectInputStream;");
      PW.println("import java.text.DecimalFormat;");
      PW.println("import java.util.ArrayList;");
      PW.println("import java.util.Arrays;");
      PW.println("");
      PW.println("import javax.swing.DefaultListModel;");
      PW.println("import javax.swing.JButton;");
      PW.println("import javax.swing.JCheckBox;");
      PW.println("import javax.swing.JComboBox;");
      PW.println("import javax.swing.JDialog;");      
      PW.println("import javax.swing.JFrame;");
      PW.println("import javax.swing.JLabel;");
      PW.println("import javax.swing.JList;");      
      PW.println("import javax.swing.JPanel;");
      PW.println("import javax.swing.JScrollPane;");
      PW.println("import javax.swing.JTabbedPane;");
      PW.println("import javax.swing.JTable;");
      PW.println("import javax.swing.JTextField;");
      PW.println("import javax.swing.WindowConstants;");
      PW.println("import javax.swing.border.EtchedBorder;");
      PW.println("import javax.swing.table.DefaultTableModel;");
      PW.println("import javax.swing.event.ListSelectionEvent;");
      PW.println("import javax.swing.event.ListSelectionListener;");

      PW.println("");
      PW.println("import VEW.Common.BoundaryDataStep;");
      PW.println("import VEW.Common.JComboCheckBox;");
      PW.println("");
      PW.println("public class LiveSim {");
      PW.println("");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("");
      PW.println("  private static LiveSim theInstance = null;");
      PW.println("  private static JFrame mainFrame = null;");
      PW.println("  private static final JButton exitButton = new JButton(\"Exit\");");
      PW.println("  private static final JButton runToHour = new JButton(\"RUN TO... (Hours)...\");");
      PW.println("  private static final JButton gotoParticle = new JButton(\"Goto...\");");
      PW.println("  private static JPanel mainDebug = new JPanel(new BorderLayout());");
      PW.println("  private static final JTextField runtil = new JTextField(\"0.0\");");
      PW.println("  private static final JComboBox sortBy = new JComboBox();");
      PW.println("");
      PW.println("  protected static final JLabel timestepLabel = new JLabel(emptyString);");
      PW.println("  protected static final JButton showDG = new JButton(\"Show Depth Graph\");");
      PW.println("  private static EnvironmentTable envTable = new EnvironmentTable();");
      PW.println("  private static ParameterTable paramTable = new ParameterTable();");      
      PW.println("  public static AmbientBioTable ambBioTable = new AmbientBioTable();");
      PW.println("  public static AmbientChemTable ambChemTable = new AmbientChemTable();");
      PW.println("  public static AmbientPhysicsTable ambPhyTable = new AmbientPhysicsTable();");      
      PW.println("  public static InternalPoolTable intPoolTable = new InternalPoolTable();");
      PW.println("  public static LocTable locTable = new LocTable();");
      PW.println("  public static StateTable stateTable = new StateTable();");
      PW.println("  private static JPanel mesoPanel;");
      PW.println("  private static JScrollPane mesoScroller;");
      PW.println("  private static JPanel paramPanel;");      
      PW.println("  private static JScrollPane paramScroller;");
      PW.println("  private static JPanel ambBioPanel;");      
      PW.println("  private static JScrollPane ambBioScroller;");
      PW.println("  private static JPanel ambChemPanel;");      
      PW.println("  private static JScrollPane ambChemScroller;");
      PW.println("  private static JPanel ambPhyPanel;");      
      PW.println("  private static JScrollPane ambPhyScroller;");
      PW.println("  private static JPanel intPoolPanel;");      
      PW.println("  private static JScrollPane intPoolScroller;");
      PW.println("  private static JPanel locPanel;");
      PW.println("  private static JScrollPane locScroller;");
      PW.println("  private static JPanel statePanel;");
      PW.println("  private static JScrollPane stateScroller;");
      PW.println("  private static int lockEvents=0;");

      PW.println("  private static JTabbedPane tabbedPane;");
      
      PW.println("  private static JPanel steps;");
      PW.println("");
      PW.println("  public static long logID = 0;");
      PW.println("");
      PW.println("  private static JCheckBox[][] stages;");
      PW.println("  private static JComboCheckBox stateList = new JComboCheckBox();");
      PW.println("  private static final JComboBox speciesName = new JComboBox();");
      PW.println("  private static final JComboBox idNo = new JComboBox();");
      PW.println("  private static ObjectInputStream boundaryData;");
      PW.println("  private static BoundaryDataStep cds;");
      PW.println("  private static DecimalFormat DF = new DecimalFormat();");
      PW.println("  private static boolean inUse = false;");
      PW.println("  private EventHandler eh = new EventHandler();");
      PW.println("  private static boolean fgUpdated = false;");
      PW.println("  DepthGraph dg;");
      PW.println("");
      PW.println("  public static void updateFlag() { fgUpdated = true; }");
      PW.println("");
      PW.println("  public static boolean inUse() {");
      PW.println("    return inUse;");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void main(String[] args) throws Exception");
      PW.println("  {");
      PW.println("    System.out.println(\"This method of simulation execution is no longer supported, please use 'java -jar VEW.jar /live' instead.\");");
      PW.println("  }");
      PW.println("");
      PW.println("  public static LiveSim getInstance() {");
      PW.println("    if (theInstance==null) theInstance = new LiveSim();");
      PW.println("    return theInstance;");
      PW.println("  }");
      PW.println("");
      PW.println("  private LiveSim() {");
      PW.println("    DF.setMaximumIntegerDigits(3);");
      PW.println("    DF.setMaximumFractionDigits(3);");
      PW.println("    mainFrame = new JFrame(\"Live VEW Simulator\");");
      PW.println("    mainFrame.setSize(800,500);");
      PW.println("    mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);");
      PW.println("    dg = new DepthGraph(mainFrame);");
      PW.println("    steps = new JPanel(new FlowLayout());");
      PW.println("    steps.add(showDG);");
      PW.println("    steps.add(new JLabel(\"Time (hours)\"));");
      PW.println("    steps.add(timestepLabel);");
      PW.println("    steps.add(runToHour);");
      PW.println("    steps.add(runtil);");
      PW.println("    timestepLabel.setPreferredSize(new Dimension(100,25));");
      PW.println("    steps.setPreferredSize(new Dimension(700,35));");
      PW.println("    runtil.setPreferredSize(new Dimension(100,25));");
      PW.println("    steps.add(exitButton);");
      PW.println("    locTable.showSpecies(0);");
      PW.println("    stateTable.showSpecies(0);");      
      PW.println("    tabbedPane = new JTabbedPane();");
      PW.println("    mesoPanel = new JPanel(new FlowLayout());");
      PW.println("      mesoScroller = new JScrollPane(envTable);");
      PW.println("      mesoPanel.add(mesoScroller);");
      PW.println("      tabbedPane.addTab(\"Mesocosm\",mesoPanel);");
      PW.println("    ambBioPanel = new JPanel(new FlowLayout());");
      PW.println("      ambBioScroller = new JScrollPane(ambBioTable);");
      PW.println("      ambBioPanel.add(ambBioScroller);");
      PW.println("      tabbedPane.addTab(\"Ambient Biology\",ambBioPanel);");
      PW.println("    ambChemPanel = new JPanel(new FlowLayout());");
      PW.println("      ambChemScroller = new JScrollPane(ambChemTable);");
      PW.println("      ambChemPanel.add(ambChemScroller);");
      PW.println("      tabbedPane.addTab(\"Ambient Chemistry\",ambChemPanel);");
      PW.println("    ambPhyPanel = new JPanel(new FlowLayout());");
      PW.println("      ambPhyScroller = new JScrollPane(ambPhyTable);");
      PW.println("      ambPhyPanel.add(ambPhyScroller);");
      PW.println("      tabbedPane.addTab(\"Ambient Physics\",ambPhyPanel);");
      PW.println("    intPoolPanel = new JPanel(new FlowLayout());");
      PW.println("      intPoolScroller = new JScrollPane(intPoolTable);");
      PW.println("      intPoolPanel.add(intPoolScroller);");
      PW.println("      tabbedPane.addTab(\"Agent Pools\",intPoolPanel);");
      PW.println("    statePanel = new JPanel(new FlowLayout());");
      PW.println("      stateScroller = new JScrollPane(stateTable);");
      PW.println("      statePanel.add(stateScroller);");
      PW.println("      tabbedPane.addTab(\"Agent State Vars\",statePanel);");
      PW.println("    locPanel = new JPanel(new FlowLayout());");
      PW.println("      locScroller = new JScrollPane(locTable);");
      PW.println("      locPanel.add(locScroller);");
      PW.println("      tabbedPane.addTab(\"Agent Local Vars\",locPanel);");
      PW.println("    paramPanel = new JPanel(new FlowLayout());");
      PW.println("      paramScroller = new JScrollPane(paramTable);");
      PW.println("      paramPanel.add(paramScroller);");
      PW.println("      tabbedPane.addTab(\"Parameters\",paramPanel);");
      PW.println("    updateStateList();");
      PW.println("    stateList.addActionListener(eh);");
      PW.println("    stateList.setPreferredSize(new Dimension(300,25));");
      PW.println("    exitButton.addActionListener(eh);");
      PW.println("    runToHour.addActionListener(eh);");
      PW.println("    gotoParticle.addActionListener(eh);");
      PW.println("    speciesName.addItemListener(eh);");
      PW.println("    idNo.addItemListener(eh);");
      PW.println("    sortBy.addItemListener(eh);");
      PW.println("    runToHour.setPreferredSize(new Dimension(150,25));");
      PW.println("    runToHour.setEnabled(true);");
      PW.println("    final JPanel particleSelector = new JPanel(new GridLayout(2,1));");
      PW.println("    particleSelector.add(speciesName);");
      PW.println("    speciesName.setPreferredSize(new Dimension(300,25));");
      PW.println("    final JPanel idSelector = new JPanel(new FlowLayout());");
      PW.println("    particleSelector.add(idSelector);");
      PW.println("    idSelector.add(new JLabel(\"Sort by:\"));");
      PW.println("    idSelector.add(sortBy);");
      PW.println("    idSelector.add(new JLabel(\"Agent id:\"));");
      PW.println("    idSelector.add(idNo);");
      PW.println("    idSelector.add(new JLabel(\"State:\"));");
      PW.println("    idSelector.add(stateList);");
      PW.println("    idSelector.add(gotoParticle);");
      PW.println("    mainDebug.add(particleSelector,BorderLayout.NORTH);");
      PW.println("    mainDebug.add(tabbedPane,BorderLayout.CENTER);");
      PW.println("    mainDebug.add(steps,BorderLayout.SOUTH);");
      PW.println("    mainFrame.getContentPane().setLayout(new BorderLayout(0,0));");
      PW.println("    mainFrame.getContentPane().add(mainDebug,BorderLayout.CENTER);");
      PW.println("    mainFrame.pack();");
      PW.println("    inUse = true;");
      PW.println("    mainFrame.setVisible(true);");
      PW.println("    mainFrame.addComponentListener(eh);");
      PW.println("    showDG.addActionListener(eh);");
      PW.println("    resize();");
      PW.println("  }");
      PW.println("");
      
      PW.print("  final static int[] speciesToFg = new int[] {");
      XMLTag[] species = model.getTag("species").getTags("species");
      for (int i=0; i<species.length; i++) {
        int j=0;
        while (!fgs[j].getValue("name").equals(species[i].getAttribute("fg"))) j++;
        PW.print(String.valueOf(j));
        if (i<species.length-1) PW.print(",");
      }
      PW.println("};");
      
      PW.println("");
      PW.println("  public static void updateStateList() {");
      PW.println("    int speciesNo = speciesName.getSelectedIndex();");
      PW.println("    stateList.removeAllItems();");
      PW.println("    if (speciesNo!=-1) {");
      PW.println("      for (int i=0; i<stages[speciesToFg[speciesNo]].length; i++)");
      PW.println("        if (stages[speciesToFg[speciesNo]][i]!=null) stateList.addItem(stages[speciesToFg[speciesNo]][i]);");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void updateValues() {");
      PW.println("    envTable.set(EnvironmentTable.TURBOCLINE,Kernel.W.MLDepth);");
      PW.println("    envTable.set(EnvironmentTable.SUNLIGHT,Kernel.W.SunLight);");
      PW.println("    envTable.set(EnvironmentTable.HEAT_LOSS,Kernel.W.Cooling);");
      PW.println("    envTable.set(EnvironmentTable.WIND_SPEED,Kernel.W.WSpeed);");
      PW.println("    envTable.set(EnvironmentTable.EKMAN,Kernel.W.Ekman);");
      PW.println("    envTable.set(EnvironmentTable.ZENITH_HEIGHT,Kernel.W.Z_Height);");
      for (int i=0; i<species.length; i++) {
        XMLTag fg = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg"));
        XMLTag[] states = fg.getTags("stage");
        for (int j=0; j<states.length; j++)
          PW.println("    envTable.setTotalBio("+i+","+j+",Kernel.W.SpeciesTotal["+i+"]["+j+"],Kernel.W.AgentCounts["+i+"]["+j+"]);");
      }      XMLTag[] chems = model.getTags("chemical");
      for (int i=0; i<chems.length; i++) {
        PW.print("    envTable.setMesoChem("+chems[i].getValue("name")+".ID_"+chems[i].getValue("name")+",");
        PW.print("Kernel.W.total_chem["+chems[i].getValue("name")+".ID_"+chems[i].getValue("name")+"],");
        PW.println("Kernel.W.particulate_chem["+chems[i].getValue("name")+".ID_"+chems[i].getValue("name")+"]);");
      }
      for (int i=0; i<chems.length; i++) {
        XMLTag[] specs = model.getTag("species").getTags("species");
        for (int j=0; j<specs.length; j++) {
          XMLTag[] stages = model.getTagWhere("functionalgroup","name",specs[j].getAttribute("fg")).getTags("stage");
          for (int k=0; k<stages.length; k++) {
            PW.print("    envTable.setMesoChem("+chems[i].getValue("name")+".ID_"+chems[i].getValue("name")+","+j+","+k+",");
            PW.println("Kernel.W.particulate_chem_ss["+chems[i].getValue("name")+".ID_"+chems[i].getValue("name")+"]["+j+"]["+k+"],0);");
          }
        }
      }
      
      PW.println("    timestepLabel.setText(String.valueOf(((Kernel.myTime-SysVars.startTime)/SysVars.stepInMillis)*SysVars.stepInHours));");
      PW.println("    mainFrame.paint(mainFrame.getGraphics());");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void startSim() throws Exception {");
      PW.println("    SysVars.init();");
      PW.println("    Kernel.DTime = SysVars.getHourOfDay(SysVars.startTime);");
      PW.println("    Kernel.W = new WaterCol();");
      PW.println("    SysVars.CallInits();");
      PW.println("    java.util.jar.JarFile JF = new java.util.jar.JarFile(Launcher.JarPath+File.separator+\"VEW.jar\");");
      PW.println("    java.util.jar.JarEntry JE = JF.getJarEntry(\"BoundaryConditions.vso\");");
      PW.println("    boundaryData = new ObjectInputStream(JF.getInputStream(JE));");
      PW.println("    Kernel.myTime = SysVars.startTime;");
      PW.println("    if (Logging.readingFromSnapshot()) {");
      PW.println("      Logging.readSnapshot();");
      PW.println("      if (SysVars._skipBoundary) {");
      PW.println("        long tsStart = SysVars._firstExecTime;");
      PW.println("        for (double d=SysVars.startTime; d<=tsStart; d+=SysVars.stepInMillis) {");
      PW.println("          cds = (BoundaryDataStep) boundaryData.readObject();");
      PW.println("          SysVars.readBoundaryStep(cds);");
      PW.println("          Kernel.myTime+=SysVars.stepInMillis;");
      PW.println("          Kernel.timeSteps++;");
      PW.println("        }");
      PW.println("        System.out.println(\"Jumpstart T = \"+Kernel.timeSteps*SysVars.stepInHours+\" - reading data...\");");
      PW.println("        runtil.setText(String.valueOf(Kernel.timeSteps*SysVars.stepInHours));");
      PW.println("      }");
      PW.println("    }");
      for (int i=0; i<species.length; i++)
        PW.println("    speciesName.addItem(new String(\""+species[i].getAttribute("name")+"\"));");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void endSim() throws Exception {");
      PW.println("    Logging.close();");
      PW.println("    Kernel.clear();");
      PW.println("    System.gc();");
      PW.println("    if (Kernel.P!=null) Kernel.P.close();");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void updateIDs() {");
      PW.println("    lockEvents++;");
      PW.println("    final ArrayList V = new ArrayList();");
      PW.println("    int species = speciesName.getSelectedIndex();");
      PW.println("    if (species>=0) {");
      PW.println("      for (int i=0; i<Kernel.W.agents[species].length; i++) {");
      PW.println("        if (stages[speciesToFg[species]][i].isSelected()) {");
      PW.println("          final int agentCount=Kernel.W.agents[species][i].size();");
      PW.println("          for (int j=0; j<agentCount; j++)");
      PW.println("            V.add(Kernel.W.agents[species][i].get(j));");
      PW.println("        }");
      PW.println("      }");
      PW.println("      if (idNo.getSelectedIndex()!=-1) logID=Integer.parseInt(idNo.getSelectedItem().toString());");
      PW.println("      idNo.removeAllItems();");
      PW.println("      int keepIndex=-1;");
      PW.println("      final int varNo = sortBy.getSelectedIndex();");
      PW.println("      if (varNo==-1) {");
      PW.println("        for (int i=0; i<V.size(); i++) {");
      PW.println("          FunctionalGroup fg = (FunctionalGroup) V.get(i);");
      PW.println("          if (fg.id==logID) keepIndex=i;");
      PW.println("          idNo.addItem(String.valueOf(fg.id));");
      PW.println("        }");
      PW.println("        if (keepIndex>=0) idNo.setSelectedIndex(keepIndex);");
      PW.println("      } else {");
      PW.println("        double maxValue=0;");
      PW.println("        double value=0;");
      PW.println("        int maxIndex=0;");
      PW.println("        while (V.size()>0) {");
      PW.println("          for (int j=0; j<V.size(); j++) {");
      PW.println("            final FunctionalGroup fg = (FunctionalGroup) V.get(j);");
      for (int i=0; i<fgs.length; i++) {
        final String cast = "(("+StringTools.nonSpace(fgs[i].getValue("name"))+")fg)";
        PW.print("            ");
        if (i>0) PW.print("} else ");
        int countFGSpecies=0;
        int gotSpecies=0;
        for (int j=0; j<species.length; j++) {
          if (species[j].getAttribute("fg").equals(fgs[i].getValue("name"))) {
            countFGSpecies++;
            gotSpecies=j;
          }
        }
        if (countFGSpecies==1) {
          PW.println("if (species=="+gotSpecies+") {");
        } else {
          PW.print("if (");
          gotSpecies=0;
          for (int j=0; j<countFGSpecies; j++) {
            if (species[j].getAttribute("fg").equals(fgs[i].getValue("name"))) {
              PW.print("(species=="+j+")");
              gotSpecies++;
              if (gotSpecies<countFGSpecies) PW.print(" || ");
            }
          }
          PW.println(") {");
        }
        PW.println("              if (varNo==0) value=fg.c[1];");
        PW.println("              else if (varNo==1) value=fg.z[1];");
        final boolean[] chemPoolsInUse = BlackBox.getChemPoolUsage(fgs[i],model);
        for (int c=0; c<chemPoolsInUse.length; c++) {
          if (chemPoolsInUse[c])
            PW.println("              else if (varNo=="+(c+2)+") value="+cast+"._"+model.getTags("chemical")[c].getValue("name")+"_Pool_Old;");
        }
        XMLTag[] vars = fgs[i].getTags("variable");
        vars = XMLTag.sortTags(vars,"name");
        for (int j=0; j<vars.length; j++) {
          PW.println("              else if (varNo=="+(j+chemPoolsInUse.length+2)+") value="+cast+"."+vars[j].getValue("name")+"[1];");
        }
        if (i==fgs.length-1) PW.println("            }");
      }
      PW.println("            if ((value>maxValue) || (j==0)) {");
      PW.println("              maxValue=value;");
      PW.println("              maxIndex=j;");
      PW.println("            }");
      PW.println("          }");
      PW.println("          idNo.addItem(String.valueOf(((FunctionalGroup)V.get(maxIndex)).id));");
      PW.println("          V.remove(maxIndex);");
      PW.println("          if (maxIndex==logID) keepIndex=maxIndex;");
      PW.println("        }");
      PW.println("        idNo.setSelectedIndex(keepIndex);");
      PW.println("      }");
      PW.println("    }");
      PW.println("    lockEvents--;");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void doTimeStep() {");
      PW.println("    SysVars.handleDistributions();");
      PW.println("    SysVars._gmtDay = SysVars.getDayOfYear(Kernel.myTime);");
      PW.println("    SysVars._gmtLeap = SysVars.getLeapFlag(Kernel.myTime);");
      PW.println("    try {");
      PW.println("      cds = (BoundaryDataStep) boundaryData.readObject();");
      PW.println("      SysVars.readBoundaryStep(cds);");
      PW.println("    } catch (Exception e) { e.printStackTrace(); }");
      PW.println("");
      PW.println("    SysVars.doEvents();");
      PW.println("    Kernel.W.updatePhysics();");
      PW.println("    Kernel.W.updateChemistry();");
      PW.println("    Kernel.W.updateBiology();");
      PW.println("    Kernel.W.agentManagement();");
      PW.println("    Kernel.W.handleDepletion();");
      PW.println("    Kernel.DTime += SysVars.stepInHours;");
      PW.println("    if (Kernel.DTime > 23.999) {");
      PW.println("      Kernel.DTime = 0;");
      PW.println("      Kernel.W.Max_MLD=0.0;");
      PW.println("      Kernel.W.Min_MLD=Kernel.W.MLDepth;");
      PW.println("      Kernel.W.YDay++;");
      PW.println("      if (Kernel.W.YDay == 365) {");
      PW.println("        Kernel.W.YDay=0;");
      PW.println("        Kernel.W.NInj_Count++;");
      PW.println("        if (Kernel.W.NInj_Count == Kernel.W.Nitro_Inj) {");
      PW.println("          Kernel.W.NInj_Count = 0;");
      PW.println("        }");
      PW.println("      }");
      PW.println("    }");
      PW.println("    Logging.checkWriteSnap();");
      PW.println("    Kernel.timeSteps++;");
      PW.println("");
      PW.println("    Kernel.myTime+=SysVars.stepInMillis;");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void changeFG() {");
      PW.println("    if (speciesName.getSelectedIndex()>=0) {");
      PW.println("      final int speciesIndex = speciesName.getSelectedIndex();");
      PW.println("      locTable.showSpecies(speciesIndex);");
      PW.println("      stateTable.showSpecies(speciesIndex);");      
      PW.println("      resize();");
      PW.println("      mainDebug.paint(mainDebug.getGraphics());");
      PW.println("");
      PW.println("      int remember = sortBy.getSelectedIndex();");
      PW.println("      sortBy.removeAllItems();");
      for (int i=0; i<fgs.length; i++) {
        PW.print("      ");
        if (i>0) PW.print("} else ");
        int countFGSpecies=0;
        int gotSpecies=0;
        for (int j=0; j<species.length; j++) {
          if (species[j].getAttribute("fg").equals(fgs[i].getValue("name"))) {
            countFGSpecies++;
            gotSpecies=j;
          }
        }
        if (countFGSpecies==1) {
          PW.println("if (speciesIndex=="+gotSpecies+") {");
        } else {
          PW.print("if (");
          gotSpecies=0;
          for (int j=0; j<countFGSpecies; j++) {
            if (species[j].getAttribute("fg").equals(fgs[i].getValue("name"))) {
              PW.print("(speciesIndex=="+j+")");
              gotSpecies++;
              if (gotSpecies<countFGSpecies) PW.print(" || ");
            }
          }
          PW.println(") {");
        }
        PW.println("        sortBy.addItem(new String(\"__Agent Sub-Pop\"));");
        PW.println("        sortBy.addItem(new String(\"__Depth\"));");
        final boolean[] chemPoolsInUse = BlackBox.getChemPoolUsage(fgs[i],model);
        for (int c=0; c<chemPoolsInUse.length; c++) {
          if (chemPoolsInUse[c])
            PW.println("        sortBy.addItem(new String(\"_"+model.getTags("chemical")[c].getValue("name")+"_Pool\"));");
        }

        
        XMLTag[] vars = fgs[i].getTags("variable");
        vars = XMLTag.sortTags(vars,"name");
        for (int j=0; j<vars.length; j++) {
          PW.println("        sortBy.addItem(new String(\""+vars[j].getValue("name")+"\"));");
        }
        if (i==fgs.length-1) PW.println("    }");
      }
      PW.println("      sortBy.setSelectedIndex(remember);");
      PW.println("      updateStateList();");
      PW.println("      updateIDs();");
      PW.println("      getDetails(false);");
      PW.println("      locTable.clearAll();");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void Launch() throws Exception {");
      
      
      int max = 0;
      for (int i=0; i<fgs.length; i++) max = Math.max(max,fgs[i].getTags("stage").length);
     
      PW.println("    stages = new JCheckBox["+fgs.length+"]["+max+"];");
      for (int i=0; i<fgs.length; i++) {
        XMLTag[] states = fgs[i].getTags("stage");
        for (int j=0; j<states.length; j++) {
          PW.println("    stages["+i+"]["+j+"] = new JCheckBox(\""+states[j].getValue("name")+"\");");
          PW.println("    stages["+i+"]["+j+"].setSelected(true);");          
        }
      }
   
      PW.println("    lockEvents++;;");
      PW.println("    startSim();");
      PW.println("    LiveSim.getInstance();");
      PW.println("    changeFG();");
      PW.println("    updateIDs();");
      PW.println("    lockEvents--;");
      PW.println(" }");
      PW.println("");
      PW.println("  public static void resize() {");
      PW.println("    int width = Math.max(0,mainFrame.getWidth()-20);");
      PW.println("    int height = Math.max(0,mainFrame.getHeight()-300);");
      PW.println("    envTable.getColumnModel().getColumn(0).setPreferredWidth(width/3);");
      PW.println("    envTable.getColumnModel().getColumn(1).setPreferredWidth(width/3);");
      PW.println("    envTable.getColumnModel().getColumn(2).setPreferredWidth(width/3);");
      PW.println("    mesoScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("");
      PW.println("    paramScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("    paramTable.getColumnModel().getColumn(0).setPreferredWidth(width/3);");
      PW.println("    paramTable.getColumnModel().getColumn(1).setPreferredWidth(width/3);");
      PW.println("    paramTable.getColumnModel().getColumn(2).setPreferredWidth(width/3);");
      PW.println("");
      PW.println("    ambBioScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("    ambBioTable.getColumnModel().getColumn(0).setPreferredWidth(width/3);");
      PW.println("    ambBioTable.getColumnModel().getColumn(1).setPreferredWidth(width/3);");
      PW.println("    ambBioTable.getColumnModel().getColumn(2).setPreferredWidth(width/3);");
      PW.println("");      
      PW.println("    ambChemScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("    ambChemTable.getColumnModel().getColumn(0).setPreferredWidth(width/3);");
      PW.println("    ambChemTable.getColumnModel().getColumn(1).setPreferredWidth(width/3);");
      PW.println("    ambChemTable.getColumnModel().getColumn(2).setPreferredWidth(width/3);");
      PW.println("");      
      PW.println("    ambPhyScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("    ambPhyTable.getColumnModel().getColumn(0).setPreferredWidth(width/3);");
      PW.println("    ambPhyTable.getColumnModel().getColumn(1).setPreferredWidth(width/3);");
      PW.println("    ambPhyTable.getColumnModel().getColumn(2).setPreferredWidth(width/3);");
      PW.println("");
      PW.println("    intPoolScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("    intPoolTable.getColumnModel().getColumn(0).setPreferredWidth(width/3);");
      PW.println("    intPoolTable.getColumnModel().getColumn(1).setPreferredWidth(width/3);");
      PW.println("    intPoolTable.getColumnModel().getColumn(2).setPreferredWidth(width/3);");
      PW.println("");
      PW.println("    locScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("    locTable.getColumnModel().getColumn(0).setPreferredWidth(width/2);");
      PW.println("    locTable.getColumnModel().getColumn(1).setPreferredWidth(width/2);");
      PW.println("");
      PW.println("    stateScroller.setPreferredSize(new Dimension(width,height));");
      PW.println("    stateTable.getColumnModel().getColumn(0).setPreferredWidth(width/3);");
      PW.println("    stateTable.getColumnModel().getColumn(1).setPreferredWidth(width/3);");
      PW.println("    stateTable.getColumnModel().getColumn(2).setPreferredWidth(width/3);");
      PW.println("  }");
      PW.println("");
      PW.println("  public static void getDetails(boolean sort) {");
      PW.println("    lockEvents++;");
      PW.println("    if (idNo.getSelectedItem()!=null) {");
      PW.println("      logID = Long.parseLong(idNo.getSelectedItem().toString());");
      PW.println("      if (sort) updateIDs();");
      
      PW.println("      for (int i=0; i<"+speciesCount+"; i++) {");
      PW.println("        final int stageCount = Kernel.W.agents[i].length;");
      PW.println("        for (int j=0; j<stageCount; j++) {");
      PW.println("          final int agentCount = Kernel.W.agents[i][j].size();");
      PW.println("          for (int k=0; k<agentCount; k++) {");
      PW.println("            final FunctionalGroup fg = (FunctionalGroup)(Kernel.W.agents[i][j].get(k));");
      PW.println("            if (fg.id==logID) {");
      PW.println("              k=agentCount;");
      PW.println("              j=Kernel.W.agents[i].length;");
      PW.println("              i="+speciesCount+";");
      PW.println("              fg.updateLiveSim(true);");
      PW.println("            }");
      PW.println("          }");
      PW.println("        }");
      PW.println("      }");
      PW.println("    }");
      PW.println("    lockEvents--;");
      PW.println("  }");
      PW.println("");
      PW.println("  class EventHandler implements ActionListener, ItemListener, ComponentListener {");
      PW.println("");
      PW.println("    public EventHandler() { }");
      PW.println("");
      PW.println("    public void itemStateChanged(ItemEvent e) {");
      PW.println("      if (lockEvents==0) {");
      PW.println("        if (e.getSource()==speciesName) {");
      PW.println("          lockEvents++;");
      PW.println("          changeFG();");
      PW.println("          lockEvents--;");
      PW.println("        } else if ((e.getSource()==idNo) || (e.getSource()==sortBy))  {");
      PW.println("          getDetails(e.getSource()==sortBy);");
      PW.println("          locTable.clearAll();");
      PW.println("        }");
      PW.println("      }");
      PW.println("    }");
      PW.println("");
      PW.println("    public void actionPerformed(ActionEvent e) {");
      PW.println("      if (e.getSource()==gotoParticle) {");
      PW.println("        String NewID = javax.swing.JOptionPane.showInputDialog(\"Which particle do you want to see?\", emptyString);");
      PW.println("        if (NewID!=null) {");
      PW.println("          NewID=NewID.trim();");
      PW.println("          int lmn = idNo.getSelectedIndex();");
      PW.println("          idNo.setSelectedItem(NewID);");
      PW.println("          if (idNo.getSelectedItem()!=null)");
      PW.println("            logID=Long.parseLong(idNo.getSelectedItem().toString());");
      PW.println("          if (lmn == idNo.getSelectedIndex()) {");
      PW.println("            javax.swing.JOptionPane.showMessageDialog(null, \"Sorry that particle could not be found.\");");
      PW.println("          }");
      PW.println("        }");
      PW.println("      } else if (e.getSource()==stateList) {");
      PW.println("        if (lockEvents==0) {");
      PW.println("          lockEvents++;");
      PW.println("          /* This event happens before the checkbox value is updated, so you have to artificially flip it.");
      PW.println("           Nasty hack... */");
      PW.println("          int speciesIndex = speciesName.getSelectedIndex();");
      PW.println("          if ((stateList.getSelectedIndex()>-1) && (speciesIndex>-1)) {");
      PW.println("            boolean state = stages[speciesToFg[speciesIndex]][stateList.getSelectedIndex()].isSelected();");
      PW.println("            stages[speciesToFg[speciesIndex]][stateList.getSelectedIndex()].setSelected(!state);");
      PW.println("            updateIDs();");
      PW.println("            stages[speciesToFg[speciesIndex]][stateList.getSelectedIndex()].setSelected(state);");
      PW.println("          }");
      PW.println("          lockEvents--;");
      PW.println("        }");
      PW.println("      } else if (e.getSource()==showDG) {");
      PW.println("        dg.setVisible(true);");
      PW.println("      } else if (e.getSource()==exitButton) {");
      PW.println("        try { endSim(); }");
      PW.println("        catch(Exception exc) { exc.printStackTrace(); }");
      PW.println("        System.exit(0);");
      PW.println("      } else if (e.getSource()==runToHour) {");
      PW.println("        lockEvents++;");
      PW.println("        fgUpdated = false;");
      PW.println("        double d = Double.parseDouble(runtil.getText());");
      PW.println("        while (Kernel.timeSteps*SysVars.stepInHours<=d) {");
      PW.println("          doTimeStep();");
      PW.println("          updateValues();");
      PW.println("          dg.mainPanel.paint(dg.mainPanel.getGraphics());");
      PW.println("          if (speciesName.getSelectedIndex()>=0) {");
      PW.println("            if (!fgUpdated) {");
      PW.println("              idNo.setSelectedIndex(-1);");
      PW.println("            }");
      PW.println("          }");
      PW.println("        }");
      PW.println("        updateIDs();");
      PW.println("        d+=SysVars.stepInHours;");
      PW.println("        runtil.setText(String.valueOf(d));");
      PW.println("        lockEvents--;");
      PW.println("      }");
      PW.println("    }");
      PW.println("");
      PW.println("    public void componentHidden(ComponentEvent e) {}");
      PW.println("    public void componentMoved(ComponentEvent e) {}");
      PW.println("");
      PW.println("    public void componentResized(ComponentEvent e) {");
      PW.println("      resize();");
      PW.println("    }");
      PW.println("");
      PW.println("    public void componentShown(ComponentEvent e) {}");
      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.println("class EnvironmentTable extends JTable {");
      PW.println("  public static final int EKMAN = 2;");
      PW.println("  public static final int HEAT_LOSS = 3;");
      PW.println("  public static final int SUNLIGHT = 4;");
      PW.println("  public static final int TURBOCLINE = 5;");
      PW.println("  public static final int WIND_SPEED = 6;");
      PW.println("  public static final int ZENITH_HEIGHT = 7;");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("  private static final int[] rowChems = new int["+chems.length+"];");
      PW.println("  private static int rowTotalChem;");      
      PW.println("");
      PW.println("  public EnvironmentTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel dtm = (DefaultTableModel) getModel();");
      PW.println("    dtm.setColumnCount(3);");
      PW.println("    dtm.setColumnIdentifiers(new String[] {\"Name\",\"Value\",\"Value\"});");
      PW.println("    dtm.addRow(new String[] {emptyString,\"<html><strong>MESOCOSM PHYSICS</strong></html>\",emptyString});");
      PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Ekman\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Heat Loss\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Sunlight\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Turbocline\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Wind Speed\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Zenith Height\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {emptyString,\"<html><strong>MESOCOSM BIOLOGY</strong></html>\",emptyString});");
      PW.println("    dtm.addRow(new String[] {emptyString,\"Value\",\"Agents\"});");      
      PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
      int rowCount=12;
      for (int i=0; i<species.length; i++) {
        String fg = species[i].getAttribute("fg");
        final XMLTag fgTag = model.getTagWhere("functionalgroup","name",fg);
        XMLTag[] states = fgTag.getTags("stage");
        for (int j=0; j<states.length; j++) {
          PW.println("    dtm.addRow(new String[] {\""+species[i].getAttribute("name")+": "+states[j].getValue("name")+"\",emptyString,emptyString});");
          rowCount++;
        }
      }
      PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {emptyString,\"<html><strong>MESOCOSM CHEMISTRY</strong></html>\",emptyString});");
      PW.println("    dtm.addRow(new String[] {emptyString,\"Solution\",\"Particulate\"});");
      PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
      rowCount+=4;
      PW.println("    rowTotalChem="+rowCount+";");
     
      for (int i=0; i<chems.length; i++) {
        PW.println("    dtm.addRow(new String[] {\""+chems[i].getValue("name")+"\",emptyString,emptyString});");
        rowCount++;
      }
      for (int i=0; i<chems.length; i++) {
        PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
        PW.println("    dtm.addRow(new String[] {emptyString,\"<html><strong>Total "+chems[i].getValue("name")+"</strong></html>\",emptyString});");
        PW.println("    dtm.addRow(new String[] {\"Species : Stage\",\"Internal Pool\",\"Ingested Amount\"});");
        PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
        rowCount+=4;
        PW.println("    rowChems["+i+"]="+rowCount+";");
        XMLTag[] specs = model.getTag("species").getTags("species");
        for (int j=0; j<specs.length; j++) {
          XMLTag fg = model.getTagWhere("functionalgroup","name",specs[j].getAttribute("fg"));
          XMLTag[] stages = fg.getTags("stage");
          for (int k=0; k<stages.length; k++) {
            PW.println("    dtm.addRow(new String[] {\""+specs[j].getAttribute("name")+" : "+stages[k].getValue("name")+"\",emptyString,emptyString});");
            rowCount++;
          }
        }
      }  
      
      
      PW.println("  }");
      PW.println("");
      PW.println("  public void set(int row, double d) { setValueAt(String.valueOf(d),row,1); }");
      PW.println("  public void setTotalBio(int fg, int stage, double val, double agents) {");
      PW.println("    int row = 12;");
      PW.println("    if (fg>0) for (int i=0; i<fg; i++) row+=SysVars.stageCounts[i];");
      PW.println("    setValueAt(String.valueOf(val),row+stage,1);");
      PW.println("    setValueAt(String.valueOf((int)agents),row+stage,2);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setMesoChem(int chem, double conc, double partic) {");
      PW.println("    setValueAt(String.valueOf(conc),rowTotalChem+chem,1);");
      PW.println("    setValueAt(String.valueOf(partic),rowTotalChem+chem,2);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setMesoChem(int chem, int species, int stage, double pool, double ing) {");
      PW.println("    int row=rowChems[chem];");
      PW.println("    if (species>0) for (int i=0; i<species; i++) row+=SysVars.stageCounts[i];");
      PW.println("    setValueAt(String.valueOf(pool),row+stage,1);");
      PW.println("    setValueAt(String.valueOf(ing),row+stage,2);");
      PW.println("  }");
      PW.println("");
      PW.println("}");
      PW.println("");
      PW.println("class ParameterTable extends JTable {");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("  public ParameterTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel dtm = (DefaultTableModel) getModel();");
      PW.println("    dtm.setColumnCount(3);");
      PW.println("    dtm.setColumnIdentifiers(new String[] {\"Name\",\"Description\",\"Value\"});");
      for (int i=0; i<species.length; i++) {
        PW.println("    dtm.addRow(new String[] {emptyString,\"<html><strong>"+species[i].getAttribute("name").toUpperCase()+" PARAMETERS</strong></html>\",emptyString});");
        PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
        XMLTag[] params = species[i].getTags("param");
        params = XMLTag.sortTags(params,"@name");
        final double x = Double.parseDouble(species[i].getAttribute("x"));
        final XMLTag fg = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg"));
        for (int j=0; j<params.length; j++) {
          final double a = Double.parseDouble(params[j].getAttribute("a"));
          final double b = Double.parseDouble(params[j].getAttribute("b"));
          final String name = params[j].getAttribute("name");
          PW.println("    dtm.addRow(new String[] {\""+name+"\",\""+fg.getTagWhere("parameter", "name", name).getValue("desc")+"\",\""+String.valueOf(a*Math.pow(x,b))+"\"});");
        }
        XMLTag[] vconcs = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("varietyconcentration");
        boolean lineDone=false;
        for (int j=0; j<vconcs.length; j++) {
          if (!vconcs[j].getValue("name").equals("Ingestion")) {
            lineDone=true;
            XMLTag foodset = model.getTag("foodsets").getTagWhere("foodset","@name",species[i].getAttribute("name")+" : "+vconcs[j].getValue("name"));
            XMLTag[] foods = foodset.getTags("food");
            if (foods.length>0) {
              PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");              
              int paramCount = foods[0].getTags("param").length;
              for (int k=0; k<paramCount; k++) {
                for (int m=0; m<foods.length; m++) {
                  XMLTag param = foods[m].getTags("param")[k];
                  PW.print("    dtm.addRow(new String[] {\""+param.getAttribute("name")+" for "+foods[m].getAttribute("species")+" : "+foods[m].getAttribute("stage")+"\",");
                  double a = Double.parseDouble(param.getAttribute("a"));
                  double b = Double.parseDouble(param.getAttribute("b"));
                  if (b==0) PW.println("\""+String.valueOf(a)+"\"});");
                  else PW.println("\""+String.valueOf(a*Math.pow(x,b))+"\"});");
                }
                PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});"); 
              }
              
              
            }
          }
        }
        if (!lineDone) PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});"); 
      }
      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.println("class AmbientBioTable extends JTable {");
      PW.println("  private static final String emptyString = \"\";");
      
      PW.println("");
      PW.println("  public AmbientBioTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel amb = (DefaultTableModel) getModel();");
      PW.println("    amb.setColumnCount(3);");
      PW.println("    amb.setColumnIdentifiers(new String[] {\"Name\",\"Value (1)\",\"Value (2)\"});");
      PW.println("    amb.addRow(new String[] {emptyString,\"<html><strong>AMBIENT BIOLOGY</strong></html>\",emptyString});");
      PW.println("    amb.addRow(new String[] {emptyString,\"Concentration\",\"Agents\"});");
      PW.println("    amb.addRow(new String[] {emptyString,emptyString,emptyString});");
      for (int i=0; i<species.length; i++) {
        String fg = species[i].getAttribute("fg");
        final XMLTag fgTag = model.getTagWhere("functionalgroup","name",fg);
        XMLTag[] states = fgTag.getTags("stage");
        for (int j=0; j<states.length; j++) {
          PW.println("    amb.addRow(new String[] {\""+species[i].getAttribute("name")+": "+states[j].getValue("name")+"\",emptyString,emptyString});");
        }
      }
      PW.println("  }");
      PW.println("");
      PW.println("  public void setAmbientBio(int spec, int stage, double val, double agents) {");
      PW.println("    int row = 3;");
      PW.println("    if (spec>0) for (int i=0; i<spec; i++) row+=SysVars.stageCounts[i];");
      PW.println("    setValueAt(String.valueOf(val),row+stage,1);");
      PW.println("    setValueAt(String.valueOf((int)agents),row+stage,2);");
      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.println("class AmbientChemTable extends JTable {");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("  private static final int[] rowChems = new int["+chems.length+"];");
      PW.println("  public AmbientChemTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel amb = (DefaultTableModel) getModel();");
      rowCount=3;
      PW.println("    amb.setColumnCount(3);");
      PW.println("    amb.setColumnIdentifiers(new String[] {\"Name\",\"Value (1)\",\"Value (2)\"});");
      PW.println("    amb.addRow(new String[] {emptyString,\"<html><strong>AMBIENT CHEMISTRY</strong></html>\",emptyString});");
      PW.println("    amb.addRow(new String[] {\"Chemical\",\"Solution\",\"Particulate\"});");
      PW.println("    amb.addRow(new String[] {emptyString,emptyString,emptyString});");
      for (int i=0; i<chems.length; i++) {
        PW.println("    amb.addRow(new String[] {\""+chems[i].getValue("name")+"\",emptyString,emptyString});");
        rowCount++;
      }
      for (int i=0; i<chems.length; i++) {
        PW.println("    amb.addRow(new String[] {emptyString,emptyString,emptyString});");
        PW.println("    amb.addRow(new String[] {emptyString,\"<html><strong>AMBIENT "+chems[i].getValue("name")+"</strong></html>\",emptyString});");
        PW.println("    amb.addRow(new String[] {\"Species : Stage\",\"Internal Pool\",\"Ingested Amount\"});");
        PW.println("    amb.addRow(new String[] {emptyString,emptyString,emptyString});");
        rowCount+=4;
        PW.println("    rowChems["+i+"]="+rowCount+";");
        XMLTag[] specs = model.getTag("species").getTags("species");
        for (int j=0; j<specs.length; j++) {
          XMLTag fg = model.getTagWhere("functionalgroup","name",specs[j].getAttribute("fg"));
          XMLTag[] stages = fg.getTags("stage");
          for (int k=0; k<stages.length; k++) {
            PW.println("    amb.addRow(new String[] {\""+specs[j].getAttribute("name")+" : "+stages[k].getValue("name")+"\",emptyString,emptyString});");
            rowCount++;
          }
        }
      }      
      PW.println("  }");
      PW.println("");
      PW.println("  public void setAmbientChem(int chem, double conc, double partic) {");
      PW.println("    setValueAt(String.valueOf(conc),3+chem,1);");
      PW.println("    setValueAt(String.valueOf(partic),3+chem,2);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setAmbientChem(int chem, int species, int stage, double pool, double ing) {");
      PW.println("    int row=rowChems[chem];");
      PW.println("    if (species>0) for (int i=0; i<species; i++) row+=SysVars.stageCounts[i];");
      PW.println("    setValueAt(String.valueOf(pool),row+stage,1);");
      PW.println("    setValueAt(String.valueOf(ing),row+stage,2);");
      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.println("class InternalPoolTable extends JTable {");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("  public InternalPoolTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel inc = (DefaultTableModel) getModel();");
      PW.println("    inc.setColumnCount(3);");
      PW.println("    inc.setColumnIdentifiers(new String[] {\"Chemical\",\"New Value\",\"Previous Value\"});");
      PW.println("    inc.addRow(new String[] {emptyString,\"<html><strong>INTERNAL POOL</strong></html>\",emptyString});");
      PW.println("    inc.addRow(new String[] {emptyString,emptyString,emptyString});");
      int ingStart=2;
      for (int i=0; i<chems.length; i++) {
        PW.println("    inc.addRow(new String[] {\""+chems[i].getValue("name")+"\",emptyString,emptyString});");
        ingStart++;
      }
      PW.println("    inc.addRow(new String[] {emptyString,emptyString,emptyString});");
      PW.println("    inc.addRow(new String[] {emptyString,\"<html><strong>INGESTED AMOUNT</strong></html>\",emptyString});");
      PW.println("    inc.addRow(new String[] {emptyString,emptyString,emptyString});");
      ingStart+=3;
      int bioStart=ingStart;
      for (int i=0; i<chems.length; i++) {
        PW.println("    inc.addRow(new String[] {\""+chems[i].getValue("name")+"\",emptyString,emptyString});");
        bioStart++;
      }
      PW.println("    inc.addRow(new String[] {emptyString,emptyString,emptyString});");
      PW.println("    inc.addRow(new String[] {emptyString,\"<html><strong>INGESTED PLANKTON</strong></html>\",emptyString});");
      PW.println("    inc.addRow(new String[] {emptyString,emptyString,emptyString});");
      bioStart+=3;
      for (int i=0; i<species.length; i++) {
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
        for (int j=0; j<stages.length; j++) {
          PW.println("    inc.addRow(new String[] {\""+species[i].getAttribute("name")+" : "+stages[j].getValue("name")+"\",emptyString,emptyString});");
        }
      }

      PW.println("  }");
      PW.println("");
      PW.println("  public void setChemPool(int chem, double pool_new, double pool_old) {");
      PW.println("    setValueAt(String.valueOf(pool_new),chem+2,1);");
      PW.println("    setValueAt(String.valueOf(pool_old),chem+2,2);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setChemIng(int chem, double ing) {");
      PW.println("    setValueAt(String.valueOf(ing),chem+"+ingStart+",1);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setBioIng(int species, int stage, double val) {");
      PW.println("    int row = "+bioStart+";");
      PW.println("    if (species>0) for (int i=0; i<species; i++) row+=SysVars.stageCounts[i];");
      PW.println("    setValueAt(String.valueOf(val),row+stage,1);");
      PW.println("  }");
      
      PW.println("}");
      PW.println("");
      PW.println("class AmbientPhysicsTable extends JTable {");
      PW.println("  public static final int DENSITY = 0;");
      PW.println("  public static final int SALINITY = 1;");
      PW.println("  public static final int TEMPERATURE = 2;");
      PW.println("  public static final int FULL_IRRAD = 3;");
      PW.println("  public static final int VIS_IRRAD = 4;");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("");
      PW.println("  public AmbientPhysicsTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel dtm = (DefaultTableModel) getModel();");
      PW.println("    dtm.setColumnCount(2);");
      PW.println("    dtm.setColumnIdentifiers(new String[] {\"Name\",\"Value\",\"Value\"});");
      PW.println("    dtm.addRow(new String[] {\"Density\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Salinity\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Temperature\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Full Irradiance\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Visible Irradiance\",emptyString,emptyString});");
      PW.println("  }");
      PW.println("");
      PW.println("  public void set(int type, double val) {");
      PW.println("    setValueAt(String.valueOf(val),type,1);");
      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.println("class LocTable extends JTable {");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("  private static final int[] startFoodBased = new int["+species.length+"];");
      PW.println("");
      PW.println("  public LocTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel dtm = (DefaultTableModel) getModel();");
      PW.println("    dtm.setColumnCount(2);");
      PW.println("    dtm.setColumnIdentifiers(new String[] {\"Name\",\"Value\"});");
      PW.println("  }");
      PW.println("");
      PW.println("  public void showSpecies(int spec) {");
      PW.println("    final int remember = getSelectedRow();");
      PW.println("    DefaultTableModel dtm = (DefaultTableModel) getModel();");
      PW.println("    while (dtm.getRowCount()>0) dtm.removeRow(0);");
      for (int i=0; i<species.length; i++) {
        PW.print("    ");
        if (i>0) PW.print("} else ");
        PW.println("if (spec=="+i+") {");
        int row=0;
        XMLTag[] locals = XMLTag.sortTags(model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("local"),"name");
        for (int j=0; j<locals.length; j++) {
          PW.println("      dtm.addRow(new String[] {\""+locals[j].getValue("name")+"\",emptyString});");
          row++;
        }
        XMLTag[] vvs = XMLTag.sortTags(model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("varietylocal"),"name");
        int countVVs=0;
        for (int j=0; j<vvs.length; j++) {
          if (!vvs[j].getAttribute("link").equals("Ingestion")) countVVs++;
        }
        if (countVVs>0) {
          PW.println("      dtm.addRow(new String[] {emptyString,emptyString});");
          PW.println("      dtm.addRow(new String[] {\"<html><strong>FOOD-BASED LOCALS</strong></html>\",emptyString});");
          PW.println("      dtm.addRow(new String[] {emptyString,emptyString});");
          row+=3;
          PW.println("      startFoodBased["+i+"]="+row+";");
          for (int j=0; j<vvs.length; j++) {
            String link = vvs[j].getAttribute("link");
            if (!link.equals("Ingestion")) {
              String setName = species[i].getAttribute("name")+" : "+link;
              XMLTag[] foods = model.getTag("foodsets").getTagWhere("foodset","@name",setName).getTags("food");
              for (int k=0; k<foods.length; k++) {
                PW.println("      dtm.addRow(new String[] {\""+vvs[j].getValue("name")+" for "+foods[k].getAttribute("species")+" : "+foods[k].getAttribute("stage")+"\",emptyString});");
              }
            }
          }
        } else PW.println("      startFoodBased["+i+"]=-1;");
        if (i==species.length-1) PW.println("    }");
      }

      PW.println("    if (remember<dtm.getRowCount()) getSelectionModel().setSelectionInterval(remember,remember);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void clearAll() {");
      PW.println("    for (int i=0; i<getRowCount(); i++) setValueAt(emptyString,i,1);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void set(int type, double val) {");
      PW.println("    setValueAt(String.valueOf(val),type,1);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setFoodBased(int species, int type, double val) {");
      PW.println("    setValueAt(String.valueOf(val),type+startFoodBased[species],1);");
      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.println("class StateTable extends JTable {");
      PW.println("  private static final String emptyString = \"\";");
      PW.println("  private static final int[] startFoodBased = new int["+species.length+"];");
      PW.println("");
      PW.println("  public StateTable() {");
      PW.println("    new JTable(new DefaultTableModel());");
      PW.println("    DefaultTableModel dtm = (DefaultTableModel) getModel();");
      PW.println("    dtm.setColumnCount(3);");
      PW.println("    dtm.setColumnIdentifiers(new String[] {\"Name\",\"New Value\",\"Previous Value\"});");
      PW.println("  }");
      PW.println("");
      PW.println("  public void showSpecies(int spec) {");
      PW.println("    final int remember = getSelectedRow();");
      PW.println("    DefaultTableModel dtm = (DefaultTableModel) getModel();");
      PW.println("    while (dtm.getRowCount()>0) dtm.removeRow(0);");
      PW.println("    dtm.addRow(new String[] {\"Agent id\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Stage\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Sub-population size (c)\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {\"Depth (z)\",emptyString,emptyString});");
      PW.println("    dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
      for (int i=0; i<species.length; i++) {
        int row=5;
        PW.print("    ");
        if (i>0) PW.print("} else ");
        PW.println("if (spec=="+i+") {");
        XMLTag[] states = XMLTag.sortTags(model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("variable"),"name");
        for (int j=0; j<states.length; j++) {
          PW.println("      dtm.addRow(new String[] {\""+states[j].getValue("name")+"\",emptyString});");
          row++;
        }
        XMLTag[] vvs = XMLTag.sortTags(model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("varietyvariable"),"name");
        int countVVs=0;
        for (int j=0; j<vvs.length; j++) {
          if (!vvs[j].getAttribute("link").equals("Ingestion")) countVVs++;
        }
        if (countVVs>0) {
          PW.println("      dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
          PW.println("      dtm.addRow(new String[] {emptyString,\"<html><strong>FOOD-BASED VARIABLES</strong></html>\",emptyString});");
          PW.println("      dtm.addRow(new String[] {emptyString,emptyString,emptyString});");
          row+=3;
          PW.println("      startFoodBased["+i+"]="+row+";");
          for (int j=0; j<vvs.length; j++) {
            String link = vvs[j].getAttribute("link");
            if (!link.equals("Ingestion")) {
              String setName = species[i].getAttribute("name")+" : "+link;
              XMLTag[] foods = model.getTag("foodsets").getTagWhere("foodset","@name",setName).getTags("food");
              for (int k=0; k<foods.length; k++) {
                PW.println("      dtm.addRow(new String[] {\""+vvs[j].getValue("name")+" for "+foods[k].getAttribute("species")+" : "+foods[k].getAttribute("stage")+"\",emptyString,emptyString});");
              }
            }
          }
        } else PW.println("      startFoodBased["+i+"]=-1;");
        if (i==species.length-1) PW.println("    }");
      }

      PW.println("    if (remember<dtm.getRowCount()) getSelectionModel().setSelectionInterval(remember,remember);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setZ(double next, double old) {");
      PW.println("    setValueAt(String.valueOf(next),3,1);");
      PW.println("    setValueAt(String.valueOf(old),3,2);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setStage(String st) {");
      PW.println("    setValueAt(st,1,1);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setC(double next, double old) {");
      PW.println("    setValueAt(String.valueOf(next),2,1);");
      PW.println("    setValueAt(String.valueOf(old),2,2);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setID(long id) {");
      PW.println("    setValueAt(String.valueOf(id),0,1);");
      PW.println("  }");
      PW.println("");
      
      PW.println("  public void set(int type, double next, double old) {");
      PW.println("    setValueAt(String.valueOf(next),type+5,1);");
      PW.println("    setValueAt(String.valueOf(old),type+5,2);");
      PW.println("  }");
      PW.println("");
      PW.println("  public void setFoodBased(int species, int type, double latest, double old) {");
      PW.println("    setValueAt(String.valueOf(latest),type+startFoodBased[species],1);");
      PW.println("    setValueAt(String.valueOf(old),type+startFoodBased[species],2);");
      PW.println("  }");
      PW.println("}");
      PW.println("");
      
      PW.println("class DepthGraph extends JDialog {");
      PW.println("  public GraphPanel mainPanel = new GraphPanel();");
      PW.println("  JCheckBox autoScale = new JCheckBox(\"Auto Scale\");");
      PW.println("  JTextField graphTitle = new JTextField(GraphChooser.NO_GRAPH);");
      PW.println("  JButton chooseGraph = new JButton(\"Choose\");");
      PW.println("  JLabel minLabel = new JLabel(\"Min: \");");
      PW.println("  JLabel maxLabel = new JLabel(\"Max: \");");
      PW.println("  JTextField minValue = new JTextField(\"0.0\");");
      PW.println("  JTextField maxValue = new JTextField(\"0.0\");");
      PW.println("  JLabel topLabel = new JLabel(\"Top: \");");
      PW.println("  JLabel bottomLabel = new JLabel(\"Bottom: \");");
      PW.println("  JTextField topValue = new JTextField(\"0.0\");");
      PW.println("  JTextField bottomValue = new JTextField(\"0.0\");");
      PW.println("  DGEventHandler dgeh = new DGEventHandler();");
      PW.println("  GraphChooser graphChoice;");
      PW.println("");
      PW.println("");
      PW.println("  public DepthGraph(JFrame parent) {");
      PW.println("    super(parent);");
      PW.println("    mainPanel.setDoubleBuffered(true);");
      PW.println("    graphChoice=new GraphChooser(this);");
      PW.println("    JPanel minMaxSelection = new JPanel(new BorderLayout());");
      PW.println("    JPanel minPanel = new JPanel(new FlowLayout());");
      PW.println("      minPanel.add(minLabel);");
      PW.println("      minPanel.add(minValue);");
      PW.println("    JPanel maxPanel = new JPanel(new FlowLayout());");
      PW.println("      maxPanel.add(maxLabel);");
      PW.println("      maxPanel.add(maxValue);");
      PW.println("    JPanel topPanel = new JPanel(new FlowLayout());");
      PW.println("      topPanel.add(topLabel);");
      PW.println("      topPanel.add(topValue);");
      PW.println("    JPanel bottomPanel = new JPanel(new FlowLayout());");
      PW.println("      bottomPanel.add(bottomLabel);");
      PW.println("      bottomPanel.add(bottomValue);");
      PW.println("    JPanel autoPanel = new JPanel(new FlowLayout());");
      PW.println("      autoPanel.add(autoScale);");
      PW.println("    minMaxSelection.add(minPanel,BorderLayout.CENTER);");
      PW.println("    minMaxSelection.add(maxPanel,BorderLayout.SOUTH);");
      PW.println("    minMaxSelection.add(autoPanel,BorderLayout.NORTH);");
      PW.println("    JPanel depthPanel = new JPanel(new BorderLayout());");
      PW.println("      depthPanel.add(topPanel,BorderLayout.CENTER);");
      PW.println("      depthPanel.add(bottomPanel,BorderLayout.SOUTH);");
      PW.println("    JPanel options = new JPanel(new BorderLayout());");
      PW.println("      options.add(minMaxSelection,BorderLayout.WEST);");
      PW.println("      options.add(depthPanel,BorderLayout.EAST);");
      PW.println("    getContentPane().add(options,BorderLayout.SOUTH);");
      PW.println("    mainPanel.setPreferredSize(new Dimension(300,500));");
      PW.println("    getContentPane().add(mainPanel,BorderLayout.CENTER);");
      PW.println("    mainPanel.setBorder(new EtchedBorder());");
      PW.println("    minValue.setPreferredSize(new Dimension(150,20));");
      PW.println("    topValue.addFocusListener(dgeh);");
      PW.println("    bottomValue.addFocusListener(dgeh);");
      PW.println("    maxValue.addFocusListener(dgeh);");
      PW.println("    minValue.addFocusListener(dgeh);");
      PW.println("    topValue.addKeyListener(dgeh);");
      PW.println("    bottomValue.addKeyListener(dgeh);");
      PW.println("    maxValue.addKeyListener(dgeh);");
      PW.println("    minValue.addKeyListener(dgeh);");
      PW.println("    maxValue.setPreferredSize(new Dimension(150,20));");
      PW.println("    topValue.setPreferredSize(new Dimension(80,20));");
      PW.println("    bottomValue.setPreferredSize(new Dimension(80,20));");
      PW.println("    autoScale.setSelected(true);");
      PW.println("    minValue.setEnabled(true);");
      PW.println("    maxValue.setEnabled(true);");
      PW.println("    minValue.setEditable(false);");
      PW.println("    maxValue.setEditable(false);");
      PW.println("    topValue.setText(\"0\");");
      PW.println("    bottomValue.setText(String.valueOf(SysVars._layers));");
      PW.println("    setTitle(\"Depth Graphs\");");
      PW.println("    setSize(300,500);");
      PW.println("    JPanel topTitle = new JPanel(new BorderLayout());");
      PW.println("    topTitle.add(graphTitle,BorderLayout.WEST);");
      PW.println("    graphTitle.setPreferredSize(new Dimension(240,20));");
      PW.println("    graphTitle.setEditable(false);");
      PW.println("    topTitle.add(chooseGraph,BorderLayout.EAST);");
      PW.println("    getContentPane().add(topTitle,BorderLayout.NORTH);");
      PW.println("    getContentPane().addComponentListener(dgeh);");
      PW.println("    chooseGraph.addActionListener(dgeh);");
      PW.println("    autoScale.addActionListener(dgeh);");
      PW.println("    pack();");
      PW.println("  }");
      PW.println("");
      PW.println("  class GraphPanel extends JPanel {");
      PW.println("    private static final int leftMargin = 50;");
      PW.println("    private static final int rightMargin = 20;");
      PW.println("    private static final int topMargin = 20;");
      PW.println("    private static final int bottomMargin = 20;");
      PW.println("    public GraphPanel() { super(); }");
      PW.println("    public void paint(Graphics g) {");
      PW.println("      final String s = graphChoice.getItem(0);");
      PW.println("      final int _width = mainPanel.getWidth();");
      PW.println("      final int _height = mainPanel.getHeight();");
      PW.println("      final int _top = Integer.parseInt(topValue.getText());");
      PW.println("      final int _bottom = Integer.parseInt(bottomValue.getText());");
      PW.println("      final double yscaler = (1.0*(_height-(topMargin+bottomMargin)))/(1.0*(_bottom-_top));");
      PW.println("      if (s!=null) {");
      PW.println("        g.setColor(Color.LIGHT_GRAY);");
      PW.println("        g.fillRect(0,0,_width,_height);");
      PW.println("        g.setColor(Color.WHITE);");
      PW.println("        g.fillRect(leftMargin,topMargin,_width-(leftMargin+rightMargin),_height-(topMargin+bottomMargin));");
      PW.println("        g.setColor(Color.BLACK);");
      PW.println("        g.drawRect(leftMargin-1,topMargin-1,2+(_width-(leftMargin+rightMargin)),2+(_height-(topMargin+bottomMargin)));");
      PW.println("        FontRenderContext frc = ((Graphics2D) g).getFontRenderContext();");
      PW.println("        for (int _i=0; _i<(_height-(topMargin+bottomMargin)); _i+=25) {");
      PW.println("          g.drawLine(leftMargin-1,topMargin+_i,leftMargin-4,topMargin+_i);");
      PW.println("          g.setColor(Color.LIGHT_GRAY);");
      PW.println("          for (int _x=0; _x<_width-(leftMargin+rightMargin); _x+=4)");
      PW.println("            g.drawLine(_x+leftMargin,_i+topMargin,_x+leftMargin+1,_i+topMargin);");
      PW.println("          String dv = String.valueOf(_top+(int)((((1.0f*_i)/(1.0f*_height-(topMargin+bottomMargin)))*(_bottom-_top))));");
      PW.println("          final int textwidth = (int) ((Graphics2D) g).getFont().getStringBounds(dv,frc).getWidth();");
      PW.println("          g.setColor(Color.BLACK);");
      PW.println("          g.drawString(dv,(leftMargin-6)-textwidth,topMargin+_i+6);");
      PW.println("        }");
         
      PW.println("        g.setColor(Color.RED);");
      PW.println("        final int _players = Kernel.W.P_LayerList.size();");
      PW.println("        final int _vars = graphChoice.getItemCount();");
      PW.println("        double max=0;");
      PW.println("        double min=0;");
      PW.println("        double value=0;");
      PW.println("");
      PW.println("        if (autoScale.isSelected()) {");
      PW.println("          boolean firstItem = true;");
      PW.println("          final int firstVar = graphChoice.getItemInt(0);");
      PW.println("          final boolean isPhysical = ((firstVar>=GraphChooser.PHY_DENSITY) && (firstVar<=GraphChooser.PHY_TEMP));");
      PW.println("          if (isPhysical) {");
      PW.println("            for (int i=0; i<_players; i++) {");
      PW.println("              final PLayer _pl = (PLayer) Kernel.W.P_LayerList.get(i);");
      PW.println("              for (int j=0; j<_vars; j++) {");
      PW.println("                final int var = graphChoice.getItemInt(j);");
      PW.println("                value=0;");
      PW.println("                if (var==GraphChooser.PHY_DENSITY) value+=_pl.Density;");
      PW.println("                else if (var==GraphChooser.PHY_FULL_IRRAD) value+=_pl.Full_Irrad;");
      PW.println("                else if (var==GraphChooser.PHY_VIS_IRRAD) value+=_pl.Vis_Irrad;");
      PW.println("                else if (var==GraphChooser.PHY_SALINITY) value+=_pl.Salinity;");
      PW.println("                else if (var==GraphChooser.PHY_TEMP) value+=_pl.Temp;");
      PW.println("              }");
      PW.println("              if (firstItem) {");
      PW.println("                max=value;");
      PW.println("                min=value;");
      PW.println("                firstItem=false;");
      PW.println("              } else {");
      PW.println("                if (value>max) max=value;");
      PW.println("                if (value<min) min=value;");
      PW.println("              }");
      PW.println("            }");
      PW.println("          } else { // Not Physical");
      PW.println("            for (int i=0; i<SysVars._layers; i++) {");
      PW.println("              final BLayer b = Kernel.W.B_Layer[i];");
      PW.println("              value=0;");
      PW.println("              for (int j=0; j<_vars; j++) {");
      PW.println("                final int var = graphChoice.getItemInt(j);");
      PW.println("                if ((var>=GraphChooser.BIO_START) && (var<GraphChooser.BIO_END)) {");
      int typesSoFar=0;
      for (int i=0; i<species.length; i++) {
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
        PW.print("                  ");
        if (i>0) PW.print("else ");
        PW.println("if ((var>="+typesSoFar+") && (var<"+(typesSoFar+stages.length)+"))");
        PW.println("                    value+=b.SpeciesConcentration["+i+"][var-"+typesSoFar+"];");
        typesSoFar+=stages.length;
      }
      PW.println("                } else if ((var>=GraphChooser.CHEM_CONC_START) && (var<GraphChooser.CHEM_CONC_END)) {");
      PW.println("                  value+=b.solution_chem[var-GraphChooser.CHEM_CONC_START];");
      PW.println("                } else if ((var>=GraphChooser.CHEM_ALLBIO_START) && (var<GraphChooser.CHEM_ALLBIO_END)) {");
      for (int i=0; i<chems.length; i++) {
        PW.print("                  ");
        if (i>0) PW.print("else ");
        PW.println("if (var-GraphChooser.CHEM_ALLBIO_START=="+i+") value+=b.particulate_chem["+i+"];");
      }
      PW.println("                } else if ((var>=GraphChooser.CHEM_INDBIO_START) && (var<GraphChooser.CHEM_INDBIO_END)) {");      
      int totalTypes = 0;
      for (int i=0; i<species.length; i++) {
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
        totalTypes+=stages.length;
      }
      PW.println("                  final int chemNo = (var-GraphChooser.CHEM_INDBIO_START)/"+totalTypes+";");
      PW.println("                  final int speciesStageNo = (var-GraphChooser.CHEM_INDBIO_START)-(chemNo*"+totalTypes+");");
      typesSoFar=0;
      for (int i=0; i<species.length; i++) {
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
        PW.print("                  ");
        if (i>0) PW.print("else ");
        PW.println("if ((speciesStageNo>="+typesSoFar+") && (speciesStageNo<"+(typesSoFar+stages.length)+"))");
        for (int j=0; j<chems.length; j++) {
          PW.print("                    ");
          if (j>0) PW.print("else ");
          PW.println("if (chemNo=="+j+") value+=b.particulate_chem_ss["+j+"]["+i+"][speciesStageNo-"+typesSoFar+"];");
        }
        typesSoFar+=stages.length;
      }
      PW.println("                }");      
      PW.println("              }");
      PW.println("              if (firstItem) {");
      PW.println("                max=value;");
      PW.println("                min=value;");
      PW.println("                firstItem=false;");
      PW.println("              } else {");
      PW.println("                if (value>max) max=value;");
      PW.println("                if (value<min) min=value;");
      PW.println("              }");
      PW.println("            }");
      PW.println("          }");
      PW.println("        } else {");
      PW.println("          max = Double.parseDouble(maxValue.getText());");
      PW.println("          min = Double.parseDouble(minValue.getText());");
      PW.println("        }");
      PW.println("        maxValue.setText(String.valueOf(max));");
      PW.println("        minValue.setText(String.valueOf(min));");
      PW.println("        final double xscaler = (1.0*(_width-(leftMargin+rightMargin))/(max-min));");
      PW.println("        boolean firstItem=true;");
      PW.println("        int startX=0;");
      PW.println("        int startY=0;");
      PW.println("        final int firstVar = graphChoice.getItemInt(0);");
      PW.println("        final boolean isPhysical = ((firstVar>=GraphChooser.PHY_DENSITY) && (firstVar<=GraphChooser.PHY_TEMP));");
      PW.println("        if (isPhysical) {");
      PW.println("          for (int i=0; i<_players; i++) {");
      PW.println("            value=0;");
      PW.println("            final PLayer _pl = (PLayer) Kernel.W.P_LayerList.get(i);");
      PW.println("            for (int j=0; j<_vars; j++) {");
      PW.println("              final int var = graphChoice.getItemInt(j);");
      PW.println("              if (var==GraphChooser.PHY_DENSITY) value+=_pl.Density;");
      PW.println("              else if (var==GraphChooser.PHY_FULL_IRRAD) value+=_pl.Full_Irrad;");
      PW.println("              else if (var==GraphChooser.PHY_VIS_IRRAD) value+=_pl.Vis_Irrad;");
      PW.println("              else if (var==GraphChooser.PHY_SALINITY) value+=_pl.Salinity;");
      PW.println("              else if (var==GraphChooser.PHY_TEMP) value+=_pl.Temp;");
      PW.println("            }");
      PW.println("            if (firstItem) {");
      PW.println("              firstItem=false;");
      PW.println("              startX=(int) (xscaler*(value-min));");
      PW.println("              startY=(int) (yscaler*(_pl.Depth-_top));");
      PW.println("            } else {");
      PW.println("              final int newX = (int) (xscaler*(value-min));");
      PW.println("              final int newY = (int) (yscaler*(_pl.Depth-_top));");
      PW.println("              if ((startY>=0) && (startY<(_height-(topMargin+bottomMargin))) &&");
      PW.println("                (newY>=0) && (newY<(_height-(topMargin+bottomMargin)))) {");
      PW.println("                if (((startX>=0) && (startX<=_width)) ||");
      PW.println("                    ((newX>=0) && (newX<=_width))) {");
      PW.println("                  final int _startX = Math.max(Math.min(startX,_width),0);");
      PW.println("                  final int _newX = Math.max(Math.min(newX,_width),0);");
      PW.println("                  g.drawLine(leftMargin+_startX,topMargin+startY,leftMargin+_newX,topMargin+newY);");
      PW.println("                }");
      PW.println("              }");
      PW.println("              startX=newX;");
      PW.println("              startY=newY;");
      PW.println("            }");
      PW.println("          }");
      PW.println("        } else { // Not physical layers");
      PW.println("          for (int i=0; i<SysVars._layers; i++) {");
      PW.println("            value=0;");
      PW.println("            final BLayer b = Kernel.W.B_Layer[i];");
      PW.println("            for (int j=0; j<_vars; j++) {");
      PW.println("              final int var = graphChoice.getItemInt(j);");
      PW.println("              if ((var>=GraphChooser.BIO_START) && (var<GraphChooser.BIO_END)) {");
      typesSoFar=0;
      for (int i=0; i<species.length; i++) {
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
        PW.print("                ");
        if (i>0) PW.print("else ");
        PW.println("if ((var>="+typesSoFar+") && (var<"+(typesSoFar+stages.length)+"))");
        PW.println("                  value+=b.SpeciesConcentration["+i+"][var-"+typesSoFar+"];");
        typesSoFar+=stages.length;
      }
      PW.println("              } else if ((var>=GraphChooser.CHEM_CONC_START) && (var<GraphChooser.CHEM_CONC_END)) {");
      PW.println("                value+=b.solution_chem[var-GraphChooser.CHEM_CONC_START];");
      PW.println("              } else if ((var>=GraphChooser.CHEM_ALLBIO_START) && (var<GraphChooser.CHEM_ALLBIO_END)) {");
      for (int i=0; i<chems.length; i++) {
        PW.print("                ");
        if (i>0) PW.print("else ");
        PW.println("if (var-GraphChooser.CHEM_ALLBIO_START=="+i+") value+=b.particulate_chem["+i+"];");
      }
      PW.println("                } else if ((var>=GraphChooser.CHEM_INDBIO_START) && (var<GraphChooser.CHEM_INDBIO_END)) {");      
      PW.println("                  final int chemNo = (var-GraphChooser.CHEM_INDBIO_START)/"+totalTypes+";");
      PW.println("                  final int speciesStageNo = (var-GraphChooser.CHEM_INDBIO_START)-(chemNo*"+totalTypes+");");
      typesSoFar=0;
      for (int i=0; i<species.length; i++) {
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
        PW.print("                  ");
        if (i>0) PW.print("else ");
        PW.println("if ((speciesStageNo>="+typesSoFar+") && (speciesStageNo<"+(typesSoFar+stages.length)+"))");
        for (int j=0; j<chems.length; j++) {
          PW.print("                  ");
          if (j>0) PW.print("else ");
          PW.println("if (chemNo=="+j+") value+=b.particulate_chem_ss["+j+"]["+i+"][speciesStageNo-"+typesSoFar+"];");
        }
        typesSoFar+=stages.length;
      }
      
      PW.println("              }");      
      PW.println("            }");
      PW.println("            if (firstItem) {");
      PW.println("              firstItem=false;");
      PW.println("              startX=(int) (xscaler*(value-min));");
      PW.println("              startY=(int) (yscaler*(b.Depth-_top));");
      PW.println("            } else {");
      PW.println("              final int newX = (int) (xscaler*(value-min));");
      PW.println("              final int newY = (int) (yscaler*(b.Depth-_top));");
      PW.println("              if ((startY>=0) && (startY<(_height-(topMargin+bottomMargin))) &&");
      PW.println("                (newY>=0) && (newY<(_height-(topMargin+bottomMargin)))) {");
      PW.println("                if (((startX>=0) && (startX<=_width)) ||");
      PW.println("                    ((newX>=0) && (newX<=_width))) {");
      PW.println("                  final int _startX = Math.max(Math.min(startX,_width),0);");
      PW.println("                  final int _newX = Math.max(Math.min(newX,_width),0);");
      PW.println("                  g.drawLine(leftMargin+_startX,topMargin+startY,leftMargin+_newX,topMargin+newY);");
      PW.println("                }");
      PW.println("              }");
      PW.println("              startX=newX;");
      PW.println("              startY=newY;");
      PW.println("            }");
      PW.println("          }");
      PW.println("        }");
      PW.println("        g.setColor(Color.GREEN);");
      PW.println("        final int mld = (int)  (yscaler * (Kernel.W.MLDepth-_top));");
      PW.println("        for (int _dots=0; _dots<=_width-(leftMargin+rightMargin); _dots+=3) {");
      PW.println("          g.drawLine(leftMargin+_dots,topMargin+mld,leftMargin+_dots+1,topMargin+mld);");
      PW.println("        }");
      PW.println("      }");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  class DGEventHandler implements ActionListener, ComponentListener, FocusListener, KeyListener {");
      PW.println("    public void actionPerformed(ActionEvent e) {");
      PW.println("      if (e.getSource()==chooseGraph) {");
      PW.println("        graphChoice.setVisible(true);");
      PW.println("        graphTitle.setText(graphChoice.getGraphText());");
      PW.println("        paint(getGraphics());");
      PW.println("");
      PW.println("      } else if (e.getSource()==autoScale) {");
      PW.println("        minValue.setEditable(!autoScale.isSelected());");
      PW.println("        maxValue.setEditable(!autoScale.isSelected());");
      PW.println("      }");
      PW.println("");
      PW.println("    }");
      PW.println("    public void keyPressed(KeyEvent e) {}");
      PW.println("    public void keyTyped(KeyEvent e) {}");
      PW.println("    public void keyReleased(KeyEvent e) {");
      PW.println("      if ((e.getKeyCode()==KeyEvent.VK_ENTER) || (e.getKeyCode()==KeyEvent.VK_ESCAPE)) {");
      PW.println("        if (e.getSource()==bottomValue) adjustBottomValue();");
      PW.println("        else if (e.getSource()==topValue) adjustTopValue();");
      PW.println("        else if (e.getSource()==minValue) adjustMinValue();");
      PW.println("        else if (e.getSource()==maxValue) adjustMaxValue();");
      PW.println("      }");
      PW.println("    }");
      PW.println("");
      PW.println("    public void componentResized(ComponentEvent e) {");
      PW.println("//      final int width = DepthGraph.this.getWidth();");
      PW.println("      //final int height = DepthGraph.this.getHeight();");
      PW.println("      //System.out.println(\"width=\"+width+\", height=\"+height);");
      PW.println("      //maxValue.setPreferredSize(new Dimension(width-120,20));");
      PW.println("//      minValue.setPreferredSize(new Dimension(width-120,20));");
      PW.println("      //mainPanel.setPreferredSize(new Dimension(width-20,height-100));");
      PW.println("    }");
      PW.println("");
      PW.println("");
      PW.println("    public void componentHidden(ComponentEvent e) {}");
      PW.println("    public void componentMoved(ComponentEvent e) {}");
      PW.println("    public void componentShown(ComponentEvent e) {}");
      PW.println("");
      PW.println("    public void focusGained(FocusEvent e) {}");
      PW.println("");
      PW.println("    public void adjustTopValue() {");
      PW.println("      try {");
      PW.println("        Integer.parseInt(topValue.getText());");
      PW.println("      } catch (Exception ex) {");
      PW.println("        topValue.setText(\"0\");");
      PW.println("      }");
      PW.println("      int _t = Integer.parseInt(topValue.getText());");
      PW.println("      int _b = Integer.parseInt(bottomValue.getText());");
      PW.println("      if ((_t<0) || (_t>SysVars._layers)) {");
      PW.println("        _t=0;");
      PW.println("        topValue.setText(\"0\");");
      PW.println("      }");
      PW.println("      if ((_b<0) || (_b>SysVars._layers)) {");
      PW.println("        _b=SysVars._layers;");
      PW.println("        bottomValue.setText(String.valueOf(SysVars._layers));");
      PW.println("      }");
      PW.println("      if (_t>_b) {");
      PW.println("        topValue.setText(String.valueOf(_b));");
      PW.println("        bottomValue.setText(String.valueOf(_t));");
      PW.println("      }");
      PW.println("      mainPanel.paint(mainPanel.getGraphics());");
      PW.println("    }");
      PW.println("");
      PW.println("    public void adjustBottomValue() {");
      PW.println("      try {");
      PW.println("        Integer.parseInt(bottomValue.getText());");
      PW.println("      } catch (Exception ex) {");
      PW.println("        bottomValue.setText(String.valueOf(SysVars._layers));");
      PW.println("      }");
      PW.println("      int _t = Integer.parseInt(topValue.getText());");
      PW.println("      int _b = Integer.parseInt(bottomValue.getText());");
      PW.println("      if ((_t<0) || (_t>SysVars._layers)) {");
      PW.println("        _t=0;");
      PW.println("        topValue.setText(\"0\");");
      PW.println("      }");
      PW.println("      if ((_b<0) || (_b>SysVars._layers)) {");
      PW.println("        _b=SysVars._layers;");
      PW.println("        bottomValue.setText(String.valueOf(SysVars._layers));");
      PW.println("      }");
      PW.println("      if (_t>_b) {");
      PW.println("        topValue.setText(String.valueOf(_b));");
      PW.println("        bottomValue.setText(String.valueOf(_t));");
      PW.println("      }");
      PW.println("      mainPanel.paint(mainPanel.getGraphics());");
      PW.println("    }");
      PW.println("");
      PW.println("    public void adjustMaxValue() {");
      PW.println("      try {");
      PW.println("        Double.parseDouble(maxValue.getText());");
      PW.println("      } catch (Exception ex) {");
      PW.println("        maxValue.setText(\"0\");");
      PW.println("      }");
      PW.println("      double _t = Double.parseDouble(minValue.getText());");
      PW.println("      double _b = Double.parseDouble(maxValue.getText());");
      PW.println("      if (_t>_b) {");
      PW.println("        minValue.setText(String.valueOf(_b));");
      PW.println("        maxValue.setText(String.valueOf(_t));");
      PW.println("      }");
      PW.println("      mainPanel.paint(mainPanel.getGraphics());");
      PW.println("    }");
      PW.println("");
      PW.println("    public void adjustMinValue() {");
      PW.println("      try {");
      PW.println("        Double.parseDouble(minValue.getText());");
      PW.println("      } catch (Exception ex) {");
      PW.println("        minValue.setText(\"0\");");
      PW.println("      }");
      PW.println("      double _t = Double.parseDouble(minValue.getText());");
      PW.println("      double _b = Double.parseDouble(maxValue.getText());");
      PW.println("      if (_t>_b) {");
      PW.println("        minValue.setText(String.valueOf(_b));");
      PW.println("        maxValue.setText(String.valueOf(_t));");
      PW.println("      }");
      PW.println("      mainPanel.paint(mainPanel.getGraphics());");
      PW.println("    }");
      PW.println("");
      PW.println("    public void focusLost(FocusEvent e) {");
      PW.println("      if (e.getSource()==topValue) adjustTopValue();");
      PW.println("      else if (e.getSource()==bottomValue) adjustBottomValue();");
      PW.println("      else if (e.getSource()==maxValue) adjustMaxValue();");
      PW.println("      else if (e.getSource()==minValue) adjustMinValue();");
      PW.println("    }");
      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.println("class GraphChooser extends JDialog {");
      PW.println("  DefaultListModel optionsModel = new DefaultListModel();");
      PW.println("  JList options = new JList(optionsModel);");
      PW.println("  DefaultListModel chosenModel = new DefaultListModel();");
      PW.println("  JList chosen = new JList(chosenModel);");
      PW.println("  ArrayList chosenModelInts = new ArrayList();");
      PW.println("  JButton add = new JButton(\"Add\");");
      PW.println("  JButton remove = new JButton(\"Remove\");");
      PW.println("  JButton ok = new JButton(\"Ok\");");
      PW.println("  JScrollPane optionsScroller;");
      PW.println("  JScrollPane chosenScroller;");
      PW.println("  public static final String NO_GRAPH = new String(\"No Graph Selected\");");
      PW.println("  public static final String DENSITY = new String(\"Physics : Density\");");
      PW.println("  public static final String FULL_IRRAD= new String(\"Physics : Irradiance (Full)\");");
      PW.println("  public static final String VIS_IRRAD = new String(\"Physics : Irradiance (Visible)\");");
      PW.println("  public static final String SALINITY = new String(\"Physics : Salinity\");");
      PW.println("  public static final String TEMPERATURE = new String(\"Physics : Temperature\");");
      PW.println("  public static final String PHYSICS = new String(\"Physics : \");");
      PW.println("  public static final String BIOLOGY = new String(\"Biology : \");");
      PW.println("  public static final String CONC = new String(\"Concentration of : \");");
      PW.println("  public static int PHY_DENSITY=0;");
      PW.println("  public static int PHY_FULL_IRRAD=0;");
      PW.println("  public static int PHY_VIS_IRRAD=0;");
      PW.println("  public static int PHY_SALINITY=0;");
      PW.println("  public static int PHY_TEMP=0;");
      PW.println("  public static int BIO_START=0;");
      PW.println("  public static int BIO_END=0;");
      PW.println("  public static int CHEM_CONC_START=0;");
      PW.println("  public static int CHEM_CONC_END=0;");
      PW.println("  public static int CHEM_ALLBIO_START=0;");
      PW.println("  public static int CHEM_ALLBIO_END=0;");
      PW.println("  public static int CHEM_INDBIO_START=0;");
      PW.println("  public static int CHEM_INDBIO_END=0;");
      
      PW.println("  GCEventHandler gceh = new GCEventHandler();");
      PW.println("  int gcLock=0;");
      PW.println("");
      PW.println("  public GraphChooser(JDialog parent) {");
      PW.println("    super(parent,\"Choose Graph\",true);");
      PW.println("    gcLock++;");
      PW.println("    setSize(new Dimension(600,200));");
      PW.println("    setLayout(new BorderLayout());");
      PW.println("    JPanel bottomButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));");
      PW.println("    bottomButtons.add(ok);");
      PW.println("    add(bottomButtons,BorderLayout.SOUTH);");
      PW.println("    JPanel main = new JPanel(new BorderLayout());");
      PW.println("    optionsScroller = new JScrollPane(options);");
      PW.println("    chosenScroller = new JScrollPane(chosen);");
      PW.println("    optionsScroller.setPreferredSize(new Dimension(250,150));");
      PW.println("    chosenScroller.setPreferredSize(new Dimension(250,150));");
      PW.println("    main.add(optionsScroller,BorderLayout.WEST);");
      PW.println("    main.add(chosenScroller,BorderLayout.EAST);");
      PW.println("    JPanel addRemove = new JPanel(new FlowLayout());");
      PW.println("    JPanel buttonGrid = new JPanel(new GridLayout(2,1));");
      PW.println("    buttonGrid.add(add);");
      PW.println("    buttonGrid.add(remove);");
      PW.println("    addRemove.add(buttonGrid);");
      PW.println("    main.add(addRemove,BorderLayout.CENTER);");
      PW.println("    getContentPane().addComponentListener(gceh);");
      PW.println("    add(main,BorderLayout.CENTER);");
      int op = 0;
      PW.println("    BIO_START="+op+";");
      for (int i=0; i<species.length; i++) {
        XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
        for (int j=0; j<stages.length; j++) {
          PW.println("    optionsModel.addElement(\"Biology Concentration: "+species[i].getAttribute("name")+" : "+stages[j].getValue("name")+"\");");
          op++;
        }
      }
      PW.println("    BIO_END = "+op+";");
      PW.println("    CHEM_CONC_START = "+op+";");
      for (int i=0; i<chems.length; i++) {
        PW.println("    optionsModel.addElement(\"Chemistry : "+chems[i].getValue("name")+", solution\");");
        op++;
      }
      PW.println("    CHEM_CONC_END = "+op+";");
      PW.println("    CHEM_ALLBIO_START = "+op+";");
      for (int i=0; i<chems.length; i++) {
        PW.println("    optionsModel.addElement(\"Chemistry : "+chems[i].getValue("name")+", all particles\");");
        op++;
      }
      PW.println("    CHEM_ALLBIO_END = "+op+";");
      PW.println("    CHEM_INDBIO_START = "+op+";");
      for (int i=0; i<chems.length; i++) {
        for (int j=0; j<species.length; j++) {
          XMLTag[] stages = model.getTagWhere("functionalgroup","name",species[j].getAttribute("fg")).getTags("stage");
          for (int k=0; k<stages.length; k++) {
            PW.println("    optionsModel.addElement(\"Chemistry : "+chems[i].getValue("name")+" in "+species[j].getAttribute("name")+":"+stages[k].getValue("name")+"\");");
            op++;
          }
        }
      }
      PW.println("    CHEM_INDBIO_END = "+op+";");     
      PW.println("    optionsModel.addElement(DENSITY);");
      PW.println("    PHY_DENSITY="+op+";");
      op++;
      PW.println("    optionsModel.addElement(FULL_IRRAD);");
      PW.println("    PHY_FULL_IRRAD="+op+";");
      op++;
      PW.println("    optionsModel.addElement(VIS_IRRAD);");
      PW.println("    PHY_VIS_IRRAD="+op+";");
      op++;
      PW.println("    optionsModel.addElement(SALINITY);");
      PW.println("    PHY_SALINITY="+op+";");
      op++;
      PW.println("    optionsModel.addElement(TEMPERATURE);");
      PW.println("    PHY_TEMP="+op+";");
      op++;
      PW.println("    ok.setEnabled(false);");
      PW.println("    add.setEnabled(false);");
      PW.println("    remove.setEnabled(false);");
      PW.println("    options.addListSelectionListener(gceh);");
      PW.println("    chosen.addListSelectionListener(gceh);");
      PW.println("    ok.addActionListener(gceh);");
      PW.println("    add.addActionListener(gceh);");
      PW.println("    remove.addActionListener(gceh);");
      PW.println("    gcLock--;");
      PW.println("  }");
      PW.println("");
      PW.println("  public void updateButtons() {");
      PW.println("    gcLock++;");
      PW.println("    add.setEnabled(options.getSelectedIndices().length>0);");
      PW.println("    remove.setEnabled(chosen.getSelectedIndices().length>0);");
      PW.println("    ok.setEnabled(chosenModel.getSize()>0);");
      PW.println("    gcLock--;");
      PW.println("  }");
      PW.println("");
      PW.println("  public String getGraphText() {");
      PW.println("    StringBuffer sb = new StringBuffer();");
      PW.println("    if (chosenModel.size()==0) return new String(NO_GRAPH);");
      PW.println("    else {");
      PW.println("      for (int i=0; i<chosenModel.size(); i++) {");
      PW.println("        sb.append(chosenModel.getElementAt(i));");
      PW.println("        if (i<chosenModel.size()-1) sb.append(\" + \");");
      PW.println("      }");
      PW.println("      return sb.toString();");
      PW.println("    }");
      PW.println("  }");
      PW.println("");
      PW.println("  public String getItem(int i) {");
      PW.println("    if (chosenModel.size()<=i) return null;");
      PW.println("    else return chosenModel.getElementAt(i).toString();");
      PW.println("  }");
      PW.println("");
      PW.println("  public int getItemInt(int i) {");
      PW.println("    if (chosenModel.size()<=i) return -1;");
      PW.println("    else return ((Integer) chosenModelInts.get(i)).intValue();");
      PW.println("  }");
      PW.println("");
      PW.println("  public int getItemCount() {");
      PW.println("    return chosenModel.getSize();");
      PW.println("  }");
      PW.println("");
      PW.println("");
      PW.println("  class GCEventHandler implements ActionListener, ListSelectionListener, ComponentListener {");
      PW.println("    public void actionPerformed(ActionEvent e) {");
      PW.println("      if ((e.getSource()==add) && (gcLock==0)) {");
      PW.println("        gcLock++;");
      PW.println("        int[] selection = options.getSelectedIndices();");
      PW.println("        for (int i=0; i<selection.length; i++) {");
      PW.println("          chosenModel.addElement(optionsModel.getElementAt(selection[i]));");
      PW.println("          chosenModelInts.add(new Integer(selection[i]));");
      PW.println("        }");
      PW.println("        options.clearSelection();");
      PW.println("        gcLock--;");
      PW.println("        updateButtons();");
      PW.println("");
      PW.println("      } else if ((e.getSource()==remove) && (gcLock==0)) {");
      PW.println("        gcLock++;");
      PW.println("        int[] selection = chosen.getSelectedIndices();");
      PW.println("        for (int i=selection.length-1; i>=0; i--) {");
      PW.println("          chosenModel.removeElementAt(selection[i]);");
      PW.println("          chosenModelInts.remove(selection[i]);");
      PW.println("        }");
      PW.println("        chosen.clearSelection();");
      PW.println("        gcLock--;");
      PW.println("        updateButtons();");
      PW.println("");
      PW.println("");
      PW.println("      } else if (e.getSource()==ok) setVisible(false);");
      PW.println("");
      PW.println("    }");
      PW.println("");
      PW.println("    public void valueChanged(ListSelectionEvent e) {");
      PW.println("      updateButtons();");
      PW.println("    }");
      PW.println("");
      PW.println("    public void componentResized(ComponentEvent e) {");
      PW.println("      if (gcLock==0) {");
      PW.println("        gcLock++;");
      PW.println("        final int width = GraphChooser.this.getWidth()-120;");
      PW.println("        optionsScroller.setPreferredSize(new Dimension(width/2,200));");
      PW.println("        chosenScroller.setPreferredSize(new Dimension(width/2,200));");
      PW.println("        gcLock--;");
      PW.println("      }");
      PW.println("    }");
      PW.println("");
      PW.println("");
      PW.println("    public void componentHidden(ComponentEvent e) {}");
      PW.println("    public void componentMoved(ComponentEvent e) {}");
      PW.println("    public void componentShown(ComponentEvent e) {}");

      PW.println("  }");
      PW.println("}");
      PW.println("");
      PW.flush();
      PW.close();
      
      
    } catch (Exception e) { e.printStackTrace(); }
    
  }
}
