package VEW.Common;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
  public Component getTableCellRendererComponent(JTable table, Object value,
             boolean isSelected, boolean hasFocus, int row, int column) {
    if(isSelected) {		
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(Color.white);
    }
    if (value instanceof Boolean) setSelected(((Boolean)value).booleanValue());
    else if (value != null) setSelected(value.toString().toLowerCase().equals("true"));
    else setSelected(false);
    return this;
  }
}