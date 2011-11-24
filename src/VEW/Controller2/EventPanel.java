package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;
import VEW.Scenario2.ScenarioPanel2;

public class EventPanel extends JPanel {
   private EventTableModel eventsTableModel = new EventTableModel();
   private JTable eventsTable = new JTable(eventsTableModel);
   private JTextField eventName = new JTextField(20);
   private JComboBox scopeCombo = new JComboBox();
   private JComboBox topDepth = new JComboBox();
   private JComboBox bottomDepth = new JComboBox();
   private JCheckBox turbocline = new JCheckBox("Turbocline");
   private JComboBox operator = new JComboBox();
   private JComboBox intervalUnit = new JComboBox();  
   private JCheckBox continuous = new JCheckBox("Continuous");
   private JTextField interval = new JTextField(8);   
   private JTextField value = new JTextField(8);
   private JButton startTime = new JButton();
   private JButton endTime = new JButton();
   private JButton addButton = new JButton("Add Event");
   private JButton overwriteButton = new JButton("Overwrite Event");
   private JButton removeButton = new JButton("Remove Event");
   
   private JCheckBox interpolate = new JCheckBox("Interpolate");
   private JTextField bottomValue = new JTextField(8);
   private DefaultListModel itemListModel = new DefaultListModel();
   private JList itemList = new JList(itemListModel);
   private XMLTag theModel;
   private VEWController2 vc2;
   private DateDialog dd;
   private GregorianCalendar startSim = new GregorianCalendar(DateDialog.GMTTimeZone);
   private GregorianCalendar endSim = new GregorianCalendar(DateDialog.GMTTimeZone);
   private GregorianCalendar startEvent = new GregorianCalendar(DateDialog.GMTTimeZone);
   private GregorianCalendar endEvent = new GregorianCalendar(DateDialog.GMTTimeZone);   
   private EventHandler eh = new EventHandler();
   private boolean changedDetails = false;
   
   public static final String WATER_COLUMN = new String("Water Column");
   public static final String PHYSICS_LAYERS = new String("Physical Properties");
   public static final String BIOCHEM_LAYERS = new String("Chemicals");

   public static final byte PHY_DENS = 0;
   public static final byte PHY_FI = 1;   
   public static final byte PHY_SALN = 2;
   public static final byte PHY_TEMP = 3;
   public static final byte PHY_VI = 4;   
   
   public static final String OP_ADD= new String("Add");
   public static final String OP_DIV = new String("Divide");
   public static final String OP_MUL = new String("Multiply");
   public static final String OP_SET = new String("Set");
   public static final String OP_SUB = new String("Subtract");   
   
   public static final String INT_TS = new String("Timestep");
   public static final String INT_SEC = new String("Second");
   public static final String INT_HOUR = new String("Hour");
   public static final String INT_DAY = new String("Day");
   public static final String INT_WEEK = new String("Week"); 
   public static final String INT_YEAR = new String("Year");
   
   private static int lockEvents = 0;
   private static GregorianCalendar anyDate = new GregorianCalendar(DateDialog.GMTTimeZone);
   
