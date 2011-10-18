package VEW.Analyser4;

import java.awt.*;

import javax.swing.JComboBox;

public class BackgroundColour {
  private static Color[] range;
  private static int type;
  private static int graduations;
  
  public static final int HORIZONTAL = 0;
  public static final int VERTICAL = 1;  
  public static final int AMMONIUM = 0;
  public static final int AUTUMN = 1;
  public static final int BLUE_SCALE = 2;
  public static final int BONE = 3;
  public static final int CLOUD_COVER = 4;
  public static final int COOL = 5;
  public static final int COPPER = 6;  
  public static final int DENSITY = 7;
  public static final int EKMAN = 8;
  public static final int GREY_SCALE = 9;
  public static final int HEAT_FLUX = 10;
  public static final int HOT = 11;
  public static final int HSV = 12;
  public static final int IRRADIANCE = 13;
  public static final int JET = 14;
  public static final int LATENT_HEAT = 15;
  public static final int MLD = 16;
  public static final int NITRATE = 17;
  public static final int PHOSPHATE = 18;
  public static final int PHYTOPLANKTON = 19;
  public static final int SALINITY = 20;
  public static final int SENSIBLE_HEAT = 21;
  public static final int SILICATE = 22;
  public static final int SOLAR_RADIATION = 23;
  public static final int SPRING = 24;
  public static final int SUMMER = 25;
  public static final int TEMPERATURE = 26;
  public static final int THERMAL_RADIATION = 27;
  public static final int TOPOGRAPHY = 28;
  public static final int TOTAL_CLOUD_COVER = 29;
  public static final int VELOCITY = 30;
  public static final int WIND = 31;
  public static final int WIND_SPEED = 32;
  public static final int WINTER = 33;
  public static final int ZOOPLANKTON = 34;
  private static int randomCol = 1;

  
  public static void setGraduations(int g) {
    graduations = g;
  }
  
  public static int getGraduations() { return graduations; }

  public static Color randomColour() {
    float r = 0.0f;
    float g = 0.0f;
    float b = 0.0f;
    if ((randomCol % 7 == 0) || (randomCol % 7 == 1) || (randomCol % 7 ==4) || (randomCol % 7 == 6)) g = 1.0f;
    if ((randomCol % 7 == 0) || (randomCol % 7 == 2) || (randomCol % 7 ==4) || (randomCol % 7 == 5)) r = 1.0f;
    if ((randomCol % 7 == 0) || (randomCol % 7 == 3) || (randomCol % 7 ==5) || (randomCol % 7 == 6)) b = 1.0f;
    float intensity = 0.7f-(0.1f*(float)Math.floor(randomCol/7.0f));
    if (intensity<0) intensity=0;
    float ir = r;
    float ig = g;
    float ib = b;
    if (ir==0) ir=intensity;
    if (ig==0) ig=intensity;
    if (ib==0) ib=intensity;
    return new Color(ir,ig,ib);
  }
  
