package VEW.Scenario2;

//import java.util.zip.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import VEW.Common.BoundaryDataStep;
import VEW.Common.DateDialog;
import VEW.Common.VEWUtilities;

public class BoundaryData implements Serializable {
  private final static double[] E = { 1.3939, 1.2155, 1.0738, 1.0135, 0.959,
      0.9094, 0.8642, 0.8228, 0.7848, 0.7498, 0.7175, 0.6876, 0.6598, 0.6339,
      0.6098, 0.5872, 0.5661, 0.5463, 0.5277, 0.5102, 0.4936, 0.478, 0.4633,
      0.4493, 0.4361, 0.4236, 0.4116, 0.4003, 0.3895, 0.3792, 0.3693, 0.36,
      0.359, 0.3424, 0.3342, 0.3263, 0.3187, 0.3115, 0.3045, 0.2978, 0.2914,
      0.2792, 0.268, 0.2574, 0.2476, 0.2385, 0.2299, 0.218, 0.2005, 0.172,
      0.15, 0.1183, 0.09686, 0.08142, 0.06986, 0.06092, 0.05382, 0.04806,
      0.04331, 0.03934, 0.03596, 0.03307, 0.03056, 0.02837, 0.02645, 0.02474,
      0.02123, 0.01851, 0.0146, 0.01195, 0.01005 };
  private final static double[] EE = { 9.2164, 6.0151, 4.0888, 3.4152, 2.8745,
      2.4364, 2.0786, 1.784, 1.5397, 1.3357, 1.1643, 1.0195, 0.8965, 0.7915,
      0.7013, 0.6236, 0.5564, 0.498, 0.447, 0.4023, 0.3631, 0.3286, 0.298,
      0.2709, 0.2468, 0.2254, 0.2062, 0.189, 0.1735, 0.1596, 0.1471, 0.1358,
      0.1255, 0.1162, 0.1077, 0.09999, 0.09295, 0.08652, 0.08064, 0.07524,
      0.07029, 0.06155, 0.05412, 0.04778, 0.04234, 0.03765, 0.03359, 0.02847,
      0.02192, 0.01361, 0.00888, 0.004244, 0.002273, 0.001324, 0.0008214,
      0.0005361, 0.0003644, 0.0002562, 0.0001853, 0.0001372, 0.0001, 0.00008,
      0.00006, 0.00005, 0.00004, 0.00003, 0.00002, 0.00001, 0.0, 0.0, 0.0 };
  private final static double[] F = { 0.003, 0.022, 0.0855, 0.067, 0.063, 0.07,
      0.13, 0.232, 0.222, 0.482, 0.514, 0.689, 0.83, 1.059, 1.074, 1.093,
      1.068, 1.181, 1.12, 1.098, 1.429, 1.751, 1.747, 1.639, 1.81, 2.006,
      2.066, 2.033, 2.074, 1.95, 1.942, 1.882, 1.833, 1.842, 1.783, 1.725,
      1.695, 1.712, 1.715, 1.70, 2.499, 3.204, 3.088, 2.972, 2.854, 2.738,
      3.285, 4.94, 8.3028, 8.89, 9.698, 10.648, 6.72, 4.88, 3.18, 2.06, 1.58,
      1.28, 0.96, 0.78, 0.62, 0.46, 0.34, 0.26, 0.22, 0.315, 0.3, 0.3, 0.2,
      0.1, 0.15 };
  public final static String BC_SSHF = new String("SensibleHeatFlux");
  public final static String BC_SLHF = new String("LatentHeatFlux");
  public final static String BC_STR = new String("ThermalRadiation");
  public final static String BC_HF = new String("HeatFlux");
  public final static String BC_EK = new String("Ekman");   
  public final static String BC_WS = new String("WindSpeed");
  public final static String BC_WSA = new String("WindSpeedAngle");
  public final static String BC_TCC= new String("CloudCover");
  public final static String BC_R = new String("RelativeHumidity");
  public final static String BC_2T = new String("AirTemp");
  public final static String BC_ZH = new String("ZenithHeight");
  public final static String BC_FWF = new String("FreshWaterFlux");
  public final static String BC_I = new String("Irradiance");
  public final static String BC_LONG = new String("Longitude"); 
  public final static String BC_LAT = new String("Latitude");
  private static double EQT = 0;
  private static double DEC = 0;
  
  private static String[] quarters = { "00", "06", "12", "18" };
  
