package VEW.Planktonica2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;
import VEW.Common.XML.*;


public class Importer extends JDialog {
 
//  private static Importer im = null;
  private JTree theTree = new JTree();
  private JPanel treePanel = new JPanel(new BorderLayout(0,0));
  private JEditorPane theDetails = new JEditorPane();
  private JPanel theIssues = new JPanel(new CardLayout());
  private JPanel buttonPanel = new JPanel(new FlowLayout());
  private JButton analyseButton = new JButton("Analyse");
  private JButton createButton = new JButton("Create");
  private JButton confirmButton = new JButton("Confirm");
  private JButton exitButton = new JButton("Exit");
  private final static String TableString = "TABLE";
  private final static String IssueString = "ISSUE";

  // For the table of linked/unlinked items card.
  
  private JPanel tablePanel = new JPanel(new BorderLayout(0,0));
  private JPanel issuePanel = new JPanel(new BorderLayout(0,0));  
  private IssueTableModel theIssueTable = new IssueTableModel();
  private JTable statusTable = new JTable(theIssueTable);

  // For the 'create new item, or rename to existing' card.


  private JButton createNew = new JButton();
  private JComboBox itemToUse = new JComboBox();
  private JButton okUse = new JButton("Use Selected");
  private JButton cancelUse = new JButton("Cancel");
  private JLabel notFoundMessage = new JLabel();

  // Some useful constants/system things
  
  private byte mode = 0;
  public static final byte FUNCTIONALGROUPS = 1;
  public static final byte CHEMICALS = 2;
  public static final byte VARIABLES = 4;
  public static final byte FG_FUNCTIONS = 5;
  public static final byte CH_FUNCTIONS = 7;
  public static final byte PI_FUNCTIONS = 9;

  private XMLTag model;
  private XMLTag toImport;
  private EventHandler eh = new EventHandler();
  private AddVarPage addVarPage = null;
  private Planktonica thePlank;
  
  
  


  public Importer(JDialog parent,Planktonica p) {
    super(parent,"Planktonica Importer",true);
    thePlank=p;
    addVarPage = new AddVarPage(this);
    int height = 500;
    int width = 400;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(0, 0, width, height);
    setLocation(screenSize.width/2 - (width/2), screenSize.height/2 - (height/2));
    
    /* Set up the tree viewer */
    
    getContentPane().setLayout(new BorderLayout(0,0));
    getContentPane().add(treePanel,BorderLayout.WEST);
    treePanel.setBorder(new BevelBorder(BevelBorder.RAISED));
    final JScrollPane treeScroller = new JScrollPane(theTree);
    treeScroller.setPreferredSize(new Dimension(200,400));
    treePanel.setPreferredSize(new Dimension(200,430));    
    treePanel.add(treeScroller,BorderLayout.NORTH);
    JPanel analyseCreate = new JPanel(new FlowLayout());
    analyseCreate.add(analyseButton);
    analyseCreate.add(createButton);
    treePanel.add(analyseCreate,BorderLayout.CENTER);
    analyseButton.setPreferredSize(new Dimension(80,30));    
    createButton.setPreferredSize(new Dimension(80,30));
    analyseButton.setEnabled(false);
    createButton.setEnabled(true);
    createButton.addActionListener(eh);
    analyseButton.addActionListener(eh);
    theTree.addMouseListener(eh);

    /* The button panel */
    buttonPanel.setPreferredSize(new Dimension(400,30));
    confirmButton.setPreferredSize(new Dimension(80,30));
    exitButton.setPreferredSize(new Dimension(80,30));
    buttonPanel.add(confirmButton);
    buttonPanel.add(exitButton);
    confirmButton.setEnabled(false);
    exitButton.setEnabled(true);
    exitButton.addActionListener(eh);
    confirmButton.addActionListener(eh);

    /* The fix issue panel */

    issuePanel.add(notFoundMessage,BorderLayout.NORTH);
    JPanel itemNew = new JPanel(new FlowLayout());
    itemNew.add(itemToUse);
    itemNew.add(okUse);
    itemNew.add(createNew);
    issuePanel.add(itemNew,BorderLayout.CENTER);
 

    /* The Issue Panel */
    theIssues.add(issuePanel,IssueString);
    theIssues.add(tablePanel,TableString);
    theIssues.setBorder(new BevelBorder(BevelBorder.RAISED));
    //theIssues.setPreferredSize(new Dimension(400,260));
    statusTable.getColumnModel().getColumn(3).setCellRenderer(new IconRenderer());    
    statusTable.addMouseListener(eh);
    tablePanel.add(new JScrollPane(statusTable),BorderLayout.CENTER);
    ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
    
    final JPanel buttonFlow = new JPanel(new FlowLayout());
    buttonFlow.add(cancelUse);
    
    okUse.addActionListener(eh);
    cancelUse.addActionListener(eh);
    itemToUse.addActionListener(eh);
    createNew.addActionListener(eh);

    /* The East side of the main screen and Details Panel */
    final JPanel eastSide = new JPanel(new BorderLayout(0,0));
    eastSide.add(new JScrollPane(theDetails),BorderLayout.NORTH);
    theDetails.setEditable(false);
    theDetails.setContentType("text/html");
    eastSide.add(theIssues,BorderLayout.CENTER);
    eastSide.add(buttonPanel,BorderLayout.SOUTH);
    getContentPane().add(eastSide,BorderLayout.EAST);
    theDetails.setBorder(new BevelBorder(BevelBorder.RAISED));
    theDetails.setPreferredSize(new Dimension(400,140));
  }

