package VEW.Common.ParticleChooser;

import gnu.trove.TLongHashSet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import VEW.Analyser4.Analyser4;
import VEW.Analyser4.BackgroundColour;
import VEW.Common.DateDialog;
import VEW.Common.WorkingTable;
import VEW.Common.ObjectList.ObjectList;
import VEW.Common.Progress.CompletionListener;
import VEW.Common.Progress.Job;
import VEW.Common.Progress.JobProgressDialog;
import VEW.Common.Progress.ProgressListener;
import VEW.Common.Random.RanMT;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class ParticleChooserDialog extends JDialog {
  
  private final static String DATA_FORMATS_FILENAME = "DataFormats.xml";
  private final static String MODEL_FILENAME = "Model.xml";
    
  private File dataPath;
  private File dataFile;
  private File timeFile;
  private double stepSizeHours;
  private XMLFile dataFormatsFile;
  private XMLFile modelFile;
  private EventHandler eh = new EventHandler();
  
  //private DefaultListModel criteriaListModel;
  //private ListModel statisticsListModel;
  
  private WorkingTable table;
  private JComboBox speciesCombo;
  private JButton OKButton = new JButton("OK");
  private JButton cancelButton = new JButton("Cancel");  
  private JButton startDateButton;
  private JButton endDateButton;
  private JButton updateButton;
  private JButton makeSubset = new JButton("Make Subset");
  private JComboBox sortCombo;
  private JRadioButton ascendingSortButton;
  private JRadioButton descendingSortButton;
  
  private String[] variables;
  private ObjectList filterList;
  private ObjectList statisticsList;
  private JComboBox sortStatisticCombo;
  private GregorianCalendar startDate = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar endDate = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar periodStartDate = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar periodEndDate = new GregorianCalendar(DateDialog.GMTTimeZone);
  private DateDialog datePicker;
  
  private boolean analyserUsage;
  private JButton chooseIDs = new JButton("Specify IDs");
  private JLabel colourLabel = new JLabel("Pick colours from:");
  private JComboBox colourFilter = new JComboBox();
  private PCDPreviewPanel colourPreview = new PCDPreviewPanel();
  private Color[] idColours;
  
  private JButton selectAllButton;
  private JButton selectNoneButton;
  private JButton selectRandomButton;
  private JFormattedTextField randomSeed;
  private JFormattedTextField randomCount;
  private JCheckBox descendantsCheck;
  private JButton showFamilyTree = new JButton("Show Family Tree");
  private JRadioButton chooseRandom = new JRadioButton("Random colours");
  private JRadioButton choosePreview = new JRadioButton("Gradient");
  private ButtonGroup colourChoice = new ButtonGroup();
  
  private RanMT random = new RanMT();
  protected boolean accepted;
  private boolean allowDescendants;
  protected boolean closeCancelled;
  protected long[] descendants;
  
  private Analyser4 a4 = null;
  
  public void setAnalyserUsage(boolean b) {
    analyserUsage = b;
    makeSubset.setVisible(analyserUsage);
    colourPreview.setVisible(analyserUsage);
    colourFilter.setVisible(analyserUsage);
    colourLabel.setVisible(analyserUsage);
    chooseIDs.setVisible(analyserUsage);
    choosePreview.setVisible(analyserUsage);
    showFamilyTree.setVisible(analyserUsage);
    chooseRandom.setVisible(analyserUsage);
  }
  
  public ParticleChooserDialog(Dialog owner, Analyser4 _a4, String _dataPath, GregorianCalendar start, GregorianCalendar end, float stepInHours) {
    this(owner, _a4, new File(_dataPath),start,end, stepInHours);
  }
  
  public ParticleChooserDialog(Dialog owner, Analyser4 _a4, File _dataPath, GregorianCalendar start, GregorianCalendar end, float stepInHours) {
    this(owner, _a4, _dataPath, false, start, end, stepInHours);
  }
  
  public ParticleChooserDialog(Dialog owner, Analyser4 _a4, String _dataPath, boolean _allowDescendants, GregorianCalendar start, GregorianCalendar end, float stepInHours) {
    this(owner, _a4, new File(_dataPath), _allowDescendants, start, end, stepInHours);
  }
  
  public ParticleChooserDialog(Dialog owner, Analyser4 _a4, File _dataPath, boolean _allowDescendants, GregorianCalendar start, GregorianCalendar end, float stepInHours) {
    super(owner);
    a4=_a4;
    
    dataPath = _dataPath;
    dataFormatsFile = XMLFile.LoadFile(new File(dataPath, DATA_FORMATS_FILENAME));
    modelFile = XMLFile.LoadFile(new File(dataPath, MODEL_FILENAME));
    
    allowDescendants = _allowDescendants;
    
    setTitle("Choose particles...");
    startDate.setTimeInMillis(start.getTimeInMillis());
    endDate.setTimeInMillis(end.getTimeInMillis());
    endDate.add(GregorianCalendar.SECOND,(int) -(stepInHours*3600));    
    periodStartDate.setTimeInMillis(startDate.getTimeInMillis());
    periodEndDate.setTimeInMillis(endDate.getTimeInMillis());
    datePicker = new DateDialog(this,1800);    
    createComponents();
    functionalGroupChanged();

  }
  
  private void createComponents() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    panel.add(createControlsPanel(), BorderLayout.WEST);
    panel.add(createParticleList(), BorderLayout.CENTER);
    panel.add(createSelectionPanel(), BorderLayout.EAST);
    panel.add(createButtonPanel(), BorderLayout.SOUTH);
    
    setContentPane(panel);
    table.addMouseListener(eh);
    pack();
  }
  
  private Component createSelectionPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridy = GridBagConstraints.RELATIVE;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.fill = GridBagConstraints.HORIZONTAL;
    
    selectAllButton = new JButton("Select all");
    selectAllButton.setEnabled(false);
    selectAllButton.addActionListener(eh);
    panel.add(selectAllButton, c);
    
    selectNoneButton = new JButton("Select none");
    selectNoneButton.setEnabled(false);
    selectNoneButton.addActionListener(eh);
    
    panel.add(selectNoneButton, c);
    
    c.gridwidth = 1;
    panel.add(new JLabel("Random seed:", SwingConstants.RIGHT), c);
    
    randomSeed = new JFormattedTextField(NumberFormat.getIntegerInstance());
    randomSeed.setValue(new Long(12345));
    Dimension s = (Dimension) randomSeed.getMinimumSize().clone();
    s.width = 2000;
    randomSeed.setMinimumSize(s);
    c.gridx = 1;
    c.insets.left = 5;
    c.insets.top = 10;
    c.weightx = 1;
    panel.add(randomSeed, c);
    
    c.gridx = 0;
    c.insets.top = 0;
    c.insets.left = 0;
    c.weightx = 0;
    panel.add(new JLabel("Number:", SwingConstants.RIGHT), c);
    
    randomCount = new JFormattedTextField(NumberFormat.getIntegerInstance());
    randomCount.setMinimumSize(s);
    c.gridx = 1;
    c.insets.left = 5;
    c.weightx = 1;
    panel.add(randomCount, c);
    
    selectRandomButton = new JButton("Select random");
    selectRandomButton.setEnabled(false);
    selectRandomButton.addActionListener(eh);
    c.gridx = 0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.weightx = 0;
    panel.add(selectRandomButton, c);
    
    c.gridx = 0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets.top=2;
    c.weightx = 0;
    
    JPanel colourPanel = new JPanel(new BorderLayout());
    JPanel chooseType = new JPanel(new FlowLayout());
    chooseType.add(chooseRandom);
    chooseType.add(choosePreview);
    chooseRandom.setSelected(true);
    colourFilter.setEnabled(false);
    colourPreview.setVisible(false);
    
    colourPreview.setPreferredSize(new Dimension(100,20));
    colourPreview.setBorder(new EtchedBorder());
    colourChoice.add(chooseRandom);
    colourChoice.add(choosePreview);
    colourPanel.add(chooseType,BorderLayout.NORTH);
    colourPanel.add(colourPreview,BorderLayout.CENTER);
    colourPanel.add(colourFilter,BorderLayout.SOUTH);
    
    panel.add(colourPanel,c);
    BackgroundColour.addColours(colourFilter);
    chooseIDs.addActionListener(eh);
    colourFilter.addActionListener(eh);
    chooseRandom.addActionListener(eh);
    choosePreview.addActionListener(eh);
    
    c.gridx = 0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.weightx = 0;
    panel.add(chooseIDs, c);
    
    
    c.gridx = 0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.weightx = 0;
    panel.add(makeSubset, c);
    makeSubset.addActionListener(eh);
    
    c.gridx = 0;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.weightx = 0;
    panel.add(showFamilyTree, c);
    showFamilyTree.addActionListener(eh);
    showFamilyTree.setEnabled(false);
    
    c.fill = GridBagConstraints.BOTH;
    c.weighty = 1;
    panel.add(Box.createGlue(), c);
    
    if (allowDescendants) {
      descendantsCheck = new JCheckBox("Calculate descendants");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weighty = 0;
      panel.add(descendantsCheck, c);
    }
    
    panel.setEnabled(false);
    
    return panel;
  }
  
  private Component createParticleList() {
    table = new WorkingTable();
    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    JScrollPane scrollPane = new JScrollPane(table);
//  scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
    return scrollPane;
  }
  
  public void setVariety(String s) {
    speciesCombo.removeAllItems();
    speciesCombo.addItem(s);
  }
  
  public void setAllVarieties() {
    speciesCombo.removeAllItems();
    String[] fgs = getFunctionalGroups();
    for (int i=0; i<fgs.length; i++) speciesCombo.addItem(fgs[i]);
  }
  
  private Component createControlsPanel() {
    final int GROUP_SEPARATION = 10;
    
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
//  panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 10));
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    
    c.gridx = 0;
    c.gridy = 0;
    c.insets.right = 5;
    c.weighty = 0;
    panel.add(new JLabel("Functional group:", SwingConstants.RIGHT), c);
    
    speciesCombo = new JComboBox(getFunctionalGroups());
    speciesCombo.addActionListener(eh);
    c.gridx = 1;
    c.gridy = GridBagConstraints.RELATIVE;
    c.insets.right = 0;
    panel.add(speciesCombo, c);
    
    c.gridx = 0;
    c.insets.top = 0;
    c.insets.right = 5;
    c.gridwidth = 1;
    panel.add(new JLabel("From:", SwingConstants.RIGHT), c);
    
    startDateButton = new JButton(DateDialog.getString(startDate));
    startDateButton.addActionListener(eh);
    c.gridx = 1;
    c.insets.right = 0;
    panel.add(startDateButton, c);
    
    c.gridx = 0;
    c.gridwidth = 1;
    c.insets.right = 5;
    panel.add(new JLabel("To:", SwingConstants.RIGHT), c);
    
    endDateButton = new JButton(DateDialog.getString(endDate));
    endDateButton.addActionListener(eh);
    c.gridx = 1;
    c.insets.right = 0;
    panel.add(endDateButton, c);
    
    c.gridx = 0;
    c.insets.top = GROUP_SEPARATION;
    c.gridwidth = GridBagConstraints.REMAINDER;
    panel.add(new JLabel("Selection criteria:", SwingConstants.LEFT), c);
    
    filterList = new ObjectList(null);
    c.gridx = 0;
    c.insets.top = 5;
    c.insets.bottom = 5;
    c.insets.bottom = 5;
//  c.weighty = 1;
    c.fill = GridBagConstraints.BOTH;
    c.gridwidth = GridBagConstraints.REMAINDER;
    panel.add(filterList, c);
    
    c.gridx = 0;
    c.insets.top = GROUP_SEPARATION;
//  c.weighty = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 1;
    panel.add(new JLabel("Sort by:", SwingConstants.RIGHT), c);
    
    sortCombo = new JComboBox();
    c.gridx = 1;
    c.insets.top = GROUP_SEPARATION;
    panel.add(sortCombo, c);
    
    c.gridx = 0;
    c.insets.top = 0;
    panel.add(new JLabel(), c);
    
    sortStatisticCombo = new JComboBox(ParticleStatistics.DESCRIPTIONS);
    c.gridx = 1;
    panel.add(sortStatisticCombo, c);
    
    c.gridx = 0;
    panel.add(new JLabel(), c);
    
    ButtonGroup sortGroup = new ButtonGroup();
    ascendingSortButton = new JRadioButton("Ascending");
    sortGroup.add(ascendingSortButton);
    ascendingSortButton.setSelected(true);
    c.gridx = 1;
    panel.add(ascendingSortButton, c);
    
    c.gridx = 0;
    panel.add(new JLabel(), c);
    
    descendingSortButton = new JRadioButton("Descending");
    sortGroup.add(descendingSortButton);
    c.gridx = 1;
    panel.add(descendingSortButton, c);
    
    c.gridx = 0;
    c.insets.top = GROUP_SEPARATION;
    c.gridwidth = GridBagConstraints.REMAINDER;
    panel.add(new JLabel("Also calculate:", SwingConstants.LEFT), c);
    
    statisticsList = new ObjectList(null);
    c.gridx = 0;
    c.insets.top = 5;
    c.insets.bottom = 5;
    c.insets.bottom = 5;
    c.gridwidth = GridBagConstraints.REMAINDER;
    panel.add(statisticsList, c);
    
    updateButton = new JButton("Update >>>");
    updateButton.addActionListener(eh);
    
    c.gridx = 0;
    c.insets.top = GROUP_SEPARATION * 2;
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.SOUTH;
    panel.add(updateButton, c);
    
    return new JScrollPane(panel);
  }
  
  protected Component createButtonPanel() {
    Box buttonBox = new Box(BoxLayout.X_AXIS);
    buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    buttonBox.add(Box.createHorizontalGlue());
    cancelButton.addActionListener(eh);
    buttonBox.add(cancelButton);
    buttonBox.add(Box.createRigidArea(new Dimension(5, 0)));
    OKButton.addActionListener(eh);
    buttonBox.add(OKButton);
    return buttonBox;
  }
  
  protected void setStartDate() {
    datePicker.show(startDate, periodEndDate, periodStartDate);
    periodStartDate.setTimeInMillis(datePicker.getDate().getTimeInMillis());
    startDateButton.setText(DateDialog.getString(periodStartDate));
  }
  
  protected void setEndDate() {
    datePicker.show(periodStartDate, endDate, periodEndDate);
    periodEndDate.setTimeInMillis(datePicker.getDate().getTimeInMillis());
    endDateButton.setText(DateDialog.getString(periodEndDate));
  }
  
  protected void functionalGroupChanged() {
    XMLTag tag = dataFormatsFile.getTagWhere("dataformat/functionalgroup",
        "name", (String) speciesCombo.getSelectedItem());
    if (tag!=null) {
      dataFile = new File(dataPath, tag.getValue("data"));
      if (tag.getValue("time")!=null) timeFile = new File(dataPath, tag.getValue("time"));
      else timeFile = null;
      if (dataFormatsFile.getValue("dataformat/step")==null) stepSizeHours = 0.5;
      else stepSizeHours = Double.parseDouble(dataFormatsFile.getValue("dataformat/step"));
      variables = tag.getValues("var/desc");
    
      sortCombo.setModel(new DefaultComboBoxModel(variables));
      filterList.setManager(new FilterManager(variables));
      statisticsList.setManager(new StatisticManager(variables));
    }
  }
  
  protected void updateParticleList() {
    ParticleStatistics ps = new ParticleStatistics(Arrays.asList(variables));
    ParticleStatisticsTableModel model = new ParticleStatisticsTableModel(ps);
    
    // TO-DO: Fix these 0.5s into timestep sizes.
    model.setStartTime(periodStartDate.getTimeInMillis());
    model.setEndTime(periodEndDate.getTimeInMillis());
    model.setSimStartTime(startDate.getTimeInMillis());
    
    List filters = filterList.getObjects();
    for (Iterator iter = filters.iterator(); iter.hasNext();) {
      Filter filter = (Filter) iter.next();
      model.addFilter(filter);
    }
    
    model.setSort(sortCombo.getSelectedIndex(), 
        sortStatisticCombo.getSelectedIndex(), 
        ParticleStatisticsTableModel.INCREASING);
    
    List statistics = statisticsList.getObjects();
    for (Iterator iter = statistics.iterator(); iter.hasNext();) {
      Statistic statistic = (Statistic) iter.next();
      model.addColumn(statistic.getVariable(), statistic.getStatistic());
    }
    
    model.setSort(
        sortCombo.getSelectedIndex(),
        sortStatisticCombo.getSelectedIndex(),
        ascendingSortButton.isSelected() ? ParticleStatisticsTableModel.INCREASING
            : ParticleStatisticsTableModel.DECREASING);
    
    XMLTag theFormat = dataFormatsFile.getTagWhere("functionalgroup","name",speciesCombo.getSelectedItem().toString()); 
    boolean useFloats = dataFormatsFile.getValue("dataformat/format").equals("float");
    try {
      model.loadData(dataFile, timeFile, stepSizeHours, useFloats, this, theFormat, dataFormatsFile);
      table.setModel(model);
      
      selectAllButton.setEnabled(true);
      selectNoneButton.setEnabled(true);
      selectRandomButton.setEnabled(true);
    } catch (FileNotFoundException e) { e.printStackTrace();  
    } catch (IOException e) { e.printStackTrace();
    }
  }
  
  private String[] getFunctionalGroups() {
    return dataFormatsFile.getValues("dataformat/functionalgroup/name");
  }
  
  private void selectRandom() {
    if (randomSeed.getValue()!=null) {
      long seed = ((Number) randomSeed.getValue()).longValue();
      if (randomCount.getValue()!=null) {
        int count = ((Number) randomCount.getValue()).intValue();
        int max = table.getRowCount();
        int index;
    
        if (count > max) {
          JOptionPane.showMessageDialog(this, "Cannot select " + count + " particles, there are only " + max, "Error", JOptionPane.ERROR_MESSAGE);
          return;
        }
    
        random.setSeed(seed);
    
        table.clearSelection();
    
        Set selectedSet = new TreeSet();
        for (int i = 0; i < count; i++) {
          index = random.choose(max);
          while (selectedSet.contains(new Integer(index)))
            index = random.choose(max);
      
          selectedSet.add(new Integer(index));
          table.addRowSelectionInterval(index, index);
        }
      }
    }
  }
  
  private void calculateDescendants() {
    LineageCalculator lc = new LineageCalculator(dataPath);
    
    long[] selected = getParticles();
    TLongHashSet particleSet = new TLongHashSet(selected.length);
    particleSet.addAll(selected);
    
    Job job = lc.createJob(particleSet, (String) speciesCombo.getSelectedItem(), Integer.MAX_VALUE);
    JobProgressDialog d = new JobProgressDialog(this, job);
    d.setModal(true);
    closeCancelled = false;
    descendants = null;
    d.show(new CompletionListener() {
    
      public void jobCancelled() {
        System.out.println("Cancelled descendant calculation");
        closeCancelled = true;
      }
    
      public void jobCompleted(Object data) {
        System.out.println("Completed descendant calculation");
        TLongHashSet _particleSet = (TLongHashSet) data;
        descendants = _particleSet.toArray();
      }
    
    });
  }
  
  
  public long[] getParticles() {
    ParticleStatisticsTableModel model = (ParticleStatisticsTableModel) table.getModel();
    long[] allParticles = model.getParticles();
    int[] indices = table.getSelectedRows();
    
    long[] selectedParticles = new long[indices.length];
    
    for (int i = 0; i < indices.length; i++)
      selectedParticles[i] = allParticles[indices[i]];
    
    return selectedParticles;
  }
  
  public boolean getRandomColours() { return chooseRandom.isSelected(); }
  
  public Color[] getColours() {
    int[] indices = table.getSelectedRows();    
    idColours = new Color[indices.length];
    if (choosePreview.isSelected()) {
      BackgroundColour.setGraduations(255);    
      for (int i = 0; i < indices.length; i++)
        idColours[i] = BackgroundColour.getColour(i,0,indices.length);
    } 
    return idColours;
  }
  
  public long[] getDescendants() {
    return descendants;
  }
  
  public ParticleGroup getParticleGroup() {
    return new ParticleGroup((String) speciesCombo.getSelectedItem(), "", getParticles(), getDescendants());
  }
  
  public boolean isAccepted() {
    return accepted;
  }
  
  
  class EventHandler implements ActionListener, MouseListener {
    
    public EventHandler() {}

    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==OKButton) {
        accepted = true;
        closeCancelled = false;
        if (descendantsCheck != null && descendantsCheck.isSelected())
          calculateDescendants();
        if (!closeCancelled)
          setVisible(false);
     
      } else if (e.getSource()==cancelButton) {
        accepted = false;
        setVisible(false);
    
      } else if (e.getSource()==selectNoneButton) table.clearSelection();
      else if (e.getSource()==updateButton) updateParticleList();
      else if (e.getSource()==endDateButton) setEndDate();
      else if (e.getSource()==startDateButton) setStartDate();
      else if (e.getSource()==speciesCombo) functionalGroupChanged();
      else if (e.getSource()==selectRandomButton) selectRandom();
      else if (e.getSource()==selectAllButton) table.selectAll();

      else if (e.getSource()==colourFilter) {
        BackgroundColour.setType(colourFilter.getSelectedIndex());
        colourPreview.repaint();
      
      } else if (e.getSource()==chooseRandom) {
        colourFilter.setEnabled(false);
        colourPreview.setVisible(false);
      
      } else if (e.getSource()==choosePreview) {
        colourFilter.setEnabled(true);
        colourPreview.setVisible(true);
      
      } else if (e.getSource()==chooseIDs) {
        String ids = JOptionPane.showInputDialog(ParticleChooserDialog.this, "Type particles, separated with comma");
        if (ids!=null) {
          int countIDs=0;
          for (int i=0; i<ids.length(); i++) 
            if (ids.charAt(i)==',') countIDs++;
          long theIDs[] = new long[countIDs+1];
          countIDs=0;
          while (ids.indexOf(",")>0) {
            theIDs[countIDs]=Long.parseLong(ids.substring(0,ids.indexOf(",")));
            ids=ids.substring(ids.indexOf(",")+1);
            countIDs++;
          }
          theIDs[countIDs]=Long.parseLong(ids);
          ParticleStatisticsTableModel model = (ParticleStatisticsTableModel) table.getModel();
          long[] allParticles = model.getParticles();
          for (int i=0; i<allParticles.length; i++) {
            for (int j=0; j<theIDs.length; j++) {
              if (allParticles[i]==theIDs[j]) {
                table.addRowSelectionInterval(i,i);
                theIDs[j]=-1;
              }
            }
          }
          boolean ok = true;
          for (int i=0; i<theIDs.length; i++)
            if (theIDs[i]!=-1) ok = false;
          if (ok) {
            JOptionPane.showMessageDialog(ParticleChooserDialog.this,"All IDs selected");
          } else {
            String s = "Could not find: ";
            for (int i=0; i<theIDs.length; i++)
              if (theIDs[i]!=-1) s = theIDs[i]+" ";
            JOptionPane.showConfirmDialog(ParticleChooserDialog.this,s);
          }
        }
        
      } else if (e.getSource()==makeSubset) {
        XMLTag tag = dataFormatsFile.getTagWhere("dataformat/functionalgroup","name", speciesCombo.getSelectedItem().toString());
        String name = tag.getValue("name");
        if (tag!=null) {
          long[] ids = getParticles();
          if (ids.length>0) {
            String newName = (String) JOptionPane.showInputDialog(ParticleChooserDialog.this,"Choose Name for new Functional Group Subset","Choose Name",JOptionPane.QUESTION_MESSAGE,null,null,name);
            if ((newName!=null) && (newName.length()>1)) {
              DataSetJob dsj = new DataSetJob(tag, newName, ids);
              JobProgressDialog d = new JobProgressDialog(ParticleChooserDialog.this, dsj);
              d.setModal(true);
              d.show();
            }
          }
        }
      } else if (e.getSource()==showFamilyTree) {
        
        XMLTag tag = dataFormatsFile.getTagWhere("dataformat/functionalgroup","name", speciesCombo.getSelectedItem().toString());
        int[] rows = table.getSelectedRows();
        long firstID = Long.parseLong(table.getModel().getValueAt(rows[0],0).toString());
        for (int i=1; i<rows.length; i++) {
          long otherID = Long.parseLong(table.getModel().getValueAt(rows[i],0).toString());
          if (otherID<firstID) firstID=otherID;
        }
        FamilyTree ft = new FamilyTree(ParticleChooserDialog.this,a4,tag,dataPath.getPath(),firstID,dataFormatsFile,modelFile);
        ft.setVisible(true);
      }
    }

    public void mouseClicked(MouseEvent e) {
      if (e.getSource()==table) {
        showFamilyTree.setEnabled(table.getSelectedRows().length>=1);
      }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
  }

  public void ReallySkip(long Bytes, DataInputStream TheStream) throws Exception {
    long BytesToGo = Bytes;
    while (BytesToGo > Integer.MAX_VALUE)
      BytesToGo -= TheStream.skipBytes(Integer.MAX_VALUE);
    if (BytesToGo > 0) TheStream.skipBytes((int) BytesToGo);
  }
  
  private void createSubset(XMLTag tag, String newName, long[] ids, ProgressListener progress, CompletionListener completion) {
    String oldFile = tag.getValue("data");
    byte bytesPerVar = 8;
    if ((tag.getValue("format")!=null) && (tag.getValue("format").equals("float"))) bytesPerVar=4;
    if ((tag.getParentTag().getValue("format")!=null) && (tag.getParentTag().getValue("format").equals("float"))) bytesPerVar=4;
    int vars = tag.getTags("var").length;
    String newFileStub = oldFile.substring(0,oldFile.length()-4);
    int fileNo=1;
    while (new File(dataPath.getPath()+File.separator+newFileStub+"_"+fileNo+".bin").exists()) fileNo++;
    String newFile = newFileStub+"_"+fileNo+".bin";
    String newTimeFile = "time_"+newFile;
    String newLineageFile = "lineage_"+newFile;
    String oldTimeFile = tag.getValue("time");
    String oldLineageFile = tag.getValue("lineage");
    boolean Version1_1 = ((dataFormatsFile.getValue("version")!=null) && (dataFormatsFile.getValue("version").equals("1.1")));
    if (dataFormatsFile.getValue("dataformat/step")==null) stepSizeHours = 0.5;
    else stepSizeHours = Double.parseDouble(dataFormatsFile.getValue("dataformat/step"));
    long timeStepMillis = (long) (stepSizeHours*3600*1000);

    
    boolean oldZip = tag.getValue("zip").equals("true");
    
    long startMillis=a4.startSimMillis;
    long endMillis=a4.endSimMillis;
    
    int numberOfIDs = ids.length;
    
    DataInputStream in = null;
    DataOutputStream out = null;
    DataInputStream timein = null;
    DataOutputStream timeout = null;
    
    long firstUsefulMilli = startMillis;
    long lastUsefulMilli = endMillis;
    long timeMillis =startMillis;
    long rememberMillis =startMillis-timeStepMillis;   
    int j;
    
    try {
      if (oldZip) {
        in = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(dataPath.getPath()+File.separator+oldLineageFile)))));
        out = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(dataPath.getPath()+File.separator+newLineageFile)))));
      } else {
        in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath.getPath()+File.separator+oldFile)));
        out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataPath.getPath()+File.separator+newFile)));
      }
      progress.setMinimum(0);
      progress.setMaximum(1000);
      progress.setMessage("Creating new lineage data");
      byte type;
      long parentID;
      long childID;
      int parentStage=-1;
      int childStage=-1;
      j = 0;
      
      loop1: while ((in.available()>0) && (numberOfIDs>0)) {
        synchronized (this) {
          if (closeCancelled) {
            completion.jobCancelled();
            break loop1;
          }
        }

        if (Version1_1) timeMillis = in.readLong();
        else timeMillis = startMillis+(in.readInt()*timeStepMillis);
        progress.setNote("(Worst case)");
        if (timeMillis!=rememberMillis) {
          progress.setProgress((int)(1000*((timeMillis-startMillis))/((1.0f*endMillis)-startMillis)));
          rememberMillis=timeMillis;
        }
        type = in.readByte();
        parentID = in.readLong();
        childID = in.readLong();
        if (Version1_1) {
          parentStage = in.readInt();
          childStage = in.readInt();
        }
        j=0;
        long firstCreation=-1;
        int countCreations=0;
        int rememberNoIDs = numberOfIDs;
        while ((j<numberOfIDs) && (numberOfIDs>0) && (!closeCancelled)) {
          if ((ids[j]==parentID) || (ids[j]==childID)) {
            if (Version1_1) out.writeLong(timeMillis);
            else out.writeInt((int)((timeMillis-startMillis)/timeStepMillis));
            out.writeByte(type);
            out.writeLong(parentID);
            out.writeLong(childID);
            if (Version1_1) {
              out.writeInt(parentStage);
              out.writeInt(childStage);
            }
            if (((type==5) && (parentID==ids[j])) ||
                ((type==4) && (childID==ids[j]))) { // removal 
              ids[j]=ids[numberOfIDs-1];
              numberOfIDs--;
              if (numberOfIDs==0) lastUsefulMilli=timeMillis;
            } 
            else if ((((type==1) || (type==3)) && (childID==ids[j])) ||
                      ((type==2) && (parentID==ids[j]))) { // split/pchange/create makes new child
              countCreations++;
              if (firstCreation==-1) firstCreation=timeMillis;
              if (countCreations==rememberNoIDs) firstUsefulMilli=firstCreation;
            } 
          } 
          j++;
        }
      }
    } catch (Exception ex) {if (!(ex instanceof EOFException)) 
      ex.printStackTrace();
    
    
    }
    
    try {
      out.flush();
      out.close();
      in.close();
      progress.setMinimum(0);
      progress.setProgress(0);
      progress.setMaximum(1000);
      progress.setMessage("Creating new FG file");
      if (oldZip) {
        in = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(dataPath.getPath()+File.separator+oldFile)))));
        out = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(dataPath.getPath()+File.separator+newFile)))));
        if (oldTimeFile!=null) timein = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(dataPath.getPath()+File.separator+oldTimeFile)))));
        timeout = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(new BufferedOutputStream(new FileOutputStream(dataPath.getPath()+File.separator+newTimeFile)))));        
      } else {
        in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath.getPath()+File.separator+oldFile)));
        out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataPath.getPath()+File.separator+newFile)));
        if (oldTimeFile!=null) timein = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath.getPath()+File.separator+oldTimeFile)));
        timeout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataPath.getPath()+File.separator+newTimeFile)));
      }
      
      boolean oldVersion = false;
      if (tag.getTags("var")[0].getTag("type")==null) oldVersion=true;
      boolean useFloats = dataFormatsFile.getValue("dataformat/format").equals("float");
      int recSize=0;
      int noVars=tag.getTags("var").length;
      byte[] types = new byte[noVars];    
      for (int i=0; i<tag.getTags("var").length; i++) {
        if (oldVersion) {
          types[i]=0;
          recSize+=(useFloats?4:8);
        } else if (tag.getTags("var")[i].getValue("type").equals("real")) {
          types[i]=0;
          recSize+=(useFloats?4:8);
        } else if (tag.getTags("var")[i].getValue("type").equals("int")) {
          types[i]=1;
          recSize+=4;
        } else if (tag.getTags("var")[i].getValue("type").equals("long")) {
          types[i]=2;
          recSize+=8;
        }
      }
      
      boolean alreadyReadTimestep=false;
      timeMillis=-1;
      if (timein!=null) {
        timeMillis=firstUsefulMilli;
        progress.setNote("Skipping... (Using time index)");
        long timeStepsToSkip = (firstUsefulMilli-startMillis)/timeStepMillis;
        if (firstUsefulMilli>1) ReallySkip(((timeStepsToSkip-1)*8),timein);
        long toSkip = timein.readLong();
        long total = toSkip;
        timein.close();
        long delta = 100000;
        long skipped = 0;
        while (toSkip>0) {
          delta=100000;
          if (delta>toSkip) delta=toSkip;
          skipped+=delta;
          toSkip-=delta;
          ReallySkip(delta,in);
          progress.setProgress((int)(1000*((skipped*1.0f)/total)));
        }
      } else {
        progress.setNote("Skipping... (Time indexing would make this faster)");
        timeMillis=startMillis;
        loop2: while (timeMillis<(firstUsefulMilli)) {
          synchronized (this) {
            if (closeCancelled) {
              completion.jobCancelled();
              break loop2;
            }
          }
          if (oldVersion) timeMillis = (int) ((useFloats? in.readFloat() : in.readDouble())*2);
          else timeMillis = in.readLong();
          if (!Version1_1) timeMillis = startMillis+(timeMillis*timeStepMillis);
          progress.setProgress((int)(1000*((1.0f*timeMillis)/firstUsefulMilli)));
          if (timeMillis<(firstUsefulMilli)) {
            if (oldVersion) ReallySkip(recSize-(useFloats?4:8),in);
            else ReallySkip(recSize-8,in);
          } else alreadyReadTimestep=true;
        }
      }
      progress.setNote("Copying relevant data...");
      long id = -1;
      ids = getParticles();
      boolean found=false;
      long bytesWritten=0;
      if (timeout!=null) for (long i=startMillis; i<firstUsefulMilli; i+=timeStepMillis) timeout.writeLong(0);
      rememberMillis=timeMillis;
      loop3: while (timeMillis<=lastUsefulMilli) {
        synchronized (this) {
          if (closeCancelled) {
            completion.jobCancelled();
            break loop3;
          }
        }
        
        if (!alreadyReadTimestep) {
          if (oldVersion) timeMillis = (int) ((useFloats? in.readFloat() : in.readDouble())*2);
          else timeMillis = in.readLong();
          if (!Version1_1) timeMillis = startMillis+(timeMillis*timeStepMillis);
        }
        alreadyReadTimestep=false;
        if (rememberMillis!=timeMillis) {
          progress.setProgress((int)(1000*((timeMillis-firstUsefulMilli)/((1.0f*lastUsefulMilli)-firstUsefulMilli))));
          while (rememberMillis<timeMillis) {
            timeout.writeLong(bytesWritten);
            rememberMillis+=(stepSizeHours*3600*1000);
          }
          rememberMillis=timeMillis;
        }
        if (oldVersion) id = (long) ((useFloats) ? in.readFloat() : in.readDouble());
        else id = in.readLong();
        j=0;
        found=false;
        while ((j<ids.length) && (!found)) {
          if (ids[j]==id) {
            found=true;
            long timeStep;
            if (!Version1_1) timeStep = ((timeMillis-startMillis)/timeStepMillis);
            else timeStep = timeMillis;
            if (oldVersion) {
              if (useFloats) {
                out.writeFloat(timeStep/2.0f);
                out.writeFloat(id);
              } else {
                out.writeDouble(timeStep/2.0);
                out.writeDouble(id);
              }
            } else {
              out.writeLong(timeStep);
              out.writeLong(id);
            }
            for (int i=0; i<vars-2; i++) {
              if (oldVersion) {
                if (useFloats) {
                  out.writeFloat(in.readFloat());
                  bytesWritten+=4;                  
                } else {
                  out.writeDouble(in.readDouble());
                  bytesWritten+=8;                  
                }
              } else {
                if (types[i+2]==0) {
                  if (useFloats) {
                    out.writeFloat(in.readFloat());
                    bytesWritten+=4;
                  } else {
                    out.writeDouble(in.readDouble());
                    bytesWritten+=8;                    
                  }
                } else if (types[i+2]==1) {
                  out.writeInt(in.readInt());
                  bytesWritten+=4;                  
                } else if (types[i+2]==2) {
                  out.writeLong(in.readLong());
                  bytesWritten+=8;
                }
              }
            }
          } else j++;
        }
        if (!found) {
          if (oldVersion) ReallySkip((vars-2)*bytesPerVar,in);
          else ReallySkip(recSize-16,in);
        }
        
      }
    } catch (Exception ex) { if (!(ex instanceof EOFException)) ex.printStackTrace(); }
    
    try {
      out.flush();
      out.close();
      if (timeout!=null) {
        timeout.flush();
        timeout.close();
      }
      in.close();
      
    } catch (Exception ex) { ex.printStackTrace(); }
    
    if (!closeCancelled) {
      progress.setNote("Updating XML");
      XMLTag newTag = new XMLTag("functionalgroup");
      newTag.addTag(new XMLTag("name",newName));
      newTag.addTag(new XMLTag("data",newFile));
      newTag.addTag(new XMLTag("type",tag.getValue("type")));      
      //newTag.addTag(new XMLTag("time",newTimeFile)); // ONLY FOR NOW!!!
      newTag.addTag(new XMLTag("lifespan"));
      if (bytesPerVar==4) newTag.addTag(new XMLTag("format","float"));
      else newTag.addTag(new XMLTag("format","double"));
      newTag.addTag(new XMLTag("zip",String.valueOf(oldZip)));
      newTag.addTag(new XMLTag("lineage",newLineageFile));
      XMLTag outputTag = new XMLTag("output");
      outputTag.addTag(new XMLTag("after",tag.getValue("output/after")));
      outputTag.addTag(new XMLTag("freq",tag.getValue("output/freq")));
      newTag.addTag(outputTag);
      for (int i=0; i<vars; i++) {
        XMLTag theVar = tag.getTags("var")[i];
        XMLTag newVar = new XMLTag("var");
        newVar.addTag("id",theVar.getValue("id"));
        newVar.addTag("name",theVar.getValue("name"));
        if (theVar.getTag("type")!=null) newVar.addTag(new XMLTag("type",theVar.getValue("type")));
        if (theVar.getTag("desc")!=null) newVar.addTag("desc",theVar.getValue("desc"));
        if (theVar.getTag("units")!=null) newVar.addTag("units",theVar.getValue("units"));
        newTag.addTag(newVar);
      }
      dataFormatsFile.getTag("dataformat").addTag(newTag);
      dataFormatsFile.write();
    }       
    completion.jobCompleted(null);
    JOptionPane.showMessageDialog(ParticleChooserDialog.this,"Please Restart analyser for new subset to appear.");
  }  

  
  
  class DataSetJob implements Job {
    XMLTag tag;
    String newName;
    long[] ids;
    
    public DataSetJob(XMLTag t, String n, long[] i) {
      tag = t;
      newName = n;
      ids = i;
          }
      
    public void start(ProgressListener progress, CompletionListener completion) {
      createSubset(tag, newName, ids, progress, completion);
    }

    public void cancel() {
      closeCancelled = true;
    }
  }

}

class PCDPreviewPanel extends JPanel {
  public PCDPreviewPanel() { super(); }
  public void paintComponent(Graphics g) {
    BackgroundColour.plotPreview(g,0,0,getWidth(),20,BackgroundColour.HORIZONTAL);
  } 
}

