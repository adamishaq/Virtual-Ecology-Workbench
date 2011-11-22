package VEW.Scenario2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import VEW.Common.JCheckBoxList;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.VEWController2;
import VEW.Planktonica2.EquationEditor;
import VEW.Planktonica2.EquationPanel;

public class ClosureEditor extends JPanel {
  private DefaultListModel tpListModel = new DefaultListModel();
  private DefaultListModel eqListModel = new DefaultListModel();
  private JList tpList = new JList(tpListModel);
  private JList eqType = new JList(eqListModel);
  private JButton editButton = new JButton("Edit");
  private JButton importButton = new JButton("Import");
  private EquationPanel eqPanel = new EquationPanel("");
  private EquationEditor ee = null;
  private EventHandler eh = new EventHandler();
  private XMLTag theModel;
  private DefaultListModel stageListModel = new DefaultListModel();
  private JCheckBoxList jcbList = new JCheckBoxList(stageListModel);  
  private VEWController2 vc2;
  private int lockEvents=0;
  
  
  public boolean greenLight(boolean fix) {
    boolean ok=true;
    StringBuffer errorString = new StringBuffer();
    if (theModel.getTag("closure")==null) {
      if (fix) {
        theModel.addTag(new XMLTag("closure",""));
        errorString.append("Closure initialised\n");
      } else ok=false;
    }
    if (ok) {
      XMLTag[] fgs = theModel.getTags("functionalgroup");
      for (int i=0; i<fgs.length; i++) {
        if ((fgs[i].getTag("predator")!=null) && (fgs[i].getValue("predator").equals("true"))) {
          XMLTag predator = theModel.getTag("closure").getTagWhere("predator","name",fgs[i].getValue("name"));
          if (predator==null) {
            if (fix) {
              XMLTag pred = new XMLTag("predator");
              pred.addTag(new XMLTag("name",fgs[i].getValue("name")));
              XMLTag func = new XMLTag("function");
              func.addTag(new XMLTag("name","TotalConcentration"));
              XMLTag eq = new XMLTag("equation");
              eq.addTag(new XMLTag("name","TotalConcentration"));
              eq.addTag(new XMLTag("eq","\\assign{\\var{N_t},\\val{\\sival{0,0},\\unit{0,0,0}}}"));
              func.addTag(eq);
              pred.addTag(func);
              XMLTag func2 = new XMLTag("function");
              func2.addTag(new XMLTag("name","Size"));
              XMLTag eq2 = new XMLTag("equation");
              eq2.addTag(new XMLTag("name","Size"));
              eq2.addTag(new XMLTag("eq","\\assign{\\var{S_t},\\val{\\sival{0,0},\\unit{0,0,0}}}"));
              func2.addTag(eq2);
              pred.addTag(func2);
              XMLTag func3 = new XMLTag("function");
              func3.addTag(new XMLTag("name","VerticalDistribution"));
              XMLTag eq3 = new XMLTag("equation");
              eq3.addTag(new XMLTag("name","VerticalDistribution"));
              eq3.addTag(new XMLTag("eq","\\assign{\\var{D_t},\\val{\\sival{0,0},\\unit{0,0,0}}}"));
              func3.addTag(eq3);
              pred.addTag(func3);
              theModel.getTag("closure").addTag(pred);
              errorString.append("Added default closure for "+fgs[i].getValue("name")+"\n");
            } else ok=false;
          }
        }
      }
    }
    if (ok) {
      XMLTag[] preds = theModel.getTag("closure").getTags("predator");
      for (int i=0; i<preds.length; i++) {
        String predName = preds[i].getValue("name");
        XMLTag fg = theModel.getTagWhere("functionalgroup","name",predName);
        if (fg==null) {
          if (fix) {
            preds[i].removeTagFromParent();
            errorString.append("Removed closure for "+predName+" - functional group not found\n");
          } else ok=false;
        } else {
          if ((fg.getTag("predator")==null) || (!fg.getValue("predator").equals("true"))) {
            preds[i].removeTagFromParent();
            errorString.append("Removed closure for "+predName+" - not marked as a top predator\n");
          }
        }
      }
    }
    if (fix) {
      if (errorString.length()>5) {
        MessageBox.showMessage(errorString.toString(),this);
        vc2.unsaved(true);
      }
      initialise();
    }
    return ok;
  }
  