   public boolean greenLight(boolean fix) {
     boolean ok=true;
     MiscUtils.findDateLimits(theModel, startSim, endSim);
     StringBuffer errorString = new StringBuffer();
     if (theModel.getTag("events")==null) {
       if (fix) {
         theModel.addTag(new XMLTag("events",""));
         errorString.append("Initialised Events\n");
       } else ok=false;
     }
     if (ok) {
       final String dataset = theModel.getTag("boundaryconditions").getValue("climatedata");
       final String flux = theModel.getTag("boundaryconditions").getValue("heatflux");
       XMLTag[] events = theModel.getTag("events").getTags("event");
       for (int i=0; i<events.length; i++) {
         boolean continuousE = false;
         if ((events[i].getAttribute("continuous")!=null) && (events[i].getAttribute("continuous").equals("true"))) 
           continuousE = true;
         if (!continuousE) {
           long _startEvent = Long.parseLong(events[i].getAttribute("start"));
           long _endEvent = Long.parseLong(events[i].getAttribute("end"));
           long _startSim = Long.parseLong(theModel.getValue("track/start"));
           long _endSim = Long.parseLong(theModel.getValue("track/end"));
           if ((_startEvent<_startSim) || (_startEvent>_endSim)) {
             if (fix) {
               events[i].setAttribute("start",String.valueOf(_startSim));
               errorString.append("Corrected start date on event: "+events[i].getAttribute("name")+"\n");
             } else ok=false;
           }
           if ((_endEvent<_startSim) || (_endEvent>_endSim)) {
             if (fix) {
               events[i].setAttribute("end",String.valueOf(_endSim));
               errorString.append("Corrected end date on event: "+events[i].getAttribute("name")+"\n");
             } else ok=false;
           }
         }
         if (events[i].getAttribute("scope").equals(BIOCHEM_LAYERS)) {
           String chemical = events[i].getAttribute("item");
           chemical = chemical.substring(0,chemical.length()-14); // Remove the " concentration" from the end.
           if (theModel.getTagWhere("chemical","name",chemical)==null) {
             if (fix) {
               events[i].removeFromParent();
               errorString.append("Removed event for missing chemical - "+chemical+"\n");
             } else ok=false;
           }
         }
         if (events[i].getAttribute("scope").equals(WATER_COLUMN)) {
           String name = events[i].getAttribute("item");
           if (dataset.equals("Bunker")) {
             if ((!name.equals("Wind Speed")) && (!name.equals("Total Cloud Cover")) 
                 && (!name.equals("Heat Flux")) && (!name.equals("Ekman"))) {
               if (fix) {
                 errorString.append("Removed event for "+name+" - not valid in Bunker\n");
                 events[i].removeFromParent();
               } else ok = false;
             }
           } else { // ERA40
             if (flux.equals("Read")) {
               if ((!name.equals("Wind Speed")) && (!name.equals("Total Cloud Cover")) 
                   && (!name.equals("Surface Sensible Heat Flux")) && (!name.equals("Surface Latent Heat Flux"))
                   && (!name.equals("Surface Thermal Radiation"))) {
                 if (fix) {
                   errorString.append("Removed event for "+name+" - not valid in ERA40 (Read flux)\n");
                   events[i].removeFromParent();
                 } else ok = false;
                 
               }
             } else { // Calculate
               if ((!name.equals("Wind Speed")) && (!name.equals("Total Cloud Cover")) 
                   && (!name.equals("Relative Humidity")) && (!name.equals("Air Temperature"))) {
                 if (fix) {
                   errorString.append("Removed event for "+name+" - not valid in ERA40 (Calculate flux)\n");
                   events[i].removeFromParent();
                 } else ok = false;
               }
             }
           }
         }
         if (!ok) i=events.length;
       }
     }
     
     if (fix) {
       if (errorString.length()>5) {
         MessageBox.showMessage(errorString.toString(),this);
         vc2.unsaved(true);
       }
       initialise();
       initCombos();
     }
     return ok;
   }
   
   
   public void initCombos() {
     lockEvents++;
     interval.setText("1");     
     scopeCombo.removeAllItems();
     scopeCombo.addItem(WATER_COLUMN);
     scopeCombo.addItem(PHYSICS_LAYERS);
     scopeCombo.addItem(BIOCHEM_LAYERS);
     scopeCombo.setSelectedIndex(0);
     operator.removeAllItems();
     operator.addItem(OP_ADD);
     operator.addItem(OP_DIV);
     operator.addItem(OP_MUL);
     operator.addItem(OP_SET);
     operator.addItem(OP_SUB);
     intervalUnit.removeAllItems();
     intervalUnit.addItem(INT_TS);
     intervalUnit.addItem(INT_SEC);     
     intervalUnit.addItem(INT_HOUR);
     intervalUnit.addItem(INT_DAY);
     intervalUnit.addItem(INT_WEEK);
     intervalUnit.addItem(INT_YEAR);
     lockEvents--;
     setCombo();
     updateButtons();
   }
   
