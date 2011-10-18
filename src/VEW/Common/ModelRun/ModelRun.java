package VEW.Common.ModelRun;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

//import VEW.Common.XML.XMLFile;
//import VEW.Common.XML.XMLTag;

public class ModelRun {

  private static final String DATAFORMATS_XML = "DataFormats.xml";
  private static final String MODEL_XML = "Model.xml";
  
  private File directory;
  private Document dataFormats;
  private Document model;
  
  private List environments;
  private List functionalGroups;
  private List allDataSources;
  
  private Map envMap;
  private Map fgMap;
  private Map dsMap;
  
  private String[] deathReasons;
  
  public ModelRun(File _directory) {
    directory = _directory;
    
    SAXReader reader = new SAXReader();
    File dataFormatsFile = new File(directory, DATAFORMATS_XML);
    File modelFile = new File(directory, MODEL_XML);
    
    try {
//    dataFormats = XMLFile.LoadFile(dataFormatsFile);
      dataFormats = reader.read(dataFormatsFile);
//    model = XMLFile.LoadFile(modelFile);
      model = reader.read(modelFile);
    } catch (DocumentException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    
    loadDataSources();
    loadReasons();
  }

  private void loadDataSources() {
//    XMLTag[] envTags = dataFormats.getTags("dataformat/environment");
    List envTags = dataFormats.selectNodes("dataformat/environment");
    environments = new ArrayList(envTags.size());
    envMap = new HashMap(envTags.size());
    for (Iterator i = envTags.iterator(); i.hasNext();) {
      Node env = (Node) i.next();
      DataSource newSource = new DataSource(directory, env, dataFormats); 
      environments.add(newSource);
      envMap.put(newSource.getName(), newSource);
    }
    
//    int countVarieties=0;
//    for (int i=0; i<model.getTags("functionalgroup").length; i++) {
//      for (int j=0; j<model.getTags("functionalgroup")[i].getTags("species").length; j++) {
//        countVarieties+=model.getTags("functionalgroup")[i].getTags("species")[j].getTags("variety").length;
//      }
//    }
//    XMLTag[] varietiesInModel = new XMLTag[countVarieties];
//    countVarieties=0;
//    for (int i=0; i<model.getTags("functionalgroup").length; i++) {
//      for (int j=0; j<model.getTags("functionalgroup")[i].getTags("species").length; j++) {
//        for (int k=0; k<model.getTags("functionalgroup")[i].getTags("species")[j].getTags("variety").length; k++) {
//          varietiesInModel[countVarieties++]=model.getTags("functionalgroup")[i].getTags("species")[j].getTags("variety")[k];
//        }
//      }
//    }
    List varietiesInModel = model.selectNodes("//model/functionalgroup/species/variety");
    
//    XMLTag[] fgTags = new XMLTag[varietiesInModel.length];
    List fgTags = new ArrayList(varietiesInModel.size());
//    for (int i=0; i<varietiesInModel.length; i++)
//      fgTags[i]=dataFormats.getTag("dataformat").getTagWhere("functionalgroup","name",varietiesInModel[i].getValue("name"));
    for (Iterator i = varietiesInModel.iterator(); i.hasNext();) {
      Node variety = (Node) i.next();
      String name = variety.valueOf("name");
      fgTags.add(dataFormats.selectSingleNode("/dataformat/functionalgroup[name='"
          + name + "']"));
    }
    functionalGroups = new ArrayList(fgTags.size());
    fgMap = new HashMap(fgTags.size());
    for (Iterator i = fgTags.iterator(); i.hasNext();) {
      Node fg = (Node) i.next();
      FGDataSource newSource = new FGDataSource(directory, fg, this);
      functionalGroups.add(newSource);
      fgMap.put(newSource.getName(), newSource);
    }
    
    allDataSources = new ArrayList(environments.size() + functionalGroups.size());
    allDataSources.addAll(environments);
    allDataSources.addAll(functionalGroups);
    
    dsMap = new HashMap(allDataSources.size());
    dsMap.putAll(envMap);
    dsMap.putAll(fgMap);
  }
  
  private void loadReasons() {
//    XMLTag[] reasonTags = dataFormats.getTags("dataformat/deathreasons/death");
    List reasonTags = dataFormats.selectNodes("/dataformat/deathreasons/death");
    int index;
    
    deathReasons = new String[reasonTags.size()];
    for (Iterator i = reasonTags.iterator(); i.hasNext();) {
      Node reason = (Node) i.next();
      index = reason.numberValueOf("@id").intValue();
      deathReasons[index] = reason.valueOf("@reason");
    }
  }
  
  public DataSource getEnvironmentByName(String name) {
    return (DataSource) envMap.get(name);
  }
  
  public FGDataSource getFunctionalGroupByName(String name) {
    return (FGDataSource) fgMap.get(name);
  }
  
  public DataSource getDataSourceByName(String name) {
    return (DataSource) dsMap.get(name);
  }
  
  public List getEnvironments() {
    return environments;
  }
  
  public List getFunctionalGroups() {
    return functionalGroups;
  }

  public List getAllDataSources() {
    return allDataSources;
  }

  public File getDirectory() {
    return directory;
  }

  public Document getDataFormats() {
    return dataFormats;
  }

  public Document getModel() {
    return model;
  }

  public File getDataFormatsFile() {
    return new File(directory, DATAFORMATS_XML);
  }
  
  public File getModelFile() {
    return new File(directory, MODEL_XML);
  }
  
  public String[] getDeathReasons() {
    return deathReasons;
  }

}
