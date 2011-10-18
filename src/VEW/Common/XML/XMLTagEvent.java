package VEW.Common.XML;

import java.util.EventObject;

public class XMLTagEvent extends EventObject
{
  private XMLPath PathToSource;

  public XMLTagEvent(XMLTag Source, XMLPath _PathToSource)
  {
    super(Source);
    PathToSource = _PathToSource;
  }

  public XMLPath getPath()
  {
    return PathToSource;
  }
}