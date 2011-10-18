package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.sun.tools.javac.Main;

public class UpdateVEW extends JDialog {
  private JButton updateButton = new JButton("Update");
  private JButton cancelButton = new JButton("Close");
  private JLabel infoLabel = new JLabel("");
  public static final String versionNo = new String("3.4.766");
  private static String newVersion = "";
  private static EventHandler eh;
  private static boolean updated = false;
  
  public void deleteFile(String s) {
    try {
      File f = new File(s);
      if (f.exists()) f.delete();
    } catch (Exception e) {}
  }
  
  public boolean needsUpdating() {
    if (updated) return false;
    else return (!checkLatest().equals(versionNo));
  }
  
  public String checkLatest() {
    InputStream is = null;
    String s = versionNo;
    try {
      URL u = new URL("http://www.vew.org.uk/vewupdate/latest.txt");
      is = u.openStream();
      BufferedReader br= new BufferedReader(new InputStreamReader(is));
      s = br.readLine().trim();
      is.close();
      return s;
    } catch (MalformedURLException mue) {
      return s;
    } catch (IOException ioe) {
      return s;
    } finally {
      try {
        if (is!=null) is.close();
        return s;
      } catch (IOException ioe) {}
    }
  }
  
  public UpdateVEW(TitlePage _tp) {
    super(_tp, "Checking for updates...",true);
    eh = new EventHandler();
    setSize(new Dimension(400,80));
    getContentPane().setLayout(new BorderLayout());
    JPanel buttons = new JPanel(new FlowLayout());
    buttons.add(updateButton);
    buttons.add(cancelButton);
    getContentPane().add(buttons,BorderLayout.SOUTH);
    infoLabel.setPreferredSize(new Dimension(400,80));
    infoLabel.setText("<html><font size='+2'><strong>Checking...</strong></font></html>");
    infoLabel.setHorizontalTextPosition(JLabel.CENTER);
    infoLabel.setHorizontalAlignment(JLabel.CENTER);
    getContentPane().add(infoLabel,BorderLayout.CENTER);
    updateButton.addActionListener(eh);
    cancelButton.addActionListener(eh);
    updateButton.setEnabled(false);
    pack();
  }
  
  public void prepareDialog() {
    newVersion = checkLatest();
    if (newVersion.equals(versionNo)) {
      updateButton.setEnabled(false);
      infoLabel.setText("<html><center><font size='+1'><strong>You are running the latest version ("+versionNo+")</strong></font></center></html>");
    } else {
      infoLabel.setText("<html><center><font size='+1'><strong>You can update from <font color='#aa0000'>"+versionNo+"</font> to <font color='#00aa00'>"+newVersion+"</font>, by clicking Update.</strong></font></center></html>");
      updateButton.setEnabled(true);
    }
  }
  
