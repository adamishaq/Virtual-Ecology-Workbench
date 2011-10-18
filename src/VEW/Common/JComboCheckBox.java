package VEW.Common;

import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

import java.awt.*;



public class JComboCheckBox extends JComboBox {
  
  public static boolean DisableEvents = false;
  public JComboCheckBox() { 
    addStuff(); 
  }
  
  public JComboCheckBox(JCheckBox[] items) { 
    super(items); 
    addStuff(); 
  }
  
  public JComboCheckBox(Vector items) { 
    super(items); 
    addStuff();
  }
  
  public JComboCheckBox(ComboBoxModel aModel) { 
    super(aModel); 
    addStuff(); 
  }

 
  private void addStuff() {
    setRenderer(new ComboBoxRenderer());
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) { 
        itemSelected();
      }
    });
  }
  
  private void itemSelected() {
    if (!DisableEvents) {
      if (getSelectedItem() instanceof JCheckBox) {
        DisableEvents=true;
        JCheckBox jcb = (JCheckBox)getSelectedItem();
        jcb.setSelected(!jcb.isSelected());
        DisableEvents=false;
      }
    }
  }

  class ComboBoxRenderer implements ListCellRenderer {
    private JLabel defaultLabel;
    public ComboBoxRenderer() { setOpaque(true); }
    public Component getListCellRendererComponent(JList list, Object value, int index,
      boolean isSelected, boolean cellHasFocus) {
      if (value instanceof Component) {
        Component c = (Component)value;
        if (isSelected) {
          c.setBackground(list.getSelectionBackground());
          c.setForeground(list.getSelectionForeground());
        } else {
          c.setBackground(list.getBackground());
          c.setForeground(list.getForeground());
        }
        return c;
      } else {
        if (defaultLabel==null) {
          if (value!=null) defaultLabel = new JLabel(value.toString());
          else defaultLabel = new JLabel("");
        }
        else {
          if (value!=null) defaultLabel.setText(value.toString());
          else defaultLabel.setText("");
        }
        return defaultLabel;
      }
    }
  }
}
