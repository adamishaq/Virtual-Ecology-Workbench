/***************************************************************\
|*  File: Generator.java                                       *|
|*  Created: 16th November 2002 by Adrian Rogers (atr99)       *|
|*  Last Modified: 20th March 2003 by Adrian Rogers (atr99)    *|
|***************************************************************|
|*  Description:                                               *|
|*  This class provides the method for generating the tracks   *|
|*  in TrackGen.                                               *|
\***************************************************************/

package VEW.Scenario2;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;

import VEW.Common.VEWUtilities;

public class Generator extends Thread
{
  private TrackPosition[] GeneratedTrack;
  private GenChangeNotifier thisNotifier = new GenChangeNotifier();
  final public static int ForwardIntegrate = 0;
  final public static int ReverseIntegrate = 1;
  final public static int StaticIntegrate  = 2;
  /** The distance in metres between two longitudes (and estimating latitudes). */  
  final private static int DegreeDistance = 111319;
  
  private String DataDir;
  
  private int StartMonth;
  private int StartDay;
  private String IntegrationMethod;
  private int RunDurationDays;
  private TrackPosition StartingLocation;
  private double[][][][] Velocities = new double[12][][][];
  private int DirectionModifier;
  private double Resolution;
  private double UnitModifier = 1;
  
  private boolean AbortRun = false;
  
  public static final String INT_FWD = new String("Forward Integration");
  public static final String INT_BWD = new String("Backward Integration");
  public static final String INT_FIX = new String("Fixed Location");
  public static final String[] velRanges = new String[] {"0-20m","20-41m","41-64m", "64-89m","89-116m","116-147m","147-182m",
                               "182-222m","222-269m","269-324m","324-389m","389-465m","465-555m","0-100m AVG","0-555m AVG"};
  public static final String[] velFiles = new String[] {"Layer01","Layer02","Layer03","Layer04","Layer05","Layer06","Layer07",
                                 "Layer08","Layer09","Layer10","Layer11","Layer12","Layer13","Ave100","Ave555"};
  
  
  public static void addVelocityFields(JComboBox j) {
    for (int i=0; i<velRanges.length; i++) j.addItem(velRanges[i]);
  }
  
  public static int findVelocity(String v) {
    int i=0;
    while (!v.equals(velRanges[i])) i++;
    return i;
  }
  
  public static void addIntegrationMethods(JComboBox j) {
    j.addItem(INT_FWD);
    j.addItem(INT_BWD);
    j.addItem(INT_FIX);
  }
  
  
  
  public Generator(int _StartMonth, int _StartDay, String _IntegrationMethod, int _RunDurationDays, 
                   TrackPosition _StartingLocation, String VelocityField) {
    int velNo = findVelocity(VelocityField);
    StartMonth = _StartMonth;
    StartDay = _StartDay;
    IntegrationMethod = new String(_IntegrationMethod);
    RunDurationDays = _RunDurationDays;
    StartingLocation = _StartingLocation;
    DataDir = "Velocities" +File.separator + velFiles[velNo]+File.separator;
    DirectionModifier = -1; // Always EAST
    Resolution = 0.25;
    String[] Units = new String("-2,m,1,0,s,-1").split(",");
    UnitModifier *= Math.pow(10, Integer.parseInt(Units[0]));
    UnitModifier /= Math.pow(10, Integer.parseInt(Units[3]));
    if(Units[4].equals("s")) UnitModifier *= 3600;
  }
  
