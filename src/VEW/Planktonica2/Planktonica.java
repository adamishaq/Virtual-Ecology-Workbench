package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Vector;
import java.util.prefs.BackingStoreException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;
import VEW.Controller2.VEWController2;
import VEW.Planktonica2.ControllerStructure.ChemicalController;
import VEW.Planktonica2.ControllerStructure.VEWController;
import VEW.Planktonica2.ControllerStructure.FunctionalGroupController;
import VEW.Planktonica2.model.Model;
/**
 * VEW Planktonica display for editing functional groups and chemicals, based on MVC OO design principle
 * 
 * @author Michael Hinstridge & Chris Bates
 *
 */
public class Planktonica extends JPanel {
	
	private static final long serialVersionUID = 5356091519865535067L;

	/* Categories tab */
	protected final JTabbedPane catTab = new JTabbedPane();
	
	/* Display panes for the tabpane... its Func-y */
	protected Display funcView;
	protected Display chemView;
	/**
	 * Constructor for starting the Planktonica class
	 * @param jd - reference to the VEWController2 parent
	 * @param xmlFile - reference to the XMLFile of the model
	 */
	public Planktonica (VEWController2 jd, XMLFile xmlFile) {
		vc2 = jd;
	    parent = jd;
	    ee = new EquationEditor(vc2);
	    Model m = new Model(xmlFile);
		try {
			m.buildFromFile();
		} catch (BackingStoreException e) {
			System.err.println(e);
			new JDialog(jd, "XMLFile: " + xmlFile.getName() + " failed to load.");
			jd.dispose();
		}
		
	    funcView = new FunctionalDisplay(new FunctionalGroupController(m), catTab.getSize());
	    chemView = new ChemicalDisplay(new ChemicalController(m), catTab.getSize());
	    initialiseGUI();
	    parent.pack();
	}
	
	private void initialiseGUI() {
		setLayout(new BorderLayout(2, 2));
		
		catTab.addTab("Functional Groups", funcView);
		catTab.addTab("Chemicals", chemView);
		
		add(catTab);
	}

  /* The frames */

  private Importer importPage;
  public final DefaultListModel groupListModel = new DefaultListModel();
  public final JList groups = new JList(groupListModel);
  public final DefaultListModel instanceListModel = new DefaultListModel();
  public final JList instances = new JList(instanceListModel);
  public final DefaultListModel functionListModel = new DefaultListModel();
  public final JList functions = new JList(functionListModel);
  public JButton stageEditor = new JButton("Edit Stages");
  public MiniStageTableModel miniStageTableModel = new MiniStageTableModel();
  public JTable miniStageTable = new JTable(miniStageTableModel);
  public EquationPanel eqPanel = new EquationPanel("");
  public PigmentPanel pigPanel;
  public JScrollPane eqsScroller = new JScrollPane(eqPanel);
  public JEditorPane detailsHTML = new JEditorPane();
  public JPanel EqPigCard = new JPanel(new CardLayout());
  public VEWController2 vc2;

  public XMLFile xmlFile;

  /* Prepare helper pages */

  private EquationEditor ee = null;
  public AddVarPage addVarPage;
  private static final int INST_FGROUP = 0;
  private static final int INST_CHEMICAL = 1;
  private final JCheckBox predCheckBox = new JCheckBox("Mark as Top Predator");
  
  private final JButton editVar = new JButton("Edit Var");
  private final JButton upFunc = new JButton(new ImageIcon(IconRoot+ "up.gif"));
  private final JButton downFunc = new JButton(new ImageIcon(IconRoot+ "down.gif"));
  private final JButton upFG = new JButton(new ImageIcon(IconRoot+ "up.gif"));
  private final JButton downFG = new JButton(new ImageIcon(IconRoot+ "down.gif"));
  private final JButton addInstance = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
  private final JButton removeInstance = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
  private final JButton renameInstance = new JButton(new ImageIcon(IconRoot + "rename.gif"));
  private final JButton copyInstance = new JButton(new ImageIcon(IconRoot + "copy.gif"));
  private final JButton addFunction = new JButton(new ImageIcon(IconRoot+ "plus.gif"));
  private final JButton removeFunction = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
  private final JButton renameFunction = new JButton(new ImageIcon(IconRoot + "rename.gif"));
  private final JButton editFunction = new JButton(new ImageIcon(IconRoot + "edit.gif"));
  private final JButton copyFunction = new JButton(new ImageIcon(IconRoot + "copy.gif"));

  private static final String IconRoot = "Data"+File.separator+"Graphics"+File.separator+"icons"+File.separator;
  private static final String[] varOptions = new String[] { "local", "colvar", "parameter", "variable", "sysvar" };
  private final static String EQUATIONPANEL = "Equation Viewer";
  private final static String PIGMENTEDITOR = "Pigment Editor";
  private static String varNameString = "";
  
  private EventHandler eh = new EventHandler();

  private final JComboBox varList = new JComboBox();
  
  private String SelectedGroup = null;
  private XMLTag SelectedInstance = null;
  private XMLTag SelectedFunction = null;
  private JDialog parent;

