package VEW.Common.ModelRun;

import org.dom4j.Node;

//import VEW.Common.XML.XMLTag;

public class Variable {
  
  private int id;
  private String name;
  private String description;
  private String units;
  private int topDepth;
  private int bottomDepth;
  
  public Variable() {
    
  }
  
  public Variable(Node node) {
    id = node.numberValueOf("id").intValue();
    name = node.valueOf("name");
    description = node.valueOf("desc");
    units = node.valueOf("units");
    
    String topString = node.valueOf("topdepth");
    try {
      topDepth = Integer.parseInt(topString);
    } catch (NumberFormatException e) {
      topDepth = -1;
    }
    
    String bottomString = node.valueOf("bottomdepth");
    try {
      bottomDepth = Integer.parseInt(bottomString);
    } catch (NumberFormatException e) {
      bottomDepth = -1;
    }
  }

  public int getBottomDepth() {
    return bottomDepth;
  }

  public void setBottomDepth(int _bottomDepth) {
    bottomDepth = _bottomDepth;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String _description) {
    description = _description;
  }

  public int getId() {
    return id;
  }

  public void setId(int _id) {
    id = _id;
  }

  public String getName() {
    return name;
  }

  public void setName(String _name) {
    name = _name;
  }

  public int getTopDepth() {
    return topDepth;
  }

  public void setTopDepth(int _topDepth) {
    topDepth = _topDepth;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String _units) {
    units = _units;
  }

  public String toString() {
    if (description != null)
      return description;
    else
      return name;
  }
}
