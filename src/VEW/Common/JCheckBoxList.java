package VEW.Common;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class JCheckBoxList extends JList {
  protected Border noFocusBorder =  new EmptyBorder(1, 1, 1, 1);

  public JCheckBoxList() {
     setCellRenderer(new CellRenderer());
     addMouseListener(new MouseAdapter() {
       public void mousePressed(MouseEvent e) {
         int index = locationToIndex(e.getPoint());
         if (index != -1) {
           JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
           checkbox.setSelected(!checkbox.isSelected());
           repaint();
         }
       }
     });
     setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }
  
  public JCheckBoxList(ListModel l) {
    this();
    setModel(l);
  }

  class CellRenderer implements ListCellRenderer {
     public Component getListCellRendererComponent(JList list, Object value, int index,
                   boolean isSelected, boolean cellHasFocus) {
       JCheckBox checkbox = (JCheckBox) value;
       checkbox.setBackground(isSelected ? getSelectionBackground() : getBackground());
        checkbox.setForeground(isSelected ? getSelectionForeground() : getForeground());
        checkbox.setEnabled(isEnabled());
        checkbox.setFont(getFont());
        checkbox.setFocusPainted(false);
        checkbox.setBorderPainted(true);
        checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
        return checkbox;
     }
  }
}
