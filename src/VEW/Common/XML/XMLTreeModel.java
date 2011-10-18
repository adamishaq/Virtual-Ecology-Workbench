/*
 * XMLTreeModel.java
 *
 * Created on December 15, 2003, 1:51 PM
 */

package VEW.Common.XML;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author  Adrian Rogers
 */
public class XMLTreeModel implements TreeModel, XMLTagListener
{
  private XMLTag RootTag;
  protected EventListenerList Listeners = new EventListenerList();
  
  /** Creates a new instance of XMLTreeModel */
  public XMLTreeModel(XMLTag _RootTag)
  {
    RootTag = _RootTag;
    RootTag.addXMLTagListener(this);
  }
  
  public void addTreeModelListener(TreeModelListener L)
  {
    Listeners.add(TreeModelListener.class, L);
  }
  
  public Object getChild(Object obj, int param)
  {
    return ((XMLTag)obj).getTag("*", param + 1);
  }
  
  public int getChildCount(Object obj)
  {
    return ((XMLTag)obj).getChildCount();
  }
  
  public int getIndexOfChild(Object obj, Object obj1)
  {
    return ((XMLTag)obj).getIndex((XMLTag)obj1);
  }
  
  public Object getRoot()
  {
    return RootTag;
  }
  
  public boolean isLeaf(Object obj)
  {
    return ((XMLTag)obj).isLeaf();
  }
  
  public void removeTreeModelListener(TreeModelListener L)
  {
    Listeners.remove(TreeModelListener.class, L);
  }
  
  public void valueForPathChanged(TreePath treePath, Object obj)
  {
  }
  
  protected void fireNodesChanged(TreePath PathToNode)
  {
    TreeModelListener[] listeners = (TreeModelListener[])Listeners.getListeners(TreeModelListener.class);
    for(int i = 0; i < listeners.length; i++)
    {
      listeners[i].treeNodesChanged(new TreeModelEvent(this, PathToNode));
    }
  }
  // XML Tag Listener Stuff
  /*
  protected void fireTagChanged(XMLPath PathToTag)
  {
    XMLTagListener[] listeners = (XMLTagListener[])Listeners.getListeners(XMLTagListener.class);
    for(int i = 0; i < listeners.length; i++)
    {
      listeners[i].tagChanged(new XMLTagEvent(this, PathToTag));
    }
    if(ParentTag != null)
    {
      ParentTag.fireTagChanged(new XMLPath(this, PathToTag, true));
    }
  }
  */
  public void tagAdded(XMLTagEvent e)
  {
    fireNodesChanged(new TreePath(RootTag));
  }
  
  public void tagChanged(XMLTagEvent e)
  {
    fireNodesChanged(new TreePath(RootTag));
  }
  
  public void tagRemoved(XMLTagEvent e)
  {
    fireNodesChanged(new TreePath(RootTag));
  }
 
}