  public void getNames(String theDir, String title, boolean subfunc) {
    XMLTag t = new XMLTag(title);
    getNames(theDir,t,subfunc);
    theTree.setModel(new XMLTreeModel(t));
    theTree.setCellRenderer(new XMLCellRenderer());
  }
  
  public void getNames(String theDir, XMLTag theTag, boolean subfunc) {
    File TheDir = new File(theDir);
    File[] names = TheDir.listFiles(new EventHandler(EventHandler.XMLFileFilter));
    for (int i=0; i<names.length; i++) {
      if (names[i].isDirectory()) {
        XMLTag t = new XMLTag(names[i].getName());
        getNames(theDir+"/"+names[i].getName(),t,subfunc);
        theTag.addTag(t);
      } else {
        XMLFile xmlf = XMLFile.LoadFile(names[i].getAbsolutePath());
        if (subfunc==names[i].getName().startsWith("$")) {
          XMLTag t = new XMLTag(xmlf.getValue("archivename"));
          t.setAttribute("filename",names[i].getAbsolutePath());
          theTag.addTag(t);
        }
      }
    }
  }
  
  public void populateTree() {
    final String resDir = "Data/Archive";
    final String fgDir = resDir+"/FunctionalGroups";
    final String chemDir = resDir+"/Chemicals";
    final String fgFuncDir = resDir+"/Functions/FunctionalGroups";
    final String chemFuncDir = resDir+"/Functions/Chemicals";

    if (mode==FUNCTIONALGROUPS) {
      getNames(fgDir,"Functional Groups",false);
    } else if (mode==CHEMICALS) {
      getNames(chemDir,"Chemicals",false);
    } else if (mode==FG_FUNCTIONS) {
      getNames(fgFuncDir,"Functions for Functional Groups",false);
    } else if (mode==CH_FUNCTIONS) {
      getNames(chemFuncDir,"Functions for Chemicals",false);
    }
  }
  
  public void showImporter(byte theMode, XMLTag theModel) {
    model = theModel;
    mode = theMode;
    populateTree();
    theIssueTable.removeAll();
    analyseButton.setEnabled(false);
    confirmButton.setEnabled(false);
    pack();
    setVisible(true);
  }

  class EventHandler implements ActionListener, FileFilter, MouseListener {
    public static final int XMLFileFilter = 1;

   
    private int objectID;
    public EventHandler(int obj) {
      objectID = obj;
    }
    public EventHandler() { }
    
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {
      if (e.getSource()==theTree) {
        boolean enableAnalyse = ((theTree.getSelectionCount()==1) && (theTree.getSelectionRows()[0]>0));
        if (enableAnalyse) {
          XMLTag t = (XMLTag) (theTree.getSelectionPath().getLastPathComponent());
          if (t.getAttribute("filename")!=null) {
            toImport = XMLFile.LoadFile(t.getAttribute("filename"));
            String name = toImport.getValue("name");
            String date = toImport.getValue("date");
            String comment = toImport.getValue("comment");
            String author = toImport.getValue("author");
            if (name==null) name="";
            if (date==null) date="";
            if (comment==null) comment="";
            if (author==null) author="";
            theDetails.setText("<html><body><table><tr><td>Name</td><td>"+toImport.getValue("name")+"</td></tr><tr><td>Author</td><td>"+toImport.getValue("author")
                         +"</td></tr><tr><td>Date</td><td>"+toImport.getValue("date")+"</td></tr><tr><td>Comments</td><td>"+toImport.getValue("comment")+"</td></tr></table></body></html>");
          } else {
            theDetails.setText("<html></html>");
            enableAnalyse=false;
          }
        } else theDetails.setText("<html></html>");
        analyseButton.setEnabled(enableAnalyse);
      } 

      else if (e.getSource()==statusTable) {
        String type = (String) statusTable.getValueAt(statusTable.getSelectedRow(),1);
        if (type.toUpperCase().equals("CHEMICAL")) prepareChooseChem();
        else if (type.toUpperCase().equals("STAGE")) prepareForStage();
        else prepareForVariable(type);
        ((CardLayout)theIssues.getLayout()).show(theIssues,IssueString);
      } 

      
    }
    
    public void finalise() {
      setVisible(false);
    }


    public boolean accept(File d) { 
      if (objectID==XMLFileFilter) {
        boolean b = ((d.getName().toUpperCase().endsWith(".XML"))||d.isDirectory());
        if (d.getName().startsWith(".")) b = false;
        if ((b) && (!d.isDirectory())){
          XMLFile f = XMLFile.LoadFile(d);
          if ((f.getValue("invisible")!=null) && (f.getValue("invisible").equals("true"))) b = false;
        }
        return b; 
      } else return false;
    }

