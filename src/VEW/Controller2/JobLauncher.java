package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import VEW.Analyser4.Analyser4;
import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Compiler2.ModelCompiler;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3FileHandle;
import ch.ethz.ssh2.Session;

public class JobLauncher extends JPanel {
  private JComboBox resourceList = new JComboBox();
  private JTextArea script = new JTextArea();
  private JLabel hostLabel = new JLabel("Host:");
  private JLabel typeLabel = new JLabel("Type:");
  private JLabel userLabel = new JLabel("Username:");
  private JLabel pwdLabel = new JLabel("Password:");
  private JLabel dirLabel = new JLabel("Results Root Dir:");
  private JLabel memLabel = new JLabel("Memory (Mb):");
  private JLabel wallLabel = new JLabel("Max Time (h:m:s):");
  private JLabel runScript = new JLabel("Run Script:");
  private JLabel queueCommLabel = new JLabel("Queue command:");
  
  private JTextField host = new JTextField("");
  private JComboBox type = new JComboBox();
  private JTextField user = new JTextField("");
  private JPasswordField pwd = new JPasswordField("");
  private JTextField dir = new JTextField("");
  private JTextField mem = new JTextField("");
  private JTextField wall = new JTextField("");
  private JTextField queue = new JTextField(""); 
  private JCheckBox server = new JCheckBox("Use Java Server Mode");
  
  private JButton newResource = new JButton("New");
  private JButton saveResource = new JButton("Save");
  private JButton renameResource = new JButton("Rename");
  private JButton removeResource = new JButton("Remove");
  private JButton copyResource = new JButton("Copy");
  
  private DefaultTableModel jobTableModel = new DefaultTableModel();
  private JTable jobTable = new JTable(jobTableModel);
  
  private JButton launchJob = new JButton("Launch");
  private JButton launchLive = new JButton("Launch Live");
  private JButton analyse = new JButton("Analyse");
  private JButton abort = new JButton("Abort");
  private JButton clear = new JButton("Clear");    
  private XMLTag machineTag = null;
  private String machinePath = "Data"+File.separator+"Machines"+File.separator;
  private JLabel resultsLabel = new JLabel("Result Path: ");
  private JLabel compLabel = new JLabel("Launched on: ");
  private JLabel startLabel = new JLabel("Start Time: ");
  private JLabel endLabel = new JLabel("Predicted End Time: ");
  private JLabel progressLabel = new JLabel("Progress: ");
  private JLabel startTime = new JLabel("");
  private JLabel endTime = new JLabel("");
  private JLabel comp = new JLabel("");
  private JTextField resPath = new JTextField("");
  private JProgressBar progress = new JProgressBar();
  
  
  
  
  private EventHandler eh = null;
  private int lockEvents = 0;
  private static Timer updatePoll;
  private static TitlePage tp = null;
  
  private final static String SSH_SERVER = new String("SSH Server");
  private final static String THIS_COMPUTER = new String("Local Computer");
  private final static String DESKTOP = new String("- Desktop Computer");
  
  public static final String JOB_COMPILING = new String("Compiling");
  public static final String JOB_FINISHED = new String("Finished");
  public static final String JOB_READY = new String("Ready");
  public static final String JOB_RUNNING = new String("Running");
  public static final String JOB_ABORTED = new String("Aborted");
  public static final String JOB_ERROR= new String("Error Compiling Model - Click here");
  
  public void updateResourceList() {
    XMLTag select = machineTag.getTagWhere("machine","name",resourceList.getSelectedItem().toString());
    String templateFile = machinePath+select.getValue("template");
    script.setText("");
      
    StringBuffer text = new StringBuffer();
    try {
      BufferedReader br = new BufferedReader(new FileReader(templateFile));
      while (br.ready()) {
        text.append(br.readLine());
        text.append("\n");
      }
      br.close();
      script.setText(text.toString());
      type.setEnabled(true);
      StringTools.setStringItem(type,select.getValue("type"));
      if (type.getSelectedItem().equals(THIS_COMPUTER)) {
        host.setEnabled(false);
        wall.setEnabled(false);
        user.setEnabled(false);
        pwd.setEnabled(false);
        queue.setEnabled(false);
      } else if (type.getSelectedItem().equals(SSH_SERVER)) {
        host.setEnabled(true);
        wall.setEnabled(true);
        user.setEnabled(true);
        pwd.setEnabled(true);
        queue.setEnabled(true);
        host.setText(select.getValue("host"));
        user.setText(select.getValue("user"));
        pwd.setText(select.getValue("pwd"));
        wall.setText(select.getValue("wall"));
        queue.setText(select.getValue("queue"));
      }
      mem.setText(select.getValue("mem"));
      dir.setText(select.getValue("dir"));
      server.setSelected(select.getValue("server").equals("true"));
        
    } catch (Exception ex) {}
  }
  
  public void populateLists() {
    lockEvents++;
    String remember = "";
    if (resourceList.getSelectedItem()!=null) remember = resourceList.getSelectedItem().toString();
    resourceList.removeAllItems();
    machineTag = XMLFile.LoadFile(machinePath+"machines.xml");
    XMLTag[] machines = XMLTag.sortTags(machineTag.getTags("machine"),"name");
    for (int i=0; i<machines.length; i++)
      resourceList.addItem(machines[i].getValue("name"));
    StringTools.setStringItem(resourceList,remember);
    updateResourceList();
    updateButtons();
    type.removeAllItems();
    type.addItem(THIS_COMPUTER);
    type.addItem(SSH_SERVER);
    lockEvents--;    
  }
  