   public void setCombo() {
     lockEvents++;
     if (scopeCombo.getSelectedItem().toString().equals(WATER_COLUMN)) {
       itemListModel.clear();
       if (theModel.getTag("boundaryconditions").getValue("climatedata").equals(ScenarioPanel2.BUNKER)) {
         itemListModel.addElement("Ekman");
         itemListModel.addElement("Heat Flux");
         itemListModel.addElement("Total Cloud Cover");
         itemListModel.addElement("Wind Speed");
       } else {
         if (theModel.getTag("boundaryconditions").getValue("heatflux").equals(ScenarioPanel2.ERA40_CALCULATE_FLUX)) {
           itemListModel.addElement("Surface Latent Heat Flux");
           itemListModel.addElement("Surface Sensible Heat Flux");
           itemListModel.addElement("Surface Thermal Radiation");
           itemListModel.addElement("Total Cloud Cover");
           itemListModel.addElement("Wind Speed");

         } else if (theModel.getTag("boundaryconditions").getValue("heatflux").equals(ScenarioPanel2.ERA40_READ_FLUX)) {
           itemListModel.addElement("Air Temperature");
           itemListModel.addElement("Relative Humidity");
           itemListModel.addElement("Total Cloud Cover");
           itemListModel.addElement("Wind Speed");
         }
       }
     } else if (scopeCombo.getSelectedItem().toString().equals(PHYSICS_LAYERS)) {
       itemListModel.clear();
       itemListModel.addElement("Density");
       itemListModel.addElement("Full Irradiance");       
       itemListModel.addElement("Salinity");
       itemListModel.addElement("Temperature");
       itemListModel.addElement("Visible Irradiance");       
     } else if (scopeCombo.getSelectedItem().toString().equals(BIOCHEM_LAYERS)) {
       itemListModel.clear();
       XMLTag[] chems = theModel.getTags("chemical");
       for (int i=0; i<chems.length; i++)
         itemListModel.addElement(chems[i].getValue("name")+" concentration");
     }
     lockEvents--;
     populateDepths();
   }
        
        
     
   
   public void updateButtons() {
     lockEvents++;
     removeButton.setEnabled(eventsTable.getSelectedRows().length>0);
     boolean addButtonOK = (StringTools.nonSpace(eventName.getText()).length()>0);
     addButtonOK = addButtonOK && (operator.getSelectedIndex()>=0);
     addButtonOK = addButtonOK && (StringTools.nonSpace(value.getText()).length()>0);
     addButtonOK = addButtonOK && (itemList.getSelectedIndices().length==1);
     addButton.setEnabled(addButtonOK);
     overwriteButton.setEnabled(addButtonOK && removeButton.isEnabled() && changedDetails);
     bottomDepth.setEnabled((!turbocline.isSelected() && (!scopeCombo.getSelectedItem().toString().equals(WATER_COLUMN))));
     startTime.setEnabled(!continuous.isSelected());
     endTime.setEnabled(!continuous.isSelected());
     interval.setEnabled(!continuous.isSelected());
     intervalUnit.setEnabled(!continuous.isSelected());
     interpolate.setEnabled(!scopeCombo.getSelectedItem().toString().equals(WATER_COLUMN));
     bottomValue.setEnabled(interpolate.isEnabled() && interpolate.isSelected());
     lockEvents--;
   } 
   
   public void populateDepths() {
     lockEvents++;
     if (scopeCombo.getSelectedItem().toString().equals(WATER_COLUMN)) {
       topDepth.setEnabled(false);
       bottomDepth.setEnabled(false);
       turbocline.setEnabled(false);
     } else {
       topDepth.setEnabled(true);
       bottomDepth.setEnabled(!turbocline.isSelected());
       turbocline.setEnabled(true);
       
       topDepth.removeAllItems();
       bottomDepth.removeAllItems();
       topDepth.addItem(String.valueOf(0));
       bottomDepth.addItem(String.valueOf(0));
       if (scopeCombo.getSelectedItem().toString().equals(PHYSICS_LAYERS)) {
         float power = -2;
         for (int i=1; i<21; i++) {
           String theVal = String.valueOf(Math.pow(10,power));
           if (theVal.length()>6) theVal = theVal.substring(0,6);
           topDepth.addItem(theVal);
           bottomDepth.addItem(theVal);
           power+=0.1;
         }
       } 
       for (int i=1; i<500; i++) {
         topDepth.addItem(String.valueOf(i));
         bottomDepth.addItem(String.valueOf(i));
       }
       bottomDepth.setSelectedIndex(bottomDepth.getItemCount()-1);
     }
     lockEvents--;
   }
  
   
   
