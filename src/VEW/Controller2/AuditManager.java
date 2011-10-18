package VEW.Controller2;

import gnu.trove.TLongHashSet;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import VEW.Common.ParticleChooser.ParticleChooserDialog;
import VEW.Common.ParticleChooser.ParticleGroup;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class AuditManager extends JDialog {
  

  public static final String AUDIT_FILE_NAME = "audit.xml";
  private static final int COL_NAME = 0;
  private static final int COL_FG = 1;
  private static final int COL_PARTICLES = 2;
  private static final int COL_DESCENDANTS = 3;
  private static final int N_COLS = 4;
  private static final String[] COLUMN_NAMES = {
    "Name",
    "Functional group",
    "Particles",
    "Descendants"
  };
  private static final Class[] COLUMN_CLASSES = {
    String.class,
    String.class,
    String.class,
    String.class
  };
    
  private ParticleGroupTableModel model;
  private JTable table;
  private XMLTag theModel;
  private XMLTag outputOptions;
  private File directory;
  protected boolean accepted;
  
  public AuditManager(Dialog owner, XMLTag _outputOptions, XMLTag _theModel, File _directory) {
    super(owner);
    theModel = _theModel;
    outputOptions = _outputOptions;
    directory = _directory;
    
    createComponents();
    loadAuditFile();
  }
  
  public AuditManager(Frame owner, XMLTag _outputOptions, XMLTag _theModel, File _directory) {
    super(owner);
    theModel = _theModel;
    
    outputOptions = _outputOptions;
    directory = _directory;
    
    createComponents();
    loadAuditFile();
  }
  
  private void createComponents() {
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JLabel resultPath;
    try {
      resultPath = new JLabel("Results path: " + directory.getCanonicalPath());
    } catch (IOException e) {
      resultPath = new JLabel("Results path: Error in path " + directory.getPath());
      e.printStackTrace();
    }
    
    panel.add(resultPath);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(createTablePanel());
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(createButtonPanel());
    
    setContentPane(panel);
    pack();
  }
  
  private Component createTablePanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    
    GridBagConstraints c = new GridBagConstraints();
    c.gridx = 0;
    c.gridwidth = 3;
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;
    
    model = new ParticleGroupTableModel();
    table = new JTable(model);
    panel.add(new JScrollPane(table), c);
    
    c.insets.top = 5;
    c.gridwidth = 1;
    panel.add(new JButton(ADD_ACTION), c);
    c.gridx = 1;
    c.insets.right = 5;
    panel.add(new JButton(REMOVE_ACTION), c);
    c.gridx = 2;
    panel.add(new JButton(CLEAR_ACTION), c);
    
    return panel;
  }
  
  protected Component createButtonPanel() {
    Box buttonBox = new Box(BoxLayout.X_AXIS);
    buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    
    buttonBox.add(Box.createHorizontalGlue());
    
    buttonBox.add(new JButton(CANCEL_ACTION));
    buttonBox.add(Box.createRigidArea(new Dimension(5, 0)));
    buttonBox.add(new JButton(OK_ACTION));
    
    return buttonBox;
  }

  protected void saveAuditFile() {
    System.out.println("Saving audit file");
    File auditFile = new File(AUDIT_FILE_NAME);
    
    XMLTag auditTag = outputOptions.getTag("audit");
    if (auditTag == null)
      auditTag = outputOptions.addTag("audit");
    auditTag.setValue(auditFile.getAbsolutePath());
    
    XMLFile auditXML = new XMLFile(auditFile.getAbsolutePath(), "audits");
    Map fgMap = collectFunctionalGroups(model.getGroups());
    
    for (Iterator i = fgMap.entrySet().iterator(); i.hasNext();) {
      Map.Entry entry = (Map.Entry) i.next();
      String fg = (String) entry.getKey();
      List list = (List) entry.getValue();
      
      XMLTag fgTag = auditXML.addTag("functionalgroup");
      fgTag.addTag("name", fg);
      
      for (Iterator j = list.iterator(); j.hasNext();) {
        ParticleGroup g = (ParticleGroup) j.next();
        fgTag.addTag(g.toXML());
      }
    }
    
    auditXML.write();
  }
  
  protected void removeAuditFile() {
    System.out.println("Removing audit file");
    File auditFile = new File(AUDIT_FILE_NAME);
    auditFile.delete();
    
    XMLTag auditTag = outputOptions.getTag("audit");
    if (auditTag != null)
      auditTag.removeTagFromParent();
  }
  
  protected void loadAuditFile() {
    File auditFile = new File(AUDIT_FILE_NAME);
    
    XMLTag auditTag = outputOptions.getTag("audit");
    if (auditTag == null)
      return;
    
    XMLFile auditXML = XMLFile.LoadFile(auditFile);
    XMLTag[] fgTags = auditXML.getTags("functionalgroup");
    if (fgTags != null) {
      for (int i = 0; i < fgTags.length; i++) {
        String fgName = fgTags[i].getValue("name");
        XMLTag[] groupTags = fgTags[i].getTags("group");
        
        for (int j = 0; j < groupTags.length; j++)
          model.add(new ParticleGroup(fgName, groupTags[j]));
      }
    }
  }

  private Map collectFunctionalGroups(List particleGroups) {
    Map fgMap = new TreeMap();
    List list;
    
    for (Iterator iter = particleGroups.iterator(); iter.hasNext();) {
      ParticleGroup g = (ParticleGroup) iter.next();
      
      list = (List) fgMap.get(g.getFunctionalGroup());
      if (list == null) {
        list = new LinkedList();
        fgMap.put(g.getFunctionalGroup(), list);
      }
      
      list.add(g);
    }
    
    return fgMap;
  }

  private final Action ADD_ACTION = new AbstractAction("Add") {
    public void actionPerformed(ActionEvent e) {
      GregorianCalendar start = new GregorianCalendar();
      GregorianCalendar end = new GregorianCalendar();
      start.setTimeInMillis(Long.parseLong(theModel.getValue("model/track/start")));
      end.setTimeInMillis(Long.parseLong(theModel.getValue("model/track/end")));
      float stepHours = Float.parseFloat(theModel.getValue("model/track/secstep"))/3600.0f; 
      ParticleChooserDialog d = new ParticleChooserDialog(AuditManager.this, null, directory, true,start,end,stepHours);
      d.setModal(true);
      d.setVisible(true);
      if (d.isAccepted()) {
        int index = model.add(d.getParticleGroup());
        table.editCellAt(index, COL_NAME);
      }
    }
  };
  
  private final Action REMOVE_ACTION = new AbstractAction("Remove") {
    public void actionPerformed(ActionEvent e) {
      int index = table.getSelectedRow();
      model.remove(index);
    }
  };
  
  private final Action CLEAR_ACTION = new AbstractAction("Clear") {
    public void actionPerformed(ActionEvent e) {
      model.clear();
    }
  };
  
  private final Action OK_ACTION = new AbstractAction("Save") {
    public void actionPerformed(ActionEvent e) {
      if (model.getGroups().isEmpty())
        removeAuditFile();
      else
        saveAuditFile();
      
      setVisible(false);
    }
  };
  
  private final Action CANCEL_ACTION = new AbstractAction("Cancel") {
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
    }
  };
  
  public class ParticleGroupTableModel extends AbstractTableModel {

    private List groups = new ArrayList();
    
    public int getRowCount() {
      return groups != null ? groups.size() : 0;
    }

    public int getColumnCount() {
      return N_COLS;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
      ParticleGroup g = (ParticleGroup) groups.get(rowIndex);
      switch (columnIndex) {
      case COL_NAME:
        return g.getGroupName();
      case COL_FG:
        return g.getFunctionalGroup();
      case COL_PARTICLES:
        return g.getParticles().size() + " particles";
      case COL_DESCENDANTS:
        TLongHashSet l = g.getDescendants();
        if (l == null || l.size() == 0)
          return "Not selected";
        else
          return l.size() + " particles";
      default:
        return null;
      }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      switch (columnIndex) {
      case COL_NAME:
        ParticleGroup g = (ParticleGroup) groups.get(rowIndex);
        g.setGroupName((String) aValue);
        break;
      }
    }

    public Class getColumnClass(int columnIndex) {
      return COLUMN_CLASSES[columnIndex];
    }

    public String getColumnName(int columnIndex) {
      return COLUMN_NAMES[columnIndex];
    }
    
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      switch (columnIndex) {
      case COL_NAME:
        return true;
      default:
        return false;
      }
    }

    public int add(ParticleGroup group) {
      int index = groups.size();
      groups.add(group);
      fireTableRowsInserted(index, index);
      return index;
    }
    
    public void clear() {
      int count = groups.size();
      if (count > 0) {
        groups.clear();
        fireTableRowsDeleted(0, count - 1);
      }
    }
    
    public void remove(int index) {
      groups.remove(index);
      fireTableRowsDeleted(index, index);
    }

    public List getGroups() {
      return groups;
    }
    
    

  }

}