  public ClosureEditor(JFrame vewController2, XMLTag _theModel) {
    theModel = _theModel;
    vc2 = (VEWController2) vewController2;
    ee = new EquationEditor(vc2);
    JPanel topPanel = new JPanel(new FlowLayout());
    JScrollPane jsp1 = new JScrollPane(tpList);
    JScrollPane jsp2 = new JScrollPane(eqType);
    JScrollPane jsp3 = new JScrollPane(jcbList);
    topPanel.add(jsp1);
    topPanel.add(jsp3);
    topPanel.add(jsp2);
    jsp1.setPreferredSize(new Dimension(200,150));
    jsp2.setPreferredSize(new Dimension(150,150));
    jsp3.setPreferredSize(new Dimension(150,150));    
    JPanel rightButtons = new JPanel(new BorderLayout());
    rightButtons.add(editButton,BorderLayout.NORTH);
    rightButtons.add(importButton,BorderLayout.SOUTH);
    topPanel.add(rightButtons);
    JScrollPane jsp4 = new JScrollPane(eqPanel);
    jsp4.setPreferredSize(new Dimension(700,200));
    
    JPanel eqScrollerAndStages = new JPanel(new BorderLayout());
    eqScrollerAndStages.add(jsp4,BorderLayout.NORTH);
    add(topPanel,BorderLayout.NORTH);
    add(eqScrollerAndStages,BorderLayout.SOUTH);
   
    importButton.setEnabled(false);
    editButton.setEnabled(false);
    editButton.addActionListener(eh);
    importButton.addActionListener(eh);
    eqType.addListSelectionListener(eh);
    tpList.addListSelectionListener(eh);
    jcbList.addListSelectionListener(eh);
    eqListModel.clear();
    eqListModel.addElement("Total Concentration");
    eqListModel.addElement("Vertical Distribution");    
    eqListModel.addElement("Size");  
    tpListModel.clear();

  }
  
  public void initialise() {
    while (tpListModel.getSize()>0) tpListModel.removeElementAt(0);
    XMLTag[] preds = theModel.getTag("closure").getTags("predator");
    for (int i=0; i<preds.length; i++) tpListModel.addElement(preds[i].getValue("name"));
  }
  
  public void setupStageTable() {
    if ((tpList!=null) && (tpList.getSelectedValue()!=null)) {
      String tpName = tpList.getSelectedValue().toString();
      XMLTag stages[] = theModel.getTagWhere("functionalgroup","name",tpName).getTags("stage");
      while (stageListModel.getSize()>0) stageListModel.removeElementAt(0);
      for (int i=0; i<stages.length; i++) {
        JCheckBox jcb = new JCheckBox(stages[i].getValue("name"));
        if (stages[i].getAttribute("closure")==null) stages[i].setAttribute("closure","true");
        jcb.setSelected(stages[i].getAttribute("closure").equals("true"));
        stageListModel.addElement(jcb);
      }
    }
  }
  
  
  public void updateEqPanel() {
    if ((tpList.getSelectedValue()==null) || (eqType.getSelectedValue()==null)) {
      eqPanel.setEquation("");
      eqPanel.repaint();
    } else {
      
      XMLTag closure = theModel.getTag("closure").getTagWhere("predator","name",tpList.getSelectedValue().toString());
      XMLTag theFunc = closure.getTagWhere("function","name",StringTools.nonSpace(eqType.getSelectedValue().toString()));
      XMLTag[] equations = theFunc.getTags("equation");
      String eqlist = "";
      for (int i=0; i<equations.length; i++)
        eqlist+=equations[i].getValue("eq")+"\\newline";
      eqPanel.setEquation(eqlist);
      eqPanel.repaint();
    }
  }
  