  public void run() {
    TrackPosition[] CurrentTrack = new TrackPosition[RunDurationDays + 1];
    if(IntegrationMethod.equals(INT_FIX)) {
      for(int i = 0; i < CurrentTrack.length; i++) {
        CurrentTrack[i] = StartingLocation.Replicate();
        thisNotifier.NotifyPointFound(CurrentTrack[i]);
      }
    } else {
      int DayOfTheYear, NextMonth, CurrentMonth, MonthLength;
      double TimeOfDay;
      double Tol, TimeStep, R, DT;
      double Delta;
      double[][][] CurrentVelocities;
      double[][][] NextVelocities;
      int trackPointer = 0;
      int[] TimeWeight = new int[2];
      Point2D.Double CurrentLocation = StartingLocation.getCoordinates();
      Point2D.Double[] ApproximatedChange = new Point2D.Double[6];
      Point2D.Double ApproximatedLocation = (Point2D.Double)CurrentLocation.clone();
      double ApproxX,ApproxY;
      boolean GoForward = (IntegrationMethod.equals(INT_FWD));
      
      Tol = 0.00005;
      TimeStep = 4.0;
      Delta = 0;
      R = 0;
      
      CurrentMonth = StartMonth;
      if (GoForward && (StartDay < VEWUtilities.StandardMonthMiddle[CurrentMonth])) {
        if(CurrentMonth == 0) CurrentMonth = 11;
        else CurrentMonth--;
      }
      else if (!GoForward && (StartDay > VEWUtilities.StandardMonthMiddle[CurrentMonth])) {
        if (CurrentMonth == 11) CurrentMonth = 0;
        else CurrentMonth++;
      }
      
      if(GoForward && (CurrentMonth == 11)) {
        NextMonth = 0;
        MonthLength = 31;
        if(StartDay >= VEWUtilities.StandardMonthMiddle[CurrentMonth]) TimeWeight[1] = 381 - StartDay;
        else TimeWeight[1] = 16 - StartDay;
        TimeWeight[0] = MonthLength - TimeWeight[1];
      }
      else if (GoForward) {
        NextMonth = CurrentMonth + 1;
        MonthLength = VEWUtilities.StandardMonthMiddle[NextMonth] - VEWUtilities.StandardMonthMiddle[CurrentMonth];
        TimeWeight[1] = VEWUtilities.StandardMonthMiddle[NextMonth] - StartDay;
        TimeWeight[0] = MonthLength - TimeWeight[1];
      } else if(CurrentMonth == 0) {
        NextMonth = 11;
        MonthLength = 31;
        TimeWeight[0] = 16 - StartDay;
        if(TimeWeight[0] < 0) TimeWeight[0] += 365;
        TimeWeight[1] = MonthLength - TimeWeight[0];
      }
      else
      {
        NextMonth = CurrentMonth - 1;
        MonthLength = VEWUtilities.StandardMonthMiddle[CurrentMonth] - VEWUtilities.StandardMonthMiddle[NextMonth];
        TimeWeight[0] = VEWUtilities.StandardMonthMiddle[CurrentMonth] - StartDay;
        TimeWeight[1] = MonthLength - TimeWeight[0];
      }
      
      DayOfTheYear = StartDay - 1;
      
      CurrentVelocities = FileIO.LoadVelocityData(CurrentMonth,DataDir);
      NextVelocities    = FileIO.LoadVelocityData(NextMonth,DataDir);
      Velocities[CurrentMonth] = CurrentVelocities;
      Velocities[NextMonth] = NextVelocities;
      
      CurrentTrack[trackPointer] = StartingLocation;
      thisNotifier.NotifyPointFound(CurrentTrack[trackPointer]);
      trackPointer++;
      for(int runDay = 0; (runDay < RunDurationDays) && !AbortRun; runDay++) {
        if (GoForward && (DayOfTheYear == 365)) DayOfTheYear = 1;
        else if (GoForward) DayOfTheYear++;
        else if (DayOfTheYear <= 1) DayOfTheYear = 365;
        else DayOfTheYear--;
        
        if (DayOfTheYear == VEWUtilities.StandardMonthMiddle[NextMonth]) {
          CurrentMonth = NextMonth;
          if (GoForward && CurrentMonth == 11) {
            NextMonth = 0;
            MonthLength = 365 - VEWUtilities.StandardMonthMiddle[CurrentMonth] + VEWUtilities.StandardMonthMiddle[NextMonth];
          }
          else if (GoForward) {
            NextMonth++;
            MonthLength = VEWUtilities.StandardMonthMiddle[NextMonth] - VEWUtilities.StandardMonthMiddle[CurrentMonth];
          } else if(CurrentMonth == 0) {
            NextMonth = 11;
            MonthLength = 31;
          }
          else
          {
            NextMonth--;
            MonthLength = VEWUtilities.StandardMonthMiddle[CurrentMonth] - VEWUtilities.StandardMonthMiddle[NextMonth];
          }
          TimeWeight[0] = 0;
          TimeWeight[1] = MonthLength;
          CurrentVelocities = NextVelocities;
          if(Velocities[NextMonth] == null)
          {
            Velocities[NextMonth] = FileIO.LoadVelocityData(NextMonth,DataDir);
          }
          NextVelocities = Velocities[NextMonth];
        }
        
        TimeOfDay = 0.0;
        
        while(TimeOfDay < 24.0) {
          if((TimeOfDay + TimeStep) > 24.0) DT = 24.0 - TimeOfDay;
          else DT = TimeStep;
          
          ApproximatedChange[0] = ApproximateChange(CurrentVelocities, NextVelocities, ApproximatedLocation, TimeWeight, DT);
          
          ApproxX = (ApproximatedChange[0].getX()*Constants.RungeKuttaConstant[0]) + CurrentLocation.getX();
          ApproxY = (ApproximatedChange[0].getY()*Constants.RungeKuttaConstant[0]) + CurrentLocation.getY();
          
          ApproximatedLocation.setLocation(ApproxX,ApproxY);
          
          ApproximatedChange[1] = ApproximateChange(CurrentVelocities, NextVelocities, ApproximatedLocation, TimeWeight, DT);
          
          ApproxX = (ApproximatedChange[0].getX()*Constants.RungeKuttaConstant[1]) + (ApproximatedChange[1].getX()*Constants.RungeKuttaConstant[2]) + CurrentLocation.getX();
          ApproxY = (ApproximatedChange[0].getY()*Constants.RungeKuttaConstant[1]) + (ApproximatedChange[1].getY()*Constants.RungeKuttaConstant[2]) + CurrentLocation.getY();
          
          ApproximatedLocation.setLocation(ApproxX,ApproxY);
          
          ApproximatedChange[2] = ApproximateChange(CurrentVelocities, NextVelocities, ApproximatedLocation, TimeWeight, DT);
          
          ApproxX = (ApproximatedChange[0].getX()*Constants.RungeKuttaConstant[3])
          - (ApproximatedChange[1].getX()*Constants.RungeKuttaConstant[4])
          + (ApproximatedChange[2].getX()*Constants.RungeKuttaConstant[5])
          + CurrentLocation.getX();
          ApproxY = (ApproximatedChange[0].getY()*Constants.RungeKuttaConstant[3])
          - (ApproximatedChange[1].getY()*Constants.RungeKuttaConstant[4])
          + (ApproximatedChange[2].getY()*Constants.RungeKuttaConstant[5])
          + CurrentLocation.getY();
          
          ApproximatedLocation.setLocation(ApproxX,ApproxY);
          
          ApproximatedChange[3] = ApproximateChange(CurrentVelocities, NextVelocities, ApproximatedLocation,
          TimeWeight, DT);
          
          ApproxX = (ApproximatedChange[0].getX()*Constants.RungeKuttaConstant[6])
          - (ApproximatedChange[1].getX()*Constants.RungeKuttaConstant[7])
          + (ApproximatedChange[2].getX()*Constants.RungeKuttaConstant[8])
          - (ApproximatedChange[3].getX()*Constants.RungeKuttaConstant[9])
          + CurrentLocation.getX();
          ApproxY = (ApproximatedChange[0].getY()*Constants.RungeKuttaConstant[6])
          - (ApproximatedChange[1].getY()*Constants.RungeKuttaConstant[7])
          + (ApproximatedChange[2].getY()*Constants.RungeKuttaConstant[8])
          - (ApproximatedChange[3].getY()*Constants.RungeKuttaConstant[9])
          + CurrentLocation.getY();
          
          ApproximatedLocation.setLocation(ApproxX,ApproxY);
          
          ApproximatedChange[4] = ApproximateChange(CurrentVelocities, NextVelocities, ApproximatedLocation,
          TimeWeight, DT);
          
          ApproxX = CurrentLocation.getX()
          - (ApproximatedChange[0].getX()*Constants.RungeKuttaConstant[10])
          + (ApproximatedChange[1].getX()*Constants.RungeKuttaConstant[11])
          - (ApproximatedChange[2].getX()*Constants.RungeKuttaConstant[12])
          + (ApproximatedChange[3].getX()*Constants.RungeKuttaConstant[13])
          - (ApproximatedChange[4].getX()*Constants.RungeKuttaConstant[14]);
          ApproxY = CurrentLocation.getY()
          - (ApproximatedChange[0].getY()*Constants.RungeKuttaConstant[10])
          + (ApproximatedChange[1].getY()*Constants.RungeKuttaConstant[11])
          - (ApproximatedChange[2].getY()*Constants.RungeKuttaConstant[12])
          + (ApproximatedChange[3].getY()*Constants.RungeKuttaConstant[13])
          - (ApproximatedChange[4].getY()*Constants.RungeKuttaConstant[14]);
          
          ApproximatedLocation.setLocation(ApproxX,ApproxY);
          
          ApproximatedChange[5] = ApproximateChange(CurrentVelocities, NextVelocities, ApproximatedLocation,
          TimeWeight, DT);
          
          ApproxX = (ApproximatedChange[0].getX()*Constants.RungeKuttaConstant[15])
          - (ApproximatedChange[2].getX()*Constants.RungeKuttaConstant[16])
          - (ApproximatedChange[3].getX()*Constants.RungeKuttaConstant[17])
          + (ApproximatedChange[4].getX()*Constants.RungeKuttaConstant[18])
          + (ApproximatedChange[5].getX()*Constants.RungeKuttaConstant[19]);
          ApproxY = (ApproximatedChange[0].getY()*Constants.RungeKuttaConstant[15])
          - (ApproximatedChange[2].getY()*Constants.RungeKuttaConstant[16])
          - (ApproximatedChange[3].getY()*Constants.RungeKuttaConstant[17])
          + (ApproximatedChange[4].getY()*Constants.RungeKuttaConstant[18])
          + (ApproximatedChange[5].getY()*Constants.RungeKuttaConstant[19]);
          
          ApproxX = Math.abs(ApproxX);
          ApproxY = Math.abs(ApproxY);
          if (ApproxY < ApproxX) R = ApproxX / TimeStep;
          else R = ApproxY / TimeStep;
          
          Delta = (Tol / R);
          Delta = java.lang.Math.sqrt(Delta);
          Delta = java.lang.Math.sqrt(Delta);
          Delta *= 0.84;
          if((R < Tol) || (DT < 0.005)) {
            ApproxX = (ApproximatedChange[0].getX()*Constants.RungeKuttaConstant[20])
            + (ApproximatedChange[2].getX()*Constants.RungeKuttaConstant[21])
            + (ApproximatedChange[3].getX()*Constants.RungeKuttaConstant[22])
            - (ApproximatedChange[4].getX()*Constants.RungeKuttaConstant[23]);
            ApproxY = (ApproximatedChange[0].getY()*Constants.RungeKuttaConstant[20])
            + (ApproximatedChange[2].getY()*Constants.RungeKuttaConstant[21])
            + (ApproximatedChange[3].getY()*Constants.RungeKuttaConstant[22])
            + (ApproximatedChange[4].getY()*Constants.RungeKuttaConstant[23]);
            if (GoForward) CurrentLocation.setLocation(CurrentLocation.getX() + ApproxX, CurrentLocation.getY() + ApproxY);
            else CurrentLocation.setLocation(CurrentLocation.getX() - ApproxX, CurrentLocation.getY() - ApproxY);
            TimeOfDay += DT;
          }
          
          if (Delta <= 0.1) TimeStep *= 0.1;
          else if (Delta > 2.0) TimeStep *= 2.0;
          else TimeStep *= Delta;
          
          if (TimeStep > 24.0) TimeStep = 24.0;
          else if (TimeStep < 0.001) TimeStep = 0.001;
          
          TimeOfDay++;
        }
        // CurrentTrack[trackPointer] = new TrackPosition(CurrentLocation, TrackPosition.AIM);
        CurrentTrack[trackPointer] = new TrackPosition(CurrentLocation);
        thisNotifier.NotifyPointFound(CurrentTrack[trackPointer]);
        trackPointer++;
        TimeWeight[0]++;
        TimeWeight[1]--;
      }
    }
    if(AbortRun) thisNotifier.sendCode(0);
    else {
      GeneratedTrack = (IntegrationMethod.equals(INT_BWD))?ReverseTrack(CurrentTrack):CurrentTrack;
      thisNotifier.sendCode(1);
    }
    
  }
  
