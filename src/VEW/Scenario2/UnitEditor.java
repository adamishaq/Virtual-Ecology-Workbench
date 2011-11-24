package VEW.Scenario2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import VEW.Common.StringTools;
import VEW.Common.VEWUtilities;
import VEW.Common.XML.XMLTag;

public class UnitEditor extends JDialog {

  private JList unitList = new JList();
  private Vector unitListData = new Vector();
  private Vector realListData = new Vector();
  private JButton okButton = new JButton("OK");
  private JButton cancelButton = new JButton("Cancel");
  private JComboBox preUnit = new JComboBox();
  private JComboBox theUnit = new JComboBox();
  private JLabel expUnit = new JLabel("1");
  private JButton addButton = new JButton("Add");
  private JButton remButton = new JButton("Del");
  private JButton lessExp = new JButton("<");
  private JButton moreExp = new JButton(">");
  private JEditorPane eqPanel = new JEditorPane();
  private int exp=1;
  private String ErrorLog;
  private EventHandler eh = new EventHandler();


  private static boolean[] unitUsed = new boolean[] {};
  private static String[] preFixes = new String[] {"zepto","atto","femto","pico","nano","micro","milli","centi","deci","-","deca","hecto","kilo","Mega","Giga","Terra","Peta","Exa","Zetta"};
 
  private static ArrayList Units = new ArrayList();
  private static String finalString = "";

  public UnitEditor(JDialog parent) { 
    super(parent,"Unit Editor",true);
    eqPanel.setContentType("text/html");
    eqPanel.setText("<html></html>");
    eqPanel.setEditable(false);
    theUnit.removeAllItems();
    preUnit.removeAllItems();
    refreshMenuAndEq();
    for (int i=0; i<preFixes.length; i++) { preUnit.addItem(preFixes[i]); }
    setSize(400,300);
    this.getContentPane().setLayout(new BorderLayout(2,2));
    JPanel units = new JPanel(new BorderLayout(1,1));
    JPanel expPanel = new JPanel(new BorderLayout(1,1));
    expPanel.add(lessExp,"West");
    expPanel.add(moreExp,"East");
    expPanel.add(expUnit,"Center");
    units.add(expPanel,"East");
    units.add(theUnit,"Center");
    units.add(preUnit,"West");
    this.getContentPane().add(units,"North");
    JScrollPane unitScroller = new JScrollPane(unitList);
    unitList.setPreferredSize(new Dimension(150,400));
    unitScroller.setPreferredSize(new Dimension(180,250));
    this.getContentPane().add(unitScroller,"Center");
    JPanel frameBottom = new JPanel(new BorderLayout(2,2));
    frameBottom.add(eqPanel,"Center");
    frameBottom.add(addButton,"West");
    frameBottom.add(remButton,"East");
    JPanel okCancelButtons = new JPanel(new BorderLayout(2,2));
    okCancelButtons.add(okButton,"West");
    okCancelButtons.add(cancelButton,"East");
    frameBottom.add(okCancelButtons,"South");
    this.getContentPane().add(frameBottom,"South");
    expUnit.setPreferredSize(new Dimension(30,25));
    expUnit.setHorizontalAlignment(SwingConstants.CENTER);

    remButton.setEnabled(false);
    preUnit.setSelectedIndex(4);
    moreExp.addActionListener(eh);
    lessExp.addActionListener(eh);
    addButton.addActionListener(eh);
    remButton.addActionListener(eh);
    unitList.addMouseListener(eh);
    unitList.addKeyListener(eh);
    okButton.addActionListener(eh);
    cancelButton.addActionListener(eh);

  }
  
