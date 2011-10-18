package VEW.Analyser4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
//import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Properties;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsio.ps.*;

import VEW.Common.DateDialog;
import VEW.Common.StringTools;


public class GraphPanel extends JPanel {
  private static final long serialVersionUID = 1227176885878529499L;
  private int pixWidth = 0;
  private int pixHeight = 0;
  private int ColourID = 0;
  private GeneralPath[] contours;
  //private Polygon[] polygons;
  private boolean plotContours = false;
  private boolean fillContours = false;
  private boolean drawContourLines = false;
  private static final int LineFill = 3;
  private static final int PlainFill = 2;
  private static final int PlainLine = 1;
  public BasicStroke[] LineStrokes;
  private BufferedImage theImage;
  private final int axisWidth = 60;
  private final int axisHeight = 60;
  private ArrayList axes = new ArrayList();
  private Analyser4 a4 = null;
    
  public BufferedImage getTheImage() { return theImage; }
  
  public double screen[][];
  
  private GregorianCalendar startPlot = new GregorianCalendar(DateDialog.GMTTimeZone);
  private GregorianCalendar endPlot = new GregorianCalendar(DateDialog.GMTTimeZone);

    
  private BackgroundChooser bc = null;
  
  public void resetAxes() { 
    Graphics g = theImage.getGraphics();
    axes.clear(); 
    g.setColor(getBackground());
    g.fillRect(0,0,(pixWidth+(2*axisWidth)),axisHeight-1);
    g.fillRect(0,0,axisWidth-1,pixHeight+(axisHeight*2));
    g.fillRect(0,axisHeight+pixHeight+2,pixWidth+(2*axisWidth),pixHeight+(2*axisHeight));
    g.fillRect(axisWidth+pixWidth+2,0,pixWidth+(2*axisWidth),pixHeight+(2*axisHeight));
  }
  
  public void addAxis(AxisDefinition ad) { axes.add(ad); } 
  
  public void plotAxes() {
    plotAxes(theImage.getGraphics(), null);
  }
  
  public void plotAxes(Graphics g, Graphics alt) {
    for (int i=0; i<axes.size(); i++) {
      AxisDefinition a = (AxisDefinition) axes.get(i);
      a.xPos+=axisWidth;
      a.yPos+=axisHeight;
      ((AxisDefinition)axes.get(i)).drawAxis((Graphics2D)g,(Graphics2D)alt);
      a.xPos-=axisWidth;
      a.yPos-=axisHeight;
    }
  }
  
  public int getGraphWidth() {  return pixWidth; }
  public int getGraphHeight() { return pixHeight; }
  public void setBackgroundChooser(BackgroundChooser _bc) { bc = _bc;
  }
  
  public void setContours(boolean b) { plotContours = b; }
  public void setContourLines(GeneralPath[] g, boolean f, boolean d) { contours = g; fillContours = f; drawContourLines = d;} 
  public void setContourLines(boolean f, boolean d) { fillContours = f; drawContourLines = d;}  
  //public void setContourLines(Polygon[] g, boolean f, boolean d) { polygons = g; fillContours = f; drawContourLines = d;} 
  
  public int getAxisWidth() { return axisWidth; }
  public int getAxisHeight() { return axisHeight; }
  
