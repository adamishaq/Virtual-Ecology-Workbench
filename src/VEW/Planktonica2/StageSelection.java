package VEW.Planktonica2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import VEW.Common.XML.XMLTag;
import VEW.Controller2.VEWController2;

public class StageSelection extends JDialog {
  JScrollPane tableScroller;
  JButton addStage = new JButton("Add Stage");
  JButton removeStage = new JButton("Remove Stage");
  JButton renameStage = new JButton("Rename Stage");
  JButton close = new JButton("Close");
  StageTableModel stageTableModel = new StageTableModel();
  JTable stageTable = new JTable(stageTableModel);
  VEWController2 vc2;
  XMLTag theModel;
  XMLTag fg;
  
  public StageSelection(VEWController2 _vc2, XMLTag _theModel) {
    super(_vc2,"Stage Editor",true);
    EventHandler eh = new EventHandler();
    getContentPane().setLayout(new BorderLayout());
    tableScroller = new JScrollPane(stageTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    stageTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    tableScroller.setPreferredSize(new Dimension(600,400));
    getContentPane().add(tableScroller,BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(addStage);
    buttonPanel.add(removeStage);
    buttonPanel.add(renameStage);
    buttonPanel.add(close);
    getContentPane().add(buttonPanel,BorderLayout.SOUTH);
    vc2=_vc2;
    theModel=_theModel;
    pack();
    addStage.addActionListener(eh);
    removeStage.addActionListener(eh);
    renameStage.addActionListener(eh);
    close.addActionListener(eh);
    
  }
  
  public void init(XMLTag _fg) {
    fg=_fg;
    XMLTag[] funcs = fg.getTags("function");
    XMLTag[] stages = fg.getTags("stage");
    while (stageTableModel.getRowCount()>0) stageTableModel.removeRow(0);
    stageTableModel.setColumnCount(stages.length);
    stageTableModel.setRowCount(funcs.length);
    Vector titles = new Vector();
    for (int i=0; i<stages.length; i++)
      titles.addElement(stages[i].getValue("name"));
    stageTableModel.setColumnIdentifiers(titles);
    
    
    DefaultListModel funcsListModel = new DefaultListModel();
    JList funcsList = new JList(funcsListModel);
    for (int i=0; i<funcs.length; i++) 
      funcsListModel.addElement(funcs[i].getValue("name")+"  ");
    funcsList.setCellRenderer(new RowHeaderRenderer(stageTable));
    tableScroller.setRowHeaderView(funcsList);
    for (int i=0; i<funcs.length; i++) {
      XMLTag[] calls = funcs[i].getTags("calledin");
      for (int j=0; j<stages.length; j++) {
        boolean found=false;
        int k=0;
        while ((k<calls.length) && (!found)) {
          if (calls[k].getValue().equals(stages[j].getValue("name"))) found=true;
          k++;
        }
        stageTableModel.setValueAt(Boolean.valueOf(found),i,j);
      }
    }
  }
  
  class EventHandler implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==close) {
        XMLTag[] funcs = fg.getTags("function");
        XMLTag[] stages = fg.getTags("stage");
        boolean changedSomething = false;
        for (int i=0; i<funcs.length; i++) {
          for (int j=0; j<stages.length; j++) {
            XMLTag[] calls = funcs[i].getTags("calledin");
            boolean val = ((Boolean) stageTableModel.getValueAt(i,j)).booleanValue();
            
            if (val) {
              int k=0;
              boolean found = false;
              while ((k<calls.length) && (!found)) {
                if (calls[k].getValue().equals(stages[j].getValue("name"))) {
                  found=true;
                }
                k++;
              }
              if (!found) {
                funcs[i].addTag(new XMLTag("calledin",stages[j].getValue("name")));
                changedSomething=true;
              }
            } else {
              int k=0;
              boolean found = false;
              while ((k<calls.length) && (!found)) {
                if (calls[k].getValue().equals(stages[j].getValue("name"))) {
                  calls[k].removeTagFromParent();
                  changedSomething=true;
                  found=true;
                }
                k++;
              }
            }
          }
        }
        if (changedSomething) vc2.unsaved(true);
        setVisible(false);
      } else if (e.getSource()==addStage) {
        AddStageDialog asd = new AddStageDialog(StageSelection.this);
        asd.setVisible(true);
        init(fg);
      } else if (e.getSource()==removeStage) {
        RemoveStageDialog rsd = new RemoveStageDialog(StageSelection.this);
        rsd.setVisible(true);
        init(fg);
      } else if (e.getSource()==renameStage) {
        RenameStageDialog rnsd = new RenameStageDialog(StageSelection.this);
        rnsd.setVisible(true);
        init(fg);
      }
    }
  }
  
  class StageTableModel extends DefaultTableModel {
    public Class getColumnClass(int column) {
      return Boolean.class;
    }
  }

  class RowHeaderRenderer extends JLabel implements ListCellRenderer {
    RowHeaderRenderer(JTable table) {
      JTableHeader header = table.getTableHeader();
      setOpaque(true);
      setBorder(UIManager.getBorder("TableHeader.cellBorder"));
      setHorizontalAlignment(RIGHT);
      setForeground(header.getForeground());
      setBackground(header.getBackground());
      setFont(header.getFont());
    }

    public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }
  
  class AddStageDialog extends JDialog {
    JComboBox baseOn = new JComboBox();
    JButton add = new JButton("Add");
    JButton cancel = new JButton("Cancel");
    JTextField name = new JTextField(15);
    private final String ALL_SELECT = new String("All Selected");
    private final String NONE_SELECT = new String("None Selected");
    
    public AddStageDialog(JDialog parent) {
      super(parent,"Add Stage",true);
      XMLTag[] stages = fg.getTags("stage");
      baseOn.addItem(ALL_SELECT);
      baseOn.addItem(NONE_SELECT);
      for (int i=0; i<stages.length; i++) baseOn.addItem(stages[i].getValue("name"));
      getContentPane().setLayout(new BorderLayout());
      JPanel namePanel = new JPanel(new FlowLayout());
      namePanel.add(new JLabel("Name"));
      namePanel.add(name);
      getContentPane().add(namePanel,BorderLayout.NORTH);
      JPanel basePanel = new JPanel(new FlowLayout());
      basePanel.add(new JLabel("Base active functions on: "));
      basePanel.add(baseOn);
      getContentPane().add(basePanel,BorderLayout.CENTER);
      JPanel buttonPanel = new JPanel(new FlowLayout());
      buttonPanel.add(add);
      buttonPanel.add(cancel);
      getContentPane().add(buttonPanel,BorderLayout.SOUTH);
      pack();
      ASDEventHandler asdeh = new ASDEventHandler();
      cancel.addActionListener(asdeh);
      add.addActionListener(asdeh);
      name.addCaretListener(asdeh);
      add.setEnabled(false);
    }
    
    public void updateButtons() {
      add.setEnabled(name.getText().trim().length()>0);
    }
    
    class ASDEventHandler implements ActionListener, CaretListener {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel) {
          setVisible(false);
        
        } else if (e.getSource()==add) {
          if (fg.getTagWhere("stage","name",name.getText())!=null) {
            JOptionPane.showMessageDialog(AddStageDialog.this,"That stage already exists. Please choose a different name");
          } else {
            XMLTag newStage = new XMLTag("stage");
            newStage.addTag(new XMLTag("name",name.getText()));
            fg.addTag(newStage);
            XMLTag[] funcs = fg.getTags("function");
            if (baseOn.getSelectedItem().toString().equals(ALL_SELECT))  {
              for (int i=0; i<funcs.length; i++) 
                funcs[i].addTag(new XMLTag("calledin",name.getText()));
            } else if (!baseOn.getSelectedItem().toString().equals(NONE_SELECT)) {
              for (int i=0; i<funcs.length; i++) {
                XMLTag[] calls = funcs[i].getTags("calledin");
                int k=0;
                boolean found = false;
                while ((k<calls.length) && (!found)) {
                  if (calls[k].getValue().equals(baseOn.getSelectedItem().toString())) {
                    found=true;
                    funcs[i].addTag(new XMLTag("calledin",name.getText()));
                  }
                  k++;
                }
              }
            }
            vc2.unsaved(true);
            setVisible(false);
          }
        }
      }

      public void caretUpdate(CaretEvent e) {
        if (e.getSource()==name) updateButtons();
      }
    }
  }
  
  class RemoveStageDialog extends JDialog {
    JComboBox theStage = new JComboBox();
    JButton remove = new JButton("Remove");
    JButton cancel = new JButton("Cancel");
    
    public RemoveStageDialog(JDialog parent) {
      super(parent,"Remove Stage",true);
      XMLTag[] stages = fg.getTags("stage");
      for (int i=0; i<stages.length; i++) theStage.addItem(stages[i].getValue("name"));
      getContentPane().setLayout(new BorderLayout());
      JPanel stagePanel = new JPanel(new FlowLayout());
      stagePanel.add(new JLabel("Stage to Remove: "));
      stagePanel.add(theStage);
      getContentPane().add(stagePanel,BorderLayout.NORTH);
      JPanel buttonPanel = new JPanel(new FlowLayout());
      buttonPanel.add(remove);
      buttonPanel.add(cancel);
      getContentPane().add(buttonPanel,BorderLayout.SOUTH);
      pack();
      RSDEventHandler rsdeh = new RSDEventHandler();
      cancel.addActionListener(rsdeh);
      remove.addActionListener(rsdeh);
    }
    
    class RSDEventHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel) {
          setVisible(false);
        
        } else if (e.getSource()==remove) {
          String stage = theStage.getSelectedItem().toString();
          StringBuffer error = new StringBuffer();
          XMLTag[] funcs = fg.getTags("function");
          for (int i=0; i<funcs.length; i++) {
            XMLTag[] eqs = funcs[i].getTags("equation");
            for (int j=0; j<eqs.length; j++) {
              String eq = eqs[j].getValue("eq");
              if (eq.indexOf("\\stage{"+stage+"}")>0)
                error.append(funcs[i].getValue("name")+":"+eqs[j].getValue("name")+"\n");
            } 
          }
          if (error.length()>0) {
            JOptionPane.showMessageDialog(RemoveStageDialog.this, "This stage cannot be deleted as it is used in the following functions:\n"+error.toString());
          } else {
            if (JOptionPane.showConfirmDialog(RemoveStageDialog.this, "Really delete "+stage+"?")==JOptionPane.YES_OPTION) {
              for (int i=0; i<funcs.length; i++) {
                XMLTag[] calls = funcs[i].getTags("calledin");
                for (int j=0; j<calls.length; j++) 
                  if (calls[j].getValue().equals(stage)) calls[j].removeFromParent();
              
              }
              fg.getTagWhere("stage","name",stage).removeFromParent();
              vc2.unsaved(true);
              setVisible(false);
            }
          }
        }
      }
    }
  }

  class RenameStageDialog extends JDialog {
    JComboBox stageToRename = new JComboBox();
    JButton rename = new JButton("Rename");
    JButton cancel = new JButton("Cancel");
    JTextField newName = new JTextField(15);
    
    public RenameStageDialog(JDialog parent) {
      super(parent,"Rename Stage",true);
      XMLTag[] stages = fg.getTags("stage");
      for (int i=0; i<stages.length; i++) stageToRename.addItem(stages[i].getValue("name"));
      getContentPane().setLayout(new BorderLayout());
      JPanel basePanel = new JPanel(new FlowLayout());
      basePanel.add(new JLabel("Rename stage: "));
      basePanel.add(stageToRename);
      getContentPane().add(basePanel,BorderLayout.NORTH);
      JPanel namePanel = new JPanel(new FlowLayout());
      namePanel.add(new JLabel("to:"));
      namePanel.add(newName);
      getContentPane().add(namePanel,BorderLayout.CENTER);
      JPanel buttonPanel = new JPanel(new FlowLayout());
      buttonPanel.add(rename);
      buttonPanel.add(cancel);
      getContentPane().add(buttonPanel,BorderLayout.SOUTH);
      pack();
      ASDEventHandler asdeh = new ASDEventHandler();
      cancel.addActionListener(asdeh);
      rename.addActionListener(asdeh);
      newName.addCaretListener(asdeh);
      rename.setEnabled(false);
    }
    
    public void updateButtons() {
      rename.setEnabled(newName.getText().trim().length()>0);
    }
    
    class ASDEventHandler implements ActionListener, CaretListener {
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==cancel) {
          setVisible(false);
        
        } else if (e.getSource()==rename) {
          if (fg.getTagWhere("stage","name",newName.getText())!=null) {
            JOptionPane.showMessageDialog(RenameStageDialog.this,"That stage already exists. Please choose a different name");
          } else {
            String origName = stageToRename.getSelectedItem().toString(); 
            XMLTag origStage = fg.getTagWhere("stage","name",origName);
            origStage.setValue("name",newName.getText());
            XMLTag[] funcs = fg.getTags("function");
            for (int i=0; i<funcs.length; i++) {
              XMLTag[] eqs = funcs[i].getTags("equation");
              for (int j=0; j<eqs.length; j++) {
                String eq = eqs[j].getValue("eq");
                final String origTag = "\\stage{"+origName+"}";
                final String newTag = "\\stage{"+newName.getText()+"}";
                while (eq.indexOf(origTag)>=0) {
                  final String newEq = eq.substring(0,eq.indexOf(origTag))+newTag+eq.substring(eq.indexOf(origTag)+origTag.length());
                  eqs[j].getTag("eq").setValue(newEq);
                  eq = newEq;
                }
              }
              XMLTag[] calls = funcs[i].getTags("calledin");
              for (int j=0; j<calls.length; j++)
                if (calls[j].getValue().equals(origName)) calls[j].setValue(newName.getText());
              
            }
            vc2.unsaved(true);
            setVisible(false);
          }
        }
      }

      public void caretUpdate(CaretEvent e) {
        if (e.getSource()==newName) updateButtons();
      }
    }
  }
  
}
