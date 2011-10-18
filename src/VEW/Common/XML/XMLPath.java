package VEW.Common.XML;

/** A class designed to implement the full XPath specification. */
public class XMLPath
{
  private String Path = ".";

  public XMLPath()
  {
  }

  public XMLPath(XMLTag TagToAdd, XMLPath OldPath, boolean AddBefore)
  {
    if(Path.equals("."))
    {
      Path = TagToAdd.getName();
    }
    else if(AddBefore)
    {
      Path = TagToAdd.getName() + "/" + Path;
    }
    else
    {
      Path += "/" + TagToAdd.getName();
    }
  }

  /*
  public void traversePath(String Path, XMLTag ContextNode)
  {
    String TempPath;
    String[] Tokens, SubTokens;
    XMLTag CurrentNode;
    XMLTag[] ResultTags = null;
    
    if(Path.startsWith("/"))
    {
      CurrentNode = ContextNode.getRootTag();
      TempPath = Path.substring(1);
    }
    else
    {
      CurrentNode = ContextNode;
      TempPath = Path;
    }
    
    Tokens = TempPath.split("/");
    for(int t = 0; t < Tokens.length; t++)
    {
      SubTokens = Tokens[t].split("::");
      if(SubTokens.length == 2)
      {
        ResultTags = processToken(SubTokens, CurrentNode);
      }
      else if(Tokens[t].equals("."))
      {
        ResultTags = processToken(new String[]{"self","node()"}, CurrentNode);
      }
      else if(Tokens[t].equals(".."))
      {
        ResultTags = processToken(new String[]{"parent","node()"}, CurrentNode);
      }
      else if(Tokens[t].equals("..."))
      {
        ResultTags = processToken(new String[]{}, CurrentNode);
      }
      else
      {
        ResultTags = processToken(new String[]{"child",Tokens[t]}, CurrentNode);
      }
    }
    return ResultTags;
  }
  
  private XMLTag[] processToken(String[] Tokens, XMLTag ContextNode)
  {
    String[] SubTokens = Tokens[1].split("\\]\\[|\\[|\\]");
    XMLTag[] ResultTags = null;
    if(Tokens[0].equals("child"))
    {
      ResultTags = ContextNode.getChildren(SubTokens[0]);
    }
    else if(Tokens[0].equals("descendant"))
    {
      ResultTags = ContextNode.getDescendents(SubTokens[0]);
    }
    else if(Tokens[0].equals("parent"))
    {
      ResultTags = ContextNode.getParentTag(SubTokens[0]);
    }
    else if(Tokens[0].equals("ancestor"))
    {
      ResultTags = ContextNode.getAncestors(SubTokens[0]);
    }
    else if(Tokens[0].equals("following-sibling"))
    {
      ResultTags = ContextNode.getFollowingSiblings(SubTokens[0]);
    }
    else if(Tokens[0].equals("preceeding-sibling"))
    {
      ResultTags = ContextNode.getPreceedingSiblings(SubTokens[0]);
    }
    else if(Tokens[0].equals("following"))
    {
      ResultTags = ContextNode.getFollowers(SubTokens[0]);
    }
    else if(Tokens[0].equals("preceeding"))
    {
      ResultTags = ContextNode.getPreceeders(SubTokens[0]);
    }
    else if(Tokens[0].equals("attribute"))
    {
      ResultTags = ContextNode.getAttributes(SubTokens[0]);
    }
    else if(Tokens[0].equals("namespace"))
    {
      ResultTags = ContextNode.getNamespaces(SubTokens[0]);
    }
    else if(Tokens[0].equals("self"))
    {
      ResultTags = ContextNode.getSelf(SubTokens[0]);
    }
    else if(Tokens[0].equals("descendent-or-self"))
    {
      ResultTags = ContextNode.getDescendentsOrSelf(SubTokens[0]);
    }
    else if(Tokens[0].equals("ancestor-or-self"))
    {
      ResultTags = ContextNode.getAncestorsOrSelf(SubTokens[0]);
    }
    else
    {
      System.err.println("Invalid Axis Name used: " + Tokens[0]);
    }
    for(int s = 1; s < SubTokens.length; s++)
    {
      ResultTags = processPredicate(ResultTags, SubTokens[s]);
    }
    return ResultTags;
  }
  
  private XMLTag[] processPredicate(XMLTag[] SourceTags, String Expression)
  {
    return SourceTags;
  }
*/
  public String toString()
  {
    return Path;
  }
}