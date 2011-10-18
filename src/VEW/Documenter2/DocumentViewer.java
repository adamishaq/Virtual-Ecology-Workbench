package VEW.Documenter2;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;

public class DocumentViewer extends JDialog {
  private JButton saveButton = new JButton("Save As...");
  private JButton closeButton = new JButton("Close");
  private String htmlName;
  Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
  Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
  
  public DocumentViewer(JFrame parent, String modelFile) {
    super(parent,"Document Viewer",true);
    setSize(new Dimension(820,640));
    parent.setCursor(hourglassCursor);
    XMLFile xf = XMLFile.LoadFile(modelFile);
    JEditorPane jep = new JEditorPane();
    jep.setContentType("text/html");
    JScrollPane jsp = new JScrollPane(jep);
    jsp.setPreferredSize(new Dimension(800,600));
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(jsp,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(saveButton);
    buttonPanel.add(closeButton);
    getContentPane().add(buttonPanel,BorderLayout.SOUTH);
    DVEventHandler dveh = new DVEventHandler();
    saveButton.addActionListener(dveh);
    closeButton.addActionListener(dveh);
    htmlName = modelFile;
    if (htmlName.toUpperCase().endsWith(".XML")) htmlName = htmlName.substring(0,htmlName.length()-4)+".html";
    else htmlName+=".html";
    
    Documenter.run(xf,htmlName);
    StringBuffer sb = new StringBuffer();
    try {
      BufferedReader br = new BufferedReader(new FileReader(htmlName));
      while (br.ready()) sb.append(br.readLine());
    } catch (Exception e) { e.printStackTrace(); }
    jep.setText(sb.toString());
    pack();
    parent.setCursor(normalCursor);        
  }
  
  class DVEventHandler implements ActionListener {
    public DVEventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==saveButton) {
        JFileChooser jfc = new JFileChooser("model.html");
        jfc.setFileFilter(new HTMLFileFilter());
        int result = jfc.showSaveDialog(DocumentViewer.this);
        if (result==JFileChooser.APPROVE_OPTION) {
          StringTools.copyFile(htmlName,jfc.getSelectedFile().getPath());
        }
      } else if (e.getSource()==closeButton) {
        setVisible(false);
      }
    }
  }
  
  static class HTMLFileFilter extends FileFilter {
    public boolean accept(File f) { return ((f.isDirectory()) || (f.getName().toUpperCase().endsWith("HTML"))); }
    public String getDescription() { return "HTML File"; }
  }

}