  public GraphPanel(int x, int y, Analyser4 _a4) {
    a4=_a4;
    theImage = new BufferedImage(x,y,BufferedImage.TYPE_INT_RGB);
    setPreferredSize(new Dimension(x,y));
    screen = new double[x-(2*axisWidth)][y-2*(axisHeight)];
    pixWidth = x-(2*axisWidth);
    pixHeight = y-(2*axisHeight);
    BackgroundColour.setType(BackgroundColour.GREY_SCALE,255);
    LineStrokes = new BasicStroke[4];
    LineStrokes[0] = new BasicStroke(1);
    LineStrokes[1] = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{6, 3}, 0);
    LineStrokes[2] = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{6, 3, 2, 3}, 0);
    LineStrokes[3] = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, new float[]{2, 4}, 0);    
    Graphics g = theImage.getGraphics();
    g.setColor(this.getBackground());
    g.fillRect(0,0,pixWidth+(2*(axisWidth)),pixHeight+(2*(axisHeight)));
    g.setColor(Color.black);
    g.drawLine(axisWidth-1,axisHeight-1,axisWidth+1+pixWidth,axisHeight-1);
    g.drawLine(axisWidth-1,pixHeight+axisHeight+1,axisWidth+1+pixWidth,pixHeight+axisHeight+1);
    g.drawLine(axisWidth-1,axisHeight-1,axisWidth-1,pixHeight+axisHeight+1);
    g.drawLine(axisWidth+pixWidth+1,axisHeight-1,axisWidth+1+pixWidth,pixHeight+axisHeight+1);    
    g.setColor(Color.red);
  }
   
  public void clearAll() {
    for (int i=0; i<pixWidth; i++) {
      for (int j=0; j<pixWidth; j++) {
        screen[i][j]=0;
      }
    }
    Graphics graphics = theImage.getGraphics();
    graphics.setColor(Color.BLACK);
    graphics.fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
  }
 
  public void plotLines(Graphics g, LineChooser2 lc) {
    if (g==null) g = theImage.getGraphics();
    long _startPlot = a4.startOfPlot.getTimeInMillis();
    long _endPlot = a4.endOfPlot.getTimeInMillis();
    long freq = Long.parseLong(a4.numFreq.getText());
    int freqUnit = a4.numType.getSelectedIndex();
    int timeStepSecs = (int) (a4.timeStepHours*3600);
    if (freqUnit==0) freq = freq * timeStepSecs * 1000;
    else if (freqUnit==1) freq = freq * 1000 * 3600;
    else if (freqUnit==2) freq = freq * 1000 * 3600 * 24;
    else if (freqUnit==3) freq = freq * 1000 * 3600 * 24 * 7;
    if (lc!=null) {
      for (int i=0; i<lc.getLineCount(); i++) {
        LineDefinition ldef = lc.getLineDef(i);
        if ((ldef.visible) && ((ldef.type==LineDefinition.SINGLE) || (ldef.type==LineDefinition.COMPOUND_PLOT))) {
          double[] yvals = ldef.dataY;
          if (yvals!=null) {
            if (ldef.autoscale) {
              double max = Double.NEGATIVE_INFINITY;
              double min = Double.POSITIVE_INFINITY;
              long tMax = Long.MIN_VALUE;
              long tMin = Long.MIN_VALUE;
              double theVal=0;
              for (int j=0; j<yvals.length; j++) {
                if (!ldef.accumulate) {
                  if (ldef.log) theVal=StringTools.log10(yvals[j]);
                  else theVal=yvals[j];
                } else {
                  if ((ldef.log) && (!Double.isInfinite(StringTools.log10(yvals[j])))) 
                    theVal+=StringTools.log10(yvals[j]);
                  else if ((!ldef.log) && (!Double.isInfinite(yvals[j]))) 
                    theVal+=yvals[j];
                }
              
                if ((theVal>max) && (!Double.isInfinite(theVal))) {
                  max=theVal;
                  tMax = _startPlot + (long) (((1.0*j)/(1.0*ldef.dataY.length))*(_endPlot-_startPlot));
                }
                if ((theVal<min) && (!Double.isInfinite(theVal))) {
                  min=theVal;
                  tMin = _startPlot + (long) (((1.0*j)/(1.0*ldef.dataY.length))*(_endPlot-_startPlot));
                }
              }
              ldef.max = max;
              ldef.min = min;
              ldef.timeMax = tMax;
              ldef.timeMin = tMin;
            } 

            g.setColor(ldef.colour);
        
            int ypix,oldYPix = 0;
            int xpix,oldXPix = 0;
            double theVal=0;
            double oldVal=0;
            for (int x=0; x<yvals.length; x++) {
              if (!ldef.accumulate) {
                if (ldef.log) theVal=StringTools.log10(yvals[x]);
                else theVal=yvals[x];
              } else {
                if ((ldef.log) && (!Double.isInfinite(StringTools.log10(yvals[x])))) 
                  theVal+=StringTools.log10(yvals[x]);
                else if ((!ldef.log) && (!Double.isInfinite(yvals[x]))) 
                  theVal+=yvals[x];
              }
            
              ypix = (int) (pixHeight*((theVal-ldef.min)/(ldef.max-ldef.min)));
              if (!ldef.invertY) ypix = pixHeight - ypix;

            
              if (x==0) {
                oldYPix=ypix;
                oldXPix = axisWidth;
                oldVal = theVal;
              } else {
                xpix = (int) (axisWidth+ (pixWidth*1.0f*(((x * freq * 1.0f))/(1.0f*(_endPlot-_startPlot)))));
                if ((!Double.isInfinite(theVal)) && (!Double.isInfinite(oldVal)))
                  clipDraw(oldXPix,oldYPix+axisHeight,xpix,ypix+axisHeight,g);
                oldXPix=xpix;
                oldYPix=ypix;
                oldVal=theVal;
              }
            }
          }
        }
      }
    }
  }
  
  
  public boolean inYRange(float y) { return ((y>=axisHeight) && (y<=(axisHeight+pixHeight))); }
  
  public void clipDraw(float x1, float y1, float x2, float y2, Graphics g) {
    float bottomAxis=axisHeight+pixHeight;
    if (y2>bottomAxis) {
      x2 = x1+(((bottomAxis-y1)/(y2-y1))*(x2-x1));
      y2 = bottomAxis;
    } else if (y1>bottomAxis) {
      x1 = x1+(((y1-bottomAxis)/(y1-y2))*(x2-x1));
      y1 = bottomAxis;
    } else if (y1<axisHeight) {
      x1 = x1+(((axisHeight-y1)/(y2-y1))*(x2-x1));
      y1 = axisHeight;
    } else if (y2<axisHeight) {
      x2 = x1+(((y1-axisHeight)/(y1-y2))*(x2-x1));
      y2 = axisHeight;
    }
    
    if ((inYRange(y2)) && (inYRange(y1))) {  
      g.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
    }
    
  }
  
  public void plotXYPlot(Graphics g, LineChooser2 lc) {
    if (g==null) g = theImage.getGraphics();
    long _startPlot = a4.startOfPlot.getTimeInMillis();
    long _endPlot = a4.endOfPlot.getTimeInMillis();
    if (lc!=null) for (int i=0; i<lc.getLineCount(); i++) {
      LineDefinition ldef = lc.getLineDef(i);
      if ((ldef.visible) && (ldef.type==LineDefinition.XY_PLOT) && (ldef.dataX!=null)) {
        g.setColor(ldef.colour);
        if (ldef.autoscale) {
          double minY = Double.MAX_VALUE;
          double maxY = Double.MIN_VALUE;
          long tMin = Long.MIN_VALUE;
          long tMax = Long.MIN_VALUE;
          double theYVal = 0;
          for (int j=0; j<ldef.dataY.length; j++) {
            if (!ldef.accumulate) {
              if (ldef.log) theYVal=StringTools.log10(ldef.dataY[j]);
              else theYVal=ldef.dataY[j];
            } else {
              if (ldef.log) theYVal+=StringTools.log10(ldef.dataY[j]);
              else theYVal+=ldef.dataY[j];
            }
            if ((theYVal>maxY) && (!Double.isInfinite(theYVal))) {
              maxY=theYVal;
              tMax = _startPlot + (long) (((1.0*j)/(1.0*ldef.dataY.length))*(_endPlot-_startPlot));
            }
            if ((theYVal<minY) && (!Double.isInfinite(theYVal))) {
              minY=theYVal;
              tMin = _startPlot + (long) (((1.0*j)/(1.0*ldef.dataY.length))*(_endPlot-_startPlot));
            }
            ldef.max = maxY;
            ldef.min = minY;
            ldef.timeMin = tMin;
            ldef.timeMax = tMax;
          }
        }
        if (ldef.xauto) {
          double minX = Double.MAX_VALUE;
          double maxX = Double.MIN_VALUE;
          long tMinX = Long.MIN_VALUE;
          long tMaxX = Long.MIN_VALUE;
          double theXVal = 0;
          for (int j=0; j<ldef.dataX.length; j++) {
            if (!ldef.accumulatex) {
              if (ldef.logx) theXVal=StringTools.log10(ldef.dataX[j]);
              else theXVal=ldef.dataX[j];
            } else {
              if (ldef.logx) theXVal+=StringTools.log10(ldef.dataX[j]);
              else theXVal+=ldef.dataX[j];
              
            }
            if ((theXVal>maxX) && (!Double.isInfinite(theXVal))) {
              maxX=theXVal;
              tMaxX = _startPlot + (long) (((1.0*j)/(1.0*ldef.dataY.length))*(_endPlot-_startPlot));
              
            }
            if ((theXVal<minX) && (!Double.isInfinite(theXVal))) {
              minX=theXVal;
              tMinX = _startPlot + (long) (((1.0*j)/(1.0*ldef.dataY.length))*(_endPlot-_startPlot));
            }
            ldef.maxX = maxX;
            ldef.minX = minX;
            ldef.timeMinX = tMinX;
            ldef.timeMaxX = tMaxX;
          }
        }
        double theXVal = 0;
        double theYVal = 0;
        int xPix = 0,yPix = 0;
        int oldX = 0,oldY = 0;
       
        for (int j=0; j<ldef.dataX.length; j++) {
          if (!ldef.accumulate) {
            if (ldef.log) theYVal=StringTools.log10(ldef.dataY[j]);
            else theYVal=ldef.dataY[j];
          } else {
            if (ldef.log) theYVal+=StringTools.log10(ldef.dataY[j]);
            else theYVal+=ldef.dataY[j];
          }
          if (!ldef.accumulatex) {
            if (ldef.logx) theXVal=StringTools.log10(ldef.dataX[j]);
            else theXVal=ldef.dataX[j];
          } else {
            if (ldef.logx) theXVal+=StringTools.log10(ldef.dataX[j]);
            else theXVal+=ldef.dataX[j];
          }
              
          
          xPix=(int)(pixWidth*((theXVal-ldef.minX)/(ldef.maxX-ldef.minX)));
          yPix=(int)(pixHeight*((theYVal-ldef.min)/(ldef.max-ldef.min)));
          if (!ldef.invertY) yPix=pixHeight-yPix;
          if (ldef.invertX) xPix=pixWidth-xPix;
          if (j==0) {
            oldX=xPix;
            oldY=yPix;
          }
          if (ldef.xyconnect) clipDraw(axisWidth+oldX,axisHeight+oldY,axisWidth+xPix,axisWidth+yPix,g);
          else clipDraw(axisWidth+xPix,axisWidth+yPix,axisWidth+xPix,axisWidth+yPix,g);
          oldX=xPix;
          oldY=yPix;
        }
      }
    }
  }
  
  public void plotDepthProfiles(Graphics g, LineChooser2 lc) {
    if (g==null) g = theImage.getGraphics();
    if (lc!=null) for (int i=0; i<lc.getLineCount(); i++) {
      LineDefinition ld = lc.getLineDef(i);
      if ((ld.visible) && (ld.type==LineDefinition.DEPTH_PROFILE) && (ld.dataY!=null)) {
        if (ld.xauto) {
          long theTime = ld.timestep.getTimeInMillis();
          long startTime = startPlot.getTimeInMillis();
          long endTime = endPlot.getTimeInMillis();
          ld.xpos=(int)(pixWidth*((1.0f*theTime-startTime)/(1.0f*endTime-startTime)));
        }
        if (ld.autoscale) {
          double min = Double.MAX_VALUE;
          double max = Double.MIN_VALUE;
          double theVal = 0;
          for (int j=0; j<ld.dataY.length; j++) {
            if (!ld.accumulate) {
              if (ld.log) theVal=StringTools.log10(ld.dataY[j]);
              else theVal=ld.dataY[j];
            } else {
              if (ld.log) theVal+=StringTools.log10(ld.dataY[j]);
              else theVal+=ld.dataY[j];
            }
            if ((theVal>max) && (!Double.isInfinite(theVal))) max=theVal;
            if ((theVal<min) && (!Double.isInfinite(theVal))) min=theVal;
            ld.max = max;
            ld.min = min;
          }
        }

        int maxAmplitude=0;
        if (!ld.invertY) maxAmplitude=(pixWidth-ld.xpos)/2;
        else maxAmplitude=-(ld.xpos/2);
        g.setColor(ld.colour);
        boolean physics=false;
        if (ld.layer==LineDefinition.PHYSICAL) physics=true;
        double screenTopDepth=0;
        double screenBottomDepth=500;
        if (!bc.usePlain()) {
          screenTopDepth = bc.getTopDepth();
          screenBottomDepth = bc.getBottomDepth();
        }
        int y1 = 0;
        int y2 = 0;
        int x1 = 0;
        int x2 = 0;
        double theVal = 0.0;
        int thisIndex = 0;
        int oldx = 0;
        int oldy = 0;
        g.setColor(ld.colour);
        for (int j=ld.topDepthIndex; j<ld.bottomDepthIndex; j++) {
          theVal = ld.dataY[j];
          thisIndex = j+ld.topDepthIndex;
          if (thisIndex>520) thisIndex=520;
          if (physics) y1 = (int) Math.floor(Plotter.physicalDepths[thisIndex]);
          else y1 = thisIndex;
          if (physics) y2 = (int) Math.floor(Plotter.physicalDepths[thisIndex+1]);
          else y2 = thisIndex+1;
        
          y1 = (int) (((y1 - screenTopDepth)/(screenBottomDepth-screenTopDepth))*pixHeight);
          y2 = (int) (((y2 - screenTopDepth)/(screenBottomDepth-screenTopDepth))*pixHeight);            
          x1 = ld.xpos;
          x2 = x1+((int)((theVal-ld.min)/(ld.max-ld.min)*maxAmplitude));
          if (j==ld.topDepthIndex) {
            oldx = x1;
            oldy = y1;
          }
          if (ld.dpfill) {
            for (int k=y1; k<=y2; k++) clipDraw(axisWidth+x1,axisHeight+k,axisWidth+x2,axisHeight+k,g);
          } else {
            clipDraw(axisWidth+oldx,axisHeight+oldy,axisWidth+x2,axisHeight+y2,g);
            oldx=x2;
            oldy=y2;
          }
        }
      }
    }
  }
  
  public void setBackgroundColour(int i) {
    BackgroundColour.setType(i);
    if (i!=ColourID) {
      i=ColourID;
      if (!bc.useContours())
        updateBackgroundPixels(null);
      else plotContours(null);
    } else {
      i=ColourID;
    }
  }
  
  public void plotPlainBackground() {
    Graphics graphics = theImage.getGraphics();
    graphics.setColor(bc.getPlainColour());
    graphics.fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
  }
  
  
  public void drawPolygonFromGeneralPath(Graphics g, GeneralPath gP) {
    //PathIterator pI = gP.getPathIterator(gP.get)
  }
  
  public void plotContours(Graphics g) {
    if (g==null) g = theImage.getGraphics();
    g.setColor(bc.getPlainColour());
    g.fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
    if (contours!=null) {
      int LineStyle;
      if ((fillContours) && (drawContourLines)) LineStyle = LineFill;
      else if ((fillContours) && (!drawContourLines)) LineStyle = PlainFill;
      else LineStyle = PlainLine;
      Graphics2D g2D = (Graphics2D) g;
      
      if((LineStyle & PlainFill) != 0) {
        for(int i = 0; i < contours.length; i++) {
          g2D.setColor(BackgroundColour.getColour(i,0,contours.length));
          if (i == 0) {
            g2D.setClip(axisWidth,axisHeight,pixWidth,pixHeight);
            g2D.fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
          } else if(i == 1) {
            g2D.setClip(axisWidth,axisHeight,pixWidth,pixHeight);
            g2D.fill(contours[0]);
          } else if(i != (contours.length - 1)) {
            g2D.setClip(contours[i - 2]);
            g2D.fill(contours[i - 1]);
          }
        }
      }
      
      if((LineStyle & PlainLine) != 0) {
        for(int i = 0; i < contours.length; i++) {
          //g2D.setStroke(LineStrokes[(i / 3) % LineStrokes.length]);
          if((LineStyle & PlainFill) != 0 && i != (contours.length - 1))
            g2D.setColor(Color.black);
          else
            g2D.setColor(BackgroundColour.getColour(i,0,contours.length));
          g2D.setClip(axisWidth,axisHeight,pixWidth,pixHeight);
          if(contours[i] != null) g2D.draw(contours[i]);
         // g2D.setStroke(LineStrokes[0]);          
        }
      
      } else {
        int LastPath = contours.length - 1;
        if(contours[LastPath] != null) {
          g2D.setColor(Color.red);
          g2D.setClip(axisWidth,axisHeight,pixWidth,pixHeight);
          g2D.draw(contours[LastPath]);
        }
      }
      g2D.setClip(0,0,pixWidth+(2*axisWidth),pixHeight+(2*axisHeight));      
    }
    
  } 
    public void updateBackgroundPixels(Graphics g) {
      if (g==null) g = theImage.getGraphics(); 
      if (bc!=null) {
        if (!bc.usePlain()) {
          
          double _minValue = Double.POSITIVE_INFINITY;
          double _maxValue = Double.NEGATIVE_INFINITY;
          double theVal = 0;
          if (bc.getAuto()) {
            for (int i=0; i<pixWidth; i++) {
              for (int j=0; j<pixHeight; j++) {
                theVal=screen[i][j];
                if (bc.getLog()) theVal=StringTools.log10(theVal);
                if ((theVal>_maxValue) && (!Double.isInfinite(theVal))) _maxValue=theVal;
                if ((theVal<_minValue) && (!Double.isInfinite(theVal))) _minValue=theVal;
              }
            }
            bc.setMinMax(_minValue,_maxValue);
          }
          _minValue = bc.getMin();
          _maxValue = bc.getMax();
          for (int i=0; i<pixWidth; i++) {
            for (int j=0; j<pixHeight; j++) {
              theVal = screen[i][j];
              if (bc.getLog()) theVal=StringTools.log10(theVal);
              if (theVal==Double.NEGATIVE_INFINITY) theVal=_minValue-1;
              g.setColor(BackgroundColour.getColour(theVal,_minValue,_maxValue));
              if (bc.getInvert()) g.drawLine(axisWidth+i,axisHeight+j,axisWidth+i+1,axisHeight+j);
              else g.drawLine(axisWidth+i,axisHeight+(pixHeight-j)-1,axisWidth+i+1,axisHeight+(pixHeight-j)-1);
            }
          }
      
          if (plotContours) {
            double[] xpix = new double[pixWidth];
            for (int i=0; i<pixWidth; i++) xpix[i]=i;
            double[] ypix = new double[pixHeight];
            for (int i=0; i<pixHeight; i++) ypix[i]=i;      
            int ngrads = 5;
            double[] grads = new double[ngrads];
            for (int i=0; i<ngrads; i++) grads[i]=_minValue+(((double)i/(double)ngrads)*(_maxValue-_minValue));
            //Plotter.contour(screen,0,pixWidth-1,0,pixHeight-1,xpix,ypix,ngrads,grads,(Graphics2D)g);
          }
        } else {
          g.setColor(bc.getPlainColour());
          g.fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
        }
      }
    }
  
  
  public void setValue(int x, int y, double val) {
    screen[x][y] = val;
  }
  public double getValue(int x, int y) {
    return screen[x][y];
  }
  
  public void plotEPS(LineChooser2 lc, String fn, int dx, int dy) {
    int oldPW = pixWidth;
    int oldPH = pixHeight;
    pixWidth = dx;
    pixHeight = dy;

    Properties p = new Properties();
    p.setProperty("PageSize","A5");
    VectorGraphics eps = null;
    try {
      eps = new PSGraphics2D(new File(fn), new Dimension(800,800));
    } catch (Exception e) {}
    eps.setProperties(p); 
    eps.startExport(); 

  
    
    if ((!bc.useContours()) && (bc.getData())) updateBackgroundPixels(eps);
    else if ((bc.useContours()) && (bc.getData())) plotContours(eps);
    else {
      eps.setColor(bc.getPlainColour());
      eps.fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
    }
    
    plotLines(eps,lc);
    plotXYPlot(eps,lc);
    plotDepthProfiles(eps,lc);
    plotAxes(eps, null);
    pixWidth = oldPW;
    pixHeight = oldPH;
    eps.endExport();
  }
  
  public void plotJPG(String dataPath, LineChooser2 lc, String fn, int dx, int dy, long timeStart, long timeEnd, long stepLength, VEW.Analyser4.ExportDialog ex) {
    double[][] oldScreen = new double[pixWidth][pixHeight];
    for (int i=0; i<pixWidth; i++) 
      for (int j=0; j<pixHeight; j++) 
        oldScreen[i][j]=screen[i][j];
      
    
    int oldPW = pixWidth;
    int oldPH = pixHeight;
    pixWidth = dx;
    pixHeight = dy;
    screen = new double[dx][dy];
    
    a4.thePlotter.exportOnlyBackground(dataPath, this, bc, timeStart, timeEnd, stepLength, a4.formatFile, ex, null, oldPW, oldPH,lc,fn,oldScreen);
  }
  
  public void realExportJPG(LineChooser2 lc, int oldPW, int oldPH, String fn, double[][] oldScreen ) { 
    BufferedImage newJPEG = null;
    try {
      newJPEG = new BufferedImage((axisWidth*2)+pixWidth,(axisHeight*2)+pixHeight,BufferedImage.TYPE_INT_RGB);
    } catch (Error e) { 
      if (e instanceof OutOfMemoryError) {
        JOptionPane.showMessageDialog(this,"Out of memory error. Choose a smaller pixel size");
      }
    }
    if (newJPEG!=null) {
      newJPEG.getGraphics().setColor(getBackground());
      newJPEG.getGraphics().fillRect(0,0,(axisWidth*2)+pixWidth,(axisHeight*2)+pixHeight);
      if ((!bc.useContours()) && (bc.getData())) updateBackgroundPixels(newJPEG.getGraphics());
      else if ((bc.useContours()) && (bc.getData())) plotContours(newJPEG.getGraphics());
      else {
        newJPEG.getGraphics().setColor(bc.getPlainColour());
        newJPEG.getGraphics().fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
      }
    
      plotLines(newJPEG.getGraphics(),lc);
      plotXYPlot(newJPEG.getGraphics(),lc);
      plotDepthProfiles(newJPEG.getGraphics(),lc);
           
      plotAxes(newJPEG.getGraphics(), theImage.getGraphics());
      try {
        ImageOutputStream ios = ImageIO.createImageOutputStream(new File(fn));

        ImageWriter writer = null;
        Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
        if (iter.hasNext()) {
            writer = (ImageWriter)iter.next();
        }

        writer.setOutput(ios);
        ImageWriteParam iwparam = writer.getDefaultWriteParam();
        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT) ;
        iwparam.setCompressionQuality(1.0f) ;
        writer.write(null, new IIOImage(newJPEG, null, null), iwparam);
        ios.flush();
        writer.dispose();
        ios.close();
      } catch (Exception e) { e.printStackTrace(); }
    }
    pixWidth = oldPW;
    pixHeight = oldPH;
    screen = new double[pixWidth][pixHeight];
    for (int i=0; i<pixWidth; i++) 
      for (int j=0; j<pixHeight; j++)
        screen[i][j]=oldScreen[i][j];
      
  }
  
  public void replotGraph(LineChooser2 lc,GregorianCalendar t1, GregorianCalendar t2) {
    startPlot.setTimeInMillis(t1.getTimeInMillis());
    endPlot.setTimeInMillis(t2.getTimeInMillis());    
    Graphics imageG = theImage.getGraphics();
    imageG.setColor(getBackground());
    imageG.fillRect(0,0,(axisWidth*2)+pixWidth,(axisHeight*2)+pixHeight);
    if ((!bc.useContours()) && (bc.getData())) updateBackgroundPixels(imageG);
    else if ((bc.useContours()) && (bc.getData())) plotContours(imageG);
    else {
      imageG.setColor(bc.getPlainColour());
      imageG.fillRect(axisWidth,axisHeight,pixWidth,pixHeight);
    }
    plotLines(imageG,lc);
    plotXYPlot(imageG,lc);
    plotDepthProfiles(imageG,lc);
    plotAxes(imageG, null);

  }

  
  public void paintComponent(Graphics g) {
    g.drawImage(theImage,0,0,new ImageObserver() { 
      public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) { return true; }
    });
        
  }
}