  public static double[][] fixERA40_leap(double[][] feb28, double[][] mar1, double point, double total) {
    final double[][] interp = new double[feb28.length][feb28[0].length];
    for (int i=0; i<feb28.length; i++) {
      for (int j=0; j<feb28[i].length; j++) {
        final double diff=((mar1[i][j]-feb28[i][j])/total)*point;
        interp[i][j]=feb28[i][j]+diff;
      }
    }
    return interp;
  }
  
   
  public static double[][][] getDataArray(String dataSource, String variableName, GregorianCalendar time) {
    double[][][] data = null;
    char fs = File.separatorChar;
    String dataName="";
    String fileDir="";
    boolean sixHourly=false;
    if (dataSource.equals(ScenarioPanel2.BUNKER)) {
      if (variableName.equals(ScenarioPanel2.B_CC)) dataName = BC_TCC;
      else if (variableName.equals(ScenarioPanel2.B_EK)) dataName = BC_EK;
      else if (variableName.equals(ScenarioPanel2.B_HF)) dataName = BC_HF;
      else if (variableName.equals(ScenarioPanel2.B_WS)) dataName = BC_WS;
      fileDir = "Climate"+fs+"Bunker"+fs;
   
    } else {
      if (variableName.equals(ScenarioPanel2.E_2T)) { dataName=BC_2T; sixHourly=true; }
      else if (variableName.equals(ScenarioPanel2.E_OHL)) { dataName=BC_HF; sixHourly=true; }
      else if (variableName.equals(ScenarioPanel2.E_TCC)) {dataName=BC_TCC; sixHourly=true; }
      else if (variableName.equals(ScenarioPanel2.E_SLHF)) { dataName=BC_SLHF; sixHourly=false; }
      else if (variableName.equals(ScenarioPanel2.E_SSHF)) { dataName=BC_SSHF; sixHourly=false; }
      else if (variableName.equals(ScenarioPanel2.E_STR)) { dataName=BC_STR; sixHourly=false; }      
      else if (variableName.equals(ScenarioPanel2.E_R)) { dataName=BC_R; sixHourly=true; }
      else if (variableName.equals(ScenarioPanel2.E_WS)) { sixHourly=true; 
        if (dataSource.equals(ScenarioPanel2.ERA40_SYNOPTIC)) dataName=BC_WSA;
        else dataName=BC_WS;
      }
      fileDir = "Climate"+fs+"ERA40"+fs;
    }

    if ((dataSource.equals(ScenarioPanel2.BUNKER)) || 
        (dataSource.equals(ScenarioPanel2.ERA40_MONTHLY_AVG))) {
      int dayOfYear = time.get(GregorianCalendar.DAY_OF_YEAR);
      int month = time.get(GregorianCalendar.MONTH); // 0-11
      if (dayOfYear < VEWUtilities.StandardMonthMiddle[month]) month = (month>0)?month-1:11;
      int nextMonth = (month==11)?0:(month+1);
      int monthLength;
      int firstWeight,secondWeight;
      if (month==11) {
        monthLength = 365 - VEWUtilities.StandardMonthMiddle[month]+ VEWUtilities.StandardMonthMiddle[nextMonth];
        if (dayOfYear >= VEWUtilities.StandardMonthMiddle[month])
          secondWeight = 365 - dayOfYear + VEWUtilities.StandardMonthMiddle[nextMonth];
        else secondWeight = VEWUtilities.StandardMonthMiddle[nextMonth] - dayOfYear;
        
      } else {
        monthLength = VEWUtilities.StandardMonthMiddle[nextMonth] - VEWUtilities.StandardMonthMiddle[month];
        secondWeight = VEWUtilities.StandardMonthMiddle[nextMonth] - dayOfYear;
      }
      firstWeight = monthLength - secondWeight;
      int lat = dataSource.equals(ScenarioPanel2.BUNKER)?180:181;
      int lon = 360;
      
      double[][][] buffer1 = LoadMonthlyWeather(month, new String[] {dataName}, fileDir, dataSource, lat, lon);
      if (buffer1!=null) {
        double[][][] buffer2 = LoadMonthlyWeather(nextMonth, new String[] {dataName}, fileDir, dataSource, lat, lon);
        if (buffer2!=null) { 
          data = new double[1][lon][lat];
          for (int i=0; i<lon; i++) {
            for (int j=0; j<lat; j++) {
              data[0][i][j] = ((buffer1[0][i][j]*firstWeight)+(buffer2[0][i][j]*secondWeight))/monthLength;
            }
          }
        }
      }
    } else {
      if ((dataSource.equals(ScenarioPanel2.ERA40_SYNOPTIC)) || (dataSource.equals(ScenarioPanel2.ERA40_MEAN_YEAR))) {
        int year = time.get(GregorianCalendar.YEAR);
        int hour = time.get(GregorianCalendar.HOUR_OF_DAY);
        if (dataSource.equals(ScenarioPanel2.ERA40_SYNOPTIC)) {
          if ((year<1958) || (year>2001)) {
          // report error - date out of range
          }
        }
        GregorianCalendar time1 = new GregorianCalendar();
        GregorianCalendar time2 = new GregorianCalendar();
        time1.setTimeInMillis(time.getTimeInMillis());
        long timespan,weight1,weight2;
        if (sixHourly) {
          while (hour%6>0) hour--;
          time1.set(GregorianCalendar.HOUR_OF_DAY, hour);
          time1.set(GregorianCalendar.MINUTE,0);
          time2.setTimeInMillis(time1.getTimeInMillis());
          time2.add(GregorianCalendar.HOUR_OF_DAY,6);
          timespan=6*3600*1000;
          
        } else {
          time1.set(GregorianCalendar.HOUR_OF_DAY,0);
          time1.set(GregorianCalendar.MINUTE,0);
          time2.setTimeInMillis(time1.getTimeInMillis());
          time2.add(GregorianCalendar.HOUR_OF_DAY,24);
          timespan=24*3600*1000;
        }
        if (dataSource.equals(ScenarioPanel2.ERA40_MEAN_YEAR)) {
          if ((time1.get(GregorianCalendar.DAY_OF_MONTH)==29) && (time1.get(GregorianCalendar.MONTH)==GregorianCalendar.FEBRUARY)) {
            time1.add(GregorianCalendar.DAY_OF_YEAR,-1);
            timespan+=(24*3600*1000);
          }
          if ((time2.get(GregorianCalendar.DAY_OF_MONTH)==29) && (time2.get(GregorianCalendar.MONTH)==GregorianCalendar.FEBRUARY)) {
            time1.add(GregorianCalendar.DAY_OF_YEAR,+1);
            timespan+=(24*3600*1000);
          }

        }
        weight2=(time.getTimeInMillis()-time1.getTimeInMillis())/timespan;
        weight1=(timespan-weight2)/timespan;
        double[][][] buffer1;
        double[][][] buffer2;
        if (dataSource.equals(ScenarioPanel2.ERA40_SYNOPTIC)) {
          buffer1 = LoadERA40(time1.get(GregorianCalendar.YEAR), time1.get(GregorianCalendar.MONTH) + 1, time1.get(GregorianCalendar.DAY_OF_MONTH),time1.get(GregorianCalendar.HOUR_OF_DAY), new String[] {dataName}, fileDir);
          if (buffer1!=null) buffer2 = LoadERA40(time2.get(GregorianCalendar.YEAR), time2.get(GregorianCalendar.MONTH) + 1, time2.get(GregorianCalendar.DAY_OF_MONTH),time2.get(GregorianCalendar.HOUR_OF_DAY), new String[] {dataName}, fileDir);
          else buffer2 = null;
        } else {
          buffer1 = LoadERA40(-1, time1.get(GregorianCalendar.MONTH) + 1, time1.get(GregorianCalendar.DAY_OF_MONTH),time1.get(GregorianCalendar.HOUR_OF_DAY), new String[] {dataName}, fileDir);
          if (buffer1!=null) buffer2 = LoadERA40(-1, time2.get(GregorianCalendar.MONTH) + 1, time2.get(GregorianCalendar.DAY_OF_MONTH),time2.get(GregorianCalendar.HOUR_OF_DAY), new String[] {dataName}, fileDir);
          else buffer2 = null;
         }
        if ((dataName.equals(BC_WSA)) && (buffer1!=null)) {
          data = new double[2][360][181];
          for (int i=0; i<360; i++) for (int j=0; j<181; j++) {
            data[0][i][j]=((buffer1[0][i][j]*weight1)+(buffer2[0][i][j]*weight2));
            data[1][i][j]=buffer1[1][i][j];
          }

        } else if (buffer1!=null) {
          data = new double[1][360][181];
          for (int i=0; i<360; i++) for (int j=0; j<181; j++)
            data[0][i][j]=((buffer1[0][i][j]*weight1)+(buffer2[0][i][j]*weight2));
        }
      }
    }
    return data;
  }
  
  
  
  public static void convertLLintoCorners(double lat, double lon, int[] Coordinates, double[] DistanceWeights) {
    // Give it latitude and longitude, and it returns grid co-ordinates, and the
    // weight multipliers for each.
    double gridX, gridY;
    if (lon>0) gridX = 360-lon;
    else gridX  = -lon; 
    gridY=90-lat;
    Coordinates[0]=(int)Math.floor(gridX);
    Coordinates[1]=Coordinates[0]+1;
    Coordinates[2]=(int)Math.floor(gridY);
    Coordinates[3]=Coordinates[2]+1;
    DistanceWeights[1] = gridX-Coordinates[0];
    DistanceWeights[0] = 1-DistanceWeights[1];
    DistanceWeights[3] = gridY-Coordinates[2];
    DistanceWeights[2] = 1-DistanceWeights[3];
  }
  
