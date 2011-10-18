package VEW.Analyser4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dom4j.Document;

import VEW.Common.DateDialog;

//import VEW.Common.XML.XMLFile;

public class LineChooser2 extends JDialog {
  private DefaultListModel linesListModel = new DefaultListModel(); 
  private JList lines = new JList(linesListModel);
  private ArrayList lineDetails = new ArrayList();
  private JButton add = new JButton("Add");
  private JButton remove = new JButton("Remove");
  private JButton edit = new JButton("Edit");
  private EventHandler eh = new EventHandler();
  private JButton cancel = new JButton("Cancel");
  private JButton ok = new JButton("Ok");
  private Document format;
  private LineDialog ld;
  private GraphPanel theGraphPanel = null;
  private GregorianCalendar theStart = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar theEnd = new GregorianCalendar(DateDialog.GMTTimeZone);
  private Analyser4 a4;

  public LineDefinition getLineDef(int index) {
    return (LineDefinition) lineDetails.get(index);
  }
  
  public void addLineDef(LineDefinition _ld) {
    linesListModel.addElement(_ld.name);
    _ld.internalID=lineDetails.size();
    lineDetails.add(_ld);
    
  }
  
  public int getLineCount() { return lineDetails.size(); }
  
  public void removeAllLines() { 
    lineDetails.clear();
    linesListModel.removeAllElements();
  }
  
  public LineChooser2(JFrame parent, Document formatFile, Document modelFile, GraphPanel gp, GregorianCalendar start, GregorianCalendar end, float stepInHours) {
    super(parent,"Choose Lines",true);
    a4 = (Analyser4) parent;
    format = formatFile;
    ld = new LineDialog(format, modelFile, this, a4, gp, start, end, stepInHours); 
    theGraphPanel = gp;
    theStart.setTimeInMillis(start.getTimeInMillis());
    theEnd.setTimeInMillis(end.getTimeInMillis());
    getContentPane().setLayout(new BorderLayout());
    JPanel tablePanel = new JPanel(new BorderLayout());
    getContentPane().add(tablePanel,BorderLayout.CENTER);
    JScrollPane tableScroller = new JScrollPane(lines);
    tableScroller.setPreferredSize(new Dimension(250,300));
    tablePanel.add(tableScroller,BorderLayout.CENTER);
    
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(cancel);
    buttonPanel.add(ok);
    getContentPane().add(buttonPanel,BorderLayout.SOUTH);
    JPanel rightButtons = new JPanel(new BorderLayout());
    JPanel gridOfButtons = new JPanel(new BorderLayout());
    add.setPreferredSize(new Dimension(100,25));
    edit.setPreferredSize(new Dimension(100,25));    
    remove.setPreferredSize(new Dimension(100,25));
    remove.setEnabled(false);
    edit.setEnabled(false);
    gridOfButtons.add(add,BorderLayout.NORTH);
    gridOfButtons.add(edit,BorderLayout.CENTER);
    gridOfButtons.add(remove, BorderLayout.SOUTH);
    gridOfButtons.setPreferredSize(new Dimension(100,75));
    rightButtons.add(gridOfButtons,BorderLayout.CENTER);
    JPanel spacerTop = new JPanel();
    JPanel spacerBottom = new JPanel();
    spacerTop.setPreferredSize(new Dimension(100,100));
    spacerBottom.setPreferredSize(new Dimension(100,125));
    rightButtons.add(spacerTop,BorderLayout.NORTH);
    rightButtons.add(spacerBottom,BorderLayout.SOUTH);    
    getContentPane().add(rightButtons,BorderLayout.EAST);
          
    add.addActionListener(eh);
    remove.addActionListener(eh);
    edit.addActionListener(eh); 
    ok.addActionListener(eh);
    cancel.addActionListener(eh);
    lines.addListSelectionListener(eh);
    
    pack();
    
    }
  
