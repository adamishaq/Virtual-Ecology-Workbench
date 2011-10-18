package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Documenter2.DocumentViewer;

public class VersionChooser extends JPanel {
  
  ReadOnlyTableModel versionChoiceModel = new ReadOnlyTableModel(); 
  JTable versionChoice = new JTable(versionChoiceModel);
  private JEditorPane authorText = new JEditorPane();
  private JEditorPane versionComment = new JEditorPane();
  private JLabel authorLabel = new JLabel("Author(s): ",JLabel.RIGHT);
  private final JLabel versionCommentLabel = new JLabel("<html><p align=\"right\">Version<br>Comments:</p></html>",JLabel.RIGHT);
  private JButton saveVersionDetails = new JButton("Save Version Details");
  private JButton loadVersion = new JButton("Open Version");
  private JLabel modelName = new JLabel("Model Name");
  private String modelPath;
  private JButton documenter = new JButton("Documentation");
  private JButton renameVersion= new JButton("Rename");
  private JButton deleteVersion = new JButton("Delete");
  private JButton newVersion = new JButton("New");
  private JButton importVersion = new JButton("Import");
  private EventHandler eh = new EventHandler();
  private int lockEvents=0;
  private static ModelFileFilter modelFileFilter;
  private static TitlePage tp;
  
  private void populateList() {
    lockEvents++;
    while (versionChoiceModel.getRowCount()>0) versionChoiceModel.removeRow(0);
    File f = new File(modelPath);
    File[] fileList = f.listFiles();
    for (int i=0; i<fileList.length; i++) {
      if (fileList[i].isDirectory()) {
        String dirName = fileList[i].getName();
        if (!dirName.startsWith(".")) {
          int buildNo = -1;
          try { buildNo = Integer.parseInt(dirName); }
          catch (Exception e) {}
          if (buildNo>0) {
            try {
              XMLFile xf = XMLFile.LoadFile(modelPath+dirName+File.separator+"Model.xml");
              String buildName = xf.getValue("buildname");
              String[] entry = new String[2];
              entry[0]=dirName;
              entry[1]=buildName;
              int j=0;
              boolean done=false;
              while ((!done) && (j<versionChoiceModel.getRowCount())) {
                int tableBuild = Integer.parseInt(versionChoiceModel.getValueAt(j,0).toString());
                if (tableBuild>buildNo) done=true;
                else j++;
              }
              if (!done) versionChoiceModel.addRow(entry);
              else versionChoiceModel.insertRow(j,entry);
            } catch (Exception e) {
              JOptionPane.showMessageDialog(this,"<html><center>The model specification for version no. "+buildNo+" of this model is damaged. Error message:<br>"+e.getMessage()+"</br></center></html>");
            }
          }
        }
      }
    }
    authorText.setText("");
    versionComment.setText("");
    versionChoice.clearSelection();
    lockEvents--;
  }
  
  public void refresh() {
    populateList();
    updateButtons();
  }
  
  public void selectLast() {
    lockEvents++;
    int row = versionChoice.getRowCount()-1;
    if (row>=0) {
      String buildNo = versionChoiceModel.getValueAt(row,0).toString();
      XMLFile xf= XMLFile.LoadFile(modelPath+buildNo+File.separator+"Model.xml");
      authorText.setText(StringTools.htmlFromXML(xf.getValue("buildauthor")));
      versionComment.setText(StringTools.htmlFromXML(xf.getValue("buildtext")));
      saveVersionDetails.setEnabled(false);
      versionChoice.setRowSelectionInterval(row,row);
    }
    updateButtons();
    lockEvents--;
  }
  