  public static void computeEQTDec(int DayOfYear, int YearLength) {
    final double[] A = { 0.000075, 0.001868, 0.032077, 0.014615, 0.040849 };
    final double[] B = { 0.006918, 0.399912, 0.070257, 0.006758, 0.000907, 0.002697, 0.001480 };
    final double DR = 2 * VEWUtilities.PI * ((1.0*DayOfYear) / YearLength);
    final double S0 = Math.sin(DR);
    final double S1 = Math.sin(DR*2);
    final double S2 = Math.sin(DR*3);
    final double C0 = Math.cos(DR);
    final double C1 = Math.cos(DR*2);
    final double C2 = Math.cos(DR*3);
    EQT = A[0] + (C0 * A[1]) - (S0 * A[2]) - (C1 * A[3]) - (S1 * A[4]);
    DEC = B[0] - (C0*B[1]) + (S0*B[2]) - (C1*B[3]) + (S1*B[4]) - (C2*B[5]) + (S2*B[6]);
  }
  
  public static double calculateEQT(int DayOfYear, int YearLength) {
    final double[] A = { 0.000075, 0.001868, 0.032077, 0.014615, 0.040849 };
    final double DR = 2 * VEWUtilities.PI * ((DayOfYear*1.0) / YearLength);
    final double S0 = Math.sin(DR);
    final double S1 = Math.sin(DR*2);
    final double C0 = Math.cos(DR);
    final double C1 = Math.cos(DR*2);
    return A[0] + (C0 * A[1]) - (S0 * A[2]) - (C1 * A[3]) - (S1 * A[4]);
  }
  
  public static double calculateDEC(int DayOfYear, int YearLength) {
    final double[] B = { 0.006918, 0.399912, 0.070257, 0.006758, 0.000907, 0.002697, 0.001480 };
    final double DR = 2 * VEWUtilities.PI * ((DayOfYear*1.0) / YearLength);
    final double S0 = Math.sin(DR);
    final double S1 = Math.sin(DR*2);
    final double S2 = Math.sin(DR*3);
    final double C0 = Math.cos(DR);
    final double C1 = Math.cos(DR*2);
    final double C2 = Math.cos(DR*3);
    return B[0] - (C0*B[1]) + (S0*B[2]) - (C1*B[3]) + (S1*B[4]) - (C2*B[5]) + (S2*B[6]);
  }
    
  public static void writePercent(float floatPercent,String targetPath, String progressFile) {
    float percent = ((int) Math.floor(floatPercent*10))/10;
    try {
      PrintWriter P = new PrintWriter(new FileOutputStream(new File(targetPath+File.separator+progressFile)));
      P.write(String.valueOf(percent)+"\n");
      P.flush();
      P.close();
      P = null;
    } catch (Exception e) { e.printStackTrace(); }
  }
  

