package VEW.Analyser4;

import java.awt.Color;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;

import VEW.Common.DateDialog;
import VEW.Common.StringTools;

public class LineDefinition {
  public String name;
  public int type;
  public int scope;
  public long[] ids;
  public long thisId;
  public int[] depthIndices;
  public int thisDepthIndex;
  public int[] compoundVars1;
  public int[] compoundVars2;
  public String[] names;
  public double[] dataX;
  public double[] dataY;
  public boolean zip;
  public int layer;
  public String file;
  public byte operator;
  public byte CPdepthOp;
  public boolean useFloat;
  public boolean dpfill;
  public boolean autoscale;
  public boolean log;
  public boolean logx;  
  public boolean accumulate;
  public boolean accumulatex;  
  public boolean invertY;
  public boolean invertX;
  public boolean xyconnect;
  public Color colour;
  public Color[] colours;
  public GregorianCalendar timestep;
  public double min;
  public double max;
  public double minX;
  public double maxX;
  public long timeMin;
  public long timeMax;
  public long timeMinX;
  public long timeMaxX;
  public boolean visible;
  public int topDepthIndex;
  public int bottomDepthIndex;
  public int xpos;
  public boolean xauto;
  public int internalID;
  public static final byte OP_ADD = 1;
  public static final byte OP_MUL = 2;
  public static final byte OP_IND = 3;
  public static final byte TIME_SERIES  = 0;
  public static final byte COMPOUND_PLOT = 1;
  public static final byte DEPTH_PROFILE = 2;
  public static final byte XY_PLOT = 3;  
  public static final byte SINGLE = 0;
  public static final byte BIOLOGICAL = 1;
  public static final byte PHYSICAL = 2;
  public static final byte FUNCTIONALGROUP = 3;
      
  
  public LineDefinition() { visible = true;}
  
  public LineDefinition getCopy() {
    LineDefinition l2 = new LineDefinition();
    l2.copyFrom(this);
    return l2;
  }
  
  public Node convertToTag() {
    Element line = DocumentFactory.getInstance().createElement("line");
    line.addElement("xyconnect").addText(String.valueOf(xyconnect));
    line.addElement("zip").addText(String.valueOf(zip));
    line.addElement("type").addText(String.valueOf(type));
    line.addElement("scope").addText(String.valueOf(scope));
    line.addElement("layer").addText(String.valueOf(layer));            
    line.addElement("col").addText(String.valueOf(colour.getRGB()));
    line.addElement("id").addText(String.valueOf(thisId));
    line.addElement("accumulate").addText(String.valueOf(accumulate));
    line.addElement("accumulatex").addText(String.valueOf(accumulatex));    
    line.addElement("bottomdepth").addText(String.valueOf(bottomDepthIndex));
    line.addElement("thisdepth").addText(String.valueOf(thisDepthIndex));
    line.addElement("topdepth").addText(String.valueOf(topDepthIndex));            
    line.addElement("inverty").addText(String.valueOf(invertY));
    line.addElement("invertx").addText(String.valueOf(invertX));            
    line.addElement("visible").addText(String.valueOf(visible));    
    line.addElement("log").addText(String.valueOf(log));
    line.addElement("logx").addText(String.valueOf(logx));    
    line.addElement("auto").addText(String.valueOf(autoscale));
    line.addElement("useFloat").addText(String.valueOf(useFloat));
    line.addElement("max").addText(String.valueOf(max));
    line.addElement("min").addText(String.valueOf(min));
    line.addElement("timemax").addText(String.valueOf(timeMax));
    line.addElement("timemin").addText(String.valueOf(timeMin));
    line.addElement("timemax").addText(String.valueOf(timeMaxX));
    line.addElement("timemin").addText(String.valueOf(timeMinX));
    line.addElement("dpfill").addText(String.valueOf(dpfill));            
    if (timestep!=null) line.addElement("timestep").addText(String.valueOf(timestep.getTimeInMillis()));
    else line.addElement("timestep").addText("0");
    line.addElement("maxx").addText(String.valueOf(maxX));
    line.addElement("minx").addText(String.valueOf(minX));
    line.addElement("xpos").addText(String.valueOf(xpos));
    line.addElement("xauto").addText(String.valueOf(xauto));            
    line.addElement("file").addText(file);
    line.addElement("internalID").addText(String.valueOf(internalID));            
    line.addElement("name").addText(name);
    line.addElement("op").addText(String.valueOf(operator));
    line.addElement("cpop").addText(String.valueOf(CPdepthOp));    
    if (compoundVars1!=null) for (int j=0; j<compoundVars1.length; j++)
      line.addElement("compound1").addText(String.valueOf(compoundVars1[j]));
    if (compoundVars2!=null) for (int j=0; j<compoundVars2.length; j++)
      line.addElement("compound2").addText(String.valueOf(compoundVars2[j]));
    return line;
  }
  