  public void InitialiseUnitInfo(XMLTag Model) {
    String[] U = new String[15+2*(Model.getTags("chemical").length)];
    int cc = 0;
    U[cc++] = "Celsius|C";
    U[cc++] = "Cells|c";
    U[cc++] = "days|d";
    U[cc++] = "Einsteins|E";
    U[cc++] = "grams|g";
    XMLTag[] chemicals = Model.getTags("chemical");
     int c = chemicals.length;
    for (int i=0; i<chemicals.length; i++) U[cc+i] = "g"+chemicals[i].getValue("name")+"|g"+chemicals[i].getValue("name");
    U[(cc++)+c] = "hours|h"; 
    U[(cc++)+c] = "Joules|J"; 
    U[(cc++)+c] = "Kelvin|K"; 
    U[(cc++)+c] = "Kilograms|kg"; 
    U[(cc++)+c] = "Litres|l";
    U[(cc++)+c] = "metres|m"; 
    U[(cc++)+c] = "mol|mol";
    for (int i=0; i<chemicals.length; i++) U[cc+c+i] = "mol"+chemicals[i].getValue("name")+"|mol"+chemicals[i].getValue("name");
    U[(cc++)+c+c] = "seconds|s"; 
    U[(cc++)+c+c] = "Timestep|ts"; 
    U[(cc++)+c+c] = "Watts|W"; 
    Arrays.sort(U,0,U.length, new CaseInsensitiveComparator());
    Units = new ArrayList();
    unitUsed = new boolean[U.length];
    for (int i=0; i<U.length; i++) {
      Units.add(U[i]);
      unitUsed[i]=false;
    }
  }

  
  public static String[] intoTriplets(String u) {
    int countCommas = 0;
    String tempu = u;
    while (tempu.indexOf(",")>=0) {
      countCommas++;
      tempu = tempu.substring(tempu.indexOf(",")+1);
    }
    String[] triplets = new String[(countCommas+1)/3];
    for (int i=0; i<triplets.length; i++) {
      triplets[i]=u.substring(0,u.indexOf(",")+1);
      u = u.substring(u.indexOf(",")+1);
      triplets[i]=triplets[i]+u.substring(0,u.indexOf(",")+1);
      u = u.substring(u.indexOf(",")+1);
      if (u.indexOf(",")>0) {
        triplets[i] = triplets[i]+u.substring(0,u.indexOf(","));
        u = u.substring(u.indexOf(",")+1);
      } else triplets[i] = triplets[i]+u;
    }
    return triplets;
  }

   public static String combine(String[] u) {
    String s = "";
    for (int i=0; i<u.length; i++) if (!u[i].startsWith("X")) s+=u[i]+",";
    if (s.endsWith(",")) s = s.substring(0,s.length()-1);
    return s;
  }


  public void main(String args[]) {
    String u = "0,m,1,0,m,2,0,C,1";
    System.out.println(order(u));
    String u1 = "0,W,1,0,m,-2";
    String u2 = "0,m,-1,0,m,-1,0,s,-1,0,J,1";
    System.out.println(equivalent(u1,u2));
  }
  
  public static String inverse(String u) {
    String[] u2 = intoTriplets(u);
    for (int i=0; i<u2.length; i++) {
      String tempu = u2[i];
      String pre = tempu.substring(0,tempu.indexOf(","));
      tempu = tempu.substring(tempu.indexOf(",")+1);
      String unit = tempu.substring(0,tempu.indexOf(","));
      tempu = tempu.substring(tempu.indexOf(",")+1);
      if (tempu.startsWith("-")) tempu=tempu.substring(1); else tempu = "-"+tempu;
      u2[i]=pre+","+unit+","+tempu;
    }
    return combine(u2);
  }
  
  public static String reduce(String u) {
    String[] u2 = intoTriplets(u);
    for (int i=0; i<u2.length; i++) {
      if (!u2[i].startsWith("X")) {
        String tempu = u2[i];
        String pre = tempu.substring(0,tempu.indexOf(","));
        tempu = tempu.substring(tempu.indexOf(",")+1);
        String unit = tempu.substring(0,tempu.indexOf(","));
        tempu = tempu.substring(tempu.indexOf(",")+1);
        int power = Integer.parseInt(tempu);
        if (i<u2.length-1) {
          for (int j=i+1; j<u2.length; j++) {
            String _tempu = u2[j];
            String _pre = _tempu.substring(0,_tempu.indexOf(","));
            _tempu = _tempu.substring(_tempu.indexOf(",")+1);
            String _unit = _tempu.substring(0,_tempu.indexOf(","));
            _tempu = _tempu.substring(_tempu.indexOf(",")+1);
            if ((_unit.equals(unit)) && (_pre.equals(pre))) {
              power += Integer.parseInt(_tempu);
              u2[j]="X"+u2[j];
            }
          }
        }
        u2[i] = pre+","+unit+","+power;
        if (power==0) u2[i]="X"+u2[i];
      }
    }
    return combine(u2);
  }
  
