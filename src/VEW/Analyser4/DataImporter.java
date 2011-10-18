package VEW.Analyser4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ProgressMonitor;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import VEW.Common.DateDialog;
import VEW.Common.StringTools;

public class DataImporter extends JDialog {
  DefaultListModel myDataModel = new DefaultListModel();
  JList myData = new JList(myDataModel);
  DefaultListModel impDataModel = new DefaultListModel();
  JList impData = new JList(impDataModel);
  ReadOnlyTableModel myTableModel = new ReadOnlyTableModel();
  JTable myTable = new JTable(myTableModel);
  ReadOnlyTableModel impTableModel = new ReadOnlyTableModel();
  JTable impTable = new JTable(impTableModel);
  ArrayList myEntries = new ArrayList();
  ArrayList impEntries = new ArrayList();  
  JButton concat = new JButton("Concatenate");
  JButton addNew = new JButton("Import as new");
  JButton exit = new JButton("Exit");
  JButton locateData = new JButton("Locate");
  JLabel myDataTitle = new JLabel("Current Data-Set");
  EventHandler eh;
  DFFileFilter DataFormatsFilter = new DFFileFilter();
  int lockEvents = 0;
  static final TimeZone GMTTimeZone = TimeZone.getTimeZone("GMT");
  static final GregorianCalendar gc = new GregorianCalendar(GMTTimeZone);
  Document dataFormats;
  Document theModel;
  String sourcePath;
  String importPath;
  ConcatDialog cd;
    
  public DataImporter(JFrame parent) {
    super(parent,"Data Importer",true);
    eh = new EventHandler();
    cd = new ConcatDialog(this);
    getContentPane().setLayout(new BorderLayout());
    
    JPanel detailsPanel = new JPanel(new FlowLayout());
      JPanel myDetailsPanel = new JPanel(new BorderLayout());
        JPanel myTitle = new JPanel(new FlowLayout());
        myTitle.setPreferredSize(new Dimension(200,40));
          myTitle.add(myDataTitle);
        myDetailsPanel.add(myTitle,BorderLayout.NORTH);
        JPanel myGrid = new JPanel(new GridLayout(2,1));
          JScrollPane myListScroller = new JScrollPane(myData);
          myListScroller.setPreferredSize(new Dimension(200,200));
          myGrid.add(myListScroller);
          myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
          JScrollPane myTableScroller = new JScrollPane(myTable);
          myTableScroller.setPreferredSize(new Dimension(200,200));
          initialiseTable(myTable,myTableModel);
          myGrid.add(myTableScroller);
        myDetailsPanel.add(myGrid,BorderLayout.CENTER);
      detailsPanel.add(myDetailsPanel);
      JPanel impDetailsPanel = new JPanel(new BorderLayout());
      JPanel impTitle = new JPanel(new FlowLayout());
        impTitle.add(locateData);
        impTitle.setPreferredSize(new Dimension(200,40));
      impDetailsPanel.add(impTitle,BorderLayout.NORTH);
      JPanel impGrid = new JPanel(new GridLayout(2,1));
        JScrollPane impListScroller = new JScrollPane(impData);
        impListScroller.setPreferredSize(new Dimension(200,200));
        impGrid.add(impListScroller);
        impTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane impTableScroller = new JScrollPane(impTable);
        impTableScroller.setPreferredSize(new Dimension(200,200));
        initialiseTable(impTable,impTableModel);
        impGrid.add(impTableScroller);
      impDetailsPanel.add(impGrid,BorderLayout.CENTER);
    detailsPanel.add(impDetailsPanel);
    
    getContentPane().add(detailsPanel,BorderLayout.CENTER);
      
    JPanel buttonPanel = new JPanel(new FlowLayout());
      buttonPanel.add(concat);
      buttonPanel.add(addNew);
      buttonPanel.add(exit);
    getContentPane().add(buttonPanel,BorderLayout.SOUTH);
    
    
    pack();
    concat.addActionListener(eh);
    addNew.addActionListener(eh);
    exit.addActionListener(eh);
    myData.addListSelectionListener(eh);
    myData.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    impData.addListSelectionListener(eh);
    locateData.addActionListener(eh);
  }
  
