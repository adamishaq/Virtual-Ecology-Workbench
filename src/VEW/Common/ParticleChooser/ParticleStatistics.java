package VEW.Common.ParticleChooser;

import gnu.trove.TIntArrayList;
import gnu.trove.TLongHashingStrategy;
import gnu.trove.TLongIntHashMap;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;

import VEW.Common.SpeedyArrayList;
import VEW.Common.Progress.CompletionListener;
import VEW.Common.Progress.Job;
import VEW.Common.Progress.ProgressListener;
import VEW.Common.XML.XMLTag;

public class ParticleStatistics {

  public static final String[] DESCRIPTIONS = { "First value", "Last value",
      "Minimum", "Maximum", "Mean" };

  public static final int FIRST = 0;
  public static final int LAST = 1;
  public static final int MIN = 2;
  public static final int MAX = 3;
  public static final int MEAN = 4;

  public static final int N_STATISTICS = 5;

  private TIntArrayList variables;
  private TIntArrayList statistics;

  private List particleData;
  private boolean[][] requiredStatistics;
  private List firstSet;
  public List nItems;
  private TLongIntHashMap particleIndexMap;
  private TIntArrayList requiredVariables;
  private int nVariables;

  private List variableNames;

  public boolean cancel;

  public ParticleStatistics(List _variableNames) {
    this.nVariables = _variableNames.size();
    variableNames = _variableNames;
    requiredVariables = new TIntArrayList(nVariables);
    requiredStatistics = new boolean[nVariables][N_STATISTICS];

    variables = new TIntArrayList();
    statistics = new TIntArrayList();
  }

  public void requestStatistic(int variable, int statistic) {
    if (!requiredStatistics[variable][statistic]) {
      if (!requiredVariables.contains(variable))
        requiredVariables.add(variable);
      requiredStatistics[variable][statistic] = true;

      variables.add(variable);
      statistics.add(statistic);
    }
  }

  public double getValue(long particle, int variable, int statistic) {
    int index = particleIndexMap.get(particle);
    if (statistic == MEAN) {
      return ((double[][]) particleData.get(index))[variable][statistic]
          / ((int[]) nItems.get(index))[variable];
    } else {
      return ((double[][]) particleData.get(index))[variable][statistic];
    }
  }

  public double getValue(long particle, int index) {
    return getValue(particle, variables.get(index), statistics.get(index));
  }

  public String getVariableName(int index) {
    return (String) variableNames.get(variables.get(index));
  }

  public String getStatisticName(int index) {
    return DESCRIPTIONS[statistics.get(index)];
  }

  public int getStatisticCount() {
    return variables.size();
  }