   public void initialise() {
     lockEvents++;
     XMLTag eventsTag = theModel.getTag("events");
     XMLTag[] events = eventsTag.getTags("event");
     while (eventsTableModel.getRowCount()>0) eventsTableModel.removeRow(0);
     for (int i=0; i<events.length; i++) addEventToTable(events[i],-1);
     startEvent.setTimeInMillis(startSim.getTimeInMillis());
     endEvent.setTimeInMillis(startSim.getTimeInMillis());  
     startTime.setText(DateDialog.getString(startEvent));
     endTime.setText(DateDialog.getString(endEvent));     
     lockEvents--;
   }
   
   public void addEventToTable(XMLTag e, int r) {
     lockEvents++;
     final String name = e.getAttribute("name");
     final String scope = e.getAttribute("scope");
     final String op = e.getAttribute("op");
     final String _value = e.getAttribute("val");
     final String item = e.getAttribute("item");
    
     String startDate;
     String endDate;
     String intervalNo;
     String _intervalUnit;
     if ((e.getAttribute("continuous")!=null) && (e.getAttribute("continuous").equals("true"))) {
       startDate = "N/A";
       endDate = "N/A";
       intervalNo = "1";
       _intervalUnit = "Step";
     } else {
       final long startMillis = Long.parseLong(e.getAttribute("start"));
       final long endMillis = Long.parseLong(e.getAttribute("end"));
       anyDate.setTimeInMillis(startMillis);
       startDate = DateDialog.getString(anyDate);
       anyDate.setTimeInMillis(endMillis);
       endDate = DateDialog.getString(anyDate);
       intervalNo = e.getAttribute("interval");
       _intervalUnit = e.getAttribute("intervalunit");
     }
       
     
     String endDepth;
     String startDepth = e.getAttribute("top");
     if (scope.equals(WATER_COLUMN)) {
       startDepth="N/A";
       endDepth="N/A";
     } else {
       startDepth = e.getAttribute("top");
       final boolean MLD = e.getAttribute("mld").equals("true");
       endDepth = (MLD)?"Turbocline":e.getAttribute("bottom");
     }
     if (r==-1) {
       String func = op+" "+_value;
       if ((e.getAttribute("interpolate")!=null) && (e.getAttribute("interpolate").equals("true")))
         func = op+" "+_value+".."+e.getAttribute("bottomval");
       eventsTableModel.addRow(new String[] {name,item,func,startDepth,endDepth,startDate,endDate,intervalNo+" "+_intervalUnit});
   } else {
       eventsTableModel.setValueAt(name,r,0);
       eventsTableModel.setValueAt(item,r,1);
       if ((e.getAttribute("interpolate")!=null) && (e.getAttribute("interpolate").equals("true")))
         eventsTableModel.setValueAt(op+" "+_value+".."+e.getAttribute("bottomval"),r,2);
         else eventsTableModel.setValueAt(op+" "+_value,r,2);
       eventsTableModel.setValueAt(startDepth,r,3);
       eventsTableModel.setValueAt(endDepth,r,4);
       eventsTableModel.setValueAt(startDate,r,5);
       eventsTableModel.setValueAt(endDate,r,6);
       eventsTableModel.setValueAt(intervalNo+" "+_intervalUnit,r,7);
     }
     lockEvents--;
   }
   
   public XMLTag addNewEvent() {
     lockEvents++;
     XMLTag e = new XMLTag("event");
     overwrite(e);
     theModel.getTag("events").addTag(e);
     vc2.unsaved(false);
     lockEvents--;
     return e;
   }
   
   public void overwrite(XMLTag e) {
     e.setAttribute("name",eventName.getText());
     e.setAttribute("op",operator.getSelectedItem().toString());
     e.setAttribute("scope",scopeCombo.getSelectedItem().toString());
     e.setAttribute("val",value.getText());
     e.setAttribute("item",itemList.getSelectedValue().toString());
     if (!scopeCombo.getSelectedItem().toString().equals(WATER_COLUMN)) {
       e.setAttribute("top",topDepth.getSelectedItem().toString());
       e.setAttribute("bottom",bottomDepth.getSelectedItem().toString());
       e.setAttribute("interpolate",String.valueOf(interpolate.isSelected()));
       e.setAttribute("bottomval",bottomValue.getText());
     }
     if (continuous.isSelected()) {
       e.setAttribute("continuous","true");
     } else {
       e.setAttribute("start",String.valueOf(startEvent.getTimeInMillis()));
       e.setAttribute("end",String.valueOf(endEvent.getTimeInMillis()));
       e.setAttribute("interval",interval.getText());
       e.setAttribute("intervalunit",intervalUnit.getSelectedItem().toString());
       e.setAttribute("continuous","false");
     }
     e.setAttribute("mld",String.valueOf(turbocline.isSelected()));
 
   }
   
