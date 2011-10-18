package VEW.Common.XML;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/** XMLFile is an extension of XMLTag that caters for reading/writing of XML files.
 * @see XMLTag
 * @author Adrian Rogers, Wes Hinsley
 * @version 2.0
 */
public class XMLFile extends XMLTag
{
  /** The path to the XML file represented by this object. */  
  protected String FileName;
  
  private static final String SAX_DRIVER_PROPERTY = "org.xml.sax.driver";
  private static final String CRIMSON_DRIVER = "org.apache.crimson.parser.XMLReaderImpl";
  private static final String XERCES_DRIVER = "org.apache.xerces.parsers.SAXParser";
  
  static {
    try {
      XMLReaderFactory.createXMLReader();
    } catch (SAXException e) {
      String driver = System.getProperty(SAX_DRIVER_PROPERTY);
      if (driver == null) {
        System.out.println("Default XML parser not set, trying a few common choices...");
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
          loader.loadClass(CRIMSON_DRIVER);
          System.setProperty(SAX_DRIVER_PROPERTY, CRIMSON_DRIVER);
          System.out.println("Using Crimson driver - if you don't like this, set the system property " + SAX_DRIVER_PROPERTY);
        } catch (ClassNotFoundException e1) {
          try {
            loader.loadClass(XERCES_DRIVER);
            System.setProperty(SAX_DRIVER_PROPERTY, XERCES_DRIVER);
            System.out.println("Using Xerces driver - if you don't like this, set the system property " + SAX_DRIVER_PROPERTY);
          } catch (ClassNotFoundException e2) {
            System.err.println("Could not find a suitable XML parser");
            System.err.println("  VEW requires at least Java 1.4 - if you don't have this, please upgrade");
            System.err.println("  If this doesn't solve the problem, please report this error, along with:");
            System.err.println("   - Your platform and operating system version");
            System.err.println("   - Your Java version");
          }
        }
      }
    }
  }

  /*\
   *  Constructors.
  \*/

  /** The constructor creates a new XMLFile object from the given path and root tag.
   * @param FileName The path where the XML file will be written to.
   * @param RootName The name of the root tag of the XML file.
   */  
  public XMLFile(String _FileName, String RootName)
  {
    FileName = _FileName;
    TagName = RootName;
  }

  /*\
   *  Get/Set Methods.
  \*/

  /** Get the path of the XML file.
   * @return The path of the file that this object will write it's XML data to.
   */  
  public String getFileName()
  {
    return FileName;
  }
  
  /** Overrides the default setValue method to ensure no value is written since
   * XMLFile represents a root tag which cannot have a value.
   * @param TagValue The value which the tag should be set to.
   * @return <CODE>false</CODE> indicating the value wasn't set.
   */  
  public boolean setValue(String _TagValue)
  {
    return false;
  }

  /** Change the path that this object will write to.
   * @param FileName The new path for the XML file.
   */  
  public void setFileName(String _FileName)
  {
    FileName = _FileName;
  }

  /*\
   *  File IO Methods.
  \*/

  /** Creates an instance of XMLFile by loading data from the given path.
   * @param FileName The path from which to read the XML data.
   * @return An XML file containing the data from the file referenced by the input path.
   */  
  public static XMLFile LoadFile(String FileName)
  {
    return LoadFile(new File(FileName));
  }
  
  /** Creates an instance of XMLFile by loading data from the given file.
   * @param fileName The file from which to read the XML data.
   * @return An XML file containing the data from the input file.
   */  
  public static XMLFile LoadFile(File fileName)
  {
    XMLReader parser;
    Reader fileReader;
    
    try {
      parser = XMLReaderFactory.createXMLReader();
    } catch (SAXException e) {
      throw new RuntimeException("Could not create an XML parser", e);
    }
    
    try {
      fileReader = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Could not find file: " + fileName, e); 
    }
    
    InputSource source = new InputSource(fileReader);
    XMLFileBuilder builder = new XMLFileBuilder(fileName.getAbsolutePath());
    
    parser.setContentHandler(builder);
    try {
      parser.parse(source);
    } catch (IOException e) {
      throw new RuntimeException("Error reading from file: " + fileName, e);
    } catch (SAXException e) {
      throw new RuntimeException("Error in XML file: " + e.getMessage());
    }
    return builder.getXMLFile();
  }

  /** Overrides the <CODE>write()</CODE> function from XMLTag to ensure that the data
   * is written to the specified file when write hits this node.
   * @return The success of the operation.
   */  
  public boolean write()
  {
    try
    {
      BufferedOutputStream WritingStream = new BufferedOutputStream(new FileOutputStream(FileName));
      return write(WritingStream);
    }
    catch(Exception e)
    {
      return false;
    }
  }
  
  /** Overrides the <CODE>write()</CODE> function from XMLTag to ensure that the
   * correct data headers are writen to the output stream.
   * @return The success of the operation.
   * @param WritingStream The stream to write to.
   */  
  public boolean write(OutputStream WritingStream)
  {
    try
    {
      BufferedWriter OutputWriter = new BufferedWriter(
                                    new OutputStreamWriter(WritingStream));
      OutputWriter.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>");
      OutputWriter.newLine();
      OutputWriter.newLine();
      OutputWriter.write("<" + TagName + ">");
      if(ChildrenCount != 0)
      {
        for(int i = 0; i < ChildrenCount; i++)
        {
          ((XMLTag)ChildTags.get(i)).write(1, OutputWriter);
        }
        OutputWriter.newLine();
        OutputWriter.write("</" + TagName + ">");
      }
      else
      {
        OutputWriter.newLine();
        OutputWriter.write("</" + TagName + ">");
      }
      OutputWriter.flush();
      OutputWriter.close();
      return true;
    }
    catch(Exception e)
    {
      e.printStackTrace();
      return false;
    }
  }

  /** Overrides the default clone operation to create an exact copy of this object.
   * @return A copy of this <CODE>XMLFile</CODE> object cast as an <CODE>Object</CODE>.
   */  
  public Object clone()
  {
		XMLFile ThisClone = new XMLFile(FileName, TagName);
		for(int i=0; i < ChildTags.size(); i++)
    {
			XMLTag TagToClone = (XMLTag)ChildTags.get(i);
			ThisClone.addTag((XMLTag)TagToClone.clone());
		}
		return ThisClone;
  }

  /** Create a copy of this object that is identical in every way except for the path
   * referenced by the object.
   * @param NewFile The path the new object should reference.
   * @return The new object referencing the path provided.
   */  
  public XMLFile cloneTo(String NewFile)
  {
		XMLFile ThisClone = new XMLFile(NewFile, TagName);
		for(int i=0; i < ChildTags.size(); i++)
    {
			XMLTag TagToClone = (XMLTag)ChildTags.get(i);
			ThisClone.addTag((XMLTag)TagToClone.clone());
		}
		return ThisClone;
  }
  
  private static class XMLFileBuilder extends DefaultHandler {
    
    private XMLFile result;
    private String fileName;
    
    private XMLTag currentTag;
    private StringBuffer currentData;
    
    public XMLFileBuilder(String _fileName) {
      fileName = _fileName;
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
      if (result == null) {
        result = new XMLFile(fileName, qName);
        currentTag = result;
      } else
        currentTag = currentTag.addTag(qName);
      
      for (int i = 0; i < atts.getLength(); i++)
        currentTag.setAttribute(atts.getQName(i), atts.getValue(i));
  
      currentData = new StringBuffer();
    }

    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
      currentTag.setValue(currentData.toString());
      
      currentTag = currentTag.getParentTag();
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
      currentData.append(ch, start, length);
    }
    
    public XMLFile getXMLFile() {
      return result;
    }

    public void error(SAXParseException e) throws SAXException {
      System.err.println("XML parsing error: " + e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXException {
      System.err.println("Fatal XML parsing error: " + e.getMessage());
      throw e;
    }

    public void warning(SAXParseException e) throws SAXException {
      System.err.println("XML parsing waring: " + e.getMessage());
    }
    
  }
}