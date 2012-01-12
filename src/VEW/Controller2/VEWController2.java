package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import VEW.Common.XML.XMLFile;
import VEW.Planktonica2.DisplayOptions;
import VEW.Planktonica2.Planktonica;
import VEW.Planktonica2.SyntaxChecker;
import VEW.Planktonica2.Model.Function;
import VEW.Planktonica2.Model.XMLWriteBackException;
import VEW.Scenario2.ChemicalConservationDialog;
import VEW.Scenario2.ClosureEditor;
import VEW.Scenario2.InitialConditionsPanel;
import VEW.Scenario2.ScenarioPanel2;
import VEW.Scenario2.VerticalDiffusionDialog;

public class VEWController2 extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
  JButton saveChanges = new JButton("Save Changes");
  JButton exitButton = new JButton("Exit");
  EventHandler eh = new EventHandler();
  private ImageIcon greyLight = new ImageIcon("Data/Graphics/icons/vc_grey.png");
  private ImageIcon redLight = new ImageIcon("Data/Graphics/icons/vc_red_flat.png");  
  private ImageIcon yellowLight = new ImageIcon("Data/Graphics/icons/vc_yellow_flat.png");  
  private ImageIcon greenLight = new ImageIcon("Data/Graphics/icons/vc_green_flat.png");
  private JLabel modelLight, speciesLight, foodLight, initLight, pmLight, trackLight;
  private JLabel colInitLight, closureLight, chemRecycleLight, eventsLight, vddLight;
  private JLabel outputLight, runLight;
  private JLabel lastPage = null;
  private XMLFile theModel;
  private JPanel mainPanel = new JPanel();
  public static String ApplicationName = new String("VEW 3.3");
  private JLabel messageLabel = new JLabel("THE VIRTUAL ECOLOGY WORKBENCH 3.3");
  private Cursor thePointyFinger = new Cursor(Cursor.HAND_CURSOR);
  private Cursor theNormalMouse = new Cursor(Cursor.DEFAULT_CURSOR);
  //private Cursor theTimer = new Cursor(Cursor.WAIT_CURSOR);
  public static boolean DEBUG = false;
  public static final int DontCompile = 0;
  public static final int CompileCode = 1;
  public static final int CompileExec = 2;
  public static final int CompileJar  = 3;
  private Planktonica p = null;
  private SpeciesBuilder2 sb2 = null;
  private FoodTypeDialog ftd = null;
  private ParticleInitialiser pi = null;  
  private ParticleManager2 pm = null;
  private OutputDialog2 od2 = null;
  private InitialConditionsPanel icp = null;
  private ScenarioPanel2 sp2 = null;
  private RunPanel rp = null;
  private EventPanel ep = null;
  private ChemicalConservationDialog cr = null;
  private ClosureEditor cd = null;
  private SyntaxChecker sc = null;
  private VerticalDiffusionDialog vdd = null;
  
  public static boolean NOC_Mode = false;
    
  public static String modelPath;

  public String getModelPath() { return modelPath; }
  
  public JLabel createLabel(String title, ImageIcon image, JPanel _p) {
    title="<html><font size=\"-2\" face=\"Arial\"><center>"+title+"</center></font></html>";
    JLabel j = new JLabel(title, image, JLabel.CENTER);
    j.setVerticalTextPosition(JLabel.BOTTOM);
    j.setHorizontalTextPosition(JLabel.CENTER);
    _p.add(j);
    return j;
  }
  
  public boolean setIconStatus(JLabel _jl, ImageIcon ii) {
    if (_jl!=null) {
      _jl.setIcon(ii);
      if (ii!=yellowLight) _jl.setCursor(thePointyFinger);
      else _jl.setCursor(theNormalMouse);
      _jl.update(_jl.getGraphics());
    }
    return (ii==greenLight);
  }
  
 
  public void updateLights() {
    boolean allOk = setIconStatus(modelLight,p.greenLight(false)?greenLight:redLight);
    boolean speciesOK = (sb2.greenLight(false));
    boolean trackOK = sp2.greenLight(false);
    allOk = setIconStatus(speciesLight,speciesOK?greenLight:redLight) && allOk;
    allOk = setIconStatus(foodLight,(speciesOK)?(ftd.greenLight(false)?greenLight:redLight):greyLight) && allOk;
    allOk = setIconStatus(trackLight,trackOK?greenLight:redLight) && allOk;
    allOk = setIconStatus(pmLight,(trackOK && speciesOK)?(pm.greenLight(false)?greenLight:redLight):greyLight) && allOk;
    boolean colInitOK=icp.greenLight(false);
    allOk = setIconStatus(colInitLight,colInitOK?greenLight:redLight) && allOk;
    allOk = setIconStatus(initLight,(trackOK & speciesOK & colInitOK)?(pi.greenLight(false)?greenLight:redLight):greyLight) && allOk;    
    allOk = setIconStatus(closureLight,(cd.greenLight(false)?greenLight:redLight)) && allOk;    
    allOk = setIconStatus(chemRecycleLight,trackOK?(cr.greenLight(false)?greenLight:redLight):greyLight) && allOk;
    if (NOC_Mode) allOk = setIconStatus(vddLight, trackOK?(vdd.greenLight(false)?greenLight:redLight):greyLight) && allOk; 
    allOk = setIconStatus(eventsLight,trackOK?(ep.greenLight(false)?greenLight:redLight):greyLight) && allOk;
    allOk = setIconStatus(outputLight,(speciesOK && trackOK)?(od2.greenLight(false)?greenLight:redLight):greyLight) && allOk;
    allOk = setIconStatus(runLight, (!allOk)?greyLight:(rp.greenLight(false)?greenLight:redLight)) && allOk;
    setIconStatus(lastPage,yellowLight);
  }
  
  public VEWController2(JFrame parent, XMLFile xmlFile) {
	  super("VEW Controller");
	  theModel = xmlFile;
	  p = new Planktonica(this, theModel);
	  sb2 = new SpeciesBuilder2(this, theModel);
	  ftd = new FoodTypeDialog(this, theModel);
	  pi = new ParticleInitialiser(this, theModel);  
	  pm = new ParticleManager2(this, theModel);
	  od2 = new OutputDialog2(this, theModel);
	  icp = new InitialConditionsPanel(this, theModel);
	  rp = new RunPanel(this, theModel);
	  ep = new EventPanel(this, theModel);
	  sp2 = new ScenarioPanel2(this, theModel);
	  cr = new ChemicalConservationDialog(this, theModel);
	  cd = new ClosureEditor(this, theModel);
	  sc = new SyntaxChecker(this, theModel);
	  if (NOC_Mode) vdd = new VerticalDiffusionDialog(this, theModel);



	  getContentPane().setLayout(new BorderLayout());
	  mainPanel.setLayout(new BorderLayout());
	  mainPanel.setBorder(new LineBorder(Color.gray));
	  getContentPane().add(mainPanel,BorderLayout.CENTER);
	  JPanel controls = new JPanel(new BorderLayout());
	  JPanel lightsPanel = new JPanel(new GridLayout(1,13));
	  modelLight = createLabel("Edit<br>Model",greyLight, lightsPanel);
	  speciesLight = createLabel("Edit<br>Species",greyLight, lightsPanel);
	  foodLight = createLabel("Edit<br>Food-sets", greyLight, lightsPanel);
	  trackLight = createLabel("Create<br>Track", greyLight, lightsPanel);
	  pmLight = createLabel("Particle<br>Manager", greyLight, lightsPanel);
	  colInitLight = createLabel("Init<br>Column", greyLight, lightsPanel);
	  initLight = createLabel("Init<br>Plankton", greyLight, lightsPanel);
	  closureLight = createLabel("Trophic<br>Closure", greyLight, lightsPanel);
	  chemRecycleLight = createLabel("Chemical<br>Recycling", greyLight, lightsPanel);
	  if (NOC_Mode) vddLight = createLabel("Vertical<br>Diffusion",greyLight, lightsPanel);
	  eventsLight = createLabel("Set<br>Events", greyLight, lightsPanel);
	  outputLight = createLabel("Set<br>Logging", greyLight, lightsPanel);
	  runLight = createLabel("Job<br>Builder", greyLight, lightsPanel);
	  controls.add(lightsPanel,BorderLayout.NORTH);
	  JPanel saveButtons = new JPanel(new FlowLayout());
	  saveButtons.add(saveChanges);
	  saveButtons.add(exitButton);
	  JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	  messagePanel.add(messageLabel);
	  messagePanel.setBackground(Color.LIGHT_GRAY);
	  controls.add(messagePanel,BorderLayout.CENTER);
	  controls.add(saveButtons,BorderLayout.SOUTH);
	  getContentPane().add(controls,BorderLayout.SOUTH);
	  mainPanel.setPreferredSize(new Dimension(800,600));
	  exitButton.addActionListener(eh);
	  saveChanges.addActionListener(eh);
	  modelLight.addMouseListener(eh);
	  speciesLight.addMouseListener(eh);
	  foodLight.addMouseListener(eh);
	  initLight.addMouseListener(eh);
	  pmLight.addMouseListener(eh);
	  trackLight.addMouseListener(eh);
	  colInitLight.addMouseListener(eh);
	  closureLight.addMouseListener(eh);
	  chemRecycleLight.addMouseListener(eh);
	  if (NOC_Mode) vddLight.addMouseListener(eh);
	  eventsLight.addMouseListener(eh);
	  outputLight.addMouseListener(eh);
	  runLight.addMouseListener(eh);
	  saveChanges.setEnabled(false);
	  updateLights();

	  pack();
  }
  
  public VEWController2(JFrame parent, String modelFile) {
    super("VEW Controller");
    modelPath = modelFile;
    theModel = XMLFile.LoadFile(modelFile);
    p = new Planktonica(this, theModel);
    sb2 = new SpeciesBuilder2(this, theModel);
    ftd = new FoodTypeDialog(this, theModel);
    pi = new ParticleInitialiser(this, theModel);  
    pm = new ParticleManager2(this, theModel);
    od2 = new OutputDialog2(this, theModel);
    icp = new InitialConditionsPanel(this, theModel);
    rp = new RunPanel(this, theModel);
    ep = new EventPanel(this, theModel);
    sp2 = new ScenarioPanel2(this, theModel);
    cr = new ChemicalConservationDialog(this, theModel);
    cd = new ClosureEditor(this, theModel);
    sc = new SyntaxChecker(this, theModel);
    if (NOC_Mode) vdd = new VerticalDiffusionDialog(this, theModel);
    
    
    
    getContentPane().setLayout(new BorderLayout());
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBorder(new LineBorder(Color.gray));
    getContentPane().add(mainPanel,BorderLayout.CENTER);
    JPanel controls = new JPanel(new BorderLayout());
    JPanel lightsPanel = new JPanel(new GridLayout(1,13));
    modelLight = createLabel("Edit<br>Model",greyLight, lightsPanel);
    speciesLight = createLabel("Edit<br>Species",greyLight, lightsPanel);
    foodLight = createLabel("Edit<br>Food-sets", greyLight, lightsPanel);
    trackLight = createLabel("Create<br>Track", greyLight, lightsPanel);
    pmLight = createLabel("Particle<br>Manager", greyLight, lightsPanel);
    colInitLight = createLabel("Init<br>Column", greyLight, lightsPanel);
    initLight = createLabel("Init<br>Plankton", greyLight, lightsPanel);
    closureLight = createLabel("Trophic<br>Closure", greyLight, lightsPanel);
    chemRecycleLight = createLabel("Chemical<br>Recycling", greyLight, lightsPanel);
    if (NOC_Mode) vddLight = createLabel("Vertical<br>Diffusion",greyLight, lightsPanel);
    eventsLight = createLabel("Set<br>Events", greyLight, lightsPanel);
    outputLight = createLabel("Set<br>Logging", greyLight, lightsPanel);
    runLight = createLabel("Job<br>Builder", greyLight, lightsPanel);
    controls.add(lightsPanel,BorderLayout.NORTH);
    JPanel saveButtons = new JPanel(new FlowLayout());
    saveButtons.add(saveChanges);
    saveButtons.add(exitButton);
    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    messagePanel.add(messageLabel);
    messagePanel.setBackground(Color.LIGHT_GRAY);
    controls.add(messagePanel,BorderLayout.CENTER);
    controls.add(saveButtons,BorderLayout.SOUTH);
    getContentPane().add(controls,BorderLayout.SOUTH);
    mainPanel.setPreferredSize(new Dimension(800,600));
    exitButton.addActionListener(eh);
    saveChanges.addActionListener(eh);
    modelLight.addMouseListener(eh);
    speciesLight.addMouseListener(eh);
    foodLight.addMouseListener(eh);
    initLight.addMouseListener(eh);
    pmLight.addMouseListener(eh);
    trackLight.addMouseListener(eh);
    colInitLight.addMouseListener(eh);
    closureLight.addMouseListener(eh);
    chemRecycleLight.addMouseListener(eh);
    if (NOC_Mode) vddLight.addMouseListener(eh);
    eventsLight.addMouseListener(eh);
    outputLight.addMouseListener(eh);
    runLight.addMouseListener(eh);
    saveChanges.setEnabled(false);
    updateLights();
    this.addComponentListener(new FrameSizeChangeListener(this));
    this.setPreferredSize(new Dimension(DisplayOptions.getOptions().FRAME_WIDTH,
    		DisplayOptions.getOptions().FRAME_HEIGHT));
    this.setLocation(DisplayOptions.getOptions().FRAME_X,
    		DisplayOptions.getOptions().FRAME_Y);
    this.setMinimumSize(new Dimension(590,440));
    pack();
    if (DisplayOptions.getOptions().MAXIMISED) {
		// The form should be maximised
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
  }   
  
  

public boolean isClickable(JLabel j) {
    return ((j.getIcon()==greenLight) || (j.getIcon()==redLight)) ;
  }
  
  public boolean isValid(JLabel j) {
    return (j.getIcon()==greenLight);
  }
  
  public boolean isUnSaved() {
    return saveChanges.isEnabled();
  }
  
  public void doSave() {
    leavePage(null);
    theModel.write();
    updateLights();
    saveChanges.setEnabled(false);
  }
  
  public void unsaved(boolean lights) {
    saveChanges.setEnabled(true);
    if (lights) updateLights();
  }
  
  public boolean leavePage(JLabel j) {
    if (lastPage==trackLight) {
      sp2.leavePage();
      return true;
    } else if (lastPage==modelLight) {
      Function.COMPILEFULLY = false;
      try {
		XMLFile file = (XMLFile) p.getModel().buildToXML();
		file.write();
      } 
      catch (XMLWriteBackException e) {
    	e.printStackTrace();
      }
      
      Function.COMPILEFULLY = true;
      
      sc.setVisible(true);
      return true;
    } else if (lastPage==initLight) {
      if (j==null) {
        pi.checkAgentsExist();
        return true;
      }
      else if (!pi.checkAgentsExist()) {
        return false;
      }
    }
    return true;
  }
  
  public void showPage(JPanel _p) {
    mainPanel.removeAll();
    mainPanel.add(_p,BorderLayout.CENTER);
    mainPanel.update(mainPanel.getGraphics());
    //VEWController2.this.pack();    
  }
  
  public boolean yellowLight(JLabel j) {
    boolean ok = leavePage(j);
    if (ok) {
      lastPage=j;
      updateLights();
      setIconStatus(j,yellowLight);
    }
    return ok;
  }
  
  public void exitToJobs() {
    TitlePage.jumpToJobs=true;
    setVisible(false);
  }
    
  public void update_location() {
	  DisplayOptions.getOptions().FRAME_HEIGHT = this.getHeight();
	  DisplayOptions.getOptions().FRAME_WIDTH = this.getWidth();
	  DisplayOptions.getOptions().MAXIMISED = (this.getExtendedState() == JFrame.MAXIMIZED_BOTH);
	  DisplayOptions.getOptions().FRAME_X = this.getX();
	  DisplayOptions.getOptions().FRAME_Y = this.getY();
	  DisplayOptions.getOptions().write_config();
  }
  
  class FrameSizeChangeListener implements ComponentListener {
		
		VEWController2 parent;
		
		public FrameSizeChangeListener(VEWController2 parent) {
			this.parent = parent;
		}

		@Override
		public void componentHidden(ComponentEvent arg0) {}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			parent.update_location();
		}

		@Override
		public void componentResized(ComponentEvent arg0) {
			parent.update_location();
		}

		@Override
		public void componentShown(ComponentEvent arg0) {}
		
	}
  
  class EventHandler implements ActionListener, MouseListener {
    public EventHandler() {}
   
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==exitButton) {
        setVisible(false);
      } else if (e.getSource()==saveChanges) {
        doSave();
      }
    }

    public void mousePressed(MouseEvent e) {
      if ((e.getSource()==modelLight) && (isClickable(modelLight))) {
        if (yellowLight(modelLight)) {
          messageLabel.setText("EDITING THE COMMUNITY (FUNCTIONAL GROUPS, CHEMICALS, PIGMENTS)");
          p.greenLight(true);
          showPage(p);
        }
      
      } else if ((e.getSource()==speciesLight) && (isClickable(speciesLight))) {
        if (yellowLight(speciesLight)) {
          messageLabel.setText("EDITING THE SPECIES");
          sb2.greenLight(true);
          showPage(sb2);
        }
      
      } else if ((e.getSource()==foodLight) && (isClickable(foodLight))) {
        if (yellowLight(foodLight)) {
          messageLabel.setText("EDITING PREDATOR AND PREY RELATIONSHIPS");
          ftd.greenLight(true);
          showPage(ftd);
        }
        
      } else if ((e.getSource()==initLight) && (isClickable(initLight))) {
        if (yellowLight(initLight)) {
          messageLabel.setText("EDITING PLANKTON DISTRIBUTIONS (INITIALISATION AND EVENTS)");
          pi.greenLight(true);
          showPage(pi);
        }
        
      } else if ((e.getSource()==pmLight) && (isClickable(pmLight))) {
        if (yellowLight(pmLight)) {
          messageLabel.setText("EDITING AGENT MANAGEMENT RULES");
          pm.greenLight(true);
          showPage(pm);
        }
      
      } else if ((e.getSource()==trackLight) && (isClickable(trackLight))) {
        if (yellowLight(trackLight)) {
          messageLabel.setText("EDITING THE TRACK FOR THE WATER COLUMN");
          sp2.greenLight(true);
          showPage(sp2);
        }
      
      } else if ((e.getSource()==colInitLight) && (isClickable(colInitLight))) {
        if (yellowLight(colInitLight)) {
          messageLabel.setText("EDITING INITIALISATION OF THE COLUMN PROFILE");
          icp.greenLight(true);
          showPage(icp);
        }
      
      } else if ((e.getSource()==closureLight) && (isClickable(closureLight))) {
        if (yellowLight(closureLight)) {
          messageLabel.setText("EDITING TROPHIC CLOSURE");
          cd.greenLight(true);
          showPage(cd);
        }
      
      } else if ((e.getSource()==vddLight) && (isClickable(vddLight))) {
        if (yellowLight(vddLight)) {
          messageLabel.setText("EDITING VERTICAL DIFFUSION OPTIONS");
          vdd.greenLight(true);
          showPage(vdd);
        }
        
      } else if ((e.getSource()==chemRecycleLight) && (isClickable(chemRecycleLight))) {
        if (yellowLight(chemRecycleLight)) {
          messageLabel.setText("EDITING CHEMICAL RECYCLING OPTIONS");
          cr.greenLight(true);
          showPage(cr);
        }
      
      } else if ((e.getSource()==eventsLight) && (isClickable(eventsLight))) {
        if (yellowLight(eventsLight)) {
          messageLabel.setText("EDITING EXOGENOUS EVENTS");
          ep.greenLight(true);
          showPage(ep);
        }
      
      } else if ((e.getSource()==outputLight) && (isClickable(outputLight))) {
        if (yellowLight(outputLight)) {
          messageLabel.setText("EDITING OUTPUT LOGGING OPTIONS");
          od2.greenLight(true);
          showPage(od2);
        }
        
      } else if ((e.getSource()==runLight) && (isClickable(runLight))) {
        if (yellowLight(runLight)) {
          messageLabel.setText("BATCHES, ENSEMBLES AND RESTART FILES");
          rp.greenLight(true);
          showPage(rp);
        }
      } 
    }
      
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
  }
}
