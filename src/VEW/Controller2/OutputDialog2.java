package VEW.Controller2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import VEW.Common.DateDialog;
import VEW.Common.MessageBox;
import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class OutputDialog2 extends JPanel {
  private XMLTag theModel;
  private DefaultListModel structureListModel= new DefaultListModel();
  private JList structureList = new JList(structureListModel);  
  private DefaultListModel scopeListModel= new DefaultListModel();
  private JList scopeList = new JList(scopeListModel);
  private DefaultListModel variableListModel = new DefaultListModel();
  private JList variableList = new JList(variableListModel);
  private DefaultListModel varSpecStageListModel = new DefaultListModel();
  private JList varSpecStageList = new JList(varSpecStageListModel);
  private JLabel listTitle = new JLabel("",SwingConstants.CENTER);
  private DefaultListModel chosenVarsListModel = new DefaultListModel();
  private JList chosenVarsList = new JList(chosenVarsListModel);
  private JButton removeEntry = new JButton("Remove");
  private JButton addEntry = new JButton("Add");  
  private int lockEvents = 0;
  private EventHandler eh = new EventHandler();
  private JButton auditTrails = new JButton("Audit IDs");
  private DateDialog dateDialog;
  private GregorianCalendar startSim, endSim;
  private StageChooser stageChooser;
      
  // Extended options
  
  private InfoTableModel fileInfoModel = new InfoTableModel();
  private FlexiTable fileInfo = new FlexiTable(fileInfoModel);
  
  private JButton applyDateToAll = new JButton("Apply Date to All");
  private JButton applyDepthToAll = new JButton("Apply Depth to All");
  private XMLTag collectAudit = new XMLTag("auditmanager");
  private VEWController2 vc2;

  public static final String STRUCT_WATER_COLUMN = new String("Water Column");  
  public static final String STRUCT_FIELD_DATA = new String("Field Data");  
  public static final String STRUCT_AUDIT_TRAIL = new String("Audit Trail");
    
  public static final String SCOPE_WC_BIOLOGICAL = new String("Biological");
  public static final String SCOPE_WC_CHEMICAL = new String("Chemical");
  public static final String SCOPE_WC_DEMOGRAPHIC = new String("Demographic");
  public static final String SCOPE_WC_PHYSICAL = new String("Physical");
  
  public static final String SCOPE_FD_BIOLOGICAL = new String("Biological");
  public static final String SCOPE_FD_CHEMICAL = new String("Chemical");
  public static final String SCOPE_FD_DEMOGRAPHIC = new String("Demographic");
  public static final String SCOPE_FD_PHYSICAL = new String("Physical");
  
  public static final String SCOPE_AT_AGENT = new String("Agent/Individual");
  public static final String SCOPE_AT_BIOLOGICAL = new String("Ambient Biological");
  public static final String SCOPE_AT_CHEMICAL = new String("Ambient Chemical");
  public static final String SCOPE_AT_DEMOGRAPHIC = new String("Ambient Demographic");
  public static final String SCOPE_AT_PHYSICAL = new String("Ambient Physical");
  public static final String SCOPE_AT_INCHEMICAL = new String("Internal Chemical");
  public static final String SCOPE_AT_LOCAL = new String("Local Variables");
  public static final String SCOPE_AT_VARIETYBASED = new String("Species-based Variables");
  public static final String AGENT_SUB_POP = new String("Sub-population Size");
  public static final String AGENT_DEPTH = new String("Depth");
  public static final String AGENT_NO_AGENTS = new String("Number of Agents");
  public static final String AGENT_BEING_INGESTED_ALL = new String("Being Ingested (Total)");
  public static final String AGENT_BEING_INGESTED_BY = new String("Being Ingested By");
  
  public static final String UNIT_STEP = new String("Step");
  public static final String UNIT_HOUR = new String("Hour");
  public static final String UNIT_DAY = new String("Day");
  public static final String UNIT_WEEK = new String("Week");
  
  public static final String BIO_CONC = new String(" concentration in Plankton");
  public static final String SOLUTION = new String(" concentration in Solution");
  public static final String CHEM_ING = new String(" Ingested By ");
  public static final String CHEM_ING_FROM = new String(" Ingested From ");
  public static final String _BY_ = new String(" By ");
  
  public static final String WC_HEAT = new String("Heat Content");
  public static final String WC_LONGITUDE = new String("Longitude");
  public static final String WC_LATITUDE = new String("Latitude");
  public static final String WC_TURBOCLINE = new String("Turbocline");
  public static final String WC_CLOUD_COVER = new String("Cloud Cover");
  public static final String WC_ZENITH_HEIGHT = new String("Zenith Height");
  public static final String WC_EKMAN = new String("Ekman");
  public static final String WC_WIND_SPEED = new String("Wind Speed");
  public static final String WC_HEAT_LOSS = new String("Heat Loss");
  public static final String WC_SUN_LIGHT = new String("Sun Light");
  
  
  public static final String DEF_BIOCONC = new String("bioconc");
  public static final String DEF_SOLN = new String("conc");  
  public static final String DEF_ING_BY = new String("ing");  
  public static final String DEF_POOL = new String("pool");
  public static final String DEF_GAIN = new String("gain");
  public static final String DEF_ING_FROM = new String("ingfrom");
  
  
  public static final String VISIBLE_IRRADIANCE = new String("Visible Irradiance");
  public static final String FULL_IRRADIANCE = new String("Full Irradiance");
  public static final String TEMPERATURE = new String("Temperature");
  public static final String TEMPADJ = new String("Temperature (After Events)");  
  public static final String DENSITY = new String("Density");
  public static final String SALINITY = new String("Salinity");
  
  public static final String CONC_INDIVIDUALS = new String("Concentration of individuals");
  
  //////////////////////////////////////////////////////////////////////////////////////
  // THIS SECTION deals with checking the internal consistency of the output tags...
  
  
  private void addDef(XMLTag tag, String name, long start, long end, String unit, int freq, boolean depth) {
    XMLTag newTag = new XMLTag(name);
    newTag.setAttribute("from",String.valueOf(start));
    newTag.setAttribute("to",String.valueOf(end));
    newTag.setAttribute("unit",unit);
    newTag.setAttribute("freq",String.valueOf(freq));
    newTag.setAttribute("active","true");
    if (depth) {
      newTag.setAttribute("top","0.0");
      int size = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
      newTag.setAttribute("bottom",String.valueOf(size+".0"));
    }
    tag.addTag(newTag);
  }
  
  private boolean addDef(XMLTag tag, String name, long start, long end, String unit, int freq, boolean fix, boolean depth, String msg, StringBuffer error) {
    boolean ok = true;
    if (tag.getTag(name)==null) {
      if (fix) {
        addDef(tag,name,start,end,unit,freq,depth);
        error.append(msg);
      } else ok=false;
    }
    return ok;
  }
  
  public boolean checkDates(XMLTag tag, String niceName, long simStart, long simEnd, StringBuffer errorString, boolean fix) {
    boolean ok = true;
    long start = Long.parseLong(tag.getAttribute("from"));
    long end = Long.parseLong(tag.getAttribute("to"));
    if ((start<simStart) || (start>simEnd)) { 
      if (fix) {
        tag.setAttribute("from",String.valueOf(simStart));
        start = Long.parseLong(tag.getAttribute("from"));
        errorString.append(niceName+" start logging time was invalid\n");
      } else ok = false;
    }
    if (ok) {
      if ((end>simEnd) || (end<simStart)) {
        if (fix) {
          tag.setAttribute("to",String.valueOf(simEnd));
          end = Long.parseLong(tag.getAttribute("to"));          
          errorString.append(niceName+" end logging time invalid\n");
        } else ok = false;
      }
    }
    return ok;
  }
  
  public boolean checkSpeciesStage(XMLTag tag, String niceName, StringBuffer errorString, boolean fix) {
    boolean ok = true;
    String species = tag.getAttribute("species");
    String stage = tag.getAttribute("stage");
    XMLTag speciesTag = theModel.getTag("species").getTagWhere("species","@name",species);
    if (speciesTag==null) {
      if (fix) {
        errorString.append(niceName+" - "+tag.getAttribute("name")+" removed - "+species+" not found\n");
        tag.removeFromParent();
      } else ok = false;
    } else {
      if (ok) {
        XMLTag fg = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
        if (fg.getTagWhere("stage","name",stage)==null) {
          if (fix) {
            errorString.append(niceName+" - "+tag.getAttribute("name")+" removed - "+stage+" not found\n");
            tag.removeFromParent();
          } else ok = false;
        }
      }
    }
    return ok;
  }
  
  public boolean checkAllSpeciesStage(XMLTag[] vars, String niceName, StringBuffer errorString, boolean fix) {
    boolean ok = true;
    int i=0;
    while ((ok) && (i<vars.length))
      ok = checkSpeciesStage(vars[i++],niceName, errorString, fix);
    return ok;
  }
  
  public boolean checkChemical(XMLTag tag, String niceName, StringBuffer errorString, boolean fix) {
    boolean ok = true;
    String chemical = tag.getAttribute("chem");
    if (theModel.getTagWhere("chemical","name",chemical)==null) {
      if (fix) {
        errorString.append(niceName+" - "+tag.getAttribute("name")+" removed - "+chemical+" not found\n");
        tag.removeFromParent();
      } else ok = false;
    }
    if ((tag.getAttribute("chemtype").equals(DEF_BIOCONC)) || (tag.getAttribute("chemtype").equals(DEF_ING_BY))) {
      if (tag.getAttribute("species")!=null) {
        String species = tag.getAttribute("species");
        String stage = tag.getAttribute("stage");
        XMLTag specTag = theModel.getTag("species").getTagWhere("species","@name",species);
        if (specTag==null) {
          if (fix) {
            errorString.append(niceName+" - "+tag.getAttribute("name")+" removed - species not found\n");
            tag.removeFromParent();
          } else ok=false;
        } else {
          XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",specTag.getAttribute("fg"));
          if (fgTag.getTagWhere("stage","name",stage)==null) {
            if (fix) {
              errorString.append(niceName+" - "+tag.getAttribute("name")+" removed - stage not found\n");
              tag.removeFromParent();
            } else ok=false;
          }
        }
      }
    }
    return ok;
  }
  public boolean checkAllChemicals(XMLTag[] vars, String niceName, StringBuffer errorString, boolean fix) {
    boolean ok = true;
    int i=0;
    while ((ok) && (i<vars.length))
      ok = checkChemical(vars[i++],niceName, errorString, fix);
    return ok;
  }
  
  public boolean checkIngestion(XMLTag[] vars, String niceName, StringBuffer errorString, boolean fix) {
    boolean ok=true;
    for (int i=0; i<vars.length; i++) {
      String name = vars[i].getAttribute("name");
      if (name.startsWith("Being Ingested : ")) {
        if (fix) {
          errorString.append(niceName+" - "+name+" upgraded to latest style ingestion\n");
          name = AGENT_BEING_INGESTED_ALL+" : "+name.substring(16);
          vars[i].setAttribute("name",name);
        } else ok=false;
      }
      if ((ok) && (name.startsWith(AGENT_BEING_INGESTED_BY))) {
        String predator = name.substring(OutputDialog2.AGENT_BEING_INGESTED_BY.length()+3);
        final String predatorSpecies = predator.substring(0,predator.indexOf(":")-1);
        predator = predator.substring(predator.indexOf(":")+2);
        final String predatorStage = predator.substring(0,predator.indexOf(":")-1);
        predator = predator.substring(predator.indexOf(":")+2);
        XMLTag speciesTag = theModel.getTag("species").getTagWhere("species","@name",predatorSpecies);
        if (speciesTag==null) {
          if (fix) {
            errorString.append(niceName+" - "+vars[i].getAttribute("name")+" removed - predator species not found\n");
            vars[i].removeFromParent();
          } else {
            ok = false;
            i = vars.length;
          }
        } else {
          XMLTag fgTag = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
          if (fgTag.getTagWhere("stage","name",predatorStage)==null) {
            if (fix) {
              errorString.append(niceName+" - "+vars[i].getAttribute("name")+" removed - predator stage not found\n");
              vars[i].removeFromParent();
            } else {
              ok = false;
              i = vars.length;
            }
          }
        }
      }
    }
    return ok;
  }
  
  public boolean checkDepth(XMLTag tag, String name, boolean fix,StringBuffer error) {
    boolean ok=true;
    double t = Double.parseDouble(tag.getAttribute("top"));
    double b = Double.parseDouble(tag.getAttribute("bottom"));
    int max = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
    if ((t>=max) || (b>=max)) {
      if (fix) {
        if (t>=max) {
          tag.setAttribute("top",String.valueOf(max-1));
          error.append(name+" : top depth corrected\n");
        }
        if (b>=max) {
          tag.setAttribute("bottom",String.valueOf(max-1));
          error.append(name+" : bottom depth corrected\n");
        }
      } else ok=false;
    }
    return ok;
  }
  
  public boolean greenLight(boolean fix) {
    lockEvents++;
    StringBuffer errorMsg = new StringBuffer();
    boolean ok=true;
    startSim = new GregorianCalendar();
    endSim = new GregorianCalendar();
    MiscUtils.findDateLimits(theModel,startSim,endSim);
    long startMillis = startSim.getTimeInMillis();
    long endMillis = endSim.getTimeInMillis();

    if (theModel.getTag("output")==null) {
      if (fix) {
        XMLTag newOutputTag = new XMLTag("output");
        theModel.addTag(newOutputTag);
      } else ok=false;
    }
    if (ok) {
      XMLTag newOutputTag = theModel.getTag("output");
      ok = addDef(newOutputTag,"col",startMillis,endMillis,UNIT_STEP, 1, fix,false, "Added water column\n",errorMsg);
      if (ok) ok = addDef(newOutputTag,"fieldbio",startMillis,endMillis,UNIT_STEP,1,fix,true, "Added biological field data\n",errorMsg);
      if (ok) ok = addDef(newOutputTag,"fieldchem",startMillis,endMillis,UNIT_STEP,1,fix,true, "Added chemical field data\n",errorMsg);
      if (ok) ok = addDef(newOutputTag,"fielddemo",startMillis,endMillis,UNIT_STEP,1,fix,true, "Added demographic field data\n",errorMsg);
      if (ok) ok = addDef(newOutputTag,"fieldphy",startMillis,endMillis,UNIT_STEP,1,fix,true, "Added physical field data\n",errorMsg);
    }
    if (ok) {
      ok = checkDepth(theModel.getTag("output/fieldphy"),"Physical field data",fix,errorMsg);
      if (ok) ok = checkDepth(theModel.getTag("output/fieldbio"),"Biological field data",fix,errorMsg);
      if (ok) ok = checkDepth(theModel.getTag("output/fieldchem"),"Chemical field data",fix,errorMsg);
      if (ok) ok = checkDepth(theModel.getTag("output/fielddemo"),"Demographic field data",fix,errorMsg);
    }
    
    if (ok) {
      XMLTag newOutputTag = theModel.getTag("output");
      if (newOutputTag.getTag("lineage")==null) {
        if (fix) {
          XMLTag linTag = new XMLTag("lineage");
          linTag.setAttribute("from",String.valueOf(startMillis));
          linTag.setAttribute("to",String.valueOf(endMillis));
          linTag.setAttribute("active","false");
          newOutputTag.addTag(linTag);
          errorMsg.append("Added lineage\n");
        } else ok = false;
      }
    }
        
    if (ok) {
      XMLTag newOutputTag = theModel.getTag("output");
      if (newOutputTag.getTag("lifespan")==null) {
        if (fix) {
          XMLTag lsTag = new XMLTag("lifespan");
          lsTag.setAttribute("from",String.valueOf(startMillis));
          lsTag.setAttribute("to",String.valueOf(endMillis));
          lsTag.setAttribute("active","false");
          newOutputTag.addTag(lsTag);
          errorMsg.append("Added lifespan\n");
        } else ok = false;
      }
    }
    
    if (ok) {
      XMLTag newOutputTag = theModel.getTag("output");
      if (newOutputTag.getTag("demography")!=null) {
        if (fix) {
          newOutputTag.getTag("demography").removeFromParent();
        }
      }
    }

    if (ok) {
      XMLTag[] species = theModel.getTag("species").getTags("species");
      XMLTag output = theModel.getTag("output");
      for (int i=0; i<species.length; i++) {
        if (output.getTagWhere("audit","@species",species[i].getAttribute("name"))==null) {
          if (fix) {
            XMLTag speciesLog = new XMLTag("audit");
            speciesLog.setAttribute("species",species[i].getAttribute("name"));
            speciesLog.setAttribute("from",String.valueOf(startMillis));
            speciesLog.setAttribute("to",String.valueOf(endMillis));
            speciesLog.setAttribute("active","true");
            XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",species[i].getAttribute("fg")).getTags("stage");
            StringBuffer stageString = new StringBuffer();
            for (int j=0; j<stages.length-1; j++) {
              stageString.append(stages[j].getValue("name"));
              stageString.append(",");
            }
            stageString.append(stages[stages.length-1].getValue("name"));
            speciesLog.setAttribute("stage",stageString.toString());
            speciesLog.setAttribute("unit",UNIT_STEP);
            speciesLog.setAttribute("freq","1");
            output.addTag(speciesLog);
            errorMsg.append("Created audit trail log for "+species[i].getAttribute("name")+"\n");
          } else {
            ok = false;
            i = species.length;
          }
        }
      }
    }
    
    if (ok) {
      XMLTag outputTag = theModel.getTag("output");
      ok = checkDates(outputTag.getTag("col"), "Column Biology", startMillis, endMillis, errorMsg, fix);
      if (ok) ok = checkDates(outputTag.getTag("fieldbio"), "Field Biology", startMillis, endMillis, errorMsg, fix);      
      if (ok) ok = checkDates(outputTag.getTag("fieldchem"), "Field Chemistry", startMillis, endMillis, errorMsg, fix);
      if (ok) ok = checkDates(outputTag.getTag("fielddemo"), "Field Demography", startMillis, endMillis, errorMsg, fix);      
      if (ok) ok = checkDates(outputTag.getTag("fieldphy"), "Field Physics", startMillis, endMillis, errorMsg, fix);      
      if (ok) ok = checkDates(outputTag.getTag("lineage"), "Lineage", startMillis, endMillis, errorMsg, fix);
      if (ok) ok = checkDates(outputTag.getTag("lifespan"), "Lifespan", startMillis, endMillis, errorMsg, fix);
    }
    
    if (ok) {
      XMLTag outputTag = theModel.getTag("output");
      XMLTag[] audits = outputTag.getTags("audit");
      for (int i=0; i<audits.length; i++) {
        if (theModel.getTag("species").getTagWhere("species","@name",audits[i].getAttribute("species"))==null) {
          if (fix) {
            errorMsg.append("Audit Trail for "+audits[i].getAttribute("species")+" removed - species not found\n");
            audits[i].removeFromParent();
          } else ok = false;
        } else ok = checkDates(audits[i],"Audit Trail for "+audits[i].getAttribute("species"),startMillis, endMillis, errorMsg, fix); 
      }
    
      if (ok) ok = checkAllSpeciesStage(outputTag.getTag("col").getTagsWhere("var","@scope",SCOPE_WC_BIOLOGICAL), "Column Biology",errorMsg,fix);
      if (ok) ok = checkAllSpeciesStage(outputTag.getTag("col").getTagsWhere("var","@scope",SCOPE_WC_DEMOGRAPHIC), "Column Demography", errorMsg, fix);      
      if (ok) ok = checkIngestion(outputTag.getTag("col").getTagsWhere("var","@scope",SCOPE_WC_DEMOGRAPHIC), "Column Demography", errorMsg, fix);
      if (ok) ok = checkAllSpeciesStage(outputTag.getTag("fieldbio").getTags("var"), "Field Biology", errorMsg, fix);      
      if (ok) ok = checkAllSpeciesStage(outputTag.getTag("fielddemo").getTags("var"), "Field Demography", errorMsg, fix);      
      if (ok) ok = checkIngestion(outputTag.getTag("fielddemo").getTags("var"), "Field Demography", errorMsg, fix);      
      if (ok) ok = checkAllChemicals(outputTag.getTag("col").getTagsWhere("var","@scope",SCOPE_WC_CHEMICAL), "Column Chemistry", errorMsg, fix);
      if (ok) ok = checkAllChemicals(outputTag.getTag("fieldchem").getTags("var"), "Field Chemistry", errorMsg, fix);
    }
    if (ok) {
      XMLTag outputTag = theModel.getTag("output");
      XMLTag[] audits = outputTag.getTags("audit");
      for (int i=0; i<audits.length; i++) {
        if (audits[i].getAttribute("species")==null) {
          if (fix) {
            audits[i].removeFromParent();
            errorMsg.append("Removed an old-style audit trail tag - no species specified\n");
          } else {
            ok = false;
            i = audits.length;
          }
        }
      }
      if (ok) {
        audits = outputTag.getTags("audit");
        for (int i=0; i<audits.length; i++) {
          if (theModel.getTag("species").getTagWhere("species","@name",audits[i].getAttribute("species"))==null) {
            if (fix) {
              audits[i].removeFromParent();
              errorMsg.append("Audit trail for "+audits[i].getAttribute("species")+" removed - species not found\n");
            } else {
              ok = false;
              i = audits.length;
            }
          }
        }
      }
        
      if (ok) {
        for (int i=0; i<audits.length; i++) {
          if (ok) {
            String species = audits[i].getAttribute("species");
            XMLTag[] selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_BIOLOGICAL);
            ok = checkAllSpeciesStage(selection,"Audit Trail (Bio) for "+species,errorMsg,fix);
            selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_CHEMICAL);
            if (ok) ok = checkAllChemicals(selection,"Audit Trail (Chem) for "+species,errorMsg,fix);
            if (ok) selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_DEMOGRAPHIC);
            if (ok) ok = checkAllSpeciesStage(selection,"Audit Trail (Demographic) for "+species,errorMsg,fix);
            if (ok) ok = checkIngestion(selection,"Audit Trail (Demographic) for "+species,errorMsg,fix);            
            if (ok) selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_INCHEMICAL);
            if (ok) ok = checkAllChemicals(selection,"Audit Trail (Internal Chem) for "+species,errorMsg,fix);
            if (ok) selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_VARIETYBASED);
            if (ok) ok = checkAllSpeciesStage(selection,"Audit Trail (Ingestion-based) for "+species,errorMsg,fix);
            if (ok) selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_AGENT);
            if (ok) {
              XMLTag speciesTag = theModel.getTag("species").getTagWhere("species","@name",species);
              XMLTag fg = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
              for (int j=0; j<selection.length; j++) {
                String theName = selection[j].getAttribute("name");
                theName = theName.substring(0,theName.indexOf("(")-1);
                boolean found = (theName.equals("c"));
                found = (found) || (theName.equals("z"));
                found = (found) || (fg.getTagWhere("variable","name",theName)!=null);
                if (!found) {
                  if (fix) {
                    errorMsg.append("Audit Trail for "+audits[i].getAttribute("species")+" - removed variable "+theName+" - not found\n");
                    selection[j].removeFromParent();
                  } else {
                    ok = false;
                    j = selection.length;
                  }
                }
              }
            }
            if (ok) selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_LOCAL);
            if (ok) {
              XMLTag speciesTag = theModel.getTag("species").getTagWhere("species","@name",species);
              XMLTag fg = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
              for (int j=0; j<selection.length; j++) {
                String theName = selection[j].getAttribute("name");
                theName = theName.substring(0,theName.indexOf("(")-1);
                if (fg.getTagWhere("local","name",theName)==null) {
                  if (fix) {
                    errorMsg.append("Audit Trail for "+audits[i].getAttribute("species")+" - removed local var "+theName+" - not found\n");
                    selection[j].removeFromParent();
                  } else {
                    ok = false;
                    j = selection.length;
                  }
                }
              }
            }
          
            if (ok) selection = audits[i].getTagsWhere("var","@scope",SCOPE_AT_VARIETYBASED);
            if (ok) {
              XMLTag speciesTag = theModel.getTag("species").getTagWhere("species","@name",species);
              XMLTag fg = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
              for (int j=0; j<selection.length; j++) {
                String theName = selection[j].getAttribute("name");
                theName = theName.substring(0,theName.indexOf("(")-1);
                if ((fg.getTagWhere("varietyvariable","name",theName)==null) && (fg.getTagWhere("varietylocal","name",theName)==null)) {
                  if (fix) {
                    errorMsg.append("Audit Trail for "+audits[i].getAttribute("species")+" - removed variety-basd "+theName+" - not found\n");
                    selection[j].removeFromParent();
                  } else {
                    ok = false;
                    j = selection.length;
                  }
                }
              }
            }
          }
        }
      }
    }
    
    if (fix) {
      fileInfoModel.setModel(theModel);
      initialiseData();
      initialiseTable();
    }
    lockEvents--;
    if (fix) {
      if (errorMsg.length()>5) {
        MessageBox.showMessage(errorMsg.toString(),this);
        vc2.unsaved(true);
      }
    }
    return ok;
  }
  
  ////////////////////////////////////////////////////////////////////////////
  // END OF CHECKING SECTION
  
  
  
  public void showAllVarSpecies() {
    lockEvents++;
    varSpecStageListModel.removeAllElements();
    XMLTag[] theSpecies = theModel.getTag("species").getTags("species");
    for (int i=0; i<theSpecies.length; i++) {
      XMLTag fg = theModel.getTagWhere("functionalgroup","name",theSpecies[i].getAttribute("fg"));
      XMLTag[] stages = fg.getTags("stage");
      for (int j=0; j<stages.length; j++)
        varSpecStageListModel.addElement(theSpecies[i].getAttribute("name")+" : "+stages[j].getValue("name"));          
    }
    lockEvents--;
  }
    
  public void initialiseData() {
    structureListModel.removeAllElements();
    structureListModel.addElement(STRUCT_WATER_COLUMN);
    structureListModel.addElement(STRUCT_FIELD_DATA);
    structureList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    XMLTag[] species = theModel.getTag("species").getTags("species");
    for (int i=0; i<species.length; i++) 
      structureListModel.addElement(STRUCT_AUDIT_TRAIL+" for "+species[i].getAttribute("name"));
  }
    
  public void initialiseTable() {
    int depth = Integer.parseInt(theModel.getTag("initialconditions").getAttribute("size"));
    String[] physicsDepths = MiscUtils.getPhysicalDepths(depth);
    String[] bioDepths = new String[depth];
    String[] freqs = new String[4];
    physicsDepths[0]="0.0";
    bioDepths[0]="0.0";
    for (int i = 1; i < depth; i++) bioDepths[i]=String.valueOf(i)+".0";
  
    freqs[0]=UNIT_STEP;
    freqs[1]=UNIT_HOUR;
    freqs[2]=UNIT_DAY;
    freqs[3]=UNIT_WEEK;
    //freqs[4]="Month";
    //freqs[5]="Year";
    //fileInfo.setRowSelectionAllowed(false);
    fileInfo.setColumnSelectionAllowed(false);
    fileInfo.addMouseListener(eh);
    CellEditorModel cem = new CellEditorModel();    
    fileInfo.setCellEditorModel(cem);
    JComboBox phyCombo = new JComboBox(physicsDepths);
    DefaultCellEditor phyEd = new DefaultCellEditor(phyCombo);
    cem.addEditor(4,5,phyEd);
    cem.addEditor(4,6,phyEd);    
    JComboBox bioCombo = new JComboBox(bioDepths);
    DefaultCellEditor bioEd = new DefaultCellEditor(bioCombo);
    cem.addEditor(3,5,bioEd);
    cem.addEditor(2,5,bioEd);
    cem.addEditor(1,5,bioEd);
    cem.addEditor(3,6,bioEd);
    cem.addEditor(2,6,bioEd);
    cem.addEditor(1,6,bioEd);
    JComboBox freqsCombo = new JComboBox(freqs);
    DefaultCellEditor freqsEd = new DefaultCellEditor(freqsCombo);
    for (int i=0; i<fileInfo.getRowCount(); i++) cem.addEditor(i,4,freqsEd);
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_NAME).setPreferredWidth(140);
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_DATE1).setPreferredWidth(110);    
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_DATE2).setPreferredWidth(110);
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_FREQ).setPreferredWidth(30);    
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_UNIT).setPreferredWidth(40);
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_TOP).setPreferredWidth(50);
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_BOTTOM).setPreferredWidth(50);
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_STAGES).setPreferredWidth(50);
    fileInfo.getColumnModel().getColumn(InfoTableModel.COL_ACTIVE).setPreferredWidth(40); 
    fileInfo.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    
  }
  
  public void initialiseInterface(JDialog parent) {
    dateDialog = new DateDialog(parent,1800);
    stageChooser = new StageChooser(parent);
    setLayout(new BorderLayout());
    JPanel variableChoice = new JPanel(new GridLayout(2,1));
      variableChoice.setBorder(new EtchedBorder());
      JPanel scopePanel = new JPanel(new GridLayout(1,2));
         scopePanel.setBorder(new EtchedBorder());
        JScrollPane structureScroller = new JScrollPane(structureList);
        JScrollPane scopeScroller = new JScrollPane(scopeList);
        structureScroller.setPreferredSize(new Dimension(230,150));
        scopeScroller.setPreferredSize(new Dimension(230,150));
        scopePanel.add(structureScroller);
        scopePanel.add(scopeScroller);
        JPanel scopeContainer = new JPanel(new BorderLayout());
        JLabel scopeTitle = new JLabel("Where to look for variables to log", SwingConstants.CENTER);
        scopeContainer.add(scopeTitle,BorderLayout.NORTH);
        scopeContainer.add(scopePanel,BorderLayout.SOUTH);
        variableChoice.add(scopeContainer);
    
      JPanel variablePanel = new JPanel(new GridLayout(1,2));
        variablePanel.setBorder(new EtchedBorder());
        JScrollPane varSpecStageScroller = new JScrollPane(varSpecStageList);
        JScrollPane variableScroller = new JScrollPane(variableList);
        varSpecStageScroller.setPreferredSize(new Dimension(230,150));
        variableScroller.setPreferredSize(new Dimension(230,150));
        variablePanel.add(variableScroller);
        variablePanel.add(varSpecStageScroller);
        JPanel variableContainer = new JPanel(new BorderLayout());
        JLabel varTitle = new JLabel("Choose the variable to log", SwingConstants.CENTER);
        variableContainer.add(varTitle,BorderLayout.CENTER);
        variableContainer.add(variablePanel,BorderLayout.SOUTH);
        variableChoice.add(variableContainer);
    add(variableChoice,BorderLayout.WEST);
    
    JPanel chosenVariables = new JPanel(new BorderLayout());
      chosenVariables.add(listTitle,BorderLayout.NORTH);
      listTitle.setText("Variables chosen");
      JScrollPane choiceScroller = new JScrollPane(chosenVarsList);
      choiceScroller.setPreferredSize(new Dimension(300,250));
      chosenVariables.add(choiceScroller,BorderLayout.CENTER);
      
      JPanel addRemovePanel = new JPanel(new FlowLayout());
      addRemovePanel.add(removeEntry);
      addRemovePanel.add(addEntry);
      chosenVariables.add(addRemovePanel,BorderLayout.SOUTH);
      chosenVariables.setBorder(new EtchedBorder());
    add(chosenVariables,BorderLayout.EAST);
    
    JPanel advancedOptions = new JPanel(new BorderLayout());
    JScrollPane infoScroller = new JScrollPane(fileInfo);
    JPanel westPad = new JPanel();
    westPad.setPreferredSize(new Dimension(70,200));
    JPanel eastPad = new JPanel();
    eastPad.setPreferredSize(new Dimension(70,200));
    infoScroller.setPreferredSize(new Dimension(610,200));
    JLabel infoTitle = new JLabel("Data Windowing Settings for each output file",SwingConstants.CENTER);
    advancedOptions.add(infoTitle,BorderLayout.NORTH);
    advancedOptions.add(infoScroller,BorderLayout.CENTER);
    advancedOptions.add(westPad,BorderLayout.WEST);
    advancedOptions.add(eastPad,BorderLayout.EAST);
    JPanel fileButtonsPanel = new JPanel(new FlowLayout());
    fileButtonsPanel.add(auditTrails);
    fileButtonsPanel.add(applyDateToAll);
    fileButtonsPanel.add(applyDepthToAll);
    advancedOptions.add(fileButtonsPanel,BorderLayout.SOUTH);
    add(advancedOptions,BorderLayout.SOUTH);
    
    // Sort out events etc.
    removeEntry.setEnabled(false);
    addEntry.setEnabled(false);  
    
    scopeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
    applyDateToAll.setEnabled(false);
    applyDepthToAll.setEnabled(false);
    structureList.addListSelectionListener(eh);
    structureList.addKeyListener(eh);
    scopeList.addListSelectionListener(eh);
    scopeList.addKeyListener(eh);
    varSpecStageList.addListSelectionListener(eh);
    varSpecStageList.addKeyListener(eh);
    removeEntry.addActionListener(eh);
    addEntry.addActionListener(eh);
    chosenVarsList.addListSelectionListener(eh);
    variableList.addListSelectionListener(eh);
    variableList.addKeyListener(eh);
    auditTrails.addActionListener(eh);
    applyDateToAll.addActionListener(eh);
    applyDepthToAll.addActionListener(eh);
      
  }
  
  public OutputDialog2(JDialog parent, XMLTag _theModel) {
    vc2 = (VEWController2) parent;
    theModel = _theModel;
    initialiseInterface(parent);
  }
 
  public void structureChanged() {
    String structure = structureList.getSelectedValue().toString();
    String oldValue = "";
    if (scopeList.getSelectedValue()!=null) oldValue=scopeList.getSelectedValue().toString();
    scopeListModel.clear();
     if ((structure.equals(STRUCT_WATER_COLUMN)) || (structure.equals(STRUCT_FIELD_DATA))) {
      scopeListModel.addElement(SCOPE_WC_BIOLOGICAL);
      scopeListModel.addElement(SCOPE_WC_CHEMICAL);
      scopeListModel.addElement(SCOPE_WC_DEMOGRAPHIC);
      scopeListModel.addElement(SCOPE_WC_PHYSICAL);
    } else if (structure.startsWith(STRUCT_AUDIT_TRAIL))  {
      scopeListModel.addElement(SCOPE_AT_AGENT);
      scopeListModel.addElement(SCOPE_AT_BIOLOGICAL);
      scopeListModel.addElement(SCOPE_AT_CHEMICAL);
      scopeListModel.addElement(SCOPE_AT_DEMOGRAPHIC);
      scopeListModel.addElement(SCOPE_AT_PHYSICAL);      
      scopeListModel.addElement(SCOPE_AT_INCHEMICAL);
      scopeListModel.addElement(SCOPE_AT_LOCAL);
      scopeListModel.addElement(SCOPE_AT_VARIETYBASED);      
    }
    StringTools.setListItem(scopeList,oldValue);
    if (scopeList.getSelectedIndex()==-1) scopeList.setSelectedIndex(0);
    updateVariableList();
    updateChooseDisplay();
  }
  

  public XMLTag getFGForSpecies(String species) {
    return theModel.getTagWhere("functionalgroup","name",theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("fg"));
  }
  
  public XMLTag[] getStagesForSpecies(String species) {
    return getFGForSpecies(species).getTags("stage");
  }
  
  public int getSpeciesID(String species) {
    return Integer.parseInt(theModel.getTag("species").getTagWhere("species","@name",species).getAttribute("id"));
  }

  public void checkVarSpecAvailability() {
    final String scope = (scopeList.getSelectedValue()!=null)?scopeList.getSelectedValue().toString():"";
    final String structure = (structureList.getSelectedValue()!=null)?structureList.getSelectedValue().toString():"";
    boolean enabledVarSpecs = false;
    if ((structure.startsWith(STRUCT_AUDIT_TRAIL)) && ((scope.equals(SCOPE_AT_DEMOGRAPHIC)) || (scope.equals(SCOPE_AT_BIOLOGICAL)))) enabledVarSpecs=true;
    else if ((structure.equals(STRUCT_WATER_COLUMN)) && ((scope.equals(SCOPE_WC_DEMOGRAPHIC) || (scope.equals(SCOPE_WC_BIOLOGICAL))))) enabledVarSpecs=true;
    else if ((structure.equals(STRUCT_FIELD_DATA)) && ((scope.equals(SCOPE_FD_DEMOGRAPHIC)) || (scope.equals(SCOPE_FD_BIOLOGICAL)))) enabledVarSpecs=true;
    else if ((structure.startsWith(STRUCT_AUDIT_TRAIL) && (scope.equals(SCOPE_AT_VARIETYBASED)) && (variableList.getSelectedValues().length>0))) enabledVarSpecs=true;
    
    
    boolean enableSpeciesChoice = false;
    if (((structure.equals(STRUCT_WATER_COLUMN)) && ((scope.equals(SCOPE_WC_CHEMICAL)))) 
       || ((structure.equals(STRUCT_FIELD_DATA)) && ((scope.equals(SCOPE_FD_CHEMICAL))))
       || ((structure.startsWith(STRUCT_AUDIT_TRAIL)) && ((scope.equals(SCOPE_AT_CHEMICAL))))) {
     Object[] selectedVars = variableList.getSelectedValues();
     for (int i=0; i<selectedVars.length; i++) {
       if ((selectedVars[i].toString().endsWith(BIO_CONC)) || (selectedVars[i].toString().endsWith(CHEM_ING))
             || ((selectedVars[i].toString().endsWith(_BY_)) && (selectedVars[i].toString().indexOf(CHEM_ING_FROM)>=0))) {
         enableSpeciesChoice = true;
         i=selectedVars.length;
       } 
     }
     if (enableSpeciesChoice) enabledVarSpecs=true;
    }
    varSpecStageList.setEnabled(enabledVarSpecs);
  }
  
  public void addChemicalEntries() {
    XMLTag[] newChems = XMLTag.sortTags(theModel.getTags("chemical"),"name");
    for (int i=0; i<newChems.length; i++) {
      final String chemName = newChems[i].getValue("name");
      variableListModel.addElement(chemName+SOLUTION);    
      variableListModel.addElement(chemName+BIO_CONC);
      variableListModel.addElement(chemName+CHEM_ING);
      XMLTag[] theSpecies = theModel.getTag("species").getTags("species");
      for (int j=0; j<theSpecies.length; j++) {
        XMLTag fg = theModel.getTagWhere("functionalgroup","name",theSpecies[j].getAttribute("fg"));
        XMLTag[] stages = fg.getTags("stage");
        for (int k=0; k<stages.length; k++) {
          String title = chemName+CHEM_ING_FROM+theSpecies[j].getAttribute("name")+" : "+stages[k].getValue("name")+_BY_;
          variableListModel.addElement(title);
        }
      }
    }        
  }
  
  
  public void updateVariableList() {
    Object[] variables = variableList.getSelectedValues();
    variableListModel.clear();
    final String scope = (scopeList.getSelectedValue()!=null)?scopeList.getSelectedValue().toString():"";
    final String structure = (structureList.getSelectedValue()!=null)?structureList.getSelectedValue().toString():"";
    if (structure.equals(STRUCT_WATER_COLUMN)) {
      if (scope.equals(SCOPE_WC_BIOLOGICAL)) {
        variableListModel.addElement(CONC_INDIVIDUALS);
      } else if (scope.equals(SCOPE_WC_CHEMICAL)) {
        addChemicalEntries();
      } else if (scope.equals(SCOPE_WC_DEMOGRAPHIC)) {
        addDeathReasons();
      } else if (scope.equals(SCOPE_WC_PHYSICAL)) {
        variableListModel.addElement(WC_CLOUD_COVER);
        variableListModel.addElement(WC_EKMAN);
        variableListModel.addElement(WC_HEAT);
        variableListModel.addElement(WC_HEAT_LOSS);
        variableListModel.addElement(WC_LATITUDE);
        variableListModel.addElement(WC_LONGITUDE);
        variableListModel.addElement(WC_SUN_LIGHT);
        variableListModel.addElement(WC_TURBOCLINE);
        variableListModel.addElement(WC_WIND_SPEED);
        variableListModel.addElement(WC_ZENITH_HEIGHT);
      }
      
    } else if (structure.equals(STRUCT_FIELD_DATA)) {
      if (scope.equals(SCOPE_FD_BIOLOGICAL)) {
        variableListModel.addElement(CONC_INDIVIDUALS);
       } else if (scope.equals(SCOPE_FD_CHEMICAL)) {
        addChemicalEntries();
      } else if (scope.equals(SCOPE_FD_DEMOGRAPHIC)) {
        addDeathReasons();
      } else if (scope.equals(SCOPE_FD_PHYSICAL)) {
        variableListModel.addElement(DENSITY);
        variableListModel.addElement(FULL_IRRADIANCE);
        variableListModel.addElement(SALINITY);
        variableListModel.addElement(TEMPERATURE);
        variableListModel.addElement(TEMPADJ);
        variableListModel.addElement(VISIBLE_IRRADIANCE);
      }
    } else if (structure.startsWith(STRUCT_AUDIT_TRAIL)) {
      XMLTag species = theModel.getTag("species").getTags("species")[structureList.getSelectedIndex()-2];
      XMLTag fg = theModel.getTagWhere("functionalgroup","name",species.getAttribute("fg"));
      if (scope.equals(SCOPE_AT_AGENT)) {
        XMLTag[] vars = fg.getTags("variable");
        XMLTag allVars = new XMLTag("temp");
        for (int i=0; i<vars.length; i++) allVars.addTag((XMLTag)vars[i].clone());
        XMLTag cVar = new XMLTag("variable");
        cVar.setValue("name","c");
        cVar.setValue("desc",AGENT_SUB_POP);
        allVars.addTag(cVar);
        XMLTag zVar = new XMLTag("variable");
        zVar.setValue("name","z");
        zVar.setValue("desc",AGENT_DEPTH);
        allVars.addTag(zVar);
        XMLTag.sortTagList(allVars,"variable","name");
        vars = allVars.getTags("variable");
        for (int i=0; i<vars.length; i++) variableListModel.addElement(vars[i].getValue("name")+" ("+vars[i].getValue("desc")+")");
        allVars=null;
      
      } else if (scope.equals(SCOPE_AT_INCHEMICAL)) {
        XMLTag[] chems = XMLTag.sortTags(theModel.getTags("chemical"),"name");
        for (int i=0; i<chems.length; i++) {
          variableListModel.addElement(chems[i].getValue("name")+" gained (ingest/uptake)"); 
          variableListModel.addElement(chems[i].getValue("name")+" pool");
        }
        
      } else if (scope.equals(SCOPE_AT_CHEMICAL)) {
        addChemicalEntries();
    
      } else if (scope.equals(SCOPE_AT_BIOLOGICAL)) {
        variableListModel.addElement("Concentration of individuals");
     
      } else if (scope.equals(SCOPE_AT_PHYSICAL)) {
        variableListModel.addElement(DENSITY);
        variableListModel.addElement(FULL_IRRADIANCE);
        variableListModel.addElement(SALINITY);
        variableListModel.addElement(TEMPERATURE);
        variableListModel.addElement(TEMPADJ);        
        variableListModel.addElement(VISIBLE_IRRADIANCE);

      } else if (scope.equals(SCOPE_AT_DEMOGRAPHIC)) {
        addDeathReasons();
     
      } else if (scope.equals(SCOPE_AT_LOCAL)) {
        XMLTag[] locals = fg.getTags("local");
        locals = XMLTag.sortTags(locals,"name");
        for (int i=0; i<locals.length; i++)
          variableListModel.addElement(locals[i].getValue("name")+" ("+locals[i].getValue("desc")+")");
     
      } else if (scope.equals(SCOPE_AT_VARIETYBASED)) {
        int countVBs = fg.getTags("varietyvariable").length+fg.getTags("varietylocal").length;
        XMLTag[] allVBs = new XMLTag[countVBs];
        countVBs=0;
        for (int i=0; i<fg.getTags("varietyvariable").length; i++)
          allVBs[countVBs++]=fg.getTags("varietyvariable")[i];
        for (int i=0; i<fg.getTags("varietylocal").length; i++)
          allVBs[countVBs++]=fg.getTags("varietylocal")[i];
        allVBs = XMLTag.sortTags(allVBs,"name");
        for (int i=0; i<allVBs.length; i++)
          variableListModel.addElement(allVBs[i].getValue("name")+" ("+allVBs[i].getValue("desc")+")");
          
      }
    }
    
    ArrayList selections = new ArrayList();
    for (int i=0; i<variableListModel.size(); i++) {
      int j=0;
      while (j<variables.length) {
        if (variables[j].toString().equals(variableListModel.get(i).toString())) {
          selections.add(new Integer(i));
          j=variables.length+1;
        } else j++;
      }
    }
    int[] sel = new int[selections.size()];
    for (int i=0; i<sel.length; i++) sel[i]=((Integer) selections.get(i)).intValue();
    variableList.setSelectedIndices(sel);
    checkVarSpecAvailability();
  }
  
  /* Helper functions for demographic logging */
  
  private boolean checkDemographicValidity(String varName, String specStage) {
    if ((varName.equals(AGENT_BEING_INGESTED_ALL)) || (varName.equals(AGENT_NO_AGENTS))
        || (varName.startsWith(AGENT_BEING_INGESTED_BY))) return true;
    else {
      String theSpecies = specStage.substring(0,specStage.indexOf(":")-1);
      String theStage = specStage.substring(specStage.indexOf(":")+2);
      XMLTag speciesTag = theModel.getTag("species").getTagWhere("species","@name",theSpecies);
      XMLTag fg = theModel.getTagWhere("functionalgroup","name",speciesTag.getAttribute("fg"));
      XMLTag[] funcs = fg.getTags("function");
      boolean found=false;
      for (int i=0; i<funcs.length; i++) {
        XMLTag[] stageCalls = funcs[i].getTags("calledin");
        boolean foundCall=false;
        for (int j=0; j<stageCalls.length; j++) { 
          if (stageCalls[j].getValue().equals(theStage)) {
            foundCall=true;
            j = stageCalls.length;
          }
        }
        if (foundCall) {
          XMLTag[] eq = funcs[i].getTags("equation");
          for (int j=0; j<eq.length; j++) {
            String theEq = eq[j].getValue("eq");
            if (eq[j].getValue("name").equals(varName) && (theEq.indexOf("change{\\stage{")>=0)) {
              found=true;
              j=eq.length;
              i=funcs.length;
            }
          }
        }
      }
      return found;
    }

  }
  
  
  
  private void getDeathsFromFunction(XMLTag theFunction, ArrayList theReasons, Object[] stages) {
    XMLTag[] eqs = theFunction.getTags("equation");
    for (int eqNo=0; eqNo<eqs.length; eqNo++) {
      XMLTag eq = eqs[eqNo];
      String eqName = eq.getValue("name");
      String eqString = eq.getValue("eq");
      if (eqString.indexOf("change{\\stage{")>=0) {
        XMLTag[] calledin = theFunction.getTags("calledin");
        boolean found = false;
        int i=0;
        while ((!found) && (i<stages.length)) {
          int j=0;
          while (j<calledin.length) {
            if (stages[i].toString().equals(calledin[j].getValue())) {
              found = true;
              theReasons.add(eqName);
            }
            j++;
          }
          i++;
        }
      }
    }
  }
  
  private void addDeathReasons() {
    if (varSpecStageList.getSelectedIndices().length>=1) {
      ArrayList deathReasons = new ArrayList();      
      Object[] specStages = varSpecStageList.getSelectedValues();
      for (int i=0; i<specStages.length; i++) {
        String theSpeciesStage = specStages[i].toString();
        String theSpecies = theSpeciesStage.substring(0,theSpeciesStage.indexOf(":")-1);
        String theStage = theSpeciesStage.substring(theSpeciesStage.indexOf(":")+2);
        variableListModel.removeAllElements();
        variableListModel.addElement(AGENT_NO_AGENTS);
        XMLTag fg = theModel.getTagWhere("functionalgroup","name",theModel.getTag("species").getTagWhere("species","@name",theSpecies).getAttribute("fg")); 
        XMLTag[] funcs = fg.getTags("function");
        Object[] stages = new Object[1];
        stages[0]=theStage;
        for (int j=0; j<funcs.length; j++) getDeathsFromFunction(funcs[j],deathReasons, stages);
        for (int j=0; j<stages.length; j++) {
          variableListModel.addElement(AGENT_BEING_INGESTED_ALL);
          // This could be more intelligent - work it out from foodsets.
          // For now, just allow ingestion by anything.
          for (int k=0; k<varSpecStageListModel.getSize(); k++) {
            variableListModel.addElement(AGENT_BEING_INGESTED_BY+" : "+varSpecStageListModel.getElementAt(k).toString());
          }
        }
      }
      int i=0;
      while (i<deathReasons.size()) {
        int j=i+1;
        while (j<deathReasons.size()) {
          if (deathReasons.get(i).toString().equals(deathReasons.get(j).toString())) {
            deathReasons.remove(j);
          } else j++;
        }
        i++;
      }
      for (int j=0; j<deathReasons.size(); j++) {
        variableListModel.addElement(deathReasons.get(j).toString());
      }
    }
  }
  
  public void checkAddEntryButton() {
    boolean fgOk=((!varSpecStageList.isEnabled()) || ((varSpecStageList.isEnabled()) && (varSpecStageList.getSelectedIndices().length>0)));
    final String scope = scopeList.getSelectedValue().toString();
    if ((scope.equals(SCOPE_FD_CHEMICAL)) || (scope.equals(SCOPE_AT_CHEMICAL))) fgOk=true; 
    addEntry.setEnabled((fgOk) && (variableList.getSelectedIndices().length>0));
  }
  
  public XMLTag getCurrentTag() {
    XMLTag tag = null;
    String structure = structureList.getSelectedValue().toString();
    String scope = scopeList.getSelectedValue().toString();
    if (structure.equals(STRUCT_WATER_COLUMN)) tag = theModel.getTag("output/col");
      
    else if (structure.equals(STRUCT_FIELD_DATA)) {
      if (scope.equals(SCOPE_FD_BIOLOGICAL)) tag = theModel.getTag("output/fieldbio");        
      else if (scope.equals(SCOPE_FD_CHEMICAL)) tag = theModel.getTag("output/fieldchem");        
      else if (scope.equals(SCOPE_FD_DEMOGRAPHIC)) tag = theModel.getTag("output/fielddemo");        
      else if (scope.equals(SCOPE_FD_PHYSICAL)) tag = theModel.getTag("output/fieldphy");        
       
    } else if (structure.startsWith(STRUCT_AUDIT_TRAIL))  {
      int i = structureList.getSelectedIndex()-2;
      tag = theModel.getTag("output").getTagWhere("audit","@species",theModel.getTag("species").getTags("species")[i].getAttribute("name"));
    }
    return tag;
  }
  
  public void updateChooseDisplay() {
    
    if ((structureList.getSelectedValue()!=null) && (scopeList.getSelectedValue()!=null)) {
      XMLTag[] tags = new XMLTag[0];  
      String structure = structureList.getSelectedValue().toString();
      String scope = scopeList.getSelectedValue().toString();
      if (structure.equals(STRUCT_WATER_COLUMN))  {
        if (scope.equals(SCOPE_WC_BIOLOGICAL)) {
          listTitle.setText("Water Column Biological Data");
          tags = theModel.getTag("output/col").getTagsWhere("var","@scope",SCOPE_WC_BIOLOGICAL);
          showAllVarSpecies();
        } else if (scope.equals(SCOPE_WC_CHEMICAL)) {
          listTitle.setText("Water Column Chemical Data");
          tags = theModel.getTag("output/col").getTagsWhere("var","@scope",SCOPE_WC_CHEMICAL);        
        } else if (scope.equals(SCOPE_WC_DEMOGRAPHIC)) {
          listTitle.setText("Water Column Demographic Data");        
          tags = theModel.getTag("output/col").getTagsWhere("var","@scope",SCOPE_WC_DEMOGRAPHIC); 
          showAllVarSpecies();          
        } else if (scope.equals(SCOPE_WC_PHYSICAL)) {
          listTitle.setText("Water Column Physical Data");        
          tags = theModel.getTag("output/col").getTagsWhere("var","@scope",SCOPE_WC_PHYSICAL);        
        }
      } else if (structure.equals(STRUCT_FIELD_DATA)) {
        if (scope.equals(SCOPE_FD_BIOLOGICAL)) {
          listTitle.setText("Biological Field Data");
          tags = theModel.getTags("output/fieldbio/var"); 
          showAllVarSpecies();          
        } else if (scope.equals(SCOPE_FD_CHEMICAL)) {
          listTitle.setText("Chemical Field Data");
          tags = theModel.getTags("output/fieldchem/var");        
        } else if (scope.equals(SCOPE_FD_DEMOGRAPHIC)) {
          listTitle.setText("Demographic Field Data");
          tags = theModel.getTags("output/fielddemo/var");  
          showAllVarSpecies();          
        } else if (scope.equals(SCOPE_FD_PHYSICAL)) {
          listTitle.setText("Physical Field Data");
          tags = theModel.getTags("output/fieldphy/var");        
        } 
      } else if (structure.startsWith(STRUCT_AUDIT_TRAIL))  {
        int i = structureList.getSelectedIndex()-2;
        XMLTag species = theModel.getTag("species").getTags("species")[i];
        XMLTag audit = theModel.getTag("output").getTagWhere("audit","@species",species.getAttribute("name"));
        if (scope.equals(SCOPE_AT_AGENT)) {
          listTitle.setText(species.getAttribute("name")+" agent properties");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_AGENT);        
        } else if (scope.equals(SCOPE_AT_BIOLOGICAL)) {
          listTitle.setText(species.getAttribute("name")+" ambient biological properties");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_BIOLOGICAL);   
          showAllVarSpecies();            
        } else if (scope.equals(SCOPE_AT_CHEMICAL)) {
          listTitle.setText(species.getAttribute("name")+" ambient chemical properties");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_CHEMICAL);
          showAllVarSpecies();
        } else if (scope.equals(SCOPE_AT_DEMOGRAPHIC)) {
          listTitle.setText(species.getAttribute("name")+" ambient demographic properties");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_DEMOGRAPHIC);
          showAllVarSpecies();            
        } else if (scope.equals(SCOPE_AT_INCHEMICAL)) {
          listTitle.setText(species.getAttribute("name")+" internal chemical properties");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_INCHEMICAL);        
        } else if (scope.equals(SCOPE_AT_LOCAL)) {
          listTitle.setText(species.getAttribute("name")+" local variables");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_LOCAL);        
        } else if (scope.equals(SCOPE_AT_PHYSICAL)) {
          listTitle.setText(species.getAttribute("name")+" ambient physical properties");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_PHYSICAL);        
        } else if (scope.equals(SCOPE_AT_VARIETYBASED)) {
          listTitle.setText(species.getAttribute("name")+" predation-based properties");
          tags = audit.getTagsWhere("var","@scope",SCOPE_AT_VARIETYBASED);
          showAllVarSpecies();
        }
      }
      chosenVarsListModel.clear();
      tags = XMLTag.sortTags(tags,"@name");
      for (int i=0; i<tags.length; i++) {
        chosenVarsListModel.addElement(tags[i].getAttribute("name"));
      }
    }
  }
 
  class EventHandler implements ListSelectionListener, ActionListener, MouseListener, KeyListener {
    
    public EventHandler() {}

    public void valueChanged(ListSelectionEvent lse) {
      if ((lse.getSource()==structureList) && (lockEvents==0) && (lse.getValueIsAdjusting())) {
        structureChanged();
        checkAddEntryButton();
      } else if ((lse.getSource()==scopeList) && (lockEvents==0) && (lse.getValueIsAdjusting())) {
        updateChooseDisplay(); 
        checkAddEntryButton();  
        updateVariableList();
      } else if ((lse.getSource()==varSpecStageList) && (lockEvents==0) && (lse.getValueIsAdjusting())) {
        updateVariableList();
        checkAddEntryButton(); 
      } else if ((lse.getSource()==variableList) && (lockEvents==0) && (lse.getValueIsAdjusting())) {
        checkVarSpecAvailability();
        checkAddEntryButton();
                
      } else if ((lse.getSource()==chosenVarsList) && (lse.getValueIsAdjusting())) {
        removeEntry.setEnabled(chosenVarsList.getSelectedIndices().length>0);
      }
    }
   
    public void actionPerformed(ActionEvent e) {
      if (e.getSource()==removeEntry) {
        lockEvents++;
        Object[] selection = chosenVarsList.getSelectedValues();
        for (int i=selection.length-1; i>=0; i--) {
          XMLTag targetTag = getCurrentTag().getTagWhere("var","@name",selection[i].toString());
          targetTag.removeFromParent();
          chosenVarsListModel.removeElement(selection[i]);
        }
        chosenVarsList.clearSelection();
        removeEntry.setEnabled(false);
        vc2.unsaved(false);
        lockEvents--;
      
      } else if (e.getSource()==addEntry) {
        lockEvents++;
        String structure = structureList.getSelectedValue().toString();
        String scope = scopeList.getSelectedValue().toString();
        Object[] varNames = variableList.getSelectedValues();
        Object[] varSpecStages = varSpecStageList.getSelectedValues();
        if (!varSpecStageList.isEnabled()) varSpecStages = new Object[0];
        XMLTag currentTag = getCurrentTag();
        
        for (int i=0; i<varNames.length; i++) {
          String varName = varNames[i].toString();
          if ((scope.equals(SCOPE_WC_DEMOGRAPHIC)) || (scope.equals(SCOPE_FD_DEMOGRAPHIC)) || (scope.equals(SCOPE_AT_DEMOGRAPHIC))) {
            for (int j=0; j<varSpecStages.length; j++) {
              if (checkDemographicValidity(varNames[i].toString(),varSpecStages[j].toString())) {
                XMLTag newVar= new XMLTag("var");
                newVar.setAttribute("name",varName+ " : "+varSpecStages[j].toString());
                newVar.setAttribute("scope", scope);
                newVar.setAttribute("structure", structure);
                String specStage = varSpecStages[j].toString();
                String spec = specStage.substring(0,specStage.indexOf(":")-1).trim();
                String stage = specStage.substring(specStage.indexOf(":")+2).trim();
                newVar.setAttribute("stage", stage);
                newVar.setAttribute("species", spec);
                currentTag.addTag(newVar);
              }
            }
          } else if ((scope.equals(SCOPE_WC_BIOLOGICAL)) || (scope.equals(SCOPE_FD_BIOLOGICAL)) || (scope.equals(SCOPE_AT_BIOLOGICAL))) {
            for (int j=0; j<varSpecStages.length; j++) {
              XMLTag newVar= new XMLTag("var");
              newVar.setAttribute("name",varName+ " : "+varSpecStages[j].toString());
              newVar.setAttribute("scope", scope);
              newVar.setAttribute("structure", structure);
              String specStage = varSpecStages[j].toString();
              String spec = specStage.substring(0,specStage.indexOf(":")-1).trim();
              String stage = specStage.substring(specStage.indexOf(":")+2).trim();
              newVar.setAttribute("stage", stage);
              newVar.setAttribute("species", spec);
              currentTag.addTag(newVar);
            }
          } else if ((scope.equals(SCOPE_WC_CHEMICAL)) || (scope.equals(SCOPE_FD_CHEMICAL))
                    || (scope.equals(SCOPE_AT_CHEMICAL))) {
            if ((varName.endsWith(BIO_CONC)) || (varName.endsWith(CHEM_ING))) {
              if (varSpecStages.length>0) {
                for (int j=0; j<varSpecStages.length; j++) {
                  XMLTag newVar = new XMLTag("var");
                  String chemName = "";
                  newVar.setAttribute("scope", scope);
                  newVar.setAttribute("structure", structure);
                  if (varName.endsWith(BIO_CONC)) newVar.setAttribute("chemtype",DEF_BIOCONC);
                  else if (varName.endsWith(CHEM_ING)) newVar.setAttribute("chemtype",DEF_ING_BY);
                  String specStage = varSpecStages[j].toString();
                  String spec = specStage.substring(0,specStage.indexOf(":")-1).trim();
                  String stage = specStage.substring(specStage.indexOf(":")+2).trim();
                  newVar.setAttribute("stage", stage);
                  newVar.setAttribute("species", spec);
                  if (varName.endsWith(SOLUTION)) { 
                    newVar.setAttribute("chemtype",DEF_SOLN);
                    chemName = varName.substring(0,varName.length()-SOLUTION.length());
                    newVar.setAttribute("name",chemName+" in solution");                    
                  } else if (varName.endsWith(BIO_CONC)) { 
                    newVar.setAttribute("chemtype",DEF_BIOCONC);
                    chemName = varName.substring(0,varName.length()-BIO_CONC.length());
                    newVar.setAttribute("name",chemName+" in "+spec+" : "+stage);                    
                  } else if (varName.indexOf(CHEM_ING)>0) { 
                    newVar.setAttribute("chemtype",DEF_ING_BY);
                    chemName = varName.substring(0,varName.length()-CHEM_ING.length());
                    newVar.setAttribute("name",chemName+" ingested by "+spec+" : "+stage);                    
                  }

                  newVar.setAttribute("chem",chemName);
                  currentTag.addTag(newVar);                  
                }
              } else {
                XMLTag newVar = new XMLTag("var");
                String chemName="";
                if (varName.endsWith(SOLUTION)) { 
                  newVar.setAttribute("chemtype",DEF_SOLN);
                  chemName = varName.substring(0,varName.length()-SOLUTION.length());
                  newVar.setAttribute("name",chemName+" in solution");
                } else if (varName.endsWith(BIO_CONC)) { 
                  newVar.setAttribute("chemtype",DEF_BIOCONC);
                  chemName = varName.substring(0,varName.length()-BIO_CONC.length());
                  newVar.setAttribute("name",chemName+" in all plankton");
                } else if (varName.endsWith(CHEM_ING)) { 
                  newVar.setAttribute("chemtype",DEF_ING_BY);
                  chemName = varName.substring(0,varName.length()-CHEM_ING.length());
                  newVar.setAttribute("name",chemName+" ingested by all plankton");

                }

                newVar.setAttribute("scope", scope);
                newVar.setAttribute("structure", structure);
                
                newVar.setAttribute("chem",chemName);
                currentTag.addTag(newVar);                  
              }
            } else if (varNames[i].toString().endsWith(SOLUTION)) {
              XMLTag newVar = new XMLTag("var");
              newVar.setAttribute("name",varName);
              newVar.setAttribute("scope", scope);
              newVar.setAttribute("structure", structure);
              newVar.setAttribute("chemtype",DEF_SOLN);
              String theChemical = varName.substring(0,varName.length()-SOLUTION.length());
              newVar.setAttribute("chem",theChemical);
              currentTag.addTag(newVar);
            
            } else if ((varNames[i].toString().endsWith(_BY_)) && (varName.indexOf(CHEM_ING_FROM)>=0)) {
              String _var = new String(varName);
              final String chemName = _var.substring(0,_var.indexOf(CHEM_ING_FROM));
              _var = _var.substring(chemName.length()+CHEM_ING_FROM.length());
              final String fromSpeciesName = _var.substring(0,_var.indexOf(":")-1);
              _var = _var.substring(_var.indexOf(":")+2);
              final String fromStageName = _var.substring(0,_var.indexOf(_BY_));

              if (varSpecStages.length>0) {
                for (int j=0; j<varSpecStages.length; j++) {
                  XMLTag newVar = new XMLTag("var");
                  String ingestor = varSpecStages[j].toString();
                  newVar.setAttribute("name",varName+ingestor);
                  newVar.setAttribute("scope", scope);
                  newVar.setAttribute("structure", structure);
                  newVar.setAttribute("chemtype",DEF_ING_FROM);
                  newVar.setAttribute("chem",chemName);
                  newVar.setAttribute("fromspecies",fromSpeciesName);
                  newVar.setAttribute("fromstage",fromStageName);
                 
                  final String ingSpecies = ingestor.substring(0,ingestor.indexOf(":")-1);
                  ingestor = ingestor.substring(ingestor.indexOf(":")+2);
                  newVar.setAttribute("byspecies",ingSpecies);
                  newVar.setAttribute("bystage",ingestor);
                  currentTag.addTag(newVar);
                }
              } else {
                XMLTag newVar = new XMLTag("var");
                newVar.setAttribute("name",varName+" all");
                newVar.setAttribute("scope", scope);
                newVar.setAttribute("structure", structure);
                newVar.setAttribute("chemtype",DEF_ING_FROM);
                newVar.setAttribute("chem",chemName);
                newVar.setAttribute("fromspecies",fromSpeciesName);
                newVar.setAttribute("fromstage",fromStageName);
                newVar.setAttribute("byall","true");
                currentTag.addTag(newVar);
              }
            }
          } else if (scope.equals(SCOPE_AT_CHEMICAL)) {
            XMLTag newVar= new XMLTag("var");
            newVar.setAttribute("name",varName);
            newVar.setAttribute("scope", scope);
            newVar.setAttribute("structure", structure);
            String chemName = "";
            if (varName.endsWith(SOLUTION)) { 
              newVar.setAttribute("chemtype",DEF_SOLN);
              chemName = varName.substring(0,varName.length()-SOLUTION.length());
            } else if (varName.endsWith(BIO_CONC)) { 
              newVar.setAttribute("chemtype",DEF_BIOCONC);
              chemName = varName.substring(0,varName.length()-BIO_CONC.length());
            } else if (varName.indexOf(CHEM_ING)>0) { 
              newVar.setAttribute("chemtype",DEF_ING_BY);
              chemName = varName.substring(0,varName.length()-CHEM_ING.length());
            }
            newVar.setAttribute("chem",chemName);
            currentTag.addTag(newVar);
          } else if (scope.equals(SCOPE_AT_INCHEMICAL)) {
            XMLTag newVar= new XMLTag("var");
            newVar.setAttribute("name",varName);
            newVar.setAttribute("scope", scope);
            newVar.setAttribute("structure", structure);
            int selectionIndex = variableList.getSelectedIndices()[i];
            if (selectionIndex%2==0) newVar.setAttribute("chemtype",DEF_GAIN);
            else newVar.setAttribute("chemtype",DEF_POOL);
            XMLTag[] chems = XMLTag.sortTags(theModel.getTags("chemical"),"name");
            newVar.setAttribute("chem",chems[selectionIndex/2].getValue("name"));
            currentTag.addTag(newVar);
          } else if (scope.equals(SCOPE_AT_VARIETYBASED)) {
            for (int j=0; j<varSpecStages.length; j++) {
              XMLTag newVar= new XMLTag("var");
              newVar.setAttribute("name",varName+ " : "+varSpecStages[j].toString());
              newVar.setAttribute("scope", scope);
              newVar.setAttribute("structure", structure);
              String specStage = varSpecStages[j].toString();
              String spec = specStage.substring(0,specStage.indexOf(":")-1).trim();
              String stage = specStage.substring(specStage.indexOf(":")+2).trim();
              newVar.setAttribute("stage", stage);
              newVar.setAttribute("species", spec);
              currentTag.addTag(newVar);
            }

            
          } else { 
            XMLTag newVar= new XMLTag("var");
            newVar.setAttribute("name",varName);
            newVar.setAttribute("scope", scope);
            newVar.setAttribute("structure", structure);
            currentTag.addTag(newVar);
          }
        }
        updateChooseDisplay();
        vc2.unsaved(false);
        lockEvents--;
        
      } else if (e.getSource() == auditTrails) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Select directory with previous results");

        int result = fileChooser.showOpenDialog(vc2);
        if (result == JFileChooser.APPROVE_OPTION) {
          File dir = fileChooser.getSelectedFile();
          File dataFormats = new File(dir, "DataFormats.xml");
          if (dataFormats.exists()) {
            AuditManager auditManager = new AuditManager(vc2, collectAudit, theModel, dir);
            auditManager.setModal(true);
            auditManager.setVisible(true);
          }
        }
        fileInfoModel.fireTableDataChanged();
        vc2.unsaved(false);
        
      } else if (e.getSource()==applyDateToAll) {
        lockEvents++;
        int row = fileInfo.getSelectedRow();
        XMLTag tag = theModel.getTag("output").getTags()[row];
        String from = tag.getAttribute("from");
        String to = tag.getAttribute("to");
        String freq = "";
        String unit = "";
        GregorianCalendar fromCal = new GregorianCalendar();
        GregorianCalendar toCal = new GregorianCalendar();        
        fromCal.setTimeInMillis(Long.parseLong(from));
        toCal.setTimeInMillis(Long.parseLong(to));

        if (!fileInfo.getValueAt(row,InfoTableModel.COL_FREQ).equals("N/A")) {
          freq = tag.getAttribute("freq");
          unit = tag.getAttribute("unit");
        }
        for (int i=0; i<theModel.getTag("output").getTags().length; i++) {
          if (row!=i) {
            theModel.getTag("output").getTags()[i].setAttribute("from",from);
            fileInfoModel.setValueAt(DateDialog.getString(fromCal),i,InfoTableModel.COL_DATE1,false);
            theModel.getTag("output").getTags()[i].setAttribute("to",to);
            fileInfoModel.setValueAt(DateDialog.getString(toCal),i,InfoTableModel.COL_DATE2,false);
            if (!fileInfo.getValueAt(i,InfoTableModel.COL_FREQ).equals("N/A")) {
              if (theModel.getTag("output").getTags()[i].getAttribute("freq")!=null) {
                theModel.getTag("output").getTags()[i].setAttribute("freq",freq);
                fileInfoModel.setValueAt(freq,i,InfoTableModel.COL_FREQ,false);
                theModel.getTag("output").getTags()[i].setAttribute("unit",unit);
                fileInfoModel.setValueAt(unit,i,InfoTableModel.COL_UNIT,false);
              }
            }
          }
        }
        fileInfoModel.fireTableDataChanged();        
        vc2.unsaved(false);
        lockEvents--;
        
      } else if (e.getSource()==applyDepthToAll) {
         lockEvents++;
         int row = fileInfo.getSelectedRow();
         XMLTag tag= theModel.getTag("output").getTags()[row];
         String top = tag.getAttribute("top");
         String bottom = tag.getAttribute("bottom");
         
         if (tag.getName().equals("fieldphy")) {
           if (Double.parseDouble(top)<1.0) top="0.0";
           if (Double.parseDouble(bottom)<1.0) bottom="0.0";
         }
         
         for (int i=0; i<theModel.getTag("output").getTags().length; i++) {
           if (row!=i) {
             if (theModel.getTag("output").getTags()[i].getAttribute("top")!=null) {
               theModel.getTag("output").getTags()[i].setAttribute("top",top);
               fileInfoModel.setValueAt(top,i,InfoTableModel.COL_TOP,false);
               theModel.getTag("output").getTags()[i].setAttribute("bottom",bottom);
               fileInfoModel.setValueAt(bottom,i,InfoTableModel.COL_BOTTOM,false);
             }
           }
         }
         fileInfoModel.fireTableDataChanged();
         lockEvents--;
         vc2.unsaved(false);
      }
    }

    public void mouseClicked(MouseEvent me) {}
    public void mousePressed(MouseEvent me) {}
    public void mouseReleased(MouseEvent me) {
      if (me.getSource()==fileInfo) {
        int row = fileInfo.getSelectedRow();
        int col = fileInfo.getSelectedColumn();

        if (fileInfo.getSelectedRows().length==1) {
          String depth = fileInfo.getValueAt(row,InfoTableModel.COL_TOP).toString();
          applyDepthToAll.setEnabled(!depth.equals("N/A"));
          applyDateToAll.setEnabled(true);
        } else {
          applyDepthToAll.setEnabled(false);
          applyDateToAll.setEnabled(false);
        }
        if (row>=0) {
          XMLTag theEntry = theModel.getTag("output").getTags()[row];
          if (col==InfoTableModel.COL_DATE1) {
            GregorianCalendar date = new GregorianCalendar();
            GregorianCalendar oldDate = (GregorianCalendar) date.clone();          
            date.setTimeInMillis(Long.parseLong(theEntry.getAttribute("from")));
            dateDialog.show(startSim,endSim,date);
            date = dateDialog.getDate();
            if ((date!=null) && (!date.equals(oldDate))) {
              theEntry.setAttribute("from",String.valueOf(date.getTimeInMillis()));
              vc2.unsaved(false);
            }
           
          } else if (col==InfoTableModel.COL_DATE2) {
            GregorianCalendar date = new GregorianCalendar();
            date.setTimeInMillis(Long.parseLong(theEntry.getAttribute("to")));
            GregorianCalendar oldDate =  (GregorianCalendar) date.clone();
            dateDialog.show(startSim,endSim,date);
            date = dateDialog.getDate();
            if ((date!=null) && (!date.equals(oldDate))) {
              theEntry.setAttribute("to",String.valueOf(date.getTimeInMillis()));
              vc2.unsaved(false);
            }
          } else if ((col==InfoTableModel.COL_STAGES) && (fileInfo.getValueAt(row,0).toString().endsWith("audit trail"))) {
            String speciesName = fileInfo.getValueAt(row,0).toString();
            speciesName = speciesName.substring(0,speciesName.length()-11).trim();
            String fg = theModel.getTag("species").getTagWhere("species","@name",speciesName).getAttribute("fg");
            XMLTag[] stages = theModel.getTagWhere("functionalgroup","name",fg).getTags("stage");
            String[] stageNames = new String[stages.length];
            String auditStage = theModel.getTag("output").getTagWhere("audit","@species",speciesName).getAttribute("stage");
            boolean[] stageSelected = new boolean[stages.length];
            for (int i=0; i<stages.length; i++) {
              String oneStage = stages[i].getValue("name");;
              stageNames[i]=oneStage;
              stageSelected[i]=((auditStage.equals(oneStage)) || (auditStage.startsWith(oneStage+",")) || (auditStage.endsWith(","+oneStage))
                  || (auditStage.indexOf(","+oneStage+",")>=0));
            }
            ////////////////////////////////////////////////////////////////////////
            stageChooser.setData(stageNames,stageSelected);
            stageChooser.setVisible(true);
            String results = stageChooser.getResult();
            if (results!=null) {
              if (!auditStage.equals(results)) {
                theModel.getTag("output").getTagWhere("audit","@species",speciesName).setAttribute("stage",results);
                vc2.unsaved(false);
              }
            }
          }
        }
      }
    }
     
    public void mouseEntered(MouseEvent me) {}
    public void mouseExited(MouseEvent me) {}

    public void keyReleased(KeyEvent e) {
      if (e.getSource()==varSpecStageList) {
        lockEvents++;
        updateVariableList();
        checkAddEntryButton();
        lockEvents--;
      } else if (e.getSource()==structureList) {
        lockEvents++;
        structureChanged();
        checkAddEntryButton();
        lockEvents--;
      } else if (e.getSource()==scopeList) {
        lockEvents++;
        updateChooseDisplay(); 
        checkAddEntryButton();  
        updateVariableList();
        lockEvents--;
      } else if (e.getSource()==variableList) {
        lockEvents++;
        checkVarSpecAvailability();
        checkAddEntryButton();
        lockEvents--;
       
      }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}

  }

  class InfoTableModel extends DefaultTableModel {
    String[] columnNames= new String[] {"Title","From","To","Freq","Unit","Top","Bottom","Stages", "Active"};

    public int getColumnCount() { return columnNames.length; }
    public int rowCount = 0;
    public XMLTag myModel = null;
    
    public static final byte COL_NAME = 0;
    public static final byte COL_DATE1 = 1;
    public static final byte COL_DATE2 = 2;
    public static final byte COL_FREQ = 3;
    public static final byte COL_UNIT = 4;
    public static final byte COL_TOP = 5;
    public static final byte COL_BOTTOM = 6;
    public static final byte COL_STAGES = 7;
    public static final byte COL_ACTIVE = 8;    
    
    public void setModel(XMLTag model) { myModel = model; }
    
    public int getRowCount() { 
      if (myModel==null) return 0;
      else if ((myModel!=null) && (myModel.getTag("output")!=null))
        return myModel.getTag("output").getTags().length;
      else return 0;
    }

    public String getColumnName(int col) {
      return columnNames[col];
    }

    public String getNiceName(String s, XMLTag theEntry) {
      if (s.equals("col")) return new String("Water Column");
      else if (s.equals("fieldbio")) return new String("Biological Field Data");
      else if (s.equals("fieldchem")) return new String("Chemical Field Data");
      else if (s.equals("fielddemo")) return new String("Demographic Field Data");
      else if (s.equals("fieldphy")) return new String("Physical Field Data");
      else if (s.equals("lineage")) return new String("Lineage Data");
      else if (s.equals("lifespan")) return new String("Lifespan Data");
      else if (s.equals("audit")) return new String(theEntry.getAttribute("species")+" audit trail");
      else return s;
    }
    
    public Object getValueAt(int row, int col) {
      XMLTag theEntry = theModel.getTag("output").getTags()[row];
      if (theEntry==null) return new String("");
      else { 
        String theName = theEntry.getName();
        if (col==COL_NAME) return getNiceName(theName,theEntry);
        else if (col==COL_DATE1) {
          GregorianCalendar gc = new GregorianCalendar();
          gc.setTimeInMillis(Long.parseLong(theEntry.getAttribute("from")));
          String d = new String(DateDialog.getString(gc));
          d =d.substring(0,d.length()-3);
          return d;
          
        } else if (col==COL_DATE2) {
          GregorianCalendar gc = new GregorianCalendar();
          gc.setTimeInMillis(Long.parseLong(theEntry.getAttribute("to")));
          String d = new String(DateDialog.getString(gc));
          d =d.substring(0,d.length()-3);
          return d;
          
          
        } else if (col==COL_FREQ) {
          if ((theName.equals("lifespan")) || (theName.equals("lineage"))) return new String("N/A");
          else return new String(theEntry.getAttribute("freq"));
          
        } else if (col==COL_UNIT) {
          if ((theName.equals("lifespan")) || (theName.equals("lineage"))) return new String("N/A");
          else return new String(theEntry.getAttribute("unit"));
          
        } else if (col==COL_TOP) {
          if (theName.startsWith("field")) return new String(theEntry.getAttribute("top"));
          else return new String("N/A");
        
        } else if (col==COL_BOTTOM) {
          if (theName.startsWith("field")) return new String(theEntry.getAttribute("bottom"));
          else return new String("N/A");
        
        } else if (col==COL_STAGES) {
          if (theName.equals("audit")) return new String("Choose");
          else return new String("N/A");
          
        } else if (col==COL_ACTIVE) return new Boolean(theEntry.getAttribute("active").equals("true"));
        else return null;
      }
    }
    
    public Class getColumnClass(int c) {
      return getValueAt(0, c).getClass();
    }
    
    public void setValueAt(Object value, int row, int col) {
      setValueAt(value, row, col, true);
    }
    
    public void setValueAt(Object value, int row, int col, boolean updateVC) {
      XMLTag theEntry = theModel.getTag("output").getTags()[row];
      if (col==COL_BOTTOM) {
        theEntry.setAttribute("bottom",String.valueOf(value));
        double topVal = Double.parseDouble(theEntry.getAttribute("top"));
        double botVal = Double.parseDouble(theEntry.getAttribute("bottom"));
        if (topVal>botVal) {
          lockEvents++;
          theEntry.setAttribute("top",String.valueOf(botVal));
          theEntry.setAttribute("bottom",String.valueOf(topVal));
          this.fireTableCellUpdated(row,COL_TOP);
          lockEvents--;
        }
        if (updateVC) vc2.unsaved(false);
      
      } else if (col==COL_TOP) {
        theEntry.setAttribute("top",String.valueOf(value));
        double topVal = Double.parseDouble(theEntry.getAttribute("top"));
        double botVal = Double.parseDouble(theEntry.getAttribute("bottom"));
        if (topVal>botVal) {
          lockEvents++;
          theEntry.setAttribute("top",String.valueOf(botVal));
          theEntry.setAttribute("bottom",String.valueOf(topVal));
          this.fireTableCellUpdated(row,COL_BOTTOM);
          lockEvents--;
        }
        if (updateVC) vc2.unsaved(false);
      
      } else if (col==COL_ACTIVE) {
        theEntry.setAttribute("active",String.valueOf(value));
        if (updateVC) vc2.unsaved(false);
      
      } else if (col==COL_FREQ) {
        theEntry.setAttribute("freq",String.valueOf(value));
        if (updateVC) vc2.unsaved(false);
        
      } else if (col==COL_UNIT) {
        theEntry.setAttribute("unit",String.valueOf(value));
        if (updateVC) vc2.unsaved(false);
      }
      fireTableCellUpdated(row, col);
    }
    
    public boolean isCellEditable(int row, int col) {
      boolean NA = (getValueAt(row,col).toString().equals("N/A"));
      if (NA) return false;
      else return ((col>=1) && (col!=COL_STAGES));
    }
  }
  
  
  class CellEditorModel {
    private Hashtable data;
    public CellEditorModel() { data = new Hashtable(); }
    public void addEditor(int row, int col, TableCellEditor e) { data.put(new String(row+"#"+col),e); }
    public void removeEditor(int row, int col) { data.remove(new String(row+"#"+col)); }
    public TableCellEditor getEditor(int row, int col) { return (TableCellEditor) data.get(new String(row+"#"+col)); }
  }
  
  class FlexiTable extends JTable {
    protected CellEditorModel cem;
    public FlexiTable() { super(); cem = null; }
    public FlexiTable(TableModel tm) { super(tm); cem = null; }
    public FlexiTable(TableModel tm, TableColumnModel cm) { super(tm,cm); cem = null; }
    public FlexiTable(TableModel tm, TableColumnModel cm, ListSelectionModel sm) { super(tm,cm,sm); cem = null; }
    public FlexiTable(int rows, int cols) { super(rows,cols); cem = null; }
    public FlexiTable(final Vector rowData, final Vector colNames) { super(rowData, colNames); cem = null; }
    public FlexiTable(final Object[][] rowData, final Object[][] colNames) { super(rowData, colNames); cem = null; }
    public FlexiTable(TableModel tm, CellEditorModel _cem) { super(tm,null,null); cem = _cem; }
    public void setCellEditorModel(CellEditorModel _cem) { cem = _cem; }
    public CellEditorModel getRowEditorModel() { return cem; }
    public TableCellEditor getCellEditor(int row, int col) {
      TableCellEditor tce = null;
      if (cem!=null) tce = cem.getEditor(row,col);
      if (tce!=null) return tce;
      return super.getCellEditor(row,col);
    }
  }
  
  class StageChooser extends JDialog {
    JButton stageOK = new JButton("OK");
    JButton stageCancel = new JButton("Cancel");
    StageTableModel stageTableModel = new StageTableModel(); 
    JTable stageTable = new JTable(stageTableModel);
    int lockStageEvents = 0;
    String finalStages = new String("");
    StageEventHandler seh = new StageEventHandler();
    
    public String getResult() { return finalStages; }
    
    public StageChooser(JDialog owner) {
      super(owner,"Choose Stages",true);
      JScrollPane stageScroller = new JScrollPane(stageTable);
      stageScroller.setPreferredSize(new Dimension(300,200));
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(stageScroller,BorderLayout.CENTER);
      JPanel buttonPanel = new JPanel(new FlowLayout());
      buttonPanel.add(stageOK);
      buttonPanel.add(stageCancel);
      getContentPane().add(buttonPanel,BorderLayout.SOUTH);
      stageTableModel.setColumnIdentifiers(new String[] {"Name","Selected"});
      pack();
      stageOK.addActionListener(seh);
      stageCancel.addActionListener(seh);      
    }
    
    public void setData(String[] stages, boolean[] selected) {
      while (stageTableModel.getRowCount()>0) stageTableModel.removeRow(0);
      for (int i=0; i<stages.length; i++)
        stageTableModel.addRow(new Object[] {new String(stages[i]), new Boolean(selected[i])});
    }
    
    class StageEventHandler implements ActionListener {
      public StageEventHandler() {}
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==stageCancel) {
          finalStages=null;
          setVisible(false);
        } else if (e.getSource()==stageOK) {
          boolean firstEntry=true;
          finalStages="";
          for (int i=0; i<stageTableModel.getRowCount(); i++) {
            if (((Boolean)stageTableModel.getValueAt(i,1)).booleanValue()) {
              if (!firstEntry) finalStages=finalStages+","+stageTableModel.getValueAt(i,0).toString();
              else {
                firstEntry=false;
                finalStages=stageTableModel.getValueAt(i,0).toString();
              }
            }
          }
          setVisible(false);
        }
      }     
    }
  }
  
  class StageTableModel extends DefaultTableModel {
    public boolean isCellEditable(int row, int col) { return (col==1); }
    public Class getColumnClass(int c) { return getValueAt(0, c).getClass(); }
    
  }
    
}