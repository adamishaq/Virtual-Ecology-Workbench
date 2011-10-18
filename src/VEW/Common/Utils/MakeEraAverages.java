package VEW.Common.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import VEW.Common.StringTools;
import VEW.Scenario2.DataFile;

public class MakeEraAverages {
  //public static final String e40Path = "E:"+File.separator+"VEW3"+File.separator+"ScenarioData"+File.separator+
                                       //"Climate"+File.separator+"ERA40"+File.separator;
  public static final String e40Path = "c:\\wes\\work\\ScenarioData\\Climate\\ERA40\\";
  
  public static String replaceYear(String fileName, String year) {
    return StringTools.replace(fileName,"%year%",year);
  }
  
  public static String replaceAll(String fileName, String year, String month, String day, String hour, String vec) {
    if (month.length()==1) month="0"+month;
    if (day.length()==1) day="0"+day;
    if (hour.length()==1) hour="0"+hour;
    
    String newString = new String(fileName);
    newString = StringTools.replace(newString,"%year%",year);
    newString = StringTools.replace(newString,"%month%",month);
    newString = StringTools.replace(newString,"%day%",day);
    newString = StringTools.replace(newString,"%hour%",hour);
    return newString;
  }
  
  public static void writeFloat(ZipOutputStream z, float f) throws Exception {
    int i = Float.floatToIntBits(f);
    int i0 = i & 0xff;
    int i1 = (i & 0xff00) >> 8;
    int i2 = (i & 0xff0000) >> 16;
    int i3 = (i & 0xff000000) >> 24;
    z.write(i0);
    z.write(i1);
    z.write(i2);
    z.write(i3);
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
    
      
  
  public static void createCompressedLeapYear(String zipFileName, String zip2FileName, String gribFileName, String dirName, String eraName, boolean sixHourly) throws Exception {
      for (int year=1958; year<=2001; year++) {
      if (year%4==0) {
        createCompressedLeapYear(zipFileName, zip2FileName, gribFileName, dirName, eraName, String.valueOf(year), sixHourly);
      }
    }
  }
  
  public static void createCompressedLeapYear(String zipFileName, String zip2FileName, String gribFileName, String dirName, String eraName, String year, boolean sixHourly) throws Exception {
    int[] monthLengths = new int[] {31,29,31,30,31,30,31,31,30,31,30,31};
    System.out.println("Converting leap year "+year);
    String fileName = replaceYear(zipFileName,year);
    ZipFile zf = new ZipFile(e40Path+dirName+"/"+fileName);
    String newZipName = e40Path+dirName+"/"+replaceYear(zip2FileName,year+"cly");
    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(newZipName));
    double[][] previousData = null; // 360 181
    double[][] nextData = null; // 360 181
    double[][] weightedData = new double[360][181];
    String monthString, dayString, hourString;
    double daysDone = 0.0;
    for (int month=1; month<=12; month++) {
      if (month>=10) monthString = String.valueOf(month);
      else monthString = "0"+String.valueOf(month);
      for (int day=1; day<=monthLengths[month-1]; day++) {
        if ((month<12) || (day<31)) {
          if (day>=10) dayString = String.valueOf(day);
          else dayString = "0"+String.valueOf(day);
          double weightForPrevious = (364.0-daysDone)/364.0; //(Initial weight = 364/364, final = 0/364)
          double weightForNext = daysDone/364.0;  // (Initial weight = 0/364, final = 364/364)
          daysDone++;
          for (int hour=0; hour<24; hour+=6) {
            if (!sixHourly) hour = 24;
            if (hour>=10) hourString = String.valueOf(hour);
            else hourString="0"+String.valueOf(hour);
            String theGrib = dirName+"/"+String.valueOf(year)+"/"+replaceAll(gribFileName,String.valueOf(year),monthString,dayString,hourString,"");
            ZipEntry ze = zf.getEntry(theGrib);
            if (ze!=null) {
              InputStream entryStream = zf.getInputStream(ze);
              nextData = DataFile.ReadGrib(entryStream, 181,360);
              if (previousData==null) {
                for (int i=0; i<360; i++) {
                  for (int j=0; j<181; j++) {
                    weightedData[i][j]=nextData[i][j];
                  }
                }
                previousData=nextData;
              } else {
                for (int i=0; i<360; i++) {
                  for (int j=0; j<181; j++) {
                    weightedData[i][j]=(previousData[i][j]*weightForPrevious)+(nextData[i][j]*weightForNext);
                  }
                }
              }
              String outputMonthString = monthString;
              String outputDayString = dayString;
              int outputMonth=month;
              int outputDay=day;
              if ((outputMonth>2) || (outputMonth==2 && outputDay==29)) {
                outputDay = day+1;
                if (outputDay>monthLengths[outputMonth-1]) {
                  outputDay=1;
                  outputMonth++;
                }
                outputMonthString = String.valueOf(outputMonth);
                if (outputMonthString.length()==1) outputMonthString="0"+outputMonthString;
                outputDayString = String.valueOf(outputDay);
                if (outputDayString.length()==1) outputDayString="0"+outputDayString;
              }
              String vsiName = outputMonthString+"/"+outputDayString+"/"+replaceAll(gribFileName,"avg",outputMonthString,outputDayString,String.valueOf(hour),"");
              System.out.println(vsiName+" <- "+year+"/"+month+"/"+day+"/"+hour);
              vsiName = StringTools.replace(vsiName,".grb", ".bin");
              zos.putNextEntry(new ZipEntry(vsiName));
              for (int i=0; i<360; i++) {
                for (int j=0; j<181; j++) {
                  writeFloat(zos,(float) weightedData[i][j]);
                }
              }
              zos.closeEntry();
            }
          }
        }
      }
    }
    zos.close();
    zf.close();
  }
  