    public void checkForStages() {
      Vector V = new Vector();
      XMLTag eqs[] = toImport.getTags("equation");
      XMLTag[] calledin = toImport.getTags("calledin");
      for (int i=0; i<eqs.length; i++) {
        String eq = eqs[i].getValue("eq");
        if (eq.indexOf("change")>=0) {
          int index=eq.indexOf("change");
          eq = eq.substring(index);
          String stageName=eq.substring(14,eq.indexOf("}"));
          boolean found = false;
          int j=0;
          while ((!found) && (j<V.size()))
            found = (((String) V.elementAt(j++)).equals(stageName));
          if (!found) V.addElement(stageName);
        }
      }
      for (int i=0; i<calledin.length; i++) {
        boolean found = false;
        String stageName = calledin[i].getValue();
        int j=0;
        while ((!found) && (j<V.size())) 
          found = (((String) V.elementAt(j++)).equals(stageName));
        if (!found) V.addElement(stageName);
      }
      
      
      XMLTag t = thePlank.getCurrentInstance();
      for (int i=0; i<V.size(); i++) {
        String stageName = (String) V.elementAt(i);
        if (t.getTagWhere("stage","name",stageName)==null) 
          theIssueTable.add(stageName,"Stage","?",IconRenderer.ERRORED,null);
        else  
          theIssueTable.add(stageName,"Stage","="+stageName,IconRenderer.FINE,null);
      }
    }
          

    public void checkForChemicals() {
      Vector V = new Vector();
      XMLTag funcs[] = toImport.getTags("function");
      for (int i=0; i<funcs.length; i++) {
        XMLTag eqs[] = funcs[i].getTags("equation");
        for (int k=0; k<eqs.length; k++) {
          String eq = eqs[k].getValue("eq");
          while (eq.indexOf("$")>=0) {
            int indexOfDOL = eq.indexOf("$");
            int index = indexOfDOL-1;
            while (eq.charAt(index)!='{') index--;
            String chemName = eq.substring(index+1,indexOfDOL);
            eq=eq.substring(0,indexOfDOL)+'#'+eq.substring(indexOfDOL+1);
            boolean found = false;
            int j=0;
            while ((!found) && (j<V.size()))
              found=(chemName.equals(V.elementAt(j++)));
            if (!found) V.addElement(chemName);
          }
        }
      }
      funcs = toImport.getTags("subfunction");
      for (int i=0; i<funcs.length; i++) {
        XMLTag eqs[] = funcs[i].getTags("equation");
        for (int k=0; k<eqs.length; k++) {
          String eq = eqs[k].getValue("eq");
          while (eq.indexOf("$")>=0) {
            int indexOfDOL = eq.indexOf("$");
            int index = indexOfDOL-1;
            while (eq.charAt(index)!='{') index--;
            String chemName = eq.substring(index+1,indexOfDOL);
            eq=eq.substring(0,indexOfDOL)+'#'+eq.substring(indexOfDOL+1);
            boolean found = false;
            int j=0;
            while ((!found) && (j<V.size()))
              found=(chemName.equals(V.elementAt(j++)));
            if (!found) V.addElement(chemName);
          }
        }
      }
      for (int i=0; i<V.size(); i++) {
        String chemName = (String) V.elementAt(i);
        if (model.getTagWhere("chemical","name",chemName)==null) 
          theIssueTable.add(chemName,"Chemical","?",IconRenderer.ERRORED,null);
        else  
          theIssueTable.add(chemName,"Chemical","="+chemName,IconRenderer.FINE,null);
      }
    }

    public void checkFuncForChem() {
      Vector V = new Vector();
      XMLTag eqs[] = toImport.getTags("equation");
      for (int i=0; i<eqs.length; i++) {
        String eq = eqs[i].getValue("eq");
        while (eq.indexOf("$")>=0) {
          int indexOfDOL = eq.indexOf("$");
          int index = indexOfDOL-1;
          while (eq.charAt(index)!='{') index--;
          String chemName = eq.substring(index+1,indexOfDOL);
          eq=eq.substring(0,indexOfDOL)+'#'+eq.substring(indexOfDOL+1);
          boolean found = false;
          int j=0;
          while ((!found) && (j<V.size()))
            found=(chemName.equals(V.elementAt(j++)));
          if (!found) V.addElement(chemName);
        }
      }
      for (int i=0; i<V.size(); i++) {
        String chemName = (String) V.elementAt(i);
        if (model.getTagWhere("chemical","name",chemName)==null)
          theIssueTable.add(chemName,"Chemical","?",IconRenderer.ERRORED,null);
        else 
          theIssueTable.add(chemName,"Chemical","="+chemName,IconRenderer.FINE,null);
      }
    }

    public Vector genericScan(String tagname, String title, XMLTag[] check, XMLTag dest) {
      Vector V = new Vector();
      for (int i=0; i<check.length; i++) {
        String name = check[i].getValue("name");
        if (dest.getTagWhere(tagname,"name",name)!=null) {
          V.add(name);
          V.add(title);
          V.add(String.valueOf(IconRenderer.FINE));  
        } else {
          V.add(name);
          V.add(title);
          V.add(String.valueOf(IconRenderer.ERRORED));  
        }
      }
      return V;
    }

    
    public void addIfNotFound(Vector V, Vector all) {
      String name, title, msg;
      int i=0;
      while (i<V.size()) {
        name = V.elementAt(i).toString();
        title = V.elementAt(1+i).toString();
        msg = V.elementAt(2+i).toString();
        int j=0;
        boolean found=false;
        while ((j<all.size()) && (!found)) {
          found = (all.elementAt(j).toString().equals(name));
          j+=3;
        }
        if (!found) {
          all.addElement(name);
          all.addElement(title);
          all.addElement(msg);
        }
        i+=3;
      }
    }
    