  public VersionChooser(TitlePage _tp) {
    tp = _tp;
    setPreferredSize(new Dimension(760,651));
    modelFileFilter = new ModelFileFilter();
    versionChoiceModel.setColumnIdentifiers(new String[] {"No","Version Title"});
    setLayout(new FlowLayout());
    JPanel modelChoicePanel = new JPanel(new BorderLayout());
    JScrollPane modelChoiceScroller = new JScrollPane(versionChoice);
    modelChoicePanel.add(modelChoiceScroller,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new BorderLayout());
    JPanel row1 = new JPanel(new FlowLayout());
    JPanel row2 = new JPanel(new FlowLayout());
    row1.add(newVersion);
    row1.add(importVersion);
    row2.add(renameVersion);
    row2.add(deleteVersion);
    buttonPanel.add(row1,BorderLayout.NORTH);
    buttonPanel.add(row2,BorderLayout.SOUTH);
    modelChoicePanel.add(buttonPanel,BorderLayout.SOUTH);
    add(modelChoicePanel);
    modelChoiceScroller.setPreferredSize(new Dimension(150,260));
    JPanel authorPanel = new JPanel(new FlowLayout());
      JScrollPane authorScroller = new JScrollPane(authorText);
      authorScroller.setPreferredSize(new Dimension(300,98));
      authorPanel.add(authorLabel);
      authorLabel.setPreferredSize(new Dimension(80,30));
      authorPanel.add(authorScroller);
      authorText.setContentType("text/html");
    JPanel commentPanel = new JPanel(new FlowLayout());
      versionCommentLabel.setPreferredSize(new Dimension(80,30));
      JScrollPane commentScroller = new JScrollPane(versionComment);
      commentScroller.setPreferredSize(new Dimension(300,138));
      commentPanel.add(versionCommentLabel);
      commentPanel.add(commentScroller);
    JPanel modelPanel = new JPanel(new BorderLayout());
    JPanel modelInfo = new JPanel(new BorderLayout());
      modelInfo.add(authorPanel,BorderLayout.NORTH);
      modelInfo.add(commentPanel,BorderLayout.SOUTH);
      modelInfo.setBorder(new EtchedBorder());
      modelPanel.add(modelInfo,BorderLayout.CENTER);
    JPanel modelButtonsPanel = new JPanel(new BorderLayout());
      JPanel mRow1 = new JPanel(new FlowLayout());
      mRow1.add(saveVersionDetails);
      mRow1.add(loadVersion);
      modelButtonsPanel.add(mRow1,BorderLayout.NORTH);
      JPanel mRow2 = new JPanel(new FlowLayout());
      mRow2.add(documenter);
      mRow2.setPreferredSize(row2.getPreferredSize());
      modelButtonsPanel.add(mRow2,BorderLayout.SOUTH);
      modelPanel.add(modelButtonsPanel,BorderLayout.SOUTH);
    add(modelPanel);
    renameVersion.setEnabled(false);
    deleteVersion.setEnabled(false);
    versionChoice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    saveVersionDetails.setEnabled(false);
    loadVersion.setEnabled(false);
    versionComment.addKeyListener(eh);
    authorText.addKeyListener(eh);
    versionChoice.getSelectionModel().addListSelectionListener(eh);
    versionChoice.getColumnModel().getSelectionModel().addListSelectionListener(eh);
    authorText.setContentType("text/html");
    versionComment.setContentType("text/html");
    renameVersion.addActionListener(eh);
    newVersion.addActionListener(eh);
    newVersion.setEnabled(false);
    importVersion.addActionListener(eh);
    deleteVersion.addActionListener(eh);
    saveVersionDetails.addActionListener(eh);
    documenter.addActionListener(eh);
    loadVersion.addActionListener(eh);
  }
  
  public void setPath(String path) {
    modelPath = path;
    modelName.setText(path);
  }
  
  public void updateButtons() {
    renameVersion.setEnabled(versionChoice.getSelectedRows().length==1);
    deleteVersion.setEnabled(versionChoice.getSelectedRows().length==1);
    loadVersion.setEnabled(versionChoice.getSelectedRows().length==1);
    newVersion.setEnabled(versionChoice.getSelectedRows().length==1);
  }
  
  public boolean checkSavedOK() {
    if (saveVersionDetails.isEnabled()) {
      int result = JOptionPane.showConfirmDialog(tp,"Version details have been changed - save them now?");
      if (result==JOptionPane.YES_OPTION) {
        saveChanges();
        return true;
      } else if (result==JOptionPane.NO_OPTION) return true;
      else return false;
    } else return true;
  }
  
