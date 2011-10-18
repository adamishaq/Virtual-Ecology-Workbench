package VEW.Common;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class UnitPanel extends JEditorPane implements TableCellRenderer,
                                                      ListCellRenderer, TreeCellRenderer
{
  private Color BGColour;

  public UnitPanel()
  {
    this(Color.white);
  }

  public UnitPanel(Color _BGColour)
  {
    super("text/html", "");
    BGColour = _BGColour;
    setBackground(BGColour);
    setEditable(false);
    setAlignmentY(BOTTOM_ALIGNMENT);
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column)
  {
    if(value == null)
    {
      this.setText("");
    }
    else
    {
      this.setText((String)value);
    }
    if(isSelected)
    {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    }
    else
    {
      setForeground(table.getForeground());
      setBackground(BGColour);
    }
    return this;
  }
  
  public void setTextPlain(String text)
  {
      super.setText(text);
  }

  public Component getListCellRendererComponent(JList list, Object value, int index,
                                                boolean isSelected, boolean cellHasFocus)
  {
    if(value == null)
    {
      this.setText("");
    }
    else
    {
      this.setText((String)value);
    }
    if(isSelected)
    {
      setForeground(list.getSelectionForeground());
      setBackground(list.getSelectionBackground());
    }
    else
    {
      setForeground(list.getForeground());
      setBackground(BGColour);
    }
    return this;
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean isSelected,
                                          boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    if(value == null)
    {
      this.setText("");
    }
    else
    {
      this.setText((String)value);
    }
    return this;
  }

  public void setText(String NewText)
  {
    if(NewText.trim().equals("") || NewText.trim().equals("0,0,0"))
    {
      super.setText("");
      return;
    }
    StringBuffer Result = new StringBuffer("<font size=\"-1\">&nbsp;");
    String[] UnitParts  = NewText.split(",");
    for(int i = 0; i < UnitParts.length - 2; i++)
    { 
      String converted = VEWUtilities.getExponent(UnitParts[i]);
      if (converted.length() > 0) {
        Result.append("<i>" + converted + "</i>");
      } else {
          // do nothing;
      }
      i++;
      Result.append(UnitParts[i]);
      i++;
      if(!UnitParts[i].equals("1"))
      {
        Result.append("<font size=\"-2\"><sup>");
        Result.append(UnitParts[i]);
        Result.append("</sup></font>");
      }
    }
    if(UnitParts.length < 3)
    {
      Result.append(NewText);
    }
    Result.append("</font>");
    
    // FIXME vertical positioning is wrong
    //super.setAlignmentX(-2);
    super.setText(Result.toString());
  }
}

