package VEW.Analyser4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;

import VEW.Common.DateDialog;
import VEW.Common.StringTools;

public class AxisDefinition {
  public int type;
  public boolean useTicks;
  public int noTicks;
  public float max;
  public float min;
  public float firstTick;
  public float interval;
  public String title = "";
  public String name = "";
  public int xPos;
  public int yPos;
  public int sourceIndex;
  public byte axis_orientation;
  public byte text_placement;  
  public byte text_rotation;
  public boolean invert_axis;
  public int size;
  public Color colour;
  public int format;
  public boolean visible;
  public GregorianCalendar t0 = new GregorianCalendar(DateDialog.GMTTimeZone);
  public static final String[] shortMonths = new String[] {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
  public double hps;
  public static final byte HORIZONTAL_AXIS = 0;
  public static final byte VERTICAL_AXIS = 1;
  public static final byte TEXT_0 = 0;
  public static final byte TEXT_45 = 1;
  public static final byte TEXT_90 = 2;  
  public static final byte TEXT_270 = 3;  
  public static final byte TEXT_315 = 4;  
  public static final byte TEXT_ABOVE = 0;
  public static final byte TEXT_BELOW = 1;
  public static final byte TEXT_LEFT = 2;
  public static final byte TEXT_RIGHT = 3;
  public static final int DD_MMM = 0;
  public static final int DD_MMM_YYYY = 11;
  public static final int MMM_YYYY = 2;
  public static final int YYYY = 3;
  public static final int DD = 4;
  public static final int TIMESTEP = 5;
  public static final int DD_MMM_YYYY_HH_MM = 6;
  public static final int DD_MMM_HH_MM = 7;
  public static final int DD_HH_MM = 8;    
  public static final int HH_MM = 9;
  public static final int MMM = 10;
  public static final int F_NONE = 11;
  public static final int F_0E0 = 12;
  public static final int F_0_0E0 = 13;
  public static final int F_0_00E0 = 14;
  public static final int F_0_000E0 = 15;
  public static final int F_0_0 = 16;
  public static final int F_0_00 = 17;
  public static final int F_0_000 = 18; 
  public static final int F_0 = 19;
  
  
  public Node convertToTag() {

    Element ax = DocumentFactory.getInstance().createElement("axis");
    ax.addElement("type").addText(String.valueOf(type));
    ax.addElement("useTicks").addText(String.valueOf(useTicks));
    ax.addElement("noTicks").addText(String.valueOf(noTicks));
    ax.addElement("max").addText(String.valueOf(max));
    ax.addElement("min").addText(String.valueOf(min));
    ax.addElement("firstTick").addText(String.valueOf(firstTick));
    ax.addElement("interval").addText(String.valueOf(interval));
    ax.addElement("sourceIndex").addText(String.valueOf(sourceIndex));    
    ax.addElement("title").addText(title);
    ax.addElement("name").addText(name);
    ax.addElement("xPos").addText(String.valueOf(xPos));
    ax.addElement("yPos").addText(String.valueOf(yPos));
    ax.addElement("invert_axis").addText(String.valueOf(invert_axis));    
    ax.addElement("axis_orientation").addText(String.valueOf(axis_orientation));
    ax.addElement("text_placement").addText(String.valueOf(text_placement));
    ax.addElement("text_rotation").addText(String.valueOf(text_rotation));
    ax.addElement("size").addText(String.valueOf(size));
    ax.addElement("colour").addText(String.valueOf(colour.getRGB()));
    ax.addElement("format").addText(String.valueOf(format));
    ax.addElement("t0").addText(String.valueOf(t0.getTimeInMillis()));
    ax.addElement("hps").addText(String.valueOf(hps));    
    return ax;
  }
  
  public static AxisDefinition createFromTag(Node node) {
    AxisDefinition ad = new AxisDefinition();
    ad.type = Integer.parseInt(node.valueOf("type"));
    ad.useTicks = StringTools.parseBoolean(node.valueOf("useTicks"));
    ad.noTicks = Integer.parseInt(node.valueOf("noTicks"));
    ad.max = Float.parseFloat(node.valueOf("max"));
    ad.min = Float.parseFloat(node.valueOf("min"));
    ad.firstTick = Float.parseFloat(node.valueOf("firstTick"));
    ad.interval = Float.parseFloat(node.valueOf("interval"));
    ad.sourceIndex = Integer.parseInt(node.valueOf("sourceIndex"));    
    ad.title = node.valueOf("title");
    ad.name = node.valueOf("name");
    ad.invert_axis=StringTools.parseBoolean(node.valueOf("invert_axis"));
    ad.xPos = Integer.parseInt(node.valueOf("xPos"));
    ad.yPos = Integer.parseInt(node.valueOf("yPos"));
    ad.axis_orientation = Byte.parseByte(node.valueOf("axis_orientation"));
    ad.text_placement = Byte.parseByte(node.valueOf("text_placement"));
    ad.text_rotation = Byte.parseByte(node.valueOf("text_rotation"));
    ad.size = Integer.parseInt(node.valueOf("size"));
    ad.colour = new Color(Integer.parseInt(node.valueOf("colour")));
    ad.format = Integer.parseInt(node.valueOf("format"));
    ad.t0 = new GregorianCalendar(DateDialog.GMTTimeZone);
    ad.t0.setTimeInMillis(Long.parseLong(node.valueOf("t0")));
    ad.hps = Double.parseDouble(node.valueOf("hps"));
    return ad;
  }
  
  
  public AxisDefinition() {}
    
  public static void plotRotatedText(Graphics2D g, int px, int py, double angle, String text) {
    if (text!=null) {
      angle = angle*(Math.PI/180.0);
      g.translate(px,py);
      g.rotate(angle);
      g.drawString(text,0,0);
      g.rotate(-angle);
      g.translate(-px,-py);
    }
  }
  
  
  private int textWidth(Graphics2D g, Graphics2D alternate, String s) {
    if (s==null) return 0; else {
      if ((g.getFont()==null) || (g.getFontRenderContext()==null)) {
        FontRenderContext frc = alternate.getFontRenderContext();
        Rectangle2D r = alternate.getFont().getStringBounds(s,frc);
        return (int) r.getWidth();
      } else {
        FontRenderContext frc = g.getFontRenderContext();
        Rectangle2D r = g.getFont().getStringBounds(s,frc);
        return (int) r.getWidth();
      }
    }
  }
  
  public void textForLabel(Graphics2D g, Graphics2D alternate, float val, float pix) {
    String theNumber = "";
    if (type==1) {
      GregorianCalendar tstart = new GregorianCalendar(DateDialog.GMTTimeZone);
      tstart.setTimeInMillis(t0.getTimeInMillis());
      long timesteps = ((long) val) - (long) min;
      tstart.add(GregorianCalendar.HOUR,(int)(timesteps*hps));
      String day = String.valueOf(tstart.get(GregorianCalendar.DAY_OF_MONTH));
      String month = shortMonths[tstart.get(GregorianCalendar.MONTH)];
      String year = String.valueOf(tstart.get(GregorianCalendar.YEAR));
      String hour = String.valueOf(tstart.get(GregorianCalendar.HOUR_OF_DAY));
      String minute = String.valueOf(tstart.get(GregorianCalendar.MINUTE));
      if (day.length()==1) day = "0"+day;
      if (hour.length()==1) hour="0"+hour;
      if (minute.length()==1) minute="0"+minute;
           
      if (format==DD_MMM) theNumber = day+" "+month;
      else if (format==DD_MMM_YYYY) theNumber = day+" "+month+" "+year;
      else if (format==MMM_YYYY) theNumber = month+" "+year;
      else if (format==YYYY) theNumber = year;
      else if (format==DD) theNumber = day;
      else if (format==TIMESTEP) theNumber = String.valueOf(timesteps);
      else if (format==DD_MMM_YYYY_HH_MM) theNumber = day+" "+month+" "+year+", "+hour+":"+minute;
      else if (format==DD_MMM_HH_MM) theNumber = day+" "+month+", "+hour+":"+minute;
      else if (format==DD_HH_MM) theNumber = day+", "+hour+":"+minute;
      else if (format==HH_MM) theNumber = hour+":"+minute;
      else if (format==MMM) theNumber = month;
      
        
    } else if (type==2) {
      if (format==F_NONE) theNumber = String.valueOf(val);
      else if (format==F_0E0) theNumber = new DecimalFormat("0E0").format(val);
      else if (format==F_0_0E0) theNumber = new DecimalFormat("0.0E0").format(val);
      else if (format==F_0_00E0) theNumber = new DecimalFormat("0.00E0").format(val);
      else if (format==F_0_000E0) theNumber = new DecimalFormat("0.000E0").format(val);
      else if (format==F_0_0) theNumber = new DecimalFormat("0.0").format(val);
      else if (format==F_0_00) theNumber = new DecimalFormat("0.00").format(val);
      else if (format==F_0_000) theNumber = new DecimalFormat("0.000").format(val);
      else if (format==F_0) theNumber = new DecimalFormat("0").format(val);      
            
    } else theNumber = String.valueOf(val);
    int theWidth = textWidth(g,alternate, theNumber);
    if (axis_orientation==HORIZONTAL_AXIS) {
      if (text_placement==TEXT_BELOW) {
        if (text_rotation==TEXT_0) {
          plotRotatedText(g, (int)(pix-(0.5*theWidth)),yPos+15,0,theNumber);
        } else if (text_rotation==TEXT_45) {
          plotRotatedText(g, (int)pix-5,yPos+10,45,theNumber);          
        } else if (text_rotation==TEXT_90) {
          plotRotatedText(g, (int)pix-5,yPos+7,90,theNumber);          
        } else if (text_rotation==TEXT_270) {
          plotRotatedText(g, (int)pix+5,yPos+theWidth+7,270,theNumber);          
        } else if (text_rotation==TEXT_315) {
          plotRotatedText(g, (int)(5+pix-(0.7*theWidth)),12+(int) (yPos+0.7*theWidth),315,theNumber);          
        } 
      }
      else if (text_placement==TEXT_ABOVE) {
        if (text_rotation==TEXT_0) {
          plotRotatedText(g, (int)(pix-(0.5*theWidth)),yPos-5,0,theNumber);
        } else if (text_rotation==TEXT_45) {
          plotRotatedText(g, (int)(pix-(0.7*(theWidth))),(int)((yPos-5)-(0.7*theWidth)),45,theNumber);          
        } else if (text_rotation==TEXT_90) {
          plotRotatedText(g, (int)pix-5,(yPos-theWidth)-5,90,theNumber);          
        } else if (text_rotation==TEXT_270) {
          plotRotatedText(g, (int)pix+5,yPos-5,270,theNumber);          
        } else if (text_rotation==TEXT_315) {
          plotRotatedText(g, (int)pix,yPos-5,315,theNumber);          
        } 
      }
        
    } else if (axis_orientation==VERTICAL_AXIS) {
      if (text_placement==TEXT_LEFT) {
        if (text_rotation==TEXT_0) {
          plotRotatedText(g, (xPos-theWidth)-5, (int)pix+5,0,theNumber);
        } else if (text_rotation==TEXT_45) {
          plotRotatedText(g, (int)(xPos-(0.7*theWidth))-8,(int)(pix-(0.7*theWidth))+3,45,theNumber);          
        } else if (text_rotation==TEXT_90) {
          plotRotatedText(g, xPos-13,(int)(pix-(theWidth/2)),90,theNumber);          
        } else if (text_rotation==TEXT_270) {
          plotRotatedText(g, xPos-5,(int)(pix+(theWidth/2)),270,theNumber);          
        } else if (text_rotation==TEXT_315) {
          plotRotatedText(g, (int)(xPos-(0.7*theWidth))-4,(int)(pix+0.7*theWidth),315,theNumber);          
        } 
      }
      else if (text_placement==TEXT_RIGHT) {
        if (text_rotation==TEXT_0) {
          plotRotatedText(g, xPos+5, (int)pix+5,0,theNumber);
        } else if (text_rotation==TEXT_45) {
          plotRotatedText(g, xPos+5,(int)pix,45,theNumber);          
        } else if (text_rotation==TEXT_90) {
          plotRotatedText(g, xPos+5,(int)(pix-(theWidth/2)),90,theNumber);       
        } else if (text_rotation==TEXT_270) {
          plotRotatedText(g, xPos+14,(int)(pix+(theWidth/2)),270,theNumber);          
        } else if (text_rotation==TEXT_315) {
          plotRotatedText(g, xPos+11,(int)pix+5,315,theNumber);          
        } 
      }
      
    }
  }
  
  public void drawAxis(Graphics2D g, Graphics2D alternate) {
    if (visible) {
      g.setColor(colour);
      if (axis_orientation==HORIZONTAL_AXIS) g.drawLine(xPos,yPos,xPos+size,yPos);
      else if (axis_orientation==VERTICAL_AXIS) g.drawLine(xPos,yPos,xPos,yPos+size);
      float val = min;
      float stepVal;
      float stepPix;
      float pix = 0;
      float realMax,realMin;
      if (((invert_axis) && (axis_orientation==VERTICAL_AXIS)) || 
          ((invert_axis) && (axis_orientation==HORIZONTAL_AXIS))) {
        realMin = max;
        realMax = min;
      } else {
        realMin = min;
        realMax = max;
      }
    
      if (useTicks) {
        val = realMin;
        stepVal = (realMax-realMin)/(noTicks-1.0f);
        stepPix = (size*1.0f)/(noTicks-1.0f);
      
        if (axis_orientation==HORIZONTAL_AXIS) pix = xPos;
        else if (axis_orientation==VERTICAL_AXIS) pix = yPos;
        for (int tickNo=0; tickNo<noTicks; tickNo++) {
          if (axis_orientation==HORIZONTAL_AXIS)
            g.drawLine((int)pix,yPos-2,(int)pix,yPos+2);
          else if (axis_orientation==VERTICAL_AXIS)
            g.drawLine(xPos-2,(int)pix,xPos+2,(int)pix);
          textForLabel(g,alternate,val,pix);
          pix+=stepPix;
          val+=stepVal;
        
        }
      } else {
        val = firstTick;
        stepVal = interval;
        stepPix = (interval/(Math.abs(realMax-realMin)))*size;
        int theEnd=0;
        if (axis_orientation==HORIZONTAL_AXIS) theEnd = xPos;
        else if (axis_orientation==VERTICAL_AXIS) theEnd = yPos;
        pix = ((val-min)/(max-min)*(1.0f*size))+theEnd;
      
        while (pix<=size+theEnd) {
          if (axis_orientation==HORIZONTAL_AXIS)
            g.drawLine((int)pix,yPos-2,(int)pix,yPos+2);
          else if (axis_orientation==VERTICAL_AXIS)
            g.drawLine(xPos-2,(int)pix,xPos+2,(int)pix);
          textForLabel(g,alternate,val,pix);
          pix+=stepPix;
          val+=stepVal;
        }
      } 
    }
    if (title.length()>0) {
      g.setColor(Color.black);
      int titleSize = textWidth(g,alternate,title);
      if ((axis_orientation==HORIZONTAL_AXIS) && (text_placement==TEXT_BELOW)) 
        plotRotatedText(g, (xPos+(size/2))-(titleSize/2),yPos+35,0,title);
      else if ((axis_orientation==HORIZONTAL_AXIS) && (text_placement==TEXT_ABOVE)) 
        plotRotatedText(g, (xPos+(size/2))-(titleSize/2),yPos-20,0,title);
      else if ((axis_orientation==VERTICAL_AXIS) && (text_placement==TEXT_LEFT)) 
        plotRotatedText(g, xPos-20,(yPos+(size/2))+(titleSize/2),270,title);
      else if ((axis_orientation==VERTICAL_AXIS) && (text_placement==TEXT_RIGHT)) 
        plotRotatedText(g, xPos+20,(yPos+(size/2))+(titleSize/2),90,title);
    }
  }
}