  public void saveChanges() {
    lockEvents++;
    int row = versionChoice.getSelectedRow();
    String buildNo = versionChoiceModel.getValueAt(row,0).toString();
    XMLFile xf = XMLFile.LoadFile(modelPath+buildNo+File.separator+"Model.xml");
    xf.setValue("buildauthor",StringTools.htmlIntoXML(authorText.getText()));
    xf.setValue("buildtext",StringTools.htmlIntoXML(versionComment.getText()));
    xf.write();
    saveVersionDetails.setEnabled(false);
    lockEvents--;
  }
  
  class EventHandler implements ActionListener, KeyListener, ListSelectionListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==deleteVersion) {
        lockEvents++;
        int row = versionChoice.getSelectedRow();
        String dirName = versionChoiceModel.getValueAt(row,0).toString();
        int result = JOptionPane.showConfirmDialog(VersionChooser.this,"Really delete this version, and all associated results?");
        if (result==JOptionPane.YES_OPTION) {
          File f = new File(modelPath+dirName);
          StringTools.recursiveDelete(f);
          refresh();
        }
        lockEvents--;
        
      } else if (e.getSource()==renameVersion) {
        lockEvents++;
        int row = versionChoice.getSelectedRow();
        String buildNo = versionChoiceModel.getValueAt(row,0).toString();
        String buildName = versionChoiceModel.getValueAt(row,1).toString();
        String newName = JOptionPane.showInputDialog(VersionChooser.this,"Type the new name for this version",buildName);
        if ((newName!=null) && (!newName.equals(buildName))) {
          XMLFile xf = XMLFile.LoadFile(modelPath+buildNo+File.separator+"Model.xml");
          xf.setValue("buildname",newName);
          xf.write();
          versionChoiceModel.setValueAt(newName,row,1);
          versionChoice.paint(versionChoice.getGraphics());
        }
        String authorT = authorText.getText();
        String versionT = versionComment.getText();
        refresh();
        authorText.setText(authorT);
        versionComment.setText(versionT);
        versionChoice.setRowSelectionInterval(row,row);
        lockEvents--;
        updateButtons();

        
      } else if (e.getSource()==saveVersionDetails) {
        saveChanges();
        updateButtons();
        
        
      } else if (e.getSource()==documenter) {
        int row = versionChoice.getSelectedRow();
        String buildNo = versionChoiceModel.getValueAt(row,0).toString();
        DocumentViewer dv = new DocumentViewer(tp,modelPath+buildNo+File.separator+"Model.xml");
        dv.setVisible(true);
        updateButtons();
      
      } else if (e.getSource()==newVersion) {
        if (checkSavedOK()) {
          lockEvents++;
          String theName = JOptionPane.showInputDialog(VersionChooser.this,"Type the name for the new version");
          if (theName!=null) {
            String newBuildNo = String.valueOf(Integer.parseInt(versionChoiceModel.getValueAt(versionChoiceModel.getRowCount()-1,0).toString())+1);
            int row = versionChoice.getSelectedRow();
            String thisBuild = versionChoiceModel.getValueAt(row,0).toString();
            File f = new File(modelPath+newBuildNo);
            f.mkdirs();
            XMLFile xf = XMLFile.LoadFile(modelPath+thisBuild+File.separator+"Model.xml");
            XMLFile xf2 = (XMLFile) xf.clone();
            xf2.setFileName(modelPath+newBuildNo+File.separator+"Model.xml");
            xf2.setValue("buildno",newBuildNo);
            xf2.setValue("buildtext","Type information about this version here.");
            versionComment.setText("Type information about this version here.");
            xf2.setValue("buildname",theName);
            xf2.write();
            xf2 = null;
            String authorT = authorText.getText();
            refresh();
            int lastrow = versionChoice.getRowCount()-1;
            versionChoice.setRowSelectionInterval(lastrow,lastrow);
            versionComment.setText("Type information about this version here.");
            authorText.setText(authorT);
            saveVersionDetails.setEnabled(false);
          }
          lockEvents--;
          updateButtons();
        }
        
      } else if (e.getSource()==importVersion) {
        if (checkSavedOK()) {
          lockEvents++;
          JFileChooser jfc = new JFileChooser();
          jfc.setDialogTitle("Locate the Model.xml file to import");
          jfc.setFileFilter(modelFileFilter);
          int result = jfc.showOpenDialog(VersionChooser.this);
          if (result==JFileChooser.APPROVE_OPTION) {
            String newName = JOptionPane.showInputDialog(VersionChooser.this,"Type a name for this build");
            if (newName!=null) {
              String newBuildNo = String.valueOf(Integer.parseInt(versionChoiceModel.getValueAt(versionChoiceModel.getRowCount()-1,0).toString())+1);
              File fwrite = new File(modelPath+newBuildNo);
              fwrite.mkdirs();
              File fread = jfc.getSelectedFile();
              XMLFile xf1 = XMLFile.LoadFile(fread.getAbsolutePath());
              XMLFile xf2;
              if ((xf1.getValue("version")==null) || (!xf1.getValue("version").equals("3.1"))) {
                xf2 = new XMLFile(modelPath+newBuildNo+File.separator+"Model.xml","model");
                ModelConverter.convert(xf1,xf2);
              } else {
                xf2 = (XMLFile) xf1.clone();
                xf2.setFileName(modelPath+newBuildNo+File.separator+"Model.xml");
              }
              XMLFile modelMeta = XMLFile.LoadFile(modelPath+"meta.xml");
              String _modelName=modelPath.substring(0,modelPath.length()-1);
              while (_modelName.indexOf(File.separator)>=0) 
                _modelName = _modelName.substring(_modelName.indexOf(File.separator)+1);
              xf2.setValue("buildno",newBuildNo);
              xf2.setValue("buildtext","Type information about this version here.");
              xf2.setValue("buildname",newName);
              xf2.setValue("buildauthor","Type author information here.");
              xf2.setValue("modelname",_modelName);
              xf2.setValue("modeltext",modelMeta.getValue("modeltext"));
              xf2.setValue("modelauthor",modelMeta.getValue("author"));
              xf2.write();
              refresh();
              selectLast();
              saveVersionDetails.setEnabled(false);
            }
          }
        }
        lockEvents--;
        updateButtons();

      } else if (e.getSource()==loadVersion) {
        if (checkSavedOK()) {
          int row = versionChoice.getSelectedRow();
          String buildNo = versionChoiceModel.getValueAt(row,0).toString();
          String modelFilename = modelPath+buildNo+File.separator+"Model.xml";
          VEWController2 vc2 = new VEWController2(tp, modelFilename);
          TitlePage.jumpToJobs=false;
          vc2.setVisible(true);
          if (TitlePage.jumpToJobs) tp.gotoJobs();
        }
      }
    }
    
    public void caretUpdate(CaretEvent e) {
      if (((e.getSource()==authorText) || (e.getSource()==versionComment)) && (lockEvents==0)) {
        saveVersionDetails.setEnabled(true);
      }
    }
  
    public void keyPressed(KeyEvent e) {
      if ((lockEvents==0) && ((e.getSource()==authorText) || (e.getSource()==versionComment))) {
       saveVersionDetails.setEnabled(true);
      }
    }
    
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource() == versionChoice.getSelectionModel() && versionChoice.getRowSelectionAllowed())
       || (e.getSource() == versionChoice.getColumnModel().getSelectionModel() && versionChoice.getColumnSelectionAllowed())) {
        if (lockEvents==0) {
          lockEvents++;
          if (versionChoice.getSelectedRows().length==1) {
            int row = versionChoice.getSelectedRow();
            String buildNo = versionChoiceModel.getValueAt(row,0).toString();
            XMLFile xf= XMLFile.LoadFile(modelPath+buildNo+File.separator+"Model.xml");
            authorText.setText(StringTools.htmlFromXML(xf.getValue("buildauthor")));
            versionComment.setText(StringTools.htmlFromXML(xf.getValue("buildtext")));
            saveVersionDetails.setEnabled(false);          
          } else {
            authorText.setText("");
            versionComment.setText("");
            saveVersionDetails.setEnabled(false);
          }
          updateButtons();
          lockEvents--;
        }
  
      }
      
    }
    
    
  }
  class ReadOnlyTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) { return false; }
  }
  static class ModelFileFilter extends FileFilter {
    public boolean accept(File f) { return ((f.isDirectory()) || (f.getName().toUpperCase().equals("MODEL.XML"))); }
    public String getDescription() { return "Model Specification File"; }
  }
}