  public static String conversions(String u) {
    String[] u2 = intoTriplets(u);
    for (int i=0; i<u2.length; i++) {
      String tempu = u2[i];
      tempu = tempu.substring(tempu.indexOf(",")+1);
      String unit = tempu.substring(0,tempu.indexOf(","));
      tempu = tempu.substring(tempu.indexOf(",")+1);
      int power = Integer.parseInt(tempu);
      
      if (unit.equals("W")) { // Watt = Joule/second
        u2[i]="0,J,"+power+",0,s,"+(-power);
        u = combine(u2);
        u2 = intoTriplets(u);
      }
    }
    return combine(u2);
  }

  public static String order(String u) {
    String[] u2 = intoTriplets(u);
    String[] u3 = new String[u2.length];
    String first;
    int index;
    for (int i=0; i<u2.length; i++) {
      int j=0;
      while (u2[j].startsWith("X")) j++;
      String tempu = u2[j];
      tempu = tempu.substring(tempu.indexOf(",")+1);
      first = tempu.substring(0,tempu.indexOf(","));
      index = j;
      while (j<u2.length) {
        if (!u2[j].startsWith("X")) {
          String _tempu = u2[j];
          _tempu = _tempu.substring(_tempu.indexOf(",")+1);
          String _comp = _tempu.substring(0,_tempu.indexOf(","));
          if (_comp.compareTo(first)<0) {
            first=_comp;
            index=j;
          }
        }
        j++;
      }
      u3[i]=u2[index];
      u2[index]="X"+u2[index];
    }
    return combine(u3);
  }

  public boolean equivalent(String u1, String u2) {
    return (order(reduce(conversions(u1))).equals(order(reduce(conversions(u2)))));
  }
  
  
  public String sameUnits(String s, XMLTag model, XMLTag group, XMLTag function) {
    String[] items = StringTools.nestedSplit(s,',');
    String firstUnits = unitsOf(items[0],model,group,function);
    for (int i=1; i<items.length; i++) {
      String otherUnits = unitsOf(items[i],model,group,function);
      if (!equivalent(firstUnits,otherUnits))
        ErrorLog+="Error - "+otherUnits+" doesn't match item "+i+", ("+firstUnits+") in "+s+" \n";
    }
    return firstUnits;
  }
 
