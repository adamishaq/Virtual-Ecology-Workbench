package VEW.Scenario2;

//import javax.swing.event.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import VEW.Common.XML.XMLTag;
import VEW.Controller2.OutputDialog2;

public class VariableChooser extends JDialog {
  private JLabel titleLabel = new JLabel("");
  private JEditorPane detailsHTML = new JEditorPane();
  private Vector varTags = new Vector();
  private Vector varTitles = new Vector();
  private DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("SCOPE");
  private DefaultMutableTreeNode replaceNode = null;
  private AbstractButton buttonText = null;
  private JTree theTree = new JTree(new DefaultTreeModel(treeNode));
  private JButton exit = new JButton("Exit");
  private JButton choose = new JButton("Choose Item");
  private JButton addvar = new JButton("Add New Item");
  private JButton remvar = new JButton("Remove Item");
  private JButton editvar = new JButton("Edit Item");
  private int options;

  private XMLTag toReplace = null;
  private XMLTag theFunction = null;
  private XMLTag theModel = null;
  private AddVarPage addVarPage = null; 
  private XMLTag theGroup = null;

  public final static int VARIABLE = 1;
  public final static int PARAMETER = 2;
  public final static int LOCALVAR = 4;
  public final static int VARPARAM = 16;
  public final static int VARCONC = 32;
  public final static int VARVAR = 64;
  public final static int VARLOCAL = 128;
  public final static int COLVAR = 256;
  public final static int PHYVAR = 512;
  public final static int CHEMVAR = 1024;
  public final static int SYSVAR = 2048;
  public final static int ALL = 4095;
  
  public static String HTMLForVarHelper(String varName,XMLTag group, XMLTag theVar, boolean incName) {
    if (varName.equals("z")) {
      return HTMLForVar("z","Depth","Built-in state variable","m");
    } else if (varName.endsWith("$Conc")) {
      String chemName = varName.substring(0,varName.length()-5);
      return HTMLForVar(varName,chemName+" concentration in solution", "Built-in field Variable", chemName);
    } else if (varName.endsWith("$Pool")) {
      String chemName = varName.substring(0,varName.length()-5);
      return HTMLForVar(varName,chemName+" internal pool", "Built-in state variable", chemName);
    } else if (varName.endsWith("$Ingested")) {
      String chemName = varName.substring(0,varName.length()-9);
      return HTMLForVar(varName,chemName+" incoming pool", "Built-in state variable", chemName);
    } else if (varName.equals("d_year")) {
      return HTMLForVar(varName,"Days this year since 1st January", "System Variable", "days");
    } else if (varName.equals("d_leap")) {
      return HTMLForVar(varName,"Extra days due to leap year (1 or 0)", "System Variable", "days");
    } else if (varName.equals("TimeStep")) {
      return HTMLForVar(varName,"Time step size", "System Variable", "hours");
    } else if (varName.equals("Visible Irradiance")) {
      return HTMLForVar(varName,"Visible Irradiance", "System Variable", "Wm-2");
    } else if (varName.equals("Full Irradiance")) {
      return HTMLForVar(varName,"Full Irradiance", "Physics Variable", "Wm-2");
    } else if (varName.equals("Temperature")) {
      return HTMLForVar(varName,"Temperature", "Physics Variable", "C");
    } else if (varName.equals("Salinity")) {
      return HTMLForVar(varName,"Salinity", "Physics Variable", "");
    } else if (varName.equals("Density")) {
      return HTMLForVar(varName,"Density", "Physics Variable", "kgm-3");
    } else if (varName.equals("Turbocline")) {
      return HTMLForVar(varName,"Turbocline", "Water Column Variable", "m");
    } else if (varName.equals("MLDepth")) {
      return HTMLForVar(varName,"Turbocline", "Water Column Variable", "m");
    } else if (varName.equals("Max_MLD")) {
      return HTMLForVar(varName,"Daily Maximum Turbocline", "Water Column Variable", "m");
    } else if (varName.equals("PI")) {
      return HTMLForVar(varName,"PI","System Constant","");
    } else return HTMLForVar(group, theVar, incName);
  }

  
  public static String HTMLForVar(XMLTag group, XMLTag theVar, boolean incName) {
    String varTypeString = theVar.getName();
    String name = theVar.getValue("name");
    String desc = theVar.getValue("desc");
    String val = theVar.getValue("value");
    String hist = theVar.getValue("history");
    if (hist==null) hist="-";
    String unit = UnitEditor.HTMLFor(theVar.getValue("unit"));
    String type = ConvertTagToText(group,varTypeString);
    if (val==null) val = "Not specified";
    String names = "";
    if (incName) names = "<tr height=\"10\"><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Name</font></td><td bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+name+"</font></td></tr>";
    return "<html><body><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">"+names+
            "<tr height=\"10\"><td width=\"33%\" bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Description</font></td><td width=\"67%\" bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+desc+"</font></td></tr>"+          
            "<tr height=\"10\"><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Type</font></td><td bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+type+"</font></td></tr>"+
            "<tr height=\"10\"><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Value [History]</font></td><td bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+val+" ["+hist+"]</font></td></tr>"+          
            "<tr height=\"10\"><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Units</font></td><td bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+unit+"</font></td></tr></table></body></html>";          
  }  
  