  public void updateJobList() {
    lockEvents++;
    String s="";
    if (jobTable.getSelectedRow()>=0) s = jobTableModel.getValueAt(jobTable.getSelectedRow(),0).toString();
    while (jobTableModel.getRowCount()>0) jobTableModel.removeRow(0);
    XMLTag[] jobs = XMLFile.LoadFile("jobs.xml").getTag("jobs").getTags("job");
    for (int i=0; i<jobs.length; i++)
      jobTableModel.addRow(new String[] {jobs[i].getAttribute("vewid"),jobs[i].getAttribute("name"),jobs[i].getAttribute("running")});
    if (s.length()>0) {
      int i=0;
      while (i<jobTableModel.getRowCount()) {
        if (jobTableModel.getValueAt(i,0).toString().equals(s)) jobTable.getSelectionModel().setSelectionInterval(i,i);
        i++;
      }
    }
    lockEvents--;
  }
  
  public JobLauncher(TitlePage _tp) {
    tp=_tp;
    lockEvents++;
    eh = new EventHandler();
    updatePoll = new Timer(5000,eh);
    setPreferredSize(new Dimension(760,651));    
    setLayout(new BorderLayout());
    JPanel topPanel = new JPanel(new GridLayout(1,2));
      JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel jobsPanel = new JPanel(new BorderLayout());
          jobsPanel.setBorder(new EtchedBorder());
          JScrollPane jobScroller = new JScrollPane(jobTable);
            jobScroller.setPreferredSize(new Dimension(200,100));
            jobsPanel.add(jobScroller,BorderLayout.CENTER);
            leftPanel.add(jobsPanel,BorderLayout.CENTER);
        JPanel jobDetails = new JPanel(new GridLayout(5,1));
          startLabel.setHorizontalAlignment(JLabel.RIGHT);
          progressLabel.setHorizontalAlignment(JLabel.RIGHT);
          endLabel.setHorizontalAlignment(JLabel.RIGHT);
          resultsLabel.setHorizontalAlignment(JLabel.RIGHT);
          compLabel.setHorizontalAlignment(JLabel.RIGHT);
          startLabel.setPreferredSize(new Dimension(120,25));
          endLabel.setPreferredSize(new Dimension(120,25));
          progressLabel.setPreferredSize(new Dimension(120,25));
          compLabel.setPreferredSize(new Dimension(120,25));
          resultsLabel.setPreferredSize(new Dimension(120,25));
          startTime.setPreferredSize(new Dimension(180,25));
          endTime.setPreferredSize(new Dimension(180,25));
          comp.setPreferredSize(new Dimension(180,25));
          resPath.setPreferredSize(new Dimension(180,25));
          resPath.setEditable(false);
          progress.setPreferredSize(new Dimension(180,25));
          progress.setStringPainted(true);
          progress.setMaximum(100);
          progress.setMinimum(0);
          progress.setValue(0);
          progress.setString("Not Available");
          
          
          JPanel startPanel = new JPanel(new FlowLayout());
            startPanel.add(startLabel);
            startPanel.add(startTime);
            jobDetails.add(startPanel);
          JPanel progressPanel = new JPanel(new FlowLayout());
            progressPanel.add(progressLabel);
            progressPanel.add(progress);
            jobDetails.add(progressPanel);
          JPanel endPanel = new JPanel(new FlowLayout());
            endPanel.add(endLabel);  
            endPanel.add(endTime);
            jobDetails.add(endPanel);
          JPanel onPanel = new JPanel(new FlowLayout());
            onPanel.add(compLabel);
            onPanel.add(comp);
            jobDetails.add(onPanel);
          JPanel pathPanel = new JPanel(new FlowLayout());
            pathPanel.add(resultsLabel);
            pathPanel.add(resPath);
            jobDetails.add(pathPanel);
          jobsPanel.add(jobDetails,BorderLayout.SOUTH);
        JPanel jobButtons1 = new JPanel(new FlowLayout());
          jobButtons1.add(launchJob);
          jobButtons1.add(launchLive);
          jobButtons1.add(analyse);
        JPanel jobButtons2 = new JPanel(new FlowLayout());
          jobButtons2.add(abort);
          jobButtons2.add(clear);
        JPanel jobButtons = new JPanel(new BorderLayout());
          jobButtons.add(jobButtons1,BorderLayout.NORTH);
          jobButtons.add(jobButtons2,BorderLayout.SOUTH);
          leftPanel.add(jobButtons,BorderLayout.SOUTH);
        topPanel.add(leftPanel);
      JPanel resourcePanel = new JPanel(new BorderLayout());
        JPanel resourceCreation = new JPanel(new BorderLayout());
          resourceCreation.add(resourceList,BorderLayout.NORTH);
        JPanel resourceButtons = new JPanel(new FlowLayout());
          resourceButtons.add(newResource);
          resourceButtons.add(saveResource);
          resourceButtons.add(copyResource);
          resourceButtons.add(renameResource);
          resourceButtons.add(removeResource);
          saveResource.setEnabled(false);
          resourceCreation.add(resourceButtons,BorderLayout.SOUTH);
          resourcePanel.add(resourceCreation,BorderLayout.NORTH);
        JPanel computerDetails = new JPanel(new GridLayout(9,1));
          computerDetails.setPreferredSize(new Dimension(320,224));
          JPanel hostPart = new JPanel(new FlowLayout());
            hostPart.add(hostLabel);
            hostPart.add(host);
            host.setPreferredSize(new Dimension(220,24));
            hostLabel.setPreferredSize(new Dimension(120,24));
            hostLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(hostPart);
          JPanel typePart = new JPanel(new FlowLayout());
            typePart.add(typeLabel);
            typePart.add(type);
            type.setPreferredSize(new Dimension(220,24));
            typeLabel.setPreferredSize(new Dimension(120,24));
            typeLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(typePart);
          JPanel userPart = new JPanel(new FlowLayout());
            userPart.add(userLabel);
            userPart.add(user);
            user.setPreferredSize(new Dimension(220,24));
            userLabel.setPreferredSize(new Dimension(120,24));
            userLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(userPart);
          JPanel pwdPart = new JPanel(new FlowLayout());
            pwdPart.add(pwdLabel);
            pwdPart.add(pwd);
            pwd.setPreferredSize(new Dimension(220,24));
            pwdLabel.setPreferredSize(new Dimension(120,24));
            pwdLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(pwdPart);
          JPanel dirPart = new JPanel(new FlowLayout());
            dirPart.add(dirLabel);
            dirPart.add(dir);
            dir.setPreferredSize(new Dimension(220,24));
            dirLabel.setPreferredSize(new Dimension(120,24));
            dirLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(dirPart);
          JPanel memPart = new JPanel(new FlowLayout());
            memPart.add(memLabel);
            memPart.add(mem);
            mem.setPreferredSize(new Dimension(220,24));
            memLabel.setPreferredSize(new Dimension(120,24));
            memLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(memPart);
          JPanel serverPart = new JPanel(new FlowLayout());
            serverPart.add(server);
            server.setPreferredSize(new Dimension(340,24));
            server.setHorizontalAlignment(JCheckBox.RIGHT);
            computerDetails.add(serverPart);
          JPanel wallPart = new JPanel(new FlowLayout());
            wallPart.add(wallLabel);
            wallPart.add(wall);
            wall.setPreferredSize(new Dimension(220,24));
            wallLabel.setPreferredSize(new Dimension(120,24));
            wallLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(wallPart);
          JPanel qcomPart = new JPanel(new FlowLayout());
            qcomPart.add(queueCommLabel);
            qcomPart.add(queue);
            queue.setPreferredSize(new Dimension(220,24));
            queueCommLabel.setPreferredSize(new Dimension(120,24));
            queueCommLabel.setHorizontalAlignment(JLabel.RIGHT);
            computerDetails.add(qcomPart);
          resourcePanel.add(computerDetails,BorderLayout.CENTER);
          JPanel bottomComputerPanel = new JPanel(new BorderLayout());
          JScrollPane scriptScroller = new JScrollPane(script);
            scriptScroller.setPreferredSize(new Dimension(200,150));
            bottomComputerPanel.add(runScript,BorderLayout.NORTH);
            runScript.setHorizontalAlignment(JLabel.CENTER);
            runScript.setPreferredSize(new Dimension(320,20));
            bottomComputerPanel.add(scriptScroller,BorderLayout.CENTER);
            resourcePanel.add(bottomComputerPanel,BorderLayout.SOUTH);
          JPanel spacerPanel = new JPanel(new FlowLayout());
          spacerPanel.setPreferredSize(new Dimension(200,100));
          bottomComputerPanel.add(spacerPanel,BorderLayout.SOUTH);
        topPanel.add(resourcePanel);
      add(topPanel,BorderLayout.CENTER);
      
      jobTableModel.setColumnIdentifiers(new String[] {"ID","Name","Status"});
      jobTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
      jobTable.getColumnModel().getColumn(0).setPreferredWidth(50);
      jobTable.getColumnModel().getColumn(1).setPreferredWidth(200);
      
    // Events
      
    resourceList.addActionListener(eh);
    newResource.addActionListener(eh);
    saveResource.addActionListener(eh);
    renameResource.addActionListener(eh);
    removeResource.addActionListener(eh);
    copyResource.addActionListener(eh);
    host.addCaretListener(eh);
    mem.addCaretListener(eh);
    server.addActionListener(eh);
    user.addCaretListener(eh);
    pwd.addCaretListener(eh);
    dir.addCaretListener(eh);
    wall.addCaretListener(eh);
    queue.addCaretListener(eh);
    type.addItemListener(eh);
    script.addCaretListener(eh);
    launchJob.addActionListener(eh);
    abort.addActionListener(eh);
    launchLive.addActionListener(eh);
    clear.addActionListener(eh);
    analyse.addActionListener(eh);
    jobTable.getSelectionModel().addListSelectionListener(eh);
    lockEvents--;
  }
  