  public static LineDefinition createFromTag(Node node) {
    LineDefinition ld = new LineDefinition();
    ld.xyconnect = StringTools.parseBoolean(node.valueOf("xyconnect"));
    ld.zip = StringTools.parseBoolean(node.valueOf("zip"));
    ld.type = Integer.parseInt(node.valueOf("type"));
    ld.scope = Integer.parseInt(node.valueOf("scope"));
    ld.layer = Integer.parseInt(node.valueOf("layer"));
    ld.colour = new Color(Integer.parseInt(node.valueOf("col")));
    ld.thisId = Long.parseLong(node.valueOf("id"));
    ld.accumulate = StringTools.parseBoolean(node.valueOf("accumulate"));
    ld.accumulatex = StringTools.parseBoolean(node.valueOf("accumulatex"));    
    ld.bottomDepthIndex = Integer.parseInt(node.valueOf("bottomdepth"));
    ld.topDepthIndex = Integer.parseInt(node.valueOf("topdepth"));
    ld.thisDepthIndex = Integer.parseInt(node.valueOf("thisdepth"));
    ld.invertY = StringTools.parseBoolean(node.valueOf("inverty"));
    ld.invertX = StringTools.parseBoolean(node.valueOf("invertx"));
    ld.timeMin=Long.MIN_VALUE;
    ld.timeMax=Long.MIN_VALUE;
    ld.timeMinX=Long.MIN_VALUE;
    ld.timeMaxX=Long.MIN_VALUE;
    
    if (node.valueOf("timeminX").length()>0) ld.timeMinX=Long.parseLong(node.valueOf("timeminX"));
    if (node.valueOf("timemaxX").length()>0) ld.timeMaxX=Long.parseLong(node.valueOf("timemaxX"));    
    ld.log = StringTools.parseBoolean(node.valueOf("log"));    
    ld.logx = StringTools.parseBoolean(node.valueOf("logx"));    
    ld.autoscale = StringTools.parseBoolean(node.valueOf("auto"));
    ld.visible = StringTools.parseBoolean(node.valueOf("visible"));
    if (node.valueOf("useFloat").equals("")) ld.useFloat=true;
    else ld.useFloat = StringTools.parseBoolean(node.valueOf("useFloat"));
    ld.max = Double.parseDouble(node.valueOf("max"));
    ld.min = Double.parseDouble(node.valueOf("min"));
    ld.dpfill = StringTools.parseBoolean(node.valueOf("dpfill"));
    ld.timestep = new GregorianCalendar(DateDialog.GMTTimeZone);
    ld.timestep.setTimeInMillis(Long.parseLong(node.valueOf("timestep")));
    ld.maxX = Double.parseDouble(node.valueOf("maxx"));
    ld.minX = Double.parseDouble(node.valueOf("minx"));
    ld.xpos = Integer.parseInt(node.valueOf("xpos"));
    ld.xauto = StringTools.parseBoolean(node.valueOf("xauto"));
    ld.file = node.valueOf("file");
    ld.internalID = Integer.parseInt(node.valueOf("internalID"));
    ld.name = node.valueOf("name");
    ld.operator = Byte.parseByte(node.valueOf("op"));
    ld.CPdepthOp = Byte.parseByte(node.valueOf("cpop"));
    
    List compound1 = node.selectNodes("compound1");
    ld.compoundVars1 = new int[compound1.size()];
    int k = 0;
    for (Iterator i = compound1.iterator(); i.hasNext();) {
      Node n = (Node) i.next();
      ld.compoundVars1[k++] = Integer.parseInt(n.getText());
    }
    
    List compound2 = node.selectNodes("compound2");
    ld.compoundVars2 = new int[compound2.size()];
    k = 0;
    for (Iterator i = compound2.iterator(); i.hasNext();) {
      Node n = (Node) i.next();
      ld.compoundVars2[k++] = Integer.parseInt(n.getText());
    }
    return ld;
  }