    public void checkFuncForVarsFG() {
      Vector allIssues = new Vector();
      XMLTag theFG = thePlank.getCurrentInstance();
      checkFuncForVarsChem();
      XMLTag[] items = toImport.getTags("varietyconcentration");
      Vector V = genericScan("varietyconcentration","Variety-based Concentration",items,theFG);
      addIfNotFound(V,allIssues);
      items = toImport.getTags("varietyparameter");
      V = genericScan("varietyparameter","Variety-based Parameter",items,theFG);
      for (int i=0; i<items.length; i++) {
        String link = items[i].getAttribute("link");
        XMLTag dummyEntry = new XMLTag("varietyconcentration");
        dummyEntry.addTag(new XMLTag("name",link));
        XMLTag[] dummyArray = new XMLTag[1];
        dummyArray[0]=dummyEntry;
        Vector V2 = genericScan("varietyconcentration","Variety-based Concentration",dummyArray,theFG);
        addIfNotFound(V2,allIssues);
      }
      addIfNotFound(V,allIssues);
      items = toImport.getTags("varietyvariable");
      V = genericScan("varietyvariable","Variety-based Variable",items,theFG);
      for (int i=0; i<items.length; i++) {
        String link = items[i].getAttribute("link");
        XMLTag dummyEntry = new XMLTag("varietyconcentration");
        dummyEntry.addTag(new XMLTag("name",link));
        XMLTag[] dummyArray = new XMLTag[1];
        dummyArray[0]=dummyEntry;
        Vector V2 = genericScan("varietyconcentration","Variety-based Concentration",dummyArray,theFG);
        addIfNotFound(V2,allIssues);
      }
      addIfNotFound(V,allIssues);
      items = toImport.getTags("varietylocal");
      for (int i=0; i<items.length; i++) {
        String link = items[i].getAttribute("link");
        XMLTag dummyEntry = new XMLTag("varietyconcentration");
        dummyEntry.addTag(new XMLTag("name",link));
        XMLTag[] dummyArray = new XMLTag[1];
        dummyArray[0]=dummyEntry;
        Vector V2 = genericScan("varietyconcentration","Variety-based Concentration",dummyArray,theFG);
        addIfNotFound(V2,allIssues);
      }
      addAll(allIssues);
    }

    public void addAll(Vector all) {
      int i=0;
      while (i<all.size()) {
        String name = all.elementAt(i).toString();
        String title = all.elementAt(i+1).toString();
        String msg = all.elementAt(i+2).toString();
        if (msg.equals(String.valueOf(IconRenderer.ERRORED)))
          theIssueTable.add(name,title,"?",IconRenderer.ERRORED,null);
        else
          theIssueTable.add(name,title,"="+name,IconRenderer.FINE,null);
        i+=3;
      }
    }
    
    
    public void checkFuncForVarsChem() {
      Vector allIssues = new Vector();
      XMLTag theFG = thePlank.getCurrentInstance();
      XMLTag[] items = toImport.getTags("parameter");
      Vector V = genericScan("parameter","Parameter",items,theFG);
      addIfNotFound(V,allIssues);
      items = toImport.getTags("variable");
      V = genericScan("variable","Variable",items,theFG);
      addIfNotFound(V,allIssues);
      items = toImport.getTags("exportvar");
      V = genericScan("exportvar","Exportvar",items,theFG);
      addIfNotFound(V,allIssues);
      addAll(allIssues);
    }
   
    public void prepareChooseChem() {
      itemToUse.removeAllItems();
      XMLTag[] chems = model.getTags("chemical");
      for (int i=0; i<chems.length; i++) itemToUse.addItem(chems[i].getValue("name"));
      createNew.setText("New Chemical");
      notFoundMessage.setText("Select or create chemical for: "+theIssueTable.getValueAt(statusTable.getSelectedRow(),0));
    }

    public void populateWith(String type) {
      itemToUse.removeAllItems();
      XMLTag[] items = thePlank.getCurrentInstance().getTags(type);
      for (int i=0; i<items.length; i++) itemToUse.addItem(items[i].getValue("name"));
    }
    
    public void prepareForVariable(String type) {
      populateWith(type.toLowerCase());
      createNew.setText("New "+type);
      notFoundMessage.setText("Select or create "+type);
    }
    
    public void prepareForStage() {
      XMLTag theFG = thePlank.getCurrentInstance();
      XMLTag[] okStages = theFG.getTags("stage");
      itemToUse.removeAllItems();
      for (int i=0; i<okStages.length; i++) itemToUse.addItem(okStages[i].getValue("name"));
      createNew.setText("New stage");
      notFoundMessage.setText("Select or create stage");
    }

