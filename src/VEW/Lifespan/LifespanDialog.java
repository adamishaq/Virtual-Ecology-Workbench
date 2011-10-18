package VEW.Lifespan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import javax.swing.*;

import org.dom4j.Node;

import VEW.Common.DateDialog;
import VEW.Common.VEWUtilities;
import VEW.Common.ModelRun.FGDataSource;
import VEW.Common.ModelRun.ModelRun;
import VEW.Common.Progress.CompletionListener;
import VEW.Common.Progress.Job;
import VEW.Common.Progress.JobProgressDialog;
//import VEW.Common.XML.XMLFile;

public class LifespanDialog extends JDialog {

  private ModelRun theModelRun;
  
  private JComboBox fgCombo;
  private JCheckBox doFG = new JCheckBox("Analyse this variety");

  private GregorianCalendar startDate;
  private GregorianCalendar endDate;
  private GregorianCalendar periodStartDate;
  private GregorianCalendar periodEndDate;
  private DateDialog datePicker;

  protected int[][] liveStages;
  protected int[][] deadStages;
  protected boolean[] doEachFG;
  protected String[] statusStrings;
  
  public LifespanDialog(Dialog owner, File directory) {
    this(owner, new ModelRun(directory));
  }
  
  public LifespanDialog(Dialog owner, ModelRun run) {
    super(owner);
    
    theModelRun = run;
    
    createComponents();
  }
  
  public LifespanDialog(Frame owner, File directory) {
    this(owner, new ModelRun(directory));
  }
  
  public LifespanDialog(Frame owner, ModelRun run) {
    super(owner);
    
    theModelRun = run;
    
    createComponents();  
    loadDateRange();
  }
  
  private void createComponents() {
    setTitle("Calculate lifespan");
    
    JPanel panel = new JPanel(new BorderLayout(0, 0));
    panel.setPreferredSize(new Dimension(400,200));
    
    //    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    JPanel mainPanel = new JPanel(new GridBagLayout());
    
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    
    mainPanel.add(new JLabel("Variety:", SwingConstants.RIGHT), c);
    
    fgCombo = new JComboBox(theModelRun.getFunctionalGroups().toArray());
    liveStages = new int[fgCombo.getItemCount()][];
    deadStages = new int[fgCombo.getItemCount()][];
    statusStrings = new String[fgCombo.getItemCount()];
    doEachFG = new boolean[fgCombo.getItemCount()];
    for (int i=0; i<statusStrings.length; i++) { 
      statusStrings[i] = "Not Selected";
      doEachFG[i] = false;
    }
    
    for (int i=0; i<statusStrings.length; i++) { statusStrings[i] = "Not Selected"; }    
    fgCombo.addActionListener(functionalGroupChanged);
    mainPanel.add(fgCombo, c);
    
    c.gridx = 0;
    mainPanel.add(new JLabel("From:", SwingConstants.RIGHT), c);
    
    c.gridx = 1;
    mainPanel.add(new JButton(startDateAction), c);
    
    c.gridx = 0;
    mainPanel.add(new JLabel("To:", SwingConstants.RIGHT), c);
    
    c.gridx = 1;
    mainPanel.add(new JButton(endDateAction), c);
    
    c.gridx = 0;
    mainPanel.add(new JLabel("Live/dead stages:", SwingConstants.RIGHT), c);
    
    c.gridx = 1;
    mainPanel.add(new JButton(stageEditAction), c);
    
    c.gridx = 0;
    mainPanel.add(new JLabel(""),c);
    c.gridx = 1;
    mainPanel.add(doFG,c);
    doFG.addActionListener(doFGChanged);     

    panel.add(mainPanel, BorderLayout.CENTER);
    
    Box buttonPanel = new Box(BoxLayout.X_AXIS);
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(new JButton(cancelAction));
    buttonPanel.add(new JButton(goAction));
    
    panel.add(buttonPanel, BorderLayout.SOUTH);
    
    setContentPane(panel);
    pack();
    
    Dimension size = getSize();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
  }
  