  public static String HTMLForVar(String name, String desc, String type, String units) {
    return "<html><body><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tr height=\"10\"><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Name</font></td><td bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+name+"</font></td></tr>"+
            "<tr height=\"10\"><td width=\"33%\" bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Description</font></td><td width=\"67%\" bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+desc+"</font></td></tr>"+          
            "<tr height=\"10\"><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Type</font></td><td bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+type+"</font></td></tr>"+
            "<tr height=\"10\"><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">Units</font></td><td bgcolor=\"ffffff\" width=\"2\"></td><td bgcolor=\"#f0f0f0\"><font size=\"-2\" face=\"Arial\">"+units+"</font></td></tr></table></body></html>";          
  }

  private void addToTree(XMLTag root, String type, DefaultMutableTreeNode d) {
    int i=1;
    XMLTag var = root.getTag(type,i);
    while (var!=null) {
      String s = var.getTag("name").getValue();
      if ((!isInTree(d,s)) && (!((type.equals("varietyconcentration") && (s.equals("Ingestion"))))))
        addInOrder(s,d);
      var = root.getTag(type,++i);
    }
   }

  private void addInOrder(String name, DefaultMutableTreeNode d) {
    int i=0;
    boolean done=false;
    while ((!done) && (i<d.getChildCount())) {
      String s = d.getChildAt(i).toString();
      if (s.toUpperCase().compareTo(name.toUpperCase())>0) {
        done = true;
        d.insert(new DefaultMutableTreeNode(name),i);
      }
      i++;
    }
    if (!done) d.add(new DefaultMutableTreeNode(name));
    varTitles.add(d.getUserObject());
    varTags.add(ConvertTextToTag((String)d.getUserObject()));
  }

  public static String ConvertTagToText(XMLTag theGroup, String c) {
    if (c.equals("sysvar")) return "System Variables";
    else if (c.equals("colvar")) return "Water Column Properties";
    else if (c.equals("phyvar")) return "Physics Properties";
    else if (c.equals("biovar")) return "Biology";
    else if (c.equals("chemvar")) return "Chemistry";
    else if (c.equals("fgvar")) return "All Functional Groups";
    else if (c.endsWith("variable")) return theGroup.getValue("name")+" State Variables";
    else if (c.equals("parameter")) return "Constant Parameter";
    else if (c.equals("varietylocal")) return "Food-based Local Variable";
    else if (c.equals("varietyparameter")) return "Food-based Parameter";
    else if (c.equals("varietyvariable")) return theGroup.getValue("name")+" Food-based State Variables";
    else if (c.equals("varietyconcentration")) return "Food Set";
    else if (c.equals("local")) return "Local Variable";
    else return "";
  }

  public static String ConvertTextToTag(String c) {
    if (c.equals("System Variables")) return "sysvar";
    else if (c.equals("Water Column Properties")) return "colvar";
    else if (c.equals("Physics Properties")) return "phyvar";
    else if (c.equals("Biology")) return "biovar";
    else if (c.equals("Chemistry")) return "chemvar";
    else if (c.equals("All Functional Groups")) return "fgvar";
    else if (c.endsWith("State Variables")) return "variable";
    else if (c.endsWith("Food-based State Variables")) return "varietyvariable";
    else if (c.equals("Food-based Local Variable")) return "varietylocal";
    else if (c.equals("Food Set")) return "varietyconcentration";
    else if (c.equals("Constant Parameter")) return "parameter";
    else if (c.equals("Food-based Parameter")) return "varietyparameter";
    else if (c.equals("Local Variable")) return "local";
    else return "";
  }
  
