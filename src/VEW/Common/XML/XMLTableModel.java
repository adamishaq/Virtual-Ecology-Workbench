package VEW.Common.XML;

//import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class XMLTableModel extends AbstractTableModel implements XMLTagListener
{
  private XMLTag RootTag;
  private String RowTagName;
  private String[] ColumnNames;
  private String[] ColumnTagNames;

  public XMLTableModel(XMLTag _RootTag, String _RowTagName, String[] HeaderInfo)
  {
    ColumnNames    = new String[HeaderInfo.length];
    ColumnTagNames = new String[HeaderInfo.length];
    String[] Bits;
    for(int i = 0; i < HeaderInfo.length; i++)
    {
      Bits = HeaderInfo[i].split(":");
      ColumnNames[i] = Bits[0];
      if(Bits.length > 1)
      {
        ColumnTagNames[i] = Bits[1];
      }
      else
      {
        ColumnTagNames[i] = Bits[0];
      }
    }
    RowTagName = _RowTagName;
    RootTag = _RootTag;
    RootTag.addXMLTagListener(this);
  }

  // Returns the number of columns in the model.
  public int getColumnCount()
  {
    return ColumnNames.length;
  }

  // Returns the name of the column at columnIndex.
  public String getColumnName(int columnIndex)
  {
    return ColumnNames[columnIndex];
  }

  public void setColumnName(int column, String NewName)
  {
    ColumnNames[column] = NewName;
    fireTableStructureChanged();
  }

  // Returns the number of rows in the model.
  public int getRowCount()
  {
    return RootTag.getTags(RowTagName).length;
  }

  // Returns the value for the cell at columnIndex and rowIndex.
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    XMLTag TempTag = RootTag.getTag(RowTagName, rowIndex + 1).getTag(ColumnTagNames[columnIndex]);
    if(TempTag == null || TempTag.getValue() == null)
    {
      return TempTag;
    }
    else
    {
      return TempTag.getValue();
    }
  }

  public XMLTag getRow(int rowNumber)
  {
    return RootTag.getTag(RowTagName, rowNumber + 1);
  }

  public void setRootTag(XMLTag _RootTag)
  {
    this.RootTag.removeXMLTagListener(this);
    RootTag = _RootTag;
    RootTag.addXMLTagListener(this);
    fireTableDataChanged();
  }

  // Sets the value in the cell at columnIndex and rowIndex to aValue.
  public void setValueAt(Object aValue, int rowIndex, int columnIndex)
  {
    XMLTag TargetTag = RootTag.getTag(RowTagName, rowIndex + 1).getTag(ColumnTagNames[columnIndex]);
    if(aValue instanceof XMLTag)
    {
      if(TargetTag.getValue() != null)
      {
        TargetTag.setValue(((XMLTag)aValue).getValue());
      }
      else
      {
        XMLTag NewTag = (XMLTag)((XMLTag)aValue).clone();
        XMLTag OldTag = TargetTag.getTag(NewTag.getName());
        if(OldTag == null)
        {
          TargetTag.addTag(NewTag);
        }
        else
        {
          NewTag.replace(OldTag);
        }
      }
    }
    else if(TargetTag == null)
    {
      TargetTag = RootTag.getTag(RowTagName, rowIndex + 1);
      TargetTag.setValue(ColumnTagNames[columnIndex] + "/" + aValue.toString());
    }
    else
    {
      TargetTag.setValue(aValue.toString());
    }
	  fireTableCellUpdated(rowIndex, columnIndex);
  }
  
  public void tagAdded(XMLTagEvent   e)
  {
    fireTableDataChanged();
  }

  public void tagRemoved(XMLTagEvent e)
  {
    fireTableDataChanged();
  }

  public void tagChanged(XMLTagEvent e)
  {
    fireTableDataChanged();
  }
}