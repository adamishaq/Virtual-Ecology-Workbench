package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.zip.GZIPInputStream;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Compiler2.ModelCompiler;

public class RunPanel extends JPanel {
  private final EventHandler eh = new EventHandler();
  private XMLTag theModel;
  private VEWController2 vc2;

  // Components for the batch control panel
  
  private final BatchTableModel batchTableModel = new BatchTableModel();
  private final JTable batchTable = new JTable(batchTableModel);
  private final JButton addBatch = new JButton("Add Batch");
  private final JButton removeBatch = new JButton("Remove Batch");
  private static final String jobString = new String("Jobs to run: ");
  private final JLabel jobCountLabel = new JLabel(jobString+"1");
  
  // Components for the restart files panel
  
  private final JCheckBox loadRestart = new JCheckBox("Load checkpoint");
  private final JButton restartToLoad = new JButton("Choose File");
  private String restartFile;
  private final JRadioButton continueRND = new JRadioButton("Continue random number stream");
  private final JRadioButton newRND = new JRadioButton("Reset random seed");
  private final ButtonGroup RNDGroup = new ButtonGroup();
  private final JCheckBox saveRestart = new JCheckBox("Write checkpoints every");
  private final JLabel firstRestartLabel = new JLabel("starting at");
  private final JButton firstRestart = new JButton("");
  private final JCheckBox skipBoundary = new JCheckBox("Skip boundary conditions to match checkpoint");
  private final JTextField intervalText = new JTextField(5);
  private final JComboBox intervalUnit = new JComboBox();
  private final GregorianCalendar startSim = new GregorianCalendar(DateDialog.GMTTimeZone);
  private final GregorianCalendar endSim = new GregorianCalendar(DateDialog.GMTTimeZone);
  private final GregorianCalendar firstDateCal = new GregorianCalendar(DateDialog.GMTTimeZone);
  
  private final JButton submitJobs = new JButton("Submit Job(s)");
  private static int lockEvents = 0;
  
  public static String YEARS = new String("Years");
  public static String DAYS = new String("Days");
  public static String WEEKS = new String("Weeks");
  public static String HOURS = new String("Hours");
  
  private BatchChooser bc;
  private DateDialog dd;
  
  public boolean greenLight(boolean fix) {
    boolean ok = true;
    StringBuffer errorString = new StringBuffer();
    if (theModel.getTag("jobs")==null) {
      if (fix) {
        theModel.addTag(new XMLTag("jobs"));
        theModel.getTag("jobs").addTag("seeds",String.valueOf((int)Math.floor(10000.0*Math.random())));
        theModel.getTag("jobs").addTag("restart");
        theModel.getTag("jobs").getTag("restart").setAttribute("load","false");
        theModel.getTag("jobs").getTag("restart").setAttribute("save","false");
        theModel.getTag("jobs").getTag("restart").setAttribute("skipboundary","true");        
        theModel.getTag("jobs").getTag("restart").setAttribute("file","");
        theModel.getTag("jobs").getTag("restart").setAttribute("rnd","continue");
        theModel.getTag("jobs").getTag("restart").setAttribute("interval","1");
        theModel.getTag("jobs").getTag("restart").setAttribute("intervalunit","Years");        
        errorString.append("Created default job\n");
      } else ok=false;
    }
    if (ok) {
      if (theModel.getTag("jobs/restart").getAttribute("skipboundary")==null) theModel.getTag("jobs").getTag("restart").setAttribute("skipboundary","true");
      MiscUtils.findDateLimits(theModel,startSim,endSim);
      boolean saveRestartTag = (theModel.getTag("jobs/restart").getAttribute("save").equals("true"));
      if (saveRestartTag) {
        long restartTime = Long.parseLong(theModel.getTag("jobs/restart").getAttribute("first"));
        if (restartTime<startSim.getTimeInMillis()) {
          if (fix) {
            theModel.getTag("jobs/restart").setAttribute("first",String.valueOf(startSim.getTimeInMillis()));
            errorString.append("First restart file time was before simulation start\n");
          } else ok=false;
        }
        if (ok) {
          if (restartTime>endSim.getTimeInMillis()) {
            if (fix) {
              theModel.getTag("jobs/restart").setAttribute("first",String.valueOf(startSim.getTimeInMillis()));
              errorString.append("First restart file time was after simulation end\n");
            } else ok=false;
          }
        }
      }
    }
   
    if (fix) {
      if (errorString.length()>5) {
        MessageBox.showMessage(errorString.toString(),this);
        vc2.unsaved(true);
      }
      populateBatchTable();
      sortOtherBits();
      updateJobCount();
      updateButtons();
    }
    return ok;
  }
  
