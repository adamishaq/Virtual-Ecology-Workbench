package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import VEW.Analyser4.Analyser4;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class ShowResults extends JPanel {
  private final DefaultTableModel resultsTableModel = new DefaultTableModel();
  private final JTable resultsTable = new JTable(resultsTableModel);
  private final JButton showResults = new JButton("Analyse");
  private final JButton locateResults = new JButton("Locate Files");  
  private final JButton removeResults = new JButton("Remove");
  private final EventHandler eh = new EventHandler();
  
  public ShowResults() {
    setPreferredSize(new Dimension(760,651));
    setLayout(new BorderLayout());
    JScrollPane tableScroller = new JScrollPane(resultsTable);
    add(tableScroller,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(showResults);
    buttonPanel.add(locateResults);
    buttonPanel.add(removeResults);
    add(buttonPanel,BorderLayout.SOUTH);
    resultsTableModel.setColumnIdentifiers(new String[] {"Name","Path"});
    tableScroller.setPreferredSize(new Dimension(600,400));
    removeResults.addActionListener(eh);
    locateResults.addActionListener(eh);
    showResults.addActionListener(eh);
    resultsTable.getSelectionModel().addListSelectionListener(eh);
    resultsTable.getColumnModel().getSelectionModel().addListSelectionListener(eh);
  }
  
  public void updateButtons() {
    final int noSelections = resultsTable.getSelectedRows().length;
    showResults.setEnabled(noSelections==1);
    removeResults.setEnabled(noSelections>0);
  }
  
  public void showHistory() {
    while (resultsTableModel.getRowCount()>0) resultsTableModel.removeRow(0);
    File f = new File("results.xml");
    if (!f.exists()) {
      XMLFile history = new XMLFile("results.xml","results");
      history.write();
    }
    XMLTag resultTag = XMLFile.LoadFile("results.xml");
    XMLTag.sortTagList(resultTag,"result","@name");
    XMLTag[] results = resultTag.getTags("result");
    for (int i=0; i<results.length; i++) {
      String path = results[i].getAttribute("path");
      File f1 = new File(path+File.separator+"Model.xml");
      File f2 = new File(path+File.separator+"DataFormats.xml");
      if ((f1.exists()) && (f2.exists())) 
        resultsTableModel.addRow(new String[] {results[i].getAttribute("name"),results[i].getAttribute("path")});
      else results[i].removeFromParent();
    }
    resultTag.write();
  }
  
  public void refresh() {
    showHistory();
    updateButtons();
  }
  
 
  class EventHandler implements ActionListener, ListSelectionListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==showResults) {
        String path = resultsTableModel.getValueAt(resultsTable.getSelectedRow(),1).toString();
        Analyser4 A = new Analyser4(path,"Model.xml", "DataFormats.xml");
        A.setVisible(true);

      } else if (e.getSource()==locateResults) {
        JFileChooser jfc = new JFileChooser();
        DXMLFileFilter dxml = new DXMLFileFilter();
        jfc.setFileFilter(dxml);
        if (jfc.showOpenDialog(jfc) == JFileChooser.APPROVE_OPTION) {
          String pathDir = jfc.getCurrentDirectory().toString();
          File f = new File(pathDir+File.separator+"Model.xml");
          String name = pathDir;
          if (f.exists()) {
            XMLFile mf = XMLFile.LoadFile(pathDir+File.separator+"Model.xml");
            if (mf.getTag("buildname")!=null) name=mf.getValue("buildname");
            else if (mf.getTag("name")!=null) name=mf.getValue("name");
          }
          XMLTag resultTag = XMLFile.LoadFile("results.xml");
          XMLTag newResult = new XMLTag("result");
          newResult.setAttribute("name",name);
          newResult.setAttribute("path",pathDir);
          if (resultTag.getTagWhere("result","@name",name)==null) resultTag.addTag(newResult);
          else if (resultTag.getTagWhere("result","@name",name).getAttribute("path").equals("path")) resultTag.addTag(newResult); 
          resultTag.write();
          showHistory();
          Analyser4 A = new Analyser4(pathDir,"Model.xml", "DataFormats.xml");
          A.setVisible(true);
          A = null;

        }
        

      } else if (e.getSource()==removeResults) {
        int result1 = JOptionPane.showConfirmDialog(ShowResults.this,"This will remove the selected entries from this table. Would you like to delete the results as well?");
        if (result1==JOptionPane.YES_OPTION) {
          int result2 = JOptionPane.showConfirmDialog(ShowResults.this,"Just to confirm: This will delete the selected files forever, ok?");
          if (result2!=JOptionPane.YES_OPTION) result1=JOptionPane.CANCEL_OPTION;
        }
        int[] rows = resultsTable.getSelectedRows();
        for (int i=0; i<rows.length; i++) {
          String thisName = resultsTableModel.getValueAt(rows[i],0).toString();
          String thisPath = resultsTableModel.getValueAt(rows[i],1).toString();
          if ((result1==JOptionPane.YES_OPTION) || (result1==JOptionPane.NO_OPTION)) {
            XMLTag resultTag = XMLFile.LoadFile("results.xml");
            XMLTag[] results = resultTag.getTags("result");
            for (int j=0; j<results.length; j++) {
              if ((results[j].getAttribute("name").equals(thisName)) && (results[j].getAttribute("path").equals(thisPath))) {
                results[j].removeFromParent();
                if (result1==JOptionPane.YES_OPTION) {
                  File[] fList = new File(thisPath).listFiles();
                  for (int k=0; k<fList.length; k++) {
                    if (fList[k].getName().equals("biData.bin")) fList[k].delete();
                    else if (fList[k].getName().equals("chData.bin")) fList[k].delete();
                    else if (fList[k].getName().equals("phData.bin")) fList[k].delete();
                    else if (fList[k].getName().equals("wcData.bin")) fList[k].delete();
                    else if (fList[k].getName().startsWith("fg_")) fList[k].delete();
                    else if (fList[k].getName().startsWith("lifespan_")) fList[k].delete();
                    else if (fList[k].getName().startsWith("lineage_")) fList[k].delete();
                    else if (fList[k].getName().equals("start.txt")) fList[k].delete();
                    else if (fList[k].getName().equals("end.txt")) fList[k].delete();
                    else if (fList[k].getName().equals("latest.txt")) fList[k].delete();
                    else if (fList[k].getName().equals("flush.txt")) fList[k].delete();
                    else if (fList[k].getName().equals("abort.txt")) fList[k].delete();
                    else if (fList[k].getName().equals("DataFormats.xml")) fList[k].delete();
                  }
                }
              }
            }
            resultTag.write();
            showHistory();
          }      
        }
      }
    }
    
    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==resultsTable.getSelectionModel() && resultsTable.getRowSelectionAllowed()) ||
        (e.getSource()==resultsTable.getColumnModel().getSelectionModel() && resultsTable.getColumnSelectionAllowed())) {
        updateButtons();
      }

      
    }
  }
  static class DXMLFileFilter extends FileFilter {
    public boolean accept(java.io.File f) {
      return ((f.getName().toUpperCase().endsWith("DATAFORMATS.XML")) || (f.isDirectory()));
    }

    public String getDescription() {
      return new String("DataFormats XML Description");
    }
  }

}