  public void calculateStatistics(DataInputStream in, DataInputStream time, long start, long end, long startSim,
      double stepSize, boolean useFloats, ProgressListener progress, CompletionListener completion, XMLTag fgDF, XMLTag formatFile) {
    
    long currentTime = 0;
    long lastTimeStep = 0;
    long timeStepMillis = (long) (stepSize*3600*1000);
    long currentParticle;
    int currentVariable;
    double[][] currentData;
    boolean[] currentFirstSet;
    int[] currentNItems;
    double value = 0;
    int particleIndex;
    int nRequiredVariables = requiredVariables.size();

    if (progress != null) {
      progress.setMessage("Calculating statistics");
    }
    progress.setMaximum(1000);
    particleIndexMap = new TLongIntHashMap(new MyHashFunction());
    particleData = new SpeedyArrayList();
    firstSet = new SpeedyArrayList();
    nItems = new SpeedyArrayList();

    requiredVariables.sort();

    boolean oldVersion = false;
    if (fgDF.getTags("var")[0].getTag("type")==null) oldVersion=true;
    
    int recSize=0;
    int noVars=fgDF.getTags("var").length;
    byte[] types = new byte[noVars];    
    for (int i=0; i<fgDF.getTags("var").length; i++) {
      if (oldVersion) {
        types[i]=0;
        recSize+=(useFloats?4:8);
      } else if (fgDF.getTags("var")[i].getValue("type").equals("real")) {
        types[i]=0;
        recSize+=(useFloats?4:8);
      } else if (fgDF.getTags("var")[i].getValue("type").equals("int")) {
        types[i]=1;
        recSize+=4;
      } else if (fgDF.getTags("var")[i].getValue("type").equals("long")) {
        types[i]=2;
        recSize+=8;
      }
    }
    double[] doubleBuffer = new double[noVars];
    int[] intBuffer = new int[noVars];
    long[] longBuffer = new long[noVars];
    time=null; // DELETE ME WHEN FIXED
    try {
      if ((start>0) && (time!=null)) { // Do some clever skipping
        progress.setNote("Looking up quick-index...");
        int index = (int) (start/timeStepMillis);
        reallySkip((index-1)*8,time);
        long timeToReach = time.readLong();
        long total = timeToReach;
        System.out.println("Skip to "+total);
        progress.setNote("Skipping...");
        long skipped = 0;
        while (timeToReach>0) {
          if (cancel) {
            if (completion != null)
              completion.jobCancelled();
            return;
          }          
          long skipAmount = 10485760;
          if (skipAmount>timeToReach) skipAmount = timeToReach;
          skipped+=skipAmount;
          timeToReach-=skipAmount;
          reallySkip(skipAmount,in);
          progress.setProgress((int)(1000*(skipped/(total*1.0f))));
        }
        
      }
    } catch (Exception e) { e.printStackTrace(); }
        
    try {
      if (oldVersion) currentTime = (int) ((useFloats ? in.readFloat() : in.readDouble())*2);
      else currentTime =  in.readLong();
      String version = formatFile.getValue("version");
      final boolean version1_1 = ((version!=null) && version.equals("1.1"));
      if (!version1_1) currentTime = startSim+(currentTime*timeStepMillis);
      
      if ((progress!=null) && (currentTime < start))
        progress.setNote("Slow skipping - use time-indexing to speed up");
      
      long rememberTime = 0;
      while (currentTime < start) {
        if (oldVersion) reallySkip(recSize-(useFloats?4:8), in);
        else reallySkip(recSize-8,in);
        if (oldVersion) currentTime = (int) ((useFloats ? in.readFloat() : in.readDouble())*2);
        else currentTime = in.readLong();
        if (!version1_1) currentTime = startSim+(currentTime*timeStepMillis);
            
        if (currentTime>rememberTime) {
          progress.setProgress((int)(1000*((currentTime-startSim)/((start*1.0f)-startSim))));
          rememberTime = currentTime;
        }
      }

      lastTimeStep = currentTime;
      
      if (progress != null) progress.setNote("Reading data...");
      
      
      while (currentTime <= end) {
        if (cancel) {
          if (completion != null)
            completion.jobCancelled();
          return;
        }
        
        if (oldVersion) currentParticle = (long) (useFloats ? in.readFloat() : in.readDouble());
        else currentParticle = in.readLong();
        if (particleIndexMap.containsKey(currentParticle)) {
          particleIndex = particleIndexMap.get(currentParticle);
          currentData = (double[][]) particleData.get(particleIndex);
          currentFirstSet = (boolean[]) firstSet.get(particleIndex);
          currentNItems = (int[]) nItems.get(particleIndex);
        } else {
          particleIndex = particleData.size();
          particleIndexMap.put(currentParticle, particleIndex);

          currentData = new double[nVariables][N_STATISTICS];
          for (int i = 0; i < nVariables; i++) {
            currentData[i][MIN] = Double.POSITIVE_INFINITY;
            currentData[i][MAX] = Double.NEGATIVE_INFINITY;
          }
          particleData.add(currentData);

          currentFirstSet = new boolean[nVariables];
          firstSet.add(currentFirstSet);

          currentNItems = new int[nVariables];
          nItems.add(currentNItems);
        }

        for (int i = 2; i < noVars; i++) { // Already done timestep/id
          if (oldVersion) {
            doubleBuffer[i]=(useFloats ? in.readFloat() : in.readDouble());
          } else {
            if (types[i]==0) doubleBuffer[i]=(useFloats ? in.readFloat() : in.readDouble());
            else if (types[i]==1) intBuffer[i]=in.readInt();
            else if (types[i]==2) longBuffer[i]=in.readLong();
          }
        }
        for (int i=0; i< nRequiredVariables; i++) {
          currentVariable = requiredVariables.get(i);
          if (oldVersion) {
            value = doubleBuffer[currentVariable+2];
          } else {
            if (types[currentVariable+2]==0) value = doubleBuffer[currentVariable+2];
            else if (types[currentVariable+2]==1) value = intBuffer[currentVariable+2];
            else if (types[currentVariable+2]==2) value = longBuffer[currentVariable+2];
          }
          updateStatistics(currentParticle, currentVariable, value, currentData, currentFirstSet, currentNItems);
        }

        if (oldVersion) currentTime = (int) ((useFloats ? in.readFloat() : in.readDouble())*2);
        else currentTime = in.readLong();
        if (!version1_1) currentTime = startSim+(currentTime*timeStepMillis);
                

        if (currentTime != lastTimeStep && progress != null) {
          progress.setProgress((int)(1000*((currentTime-startSim)/((1.0f*end)-startSim))));
          lastTimeStep = currentTime;
        }
      }
    } catch (EOFException e) {
//      System.out.println("No more left");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    if (completion != null)
      completion.jobCompleted(this);
  }
  
  public Job createJob(DataInputStream in, DataInputStream time, long start, long end, long startSim, double stepSize, boolean useFloats, XMLTag fgdf, XMLTag ff) {
    return new ParticleStatisticsJob(in, time, start, end, startSim, stepSize, useFloats,fgdf, ff);
  }

  private static void reallySkip(long bytes, DataInputStream stream)
      throws Exception {
    long bytesToGo = bytes;
    while (bytesToGo > Integer.MAX_VALUE)
      bytesToGo -= stream.skipBytes(Integer.MAX_VALUE);
    if (bytesToGo > 0) {
      stream.skipBytes((int) bytesToGo);
    }
  }

  public long[] getParticles() {
    return particleIndexMap.keys();
  }

  private void updateStatistics(long particle, int variable, double newValue,
      double[][] data, boolean[] _firstSet, int[] _nItems) {
    final double[] currentData = data[variable];
    if (requiredStatistics[variable][FIRST] && !_firstSet[variable]) {
      currentData[FIRST] = newValue;
      _firstSet[variable] = true;
    }

    if (requiredStatistics[variable][LAST])
      currentData[LAST] = newValue;

    if (requiredStatistics[variable][MIN] && (newValue < currentData[MIN]))
      currentData[MIN] = newValue;

    if (requiredStatistics[variable][MAX] && (newValue > currentData[MAX]))
      currentData[MAX] = newValue;

    if (requiredStatistics[variable][MEAN]) {
      currentData[MEAN] += newValue;
      _nItems[variable]++;
    }
  }
/*
  private int[] calculateSkips(TIntArrayList list, boolean useFloats) {
    int size = useFloats ? 4 : 8;
    if (list.isEmpty())
      return new int[] { size * nVariables };

    int n = list.size();
    int[] skips = new int[n + 1];

    list.sort();

    // Skip for first variable (from id field)
    skips[0] = size * list.get(0);

    // Skips for remaining variables (from previous variable)
    for (int i = 1; i < n; i++)
      skips[i] = size * (list.get(i) - list.get(i - 1)) - size;

    // Skip to start of next row
    skips[n] = size * (nVariables - list.get(n - 1)) - size;

    return skips;
  }
*/
  private class ParticleStatisticsJob implements Job {

    private DataInputStream in;
    private DataInputStream time;
    private long  startMillis;
    private long  endMillis;
    private long  startSimMillis;
    private boolean useFloats;
    private double stepSize;
    private XMLTag fgDF;
    private XMLTag formatFile;    
    
    public ParticleStatisticsJob(DataInputStream _in, DataInputStream _time, long  _start, long _end, long _startSim, double _stepSize, boolean floats, XMLTag theFgDFTag, XMLTag theFormatFile) {
      in = _in;
      startMillis = _start;
      endMillis = _end;
      startSimMillis = _startSim;
      time = _time;
      stepSize = _stepSize;
      useFloats = floats;
      fgDF = theFgDFTag;
      formatFile = theFormatFile;
    }

    public void start(ProgressListener progress, CompletionListener completion) {
      cancel = false;
      calculateStatistics(in, time, startMillis, endMillis, startSimMillis, stepSize, useFloats, progress, completion, fgDF, formatFile);
    }

    public void cancel() {
      cancel = true;
    }
    
  }
  
  private class MyHashFunction implements TLongHashingStrategy {

    public int computeHashCode(long value) {
      return (int) (value ^ (value >> 32));
    }

  }

}
