package VEW.Common.XML;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class XMLCellRenderer extends JLabel implements ListCellRenderer, TableCellRenderer, TreeCellRenderer
{
  private String TagName, WhereName;
  private XMLTag SearchTag;

  public XMLCellRenderer()
  {
    this(".");
  }

  public XMLCellRenderer(String _TagName)
  {
    super();
    setOpaque(true);
    TagName = _TagName;
  }

  public XMLCellRenderer(XMLTag _SearchTag, String _TagName, String _WhereName)
  {
    super();
    setOpaque(true);
    TagName = _TagName;
    SearchTag = _SearchTag;
    WhereName = _WhereName;
  }

  public Component getListCellRendererComponent(JList list, Object value, int index,
                                                boolean isSelected, boolean cellHasFocus)
  {
    if(value == null)
    {
      setText("");
    }
    else if(value instanceof XMLTag)
    {
      XMLTag ObjectToDisplay = (XMLTag)value;
      setText(ObjectToDisplay.getValue(TagName));
    }
    else if(WhereName != null)
    {
      XMLTag ObjectToDisplay = SearchTag.getTagWhere(TagName, WhereName, value.toString());
      if(ObjectToDisplay == null)
      {
        setText("");
      }
      setText(ObjectToDisplay.getValue());
    }
    else
    {
      setText(value.toString());
    }
    if(isSelected)
    {
      setForeground(list.getSelectionForeground());
      setBackground(list.getSelectionBackground());
    }
    else
    {
      setForeground(list.getForeground());
      setBackground(list.getBackground());
    }
    return this;
  }

  
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column)
  {
    if(value == null) setText("");
    else if(value instanceof XMLTag) {
      XMLTag ObjectToDisplay = (XMLTag)value;
      setText(ObjectToDisplay.getValue(TagName));
    } else if(WhereName != null) {
      XMLTag ObjectToDisplay = SearchTag.getTagWhere(TagName, WhereName, value.toString());
      if(ObjectToDisplay == null) setText("");
      else setText(ObjectToDisplay.getValue());
    } else setText(value.toString());
    
    if(isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(table.getBackground());
    }
    return this;
  }
  
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    if(value == null)
    {
      setText("");
    }
    else if(value instanceof XMLTag)
    {
      XMLTag ObjectToDisplay = (XMLTag)value;
      if(leaf && ObjectToDisplay.getValue() != null)
      {
        setText(ObjectToDisplay.getValue());
      }
      else
      {
        setText(ObjectToDisplay.getName());
      }
    }
    else
    {
      setText(value.toString());
    }
    if(selected)
    {
      setForeground(Color.black);
      setBackground(Color.cyan.brighter().brighter());
    }
    else
    {
      setForeground(tree.getForeground());
      setBackground(tree.getBackground());
    }
    return this;
  }
  
}