  public void copyFrom(LineDefinition ld) {
    this.name = new String(ld.name);
    this.type = ld.type;
    this.scope = ld.scope;
    this.thisId = ld.thisId;
    this.thisDepthIndex = ld.thisDepthIndex;
    this.operator = ld.operator;
    this.CPdepthOp = ld.CPdepthOp;
    this.autoscale = ld.autoscale;
    this.log = ld.log;
    this.logx = ld.logx;    
    this.accumulate = ld.accumulate;
    this.accumulatex = ld.accumulatex;    
    this.invertY = ld.invertY;
    this.min = ld.min;
    this.minX = ld.minX;
    this.maxX = ld.maxX;
    this.timeMin = ld.timeMin;
    this.timeMax = ld.timeMax;
    this.timeMinX = ld.timeMinX;
    this.timeMaxX = ld.timeMaxX;
    this.invertX = ld.invertX;
    this.xyconnect = ld.xyconnect;
    this.colour = new Color(ld.colour.getRGB());
    this.max = ld.max;
    this.visible = ld.visible;
    this.zip = ld.zip;
    this.file = new String(ld.file);
    this.layer = ld.layer;
    this.topDepthIndex = ld.topDepthIndex;
    this.bottomDepthIndex = ld.bottomDepthIndex;
    this.xpos = ld.xpos;
    this.xauto = ld.xauto;
    this.dpfill = ld.dpfill;
    if (ld.dataX!=null) {
      this.dataX = new double[ld.dataX.length];
      for (int i=0; i<ld.dataX.length; i++) this.dataX[i]=ld.dataX[i];      
    } else this.dataX = null;
    if (ld.dataY!=null) {
      this.dataY = new double[ld.dataY.length];
      for (int i=0; i<ld.dataY.length; i++) this.dataY[i]=ld.dataY[i];      
    } else this.dataY = null;
    
        
    this.useFloat = ld.useFloat;
    if (ld.depthIndices!=null) {
      this.depthIndices = new int[ld.depthIndices.length];
      for (int i=0; i<ld.depthIndices.length; i++) this.depthIndices[i]=ld.depthIndices[i];
    }
    if (ld.compoundVars1!=null) {
      this.compoundVars1 = new int[ld.compoundVars1.length];
      for (int i=0; i<ld.compoundVars1.length; i++) this.compoundVars1[i]=ld.compoundVars1[i];
    }
    if (ld.compoundVars2!=null) {
      this.compoundVars2 = new int[ld.compoundVars2.length];
      for (int i=0; i<ld.compoundVars2.length; i++) this.compoundVars2[i]=ld.compoundVars2[i];
    }

    if (ld.ids!=null) {
      this.ids= new long[ld.ids.length];
      for (int i=0; i<ld.ids.length; i++) this.ids[i]=ld.ids[i];
    }
    if (ld.timestep!=null) this.timestep = (GregorianCalendar) ld.timestep.clone();
  }
}

