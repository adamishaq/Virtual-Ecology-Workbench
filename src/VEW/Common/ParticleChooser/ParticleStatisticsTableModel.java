package VEW.Common.ParticleChooser;

import java.awt.Dialog;
import java.io.*;
import java.util.*;
import java.util.zip.GZIPInputStream;

import javax.swing.table.AbstractTableModel;

import VEW.Common.Progress.CompletionListener;
import VEW.Common.Progress.Job;
import VEW.Common.Progress.JobProgressDialog;
import VEW.Common.Progress.ProgressListener;
import VEW.Common.XML.XMLTag;


public class ParticleStatisticsTableModel extends AbstractTableModel {

  public static final int INCREASING = 0;
  public static final int DECREASING = 1;

  private ParticleStatistics particleStatistics;

  private List filters = new LinkedList();

  private int sortVariable;
  private int sortStatistic;
  private int sortDirection;

  private long[] particles;
  private boolean finishedCalculating;

  private long start;
  private long end;
  private long simStart;
  
  private boolean cancelSort = false;
 // private static final Random RND = new Random();

  public ParticleStatisticsTableModel(ParticleStatistics ps) {
    particleStatistics = ps;
  }

  public void setStartTime(long time) { start = time; }
  public void setEndTime(long time) { end = time; }
  public void setSimStartTime(long time) { simStart = time; }

  public void addColumn(int variable, int statistic) {
    particleStatistics.requestStatistic(variable, statistic);
  }

  public void addFilter(Filter filter) {
    addColumn(filter.getVariable(), filter.getStatistic());
    filters.add(filter);
  }

  public void setSort(int variable, int statistic, int direction) {
    addColumn(variable, statistic);
    sortVariable = variable;
    sortStatistic = statistic;
    sortDirection = direction;
  }

  public int getRowCount() {
    return finishedCalculating ? particles.length : 0;
  }

  public int getColumnCount() {
    return particleStatistics.getStatisticCount() + 1;
  }

  public Class getColumnClass(int col) {
    if (col == 0)
      return Long.class;
    else
      return Double.class;
  }

  public String getColumnName(int col) {
    if (col == 0)
      return "Particle ID";
    else
      return particleStatistics.getVariableName(col - 1) + " ("
          + particleStatistics.getStatisticName(col - 1) + ")";
  }

  public Object getValueAt(int row, int col) {
    if (col == 0)
      return new Long(particles[row]);
    else
      return new Double(particleStatistics.getValue(particles[row], col - 1));
  }

  public void loadData(File dataFile, File timeFile, double stepSizeHours, boolean useFloats, Dialog owner, XMLTag fgdf, XMLTag ff)
      throws FileNotFoundException, IOException {
    
    finishedCalculating = false;
  
    DataInputStream in = new DataInputStream(new BufferedInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(dataFile)))));

    DataInputStream time = null;
    if (timeFile!=null) 
  	  time = new DataInputStream(new BufferedInputStream(
    	        new GZIPInputStream(new BufferedInputStream(new FileInputStream(
    	            timeFile)))));

    Job job = particleStatistics.createJob(in, time, start, end, simStart, stepSizeHours, useFloats,fgdf,ff);

    CompletionListener listener = new CompletionListener() {
      public void jobCancelled() { }
      public void jobCompleted(Object data) { }
    };
    
    JobProgressDialog d = new JobProgressDialog(owner, job);
    d.setModal(true);
    d.show(listener);
    
    particles = particleStatistics.getParticles();
    
    for (Iterator i = filters.iterator(); i.hasNext();) {
      Filter filter = (Filter) i.next();
      particles = filter.filter(particles, particleStatistics);
    }
    
    Job sortJob = createSortJob(particles);
    JobProgressDialog ds = new JobProgressDialog(owner, sortJob);
    ds.setModal(true);
    ds.show(listener);
    
    finishedCalculating = true;
    fireTableRowsInserted(0, particles.length - 1);
  }
/*
  private void swap(long[] array, int i, int j) {
    long tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }

  private int partition(long[] array, int begin, int _end) {
    int index = begin + RND.nextInt(_end - begin + 1);
    long pivot = array[index];
    swap(array, index, _end);
    for (int i = index = begin; i < _end; ++i) {
      if (compare(array[i], pivot)) {
        swap(array, index++, i);
      }
    }
    swap(array, index, _end);
    return (index);
  }

  private void qsort(long[] array, int begin, int _end) {
    if (_end > begin) {
      int index = partition(array, begin, _end);
      qsort(array, begin, index - 1);
      qsort(array, index + 1, _end);
    }
  }
  

  private void sort(long[] array) {
    qsort(array, 0, array.length - 1);
  }
*/  
  
  private void slowSort(long[] array, ProgressListener progress, CompletionListener completion) {
    int i,j,biggest;
    long swap;
    progress.setMessage("Sorting results");

    progress.setMaximum(array.length-1);
    progress.setMinimum(0);
    for (i=0; i<array.length-1; i++) {
      progress.setProgress(i);
      if (i%10==0) progress.setNote(String.valueOf(i)+"/"+String.valueOf(array.length-1));
      if (cancelSort) {
        if (completion != null)
          completion.jobCancelled();
        return;
      }         
      biggest = i;
      for (j=i; j<array.length; j++)
        if (compare(array[j],array[biggest])) biggest = j;
      swap = array[biggest];
      array[biggest] = array[i];
      array[i] = swap;
    }
    if (completion != null)
      completion.jobCompleted(this);
  }

  private boolean compare(long p1, long p2) {
    double v1 = particleStatistics.getValue(p1, sortVariable, sortStatistic);
    double v2 = particleStatistics.getValue(p2, sortVariable, sortStatistic);
    switch (sortDirection) {
    case INCREASING:
      return v1 <= v2;
    case DECREASING:
      return v1 >= v2;
    default:
      return false;
    }
  }

  public long[] getParticles() {
    return particles;
  }

  public void setParticles(long[] _particles) {
    particles = _particles;
  }
  
  public Job createSortJob(long[] theParticles) {
    return new SortJob(theParticles);
  }
  
  private class SortJob implements Job {

    private long[] theParticles;
    
    public SortJob(long[] p) {
      theParticles = p;
    }

    public void start(ProgressListener progress, CompletionListener completion) {
      cancelSort = false;
      slowSort(theParticles,progress,completion);
    }

    public void cancel() {
      cancelSort = true;
    }
  }
}