   public EventPanel(JFrame vewController2, XMLTag model) {
     lockEvents++;
     theModel = model;
    
     vc2 = (VEWController2) vewController2;
     dd = new DateDialog(vc2,1800);
     setLayout(new BorderLayout());
     JPanel topHalf = new JPanel(new BorderLayout());
       JPanel eventInfo = new JPanel(new FlowLayout());
       eventInfo.setBorder(new EtchedBorder());
         JPanel selectionInfo = new JPanel(new BorderLayout());
           selectionInfo.add(scopeCombo,BorderLayout.NORTH);
           JScrollPane itemScroller = new JScrollPane(itemList);
           itemScroller.setPreferredSize(new Dimension(200,150));
           selectionInfo.add(itemScroller,BorderLayout.CENTER);
           eventInfo.add(selectionInfo);
         JPanel quantityInfo = new JPanel(new GridLayout(6,1));
           JPanel nameLine = new JPanel(new FlowLayout());
             nameLine.add(new JLabel("Event Name: "));
             nameLine.add(eventName);
             quantityInfo.add(nameLine);
   
         
         JPanel changeInfo = new JPanel(new FlowLayout());
             changeInfo.add(operator);
             changeInfo.add(value);
             quantityInfo.add(changeInfo);
           JPanel depthInfo = new JPanel(new FlowLayout());
             depthInfo.add(new JLabel("Between"));
             depthInfo.add(topDepth);
             depthInfo.add(new JLabel("and"));
             depthInfo.add(bottomDepth);
             depthInfo.add(new JLabel("metres or "));
             depthInfo.add(turbocline);
             quantityInfo.add(depthInfo);
           JPanel interpInfo = new JPanel(new FlowLayout());
             interpInfo.add(interpolate);
             interpInfo.add(new JLabel(" down to "));
             interpInfo.add(bottomValue);
             quantityInfo.add(interpInfo);
             bottomValue.setEnabled(false);
             
           JPanel timeInfo = new JPanel(new FlowLayout());
             timeInfo.add(new JLabel("From"));
             timeInfo.add(startTime);
             timeInfo.add(new JLabel("to"));
             timeInfo.add(endTime);
             quantityInfo.add(timeInfo);
           JPanel intervalInfo = new JPanel(new FlowLayout());
             intervalInfo.add(new JLabel("every"));
             intervalInfo.add(interval);
             intervalInfo.add(intervalUnit);
             intervalInfo.add(continuous);
             quantityInfo.add(intervalInfo);
           eventInfo.add(quantityInfo);
           topHalf.add(eventInfo,BorderLayout.CENTER);
         JPanel addOverwritePanel = new JPanel(new FlowLayout());
           addOverwritePanel.add(addButton);
           addOverwritePanel.add(overwriteButton);
         topHalf.add(addOverwritePanel,BorderLayout.SOUTH);
       add(topHalf,BorderLayout.NORTH);
       JPanel bottomHalf = new JPanel(new BorderLayout());
         JScrollPane eventsTableScroller = new JScrollPane(eventsTable);
         eventsTableScroller.setPreferredSize(new Dimension(600,200));
         bottomHalf.add(eventsTableScroller,BorderLayout.CENTER);
         
         JPanel removeEventPanel = new JPanel(new FlowLayout());
         removeEventPanel.add(removeButton);
         bottomHalf.add(removeEventPanel,BorderLayout.SOUTH);
       add(bottomHalf,BorderLayout.CENTER);
       
     value.setPreferredSize(new Dimension(200,26));
     eventName.setPreferredSize(new Dimension(200,26));     
     interval.setPreferredSize(new Dimension(200,26));     
     itemList.addListSelectionListener(eh);
     removeButton.addActionListener(eh);
     addButton.addActionListener(eh);
     overwriteButton.addActionListener(eh);
     topDepth.addActionListener(eh);
     turbocline.addActionListener(eh);
     continuous.addActionListener(eh);
     bottomDepth.addActionListener(eh);
     startTime.addActionListener(eh);
     endTime.addActionListener(eh);
     scopeCombo.addActionListener(eh);
     value.addCaretListener(eh);
     eventName.addCaretListener(eh);
     interval.addCaretListener(eh);
     interpolate.addActionListener(eh);
     intervalUnit.addActionListener(eh);
     eventsTableModel.setColumnCount(8);
     eventsTableModel.setColumnIdentifiers(new String[] {"Name","Item","Function","Top","Bottom","From","Until","Interval"});
     eventsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
     eventsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
     eventsTable.getColumnModel().getColumn(3).setPreferredWidth(20);
     eventsTable.getColumnModel().getColumn(4).setPreferredWidth(20); 
     eventsTable.addMouseListener(eh);
     
     lockEvents--;
   }
   