  private void initialiseTable(JTable jt, DefaultTableModel dtm) {
    dtm.setColumnCount(2);
    dtm.setRowCount(5);
    dtm.setValueAt(new String("From"),0,0);
    dtm.setValueAt(new String("To"),1,0);
    dtm.setValueAt(new String("Freq"),2,0);
    dtm.setValueAt(new String("Layers"),3,0);
    dtm.setValueAt(new String("Vars"),4,0);
    jt.getColumnModel().getColumn(0).setMaxWidth(40);
    jt.getColumnModel().getColumn(1).setMinWidth(170);
    jt.setTableHeader(null);
  }

  private long getStartTime(Element e) {
    if (e.getName().equals("environment"))
      return Long.parseLong(e.valueOf("output/after"));
    else if (e.getName().equals("field"))
      return Long.parseLong(e.valueOf("dimensions/dim[1]/@start"));
    else if (e.getName().equals("functionalgroup"))
      return Long.parseLong(e.valueOf("output/after"));
    else return -1;
  }
  
  private void setStartTime(Element e, long t) {
    if (e.getName().equals("environment"))
      e.selectSingleNode("output/after").setText(String.valueOf(t));
    else if (e.getName().equals("field"))
      e.selectSingleNode("dimensions/dim[1]/@start").setText(String.valueOf(t));
    else if (e.getName().equals("functionalgroup"))
      e.selectSingleNode("output/after").setText(String.valueOf(t));
  }
  
  private long getEndTime(Element e) {
    if (e.getName().equals("environment"))
      return Long.parseLong(e.valueOf("output/until"));
    else if (e.getName().equals("field"))
      return Long.parseLong(e.valueOf("dimensions/dim[1]/@until"));
    else if (e.getName().equals("functionalgroup"))
      return Long.parseLong(e.valueOf("output/to"));
    else return -1;
  }
  
  private void setEndTime(Element e, long t) {
    if (e.getName().equals("environment"))
      e.selectSingleNode("output/until").setText(String.valueOf(t));
    else if (e.getName().equals("field"))
      e.selectSingleNode("dimensions/dim[1]/@until").setText(String.valueOf(t));
    else if (e.getName().equals("functionalgroup"))
      e.selectSingleNode("output/to").setText(String.valueOf(t));
  }
  

  private String getFreq(Element e) {
    if (e.getName().equals("environment")) 
      return e.valueOf("output/freq")+" timesteps";
    else if (e.getName().equals("field")) 
      return e.valueOf("dimensions/dim[1]/@step")+" "+e.valueOf("dimensions/dim[1]/@label");
    else if (e.getName().equals("functionalgroup")) 
      return e.valueOf("output/freq") +" timesteps";
    else return "";
  }
  
  private String getDepth(Element e) {
    if (e.getName().equals("environment")) 
      return "N/A";
    else if (e.getName().equals("field"))
      return e.valueOf("dimensions/dim[2]/@start")+".."+e.valueOf("dimensions/dim[2]/@end");
    else if (e.getName().equals("functionalgroup")) 
      return "N/A";
    else return "";
  }

  private void addData(Document df, String name, DefaultListModel dtm, ArrayList storeage) {
    List e = df.selectNodes(name);
    for (int i=0; i<e.size(); i++) {
      dtm.addElement(((Element)e.get(i)).valueOf("name"));
      storeage.add(e.get(i));
    }
  }
  
  private void initialise(Document df, DefaultListModel dtm, ArrayList storeage) {
    storeage.clear();
    dtm.removeAllElements();
    addData(df,"/dataformat/environment",dtm,storeage); 
    addData(df,"/dataformat/field",dtm,storeage);
    addData(df,"/dataformat/functionalgroup",dtm,storeage);
  }
  