  /*public Planktonica(JDialog jd, XMLFile xmlfile) {
	    vc2 = (VEWController2) jd;
	    parent = jd;
	    ee = new EquationEditor(vc2);
	    initialiseGUI();
	    xmlFile = xmlfile;
	    parent.pack();
	  }*/
  
  public boolean greenLight(boolean fix) {
	  
	return true;
	 /*
    if (xmlFile.getTag("kernel")!=null) xmlFile.getTag("kernel").removeFromParent();
    
    XMLTag[] fgs = xmlFile.getTags("functionalgroup");
    for (int i=0; i<fgs.length; i++) {
      XMLTag[] bits = fgs[i].getTags("fgvar");
      for (int j=0; j<bits.length; j++) {
        bits[j].removeFromParent();
      }
      bits = fgs[i].getTags("variable");
      for (int j=0; j<bits.length; j++) {
        if (bits[j].getValue("name").endsWith("$Pool")) {
          bits[i].removeFromParent();
        } else if (bits[j].getValue("name").equals("z")) {
          bits[i].removeFromParent();
        } else if (bits[j].getValue("name").endsWith("$Ingested")) {
          bits[j].removeFromParent();
        }
      }
      XMLTag[] funcs = fgs[i].getTags("function");
      for (int j=0; j<funcs.length; j++) {
        XMLTag[] fgvars = funcs[j].getTags("fgvar");
        for (int k=0; k<fgvars.length; k++) {
          fgvars[k].removeFromParent();
        }
        XMLTag[] sysvars = funcs[j].getTags("sysvar");
        for (int k=0; k<sysvars.length; k++) {
          sysvars[k].removeFromParent();
        }
        XMLTag[] equations = funcs[j].getTags("equation");
        for (int k=0; k<equations.length; k++) {
          if (equations[k].getTag("independentvar")!=null) equations[k].getTag("independentvar").removeFromParent();
          String eq = equations[k].getValue("eq");
          if (eq.indexOf("\\remineralise{")>=0) {
            eq = eq.substring(0,eq.indexOf("\\remineralise"))+"\\release{"+eq.substring(eq.indexOf("\\remineralise{")+14);
            equations[k].getTag("eq").setValue(eq);
          }
        }
      }
    }
    XMLTag[] chems = xmlFile.getTags("chemical");
    for (int i=0; i<chems.length; i++) {
      if ((chems[i].getTag("pigment")!=null) && (chems[i].getValue("pigment").equals("true"))) {
        XMLTag[] spectra = chems[i].getTags("spectrum");
        for (int j=0; j<spectra.length; j++) {
          if (spectra[j].getValue("name").equals("Heat Response")) {
            spectra[j].setValue("name",PigmentPanel.Pigment_CHI);
            spectra[j].getTag("equation").setValue("name",PigmentPanel.Pigment_CHI);
          } else if (spectra[j].getValue("name").equals("Photosynthesis Response")) {
            spectra[j].setValue("name",PigmentPanel.Pigment_e);
            spectra[j].getTag("equation").setValue("name",PigmentPanel.Pigment_e);
          } else if (spectra[j].getValue("name").equals(PigmentPanel.Pigment_CHI)) {
          } else if (spectra[j].getValue("name").equals(PigmentPanel.Pigment_e)) {
          } else spectra[j].removeFromParent();
        }
      }
    }
    if (fix) {
      eh.groupListHandler();
    }
    return true;
    */
  }
/*
  private void initialiseGUI() {
    setLayout(new BorderLayout(2, 2));
    pigPanel = new PigmentPanel(this);
    final JPanel topBoxes = new JPanel(new FlowLayout());
    final JPanel p1 = new JPanel(new BorderLayout());
    final JPanel p2 = new JPanel(new BorderLayout());
    final JPanel p3 = new JPanel(new BorderLayout());
    final JScrollPane jsp1 = new JScrollPane(groups);
    jsp1.setPreferredSize(new Dimension(120, 100));
    final JScrollPane jsp2 = new JScrollPane(instances);
    jsp2.setPreferredSize(new Dimension(195, 100));
    final JScrollPane jsp3 = new JScrollPane(functions);
    jsp3.setPreferredSize(new Dimension(295, 100));
    p1.add(jsp1, BorderLayout.CENTER);
    p1.add(new JLabel("Catagories"), BorderLayout.NORTH);
    p1.add(predCheckBox, BorderLayout.SOUTH);
    p2.add(jsp2, BorderLayout.CENTER);
    p2.add(new JLabel("Items"), BorderLayout.NORTH);
    p3.add(jsp3, BorderLayout.CENTER);
    p3.add(new JLabel("Functions"), BorderLayout.NORTH);
    predCheckBox.setEnabled(false);
    predCheckBox.addActionListener(eh);

    final JPanel ar2 = new JPanel(new FlowLayout());// new BorderLayout());
    final JPanel ar3 = new JPanel(new FlowLayout());// new BorderLayout());
    final JPanel ar4 = new JPanel(new FlowLayout());// new BorderLayout());
    ar2.setPreferredSize(new Dimension(195, 30));
    ar3.setPreferredSize(new Dimension(195, 30));
    ar4.setPreferredSize(new Dimension(195, 30));
    




    ar2.add(upFG);
    ar2.add(downFG);
    ar2.add(addInstance);
    ar2.add(renameInstance);
    ar2.add(removeInstance);
    ar2.add(copyInstance);
    ar3.add(upFunc);
    ar3.add(downFunc);
    ar3.add(addFunction);
    ar3.add(renameFunction);
    ar3.add(editFunction);
    ar3.add(removeFunction);
    ar3.add(copyFunction);
    p2.add(ar2, BorderLayout.SOUTH);
    p3.add(ar3, BorderLayout.SOUTH);
    topBoxes.add(p1);
    topBoxes.add(p2);
    topBoxes.add(p3);
    groupListModel.addElement("Functional Groups");
    groupListModel.addElement("Chemicals");

    // Ensure only one item can be selected at a time from the lists.
    groups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    instances.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    functions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    final JPanel varPanel = new JPanel(new BorderLayout());
    // varPanel.setPreferredSize(new Dimension(600,150));
    final JPanel varDetails = new JPanel(new BorderLayout());

    varDetails.add(detailsHTML, BorderLayout.SOUTH);
    detailsHTML.setContentType("text/html");
    detailsHTML.setText("<html><body></body></html>");
    detailsHTML.setEditable(false);
    detailsHTML.setPreferredSize(new Dimension(300, 100));
    varList.setPreferredSize(new Dimension(200, 20));
    varList.addItemListener(eh);
    editVar.setPreferredSize(new Dimension(80, 20));
    editVar.addActionListener(eh);
    JPanel varListPanel = new JPanel(new BorderLayout());
    varListPanel.add(varList, BorderLayout.WEST);
    varListPanel.add(editVar, BorderLayout.EAST);
    editVar.setEnabled(false);
    varDetails.add(varListPanel, BorderLayout.NORTH);
    varPanel.add(varDetails, BorderLayout.WEST);
    final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(stageEditor);
    miniStageTableModel.setColumnCount(2);
    miniStageTableModel.setColumnIdentifiers(new String[] {"Stage","Selected"});
    varPanel.add(buttonPanel, BorderLayout.CENTER);
    
    eqsScroller.setPreferredSize(new Dimension(750, 300));
    eqsScroller.setOpaque(false);
    eqsScroller.setBorder(BorderFactory.createLoweredBevelBorder());

    /* Centre and south of main page */
/*
    add(varPanel, BorderLayout.CENTER);

    Box boxPanel = Box.createVerticalBox();
    EqPigCard.add(eqsScroller, EQUATIONPANEL);
    EqPigCard.add(pigPanel, PIGMENTEDITOR);

    boxPanel.setPreferredSize(new Dimension(696, 300));
    EqPigCard.setPreferredSize(new Dimension(696, 300));
    eqsScroller.setPreferredSize(new Dimension(696, 300));
    boxPanel.add(EqPigCard);
    add(boxPanel, BorderLayout.SOUTH);
    copyFunction.setEnabled(false);
    editFunction.setEnabled(false);
    addInstance.setEnabled(false);
    removeInstance.setEnabled(false);
    copyInstance.setEnabled(false);
    renameInstance.setEnabled(false);
    predCheckBox.setEnabled(false);
    addFunction.setEnabled(false);
    removeFunction.setEnabled(false);
    renameFunction.setEnabled(false);
    upFG.setEnabled(false);
    upFunc.setEnabled(false);
    downFG.setEnabled(false);
    downFunc.setEnabled(false);
    groups.getSelectionModel().addListSelectionListener(eh);
    instances.getSelectionModel().addListSelectionListener(eh);
    functions.getSelectionModel().addListSelectionListener(eh);
    editFunction.addActionListener(eh);
    removeFunction.addActionListener(eh);
    addFunction.addActionListener(eh);
    upFunc.addActionListener(eh);
    upFG.addActionListener(eh);
    downFunc.addActionListener(eh);
    downFG.addActionListener(eh);
    copyFunction.addActionListener(eh);
    addInstance.addActionListener(eh);
    removeInstance.addActionListener(eh);
    renameInstance.addActionListener(eh);
    renameFunction.addActionListener(eh);
    copyInstance.addActionListener(eh);
    stageEditor.addActionListener(eh);
    addInstance.setPreferredSize(new Dimension(24, 24));
    removeInstance.setPreferredSize(new Dimension(24, 24));
    renameInstance.setPreferredSize(new Dimension(24, 24));
    copyInstance.setPreferredSize(new Dimension(24, 24));    
    addFunction.setPreferredSize(new Dimension(24, 24));
    upFunc.setPreferredSize(new Dimension(24, 24));
    downFunc.setPreferredSize(new Dimension(24, 24));
    upFG.setPreferredSize(new Dimension(24, 24));
    downFG.setPreferredSize(new Dimension(24, 24));
    removeFunction.setPreferredSize(new Dimension(24, 24));
    renameFunction.setPreferredSize(new Dimension(24, 24));
    editFunction.setPreferredSize(new Dimension(24, 24));
    copyFunction.setPreferredSize(new Dimension(24,24));

    add(topBoxes, BorderLayout.NORTH);

    importPage = new Importer(parent, this);
    addVarPage = new AddVarPage(parent);
    CardLayout CL = (CardLayout) EqPigCard.getLayout();
    eqPanel.setEquation("");
    CL.show(EqPigCard, PIGMENTEDITOR);
    CL.show(EqPigCard, EQUATIONPANEL);
  }
*/
  /*
   * Initialise the main screen with the boxes for FG/Chem/Pigment, Instances,
   * Functions and Subs.
   */