    public void addNewChemical(String name) {
      XMLTag chem = new XMLTag("chemical");
      chem.addTag(new XMLTag("pigment","false"));
      chem.addTag(new XMLTag("name",name));
      XMLTag hrf = new XMLTag("spectrum");
      hrf.addTag(new XMLTag("name","Heat Response"));
      hrf.addTag(new XMLTag("equation"));
      hrf.getTag("equation").addTag(new XMLTag("name","Heat Response"));
      hrf.getTag("equation").addTag(new XMLTag("eq","\\graphvals{{0,0.0},{1,0.0},{2,0.0},{3,0.0},{4,0.0},{5,0.0},{6,0.0},{7,0.0},{8,0.0},{9,0.0},{10,0.0},{11,0.0},{12,0.0},{13,0.0},{14,0.0},{15,0.0},{16,0.0},{17,0.0},{18,0.0},{19,0.0},{20,0.0},{21,0.0},{22,0.0},{23,0.0},{24,0.0}}"));
      chem.addTag(hrf);
      XMLTag hrf2 = new XMLTag("spectrum");
      hrf2.addTag(new XMLTag("name","Photosynthesis Response"));
      hrf2.addTag(new XMLTag("equation"));
      hrf2.getTag("equation").addTag(new XMLTag("name","Photosynthesis Response"));
      hrf2.getTag("equation").addTag(new XMLTag("eq","\\graphvals{{0,0.0},{1,0.0},{2,0.0},{3,0.0},{4,0.0},{5,0.0},{6,0.0},{7,0.0},{8,0.0},{9,0.0},{10,0.0},{11,0.0},{12,0.0},{13,0.0},{14,0.0},{15,0.0},{16,0.0},{17,0.0},{18,0.0},{19,0.0},{20,0.0},{21,0.0},{22,0.0},{23,0.0},{24,0.0}}"));
      chem.addTag(hrf2);
      XMLTag hrf3 = new XMLTag("spectrum");
      hrf3.addTag(new XMLTag("name","Vision Spectrum"));
      hrf3.addTag(new XMLTag("equation"));
      hrf3.getTag("equation").addTag(new XMLTag("name","Vision Spectrum"));
      hrf3.getTag("equation").addTag(new XMLTag("eq","\\graphvals{{0,0.0},{1,0.0},{2,0.0},{3,0.0},{4,0.0},{5,0.0},{6,0.0},{7,0.0},{8,0.0},{9,0.0},{10,0.0},{11,0.0},{12,0.0},{13,0.0},{14,0.0},{15,0.0},{16,0.0},{17,0.0},{18,0.0},{19,0.0},{20,0.0},{21,0.0},{22,0.0},{23,0.0},{24,0.0}}"));
      chem.addTag(hrf3);
      XMLTag hrf4 = new XMLTag("spectrum");
      hrf4.addTag(new XMLTag("name","Reflectivity Spectrum"));
      hrf4.addTag(new XMLTag("equation"));
      hrf4.getTag("equation").addTag(new XMLTag("name","Reflectivity Spectrum"));
      hrf4.getTag("equation").addTag(new XMLTag("eq","\\graphvals{{0,0.0},{1,0.0},{2,0.0},{3,0.0},{4,0.0},{5,0.0},{6,0.0},{7,0.0},{8,0.0},{9,0.0},{10,0.0},{11,0.0},{12,0.0},{13,0.0},{14,0.0},{15,0.0},{16,0.0},{17,0.0},{18,0.0},{19,0.0},{20,0.0},{21,0.0},{22,0.0},{23,0.0},{24,0.0}}"));
      chem.addTag(hrf4);
      model.addTag(chem);
    }

    
    public void addNewFunctionalGroup(String name) {
      XMLTag fg = new XMLTag("functionalgroup");
      fg.addTag(new XMLTag("name",name));
      fg.addTag(new XMLTag("stage"));
      fg.getTag("stage").addTag("name","Default");
      fg.getTag("stage").setAttribute("closure","false");
      fg.getTag("stage").setAttribute("log","false");
      fg.getTag("stage").addTag("comment","Default Stage");
      model.addTag(fg);
    }

    public String formalName(String type) {
      if (type.toUpperCase().equals("FUNCTION")) return "function";
      else if (type.toUpperCase().equals("SUB-FUNCTION")) return "subfunction";
      else if (type.toUpperCase().equals("CHEMICAL")) return "chemical";
      else if (type.toUpperCase().equals("FUNCTIONAL GROUP")) return "functionalgroup";
      else return type;
    }
    
    public void AddNewItem(String type,String defName,String NewID, boolean table) {
      type = formalName(type);
      NewID = NewID.trim();
     
      if (type.toUpperCase().equals("CHEMICAL")) {
        if (model.getTagWhere(type,"name",NewID)!=null) {
          javax.swing.JOptionPane.showMessageDialog(null, "A Chemical with that name already exists");
        } else {
          addNewChemical(NewID);
          if (table) {
            int i = statusTable.getSelectedRow();
            theIssueTable.setValueAt("Created",i,2);
            theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            checkConfirmOption();
            ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
          }
        }
      } else if (type.toUpperCase().equals("FUNCTIONALGROUP")) {
        if (model.getTagWhere(type,"name",NewID)!=null) {
          javax.swing.JOptionPane.showMessageDialog(null, "A Functional Group with that name already exists");
        } else {
          addNewFunctionalGroup(NewID);
          if (table) {
            int i = statusTable.getSelectedRow();
            theIssueTable.setValueAt("Created",i,2);
            theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            checkConfirmOption();
            ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
          }
        }
      } else if (type.toUpperCase().equals("STAGE")) {
        XMLTag inst = thePlank.getCurrentInstance();
      
        if (inst.getTagWhere(type,"name",NewID)!=null) {
          javax.swing.JOptionPane.showMessageDialog(null, "A Stage with that name already exists");
        } else {
          XMLTag stage = new XMLTag("stage");
          stage.addTag("name",NewID);
          stage.addTag("comment",NewID);
          inst.addTag(stage);

          if (table) {
            int i = statusTable.getSelectedRow();
            theIssueTable.setValueAt("Created",i,2);
            theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            checkConfirmOption();
            ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
          }
        }

      } else {
        XMLTag group = thePlank.getCurrentInstance();
        if (group.getTagWhere(type,"name",NewID)!=null) {
          javax.swing.JOptionPane.showMessageDialog(null, "A "+type+" with that name already exists");
        } else {
          XMLTag newTag = new XMLTag(type);
          newTag.addTag(new XMLTag("name",NewID));
          group.addTag(newTag);

          if (table) {
            int i = statusTable.getSelectedRow();
            theIssueTable.setValueAt("Created",i,2);
            theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            checkConfirmOption();
            ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
          }
        }
      }
    }