  class EventHandler implements ActionListener, ListSelectionListener {
    public EventHandler() {}
    public void actionPerformed(ActionEvent e) {
      
      if ((e.getSource()==editButton) && (lockEvents==0)) {
        lockEvents++;
        String predName = tpList.getSelectedValue().toString();
        XMLTag closureTag = theModel.getTag("closure").getTagWhere("predator","name",predName);
        if (closureTag==null) {
          closureTag = theModel.getTag("closure").addTag("predator");
          closureTag.addTag(new XMLTag("name",predName));
          XMLTag vdFunc = closureTag.addTag("function");
          vdFunc.addTag(new XMLTag("name","VerticalDistribution"));
          XMLTag vdFuncEq = vdFunc.addTag("equation");
          vdFuncEq.addTag(new XMLTag("eq","\\eq{\\assign{\\var{D_t},{}}}"));
          vdFuncEq.addTag(new XMLTag("name","VerticalDistribution"));
          XMLTag sFunc = closureTag.addTag("function");
          sFunc.addTag(new XMLTag("name","Size"));
          XMLTag sFuncEq = sFunc.addTag("equation");
          sFuncEq.addTag(new XMLTag("eq","\\eq{\\assign{\\var{S_t},{}}}"));
          sFuncEq.addTag(new XMLTag("name","Size"));
          XMLTag tcFunc = closureTag.addTag("function");
          tcFunc.addTag(new XMLTag("name","TotalConcentration"));
          XMLTag tcFuncEq = tcFunc.addTag("equation");
          tcFuncEq.addTag(new XMLTag("eq","\\eq{\\assign{\\var{N_t},{}}}"));
          tcFuncEq.addTag(new XMLTag("name","TotalConcentration"));
        }
        if (eqType.getSelectedValue().toString().equals("Total Concentration")) ee.show(closureTag.getTagWhere("function","name","TotalConcentration"),closureTag,theModel);
        else if (eqType.getSelectedValue().toString().equals("Vertical Distribution")) ee.show(closureTag.getTagWhere("function","name","VerticalDistribution"),closureTag,theModel); 
        else if (eqType.getSelectedValue().toString().equals("Size")) ee.show(closureTag.getTagWhere("function","name","Size"),closureTag,theModel);
        lockEvents--;
        vc2.unsaved(false);

      } else if ((e.getSource()==importButton) && (lockEvents==0)) {
        lockEvents++;
        vc2.unsaved(false);
        lockEvents--;
      
      }
    }
 
    public void valueChanged(ListSelectionEvent e) {
      if ((lockEvents==0) && ((((e.getSource()==tpList) || (e.getSource()==eqType))) && (!e.getValueIsAdjusting()))) {
        lockEvents++;
        editButton.setEnabled((tpList.getSelectedIndices().length==1) && (eqType.getSelectedIndices().length==1));
        importButton.setEnabled(false);
        updateEqPanel();
        lockEvents--;
      
      } else if ((e.getSource()==tpList) && (lockEvents==0)) {
        lockEvents++;
        if (e.getValueIsAdjusting()) setupStageTable();
        lockEvents--;
      
      } else if ((e.getSource()==jcbList) && (lockEvents==0)) {
        lockEvents++;
        for (int i=0; i<stageListModel.size(); i++) {
          JCheckBox jcb = (JCheckBox) stageListModel.getElementAt(i);
          theModel.getTagWhere("functionalgroup","name",tpList.getSelectedValue().toString()).getTagWhere("stage","name",jcb.getText()).setAttribute("closure",String.valueOf(jcb.isSelected()));
          theModel.write();          
        }
        vc2.unsaved(false);
        lockEvents--;
      }  
    }
  }
}
