package VEW.Common;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class CheckBoxEditor extends DefaultCellEditor {
  public final static int BOOLEAN_MODE = 0;
  public final static int TEXT_MODE    = 1;

  protected JCheckBox box = new JCheckBox();
  private int DataMode;
  
  int row = 0;

  public CheckBoxEditor(int _DataMode) {
    super(new JCheckBox());
    DataMode = _DataMode;
    box.setOpaque(true);
    box.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                               int _row, int column)
  {
    if(isSelected)
    {		
      box.setForeground(table.getSelectionForeground());
      box.setBackground(table.getSelectionBackground());
    }
    else
    {
      box.setForeground(table.getForeground());
      box.setBackground(Color.white);
    }
    if(value instanceof Boolean)
    {
      box.setSelected(((Boolean)value).booleanValue());
    }
    else if(value != null)
    {
      String Val = value.toString().toLowerCase();
      if(Val.equals("true"))
      {
        box.setSelected(true);
      }
      else
      {
        box.setSelected(false);
      }
    }
    else
    {
      box.setSelected(false);
    }
    return box;
  }

  public Object getCellEditorValue()
  {
    Boolean Value = new Boolean(box.isSelected());
    if(DataMode == BOOLEAN_MODE)
    {
      return Value;
    }
    else if(DataMode == TEXT_MODE)
    {
      return Value.toString();
    }
    else
    {
      return null;
    }
  }
  
  public boolean stopCellEditing()
  {
    return super.stopCellEditing();
  }

  protected void fireEditingStopped()
  {
    super.fireEditingStopped();
  }
}