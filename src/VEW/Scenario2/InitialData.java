package VEW.Scenario2;

//import java.util.zip.*;
import java.io.*;

import VEW.Common.InitialDataLayer;
import VEW.Common.InitialDataObject;
import VEW.Common.XML.XMLTag;

public class InitialData implements Serializable {
  public static void createInitialData(XMLTag ModelFile, String ModelPath) {
    XMLTag ICTag = ModelFile.getTag("initialconditions");
    XMLTag[] FieldTags = ICTag.getTags("field");
    String ProcessingName;
    double[] ProcessingProfile;
    int ColumnDepth = Integer.parseInt(ModelFile.getTag("initialconditions").getAttribute("size"));
    final String[] NutrientName = new String[FieldTags.length - 3];
    final double[][] NutrientProfile = new double[FieldTags.length - 3][ColumnDepth];
    double[] DensityProfile = new double[ColumnDepth];
    double[] TemperatureProfile = new double[ColumnDepth];
    double[] SalinityProfile = new double[ColumnDepth];
    int NutrientPointer = 0;
    String[] DataValues;
    final double MixedLayerDepth = Double.parseDouble(ICTag.getValue("turbocline"));
    for (int i = 0; i < FieldTags.length; i++) {
      ProcessingProfile = new double[ColumnDepth];
      DataValues = FieldTags[i].getValue().split(",");
      for (int j = 0; j < DataValues.length; j++)
        ProcessingProfile[j] = Double.parseDouble(DataValues[j]);
      ProcessingName = FieldTags[i].getAttribute("name");
      if (ProcessingName.equals("Temperature")) TemperatureProfile = ProcessingProfile;
      else if (ProcessingName.equals("Salinity")) SalinityProfile = ProcessingProfile;
      else if (ProcessingName.equals("Density")) DensityProfile = ProcessingProfile;
      else {
        NutrientName[NutrientPointer] = ProcessingName;
        NutrientProfile[NutrientPointer] = ProcessingProfile;
        NutrientPointer++;
      }
    }
    int DepthPointer = 0;
    int Offset;
    double[] TopLayerDepths = new double[21];
    double Power = -2;
    TopLayerDepths[0] = 0;
    for (int i = 1; i < 21; i++) {
      TopLayerDepths[i] = Math.pow(10, Power);
      Power += 0.1;
    }
    InitialDataLayer[] Layers;
    if (Math.floor(MixedLayerDepth) != MixedLayerDepth) {
      if (MixedLayerDepth > 1) {
        Layers = new InitialDataLayer[ColumnDepth + 21];
        for (Offset = 0; Offset < 21; Offset++) {
          Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[0], SalinityProfile[0], DensityProfile[0], TopLayerDepths[Offset], InitialDataLayer.BeforeMixedLayer);
        }
        Offset--;
        DepthPointer++;
        for (; DepthPointer < MixedLayerDepth; DepthPointer++) {
          Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[DepthPointer], SalinityProfile[DepthPointer], DensityProfile[DepthPointer], DepthPointer, InitialDataLayer.BeforeMixedLayer);
        }
        Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[DepthPointer - 1], SalinityProfile[DepthPointer - 1], DensityProfile[DepthPointer - 1], MixedLayerDepth, InitialDataLayer.EqualsMixedLayer);
        Offset++;
        for (; DepthPointer < ColumnDepth; DepthPointer++) {
          Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[DepthPointer], SalinityProfile[DepthPointer], DensityProfile[DepthPointer], DepthPointer, InitialDataLayer.AfterMixedLayer);
        }
      } else {
        boolean IsLayer = false;
        for (int i = 0; i < 21 && !IsLayer; i++) {
          if (MixedLayerDepth == TopLayerDepths[i])
            IsLayer = true;
        }
        if (IsLayer) {
          Layers = new InitialDataLayer[ColumnDepth + 20];
        } else {
          Layers = new InitialDataLayer[ColumnDepth + 21];
        }
        for (Offset = 0; TopLayerDepths[Offset] < MixedLayerDepth; Offset++) {
          Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[0], SalinityProfile[0], DensityProfile[0], TopLayerDepths[Offset], InitialDataLayer.BeforeMixedLayer);
        }
        Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[0], SalinityProfile[0], DensityProfile[0], MixedLayerDepth, InitialDataLayer.EqualsMixedLayer);
        if (!IsLayer) // Then TopLayerDepths[Offset] will not be equal to
                      // MixedLayerDepth
        {
          DepthPointer++;
        }
        for (; Offset < 21; Offset++) {
          Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[0], SalinityProfile[0], DensityProfile[0], TopLayerDepths[Offset], InitialDataLayer.AfterMixedLayer);
        }
        Offset--;
        Offset += DepthPointer;
        for (DepthPointer = 1; DepthPointer < ColumnDepth; DepthPointer++) {
          Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[DepthPointer], SalinityProfile[DepthPointer], DensityProfile[DepthPointer], DepthPointer, InitialDataLayer.AfterMixedLayer);
        }
      }
    } else {
      Layers = new InitialDataLayer[ColumnDepth + 20];
      for (Offset = 0; Offset < 21; Offset++) {
        Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[0], SalinityProfile[0], DensityProfile[0], TopLayerDepths[Offset], InitialDataLayer.BeforeMixedLayer);
      }
      Offset--;
      DepthPointer++;
      for (; DepthPointer < MixedLayerDepth; DepthPointer++) {
        Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[DepthPointer], SalinityProfile[DepthPointer], DensityProfile[DepthPointer], DepthPointer, InitialDataLayer.BeforeMixedLayer);
      }
      Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[DepthPointer], SalinityProfile[DepthPointer], DensityProfile[DepthPointer], MixedLayerDepth, InitialDataLayer.EqualsMixedLayer);
      DepthPointer++;
      for (; DepthPointer < ColumnDepth; DepthPointer++) {
        Layers[DepthPointer + Offset] = new InitialDataLayer(TemperatureProfile[DepthPointer], SalinityProfile[DepthPointer], DensityProfile[DepthPointer], DepthPointer, InitialDataLayer.AfterMixedLayer);
      }
    }
    WriteToFile(MixedLayerDepth,Layers,NutrientName,NutrientProfile,ModelPath);
  }

  public double[] getNutrient(String Nutrient, String[] NutrientName, double[][] NutrientProfile) {
    int i = 0;
    while (i < NutrientName.length && !NutrientName[i].equals(Nutrient)) {
      i++;
    }
    if (i == NutrientName.length) {
      double[] TempReturn = new double[501];
      for (int j = 0; j < 501; j++) {
        TempReturn[j] = -99.9999;
      }
      return TempReturn;
    } else {
      return NutrientProfile[i];
    }
  }

  public static boolean WriteToFile(double MixedLayerDepth, InitialDataLayer[] Layers, String[] NutrientName, double[][] NutrientProfile, String FilePath) {
    try {
      ObjectOutputStream OS = new ObjectOutputStream(new DataOutputStream(new BufferedOutputStream(new FileOutputStream(FilePath + "/InitialConditions.vso"))));
      OS.writeObject(new InitialDataObject(MixedLayerDepth, Layers, NutrientName, NutrientProfile));
      OS.flush();
      OS.close();
      return true;
    } catch (Exception e) {
      System.out.println("Error writing initial conditions");
      e.printStackTrace();
      System.exit(1);
      return false;
    }
  }

  public static InitialDataObject ReadFromFile(String FilePath) {
    try {
      ObjectInputStream OS = new ObjectInputStream(new DataInputStream(new BufferedInputStream(new FileInputStream(FilePath + "/InitialConditions.vso"))));
      InitialDataObject ReturnValue = (InitialDataObject) OS.readObject();
      return ReturnValue;
    } catch (Exception e) {
      System.out.println("Error reading intitial conditions");
      e.printStackTrace();
      return null;
    }
  }
}