  public void showDialog(Document _dataFormats, Document _model, String dataPath) {
    lockEvents++;
    boolean ok = true;
    dataFormats = _dataFormats;
    theModel = _model;
    initialise(dataFormats,myDataModel,myEntries);
    JFileChooser jfc = new JFileChooser();
    jfc.setFileFilter(DataFormatsFilter);
    jfc.setCurrentDirectory(new File(dataPath));
    sourcePath=dataPath;
    int result = jfc.showOpenDialog(this);
    if (result==JFileChooser.APPROVE_OPTION) {
      try {
        importPath = jfc.getSelectedFile().getAbsolutePath();
        locateData.setText(importPath);
        int i = importPath.length()-1;
        while (!(importPath.charAt(i)==File.separatorChar)) i--;
        importPath = importPath.substring(0,i);
        SAXReader reader = new SAXReader();
        Document importDF =  reader.read(new FileReader(jfc.getSelectedFile().getAbsolutePath()));
        initialise(importDF, impDataModel, impEntries);
      } catch (Exception e) {ok = false; e.printStackTrace(); }
    
    } else ok=false;
    
    myData.clearSelection();
    impData.clearSelection();
    updateButtons();
    lockEvents--;
    setVisible(ok);
    
  }
  
  private String longToDate(long d) {
    gc.setTimeInMillis(d);
    return DateDialog.getString(gc);
  }
  
  private void updateTable(DefaultTableModel dtm, Element e) {
    lockEvents++;
    dtm.setValueAt(longToDate(getStartTime(e)),0,1);
    dtm.setValueAt(longToDate(getEndTime(e)),1,1);
    dtm.setValueAt(getFreq(e),2,1);
    dtm.setValueAt(getDepth(e),3,1);
    dtm.setValueAt(String.valueOf(e.selectNodes("var").size()),4,1);
    dtm.fireTableDataChanged();
    lockEvents--;
  }
  
