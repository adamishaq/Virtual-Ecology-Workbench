package VEW.Common.XML;

//import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class BackupXMLListModel extends AbstractListModel implements XMLTagListener, ComboBoxModel
{
  private XMLTag RootTag;
  private XMLTag[] Elements;
  private XMLTag ResultTag;
  private Object SelectedElement;
  private String ItemTagName;
  private int LowerBound = 0;
  private int UpperBound = 0;
  
  private static XMLTag DummyResult = new XMLTag("B", "B", true);

  public BackupXMLListModel(XMLTag _RootTag, String _ItemTagName)
  {
    this(_RootTag, _ItemTagName, DummyResult);
  }

  public BackupXMLListModel(XMLTag _RootTag, String _ItemTagName, XMLTag _ResultTag)
  {
    ItemTagName = _ItemTagName;
    RootTag = _RootTag;
    ResultTag = _ResultTag;
    Elements = RootTag.getTags(ItemTagName);
    LowerBound = 0;
    UpperBound = Elements.length - 1;
    if(Elements.length > 0)
    {
      SelectedElement = Elements[0];
    }
    RootTag.addXMLTagListener(this);
  }

  public BackupXMLListModel(XMLTag _RootTag, String _ItemTagName, XMLTag _ResultTag, int _LowerBound, int _UpperBound)
  {
    ItemTagName = _ItemTagName;
    RootTag = _RootTag;
    ResultTag = _ResultTag;
    Elements = RootTag.getTags(ItemTagName);
    if(_LowerBound >= Elements.length) LowerBound = 0;
    else LowerBound = _LowerBound;
    if(_UpperBound < Elements.length) UpperBound = _UpperBound;
    else UpperBound = Elements.length - 1;
    if(Elements.length > 0) SelectedElement = Elements[_LowerBound];
    RootTag.addXMLTagListener(this);
  }
  
  public void setListBounds(int _LowerBound, int _UpperBound)
  {
    LowerBound = _LowerBound;
    UpperBound = _UpperBound;
    fireContentsChanged(this, 0, getSize());
  }

  public Object getSelectedItem()
  {
    return SelectedElement;
  }

  public void setSelectedItem(Object anItem)
  {
    SelectedElement = anItem;
    ResultTag.setValue(((XMLTag)anItem).getValue());
  }

  public Object getElementAt(int index)
  {
    return Elements[LowerBound + index];
  }

  // Returns the number of rows in the model.
  public int getSize()
  {
    if(UpperBound < LowerBound)
    {
      return 0;
    }
    else
    {
      return 1 + UpperBound - LowerBound;//Elements.length;
    }
  }

  public void setRootTag(XMLTag _RootTag)
  {
    if(RootTag != null) RootTag.removeXMLTagListener(this);
    RootTag = _RootTag;
    Elements = RootTag.getTags(ItemTagName);
    LowerBound = 0;
    UpperBound = Elements.length - 1;
    if(Elements.length > 0)
    {
      SelectedElement = Elements[0];
      ResultTag.setValue(Elements[0].getValue());
    }
    else
    {
      SelectedElement = null;
    }
    fireContentsChanged(this, 0, getSize());//Elements.length);
    RootTag.addXMLTagListener(this);
  }
  
  public void tagAdded(XMLTagEvent   e)
  {
    UpperBound++;
    Elements = RootTag.getTags(ItemTagName);
    if(Elements.length > 0)
    {
      SelectedElement = Elements[LowerBound];
      ResultTag.setValue(Elements[LowerBound].getValue());
    }
    else
    {
      SelectedElement = null;
    }
    fireIntervalAdded(this, 0, getSize());//Elements.length);
  }

  public void tagRemoved(XMLTagEvent e)
  {
    UpperBound--;
    Elements = RootTag.getTags(ItemTagName);
    if(Elements.length > 0)
    {
      SelectedElement = Elements[LowerBound];
      ResultTag.setValue(Elements[LowerBound].getValue());
    }
    else
    {
      SelectedElement = null;
    }
    fireIntervalRemoved(this, 0, getSize());//Elements.length);
  }

  public void tagChanged(XMLTagEvent e)
  {
    Elements = RootTag.getTags(ItemTagName);
    ResultTag.setValue(((XMLTag)SelectedElement).getValue());
    fireContentsChanged(this, 0, getSize());//Elements.length);
  }
}