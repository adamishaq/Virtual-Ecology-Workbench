package VEW.Common.ParticleChooser;

import gnu.trove.TLongHashSet;

import java.io.*;
import java.util.zip.GZIPInputStream;

import VEW.Common.Progress.CompletionListener;
import VEW.Common.Progress.Job;
import VEW.Common.Progress.ProgressListener;
import VEW.Common.XML.XMLFile;
import VEW.Common.XML.XMLTag;

public class LineageCalculator {

  private static final String DATA_FORMATS_FILENAME = "DataFormats.xml";
 // private static final String MODEL_FILENAME = "Model.xml";

  public static final byte CREATE = 0;
  public static final byte PCHANGEPARENT = 1;
  public static final byte PCHANGECHILD = 2;
  public static final byte SPLIT = 3;
  public static final byte MERGE = 4;

  private File directory;
  private XMLFile dataFormats;
  public boolean cancelled;

  public LineageCalculator(File _directory) {
    directory = _directory;

    File dataFormatsFile = new File(directory, DATA_FORMATS_FILENAME);
    //File modelFile = new File(directory, MODEL_FILENAME);

    dataFormats = XMLFile.LoadFile(dataFormatsFile);
  }

  public LineageCalculator(String _directory) {
    this(new File(_directory));
  }

  public TLongHashSet getDescendants(TLongHashSet particles, String functionalGroup,
      int endTimeStep, ProgressListener progressListener, CompletionListener completionListener) {
    XMLTag fgTag = dataFormats.getTagWhere("functionalgroup", "name",
        functionalGroup);
    
    TLongHashSet allParticles = new TLongHashSet();
    allParticles.addAll(particles.toArray());
    System.out.println(allParticles.size());
    
    boolean zipped = fgTag.getValue("zip").equals("true");
    // boolean useFloats = fgTag.getValue("format").equals("float");
    String lineageFileName = fgTag.getValue("lineage");
    File lineageFile = new File(directory, lineageFileName);
    DataInputStream in;

    int timestep = 0, lastTimestep = -1;
    byte event;
    long id1, id2;
    
    if (progressListener != null) {
      progressListener.setMessage("Finding descendants");
      progressListener.setMinimum(0);
      progressListener.setMaximum(endTimeStep);
    }

    try {
      if (zipped)
        in = new DataInputStream(new BufferedInputStream(new GZIPInputStream(
            new BufferedInputStream(new FileInputStream(lineageFile)))));
      else
        in = new DataInputStream(new BufferedInputStream(new FileInputStream(
            lineageFile)));

      timestep = in.readInt();
      
      while (timestep < endTimeStep) {
        synchronized (this) {
          if (cancelled) {
            if (completionListener != null)
              completionListener.jobCancelled();
            return null;
          }
        }
        
        event = in.readByte();
        id1 = in.readLong();
        id2 = in.readLong();

        switch (event) {
        case CREATE:
        case PCHANGECHILD:
        case SPLIT:
          if (allParticles.contains(id1)) {
            allParticles.add(id2);
          }
          break;

        case MERGE:
          if (allParticles.contains(id1)) {
            allParticles.add(id2);
          } else if (allParticles.contains(id2)) {
            allParticles.add(id1);
          }
          break;
        }

        timestep = in.readInt();
        
        if (progressListener != null && timestep != lastTimestep) {
          lastTimestep = timestep;
          progressListener.setProgress(timestep);
        }
      }
      
      allParticles.removeAll(particles.toArray());
      
      if (completionListener != null)
        completionListener.jobCompleted(allParticles);
      
      return allParticles;
    } catch (EOFException e) {
      allParticles.removeAll(particles.toArray());
      
      if (completionListener != null)
        completionListener.jobCompleted(allParticles);
      
      return allParticles;
    } catch (IOException e) {
      e.printStackTrace();
      if (completionListener != null)
        completionListener.jobCancelled();
    }
    
    return null;
  }
  
  public Job createJob(TLongHashSet particles, String functionalGroup, int endTimeStep) {
    return new LineageJob(particles, functionalGroup, endTimeStep);
  }
  
  private class LineageJob implements Job {

    private TLongHashSet particles;
    private String functionalGroup;
    private int endTimeStep;

    public LineageJob(TLongHashSet _particles, String _functionalGroup, int _endTimeStep) {
      particles = _particles;
      functionalGroup = _functionalGroup;
      endTimeStep = _endTimeStep;
    }

    public void start(ProgressListener progress, CompletionListener completion) {
      cancelled = false;
      getDescendants(particles, functionalGroup, endTimeStep, progress, completion);
    }

    public void cancel() {
      synchronized (LineageCalculator.this) {
        cancelled = true;
      }
    }
    
  }
}
