package VEW.Scenario2;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.*;
import javax.swing.tree.*;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class EquationEditor extends JDialog {
  
  //private static final StringTools StringTools = StringTools.getInstance();
  private EquationPanel theEqPanel = new EquationPanel("");
  private XMLTag newFuncTag = null; // parent to update
  private final JPanel NSpacer = new JPanel();
  private final JPanel SSpacer = new JPanel();
  private final JPanel WSpacer = new JPanel();
  private final JPanel ESpacer = new JPanel();
  private int lockEvents = 0;

  private final String IconRoot = "Data/Graphics/icons/";

  /* Components for unary elements */
  
  private final JPanel unaryPanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton unaryTitle = new JToggleButton("Eq Type");
  private final JToggleButton unaryTerm = new JToggleButton("Term");
  private final JButton unaryExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif"));
  
  /* Components for binary elements */
  
  private final JPanel binaryPanel = new JPanel(new BorderLayout());
  private final JToggleButton binaryTitle = new JToggleButton("Eq Type");
  private final JToggleButton binaryLHS = new JToggleButton("LHS");
  private final JToggleButton binaryRHS = new JToggleButton("RHS");
  private final JButton binaryExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif"));
  private final JButton binarySwap = new JButton(new ImageIcon(IconRoot + "swap.gif"));

  /* Components for multi elements */

  private final JPanel multiPanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton multiTitle = new JToggleButton("Eq Type");
  private final JList multiList = new JList();

  private final JButton multiExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif")); 
  private final JButton multiRemove = new JButton(new ImageIcon(IconRoot + "bin1.gif"));

  /* Components for the ifswitch panel */

  private final JPanel ifswitchPanel = new JPanel(new BorderLayout());
  private final JToggleButton ifButton = new JToggleButton("If");
  private final JToggleButton thenButton = new JToggleButton("Then =");
  private final JToggleButton elseButton = new JToggleButton("Else =");
  private final JButton swapIfButton = new JButton(new ImageIcon(IconRoot + "swap.gif"));
  private final JButton ifswitchExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif"));
 
  /* Components for the Variable panel */

  private final JPanel varPanel = new JPanel(new FlowLayout());
  private final JToggleButton varButton = new JToggleButton("Var");
  private final JButton varNameButton = new JButton("Name");

  /* Components for the Valuepanel */

  private final JPanel valPanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton valButton = new JToggleButton("Val");
  private final JButton lessValExp = new JButton("<");
  private final JButton moreValExp = new JButton(">");
  private final JLabel valExp = new JLabel("0");
  private final JButton valUnitButton = new JButton("Unit");

  private int valExpInt = 0;
  private final JTextField valMain = new JTextField();
  private EquationPanel valUnit = new EquationPanel("");
  private String valUnitString = "";
  private boolean dontUpdateVal = false; // To protect from an unwanted event trigger 

  /* Components for the Equation Selector */

  private final JButton addEquation = new JButton(new ImageIcon(IconRoot + "plus.gif"));
  private final JButton removeEquation = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
  private final JButton upEquation = new JButton(new ImageIcon(IconRoot + "up.gif"));
  private final JButton downEquation = new JButton(new ImageIcon(IconRoot + "down.gif"));
  private final JButton renameEquation = new JButton(new ImageIcon(IconRoot + "rename.gif"));
  private final JButton copyEquation = new JButton(new ImageIcon(IconRoot + "copy.gif"));  
  private final JButton unitCheck = new JButton(new ImageIcon(IconRoot + "unit.gif"));
  private JList eqJList;
  private int lastEqSelected=-1;
  
  /* Components for the Choose Equation-Type Panel */

  private final JPanel eqTypePanel = new JPanel(new BorderLayout(2,2));
  private final JLabel labChooseEq = new JLabel("Choose Rule Type Above");
  
  /* Components for the Reproduce Panel */

  private final JPanel rePanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton reTitle = new JToggleButton();
  private final JList reList = new JList();
  private final JButton reExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif"));
  private final JButton reRemove = new JButton(new ImageIcon(IconRoot + "bin1.gif"));
  private final JComboBox reproduceStateList  = new JComboBox();
  private final JLabel reOffspringLabel = new JLabel("Offspring count");
  private final JToggleButton reOffspring = new JToggleButton();

  /* Components for the Remineralise/Uptake Panel */
  
  private final JPanel chemPanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton chemFunction = new JToggleButton("Release");
  private final JToggleButton chemAmount = new JToggleButton();
  private final JComboBox chemDropDown = new JComboBox();
  private final JButton chemExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif"));

  /* Components for the While/Do Panel */

  private final JPanel whilePanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton whileButton = new JToggleButton("While");
  private final JToggleButton condition = new JToggleButton("? Bool");
  private final JList statList = new JList();
  private final JButton statExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif"));
  private final JButton remStat = new JButton(new ImageIcon(IconRoot + "bin1.gif"));

  /* The unknown component panel */

  private final JPanel ucPanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton ucButton = new JToggleButton();
  private final JLabel ucLabel = new JLabel("Choose an item from the above list.");
  
  /* Components for the Element/Operator Selector */

  private JList opJList;
  private final JPanel optionsView = new JPanel(new BorderLayout(0,0));
  private final JButton addOp = new JButton(new ImageIcon(IconRoot + "plus.gif"));
  private final JButton replaceOp = new JButton(new ImageIcon(IconRoot + "replace.gif")); 
  private final JButton surroundOp = new JButton(new ImageIcon(IconRoot + "surround.gif"));
  private final JButton unSurroundOp = new JButton(new ImageIcon(IconRoot + "unsurround.gif"));
  private Vector eqList = new Vector();
  private Vector eqNames = new Vector();
  private XMLTag currentEquation;
  private String currentEqString;

  /* Components for the equation tree viewer and the rest.*/

  private DefaultMutableTreeNode equationNode = new DefaultMutableTreeNode();
  private JTree equationTree = new JTree(equationNode); 
  private JScrollPane treeJsp;
  private final JButton backEqButton = new JButton("Back");
  public final JButton saveEqButton = new JButton("Save Changes");

  /* Components for the ingestion panel */
  
  private final JPanel ingPanel = new JPanel(new BorderLayout());
  private JToggleButton ingTitle = new JToggleButton("ingest");
  private JButton ingFoodSet = new JButton("? Var");
  private JToggleButton ingThresh = new JToggleButton("? Num");
  private JToggleButton ingRate= new JToggleButton("? Num");
  private final JButton ingExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif"));
  
  private static VariableChooser varChooser = null;
  private static int editingMode = 0; 
  private static final int NONE = -1;
  private static final int MULTI = 1;
  private static final int BINARY = 2;
  private static final int IFSWITCH = 3;
  private static final int UNARY = 4;
  private static final int NUMERICAL = 5;
  private static final int BOOLEAN = 6;
  private static final int FUNCTION = 7;
  private static final int VARONLY = 8;
  private static final int VALONLY = 9;
  private static final int REPRODUCE= 10;
  private static final int ONLY_OPTION = 11;
  private static final int ASSIGN_ONLY = 12;
  private static final int WHILE = 13;
  private static final int CHANGE_CHANCE = 14;
  private static final int CHANGE_STATE = 15;
  private static final int REMIN_UPTAKE = 16;
  private static final int INGESTION = 17;


  private XMLTag originalFuncTag = null;
  private XMLTag originalGroupTag = null;
  private XMLTag theModel = null;
  
 /* Components for the StateChange panel */

  private final JPanel        changeStatePanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton changeStateTitle = new JToggleButton("change");
  private final JComboBox     changeStateList  = new JComboBox();

  /* Components for the ChangeChance panel */

  private final JPanel        changeChancePanel = new JPanel(new BorderLayout(2,2));
  private final JToggleButton changeChanceTitle = new JToggleButton("pchange");
  private final JComboBox     changeChanceList  = new JComboBox();
  private final JToggleButton changeChanceValue = new JToggleButton();
  private final JButton       changeChanceExpand = new JButton(new ImageIcon(IconRoot + "zoomin.gif")); 
  private UnitEditor theUnitEditor = null;
  private EventHandler eh = new EventHandler();
  // Construct the frame
  
  public EquationEditor(JFrame jf) {
    super(jf,"Rule Editor",true);
    initialiseMe();
  }
  
  public EquationEditor(JDialog jd) {
    super(jd,"Rule Editor",true);
    initialiseMe();
  }
  
  private void initialiseMe() {
    theUnitEditor = new UnitEditor(this);    
    varChooser = new VariableChooser(this);
    getContentPane().setLayout(new BorderLayout(2,2));
    setSize(600,600);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //window.setSize(imgIcon.getIconWidth() + 40, imgIcon.getIconHeight() + 70 + title.getHeight());
    setLocation(screenSize.width/2 - (600/2),
                screenSize.height/2 - (600/2));
    
    opJList = new JList();
    opJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane opJsp = new JScrollPane(opJList);
    opJsp.setPreferredSize(new Dimension(300,150));
    setOpListContents(NONE);
    
    eqJList = new JList();
    JScrollPane eqJsp = new JScrollPane(eqJList);
    eqJsp.setPreferredSize(new Dimension(300,150));
    JPanel topLeftPanel = new JPanel(new BorderLayout(2,2));
    topLeftPanel.add(eqJsp,"North");
    
    JLabel fillerLabel = new JLabel("   ");
    
  //  iVarDropDownPanel.add(iVarDropDownLabel);
  //  iVarDropDownPanel.add(iVarDropDown);
    
    final JPanel eqButtonPanel = new JPanel(new FlowLayout());
    eqButtonPanel.add(upEquation);
    eqButtonPanel.add(downEquation);
    eqButtonPanel.add(addEquation);
    eqButtonPanel.add(removeEquation);
    eqButtonPanel.add(renameEquation);
    eqButtonPanel.add(copyEquation);
    eqButtonPanel.add(unitCheck);
    
    JPanel otherButtonPanel = new JPanel(new FlowLayout());
    otherButtonPanel.add(fillerLabel);    
 //   otherButtonPanel.add(iVarDropDownPanel);
    
    
    upEquation.setPreferredSize(new Dimension(32,32));
    downEquation.setPreferredSize(new Dimension(32,32));
    addEquation.setPreferredSize(new Dimension(32,32));
    removeEquation.setPreferredSize(new Dimension(32,32));
    renameEquation.setPreferredSize(new Dimension(32,32));
    copyEquation.setPreferredSize(new Dimension(32,32));
    unitCheck.setPreferredSize(new Dimension(32,32));
    
    topLeftPanel.add(eqButtonPanel,BorderLayout.CENTER);
    topLeftPanel.add(otherButtonPanel, BorderLayout.SOUTH);
    
   // iVarDropDown.addItemListener(new EventHandler(EventHandler.IVar));
    upEquation.addActionListener(eh);
    downEquation.addActionListener(eh);
    JPanel topRightPanel = new JPanel(new BorderLayout(2,2));
    
    topRightPanel.add(opJsp,BorderLayout.NORTH);
    
    
    addOp.setPreferredSize(new Dimension(32,32));
    replaceOp.setPreferredSize(new Dimension(32,32));
    surroundOp.setPreferredSize(new Dimension(32,32));
    unSurroundOp.setPreferredSize(new Dimension(32,32));
    
    final JPanel opButtonPanel = new JPanel(new FlowLayout());
    
    opButtonPanel.add(addOp);
    opButtonPanel.add(replaceOp);
    opButtonPanel.add(surroundOp);
    opButtonPanel.add(unSurroundOp);
    
    topRightPanel.add(opButtonPanel,BorderLayout.CENTER);
    
    addOp.setEnabled(false);
    
    replaceOp.setEnabled(false);
    surroundOp.setEnabled(false);
    unSurroundOp.setEnabled(false);
    
    getContentPane().add(topLeftPanel,BorderLayout.WEST);
    
    getContentPane().add(topRightPanel,BorderLayout.EAST);
    
    JPanel lowerTwoThirds = new JPanel(new BorderLayout(2,2));
    equationTree.setLargeModel(true);
    equationTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    treeJsp = new JScrollPane(equationTree);
    treeJsp.setPreferredSize(new Dimension(300,320));
    optionsView.setBorder(new BevelBorder(BevelBorder.RAISED));
    optionsView.setPreferredSize(new Dimension(300,320));
    
    lowerTwoThirds.add(treeJsp,BorderLayout.WEST);
    
    lowerTwoThirds.add(optionsView,BorderLayout.EAST);
    
    JPanel eqAndButtons = new JPanel(new BorderLayout());
    JScrollPane eqScroller = new JScrollPane(theEqPanel);
    eqScroller.setPreferredSize(new Dimension(600,100));
    
    eqAndButtons.add(eqScroller,BorderLayout.NORTH);
    JPanel buttonPanel = new JPanel(new BorderLayout());
    buttonPanel.setPreferredSize(new Dimension(600,30));
    buttonPanel.add(backEqButton, BorderLayout.WEST);   
    buttonPanel.add(saveEqButton, BorderLayout.EAST);
    eqAndButtons.add(buttonPanel,BorderLayout.SOUTH);
    lowerTwoThirds.add(eqAndButtons,BorderLayout.SOUTH);
    getContentPane().add(lowerTwoThirds,BorderLayout.SOUTH);
    
    updateButtons();    
    
    pack();

    /* Now the different panels on the right of the tree view */

    /* Particular ones for... Binary functions, mult-param functions, */
    /* the if-switch, and reproduce parameters. */

    /* First, the panel for binary equations */
    
    final JPanel titleBinaryPanel = new JPanel(new FlowLayout());
    titleBinaryPanel.add(binaryTitle);
    final JPanel middleBinaryPanel = new JPanel(new FlowLayout());
    middleBinaryPanel.add(binaryLHS);
    middleBinaryPanel.add(binarySwap);
    middleBinaryPanel.add(binaryRHS);
    final JPanel magBinaryPanel = new JPanel(new FlowLayout());
    magBinaryPanel.add(binaryExpand);
    
    binaryTitle.addActionListener(eh);
    binaryLHS.addActionListener(eh);  
    binaryRHS.addActionListener(eh);  
    binaryExpand.addActionListener(eh);
    binarySwap.addActionListener(eh);
    
    binaryPanel.add(titleBinaryPanel,BorderLayout.NORTH);
    binaryPanel.add(middleBinaryPanel,BorderLayout.CENTER);
    binaryPanel.add(magBinaryPanel,BorderLayout.SOUTH);
    
    /* Second, the panel for multi equations */

    final JScrollPane multiScroll = new JScrollPane(multiList);
    multiScroll.setPreferredSize(new Dimension(240,240));
    multiTitle.setPreferredSize(new Dimension(280,26));
    multiPanel.add(multiTitle,BorderLayout.NORTH);
    multiPanel.add(multiScroll,BorderLayout.CENTER);
    multiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    final JPanel lowPanel = new JPanel(new GridLayout(1,0));
    lowPanel.add(multiRemove);
    multiRemove.setEnabled(false);
    lowPanel.add(multiExpand);
    multiPanel.add(lowPanel,BorderLayout.SOUTH);

    
    /* Click on an item in the list */
    multiList.addListSelectionListener(eh);
    multiTitle.addActionListener(eh);
    multiExpand.addActionListener(eh);
    multiRemove.addActionListener(eh);
       
    /* Third, the if-switch panel. */
    
    JPanel ifSwitchGrid = new JPanel(new GridLayout(4,2));
    ifSwitchGrid.add(new JLabel("IF   ",JLabel.RIGHT));
    ifSwitchGrid.add(ifButton);
    ifSwitchGrid.add(new JLabel("THEN   ",JLabel.RIGHT));
    ifSwitchGrid.add(thenButton);
    ifSwitchGrid.add(new JLabel("ELSE   ",JLabel.RIGHT));
    ifSwitchGrid.add(elseButton);
    ifSwitchGrid.add(new JLabel(""));
    ifSwitchGrid.add(ifswitchExpand);
    
    ifswitchPanel.add(ifSwitchGrid,BorderLayout.CENTER);
    
    JPanel swapPanel = new JPanel(new FlowLayout());
    swapPanel.add(swapIfButton);
    ifswitchPanel.add(swapPanel,BorderLayout.EAST);
    
    ifButton.addActionListener(eh);
    thenButton.addActionListener(eh);
    elseButton.addActionListener(eh);
    ifButton.addActionListener(eh);
    ifswitchExpand.addActionListener(eh);
    swapIfButton.addActionListener(eh);
   
    /* Fourthly, the unary element editor */

    unaryTitle.setPreferredSize(new Dimension(100,20));
    unaryTerm.setPreferredSize(new Dimension(100,20));
    unaryPanel.add(unaryTitle,BorderLayout.WEST);
    unaryPanel.add(unaryTerm,BorderLayout.EAST);
    unaryPanel.add(unaryExpand,BorderLayout.SOUTH);
    unaryTitle.addActionListener(eh);
    unaryTerm.addActionListener(eh);
    unaryExpand.addActionListener(eh);

    /* 5. Quick instruction panel for choosing equation */
    /* (Just a label) */

    eqTypePanel.add(labChooseEq,BorderLayout.CENTER);
    
    /* 6. Reproduce panel */

    rePanel.add(reTitle,"North");
    reList.setPreferredSize(new Dimension(150,150));
    reList.setBorder(new BevelBorder(BevelBorder.RAISED));
    reList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JPanel reSPanel = new JPanel(new BorderLayout(2,2));
    JPanel reTopPanel = new JPanel(new BorderLayout(2,2));
    reTopPanel.add(reproduceStateList,BorderLayout.NORTH);
    reTopPanel.add(reOffspringLabel,BorderLayout.WEST);
    reTopPanel.add(reOffspring,BorderLayout.EAST);
    reSPanel.add(reTopPanel,BorderLayout.NORTH);
    reSPanel.add(reList,BorderLayout.CENTER);
    JPanel reREPanel = new JPanel(new GridLayout(1,0));
    reREPanel.add(reRemove,BorderLayout.WEST);
    reREPanel.add(reExpand,BorderLayout.EAST);
    reSPanel.add(reREPanel,BorderLayout.SOUTH);
    rePanel.add(reSPanel,BorderLayout.SOUTH);
    reTitle.addActionListener(eh);
    reExpand.addActionListener(eh);
    reList.addListSelectionListener(eh);
    reRemove.addActionListener(eh);
    reOffspring.addActionListener(eh);
    reproduceStateList.addActionListener(eh);

    /* 8. The "? Val" Panel !! Just a single button */
    
    ucPanel.add(ucButton,BorderLayout.CENTER);
    ucPanel.add(ucLabel,BorderLayout.NORTH);
    ucLabel.setHorizontalAlignment(SwingConstants.CENTER);
    ucButton.addActionListener(eh);
    
    /* 9. The Value Panel */

    valPanel.add(valButton,BorderLayout.NORTH);
    valPanel.add(valMain,BorderLayout.WEST);
    valMain.setPreferredSize(new Dimension(60,20));
    JLabel expBit = new JLabel("x 10^");
    expBit.setHorizontalAlignment(SwingConstants.CENTER);
    expBit.setPreferredSize(new Dimension(60,20));
    valPanel.add(expBit,BorderLayout.CENTER);
    JPanel expPanel = new JPanel(new BorderLayout(2,2));
    expPanel.add(lessValExp,BorderLayout.WEST);
    expPanel.add(moreValExp,BorderLayout.EAST);
    expPanel.add(valExp,BorderLayout.CENTER);
    valExp.setPreferredSize(new Dimension(20,20));
    valExp.setHorizontalAlignment(SwingConstants.CENTER);
    valPanel.add(expPanel,BorderLayout.EAST);
    JPanel valUnitPanel = new JPanel(new BorderLayout(2,2));
    valUnitPanel.add(valUnit,BorderLayout.EAST);
    valUnitPanel.add(valUnitButton,BorderLayout.WEST);
    valPanel.add(valUnitPanel,BorderLayout.SOUTH);
    valUnitButton.addActionListener(eh);
    moreValExp.addActionListener(eh);
    lessValExp.addActionListener(eh);
    valButton.addActionListener(eh);
    valMain.addCaretListener(eh);
    valMain.addKeyListener(eh);

    /* 10. The While/Do Panel */
    
    whilePanel.add(whileButton,BorderLayout.WEST);
    whilePanel.add(condition,BorderLayout.EAST);
    whileButton.setPreferredSize(new Dimension(90,20));
    condition.setPreferredSize(new Dimension(90,20));
    statExpand.setPreferredSize(new Dimension(32,32));
    final JPanel lowerPanel = new JPanel(new BorderLayout(2,2));
    final JScrollPane statScroll = new JScrollPane(statList);
    statList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    statScroll.setPreferredSize(new Dimension(240,220));
    lowerPanel.add(statScroll,BorderLayout.NORTH);
    lowerPanel.add(statExpand,BorderLayout.CENTER);
    lowerPanel.add(remStat,BorderLayout.EAST);
    remStat.setEnabled(false);
    statExpand.setEnabled(false);
    whilePanel.add(lowerPanel,BorderLayout.SOUTH);
    
    whileButton.addActionListener(eh);
    condition.addActionListener(eh);
    statList.addListSelectionListener(eh);
    statExpand.addActionListener(eh);
    remStat.addActionListener(eh);
    
    /* 11. changeChance(State,Numerical) */

    changeChancePanel.add(changeChanceTitle,BorderLayout.NORTH);
    changeChancePanel.add(changeChanceValue,BorderLayout.WEST);
    changeChancePanel.add(changeChanceExpand,BorderLayout.EAST);
    changeChanceExpand.setPreferredSize(new Dimension(32,32));
    changeChancePanel.add(changeChanceList,BorderLayout.SOUTH);
    changeChanceTitle.addActionListener(eh);
    changeChanceValue.addActionListener(eh);
    changeChanceExpand.addActionListener(eh);
    changeChanceExpand.setEnabled(false);
    changeChanceList.addActionListener(eh);

    /* 12. changeState(State) */

    changeStateList.addActionListener(eh);
    changeStateTitle.setPreferredSize(new Dimension(100,30));
    changeStateList.setPreferredSize(new Dimension(100,30));
    changeStatePanel.add(changeStateTitle,BorderLayout.NORTH);
    changeStatePanel.add(changeStateList,BorderLayout.CENTER);
    changeStateTitle.addActionListener(eh);
   
    /* 13. Panel for uptake and remineralise */

    chemPanel.add(chemFunction,BorderLayout.NORTH);
    chemPanel.add(chemAmount,BorderLayout.WEST);
    chemPanel.add(chemDropDown,BorderLayout.EAST);
    chemPanel.add(chemExpand,BorderLayout.SOUTH);
    chemDropDown.setPreferredSize(new Dimension(120,20));
    chemAmount.addActionListener(eh);
    chemDropDown.addActionListener(eh);
    chemExpand.addActionListener(eh);
    chemFunction.addActionListener(eh);
    
    /* 14. Ingestion Panel */
    
    ingPanel.add(ingTitle,BorderLayout.NORTH);
    JPanel ingGrid = new JPanel(new GridLayout(4,2));
    ingGrid.add(new JLabel("Food-Set   ",JLabel.RIGHT));
    ingGrid.add(ingFoodSet);
    ingGrid.add(new JLabel("Threshold   ",JLabel.RIGHT));
    ingGrid.add(ingThresh);
    ingGrid.add(new JLabel("Rate   ",JLabel.RIGHT));
    ingGrid.add(ingRate);
    ingGrid.add(new JLabel(""));
    ingGrid.add(ingExpand);
    ingPanel.add(ingGrid,BorderLayout.CENTER);
    
    ingFoodSet.addActionListener(eh);
    ingThresh.addActionListener(eh);
    ingRate.addActionListener(eh);
    ingExpand.addActionListener(eh);
    ingTitle.addActionListener(eh);
    
    /* Click on item in operation list */

    addEquation.setPreferredSize(new Dimension(32,32));
    removeEquation.setPreferredSize(new Dimension(32,32));
    renameEquation.setPreferredSize(new Dimension(32,32));
    unitCheck.setPreferredSize(new Dimension(32,32));
    replaceOp.setPreferredSize(new Dimension(32,32));
    surroundOp.setPreferredSize(new Dimension(32,32));
    unSurroundOp.setPreferredSize(new Dimension(32,32));
    addOp.setPreferredSize(new Dimension(32,32));

    addEquation.setToolTipText("Add New Equation");
    removeEquation.setToolTipText("Remove Equation");
    renameEquation.setToolTipText("Rename Equation");
    copyEquation.setToolTipText("Make Copy of Equation");
    unitCheck.setToolTipText("Check Units");
    upEquation.setToolTipText("Move rule earlier");
    downEquation.setToolTipText("Move rule later");
    replaceOp.setToolTipText("Replace Item");
    surroundOp.setToolTipText("Surround Item");
    unSurroundOp.setToolTipText("Remove Surrounding Item");
    addOp.setToolTipText("Add Item");

    opJList.addListSelectionListener(eh);
    addOp.addActionListener(eh);
    replaceOp.addActionListener(eh);
    surroundOp.addActionListener(eh);
    unSurroundOp.addActionListener(eh);
    addEquation.addActionListener(eh);
    removeEquation.addActionListener(eh);
    unitCheck.addActionListener(eh);
    renameEquation.addActionListener(eh);
    copyEquation.addActionListener(eh);
    varButton.addActionListener(eh);
    varNameButton.addActionListener(eh);

    varPanel.add(varButton);
    varPanel.add(varNameButton);
    
    eqJList.addListSelectionListener(eh);
    eqJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
    equationTree.addMouseListener(eh);
    equationTree.addKeyListener(eh);
    addWindowListener(new EventHandler(EventHandler.eqBuilderWC));    

    /* And the back button - also a close event */

    backEqButton.addActionListener(eh);
    saveEqButton.addActionListener(eh);

    try {
      equationTree.setSelectionRow(0);
    } catch (Exception e) { }
  }

  // Initialise for a display...

  public void updateXML() {
    if (eqJList.getModel().getSize()>0) {
      XMLTag equation = newFuncTag.getTagWhere("equation","name",(String)eqJList.getSelectedValue());
      equation.getTag("eq").setValue((String)eqList.elementAt(eqJList.getSelectedIndex()));
      saveEqButton.setEnabled(true);
    }
  }
  
  public void show(XMLTag funcTag, XMLTag groupTag, XMLTag model) {
    InitialiseEquationTree(null);
    eqNames = new Vector();
    eqList = new Vector();
    newFuncTag = (XMLTag) funcTag.clone();
    originalFuncTag = funcTag;
    originalGroupTag = groupTag;
    theModel = model;
    XMLTag[] chems = theModel.getTags("chemical");
    dontUpdateVal=true;
    chemDropDown.removeAllItems();
    for (int i=0; i<chems.length; i++) chemDropDown.addItem(chems[i].getValue("name"));
    dontUpdateVal=false;
    XMLTag[] eqs = funcTag.getTags("equation");
    for (int i=0; i<eqs.length; i++) {
      XMLTag eq = eqs[i];
      eqNames.add(eq.getValue("name"));
      eqList.addElement(eq.getValue("eq"));
    }
    upEquation.setEnabled(false);
    downEquation.setEnabled(false);
    eqJList.setListData(eqNames);
    eqJList.clearSelection();
    if (eqList.size()>0) {
      eqJList.setSelectedIndex(0);
      lastEqSelected=0;
    }
    updateTreeSelector(equationTree.getSelectionPath());
    setVisible(true);
  }
 
  public void setEquation(String s) {
    if (s==null) s = "";
    if (s.equals("")) {
      optionsView.removeAll();
      NSpacer.setPreferredSize(new Dimension(300,127));
      SSpacer.setPreferredSize(new Dimension(300,128));
      WSpacer.setPreferredSize(new Dimension(40,85));
      ESpacer.setPreferredSize(new Dimension(40,85));
      optionsView.add(NSpacer,BorderLayout.NORTH);
      optionsView.add(SSpacer,BorderLayout.SOUTH);
      optionsView.add(ESpacer,BorderLayout.EAST);
      optionsView.add(WSpacer,BorderLayout.WEST);
      optionsView.add(eqTypePanel,BorderLayout.CENTER);
      optionsView.update(optionsView.getGraphics());
      editingMode = FUNCTION;
      updateButtons();
      setOpListContents(FUNCTION);
      currentEquation = null;
      InitialiseEquationTree(null);
      theEqPanel.setEquation("");
      pack();
    } else {
      currentEquation = setEquationHelper(s);
      currentEqString = s;
      eqList.setElementAt(s,eqJList.getSelectedIndex());
      theEqPanel.setEquation(s);
      theEqPanel.repaint();
      InitialiseEquationTree(currentEquation);
      equationTree.setSelectionRow(0);
      pack();
    }
  }
  
  public static XMLTag setEquationHelper(String s) {
    XMLTag innerTag = null;
    if (s.indexOf("{")>=0) {
      String tag = s.substring(0,s.indexOf("{"));
      String inner = StringTools.spit(s,tag+"{");
      tag = tag.substring(1);
       
      if (tag.equals("val")) {
        String inVal = StringTools.spit(s,"\\val{");
        String siValBit = StringTools.spit(StringTools.LHS(inVal,StringTools.getUnNested(inVal,',')),"\\sival{");
        String unitBit = StringTools.spit(StringTools.RHS(inVal,StringTools.getUnNested(inVal,',')),"\\unit{");
        String mainSI = StringTools.LHS(siValBit,StringTools.getUnNested(siValBit,','));
        String expSI = StringTools.RHS(siValBit,StringTools.getUnNested(siValBit,','));
        innerTag = new XMLTag("node");
        innerTag.addTag("name",tag);
        innerTag.addTag("sival",mainSI);
        innerTag.addTag("exp",expSI);
        innerTag.addTag("unit",unitBit);
      } else if (tag.equals("var")) {
        innerTag = new XMLTag("node");
        innerTag.addTag("name","var:"+StringTools.spit(s,"\\var{"));
        innerTag.addTag("varname",StringTools.spit(s,"\\var{"));
      
      
      } else if (tag.equals("change")) {
        String theStage = StringTools.spit(s,"\\change{");
        innerTag = new XMLTag("node");
        innerTag.addTag("name",tag);
        innerTag.addTag("stage",StringTools.spit(theStage,"\\stage{"));

      } else if (tag.equals("pchange")) {
        String inCC = StringTools.spit(s,"\\pchange{");
        innerTag = new XMLTag("node");
        innerTag.addTag("name",tag);
        String lhs = StringTools.LHS(inCC,StringTools.getUnNested(inCC,','));
        String rhs = StringTools.RHS(inCC,StringTools.getUnNested(inCC,','));
        innerTag.addTag("stage",StringTools.spit(lhs,"\\stage{"));
        innerTag.addTag(setEquationHelper(rhs));
      
      } else if (tag.equals("uptake")) {
        String inUp = StringTools.spit(s,"\\uptake{");
        String lhs = StringTools.LHS(inUp,StringTools.getUnNested(inUp,','));
        String rhs = StringTools.RHS(inUp,StringTools.getUnNested(inUp,','));
        rhs = StringTools.spit(rhs,"\\var{");
        rhs = rhs.substring(0,rhs.indexOf("$"));
        innerTag = new XMLTag("node");
        innerTag.addTag("name",tag);
        innerTag.addTag(setEquationHelper(lhs));
        innerTag.addTag("chem",rhs);

      } else if (tag.equals("release")) {
        String inRe = StringTools.spit(s,"\\release{");
        String lhs = StringTools.LHS(inRe,StringTools.getUnNested(inRe,','));
        String rhs = StringTools.RHS(inRe,StringTools.getUnNested(inRe,','));
        rhs = StringTools.spit(rhs,"\\var{");
        rhs = rhs.substring(0,rhs.indexOf("$"));
        innerTag = new XMLTag("node");
        innerTag.addTag("name",tag);
        innerTag.addTag(setEquationHelper(lhs));
        innerTag.addTag("chem",rhs);

      } else if (tag.equals("create")) {
        String inRep = StringTools.spit(s,"\\create{");
        innerTag = new XMLTag("node");
        innerTag.addTag("name",tag);
        String lhs = StringTools.LHS(inRep,StringTools.getUnNested(inRep,','));
        String rhs = StringTools.RHS(inRep,StringTools.getUnNested(inRep,','));
        innerTag.addTag("stage",StringTools.spit(lhs,"\\stage{"));
        while (lhs.length()>2) {
          lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
          rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
          innerTag.addTag(setEquationHelper(lhs));
        }
        innerTag.addTag(setEquationHelper(rhs));
      
      } else {
        if (tag.indexOf("{")>=0) tag = tag.substring(0,tag.indexOf("{"));
        String lhs = "";
        String rhs = "";
        if (inner.indexOf(",")>=0) {
          lhs = StringTools.LHS(inner,StringTools.getUnNested(inner,','));
          rhs = StringTools.RHS(inner,StringTools.getUnNested(inner,','));
        } else {
          lhs = inner;
          rhs = "";
        }
        
        innerTag = new XMLTag("node");
        innerTag.addTag("name",tag);
        XMLTag lhsTag = setEquationHelper(lhs);
        if (lhsTag!=null) innerTag.addTag(lhsTag);
        while (StringTools.getUnNested(rhs,',')>=0) {
          lhs = StringTools.LHS(rhs,StringTools.getUnNested(rhs,','));
          innerTag.addTag(setEquationHelper(lhs));
          rhs = StringTools.RHS(rhs,StringTools.getUnNested(rhs,','));
        }
        if (rhs.length()>0) innerTag.addTag(setEquationHelper(rhs));
      }
    }
    return innerTag;
  }
  
  public void setOpListContents(int i) {
    if (i==NONE) {
      opJList.setListData(new String[]{});
      opJList.clearSelection();
    } else if (i==NUMERICAL) {
      opJList.setListData(new String[] {"abs","acos","add","asin","atan","conditional","cos","densityAt","depthForFI","depthForVI","div","exp","fullIrradAt","integrate","ln","log10","max","min","minus","mul","pow","rnd","salinityAt","sin","sqrt","sumFGPool","sub","tan","temperatureAt","UVIrradAt","val","var","varhist","varietysum","varietymul","visIrradAt"});
      opJList.clearSelection();
    } else if (i==BOOLEAN) {
      opJList.setListData(new String[] {"and","allVariety","equal","greater","greaterequal","less","lessequal","neq","not","noVariety","or","someVariety"});
      opJList.clearSelection();
    } else if (i==VARONLY) {
      opJList.setListData(new String[] {"var"});
      opJList.clearSelection();
    } else if (i==FUNCTION) {
      opJList.setListData(new String[] {"assign","change","create","divide","ifthen","ingest","pchange","release","uptake","while"});
      opJList.clearSelection();
    } else if (i==ASSIGN_ONLY) {
      opJList.setListData(new String[] {"assign"});
      opJList.clearSelection();
    }
  }

  public static boolean isAssign(String s) {
    return (s.equals("assign"));
  }
  
  public static boolean isBinary(String s) {
    return ((s.equals("uptake"))||(s.equals("release"))||(s.equals("assign"))||(s.equals("greater"))||(s.equals("greaterequal"))||
            (s.equals("equal"))||(s.equals("neq"))||(s.equals("less"))||(s.equals("lessequal"))||
            (s.equals("pow"))||(s.equals("ifthen"))||(s.equals("div"))||(s.equals("sub"))||(s.equals("varhist"))||(s.equals("set")));
  }

  public static boolean isUnary(String s) {
    return ((s.equals("someVariety"))||(s.equals("allVariety"))||(s.equals("noVariety"))||(s.equals("varietysum"))||(s.equals("varietymul"))||(s.equals("sin"))||(s.equals("cos"))||(s.equals("tan"))||(s.equals("acos"))||(s.equals("atan"))|
            (s.equals("asin"))||(s.equals("abs"))||(s.equals("exp"))||(s.equals("ln"))||
            (s.equals("log10"))||(s.equals("minus"))||(s.equals("rnd"))||(s.equals("sqrt"))||
            (s.equals("not"))||(s.equals("divide"))||(s.equals("var"))||(s.equals("integrate"))||
            (s.equals("temperatureAt"))||(s.equals("salinityAt"))||(s.equals("densityAt"))||
            (s.equals("fullIrradAt"))||(s.equals("visIrradAt"))||(s.equals("UVIrradAt"))||(s.equals("depthForVI"))||(s.equals("depthForFI")));
  }

  public static boolean isMulti(String s) {
    return ((s.equals("add"))||(s.equals("mul"))||(s.equals("or"))||(s.equals("and"))||(s.equals("max"))||(s.equals("min")));
  }

  public static boolean isFunction(String s) {
    return ((s.equals("uptake"))||
            (s.equals("assign"))||
            (s.equals("change"))||
            (s.equals("create"))||
            (s.equals("divide"))||
            (s.equals("ifthen"))||
            (s.equals("ingest"))||  
            (s.equals("pchange"))||
            (s.equals("release"))||
            (s.equals("while"))||
            (s.equals("? Func")));
  }

  public static boolean isUnknown(String s) {
    return ((s.equals("? Func"))||(s.equals("? Bool"))||(s.equals("? Num"))||(s.equals("? Func")));
  }

  public static boolean isBoolean(String s) {
    return ((s.equals("someVariety"))||(s.equals("allVariety"))||(s.equals("noVariety"))||(s.equals("greater"))||(s.equals("greaterequal"))||(s.equals("not"))||
            (s.equals("equal"))||(s.equals("neq"))||(s.equals("less"))||(s.equals("lessequal"))||
            (s.equals("and"))||(s.equals("or"))||(s.equals("? Bool")));
  }
  
  public static boolean boolTakesBoolean(String s) {
    return ((s.equals("someVariety"))||(s.equals("allVariety"))||(s.equals("noVariety"))||(s.equals("not"))||
        (s.equals("and"))||(s.equals("or"))||(s.equals("? Bool")));
  }

  public static boolean boolTakesNumerical(String s) {
    return ((s.equals("greater"))||(s.equals("greaterequal"))||
            (s.equals("equal"))||(s.equals("neq"))||(s.equals("less"))||(s.equals("lessequal")));
  }

  public static boolean isNumerical(String s) {
    return ((s.equals("varietysum"))||(s.equals("varietymul"))||(s.equals("sin"))||(s.equals("cos"))||(s.equals("tan"))||(s.equals("acos"))||(s.equals("atan"))|
           (s.equals("asin"))||(s.equals("abs"))||(s.equals("div"))||(s.equals("exp"))||(s.equals("ln"))||
            (s.equals("log10"))||(s.equals("minus"))||(s.equals("rnd"))||(s.equals("sqrt"))||(s.equals("val"))||
            (s.equals("var"))||(s.equals("add"))||(s.equals("mul"))||(s.equals("max"))||(s.equals("min"))||
            (s.equals("pow"))||(s.equals("conditional"))||(s.equals("sub"))||(s.equals("varhist"))||(s.equals("? Num"))||
            (s.equals("integrate"))||(s.startsWith("var:"))||
            (s.equals("temperatureAt"))||(s.equals("salinityAt"))||(s.equals("densityAt"))||
            (s.equals("fullIrradAt"))||(s.equals("visIrradAt"))||(s.equals("UVIrradAt"))||(s.equals("depthForVI"))||(s.equals("depthForFI")));
  }

  /* Prepare a newly added/replaced equation element with appropriate placeholders */
  
  public void fixIfThen(XMLTag newTag, XMLTag oldTag) {
    if ((oldTag!=null) && (oldTag.getValue("name").equals("ifthen"))) {      // Ifthen replaced an 'ifthen' bizarrely!
      newTag.addTag("node").addTag((XMLTag)(oldTag.getTag("node",1).clone()));  // Just copy
      newTag.addTag("node").addTag((XMLTag)(oldTag.getTag("node",2).clone()));  // both sides.
    } else {    // It was added as a new tag - use standard defaults.
      newTag.addTag("node").addTag("name","? Bool");
      newTag.addTag("node").addTag("name","? Func");
    }
  }

  public void fixBinary(String s, XMLTag newTag, XMLTag oldTag) {
    if ((oldTag!=null) && (isMulti(oldTag.getValue("name")))) {                    // This binary replaced a multi-function
      int count = oldTag.getTags().length-1;                   // How many elements did it used to have.
      int result = 0;                                          // Warn if necessary that we'll only keep 2.
      if (count>2) result = JOptionPane.showConfirmDialog(this, "Only the first two entries of this '"+oldTag.getValue("name")+"' will survive. Continue?", "Confirm replace",JOptionPane.YES_NO_OPTION);
      if (result==0) {
        if (oldTag.getTag("node",1)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",1).clone()));
        else newTag.addTag("node").addTag("name","? Num");
        if (oldTag.getTag("node",2)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",2).clone()));
        else newTag.addTag("node").addTag("name","? Num");
      }
    } else if (s.equals("varhist")) {             // It's a varhist kind of binary function
      newTag.addTag("node").addTag("name","? Var");
      newTag.addTag("node").addTag("name","? Num");
    } else if (isAssign(s)) {                     // It's an assignment kind of binary function
      newTag.addTag("node").addTag("name","? Var");
      newTag.addTag("node").addTag("name","? Num");
    } else if (isNumerical(s)) {
      if (oldTag==null) {
        newTag.addTag("node").addTag("name","? Num");
        newTag.addTag("node").addTag("name","? Num");
      } else {
        if (oldTag.getTag("node",1)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",1).clone()));
        else newTag.addTag("node").addTag("name","? Num");
        if (oldTag.getTag("node",2)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",2).clone()));
        else newTag.addTag("node").addTag("name","? Num");
      }
    } else if (isBoolean(s)) {
      if (boolTakesBoolean(s)) {
        if (oldTag==null) {
          newTag.addTag("node").addTag("name","? Bool");
          newTag.addTag("node").addTag("name","? Bool");
        } else {
          if (oldTag.getTag("node",1)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",1).clone()));
          else newTag.addTag("node").addTag("name","? Bool");
          if (oldTag.getTag("node",2)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",2).clone()));
          else newTag.addTag("node").addTag("name","? Bool");
        }
      } else if (boolTakesNumerical(s)) {
        newTag.addTag("node").addTag("name","? Num");
        newTag.addTag("node").addTag("name","? Num");
      } else {
        if (oldTag.getTag("node",1)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",1).clone()));
        else newTag.addTag("node").addTag("name","? Num");
        if (oldTag.getTag("node",2)!=null) newTag.addTag((XMLTag)(oldTag.getTag("node",2).clone()));
        else newTag.addTag("node").addTag("name","? Num");
      }
        
      
    } else if (s.equals("uptake")) {
      newTag.addTag("node").addTag("name","? Num");
    } else if (s.equals("release")) {
      newTag.addTag("node").addTag("name","? Num");
    }
  }
  
  public void fixWhile(XMLTag newTag, XMLTag oldTag) {
    if ((oldTag!=null) && (oldTag.getValue("name").equals("while")))
      newTag.addTag("node").addTag(oldTag.getTag("node").getTag("name"));
    else newTag.addTag("node").addTag("name","? Bool");
  }
  
  public void fixReproduce(XMLTag newTag, XMLTag oldTag) {
    newTag.addTag("stage","");
    newTag.addTag("node").addTag("name","? Num");
  }

  public void fixChangeState(XMLTag newTag, XMLTag oldTag) {
    newTag.addTag("stage","");
  }

  public void fixChangeChance(XMLTag newTag, XMLTag oldTag) {
    newTag.addTag("stage","");
    newTag.addTag("node").addTag("name","? Num");
  }
   
  public void fixValue(XMLTag newTag, XMLTag oldTag) {
    if ((oldTag!=null) && (oldTag.getValue("name").equals("val"))) { // Replace val with a val... 
      newTag.addTag("sival",oldTag.getValue("sival"));
      newTag.addTag("exp",oldTag.getValue("exp"));
      newTag.addTag("unit",oldTag.getValue("unit"));
    } else { // Set up new value
      newTag.addTag("sival","0.0");
      newTag.addTag("exp","0");
      newTag.addTag("unit","0,0,0");
    }
  }

  public void fixIfSwitch(XMLTag newTag, XMLTag oldTag) {
    newTag.addTag("node").addTag("name","? Bool");
    newTag.addTag("node").addTag("name","? Num");
    newTag.addTag("node").addTag("name","? Num");
  }

  public void fixVar(XMLTag newTag, XMLTag oldTag) {
    newTag.getTag("name").setValue("? Var");
  }

  public void fixUnary(String s, XMLTag newTag, XMLTag oldTag) {
    if ((oldTag!=null) && (isUnary(oldTag.getValue("name"))))
      newTag.addTag((XMLTag)oldTag.getTag("node").clone());
    else 
      newTag.addTag("node").addTag("name","? Num");
  }

  public void fixingest(XMLTag newTag, XMLTag oldTag) {
    newTag.addTag("node").addTag("name","? Var");
    newTag.addTag("node").addTag("name","? Num");
    newTag.addTag("node").addTag("name","? Num");
  }

  public void fixMulti(String s, XMLTag newTag, XMLTag oldTag) {
    if (oldTag!=null) {  // If its not new
      XMLTag[] t = oldTag.getTags("node");   // Then get whatever nodes you can!
      for (int i=0; i<t.length; i++)
        if (!t[i].getName().equals("name")) newTag.addTag((XMLTag)t[i].clone());
    } // otherwise... you don't need to add anything for a multi.
  }

  public void fixNewTag(String s, XMLTag newTag, XMLTag oldTag) {
    // Given a newtag and an oldtag, fixNewTag creates a new 'default' tag,
    // taking as much information from the oldTag as possible.

    if (s.equals("change")) fixChangeState(newTag,oldTag);
    else if (s.equals("pchange")) fixChangeChance(newTag,oldTag);
    else if (s.equals("create")) fixReproduce(newTag,oldTag);
    else if (s.equals("ifthen")) fixIfThen(newTag,oldTag);
    else if (s.equals("while")) fixWhile(newTag,oldTag);
    else if (s.equals("val")) fixValue(newTag,oldTag);
    else if (s.equals("conditional")) fixIfSwitch(newTag,oldTag);
    else if (s.equals("var")) fixVar(newTag,oldTag);
    else if (s.equals("ingest")) fixingest(newTag,oldTag);
    else if (isBinary(s)) fixBinary(s,newTag,oldTag); 
    else if (isUnary(s)) fixUnary(s,newTag,oldTag);
    else if (isMulti(s)) fixMulti(s,newTag,oldTag);
 
  }
  
  public void updateDropDown() {
    Vector params = new Vector();
    if ((newFuncTag!=null) && (eqJList.getSelectedIndex()>-1)) {
      XMLTag eq = newFuncTag.getTag("equation",1+eqJList.getSelectedIndex());
      if (eq!=null) {
        String eqs = eq.getValue("eq");
        if (eqs==null) eqs = "";
        while (eqs.indexOf("\\var{")>=0) {
          eqs = eqs.substring(eqs.indexOf("\\var{")+5);
          String eqt = eqs.substring(0,eqs.indexOf('}'));
          boolean found=false;
          int pNo=0;
          while ((!found) && (pNo<params.size())) found=(((String)params.elementAt(pNo++)).equals(eqt));
          if (!found) StringTools.addInOrder(params,eqt);
          eqs = eqs.substring(eqs.indexOf('}'));
        }
      }
    }
  }

  public void updateButtons() {
    addEquation.setEnabled(true);
    removeEquation.setEnabled(eqJList.getSelectedIndex()>=0);
    renameEquation.setEnabled(eqJList.getSelectedIndex()>=0);
    unitCheck.setEnabled(eqJList.getSelectedIndex()>=0);
    copyEquation.setEnabled(eqJList.getSelectedIndex()>=0);
    boolean addEnabled = false;
    boolean repEnabled = false;
    boolean surEnabled = true;
    if (editingMode==MULTI) {
      if ((opJList.getSelectedIndex()>=0)) addEnabled = true;
      if ((opJList.getSelectedIndex()>=0) && (multiTitle.isSelected())) repEnabled = true;
      if ((opJList.getSelectedIndex()>=0) && (multiList.getSelectedIndex()>=0)) repEnabled = true;
    }

    if (editingMode==WHILE) {
      if ((opJList.getSelectedIndex()>=0) && (statList.getSelectedIndex()>=0)) repEnabled = true;
      if ((opJList.getSelectedIndex()>=0) && (whileButton.isSelected())) repEnabled = true;
      if ((opJList.getSelectedIndex()>=0) && (condition.isSelected())) repEnabled = true;
      if ((opJList.getSelectedIndex()>=0) && (!condition.isSelected())) addEnabled = true;
    }

    if ((editingMode==UNARY) && (opJList.getSelectedIndex()>=0) && ((unaryTerm.isSelected()) || 
      (unaryTitle.isSelected()))) repEnabled = true;
    if ((editingMode==BINARY) && (opJList.getSelectedIndex()>=0) && ((binaryLHS.isSelected()) || 
      (binaryRHS.isSelected()) || (binaryTitle.isSelected()))) repEnabled = true;
    if ((editingMode==IFSWITCH) && (opJList.getSelectedIndex()>=0) && ((ifButton.isSelected()) || 
      (thenButton.isSelected()) || (elseButton.isSelected()))) repEnabled = true;
    if ((editingMode==VARONLY) && (opJList.getSelectedIndex()>=0) && ((varButton.isSelected())))
      repEnabled = true;
    if ((editingMode==VALONLY) && (opJList.getSelectedIndex()>=0) && ((valButton.isSelected())))
      repEnabled = true;
    if ((editingMode==CHANGE_STATE) && (opJList.getSelectedIndex()>=0) && (changeStateTitle.isSelected())) {
      repEnabled = true;
      surEnabled = false;
    }
    if (((editingMode==CHANGE_CHANCE) && (opJList.getSelectedIndex()>=0)) && 
      ((changeChanceTitle.isSelected()) || (changeChanceValue.isSelected()))) {
      repEnabled = true;
      surEnabled = false;
    }
    if ((editingMode==FUNCTION) && (opJList.getSelectedIndex()>=0)) addEnabled = true;

    if (editingMode==REPRODUCE) {
      if ((reTitle.isSelected()) && (opJList.getSelectedIndex()>=0)) repEnabled = true;
      if ((reOffspring.isSelected()) && (opJList.getSelectedIndex()>=0)) repEnabled = true;
      if ((opJList.getSelectedIndex()>=0)) addEnabled = true;
      if ((opJList.getSelectedIndex()>=0) && (reTitle.isSelected())) repEnabled = true;
      if ((opJList.getSelectedIndex()>=0) && (reList.getSelectedIndex()>=0)) repEnabled = true;
    }

    if ((editingMode==ONLY_OPTION) && (opJList.getSelectedIndex()>=0)) repEnabled = true;
    
    if (editingMode==INGESTION) {
      if ((ingTitle.isSelected()) && (opJList.getSelectedIndex()>=0)) repEnabled=true;
      if (ingThresh.isSelected()) ingExpand.setEnabled(true);
      else if (ingRate.isSelected()) ingExpand.setEnabled(true);
      else ingExpand.setEnabled(false);
    }
    
    if (editingMode==REMIN_UPTAKE) {
      if ((chemFunction.isSelected()) && (opJList.getSelectedIndex()>=0)) repEnabled=true;
      addEnabled=false;
      surEnabled=false;
    }
    
    multiRemove.setEnabled((editingMode==MULTI) && (multiList.getSelectedIndex()>=0));
    reRemove.setEnabled((editingMode==REPRODUCE) && (reList.getSelectedIndex()>=0));

    addOp.setEnabled(addEnabled);
    replaceOp.setEnabled(repEnabled);
    surroundOp.setEnabled(repEnabled && surEnabled);
    // NEXT LINE IS A QUICK HACK!
    unSurroundOp.setEnabled(repEnabled && surEnabled);
    updateDropDown();
  }
  
  private static String deriveEquation(XMLTag x) {
    String xv = x.getValue("name");
    if (xv.startsWith("var:")) xv = "\\var{"+xv.substring(xv.indexOf(":")+1);
    else if (xv.equals("val")) xv = "\\val{\\sival{"+x.getValue("sival")+","+x.getValue("exp")+"},\\unit{"+x.getValue("unit")+"}";
    else if (xv.equals("pchange")) xv = "\\pchange{\\stage{"+x.getValue("stage")+"},"+deriveEquation(x.getTag("node"));
    else if (xv.equals("uptake")) xv = "\\uptake{"+deriveEquation(x.getTag("node"))+",\\var{"+x.getValue("chem")+"$Conc}";
    else if (xv.equals("release")) xv = "\\release{"+deriveEquation(x.getTag("node"))+",\\var{"+x.getValue("chem")+"$Conc}";
    else if (xv.equals("change")) xv = "\\change{\\stage{"+x.getValue("stage")+"}";
    else if (xv.equals("create")) {
      xv = "\\create{\\stage{"+x.getValue("stage")+"},";
      for (int i=0; i<x.getTags("node").length; i++) {
        xv+= deriveEquation(x.getTag("node",i+1));
        if (i<x.getTags("node").length-1) xv += ",";
      }
      xv += "}";
    
    } else {
      xv = "\\"+xv+"{";
      XMLTag[] tags = x.getTags();
      for (int i=0; i<tags.length; i++) {
        if (!tags[i].getName().equals("name")) {
          xv+=deriveEquation(tags[i]);
          if (i<tags.length-1) xv+=",";
        }
      }
    }
    return xv+"}";
  }
  
  private void updateTreeSelector(TreePath treeSelPath) {
    int i=0;
    if (treeSelPath!=null) {
      equationTree.clearSelection();
      i=equationTree.getRowCount();
      while (i>0) {
        equationTree.collapseRow(i);
        equationTree.repaint();
        i--;
        if (i>equationTree.getRowCount()) i=equationTree.getRowCount();
      }
      equationTree.setSelectionPath(treeSelPath);
      equationTree.expandPath(treeSelPath);
    }
    equationTree.repaint();
    XMLTag x = tagForPath();
    binaryLHS.setSelected(false);
    binaryRHS.setSelected(false);
    binaryTitle.setSelected(false);
    multiList.clearSelection();
    multiTitle.setSelected(false);
    setOpListContents(NONE);
    ifButton.setSelected(false);
    elseButton.setSelected(false);
    thenButton.setSelected(false);
    varButton.setSelected(false);
    unaryTitle.setSelected(false);
    unaryTerm.setSelected(false);
    multiExpand.setEnabled(false);
    binaryExpand.setEnabled(false);
    unaryExpand.setEnabled(false);
    ifswitchExpand.setEnabled(false);
    if (x!=null) {
      String s = x.getValue("name");
      if (s.equals("conditional")) {
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,47));
        SSpacer.setPreferredSize(new Dimension(300,47));
        WSpacer.setPreferredSize(new Dimension(0,85));
        ESpacer.setPreferredSize(new Dimension(10,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(ifswitchPanel,BorderLayout.CENTER);
        optionsView.update(optionsView.getGraphics());
        ifButton.setText(x.getTag("node",1).getValue("name"));
        thenButton.setText(x.getTag("node",2).getValue("name"));
        elseButton.setText(x.getTag("node",3).getValue("name"));
        ifButton.setSelected(false);
        thenButton.setSelected(false);
        elseButton.setSelected(false);
        ifswitchExpand.setEnabled(false);
        editingMode = IFSWITCH;
        updateButtons();
        ifswitchPanel.update(ifswitchPanel.getGraphics());
        pack();

      } else if (s.equals("create")) {
        optionsView.removeAll();
        reTitle.setText(s);
        i=2;
        XMLTag set = x.getTag("node",i);
        Vector reListData = new Vector();
        while (set!=null) {
          reListData.add(new String(set.getTag("node",1).getValue("name")));
          set = x.getTag("node",++i);
        }
        reList.setListData(reListData);
        reList.clearSelection();
        reExpand.setEnabled(false);
        String stage = x.getValue("stage");
        XMLTag[] stages = originalGroupTag.getTags("stage");
        lockEvents++;
        reproduceStateList.removeAllItems();
        for (int _i=0; _i<stages.length; _i++) reproduceStateList.addItem(stages[_i].getValue("name"));
        StringTools.setStringItem(reproduceStateList,stage);
        lockEvents--;
        reOffspring.setText(x.getTag("node",1).getValue("name"));
        NSpacer.setPreferredSize(new Dimension(300,17));
        SSpacer.setPreferredSize(new Dimension(300,17));
        WSpacer.setPreferredSize(new Dimension(20,85));
        ESpacer.setPreferredSize(new Dimension(20,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(rePanel,BorderLayout.CENTER);
        editingMode = REPRODUCE;
        setOpListContents(VARONLY);
        updateButtons();
        optionsView.update(optionsView.getGraphics());
        rePanel.update(rePanel.getGraphics());
        pack();

      } else if (isMulti(s)) {
        Vector V = new Vector();
        i=1;
        XMLTag y = x.getTag("node",i);
        while (y!=null) {
          V.addElement(y.getValue("name"));
          y = x.getTag("node",++i);
        }
        multiList.setListData(V);
        multiList.clearSelection();
        multiExpand.setEnabled(false);
        multiTitle.setText(s);
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,17));
        SSpacer.setPreferredSize(new Dimension(300,17));
        WSpacer.setPreferredSize(new Dimension(20,85));
        ESpacer.setPreferredSize(new Dimension(20,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(multiPanel,BorderLayout.CENTER);
        editingMode = MULTI;
        if (isBoolean(x.getValue("name"))) setOpListContents(BOOLEAN);
        else if (isNumerical(x.getValue("name"))) setOpListContents(NUMERICAL);
        updateButtons();
        optionsView.update(optionsView.getGraphics());
        multiPanel.update(multiPanel.getGraphics());
        pack();

      } else if ((s.equals("? Var"))||(s.equals("var"))||(s.startsWith("var:"))) {
        optionsView.removeAll();
        optionsView.removeAll();
        varButton.setText("var");
        varNameButton.setText(s.substring(s.indexOf(":")+1));
        NSpacer.setPreferredSize(new Dimension(300,130));
        SSpacer.setPreferredSize(new Dimension(300,130));
        WSpacer.setPreferredSize(new Dimension(20,85));
        ESpacer.setPreferredSize(new Dimension(20,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(varPanel,BorderLayout.CENTER);
        optionsView.update(optionsView.getGraphics());
        varPanel.update(varPanel.getGraphics());
        editingMode=VARONLY;
        pack();

      } else if (s.equals("val")) {
        optionsView.removeAll();
        unaryTitle.setText(s);
        NSpacer.setPreferredSize(new Dimension(300,100));
        SSpacer.setPreferredSize(new Dimension(300,100));
        WSpacer.setPreferredSize(new Dimension(40,85));
        ESpacer.setPreferredSize(new Dimension(40,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(valPanel,BorderLayout.CENTER);
        editingMode = VALONLY;
        updateButtons();
        dontUpdateVal=true; // The next line fires an event which tried to update x
        valMain.setText(x.getValue("sival"));
        valExpInt = Integer.parseInt(x.getValue("exp"));
        valUnit.setEquation("\\unit{"+x.getValue("unit")+"}");
        valUnitString = x.getValue("unit");
        dontUpdateVal=false;
        valExp.setText(String.valueOf(valExpInt));
        valButton.setSelected(false);
        valUnit.paint(valUnit.getGraphics());
        optionsView.update(optionsView.getGraphics());
        valPanel.update(valPanel.getGraphics());
        pack();

      } else if (s.equals("change")) { // change stage
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,127));
        SSpacer.setPreferredSize(new Dimension(300,128));
        WSpacer.setPreferredSize(new Dimension(40,85));
        ESpacer.setPreferredSize(new Dimension(40,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(changeStatePanel,BorderLayout.CENTER);
        lockEvents++;
        changeStateList.removeAllItems();
        XMLTag[] stages = originalGroupTag.getTags("stage");
        for (int _i=0; _i<stages.length; _i++) changeStateList.addItem(stages[_i].getValue("name"));
        StringTools.setStringItem(changeStateList,x.getValue("stage"));
        lockEvents--;
        editingMode = CHANGE_STATE;
        updateButtons();
        
        updateTree();
        optionsView.update(optionsView.getGraphics());
        changeStatePanel.update(changeStatePanel.getGraphics());
        pack();

      } else if (s.equals("ingest")) {
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,90));
        SSpacer.setPreferredSize(new Dimension(300,90));
        WSpacer.setPreferredSize(new Dimension(40,85));
        ESpacer.setPreferredSize(new Dimension(40,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(ingPanel,BorderLayout.CENTER);
        ingFoodSet.setText(x.getTag("node",1).getValue("name"));
        ingThresh.setText(x.getTag("node",2).getValue("name"));
        ingRate.setText(x.getTag("node",3).getValue("name"));
        ingThresh.setSelected(false);
        ingRate.setSelected(false);
        ingExpand.setEnabled(false);
        
        lockEvents++;
        editingMode = INGESTION;
        updateButtons();
        updateTree();
        optionsView.update(optionsView.getGraphics());
        ingPanel.update(ingPanel.getGraphics());
        pack();
        
      
      } else if (s.equals("pchange")) { // deathChance
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,117));
        SSpacer.setPreferredSize(new Dimension(300,118));
        WSpacer.setPreferredSize(new Dimension(40,85));
        ESpacer.setPreferredSize(new Dimension(40,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(changeChancePanel,BorderLayout.CENTER);
        changeChanceValue.setText(x.getTag("node").getValue("name"));
        
        lockEvents++;
        changeChanceList.removeAllItems();
        XMLTag[] stages = originalGroupTag.getTags("stage");
        for (int _i=0; _i<stages.length; _i++) changeChanceList.addItem(stages[_i].getValue("name"));
        StringTools.setStringItem(changeChanceList,x.getValue("stage"));
        lockEvents--;
        editingMode = CHANGE_CHANCE;
        updateButtons();
        updateTree();
        optionsView.update(optionsView.getGraphics());
        changeChancePanel.update(changeChancePanel.getGraphics());
        pack();

      } else if (isUnknown(s)) {
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,117));
        SSpacer.setPreferredSize(new Dimension(300,118));
        WSpacer.setPreferredSize(new Dimension(40,85));
        ESpacer.setPreferredSize(new Dimension(40,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(ucPanel,BorderLayout.CENTER);
        editingMode = ONLY_OPTION;
        ucButton.setText(x.getValue("name"));
        ucButton.setSelected(true);
        if (isFunction(x.getValue("name"))) setOpListContents(FUNCTION);
        else if (isNumerical(x.getValue("name"))) setOpListContents(NUMERICAL);
        else if (isBoolean(x.getValue("name"))) setOpListContents(BOOLEAN);
        updateButtons();
        optionsView.update(optionsView.getGraphics());
        ucPanel.update(ucPanel.getGraphics());

      } else if ((s.equals("release")) || (s.equals("uptake"))) {
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,117));
        SSpacer.setPreferredSize(new Dimension(300,118));
        WSpacer.setPreferredSize(new Dimension(40,85));
        ESpacer.setPreferredSize(new Dimension(40,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(chemPanel,BorderLayout.CENTER);
        chemFunction.setText(s);
        chemAmount.setText(x.getTag("node").getValue("name"));
        String chem = x.getValue("chem");
        chemDropDown.setSelectedItem(chem);
        editingMode = REMIN_UPTAKE;
        updateButtons();
        updateTree();
        optionsView.update(optionsView.getGraphics());
        changeChancePanel.update(chemPanel.getGraphics());
        pack();


      } else if (isBinary(s)) {
        optionsView.removeAll();
        binaryTitle.setText(s);
        NSpacer.setPreferredSize(new Dimension(300,87));
        SSpacer.setPreferredSize(new Dimension(300,88));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(binaryPanel,BorderLayout.CENTER);
        editingMode = BINARY;
        updateButtons();
        String sn = x.getTag("node",1).getValue("name");
        binaryLHS.setText(sn);
        binaryRHS.setText(x.getTag("node",2).getValue("name"));
        binarySwap.setEnabled(!((s.startsWith("assign")) || (s.equals("ifthen")) || 
            (s.equals("set")) || (s.equals("varhist")))); 
        binaryLHS.setSelected(false);
        binaryRHS.setSelected(false);
        binaryExpand.setEnabled(false);
        optionsView.update(optionsView.getGraphics());
        binaryPanel.update(binaryPanel.getGraphics());
        pack();

      } else if (isUnary(s)) {
        optionsView.removeAll();
        unaryTitle.setText(s);
        NSpacer.setPreferredSize(new Dimension(300,127));
        SSpacer.setPreferredSize(new Dimension(300,128));
        WSpacer.setPreferredSize(new Dimension(40,85));
        ESpacer.setPreferredSize(new Dimension(40,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(unaryPanel,BorderLayout.CENTER);
        editingMode = UNARY;
        updateButtons();
        unaryTerm.setText(x.getValue("node/name"));
        unaryTerm.setSelected(false);
        unaryExpand.setEnabled(false);
        optionsView.update(optionsView.getGraphics());
        unaryPanel.update(unaryPanel.getGraphics());
        pack();

      } else if (s.equals("while")) {
        Vector V = new Vector();
        i=1;
        XMLTag y = x.getTag("node",i++);
        condition.setText(y.getValue("name"));
        y = x.getTag("node",i);
        while (y!=null) {
          V.addElement(y.getValue());
          y = x.getTag("node",++i);
        }
        statList.setListData(V);
        statList.clearSelection();
        statExpand.setEnabled(false);
        optionsView.removeAll();
        NSpacer.setPreferredSize(new Dimension(300,17));
        SSpacer.setPreferredSize(new Dimension(300,17));
        WSpacer.setPreferredSize(new Dimension(20,85));
        ESpacer.setPreferredSize(new Dimension(20,85));
        optionsView.add(NSpacer,BorderLayout.NORTH);
        optionsView.add(SSpacer,BorderLayout.SOUTH);
        optionsView.add(ESpacer,BorderLayout.EAST);
        optionsView.add(WSpacer,BorderLayout.WEST);
        optionsView.add(whilePanel,BorderLayout.CENTER);
        editingMode = WHILE;
        setOpListContents(ASSIGN_ONLY);
        opJList.setSelectedIndex(0);
        updateButtons();
        optionsView.update(optionsView.getGraphics());
        whilePanel.update(whilePanel.getGraphics());
        pack();
      }  
      
      //else if (s.equals("set")) {

        //equationTree.expandRow(equationTree.getSelectionRows()[0]);
        //equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1);
     //18/07/2003 }
    } else optionsView.removeAll();
  }

  public void updateTree() {
    boolean[] b = null;
    int s = 0;
    if (equationTree.getRowCount()>0) {
      b = new boolean[equationTree.getRowCount()];
      for (int i=0; i<equationTree.getRowCount(); i++) b[i] = equationTree.isExpanded(i);
    }
    if ((equationTree.getSelectionRows()!=null) && (equationTree.getSelectionRows().length>0)) s = equationTree.getSelectionRows()[0];

    XMLTag root = currentEquation;
    InitialiseEquationTree(root);
    currentEqString = deriveEquation(root);
    eqList.setElementAt(currentEqString,eqJList.getSelectedIndex());
    if (b!=null) {
      for (int i=0; i<b.length; i++) if (b[i]) equationTree.expandRow(i); else equationTree.collapseRow(i);
      equationTree.setSelectionRow(s);
    } else if (equationTree.getRowCount()>=0) equationTree.setSelectionRow(0);

    theEqPanel.setEquation(currentEqString);
    theEqPanel.repaint();
    updateXML();
  }
  
  
  public class EventHandler implements ActionListener, MouseListener, KeyListener, WindowListener, CaretListener, ListSelectionListener {
    private int objectID;
    
    public final static int eqBuilderWC = 1;
    public EventHandler(int obj) {
      objectID=obj;
    }
    
    public EventHandler() { objectID=0;}

   

    public void surroundObject(XMLTag rootNode) {
      String opV = (String)opJList.getSelectedValue();
      XMLTag targetNode = findTargetNode(rootNode);
      XMLTag surroundNode = new XMLTag("node");
      surroundNode.addTag("name",opV);
      fixNewTag(opV,surroundNode,null);
      
      if (surroundNode.getValue("name").equals("conditional")) {
        ((XMLTag)targetNode.clone()).replace(surroundNode.getTag("node",2));
        surroundNode.replace(targetNode);

      } else if (isMulti(surroundNode.getValue("name"))) {
        surroundNode.addTag("node","");
        ((XMLTag)targetNode.clone()).replace(surroundNode.getTag("node",1));
        surroundNode.replace(targetNode);

      } else {
        ((XMLTag)targetNode.clone()).replace(surroundNode.getTag("node",1));
        surroundNode.replace(targetNode);
      }
    }

    public XMLTag findTargetNode(XMLTag rootNode) {
      XMLTag targetNode = null;
      if (editingMode==IFSWITCH) {
        if (ifButton.isSelected()) targetNode = rootNode.getTag("node",1);
        else if (thenButton.isSelected()) targetNode = rootNode.getTag("node",2);
        else if (elseButton.isSelected()) targetNode = rootNode.getTag("node",3);
      } else if (editingMode==BINARY) {
        if (binaryLHS.isSelected()) targetNode = rootNode.getTag("node",1);
        else if (binaryRHS.isSelected()) targetNode = rootNode.getTag("node",2);
        else if (binaryTitle.isSelected()) targetNode = rootNode;
      } else if (editingMode==MULTI) {
        if (multiTitle.isSelected()) targetNode = rootNode;
        else if (!multiList.isSelectionEmpty()) targetNode = rootNode.getTag("node",multiList.getSelectedIndex()+1);
      } else if (editingMode==UNARY) {
        if (unaryTitle.isSelected()) targetNode = rootNode;
        else if (unaryTerm.isSelected()) targetNode = rootNode.getTag("node",1);
      } else if (editingMode==VARONLY) {
        targetNode = rootNode; 
      } else if (editingMode==VALONLY) {
        targetNode = rootNode;
      } else if (editingMode==WHILE) {
        if (condition.isSelected()) targetNode = rootNode.getTag("node",1);
      } 
      return targetNode;
    }

    
    public void unSurroundObject(XMLTag rootNode) {
      XMLTag targetNode = findTargetNode(rootNode);
      XMLTag childNode = targetNode.getTag("node",1);
      childNode.removeFromParent();
      childNode.replace(targetNode);    
    }
    
    public int checkSaveChanges() {   
      int result=JOptionPane.NO_OPTION;
      if (saveEqButton.isEnabled())
        result = JOptionPane.showConfirmDialog(EquationEditor.this, "Save changes to equations?", "Save Changes",JOptionPane.YES_NO_CANCEL_OPTION);
      if (result==JOptionPane.YES_OPTION) {
        saveChanges();
        result = JOptionPane.NO_OPTION;
      }
      return result;
    }
    
    public void saveChanges() {
      newFuncTag.replace(originalFuncTag);
      String suborFunc = newFuncTag.getName();
      originalFuncTag = originalGroupTag.getTagWhere(suborFunc,"name",newFuncTag.getValue("name"));
      newFuncTag = (XMLTag) originalFuncTag.clone();
      saveEqButton.setEnabled(false);
    }

//    private JDialog window = null;
    
    public void actionPerformed(ActionEvent e) {
      
      /* General equation events */
      
      if (e.getSource()==upEquation)  {
        int index = eqJList.getSelectedIndex();
        XMLTag t1 = newFuncTag.getTag("equation",index);
        XMLTag t2 = newFuncTag.getTag("equation",index+1);
        XMLTag rt1 = (XMLTag) t1.clone();
        XMLTag rt2 = (XMLTag) t2.clone();
        rt1.replace(t2);
        rt2.replace(t1);
        eqNames.removeAllElements();
        eqList.removeAllElements();

        XMLTag[] eqs = newFuncTag.getTags("equation");
        for (int i=0; i<eqs.length; i++) {
          eqNames.add(eqs[i].getValue("name"));
          eqList.add(eqs[i].getValue("eq"));
        }
        eqJList.setListData(eqNames);
        eqJList.setSelectedIndex(index-1);
        lastEqSelected=eqJList.getSelectedIndex();
        index--;
        if (index==0) upEquation.setEnabled(false);
        updateXML();

      } else if (e.getSource()==downEquation) {
        int index = eqJList.getSelectedIndex();
        // NEXT LINE FAILS
        XMLTag t1 = newFuncTag.getTag("equation",index+1);
        XMLTag t2 = newFuncTag.getTag("equation",index+2);
        XMLTag rt1 = (XMLTag) t1.clone();
        XMLTag rt2 = (XMLTag) t2.clone();
        rt1.replace(t2);
        rt2.replace(t1);
        eqNames.removeAllElements();
        eqList.removeAllElements();
        XMLTag[] eqs = newFuncTag.getTags("equation");
        for (int i=0; i<eqs.length; i++) {
          eqNames.add(eqs[i].getValue("name"));
          eqList.add(eqs[i].getValue("eq"));
        }
        eqJList.setListData(eqNames);
        eqJList.setSelectedIndex(index+1);
        lastEqSelected=eqJList.getSelectedIndex();
        index++;
        if (index==eqs.length) downEquation.setEnabled(false);
        updateXML();

      /* BINARY WINDOW */
      
      } else if (e.getSource()==binaryTitle) {
        binaryRHS.setSelected(false);
        binaryLHS.setSelected(false);
        binaryExpand.setEnabled(false);
        if (binaryTitle.isSelected()) {
          if (equationTree.getSelectionRows()[0]==0) {
            setOpListContents(FUNCTION); 
          } else {
            XMLTag x = tagForPath();
            if (isNumerical(x.getValue("name"))) setOpListContents(NUMERICAL);
            else if (isBoolean(x.getValue("name"))) setOpListContents(BOOLEAN);
            else if (isFunction(x.getValue("name"))) setOpListContents(FUNCTION);
          }
        } else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==binaryLHS) {
        binaryRHS.setSelected(false);
        binaryTitle.setSelected(false);
        binaryExpand.setEnabled(binaryLHS.isSelected());
        if (binaryLHS.isSelected()) {
          String s = binaryLHS.getText();
          if (s.equals("varhist")) setOpListContents(VARONLY);
          else if (isBoolean(s)) setOpListContents(BOOLEAN);
          else if (isNumerical(s)) setOpListContents(NUMERICAL);
          else if (isAssign(s)) setOpListContents(VARONLY);
          else if (s.equals("ifthen")) setOpListContents(BOOLEAN);
          else setOpListContents(NONE);
        } else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==binaryRHS) {
        binaryLHS.setSelected(false);
        binaryTitle.setSelected(false);
        if (binaryRHS.isSelected()) {
          String s = binaryRHS.getText();
          if (isBoolean(s)) setOpListContents(BOOLEAN);
          else if (isNumerical(s)) setOpListContents(NUMERICAL);
          else if (isFunction(s)) setOpListContents(FUNCTION);
          else setOpListContents(NONE);
        } else setOpListContents(NONE);
        binaryExpand.setEnabled(binaryRHS.isSelected());
        updateButtons();

      } else if (e.getSource()==binarySwap) {
        String s = binaryRHS.getText();
        binaryRHS.setText(binaryLHS.getText());
        binaryLHS.setText(s);
        XMLTag x = tagForPath();
        XMLTag lhsNode = (XMLTag) x.getTag("node",1).clone();
        XMLTag rhsNode = (XMLTag) x.getTag("node",2).clone();
        x.getTag("node",2).removeFromParent();
        x.getTag("node",1).removeFromParent();
        x.addTag(rhsNode);
        x.addTag(lhsNode);
        updateTree();
      
      } else if (e.getSource()==binaryExpand) {
        equationTree.expandRow(equationTree.getSelectionRows()[0]);
        equationTree.collapseRow(equationTree.getSelectionRows()[0]+1);
        if (binaryLHS.isSelected()) {
          String s = binaryTitle.getText();
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]);
          if (isAssign(s)) {
            int op = VariableChooser.VARIABLE + VariableChooser.LOCALVAR + VariableChooser.VARVAR + VariableChooser.VARLOCAL;
            equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1);
            varChooser.show(theModel,tagForPath(),(DefaultMutableTreeNode)equationTree.getLastSelectedPathComponent(),binaryLHS,newFuncTag, originalGroupTag,op);
          } else equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1); 
        } else if ((binaryRHS.isSelected()) && (binaryRHS.getText().startsWith("var:"))) {
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+2);
          varChooser.show(theModel,tagForPath(),(DefaultMutableTreeNode)equationTree.getLastSelectedPathComponent(),binaryLHS,newFuncTag, originalGroupTag,VariableChooser.ALL);
        } else {
          equationTree.expandRow(equationTree.getSelectionRows()[0]+2);
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+2); 
        }
        updateButtons();
        updateTreeSelector(null);

      /* MULTI EXPRESSION EVENTS - and/or/mul/add */
      
      
      } else if (e.getSource()==multiTitle) {
        multiExpand.setEnabled(false);
        multiList.clearSelection();
        XMLTag x = tagForPath();        
        setOpListContents(NONE);
        if (multiTitle.isSelected()) {
          if (isBoolean(x.getValue("name"))) setOpListContents(BOOLEAN);
          else if (isNumerical(x.getValue("name"))) setOpListContents(NUMERICAL);
        }
        updateButtons();

      } else if (e.getSource()==multiExpand) {
        equationTree.expandRow(equationTree.getSelectionRows()[0]);
        int k = equationTree.getSelectionRows()[0];
        int j = multiList.getSelectedIndex()+k;
        for (int i=1; i<j; i++) equationTree.collapseRow(i+k);
        equationTree.setSelectionRow(j+1);
        equationTree.expandRow(j+1);
        updateButtons();
        updateTreeSelector(null);

      } else if (e.getSource()==multiRemove) {
        XMLTag x = tagForPath();
        int ix = multiList.getSelectedIndex()+1;
        int k = equationTree.getSelectionRows()[0];
        int j = multiList.getSelectedIndex()+k;
        for (int i=1; i<j; i++) equationTree.collapseRow(i+k);
        XMLTag y = x.getTag("node",ix);
        x.removeChild(y);
        Vector V = new Vector();
        int i=1;
        y = x.getTag("node",i);
        while (y!=null) {
          V.addElement(y.getValue());
          y = x.getTag("node",++i);
        }
        multiList.setListData(V);
        multiList.clearSelection();
        updateTree();
        updateTreeSelector(null);

      /* CONDITIONAL (3-part) */
      
      } else if (e.getSource()==ifButton) {
        elseButton.setSelected(false);
        thenButton.setSelected(false);
        ifswitchExpand.setEnabled(ifButton.isSelected() && (!ifButton.getText().startsWith("?")));
        if (ifButton.isSelected()) setOpListContents(BOOLEAN); else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==thenButton) {
        ifButton.setSelected(false);
        elseButton.setSelected(false);
        ifswitchExpand.setEnabled(thenButton.isSelected()&& (!thenButton.getText().startsWith("? Num")));
        if (thenButton.isSelected()) setOpListContents(NUMERICAL); else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==elseButton) {
        ifButton.setSelected(false);
        thenButton.setSelected(false);
        ifswitchExpand.setEnabled(elseButton.isSelected() && (!elseButton.getText().startsWith("?")));
        if (elseButton.isSelected()) setOpListContents(NUMERICAL); else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==swapIfButton) {
        String s = thenButton.getText();
        thenButton.setText(elseButton.getText());
        elseButton.setText(s);
        XMLTag x = tagForPath();
        XMLTag thenNode = (XMLTag) x.getTag("node",2).clone();
        XMLTag elseNode = (XMLTag) x.getTag("node",3).clone();
        x.getTag("node",3).removeFromParent();
        x.getTag("node",2).removeFromParent();
        x.addTag(elseNode);
        x.addTag(thenNode);
        updateTree();
      
      } else if (e.getSource()==ifswitchExpand) {
        boolean ifB = ifButton.isSelected();
        boolean elB = elseButton.isSelected();
        boolean thB = thenButton.isSelected();
        equationTree.expandRow(equationTree.getSelectionRows()[0]);
        equationTree.collapseRow(equationTree.getSelectionRows()[0]+1);
        equationTree.collapseRow(equationTree.getSelectionRows()[0]+2);
        equationTree.collapseRow(equationTree.getSelectionRows()[0]+3);
        if (ifB) { 
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1); 
          equationTree.expandRow(equationTree.getSelectionRows()[0]+1); 
        } else if (thB) { 
          String s = thenButton.getText();
          if (s.equals("? Var"))
            varChooser.show(theModel,tagForPath(),(DefaultMutableTreeNode)equationTree.getLastSelectedPathComponent(),thenButton,newFuncTag, originalGroupTag,VariableChooser.ALL);
          else {
            equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+2); 
            equationTree.expandRow(equationTree.getSelectionRows()[0]+2); 
          }
        } else if (elB) { 
          String s = elseButton.getText();
          if (s.equals("? Var"))
            varChooser.show(theModel,tagForPath(),(DefaultMutableTreeNode)equationTree.getLastSelectedPathComponent(),elseButton,newFuncTag, originalGroupTag,VariableChooser.ALL);
          else {
            equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+3); 
            equationTree.expandRow(equationTree.getSelectionRows()[0]+3); 
          }
        }
        updateButtons();
        updateTreeSelector(null);

     /* UNARY FUNCTIONS */
        
      } else if (e.getSource()==unaryTitle) {
        unaryTerm.setSelected(false);
        unaryExpand.setEnabled(false);
        if (unaryTitle.isSelected()) {
          if (isBoolean(unaryTitle.getText())) setOpListContents(BOOLEAN); 
          else if (isNumerical(unaryTitle.getText())) setOpListContents(NUMERICAL); 
          else if (isFunction(unaryTitle.getText())) setOpListContents(FUNCTION); 
          else setOpListContents(NONE);
        } else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==unaryTerm) {
        unaryTitle.setSelected(false);
        unaryExpand.setEnabled(unaryTerm.isSelected() && (!unaryTerm.getText().startsWith("? Num")));
        if (unaryTerm.isSelected()) {
          setOpListContents(NUMERICAL);
        } else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==unaryExpand) {
        equationTree.expandRow(equationTree.getSelectionRows()[0]);
        equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1);
        updateButtons();
        updateTreeSelector(null);

      /* CREATE (REPRODUCE) PANEL */
      
      
      } else if (e.getSource()==reTitle) {
        if (reTitle.isSelected()) {
          reList.clearSelection();
          reOffspring.setSelected(false);
          setOpListContents(FUNCTION);
        } else {
          setOpListContents(NONE);
        }
        updateButtons();

      } else if (e.getSource()==reOffspring) {
        if (reOffspring.isSelected()) {
          reList.clearSelection();
          reTitle.setSelected(false);
          setOpListContents(NUMERICAL);
        } else {
          setOpListContents(NONE);
        }
        reExpand.setEnabled(reOffspring.isSelected());
        updateButtons();

      } else if (e.getSource()==reRemove) {
        XMLTag x = tagForPath();
        int ix = reList.getSelectedIndex()+2;
        int k = equationTree.getSelectionRows()[0];
        int j = reList.getSelectedIndex()+k;
        for (int i=1; i<j; i++) equationTree.collapseRow(i+k);
        XMLTag y = x.getTag("node",ix);
        x.removeChild(y);
        Vector V = new Vector();
        int i=1;
        y = x.getTag("node",i);
        while (y!=null) {
          V.addElement(y.getValue());
          y = x.getTag("node",++i);
        }
        reList.setListData(V);
        reList.clearSelection();
        updateButtons();
        updateTree();
        updateTreeSelector(null);

      } else if (e.getSource()==reExpand) {
        if (reOffspring.isSelected()) {
          reOffspring.setSelected(false);
          equationTree.expandRow(equationTree.getSelectionRows()[0]);
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1);
      
        } else {
          int j = reList.getSelectedIndex();
          equationTree.expandRow(equationTree.getSelectionRows()[0]);
          int k = equationTree.getSelectionRows()[0]+2;
          j+=k;
          for (int i=k; i<j; i++) equationTree.collapseRow(i);
          equationTree.setSelectionRow(j);
          equationTree.expandRow(j);
          equationTree.setSelectionRow(j);
        }
        updateButtons();
        updateTreeSelector(null);
        
      /* CHEM?? */
      
      } else if (e.getSource()==chemFunction) {
        if (chemFunction.isSelected()) {
          setOpListContents(FUNCTION);
        } else {
          setOpListContents(NONE);
        }
        updateButtons();

        
      } else if (e.getSource()==chemExpand) {
        if (chemAmount.isSelected()) {
          chemAmount.setSelected(false);
          equationTree.expandRow(equationTree.getSelectionRows()[0]);
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1);
          updateButtons();
          updateTreeSelector(null);
        }

      } else if (e.getSource()==chemDropDown) {
        if (!dontUpdateVal) {
          XMLTag x = tagForPath();
          if (chemDropDown.getSelectedIndex()==-1) chemDropDown.setSelectedIndex(0);
          if (x.getTag("chem")==null) x.addTag(new XMLTag("chem"));
          x.getTag("chem").setValue((String)chemDropDown.getSelectedItem());
          saveEqButton.setEnabled(true);
          updateButtons();
          updateTree();
        }

      /* RELEASE/UPTAKE */
      
      } else if (e.getSource()==chemAmount) {
        if (chemAmount.isSelected()) {
          setOpListContents(NUMERICAL);
          chemExpand.setEnabled(true);
        } else {
          setOpListContents(NONE);
          chemExpand.setEnabled(false);
        }

      /* UNKNOWN COMPONENT */
      
      } else if (e.getSource()==ucButton) {
        ucButton.setSelected(true);
        updateButtons();

      /* ABSOLUTE VALUE */
      
      } else if (e.getSource()==moreValExp) {
        valExpInt++;
        valExp.setText(String.valueOf(valExpInt));
        XMLTag x = tagForPath();
        x.getTag("exp").setValue(String.valueOf(valExpInt));
        updateTree();

      } else if (e.getSource()==valUnit) {
        theUnitEditor.setUnitEditor(valUnitString,theModel);
        theUnitEditor.setVisible(true);
        valUnitString = theUnitEditor.getUnit();
        valUnit.setEquation("\\unit{"+valUnitString+"}");
        XMLTag x = tagForPath();
        x.getTag("unit").setValue(valUnitString);
        updateTree();
        valUnit.paint(valUnit.getGraphics());
      
      } else if (e.getSource()==lessValExp) {
        valExpInt--;
        valExp.setText(String.valueOf(valExpInt));
        XMLTag x = tagForPath();
        x.getTag("exp").setValue(String.valueOf(valExpInt));
        updateTree();

      } else if (e.getSource()==valButton) {
        if (valButton.isSelected()) setOpListContents(NUMERICAL);
        else setOpListContents(NONE);

      /* OP BUTTONS */
      
      } else if (e.getSource()==addOp) {
        String s = (String) opJList.getSelectedValue();
        if (editingMode==FUNCTION) {
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",s);
          fixNewTag(s,newTag,null);
          currentEquation = newTag;
          InitialiseEquationTree(currentEquation);
          equationTree.setSelectionRow(0);
          updateTreeSelector(equationTree.getSelectionPath());

          if (s.equals("while")) {
            setOpListContents(ASSIGN_ONLY);
            opJList.setSelectedIndex(0);
            updateButtons();
          } 

        } else if (editingMode==MULTI) {
          XMLTag x = tagForPath();
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",s);
          x.addTag(newTag);
          fixNewTag(s,newTag,null);
          Vector V = new Vector();
          XMLTag y[] = x.getTags("node");
          for (int i=0; i<y.length; i++) V.addElement(y[i].getValue("name"));
          multiList.setListData(V);
          multiList.clearSelection();

        } else if (editingMode==REPRODUCE ) {
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name","set");
          newTag.addTag("node").addTag("name","? Var");
          newTag.addTag("node").addTag("name","? Num");
          XMLTag x = tagForPath();
          x.addTag(newTag);
          Vector V = new Vector();
          XMLTag y[] = x.getTagsWhere("node","name","set");
          for (int i=0; i<y.length; i++) V.addElement(y[i].getValue("node/name"));
          reList.setListData(V);
          reList.setSelectedIndex(reList.getModel().getSize()-1);


        } else if (editingMode==WHILE) {
          XMLTag x = tagForPath();
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",s);
          x.addTag(newTag);
          fixNewTag(s,newTag,null);
          Vector V = new Vector();
          int i=1;
          XMLTag y = x.getTag("node",i);
          while (y!=null) {
            V.addElement(y.getValue());
            y = x.getTag("node",++i);
          }
          statList.setListData(V);
          V.removeAllElements();
          statList.clearSelection();
        }
        updateTree();
    
      } else if (e.getSource()==surroundOp) {
        XMLTag x = tagForPath();
        surroundObject(x);
        updateTree();
        updateTreeSelector(equationTree.getSelectionPath());
      
      } else if (e.getSource()==unSurroundOp) {
        XMLTag x = tagForPath();
        unSurroundObject(x);
        updateTree();
        updateTreeSelector(equationTree.getSelectionPath());

        
      /* REPLACE OP BUTTON */  
      } else if (e.getSource()==replaceOp) {
        XMLTag x = tagForPath();
        String opV = (String)opJList.getSelectedValue();
        if (editingMode==WHILE) { // Must be while button or condition!
          if (condition.isSelected()) {  // In while mode, replace the condition...
            XMLTag y = x.getTag("node",1);
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            ifButton.setText(opV);
            updateTree();
            updateTreeSelector(equationTree.getSelectionPath());
          } else if (whileButton.isSelected()) {
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,x);
            if (x==null) {
              currentEquation = newTag;
              InitialiseEquationTree(currentEquation);
            } else if (x.getParentTag()==null) {
              currentEquation = newTag;
              InitialiseEquationTree(currentEquation);
            } else {
              newTag.replace(x);
            }
          }

        } else if (editingMode==MULTI) {
          if (multiList.getSelectedIndex()>=0) {
            int ml = multiList.getSelectedIndex();
            XMLTag y = x.getTag("node",1+multiList.getSelectedIndex());
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            Vector V = new Vector();
            int i=1;
            y = x.getTag("node",i);
            while (y!=null) {
              V.addElement(y.getValue());
              y = x.getTag("node",++i);
            }
            multiList.setListData(V);
            multiList.clearSelection();  
            multiTitle.setSelected(false);
            setOpListContents(NONE);
            multiList.setSelectedIndex(ml);
            
          } else {
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,x);
            newTag.replace(x);
            multiTitle.setText(opV);
            multiTitle.setSelected(false);
            setOpListContents(NONE);
          }
        } else if (editingMode==IFSWITCH) {
          if (ifButton.isSelected()) {
            XMLTag y = x.getTag("node",1);
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            ifButton.setText(opV);
          } else if (thenButton.isSelected()) {
            XMLTag y = x.getTag("node",2);
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            thenButton.setText(opV);
          } else if (elseButton.isSelected()) {
            XMLTag y = x.getTag("node",3);
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            elseButton.setText(opV);
          }
        } else if (editingMode==BINARY) {
          if (binaryTitle.isSelected()) {
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,x);
            if (x==null) {
              currentEquation = newTag;
              InitialiseEquationTree(currentEquation);
            } else if (x.getParentTag()==null) {
              currentEquation = newTag;
              InitialiseEquationTree(currentEquation);
            } else {
              newTag.replace(x);
            }
            binaryTitle.setText(opV);
          } else if (binaryLHS.isSelected()) {
            XMLTag y = x.getTag("node",1);
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            binaryLHS.setText(opV);
          } else if (binaryRHS.isSelected()) {
            XMLTag y = x.getTag("node",2);
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            binaryRHS.setText(opV);
          }
        } else if (editingMode==UNARY) {
          if (unaryTitle.isSelected()) {
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,x);
           
            if (x==null) {
              currentEquation = newTag;
              InitialiseEquationTree(currentEquation);
            } else if (x.getParentTag()==null) {
              currentEquation = newTag;
              InitialiseEquationTree(currentEquation);
            } else {
              newTag.replace(x);
            }
            unaryTitle.setText(opV);
          } else if (unaryTerm.isSelected()) {
            XMLTag y = x.getTag("node",1);
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,y);
            newTag.replace(y);
            unaryTerm.setText(opV);
          }
        } else if (editingMode==VARONLY) {
          if (varButton.isSelected()) {
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,x);
            newTag.replace(x);
            varButton.setText(opV);
          }
        } else if (editingMode==VALONLY) {
          if (valButton.isSelected()) {
            XMLTag newTag = new XMLTag("node");
            newTag.addTag("name",opV);
            fixNewTag(opV,newTag,x);
            newTag.replace(x);
            valButton.setText(opV);
          }
        } else if (editingMode==ONLY_OPTION) {
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",opV);
          fixNewTag(opV,newTag,x);
          newTag.replace(x);
          ucButton.setText(opV);

        } else if (editingMode==REPRODUCE) {
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",opV);
          if (reTitle.isSelected()) {
            fixNewTag(opV,newTag,x);
            newTag.replace(x);
          } else if (reOffspring.isSelected()) {
            x = x.getTag("node",1);
            fixNewTag(opV,newTag,x);
            newTag.replace(x);
          }

        } else if (editingMode==INGESTION) {
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",opV);
          fixNewTag(opV,newTag,x);
          if (x==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else if (x.getParentTag()==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else {
            newTag.replace(x);
          }
        
        } else if (editingMode==CHANGE_STATE) {
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",opV);
          fixNewTag(opV,newTag,x);
          if (x==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else if (x.getParentTag()==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else {
            newTag.replace(x);
          }
          
        } else if (editingMode==CHANGE_CHANCE) {
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",opV);
          fixNewTag(opV,newTag,x);
          if (x==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else if (x.getParentTag()==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else {
            newTag.replace(x);
          }
          
        } else if (editingMode==REMIN_UPTAKE) {
          chemFunction.setSelected(false);
          XMLTag newTag = new XMLTag("node");
          newTag.addTag("name",opV);
          fixNewTag(opV,newTag,x);
          if (x==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else if (x.getParentTag()==null) {
            currentEquation = newTag;
            InitialiseEquationTree(currentEquation);
          } else {
            newTag.replace(x);
          }
          updateButtons();
        }
        updateTree();
        updateTreeSelector(equationTree.getSelectionPath());
      
      /* VAR PANEL */
      
      } else if (e.getSource()==varButton) {
        if (varButton.isSelected()) {
          XMLTag x = tagForPath();
          TreePath tpThis = equationTree.getSelectionPaths()[0];
          TreePath tpOneUp = equationTree.getPathForRow(equationTree.getSelectionRows()[0]-1);
          boolean lefty = (tpThis.getPath().length!=tpOneUp.getPath().length);
          boolean assignment = isAssign(x.getParentTag().getValue("name"));
          if ((assignment) && (lefty)) {
            setOpListContents(VARONLY);
          } else setOpListContents(NUMERICAL);
        } else setOpListContents(NONE);

      } else if (e.getSource()==varNameButton) {
        String parent = tagForPath().getParentTag().getValue("name");
        if (parent.equals("assign")) {
          XMLTag[] assignNodes = tagForPath().getParentTag().getTags("node");
          int op;
          if (assignNodes[0]==tagForPath()) { // Assign left hand side
            op = VariableChooser.VARIABLE + VariableChooser.LOCALVAR + VariableChooser.VARVAR + VariableChooser.VARLOCAL;
          } else { // Assign right hand side.
            op = VariableChooser.ALL;
          }
          varChooser.show(theModel,tagForPath(),(DefaultMutableTreeNode)equationTree.getLastSelectedPathComponent(),varNameButton,newFuncTag, originalGroupTag, op);
        } else varChooser.show(theModel,tagForPath(),(DefaultMutableTreeNode)equationTree.getLastSelectedPathComponent(),varNameButton,newFuncTag, originalGroupTag, VariableChooser.ALL);
        updateTree();
        
      } else if (e.getSource()==backEqButton) {
        if (checkSaveChanges()==JOptionPane.NO_OPTION) {
          eqJList.setListData(new Vector());
          opJList.setListData(new Vector());
          lastEqSelected=-1;
          setVisible(false);
        }

      } else if (e.getSource()==saveEqButton) {
        saveChanges();
   
      } else if (e.getSource()==addEquation) {
        String desc = JOptionPane.showInputDialog("Please enter the name for the rule");
        if ((desc!=null) && (desc.trim().length()>0)) {
          eqList.addElement("");
          eqNames.addElement(desc);
          Vector V = new Vector();
          for (int i=0; i<eqNames.size(); i++) V.addElement(eqNames.elementAt(i));
          eqJList.setListData(V);
          eqJList.setSelectedIndex(eqList.size()-1);
          currentEquation = null;
          currentEqString = "";
          updateButtons();
          InitialiseEquationTree(currentEquation);
          equationTree.setSelectionRows(new int[] {0});
          updateTreeSelector(null);
          setEquation("");
          XMLTag newEq = new XMLTag("equation");
          newEq.addTag("name",desc);
          newEq.addTag("eq","");
          newFuncTag.addTag(newEq);
          updateXML();
        }
      } else if (e.getSource()==removeEquation) {
        int i = eqJList.getSelectedIndex();
        String n = (String) eqJList.getSelectedValue();
        eqList.remove(i);
        eqNames.remove(i);
        newFuncTag.getTagWhere("equation","name",n).removeFromParent();
        if (i>=eqNames.size()) i = eqNames.size()-1;
        if (i>=0) {
          lastEqSelected = i;
          eqJList.setListData(eqNames);
          eqJList.setSelectedIndex(i);
          setEquation((String) eqList.elementAt(i));
        } else {
          lastEqSelected = -1;
          setEquation("");
          eqJList.setListData(eqNames);
        } 
        updateXML();
       
      } else if (e.getSource()==unitCheck) {
        theUnitEditor.checkStatement(currentEqString,theModel,originalGroupTag,originalFuncTag);
      
      } else if (e.getSource()==renameEquation) {
        String OldName = (String)eqJList.getSelectedValue();
        String NewName = JOptionPane.showInputDialog("Please enter the new name for " + OldName, OldName);
        while(NewName != null) {
          if (eqNames.indexOf(NewName) == -1) {
            int OldSelection = eqJList.getSelectedIndex();
            eqNames.setElementAt(NewName, OldSelection);
            newFuncTag.getTagWhere("equation","name",OldName).getTag("name").setValue(NewName);
            //xmlFile.getTagWhere(lookupBaseType(), "name", OldName).setValue("name", NewName);
            eqJList.setListData(eqNames);
            eqJList.ensureIndexIsVisible(OldSelection);
            eqJList.setSelectedIndex(OldSelection);
            //instanceListHandler();
            NewName = null;
            updateXML();
          }
          else {
            NewName = JOptionPane.showInputDialog("Sorry " + NewName + " is already taken.\nPlease enter a different name for " + OldName, NewName);
          }
        }
      } else if (e.getSource()==copyEquation) {
        String OldName = (String)eqJList.getSelectedValue();
        String NewName = JOptionPane.showInputDialog("Please enter the new name for the copy of " + OldName, OldName);
        while(NewName != null) {
          if (eqNames.indexOf(NewName) == -1) {
            eqNames.addElement(NewName);
            eqList.addElement(newFuncTag.getTagWhere("equation","name",OldName).getValue("eq"));
            XMLTag theClone = (XMLTag) newFuncTag.getTagWhere("equation","name",OldName).clone();
            theClone.setValue("name",NewName);
            newFuncTag.addTag(theClone);
            eqJList.setListData(eqNames);
            eqJList.ensureIndexIsVisible(eqNames.size()-1);
            eqJList.setSelectedIndex(eqNames.size()-1);
            NewName = null;
            updateXML();
          }
          else {
            NewName = JOptionPane.showInputDialog("Sorry " + NewName + " is already taken.\nPlease enter a different name for " + OldName, NewName);
          }
        }
      } else if (e.getSource()==whileButton) {
        condition.setSelected(false);
        statList.clearSelection();
        statExpand.setEnabled(false);
        if (whileButton.isSelected()) {
          setOpListContents(FUNCTION);
          updateButtons();
        } else {
          setOpListContents(ASSIGN_ONLY);
          opJList.setSelectedIndex(0);
          updateButtons();
        }

      } else if (e.getSource()==condition) {
        whileButton.setSelected(false);
        statList.clearSelection();
        if (condition.isSelected()) {
          statExpand.setEnabled(true);
          setOpListContents(BOOLEAN);
          updateButtons();
        } else {
          statExpand.setEnabled(false);          
          setOpListContents(ASSIGN_ONLY);
          opJList.setSelectedIndex(0);
          updateButtons();
        }
       
      } else if (e.getSource()==statExpand) {
        equationTree.expandRow(equationTree.getSelectionRows()[0]);
        int k = equationTree.getSelectionRows()[0];
        int j = statList.getSelectedIndex()+k+2;
        for (int i=1; i<j; i++) equationTree.collapseRow(i+k);
        equationTree.setSelectionRow(j);
        updateButtons();

      } else if (e.getSource()==remStat) {
        XMLTag x = tagForPath();
        int ix = statList.getSelectedIndex()+1;
        int k = equationTree.getSelectionRows()[0]+1;
        int j = ix+k;
        for (int i=1; i<j; i++) equationTree.collapseRow(i+k);
        XMLTag y = x.getTag("node",ix);
        x.removeChild(y);
        Vector V = new Vector();
        int i=2;
        y = x.getTag("node",i);
        while (y!=null) {
          V.addElement(y.getValue());
          y = x.getTag("node",++i);
        }
        statList.setListData(V);
        statList.clearSelection();
        remStat.setEnabled(false);
        statExpand.setEnabled(false);
        updateButtons();
        updateTree();
      
      } else if (e.getSource()==changeStateTitle) {
        if (changeStateTitle.isSelected()) {
          setOpListContents(FUNCTION); 
        } else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==changeChanceTitle) {
        changeChanceValue.setSelected(false);
        if (changeChanceTitle.isSelected()) {
          setOpListContents(FUNCTION); 
        } else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==changeChanceValue) {
        changeChanceTitle.setSelected(false);
        changeChanceExpand.setEnabled(changeChanceValue.isSelected());
        if (changeChanceExpand.isSelected()) setOpListContents(NUMERICAL);
        else setOpListContents(NONE);
        updateButtons();

      } else if (e.getSource()==changeChanceExpand) {
        equationTree.expandRow(equationTree.getSelectionRows()[0]);
        equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1);
        updateButtons();
        updateTreeSelector(null);

      } else if ((e.getSource()==changeChanceList) && (lockEvents==0)) {
        XMLTag x = tagForPath();
        x.getTag("stage").setValue(changeChanceList.getSelectedItem().toString());
        saveEqButton.setEnabled(true);
        updateButtons();
        updateTree();

      } else if ((e.getSource()==changeStateList) && (lockEvents==0)) {
        XMLTag x = tagForPath();
        x.getTag("stage").setValue(changeStateList.getSelectedItem().toString());
        saveEqButton.setEnabled(true);     
        updateButtons();
        updateTree();
    
      } else if ((e.getSource()==reproduceStateList) && (lockEvents==0)) {
        XMLTag x = tagForPath();
        x.getTag("stage").setValue(reproduceStateList.getSelectedItem().toString());
        saveEqButton.setEnabled(true);     
        updateButtons();
        updateTree();
      
      /* FOOD-SET PANEL */
      
      } else if (e.getSource()==ingTitle) {
        ingThresh.setSelected(false);
        ingRate.setSelected(false);
        ingExpand.setEnabled(false);
        if (ingTitle.isSelected()) setOpListContents(FUNCTION);
        else setOpListContents(NONE);
      
      } else if (e.getSource()==ingFoodSet) {
        equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+1);
        varChooser.show(theModel,tagForPath(),(DefaultMutableTreeNode)equationTree.getLastSelectedPathComponent(),ingFoodSet,newFuncTag, originalGroupTag,VariableChooser.VARCONC);
        equationTree.setSelectionRow(equationTree.getSelectionRows()[0]-1);        

      } else if (e.getSource()==ingThresh) {
        if (ingThresh.isSelected()) ingRate.setSelected(false);
        updateButtons();
        
      } else if (e.getSource()==ingRate) {
        if (ingRate.isSelected()) ingThresh.setSelected(false);
        updateButtons();

      } else if (e.getSource()==ingExpand) {
        if (ingThresh.isSelected()) {
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+2);
          updateTreeSelector(equationTree.getSelectionPath());
        } else if (ingRate.isSelected()) {
          equationTree.setSelectionRow(equationTree.getSelectionRows()[0]+3);
          updateTreeSelector(equationTree.getSelectionPath());
        }
      }
    }
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}

    public void caretUpdate(CaretEvent e) {
      if (e.getSource()==valMain) {
        if (!dontUpdateVal) {
          XMLTag x = tagForPath();
          if ((x!=null) && (x.getValue("sival")!=null)) {
            boolean updateMe = (!x.getValue("sival").equals(valMain.getText()));
            x.getTag("sival").setValue(valMain.getText());
            if (updateMe) updateTree();
          }
        }
      }
    }

    public void keyReleased(KeyEvent e) {
      if (e.getSource()==valMain) {
        if ((!dontUpdateVal)&&(e.getKeyCode()==KeyEvent.VK_ENTER)) {
          XMLTag x = tagForPath();
          boolean updateMe = (!x.getValue("sival").equals(valMain.getText()));
          x.getTag("sival").setValue(valMain.getText());
         if (updateMe) updateTree();
        }
      } else if (e.getSource()==equationTree) {
        TreePath treeSelPath = equationTree.getSelectionPath();
        if (treeSelPath!=null) updateTreeSelector(treeSelPath);
      }
    }

    public void focusGained(FocusEvent e) {}

    public void valueChanged(ListSelectionEvent e) {
      if (e.getSource()==multiList) {
        multiExpand.setEnabled(multiList.getSelectedIndex()>=0);
        XMLTag x = tagForPath();
        int index = opJList.getSelectedIndex();
        if (x==null) setOpListContents(NONE); 
        else if (isBoolean(x.getValue("name"))) setOpListContents(BOOLEAN);
        else if (isNumerical(x.getValue("name"))) setOpListContents(NUMERICAL);
        opJList.setSelectedIndex(index);
        updateButtons();

      } else if (e.getSource()==reList) {
        reTitle.setSelected(!(reList.getSelectedIndex()>=0));
        reOffspring.setSelected(!(reList.getSelectedIndex()>=0));
        reExpand.setEnabled(reList.getSelectedIndex()>=0);
        updateButtons();

      } else if (e.getSource()==opJList) {
        if (e.getValueIsAdjusting()) {
          updateButtons();
        }

      } else if (e.getSource()==eqJList) {
        if ((eqJList.isSelectionEmpty()) && (eqList.size()>0)) eqJList.setSelectedIndex(lastEqSelected);
        upEquation.setEnabled((eqJList.getSelectedIndex()>0));
        downEquation.setEnabled((eqJList.getSelectedIndex()<eqJList.getModel().getSize()-1));
        lastEqSelected=eqJList.getSelectedIndex();
        if (lastEqSelected>=0) {
          setEquation((String) eqList.elementAt(lastEqSelected));
          TreePath treeSelPath = equationTree.getSelectionPath();
          if (treeSelPath!=null) updateTreeSelector(treeSelPath);
        }
 
        else setEquation("");

      } else if (e.getSource()==statList) {
        whileButton.setSelected(false);
        condition.setSelected(false);
        statExpand.setEnabled(statList.getSelectedIndices().length==1);
        remStat.setEnabled(!statList.isSelectionEmpty());
        setOpListContents(ASSIGN_ONLY);
        opJList.setSelectedIndex(0);
        updateButtons();

      }
    }
    
    
    public void keyPressed(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    
    public void mouseReleased(MouseEvent e) {
      if (e.getSource()==equationTree) {
        TreePath treeSelPath = equationTree.getClosestPathForLocation(e.getX(), e.getY());
        if (treeSelPath!=null) updateTreeSelector(treeSelPath);
      }
    }
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
      if (objectID==eqBuilderWC) {
        if (checkSaveChanges()==1) {
          eqJList.setListData(new Vector());
          opJList.setListData(new Vector());
          lastEqSelected=-1;
          setVisible(false);
        }
      }
    }
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
  }

  private void InitialiseEquationTree(XMLTag root) {
    if ((root!=null) && (root.getTag("name")!=null)) {
      equationNode = new DefaultMutableTreeNode(root.getValue("name"));
      XMLTag[] children = root.getTags();
      for (int i=0; i<children.length; i++) {
        if (!children[i].getName().equals("name")) {
          InitialiseEquationTree(children[i],equationNode);
        }
      }
      ((DefaultTreeModel)equationTree.getModel()).setRoot(equationNode);
    } else {
      equationNode = null;
      ((DefaultTreeModel)equationTree.getModel()).setRoot(equationNode);
    }
  }
  
  private static void InitialiseEquationTree(XMLTag root, DefaultMutableTreeNode dmtn) {
    if (root.getTag("name")!=null) {
      DefaultMutableTreeNode addThis = new DefaultMutableTreeNode(root.getValue("name"));
      XMLTag[] children = root.getTags();
      for (int i=0; i<children.length; i++) {
        if (!children[i].getName().equals("name")) {
          InitialiseEquationTree(children[i],addThis);
        }
      }
      dmtn.add(addThis);
    }
  }

  private XMLTag tagForPath() {
    TreePath tp = equationTree.getSelectionPath();
    XMLTag t = currentEquation;
    if (tp!=null) {
      String[] sa = tp.toString().substring(1,tp.toString().length()-1).split(",");
      
      int[] childIndexes = new int[sa.length];
      int rowNo = equationTree.getRowForPath(tp);
      int pathSize = equationTree.getPathForRow(rowNo).getPath().length;
      int pathPointer = sa.length-1;
      int nodeCount = 0;
      while (rowNo>0) {
        rowNo--;
        nodeCount++;
        if (equationTree.getPathForRow(rowNo).getPath().length<pathSize) {
          pathSize = equationTree.getPathForRow(rowNo).getPath().length;
          childIndexes[pathPointer]=nodeCount;
          nodeCount=0;
          pathPointer--;
        }
      }
 
      for (int i=1; i<sa.length; i++) t = t.getTag("node",childIndexes[i]);
      return t;
    } else return null;
  }
}