  public TrackPosition[] ReverseTrack(TrackPosition[] TheTrack)
  {
    TrackPosition[] ReturnTrack = new TrackPosition[TheTrack.length];
    int j = TheTrack.length;
    for(int i = 0; i < TheTrack.length; i++)
    {
      j--;
      ReturnTrack[j] = TheTrack[i];
    }
    return ReturnTrack;
  }
  
  public void abort()
  {
    AbortRun = true;
  }
  
  
  // State Variables required by ApproximateChange to save on recalculation.
  int LastX  = -700;
  int LastY  = -700;
  int LastT1 = -1;
  double LastU1, LastU2, LastV1, LastV2;
  private Point2D.Double ApproximateChange(double[][][] CurrentVelocities,	double[][][] NextVelocities,
  Point2D.Double CurrentLocation, int[] TimeWeight, double DT)
  {
    double XTemp = CurrentLocation.getX();
    double YTemp = CurrentLocation.getY();
    if(XTemp < 0)
    {
      XTemp *= -1;
    }
    else
    {
      XTemp = 360 - XTemp;
    }
    if(XTemp < 0)
    {
      XTemp = 0;
    }
    else if(XTemp > 359.5)
    {
      XTemp = 359.5;
    }
    YTemp = 90 - YTemp;
    if(YTemp < 0)
    {
      YTemp = 0;
    }
    else if(YTemp > 179.5)
    {
      YTemp = 179.5;
    }
    XTemp /= Resolution;
    YTemp /= Resolution;
    int X = (int)Math.floor(XTemp);
    int Y = (int)Math.floor(YTemp);
    int TotalTime = TimeWeight[0] + TimeWeight[1];
    double DX,DY,U1,U2,V1,V2,D1,D2;
    int Bound;
    if((X != LastX) || (Y != LastY) || LastT1 != TimeWeight[1])
    {
      U1 = ((TimeWeight[0] * NextVelocities[0][Y][X])
      + (TimeWeight[1]  * CurrentVelocities[0][Y][X]))
      / TotalTime;
      U2 = ((TimeWeight[0] * NextVelocities[0][Y][X+1])
      + (TimeWeight[1]  * CurrentVelocities[0][Y][X+1]))
      / TotalTime;
      U1 *= DirectionModifier;
      U2 *= DirectionModifier;
      V1 = ((TimeWeight[0] * NextVelocities[1][Y][X])
      + (TimeWeight[1]  * CurrentVelocities[1][Y][X]))
      / TotalTime;
      V2 = ((TimeWeight[0] * NextVelocities[1][Y+1][X])
      + (TimeWeight[1]  * CurrentVelocities[1][Y+1][X]))
      / TotalTime;
      LastU1 = U1;
      LastU2 = U2;
      LastV1 = V1;
      LastV2 = V2;
      LastT1 = TimeWeight[1];
      LastX  = X;
      LastY  = Y;
    }
    else
    {
      U1 = LastU1;
      U2 = LastU2;
      V1 = LastV1;
      V2 = LastV2;
    }
    Bound = X;
    D1 = XTemp - Bound;
    D2 = 1 - D1;
    DX = (D1 * U2) + (D2 * U1);
    DX *= DT;
    DX *= UnitModifier; // Calculate the distance moved in an hour.
    DX /= DegreeDistance; // Convert distance in metres to distance in degrees.
    
    Bound = Y;
    D1 = YTemp - Bound;
    D2 = 1 - D1;
    DY = (D1 * V2) + (D2 * V1);
    DY *= DT;
    DY *= UnitModifier; // Calculate the distance moved in an hour.
    DY /= DegreeDistance; // Convert distance in metres to distance in degrees.
    return new Point2D.Double(DX,DY);
  }
  
  public TrackPosition[] getGeneratedTrack(boolean Generate)
  {
    if(GeneratedTrack == null && Generate)
    {
      this.run();
    }
    return GeneratedTrack;
  }
  
  public static String[] getIntegrationMethods()
  {
    String[] IntegrationMethods =
    {"Forward Integration", "Backward Integration", "Fixed Location"};
    return IntegrationMethods;
  }
  
  public void addObserver(Observer ob)
  {
    thisNotifier.addObserver(ob);
  }
  
  private class GenChangeNotifier extends Observable
  {
    public GenChangeNotifier()
    {
      super();
    }
    
    public void NotifyPointFound(TrackPosition NewPoint)
    {
      setChanged();
      notifyObservers(NewPoint);
    }
    
    public void sendCode(int CodeNumber)
    {
      setChanged();
      notifyObservers(new Integer(CodeNumber));
    }
  }
}