  public static void createAvg(String zipFileName, String gribFileName, String dirName, String eraName, boolean sixHourly) throws Exception {
    System.out.println("Processing "+eraName);
   
    ZipFile[] zf = new ZipFile[44];
    float[][] avgData = new float[360][181];
    for (int year=0; year<44; year++) {
      String fileName;
      if ((year+1958)%4==0) fileName = replaceYear(zipFileName,String.valueOf(year+1958)+"cly");
      else fileName = replaceYear(zipFileName,String.valueOf(year+1958));
      zf[year] = new ZipFile(e40Path+dirName+"/"+fileName);
    }
    String newZipName = e40Path+dirName+"/"+replaceYear(zipFileName,"avg");      
    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(newZipName));      
    
    for (int month=1; month<=12; month++) {
      String monthString = String.valueOf(month);
      if (monthString.length()==1) monthString="0"+monthString;
      for (int day=1; day<=31; day++) {
        String dayString = String.valueOf(day);
        if (dayString.length()==1) dayString="0"+dayString;
        for (int hour=0; hour<24; hour+=6) {
          if (!sixHourly) hour=24;
          String hourString = String.valueOf(hour);
          if (hourString.length()==1) hourString="0"+hourString;
          float countEntries = 0;
         
          for (int year=1958; year<=2001; year++) {
            if (year%4==0) {
              String vsiName = monthString+"/"+dayString+"/"+replaceAll(gribFileName,"avg",String.valueOf(month),String.valueOf(day),String.valueOf(hour),"");
              vsiName = StringTools.replace(vsiName,".grb", ".bin");
              ZipEntry ze = zf[year-1958].getEntry(vsiName);
              if (ze!=null) {
                countEntries++;
                InputStream entryStream = zf[year-1958].getInputStream(ze);
                for (int i=0; i<360; i++) for (int j=0; j<181; j++) avgData[i][j]+=readFloat(entryStream);
              }
            } else {
              String theGrib = dirName+"/"+String.valueOf(year)+"/"+replaceAll(gribFileName,String.valueOf(year),monthString,dayString,hourString,"");
              ZipEntry ze = zf[year-1958].getEntry(theGrib);
              if (ze!=null) {
                countEntries++;
                InputStream entryStream = zf[year-1958].getInputStream(ze);
                double[][] data = DataFile.ReadGrib(entryStream,181,360);
                for (int i=0; i<360; i++) for (int j=0; j<181; j++) avgData[i][j]+=data[i][j];
              } else {
                System.out.println("Couldn't find "+theGrib+" in "+zf[year-1958].getName());
              }
            }
          }
          if (countEntries>0) {
            System.out.println(dayString+"/"+monthString+" at "+hourString+":00 - "+countEntries+" entries");
            String vsiName = monthString+"/"+dayString+"/"+replaceAll(gribFileName,"avg",String.valueOf(month),String.valueOf(day),String.valueOf(hour),"");
            vsiName = StringTools.replace(vsiName,".grb", ".bin");
            zos.putNextEntry(new ZipEntry(vsiName));
            for (int i=0; i<360; i++) {
              for (int j=0; j<181; j++) {
                avgData[i][j]/=countEntries;
                writeFloat(zos,avgData[i][j]);
                avgData[i][j]=0;
              }
            }
            zos.closeEntry();
          }
        }
      }
    }
    zos.flush();
    zos.close();
  }
  
  public static void createWSAvgs() throws Exception {
    System.out.println("Processing ERA40 Wind Speed");
    ZipFile[] zfU = new ZipFile[44];
    ZipFile[] zfV = new ZipFile[44];    
    float[][] avgData = new float[360][181];
    for (int year=0; year<44; year++) {
      String yearString = String.valueOf(year+1958);
      if ((year+1958)%4==0) {
        zfU[year] = new ZipFile(e40Path+"ws/wu"+yearString+"cly.zip");
        zfV[year] = new ZipFile(e40Path+"ws/wv"+yearString+"cly.zip");
      } else {
        zfU[year] = new ZipFile(e40Path+"ws/ws"+yearString+".zip");
        zfV[year] = null;
      }
    }
    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(e40Path+"ws/wsavg.zip"));      
    for (int month=1; month<=12; month++) {
      String monthString = String.valueOf(month);
      if (monthString.length()==1) monthString="0"+monthString;
      for (int day=1; day<=31; day++) {
        String dayString = String.valueOf(day);
        if (dayString.length()==1) dayString="0"+dayString;
        for (int hour=0; hour<24; hour+=6) {
          String hourString = String.valueOf(hour);
          if (hourString.length()==1) hourString="0"+hourString;
          float countEntries = 0;
          double[][] dataU, dataV;
          for (int year=1958; year<=2001; year++) {
            if (year%4==0) {
              ZipEntry zeU = zfU[year-1958].getEntry(monthString+"/"+dayString+"/gpasavg"+monthString+dayString+hourString+"10u.bin");
              ZipEntry zeV = zfV[year-1958].getEntry(monthString+"/"+dayString+"/gpasavg"+monthString+dayString+hourString+"10v.bin");              
              if ((zeU!=null) && (zeV!=null)) {
                countEntries++;
                InputStream entryStreamU = zfU[year-1958].getInputStream(zeU);
                InputStream entryStreamV = zfV[year-1958].getInputStream(zeV);                
                float dataUFloat,dataVFloat, magnitude;
                for (int i=0; i<360; i++) {
                  for (int j=0; j<181; j++) { 
                    dataUFloat = readFloat(entryStreamU);
                    dataVFloat = readFloat(entryStreamV);
                    magnitude = (float) Math.sqrt((dataUFloat*dataUFloat)+(dataVFloat*dataVFloat));
                    avgData[i][j]+=magnitude;
                  }
                }
              }

            } else {
              String theGribU = "ws/"+String.valueOf(year)+"/gpas"+String.valueOf(year)+monthString+dayString+hourString+"10u.grb";
              String theGribV = "ws/"+String.valueOf(year)+"/gpas"+String.valueOf(year)+monthString+dayString+hourString+"10v.grb";              
              ZipEntry zeU = zfU[year-1958].getEntry(theGribU);
              ZipEntry zeV = zfU[year-1958].getEntry(theGribV);              
              if ((zeU!=null) && (zeV!=null)) {
                countEntries++;
                InputStream entryStreamU = zfU[year-1958].getInputStream(zeU);
                InputStream entryStreamV = zfU[year-1958].getInputStream(zeV);                
                dataU = DataFile.ReadGrib(entryStreamU, 181,360);
                dataV = DataFile.ReadGrib(entryStreamV, 181,360);                
                for (int i=0; i<360; i++) {
                  for (int j=0; j<181; j++) {
                    float magnitude = (float) Math.sqrt((dataU[i][j]*dataU[i][j])+(dataV[i][j]*dataV[i][j]));
                    avgData[i][j]+=magnitude;
                  }
                }
              } 
            }
          }
          if (countEntries>0) {
            System.out.println(dayString+"/"+monthString+" at "+hourString+":00 - "+countEntries+" entries");
            zos.putNextEntry(new ZipEntry(monthString+"/"+dayString+"/gpasavg"+monthString+dayString+hourString+"ws.bin"));            
            for (int i=0; i<360; i++) {
              for (int j=0; j<181; j++) {
                avgData[i][j]/=countEntries;
                writeFloat(zos,avgData[i][j]);
                avgData[i][j]=0;
              }
            }
            zos.closeEntry();
          }
        }
      }
    }
    zos.flush();
    zos.close();
  }

  
  public static void createMonthlyAvg6(String varName) {
    try {
      final int[] monthLengths = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
      System.out.println("Creating monthly avg for (6-hourly) mean year: "+varName);
      ZipFile meanYear = new ZipFile(e40Path+varName+File.separator+varName+"avg.zip");
      ZipOutputStream monthAvg = new ZipOutputStream(new FileOutputStream(e40Path+varName+File.separator+varName+"monthly.zip"));
      for (int month=0; month<12; month++) {
        String monthString = String.valueOf(month+1);
        System.out.print(varName+"-"+monthString+": ");
        if (monthString.length()==1) monthString="0"+monthString;
        double[][] total = new double[360][181];
        for (int i=0; i<360; i++) for (int j=0; j<181; j++) total[i][j]=0.0;
        for (int day=1; day<=monthLengths[month]; day++) {
          String dayString = String.valueOf(day);
          if (dayString.length()==1) dayString="0"+dayString;
          System.out.print(dayString+",");
          
          for (int hour=0; hour<24; hour+=6) {
            String hourString = String.valueOf(hour);
            if (hourString.length()==1) hourString="0"+hourString;
            ZipEntry ze = meanYear.getEntry(monthString+"/"+dayString+"/gpasavg"+monthString+dayString+hourString+varName+".bin");
            InputStream zeStream = meanYear.getInputStream(ze);
            for (int i=0; i<360; i++) {
              for (int j=0; j<181; j++) {
                total[i][j]+=readFloat(zeStream);
              }
            }
          }
        }
        System.out.println();
        monthAvg.putNextEntry(new ZipEntry(varName+monthString+".bin"));
        
        for (int i=0; i<360; i++) 
          for (int j=0; j<181; j++)  
            writeFloat(monthAvg,(float)(total[i][j]/(4.0*monthLengths[month])));
        monthAvg.closeEntry();
      }
      monthAvg.flush();
      monthAvg.close();
      meanYear.close();
    } catch (Exception e) { e.printStackTrace(); }
  }
   
  public static void createMonthlyAvg(String varName) {
    System.out.println("Creating monthly avg for (daily) mean year: "+varName);
    try {
      final int[] monthLengths = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
      ZipFile meanYear = new ZipFile(e40Path+varName+File.separator+varName+"avg.zip");
      ZipOutputStream monthAvg = new ZipOutputStream(new FileOutputStream(e40Path+varName+File.separator+varName+"monthly.zip"));
      for (int month=0; month<12; month++) {
        String monthString = String.valueOf(month+1);
        if (monthString.length()==1) monthString="0"+monthString;
        System.out.print(varName+"-"+monthString+": ");
        double[][] total = new double[360][181];
        for (int i=0; i<360; i++) for (int j=0; j<181; j++) total[i][j]=0.0;
        for (int day=1; day<=monthLengths[month]; day++) {
          String dayString = String.valueOf(day);
          System.out.print(dayString+",");
          if (dayString.length()==1) dayString="0"+dayString;
          ZipEntry ze = meanYear.getEntry(monthString+"/"+dayString+"/gpfsavg"+monthString+dayString+varName+".bin");
          InputStream zeStream = meanYear.getInputStream(ze);
          for (int i=0; i<360; i++) {
            for (int j=0; j<181; j++) {
              total[i][j]+=readFloat(zeStream);
            }
          }
        }
        System.out.println("");
        monthAvg.putNextEntry(new ZipEntry(varName+monthString+".bin"));
        
        for (int i=0; i<360; i++) 
          for (int j=0; j<181; j++)  
            writeFloat(monthAvg,(float)(total[i][j]/(monthLengths[month])));
        monthAvg.closeEntry();
      }
      monthAvg.flush();
      monthAvg.close();
      meanYear.close();
    } catch (Exception e) { e.printStackTrace(); }
  }
  
  
  
  public static void main(String[] args)  {
    try {
//      createCompressedLeapYear("2t%year%.zip","2t%year%.zip","gpas%year%%month%%day%%hour%2t.grb","2t","ERA40 Air Temperature",true);
//      createCompressedLeapYear("tcc%year%.zip","tcc%year%.zip","gpas%year%%month%%day%%hour%tcc.grb","tcc","ERA40 Total Cloud Cover",true);
//      createCompressedLeapYear("r%year%.zip","r%year%.zip","gpas%year%%month%%day%%hour%r.grb","r","ERA40 Relative Humidity",true);
//      createCompressedLeapYear("ws%year%.zip","wu%year%.zip","gpas%year%%month%%day%%hour%10u.grb","ws","ERA40 Wind Speed",true);
//      createCompressedLeapYear("ws%year%.zip","wv%year%.zip","gpas%year%%month%%day%%hour%10v.grb","ws","ERA40 Wind Speed",true);
//      createCompressedLeapYear("ishf%year%.zip","ishf%year%.zip","gpfs%year%%month%%day%ishf.grb","ishf","ERA40 Instantaneous Surface Heat Flux",false);
//      createCompressedLeapYear("slhf%year%.zip","slhf%year%.zip","gpfs%year%%month%%day%slhf.grb","slhf","ERA40 Surface Latent Heat Flux",false);
//      createCompressedLeapYear("sshf%year%.zip","sshf%year%.zip","gpfs%year%%month%%day%sshf.grb","sshf","ERA40 Surface Sensible Heat Flux",false);
//      createCompressedLeapYear("str%year%.zip","str%year%.zip","gpfs%year%%month%%day%str.grb","str","ERA40 Surface Thermal Radiation",false);

      // Now compute the averages using CLY for each leap year.
      
//      createAvg("2t%year%.zip","gpas%year%%month%%day%%hour%2t.grb","2t","ERA40 Air Temperature",true);
//      createAvg("tcc%year%.zip","gpas%year%%month%%day%%hour%tcc.grb","tcc","ERA40 Total Cloud Cover",true);
//      createAvg("ishf%year%.zip","gpfs%year%%month%%day%ishf.grb","ishf","ERA40 Instantaneous Surface Heat Flux",false);
//      createAvg("slhf%year%.zip","gpfs%year%%month%%day%slhf.grb","slhf","ERA40 Surface Latent Heat Flux",false);
//      createAvg("sshf%year%.zip","gpfs%year%%month%%day%sshf.grb","sshf","ERA40 Surface Sensible Heat Flux",false);
//      createAvg("str%year%.zip","gpfs%year%%month%%day%str.grb","str","ERA40 Surface Thermal Radiation",false);
//      createAvg("r%year%.zip","gpas%year%%month%%day%%hour%r.grb","r","ERA40 Relative Humidity",true);
//      createWSAvgs();
      
      // Now do monthly averages from the mean year...
      
      //createMonthlyAvg6("2t");
      //createMonthlyAvg6("tcc");
      //createMonthlyAvg("ishf");
      //createMonthlyAvg("slhf");
      //createMonthlyAvg("sshf");
      //createMonthlyAvg("str");
      //createMonthlyAvg6("r");
      //createMonthlyAvg6("ws");
      
            
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
