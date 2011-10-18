package VEW.Controller2;

import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;

import VEW.Common.XML.XMLTag;

public class MiscUtils {
  
  public static void findDateLimitsCOMPAT(XMLTag theModel, GregorianCalendar startSim, GregorianCalendar endSim) {
    XMLTag scenarioTag = theModel.getTag("scenario/column");
    int StartYear = Integer.parseInt(scenarioTag.getValue("startyear"));
    int StartDay = Integer.parseInt(scenarioTag.getValue("startday")) + 1;
    int StartMonth = Integer.parseInt(scenarioTag.getValue("startmonth"));
    String[] StartTimeString = scenarioTag.getValue("starttime").split(":");
    int StartTime = 2 * (Integer.parseInt(StartTimeString[0]));
    if (StartTimeString[1].equals("30")) StartTime++;
    int DurationTimeSteps = Integer.parseInt(scenarioTag.getValue("duration"));
    int Hours = StartTime / 2;
    int Minutes = 30 * (StartTime % 2);
    SimpleTimeZone TZ = new SimpleTimeZone(0, "GMT") {
      public boolean useDaylightTime() { return false; }
    };

    startSim.set(StartYear, StartMonth, StartDay, Hours, Minutes, 0);
    startSim.set(GregorianCalendar.MILLISECOND,0);
    startSim.setTimeZone(TZ);
    endSim.set(StartYear, StartMonth, StartDay, Hours, Minutes, 0);
    endSim.set(GregorianCalendar.MILLISECOND,0);    

    int hourInc = (DurationTimeSteps / (3600 / Integer.parseInt(scenarioTag.getValue("steplength"))));
    endSim.add(GregorianCalendar.HOUR, hourInc);
    endSim.add(GregorianCalendar.MINUTE, -30);
  }
  
  public static void findDateLimits(XMLTag theModel, GregorianCalendar startSim, GregorianCalendar endSim) {
    if (theModel.getTag("track")!=null) {
      startSim.setTimeInMillis(Long.parseLong(theModel.getValue("track/start")));
      endSim.setTimeInMillis(Long.parseLong(theModel.getValue("track/end")));
      startSim.set(GregorianCalendar.MILLISECOND,0);
      endSim.set(GregorianCalendar.MILLISECOND,0);
      int secPerStep = -Integer.parseInt(theModel.getValue("track/secstep"));
      endSim.add(GregorianCalendar.SECOND,secPerStep);
    }
  }
  
  public static String[] getPhysicalDepths(int depth) {
    String[] physicsDepths = new String[depth+20];
    physicsDepths[0]="0.0";
    float power = -2;
    for (int i = 0; i < 20; i++) {
      physicsDepths[i+1]=String.valueOf(Math.pow(10,power));
      power = power + 0.1f;
    }
    for (int i = 1; i < depth; i++) physicsDepths[i+20]=String.valueOf(i)+".0";
    return physicsDepths;
  }
}