  public String unitsOf(String s, XMLTag model, XMLTag group, XMLTag function) {
    if (StringTools.chomp(s,"\\var{")) {
      String varName = StringTools.spit(s,"\\var{");
      XMLTag v = null;
      if (function!=null) {
        v = function.getTagWhere("localvar","name",varName);
        if (v==null) function.getTagWhere("varietylocal","name",varName);
      }
      if (group!=null) if (v==null) v = group.getTagWhere("*","name",varName);
      if ((v==null) && (group.getTag("predator")!=null) && (group.getValue("predator").equals("true"))) 
        v = model.getTag("closure").getTagWhere("predator","name",group.getValue("name")).getTagWhere("*","name",varName);
      String units = v.getValue("unit");
      return units;
    } 
    else if (StringTools.chomp(s,"\\val{")) {
      String[] leftAndRight = StringTools.nestedSplit(StringTools.spit(s,"\\val"),',');
      return StringTools.spit(leftAndRight[1],"\\units{");
    }

    else if (StringTools.chomp(s,"\\minus{")) return unitsOf(StringTools.spit(s,"\\minus{"),model,group,function);
    else if (StringTools.chomp(s,"\\abs{")) return unitsOf(StringTools.spit(s,"\\abs{"),model,group,function);
    else if (StringTools.chomp(s,"\\rnd{")) return unitsOf(StringTools.spit(s,"\\rnd{"),model,group,function);
    else if (StringTools.chomp(s,"\\depthForVI{")) return "0,m,1";
    else if (StringTools.chomp(s,"\\depthForFI{")) return "0,m,1";
    else if (StringTools.chomp(s,"\\temperatureAt{")) return "0,C,1";
    else if (StringTools.chomp(s,"\\salinityAt{")) return "0,?,1";
    else if (StringTools.chomp(s,"\\densityAt{")) return "0,kg,1,0,m,-3";
    else if (StringTools.chomp(s,"\\fullIrradAt{")) return "0,j,1,0,s,-1,0,m,-2";
    else if (StringTools.chomp(s,"\\visIrradAt{")) return "0,j,1,0,s,-1,0,m,-2";
    else if (StringTools.chomp(s,"\\UVIrradAt{")) return "0,j,1,0,s,-1,0,m,-2";

    /* Not sure about how to check these... eg. what are the units of log(x) ? */
    
    else if (StringTools.chomp(s,"\\log{")) return unitsOf(StringTools.spit(s,"\\log{"),model,group,function);
    else if (StringTools.chomp(s,"\\log10{")) return unitsOf(StringTools.spit(s,"\\log10{"),model,group,function);
    else if (StringTools.chomp(s,"\\asin{")) return unitsOf(StringTools.spit(s,"\\asin{"),model,group,function);
    else if (StringTools.chomp(s,"\\acos{")) return unitsOf(StringTools.spit(s,"\\acos{"),model,group,function);
    else if (StringTools.chomp(s,"\\atan{")) return unitsOf(StringTools.spit(s,"\\atan{"),model,group,function);
    else if (StringTools.chomp(s,"\\sin{")) return unitsOf(StringTools.spit(s,"\\sin{"),model,group,function);
    else if (StringTools.chomp(s,"\\cos{")) return unitsOf(StringTools.spit(s,"\\cos{"),model,group,function);
    else if (StringTools.chomp(s,"\\tan{")) return unitsOf(StringTools.spit(s,"\\tan{"),model,group,function);
    else if (StringTools.chomp(s,"\\sqrt{")) return unitsOf(StringTools.spit(s,"\\sqrt{"),model,group,function);
    else if (StringTools.chomp(s,"\\exp{")) return unitsOf(StringTools.spit(s,"\\exp{"),model,group,function);

    else if (StringTools.chomp(s,"\\pow{")) {
      String[] leftAndRight = StringTools.nestedSplit(s,',');
      return unitsOf(leftAndRight[0],model,group,function);
    }

    else if (StringTools.chomp(s,"\\div{")) {
      String[] leftAndRight = StringTools.nestedSplit(s,',');
      String topUnits = unitsOf(leftAndRight[0],model,group,function);
      String bottomUnits = inverse(unitsOf(leftAndRight[1],model,group,function));
      return topUnits+','+bottomUnits;
    }

    else if (StringTools.chomp(s,"\\sub{")) return sameUnits(StringTools.spit(s,"\\sub{"),model,group,function);
    else if (StringTools.chomp(s,"\\greater{")) { sameUnits(StringTools.spit(s,"\\greater{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\greaterequal{")) { sameUnits(StringTools.spit(s,"\\greaterequal{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\equal{")) { sameUnits(StringTools.spit(s,"\\equal{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\lessequal{")) { sameUnits(StringTools.spit(s,"\\lessequal{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\less{")) { sameUnits(StringTools.spit(s,"\\less{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\not{")) { unitsOf(StringTools.spit(s,"\\not{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\neq{")) { sameUnits(StringTools.spit(s,"\\neq{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\and{")) { sameUnits(StringTools.spit(s,"\\and{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\or{")) { sameUnits(StringTools.spit(s,"\\or{"),model,group,function); return "BOOLEAN"; }
    else if (StringTools.chomp(s,"\\add{")) return sameUnits(StringTools.spit(s,"\\add{"),model,group,function);
    else if (StringTools.chomp(s,"\\min{")) return sameUnits(StringTools.spit(s,"\\min{"),model,group,function);
    else if (StringTools.chomp(s,"\\max{")) return sameUnits(StringTools.spit(s,"\\max{"),model,group,function);
    else if (StringTools.chomp(s,"\\mul{")) {
      String[] items = StringTools.nestedSplit(StringTools.spit(s,"\\mul{"),',');
      String firstUnits = unitsOf(items[0],model,group,function);
      for (int i=1; i<items.length; i++) {
        String otherUnits = unitsOf(items[i],model,group,function);
        firstUnits+=","+otherUnits;
      }
      return firstUnits;
    }

    else if (StringTools.chomp(s,"\\conditional{")) {
      String[] triplet = StringTools.nestedSplit(StringTools.spit(s,"\\conditional{"),',');
      if (!unitsOf(triplet[0],model,group,function).equals("BOOLEAN")) ErrorLog+="Non-boolean as first element of conditional - "+s+" \n";
      return sameUnits(triplet[1]+","+triplet[2],model,group,function);
    }
      
    else if (StringTools.chomp(s,"\\varhist{")) {
      String[] leftAndRight = StringTools.nestedSplit(StringTools.spit(s,"\\varhist{"),',');
      return unitsOf(leftAndRight[0],model,group,function);
    }
    else if (StringTools.chomp(s,"\\integrate{")) return "0,m,1,"+unitsOf(StringTools.spit(s,"\\integrate{"),model,group,function);
    else return "";
  }

  
  public boolean checkStatement(String s, XMLTag model, XMLTag group, XMLTag function) {
    ErrorLog="";
    // Take's an 'eq' string, and checks units of it, showing appropriate warning messages...
    boolean success = true;
    if (StringTools.chomp(s,"\\assign{")) {
      String[] leftAndRight = StringTools.nestedSplit(StringTools.spit(s,"\\assign{"),',');
      String unitsLeft = unitsOf(leftAndRight[0],model,group,function);
      String unitsRight = unitsOf(leftAndRight[1],model,group,function);
      success = (equivalent(unitsLeft,unitsRight));
      if (!success) ErrorLog+="LHS: "+unitsLeft+", RHS: "+unitsRight+"\n";
    }
    else if (StringTools.chomp(s,"\\assigndiff{")) {
      String[] leftAndRight = StringTools.nestedSplit(StringTools.spit(s,"\\assigndiff{"),',');
      String unitsLeft = unitsOf(leftAndRight[0],model,group,function);
      String unitsRight = unitsOf(leftAndRight[1],model,group,function);
      success = (equivalent(unitsLeft,unitsRight));
      if (!success) ErrorLog+="LHS: "+unitsLeft+", unitsRight = "+unitsRight+"\n";
    }
    else if (StringTools.chomp(s,"\\ifthen{")) {
      String[] leftAndRight = StringTools.nestedSplit(StringTools.spit(s,"\\ifthen{"),',');
      String cond = unitsOf(leftAndRight[0],model,group,function);
      if (!cond.equals("BOOLEAN")) {
        ErrorLog +="Conditional statement must start with BOOLEAN condition \n";
        success = false;
        checkStatement(leftAndRight[1],model,group,function);
      } else success = checkStatement(leftAndRight[1],model,group,function);
    }
    if (!success) JOptionPane.showMessageDialog(UnitEditor.this,"Unit Checking Error:\n"+ErrorLog);
    else JOptionPane.showMessageDialog(UnitEditor.this,"Unit Checking OK");
    return success;
  }
    

    
    
  
  
  public static String HTMLFor(String unit) {
    // Converts 0,kg,1,0,m,1,0,s,-1 into kgms<sup>-1</sup>
    if (unit==null) unit="";
    if (unit.startsWith("\\unit{")) unit=unit.substring(6);
    if (unit.equals("0,0,0")) unit="";
    String[] bits = unit.split(",");
    String res = "";
    
    int i=0;
    while (i<bits.length-1) {
      String pre = bits[i++];
      String un = bits[i++];
      String sup = bits[i++];
      res+="<strong>"+(VEWUtilities.getExponentSymbol(pre))+"</strong>";
      res+=un;
      if (!sup.equals("1")) res+="<sup>"+sup+"</sup>";
    }
    if (res.equals("")) res = "dimensionless";
    return res;
  }
  
  public String getUnit() { 
    if (finalString.equals("")) finalString="0,0,0";
    return finalString; 
  }

  public void setUnitEditor(String firstUnit, XMLTag model) {
    finalString = firstUnit;
    InitialiseUnitInfo(model);
    if (firstUnit==null) firstUnit="";
    realListData.removeAllElements();
    unitListData.removeAllElements();
    for (int i=0; i<unitUsed.length; i++) { unitUsed[i]=false; }
    refreshMenuAndEq();
    if (firstUnit.equals("0,0,0")) firstUnit="";
    while (firstUnit.length()>0) {
      String u1 = firstUnit.substring(0,firstUnit.indexOf(','));
      firstUnit = firstUnit.substring(firstUnit.indexOf(',')+1);
      String u2 = firstUnit.substring(0,firstUnit.indexOf(','));
      firstUnit = firstUnit.substring(firstUnit.indexOf(',')+1);
      String u3 ="";
      if (firstUnit.indexOf(',')>0) {
        u3 = firstUnit.substring(0,firstUnit.indexOf(','));
        firstUnit = firstUnit.substring(firstUnit.indexOf(',')+1);
      } else { 
        u3 = firstUnit;
        firstUnit="";
      }
      realListData.addElement(new String(u1+","+u2+","+u3));
      String s = VEWUtilities.getExponentSymbol(u1)+" ";
      int i=0;
      boolean found = false;
      while ((i<Units.size()) && (!found)) {
        String u = (String)Units.get(i);
        if (u.substring(u.indexOf('|')+1).equals(u2)) {
          found=true;
          s+=u.substring(u.indexOf('|')+1)+" ";
        } else i++;
      }
      s += "^"+u3;
      unitListData.addElement(s);
      unitUsed[i]=true;
      i=0;
    }
    unitList.setListData(unitListData);
    refreshMenuAndEq();
  }
  
  public void updateFinalString() {
    String s = "";
    for (int i=0; i<realListData.size(); i++) {
      s+=(String) realListData.elementAt(i);
      if (i<realListData.size()-1) s+=",";
    }
    finalString = s;
  }
  
  public void refreshMenuAndEq() {
    theUnit.removeAllItems();
    for (int i=0; i<Units.size(); i++) {
      String u = (String) Units.get(i);
      if (!unitUsed[i]) theUnit.addItem(u.substring(0,u.indexOf('|')));
    }
    addButton.setEnabled(theUnit.getItemCount()>0);
    remButton.setEnabled(unitList.getSelectedIndices().length>0);
    String eq = "\\unit{";
    for (int i=0; i<realListData.size(); i++) {
      eq+=(String) realListData.elementAt(i);
      if (i<realListData.size()-1) eq+=",";
    }
    eqPanel.setText("<html>"+HTMLFor(eq)+"</html>");
  }

  public class CaseInsensitiveComparator implements Comparator {
    public int compare(Object o1, Object o2) { 
      String s = StringTools.nonSpace(((String) o1));
      String t = StringTools.nonSpace(((String) o2));
      return (s.compareToIgnoreCase(t));
    }
    public boolean equals(Object obj) { return this.equals(obj); }
  }
  
  class EventHandler implements ActionListener, MouseListener, WindowListener, KeyListener {
    private int objectID;
    private static final int closeWin = 7;

    
    public EventHandler(int obj) {
      objectID = obj;
    }

    public EventHandler() {}
    
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==moreExp) {
        exp++;
        if (exp==0) exp++;
        expUnit.setText(String.valueOf(exp));

      } else if (e.getSource()==lessExp)  {
        exp--;
        if (exp==0) exp--;
        expUnit.setText(String.valueOf(exp));

      } else if (e.getSource()==addButton) {
        String s = ((String) preUnit.getSelectedItem())+" "+((String) theUnit.getSelectedItem())+" ^"+exp;
        unitListData.addElement(s);
        String preNum = VEWUtilities.getExponentForName((String)preUnit.getSelectedItem());
        int i=0;
        boolean found = false;
        while (!found) {
          String ug =  (String) Units.get(i); 
          ug = ug.substring(0,ug.indexOf("|"));
          found = (ug.equals(theUnit.getSelectedItem()));
          if (!found) i++;
        }
        String u = (String) Units.get(i);
        realListData.addElement(preNum+","+u.substring(1+u.indexOf('|'))+","+exp);
        unitList.setListData(unitListData);
        unitUsed[i]=true;
        refreshMenuAndEq();
        preUnit.setSelectedIndex(4);

      } else if (e.getSource()==remButton) {
        int[] target = unitList.getSelectedIndices();
        for (int j=target.length-1; j>=0; j--) {
          String realData = (String) realListData.elementAt(target[j]);
          String unitData = realData.substring(realData.indexOf(',')+1);
          unitData = unitData.substring(0,unitData.indexOf(','));
          String unitMatch = unitData;
          int i=0;
          while (!((String)Units.get(i)).substring(1+((String)Units.get(i)).indexOf('|')).equals(unitMatch)) i++;
          unitUsed[i]=false;
          realListData.remove(target[j]);
          unitListData.remove(target[j]);
          unitList.setListData(unitListData);
        }
        Object o = theUnit.getSelectedItem();
        refreshMenuAndEq();
        theUnit.setSelectedItem(o);

      } else if (e.getSource()==okButton) {
        updateFinalString();
        setVisible(false);
      
      } else if (e.getSource()==cancelButton) {
        setVisible(false);

      }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
      if (e.getSource()==unitList) {
        remButton.setEnabled(unitList.getSelectedIndices().length>0);
      }
    }

    public void mouseReleased(MouseEvent e) {
      if (e.getSource()==unitList) {
        remButton.setEnabled(unitList.getSelectedIndices().length>0);
      }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {
      if (objectID==closeWin) {
        setVisible(false);
      }
    }
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}

  }
}   