  public static void getLock(String file) {
    try {
      File f = new File(file);
      while (f.createNewFile()==false) { }
    } catch (Exception e) {}
  }
  
  public static void releaseLock(String file) {
    File f = new File(file);
    if (f.exists()) f.delete();
  }
    
  
  
  public RunPanel(VEWController2 _parent, XMLTag _model) {
    vc2 = _parent;
    theModel = _model;
    dd = new DateDialog(vc2,1800);
    bc = new BatchChooser();
    //opd = new OutputPathDialog();
    JPanel everything = new JPanel(new BorderLayout(0,10));

    JPanel batchPanel = new JPanel(new BorderLayout());
      batchPanel.setBorder(new EtchedBorder());
      JPanel batchTitle = new JPanel(new FlowLayout());
      batchTitle.add(new JLabel("<html><u><font color='#00008f'>CREATE A SET OF JOBS TO LAUNCH</font></u></html>"));
      batchPanel.add(batchTitle,BorderLayout.NORTH);      
      JPanel batchTablePanel = new JPanel(new BorderLayout());
        JScrollPane batchScroller = new JScrollPane(batchTable);
          batchTablePanel.add(batchScroller,BorderLayout.CENTER);
          batchScroller.setPreferredSize(new Dimension(750,250));
        JPanel jobCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
          jobCountPanel.add(jobCountLabel);
          batchTablePanel.add(jobCountPanel,BorderLayout.SOUTH);
      batchPanel.add(batchTablePanel,BorderLayout.CENTER);
    
      JPanel batchButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       batchButtonPanel.add(addBatch);
       batchButtonPanel.add(removeBatch);
       batchPanel.add(batchButtonPanel,BorderLayout.SOUTH);
       batchTableModel.setColumnCount(2);
       batchTableModel.setColumnIdentifiers(new String[] {"Name", "Value(s) - comma separated"});
       everything.add(batchPanel,BorderLayout.NORTH);
     
    JPanel checkpointPanel = new JPanel(new BorderLayout());
    checkpointPanel.setPreferredSize(new Dimension(754,160));
    checkpointPanel.setBorder(new EtchedBorder());
    JPanel titleCheck = new JPanel(new FlowLayout());
    titleCheck.add(new JLabel("<html><u><font color='#00008f'>SET UP CHECKPOINTS (RESTART FILES)</font></u></html>"));
    checkpointPanel.add(titleCheck,BorderLayout.NORTH);
    JPanel loadPanel = new JPanel(new BorderLayout());
        JPanel loadPanel1 = new JPanel(new FlowLayout());
          loadPanel1.add(loadRestart);
          loadPanel1.add(restartToLoad);
        JPanel loadPanel2 = new JPanel(new FlowLayout());
          loadPanel2.add(newRND);
          loadPanel2.add(continueRND);
          RNDGroup.add(newRND);
          RNDGroup.add(continueRND);
        JPanel loadPanel3 = new JPanel(new FlowLayout());
          loadPanel3.add(skipBoundary);
          loadPanel.add(loadPanel1,BorderLayout.NORTH);
          loadPanel.add(loadPanel2,BorderLayout.CENTER);
          loadPanel.add(loadPanel3,BorderLayout.SOUTH);
        checkpointPanel.add(loadPanel,BorderLayout.CENTER);
      JPanel savePanel = new JPanel(new FlowLayout());
        savePanel.add(saveRestart);
        savePanel.add(intervalText);
        savePanel.add(intervalUnit);
        savePanel.add(firstRestartLabel);
        savePanel.add(firstRestart);
        intervalText.setPreferredSize(new Dimension(80,20));
        checkpointPanel.add(savePanel,BorderLayout.SOUTH);
        everything.add(checkpointPanel,BorderLayout.SOUTH);
      add(everything,BorderLayout.CENTER);
      JPanel submitPanel = new JPanel(new FlowLayout());
      submitPanel.add(submitJobs);
      add(submitPanel,BorderLayout.SOUTH);
    
    intervalUnit.addItem(HOURS);
    intervalUnit.addItem(DAYS);
    intervalUnit.addItem(WEEKS);    
    intervalUnit.addItem(YEARS);    
    removeBatch.setEnabled(false);   
    addBatch.addActionListener(eh);
    removeBatch.addActionListener(eh);
    batchTableModel.addTableModelListener(eh);
    submitJobs.addActionListener(eh);
    intervalText.addFocusListener(eh);
    batchTable.addMouseListener(eh);
    firstRestart.setEnabled(false);
    intervalUnit.setEnabled(false);
    intervalText.setEnabled(false);
    restartToLoad.setEnabled(false);
    skipBoundary.setEnabled(false);
    loadRestart.addActionListener(eh);
    saveRestart.addActionListener(eh);
    newRND.addActionListener(eh);
    continueRND.addActionListener(eh);
    intervalUnit.addActionListener(eh);
    restartToLoad.addActionListener(eh);
    skipBoundary.addActionListener(eh);
    firstRestart.addActionListener(eh);
    newRND.setEnabled(false);
    continueRND.setEnabled(false);
  }
  
 
  
