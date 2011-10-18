package VEW.Common.ParticleChooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.util.GregorianCalendar;
import java.util.zip.GZIPInputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import VEW.Analyser4.Analyser4;
import VEW.Common.DateDialog;
import VEW.Common.Progress.CompletionListener;
import VEW.Common.Progress.Job;
import VEW.Common.Progress.JobProgressDialog;
import VEW.Common.Progress.ProgressListener;
import VEW.Common.XML.XMLTag;

public class FamilyTree extends JDialog {
  private DefaultTableModel familyTreeModel = new DefaultTableModel();
  private JTable familyTreeTable = new JTable(familyTreeModel);
  private JTextField id = new JTextField(6);
  private JButton search = new JButton("Search");
  private JButton clear = new JButton("Clear");
  private JButton exit = new JButton("Exit");
  private EventHandler eh = new EventHandler();
  private int lockEvents = 0;
  private XMLTag dfTag = null;
  private XMLTag dfRoot = null;
  private XMLTag theModel = null;
  private String path;
  protected boolean closeCancelled = false;
  public static final byte CREATE = 0;
  public static final byte PCHANGEPARENT = 1;
  public static final byte PCHANGECHILD = 2;
  public static final byte SPLIT = 3;
  public static final byte MERGE = 4;
  public static final byte REMOVAL = 5;
  public static final byte INGEST = 6;
  public static final byte DIVIDE = 7;
  public static final byte CHANGE = 8;
  private Analyser4 a4;

  
  public FamilyTree(JDialog parent,Analyser4 _a4, XMLTag _dfTag, String _path, long _id, XMLTag _dfRoot,XMLTag _model) {
    super(parent,"Family Tree",true);
    a4=_a4;
    dfTag = _dfTag;
    dfRoot = _dfRoot;
    theModel = _model;
    id.setText(String.valueOf(_id));
    path = new String(_path);
    familyTreeModel.setColumnCount(6);
    familyTreeModel.setColumnIdentifiers(new String[] {"Date/Time","ID 1","Stage 1", "Action","ID 2", "Stage 2"});
    JScrollPane familyTreeScroller = new JScrollPane(familyTreeTable);
    familyTreeScroller.setPreferredSize(new Dimension(500,200));
    setLayout(new BorderLayout());
    add(familyTreeScroller,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(new JLabel("Agent Id: "));
    buttonPanel.add(id);
    buttonPanel.add(search);
    buttonPanel.add(clear);
    buttonPanel.add(exit);
    add(buttonPanel,BorderLayout.SOUTH);
    id.addCaretListener(eh);
    search.addActionListener(eh);
    exit.addActionListener(eh);
    clear.addActionListener(eh);
    pack();
  }
  
  public void search(long theId, ProgressListener progress, CompletionListener completion) {
    GregorianCalendar anyDate = new GregorianCalendar();
    anyDate.setTimeZone(DateDialog.GMTTimeZone);
    String lineage = dfTag.getValue("lineage");
    boolean Version1_1 = ((dfTag.getParentTag().getValue("version")!=null) && (dfTag.getParentTag().getValue("version").equals("1.1")));
    DataInputStream in = null;
    long stepMillis = a4.timeStepMillis;
    long startSimMillis = a4.startSimMillis;
    long endSimMillis = a4.endSimMillis;    
    String speciesName = dfTag.getValue("name");
    String fg = "";
    if (Version1_1) {
      fg = theModel.getTag("species").getTagWhere("species","@name",speciesName).getAttribute("fg");
    }
    try {
      in = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(path+File.separator+lineage)))));
      progress.setMinimum(0);
      progress.setMaximum(1000);
      progress.setMessage("Searching lineage data");
      boolean agentAlive = true;
      byte type;
      long parentID;
      long childID;
      int parentStageInt;
      int childStageInt;
      String parentStage;
      String childStage;
      
      long timeMillis;
      long oldMillis=0;
      searchLoop: while ((in.available()>0) && (agentAlive) && (!closeCancelled)) {
        synchronized (this) {
          if (closeCancelled) {
            completion.jobCancelled();
            break searchLoop;
          }
        }
        if (Version1_1) timeMillis = in.readLong();
        else timeMillis = startSimMillis+(in.readInt()*stepMillis);
        if (oldMillis!=timeMillis) {
          oldMillis=timeMillis;
          progress.setNote("(Worst case)");
          progress.setProgress((int)(1000*((timeMillis-startSimMillis)/((1.0f*endSimMillis)-startSimMillis))));
        }
        type = in.readByte();
        parentID = in.readLong();
        childID = in.readLong();
        parentStage = "N/A";
        childStage = "N/A";
        if (Version1_1) {
          parentStageInt = in.readInt();
          childStageInt = in.readInt();
          parentStage = dfRoot.getTag("states").getTagWhere("functionalgroup","name",fg).getTagWhere("state","@id",String.valueOf(parentStageInt)).getAttribute("name");
          childStage = dfRoot.getTag("states").getTagWhere("functionalgroup","name",fg).getTagWhere("state","@id",String.valueOf(childStageInt)).getAttribute("name");
        }
        String dateString;
        if ((parentID==theId) || (childID==theId)) {
          anyDate.setTimeInMillis(timeMillis);
          dateString = DateDialog.getString(anyDate);
          if (type==CREATE) {
            familyTreeModel.addRow(new String[] {dateString,String.valueOf(parentID),parentStage,"Created new agent ->",String.valueOf(childID),childStage});

          } else if (type==PCHANGECHILD) {
            familyTreeModel.addRow(new String[] {dateString,String.valueOf(childID),childStage,"Created by p-change ->",String.valueOf(parentID),parentStage});
            
          } else if (type==SPLIT) {
            familyTreeModel.addRow(new String[] {dateString,String.valueOf(parentID),parentStage,"Split, creating ->",String.valueOf(childID),childStage});
            
          } else if (type==MERGE) {
            familyTreeModel.addRow(new String[] {dateString,String.valueOf(childID),childStage,"Merged <-",String.valueOf(parentID),parentStage});
            if (childID==theId) agentAlive=false;
            
          } else if (type==REMOVAL) {
            familyTreeModel.addRow(new String[] {dateString,String.valueOf(parentID),parentStage,"Removed from column","",""});
            if (parentID==theId) agentAlive=false;
          }
        }
      }
    } catch (Exception e) { if (!(e instanceof EOFException)) e.printStackTrace(); }
    completion.jobCompleted(null);
  }
  
  
  class EventHandler implements ActionListener, CaretListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==clear) {
        lockEvents++;
        while (familyTreeModel.getRowCount()>0) familyTreeModel.removeRow(0);
        lockEvents--;
      
      } else if (e.getSource()==search) {
        SearchLineageJob slj = new SearchLineageJob(id.getText());
        JobProgressDialog d = new JobProgressDialog(FamilyTree.this, slj);
        d.setModal(true);
        d.show();
      
      } else if (e.getSource()==exit) {
        setVisible(false);
      }
    }

    public void caretUpdate(CaretEvent e) {
      
    }
  }
  class SearchLineageJob implements Job {
    long searchId;
    
    public SearchLineageJob(String _id) {
      searchId = Long.parseLong(_id);
    }
      
    public void start(ProgressListener progress, CompletionListener completion) {
      search(searchId, progress, completion);
    }

    public void cancel() {
      closeCancelled = true;
    }
  }
}
