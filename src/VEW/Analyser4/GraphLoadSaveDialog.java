package VEW.Analyser4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import VEW.Common.DateDialog;
import VEW.Common.StringTools;

public class GraphLoadSaveDialog extends JDialog {
  
  private DefaultListModel graphListModel = new DefaultListModel();
  private JList graphList = new JList(graphListModel);
  private JButton performButton = new JButton("Save");
  private JButton deleteButton = new JButton("Delete");
  private JTextField newName = new JTextField(10);
  private JButton cancelButton = new JButton("Cancel");
  private static final int MODE_SAVE = 2;
  private static final int MODE_LOAD = 1;    
  private int mode;
  private Document formatFile;
  private EventHandler eh = new EventHandler();
  private int lockEvents=0;
  private String dataPath;
  private String formatFilePath;
  private Analyser4 a4 = null;
    
  public void saveXMLFile(String theFilename, Document theFile) {
    try {
      OutputFormat format = OutputFormat.createPrettyPrint();
      XMLWriter writer = new XMLWriter(new FileWriter(theFilename), format);
      writer.write(theFile);
      writer.flush();
    } catch (IOException e1) { e1.printStackTrace(); }
  }
  
  public void populateTable() {
    Node savedGraphTag = formatFile.selectSingleNode("/dataformat/savedgraphs");
    List savedGraphs = savedGraphTag.selectNodes("graph");
    graphListModel.clear();      
    for (int i=0; i<savedGraphs.size(); i++) {
      Node node = (Node) savedGraphs.get(i);
      graphListModel.addElement(node.valueOf("name"));
    }
  }
  
  public void updateButtons() {
    lockEvents++;
    deleteButton.setEnabled(graphList.getSelectedIndices().length>=1);
    if (mode==MODE_LOAD) {
      performButton.setEnabled(graphList.getSelectedIndices().length==1);
    } else if (mode==MODE_SAVE) {
      if (graphList.getSelectedIndices().length==1) {
        performButton.setEnabled(true);
        performButton.setText("Overwrite Graph");
      
      } else {
        performButton.setText("Save Graph");
        String theName = newName.getText();
        if (theName.length()>=1) performButton.setEnabled(true);
        else performButton.setEnabled(false);
      }
    }
    lockEvents--;
  }
    
  public void showLoadDialog() {
    mode = MODE_LOAD;
    performButton.setText("Load Graph");
    newName.setEnabled(false);
    populateTable();
    setVisible(true);
  }
  
  public void showSaveDialog(String theName) {
    mode = MODE_SAVE;
    performButton.setText("Save Graph");
    newName.setText(theName);
    newName.setEnabled(true);
    populateTable();
    newName.selectAll();
    setVisible(true);
  }
   