  public void populateBatchTable() {
    lockEvents++;
    while (batchTableModel.getRowCount()>0) batchTableModel.removeRow(0);
    batchTableModel.addRow(new String[] {"seeds", theModel.getValue("jobs/seeds").toString()});
    XMLTag[] batches = theModel.getTag("jobs").getTags("batch");
    for (int i=0; i<batches.length; i++) {
      batchTableModel.addRow(new String[] {batches[i].getAttribute("name"),batches[i].getAttribute("vals")});
    }
    lockEvents--;
  }
  
  public void sortOtherBits() {
    lockEvents++;
    
    dd.setEarliest(startSim);
    dd.setLatest(endSim);
    loadRestart.setSelected(theModel.getTag("jobs/restart").getAttribute("load").equals("true"));
    saveRestart.setSelected(theModel.getTag("jobs/restart").getAttribute("save").equals("true"));
    if (saveRestart.isSelected()) {
      StringTools.setStringItem(intervalUnit,theModel.getTag("jobs/restart").getAttribute("intervalunit"));
      intervalText.setText(theModel.getTag("jobs/restart").getAttribute("interval"));
      long firstMillis = Long.parseLong(theModel.getTag("jobs/restart").getAttribute("first"));
      firstDateCal.setTimeInMillis(firstMillis);
      firstRestart.setText(DateDialog.getString(firstDateCal));
    } else {
      firstDateCal.setTimeInMillis(startSim.getTimeInMillis());
      firstRestart.setText(DateDialog.getString(startSim));
    }
    if (theModel.getTag("jobs/restart").getAttribute("rnd")==null) continueRND.setSelected(true);
    else if (theModel.getTag("jobs/restart").getAttribute("rnd").equals("new")) newRND.setSelected(true);
    else continueRND.setSelected(true);
    if (theModel.getTag("jobs/restart").getAttribute("skipboundary")==null) skipBoundary.setSelected(true);
    else skipBoundary.setSelected(theModel.getTag("jobs/restart").getAttribute("skipboundary").equals("true"));
    if (theModel.getTag("jobs/restart").getAttribute("file")!=null) 
      restartFile=theModel.getTag("jobs/restart").getAttribute("file");
    lockEvents--;
  }
  
  
  public void updateJobCount() {
    lockEvents++;
    int running=1;
    for (int i=0; i<batchTableModel.getRowCount(); i++) {
      String s = batchTableModel.getValueAt(i,1).toString();
      String[] splits = s.split(",");
      StringBuffer newString = new StringBuffer();
      boolean writtenFirst=false;
      for (int j=0; j<splits.length; j++) {
        try {
          Double.parseDouble(splits[j]);
          if (writtenFirst) newString.append(',');
          newString.append(splits[j]);
          writtenFirst=true;
        } catch (NumberFormatException nfe) {}
      }
      s = newString.toString();
      batchTableModel.setValueAt(s,i,1);
      running *= s.split(",").length;
    }
    jobCountLabel.setText(jobString+" "+String.valueOf(running));
    lockEvents--;
  }
  