  class EventHandler implements ActionListener, ListSelectionListener {
    
    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==add) {
        ld.setColour(BackgroundColour.randomColour());
        ld.prepareForAdd();
        ld.showDialog();
        LineDefinition linedef = ld.getLineDef();
        if (linedef!=null) {
         if ((linedef.depthIndices!=null) && (linedef.depthIndices.length>0)) {
            if (linedef.operator==LineDefinition.OP_IND) {
              for (int i=0; i<linedef.depthIndices.length; i++) {
                LineDefinition l2 = linedef.getCopy();
                l2.copyFrom(linedef);
                l2.operator=LineDefinition.OP_ADD;
                l2.thisDepthIndex = linedef.depthIndices[i];
                l2.depthIndices = new int[1];
                l2.depthIndices[0]=l2.thisDepthIndex;
                l2.colour = linedef.colours[i];
                l2.internalID=lineDetails.size();
                lineDetails.add(l2);
                l2.name = l2.name + " ["+l2.thisDepthIndex+"]";
                linesListModel.addElement(l2.name);
              }
            } else {
              LineDefinition l2 = linedef.getCopy();
              l2.copyFrom(linedef);
              l2.internalID=lineDetails.size();              
              lineDetails.add(l2);
              linesListModel.addElement(l2.name);
            }
          } else if ((linedef.ids!=null) && (linedef.ids.length>0)) {
            for (int i=0; i<linedef.ids.length; i++) {
              for (int j=0; j<linedef.compoundVars1.length; j++) {
                LineDefinition l2 = linedef.getCopy();
                l2.copyFrom(linedef);
                l2.thisId=l2.ids[i];
                l2.colour = BackgroundColour.nextRandomColour();
                l2.internalID=lineDetails.size();              
                lineDetails.add(l2);
                if (linedef.compoundVars1.length>1) l2.name = linedef.names[j]+" ["+l2.ids[i]+"]";
                else l2.name = linedef.name+" ["+l2.ids[i]+"]";
                l2.compoundVars1 = new int[1];
                l2.compoundVars1[0]=linedef.compoundVars1[j];
                linesListModel.addElement(l2.name);
              }
            }
          } else if ((linedef.type==LineDefinition.COMPOUND_PLOT) && (linedef.operator==LineDefinition.OP_IND)) {
            for (int i=0; i<linedef.compoundVars1.length; i++) {
              LineDefinition l2 = linedef.getCopy();
              l2.operator=LineDefinition.OP_ADD;
              l2.compoundVars1 = new int[1];
              l2.compoundVars1[0] = linedef.compoundVars1[i];
              l2.colour = linedef.colours[i];
              l2.internalID=lineDetails.size();
              lineDetails.add(l2);
              l2.name = linedef.names[i];
              linesListModel.addElement(l2.name);
            }
            
          } else {
            linedef.internalID=lineDetails.size();
            lineDetails.add(linedef);
            linesListModel.addElement(linedef.name);
          }
        }
      } else if (e.getSource()==remove) {
        int[] selectedIndices = lines.getSelectedIndices();
        for (int i=selectedIndices.length-1; i>=0; i--) {
          lineDetails.remove(selectedIndices[i]);
          linesListModel.remove(selectedIndices[i]);
        }
        for (int i=0; i<lineDetails.size(); i++)
          ((LineDefinition) lineDetails.get(i)).internalID=i;
        lines.clearSelection();        
        remove.setEnabled(false);
        edit.setEnabled(false);
      } else if (e.getSource()==edit) {
        int[] selectedIndices = lines.getSelectedIndices();
        LineDefinition firstDef = (LineDefinition) lineDetails.get(selectedIndices[0]);
        double minY = firstDef.min;
        double maxY = firstDef.max;
        double minX = firstDef.minX;
        double maxX = firstDef.maxX;
        for (int i=1; i<selectedIndices.length; i++) {
          LineDefinition nextDef = (LineDefinition) lineDetails.get(selectedIndices[i]);
          if (nextDef.min<minY) minY=nextDef.min;
          if (nextDef.max>maxY) maxY=nextDef.max;
          if (nextDef.minX<minX) minX=nextDef.minX;
          if (nextDef.maxX>maxX) maxX=nextDef.maxX;
        }
        
        if (selectedIndices.length>1) ld.prepareForEditMulti(minY,maxY,minX,maxX);
        else ld.prepareForEditSingle();
        ld.setDefinition(firstDef);        
        //ld.showDialog();
        ld.setVisible(true);
        LineDefinition result = ld.getLineDef();
        if (result!=null) {
          if (selectedIndices.length>1) {
            for (int i=0; i<selectedIndices.length; i++) {
              LineDefinition oneDef = (LineDefinition) lineDetails.get(selectedIndices[i]);
              if (ld.didColourChange()) oneDef.colour = result.colour;
              if (ld.didMaxChange()) oneDef.max = result.max;
              if (ld.didMinChange()) oneDef.min = result.min;
              if (ld.didAutoChange()) oneDef.autoscale = result.autoscale;
              if (ld.didInvertChange()) oneDef.invertY = result.invertY;
              if (ld.didLogChange()) oneDef.log = result.log;
              if (ld.didAccChange()) oneDef.accumulate = result.accumulate;
              if (ld.didMaxXChange()) oneDef.maxX = result.maxX;
              if (ld.didMinXChange()) oneDef.minX = result.minX;
              if (ld.didAutoXChange()) oneDef.xauto = result.xauto;
              if (ld.didInvertXChange()) oneDef.invertX = result.invertX;
              if (ld.didLogXChange()) oneDef.logx = result.logx;
              if (ld.didAccXChange()) oneDef.accumulatex = result.accumulatex;

            }
          } else {
            lineDetails.set(selectedIndices[0],result);
            linesListModel.setElementAt(result.name,selectedIndices[0]);
          }
        }
        
      } else if (e.getSource()==ok) {
        theGraphPanel.replotGraph(LineChooser2.this,theStart,theEnd);
        setVisible(false);
      } else if (e.getSource()==cancel) {
        setVisible(false);
      }
    }

    public void valueChanged(ListSelectionEvent e) {
      if (e.getSource()==lines) {
        remove.setEnabled(lines.getSelectedIndices().length>0);
        edit.setEnabled(lines.getSelectedIndices().length>0);
      }
    }
  }

}
