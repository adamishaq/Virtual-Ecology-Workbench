package VEW.Common;

import java.io.Serializable;

public class BoundaryDataStep implements Serializable {
  public double[] BoundaryData;
 
  public BoundaryDataStep(double[] _BoundaryData) {
    BoundaryData = (double[])_BoundaryData.clone();
  }
  
  public String getCommaSeparated() {
    String TempString = String.valueOf(BoundaryData[0]);
    for (int i = 1; i < BoundaryData.length; i++)
      TempString = TempString + "," + BoundaryData[i];
    return TempString;
  }
}