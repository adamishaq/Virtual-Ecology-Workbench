package VEW.Common.XML;

//import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class XMLListModel extends AbstractListModel implements XMLTagListener, ComboBoxModel
{
  private XMLTag RootTag;
  private XMLTag[] Elements;
  private XMLTag ResultTag;
  private Object SelectedElement;
  private String ItemTagName;

  public XMLListModel(XMLTag _RootTag, String _ItemTagName)
  {
    this(_RootTag, _ItemTagName, new XMLTag("B", "B", true));
  }

  public XMLListModel(XMLTag _RootTag, String _ItemTagName, XMLTag _ResultTag)
  {
    ItemTagName = _ItemTagName;
    RootTag = _RootTag;
    ResultTag = _ResultTag;
    Elements = RootTag.getTags(ItemTagName);
    XMLTag InitialSelection = RootTag.getTagWhere(ItemTagName, ".", ResultTag.getValue());
    if(InitialSelection != null)
    {
      SelectedElement = InitialSelection;
    }
    else if(Elements.length > 0)
    {
      SelectedElement = Elements[0];
    }
    RootTag.addXMLTagListener(this);
  }

  public Object getSelectedItem()
  {
    return SelectedElement;
  }

  public void setSelectedItem(Object anItem)
  {
    if(anItem instanceof XMLTag)
    {
      SelectedElement = anItem;
      ResultTag.setValue(((XMLTag)anItem).getValue());
    }
    else if(anItem != null)
    {
      SelectedElement = RootTag.getTagWhere(ItemTagName, ".", anItem.toString());
      ResultTag.setValue(((XMLTag)SelectedElement).getValue());
    }
  }

  public Object getElementAt(int index)
  {
    return Elements[index];
  }

  // Returns the number of rows in the model.
  public int getSize()
  {
    return Elements.length;
  }

  public void setRootTag(XMLTag _RootTag)
  {
    if (RootTag!=null) RootTag.removeXMLTagListener(this);
    RootTag = _RootTag;
    Elements = RootTag.getTags(ItemTagName);
    if(Elements.length > 0)
    {
      SelectedElement = Elements[0];
      ResultTag.setValue(Elements[0].getValue());
    }
    else
    {
      SelectedElement = null;
    }
    fireContentsChanged(this, 0, Elements.length);
  }
  
  public void tagAdded(XMLTagEvent   e)
  {
    Elements = RootTag.getTags(ItemTagName);
    if(Elements.length > 0)
    {
      SelectedElement = Elements[0];
      ResultTag.setValue(Elements[0].getValue());
    }
    else
    {
      SelectedElement = null;
    }
    fireIntervalAdded(this, 0, Elements.length);
  }

  public void tagRemoved(XMLTagEvent e)
  {
    Elements = RootTag.getTags(ItemTagName);
    if(Elements.length > 0)
    {
      SelectedElement = Elements[0];
      ResultTag.setValue(Elements[0].getValue());
    }
    else
    {
      SelectedElement = null;
    }
    fireIntervalRemoved(this, 0, Elements.length);
  }

  public void tagChanged(XMLTagEvent e)
  {
    Elements = RootTag.getTags(ItemTagName);
    ResultTag.setValue(((XMLTag)SelectedElement).getValue());
    fireContentsChanged(this, 0, Elements.length);
  }
}