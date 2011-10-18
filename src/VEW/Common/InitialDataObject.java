package VEW.Common;

import java.io.Serializable;

public class InitialDataObject implements Serializable
{
  public final double MixedLayerDepth;
  public final InitialDataLayer[] Layers;
  public final String[] NutrientName;
  public final double[][] NutrientProfile;

  public InitialDataObject(double MLD, InitialDataLayer[] L, String[] NN, double[][] NP)
  {
    MixedLayerDepth = MLD;
    Layers = L;
    NutrientName = NN;
    NutrientProfile = NP;
  }
}