  public void updateButtons() {
    removeBatch.setEnabled((batchTable.getSelectedRowCount()==1) && (batchTable.getSelectedRow()>0));
    firstRestart.setEnabled(saveRestart.isSelected());
    intervalUnit.setEnabled(saveRestart.isSelected());
    intervalText.setEnabled(saveRestart.isSelected());
    restartToLoad.setEnabled(loadRestart.isSelected());
    skipBoundary.setEnabled(loadRestart.isSelected());
    newRND.setEnabled(loadRestart.isSelected());
    continueRND.setEnabled(loadRestart.isSelected());
    boolean okSubmit = true;
    if (saveRestart.isSelected()) {
      try { Integer.parseInt(intervalText.getText());
      } catch (Exception e) { okSubmit = false; }
    }
    submitJobs.setEnabled(okSubmit);
  }
  
  class BatchTableModel extends DefaultTableModel {
    public boolean isCellEditable(int col, int row) { return (row==1); }
  }
  
  class EventHandler implements ActionListener, MouseListener, TableModelListener, FocusListener {
    
    public void actionPerformed(ActionEvent e) {
     if (e.getSource()==addBatch) {
        lockEvents++;
        bc.populateSpecies();
        if (bc.speciesListModel.size()>0) bc.speciesList.setSelectedIndex(0);
        bc.populateVarChooser();
        bc.updateBCButtons();
        bc.setVisible(true);
        String var = bc.getVar();
        if (var.length()>0) {
          String species = bc.getSpecies();
          String vals = bc.getValues();
          XMLTag newBatch = new XMLTag("batch");
          newBatch.setAttribute("name",species+": "+var);
          newBatch.setAttribute("vals",vals);
          theModel.getTag("jobs").addTag(newBatch);
          batchTableModel.addRow(new String[] {species+": "+var, vals});
          updateJobCount();
        }
        vc2.unsaved(false);
        lockEvents--;
      
      } else if (e.getSource()==removeBatch) {
        lockEvents++;
        theModel.getTag("jobs").getTagWhere("batch","@name",batchTableModel.getValueAt(batchTable.getSelectedRow(),0).toString()).removeTagFromParent();
        batchTableModel.removeRow(batchTable.getSelectedRow());
        batchTable.clearSelection();
        updateJobCount();
        vc2.unsaved(false);
        lockEvents--;
   
      } else if (e.getSource()==loadRestart) {
        theModel.getTag("jobs/restart").setAttribute("load",String.valueOf(loadRestart.isSelected()));
//        if (!loadRestart.isSelected())
          //theModel.getTag("jobs/restart").setAttribute("snaptime","0");
        //else 
//          theModel.getTag("jobs/restart").setAttribute("snaptime","0");
        updateButtons();
        vc2.unsaved(false);
        
      } else if (e.getSource()==saveRestart) {
        theModel.getTag("jobs/restart").setAttribute("save",String.valueOf(saveRestart.isSelected()));
        theModel.getTag("jobs/restart").setAttribute("first",String.valueOf(firstDateCal.getTimeInMillis()));
        
        updateButtons();
        vc2.unsaved(false);
      
      } else if (e.getSource()==newRND) {
        if (newRND.isSelected()) theModel.getTag("jobs/restart").setAttribute("rnd","new");
        else theModel.getTag("jobs/restart").setAttribute("rnd","continue");
        vc2.unsaved(false);
      
      } else if (e.getSource()==continueRND) {
        if (continueRND.isSelected()) theModel.getTag("jobs/restart").setAttribute("rnd","continue");
        else theModel.getTag("jobs/restart").setAttribute("rnd","new");
        vc2.unsaved(false);
        
      } else if (e.getSource()==skipBoundary) {
        theModel.getTag("jobs/restart").setAttribute("skipboundary",String.valueOf(skipBoundary.isSelected()));
        vc2.unsaved(false);
      
      } else if (e.getSource()==intervalUnit) {
        theModel.getTag("jobs/restart").setAttribute("intervalunit",intervalUnit.getSelectedItem().toString());
        updateButtons();
        vc2.unsaved(false);
        
      } else if (e.getSource()==firstRestart) {
        dd.show(startSim,endSim,firstDateCal);
        firstDateCal.setTimeInMillis(dd.getDate().getTimeInMillis());
        firstRestart.setText(DateDialog.getString(firstDateCal));
        theModel.getTag("jobs/restart").setAttribute("first",String.valueOf(firstDateCal.getTimeInMillis()));
        vc2.unsaved(false);
        
      } else if (e.getSource()==restartToLoad) {
        JFileChooser jfc = new JFileChooser();
        SnapFileFilter sff = new SnapFileFilter();
        jfc.setFileFilter(sff);
        if (restartFile==null) restartFile="";
        jfc.setSelectedFile(new File(restartFile));
        
        if (jfc.showOpenDialog(jfc) == JFileChooser.APPROVE_OPTION) {
          String path = jfc.getSelectedFile().getAbsolutePath();
          restartFile=new String(path);
          try {
            BufferedInputStream b1 = new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(new File(restartFile)))));
            StringBuffer sb1 = new StringBuffer();
            final DataInputStream d1 = new DataInputStream(b1);
            for (int i=0; i<6; i++) sb1.append(d1.readChar());
            if (!sb1.toString().equals("VEW-CP")) {
              JOptionPane.showMessageDialog(vc2,"This does not seem to be a VEW 3.1 restart file");
              if (theModel.getTag("jobs/restart").getAttribute("file")!=null) theModel.getTag("jobs/restart").removeAttribute("file");
            } else {
              int ver1 = d1.readInt();
              if (ver1!=739) {
                JOptionPane.showMessageDialog(vc2,"This snapshot version is from an older VEW version");
                if (theModel.getTag("jobs/restart").getAttribute("file")!=null) theModel.getTag("jobs/restart").removeAttribute("file");
              } else {
                long t1 = d1.readLong();
                theModel.getTag("jobs/restart").setAttribute("snaptime",String.valueOf(t1));
                theModel.getTag("jobs/restart").setAttribute("file",restartFile);
              }
            }
            b1.close();
          } catch (Exception ex) { ex.printStackTrace(); }
          