    public String replaceText(String eq, String oldText, String newText) {
      while (eq.indexOf(oldText)>=0)
        eq = eq.substring(0,eq.indexOf(oldText))+newText+eq.substring(eq.indexOf(oldText)+oldText.length());
      return eq;
    }

    public void replaceInVarTag(XMLTag t, String oldChemical, String newChemical) {
      String name = t.getValue("name");
      if (name.startsWith(oldChemical+"$")) {
        name = replaceText(name,oldChemical+"$",newChemical+"$");
        t.getTag("name").setValue(name);
        String codename=t.getValue("codename");
        codename = replaceText(name,"\""+oldChemical+"\"","\""+newChemical+"\"");
        t.getTag("codename").setValue(codename);
      }
    }
    
    public void linkChemicals() { 
      for (int i=0; i<theIssueTable.getRowCount(); i++) {
        String type = (String) theIssueTable.getValueAt(i,1);
        if (type.toUpperCase().equals("CHEMICAL")) {
          String linkage = (String) theIssueTable.getValueAt(i,2);
          if (linkage.startsWith("Use ")) {
            String oldChemical = (String) theIssueTable.getValueAt(i,0);
            String newChemical = linkage.substring(4);
            XMLTag[] funcs = toImport.getTags("function");
            for (int j=0; j<funcs.length; j++) {
              linkEqChemicals(funcs[j],oldChemical,newChemical);
            }
            funcs = toImport.getTags("subfunction");
            for (int j=0; j<funcs.length; j++) {
              linkEqChemicals(funcs[j],oldChemical,newChemical);
            }
          }
        }
      }
    }
     
    public void fixVLink(XMLTag[] fs, String oldVar, String newVar, String type) {
      for (int j=0; j<fs.length; j++) fixVLink(fs[j],oldVar,newVar,type);
    }
    
    public void fixVLink(XMLTag f, String oldVar, String newVar, String type) {
      XMLTag[] v = f.getTags(type);
      for (int k=0; k<v.length; k++) {
        if (v[k].getAttribute("link").equals(oldVar)) v[k].setAttribute("link",newVar);
      }
    }
    
    
    public void linkVars() { 
      for (int i=0; i<theIssueTable.getRowCount(); i++) {
        String type = (String) theIssueTable.getValueAt(i,1);
        if ((type.toUpperCase().equals("VARIABLE")) ||
            (type.toUpperCase().equals("PARAMETER")) ||
            (type.toUpperCase().equals("EXPORTVAR")) ||            
            (type.toUpperCase().equals("VARIETY-BASED VARIABLE")) ||
            (type.toUpperCase().equals("VARIETY-BASED PARAMETER")) ||
            (type.toUpperCase().equals("VARIETY-BASED CONCENTRATION"))) {
          String linkage = (String) theIssueTable.getValueAt(i,2);
          if (linkage.startsWith("Use ")) {
            String oldVar = (String) theIssueTable.getValueAt(i,0);
            String newVar = linkage.substring(4);
            XMLTag[] funcs = toImport.getTags("function");
            XMLTag[] subfuncs = toImport.getTags("subfunction");
            for (int j=0; j<funcs.length; j++) {
              linkEqChemicals(funcs[j],oldVar,newVar);
            }
            for (int j=0; j<subfuncs.length; j++) {
              linkEqChemicals(subfuncs[j],oldVar,newVar);
            }
            if (type.equals("VARIEY-BASED CONCENTRATION")) {
              fixVLink(funcs,oldVar,newVar,"varietylocal");
              fixVLink(subfuncs,oldVar,newVar,"varietylocal");
              fixVLink(toImport,oldVar,newVar,"varietyvariable");
              fixVLink(toImport,oldVar,newVar,"varietyparameter");
            }
          }
        }
      }
    }
    public void linkEqVars(XMLTag parent, String oldVar, String newVar) {
      XMLTag[] eqs = parent.getTags("equation");
      for (int k=0; k<eqs.length; k++) {
        String eq = eqs[k].getValue("eq");
        eq = replaceText(eq,"\\var{"+oldVar+"}","\\var{"+newVar+"}");
        eqs[k].setValue(eq);
      }
    }

    public void linkEqChemicals(XMLTag parent, String oldChemical, String newChemical) {
      XMLTag[] eqs = parent.getTags("equation");
      for (int k=0; k<eqs.length; k++) {
        String eq = eqs[k].getValue("eq");
        eq = replaceText(eq,oldChemical+"$",newChemical+"$");
        eqs[k].setValue(eq);
      }
    }
    
    public void linkEqStages(XMLTag parent, String oldStage, String newStage) {
      XMLTag[] eqs = parent.getTags("equation");
      for (int k=0; k<eqs.length; k++) {
        String eq = eqs[k].getValue("eq");
        eq = replaceText(eq,"change{\\stage{"+oldStage+"}","change{\\stage{"+newStage+"}");
        eqs[k].setValue(eq);
      }
    }
    
    public void linkCallStages(XMLTag parent, String oldStage, String newStage) {
      XMLTag[] calls = parent.getTags("calledin");
      for (int k=0; k<calls.length; k++) {
        if (calls[k].getValue().equals(oldStage)) calls[k].setValue(newStage);
      }
    }