  private void loadDateRange() {
//    Document model = theModelRun.getModel();
    Node columnTag = theModelRun.getModel().selectSingleNode("/model/scenario/column");
    
    int startYear = columnTag.numberValueOf("startyear").intValue();
    int startDay = columnTag.numberValueOf("startday").intValue() + 1;
    int startMonth = columnTag.numberValueOf("startmonth").intValue();
    
    String startTimeString[] = columnTag.valueOf("starttime").split(":");
    int startTime = 2 * (Integer.parseInt(startTimeString[0]));
    if (startTimeString[1].equals("30"))
      startTime += 1;
    int hours = startTime / 2;
    int minutes = 30 * (startTime % 2);
    
    int duration = columnTag.numberValueOf("duration").intValue();
    int durHours = duration / 2;
    int durMinutes = (duration % 2) * 30;
    
    SimpleTimeZone tz = new SimpleTimeZone(0, "GMT") {
      public boolean useDaylightTime() {
        return false;
      }
    };
    
    startDate = new GregorianCalendar(startYear, startMonth, startDay,hours, minutes, 0);
    startDate.setTimeZone(tz);
    endDate = new GregorianCalendar(startYear, startMonth, startDay,hours, minutes, 0);
    endDate.setTimeZone(tz);
    endDate.add(Calendar.HOUR_OF_DAY, durHours);
    endDate.add(Calendar.MINUTE, durMinutes);
    endDate.add(Calendar.MINUTE, -30);
    
    periodStartDate = (GregorianCalendar) startDate.clone();
    periodEndDate = (GregorianCalendar) endDate.clone();
    
    startDateAction.putValue(Action.NAME, formatCalendar(periodStartDate));
    endDateAction.putValue(Action.NAME, formatCalendar(periodEndDate));
    
    datePicker = new DateDialog(this,1800);
  }
  
  protected static final String formatCalendar(Calendar c) {
    return DateFormat.getDateTimeInstance().format(c.getTime());
  }
  
  protected int getStartTimestep() {
    return (int) ((periodStartDate.getTimeInMillis() - startDate.getTimeInMillis()) / 1800000);
  }
  
  protected int getEndTimestep() {
    return (int) ((periodEndDate.getTimeInMillis() - startDate.getTimeInMillis()) / 1800000);
  }
  
  private Action goAction = new AbstractAction("Go") {
    public void actionPerformed(ActionEvent e) {
      LifespanCalculator lc = new LifespanCalculator(theModelRun);
      for (int fgNo = 0; fgNo < fgCombo.getItemCount(); fgNo++) {
        if (doEachFG[fgNo]) {
          FGDataSource fg = (FGDataSource) fgCombo.getItemAt(fgNo);
          int nStages = fg.getStages().size();
      
          boolean[] live = new boolean[nStages];
          for (int i = 0; i < liveStages[fgNo].length; i++) live[liveStages[fgNo][i]] = true;
      
          boolean[] dead = new boolean[nStages];
          for (int i = 0; i < deadStages[fgNo].length; i++) dead[deadStages[fgNo][i]] = true;
      
          Job job = lc.createJob(fg, getStartTimestep(), getEndTimestep(), live, dead);
      
          JobProgressDialog d = new JobProgressDialog(LifespanDialog.this, job);
          d.setModal(true);
          d.show(new CompletionListener() {
      
            public void jobCancelled() {
              setVisible(false);
            }
      
            public void jobCompleted(Object data) {
              setVisible(false);
            }
          });
        }
      }
      VEWUtilities.showInfoMessage(getOwner(), "Lifespan", "Lifespan calculation complete");      
    }
  };
  
  private Action cancelAction = new AbstractAction("Cancel") {
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
    }
  };
  
  private Action startDateAction = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      datePicker.show(startDate, endDate, periodStartDate);
      periodStartDate = (GregorianCalendar) datePicker.getDate().clone();
      
      putValue(NAME, formatCalendar(periodStartDate));
    }
  };
  
  private Action endDateAction = new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
      datePicker.show(startDate, endDate, periodEndDate);
      periodEndDate = (GregorianCalendar) datePicker.getDate().clone();
      
      putValue(NAME, formatCalendar(periodEndDate));
    }
  };
  
  private Action stageEditAction = new AbstractAction("Not selected") {
    public void actionPerformed(ActionEvent e) {
      FGDataSource fg = (FGDataSource) fgCombo.getSelectedItem();
      int fgNo = fgCombo.getSelectedIndex();
      StageEditor se = new StageEditor(LifespanDialog.this, fg.getStages(), fg.getTransitions(), liveStages[fgNo], deadStages[fgNo]);
      se.setModal(true);
      se.setVisible(true);
      
      if (se.isAccepted()) {
        liveStages[fgNo] = se.getLiveStages();
        deadStages[fgNo] = se.getDeadStages();
        statusStrings[fgNo] = "Selected";
        putValue(NAME, "Selected");
        doFG.setSelected(true);
        doEachFG[fgNo] = true;
      }
    }
  };

  private final ActionListener functionalGroupChanged = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      stageEditAction.putValue(Action.NAME, statusStrings[fgCombo.getSelectedIndex()]);
      doFG.setSelected(doEachFG[fgCombo.getSelectedIndex()]);      
    }
  }; 
  
  private final ActionListener doFGChanged = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      doEachFG[fgCombo.getSelectedIndex()]=doFG.isSelected();      
    }
  }; 
  
  /**
   * @param args
   */
  public static void main(String[] args) {
    LifespanDialog d = new LifespanDialog(new JFrame(), new File(args[0]));
    d.setModal(true);
    d.setVisible(true);
  }

}