          vc2.unsaved(false);
        } 
        
      
      } else if (e.getSource()==submitJobs) {
        if (vc2.isUnSaved()) {
          int ok=JOptionPane.showConfirmDialog(vc2,"Your model will be saved before compiling. Ok to continue?");
          if (ok==JOptionPane.OK_OPTION) vc2.doSave();
        }
        if (!vc2.isUnSaved()) {
          String firstJobNo = null;
          //opd.initialise();
          //opd.setVisible(true);
          //if (opd.goAhead()) {
            // Add the jobs to the job queue
            XMLTag[] batchTags = theModel.getTag("jobs").getTags("batch");
            String[] seeds = theModel.getValue("jobs/seeds").split(",");
            int jobs = 1;
            String[][] batches = new String[batchTags.length][];
            for (int i=0; i<batchTags.length; i++) {
              batches[i] = batchTags[i].getAttribute("vals").split(",");
              jobs*=batches[i].length;
            }
            int[] batchCounters = new int[batchTags.length];
            StringBuffer title,path;
            long pid = -1;
            for (int i=0; i<seeds.length; i++) {
              int job=0;
              for (int j=0; j<batchTags.length; j++) batchCounters[j]=0;      
              while (job<jobs) {
                title = new StringBuffer();
                title.append("Seed: "+seeds[i]);
                path = new StringBuffer();
                path.append("Seed");
                path.append(seeds[i]);
                for (int j=0; j<batchTags.length; j++) {
                  title.append(", ");
                  String name = batchTags[j].getAttribute("name");
                  String val = batches[j][batchCounters[j]];
                  name = name.substring(name.indexOf(":")+2);
                  name = name.substring(0,name.indexOf("(")-1);
                  title.append(name+":"+val);
                  path.append(name);
                  path.append(val);
                }
                XMLTag vewTag = XMLFile.LoadFile("vew.xml").getTag("vew");
                if (vewTag.getTag("nextid")==null) vewTag.addTag(new XMLTag("nextid","1"));
                long id = Long.parseLong(vewTag.getValue("nextid"));
                vewTag.setValue("nextid",String.valueOf(id+1));
                vewTag.write();
                if (firstJobNo==null) firstJobNo=String.valueOf(id);
                XMLTag newJob = new XMLTag("job");
                newJob.setAttribute("running",JobLauncher.JOB_COMPILING);
                newJob.setAttribute("output","VEWTemp"+File.separator+String.valueOf(firstJobNo));
                newJob.setAttribute("vewid",String.valueOf(id));
                if (pid==-1) pid=id;
                newJob.setAttribute("leadid",String.valueOf(pid));
                String jobName = theModel.getValue("buildname")+"_Seed"+seeds[i]+"_Job"+String.valueOf(job)+"_"+String.valueOf(id);
                newJob.setAttribute("name",jobName);
                getLock("jobs.xml.lock");
                XMLTag jobTag = XMLFile.LoadFile("jobs.xml").getTag("jobfile/jobs");
                jobTag.addTag(newJob);
                jobTag.write();
                releaseLock("jobs.xml.lock");
                int j=0;
                boolean done=false;
                if (job<jobs-1) {
                  while (!done) {
                    batchCounters[j]++;
                    if (batchCounters[j]<batches[j].length) done=true;
                    else {
                      batchCounters[j]=0;
                      j++;
                    }
                  }
                }
                job++;
              }
            }
            try {
              String compilePath = "VEWTemp";
              String modelPath = vc2.getModelPath();
              String nocMode = VEWController2.NOC_Mode?" /noc":"";
              
              ModelCompiler.compile(modelPath,compilePath,String.valueOf(pid));
              //Runtime.getRuntime().exec("java -Xmx500m -classpath ."+File.pathSeparator+"jgrib.jar"+File.pathSeparator+"tools.jar VEW.Compiler2.ModelCompiler "+modelPath+" "+compilePath+" "+String.valueOf(pid)+nocMode);
              //System.out.println("java -Xmx1g -classpath ."+File.pathSeparator+"jgrib.jar"+File.pathSeparator+"tools.jar VEW.Compiler2.ModelCompiler "+modelPath+" "+compilePath+" "+String.valueOf(pid));
            } catch (Exception ex) { ex.printStackTrace(); }
            int ok=JOptionPane.showConfirmDialog(vc2,"Job(s) submitted. Proceed to Job Controller?");
            if (ok==JOptionPane.YES_OPTION) vc2.exitToJobs();
          //} 
        }
      }
    }

    public void mouseClicked(MouseEvent e) {
      if (e.getSource()==batchTable) {
        updateButtons();
      }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void tableChanged(TableModelEvent e) {
      if ((e.getSource()==batchTableModel) && (lockEvents==0)) {
        int row = e.getFirstRow();
        int col = e.getColumn();
        if (row==0) {
          theModel.getTag("jobs/seeds").setValue(batchTableModel.getValueAt(row,col).toString());
        } else {
          theModel.getTag("jobs").getTags("batch")[row-1].setAttribute("vals",batchTableModel.getValueAt(row,col).toString());
        }
        updateJobCount();
        updateButtons();
      }
    }


    public void focusGained(FocusEvent arg0) {}

    public void focusLost(FocusEvent e) {
      if (e.getSource()==intervalText) {
        long interval = 0;
        try {
          interval = Long.parseLong(intervalText.getText());
        } catch (Exception ex) {}
        theModel.getTag("jobs/restart").setAttribute("interval",String.valueOf(interval));
        theModel.getTag("jobs/restart").setAttribute("intervalunit",intervalUnit.getSelectedItem().toString());
        updateButtons();
        vc2.unsaved(false);
      }
    }
  
  }
  
  class BatchChooser extends JDialog {
    private DefaultListModel speciesListModel = new DefaultListModel();
    private JList speciesList = new JList(speciesListModel);  
    private DefaultListModel varListModel = new DefaultListModel();
    private JList varList = new JList(varListModel);
    private JLabel valuesLabel = new JLabel("Values (comma-separated)");
    private JTextField valuesText = new JTextField(20);
    private int lockBatchEvents = 0;
    private JButton okButton = new JButton("Ok");
    private JButton cancelButton = new JButton("Cancel");
    private BatchEventHandler bceh = new BatchEventHandler();
    private String _varName = "";
    private String _species = "";
    private String _values = "";
    
    public String  getVar() { return _varName; }
    public String getSpecies() { return _species; }
    public String getValues() { return _values; }
    
    public void populateVarChooser() {
      lockBatchEvents++;
      varListModel.clear();
      if (speciesList.getSelectedValue()!=null) {
        String species = speciesList.getSelectedValue().toString();
        XMLTag theSpecies = theModel.getTag("species").getTagWhere("species","@name",species);
        XMLTag theFG = theModel.getTagWhere("functionalgroup","name",theSpecies.getAttribute("fg"));
        XMLTag[] params = theSpecies.getTags("param");
        ArrayList a = new ArrayList();
        for (int i=0; i<params.length; i++) a.add(params[i].getAttribute("name"));
        ArrayList sorted = new ArrayList();
        while (a.size()>0) {
          String s = a.get(0).toString();
          int index=0;
          for (int i=1; i<a.size(); i++) {
            if (StringTools.nonSpace(a.get(i).toString()).compareToIgnoreCase(StringTools.nonSpace(s))<0) {
              index=i;
              s = a.get(i).toString();
            }
          }
          sorted.add(s);
          a.remove(index);
        }
        for (int i=0; i<sorted.size(); i++) {
          String s = sorted.get(i).toString();
          String desc = theFG.getTagWhere("parameter","name",s).getValue("desc");
          varListModel.addElement(s+" ("+desc+")");
        }
        lockBatchEvents--;
      }
    }
    
    public void populateSpecies() {
      lockBatchEvents++;
      XMLTag[] species = theModel.getTag("species").getTags("species");
      speciesListModel.removeAllElements();
      for (int i=0; i<species.length; i++) speciesListModel.addElement(species[i].getAttribute("name"));
      lockBatchEvents--;      
    }
    
    public BatchChooser() {
      super(vc2,"Choose Batch Variable",true);
      lockBatchEvents++;
      getContentPane().setLayout(new BorderLayout());
      JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel tablesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
          JScrollPane speciesListScroller = new JScrollPane(speciesList);
          speciesListScroller.setPreferredSize(new Dimension(250,200));
          JScrollPane varListScroller = new JScrollPane(varList);
          varListScroller.setPreferredSize(new Dimension(250,200));
          tablesPanel.add(speciesListScroller);
          tablesPanel.add(varListScroller);
        mainPanel.add(tablesPanel,BorderLayout.CENTER);
        JPanel valPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
          valPanel.add(valuesLabel);
          valPanel.add(valuesText);
        mainPanel.add(valPanel,BorderLayout.SOUTH);
        getContentPane().add(mainPanel,BorderLayout.CENTER);
      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
      getContentPane().add(buttonPanel,BorderLayout.SOUTH);
      speciesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      varList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      okButton.addActionListener(bceh);
      cancelButton.addActionListener(bceh);
      speciesList.addListSelectionListener(bceh);
      varList.addListSelectionListener(bceh);
      varList.clearSelection();
      valuesText.addCaretListener(bceh);
      lockBatchEvents--;
      pack();
    }
    
    public boolean checkVarParse() {
      if (valuesText.getText().length()==0) return false;
      else {
        String s = valuesText.getText().trim();
        String[] splits = s.split(",");
        if (splits.length==0) return false;
        else {
          boolean ok = true;
          int i=0;
          while ((i<splits.length) && (ok)) {
            try { Double.parseDouble(splits[i]);
            } catch (NumberFormatException ex) { ok=false; }
            i++;
          }
          return ok;
        }
      }
    }
    
    public void updateBCButtons() {
      boolean ok = (varList.getSelectedIndices().length==1);
      if (ok) {
        String theName = speciesList.getSelectedValue().toString()+": "+varList.getSelectedValue().toString();
        if (theModel.getTag("jobs").getTagWhere("batch","@name",theName)!=null) ok=false;
      }
      if (ok) ok = checkVarParse();
      okButton.setEnabled(ok);
    }
    
    class BatchEventHandler implements ActionListener, ListSelectionListener, CaretListener {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==okButton) {
          _varName = varList.getSelectedValue().toString();
          _species = speciesList.getSelectedValue().toString();
          _values = valuesText.getText();
          setVisible(false);
          
        } else if (e.getSource()==cancelButton) {
          _varName = "";
          _species = "";
          _values = "";
          setVisible(false);
          
        }
      }

      public void valueChanged(ListSelectionEvent e) {
        if ((e.getSource()==speciesList) && (lockBatchEvents==0)) {
          populateVarChooser();
          updateBCButtons();
        } else if ((e.getSource()==varList) && (lockBatchEvents==0)) {
          updateBCButtons();
        }
      }

      public void caretUpdate(CaretEvent e) {
        if (e.getSource()==valuesText) updateBCButtons();
      }
    }
  }
  
  class SnapFileFilter extends FileFilter {
    public boolean accept(java.io.File arg0) {
      return ((arg0.getName().toUpperCase().startsWith("SNAP.")) || (arg0.isDirectory()));
    }

    public String getDescription() {
      return new String("Restart Files");
    }
  }
}

