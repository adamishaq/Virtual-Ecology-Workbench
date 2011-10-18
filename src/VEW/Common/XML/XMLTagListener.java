package VEW.Common.XML;

import java.util.EventListener;

public interface XMLTagListener extends EventListener
{
  public void tagAdded(XMLTagEvent   e);
  public void tagRemoved(XMLTagEvent e);
  public void tagChanged(XMLTagEvent e);
}