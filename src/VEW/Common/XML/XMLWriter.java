package VEW.Common.XML;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Stack;

public class XMLWriter
{
  int CurrentIndentLevel = 0;
  boolean fileCompleted = false;
  BufferedWriter Outputter;
  Stack TagStack = new Stack();
  
  public XMLWriter(String FileName)
  {
    try
    {
      Outputter = new BufferedWriter(new FileWriter(FileName));
      Outputter.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"  ?>");
    }
    catch(Exception e)
    {
      System.err.println("An exception has occurred whilst trying to open an output stream");
    }
  }

  public String CloseTag() throws java.io.IOException
  {
    if(CurrentIndentLevel > 0)
    {
      CurrentIndentLevel--;
      Outputter.newLine();
      String TagName = (String)TagStack.pop();
      for(int i = 0; i < CurrentIndentLevel; i++)
      {
        Outputter.write("  ");
      }
      Outputter.write("</" + TagName + ">");
      return TagName;
    }
    else
    {
      return null;
    }
  }

  public void CloseAllTags() throws java.io.IOException
  {
    while(CloseTag() != null)
    {
    }
    Outputter.flush();
  }

  public String GetOpenTag()
  {
    return (String)TagStack.peek();
  }

  public void AddTag(String TagName) throws java.io.IOException
  {
    if(!fileCompleted)
    {
      Outputter.newLine();
      for(int i = 0; i < CurrentIndentLevel; i++)
      {
        Outputter.write("  ");
      }
      Outputter.write("<" + TagName + ">");
      TagStack.push(TagName);
      CurrentIndentLevel++;
    }
  }

  public void AddTag(String Tag, String Value) throws java.io.IOException
  {
    if(!fileCompleted)
    {
      Outputter.newLine();
      for(int i = 0; i < CurrentIndentLevel; i++)
      {
        Outputter.write("  ");
      }
      Outputter.write("<" + Tag + ">");
      Outputter.write(Value);
      Outputter.write("</" + Tag + ">");
    }
  }

  public void AddEmptyTag(String Tag, String[][] Attributes) throws java.io.IOException
  {
    if(!fileCompleted)
    {
      Outputter.newLine();
      for(int i = 0; i < CurrentIndentLevel; i++)
      {
        Outputter.write("  ");
      }
      Outputter.write("<" + Tag);
      for(int i = 0; i < Attributes.length; i++)
      {
        Outputter.write(" " + Attributes[i][0] + "=\"" + Attributes[i][1] + "\"");
      }
      Outputter.write(" />");
    }
  }

  public void AddTag(String TagName, String[][] Attributes) throws java.io.IOException
  {
    if(!fileCompleted)
    {
      Outputter.newLine();
      for(int i = 0; i < CurrentIndentLevel; i++)
      {
        Outputter.write("  ");
      }
      Outputter.write("<" + TagName);
      for(int i = 0; i < Attributes.length; i++)
      {
        Outputter.write(" " + Attributes[i][0] + "=\"" + Attributes[i][1] + "\"");
      }
      Outputter.write(">");
      TagStack.push(TagName);
      CurrentIndentLevel++;
    }
  }

  public void AddTag(XMLTag Tag) throws java.io.IOException
  {
    if(!fileCompleted)
    {
      Outputter.newLine();
      Tag.writeTag(Outputter, CurrentIndentLevel);
    }
  }
}