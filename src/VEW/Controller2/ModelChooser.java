package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class ModelChooser extends JPanel {
  
  DefaultListModel modelChoiceModel = new DefaultListModel(); 
  JList modelChoice = new JList(modelChoiceModel);
  private JEditorPane authorText = new JEditorPane();
  private JEditorPane modelComment = new JEditorPane();
  private JLabel authorLabel = new JLabel("Author(s): ",JLabel.RIGHT);
  private final JLabel modelCommentLabel = new JLabel("<html><p align=\"right\">Model<br>Comments:</p></html>",JLabel.RIGHT);
  private JButton saveModelDetails = new JButton("Save Model Details");
  private JButton loadModel = new JButton("Open Model");
  private String dataPath = "ModelTree"+File.separator;
  private JButton renameModel = new JButton("Rename");
  private JButton deleteModel = new JButton("Delete");
  private JButton newModel = new JButton("New");
  private JButton importModel = new JButton("Import");
  private EventHandler eh = new EventHandler();
  private int lockEvents=0;
  private TitlePage tp = null;
  private static ModelFileFilter modelFileFilter;
  
  private void populateList() {
    lockEvents++;
    modelChoiceModel.removeAllElements();
    File f = new File(dataPath);
    File[] fileList = f.listFiles();
    for (int i=0; i<fileList.length; i++) {
      if (fileList[i].isDirectory()) {
        String s = fileList[i].getName();
        if (!s.startsWith(".")) {
          int j=0;
          while ((j<modelChoiceModel.getSize()) && (modelChoiceModel.getElementAt(j).toString().compareToIgnoreCase(s)<0)) j++;
          if (j<modelChoiceModel.getSize()) modelChoiceModel.insertElementAt(s,j);
          else modelChoiceModel.addElement(s);
        }
      }
    }
    authorText.setText("");
    modelComment.setText("");
    modelChoice.clearSelection();
    lockEvents--;
  }
  
  public void refresh() {
    populateList();
    updateButtons();
  }
  
  public ModelChooser(TitlePage _tp) {
    tp=_tp;
    setPreferredSize(new Dimension(760,651));
    modelFileFilter = new ModelFileFilter();
    setLayout(new FlowLayout());
    JPanel modelChoicePanel = new JPanel(new BorderLayout());
    JScrollPane modelChoiceScroller = new JScrollPane(modelChoice);
    modelChoicePanel.add(modelChoiceScroller,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new BorderLayout());
    JPanel row1 = new JPanel(new FlowLayout());
    JPanel row2 = new JPanel(new FlowLayout());
    row1.add(newModel);
    row1.add(importModel);
    row2.add(renameModel);
    row2.add(deleteModel);
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
      modelCommentLabel.setPreferredSize(new Dimension(80,30));
      JScrollPane commentScroller = new JScrollPane(modelComment);
      commentScroller.setPreferredSize(new Dimension(300,138));
      commentPanel.add(modelCommentLabel);
      commentPanel.add(commentScroller);
    JPanel modelPanel = new JPanel(new BorderLayout());
    JPanel modelInfo = new JPanel(new BorderLayout());
      modelInfo.add(authorPanel,BorderLayout.NORTH);
      modelInfo.add(commentPanel,BorderLayout.SOUTH);
      modelInfo.setBorder(new EtchedBorder());
      modelPanel.add(modelInfo,BorderLayout.CENTER);
    JPanel modelButtonsPanel = new JPanel(new BorderLayout());
      JPanel mRow1 = new JPanel(new FlowLayout());
      mRow1.add(saveModelDetails);
      mRow1.add(loadModel);
      modelButtonsPanel.add(mRow1,BorderLayout.NORTH);
      JPanel mRow2 = new JPanel(new FlowLayout());
      mRow2.setPreferredSize(row2.getPreferredSize());
      modelButtonsPanel.add(mRow2,BorderLayout.SOUTH);
      modelPanel.add(modelButtonsPanel,BorderLayout.SOUTH);
    add(modelPanel);
    renameModel.setEnabled(false);
    deleteModel.setEnabled(false);
    modelChoice.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    saveModelDetails.setEnabled(false);
    loadModel.setEnabled(false);
    modelComment.addKeyListener(eh);
    authorText.addKeyListener(eh);
    modelChoice.addListSelectionListener(eh);
    authorText.setContentType("text/html");
    modelComment.setContentType("text/html");
    renameModel.addActionListener(eh);
    newModel.addActionListener(eh);
    importModel.addActionListener(eh);
    deleteModel.addActionListener(eh);
    saveModelDetails.addActionListener(eh);
    loadModel.addActionListener(eh);
  }
  
  public void updateButtons() {
    renameModel.setEnabled(modelChoice.getSelectedValues().length==1);
    deleteModel.setEnabled(modelChoice.getSelectedValues().length==1);
    loadModel.setEnabled(modelChoice.getSelectedValues().length==1);    
  }
  
  public boolean checkSavedOK() {
    if (saveModelDetails.isEnabled()) {
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
    String theName = modelChoice.getSelectedValue().toString();
    XMLFile xf = XMLFile.LoadFile(dataPath+theName+File.separator+"meta.xml");
    xf.setValue("author",StringTools.htmlIntoXML(authorText.getText()));
    xf.setValue("modeltext",StringTools.htmlIntoXML(modelComment.getText()));
    xf.write();
    saveModelDetails.setEnabled(false);
    lockEvents--;
  }
  
  class EventHandler implements ActionListener, ListSelectionListener, KeyListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==deleteModel) {
        lockEvents++;
        String oldName = modelChoice.getSelectedValue().toString();
        int result = JOptionPane.showConfirmDialog(ModelChooser.this,"Really delete this model, and all versions and results?");
        if (result==JOptionPane.YES_OPTION) {
          File f = new File(dataPath+oldName);
          StringTools.recursiveDelete(f);
          refresh();
        }
        lockEvents--;
        updateButtons();
        
      } else if (e.getSource()==renameModel) {
        lockEvents++;
        String oldName = modelChoice.getSelectedValue().toString();
        String newName = JOptionPane.showInputDialog(ModelChooser.this,"Type the new name for the model",oldName);
        if ((newName!=null) && (!newName.equals(oldName))) {
          File f = new File(dataPath+oldName);
          f.renameTo(new File(dataPath+newName));
          String authorT = authorText.getText();
          String modelT = modelComment.getText();
          refresh();
          authorText.setText(authorT);
          modelComment.setText(modelT);
          StringTools.setListItem(modelChoice,newName);
        }
        lockEvents--;
        updateButtons();        
        
      } else if (e.getSource()==saveModelDetails) {
        saveChanges();
        updateButtons();        
        
      } else if (e.getSource()==newModel) {
        if (checkSavedOK()) {
          lockEvents++;
          String theName = JOptionPane.showInputDialog(ModelChooser.this,"Type the name for the new model");
          if (theName!=null) {
            File f = new File(dataPath+theName);
            f.mkdirs();
            XMLFile xf = new XMLFile(dataPath+theName+File.separator+"meta.xml","meta");
            xf.addTag(new XMLTag("author","Type the author information here."));
            xf.addTag(new XMLTag("modeltext","Type information about the model here."));
            xf.write();
            refresh();
            authorText.setText("Type the author information here.");
            modelComment.setText("Type information about the model here.");
            StringTools.setListItem(modelChoice,theName);
            saveModelDetails.setEnabled(false);
            f = new File(dataPath+theName+File.separator+"1");
            f.mkdirs();
            StringTools.copyFile(dataPath+"default.xml",dataPath+theName+File.separator+"1"+File.separator+"Model.xml");
            xf = XMLFile.LoadFile(dataPath+theName+File.separator+"1"+File.separator+"Model.xml");
            xf.setValue("buildno","1");
            xf.setValue("buildtext","Type information about this version here.");
            xf.setValue("buildname","First import");
            xf.setValue("buildauthor","Type author information here.");
            xf.setValue("modelname",theName);
            xf.setValue("modeltext","Type information about the model here.");
            xf.setValue("modelauthor","Type the author information here.");
            xf.write();
          }
          updateButtons();
          lockEvents--;
        }
        updateButtons();
        
      } else if (e.getSource()==importModel) {
        if (checkSavedOK()) {
          lockEvents++;
          JFileChooser jfc = new JFileChooser();
          jfc.setDialogTitle("Locate the Model.xml file to import");
          jfc.setFileFilter(modelFileFilter);
          int result = jfc.showOpenDialog(ModelChooser.this);
          if (result==JFileChooser.APPROVE_OPTION) {
            String newName = JOptionPane.showInputDialog(ModelChooser.this,"Type a name for this model");
            if (newName!=null) {
              File fwrite = new File(dataPath+newName+File.separator+"1");
              fwrite.mkdirs();
              XMLFile xf = new XMLFile(dataPath+newName+File.separator+"meta.xml","meta");
              xf.addTag(new XMLTag("author","Type the author information here."));
              xf.addTag(new XMLTag("modeltext","Type information about the model here."));
              xf.write();
              File fread = jfc.getSelectedFile();
              XMLFile xf1 = XMLFile.LoadFile(fread.getAbsolutePath());
              XMLFile xf2;
              if ((xf1.getValue("version")==null) || (!xf1.getValue("version").equals("3.1"))) {
                xf2 = new XMLFile(dataPath+newName+File.separator+"1"+File.separator+"Model.xml","model");
                ModelConverter.convert(xf1,xf2);
              } else {
                xf2 = (XMLFile) xf1.clone();
                xf2.setFileName(dataPath+newName+File.separator+"1"+File.separator+"Model.xml");
              }
              xf2.setValue("buildno","1");
              xf2.setValue("buildtext","Type information about this version here.");
              xf2.setValue("buildname","First import");
              xf2.setValue("buildauthor","Type author information here.");
              xf2.setValue("modelname",newName);
              xf2.setValue("modeltext","Type information about the model here.");
              xf2.setValue("modelauthor","Type the author information here.");
              xf2.write();
              refresh();
              authorText.setText("Type the author information here.");
              modelComment.setText("Type information about the model here.");
              StringTools.setListItem(modelChoice,newName);
              updateButtons();
              saveModelDetails.setEnabled(false);
              lockEvents--;
            }
          }
        }
        updateButtons();
        
      } else if (e.getSource()==loadModel) {
        if (checkSavedOK()) {
          try {
            XMLFile.LoadFile(dataPath+modelChoice.getSelectedValue().toString()+File.separator+"meta.xml");
            tp.showVersionPanel(dataPath+modelChoice.getSelectedValue().toString()+File.separator);          
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(ModelChooser.this,"<html><center>The meta-file for this model is damaged. Error message:<br>"+ex.getMessage()+"</br></center></html>");
          }
        }
      }
    }
    
    public void caretUpdate(CaretEvent e) {
      if (((e.getSource()==authorText) || (e.getSource()==modelComment)) && (lockEvents==0)) {
        saveModelDetails.setEnabled(true);
      }
    }
    public void valueChanged(ListSelectionEvent e) {
      if ((lockEvents==0) && (e.getSource()==modelChoice) && (e.getValueIsAdjusting())) {
        lockEvents++;
        updateButtons();
        if (modelChoice.getSelectedIndices().length==1) {
          String s = modelChoice.getSelectedValue().toString();
          XMLFile meta = XMLFile.LoadFile(dataPath+s+File.separator+"meta.xml");
          authorText.setText(StringTools.htmlFromXML(meta.getValue("author")));
          modelComment.setText(StringTools.htmlFromXML(meta.getValue("modeltext")));
          saveModelDetails.setEnabled(false);          
        } else {
          authorText.setText("");
          modelComment.setText("");
          saveModelDetails.setEnabled(false);
        }
        lockEvents--;
      }
      
    }
    public void keyPressed(KeyEvent e) {
      if ((lockEvents==0) && ((e.getSource()==authorText) || (e.getSource()==modelComment))) {
       saveModelDetails.setEnabled(true);
      }
    }
    
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
  }
  
  static class ModelFileFilter extends FileFilter {
    public boolean accept(File f) { return ((f.isDirectory()) || (f.getName().toUpperCase().equals("MODEL.XML"))); }
    public String getDescription() { return "Model Specification File"; }
  }
}