  private void tagFromFields(XMLTag machine) {
    machine.getTag("type").setValue(type.getSelectedItem().toString());
    machine.getTag("user").setValue(user.getText());
    machine.getTag("pwd").setValue(String.valueOf(pwd.getPassword()));
    machine.getTag("mem").setValue(mem.getText());
    machine.getTag("wall").setValue(wall.getText());
    machine.getTag("host").setValue(host.getText());
    machine.getTag("queue").setValue(queue.getText());
    machine.getTag("server").setValue(String.valueOf(server.isSelected()));
    machine.getTag("dir").setValue(dir.getText());
  }
  
  private void updateButtons() {
    boolean selectReadOnly=false;
    if (resourceList.getSelectedItem().equals(DESKTOP)) selectReadOnly=true;
    removeResource.setEnabled(!selectReadOnly);
    renameResource.setEnabled(!selectReadOnly);
    int[] jobRow = jobTable.getSelectedRows();
    boolean somethingRunning=false;
    boolean somethingToRun=false;
    boolean somethingToAnalyse=false;
    boolean somethingToClear=false;    
    for (int i=0; i<jobRow.length; i++) {
      String s = jobTableModel.getValueAt(jobRow[i],2).toString();
      if (s.equals(JOB_READY)) {
        somethingToRun=true;
        somethingToClear=true;
      }
      if (s.equals(JOB_RUNNING)) {
        somethingToAnalyse=true;
        somethingRunning=true;
      }
      if ((s.equals(JOB_FINISHED) || (s.equals(JOB_ABORTED))) || (s.equals(JOB_READY))) {
        somethingToClear=true;
        somethingToAnalyse=true;
      }
      if (s.equals(JOB_ERROR)) somethingToClear=true;
    }
    launchJob.setEnabled(somethingToRun && (dir.getText().length()>0));
    launchLive.setEnabled(somethingToRun && (dir.getText().length()>0) && (jobRow.length==1) && (type.getSelectedItem().toString().equals(THIS_COMPUTER)));
    analyse.setEnabled(somethingToAnalyse && (jobRow.length==1));
    abort.setEnabled((jobRow.length>=1) && (somethingRunning));
    clear.setEnabled(somethingToClear);
  }
  