   public void showDefinition(XMLTag e) {
     lockEvents++;
     eventName.setText(e.getAttribute("name"));
     StringTools.setStringItem(scopeCombo,e.getAttribute("scope"));
     setCombo();
     StringTools.setStringItem(operator,e.getAttribute("op"));
     value.setText(e.getAttribute("val"));
     StringTools.setListItem(itemList,e.getAttribute("item"));
     if (e.getAttribute("top")!=null) StringTools.setStringItem(topDepth,e.getAttribute("top"));
     if (e.getAttribute("bottom")!=null) StringTools.setStringItem(bottomDepth,e.getAttribute("bottom"));
     if (e.getAttribute("mld").equals("true")) {
       turbocline.setSelected(true);
       bottomDepth.setEnabled(false);
     }
     if ((e.getAttribute("interpolate")!=null) && (e.getAttribute("interpolate").equals("true")))
       interpolate.setSelected(true);
     else interpolate.setSelected(false);
     if (e.getAttribute("bottomval")!=null) {
       double bv=0;
       try { bv = Double.parseDouble(e.getAttribute("bottomval")); } catch (Exception ex) {}
       bottomValue.setText(String.valueOf(bv));
     } else bottomValue.setText("0.0");
     
     if ((e.getAttribute("continuous")!=null) && (e.getAttribute("continuous").equals("true"))) {
       continuous.setSelected(true);
       if (e.getAttribute("start")!=null) {
         startEvent.setTimeInMillis(Long.parseLong(e.getAttribute("start")));
         startTime.setText(DateDialog.getString(startEvent));
       }
       if (e.getAttribute("end")!=null) {
         endEvent.setTimeInMillis(Long.parseLong(e.getAttribute("end")));
         endTime.setText(DateDialog.getString(endEvent));
       }
       if (e.getAttribute("intervalunit")!=null) StringTools.setStringItem(intervalUnit,e.getAttribute("intervalunit"));
       if (e.getAttribute("interval")!=null) interval.setText(e.getAttribute("interval"));
     } else {
       continuous.setSelected(false);
       startEvent.setTimeInMillis(Long.parseLong(e.getAttribute("start")));
       endEvent.setTimeInMillis(Long.parseLong(e.getAttribute("end")));
       startTime.setText(DateDialog.getString(startEvent));
       endTime.setText(DateDialog.getString(endEvent));
       StringTools.setStringItem(intervalUnit,e.getAttribute("intervalunit"));
       interval.setText(e.getAttribute("interval"));
     }
     updateButtons();
     lockEvents--;
   }
   
   class EventHandler implements ActionListener, ListSelectionListener, CaretListener, MouseListener {
     