  private boolean isInTree(DefaultMutableTreeNode d, String s) {
    boolean b = false;
    int i=0;
    while ((i<d.getChildCount()) && (!b)) {
      TreeNode c = d.getChildAt(i++);
      b = (c.toString().equals(s));
    }
    return b;
  }
   
  public void initialiseVarChooser() {
    varTags.removeAllElements();
    varTitles.removeAllElements();
    treeNode = new DefaultMutableTreeNode("SCOPE");
    ((DefaultTreeModel)theTree.getModel()).setRoot(treeNode);
   
    if ((options & COLVAR)>0)  {
      DefaultMutableTreeNode wcolTree = new DefaultMutableTreeNode("Water Column Properties");
      addInOrder(OutputDialog2.WC_TURBOCLINE,wcolTree);
      treeNode.add(wcolTree);
    }

    if ((options & PHYVAR)>0) {
      DefaultMutableTreeNode phyTree = new DefaultMutableTreeNode("Physics Properties");
      addInOrder(OutputDialog2.DENSITY,phyTree);
      addInOrder(OutputDialog2.FULL_IRRADIANCE,phyTree);
      addInOrder(OutputDialog2.SALINITY,phyTree);
      addInOrder(OutputDialog2.TEMPERATURE,phyTree);
      addInOrder(OutputDialog2.VISIBLE_IRRADIANCE,phyTree);
      treeNode.add(phyTree);  
    }

   if ((options & CHEMVAR)>0) {
     XMLTag[] chems = theModel.getTags("chemical");
     DefaultMutableTreeNode chemTree = new DefaultMutableTreeNode("Chemical Properties");
     for (int i=0; i<chems.length; i++) addInOrder(chems[i].getValue("name")+"$Conc",chemTree);
     if (chemTree.getChildCount()>0) treeNode.add(chemTree);  
   }
    
    if ((options & SYSVAR)>0) {
      DefaultMutableTreeNode sysTree = new DefaultMutableTreeNode("System Variables");
      addInOrder("d_year",sysTree);
      addInOrder("d_leap",sysTree);
      addInOrder("PI",sysTree);
      addInOrder("TimeStep",sysTree);
      treeNode.add(sysTree);
    }

    if (((options & VARIABLE)>0) || ((options & VARVAR)>0)) {
      DefaultMutableTreeNode stateTree = new DefaultMutableTreeNode(theGroup.getValue("name")+" State Variables");
      if ((options & VARIABLE)>0) addToTree(theGroup,"variable",stateTree);
      XMLTag[] chems = theModel.getTags("chemical");
      for (int i=0; i<chems.length; i++) {
        addInOrder(chems[i].getValue("name")+"$Pool",stateTree);
        addInOrder(chems[i].getValue("name")+"$Ingested",stateTree);
      }
      addInOrder("z",stateTree);
      if ((options & VARVAR)>0) addToTree(theGroup,"varietyvariable",stateTree);
      if (stateTree.getChildCount()>0) treeNode.add(stateTree);
    }
    
    if (((options & LOCALVAR)>0) || ((options & VARLOCAL)>0)) {
      DefaultMutableTreeNode localTree = new DefaultMutableTreeNode("Local Variables");
      if ((options & LOCALVAR)>0) addToTree(theGroup,"local",localTree);    
      if ((options & VARLOCAL)>0) addToTree(theGroup,"varietylocal",localTree);    
      if (localTree.getChildCount()>0) treeNode.add(localTree);
    }
   
    if ((options & VARCONC)>0) {
      DefaultMutableTreeNode varConcTree = new DefaultMutableTreeNode("Food Sets");
      addToTree(theGroup,"varietyconcentration",varConcTree);    
      if (varConcTree.getChildCount()>0) treeNode.add(varConcTree);
    }

    if (((options & VARPARAM)>0) || ((options & PARAMETER)>0)) {
      DefaultMutableTreeNode parameters = new DefaultMutableTreeNode("Constant Parameters");
      if ((options & PARAMETER)>0) addToTree(theGroup,"parameter",parameters);
      if ((options & VARPARAM)>0) addToTree(theGroup,"varietyparameter",parameters);
      if (parameters.getChildCount()>0) treeNode.add(parameters);
    }
    
    int i=0;
    while (i<theTree.getRowCount()) theTree.collapseRow(i++);
    theTree.expandRow(0);
    if (treeNode.getChildCount()==1) theTree.expandRow(1);
    remvar.setEnabled(false);
    editvar.setEnabled(false);
    detailsHTML.setText("<html></html>");
  }

