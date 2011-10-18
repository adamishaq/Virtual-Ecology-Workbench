package VEW.Analyser4;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

public class ExportDialog extends JDialog {

  private JComboBox formatCombo = null;
  private JTextField dimX,dimY = null;
  private JButton okButton, cancelButton = null;
  private static EventHandler eh;
  private int xDim; 
  private int yDim;
  private final byte CSV_FORMAT = 0;
  private final byte EPS_FORMAT = 1;
  private final byte JPG_FORMAT = 2;
  private final CSVFileFilter CSV_FILTER = new CSVFileFilter();
  private final JPGFileFilter JPG_FILTER = new JPGFileFilter();  
  private final EPSFileFilter EPS_FILTER = new EPSFileFilter(); 
  private String dataPath;
  private GraphPanel gp = null;
  private LineChooser2 lc = null;
  private BackgroundChooser bc = null;  
  private Analyser4 a4 = null;
  
  public void setPath(String dp) { dataPath=new String(dp); }
  public void setDimensions(int x, int y) {
    xDim = x;
    yDim = y;
    dimX.setText(String.valueOf(xDim));
    dimY.setText(String.valueOf(yDim));
  }
  
  public ExportDialog(JFrame parent, LineChooser2 _lc, GraphPanel _gp, BackgroundChooser _bc) {
    super(parent,"Export Data",true);
    a4 = (Analyser4) parent;
    if (eh==null) eh = new EventHandler();
    gp=_gp;
    lc=_lc;
    bc=_bc;
    getContentPane().setLayout(new BorderLayout(2,2));
    
    JPanel formatMenu = new JPanel(new FlowLayout());
    formatMenu.add(new JLabel("Export Format:"));
    formatCombo = new JComboBox();
    formatCombo.addItem("CSV (Comma-separated for Excel)");
    formatCombo.addItem("EPS (Encapsulated Postscript)");
    formatCombo.addItem("JPG (JPEG Image)");
    formatCombo.addActionListener(eh);
    formatMenu.add(formatCombo);
    getContentPane().add(formatMenu,BorderLayout.NORTH);
    
    JPanel dimensionMenu = new JPanel(new FlowLayout());
    dimensionMenu.add(new JLabel("Export Dimensions"));
    dimX = new JTextField("0");
    dimensionMenu.add(dimX);
    dimensionMenu.add(new JLabel("x"));
    dimY = new JTextField("0");
    dimensionMenu.add(dimY);
    getContentPane().add(dimensionMenu,BorderLayout.CENTER);
    
    JPanel okCancel = new JPanel(new FlowLayout());
    okButton = new JButton("Ok");
    cancelButton = new JButton("Cancel");
    okCancel.add(cancelButton);
    okCancel.add(okButton);
    getContentPane().add(okCancel,BorderLayout.SOUTH);
    cancelButton.addActionListener(eh);
    okButton.addActionListener(eh);
    dimX.addActionListener(eh);
    dimY.addActionListener(eh);    
    pack();
  }
  
