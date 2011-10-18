package VEW.Common.ModelRun;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.dom4j.Node;

import VEW.Common.RealDataInputStream;
//import VEW.Common.XML.XMLTag;

public class FGDataSource extends DataSource {
  
  protected File timeFile;
  protected File lineageFile;
  protected File lifespanFile;
  protected int type;
  protected List stages;
  protected boolean[][] transitions;
  
  public FGDataSource() {
    
  }
  
  public FGDataSource(File directory, Node fg, ModelRun theModelRun) {
    name = fg.valueOf("name");
    
    String dataFileName = fg.valueOf("data");
    dataFile = new File(directory, dataFileName);
    
    String timeFileName = fg.valueOf("time");
    if (timeFileName != null)
      timeFile = new File(directory, timeFileName);
    
    String lineageFileName = fg.valueOf("lineage");
    if (lineageFileName != null)
      lineageFile = new File(directory, lineageFileName);
    
    String lifespanFileName = fg.valueOf("lifespan");
    if (lifespanFileName != null)
      lifespanFile = new File(directory, lifespanFileName);
    
    layers = LAYERS_PARTICLE;
    
    zipped = fg.valueOf("zip").equals("true");
    
    usingFloats = theModelRun.getDataFormats().valueOf("/dataformat/format").equals("float");
    
    outputAfter = Integer.parseInt(fg.valueOf("output/after"));
    outputFrequency = Integer.parseInt(fg.valueOf("output/freq"));
    
    type = Integer.parseInt(fg.valueOf("type"));
    
//    XMLTag[] varTags = fg.getTags("var");
    List varTags = fg.selectNodes("var");
    variables = new ArrayList(varTags.size());
    Variable v;
    for (Iterator i = varTags.iterator(); i.hasNext(); ) {
      v = new Variable((Node) i.next());
      variables.add(v);
    }
    
//    XMLTag fgTag = theModelRun.getModel().getTagWhere("model/functionalgroup", "species/variety/name", name);
    Node fgTag = theModelRun.getModel().selectSingleNode(
        "/model/functionalgroup[species/variety/name='" + name + "']");
    if (fgTag!=null) {
      String fgName = fgTag.valueOf("name");
//      XMLTag stateGroup = theModelRun.getDataFormats().getTagWhere("dataformat/states/functionalgroup", "name", fgName);
      Node stateGroup = theModelRun.getDataFormats().selectSingleNode(
          "/dataformat/states/functionalgroup[name='" + fgName + "']");
//      XMLTag[] states = stateGroup.getTags("state");
      List states = stateGroup.selectNodes("state");
      String[] stageArray = new String[states.size()];
      int stageNo;
      for (Iterator i = states.iterator(); i.hasNext(); ) {
        Node node = (Node) i.next();
        stageNo = Integer.parseInt(node.valueOf("@id"));
        stageArray[stageNo] = node.valueOf("@name");
      }
      stages = new ArrayList(Arrays.asList(stageArray));
    
//      XMLTag[] transitionTags = stateGroup.getTags("transition");
      List transitionTags = stateGroup.selectNodes("transition");
      int stage1, stage2;
      transitions = new boolean[stages.size()][stages.size()];
      for (Iterator i = transitionTags.iterator(); i.hasNext(); ) {
        Node node = (Node) i.next();
        stage1 = Integer.parseInt(node.valueOf("@from"));
        stage2 = Integer.parseInt(node.valueOf("@to"));
        transitions[stage1][stage2] = true;
      }
    }
  }

  public RealDataInputStream getTimeInputStream() throws IOException {
    InputStream stream = new BufferedInputStream(new FileInputStream(timeFile));
    if (zipped)
      stream = new BufferedInputStream(new GZIPInputStream(stream));
    return RealDataInputStream.getInstance(stream, usingFloats);
  }
  
  public RealDataInputStream getLineageInputStream() throws IOException {
    InputStream stream = new BufferedInputStream(new FileInputStream(lineageFile));
    if (zipped)
      stream = new BufferedInputStream(new GZIPInputStream(stream));
    return RealDataInputStream.getInstance(stream, usingFloats);
  }
  
  public RealDataInputStream getLifespanInputStream() throws IOException {
    InputStream stream = new BufferedInputStream(new FileInputStream(lifespanFile));
    if (zipped)
      stream = new BufferedInputStream(new GZIPInputStream(stream));
    return RealDataInputStream.getInstance(stream, usingFloats);
  }
  
  public File getLifespanFile() {
    return lifespanFile;
  }

  public void setLifespanFile(File _lifespanFile) {
    lifespanFile = _lifespanFile;
  }

  public File getLineageFile() {
    return lineageFile;
  }

  public void setLineageFile(File _lineageFile) {
    lineageFile = _lineageFile;
  }

  public File getTimeFile() {
    return timeFile;
  }

  public void setTimeFile(File _timeFile) {
    timeFile = _timeFile;
  }

  public int getType() {
    return type;
  }

  public void setType(int _type) {
    type = _type;
  }

  public boolean isFunctionalGroup() {
    return true;
  }
  
  public List getStages() {
    return stages;
  }
  
  public String getStage(int id) {
    return (String) stages.get(id);
  }

  public boolean[][] getTransitions() {
    return transitions;
  }

}