  public VariableChooser(JDialog jd) {
    super(jd,"Choose Variable",true);
    addVarPage = new AddVarPage(this);    
    getContentPane().setLayout(new BorderLayout());
    EventHandler eh = new EventHandler();
    setSize(400,425);
    getContentPane().add(titleLabel,"North");
    addWindowListener(eh);
    theTree.putClientProperty("JTree.lineStyle", "Horizontal");
    final JScrollPane tt = new JScrollPane(theTree);
    tt.setPreferredSize(new Dimension(350,300));
    detailsHTML.setText("<html></html>");
    detailsHTML.setEditable(false);
    detailsHTML.setContentType("text/html");
    getContentPane().add(tt,"North");
    exit.addActionListener(eh);
    addvar.addActionListener(eh);
    choose.addActionListener(eh);
    remvar.addActionListener(eh);
    editvar.addActionListener(eh);
    choose.setEnabled(false);
    remvar.setEnabled(false);
    editvar.setEnabled(false);
    getContentPane().add(detailsHTML,"Center");
    exit.setPreferredSize(new Dimension(120,20));
    choose.setPreferredSize(new Dimension(120,20));
    addvar.setPreferredSize(new Dimension(120,20));
    remvar.setPreferredSize(new Dimension(120,20));
    editvar.setPreferredSize(new Dimension(120,20));
    detailsHTML.setPreferredSize(new Dimension(250,100));
    JPanel butPanel = new JPanel(new GridLayout(5,0));
    butPanel.add(choose);
    butPanel.add(addvar);
    butPanel.add(remvar);
    butPanel.add(editvar);
    butPanel.add(exit);
    getContentPane().add(butPanel,"East");
    theTree.addMouseListener(eh);
    theTree.addKeyListener(eh);
    pack();
  }

  public void locateVar(XMLTag t) {
    String theCat = t.getName();
    String theName = t.getTag("name").getValue();
    String tp = "[SCOPE, "+ConvertTagToText(theGroup,theCat)+", "+theName+"]";
    int i=0;
    while (i<theTree.getRowCount()) theTree.expandRow(i++);
    boolean b = false;
    i = 1;
    while (!b) {
      if (theTree.getPathForRow(i).toString().equals(tp)) {
        TreePath tpath = theTree.getPathForRow(i);
        b = true;
        i = 1;
        while (i<theTree.getRowCount()) theTree.collapseRow(i++);
        theTree.setSelectionPath(tpath);
      } else i++;
    }
  }
  

  public void show(XMLTag xmlFile, XMLTag r, DefaultMutableTreeNode tr, AbstractButton b, XMLTag func, XMLTag group, int op) {
    buttonText = b;
    toReplace = r;
    replaceNode = tr;
    theModel = xmlFile.getTag("model");
    theFunction = func;
    theGroup = group;
/* NPE sometimes happens on following line. */

    options=op;
    initialiseVarChooser();
    setVisible(true);
  }
  
  class EventHandler implements MouseListener, ActionListener, WindowListener, KeyListener {
   
    public EventHandler() {}