    public void linkStages() {
      for (int i=0; i<theIssueTable.getRowCount(); i++) {
        String type = (String) theIssueTable.getValueAt(i,1);
        if (type.toUpperCase().equals("STAGE")) {
          String linkage = (String) theIssueTable.getValueAt(i,2);
          if (linkage.startsWith("Use ")) {
            String oldStage = (String) theIssueTable.getValueAt(i,0);
            String newStage = linkage.substring(4);
            linkEqStages(toImport,oldStage,newStage);
            linkCallStages(toImport,oldStage,newStage);
          }
        }
      }
    }
      
    public void linkFuncChemicals() { 
      for (int i=0; i<theIssueTable.getRowCount(); i++) {
        String type = (String) theIssueTable.getValueAt(i,1);
        if (type.toUpperCase().equals("CHEMICAL")) {
          String linkage = (String) theIssueTable.getValueAt(i,2);
          if (linkage.startsWith("Use ")) {
            String oldChemical = (String) theIssueTable.getValueAt(i,0);
            String newChemical = linkage.substring(4);
            linkEqChemicals(toImport,oldChemical,newChemical);
          }
        }
      }
    }

    public void analyse() {
      theIssueTable.removeAll();
      if ((mode==FUNCTIONALGROUPS) || (mode==CHEMICALS)) {
        checkForChemicals();
      } 

      else if (mode>=FG_FUNCTIONS) {
        checkFuncForChem();

        if (mode==FG_FUNCTIONS)  {
          checkFuncForVarsFG();
          checkForStages();
        } else checkFuncForVarsChem();
      }

      checkConfirmOption();
      ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
    }
    
