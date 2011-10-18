package VEW.Documenter2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileReader;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

public class DocumentWindow extends JDialog {
  private JEditorPane htmlPane;
  private JScrollPane scroller;
  
  public DocumentWindow() {
    setSize(600,600);
    getContentPane().setLayout(new BorderLayout(0,0));
    htmlPane = new JEditorPane();
    htmlPane.setContentType("text/html");
    scroller = new JScrollPane(htmlPane);
    scroller.setPreferredSize(new Dimension(600,560));
    getContentPane().add(scroller);
  }

  public void show(String fileName) {
    setTitle("Showing "+fileName);    
    StringBuffer sb = new StringBuffer("");
    try {
      FileReader br = new FileReader(new File(fileName));
      int i = br.read();
      while (i!=-1) {
        sb.append((char)i);
        i = br.read();
      }
      htmlPane.setText(sb.toString());
      br.close();
    } catch (Exception e) { e.printStackTrace(); }
    setVisible(true);
  }
}