  public void downloadVEW() {
    try {
      cancelButton.setEnabled(false);
      updateButton.setEnabled(false);
      infoLabel.setText("<html><center><font size='+1'><strong>Downloading VEW "+newVersion+"</strong></font></center></html>");
      UpdateVEW.this.paint(UpdateVEW.this.getGraphics());
      URL u = new URL ("http://www.vew.org.uk/vewupdate/latest.zip");
      URLConnection ucon = u.openConnection();
      int size = ucon.getContentLength();
      BufferedInputStream bis = new BufferedInputStream(ucon.getInputStream());
      String outFilename = "update.zip";
      FileOutputStream out = new FileOutputStream(outFilename);
      BufferedOutputStream bo = new BufferedOutputStream(out);
      int len;
      int bytes=0;
      int p;
      while ((len = bis.read()) != -1) {
        bo.write(len);
        bytes++;
        p = (bytes*100 / size);
        if (bytes%10240==0) {
          infoLabel.setText("<html><center><font size='+1'><strong>Downloading VEW "+newVersion+"... </strong>("+p+"%)</font></center></font></html>");
          UpdateVEW.this.paint(UpdateVEW.this.getGraphics());
        }
      }
      bis.close(); 
      bo.close();
      deleteFile("VEW/Controller2/JobControl.java");
      deleteFile("VEW/Controller2/JobControl.class");
      deleteFile("VEW/Compiler2/ConsumeRecordMaker.java");
      deleteFile("VEW/Compiler2/ConsumeRecordMaker.class");
      deleteFile("VEW/Compiler2/IngestRecordMaker.java");
      deleteFile("VEW/Compiler2/IngestRecordMaker.class");
      deleteFile("VEW/Compiler2/DiffFuncMaker.java");
      deleteFile("VEW/Compiler2/DiffFuncMaker.class");
      deleteFile("VEW/Compiler2/UpdateFunctionMaker.java");
      deleteFile("VEW/Compiler2/UpdateFunctionMaker.class");
      deleteFile("VEW/Compiler2/VariableMaker.java");
      deleteFile("VEW/Compiler2/VariableMaker.class");
      deleteFile("VEW/Compiler2/LayerMaker.java");
      deleteFile("VEW/Compiler2/LayerMaker.class");

      ArrayList javaFiles = new ArrayList();
      ZipFile zf = new ZipFile("update.zip");
      Enumeration zipEnum = zf.entries();
      size = zf.size();
      int fileNo=0; 
      String dir = new String(".");
      if (dir.charAt(dir.length()-1) != '/') dir += "/";
      while (zipEnum.hasMoreElements()) {
        ZipEntry item = (ZipEntry) zipEnum.nextElement();
        if (item.isDirectory()) {
          File newdir = new File( dir + item.getName() );
          newdir.mkdir();
        } else {
          String newfile = dir + item.getName();
          if (newfile.toUpperCase().endsWith(".JAVA")) javaFiles.add(newfile);
          InputStream is = zf.getInputStream(item);
          FileOutputStream fos = new FileOutputStream(newfile);
          int ch;
          while ((ch = is.read()) != -1 ) {
            fos.write(ch);
          }
          is.close();
          fos.close();
        }
        fileNo++;
        p = (fileNo*100)/size;
        infoLabel.setText("<html><center><font size='+1'><strong>Unpacking VEW "+newVersion+"... </strong>("+p+"%)</font></center></font></html>");
        UpdateVEW.this.paint(UpdateVEW.this.getGraphics());
      }
      zf.close();   
      infoLabel.setText("<html><center><font size='+1'><strong>Compiling VEW "+newVersion+"</strong></font></center></html>");
      UpdateVEW.this.paint(UpdateVEW.this.getGraphics());
      
      String[] args = new String[javaFiles.size()+2];
      args[0] = "-classpath";
      final String ps = File.pathSeparator;
      args[1] = "."+ps+"ganymed-ssh2.jar"+ps+"netcdf.jar"+ps+"trove.jar"+ps+"jgrib.jar"+ps+
                "freehep-graphics2d-2.0.jar"+ps+"freehep-io-2.0.1.jar"+ps+"freehep-util-2.0.1.jar"+
             ps+"freehep-graphicsio-2.0.jar"+ps+"freehep-graphicsio-ps-2.0.jar"+ps+"dom4j-1.6.1.jar"+ps+
             "jaxen-1.1-beta-9.jar";
      if (new File("tools.jar").exists()) args[1]+=ps+"tools.jar";
      for (int i=0; i<javaFiles.size(); i++) args[i+2]=new String(javaFiles.get(i).toString());
      Main.compile(args);
      infoLabel.setText("<html><center><font size='+1'><strong>Upgrade Complete! Please restart the VEW</strong></font></center></html>");
      UpdateVEW.this.paint(UpdateVEW.this.getGraphics());
      cancelButton.setEnabled(true);
      updated=true;
    } catch (Exception e) { e.printStackTrace(); }
  }
  
  class EventHandler implements ActionListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==cancelButton) {
        UpdateVEW.this.setVisible(false);
      } else if (e.getSource()==updateButton) {
       downloadVEW();    
      }
    }
  }
}