    public void reAnalyse() {
      for (int i=0; i<theIssueTable.getRowCount(); i++) {
        String status = (String) theIssueTable.getValueAt(i,2);
        if (status.equals("?")) {
          String type = (String) theIssueTable.getValueAt(i,1);
          String name = (String) theIssueTable.getValueAt(i,0);
          XMLTag theFG = thePlank.getCurrentInstance();          
          if (type.toUpperCase().equals("CHEMICAL")) {
            if (model.getTag("model").getTagWhere("chemical","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          } else if (type.toUpperCase().equals("STAGE")) {
            if (theFG.getTagWhere("stage","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          } else if (type.toUpperCase().equals("PARAMETER")) {
            if (theFG.getTagWhere("parameter","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          } else if (type.toUpperCase().equals("EXPORTVAR")) {
            if (theFG.getTagWhere("exportvar","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          } else if (type.toUpperCase().equals("VARIABLE")) {
            if (theFG.getTagWhere("variable","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          } else if (type.toUpperCase().equals("VARIEY-BASED VARIABLE")) {
            if (theFG.getTagWhere("varietyvariable","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          } else if (type.toUpperCase().equals("VARIETY-BASED PARAMETER")) {
            if (theFG.getTagWhere("varietyparameter","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          } else if (type.toUpperCase().equals("VARIETY-BASED CONCENTRATION")) {
            if (theFG.getTagWhere("varietyconcentration","name",name)!=null) {
              theIssueTable.setValueAt("="+name,i,2);
              theIssueTable.setValueAt(String.valueOf(IconRenderer.FIXED),i,3);
            }
          }
        }
      }

      checkConfirmOption();
      ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
    }
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==exitButton) {
        setVisible(false);

      } else if (e.getSource()==confirmButton) {
        if ((mode==FUNCTIONALGROUPS) || (mode==CHEMICALS)) {
          linkChemicals();
          linkFuncChemicals();
          linkStages();
          linkVars();
          model.addTag((XMLTag)toImport.clone());
        
        } else if (mode>=FG_FUNCTIONS) {
          linkFuncChemicals();
          linkStages();
          linkVars();
          XMLTag theInstance = thePlank.getCurrentInstance();
          XMLTag[] checkNeeded = toImport.getTags();
          for (int i=0; i<checkNeeded.length; i++) {
            if (checkNeeded[i].getName().equals("parameter")) checkNeeded[i].removeFromParent();
            if (checkNeeded[i].getName().equals("biovar")) checkNeeded[i].removeFromParent();
            if (checkNeeded[i].getName().equals("chemvar")) checkNeeded[i].removeFromParent();            
            if (checkNeeded[i].getName().equals("exportvar")) checkNeeded[i].removeFromParent();            
            if (checkNeeded[i].getName().equals("sysvar")) checkNeeded[i].removeFromParent();
            if (checkNeeded[i].getName().equals("variable")) checkNeeded[i].removeFromParent();
            if (checkNeeded[i].getName().equals("varietyvariable")) checkNeeded[i].removeFromParent();
            if (checkNeeded[i].getName().equals("varietyconcentration")) checkNeeded[i].removeFromParent();            
            if (checkNeeded[i].getName().equals("varietyparameter")) checkNeeded[i].removeFromParent();            
          }
          theInstance.addTag((XMLTag)toImport.clone());
        }

        setVisible(false);

      } else if (e.getSource()==createButton) {
        String type = "";
        if (mode==FUNCTIONALGROUPS) type = "Functional Group";
        if (mode==CHEMICALS) type = "Chemical";
        if ((mode==FG_FUNCTIONS) || (mode==CH_FUNCTIONS)) type = "Function";

        String defName="";
        String NewID = javax.swing.JOptionPane.showInputDialog("Type name for new "+type, defName);
        if (NewID!=null) {
          AddNewItem(type,defName,NewID,false);
          setVisible(false);
        }

      } else if (e.getSource()==analyseButton) {
        analyse();

      } else if (e.getSource()==itemToUse) {
        okUse.setEnabled(itemToUse.getSelectedIndex()>-1);
        
      } else if (e.getSource()==okUse) {
               
        int i = statusTable.getSelectedRow();
        theIssueTable.setValueAt("Use "+(String)itemToUse.getSelectedItem(),i,2);
        theIssueTable.setValueAt(""+IconRenderer.FIXED,i,3);
        ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);
        checkConfirmOption();
      
      } else if (e.getSource()==cancelUse) {
        ((CardLayout)theIssues.getLayout()).show(theIssues,TableString);

      } else if (e.getSource()==createNew) {
        String type = (String) statusTable.getValueAt(statusTable.getSelectedRow(),1);
        String defName = (String) statusTable.getValueAt(statusTable.getSelectedRow(),0);
        if (type.toUpperCase().equals("CHEMICAL")) {
          String NewID = javax.swing.JOptionPane.showInputDialog("Type name for new "+type, defName);
          if (NewID!=null) AddNewItem(type,defName,NewID,true);
        } else {
          if (type.toUpperCase().equals("STAGE")) {
            String NewStage = javax.swing.JOptionPane.showInputDialog("Type name for new stage",defName);
            if (NewStage!=null) {
              AddNewItem(type,defName,NewStage,true);
            }
          } else if (type.toUpperCase().equals("PARAMETER")) addVarPage.addNewVar(model,null,thePlank.getCurrentInstance(),AddVarPage.PARAMETER, defName);
          else if (type.toUpperCase().equals("VARIABLE")) addVarPage.addNewVar(model,null,thePlank.getCurrentInstance(),AddVarPage.VARIABLE, defName);
          else if (type.toUpperCase().equals("VARIETY-BASED CONCENTRATION")) addVarPage.addNewVar(model,null,thePlank.getCurrentInstance(),AddVarPage.VARCONC, defName);
          else if (type.toUpperCase().equals("VARIETY-BASED PARAMETER")) addVarPage.addNewVar(model,null,thePlank.getCurrentInstance(),AddVarPage.VARPARAM, defName);
          else if (type.toUpperCase().equals("VARIETY-BASED VARIABLE")) addVarPage.addNewVar(model,null,thePlank.getCurrentInstance(),AddVarPage.VARVAR,defName);

          reAnalyse();
        }
      }
    }

    public void checkConfirmOption() {
      boolean b = true;
      int i=0;
      while ((b) && (i<theIssueTable.getRowCount()))
        b = (!theIssueTable.getValueAt(i++,3).toString().equals(""+IconRenderer.ERRORED));
      confirmButton.setEnabled(b);
    }
  }

  class IssueTableModel extends AbstractTableModel {
    private ArrayList names = new ArrayList();
    private ArrayList types = new ArrayList();
    private ArrayList assigns = new ArrayList();
    private ArrayList icons = new ArrayList();
    private ArrayList tags = new ArrayList();
    protected String[] columnNames = new String[] {"Name","Type","Action","Status"};
    protected Class[] columnClasses = new Class[] {String.class, String.class, String.class, String.class};
    public String getColumnName(int col) { return columnNames[col]; }
    public Class getColumnClass(int col) { return columnClasses[col]; }
    public int getColumnCount() { return 4; }
    public int getRowCount() { return names.size(); }
    
    public void removeAll() {
      names.clear();
      types.clear();
      assigns.clear();
      icons.clear();
      tags.clear();
      fireTableDataChanged();
    }

    public Object getValueAt(int row, int col) {
      if (col==0) return names.get(row);
      else if (col==1) return types.get(row);
      else if (col==2) return assigns.get(row);
      else if (col==3) return icons.get(row);
      else return null;
    }

    public void setValueAt(Object v, int row, int col) {
      if (col==0) names.set(row,v.toString());
      else if (col==1) types.set(row,v.toString());
      else if (col==2) assigns.set(row,v.toString());
      else if (col==3) icons.set(row,v.toString());
    }

    public IssueTableModel() {
      names.clear();
      types.clear();
      assigns.clear();
      icons.clear();
      tags.clear();
      fireTableDataChanged();
    }

    public IssueTableModel(ArrayList n, ArrayList t, ArrayList a, ArrayList i, XMLTag tag) {
      new IssueTableModel();
      for (int j=0; j<n.size(); j++) {
        names.add(n.get(j));
        types.add(t.get(j));
        assigns.add(a.get(j));
        icons.add(i.get(j));
        tags.add(tag);
        fireTableDataChanged();
      }
    }

    public void add(String n, String t, String a, int i, XMLTag tag) {
      names.add(n);
      types.add(t);
      assigns.add(a);
      icons.add(String.valueOf(i));
      tags.add(tag);
      fireTableDataChanged();
    }
  }
  
  public class IconRenderer extends DefaultTableCellRenderer {
    public static final byte FINE = 0;
    public static final byte FIXED = 1;
    public static final byte ERRORED = 2;
    private final ImageIcon[] iconpics = new ImageIcon[] {
      new ImageIcon("data/graphics/icons/imp_ok.gif"), 
      new ImageIcon("data/graphics/icons/imp_fixed.gif"), 
      new ImageIcon("data/graphics/icons/imp_error.gif")};

    public Component getTableCellRendererComponent ( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
      setIcon(iconpics[Integer.parseInt((String)value)]);
      return this;
    }
  }
}