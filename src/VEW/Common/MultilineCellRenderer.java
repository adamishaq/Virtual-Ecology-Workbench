package VEW.Common;

import java.awt.Component;

import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;
//import javax.swing.UIManager;

public class MultilineCellRenderer extends JEditorPane implements ListCellRenderer,
                                                                   TableCellRenderer
{
  private boolean Split = false;
  private String SplitOn;

  public MultilineCellRenderer() {
    this(null);
  }

  public MultilineCellRenderer(String _SplitOn) {
    super("text/html", "");
    setOpaque(true);
    setEditable(false);
    if(_SplitOn != null) {
      SplitOn = _SplitOn;
      Split = true;
    }
  }

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                 boolean hasFocus, int row, int column)
  {
    if(Split)
    {
      String[] Sections = value.toString().split(SplitOn, 3);
      if(Sections.length == 1)
      {
        setText("<font size=\"-1\">" + Sections[0] + "</font>");
      }
      else if(Sections.length == 2)
      {
        setText("<font size=\"-1\">" + Sections[0]
                     + "<br>\u2192 " + Sections[1] + "</font>");
      }
      else
      {
        setText("<font size=\"-1\">" + Sections[0]
                     + "<br>\u2192 " + Sections[1]
               + "<br>\u2500\u2192 " + Sections[2] + "</font>");
      }
    }
    else
    {
      setText(value.toString());
    }
    if(isSelected)
    {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    }
    else
    {
      setForeground(table.getForeground());
      setBackground(table.getBackground());
    }
    return this;
  }

  public Component getListCellRendererComponent(JList list, Object value, int index,
                                                boolean isSelected, boolean cellHasFocus)
  {
    if(Split)
    {
      String[] Sections = value.toString().split(SplitOn, 3);
      if(Sections.length == 1)
      {
        setText("<font size=\"-1\">" + Sections[0] + "</font>");
      }
      else if(Sections.length == 2)
      {
        setText("<font size=\"-1\">" + Sections[0]
                     + "<br>\u2192 " + Sections[1] + "</font>");
      }
      else
      {
        setText("<font size=\"-1\">" + Sections[0]
                     + "<br>\u2192 " + Sections[1]
               + "<br>\u2500\u2192 " + Sections[2] + "</font>");
      }
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
}