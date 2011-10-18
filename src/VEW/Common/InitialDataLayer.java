package VEW.Common;

import java.io.Serializable;

public class InitialDataLayer implements Serializable
{
  public final double Temperature;
  public final double Salinity;
  public final double Density;
  public final double Depth;
  public final int MixedLayerStatus;

  public static final int BeforeMixedLayer = 0;
  public static final int EqualsMixedLayer = 1;
  public static final int AfterMixedLayer  = 2;

  public InitialDataLayer(double _Temperature, double _Salinity, double _Density, double _Depth, int ML) {
    Temperature = _Temperature;
    Salinity = _Salinity;
    Density = _Density;
    Depth = _Depth;
    MixedLayerStatus = ML;
  }

  public String getCommaSeparated() {
    return Depth + "," + Temperature + "," + Salinity + "," + Density;
  }

  public String toString() {
    return "Depth: " + Depth + " Density: " + Density + " Salinity: " + Salinity + " Temperature: " + Temperature;
  }
}