  private void updateButtons() {
    lockEvents++;
    addNew.setEnabled(impData.getSelectedIndices().length>=1);
    boolean concatOK = ((myData.getSelectedIndices().length==1) && (impData.getSelectedIndices().length==1));
    if (concatOK) {
      Element e1 = (Element) myEntries.get(myData.getSelectedIndex());
      Element e2 = (Element) impEntries.get(impData.getSelectedIndex());
      if (!e1.getName().equals(e2.getName())) concatOK=false;
      if ((concatOK) && (e1.selectNodes("var").size()!=e2.selectNodes("var").size())) concatOK=false;
      if ((concatOK) && (e1.getName().equals("field"))) {
        double top1 = Double.parseDouble(e1.valueOf("dimensions/dim[2]/@start"));
        double bottom1 = Double.parseDouble(e1.valueOf("dimensions/dim[2]/@end"));
        double top2 = Double.parseDouble(e2.valueOf("dimensions/dim[2]/@start"));
        double bottom2 = Double.parseDouble(e2.valueOf("dimensions/dim[2]/@end"));
        if ((top1!=top2) || (bottom1!=bottom2)) concatOK=false;
      }
      if (concatOK) {
        long start1=0;
        long start2=0;
        long end1=0;
        long end2=0;
        String freq1="";
        String freq2="";
        start1 = getStartTime(e1);
        start2 = getStartTime(e2);
        end1 = getEndTime(e1);
        end2 = getEndTime(e2);
        freq1 = getFreq(e1);
        freq2 = getFreq(e2);
        concatOK = concatOK && (freq1.equals(freq2));
        if ((concatOK) && (start2>end1) && (end2>end1)) concatOK=false; 
        if ((concatOK) && (start2<start1) && (end1<start1)) concatOK=false;
      }
    }
    concat.setEnabled(concatOK);
    lockEvents--;
  }
  
  
  private void clearTable(DefaultTableModel dtm) {
    lockEvents++;
    dtm.setValueAt(null,0,1);
    dtm.setValueAt(null,1,1);
    dtm.setValueAt(null,2,1);
    dtm.setValueAt(null,3,1);
    dtm.setValueAt(null,4,1);
    lockEvents--;
  }
  
  
  class EventHandler implements ActionListener, ListSelectionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==exit) setVisible(false);
      
      else if (e.getSource()==addNew) {
        int confirm;
        if (impData.getSelectedIndices().length>1) confirm=JOptionPane.showConfirmDialog(DataImporter.this,"Import these data files into your current set?");
        else confirm=JOptionPane.showConfirmDialog(DataImporter.this,"Import this data file into your current set?");
        if (confirm==JOptionPane.YES_OPTION) {
          long startSim = Long.parseLong(theModel.valueOf("/model/track/start"));
          long endSim = Long.parseLong(theModel.valueOf("/model/track/end"));
          long adjustStartSim = startSim;
          long adjustEndSim = endSim;
          
          for (int i=0; i<impData.getSelectedIndices().length; i++) {
            Element e1 = (Element) ((Element) impEntries.get(i)).clone();
            String origName = e1.valueOf("data");
            String name = "";
            String message = "Please give a name for the new data file";
            while (name.equals("")) {
              name = e1.valueOf("name");
              name = JOptionPane.showInputDialog(DataImporter.this,message,name);
              if ((dataFormats.selectNodes("/dataformat/environment[name='"+name+"']").size()>0) ||
                  (dataFormats.selectNodes("/dataformat/field[name='"+name+"']").size()>0) ||
                  (dataFormats.selectNodes("/dataformat/functionalgroup[name='"+name+"']").size()>0)) {
                message = "Please give a unique name for the new data file";
                name="";
              }
            }              
            e1.element("name").setText(name);  
            String fileName = e1.valueOf("data");
            long compareStart = getStartTime(e1);
            long compareEnd = getEndTime(e1);
            if (compareStart<adjustStartSim) adjustStartSim=compareStart;
            if (compareEnd>adjustEndSim) adjustEndSim=compareEnd;
            fileName = StringTools.assureUniqueFile(sourcePath,fileName);
            e1.element("data").setText(fileName);
            ((Element) dataFormats.selectSingleNode("/dataformat")).add(e1);
            StringTools.copyFile(importPath, sourcePath, origName, fileName);
          }

          try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileWriter(sourcePath+File.separator+"DataFormats.xml"), format);
            writer.write(dataFormats);
            writer.flush();
          } catch (IOException e1) { e1.printStackTrace(); }
          if ((adjustStartSim!=startSim) || (adjustEndSim!=endSim)) {
            theModel.selectSingleNode("/model/track/start").setText(String.valueOf(adjustStartSim));
            theModel.selectSingleNode("/model/track/end").setText(String.valueOf(adjustEndSim));
            try {
              OutputFormat format = OutputFormat.createPrettyPrint();
              XMLWriter writer = new XMLWriter(new FileWriter(sourcePath+File.separator+"Model.xml"), format);
              writer.write(theModel);
              writer.flush();
            } catch (IOException e1) { e1.printStackTrace(); }
          }
        }
        JOptionPane.showMessageDialog(DataImporter.this,"File added successfully. Restart Analyser!");
      
      } else if (e.getSource()==concat) {
        cd.showDialog((Element)(myEntries.get(myData.getSelectedIndex())),(Element)(impEntries.get(impData.getSelectedIndex())),dataFormats,theModel);

      } else if (e.getSource()==locateData) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(DataFormatsFilter);
        jfc.setCurrentDirectory(new File(importPath));
        int result = jfc.showOpenDialog(DataImporter.this);
        if (result==JFileChooser.APPROVE_OPTION) {
          try {
            importPath = jfc.getSelectedFile().getAbsolutePath();
            locateData.setText(importPath);
            int i = importPath.length()-1;
            while (!(importPath.charAt(i)==File.separatorChar)) i--;
            importPath = importPath.substring(0,i);
            SAXReader reader = new SAXReader();
            Document importDF =  reader.read(new FileReader(jfc.getSelectedFile().getAbsolutePath()));
            initialise(importDF, impDataModel, impEntries);
          } catch (Exception ex) {ex.printStackTrace(); }
          impData.clearSelection();
          updateButtons();
        }
      }
    }

    public void valueChanged(ListSelectionEvent e) {
      if (lockEvents==0) {
        if (e.getSource()==myData) {
          if (myData.getSelectedIndex()<0) clearTable(myTableModel);
          else updateTable(myTableModel, (Element)myEntries.get(myData.getSelectedIndex()));
          updateButtons();
        
        } else if (e.getSource()==impData) {
          if (impData.getSelectedIndex()<0) clearTable(impTableModel);
          else updateTable(impTableModel, (Element)impEntries.get(impData.getSelectedIndex()));
          updateButtons();
          
        }
      }
    }
  }
  
  class DFFileFilter extends FileFilter {
    public boolean accept(File f) { return ((f.isDirectory()) || (f.getName().equals("DataFormats.xml"))); }
    public String getDescription() { return new String("VEW Data Format File");  }
  }
  
  class ReadOnlyTableModel extends DefaultTableModel {
    public boolean isCellEditable(int r, int c) { return false; }
  }
  
  class ConcatDialog extends JDialog {
    ReadOnlyTableModel concatTableModel = new ReadOnlyTableModel();
    JTable concatTable = new JTable(concatTableModel);
    JButton dateChoice = new JButton("Date");
    JButton okButton = new JButton("Ok");
    JButton cancelButton = new JButton("Cancel");
    DateDialog dd;
    ConcatEventHandler ceh;
    Element myTag, importTag;
    Document myDoc, myModel;
    final static String _MAIN = "Main dataset";
    final static String _IMPORT = "Imported dataset";
    
    public ConcatDialog(JDialog parent) {
      super(parent,"Concatenation options",true);
      ceh = new ConcatEventHandler();
      dd = new DateDialog(this,1800);
      getContentPane().setLayout(new BorderLayout());
      JPanel dateFlow = new JPanel(new FlowLayout());
        dateFlow.add(new JLabel("Transition Date:")); 
        dateFlow.add(dateChoice);
        getContentPane().add(dateFlow,BorderLayout.NORTH);
      JScrollPane tableScroller = new JScrollPane(concatTable);
        getContentPane().add(tableScroller,BorderLayout.CENTER);
        tableScroller.setPreferredSize(new Dimension(400,80));
      concatTableModel.setColumnCount(3);
      concatTableModel.setColumnIdentifiers(new String[] {"Data","Earliest","Latest"});
      concatTableModel.setRowCount(2);
      JPanel buttonPanel = new JPanel(new FlowLayout());
      buttonPanel.add(okButton);
      buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);
      pack();
      dateChoice.addActionListener(ceh);
      okButton.addActionListener(ceh);
      cancelButton.addActionListener(ceh);
    }
    
    public void showDialog(Element _myTag, Element _importTag, Document _myDoc, Document _modelDoc) {
      myTag = _myTag;
      importTag = _importTag;
      myDoc = _myDoc;
      myModel = _modelDoc;
      
      long start1=getStartTime(myTag);
      long start2=getStartTime(importTag);
      long end1=getEndTime(myTag);
      long end2=getEndTime(importTag);
      
      if (start1<=start2) {
        concatTableModel.setValueAt(_MAIN,0,0);
        concatTableModel.setValueAt(longToDate(start1),0,1);
        concatTableModel.setValueAt(longToDate(end1),0,2);        
        concatTableModel.setValueAt(_IMPORT,1,0);
        concatTableModel.setValueAt(longToDate(start2),1,1);
        concatTableModel.setValueAt(longToDate(end2),1,2);        
      } else {
        concatTableModel.setValueAt(_MAIN,1,0);
        concatTableModel.setValueAt(longToDate(start1),1,1);
        concatTableModel.setValueAt(longToDate(end1),1,2);        
        concatTableModel.setValueAt(_IMPORT,0,0);
        concatTableModel.setValueAt(longToDate(start2),0,1);
        concatTableModel.setValueAt(longToDate(end2),0,2);
      }
      
      gc.setTimeInMillis(Math.min(end1,end2));
      dd.setLatest(gc);
      gc.setTimeInMillis(Math.max(start1,start2));
      dd.setEarliest(gc);
      dateChoice.setText(DateDialog.getString(gc));
      setVisible(true);
    }
    
    class ConcatEventHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==dateChoice) {
          dd.show(gc);
          gc.setTimeInMillis(dd.getDate().getTimeInMillis());
          dateChoice.setText(DateDialog.getString(gc));
        
        } else if (e.getSource()==okButton) {
          if (JOptionPane.showConfirmDialog(ConcatDialog.this,"Perform concatenation? (This could take a while...)")==JOptionPane.YES_OPTION) {
            
            Thread t = new Thread(new ConcatThread(gc.getTimeInMillis()));
            t.start();
          }
          setVisible(false);
        
        } else if (e.getSource()==cancelButton) setVisible(false);
        
        
      }
    }
    
    class ConcatThread implements Runnable {
      long time;
      
      public ConcatThread(long _time) { time = _time; }
      
      public void run() {
        ProgressMonitor pm = new ProgressMonitor(DataImporter.this,"Creating new data file","",0,1000);
        pm.setMaximum(1000);
        pm.setProgress(0);
        
        String file1 = sourcePath+File.separator+myTag.valueOf("data");
        String file2 = importPath+File.separator+importTag.valueOf("data");
        String file3 = StringTools.assureUniqueFile(sourcePath,myTag.valueOf("data"));
        final long step = Long.parseLong(myModel.valueOf("/model/track/secstep"))*1000;
        final int vars = myTag.selectNodes("var").size();
        final long start1 = getStartTime(myTag);
        final long end2 = getEndTime(importTag);
        final float total = end2-start1;
        final byte[] buffer = new byte[vars*4];
        try {
          DataInputStream theFile1 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file1))));
          DataInputStream theFile2 = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(file2))));
          DataOutputStream outFile = new DataOutputStream(new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(sourcePath+File.separator+file3))));
          for (long i=start1; i<time; i+=step) {
            theFile1.read(buffer,0,buffer.length);
            outFile.write(buffer);
            final int prog = (int)(1000*((i-start1)/total));
            pm.setProgress(prog);
            pm.setNote("Copying early data "+String.valueOf(prog/10.0)+" %");
          }
          theFile1.close();
          for (long i=time; i<end2; i+=step) {
            theFile2.read(buffer,0,buffer.length);
            outFile.write(buffer);
            final int prog = (int)(1000*((i-start1)/total));
            pm.setProgress(prog);
            pm.setNote("Appending extra data "+String.valueOf(prog/10.0)+ " %");
          }
          pm.close();
          theFile2.close();
          outFile.flush();
          outFile.close();
          
          // Update model file if necessary
          
          long startSim = Long.parseLong(myModel.valueOf("/model/track/start"));
          long endSim = Long.parseLong(myModel.valueOf("/model/track/start"));
          if ((start1<startSim) || (end2>endSim)) {
            myModel.selectSingleNode("/model/track/start").setText(String.valueOf(Math.min(start1,startSim)));
            myModel.selectSingleNode("/model/track/end").setText(String.valueOf(Math.max(endSim,end2)));
            try {
              OutputFormat format = OutputFormat.createPrettyPrint();
              XMLWriter writer = new XMLWriter(new FileWriter(sourcePath+File.separator+"Model.xml"), format);
              writer.write(myModel);
              writer.flush();
            } catch (IOException e1) { e1.printStackTrace(); }
          }
          
          // Update DataFormats file
          
          setStartTime(myTag, start1);
          setEndTime(myTag,end2);
          myTag.selectSingleNode("data").setText(file3);
     
          try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileWriter(sourcePath+File.separator+"DataFormats.xml"), format);
            writer.write(myDoc);
            writer.flush();
          } catch (IOException e1) { e1.printStackTrace(); }
        } catch (Exception e) { e.printStackTrace(); }
        JOptionPane.showMessageDialog(DataImporter.this,"File appended successfully. Restart Analyser!");
      }
    
    }
  }
}