    public void treeSelectHandler() {
      TreePath selPath = theTree.getSelectionPath();
      if (selPath==null) {
        theTree.setSelectionRow(0);
        selPath=theTree.getSelectionPath();
      }
      theTree.clearSelection();
      int i=1;
      while (i<theTree.getRowCount()) theTree.collapseRow(i++);
      theTree.setSelectionPath(selPath);
      theTree.expandPath(selPath);
      choose.setEnabled(selPath.getPath().length==3);
      
      if (selPath.getPath().length!=3) editvar.setEnabled(false);
      else {
        String varName = theTree.getSelectionPath().getPath()[2].toString();
        if ((varName.endsWith("$Pool")) || (varName.endsWith("$Ingested")) || (varName.equals("z"))) editvar.setEnabled(false);
        else editvar.setEnabled(true);
      }
      remvar.setEnabled(editvar.isEnabled());
        
      if ((theTree.getSelectionPath()!=null) && (theTree.getSelectionPath().getPath().length==3)) {
        boolean found = false;
        i=0;
        String typeName = theTree.getSelectionPath().getPath()[1].toString();
        while (!found) 
          if (((String)varTitles.elementAt(i)).equals(typeName)) found = true; 
          else i++;
        String tagName = "*";//(String)varTags.elementAt(i);
        String varName = theTree.getSelectionPath().getPath()[2].toString();
   
        XMLTag theTag = theFunction.getTagWhere(tagName,"name",varName);
        if (theTag==null) theTag = theGroup.getTagWhere(tagName,"name",varName);
        detailsHTML.setText(VariableChooser.HTMLForVarHelper(varName,theGroup,theTag,false));
      } else {
        detailsHTML.setText("<html></html>");
      }
    }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
      if (e.getSource()==theTree) treeSelectHandler();
    }
    public void keyReleased(KeyEvent e) {
      if (e.getSource()==theTree) treeSelectHandler();
    }
    public void keyPressed(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==exit) {
        setVisible(false);
      } else if (e.getSource()==choose) {
        boolean found = false;
        int i=0;
        String typeName = theTree.getSelectionPath().getPath()[1].toString();
        while (!found) 
          if (((String)varTitles.elementAt(i)).equals(typeName)) found = true; else i++;
        String varName = theTree.getSelectionPath().getPath()[2].toString();
        if (replaceNode!=null) replaceNode.setUserObject("var:"+varName.trim());
        if (toReplace!=null) toReplace.getTag("name").setValue("var:"+varName.trim());
        if (buttonText!=null) buttonText.setText(varName);
        setVisible(false);
       
      } else if (e.getSource()==addvar) {
        addVarPage.addNewVar(theModel,theFunction,theGroup,AddVarPage.ALL, "");
        initialiseVarChooser();

      } else if (e.getSource()==editvar) {
        boolean found = false;
        int i=0;
        String typeName = theTree.getSelectionPath().getPath()[1].toString();
        while (!found) 
          if (((String)varTitles.elementAt(i)).equals(typeName)) found = true; else i++;
        
        String varName = theTree.getSelectionPath().getPath()[2].toString();
        XMLTag theTag=theFunction.getTagWhere("*","name",varName);
        if (theTag==null) theTag = theGroup.getTagWhere("*","name",varName);
        
        addVarPage.editThisVar(theModel,theFunction,theGroup,theTag,AddVarPage.ALL);
        initialiseVarChooser();

      } else if (e.getSource()==remvar) {
        String varName = theTree.getSelectionPath().getPath()[2].toString();
        Vector V = new Vector();
        int fgNo = 1;
        XMLTag fg = theModel.getTag("functionalgroup",fgNo);
        while (fg!=null) {
          int fNo = 1;
          XMLTag f = fg.getTag("function",fNo);
          while (f!=null) {
            int eNo = 1;
            XMLTag etag = f.getTag("equation",eNo);
            while (etag!=null) {
              String eq = etag.getValue("eq");
              if ((eq.indexOf("\\var{"+varName+"}")!=-1) || (eq.indexOf("\\varhist{"+varName+",")!=-1))
                V.addElement(new String(fg.getValue("name")+": "+f.getValue("name")+", "+etag.getValue("name")));
              etag = f.getTag("equation",++eNo);
            }
            f = fg.getTag("function",++fNo);
          }
          fg = theModel.getTag("functionalgroup",++fgNo);
        }
        if (V.size()!=0) {
          StringBuffer error = new StringBuffer();
          error.append("Cannot delete - "+varName+" - it occurs in:\n");
          for (int i=0; i<V.size(); i++) error.append("  *  "+V.get(i).toString());
          JOptionPane.showMessageDialog(VariableChooser.this, error.toString());
        } else {
          boolean found = false;
          int i=0;
          String typeName = theTree.getSelectionPath().getPath()[1].toString();
          while (!found) 
            if (((String)varTitles.elementAt(i)).equals(typeName)) found = true; else i++;
          String tagName = "*";//(String)varTags.elementAt(i);
          XMLTag theTag=theFunction.getTagWhere(tagName,"name",varName);
          if (theTag==null) theTag = theGroup.getTagWhere(tagName,"name",varName);
          theTag.removeFromParent();
          initialiseVarChooser();
        }
      }
    }

    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) { setVisible(false); }

    public void windowDeactivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
  }
}
