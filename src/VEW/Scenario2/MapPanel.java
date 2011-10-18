package VEW.Scenario2; 

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import VEW.Analyser4.BackgroundColour;

public class MapPanel extends JPanel implements Observer {
  private static BufferedImage MapImage = FileIO.FetchMapPicture(); // Res = 8640x2160
  private static BufferedImage MaskImage = FileIO.FetchMaskPicture(); // Res = 8640x2160
  private static final String IconRoot = "Data/Graphics/icons/";
  private boolean Locked = false;

  // Map Panel modes
  private static ScenarioPanel2 sp2 = null;
  public static final int AddPointMode = 0;
  public static final int ZoomInMode   = 2;
  public static final int ZoomOutMode  = 3;

  private boolean MouseIn = false;

  private int[] MapBoundaries = new int[4];

  private int currentMode = AddPointMode;

  private MapMonitor thisListener = new MapMonitor();
  private MapChangeNotifier thisNotifier = new MapChangeNotifier();

  private double Latitude = 90.00;
  private double Longitude = -180.00;

  private int StartDay;
  private int CollectedPoints = 0;

  private Point2D.Double StartPoint;

  private DecimalFormat TwoDecPlaces = new DecimalFormat("0.##");

  private TrackPosition[] GeneratedTrack;

  // Custom Mouse Cursors
  private static Cursor ZoomInCursor;
  private static Cursor ZoomOutCursor;
  private Cursor CrosshairCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);

  private GeneralPath VisualPath = new GeneralPath();
  //private ArrayList KeyPoints = new ArrayList();

  private Generator TrackGenerator;

  private boolean GoForward = true;
  
  public final static String MAP_WORLD = new String("World");
  public final static String MAP_NORTH_ATLANTIC = new String("North Atlantic");
  public final static String MAP_NEAR_GOOS = new String("NEAR GOOS");
  public final static String MAP_SEA_GOOS = new String("SEA GOOS");
  public final static String MAP_IO_GOOS = new String("IO GOOS");
  public final static String MAP_AFRICA_GOOS = new String("Africa GOOS");
  public final static String MAP_BLACK_SEA_GOOS = new String("Black Sea GOOS"); 
  public final static String MAP_EURO_GOOS = new String("Euro GOOS");
  public final static String MAP_MED_GOOS = new String("Med GOOS");
  public final static String MAP_PI_GOOS = new String("PI GOOS"); 
  public final static String MAP_IOCARIBE_GOOS = new String("IOCARIBE GOOS");
  public final static String MAP_US_GOOS = new String("US GOOS");
  public final static String MAP_GRASP = new String("GRASP"); 
  public final static String MAP_SW_TOP_ATLANTIC = new String("SW/Trop Atlantic");
  private static final GeneralPath ArrowHead, ArrowTail;
  
  public boolean useOverlay = false;
  static {
    ArrowHead = new GeneralPath();
    ArrowHead.moveTo(22.5f,12.5f);
    ArrowHead.lineTo(12.5f,10f);
    ArrowHead.lineTo(15f,12.5f);
    ArrowHead.lineTo(12.5f,15f);
    ArrowHead.closePath();
    ArrowTail = new GeneralPath();
    ArrowTail.moveTo(2.5f,12.5f);
    ArrowTail.lineTo(22.5f,12.5f);
  }

  
  public static void writeString(ZipOutputStream zos, String s) {
    final byte[] bytes = new byte[s.length()];
    for (int i=0; i<bytes.length; i++) bytes[i]=(byte)(s.charAt(i));
    try {
      zos.write(bytes);
    } catch (Exception e) { e.printStackTrace(); }
  }
      
  
  
  
  public static void showGoogleEarth(double[][] data, String tempDir, boolean mask) {
    // Create a .kmz file for GoogleEarth.
    // NB, As this file could get huge, I'm avoiding using the XML libraries.
    // I don't want this file built in RAM!
    
    double min=Double.POSITIVE_INFINITY; 
    double max=Double.NEGATIVE_INFINITY;
    for (int i=0; i<data.length; i++) {
      for (int j=0; j<data[i].length; j++) {
        if (data[i][j]>max) max=data[i][j];
        if (data[i][j]<min) min=data[i][j];
      }
    }
    
    try {
      ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tempDir+File.separator+"vew.kmz"));
      zos.putNextEntry(new ZipEntry("vew.kml"));
      writeString(zos,"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      writeString(zos,"<kml xmlns=\"http://earth.google.com/kml/2.0\">");
      writeString(zos,"<Document>");
      Color c;
      int lat,lon;
      for (int i=0; i<data.length; i++) {
        for (int j=0; j<data[i].length-1; j++) {
          if (i<180) lon=i; else lon=i-360;
          lat=90-j;
          
          final int col = (mask)?MaskImage.getRGB(i*12,j*12):-16777216;
          if (col==-16777216) {
            writeString(zos,"<Placemark>");
            writeString(zos,"<name>"+lon+","+lat+"</name>");
            writeString(zos,"<description>"+data[i][j]+"</description>");
            writeString(zos,"<Style><PolyStyle><outline>0</outline><color>ff");
            c = BackgroundColour.getColour(data[i][j],min,max);
            writeString(zos,Integer.toHexString(c.getBlue()));
            writeString(zos,Integer.toHexString(c.getGreen()));
            writeString(zos,Integer.toHexString(c.getRed()));
            writeString(zos,"</color></PolyStyle></Style><Polygon><extrude>0</extrude><altitudeMode>clampToGround</altitudeMode>");
            writeString(zos,"<outerBoundaryIs><LinearRing><coordinates>");
            writeString(zos,String.valueOf(lon-0.5)+","+String.valueOf(lat-0.5)+" ");
            writeString(zos,String.valueOf(lon+0.5)+","+String.valueOf(lat-0.5)+" ");
            writeString(zos,String.valueOf(lon+0.5)+","+String.valueOf(lat+0.5)+" ");
            writeString(zos,String.valueOf(lon-0.5)+","+String.valueOf(lat+0.5)+" ");
            writeString(zos,"</coordinates></LinearRing></outerBoundaryIs></Polygon></Placemark>");
          }
        }
      }
      writeString(zos,"</Document>");
      writeString(zos,"</kml>");
      zos.closeEntry();
      zos.flush();
      zos.close();
    } catch (Exception e) { e.printStackTrace(); }
  }
  
  public static void showGoogleEarthOCCAM(double[][] data, double[][] angle, String tempDir, boolean mask) {
    // Create a .kmz file for GoogleEarth.
    // NB, As this file could get huge, I'm avoiding using the XML libraries.
    // I don't want this file built in RAM!
    
    double min=Double.POSITIVE_INFINITY; 
    double max=Double.NEGATIVE_INFINITY;
    for (int i=0; i<data.length; i++) {
      for (int j=0; j<data[i].length; j++) {
        if (data[i][j]>max) max=data[i][j];
        if (data[i][j]<min) min=data[i][j];
      }
    }
    
    try {
      ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tempDir+File.separator+"vew.kmz"));
      zos.putNextEntry(new ZipEntry("vew.kml"));
      writeString(zos,"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      writeString(zos,"<kml xmlns=\"http://earth.google.com/kml/2.0\">");
      writeString(zos,"<Document>");
      double lat,lon;
      for (int i=0; i<data.length; i+=2) {
        for (int j=0; j<data[i].length; j+=2) {
          if (i<720) lon=i; else lon=i-1440.0;
          lat=90.0-(j/4.0);
          final int col = (mask)?MaskImage.getRGB((i*3),j*3):-16777216;
          if (col==-16777216) {
            double theAngle=angle[i][j];
            if ((!Double.isNaN(theAngle)) && (!Double.isInfinite(theAngle))) {
              writeString(zos,"<Placemark>");
              writeString(zos,"<name>"+lon+","+lat+"</name>");
              writeString(zos,"<description>"+data[i][j]+"</description>");
              writeString(zos,"<Style><PolyStyle><outline>0</outline><color>ff");
              final int lum = (int) (255-(((1.0*(data[i][j]-min))/((1.0*max-min)))*255.0));
              writeString(zos,Integer.toHexString(lum));
              writeString(zos,Integer.toHexString(lum));
              writeString(zos,Integer.toHexString(lum));
              writeString(zos,"</color></PolyStyle></Style>");
              writeString(zos,"<Polygon><altitudeMode>clampToGround</altitudeMode>");
              writeString(zos,"<outerBoundaryIs><LinearRing><coordinates>");
              final double tipX = (lon+(0.075*(Math.cos(theAngle))));
              final double tipY = (lat+(0.075*(Math.sin(theAngle))));
              final double leftX = (lon-(0.0375*(Math.cos(theAngle-0.4))));
              final double leftY = (lat-(0.0375*(Math.sin(theAngle-0.4))));
              final double rightX = (lon-(0.0375*(Math.cos(theAngle+0.4))));
              final double rightY = (lat-(0.0375*(Math.sin(theAngle+0.4))));
              writeString(zos,String.valueOf(tipX)+","+String.valueOf(tipY)+" ");
              writeString(zos,String.valueOf(leftX)+","+String.valueOf(leftY)+" ");
              writeString(zos,String.valueOf(rightX)+","+String.valueOf(rightY)+" ");
              writeString(zos,String.valueOf(tipX)+","+String.valueOf(tipY)+" ");
              writeString(zos,"</coordinates></LinearRing></outerBoundaryIs>");
              writeString(zos,"</Polygon></Placemark>");
            }
          }
        }
      }
      writeString(zos,"</Document>");
      writeString(zos,"</kml>");
      zos.closeEntry();
      zos.flush();
      zos.close();
    } catch (Exception e) { e.printStackTrace(); }
  }

  public static void showGoogleEarth(double[][] data, double[][] angle, String tempDir, boolean mask) {
    // Create a .kmz file for GoogleEarth.
    // NB, As this file could get huge, I'm avoiding using the XML libraries.
    // I don't want this file built in RAM!

    double min=Double.POSITIVE_INFINITY; 
    double max=Double.NEGATIVE_INFINITY;
    for (int i=0; i<data.length; i++) {
      for (int j=0; j<data[i].length; j++) {
        if (data[i][j]>max) max=data[i][j];
        if (data[i][j]<min) min=data[i][j];
      }
    }
    
    try {
      ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(tempDir+File.separator+"vew.kmz"));
      zos.putNextEntry(new ZipEntry("vew.kml"));
      writeString(zos,"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      writeString(zos,"<kml xmlns=\"http://earth.google.com/kml/2.0\">");
      writeString(zos,"<Document>");
      
      double lat,lon;
      for (int i=0; i<data.length; i++) {
        for (int j=0; j<data[i].length; j++) {
          if (i<180) lon=i; else lon=i-360.0;
          lat=90.0-j;
          final int col = (mask)?MaskImage.getRGB(i*12,j*12):-16777216;
          if (col==-16777216) {
            writeString(zos,"<Placemark>");
            writeString(zos,"<name>"+lon+","+lat+"</name>");
            writeString(zos,"<description>"+data[i][j]+"</description>");
            writeString(zos,"<Style><PolyStyle><outline>0</outline><color>ff");
            final int lum = (int) (255-(((1.0*(data[i][j]-min))/((1.0*max-min)))*255.0));
            writeString(zos,Integer.toHexString(lum));
            writeString(zos,Integer.toHexString(lum));
            writeString(zos,Integer.toHexString(lum));
            
            writeString(zos,"</color></PolyStyle></Style>");
            writeString(zos,"<Polygon><altitudeMode>clampToGround</altitudeMode>");
            writeString(zos,"<outerBoundaryIs><LinearRing><coordinates>");
            double theAngle=angle[i][j];
            if (Double.isNaN(theAngle)) theAngle=0;
            final double tipX = (lon+(0.45*(Math.cos(theAngle))));
            final double tipY = (lat+(0.45*(Math.sin(theAngle))));
            final double leftX = (lon-(0.25*(Math.cos(theAngle-0.4))));
            final double leftY = (lat-(0.25*(Math.sin(theAngle-0.4))));
            final double rightX = (lon-(0.25*(Math.cos(theAngle+0.4))));
            final double rightY = (lat-(0.25*(Math.sin(theAngle+0.4))));
            writeString(zos,String.valueOf(tipX)+","+String.valueOf(tipY)+" ");
            writeString(zos,String.valueOf(leftX)+","+String.valueOf(leftY)+" ");
            writeString(zos,String.valueOf(rightX)+","+String.valueOf(rightY)+" ");
            writeString(zos,String.valueOf(tipX)+","+String.valueOf(tipY)+" ");
            writeString(zos,"</coordinates></LinearRing></outerBoundaryIs>");
            writeString(zos,"</Polygon></Placemark>");
          }
        }
      }
      writeString(zos,"</Document>");
      writeString(zos,"</kml>");
      zos.closeEntry();
      zos.flush();
      zos.close();
    } catch (Exception e) { e.printStackTrace(); }
  }

  
  
  
  public ArrayList setOverlay(double[][] data, boolean mask) {
    ArrayList a = new ArrayList();
    useOverlay=(data!=null);
    MapImage = FileIO.FetchMapPicture();
    if (useOverlay) {
      double min=Double.POSITIVE_INFINITY; 
      double max=Double.NEGATIVE_INFINITY;
      for (int i=0; i<data.length; i++) {
        for (int j=0; j<data[i].length; j++) {
          if (data[i][j]>max) max=data[i][j];
          if (data[i][j]<min) min=data[i][j];
        }
      }
      a.add(new Double(max));
      a.add(new Double(min));
      int iCoord,jCoord;
      Color c;
      int width = MapImage.getWidth();
      int height = MapImage.getHeight();
      for (int i=0; i<width; i++) { 
        for (int j=0; j<height; j++) {
          if (i<4320) iCoord=(i/12);
          else iCoord=(i-4320)/12;
          jCoord = j/12;
          c = BackgroundColour.getColour(data[iCoord][jCoord],min,max);
          if (mask) {
            int col = MaskImage.getRGB(i,j);
            if (col>-16777216) 
            c = new Color(col);
          }
          MapImage.setRGB(i,j,c.getRGB());
        }
      }
    }
    return a;
  }
  
  public ArrayList setOverlay(double[][] mag, double[][] angle, boolean mask) {
    ArrayList a = new ArrayList();
    useOverlay=(mag!=null);
    MapImage = FileIO.FetchMapPicture();
    Graphics g = MapImage.getGraphics();
    if (useOverlay) {
      double min=Double.POSITIVE_INFINITY; 
      double max=Double.NEGATIVE_INFINITY;
      for (int i=0; i<mag.length; i++) {
        for (int j=0; j<mag[i].length; j++) {
          if (mag[i][j]>max) max=mag[i][j];
          if (mag[i][j]<min) min=mag[i][j];
        }
      }
      a.add(new Double(max));
      a.add(new Double(min));
      int iCoord,jCoord;
      int width = MapImage.getWidth();
      int height = MapImage.getHeight();
      for (int i=0; i<width; i++) { 
        for (int j=0; j<height; j++) {
          if (i<4320) iCoord=(i/12);
          else iCoord=(i-4320)/12;
          jCoord = j/12;
          MapImage.setRGB(i,j,BackgroundColour.getColour(mag[iCoord][jCoord],min,max).getRGB());
        }
      }
      g.setColor(Color.black);
      int baseX,baseY,tipX,tipY,leftX,leftY,rightX,rightY;
      double theAngle = 0;
      int centreX,centreY;
      for (int i=0; i<width; i+=12) { 
        for (int j=0; j<height; j+=12) {
          if (i<4320) iCoord=(i/12);
          else iCoord=(i-4320)/12;
          jCoord = j/12;
          centreX=i+6;
          centreY=j+6;
          theAngle=angle[iCoord][jCoord];
          
          baseX = (int) (centreX-(5*(Math.cos(theAngle))));
          baseY = (int) (centreY-(5*(Math.sin(theAngle))));
          tipX = (int) (centreX+(5*(Math.cos(theAngle))));
          tipY = (int) (centreY+(5*(Math.sin(theAngle))));
          leftX = (int) (centreX+(5*(Math.cos(1.3+theAngle))));
          leftY = (int) (centreY+(5*(Math.sin(1.3+theAngle))));
          rightX = (int) (centreX+(5*(Math.cos(theAngle-1.3))));
          rightY = (int) (centreY+(5*(Math.sin(theAngle-1.3))));
          
          g.drawLine(baseX,baseY,tipX,tipY);
          g.fillPolygon(new int[] {leftX,tipX,rightX}, new int[] {leftY,tipY,rightY},3);
        }
      }
      if (mask) {
        for (int i=0; i<width; i++) { 
          for (int j=0; j<height; j++) {
            if (i<4320) iCoord=(i/12);
            else iCoord=(i-4320)/12;
            jCoord = j/12;
            int col = MaskImage.getRGB(i,j);
            if (col>-16777216) { 
              Color c = new Color(col);
              MapImage.setRGB(i,j,c.getRGB());
            }
          }
        }
      }
    }
    return a;
  }
  
  public ArrayList setOccamOverlay(double[][] mag, double[][] angle, boolean mask) {
    ArrayList a = new ArrayList();
    useOverlay=(mag!=null);
    MapImage = FileIO.FetchMapPicture();
    Graphics g = MapImage.getGraphics();
    if (useOverlay) {
      double min=Double.POSITIVE_INFINITY; 
      double max=Double.NEGATIVE_INFINITY;
      for (int i=0; i<mag.length; i++) {
        for (int j=0; j<mag[i].length; j++) {
          if (!Double.isInfinite(mag[i][j])) {
            if (mag[i][j]>max) max=mag[i][j];
            if (mag[i][j]<min) min=mag[i][j];
          }
        }
      }
      a.add(new Double(max));
      a.add(new Double(min));
      int iCoord,jCoord;
      int width = MapImage.getWidth();
      int height = MapImage.getHeight();
      for (int i=0; i<width; i++) { 
        for (int j=0; j<height; j++) {
          if (i<4320) iCoord=(i/3);
          else iCoord=(i-4320)/3;
          jCoord = j/3;
          MapImage.setRGB(i,j,BackgroundColour.getColour(mag[iCoord][jCoord],min,max).getRGB());
        }
      }
      g.setColor(Color.black);
      int baseX,baseY,tipX,tipY;
      double theAngle = 0;
      int centreX,centreY;
      for (int i=0; i<width; i+=3) { 
        for (int j=0; j<height; j+=3) {
          if (i<4320) iCoord=(i/3);
          else iCoord=(i-4320)/3;
          jCoord = j/3;
          centreX=i+1;
          centreY=j+1;
          theAngle=angle[iCoord][jCoord];
          
          baseX = (int) (centreX-(1*(Math.cos(theAngle))));
          baseY = (int) (centreY-(1*(Math.sin(theAngle))));
          tipX = (int) (centreX+(1*(Math.cos(theAngle))));
          tipY = (int) (centreY+(1*(Math.sin(theAngle))));
          g.setColor(Color.black);
          g.drawLine(baseX,baseY,tipX,tipY);
          g.setColor(Color.RED);
          g.drawLine(tipX,tipY,tipX,tipY);
        }
      }
      if (mask) {
        for (int i=0; i<width; i++) { 
          for (int j=0; j<height; j++) {
            if (i<4320) iCoord=(i/12);
            else iCoord=(i-4320)/12;
            jCoord = j/12;
            int col = MaskImage.getRGB(i,j);
            if (col>-16777216) { 
              Color c = new Color(col);
              MapImage.setRGB(i,j,c.getRGB());
            }
          }
        }
      }
    }
    return a;
  }
  
    

  public void drawArrow(BufferedImage map, int i, int j, double Angle) {
    AffineTransform ScaleAndRotate = AffineTransform.getScaleInstance(1, 1);
    ScaleAndRotate.rotate(Angle,12.5,12.5);
    GeneralPath AngledHead = (GeneralPath)ArrowHead.clone();
    GeneralPath AngledTail = (GeneralPath)ArrowTail.clone();
    AngledHead.transform(ScaleAndRotate);
    AngledTail.transform(ScaleAndRotate);
    AffineTransform location = AffineTransform.getTranslateInstance(i,j);
    AngledHead.transform(location);
    AngledTail.transform(location);
    Graphics2D drawer = (Graphics2D)map.getGraphics();
    drawer.setColor(Color.black);
    drawer.draw(AngledTail);
    drawer.fill(AngledHead);
  }

  
  public static void addMaps(JComboBox j) {
    j.addItem(MAP_WORLD);
    j.addItem(MAP_NORTH_ATLANTIC);
    j.addItem(MAP_NEAR_GOOS);
    j.addItem(MAP_SEA_GOOS);
    j.addItem(MAP_IO_GOOS);
    j.addItem(MAP_AFRICA_GOOS);
    j.addItem(MAP_BLACK_SEA_GOOS);
    j.addItem(MAP_EURO_GOOS);
    j.addItem(MAP_MED_GOOS);
    j.addItem(MAP_PI_GOOS);
    j.addItem(MAP_IOCARIBE_GOOS);
    j.addItem(MAP_US_GOOS);
    j.addItem(MAP_GRASP);
    j.addItem(MAP_SW_TOP_ATLANTIC);
  }
  
  public MapPanel(ScenarioPanel2 _sp2) {
    setDoubleBuffered(true);
    sp2=_sp2;
    initialiseCursors();
    setCursor(CrosshairCursor);
    this.addMouseListener(thisListener);
    this.addMouseMotionListener(thisListener);
    changeViewWindow(180, 90, 360, 180);
  }

  
  public void changeViewWindow(int W, int N, int Width, int Height) {
    MapBoundaries[0] = 360 - W;
    while (MapBoundaries[0] > 540) MapBoundaries[0] -= 360;
    while (MapBoundaries[0] < 0) MapBoundaries[0] += 360;
    MapBoundaries[1] = 90 - N;
    if (MapBoundaries[1] < 0) MapBoundaries[1] = 0;
    MapBoundaries[2] = Width;
    if (MapBoundaries[2] > 360) MapBoundaries[2] = 360;
    if (MapBoundaries[2] + MapBoundaries[0] > 720) MapBoundaries[0] -= 360;
    MapBoundaries[3] = Height;
    if (MapBoundaries[3] > 180) MapBoundaries[3] = 180;
    if (MapBoundaries[3] + MapBoundaries[1] > 180) MapBoundaries[1] = 180 - MapBoundaries[3];
    for (int i = 0; i < 4; i++) MapBoundaries[i] *= 12;
   
  }

  public void changeViewCoords(int N, int S, int E, int W) {
    int[] TempArray = ConvertCoordsToBounds(N,S,W,E);
    for(int i = 0; i < 4; i++) MapBoundaries[i] = TempArray[i];
    
  }
  
  public static int[] ConvertCoordsToBounds(int N, int S, int W, int E) {
    int[] TempInt = new int[4];
    TempInt[0] = 360 - W;
    TempInt[1] = 90 - N;
    TempInt[2] = W + E;
    TempInt[3] = N + S;
    if (TempInt[2] < 0) TempInt[2] += 360;
    for (int i = 0; i < 4; i++) TempInt[i] *= 12;
    return TempInt;
  }

  public void setLock(boolean LockStatus) {
    Locked = LockStatus;
  }

   public void addObserver(Observer ob) {
      thisNotifier.addObserver(ob);
   }

  public void GoForth(boolean _GoForward) {
    GoForward = _GoForward;
    repaint();
  }

   public void GenerateTrack(int StartMonth, int _StartDay, int RunDuration, String IntegrationMethod, String VelocityName) {
     GoForward = (!IntegrationMethod.equals(Generator.INT_BWD));
     GeneratedTrack = new TrackPosition[RunDuration + 1];
     StartDay = _StartDay;
     if ((IntegrationMethod.equals(Generator.INT_FIX)) || (FileIO.checkForExistence("Velocities"+File.separator+Generator.velFiles[Generator.findVelocity(VelocityName)]))) {
       TrackGenerator = new Generator(StartMonth, StartDay, IntegrationMethod, RunDuration+1, new TrackPosition(StartPoint.getY(), StartPoint.getX()), VelocityName);
       TrackGenerator.addObserver(this);
       TrackGenerator.start();
     } else {
       sp2.generateButton.setEnabled(true);
       sp2.setLongitude.setEnabled(true);
       sp2.setLatitude.setEnabled(true);
       GeneratedTrack = null;
       setLock(false);
     }
   }

   public TrackPosition[] getGeneratedTrack() {
      return GeneratedTrack;
   }

   public static Point2D.Double GridToLatLon(Point gridPoint, int[] MapBounds, Rectangle2D.Double DisplayBounds) {
      double lat = 1080 - MapBounds[1];
      lat -= (gridPoint.getY() - DisplayBounds.y) * MapBounds[3] / DisplayBounds.height;
      lat /= 12.0;
      double lon = MapBounds[0];
      lon += (gridPoint.getX() - DisplayBounds.x) * MapBounds[2] / DisplayBounds.width;
      lon /= 12.0;
      if(lon > 180)  lon -= 360;
      return new Point2D.Double(-1 * lon, lat);
   }

   public static Point2D.Double LatLonToGrid(Point2D.Double latLonPoint, int[] MapBounds, Rectangle2D.Double DisplayBounds) {

     double x = (360 - latLonPoint.getX()) * 12;
     while (x < MapBounds[0]) x += 4320;
     x -= MapBounds[0];
     x *= DisplayBounds.width;
     x /= MapBounds[2];
     x += DisplayBounds.x;

     double y = 1080 - MapBounds[1];
     y -= (latLonPoint.getY() * 12);
     y *= DisplayBounds.height;
     y /= MapBounds[3];
     y += DisplayBounds.y;
     return new Point2D.Double(x, y);
   }

   public double getLatitude() { return Latitude; }
   public double getLongitude() { return Longitude; }
   public String getLatitudeString() { return MouseIn ? TwoDecPlaces.format(Latitude) : "XXX"; }
   public String getLongitudeString() { return MouseIn ? TwoDecPlaces.format(Longitude) : "XXX"; }

   public void setMode(int newMode) {
     if (currentMode != newMode) {
       currentMode = newMode;
       if (currentMode==AddPointMode) setCursor(CrosshairCursor);
       else if (currentMode==ZoomInMode) setCursor(ZoomInCursor);
       else if (currentMode==ZoomOutMode) setCursor(ZoomOutCursor);
     }
   }

   public void addPoint(double latitude, double longitude) {
     StartPoint = new Point2D.Double(latitude, longitude);
     repaint();
     thisNotifier.NotifyPointSet();
   }

   public void removeAllPoints() {
     StartPoint = null;
     VisualPath.reset();
     GeneratedTrack = null;
     CollectedPoints = 0;
     repaint();
   }

   protected void initialiseCursors() {
     Toolkit defaultKit = Toolkit.getDefaultToolkit();
     try {
        ZoomInCursor = defaultKit.createCustomCursor(ImageIO.read(new File(IconRoot + "zoomin.gif")), new Point(5, 5), "ZoomIn");
        ZoomOutCursor = defaultKit.createCustomCursor(ImageIO.read(new File(IconRoot + "zoomout.gif")), new Point(5, 5), "ZoomOut");
     } catch(Exception e) {}
   }
   
  public static void paintMapTrack(Graphics2D outputGraphics, int[] MapBounds, TrackPosition[] Track, Rectangle2D.Double DisplayBounds, boolean GoForward, int CollectedPoints, int StartDay, int StartYear) {
    //Rectangle2D.Double nextToFill = new Rectangle2D.Double(0, 0, 1,1);
    // Draw the map.
    BufferedImage ImageToShow = MapImage.getSubimage(MapBounds[0], MapBounds[1], MapBounds[2], MapBounds[3]);
    outputGraphics.drawImage(ImageToShow, (int)DisplayBounds.x, (int)DisplayBounds.y, (int)DisplayBounds.width, (int)DisplayBounds.height, null);
    // Draw the track.
    int DayMover = GoForward ? 1 : -1;
    //CollectedPoints = GeneratedTrack.length);
    GeneralPath TempPath = new GeneralPath();
    if (Track[0]!=null) {
      Point2D.Double LatLonPoint = LatLonToGrid(Track[0].getCoordinates(), MapBounds, DisplayBounds);
      double LastX = LatLonPoint.getX();
      double Distance = 0;
      TempPath.moveTo((float)LatLonPoint.getX(), (float)LatLonPoint.getY());
      for(int i = 1; i < CollectedPoints; i++) {
        LatLonPoint = LatLonToGrid(Track[i].getCoordinates(), MapBounds, DisplayBounds);
        Distance = Math.abs(LatLonPoint.getX() - LastX);
        if (Distance < 0) Distance *= -1;
        if (Distance >= DisplayBounds.width - 1) TempPath.moveTo((float)LatLonPoint.getX(), (float)LatLonPoint.getY());
        else TempPath.lineTo((float)LatLonPoint.getX(), (float)LatLonPoint.getY());
        LastX = LatLonPoint.getX();
      }
    
      outputGraphics.setColor(Color.black);
      outputGraphics.draw(TempPath);
      outputGraphics.setColor(Color.orange);
      int DayOfYear = StartDay - DayMover;
      int TrackPointer = 0;
    // Mark the intermediate years
      while(TrackPointer < CollectedPoints) {
        DayOfYear += DayMover;
        TrackPointer++;
        if (DayOfYear==366) DayOfYear = 1;
        else if (DayOfYear==0) DayOfYear=365;
        if (DayOfYear == 1 && TrackPointer < (Track.length - 1) && TrackPointer != 1) {
          LatLonPoint = LatLonToGrid(Track[TrackPointer].getCoordinates(), MapBounds, DisplayBounds);
          markPoint(outputGraphics, LatLonPoint, 2);
        }
      }
      // Mark the first track point
      outputGraphics.setColor(GoForward ? Color.yellow : Color.red);
      LatLonPoint = LatLonToGrid(Track[0].getCoordinates(), MapBounds, DisplayBounds);
      markPoint(outputGraphics, LatLonPoint, 4);
      if(CollectedPoints == Track.length) { // If we have it, mark the last track point
        outputGraphics.setColor(GoForward ? Color.red : Color.yellow);
        TrackPointer--;
        LatLonPoint = LatLonToGrid(Track[TrackPointer].getCoordinates(), MapBounds, DisplayBounds);
        markPoint(outputGraphics, LatLonPoint, 4);
      }
    }
  }

     
  protected void paintComponent(Graphics g) {
    Graphics2D g2D = (Graphics2D)g;
    Rectangle2D.Double DisplayBounds = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
    if(GeneratedTrack != null && CollectedPoints > 0)
      paintMapTrack(g2D, MapBoundaries, GeneratedTrack, DisplayBounds, GoForward, CollectedPoints, StartDay, 2000);//StartYear);
    else if(StartPoint != null) { // Mark start point.
      BufferedImage ImageToShow = MapImage.getSubimage(MapBoundaries[0], MapBoundaries[1], MapBoundaries[2], MapBoundaries[3]);
      g2D.drawImage(ImageToShow,0,0,getWidth(),getHeight(),null);
      Point2D.Double LatLonPoint = LatLonToGrid(StartPoint, MapBoundaries, DisplayBounds);
      g2D.setColor(GoForward ? Color.yellow : Color.red);
      markPoint(g2D, LatLonPoint, 4);
    } else {
      BufferedImage ImageToShow = MapImage.getSubimage(MapBoundaries[0], MapBoundaries[1], MapBoundaries[2], MapBoundaries[3]);
      g2D.drawImage(ImageToShow,0,0,getWidth(),getHeight(),null);
    }
  }

  private static void markPoint(Graphics2D g2D, Point2D.Double Coords, int Size) {
    double X = Coords.getX();
    double Y = Coords.getY();
    g2D.fill(new Rectangle2D.Double(X - Size, Y, 1 + (2 * Size), 1));
    g2D.fill(new Rectangle2D.Double(X, Y - Size, 1, 1 + (2 * Size)));
    
  }

   public void changeRegion(String region) {
    if (region.equals(MAP_WORLD)) changeViewCoords(90,90,180,180);
    else if (region.equals(MAP_NORTH_ATLANTIC)) changeViewCoords(75,15,15,100);
    else if (region.equals(MAP_NEAR_GOOS)) changeViewCoords(56,-20,173,-115);
    else if (region.equals(MAP_SEA_GOOS)) changeViewCoords(21,17,147,-92);
    else if (region.equals(MAP_IO_GOOS)) changeViewCoords(19,54,105,-35);
    else if (region.equals(MAP_AFRICA_GOOS)) changeViewCoords(32,49,51,23);
    else if (region.equals(MAP_BLACK_SEA_GOOS)) changeViewCoords(47,-35,49,-30);
    else if (region.equals(MAP_EURO_GOOS)) changeViewCoords(90,-37,59,34);
    else if (region.equals(MAP_MED_GOOS)) changeViewCoords(46,-30,32,13);
    else if (region.equals(MAP_PI_GOOS)) changeViewCoords(5,50,-149,-143);
    else if (region.equals(MAP_IOCARIBE_GOOS)) changeViewCoords(27,9,-32,103);
    else if (region.equals(MAP_US_GOOS)) changeViewCoords(72,-25,-42,143);
    else if (region.equals(MAP_GRASP)) changeViewCoords(-10,75,-63,100);
    else if (region.equals(MAP_SW_TOP_ATLANTIC)) changeViewCoords(-10,65,-29,77);
    repaint();
  }
 
  // Has to be implemented for the Observer interface
  public void update(Observable o, Object arg) {
    if (arg != null) {
      if(arg.getClass().getName().equals("VEW.Scenario.TrackPosition")) {
        GeneratedTrack[CollectedPoints] = (TrackPosition)arg;
        CollectedPoints++;
        repaint();
      } else if (arg.getClass().getName().equals("java.lang.Integer")) {
        Number CodeNumber = (Number)arg;
        if(CodeNumber.intValue() == 0) { // Run Aborted
          GeneratedTrack = null;
          CollectedPoints = 0;
          repaint();
          thisNotifier.sendCode(0);
          
        } else if (CodeNumber.intValue() == 1) { // Run Completed
          sp2.generateButton.setEnabled(true);
          sp2.setLongitude.setEnabled(true);
          sp2.setLatitude.setEnabled(true);
          setLock(false);
          GeneratedTrack = TrackGenerator.getGeneratedTrack(false);
          if (GeneratedTrack != null) CollectedPoints = GeneratedTrack.length;
          GoForward = true;
          repaint();
          thisNotifier.sendCode(1);
        }
      }
    }
  }

  public void abortGeneration() { TrackGenerator.abort(); }

  private class MapChangeNotifier extends Observable {
    public MapChangeNotifier() { super(); }

    public void makeDirtyAndNotify() {
      setChanged();
      notifyObservers();
    }

    public void NotifyPointSet() {
      setChanged();
      notifyObservers(StartPoint);
    }

    public void sendCode(int CodeNumber) {
      setChanged();
      notifyObservers(new Integer(CodeNumber));
    }
  }

  private class MapMonitor extends MouseInputAdapter {
    public MapMonitor() { super(); }

    public void mouseClicked(MouseEvent e) {
      if (currentMode == AddPointMode) {
        if (!Locked) {
          removeAllPoints();          
          StartPoint = GridToLatLon(e.getPoint(), MapBoundaries, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));// = new Point2D.Double(e.getX(), e.getY());
          thisNotifier.NotifyPointSet();
          repaint();
        }
      }
      else if (currentMode == ZoomInMode) {
        Point2D.Double centerPoint = GridToLatLon(e.getPoint(), MapBoundaries, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        int Y = (int)centerPoint.getY();
        int X = (int)centerPoint.getX();
        int newWidth  = MapBoundaries[2] / 36;
        int newHeight = MapBoundaries[3] / 36;
        if (newWidth < 15) newWidth = 15;
        if (newHeight < 15) newHeight = 15;
        changeViewWindow(X + (newWidth / 2), Y + (newHeight / 2), newWidth, newHeight);
        repaint();
      } else if (currentMode == ZoomOutMode) {
        Point2D.Double centerPoint = GridToLatLon(e.getPoint(), MapBoundaries, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        int Y = (int)centerPoint.getY();
        int X = (int)centerPoint.getX();
        int newWidth  = MapBoundaries[2] / 8;
        if(newWidth > 360) newWidth = 360;
        int newHeight = MapBoundaries[3] / 8;
        changeViewWindow(X + (newWidth / 2), Y + (newHeight / 2), newWidth, newHeight);
        repaint();
      }
    }

    public void mouseMoved(MouseEvent e) {
      Point2D.Double convE = GridToLatLon(e.getPoint(), MapBoundaries, new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
      Longitude = convE.getX();
      Latitude = convE.getY();
      MouseIn = true;
      thisNotifier.makeDirtyAndNotify();
    }

    public void mouseExited(MouseEvent e) {
      MouseIn = false;
      thisNotifier.makeDirtyAndNotify();
    }
  }
}