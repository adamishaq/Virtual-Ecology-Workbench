package VEW.Common;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class DateDialog extends JDialog {

  private GregorianCalendar earliest = new GregorianCalendar(GMTTimeZone);
  private GregorianCalendar latest = new GregorianCalendar(GMTTimeZone);
  private GregorianCalendar current = new GregorianCalendar(GMTTimeZone);
  private GregorianCalendar remember = new GregorianCalendar(GMTTimeZone);
  private JToggleButton[][] days = new JToggleButton[6][7];
  private int secsPerStep= 1800;
  
  private JComboBox yearCombo = new JComboBox();
  private JComboBox monthCombo = new JComboBox();
  private JComboBox timeCombo = new JComboBox();
  
  private int lockEvents = 0;
  private String[] months = new String[] {"January","February","March","April","May","June",
                                "July","August","September","October","November","December"};
  private JButton okButton = new JButton("Accept");
  private JButton cancelButton = new JButton("Cancel");
  
  private static DateFormat dfGMT = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
  private static DateFormat dfLocal = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);

  public GregorianCalendar getFirst() { return earliest; }
  public GregorianCalendar getLast() { return latest; }
  public GregorianCalendar getDate() { return current; }
  
  public static final TimeZone GMTTimeZone = TimeZone.getTimeZone("GMT");
  
  static {
    TimeZone.setDefault(GMTTimeZone);
    dfGMT.setTimeZone(GMTTimeZone);
  }
  
  public String getDateString() {
    return dfGMT.format(current.getTime());
  }

  public static String getString(GregorianCalendar d) {
    return dfGMT.format(d.getTime());
  }

  public static String getLocalString(GregorianCalendar d) {
    return dfLocal.format(d.getTime());
  }


  public void setLatest(GregorianCalendar g) { 
    lockEvents++;
    if (g!=null) {
      if (latest==null) {
        latest = new GregorianCalendar();
        latest.setTimeZone(GMTTimeZone);
      }
      latest.setTimeInMillis(g.getTimeInMillis()); 
    } else latest = null;
    initialise(secsPerStep);
    lockEvents--;
  }
  
  public void setEarliest(GregorianCalendar g) { 
    lockEvents++;
    if (g!=null) {
      if (earliest==null) {
        earliest = new GregorianCalendar();
        earliest.setTimeZone(GMTTimeZone);
      }
      earliest.setTimeInMillis(g.getTimeInMillis());
    } else earliest = null;
    initialise(secsPerStep);
    lockEvents--;
  }
  
  public void show(GregorianCalendar E, GregorianCalendar L, GregorianCalendar D) {
    lockEvents++;
    setEarliest(E);
    setLatest(L);
    if (remember==null) {
      remember = new GregorianCalendar();
      remember.setTimeZone(GMTTimeZone);
    }
    if (current==null) {
      current = new GregorianCalendar();
      current.setTimeZone(GMTTimeZone);
    }
 
    remember.setTimeInMillis(D.getTimeInMillis());
    current.setTimeInMillis(D.getTimeInMillis());
    update();
    lockEvents--;
    setVisible(true);
    
  }

  public void show(GregorianCalendar D) {
    lockEvents++;
    if (remember==null) {
      remember = new GregorianCalendar();
      remember.setTimeZone(GMTTimeZone);
    }
    if (current==null) {
      current = new GregorianCalendar();
      current.setTimeZone(GMTTimeZone);
    }
    remember.setTimeInMillis(D.getTimeInMillis());    
    current.setTimeInMillis(D.getTimeInMillis());
    update();
    lockEvents--;    
    setVisible(true);
    
  }

  public DateDialog(Dialog parent,int _secsPerStep) {
    super(parent,"Choose Date",true);
    earliest=null;
    latest=null;
    
    initialiseGUI();
    initialise(_secsPerStep);
  }
  
  public DateDialog(Frame parent,int _secsPerStep) {
    super(parent,"Choose Date",true);
    initialiseGUI();
    initialise(_secsPerStep);
  }
  
  public void setSecStep(int _secstep) {
    initialise(_secstep);
  }
  
  private void initialiseGUI() {
    lockEvents++;
    JPanel scrollers = new JPanel(new FlowLayout());
    JPanel spacer = new JPanel();
    spacer.setPreferredSize(new Dimension(20,20));
    JPanel spacer2 = new JPanel();
    spacer2.setPreferredSize(new Dimension(20,20));

    scrollers.add(timeCombo);
    scrollers.add(spacer2);
    scrollers.add(monthCombo);
    scrollers.add(spacer);
    scrollers.add(yearCombo);
    yearCombo.setPreferredSize(new Dimension(100,20));

    okButton.addActionListener(new EventHandler(EventHandler.OkButton,0,0));
    cancelButton.addActionListener(new EventHandler(EventHandler.CancelButton,0,0));
    JPanel dayPickPanel = new JPanel(new BorderLayout(0,0));
    JPanel leftSpacer = new JPanel();
    leftSpacer.setPreferredSize(new Dimension(100,50));
    JPanel rightSpacer = new JPanel();
    rightSpacer.setPreferredSize(new Dimension(100,50));
    JPanel dayPicker = new JPanel(new GridLayout(6,7));
    dayPickPanel.add(dayPicker,"Center");
    dayPickPanel.add(leftSpacer,"West");
    dayPickPanel.add(rightSpacer,"East");
    for (int i=0; i<6; i++) {
      for (int j=0; j<7; j++) {
        days[i][j]=new JToggleButton();
        days[i][j].addActionListener(new EventHandler(EventHandler.ChooseDay,i,j));
        dayPicker.add(days[i][j]);
        days[i][j].setPreferredSize(new Dimension(50,20));
      }
    }

    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(new JPanel(),"West");
    getContentPane().add(new JPanel(),"East");
    getContentPane().add(scrollers,"North");
    getContentPane().add(dayPickPanel,"Center");
    getContentPane().add(buttonPanel,"South");
    EventHandler eh = new EventHandler();
    yearCombo.addActionListener(eh);
    monthCombo.addActionListener(eh);
    timeCombo.addActionListener(eh);
    lockEvents--;    
    pack();
    
}
  
  private void initialise(int _secsPerStep) {
    lockEvents++;
    secsPerStep=_secsPerStep;
    monthCombo.setEditable(false);
    monthCombo.removeAllItems();
    for (int i=0; i<months.length; i++) monthCombo.addItem(months[i]);
    yearCombo.removeAllItems();
    int startYear=1958;
    int endYear=2001;
    if (earliest!=null) startYear = earliest.get(GregorianCalendar.YEAR);
    if (latest!=null) endYear = latest.get(GregorianCalendar.YEAR);
      
    for (int i=startYear; i<=endYear; i++) yearCombo.addItem(String.valueOf(i));
    yearCombo.setEditable(true);
    timeCombo.removeAllItems();
    
    GregorianCalendar gc = new GregorianCalendar(GMTTimeZone);
    gc.set(GregorianCalendar.YEAR,2000);
    gc.set(GregorianCalendar.MONTH,0);
    gc.set(GregorianCalendar.DAY_OF_YEAR,1);
    gc.set(GregorianCalendar.HOUR_OF_DAY,0);
    gc.set(GregorianCalendar.MINUTE,0);
    gc.set(GregorianCalendar.MILLISECOND,0);
    int secondsPerDay = 24*3600;
    int totalSeconds=0;
    while (totalSeconds<secondsPerDay) {
      String minutes = String.valueOf(gc.get(GregorianCalendar.MINUTE));
      if (minutes.length()==1) minutes="0"+minutes;
      String hours = String.valueOf(gc.get(GregorianCalendar.HOUR_OF_DAY));
      if (hours.length()==1) hours="0"+hours;
      timeCombo.addItem(hours+":"+minutes);
      totalSeconds+=secsPerStep;
      gc.add(GregorianCalendar.SECOND,secsPerStep);
    }
    timeCombo.setEditable(false);
    
    lockEvents--;
  }

  private void updateButtons() {
    lockEvents++;
    GregorianCalendar temp = new GregorianCalendar();
    temp.setTimeZone(GMTTimeZone);
    temp.setTimeInMillis(current.getTimeInMillis());
    for (int i=0; i<7; i++) {
      for (int j=0; j<6; j++) {
        if (days[j][i].getText().equals("")) days[j][i].setEnabled(false);
        else {
          int date = Integer.parseInt(days[j][i].getText());
          temp.set(GregorianCalendar.DAY_OF_MONTH,date);
          if ((!temp.before(earliest)) && (!temp.after(latest))) days[j][i].setEnabled(true);
          else days[j][i].setEnabled(false);
        }
      }
    }
    lockEvents--;
  }
  
  
  private void update() {
    lockEvents++;
    if ((earliest!=null) && (current.getTimeInMillis()<earliest.getTimeInMillis()))
      current.setTimeInMillis(earliest.getTimeInMillis());
    if ((latest!=null) && (current.getTimeInMillis()>latest.getTimeInMillis()))
      current.setTimeInMillis(latest.getTimeInMillis());
    
    GregorianCalendar temp = new GregorianCalendar();
    temp.setTimeZone(GMTTimeZone);
    temp.setTimeInMillis(current.getTimeInMillis());
    temp.set(GregorianCalendar.DAY_OF_MONTH,1);
    int weekDay = temp.get(GregorianCalendar.DAY_OF_WEEK);
    for (int i=0; i<7; i++) {
      for (int j=0; j<6; j++) {
        days[j][i].setText("");
        days[j][i].setSelected(false);
        days[j][i].setEnabled(false);
      }
    }
    int i=0;
    int j=0;
    while (i<temp.getActualMaximum(GregorianCalendar.DATE)) {
      i++;
      days[j][weekDay-1].setText(""+i);
      days[j][weekDay-1].setSelected(i==current.get(GregorianCalendar.DAY_OF_MONTH));
      weekDay++;
      if (weekDay==8) {
        weekDay=1;
        j++;
      }
    }
    monthCombo.setSelectedIndex(temp.get(GregorianCalendar.MONTH));
    yearCombo.setSelectedItem(String.valueOf(temp.get(GregorianCalendar.YEAR)));
    int th = temp.get(GregorianCalendar.HOUR_OF_DAY);
    String theTime = "";
    if (th<10) theTime = "0";
    theTime=theTime+th+":"+temp.get(GregorianCalendar.MINUTE);
    if (!theTime.endsWith("30")) theTime=theTime+"0";
    StringTools.setStringItem(timeCombo,theTime);
    updateButtons();
    lockEvents--;
  }

  class EventHandler implements ActionListener {
    private int id, py, px;

    public static final int MonthDown = 0;
    public static final int MonthUp = 1;
    public static final int YearDown = 2;
    public static final int YearUp = 3;
    public static final int ChooseDay = 4;
    public static final int MinUp = 5;
    public static final int MinDown = 6;
    public static final int HourUp = 7;
    public static final int HourDown = 8;
    public static final int OkButton = 9;
    public static final int CancelButton = 10;

    public EventHandler() {}
    
    public EventHandler(int a, int b, int c) {
      id = a;
      py = b;
      px = c;
    }

    public void actionPerformed(ActionEvent e) {
      if ((id==ChooseDay) && (lockEvents==0)) {
        lockEvents++;
        for (int i=0; i<6; i++)
          for (int j=0; j<7; j++)
            days[i][j].setSelected(false);
        days[py][px].setSelected(true);
        current.set(GregorianCalendar.DAY_OF_MONTH,Integer.parseInt(days[py][px].getText()));
        update();
        lockEvents--;
        
      } else if (id==OkButton) {
        setVisible(false);
      } else if (id==CancelButton) {
        current.setTimeInMillis(remember.getTimeInMillis());
        setVisible(false);
      } else if ((e.getSource()==yearCombo) && (lockEvents==0)) {
        lockEvents++;
        int year=1958;
        if (earliest!=null) year = earliest.get(GregorianCalendar.YEAR);
        try { year = Integer.parseInt(yearCombo.getSelectedItem().toString()); }
        catch (NumberFormatException nfe) {}
        current.set(GregorianCalendar.YEAR,year);
        update();
        lockEvents--;
      } else if ((e.getSource()==monthCombo) && (lockEvents==0)) {
        lockEvents++;
        current.set(GregorianCalendar.MONTH,monthCombo.getSelectedIndex());
        if ((latest!=null) && (current.getTimeInMillis()>latest.getTimeInMillis())) {
          current.add(GregorianCalendar.YEAR,-1);
          if ((earliest!=null) && (current.getTimeInMillis()<earliest.getTimeInMillis()))
            current.setTimeInMillis(earliest.getTimeInMillis());
        
        }
        if ((earliest!=null) && (current.getTimeInMillis()<earliest.getTimeInMillis())) {
          current.add(GregorianCalendar.YEAR,1);
          if ((latest!=null) && (current.getTimeInMillis()>latest.getTimeInMillis()))
            current.setTimeInMillis(latest.getTimeInMillis());
        }
        update();
        lockEvents--;
      } else if ((e.getSource()==timeCombo) && (lockEvents==0)) {
        lockEvents++;
        String time = timeCombo.getSelectedItem().toString();
        current.set(GregorianCalendar.HOUR_OF_DAY,Integer.parseInt(time.substring(0,2)));
        current.set(GregorianCalendar.MINUTE,Integer.parseInt(time.substring(3,5)));
        update();
        lockEvents--;
      }
    }

  }
}