  /*************/
  /* LAUNCHING */
  /*************/
  
  private boolean launchSSHJob(String id, String name) {
    Connection conn = null;    
    try {
      conn = new Connection(host.getText());
      conn.connect();
      boolean isAuthenticated = conn.authenticateWithPassword(user.getText(), String.valueOf(pwd.getPassword()));
      if (isAuthenticated == false) {
        JOptionPane.showMessageDialog(tp, "Log-in to "+host.getText()+" failed.");
        conn.close();
        return false;
      } else {
        name = StringTools.nonSpace(name);
        StringBuffer dirName = new StringBuffer(dir.getText());
        if (dirName.charAt(0)!='/') dirName.insert(0,'/');
        if (dirName.charAt(dirName.length()-1)!='/') dirName.append("/");
        int j=0;
        while ((j<dirName.length()) && (dirName.indexOf("/",j+1)>=0)) {
          String dirSoFar = dirName.substring(0,j+1);
          String nextDir = dirName.substring(j+1,dirName.indexOf("/",j+1));
          if (nextDir.equals("")) j=dirName.indexOf("/",j+1)+1;
          else {
            Session sess = conn.openSession();
            sess.execCommand("cd "+dirSoFar+" && mkdir "+nextDir);
            j=dirName.indexOf("/",j+1);
          }
        }
        Session sess = conn.openSession();
        sess.execCommand("cd "+dir.getText()+" && mkdir "+name);
        sess.close();
        SFTPv3Client sftp = new SFTPv3Client(conn);
        SFTPv3FileHandle sftpFile =sftp.createFile(dir.getText()+"/"+name+"/run.sh");
        String s = script.getText();
        s = StringTools.replace(s,"%walltime%",wall.getText());
        s = StringTools.replace(s,"%mem%",mem.getText()+"m");
        if (server.isSelected()) s = StringTools.replace(s,"%server%","-server");
        else s = StringTools.replace(s,"%server%","");
        s = StringTools.replace(s,"%dir%",dir.getText()+"/"+name);
        byte[] s_array = s.getBytes();
        sftp.write(sftpFile,0, s_array,0,s_array.length);
        sftp.closeFile(sftpFile);
        SFTPv3FileHandle jarFile = sftp.createFile(dir.getText()+"/"+name+"/VEW.jar");
        FileInputStream fis = new FileInputStream(new File("CompiledJobs"+File.separator+"VEW"+id+".jar"));
        byte[] buf = new byte[1024];
        int i = 0;
        int offset=0;
        while ((i = fis.read(buf)) != -1) {
          sftp.write(jarFile,offset,buf,0,i);
          offset+=i;
        }
        fis.close();
        sftp.closeFile(jarFile);
        sftp.close();
        
        Session sess2 = conn.openSession();
        sess2.execCommand("cd "+dir.getText()+"/"+name+" && chmod 755 ./run.sh && "+queue.getText()+" ./run.sh");
        sess2.close();
        conn.close();
        return true;
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(tp, "An unknown error occurred.");
      e.printStackTrace();
      if (conn!=null) conn.close();
      return false;
    }
  }
  
  private boolean launchLocalJob(String id, String name, String live) {
    try {
      name = StringTools.nonSpace(name);
      String output = dir.getText()+File.separator+name;
      new File(output).mkdirs();
      String s = script.getText();
      s = StringTools.replace(s,"%mem%",mem.getText()+"m");
      if (server.isSelected()) s = StringTools.replace(s,"%server%","-server");
      else s = StringTools.replace(s,"%server%","");
      s = StringTools.replace(s,"%dir%",dir.getText()+"/"+name);
      StringTools.copyFile("CompiledJobs"+File.separator+"VEW"+id+".jar",dir.getText()+File.separator+name+File.separator+"VEW.jar");
      Runtime.getRuntime().exec(s+" "+live,null,new File(output));
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  
  /*************/
  /* ABORTING  */
  /*************/
  
  
  public static void abortSSH(XMLTag job, XMLTag machine) {
    Connection conn = null;
    String host = machine.getValue("host");
    String user = machine.getValue("user");
    String pwd = machine.getValue("pwd");
    String output = job.getAttribute("output");
    output = StringTools.replace(output,"\\","/");
    try {
      conn = new Connection(host);
      conn.connect();
      boolean isAuthenticated = conn.authenticateWithPassword(user, pwd);
      if (isAuthenticated == false) {
        JOptionPane.showMessageDialog(tp, "Log-in to "+host+" failed.");
        conn.close();
      } else {
        SFTPv3Client sftp = new SFTPv3Client(conn);
        SFTPv3FileHandle sftpFile =sftp.createFile(output+"/abort.txt");
        String s = "Abort";
        byte[] s_array = s.getBytes();
        sftp.write(sftpFile,0, s_array,0,s_array.length);
        sftp.closeFile(sftpFile);
        conn.close();
      }
    } catch (IOException e) {
      JOptionPane.showMessageDialog(tp, "An unknown error occurred.");
      e.printStackTrace();
      if (conn!=null) conn.close();
    }
  }
  
  public static void abortLocal(XMLTag job) {
    String directory = job.getAttribute("output");
    try {
      PrintWriter P = new PrintWriter(new FileOutputStream(new File(directory+File.separator+"abort.txt")));
      P.write("Please abort");
      P.flush();
      P.close();
    } catch (Exception ex) { ex.printStackTrace(); }
  }  
  
  /********************/
  /* GETTING PROGRESS */
  /********************/
  
  public float tryAndRead(String fileName) {
    float val = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      val = Float.parseFloat(br.readLine());
      br.close();
    } catch (Exception ex) {}
    return val;
  }
  
  public long tryAndReadLong(String fileName) {
    long val = 0;
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      val = Long.parseLong(br.readLine());
      br.close();
    } catch (Exception ex) {}
    return val;
  }
  
  public void getProgressLocal(XMLTag job) {
    GregorianCalendar anyTime = new GregorianCalendar();
    String outputPath = job.getAttribute("output");
    if ((!job.getAttribute("running").equals(JOB_ABORTED)) && (!job.getAttribute("running").equals(JOB_FINISHED))) {
      File f = new File(outputPath+File.separator+"end.txt");
      if (f.exists()) {
        job.setAttribute("running", JOB_FINISHED);
        job.write();
      }
    }
    float progressVal = tryAndRead(outputPath+File.separator+"latest.txt");
    float theEnd = Float.parseFloat(job.getAttribute("timesteps"));
    long startTimeLong = tryAndReadLong(outputPath+File.separator+"start.txt");
    long nowTime = System.currentTimeMillis();
    long predictMillis = (long) (startTimeLong+((nowTime-startTimeLong)/((1.0f*progressVal/theEnd))));
    anyTime.setTimeInMillis(predictMillis);
    String ds = DateDialog.getLocalString(anyTime);
    if (job.getAttribute("running").equals(JOB_ABORTED)) ds = "Job Aborted";
    else if (job.getAttribute("running").equals(JOB_FINISHED)) ds = "Job Finished";    
    else if (progressVal==0) ds = "No progress information";
    endTime.setText(ds);
    anyTime.setTimeInMillis(startTimeLong);
    startTime.setText(DateDialog.getLocalString(anyTime));
    String pc = String.valueOf(Math.floor(10000f*(progressVal/theEnd))/100f)+" %";
    progress.setValue((int)(100*(1.0f*progressVal/theEnd)));
    progress.setString(pc);
    progress.paint(progress.getGraphics());
    comp.setText(job.getAttribute("comp"));
    resPath.setText(job.getAttribute("output"));
    
  }
  
  
  public static long readLongSSH(XMLTag job, XMLTag machine, String file) {
    String host = machine.getValue("host");
    String user = machine.getValue("user");
    String pwd = machine.getValue("pwd");
    String output = job.getAttribute("output");
    output = StringTools.replace(output,"\\","/");
    Connection conn = null;
    long val = -1;
    try {
      conn = new Connection(host);
      conn.connect();
      boolean isAuthenticated = conn.authenticateWithPassword(user, pwd);
      if (isAuthenticated == false) {
        JOptionPane.showMessageDialog(tp, "Log-in to "+host+" failed.");
        conn.close();
      } else {
        StringBuffer sb = new StringBuffer();
        SFTPv3Client sftp = new SFTPv3Client(conn);
        if (SSHFileExists(machine,output+"/"+file)) {
          SFTPv3FileHandle sftpFile =sftp.openFileRO(output+"/"+file);
          byte[] s_array = new byte[10];
          int i=1;
          int bytePointer=0;
          while (i>0) {
            i = sftp.read(sftpFile,bytePointer, s_array,0,10);
            if (i>0) {
              bytePointer+=i;
              sb.append(new String(s_array).substring(0,i));
            }
          }
          sftp.closeFile(sftpFile);
          conn.close();
          sb.deleteCharAt(sb.length()-1);
          val = Long.parseLong(sb.toString());
        }
      } 
    } catch (Exception e) { e.printStackTrace(); }
    return val;
  }
  
  public static boolean SSHFileExists(XMLTag machine, String file) {
    boolean found = false;
    String host = machine.getValue("host");
    String user = machine.getValue("user");
    String pwd = machine.getValue("pwd");
    file = StringTools.replace(file,"\\","/");
    Connection conn = null;
    try {
      conn = new Connection(host);
      conn.connect();
      boolean isAuthenticated = conn.authenticateWithPassword(user, pwd);
      if (isAuthenticated == false) {
        JOptionPane.showMessageDialog(tp, "Log-in to "+host+" failed.");
        conn.close();
      } else {
        SFTPv3Client sftp = new SFTPv3Client(conn);
        try {
          SFTPv3FileHandle sftpFile =sftp.openFileRO(file);
          found=true;
          sftp.closeFile(sftpFile);
        } catch (Exception e) {}
        conn.close();
      } 
    } catch (Exception e) { e.printStackTrace(); }
    return found;
    
  }
  
  
  public void getProgressSSH(XMLTag job, XMLTag machine) {
    GregorianCalendar anyTime = new GregorianCalendar();
    String outputPath = job.getAttribute("output");
    if ((!job.getAttribute("running").equals(JOB_ABORTED)) && (!job.getAttribute("running").equals(JOB_FINISHED))) {
      if (SSHFileExists(machine,outputPath+File.separator+"end.txt")) {
        job.setAttribute("running", JOB_FINISHED);
        job.write();
      }
    }
    float progressVal = readLongSSH(job,machine,"latest.txt");
    long startTimeLong = readLongSSH(job, machine,"start.txt");
    float theEnd = Float.parseFloat(job.getAttribute("timesteps"));
    long nowTime = System.currentTimeMillis();
    long predictMillis = (long) (startTimeLong+((nowTime-startTimeLong)/((1.0f*progressVal/theEnd))));
    anyTime.setTimeInMillis(predictMillis);
    String ds = DateDialog.getLocalString(anyTime);
    if (job.getAttribute("running").equals(JOB_ABORTED)) ds = "Job Aborted";
    else if (job.getAttribute("running").equals(JOB_FINISHED)) ds = "Job Finished";    
    else if (progressVal==0) ds = "No progress information";
    endTime.setText(ds);
    anyTime.setTimeInMillis(startTimeLong);
    startTime.setText(DateDialog.getLocalString(anyTime));
    String pc = String.valueOf(Math.floor(10000f*(progressVal/theEnd))/100f)+" %";
    progress.setValue((int)(100*(1.0f*progressVal/theEnd)));
    progress.setString(pc);
    progress.paint(progress.getGraphics());
    comp.setText(job.getAttribute("comp"));
    resPath.setText(job.getAttribute("output"));
  }
  
  
  /************/
  /* ANALYSIS */
  /************/
  
  public void analyseLocal(XMLTag job) {
    String directory = job.getAttribute("output");
    try {
      PrintWriter P = new PrintWriter(new FileOutputStream(new File(directory+File.separator+"abort.txt")));
      P.write("Please flush");
      P.flush();
      P.close();
    } catch (Exception ex) { ex.printStackTrace(); }
    Analyser4 A = new Analyser4(directory,"Model.xml", "DataFormats.xml");
    A.setVisible(true);
    A=null;
  }
  
  
  public static void getLock(String file) {
    try {
      File f = new File(file);
      while (f.createNewFile()==false) {}
    } catch (Exception e) {}
  }
  
  public static void releaseLock(String file) {
    File f = new File(file);
    if (f.exists()) f.delete();
  }
  
  public void timerUpdate() {
    boolean updated=false;
    lockEvents++;
    getLock("jobs.xml.lock");
    //populateLists();
    XMLTag jobTag = XMLFile.LoadFile("jobs.xml").getTag("jobfile/jobs");
    XMLTag[] jobs = jobTag.getTags("job");
    for (int i=0; i<jobs.length; i++) {
      XMLTag job = jobs[i];
      if (job.getAttribute("running").equals(JOB_COMPILING)) {
        String id = job.getAttribute("vewid");
        File f = new File("CompiledJobs"+File.separator+"VEW"+id+".jar.msg");
        if (f.exists()) {
          f.delete();
          String targetPath = job.getAttribute("output");
          StringTools.recursiveDelete(new File(targetPath));
          StringBuffer sb = new StringBuffer();
          try {
            JarFile JF = new JarFile("CompiledJobs"+File.separator+"VEW"+id+".jar");
            JarEntry JE = JF.getJarEntry("compile.log");
            BufferedInputStream compLog = new BufferedInputStream(JF.getInputStream(JE));
            
            while (compLog.available()>0) {
              sb.append((char)compLog.read());
            }
            compLog.close();
            JF.close();
          } catch (Exception e) {}
          if (sb.toString().endsWith("\n")) sb.deleteCharAt(sb.length()-1);
          if (sb.toString().endsWith("\r")) sb.deleteCharAt(sb.length()-1);
          if (sb.toString().endsWith(ModelCompiler.COMPILE_STATUS+"0")) {
            job.setAttribute("running",JOB_READY);
          } else {
            job.setAttribute("running",JOB_ERROR);
          }
          updated=true;
        }
      }
    }
    if (updated) jobTag.write();
    updateJobList();
    updateButtons();
    if (jobTable.getSelectedRow()>=0) {
      String jobid = jobTable.getValueAt(jobTable.getSelectedRow(),0).toString();
      XMLTag job = jobTag.getTagWhere("job","@vewid",jobid);
      if (job.getAttribute("running").equals(JOB_RUNNING)) {
        String compName = job.getAttribute("comp");
        XMLTag machine = machineTag.getTagWhere("machine","name",compName);
        if (machine!=null) {
          if (machine.getValue("type").equals(THIS_COMPUTER)) {
            getProgressLocal(job);
          } else if (machine.getValue("type").equals(SSH_SERVER)) {
            getProgressSSH(job,machine);
          }
        }
      }
    }
    releaseLock("jobs.xml.lock");
    lockEvents--;
  }
  
  public void stopJobs() {
    updatePoll.stop();
  }
  
  public void startJobs() {
    populateLists();
    updateResourceList();
    updateJobList();
    updatePoll.start();
  }
  
  
  class EventHandler implements ActionListener, CaretListener, ItemListener, ListSelectionListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if ((e.getSource()==resourceList) && (lockEvents==0)) {
        updateResourceList();
        updateButtons();
        saveResource.setEnabled(false);
      
      } else if (e.getSource()==newResource) {
        String newName = JOptionPane.showInputDialog(tp,"Type name for new computing resource").trim();
        if (newName.length()>0) {
          if (machineTag.getTagsWhere("machine","name",newName)!=null) {
            JOptionPane.showMessageDialog(tp,"A machine with that name already exists");
          } else {
            lockEvents++;
            XMLTag newMachine = new XMLTag("machine");
            newMachine.addTag(new XMLTag("name",newName));
            newMachine.addTag(new XMLTag("type",SSH_SERVER));
            newMachine.addTag(new XMLTag("user",""));
            newMachine.addTag(new XMLTag("pwd",""));
            newMachine.addTag(new XMLTag("host",""));
            newMachine.addTag(new XMLTag("mem","850"));
            newMachine.addTag(new XMLTag("wall","24:00:00"));
            String templateFile=StringTools.nonSpace(newName)+".template";
            templateFile=StringTools.assureUniqueFile(machinePath,templateFile);
            newMachine.addTag(new XMLTag("template",templateFile));
            machineTag.addTag(newMachine);
            machineTag.write();
            try {
              PrintWriter P = new PrintWriter(new FileOutputStream(new File(machinePath+templateFile)));
              P.flush();
              P.close();
            } catch (Exception ex) {}
            populateLists();
            StringTools.setStringItem(resourceList,newName);
            updateResourceList();
            lockEvents--; 
          }
        }
        
      } else if (e.getSource()==saveResource) {
        XMLTag machine = machineTag.getTagWhere("machine","name",resourceList.getSelectedItem().toString());
        tagFromFields(machine);
        machineTag.write();
        saveResource.setEnabled(false);
        
      } else if (e.getSource()==renameResource) {
        String oldName = resourceList.getSelectedItem().toString();
        String newName = JOptionPane.showInputDialog(tp,"Type name for new computing resource",oldName).trim();
        if (newName.length()>0) {
          if (machineTag.getTagsWhere("machine","name",newName)!=null) {
            JOptionPane.showMessageDialog(tp,"A machine with that name already exists");
          } else {
            lockEvents++;
            machineTag.getTagWhere("machine","name",oldName).removeFromParent();
            XMLTag machine = new XMLTag("machine");
            machine.addTag(new XMLTag("name",newName));
            tagFromFields(machine);
            machineTag.addTag(machine);
            machineTag.write();
            saveResource.setEnabled(false);
            populateLists();
            StringTools.setStringItem(resourceList,newName);
            updateResourceList();
            lockEvents--; 
          }
        }
        
      } else if (e.getSource()==removeResource) {
        String name = resourceList.getSelectedItem().toString();
        if (JOptionPane.showConfirmDialog(tp,"Really delete "+name+"?")==JOptionPane.OK_OPTION) {
          lockEvents++;
          int i = resourceList.getSelectedIndex();
          machineTag.getTagWhere("machine","name",name).removeFromParent();
          machineTag.write();
          populateLists();
          if (i==resourceList.getItemCount()) i--;
          resourceList.setSelectedIndex(i);
          updateResourceList();
          lockEvents--;
        }
        
        
      } else if (e.getSource()==copyResource) {
        String oldName = resourceList.getSelectedItem().toString();
        String newName = JOptionPane.showInputDialog(tp,"Type name for new computing resource",oldName).trim();
        if (newName.length()>0) {
          if (machineTag.getTagsWhere("machine","name",newName)!=null) {
            JOptionPane.showMessageDialog(tp,"A machine with that name already exists");
          } else {
            lockEvents++;
            XMLTag machine = new XMLTag("machine");
            machine.addTag(new XMLTag("name",newName));
            tagFromFields(machine);
            machineTag.addTag(machine);
            machineTag.write();
            saveResource.setEnabled(false);
            populateLists();
            StringTools.setStringItem(resourceList,newName);
            updateResourceList();
            lockEvents--; 
          }
        }
      } else  if (e.getSource()==launchJob) {
        int jobCount=jobTable.getSelectedRowCount();
        if (jobCount>0) {
          String jobString = String.valueOf(jobCount)+" jobs";
          if (jobCount==1) jobString = jobTable.getValueAt(jobTable.getSelectedRow(),1).toString();
          if (JOptionPane.showConfirmDialog(tp,"Really launch "+jobString+" on "+resourceList.getSelectedItem().toString())==JOptionPane.YES_OPTION) {
            for (int i=0; i<jobCount; i++) {
              int row = jobTable.getSelectedRows()[i];
              String jobID = jobTable.getValueAt(row,0).toString();
              String jobName = jobTable.getValueAt(row,1).toString();
              String profile = resourceList.getSelectedItem().toString();
              boolean success = true;
              if (type.getSelectedItem().toString().equals(SSH_SERVER)) {
                success = launchSSHJob(jobID,jobName);
              } else if (type.getSelectedItem().toString().equals(THIS_COMPUTER)) {
                success = launchLocalJob(jobID,jobName,"");
              }
              if (success) {
                getLock("jobs.xml.lock");
                XMLTag job = XMLFile.LoadFile("jobs.xml").getTag("jobs").getTagWhere("job","@vewid",jobID);
                job.setAttribute("running",JOB_RUNNING);
                job.setAttribute("comp",profile);
                job.setAttribute("output",dir.getText()+File.separator+StringTools.nonSpace(job.getAttribute("name")));
                job.write();
                releaseLock("jobs.xml.lock");
              }
            }
          }            
        }
      
      } else if (e.getSource()==server) {
        saveResource.setEnabled(true);
      
      } else if ((e.getSource()==updatePoll) & (lockEvents==0)) {
        timerUpdate();
      
      } else if (e.getSource()==abort) {
        String j = "these jobs";
        if (jobTable.getSelectedRows().length==1) {
          j = "job "+jobTable.getValueAt(jobTable.getSelectedRow(),0).toString();
        }
        if (JOptionPane.showConfirmDialog(tp,"Really abort "+j+"?")==JOptionPane.YES_OPTION) {
          String[] jobIDs = new String[jobTable.getSelectedRows().length];
          for (int i=0; i<jobIDs.length; i++) jobIDs[i]=jobTable.getValueAt(jobTable.getSelectedRows()[i],0).toString();
          getLock("jobs.xml.lock");
          XMLTag jobs = XMLFile.LoadFile("jobs.xml").getTag("jobs");
          for (int i=0; i<jobIDs.length; i++) {
            XMLTag job = jobs.getTagWhere("job","@vewid",jobIDs[i]);
            String status = job.getAttribute("running");
            if (status.equals(JOB_RUNNING)) {
              String compName = job.getAttribute("comp");
              XMLTag machines = XMLFile.LoadFile(machinePath+"machines.xml");
              XMLTag machine = machines.getTagWhere("machine","name",compName);
              if (machine!=null) {
                String _type = machine.getValue("type");
                if (_type.equals(SSH_SERVER)) abortSSH(job,machine);
                else if (_type.equals(THIS_COMPUTER)) abortLocal(job);
              }
              job.setAttribute("running",JOB_ABORTED);
            } else {
              job.setAttribute("running",JOB_ABORTED);
            }
          }
          jobs.write();
          releaseLock("jobs.xml.lock");
        }
           
      } else if (e.getSource()==analyse) {
        XMLTag jobs = XMLFile.LoadFile("jobs.xml").getTag("jobs");
        XMLTag job = jobs.getTagWhere("job","@vewid",jobTable.getValueAt(jobTable.getSelectedRow(),0).toString());
        String compName = job.getAttribute("comp");
        XMLTag machines = XMLFile.LoadFile(machinePath+"machines.xml");
        XMLTag machine = machines.getTagWhere("machine","name",compName);
        if (machine!=null) {
          String _type = machine.getValue("type");
          //if (_type.equals(SSH_SERVER)) analyseSSH(job,machine);
          /*else*/ if (_type.equals(THIS_COMPUTER)) analyseLocal(job);
        }
      
      } else if (e.getSource()==clear) {
        lockEvents++;
        int[] rows = jobTable.getSelectedRows();
        XMLTag jobTag = XMLFile.LoadFile("jobs.xml").getTag("jobs");
        for (int i=0; i<rows.length; i++) {
          String id = jobTable.getValueAt(rows[i],0).toString();
          XMLTag job = jobTag.getTagWhere("job","@vewid",id);
          File f = new File("CompiledJobs"+File.separator+"VEW."+id+".jar");
          if (f.exists()) f.delete();
          f = new File("CompiledJobs"+File.separator+"VEW."+id+".jar.msg");
          if (f.exists()) f.delete();
          String running = job.getAttribute("running");
          if (running.equals(JOB_ABORTED)) job.removeFromParent();
          else if (running.equals(JOB_FINISHED)) job.removeFromParent();
          else if (running.equals(JOB_READY)) job.removeFromParent();
          else if (running.equals(JOB_ERROR)) job.removeFromParent();
        }
        jobTag.write();
        updateJobList();
        updateButtons();
        lockEvents--;
          
        
      
      } else if (e.getSource()==launchLive) {
        int row = jobTable.getSelectedRow();
        String jobString = jobTable.getValueAt(row,1).toString();
        if (JOptionPane.showConfirmDialog(tp,"Really launch "+jobString+" live?")==JOptionPane.YES_OPTION) {
          String jobID = jobTable.getValueAt(row,0).toString();
          String jobName = jobTable.getValueAt(row,1).toString();
          launchLocalJob(jobID,jobName,"/live");
        }
      }
    }
    
