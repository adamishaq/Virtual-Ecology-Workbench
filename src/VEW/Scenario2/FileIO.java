package VEW.Scenario2;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import VEW.Common.DateDialog;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class FileIO {
  public static String DefaultDataDirectory = ".."+File.separator+"ScenarioData"+File.separator;
  public final static String L_DENSITY = new String("Density");
  public final static String L_NITRATE = new String("Nitrate");
  public final static String L_PHOSPHATE = new String("Phosphate");
  public final static String L_SALINITY = new String("Salinity");
  public final static String L_SILICATE = new String("Silicate");
  public final static String L_TEMPERATURE = new String("Temperature");
  public final static String L_MLD = new String("MixedLayerDepth");
  
  public static boolean checkForExistence(String file) {

    XMLFile xf = XMLFile.LoadFile("vew.xml");
    if (xf.getTag("scenariodata")!=null) DefaultDataDirectory = xf.getValue("scenariodata");
    File f = new File(DefaultDataDirectory+file);
    boolean aborted=false;
    while ((!f.exists()) && (!aborted)) {
      String diskNo="1";
      if (file.startsWith("Climate"+File.separator+"ERA40")) {
        if (file.toUpperCase().endsWith("AVG.ZIP")) diskNo="1"; else {
          int year = Integer.parseInt(file.substring(file.length()-8,file.length()-4));
          if (year==1958) diskNo="2";
          else if (year<=1962) diskNo="3";
          else if (year<=1966) diskNo="4";
          else if (year<=1970) diskNo="5";
          else if (year<=1974) diskNo="6";
          else if (year<=1978) diskNo="7";
          else if (year<=1982) diskNo="8";
          else if (year<=1986) diskNo="9";
          else if (year<=1990) diskNo="10";
          else if (year<=1994) diskNo="11";
          else if (year<=1998) diskNo="12";
          else diskNo="13";
        }
      } else if (file.startsWith("Velocities"+File.separator)) {
        try {
          int layer=Integer.parseInt(file.substring(16,18));
          if (layer>=6) diskNo="2";
        } catch (Exception e) {}
      }
      
      JOptionPane.showMessageDialog(new JLabel(""),"<html>Could not find data file: "+file+"<br>Please locate your ScenarioData directory<br>(Disk "+diskNo+" of the DVD set)</html>");
      JFileChooser jfc = new JFileChooser();
      jfc.setApproveButtonText("Use Folder");
      jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      if (jfc.showDialog(new JLabel(""),"Locate your Scenario Data folder") == JFileChooser.APPROVE_OPTION) {
        String path = jfc.getSelectedFile().getAbsolutePath();
        DefaultDataDirectory=path+File.separator;
        f = new File(DefaultDataDirectory+file);
        if (xf.getTag("scenariodata")==null) xf.addTag(new XMLTag("scenariodata",DefaultDataDirectory));
        else xf.getTag("scenariodata").setValue(DefaultDataDirectory);
        xf.write();
      } else aborted=true;
    }
    return !aborted;
  }
  
  public static double[][][] getVelocityData(int layerNo, GregorianCalendar time) {
    double[][][] Velocities = new double[2][1440][720];
    String DataDirectory = "Velocities"+File.separator+Generator.velFiles[layerNo]+File.separator;
    GregorianCalendar time2 = new GregorianCalendar();
    time2.setTimeInMillis(time.getTimeInMillis());
    time2.set(GregorianCalendar.DAY_OF_MONTH,1);
    time2.set(GregorianCalendar.HOUR_OF_DAY,0);
    time2.set(GregorianCalendar.MINUTE,0);
    time2.set(GregorianCalendar.SECOND,0);
    time2.set(GregorianCalendar.MILLISECOND,0);
    final long startOfMonth = time2.getTimeInMillis();
    time2.add(GregorianCalendar.MONTH,1);
    final long endOfMonth = time2.getTimeInMillis();
    final long timeMillis = time.getTimeInMillis();
    long midMonth1,midMonth2;
    double[][][] data;
    double[][][] data2;
    if (timeMillis>((startOfMonth+endOfMonth)/2)) {
      midMonth1=(startOfMonth+endOfMonth)/2;
      time2.add(GregorianCalendar.MONTH,1);
      midMonth2=(endOfMonth+time2.getTimeInMillis())/2;
      data = LoadVelocityData(time2.get(GregorianCalendar.MONTH),DataDirectory);
      data2 = LoadVelocityData(time2.get(GregorianCalendar.MONTH)+1,DataDirectory);
        
    } else {
      midMonth2=(startOfMonth+endOfMonth)/2;
      time2.add(GregorianCalendar.MONTH,-1);
      midMonth1=(startOfMonth+time2.getTimeInMillis())/2;
      data = LoadVelocityData(time2.get(GregorianCalendar.MONTH)+1,DataDirectory);
      if (data!=null) data2 = LoadVelocityData(time2.get(GregorianCalendar.MONTH)+2,DataDirectory);
      else data2 = null;
    }
    double weight1 = (midMonth2-timeMillis)/(1.0f*(midMonth2-midMonth1));
    double weight2 = (timeMillis-midMonth1)/(1.0f*(midMonth2-midMonth1));
    double x1,x2,y1,y2,xw,yw;
    for (int j=0; j<720; j++) {
      for (int k=0; k<1440; k++) {
        x1 = data[0][j][k];
        y1 = data[1][j][k];
        x2 = data2[0][j][k];
        y2 = data2[1][j][k];
        xw = (x1*weight1)+(x2*weight2);
        yw = (y1*weight1)+(y2*weight2);
        Velocities[0][k][j] = Math.sqrt((xw*xw)+(yw*yw));
        if ((xw*yw)!=0) Velocities[1][k][j] = Math.atan(xw/yw); 
        else Velocities[1][k][j]=Double.NEGATIVE_INFINITY;
      }
    }
    
    return Velocities;
  }
  
  
  public static double[][][] LoadVelocityData(int Month, String DataDirectory) {
    String FileName = DataDirectory+"Velocity-" + (Month < 9 ? "0" : "") + (Month + 1) + ".vsi";
    double[][][] Velocities = null;
    if (checkForExistence(FileName)) {
      Velocities = new double[2][720][1440];
      try {
        DataInputStream DataReader = new DataInputStream(new BufferedInputStream(new FileInputStream(DefaultDataDirectory+FileName)));
        for (int uv = 0; uv < Velocities.length; uv++) {
          for (int y = 0; y < Velocities[uv].length; y++) {
            for (int x = 0; x < Velocities[uv][y].length; x++) {
              Velocities[uv][y][x] = DataReader.readDouble();
              if (Velocities[uv][y][x] > 999999) Velocities[uv][y][x] = 0;
            }
          }
        }
      } catch(Exception e) {
        System.err.println(e.getMessage());
        return null;
      }
    }
    return Velocities;
  }
  
  public static void ReallySkip(int Bytes, DataInputStream TheStream) throws Exception {
    int BytesToGo = Bytes;
    while(BytesToGo > 0)
      BytesToGo -= TheStream.skipBytes(BytesToGo);
  }

 
  public static double[][] LoadGlobalProfileLayer(String variableName, GregorianCalendar time, int layer) {
    return LoadGlobalProfile(variableName,time)[layer];
  }
  
  public static double[][][] readLevData(boolean mld, String file) {
    double[][][] data = new double[14][360][180];
    try {
      DataInputStream ProfileReader = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
      double d;
      if (!mld) {
        for (int j=0; j<180; j++) {
          for (int i=0; i<360; i++) {
            for (int k=0; k<14; k++) {
              d=ProfileReader.readDouble();
              if (d>9000) d-=10000;
              if (d<0) d=0;
              data[k][i][179-j]=d;
            }
          }
        }
      } else {
        for (int j=0; j<180; j++) {
          for (int i=0; i<360; i++) {
            d=ProfileReader.readDouble();
            if (d>9000) d-=10000;
            if (d<0) d=0;
            data[0][i][179-j]=d;
          }
        }
      }
    } catch (Exception e) { e.printStackTrace(); }
    return data;
  }
  
  public static double[][][] LoadGlobalProfile(String variableName, GregorianCalendar time) {
    String[] months = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
    boolean monthly = false;
    boolean mld = false;
    double[][][] data = null;
    String fileDir = "Levitus"+File.separator+variableName;
    if (variableName.equals(L_DENSITY)) monthly = true;
    if (variableName.equals(L_SALINITY)) monthly = true;
    if (variableName.equals(L_TEMPERATURE)) monthly = true;
    if (variableName.equals(L_MLD)) { monthly = true; mld=true; }
    String testFile = fileDir+".vsi";
    if (monthly) testFile=fileDir+"January.vsi";
    if (checkForExistence(testFile)) {
      data = new double[14][360][180];
      if (!monthly) data = readLevData(mld,DefaultDataDirectory+fileDir+".vsi");
      else {
        GregorianCalendar time2 = new GregorianCalendar();
        time2.setTimeInMillis(time.getTimeInMillis());
        time2.set(GregorianCalendar.DAY_OF_MONTH,1);
        time2.set(GregorianCalendar.HOUR_OF_DAY,0);
        time2.set(GregorianCalendar.MINUTE,0);
        time2.set(GregorianCalendar.SECOND,0);
        time2.set(GregorianCalendar.MILLISECOND,0);
        final long startOfMonth = time2.getTimeInMillis();
        time2.add(GregorianCalendar.MONTH,1);
        final long endOfMonth = time2.getTimeInMillis();
        final long timeMillis = time.getTimeInMillis();
        long midMonth1,midMonth2;
        double[][][] data2 = new double[14][360][180];
        if (timeMillis>((startOfMonth+endOfMonth)/2)) {
          midMonth1=(startOfMonth+endOfMonth)/2;
          time2.add(GregorianCalendar.MONTH,1);
          midMonth2=(endOfMonth+time2.getTimeInMillis())/2;
          data = readLevData(mld,DefaultDataDirectory+fileDir+months[time2.get(GregorianCalendar.MONTH)-1]+".vsi");
          data2 = readLevData(mld,DefaultDataDirectory+fileDir+months[time2.get(GregorianCalendar.MONTH)]+".vsi");
       
        } else {
          midMonth2=(startOfMonth+endOfMonth)/2;
          time2.add(GregorianCalendar.MONTH,-1);
          midMonth1=(startOfMonth+time2.getTimeInMillis())/2;
          data = readLevData(mld,DefaultDataDirectory+fileDir+months[time2.get(GregorianCalendar.MONTH)]+".vsi");
          data2 = readLevData(mld,DefaultDataDirectory+fileDir+months[time2.get(GregorianCalendar.MONTH)+1]+".vsi");        
        }
        double weight1 = (midMonth2-timeMillis)/(1.0f*(midMonth2-midMonth1));
        double weight2 = (timeMillis-midMonth1)/(1.0f*(midMonth2-midMonth1));
        if (!variableName.equals(L_MLD)) {
          for (int i=0; i<14; i++) {
            for (int j=0; j<360; j++) {
              for (int k=0; k<180; k++) {
                data[i][j][k]=(data[i][j][k]*weight1)+(data2[i][j][k]*weight2);
              }
            }
          }
        } else {
          for (int j=0; j<360; j++) {
            for (int k=0; k<180; k++) {
              data[0][j][k]=(data[0][j][k]*weight1)+(data2[0][j][k]*weight2);
            }
          }
        }
      }
    }
    return data;
  }

  public static double[] LoadProfile(double Latitude, double Longitude, double LatitudeResolution, double LongitudeResolution,
                                        int layers, int MaxLayers, String FilePath) {
    double[] theData = new double[layers];
    
    // It *seems* from the existing code that the data file reads in from South Pole upwards... so if you have, eg,
    // Azores, which is North and West, Longitude +41 (positive North), Longitude +27 (positive West), then in the data 
    // file, you should firstly skip 130 full lines (= -90?..+41?) full lines, (each line being 360 entries)
    
    // The latitude however... *seems* to be written from West to East, starting at zero degrees. Hence, to get to
    // the azores, (+) 27 West, you start at 0degrees, move East, and go all the way around -> 360-27 = 333 points.
    
    // So the longitude lines below check if you have a 'positive west' value, do 360-x. If however you supply a negative
    // value (ie, somewhere east of zero), then the sign just needs flipping. 
    
    // Convert longitude (-180..+180, left to right)< and latitude (+90 to -90, top to bottom) into grid co-ordinates.
    
    double i,j;
    if (Longitude > 0) i = ((360 - Longitude) / LongitudeResolution);
    else i = ((Longitude * -1) / LongitudeResolution);
    j = ((90 - Latitude) / LatitudeResolution);
    
    // Now find the grid-points that surround (i,j).
    
    int iFloor = (int) (Math.floor(i/LongitudeResolution)*LongitudeResolution);
    int jFloor = (int) (Math.floor(j/LatitudeResolution)*LatitudeResolution);
    int gridSizeX = (int) (360/LongitudeResolution);
    
    double xFraction = (i-iFloor)/LongitudeResolution;
    double yFraction = (1-(j-jFloor))/LongitudeResolution;
    
    double[] topLeftCorner = new double[MaxLayers];
    double[] topRightCorner = new double[MaxLayers];
    double[] bottomLeftCorner = new double[MaxLayers];
    double[] bottomRightCorner = new double[MaxLayers];
    
    try {
      if (checkForExistence(FilePath)) {
        DataInputStream ProfileReader = new DataInputStream(
                                        new BufferedInputStream(
                                        new FileInputStream(DefaultDataDirectory+FilePath)));
        if (jFloor>0) ReallySkip(8*gridSizeX*MaxLayers*(178-jFloor),ProfileReader);    // First skip all latitude before jFloor.
        if (iFloor>0) ReallySkip(8*(iFloor)*MaxLayers,ProfileReader);              // Then skip the longitude before iFloor.
                                                                                   // And read the top left corner, and the top right corner.
        for (int k=0; k<bottomLeftCorner.length; k++) bottomLeftCorner[k]=ProfileReader.readDouble();
        for (int k=0; k<bottomRightCorner.length; k++) bottomRightCorner[k]=ProfileReader.readDouble();
        ReallySkip(8*MaxLayers*((gridSizeX-2)),ProfileReader);            // And skip rest of row, and start of the next.
        for (int k=0; k<topLeftCorner.length; k++) topLeftCorner[k]=ProfileReader.readDouble();
        for (int k=0; k<topRightCorner.length; k++) topRightCorner[k]=ProfileReader.readDouble();
        ProfileReader.close();
      }
    } catch (Exception e) { e.printStackTrace(); }
    
    for (int k=0; k<bottomLeftCorner.length; k++) {
      if (bottomLeftCorner[k] > 9000) bottomLeftCorner[k] -= 10000;
      if (bottomRightCorner[k] > 9000) bottomRightCorner[k] -= 10000;      
      if (topLeftCorner[k] > 9000) topLeftCorner[k] -= 10000;      
      if (topRightCorner[k] > 9000) topRightCorner[k] -= 10000;      
    }

    
    double[] avgLeftSide = new double[MaxLayers];
    double[] avgRightSide = new double[MaxLayers];
    double[] avgTopSide = new double[MaxLayers];
    double[] avgBottomSide = new double[MaxLayers];
    for (int k=0; k<MaxLayers; k++) {
      avgLeftSide[k]=bottomLeftCorner[k]+((yFraction)*(topLeftCorner[k]-bottomLeftCorner[k]));
      avgTopSide[k]=topLeftCorner[k]+(xFraction*(topRightCorner[k]-topLeftCorner[k]));    
      avgBottomSide[k]=bottomLeftCorner[k]+(xFraction*(bottomRightCorner[k]-bottomLeftCorner[k]));    
      avgRightSide[k]=bottomRightCorner[k]+(yFraction*(topRightCorner[k]-bottomRightCorner[k]));
    }
    double[] midX = new double[MaxLayers];
    double[] midY = new double[MaxLayers];    
    for (int k=0; k<MaxLayers; k++) midX[k]=avgLeftSide[k]+(xFraction*(avgRightSide[k]-avgLeftSide[k]));    
    for (int k=0; k<MaxLayers; k++) midY[k]=avgBottomSide[k]+(yFraction*(avgTopSide[k]-avgBottomSide[k]));
    for (int k=0; k<MaxLayers; k++) theData[k]=(midX[k]+midY[k])/2.0;
    return theData;
  }
  
 
  public static BufferedImage FetchMapPicture() {
    try {
      return ImageIO.read(new File("Data/Scenario/Maps/WorldMap.vsi"));
    } catch(Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      return null;
    } 
  }

  public static BufferedImage FetchMaskPicture() {
    try {
      return ImageIO.read(new File("Data/Scenario/Maps/WorldMask.vsi"));
    } catch(Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
      return null;
    } 
  }

  public static boolean WriteTrackToCSV(TrackPosition[] GeneratedTrack, String OutputFile, String Preamble) {
    try {
      FileWriter outputWriter = new FileWriter(OutputFile);
      int OutputLines = GeneratedTrack.length;
      outputWriter.write(Preamble + "\nRun Day,Latitude,Longitude\n");
      for(int i = 0; i < OutputLines; i++)
        outputWriter.write(i + "," + GeneratedTrack[i].getLat() + "," + GeneratedTrack[i].getLon() + "\n");
      outputWriter.close();
      return true;
    }
    catch(Exception e) { return false; }
  }

  public static void GenerateDataFromSpec(XMLFile SpecificationFile,String dataPath, String progressFile) {
  
    // Read in the relevant variables
    GregorianCalendar startDate = new GregorianCalendar(DateDialog.GMTTimeZone);
    GregorianCalendar endDate = new GregorianCalendar(DateDialog.GMTTimeZone);
    
    double Latitude  = Double.parseDouble(SpecificationFile.getValue("track/latitude"));
    double Longitude = Double.parseDouble(SpecificationFile.getValue("track/longitude"));
    long startMillis = Long.parseLong(SpecificationFile.getValue("track/start"));
    long endMillis = Long.parseLong(SpecificationFile.getValue("track/end"));
    startDate.setTimeInMillis(startMillis);    
    endDate.setTimeInMillis(endMillis);
    long timeStepInSeconds = Long.parseLong(SpecificationFile.getValue("track/secstep"));
    String VelocityDataSet  = SpecificationFile.getValue("track/velocityfield");
    String IntegrationMethod = SpecificationFile.getValue("track/trackmode");    
    String ClimateDataSet   = SpecificationFile.getValue("boundaryconditions/climatedata");
    String HeatFluxMethod    = SpecificationFile.getValue("boundaryconditions/heatflux");
    
    
    int StartMonth = startDate.get(GregorianCalendar.MONTH);
    int StartDayOfYear = startDate.get(GregorianCalendar.DAY_OF_YEAR);
    int EndMonth = endDate.get(GregorianCalendar.MONTH);
    int EndDay = endDate.get(GregorianCalendar.DAY_OF_YEAR); // CHECK! Is it day of year or not?
    int RunSteps = (int) ((endMillis-startMillis)/(1000*timeStepInSeconds));

    
    double timeStepInHours = timeStepInSeconds/3600.0;
    int RunDays = 1+(int) (RunSteps/(24*(3600.0/timeStepInSeconds))); // The 1+ is so there is a final point to interpolate towards.

    boolean GoForward = (!IntegrationMethod.equals(Generator.INT_BWD));
    Generator TrackGenerator;
    
    
    if (GoForward) TrackGenerator = new Generator(StartMonth, StartDayOfYear, IntegrationMethod, RunDays,
      new TrackPosition(new Point2D.Double(Longitude, Latitude)), VelocityDataSet);
    else TrackGenerator = new Generator(EndMonth, EndDay, IntegrationMethod, RunDays,
      new TrackPosition(new Point2D.Double(Longitude, Latitude)), VelocityDataSet);
  
    TrackPosition[] GeneratedTrack = TrackGenerator.getGeneratedTrack(true);
    InitialData.createInitialData(SpecificationFile,dataPath);
    if (ClimateDataSet.equals(ScenarioPanel2.BUNKER)) {
      String[] ClimateDataRequired = {BoundaryData.BC_WS,BoundaryData.BC_TCC,BoundaryData.BC_HF,BoundaryData.BC_EK};
      BoundaryData.generateMonthlyValuesBoundaryConditions(timeStepInHours, GeneratedTrack, ClimateDataRequired, ClimateDataSet, dataPath, progressFile, startMillis, endMillis);

          
    } else if ((ClimateDataSet.equals(ScenarioPanel2.ERA40_SYNOPTIC)) && (HeatFluxMethod.equals(ScenarioPanel2.ERA40_CALCULATE_FLUX))) {
    	String[] ClimateDataRequired = {BoundaryData.BC_WS, BoundaryData.BC_TCC, BoundaryData.BC_R, BoundaryData.BC_2T}; 
  	  BoundaryData.generateERA40_6hourly(timeStepInHours, GeneratedTrack, ClimateDataRequired, ClimateDataSet, dataPath, progressFile, startMillis, endMillis);
	  
    } else if ((ClimateDataSet.equals(ScenarioPanel2.ERA40_SYNOPTIC)) && (HeatFluxMethod.equals(ScenarioPanel2.ERA40_READ_FLUX))) {
      String[] ClimateDataRequired = {BoundaryData.BC_WS, BoundaryData.BC_TCC, BoundaryData.BC_SSHF, BoundaryData.BC_SLHF, BoundaryData.BC_STR};
      BoundaryData.generateERA40_6hourly(timeStepInHours, GeneratedTrack, ClimateDataRequired, ClimateDataSet, dataPath, progressFile, startMillis, endMillis);
    
    } else if ((ClimateDataSet.equals(ScenarioPanel2.ERA40_MEAN_YEAR)) && (HeatFluxMethod.equals(ScenarioPanel2.ERA40_CALCULATE_FLUX))) {
      String[] ClimateDataRequired = {BoundaryData.BC_WS, BoundaryData.BC_TCC, BoundaryData.BC_R, BoundaryData.BC_2T};
      BoundaryData.generateERA40_6hourly(timeStepInHours,GeneratedTrack, ClimateDataRequired, ClimateDataSet, dataPath, progressFile, startMillis, endMillis);
		
    } else if ((ClimateDataSet.equals(ScenarioPanel2.ERA40_MEAN_YEAR)) && (HeatFluxMethod.equals(ScenarioPanel2.ERA40_READ_FLUX))) {
      String[] ClimateDataRequired = {BoundaryData.BC_WS, BoundaryData.BC_TCC, BoundaryData.BC_SSHF,BoundaryData.BC_SLHF,BoundaryData.BC_STR};
      BoundaryData.generateERA40_6hourly(timeStepInHours, GeneratedTrack, ClimateDataRequired, ClimateDataSet, dataPath, progressFile, startMillis, endMillis);
	  
    } else if ((ClimateDataSet.equals(ScenarioPanel2.ERA40_MONTHLY_AVG)) && (HeatFluxMethod.equals(ScenarioPanel2.ERA40_CALCULATE_FLUX))) {
      String[] ClimateDataRequired = {BoundaryData.BC_WS, BoundaryData.BC_TCC, BoundaryData.BC_R, BoundaryData.BC_2T};
      BoundaryData.generateMonthlyValuesBoundaryConditions(timeStepInHours, GeneratedTrack, ClimateDataRequired, ClimateDataSet, dataPath, progressFile, startMillis, endMillis);
    
    } else if ((ClimateDataSet.equals(ScenarioPanel2.ERA40_MONTHLY_AVG)) && (HeatFluxMethod.equals(ScenarioPanel2.ERA40_READ_FLUX))) {
      String[] ClimateDataRequired = {BoundaryData.BC_WS, BoundaryData.BC_TCC, BoundaryData.BC_SSHF,BoundaryData.BC_SLHF,BoundaryData.BC_STR};
      BoundaryData.generateMonthlyValuesBoundaryConditions(timeStepInHours, GeneratedTrack, ClimateDataRequired, ClimateDataSet, dataPath, progressFile, startMillis, endMillis);
    }    
      
    TrackPosition.WriteToFile(GeneratedTrack,dataPath);
  }
}