  public static void generateMonthlyValuesBoundaryConditions(double _timeStepInHours, TrackPosition[] GeneratedRoute, 
      String[] DataRequired, String ClimateUsed, String targetPath, String progressFile, long startMillis, long endMillis) {
    int CloudIndex = 0;
    int lat = 0;
    int lon = 0;
    String DataDirectory = "";
    if (ClimateUsed.equals(ScenarioPanel2.BUNKER)) {
      lat = 180;
      lon = 360;
      DataDirectory="Climate"+File.separator+"Bunker"+File.separator;

    } else if (ClimateUsed.equals(ScenarioPanel2.ERA40_MONTHLY_AVG)) {
      lat = 181;
      lon = 360;
      DataDirectory="Climate"+File.separator+"ERA40"+File.separator;
    }
    final String[] DataNames = new String[DataRequired.length + 5];
    for (int i = 0; i < DataRequired.length; i++) {
      DataNames[i] = DataRequired[i];
      if (DataRequired[i].equals(BC_TCC)) CloudIndex = i;
    }
    final int ZenithIndex = DataRequired.length;
    final int FreshWaterIndex = DataRequired.length + 1;
    final int IrradianceIndex = DataRequired.length + 2;
    final int LongitudeIndex = DataRequired.length + 3;
    final int LatitudeIndex = DataRequired.length + 4;
    DataNames[ZenithIndex] = BC_ZH;
    DataNames[FreshWaterIndex] = BC_FWF;
    DataNames[IrradianceIndex] = BC_I;
    DataNames[LongitudeIndex] = BC_LONG;
    DataNames[LatitudeIndex] = BC_LAT;

    final GregorianCalendar gc = new GregorianCalendar(DateDialog.GMTTimeZone);
    final GregorianCalendar spare1 = new GregorianCalendar(DateDialog.GMTTimeZone);
    final GregorianCalendar spare2 = new GregorianCalendar(DateDialog.GMTTimeZone);
    long timeStepMillis = (int) (_timeStepInHours*3600000L);
    int firstMonth=-1;
    int secondMonth=-1;
    double timeWeight;
    int trackDay=0;
    TrackPosition trackPosition1 = GeneratedRoute[0];
    TrackPosition trackPosition2 = GeneratedRoute[1];
    int _gcDayOfSim=0;
    double[][][] firstWeather = null;
    double[][][] secondWeather = null;
    int[] coords = new int[4];
    double[] distanceWeights = new double[4];
    double[] interpolatedWeather;
    double[] tempIrrad = new double[2];
    float floatPercent = 0;
    float lastPercent = 0;
    
    try {
      ObjectOutputStream OS = new ObjectOutputStream(new BufferedOutputStream(
        new FileOutputStream(targetPath + File.separator + "BoundaryConditions.vso")));
      for (long i=startMillis; i<=endMillis; i+=timeStepMillis) {
        floatPercent = (10000f*((i-startMillis)/(1.0f*(endMillis-startMillis))))/100.0f;
        if (floatPercent>lastPercent+0.1) {
          writePercent(floatPercent,targetPath, progressFile);
          lastPercent=floatPercent;
        }

        
        gc.setTimeInMillis(i);
        if ((i>startMillis) && (gc.get(GregorianCalendar.HOUR_OF_DAY)==0) && (gc.get(GregorianCalendar.MINUTE)==0)) _gcDayOfSim++;
        final int _gcYear = gc.get(GregorianCalendar.YEAR);
        final int _gcDaysInYear = gc.isLeapYear(_gcYear)?366:365;
        final int _gcDayOfYear = gc.get(GregorianCalendar.DAY_OF_YEAR);
        final int _gcDaysInMonth = gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        final int _gcDayOfMonth = gc.get(GregorianCalendar.DAY_OF_MONTH);
        final int _gcMonth = gc.get(GregorianCalendar.MONTH); // Remember 0 = January
        double _gcHourOfDay = gc.get(GregorianCalendar.HOUR_OF_DAY);
        if (gc.get(GregorianCalendar.MINUTE)!=0) _gcHourOfDay+=0.5;
        final double _EQT = calculateEQT(_gcDayOfYear,_gcDaysInYear);
        final double _DEC = calculateDEC(_gcDayOfYear,_gcDaysInYear);
      
        // Calculate interpolation over time.
        // First find months with mid-points that surround target date.
        // Then calculate weightFromFirst - ie... val = d0+(d1-d0)weightFromFirst
      
        spare1.setTimeInMillis(i);
        spare2.setTimeInMillis(i);
        spare1.set(GregorianCalendar.HOUR_OF_DAY,0);
        spare2.set(GregorianCalendar.HOUR_OF_DAY,0);
        spare1.set(GregorianCalendar.MINUTE,0);
        spare2.set(GregorianCalendar.MINUTE,0);
      
      
        if ((_gcDaysInMonth/2)>_gcDayOfMonth) {
          final int _gcPreviousMonth=(_gcMonth==GregorianCalendar.JANUARY)?GregorianCalendar.DECEMBER:_gcMonth-1;
          if (_gcPreviousMonth!=firstMonth) {
            firstWeather = LoadMonthlyWeather(_gcPreviousMonth,DataRequired,DataDirectory,ClimateUsed,lat,lon);
            firstMonth=_gcPreviousMonth;
          }
          spare1.add(GregorianCalendar.MONTH,-1);
          spare1.set(GregorianCalendar.DAY_OF_MONTH,spare1.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)/2);
          if (_gcMonth!=secondMonth) {
            secondWeather = LoadMonthlyWeather(_gcMonth,DataRequired,DataDirectory,ClimateUsed,lat,lon);
            secondMonth=_gcMonth;
          }
          spare2.set(GregorianCalendar.DAY_OF_MONTH,spare2.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)/2);
          
  
        } else {
          final int _gcNextMonth=(_gcMonth==GregorianCalendar.DECEMBER)?GregorianCalendar.JANUARY:_gcMonth+1;
          if (_gcMonth!=firstMonth) {
            firstWeather = LoadMonthlyWeather(_gcMonth,DataRequired,DataDirectory,ClimateUsed,lat,lon);
            firstMonth=_gcMonth;
          }
          spare1.set(GregorianCalendar.DAY_OF_MONTH,spare1.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)/2);

          if (_gcNextMonth!=secondMonth) {
            secondWeather = LoadMonthlyWeather(_gcNextMonth,DataRequired,DataDirectory,ClimateUsed,lat,lon);
            secondMonth=_gcNextMonth;
          }
          spare2.add(GregorianCalendar.MONTH,1);
          spare2.set(GregorianCalendar.DAY_OF_MONTH,spare2.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)/2);
        }
        
        final long spare1Millis = spare1.getTimeInMillis();
        timeWeight= ((double)(i-spare1Millis)/((double)(spare2.getTimeInMillis()-spare1Millis)));
      
        // Next deal with geographical interpolation.
      
        spare2.setTimeInMillis(i);
        spare2.set(GregorianCalendar.HOUR_OF_DAY,0);
        double positionRatio = (i-spare2.getTimeInMillis())/(24.0*3600*1000);
      
        if (_gcDayOfSim!=trackDay) {
          trackDay=_gcDayOfSim;
          trackPosition1 = GeneratedRoute[trackDay];
          trackPosition2 = GeneratedRoute[trackDay+1];
        }
        double actualLat = trackPosition1.getLat();
        actualLat = actualLat+(positionRatio*(trackPosition2.getLat()-actualLat));
        double actualLon = trackPosition1.getLon();
        actualLon = actualLon+(positionRatio*(trackPosition2.getLon()-actualLon));
        convertLLintoCorners(actualLat,actualLon, coords, distanceWeights);
        interpolatedWeather = CalculateWeatherValues(firstWeather, secondWeather,  distanceWeights, coords, timeWeight, DataNames);      
        tempIrrad = CalculateIrradiance(actualLat, _gcHourOfDay, interpolatedWeather[CloudIndex], _EQT, _DEC);
        interpolatedWeather[ZenithIndex] = tempIrrad[0];
        interpolatedWeather[FreshWaterIndex] = 0.0; // FW_Flux was never used...
        interpolatedWeather[IrradianceIndex] = tempIrrad[1];
        interpolatedWeather[LongitudeIndex] = GeneratedRoute[0].getLon();
        interpolatedWeather[LatitudeIndex] = GeneratedRoute[0].getLat();
        OS.writeObject(new BoundaryDataStep(interpolatedWeather));
      }
      OS.flush();
      OS.close();
    } catch (Exception e) { e.printStackTrace(); }
  }
  
  public static void generateERA40_6hourly(double _timeStepInHours, TrackPosition[] generatedRoute, String[] dataRequired,
    String climateUsed, String targetPath, String progressFile, long startMillis, long endMillis) {
    try {
      ObjectOutputStream OS = new ObjectOutputStream(new BufferedOutputStream(
        new FileOutputStream(targetPath + File.separator + "BoundaryConditions.vso")));

      GregorianCalendar startTime = new GregorianCalendar();
      startTime.setTimeInMillis(startMillis);
      final int startMonth = startTime.get(GregorianCalendar.MONTH);
      final float StartTime_Hours = startTime.get(GregorianCalendar.HOUR_OF_DAY)+(startTime.get(GregorianCalendar.MINUTE)/60.0f)+(startTime.get(GregorianCalendar.SECOND)/3600.0f);
      int startDayOfMonth = startTime.get(GregorianCalendar.DAY_OF_MONTH);
      int startTimeStep = (int) (StartTime_Hours/_timeStepInHours);
      int startYear = startTime.get(GregorianCalendar.YEAR);
   
      byte newDataNextTime = 1;
      boolean synoptic = (climateUsed.equals(ScenarioPanel2.ERA40_SYNOPTIC));
      int timeStepInMinutes = (int) (60 * _timeStepInHours);
      int intervalLength;
      int stepOfDay, day, quarter;
      boolean quarterAdvanced, dayAdvanced;
      double hourOfDay;
      int[] timeWeight = new int[2];
      int cloudIndex = 0;
      final String DataDirectory = "Climate"+File.separator+"ERA40"+File.separator;
      final String[] DataNames = new String[dataRequired.length + 5];
      for (int i = 0; i < dataRequired.length; i++) {
        DataNames[i] = dataRequired[i];
        if (dataRequired[i].equals("CloudCover")) cloudIndex = i;
      }
      
      final int ZenithIndex = dataRequired.length;
      final int FreshWaterIndex = dataRequired.length + 1;
      final int IrradianceIndex = dataRequired.length + 2;
      final int LongitudeIndex = dataRequired.length + 3;
      final int LatitudeIndex = dataRequired.length + 4;
      DataNames[ZenithIndex] = BC_ZH;
      DataNames[FreshWaterIndex] = BC_FWF;
      DataNames[IrradianceIndex] = BC_I;
      DataNames[LatitudeIndex] = BC_LAT;
      DataNames[LongitudeIndex] = BC_LONG;
      long RunSteps = (int) (((endMillis-startMillis)/1000)/(3600*_timeStepInHours));
      GregorianCalendar calendar = new GregorianCalendar(startYear, startMonth, startDayOfMonth, (int) StartTime_Hours, (timeStepInMinutes * (startTimeStep % 2)));
      calendar.set(GregorianCalendar.MILLISECOND,0);
      int StartDay = calendar.get(GregorianCalendar.DAY_OF_YEAR);
      intervalLength = (int) (6.0 / (float) _timeStepInHours);
      timeWeight[0] = startTimeStep % intervalLength;
      timeWeight[1] = intervalLength - timeWeight[0];

      double FW_Flux = 0.0; // Set to this arbitrary value
      double[][][] currentWeather;
      double[][][] nextWeather;
      double[] distanceWeights = new double[4];
      int[] coordinates = new int[4];
      double[] weatherValues = new double[DataNames.length];
      convertLLintoCorners(generatedRoute[0].getLat(),generatedRoute[0].getLon(), coordinates, distanceWeights);
    
      if (synoptic) {
        currentWeather = LoadERA40(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR_OF_DAY), dataRequired, DataDirectory);
        calendar.add(Calendar.MINUTE, timeStepInMinutes);
        nextWeather = LoadERA40(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.HOUR_OF_DAY), dataRequired, DataDirectory);
        calendar.add(Calendar.MINUTE, -timeStepInMinutes);
      } else {
        currentWeather = LoadERA40(-1, calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), dataRequired, DataDirectory);
        calendar.add(Calendar.MINUTE, timeStepInMinutes);
        nextWeather = LoadERA40(-1, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),dataRequired, DataDirectory);
        calendar.add(Calendar.MINUTE, -timeStepInMinutes);
      }
      weatherValues = CalculateWeatherValues(currentWeather, nextWeather, distanceWeights, coordinates, timeWeight, intervalLength, DataNames);
      computeEQTDec(StartDay,365);
      double[] tempIrradiance = CalculateIrradiance(generatedRoute[0].getLat(), StartTime_Hours, weatherValues[cloudIndex], EQT, DEC);
      weatherValues[ZenithIndex] = tempIrradiance[0];
      weatherValues[FreshWaterIndex] = FW_Flux;
      weatherValues[IrradianceIndex] = tempIrradiance[1];
      weatherValues[LongitudeIndex] = generatedRoute[0].getLon();
      weatherValues[LatitudeIndex] = generatedRoute[0].getLat();

      OS.writeObject(new BoundaryDataStep(weatherValues));

      int dayPointer = 0;
      int percent=-1;
      float floatPercent=0;
      for (int step = 1; step < RunSteps; step++) {
        floatPercent = 100*((step*1.0f)/(RunSteps*1.0f));
        if (floatPercent>percent+0.1) writePercent(floatPercent,targetPath, progressFile);
        
        stepOfDay = ((calendar.get(Calendar.HOUR_OF_DAY) * 60) + calendar .get(Calendar.MINUTE)) / timeStepInMinutes;
        timeWeight[0] = stepOfDay % intervalLength;
        timeWeight[1] = intervalLength - timeWeight[0];

        // advance the date by 1 time step, and check to see if day or quarter
        //  have been advanced
      
        day = calendar.get(Calendar.DAY_OF_YEAR);
        quarter = calendar.get(Calendar.HOUR_OF_DAY) / 6;
        calendar.add(Calendar.MINUTE, timeStepInMinutes); 
        dayAdvanced = (day != calendar.get(Calendar.DAY_OF_YEAR));
        quarterAdvanced = (quarter != (calendar.get(Calendar.HOUR_OF_DAY) / 6));
        newDataNextTime++;
        if (quarterAdvanced) newDataNextTime=0;
        if (newDataNextTime==1) {
          currentWeather = nextWeather;
          if (!synoptic) {
              nextWeather = LoadERA40(-1, calendar.get(GregorianCalendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(GregorianCalendar.HOUR_OF_DAY), dataRequired, DataDirectory);
          } else {
            nextWeather = LoadERA40(calendar.get(GregorianCalendar.YEAR), calendar.get(GregorianCalendar.MONTH) + 1, calendar.get(GregorianCalendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY), dataRequired, DataDirectory);
          }
        }
  
        if (dayAdvanced) {
          dayPointer++;
          convertLLintoCorners(generatedRoute[0].getLat(),generatedRoute[0].getLon(), coordinates, distanceWeights);
          computeEQTDec(calendar.get(Calendar.DAY_OF_YEAR),365);
        }
  
        weatherValues = CalculateWeatherValues(currentWeather, nextWeather, distanceWeights, coordinates, timeWeight, intervalLength, DataNames);
        hourOfDay = ((calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE))) / 60.0;
  
        tempIrradiance = CalculateIrradiance(generatedRoute[dayPointer].getLat(), hourOfDay, weatherValues[cloudIndex], EQT, DEC);
        weatherValues[ZenithIndex] = tempIrradiance[0];
        weatherValues[FreshWaterIndex] = FW_Flux;
        weatherValues[IrradianceIndex] = tempIrradiance[1];
        weatherValues[LongitudeIndex] = generatedRoute[dayPointer].getLon();
        weatherValues[LatitudeIndex] = generatedRoute[dayPointer].getLat();
        OS.writeObject(new BoundaryDataStep(weatherValues));
      }
      OS.flush();
      OS.close();
    } catch (Exception e) { e.printStackTrace(); }
  }

  private static double[] CalculateWeatherValues(double[][][] Wnow,
      double[][][] Wnext, double[] dw, int[] co,
      int[] TimeWeight, int TimeInterval, String[] DataNames) {
      double[] ReturnValues = new double[DataNames.length];
    try {
      for (int w = 0; w < DataNames.length - 5; w++) {
        final double bottomLeft0 = Wnow[w][co[0]][co[2]];
        final double bottomRight0 = Wnow[w][co[1]][co[2]];
        final double topLeft0 = Wnow[w][co[0]][co[3]];
        final double topRight0 = Wnow[w][co[1]][co[3]];
        final double leftMid0 = (bottomLeft0 * dw[2]) + (topLeft0 * dw[3]);
        final double rightMid0 = (bottomRight0 * dw[2]) + (topRight0 * dw[3]);
        final double centre0 = (leftMid0 * dw[0]) + (rightMid0 * dw[1]);
        final double bottomLeft1 = Wnext[w][co[0]][co[2]];
        final double bottomRight1 = Wnext[w][co[1]][co[2]];
        final double topLeft1 = Wnext[w][co[0]][co[3]];
        final double topRight1 = Wnext[w][co[1]][co[3]];
        final double leftMid1 = (bottomLeft1 * dw[2]) + (topLeft1 * dw[3]);
        final double rightMid1 = (bottomRight1 * dw[2]) + (topRight1 * dw[3]);
        final double centre1 = (leftMid1 * dw[0]) + (rightMid1 * dw[1]);
        ReturnValues[w] = ((centre1 * TimeWeight[0]) + (centre0 * TimeWeight[1])) / TimeInterval;

      }
    } catch (Exception ex) {
      System.err.println("The following error has occurred whilst attempting to calculate the weather (Location given below)");
      ex.printStackTrace();
      System.err.println(co[0] + " : " + co[2]);
      System.err.println(co[1] + " : " + co[3]);
      System.exit(1);
    }
 
    return ReturnValues;
  }

  private static double[] CalculateWeatherValues(double[][][] Wnow,
      double[][][] Wnext, double[] dw, int[] co, double TimeWeight, String[] DataNames) {
      double[] ReturnValues = new double[DataNames.length];
    try {
      for (int w = 0; w < DataNames.length - 5; w++) {
        final double bottomLeft0 = Wnow[w][co[0]][co[2]];
        final double bottomRight0 = Wnow[w][co[1]][co[2]];
        final double topLeft0 = Wnow[w][co[0]][co[3]];
        final double topRight0 = Wnow[w][co[1]][co[3]];
        final double leftMid0 = (bottomLeft0 * dw[2]) + (topLeft0 * dw[3]);
        final double rightMid0 = (bottomRight0 * dw[2]) + (topRight0 * dw[3]);
        final double centre0 = (leftMid0 * dw[0]) + (rightMid0 * dw[1]);
        final double bottomLeft1 = Wnext[w][co[0]][co[2]];
        final double bottomRight1 = Wnext[w][co[1]][co[2]];
        final double topLeft1 = Wnext[w][co[0]][co[3]];
        final double topRight1 = Wnext[w][co[1]][co[3]];
        final double leftMid1 = (bottomLeft1 * dw[2]) + (topLeft1 * dw[3]);
        final double rightMid1 = (bottomRight1 * dw[2]) + (topRight1 * dw[3]);
        final double centre1 = (leftMid1 * dw[0]) + (rightMid1 * dw[1]);
        ReturnValues[w] = centre0+(TimeWeight*(centre1-centre0));

      }
    } catch (Exception ex) {
      System.err.println("The following error has occurred whilst attempting to calculate the weather (Location given below)");
      ex.printStackTrace();
      System.err.println(co[0] + " : " + co[2]);
      System.err.println(co[1] + " : " + co[3]);
      System.exit(1);
    }
 
    return ReturnValues;
  }

  
  
  private static double[][][] LoadMonthlyWeather(int Month, String[] _DataNames, String DataDirectory, String climateData, int lat, int lon) {
    
    double[][][] WeatherValues = new double[_DataNames.length][lon][lat];
    DataInputStream WeatherReader;
    try {
      for (int i = 0; i < _DataNames.length; i++) {
        if (climateData.equals(ScenarioPanel2.ERA40_MONTHLY_AVG)) {
          String month = String.valueOf(Month+1);
          if (month.length()==1) month="0"+month;
          if (_DataNames[i].equals(BC_2T)) {
            WeatherValues[i] = readERA40MonthlyAvg(DataDirectory,"2t",month);

          } else if (_DataNames[i].equals(BC_R)) {
            WeatherValues[i] = readERA40MonthlyAvg(DataDirectory,"r",month);

          } else if (_DataNames[i].equals(BC_TCC)) {
            WeatherValues[i] = readERA40MonthlyAvg(DataDirectory,"tcc",month);
            
          } else if (_DataNames[i].equals(BC_WS)) {
            WeatherValues[i] = readERA40MonthlyAvg(DataDirectory,"ws",month);
         
          } else if (_DataNames[i].equals(BC_SSHF)) {
            WeatherValues[i] = readERA40MonthlyAvg(DataDirectory,"sshf",month);
            
          } else if (_DataNames[i].equals(BC_SLHF)) {
            WeatherValues[i] = readERA40MonthlyAvg(DataDirectory,"slhf",month);
            
          } else if (_DataNames[i].equals(BC_STR)) {
            WeatherValues[i] = readERA40MonthlyAvg(DataDirectory,"str",month);
            
          } else if (_DataNames[i].equals(BC_HF)) {
            double[][] buffer1 = readERA40MonthlyAvg(DataDirectory,"sshf",month);
            double[][] buffer2 = readERA40MonthlyAvg(DataDirectory,"str",month);
            double[][] buffer3 = readERA40MonthlyAvg(DataDirectory,"slhf",month);
            for (int j=0; j<buffer1.length; j++) {
              for (int k=0; k<buffer1[j].length; k++) {
                WeatherValues[i][j][k]=(buffer1[j][k]+buffer2[j][k]+buffer3[j][k]);
              }
            }
          }
          
        } else if (climateData.equals(ScenarioPanel2.BUNKER)) {
          if (FileIO.checkForExistence(DataDirectory + _DataNames[i] + VEWUtilities.LongMonthName[Month] + ".vsi")) {
            WeatherReader = new DataInputStream(new BufferedInputStream(new FileInputStream(FileIO.DefaultDataDirectory + DataDirectory + _DataNames[i] + VEWUtilities.LongMonthName[Month] + ".vsi")));
            if (_DataNames[i].equals(BC_HF)) {
              for (int j = lat-1; j >=0; j--) for (int k = 0; k < lon; k++) WeatherValues[i][k][j] = -WeatherReader.readDouble();
            } else if (_DataNames[i].equals(BC_TCC)) {
              for (int j = lat-1; j >=0; j--) for (int k = 0; k < lon; k++) WeatherValues[i][k][j] = WeatherReader.readDouble()/10.0;
            } else { 
              for (int j = lat-1; j >=0; j--) for (int k = 0; k < lon; k++) WeatherValues[i][k][j] = WeatherReader.readDouble();
            }  
            WeatherReader.close();
          } else {
            WeatherValues = null;
            i=_DataNames.length;
          }
        }
      }
    } catch (Exception e) { e.printStackTrace(); }
   return WeatherValues;
  }

  private static double[][] readScalarGrib(String dataPath, String variableName, String prefix, String year, String month, String day, String hour) {
    if (year.equals("avg")) {
      if ((month.equals("02")) && (day.equals("29"))) {
        if (hour.equals("")) {
          double[][] feb28 = readScalarAvg(dataPath,variableName,prefix,year,"02","28","");
          double[][] mar01 = readScalarAvg(dataPath,variableName,prefix,year,"03","01","");
          return fixERA40_leap(feb28,mar01,1,2);
            
        } else {
          double[][] feb28 = readScalarAvg(dataPath,variableName,prefix,year,"02","28","18");
          double[][] mar01 = readScalarAvg(dataPath,variableName,prefix,year,"03","01","00");
          if (hour.equals("00")) return fixERA40_leap(feb28,mar01,1,5);
          else if (hour.equals("06")) return fixERA40_leap(feb28,mar01,2,5);
          else if (hour.equals("12")) return fixERA40_leap(feb28,mar01,3,5);
          else return fixERA40_leap(feb28,mar01,4,5);
        }
      } else return readScalarAvg(dataPath,variableName,prefix,year,month,day,hour);
    } else try {
      if (FileIO.checkForExistence(dataPath+File.separator+variableName+year+".zip")) {
        ZipFile zipFile = new ZipFile(FileIO.DefaultDataDirectory+dataPath+File.separator+variableName+year+".zip");
        ZipEntry entry = zipFile.getEntry(variableName+"/"+year+"/"+prefix+year+month+day+hour+variableName+".grb");
        BufferedInputStream entryStream = new BufferedInputStream(zipFile.getInputStream(entry));
        return DataFile.ReadGrib(entryStream, 181,360);
      } else return null;
    } catch (Exception ex) { ex.printStackTrace(); return null; }
  }
  
  public static float readFloat(InputStream z) throws Exception {
    int i0,i1,i2,i3;
    i0 = z.read();
    i1 = z.read();
    i2 = z.read();
    i3 = z.read();
    int itotal = i0+(i1 << 8) + (i2 << 16) + (i3 << 24);
    return Float.intBitsToFloat(itotal);
  }
  
  private static double[][] readScalarAvg(String dataPath, String variableName, String prefix, String year, String month, String day, String hour) {
    try {
      double[][] data = null;
      if (FileIO.checkForExistence(dataPath+File.separator+variableName+"avg.zip")) {
        data = new double[360][181];
        ZipFile zipFile = new ZipFile(FileIO.DefaultDataDirectory+dataPath+File.separator+variableName+"avg.zip");
        final String item = month+"/"+day+"/"+prefix+"avg"+month+day+hour+variableName+".bin";
        ZipEntry entry = zipFile.getEntry(item);
        BufferedInputStream entryStream = new BufferedInputStream(zipFile.getInputStream(entry));
        for (int i=0; i<360; i++) {
          for (int j=0; j<181; j++) {
            data[i][j]=readFloat(entryStream);
          }
        }
      }
      return data;
    } catch (Exception e) {e.printStackTrace(); return null;}
  }
  
  private static double[][] readERA40MonthlyAvg(String dataPath, String variableName, String month) {
    double[][] data = null; 
    try {
      if (FileIO.checkForExistence(dataPath+variableName+File.separator+variableName+"monthly.zip")) {
        data = new double[360][181];
        ZipFile zipFile = new ZipFile(FileIO.DefaultDataDirectory+dataPath+variableName+File.separator+variableName+"monthly.zip");
        ZipEntry entry = zipFile.getEntry(variableName+month+".bin");
        BufferedInputStream entryStream = new BufferedInputStream(zipFile.getInputStream(entry));
        for (int i=0; i<360; i++) {
          for (int j=0; j<181; j++) {
            data[i][j]=readFloat(entryStream);
          }
        }
      }
      
      return data;
    } catch (Exception e) {e.printStackTrace(); return null;}
  }
  
  private static double[][][] readERA40WindSpeedAngle(String dataPath, String year, String month, String day, String hour) {
    double[][][] data3 = null;
    try {
      if (FileIO.checkForExistence(dataPath+File.separator+"ws"+year+".zip")) {
        data3 = new double[2][360][181];
        ZipFile zipFile = new ZipFile(FileIO.DefaultDataDirectory+dataPath+File.separator+"ws"+year+".zip");
        ZipEntry entry = zipFile.getEntry("ws/"+year+"/gpas"+year+month+day+hour+"10u.grb");
        BufferedInputStream entryStream = new  BufferedInputStream(zipFile.getInputStream(entry));
        final double[][] data1 = DataFile.ReadGrib(entryStream, 181,360);
        entryStream.close();
        entry = zipFile.getEntry("ws/"+year+"/gpas"+year+month+day+hour+"10v.grb");
        entryStream = new BufferedInputStream(zipFile.getInputStream(entry));
        final double[][] data2 = DataFile.ReadGrib(entryStream, 181,360);
        entryStream.close();
        for (int i=0; i<360; i++) {
          for (int j=0; j<181; j++) {
            data3[0][i][j]=Math.sqrt((data1[i][j]*data1[i][j])+(data2[i][j]*data2[i][j]));
            data3[1][i][j]=Math.atan(data1[i][j]/data2[i][j]);
          }
        }
      }
      return data3;
    } catch (Exception ex) { ex.printStackTrace(); return null; }
  }

  private static double[][] readERA40WindSpeed(String dataPath, String year, String month, String day, String hour) {
    if (year.equals("avg")) {
      if ((month.equals("02")) && (day.equals("29"))) {
        double[][] feb28 = readScalarAvg(dataPath,"ws","gpas",year,"02","28","18");
        double[][] mar01 = readScalarAvg(dataPath,"ws","gpas",year,"03","01","00");
        if (hour.equals("00")) return fixERA40_leap(feb28,mar01,1,5);
        else if (hour.equals("06")) return fixERA40_leap(feb28,mar01,2,5);
        else if (hour.equals("12")) return fixERA40_leap(feb28,mar01,3,5);
        else return fixERA40_leap(feb28,mar01,4,5);
      } else return readScalarAvg(dataPath,"ws","gpas",year,month,day,hour);
    }
    else try {
      double[][] data3 =null;
      if (FileIO.checkForExistence(dataPath+File.separator+"ws"+year+".zip")) {
        data3 = new double[360][181];
        ZipFile zipFile = new ZipFile(FileIO.DefaultDataDirectory+dataPath+File.separator+"ws"+year+".zip");
        ZipEntry entry = zipFile.getEntry("ws/"+year+"/gpas"+year+month+day+hour+"10u.grb");
        BufferedInputStream entryStream = new BufferedInputStream(zipFile.getInputStream(entry));
        final double[][] data1 = DataFile.ReadGrib(entryStream, 181,360);
        entryStream.close();
        entry = zipFile.getEntry("ws/"+year+"/gpas"+year+month+day+hour+"10v.grb");
        entryStream = new BufferedInputStream(zipFile.getInputStream(entry));
        final double[][] data2 = DataFile.ReadGrib(entryStream, 181,360);
        entryStream.close();
        for (int i=0; i<360; i++) {
          for (int j=0; j<181; j++) {
            data3[i][j]=Math.sqrt((data1[i][j]*data1[i][j])+(data2[i][j]*data2[i][j]));
          }
        }
      }
      return data3;
    } catch (Exception ex) { ex.printStackTrace(); return null; }
  }

  private static double[][][] LoadERA40(int year, int month, int day, int hour, String[] dataNames, String dataDirectory) {
    double[][] buffer1;
    double[][][] weatherValues;
    if (dataNames.length==1) {
      if (dataNames[0].equals(BC_WSA)) {
        weatherValues = new double[2][360][181];
      } else weatherValues = new double[dataNames.length][360][181];
    } else weatherValues = new double[dataNames.length][360][181];
    
    String yearString, monthString, dayString;
    int quarter = hour / 6;

    if (year>-1) yearString = Integer.toString(year);
    else yearString = "avg";
    monthString = Integer.toString(month);
    if (month<10) monthString = "0".concat(monthString);
    dayString = Integer.toString(day);
    if (day<10) dayString = "0".concat(dayString);
     
    try {
      for (int i = 0; i < dataNames.length; i++) {
        if (dataNames[i].equals(BC_SSHF)) {
          buffer1 = readScalarGrib(dataDirectory+"sshf","sshf","gpfs",yearString,monthString,dayString,"");
          for (int j=0; j<360; j++) for (int k=0; k<181; k++) weatherValues[i][j][k] = buffer1[j][k];  
          
        } else if (dataNames[i].equals(BC_SLHF)) {
          buffer1 = readScalarGrib(dataDirectory+"slhf","slhf","gpfs",yearString,monthString,dayString,"");
          for (int j=0; j<360; j++) for (int k=0; k<181; k++) weatherValues[i][j][k] = buffer1[j][k];  
         
        } else if (dataNames[i].equals(BC_HF)) {
          buffer1 = readScalarGrib(dataDirectory+"slhf","slhf","gpfs",yearString,monthString,dayString,"");
          if (buffer1!=null) {
            double[][] buffer2 = readScalarGrib(dataDirectory+"sshf","sshf","gpfs",yearString,monthString,dayString,"");
            if (buffer2!=null) {
              double[][] buffer3 = readScalarGrib(dataDirectory+"str","str","gpfs",yearString,monthString,dayString,"");
              if (buffer3!=null) {
                for (int j=0; j<360; j++) for (int k=0; k<181; k++) weatherValues[i][j][k] = buffer1[j][k]+buffer2[j][k]+buffer3[j][k];
              } else { weatherValues=null; i = dataNames.length; }
            } else { weatherValues=null; i = dataNames.length; }
          } else { weatherValues=null; i = dataNames.length; }

        } else if (dataNames[i].equals(BC_STR)) {
          buffer1 = readScalarGrib(dataDirectory+"str","str","gpfs",yearString,monthString,dayString,"");
          if (buffer1!=null) for (int j=0; j<360; j++) for (int k=0; k<181; k++) weatherValues[i][j][k] = buffer1[j][k];
          else { weatherValues=null; i = dataNames.length; }
        } else if (dataNames[i].equals(BC_WS)) {
          buffer1 = readERA40WindSpeed(dataDirectory+"ws",yearString, monthString, dayString, quarters[quarter]);
          if (buffer1!=null) for (int j=0; j<360; j++) for (int k=0; k<181; k++) weatherValues[i][j][k] = buffer1[j][k];
          else { weatherValues=null; i = dataNames.length; }
        } else if (dataNames[i].equals(BC_WSA)) {
          weatherValues = readERA40WindSpeedAngle(dataDirectory+"ws",yearString, monthString, dayString, quarters[quarter]);
          if (weatherValues==null) i = dataNames.length;

        } else if (dataNames[i].equals(BC_TCC)) {
          buffer1 = readScalarGrib(dataDirectory+"tcc","tcc","gpas",yearString,monthString,dayString,quarters[quarter]);
          if (buffer1!=null) for (int j = 0; j < 360; j++) for (int k = 0; k < 181; k++) weatherValues[i][j][k] = buffer1[j][k];
          else { weatherValues=null; i = dataNames.length; }

        } else if (dataNames[i].equals(BC_R)) {
          buffer1 = readScalarGrib(dataDirectory+"r","r","gpas",yearString,monthString,dayString,quarters[quarter]);
          if (buffer1!=null) for (int j = 0; j < 360; j++) for (int k = 0; k < 181; k++) weatherValues[i][j][k] = buffer1[j][k];
          else { weatherValues=null; i = dataNames.length; }        
        
        } else if (dataNames[i].equals(BC_2T)) {
          buffer1 = readScalarGrib(dataDirectory+"2t","2t","gpas",yearString,monthString,dayString,quarters[quarter]);
          if (buffer1!=null) for (int j = 0; j < 360; j++) for (int k = 0; k < 181; k++) weatherValues[i][j][k] = buffer1[j][k];
          else { weatherValues=null; i = dataNames.length; }
        
        }
      }
    } catch (Exception e) { weatherValues = null; e.printStackTrace(); }
    return weatherValues;
  }

   private static double[] CalculateIrradiance(double Latitude, double TimeOfDay,
      double CloudCoverage, double TimeEquation, double Declination) {
    double Fac = 783.434540967;
    double WaterVapour = 120.21146801453;

    double[] ReturnValues = { 0, 0 };

    double RLatitude = Latitude * VEWUtilities.PI / 180;
    double TR = TimeOfDay * VEWUtilities.PI / 12;
    double RLT = TR + TimeEquation - VEWUtilities.PI; // The local apparent
                                                      // time.

    ReturnValues[0] = Math.acos((Math.sin(Declination) * Math.sin(RLatitude))
        + (Math.cos(Declination) * Math.cos(RLatitude) * Math.cos(RLT)));

    if (ReturnValues[0] > 1.4) {
      ReturnValues[1] = 0;
    } else {
      double FC = Fac * Math.cos(ReturnValues[0]);
      double ROA = Math.sqrt(Math.pow(FC, 2) + (2 * Fac) + 1) - FC;
      double X = 0.32 * ROA;
      double Y = ROA;
      FC = X;
      double RDus = 0;
      double RRasc = 0;
      double RRadu = 0;
      for (int i = 0; i < 70; i++) {
        RDus += (F[i] * Math.exp(-1 * E[i] * X));
        RRasc += (F[i] * Math.exp(-1 * EE[i] * Y));
        RRadu += (F[i] * Math.exp((-1 * EE[i] * Y) - (E[i] * FC)));
      }

      // Diffuse the radiation in clear sky
      X = Math.cos(ReturnValues[0]);
      double D1 = (135.3 - RRasc) * X - (37.884 * X / (7.43 * X));
      double D2 = D1 + ((D1 + (RRasc * X)) * 0.06 * 0.0685);
      D1 = (135.3 - RDus) * X * 0.14 * (1 + X) * (1 + X) * (2 - X) / 4;
      double RDif = D2 + D1;

      // Reflectance
      X = Math.asin(3.0 * Math.sin(ReturnValues[0]) / 4);
      D1 = Math.sin(ReturnValues[0] - X) / Math.sin(ReturnValues[0] + X);
      D2 = D1 * Math.cos(ReturnValues[0] + X) / Math.cos(ReturnValues[0] - X);
      double Reflex = (D1 * D1) + (D2 * D2);
      if (Reflex < 0) {
        Reflex *= -0.5;
      } else {
        Reflex *= 0.5;
      }

      // Irradiance in cloudy sky
      X = (RRadu + WaterVapour - 135.3) * Math.cos(ReturnValues[0]);
      Y = RDif / (RDif + X);
      D1 = (X * (1 - (Reflex * (1 - Y)) - (0.066 * Y))) + RDif;
      ReturnValues[1] = 10 * D1 * (1 - (0.62 * CloudCoverage)); // Used to be CC/10
      if (ReturnValues[1] < 0) {
        ReturnValues[1] = 0;
      }
    }

    return ReturnValues;
  }
}