  class EventHandler implements ActionListener {
    
    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==okButton) {
        try { xDim = Integer.parseInt(dimX.getText());
        } catch (Exception ex) { xDim = 0; }
        if (xDim>65500) xDim=65500;
        try { yDim = Integer.parseInt(dimY.getText());
        } catch (Exception ex) {yDim = 0; }
        if (yDim>65500) yDim=65500;
        
          
        JFileChooser jfc = new JFileChooser(dataPath);
        
        int type = formatCombo.getSelectedIndex();
        if (type==CSV_FORMAT) jfc.setFileFilter(CSV_FILTER);
        else if (type==JPG_FORMAT) jfc.setFileFilter(JPG_FILTER);
        else if (type==EPS_FORMAT) jfc.setFileFilter(EPS_FILTER);
        if (jfc.showSaveDialog(ExportDialog.this)==JFileChooser.APPROVE_OPTION) {
          if (type==EPS_FORMAT) {
            String s = jfc.getSelectedFile().getAbsolutePath();
            if (!s.toUpperCase().endsWith(".EPS")) s+=".eps";
            gp.plotEPS(lc,s,xDim,yDim);
          
          } else if (type==JPG_FORMAT) {
            String s = jfc.getSelectedFile().getAbsolutePath();
            if (!s.toUpperCase().endsWith(".JPG")) s+=".jpg";
            long startPlotMillis = a4.startOfPlot.getTimeInMillis();
            long endPlotMillis = a4.endOfPlot.getTimeInMillis();
            long stepLengthSeconds = 0;
            if (!a4.modelFile.valueOf("/model/track/secstep").equals(""))
              stepLengthSeconds = Long.parseLong(a4.modelFile.valueOf("/model/track/secstep"));
            else if (!a4.modelFile.valueOf("/model/scenario/column/steplength").equals(""))
              stepLengthSeconds = Long.parseLong(a4.modelFile.valueOf("/model/scenario/column/steplength"));
            else stepLengthSeconds = 1800;
            gp.plotJPG(dataPath,lc,s,xDim,yDim, startPlotMillis, endPlotMillis,stepLengthSeconds,ExportDialog.this);
          
          } else if (type==CSV_FORMAT) {
            String s = jfc.getSelectedFile().getAbsolutePath();
            if (!s.toUpperCase().endsWith(".CSV")) s+=".csv";
            long spacing = Long.parseLong(a4.numFreq.getText());
            if (a4.numType.getSelectedIndex()==a4.TIMESTEP) spacing*=a4.timeStepMillis;
            else if (a4.numType.getSelectedIndex()==a4.HOUR) spacing*=(a4.timeStepMillis*3600000);
            else if (a4.numType.getSelectedIndex()==a4.DAY) spacing*=(a4.timeStepMillis*3600000*24);
            else if (a4.numType.getSelectedIndex()==a4.WEEK) spacing*=(a4.timeStepMillis*3600000*24*7);            
             
            try {
              final int countDefs = lc.getLineCount();
              PrintWriter PW = new PrintWriter(new FileOutputStream(new File(s)));
              if (countDefs>0) {
                PW.print("Time,Step,");
                for (int i=0; i<countDefs; i++) {
                  LineDefinition ld = lc.getLineDef(i);
                  if (ld.type==LineDefinition.XY_PLOT) PW.print(ld.name+" X,"+ld.name+" Y");
                  else PW.print(ld.name);
                  if (i<countDefs-1) PW.print(",");
                }
                PW.println("");
                LineDefinition firstDef = lc.getLineDef(0);
                for (int i=0; i<firstDef.dataY.length; i++) {
                  PW.print(String.valueOf(a4.startSimMillis+(i*spacing))+","+String.valueOf(i)+",");
                  for (int j=0; j<countDefs; j++) {
                    LineDefinition ld = lc.getLineDef(j);
                    if (ld.type==LineDefinition.XY_PLOT) {
                      if (ld.dataX.length>i) 
                        PW.print(String.valueOf(ld.dataX[i])+","+String.valueOf(ld.dataY[i]));
                      else PW.print("-Inf,-Inf");
                    } else {
                      if (ld.dataY.length>i) 
                        PW.print(String.valueOf(ld.dataY[i]));
                      else PW.print("-Inf");
                    }
                    
                    if (j<countDefs-1) PW.print(",");
                  }
                  PW.println("");
                }
              }
              
              if (bc.getFieldName()!=null) {
                PW.println("Background: "+bc.getFieldName());
                long startPlotMillis = a4.startOfPlot.getTimeInMillis();
                long startMillis = a4.startOfSim.getTimeInMillis();
                long endPlotMillis = a4.endOfPlot.getTimeInMillis();
                long stepLengthSeconds = 0;
                if (!a4.modelFile.valueOf("/model/track/secstep").equals(""))
                  stepLengthSeconds = Long.parseLong(a4.modelFile.valueOf("/model/track/secstep"));
                else if (!a4.modelFile.valueOf("/model/scenario/column/steplength").equals(""))
                  stepLengthSeconds = Long.parseLong(a4.modelFile.valueOf("/model/scenario/column/steplength"));
                else stepLengthSeconds = 1800;
              
                int timeStepStart = (int) ((startPlotMillis - startMillis) / (1000 * stepLengthSeconds));
                int timeStepEnd = (int) ((endPlotMillis - startMillis) / (1000 * stepLengthSeconds));

                a4.thePlotter.plotOnlyBackground(dataPath, gp, bc, timeStepStart, timeStepEnd, stepLengthSeconds, 
                      a4.formatFile, ExportDialog.this, PW);

                bc.setData(true);
              }

              PW.flush();
              PW.close();
            } catch (Exception ex) { ex.printStackTrace(); }
          }
        }
        setVisible(false);
      } else if (e.getSource()==cancelButton) {
        setVisible(false);
      } else if (e.getSource()==formatCombo) {
        if (formatCombo.getSelectedIndex()==CSV_FORMAT) { 
          dimX.setEnabled(false);
          dimY.setEnabled(false);
        } else {
          dimX.setEnabled(true);
          dimY.setEnabled(true);
        }
        
      } else if ((e.getSource()==dimX) || (e.getSource()==dimY)) {
        try { xDim = Integer.parseInt(dimX.getText());
        } catch (Exception ex) { xDim = 0; }
        if (xDim>65500) xDim=65500;
        try { yDim = Integer.parseInt(dimY.getText());
        } catch (Exception ex) { yDim = 0; }
        if (yDim>65500) yDim=65500;
        dimX.setText(String.valueOf(xDim));
        dimY.setText(String.valueOf(yDim));
      }
    }
  }
  
  class CSVFileFilter extends FileFilter {
    public boolean accept(java.io.File arg0) { 
      return ((arg0.getName().toUpperCase().endsWith(".CSV")) ||
              (arg0.isDirectory())); 
    }
    public String getDescription() { return new String("CSV (Comma-Separated Values)"); }
  }
  
  class JPGFileFilter extends FileFilter {
    public boolean accept(java.io.File arg0) { 
      return ((arg0.getName().toUpperCase().endsWith(".JPG"))
          || (arg0.isDirectory())); }
    public String getDescription() { return new String("JPG Image"); }
  }
  
  class EPSFileFilter extends FileFilter {
    public boolean accept(java.io.File arg0) { 
      return ((arg0.getName().toUpperCase().endsWith(".EPS")) ||
              (arg0.isDirectory())); }
    public String getDescription() { return new String("EPS (Encapsulated Postscript)"); }
  }
    
}


    

  