  public String lookupBaseType() {
    int ind = groups.getSelectedIndex();
    if (ind == 0)
      return "functionalgroup";
    else if (ind == 1)
      return "chemical";
    else
      return "";
  }

  public XMLTag getCurrentFunction() {
    return getCurrentInstance().getTagWhere("function", "name",
        (String) functions.getSelectedValue());
  }

  public XMLTag getCurrentInstance() {
    return xmlFile.getTag("model").getTagWhere(lookupBaseType(), "name",
        (String) instances.getSelectedValue());
  }

  public class EventHandler implements ActionListener, ItemListener,
      ListSelectionListener {

    public void valueChanged(ListSelectionEvent e) {
      if (!e.getValueIsAdjusting()) {
        if (e.getSource() == groups.getSelectionModel())
          groupListHandler();
        else if (e.getSource() == instances.getSelectionModel())
          instanceListHandler();
        else if (e.getSource() == functions.getSelectionModel())
          functionListHandler();
      }
    }

    public void itemStateChanged(ItemEvent e) {
      if (e.getSource() == varList) {
        if (varList.getSelectedItem() != null) {
          varNameString = varList.getSelectedItem().toString();
          XMLTag theFunction;
          theFunction = getCurrentFunction();
          XMLTag theVar = theFunction.getTagWhere("*", "name", varNameString);
          XMLTag group = getCurrentInstance();
          if (theVar == null) theVar = group.getTagWhere("*", "name", varNameString);
          detailsHTML.setText(VariableChooser.HTMLForVarHelper(varNameString,group, theVar, false));
          editVar.setEnabled(true);
          parent.pack();
        } else
          editVar.setEnabled(false);
      }
    }

    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == stageEditor) {
        StageSelection ss = new StageSelection(vc2,xmlFile);
        XMLTag fg = getCurrentInstance();
        ss.init(fg);
        ss.setVisible(true);
        
      } else if (e.getSource() == predCheckBox) {
      
        XMLTag fg = getCurrentInstance();
        if (fg.getTag("predator") == null)
          fg.addTag(new XMLTag("predator", "wait"));
        fg.getTag("predator").setValue("" + predCheckBox.isSelected());
        if (predCheckBox.isSelected()) {
          XMLTag sizeTag = fg.getTagWhere("variable", "name", "S_t");
          if (sizeTag == null) {
            sizeTag = new XMLTag("variable");
            sizeTag.addTag(new XMLTag("name", "S_t"));
            sizeTag.addTag(new XMLTag("desc", "Size of predator"));
            sizeTag.addTag(new XMLTag("codename", "SysVars.getPredSize()"));
            sizeTag.addTag(new XMLTag("value", "3"));
            sizeTag.addTag(new XMLTag("unit", "-3,m,1"));
            sizeTag.addTag(new XMLTag("hist", "1"));
            fg.addTag(sizeTag);
          }
          vc2.unsaved(true);
        }
      } else if (e.getSource() == upFunc) {
        int index = functions.getSelectedIndex();
        XMLTag theFG = getCurrentInstance();
        XMLTag t1 = theFG.getTag("function", index);
        XMLTag t2 = theFG.getTag("function", index + 1);
        t1.replace(theFG, "function", index + 1);
        t2.replace(theFG, "function", index);
        XMLTag[] funcs = theFG.getTags("function");
        functionListModel.removeAllElements();
        for (int i = 0; i < funcs.length; i++)
          functionListModel.addElement(funcs[i].getValue("name"));
        functions.setSelectedIndex(index - 1);
        upFunc.setEnabled(functions.getSelectedIndex() > 0);
        downFunc.setEnabled(functions.getSelectedIndex() < functionListModel
            .size() - 1);
        vc2.unsaved(true);

      } else if (e.getSource() == downFunc) {
        int index = functions.getSelectedIndex();
        XMLTag theFG = getCurrentInstance();
        XMLTag t1 = theFG.getTag("function", index + 1);
        XMLTag t2 = theFG.getTag("function", index + 2);
        t1.replace(theFG, "function", index + 2);
        t2.replace(theFG, "function", index + 1);
        XMLTag[] funcs = theFG.getTags("function");
        functionListModel.removeAllElements();
        for (int i = 0; i < funcs.length; i++)
          functionListModel.addElement(funcs[i].getValue("name"));
        functions.setSelectedIndex(index + 1);
        upFunc.setEnabled(functions.getSelectedIndex() > 0);
        downFunc.setEnabled(functions.getSelectedIndex() < functionListModel
            .size() - 1);
        vc2.unsaved(false);

      } else if (e.getSource() == upFG) {
        int index = instances.getSelectedIndex();
        String s = lookupBaseType();
        XMLTag t1 = xmlFile.getTag(s, index);
        if (t1.getDefaultValue("false", "invisible").equals("true")) {
          t1 = xmlFile.getTag(s, index - 1);
          XMLTag t2 = xmlFile.getTag(s, index + 1);
          t1.replace(xmlFile, s, index + 1);
          t2.replace(xmlFile, s, index - 1);
        } else {
          XMLTag t2 = xmlFile.getTag(s, index + 1);
          t1.replace(xmlFile, s, index + 1);
          t2.replace(xmlFile, s, index);
        }
        XMLTag[] items = xmlFile.getTags(s);
        instanceListModel.removeAllElements();
        for (int i = 0; i < items.length; i++)
          if (!items[i].getDefaultValue("false", "invisible").equals("true"))
            instanceListModel.addElement(items[i].getValue("name"));
        instances.setSelectedIndex(index - 1);
        upFG.setEnabled(instances.getSelectedIndex() > 0);
        downFG.setEnabled(instances.getSelectedIndex() < instanceListModel.size() - 1);
        vc2.unsaved(false);

      } else if (e.getSource() == downFG) {
        int index = instances.getSelectedIndex();
        String s = lookupBaseType();
        XMLTag t1 = xmlFile.getTag(s, index + 1);
        XMLTag t2 = xmlFile.getTag(s, index + 2);
        if (t2.getDefaultValue("false", "invisible").equals("true")) {
          t2 = xmlFile.getTag(s, index + 3);
          t1.replace(xmlFile, s, index + 3);
          t2.replace(xmlFile, s, index + 1);
        } else {
          t1.replace(xmlFile, s, index + 2);
          t2.replace(xmlFile, s, index + 1);
        }
        XMLTag[] items = xmlFile.getTags(s);
        instanceListModel.removeAllElements();
        for (int i = 0; i < items.length; i++)
          if (!items[i].getDefaultValue("false", "invisible").equals("true"))
            instanceListModel.addElement(items[i].getValue("name"));
        instances.setSelectedIndex(index + 1);
        upFG.setEnabled(instances.getSelectedIndex() > 0);
        downFG.setEnabled(instances.getSelectedIndex() < instanceListModel.size() - 1);
        vc2.unsaved(false);

        // } else if (e.getSource()==saveChanges) {
        // SaveStageSelection();
        // xmlFile.write();
        // saveChanges.setEnabled(false);

        // } else if (e.getSource()==closeButton) setVisible(false);

      } else if (e.getSource() == editVar) {
        XMLTag theFunction;
        theFunction = getCurrentFunction();
        
        varNameString = varList.getSelectedItem().toString();
        XMLTag group = getCurrentInstance();
        XMLTag theVar = theFunction.getTagWhere("*", "name", varNameString);
        XMLTag theVarTemp = null;
        if (theVar == null) theVar = group.getTagWhere("*", "name", varNameString);
        //if (theVar == null) theVar = xmlFile.getTag("model/kernel").getTagWhere("*", "name", varNameString);
        if (theVar == null) theVarTemp = xmlFile.getTag("model/kernel");
        if (theVarTemp != null) theVar = theVarTemp.getTagWhere("*", "name", varNameString);
        if (theVar != null) addVarPage.editThisVar(xmlFile.getTag("model"), theFunction, group, theVar, AddVarPage.ALL);
        theVar = theFunction.getTagWhere("*", "name", varNameString);
        if (theVar == null) theVar = group.getTagWhere("*", "name", varNameString);
        detailsHTML.setText(VariableChooser.HTMLForVarHelper(varNameString,group, theVar, false));
        vc2.unsaved(false);
        parent.pack();

      } else if (e.getSource() == addInstance) {
        int ind = groups.getSelectedIndex();
        if (ind == INST_FGROUP) {
          importPage.showImporter(Importer.FUNCTIONALGROUPS, xmlFile);
          groupListHandler();
          if (instances.getSelectedIndex() == -1) instances.setSelectedIndex(instances.getModel().getSize() - 1);
          XMLTag theFG = getCurrentInstance();
          if (theFG.getTagWhere("varietyconcentration", "name", "Ingestion") == null) {
            XMLTag newTag = new XMLTag("varietyconcentration");
            newTag.addTag(new XMLTag("name", "Ingestion"));
            newTag.addTag(new XMLTag("desc","Concentration of any variety that has been ingested"));
            newTag.addTag(new XMLTag("value", "0"));
            newTag.addTag(new XMLTag("hist", "1"));
            newTag.addTag(new XMLTag("unit", "0,c,1,0,m,-3"));
            theFG.addTag(newTag);
            newTag = new XMLTag("varietyvariable");
            newTag.setAttribute("link", "Ingestion");
            newTag.addTag(new XMLTag("name", "IngestedCells"));
            newTag.addTag(new XMLTag("desc","Number of cells ingested for any variety"));
            newTag.addTag(new XMLTag("value", "0"));
            newTag.addTag(new XMLTag("hist", "1"));
            newTag.addTag(new XMLTag("unit", "0,c,1"));
            theFG.addTag(newTag);
          }

        } else if (ind == INST_CHEMICAL) {
          importPage.showImporter(Importer.CHEMICALS, xmlFile);
          vc2.unsaved(true);
          groupListHandler();
        }
      } else if (e.getSource() == removeInstance) {
        String InstanceToRemove = (String) instances.getSelectedValue();
        if (JOptionPane.showConfirmDialog(Planktonica.this,"Really delete "+InstanceToRemove+"?")==JOptionPane.YES_OPTION) {
          XMLTag theInstance = xmlFile.getTag("model").getTagWhere(SelectedGroup,"name", InstanceToRemove);
          theInstance.removeFromParent();
          instanceListModel.removeElementAt(instances.getSelectedIndex());
          if (SelectedGroup.equals("chemical")) {
            if (xmlFile.getTag("model/kernel")!=null) {
              if (xmlFile.getTag("model/kernel").getTagWhere("colvar", "name",InstanceToRemove + "$ColBioConc") != null)
                xmlFile.getTag("model/kernel").getTagWhere("colvar", "name",InstanceToRemove + "$ColBioConc").removeFromParent();
              if (xmlFile.getTag("model/kernel").getTagWhere("colvar", "name",InstanceToRemove + "$ColConc") != null)
                xmlFile.getTag("model/kernel").getTagWhere("colvar", "name",InstanceToRemove + "$ColConc").removeFromParent();
              if (xmlFile.getTag("model/kernel").getTagWhere("colvar", "name",InstanceToRemove + "$ColTotal") != null)
                xmlFile.getTag("model/kernel").getTagWhere("colvar", "name",InstanceToRemove + "$ColTotal").removeFromParent();
            }
          }
          vc2.unsaved(true);
          eqPanel.setEquation("");
          eqPanel.repaint();
          eqsScroller.repaint();
        }

      } else if (e.getSource() == addFunction) {
        int ind = groups.getSelectedIndex();
        if (ind == 0) importPage.showImporter(Importer.FG_FUNCTIONS, xmlFile);
        else if (ind == 1) importPage.showImporter(Importer.CH_FUNCTIONS, xmlFile);
        instanceListHandler();
        vc2.unsaved(true);

      } else if (e.getSource() == editFunction) {
        XMLTag parentEq = getCurrentFunction();
        XMLTag[] tags = parentEq.getTags();
        int i = 0;
        ee.saveEqButton.setEnabled(false);
        ee.show(parentEq, getCurrentInstance(), xmlFile.getTag("model"));
        tags = parentEq.getTags();
        String eqlist = new String();
        for (i = 0; i < tags.length; i++)
          if (tags[i].getName().equals("equation"))
            eqlist += tags[i].getValue("eq") + "\\newline";
        eqPanel.setEquation(eqlist);
        eqPanel.repaint();
        eqsScroller.repaint();
        parent.pack();
        functionListHandler();
        vc2.unsaved(false);

      } else if (e.getSource() == removeFunction) {
        String funcName = functions.getSelectedValue().toString();
        if (JOptionPane.showConfirmDialog(Planktonica.this,"Really delete "+funcName+"?")==JOptionPane.YES_OPTION) {
          getCurrentInstance().getTagWhere("function", "name",funcName).removeFromParent();
          functionListModel.removeElementAt(functions.getSelectedIndex());
          functions.clearSelection();
          removeFunction.setEnabled(false);
          renameFunction.setEnabled(false);
          editFunction.setEnabled(false);
          vc2.unsaved(true);
          eqPanel.setEquation("");
          eqPanel.repaint();
          eqsScroller.repaint();
        }

      } else if (e.getSource() == renameInstance) {
        String OldName = (String) instances.getSelectedValue();
        String NewName = JOptionPane.showInputDialog("Please enter the new name for " + OldName, OldName);
        while (NewName != null) {
          if (instanceListModel.indexOf(NewName) == -1) {
            int OldSelection = instances.getSelectedIndex();
            instanceListModel.setElementAt(NewName, OldSelection);
            xmlFile.getTagWhere(lookupBaseType(), "name", OldName).setValue("name", NewName);
            instances.ensureIndexIsVisible(OldSelection);
            instances.setSelectedIndex(OldSelection);
            vc2.unsaved(true);
            instanceListHandler();
            NewName = null;
          } else
            NewName = JOptionPane.showInputDialog("Sorry " + NewName
                + " is already taken.\nPlease enter a different name for " + OldName, NewName);
        }
      } else if (e.getSource() == copyInstance) {
        String OldName = (String) instances.getSelectedValue();
        String NewName = JOptionPane.showInputDialog("Please enter the name for the copy of " + OldName, OldName);
        while (NewName != null) {
          if (instanceListModel.indexOf(NewName) == -1) {
            XMLTag theClone = (XMLTag) xmlFile.getTagWhere(lookupBaseType(), "name", OldName).clone();
            theClone.setValue("name",NewName);
            xmlFile.addTag(theClone);
            instanceListModel.addElement(NewName);
            instances.setSelectedIndex(instanceListModel.size()-1);
            instances.ensureIndexIsVisible(instanceListModel.size()-1);
            vc2.unsaved(true);
            instanceListHandler();
            NewName = null;
          } else
            NewName = JOptionPane.showInputDialog("Sorry " + NewName + " is already taken.\nPlease enter a different name",NewName);
        }
      } else if (e.getSource() == renameFunction) {
        String OldName = (String) functions.getSelectedValue();
        String NewName = JOptionPane.showInputDialog("Please enter the new name for " + OldName, OldName);
        while (NewName != null) {
          if (functionListModel.indexOf(NewName) == -1) {
            int OldSelection = functions.getSelectedIndex();
            functionListModel.setElementAt(NewName, OldSelection);
            getCurrentInstance().getTagWhere("function", "name", OldName).setValue("name", NewName);
            functions.ensureIndexIsVisible(OldSelection);
            functions.setSelectedIndex(OldSelection);
            vc2.unsaved(true);
            functionListHandler();
            NewName = null;
          } else
            NewName = JOptionPane.showInputDialog("Sorry " + NewName+ " is already taken.\nPlease enter a different name for "+ OldName, NewName);
        }
      } else if (e.getSource() == copyFunction) {
        String OldName = (String) functions.getSelectedValue();
        String NewName = JOptionPane.showInputDialog("Please enter the name for the copy of " + OldName, OldName);
        while (NewName != null) {
          if (functionListModel.indexOf(NewName) == -1) {
            XMLTag newFunction = (XMLTag) getCurrentInstance().getTagWhere("function","name",OldName).clone();
            newFunction.setValue("name",NewName);
            getCurrentInstance().addTag(newFunction);
            functionListModel.addElement(NewName);
            functions.setSelectedIndex(functionListModel.size()-1);
            functions.ensureIndexIsVisible(functionListModel.size()-1);
            functionListHandler();
            vc2.unsaved(true);
            NewName=null;
          } else NewName = JOptionPane.showInputDialog("Sorry " + NewName+ " is already taken.\nPlease enter a different name");
        }
      }
    }



    public void groupListHandler() {
      int SelectedIndex = groups.getSelectedIndex();
      if (SelectedIndex != -1) {
        if (SelectedIndex == 0) SelectedGroup = "functionalgroup";
        else if (SelectedIndex == 1) SelectedGroup = "chemical";
        else SelectedGroup = null;


        // Set the tool tip text for the add instance button, and enable the button.
        if (SelectedIndex == 0) addInstance.setToolTipText("Add a new functional group.");
        else addInstance.setToolTipText("Add a new " + SelectedGroup + ".");
        addInstance.setEnabled(true);

        // Populate the instance list.
        instanceListModel.removeAllElements();
        XMLTag[] Tags = xmlFile.getTags(SelectedGroup);
        for (int i = 0; i < Tags.length; i++) instanceListModel.addElement(Tags[i].getValue("name"));
        instances.setEnabled(true);

        // Clear the equation panel and redraw everything.
        eqPanel.setEquation("");
        eqPanel.repaint();
        eqsScroller.repaint();
      } else {
        SelectedGroup = null;
        addInstance.setEnabled(false);
        addInstance.setToolTipText(null);
      }
      CardLayout CL = (CardLayout) EqPigCard.getLayout();
      CL.show(EqPigCard, EQUATIONPANEL);
      stageEditor.setEnabled(groups.getSelectedIndex()==INST_FGROUP && instances.getSelectedIndices().length==1);
    }

    public void instanceListHandler() {
      int SelectedIndex = instances.getSelectedIndex();
      if (SelectedIndex != -1) {
        SelectedInstance = xmlFile.getTag("model").getTagWhere(SelectedGroup,
            "name", (String) instances.getSelectedValue());
        upFG.setEnabled(SelectedIndex > 0);
        downFG.setEnabled(SelectedIndex < instanceListModel.size() - 1);
        removeInstance.setEnabled(true);
        copyInstance.setEnabled(true);
        renameInstance.setEnabled(true);
        addFunction.setEnabled(true);

        // Set tool tip text for the relevant buttons.
        String InstanceName = SelectedInstance.getValue("name");
        predCheckBox.setEnabled(SelectedGroup.equals("functionalgroup"));
        XMLTag predTag = SelectedInstance.getTag("predator");
        predCheckBox.setSelected((predTag != null) && (predTag.getValue().equals("true")));
        upFG.setToolTipText("Move " + InstanceName + " up.");
        downFG.setToolTipText("Move " + InstanceName + " down.");
        removeInstance.setToolTipText("Remove " + InstanceName + ".");
        copyInstance.setToolTipText("Make copy of " + InstanceName + ".");
        
        renameInstance.setToolTipText("Rename " + InstanceName + ".");
        addFunction.setToolTipText("Add a new function to " + InstanceName + ".");

        // Reset the variable list.
        varList.removeAllItems();
        detailsHTML.setText("<html></html>");

        String[] Strings;
        functionListModel.removeAllElements();
        // Create the vector of functions with which to populate the function
        // list, then do it and enable the list.
        Strings = SelectedInstance.getValues("function/name");
        for (int i = 0; i < Strings.length; i++)
          functionListModel.addElement(Strings[i]);

        functions.setEnabled(true);


        if (SelectedGroup.equals("chemical")) {
          CardLayout CL = (CardLayout) EqPigCard.getLayout();
          CL.show(EqPigCard, PIGMENTEDITOR);
          pigPanel.show(SelectedInstance, xmlFile);
        }

        // Clear the equation panel then redraw everything.
        eqPanel.setEquation("");
        eqPanel.repaint();
        eqsScroller.repaint();
      } else {
        SelectedInstance = null;
        functionListModel.removeAllElements();
        functions.setEnabled(false);
        varList.removeAllItems();
        detailsHTML.setText("<html></html>");
        upFG.setEnabled(false);
        downFG.setEnabled(false);
        removeInstance.setEnabled(false);
        predCheckBox.setEnabled(false);
        renameInstance.setEnabled(false);
        copyInstance.setEnabled(false);
        addFunction.setEnabled(false);
        upFG.setToolTipText(null);
        downFG.setToolTipText(null);
        removeInstance.setToolTipText(null);
        renameInstance.setToolTipText(null);
        copyInstance.setToolTipText(null);
      }
      stageEditor.setEnabled(groups.getSelectedIndex()==INST_FGROUP && instances.getSelectedIndices().length==1);
    }

    public void functionListHandler() {
      int SelectedIndex = functions.getSelectedIndex();
      if (SelectedIndex != -1) {

        // Enable the relevant function buttons.
        upFunc.setEnabled(SelectedIndex > 0);
        downFunc.setEnabled(SelectedIndex < functionListModel.size() - 1);
        editFunction.setEnabled(true);
        removeFunction.setEnabled(true);
        copyFunction.setEnabled(true);
        renameFunction.setEnabled(true);

        // Find out what's been selected
        SelectedFunction = SelectedInstance.getTagWhere("function", "name",
            (String) functions.getSelectedValue());

        String FunctionName = SelectedFunction.getValue("name");
        upFunc.setToolTipText("Move " + FunctionName + " up.");
        downFunc.setToolTipText("Move " + FunctionName + " down.");
        editFunction.setToolTipText("Edit " + FunctionName + ".");
        removeFunction.setToolTipText("Remove " + FunctionName + ".");
        renameFunction.setToolTipText("Rename " + FunctionName + ".");
        copyFunction.setToolTipText("Make copy of " + FunctionName + ".");

        varList.removeAllItems();
        detailsHTML.setText("<html></html>");
        XMLTag[] tags = SelectedFunction.getTags();
        Vector params = new Vector();

        for (int i = 0; i < tags.length; i++)
          if (StringTools.memberOf(tags[i].getName(), varOptions))
            StringTools.addInOrder(params, tags[i].getValue("name"));

        int eqNo = 1;
        XMLTag eq = SelectedFunction.getTag("equation", eqNo);

        while ((eq != null) && (eq.getValue("eq") != null)) {
          String eqs = eq.getValue("eq");
          while (eqs.indexOf("\\var{") >= 0) {
            eqs = eqs.substring(eqs.indexOf("\\var{") + 5);
            String eqt = eqs.substring(0, eqs.indexOf('}'));
            boolean found = false;
            int pNo = 0;
            while ((!found) && (pNo < params.size())) {
              found = (((String) params.elementAt(pNo++)).equals(eqt));
            }
            if (!found) StringTools.addInOrder(params, eqt);
            eqs = eqs.substring(eqs.indexOf('}'));
          }
          eq = SelectedFunction.getTag("equation", eqNo++);
        }

        for (int i = 0; i < params.size(); i++)
          varList.addItem(params.elementAt(i));

        // Set up the equation panel.
        String eqlist = new String();
        for (int i = 0; i < tags.length; i++)
          if (tags[i].getName().equals("equation"))
            eqlist += tags[i].getValue("eq") + "\\newline";
        eqPanel.setEquation(eqlist);

        // Ensure everything is redrawn.
        eqPanel.repaint();
        eqsScroller.repaint();
        varList.repaint();
        parent.pack();

        // Ensure the equation panel is on display.
        CardLayout CL = (CardLayout) EqPigCard.getLayout();
        CL.show(EqPigCard, EQUATIONPANEL);
      } else {
        SelectedFunction = null;
        upFunc.setEnabled(false);
        downFunc.setEnabled(false);
        removeFunction.setEnabled(false);
        renameFunction.setEnabled(false);
        copyFunction.setEnabled(false);
        editFunction.setEnabled(false);
        upFunc.setToolTipText(null);
        downFunc.setToolTipText(null);
        editFunction.setToolTipText(null);
        removeFunction.setToolTipText(null);
        renameFunction.setToolTipText(null);
        copyFunction.setToolTipText(null);
      }
    }
  }
  
  class MiniStageTableModel extends DefaultTableModel {
    public Class getColumnClass(int column) {
      if (column==1) return Boolean.class;
      else return String.class;
    }
  }

}
