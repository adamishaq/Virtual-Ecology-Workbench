package VEW.Common.Utils;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import VEW.Common.DateDialog;
import javax.swing.filechooser.FileFilter;

public class CSVTool extends JFrame {
  
  private JLabel dateFromLabel = new JLabel("Date From:");
  private JLabel dateToLabel = new JLabel("Date To:");
  private JButton dateFrom = new JButton("");
  private JButton dateTo = new JButton("");
  private JCheckBox newColYear = new JCheckBox("New Column(s) each year");
  private JCheckBox newCSVYear = new JCheckBox("New CSV file each year");
  private JButton go = new JButton("Go");
  private EventHandler eh = null;
  private GregorianCalendar start = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar end = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar outputStart = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar outputEnd = new GregorianCalendar(DateDialog.GMTTimeZone);
  
  private long dataStart;
  private long dataEnd;
  private long dataStep;
  private DateDialog dd;
  private String fileName;
  
  public CSVTool(String file) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    fileName=file;
    dd = new DateDialog(this,1800);
    eh = new EventHandler();
    setSize(300,200);
    setTitle("CSV Splitter");
    getContentPane().setLayout(new BorderLayout());
    JPanel dates = new JPanel(new BorderLayout());
      JPanel dateFromPanel = new JPanel(new FlowLayout());
        dateFromPanel.add(dateFromLabel);
        dateFromPanel.add(dateFrom);
      JPanel dateToPanel = new JPanel(new FlowLayout());
        dateToPanel.add(dateToLabel);
        dateToPanel.add(dateTo);
          dates.add(dateFromPanel,BorderLayout.NORTH);
          dates.add(dateToPanel,BorderLayout.SOUTH);
    getContentPane().add(dates,BorderLayout.NORTH);
    
    JPanel optionsPanel = new JPanel(new BorderLayout());
      JPanel colYearPanel = new JPanel(new FlowLayout());
        colYearPanel.add(newColYear);
        optionsPanel.add(colYearPanel,BorderLayout.NORTH);
      JPanel colFilePanel = new JPanel(new FlowLayout());
        colFilePanel.add(newCSVYear);
        optionsPanel.add(colFilePanel,BorderLayout.SOUTH);
    getContentPane().add(optionsPanel,BorderLayout.CENTER);
    