  public static Color nextRandomColour() {
    randomCol++;
    if (randomCol>50) randomCol=0;
    return randomColour();
  }
    
  
  public static void addColours(JComboBox jcb) {
    jcb.addItem("Ammonium");
    jcb.addItem("Autumn");
    jcb.addItem("Blue");
    jcb.addItem("Bone");
    jcb.addItem("Cloud Cover");
    jcb.addItem("Cool");
    jcb.addItem("Copper");
    jcb.addItem("Density");
    jcb.addItem("Ekman");    
    jcb.addItem("Grey");
    jcb.addItem("Heat Flux");
    jcb.addItem("Hot");    
    jcb.addItem("HSV");    
    jcb.addItem("Irradiance");
    jcb.addItem("Jet");
    jcb.addItem("Latent Heat");
    jcb.addItem("Mixing Layer Depth");
    jcb.addItem("Nitrate");
    jcb.addItem("Phosphate");
    jcb.addItem("Phytoplankton");
    jcb.addItem("Salinity");
    jcb.addItem("Sensible Heat");
    jcb.addItem("Silicate");
    jcb.addItem("Solar Radiation");
    jcb.addItem("Spring");
    jcb.addItem("Summer");
    jcb.addItem("Temperature");
    jcb.addItem("Thermal Radiation");
    jcb.addItem("Topography");
    jcb.addItem("Total Cloud Cover");
    jcb.addItem("Velocity");
    jcb.addItem("Wind");
    jcb.addItem("Wind Speed");
    jcb.addItem("Winter");
    jcb.addItem("Zooplankton");
   }

  
  public static void setType(int t) {
    type = t;
    range = new Color[graduations];
    float gF = graduations;
    float hgF = graduations/2;
    float tgF = graduations/3;
    float ttgF = 2.0f*tgF;
    float iF;
    for (int i=0; i<graduations; i++) {
      iF = i;
      
      if (type==AMMONIUM) {
        if (iF<hgF) range[i] = new Color(0.93f + (0.03f*(iF/hgF)),0.96f - (0.36f*(iF/hgF)), 0.97f - (0.37f*(iF/hgF)));
        else range[i] = new Color(0.96f + (0.04f*((iF-hgF)/hgF)),0.6f-(0.6f*((iF-hgF)/hgF)),0.6f-(0.6f*((iF-hgF)/hgF)));
      
      } else if (type==AUTUMN) {
        range[i] = new Color(1.0f,(iF/gF),0.0f);
      
      } else if (type==BLUE_SCALE) {
        range[i] = new Color(0.0f,0.0f,1.0f*(iF/gF));
      
      } else if (type==BONE) {
        if (iF<tgF) range[i] = new Color(0.26f*(iF/tgF),0.26f*(iF/tgF),0.39f*(iF/tgF));
        else if (iF<=ttgF) range[i] = new Color(0.26f+(0.31f*((iF-tgF)/tgF)),0.26f+(0.43f*((iF-tgF)/tgF)),0.39f+(0.3f*((iF-tgF)/tgF)));
        else range[i] = new Color(0.57f+(0.43f*((iF-ttgF)/tgF)),0.69f+(0.31f*((iF-ttgF)/tgF)),0.69f+(0.31f*((iF-ttgF)/tgF))); 
        
      } else if (type==CLOUD_COVER) {
        range[i] = new Color(1.0f-(0.585f*(iF/gF)),1.0f-(0.553f*(iF/gF)),1.0f-(0.542f*(iF/gF)));
        
      } else if (type==COOL) {
        range[i] = new Color((iF/gF),1.0f-(iF/gF),1.0f);

      } else if (type==COPPER) {
        range[i] = new Color((iF/gF),0.78f*(iF/gF),0.5f*(iF/gF));
      
      } else if (type==DENSITY) {
        range[i] = new Color(1.0f-(0.432f*(iF/gF)),1.0f-(iF/gF),1.0f-(0.432f*(iF/gF)));
        
      } else if (type==EKMAN) {
        range[i] = new Color(1.0f-(0.687f*(iF/gF)),1.0f-(0.383f*(iF/gF)),1.0f-(0.393f*(iF/gF)));

      } else if (type==GREY_SCALE) {
        range[i] = new Color(1.0f*(iF/gF),1.0f*(iF/gF),1.0f*(iF/gF));
                
      } else if (type==HEAT_FLUX) {
        range[i] = new Color(1.0f-(iF/gF),1.0f-(iF/gF),1.0f);
        
      } else if (type==HOT) {
        if (iF<tgF) range[i] = new Color(0.14f+(0.86f*(iF/tgF)),0.0f,0.0f);
        else if (iF<ttgF) range[i] = new Color(1.0f,(iF-tgF)/tgF,0.0f);
        else range[i] = new Color(1.0f,1.0f,(iF-ttgF)/tgF);
        
      } else if (type==HSV) {
        if (iF<tgF) range[i] = new Color(1.0f,(iF/tgF),0.0f);
        else if (iF<ttgF) range[i] = new Color(1.0f-((iF-tgF)/tgF),1.0f,0.0f);
        else range[i] = new Color(0.0f,1.0f,(iF-ttgF)/tgF);
        
      } else if (type==IRRADIANCE) {
        if (iF<hgF) range[i] = new Color(0.99f - (0.74f*(iF/hgF)),0.96f - (0.24f*(iF/hgF)),0.97f - (0.24f*(iF/hgF)));
        else range[i] = new Color(0.25f-(0.18f*((iF-hgF)/hgF)),0.72f-(0.62f*((iF-hgF)/hgF)),0.73f-(0.4f*((iF-hgF)/hgF)));

      } else if (type==JET) {
        float f1o5 = gF/5;
        float f2o5 = 2*f1o5;
        float f3o5 = 3*f1o5;
        float f4o5 = 4*f1o5;
        if (iF<f1o5) range[i] = new Color(0.0f,0.0f,0.5f+(0.5f*(iF/f1o5)));
        else if (iF<f2o5) range[i] = new Color(0.0f,(iF-f1o5)/f1o5,1.0f);
        else if (iF<f3o5) range[i] = new Color((iF-f2o5)/f1o5,1.0f,1.0f-((iF-f2o5)/f1o5));
        else if (iF<f4o5) range[i] = new Color(1.0f,1.0f-((iF-f3o5)/f1o5),0.0f);
        else range[i] = new Color(1.0f-(0.5f*((iF-f4o5)/f1o5)),0.0f,0.0f);
              
      } else if (type==LATENT_HEAT) {
        range[i]= new Color(1.0f-(0.93f*(iF/gF)),1.0f-(0.26f*(iF/gF)),1.0f-(0.93f*(iF/gF)));
        
      } else if (type==MLD) {
        range[i] = new Color(1.0f-(iF/gF),1.0f-(0.96f*(iF/gF)),1.0f-(0.295f*(iF/gF)));
        
      } else if (type==NITRATE) {
        range[i] = new Color(1.0f-(0.648f*(iF/gF)),1.0f-(0.51f*(iF/gF)),1.0f-(0.706f*(iF/gF)));
        
      } else if (type==PHOSPHATE) {
        range[i] = new Color(1.0f-(0.099f*(iF/gF)),1.0f-(0.099f*(iF/gF)),1.0f-(iF/gF));
 
      } else if (type==PHYTOPLANKTON) {
        if (iF<hgF) range[i] = new Color(0.95f-(0.95f*(iF/hgF)),0.99f,0.95f-(0.45f*(iF/hgF)));
        else range[i] = new Color(0.15f*((iF-hgF)/hgF),1.0f-(0.75f*((iF-hgF)/hgF)),0.5f-(0.41f*((iF-hgF)/hgF)));
        
      } else if (type==SALINITY) {
        range[i] = new Color(1.0f-(0.02f*(iF/gF)),1.0f-(0.65f*(iF/gF)),1.0f-(iF/gF));
              
      } else if (type==SENSIBLE_HEAT) {
        range[i] = new Color(1.0f-(0.48f*(iF/gF)),1.0f-(0.71f*(iF/gF)),1.0f-(0.69f*(iF/gF)));
        
      } else if (type==SILICATE) {
        range[i] = new Color(1.0f-(0.373f*(iF/gF)),1.0f-(0.687f*(iF/gF)),1.0f-(0.687f*(iF/gF)));
      
      } else if (type==SOLAR_RADIATION) {
        range[i] = new Color(1.0f, 1.0f-(0.44f*(iF/gF)), 1.0f-(0.19f*(iF/gF)));
        
      } else if (type==SPRING) {
        range[i] = new Color(1.0f,(iF/gF),1.0f-(iF/gF)); 
      
      } else if (type==SUMMER) {
        range[i] = new Color((iF/gF),0.5f+(0.5f*(iF/gF)),0.4f);
        
      } else if (type==TEMPERATURE) {
        range[i] = new Color(1.0f-(0.215f*(iF/gF)),1.0f-(iF/gF),1.0f-(iF/gF));

      } else if (type==THERMAL_RADIATION) {
        range[i] = new Color(1.0f-(iF/gF),1.0f-(iF/gF),1.0f);
        
      } else if (type==TOPOGRAPHY) {
        range[i] = new Color(0.0f, 1.0f-(0.7f*(iF/gF)),1.0f-(0.35f*(iF/gF)));
        
      } else if (type==TOTAL_CLOUD_COVER) {
        range[i] = new Color(0.58f+(0.42f*(iF/gF)),0.875f+(0.125f*(iF/gF)),0.96f+(0.04f*(iF/gF)));
         
      } else if (type==VELOCITY) {
        range[i] = new Color(1.0f-(0.49f*(iF/gF)),1.0f-(0.882f*(iF/gF)),1.0f-(0.471f*(iF/gF)));
      
      } else if (type==WIND) {
        range[i] = new Color(1.0f-(0.6f*(iF/gF)),1.0f-(0.6f*(iF/gF)),1.0f-(0.6f*(iF/gF)));
      
      } else if (type==WIND_SPEED) {
        range[i] = new Color(1.0f-(0.412f*(iF/gF)),1.0f-(0.412f*(iF/gF)),1.0f-(0.412f*(iF/gF)));        
      
      } else if (type==WINTER) {
        range[i] = new Color(0.0f,(iF/gF),1.0f-(0.5f*(iF/gF)));
          
      } else if (type==ZOOPLANKTON) {
        if (iF<hgF) range[i] = new Color(0.93f+(0.01f*(iF/hgF)),0.9f-(0.28f*(iF/hgF)),0.9f-(0.17f*(iF/hgF)));
        else range[i] = new Color(0.95f+(0.01f*((iF-hgF)/hgF)),0.61f-(0.45f*((iF-hgF)/hgF)),0.72f-(0.19f*((iF-hgF)/hgF)));
      }
    }
  }
  
  public static void setType(int t, int g) {
    setGraduations(g);
    setType(t);
  }

  public static void plotPreview(Graphics g, int startX, int startY, int width, int height, int orientation) {
    if (orientation==HORIZONTAL) {
      for (int i=0; i<width; i++) {
        int x = (int)Math.floor(((double)i/(double)width)*(range.length-1));
        g.setColor(range[x]);
        g.drawLine(startX+i,startY,startX+i,startY+height);
      }
    } else {
      for (int j=0; j<height; j++) {
        int x = (int)Math.floor(((double)j/(double)height)*(range.length-1));
        g.setColor(range[x]);
        g.drawLine(startX,startY+j,startX+width,startY+j);
      }
    }
  }

  
  public static Color getColour(double val, double min, double max) {
    int index = (int) Math.floor(((val-min)/(max-min))*(range.length-1));
    if (index<0) index=0;
    if (index>=range.length) index=range.length-1;
    return range[index];
  }
}



  