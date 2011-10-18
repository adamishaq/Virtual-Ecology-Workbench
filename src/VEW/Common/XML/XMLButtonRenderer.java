package VEW.Common.XML;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class XMLButtonRenderer extends JButton implements TableCellRenderer
{
  
  public XMLButtonRenderer(String lab)
  {
    super(lab);
    setOpaque(true);
  }
  
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
  {
    if (isSelected)
    {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else
    {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    System.out.println((String)value);
    //setText(((XMLTag)value).getValue());
    return this;
  }
  
}