     public void actionPerformed(ActionEvent e) {
       if ((e.getSource()==scopeCombo) && (lockEvents==0)) {
         changedDetails=true;         
         setCombo();
         updateButtons();
       
       } else if (e.getSource()==startTime) {
         changedDetails=true;
         dd.show(startSim,endSim,startEvent);
         if (dd.getDate()!=null) {
           startEvent.setTimeInMillis(dd.getDate().getTimeInMillis());
           startTime.setText(DateDialog.getString(startEvent));
           if (startEvent.getTimeInMillis()>startEvent.getTimeInMillis())
             endEvent.setTimeInMillis(startEvent.getTimeInMillis());
           changedDetails=true;
           updateButtons();
                    
         }
       
       } else if (e.getSource()==endTime) {
         changedDetails=true;         
         dd.show(startSim,endSim,endEvent);
         if (dd.getDate()!=null) {
           endEvent.setTimeInMillis(dd.getDate().getTimeInMillis());
           endTime.setText(DateDialog.getString(endEvent));
           if (endEvent.getTimeInMillis()<startEvent.getTimeInMillis())
             startEvent.setTimeInMillis(endEvent.getTimeInMillis());
           changedDetails=true;
           updateButtons();
         }
       
       } else if (e.getSource()==addButton) {
         lockEvents++;
         vc2.unsaved(false);
         changedDetails=false;
         addEventToTable(addNewEvent(),-1);
         updateButtons();
         lockEvents--;
       
       } else if (e.getSource()==removeButton) {
         vc2.unsaved(false);
         int row = eventsTable.getSelectedRow();
         theModel.getTag("events").getTags("event")[row].removeFromParent();
         eventsTableModel.removeRow(row);
         eventsTable.clearSelection();
         updateButtons();
         vc2.unsaved(false);
       
       } else if (e.getSource()==overwriteButton) {
         lockEvents++;
         int row = eventsTable.getSelectedRow();
         XMLTag event = theModel.getTag("events").getTags("event")[row];
         overwrite(event);
         addEventToTable(event,row);
         changedDetails=false;
         updateButtons();
         vc2.unsaved(false);
         lockEvents--;
         
       } else if ((e.getSource()==intervalUnit) && (lockEvents==0)) {
         changedDetails=true;
         updateButtons();
       
       } else if ((e.getSource()==topDepth) && (lockEvents==0)) {
         changedDetails=true;
         if (bottomDepth.getSelectedIndex()<topDepth.getSelectedIndex()) {
           int i=topDepth.getSelectedIndex();
           topDepth.setSelectedIndex(bottomDepth.getSelectedIndex());
           bottomDepth.setSelectedIndex(i);
         }
         updateButtons();
         
       } else if ((e.getSource()==bottomDepth) && (lockEvents==0)) {
         changedDetails=true;
         if (bottomDepth.getSelectedIndex()<topDepth.getSelectedIndex()) {
           int i=topDepth.getSelectedIndex();
           topDepth.setSelectedIndex(bottomDepth.getSelectedIndex());
           bottomDepth.setSelectedIndex(i);
         }
         updateButtons();
         
       } else if ((e.getSource()==interpolate) && (lockEvents==0)) {
         changedDetails=true;
         updateButtons();
       
       } else if ((e.getSource()==turbocline) && (lockEvents==0)) {
         changedDetails=true;
         bottomDepth.setEnabled(!turbocline.isSelected());
         updateButtons();
       
       } else if ((e.getSource()==continuous) && (lockEvents==0)) {
         changedDetails=true;
         updateButtons();
       }
     }

    public void valueChanged(ListSelectionEvent e) {
      if ((e.getSource()==itemList) && (lockEvents==0)) {
        changedDetails=true;
        updateButtons();
      }
    }

    public void caretUpdate(CaretEvent e) {
      if ((e.getSource()==value) && (lockEvents==0)) {
        changedDetails=true;
        updateButtons();
      
      } else if ((e.getSource()==eventName) && (lockEvents==0)) {
        changedDetails=true;
        updateButtons();
        
      } else if ((e.getSource()==interval) && (lockEvents==0)) {
        changedDetails=true;
        updateButtons();
      }
    }

    public void mouseClicked(MouseEvent e) {
      if ((e.getSource()==eventsTable) && (lockEvents==0)) {
        int result = JOptionPane.YES_OPTION;
        if (changedDetails) {
          result = JOptionPane.showConfirmDialog(vc2,"The event settings have been changed - ok to lose changes","Ok to lose changes?",JOptionPane.YES_NO_OPTION);
        } 
        if (result==JOptionPane.YES_OPTION) {
          if (eventsTable.getSelectedRows().length>0) {
            XMLTag ev = theModel.getTag("events").getTags("event")[eventsTable.getSelectedRow()];
            showDefinition(ev);
            changedDetails=false;
          }
        }
        updateButtons();
      }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
  }
  class EventTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) { return false; }
  }
}