    JPanel okPanel = new JPanel(new FlowLayout());
      okPanel.add(go);
    getContentPane().add(okPanel,BorderLayout.SOUTH);
    readFile(fileName);
    start.setTimeInMillis(dataStart);
    end.setTimeInMillis(dataEnd);
    dd.setEarliest(start);
    dd.setLatest(end);
    outputStart.setTimeInMillis(dataStart);
    outputEnd.setTimeInMillis(dataEnd);
    dateFrom.setText(DateDialog.getString(start));
    dateTo.setText(DateDialog.getString(end));
    go.addActionListener(eh);
    dateFrom.addActionListener(eh);
    dateTo.addActionListener(eh);
    pack();
    
  }
  
  public void doConversion() {
    int startYear = start.get(GregorianCalendar.YEAR);
    int endYear = end.get(GregorianCalendar.YEAR);
    GregorianCalendar gc = new GregorianCalendar(DateDialog.GMTTimeZone);
    GregorianCalendar startIgnoreYear = (GregorianCalendar) outputStart.clone();
    GregorianCalendar endIgnoreYear = (GregorianCalendar) outputEnd.clone();
    startIgnoreYear.set(GregorianCalendar.YEAR,1996);
    endIgnoreYear.set(GregorianCalendar.YEAR,1996);    
    String stub = fileName.substring(0,fileName.length()-4);
    if (newColYear.isSelected()) {
      String newFile = stub+"_cols.csv";
      try {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter PW = new PrintWriter(new FileOutputStream(new File(newFile)));
        String s = br.readLine();
        String[] cols = s.split(",");
        PW.write(cols[0]+","+cols[1]+",");
        for (int year=startYear; year<=endYear; year++) {
          for (int i=2; i<cols.length; i++) {
            PW.write("["+year+"] "+cols[i]+",");
          }
        }
        long startMillis = startIgnoreYear.getTimeInMillis();
        long endMillis = endIgnoreYear.getTimeInMillis();
        int t=0;
        for (long l=startMillis; l<endMillis; l+=dataStep) {
          PW.write(String.valueOf(l)+","+String.valueOf(t++)+",\n");
        }
        PW.flush();
        PW.close();
        
        for (int year=startYear; year<=endYear; year++) {
          BufferedReader brNew = new BufferedReader(new FileReader(newFile));
          PrintWriter PWtemp = new PrintWriter(new FileOutputStream(new File(newFile+".tmp")));
          PWtemp.write(brNew.readLine()+"\n");
          while (brNew.ready()) {
            String sNew = brNew.readLine();
            long timeNew = Long.parseLong(sNew.substring(0,sNew.indexOf(",")));
            s = br.readLine();
            if (s!=null) {
              long time = Long.parseLong(s.substring(0,s.indexOf(",")));
              gc.setTimeInMillis(time);
              gc.set(GregorianCalendar.YEAR,1996);
              time=gc.getTimeInMillis();
              while ((time!=timeNew) && (s!=null)) {
                s = br.readLine();
                if (s!=null) time = Long.parseLong(s.substring(0,s.indexOf(",")));
                gc.setTimeInMillis(time);
                gc.set(GregorianCalendar.YEAR,1996);
                time=gc.getTimeInMillis();
              }
            }
            if (s!=null) {
              s = s.substring(s.indexOf(",")+1);
              s = s.substring(s.indexOf(",")+1);
              PWtemp.write(sNew+s+",\n");
            } else PWtemp.write(sNew+"\n");
          }
          brNew.close();
          PWtemp.flush();
          PWtemp.close();
          new File(newFile).delete();
          new File(newFile+".tmp").renameTo(new File(newFile));
        }
        JOptionPane.showMessageDialog(this,"Ok!");
      } catch (Exception e) { e.printStackTrace(); }
      
    } else if (newCSVYear.isSelected()) {
      
      try {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String title = br.readLine();
        String data = br.readLine();
      
        for (int year=startYear; year<=endYear; year++) {
          PrintWriter PW = new PrintWriter(new FileOutputStream(new File(stub+"_"+String.valueOf(year)+".csv")));
          PW.write(title+"\n");
          gc.setTimeInMillis(Long.parseLong(data.substring(0,data.indexOf(","))));
          while (gc.get(GregorianCalendar.YEAR)==year) {
            gc.set(GregorianCalendar.YEAR,1996);
            if ((!gc.before(startIgnoreYear)) && (!gc.after(endIgnoreYear))) PW.write(data+"\n");
            data = br.readLine();
            if (data==null) {
              gc.set(GregorianCalendar.YEAR,99999);
              year=endYear+1;
            } else gc.setTimeInMillis(Long.parseLong(data.substring(0,data.indexOf(","))));
            
          }
          PW.flush();
          PW.close();
        }
        JOptionPane.showMessageDialog(this,"Ok!");
      } catch (Exception e) { e.printStackTrace();}
    }
      
  
  }
  
  public void readFile(String file) {
    String s;
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      br.readLine();
      s = br.readLine();
      dataStart = Long.parseLong(s.substring(0,s.indexOf(",")));
      s = br.readLine();
      dataStep = Long.parseLong(s.substring(0,s.indexOf(",")))-dataStart;
      while (br.ready()) {
        s = br.readLine();
      }
      dataEnd = Long.parseLong(s.substring(0,s.indexOf(",")));
      br.close();
      System.out.println(dataStart+", "+dataEnd+" step "+dataStep);
    } catch (Exception e) { e.printStackTrace(); }
  }
  
  class EventHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==go) {
        doConversion();
      } else if (e.getSource()==dateFrom) {
        dd.show(start,outputEnd,outputStart);
        outputStart.setTimeInMillis(dd.getDate().getTimeInMillis());
        dateFrom.setText(DateDialog.getString(outputStart));
      } else if (e.getSource()==dateTo) {
        dd.show(outputStart,end,outputEnd);
        outputEnd.setTimeInMillis(dd.getDate().getTimeInMillis());
        dateTo.setText(DateDialog.getString(outputEnd));
      }
    }
  }
   
  public static void main(String[] args) {
    String path="";
    if (args.length==0) {
      System.out.println("For command-line usage: java VEW.Common.Utils.CSVTool file.csv");
      JFileChooser jfc = new JFileChooser();
      jfc.setFileFilter(new CSVFilter());
      jfc.showOpenDialog(null);
      path = jfc.getSelectedFile().getAbsolutePath();
      if ((path==null) || (!new File(path).exists())) path="";
      
    } else path = args[0];
    
    if (path.length()>0) {
      CSVTool csv = new CSVTool(path);
      csv.setVisible(true);
    }
    
  }

}

class CSVFilter extends FileFilter {
  public String getDescription() { return "CSV files"; }
  public boolean accept(File f) { return (f.isDirectory()) || (f.getName().toUpperCase().endsWith(".CSV")); }

}