    public void caretUpdate(CaretEvent e) {
      if ((e.getSource()==host) && (lockEvents==0)) {
        saveResource.setEnabled(true);
      } else if ((e.getSource()==user) && (lockEvents==0)){
        saveResource.setEnabled(true);
      } else if ((e.getSource()==pwd) && (lockEvents==0)){
        saveResource.setEnabled(true);
      } else if ((e.getSource()==mem) && (lockEvents==0)){
        saveResource.setEnabled(true);
      } else if ((e.getSource()==wall) && (lockEvents==0)) {
        saveResource.setEnabled(true);
      } else if ((e.getSource()==queue) && (lockEvents==0)) {
        saveResource.setEnabled(true);
      } else if ((e.getSource()==dir) && (lockEvents==0)){
        saveResource.setEnabled(true);
        updateButtons();
      } else if ((e.getSource()==script) && (lockEvents==0)){
        saveResource.setEnabled(true);
      }
    }
    
    public void itemStateChanged(ItemEvent e) {
      if ((e.getSource()==type) && (lockEvents==0)) {
        String t = type.getSelectedItem().toString();
        if (t.equals(SSH_SERVER)) {
          host.setEnabled(true);
          wall.setEnabled(true);
          queue.setEnabled(true);
          user.setEnabled(true);
          pwd.setEnabled(true);
        } else if (t.equals(THIS_COMPUTER)) {
          host.setEnabled(false);
          wall.setEnabled(false);
          user.setEnabled(false);
          pwd.setEnabled(false);
          queue.setEnabled(false);
        }
        saveResource.setEnabled(true);
      }
    }
    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==jobTable.getSelectionModel()) && (lockEvents==0)) {
        lockEvents++;
        updateButtons();
        if (jobTable.getSelectedRows().length==1) {
          XMLTag jobTag = XMLFile.LoadFile("jobs.xml").getTag("jobfile/jobs");
          XMLTag theJob = jobTag.getTags("job")[jobTable.getSelectedRow()];
          
          if (theJob.getAttribute("running").equals(JOB_ERROR)) {
            StringBuffer sb = new StringBuffer();
            String id = theJob.getAttribute("vewid");
            try {
              JarFile JF = new JarFile("CompiledJobs"+File.separator+"VEW"+id+".jar");
              JarEntry JE = JF.getJarEntry("compile.log");
              BufferedInputStream compLog = new BufferedInputStream(JF.getInputStream(JE));
              
              while (compLog.available()>0) {
                sb.append((char)compLog.read());
              }
              compLog.close();
              MessageBox.showMessage(sb.toString(),JobLauncher.this);
              JF.close();
            } catch (Exception ex) {}
            
          }
        }
        timerUpdate();
        lockEvents--;
      }
    }
  }
}
