package VEW.Analyser4;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.zip.GZIPInputStream;

import VEW.Common.StringTools;
import VEW.Common.XML.XMLTag;

public class FileSet {
  int currentFile;
  long currentFileLength;  
  long currentFilePos;
  boolean currentFileFloat;
  DataInputStream dis = null;
  String dataPath;
  XMLTag record;
  XMLTag fileTag;
  boolean oldFile;
  
  public boolean getZip(XMLTag _fileTag) {
    if (oldFile) return StringTools.parseBoolean(_fileTag.getValue("zip"));
    else return StringTools.parseBoolean(_fileTag.getAttribute("zip"));
  }
  
  public boolean getFloat(XMLTag _fileTag) {
    if (oldFile) return StringTools.parseBoolean(_fileTag.getValue("float"));
    else return StringTools.parseBoolean(_fileTag.getAttribute("float"));
  }
  
  public long getStartTime(XMLTag _fileTag) {
    if (oldFile) {
      if (_fileTag.getName().equals("field"))
        return Long.parseLong(_fileTag.getTag("dimensions").getTags("dim")[0].getAttribute("start"));
      else return Long.parseLong(_fileTag.getTag("output").getValue("after"));
    }        
    else return Long.parseLong(_fileTag.getAttribute("sts"));
  }

  public long getEndTime(XMLTag _fileTag) {
    if (oldFile) {
      if (_fileTag.getName().equals("field"))
        return Long.parseLong(_fileTag.getTag("dimensions").getTags("dim")[0].getAttribute("until"));
      else return Long.parseLong(_fileTag.getTag("output").getValue("until"));
    }        
    else return Long.parseLong(_fileTag.getAttribute("ets"));
  }
  
  public String getTimeFile(XMLTag _fileTag) {
    if (oldFile) {
      if (_fileTag.getValue("time")!=null) return _fileTag.getValue("time");
      else return "";
    } else return _fileTag.getAttribute("time");
  }
      
  public FileSet(XMLTag formatRecord, String path) {
    record = formatRecord;
    dataPath = new String(path);
  }
  
  public void prepareForRead() {
    currentFile = 0;
    currentFilePos = 0;
    openFile(currentFile);
  }
    
  public static int valsPerTimeStep(XMLTag rec) {
    String recName = rec.getName();
    if (recName.equals("functionalgroup")) {
      return rec.getTags("var").length;
    } else if (recName.equals("environment")) {
      return rec.getTags("var").length;
    } else if (recName.equals("field")) {
      return 1+(Integer.parseInt(rec.getTag("dim",2).getAttribute("end"))-Integer.parseInt(rec.getTag("dim",2).getAttribute("start")));
    } else return 0;
  }
    
  public void ReallySkip(long Bytes) {
    long BytesToGo = Bytes;
    try {
      while (BytesToGo > Integer.MAX_VALUE)
        BytesToGo -= dis.skipBytes(Integer.MAX_VALUE);
      if (BytesToGo > 0) dis.skipBytes((int) BytesToGo);
    } catch (Exception e) {e.printStackTrace(); }
  }
    
  public void openFileAtTimeStep(int ts) {
    int i=0;
    int fileCount;
    oldFile = false;
    if (record.getTag("fileset")==null) {
      fileCount = 1;
      oldFile = true;
    }
    else fileCount = record.getTag("fileset").getTags("file").length;
    while (i<fileCount) {
      XMLTag file;
      if (oldFile) file = record.getTag("file");
      else file = record.getTags("fileset")[i];
      if ((ts>=getStartTime(file)) && (ts<=getEndTime(file))) {
        openFile(i);
        i=fileCount;
      }
      i++;
    }
    // Now skip timesteps.
    int valsPerStep = valsPerTimeStep(record);
    if (getFloat(fileTag)) valsPerStep*=4;
    else valsPerStep*=8;
    long startTs = getStartTime(fileTag);
    if (valsPerStep>0)
      for (int j=0; j<ts-startTs; j++)
        ReallySkip(valsPerStep);
    else { // It's a functional group
      String timeFile = getTimeFile(fileTag);
      if ((timeFile!=null) && (timeFile.length()>0)) { // Use timefile
        try {
          DataInputStream disTime = null;
          if (getZip(fileTag)) disTime = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(dataPath + File.separator + timeFile))));
          else disTime = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath + File.separator + timeFile)));
          long t = getStartTime(fileTag);
          long filePos = 0;
          while (t<ts) {
            filePos = disTime.readLong();
            t++;
          }
          disTime.close();
          ReallySkip(filePos);
        } catch (Exception e) { e.printStackTrace(); }
      } else {
        throw new Error("No time files present");
      }
    }
  }
  
  public void openFile(int i) {
    try {
      if (record.getTag("fileset")==null) {
        oldFile=true;
        fileTag = record.getTag("file");
      } else {
        fileTag = record.getTag("fileset").getTags("files")[i];
        oldFile=false;
      }
      currentFileFloat = getFloat(fileTag);
      if (getZip(fileTag)) dis = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new FileInputStream(dataPath + File.separator + fileTag.getValue()))));
      else dis = new DataInputStream(new BufferedInputStream(new FileInputStream(dataPath + File.separator + fileTag.getValue())));
      currentFile = i;
      currentFilePos = 0;
      currentFileLength = Long.parseLong(fileTag.getAttribute("bytes"));
    } catch (Exception e) { e.printStackTrace(); dis = null; }
  }
  
  public double readValue() {
    double d = 0;
    try {
      if (currentFileFloat) {
        d = dis.readFloat();
        currentFilePos+=4;
      } else {
        d = dis.readDouble();
        currentFilePos+=8;
      }
    } catch (Exception e) { e.printStackTrace(); }
    if (currentFilePos>=currentFileLength) {
      try {
        dis.close();
      } catch (Exception e) { e.printStackTrace(); }
      openFile(currentFile+1);
    }
    return d;
  }
}
