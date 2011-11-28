package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class TitlePage extends JFrame {
  private static final ImageIcon modelsImage = new ImageIcon("Data/Graphics/icons/title_model.jpg");
  private static final ImageIcon resultsImage = new ImageIcon("Data/Graphics/icons/title_results.jpg");
  private static final ImageIcon jobsImage = new ImageIcon("Data/Graphics/icons/title_jobs.jpg");
  private static final ImageIcon titleImage = new ImageIcon("Data/Graphics/icons/vew31.jpg");
  private final JLabel titleLabel = new JLabel(titleImage);
  private final JButton modelsButton = new JButton(modelsImage);
  private final JButton resultsButton =  new JButton(resultsImage);
  private final JButton jobsButton = new JButton(jobsImage);
  private final JButton updateButton = new JButton("<html><font color='#ff0000'>Update</font></html>");
  private final JButton exitButton = new JButton("Exit");
  private static final String buildModels = "Model Building";
  private static final String buildModelsText = "Create, import, develop and edit models.";
  private static final String analyseResults = "Data Analysis";
  private static final String analyseResultsText = "View results of simulations already run.";
  private static final String viewJobs = "Current Jobs";
  private static final String viewJobsText = "Manage the jobs currently being run.";
  private final JLabel modelsText = new JLabel("<html><font face='Arial' color='#000000' size='5'><u>"+buildModels+"</u></font><br>"+buildModelsText+"</html>",JLabel.LEFT);
  private final JLabel resultsText = new JLabel("<html><font face='Arial' color='#000000' size='5'><u>"+analyseResults+"</u></font><br>"+analyseResultsText +"</html>",JLabel.LEFT);  
  private final JLabel jobsText = new JLabel("<html><font face='Arial' color='#000000' size='5'><u>"+viewJobs+"</u></font><br>"+viewJobsText+"</html>",JLabel.LEFT);
  private EventHandler eh = new EventHandler();
  private JobLauncher jl;
  private ShowResults sr;
  private ModelChooser mc;
  private VersionChooser vp;
  private UpdateVEW uv;
  private static final Cursor thePointyFinger = new Cursor(Cursor.HAND_CURSOR);
  private static final Cursor theNormalMouse = new Cursor(Cursor.DEFAULT_CURSOR);
  private final JPanel buttonGrid = new JPanel(new GridLayout(3,1));
  private final JPanel titlePanel = new JPanel(new FlowLayout());
  private JPanel currentPanel = null;
  public static boolean jumpToJobs = false;

  
  public TitlePage() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    File f = new File("jobs.xml.lock");
    if (f.exists()) { f.delete(); }
    f = new File("vew.xml");
    if (!f.exists()) {
      XMLFile xf = new XMLFile("vew.xml","vew");
      xf.addTag(new XMLTag("scenariodata",".."+File.separator+"ScenarioData"));
      xf.addTag(new XMLTag("jobrun",""));
      xf.getTag("jobrun").setAttribute("no","2");
      xf.getTag("jobrun").setAttribute("auto","false");
      xf.write();
    } else {
      XMLFile xf = XMLFile.LoadFile("vew.xml");
      if (xf.getTag("scenariodata")==null) {
        xf.addTag(new XMLTag("scenariodata",".."+File.separator+"ScenarioData"+File.separator));
        xf.write();
      }
    }
    f = new File("jobs.xml");
    if (!f.exists()) {
      XMLFile xf = new XMLFile("jobs.xml","jobfile");
      xf.addTag(new XMLTag("jobs"));
      xf.write();
    }
    f = new File("results.xml");
    if (!f.exists()) {
      XMLFile xf = new XMLFile("results.xml","results");
      xf.write();
    }
    setTitle("VEW "+UpdateVEW.versionNo);
    setSize(800,740);
    getContentPane().setLayout(new BorderLayout());
    modelsButton.setPreferredSize(new Dimension(110,110));
    resultsButton.setPreferredSize(new Dimension(110,110));
    jobsButton.setPreferredSize(new Dimension(110,110));
    

    titlePanel.add(titleLabel);
    final JPanel modelsSpacer = new JPanel();
    modelsSpacer.setPreferredSize(new Dimension(40,100));
    final JPanel resultsSpacer = new JPanel();
    resultsSpacer.setPreferredSize(new Dimension(40,100));    
    final JPanel jobsSpacer = new JPanel();    
    jobsSpacer.setPreferredSize(new Dimension(40,100));    
    
      final JPanel modelsPanel = new JPanel(new FlowLayout());
        modelsPanel.add(modelsButton);
        modelsPanel.add(modelsSpacer);
        modelsPanel.add(modelsText);
        modelsText.setPreferredSize(new Dimension(240,100));
        modelsPanel.setPreferredSize(new Dimension(240,120));
      final JPanel resultsPanel = new JPanel(new FlowLayout());
        resultsPanel.add(resultsButton);
        resultsPanel.add(resultsSpacer);
        resultsPanel.add(resultsText);
        resultsText.setPreferredSize(new Dimension(240,100));        
        resultsPanel.setPreferredSize(new Dimension(240,120));        
      final JPanel jobsPanel = new JPanel(new FlowLayout());
        jobsPanel.add(jobsButton);
        jobsPanel.add(jobsSpacer);
        jobsPanel.add(jobsText);
        jobsText.setPreferredSize(new Dimension(240,100));        
        jobsPanel.setPreferredSize(new Dimension(240,120));
      buttonGrid.removeAll();
      buttonGrid.add(modelsPanel);
      buttonGrid.add(resultsPanel);
      buttonGrid.add(jobsPanel);
      final JPanel emptyPanel = new JPanel();
      final JPanel exitPanel = new JPanel(new GridLayout(2,1));
      exitPanel.add(emptyPanel);
      final JPanel exitButtonPanel = new JPanel(new FlowLayout());
      exitButtonPanel.add(updateButton);
      updateButton.setVisible(false);
      exitButtonPanel.add(exitButton);
      exitPanel.add(exitButtonPanel);
    
      getContentPane().add(titlePanel,BorderLayout.NORTH);
      getContentPane().add(buttonGrid,BorderLayout.CENTER);
      getContentPane().add(exitPanel,BorderLayout.SOUTH);
      exitButton.addActionListener(eh);
      modelsButton.addActionListener(eh);
      jobsButton.addActionListener(eh);
      updateButton.addActionListener(eh);
      resultsButton.addMouseListener(eh);
      modelsButton.addMouseListener(eh);
      jobsButton.addMouseListener(eh);
      resultsButton.addActionListener(eh);
      modelsText.addMouseListener(eh);
      jobsText.addMouseListener(eh);
      resultsText.addMouseListener(eh);
      jl = new JobLauncher(this); 
      sr = new ShowResults();
      mc = new ModelChooser(this);
      vp = new VersionChooser(this);
      uv = new UpdateVEW(this);
      updateButton.setVisible(uv.needsUpdating());
      pack();
  }
    
  public void showPanel(JPanel p) {
    getContentPane().remove(buttonGrid);
    getContentPane().remove(titlePanel);
    if (p==jl) jl.startJobs();
    getContentPane().add(p,BorderLayout.CENTER);
    exitButton.setText("Back");
    getContentPane().paint(getContentPane().getGraphics());
    currentPanel=p;
    pack();
  }
  
  public void showVersionPanel(String path) {
    getContentPane().remove(buttonGrid);
    getContentPane().remove(currentPanel);
    vp.setPath(path);
    vp.refresh();
    vp.selectLast();
    getContentPane().add(vp,BorderLayout.CENTER);
    exitButton.setText("Back");
    getContentPane().paint(getContentPane().getGraphics());
    currentPanel=vp;
    pack();
  }
  
  public void gotoJobs() {
    getContentPane().remove(currentPanel);
    jl.startJobs();
    getContentPane().add(jl,BorderLayout.CENTER);
    exitButton.setText("Back");
    getContentPane().paint(getContentPane().getGraphics());
    currentPanel=jl;
    pack();
    jumpToJobs=false;
  }

  

  
  class EventHandler implements ActionListener, MouseListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==updateButton) {
        uv.prepareDialog();
        uv.setVisible(true);
        if (!uv.needsUpdating()) { // Update was done - restart!
          JOptionPane.showMessageDialog(TitlePage.this,"VEW has been updated, and will now close. Please click OK, then restart the VEW.");
          jl.stopJobs();
          setVisible(false);
          System.exit(0);
        }
        
      } else if (e.getSource()==exitButton) {
        if (exitButton.getText().equals("Exit")) {
          jl.stopJobs();
          setVisible(false);
          System.exit(0);
          
        } else {
          jl.stopJobs();
          getContentPane().remove(currentPanel);
          getContentPane().add(buttonGrid,BorderLayout.CENTER);
          getContentPane().add(titlePanel,BorderLayout.NORTH);
          exitButton.setText("Exit");
          pack();
          getContentPane().paint(getContentPane().getGraphics());
        }
          
      } else if (e.getSource()==modelsButton) {
        
        mc.refresh();
        modelsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+buildModels+"</u></font><br>"+buildModelsText+"</html>");
        showPanel(mc);
      } else if (e.getSource()==jobsButton) {
        jobsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+viewJobs+"</u></font><br>"+viewJobsText+"</html>");
        showPanel(jl);
      } else if (e.getSource()==resultsButton) {
        resultsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+analyseResults+"</u></font><br>"+analyseResultsText+"</html>");
        sr.refresh();
        showPanel(sr);
      }
    }
    public void mouseClicked(MouseEvent e) {
      if (e.getSource()==modelsText) {
        mc.refresh();
        modelsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+buildModels+"</u></font><br>"+buildModelsText+"</html>");
        showPanel(mc);
      } else if (e.getSource()==resultsText) {
        resultsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+analyseResults+"</u></font><br>"+analyseResultsText+"</html>");
        sr.refresh();
        showPanel(sr);
      } else if (e.getSource()==jobsText) {
        jobsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+viewJobs+"</u></font><br>"+viewJobsText+"</html>");
        showPanel(jl);
      }
    }
    
    public void mouseEntered(MouseEvent e) {
      if ((e.getSource()==modelsText) || (e.getSource()==modelsButton)) {
        modelsText.setText("<html><font face='Arial' color='#ff0000' size='5'><u>"+buildModels+"</u></font><br>"+buildModelsText+"</html>");
        modelsText.setCursor(thePointyFinger);
        modelsButton.setCursor(thePointyFinger);
      } else if ((e.getSource()==resultsText) || (e.getSource()==resultsButton)) { 
        resultsText.setText("<html><font face='Arial' color='#ff0000' size='5'><u>"+analyseResults+"</u></font><br>"+analyseResultsText+"</html>");
        resultsText.setCursor(thePointyFinger);
        resultsButton.setCursor(thePointyFinger);
      } else if ((e.getSource()==jobsText) || (e.getSource()==jobsButton)) {
        jobsText.setText("<html><font face='Arial' color='#ff0000' size='5'><u>"+viewJobs+"</u></font><br>"+viewJobsText+"</html>");
        jobsText.setCursor(thePointyFinger);
        jobsButton.setCursor(thePointyFinger);
      }
    }

    public void mouseExited(MouseEvent e) {
      if ((e.getSource()==modelsText) || (e.getSource()==modelsButton)) {
        modelsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+buildModels+"</u></font><br>"+buildModelsText+"</html>");
        modelsText.setCursor(theNormalMouse);
        modelsButton.setCursor(theNormalMouse);
      } else if ((e.getSource()==resultsText) || (e.getSource()==resultsButton)) { 
        resultsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+analyseResults+"</u></font><br>"+analyseResultsText+"</html>");
        resultsText.setCursor(theNormalMouse);
        resultsText.setCursor(theNormalMouse);
      } else if ((e.getSource()==jobsText) || (e.getSource()==jobsButton)) {
        jobsText.setText("<html><font face='Arial' color='#000000' size='5'><u>"+viewJobs+"</u></font><br>"+viewJobsText+"</html>");
        jobsText.setCursor(theNormalMouse);
        jobsButton.setCursor(theNormalMouse);
      }
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
  }
}
  
  
