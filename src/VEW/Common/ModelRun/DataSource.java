package VEW.Common.ModelRun;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.dom4j.Document;
import org.dom4j.Node;

import VEW.Common.RealDataInputStream;
//import VEW.Common.XML.XMLTag;

public class DataSource {
  
  public static final byte LAYERS_SINGLE = 0;
  public static final byte LAYERS_PHYSICAL = 1;
  public static final byte LAYERS_BIOLOGICAL = 2;
  public static final byte LAYERS_PARTICLE = 3;
  
  protected String name;
  protected File dataFile;
  protected byte layers;
  protected boolean zipped;
  protected int outputAfter;
  protected int outputFrequency;
  protected List variables;
  protected boolean usingFloats;
  
  public DataSource() {
    
  }
  
  public DataSource(File directory, Node node, Document dataFormats) {
    name = node.valueOf("name");
    
    String dataFileName = node.valueOf("data");
    dataFile = new File(directory, dataFileName);
    
    String layersString = node.valueOf("layers").toLowerCase();
    if (layersString.equals("single"))
      layers = LAYERS_SINGLE;
    else if (layersString.equals("physical"))
      layers = LAYERS_PHYSICAL;
    else if (layersString.equals("biological"))
      layers = LAYERS_BIOLOGICAL;
    else
      throw new IllegalArgumentException("Unknown layer name: " + layersString);
    
    zipped = node.valueOf("zip").equals("true");
    
    usingFloats = dataFormats.valueOf("/dataformat/format").equals("float");
    
    outputAfter = Integer.parseInt(node.valueOf("output/after"));
    outputFrequency = Integer.parseInt(node.valueOf("output/freq"));
    
//    XMLTag[] varTags = node.getTags("var");
//    variables = new ArrayList(varTags.length);
//    Variable v;
//    for (int i = 0; i < varTags.length; i++) {
//      v = new Variable(varTags[i]);
//      variables.add(v);
//    }
    List varTags = node.selectNodes("var");
    variables = new ArrayList(varTags.size());
    for (Iterator i = varTags.iterator(); i.hasNext();)
      variables.add(new Variable((Node) i.next()));
  }
  
  public RealDataInputStream getDataInputStream() throws IOException {
    InputStream stream = new BufferedInputStream(new FileInputStream(dataFile));
    if (zipped)
      stream = new BufferedInputStream(new GZIPInputStream(stream));
    return RealDataInputStream.getInstance(stream, usingFloats);
  }

  public File getDataFile() {
    return dataFile;
  }

  public void setDataFile(File _dataFile) {
    dataFile = _dataFile;
  }

  public byte getLayers() {
    return layers;
  }

  public void setLayers(byte _layers) {
    layers = _layers;
  }

  public String getName() {
    return name;
  }

  public void setName(String _name) {
    name = _name;
  }

  public int getOutputAfter() {
    return outputAfter;
  }

  public void setOutputAfter(int _outputAfter) {
    outputAfter = _outputAfter;
  }

  public int getOutputFrequency() {
    return outputFrequency;
  }

  public void setOutputFrequency(int _outputFrequency) {
    outputFrequency = _outputFrequency;
  }

  public List getVariables() {
    return variables;
  }

  public void setVariables(List _variables) {
    variables = _variables;
  }

  public boolean isZipped() {
    return zipped;
  }

  public void setZipped(boolean _zipped) {
    zipped = _zipped;
  }
  
  public boolean isFunctionalGroup() {
    return false;
  }
  
  public String toString() {
    return name;
  }

  public boolean isUsingFloats() {
    return usingFloats;
  }

  public void setUsingFloats(boolean _usingFloats) {
    usingFloats = _usingFloats;
  }
}