  public GraphLoadSaveDialog(Analyser4 parent, String path, String ffp, Document ff) {
    super(parent,"Choose Graph",true);
    a4 = parent;
    dataPath = path;
    formatFilePath = ffp;
    formatFile = ff;
    
    setLayout(new BorderLayout());
    add(newName,BorderLayout.NORTH);
    JScrollPane graphScroller = new JScrollPane(graphList);
    graphScroller.setPreferredSize(new Dimension(300,120));
    add(graphScroller,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(cancelButton);
    buttonPanel.add(deleteButton);
    buttonPanel.add(performButton);
    deleteButton.setEnabled(false);
    performButton.setEnabled(false);
    cancelButton.setEnabled(true);
    add(buttonPanel,BorderLayout.SOUTH);
    pack();
    deleteButton.addActionListener(eh);
    performButton.addActionListener(eh);
    cancelButton.addActionListener(eh);
    graphList.addListSelectionListener(eh);
    newName.addCaretListener(eh);
  }
    
  public void saveGraph(String theName, boolean overwrite) {
    Element savedGraphTag = (Element) formatFile.selectSingleNode("/dataformat/savedgraphs");
    String graphFileName="";
    if (overwrite) {
      Node toOverwrite = savedGraphTag.selectSingleNode("graph[name='"+theName+"']");
      graphFileName = toOverwrite.valueOf("file");
      toOverwrite.detach();
    }

    if ((theName!=null) && (theName.length()>0)) { 
      Element newGraph = savedGraphTag.addElement("graph");
      newGraph.addElement("name").addText(theName);
      newGraph.addElement("starttime").addText(String.valueOf(a4.startOfPlot.getTimeInMillis()));
      newGraph.addElement("endtime").addText(String.valueOf(a4.endOfPlot.getTimeInMillis()));
      newGraph.addElement("sample").addText(a4.numFreq.getText());
      newGraph.addElement("sampleunit").addText(a4.numType.getSelectedItem().toString());
      
      int numGraphs = savedGraphTag.selectNodes("graph").size();
      if (graphFileName.equals("")) 
        graphFileName = new String("saved_"+String.valueOf(numGraphs))+".bin";
      newGraph.addElement("file").addText(graphFileName);
      newGraph.add(a4.bc.convertToTag());

      Element lines = newGraph.addElement("lines");
      for (int i=0; i<a4.lc2.getLineCount(); i++)
        lines.add(a4.lc2.getLineDef(i).convertToTag());

      Element axesTag = newGraph.addElement("axes");
      axesTag.add(a4.leftAxisDef.convertToTag());
      axesTag.add(a4.topAxisDef.convertToTag());
      axesTag.add(a4.rightAxisDef.convertToTag());
      axesTag.add(a4.bottomAxisDef.convertToTag());
      saveXMLFile(formatFilePath, formatFile);
      
      try {
        DataOutputStream graphData = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(dataPath+File.separator+graphFileName)))));
        graphData.writeInt(a4.theGraph.getGraphWidth()+(2*(a4.theGraph.getAxisWidth())));
        graphData.writeInt(a4.theGraph.getGraphHeight()+(2*(a4.theGraph.getAxisHeight())));
        BufferedImage bi = a4.theGraph.getTheImage();
        for (int i=0; i<a4.theGraph.getGraphWidth()+(2*(a4.theGraph.getAxisWidth())); i++) 
          for (int j=0; j<a4.theGraph.getGraphHeight()+(2*(a4.theGraph.getAxisHeight())); j++) 
            graphData.writeInt(bi.getRGB(i,j));
        graphData.writeInt(a4.lc2.getLineCount());
        for (int i=0; i<a4.lc2.getLineCount(); i++) {
          LineDefinition ld = a4.lc2.getLineDef(i);
          if (ld.dataX==null) graphData.writeInt(0); else {
            graphData.writeInt(ld.dataX.length);
            for (int j=0; j<ld.dataX.length; j++) 
              graphData.writeDouble(ld.dataX[j]);
          }
          if (ld.dataY==null) graphData.writeInt(0); else {
            graphData.writeInt(ld.dataY.length);
            for (int j=0; j<ld.dataY.length; j++) 
              graphData.writeDouble(ld.dataY[j]);
          }
        }
        graphData.writeInt(a4.theGraph.getGraphWidth());
        graphData.writeInt(a4.theGraph.getGraphHeight());
        for (int i=0; i<a4.theGraph.getGraphWidth(); i++) {
          for (int j=0; j<a4.theGraph.getGraphHeight(); j++) {
            graphData.writeDouble(a4.theGraph.screen[i][j]);
          }
        }
        graphData.flush();
        graphData.close();
      } catch (Exception ex) { ex.printStackTrace(); }
      JOptionPane.showMessageDialog(GraphLoadSaveDialog.this, new String("Saved successfully."));          
    }
  }
  
  public void loadGraph(String value) {
    if (value.length()>0) {
      Node savedGraphTag = formatFile.selectSingleNode("/dataformat/savedgraphs");
      Node theGraphTag = savedGraphTag.selectSingleNode("graph[name='" + value + "']");
      String graphFileName = theGraphTag.valueOf("file"); 
      List theLines = theGraphTag.selectNodes("lines/line");
      a4.lc2.removeAllLines();
      for (Iterator iter = theLines.iterator(); iter.hasNext();) {
        Node node = (Node) iter.next();
        a4.lc2.addLineDef(LineDefinition.createFromTag(node));
      }
       
      try {
        DataInputStream graphData = new DataInputStream(
            new BufferedInputStream(new GZIPInputStream(
                new BufferedInputStream(new FileInputStream(dataPath + File.separator + graphFileName)))));
        int pic_width = graphData.readInt();
        int pic_height = graphData.readInt();
        BufferedImage bi = a4.theGraph.getTheImage();
        for (int i = 0; i < pic_width; i++) {
          for (int j = 0; j < pic_height; j++) {
            bi.setRGB(i, j, graphData.readInt());
          }
        }
        int lineCount = graphData.readInt();
        for (int i = 0; i < lineCount; i++) {
          LineDefinition ld = a4.lc2.getLineDef(i);
          ld.dataX = new double[graphData.readInt()];
          for (int j = 0; j < ld.dataX.length; j++) ld.dataX[j] = graphData.readDouble();
          ld.dataY = new double[graphData.readInt()];
          for (int j = 0; j < ld.dataY.length; j++) ld.dataY[j] = graphData.readDouble();
        }
        a4.theGraph.getGraphics().drawImage(bi, 0, 0, new ImageObserver() {
          public boolean imageUpdate(Image img, int infoflags, int x,
              int y, int width, int height) {
            return true;
          }
        });
        int gwid = graphData.readInt();
        int ghei = graphData.readInt();
        if ((gwid != a4.theGraph.getGraphWidth())
            || (ghei != a4.theGraph.getGraphHeight())) {
          throw new Error("Non-matching graph sizes reloading graph");
        }
        for (int i = 0; i < a4.theGraph.getGraphWidth(); i++) {
          for (int j = 0; j < a4.theGraph.getGraphHeight(); j++) {
            a4.theGraph.screen[i][j] = graphData.readDouble();
          }
        }

        graphData.close();
      } catch (Exception ex) {
       ex.printStackTrace();
      }
      a4.startOfPlot.setTimeInMillis(Long.parseLong(theGraphTag.valueOf("starttime")));
      a4.fromDate.setText(DateDialog.getString(a4.startOfPlot));
      a4.endOfPlot.setTimeInMillis(Long.parseLong(theGraphTag.valueOf("endtime")));
      a4.toDate.setText(DateDialog.getString(a4.endOfPlot));
      a4.numFreq.setText(theGraphTag.valueOf("sample"));
      StringTools.setStringItem(a4.numType, theGraphTag.valueOf("sampleunit"));
      Node back = theGraphTag.selectSingleNode("background");
      if ((back != null) && (back.selectSingleNode("type") != null)) {
        a4.bc.init(formatFile);
        a4.bc.populateCombos();
        a4.bc.setFromTag(back);
      }
      a4.csc.repaint();
      List axes = theGraphTag.selectNodes("axes/axis");
      for (int i=0; i<axes.size(); i++) {
        if (i==3) a4.bottomAxisDef = AxisDefinition.createFromTag((Node) axes.get(3));
        else if (i==2) a4.rightAxisDef = AxisDefinition.createFromTag((Node) axes.get(2));
        else if (i==1) a4.topAxisDef = AxisDefinition.createFromTag((Node) axes.get(1));
        else if (i==0) a4.leftAxisDef = AxisDefinition.createFromTag((Node) axes.get(0));
      }
    }
  }
    
    
  class EventHandler implements ActionListener, ListSelectionListener, CaretListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==cancelButton) {
        setVisible(false);
      } else if (e.getSource()==performButton) {
        String theName = newName.getText();
        if (mode==MODE_LOAD) {
          loadGraph(theName);
          setVisible(false);
        
        } else if (mode==MODE_SAVE) {
          if (performButton.getText().startsWith("Overwrite")) {
            saveGraph(theName,true);
          } else if (performButton.getText().startsWith("Save")) {
            saveGraph(theName,false);
          }
        }
        setVisible(false);
        
      } else if (e.getSource()==deleteButton) {
        lockEvents++;
        Object[] values = graphList.getSelectedValues();
        String s = "";
        if (values.length==1) s = "this graph.";
        else s = "these graphs.";
        if (JOptionPane.showConfirmDialog(GraphLoadSaveDialog.this,"Please confirm you'd like to delete "+s)==JOptionPane.YES_OPTION) {
          for (int i=0; i<values.length; i++) {
            String name = values[i].toString();
            Node savedGraphTag = formatFile.selectSingleNode("/dataformat/savedgraphs");
            Node deleteTarget = savedGraphTag.selectSingleNode("graph[name='"+name+"']");
            String file = deleteTarget.valueOf("file");
            deleteTarget.detach();
            new File(dataPath+File.separator+file).delete();
          }
          saveXMLFile(formatFilePath, formatFile);
          populateTable();
        }
        lockEvents--;
      }
    }

    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==graphList) && (lockEvents==0)) {
        lockEvents++;
        if (graphList.getSelectedIndices().length==1) {
          newName.setText(graphList.getSelectedValue().toString());
        } else {
          newName.setText("");
        }
        updateButtons();
        lockEvents--;
      }
       
    }
  
    public void caretUpdate(CaretEvent e) {
      if ((e.getSource()==newName) && (lockEvents==0)) {
        lockEvents++;
        String theName = newName.getText();
        Node savedGraphTag = formatFile.selectSingleNode("/dataformat/savedgraphs");
        Node n = savedGraphTag.selectSingleNode("graph[name='"+theName+"']");
        if (n==null) graphList.clearSelection();
        updateButtons();
        lockEvents--;
      }
    }
  }
}



