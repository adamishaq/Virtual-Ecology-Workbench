package VEW.Common.Progress;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class JobProgressBar extends JProgressBar {

  private Job job;
  private JobProgressListener progressListener = new JobProgressListener();
  
  public JobProgressBar(Job _job) {
    super();
    job = _job;
  }
  
  public JobProgressBar() {
    this(null);
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job _job) {
    job = _job;
  }
  
  public ProgressListener getProgressListener() {
    return progressListener;
  }
  
  public void start(CompletionListener completionListener) {
    new JobRunner(job, progressListener, completionListener).start();
  }
  
  public void cancel() {
    job.cancel();
  }
  
  private class JobProgressListener implements ProgressListener {

    public void setMinimum(int minimum) {
      SwingUtilities.invokeLater(new SetMinimum(minimum));
    }

    public void setMaximum(int maximum) {
      SwingUtilities.invokeLater(new SetMaximum(maximum));
    }

    public void setProgress(int progress) {
      SwingUtilities.invokeLater(new SetProgress(progress));
    }

    public void setMessage(String message) {
      // Do nothing with this
    }

    public void setNote(String note) {
      SwingUtilities.invokeLater(new SetNote(note));
    }

    public void setIndeterminate(boolean indeterminate) {
      SwingUtilities.invokeLater(new SetIndeterminate(indeterminate));
    }
    
    private class SetMinimum implements Runnable {
      
      private int minimum;
      
      public SetMinimum(int _minimum) {
        minimum = _minimum;
      }
      
      public void run() {
        JobProgressBar.this.setMinimum(minimum);
      }
    }

    private class SetMaximum implements Runnable {
      
      private int maximum;
      
      public SetMaximum(int _maximum) {
        maximum = _maximum;
      }
      
      public void run() {
        JobProgressBar.this.setMaximum(maximum);
      }
    }
    
    private class SetProgress implements Runnable {
      private int progress;
      
      public SetProgress(int _progress) {
        progress = _progress;
      }
      
      public void run() {
        JobProgressBar.this.setValue(progress);
      }
    }
  
    private class SetNote implements Runnable {
      private String note;
      
      public SetNote(String _note) {
        note = _note;
      }
      
      public void run() {
        JobProgressBar.this.setString(note);
      }
    }
    
    private class SetIndeterminate implements Runnable {
      private boolean indeterminate;
      
      public SetIndeterminate(boolean _indeterminate) {
        indeterminate = _indeterminate;
      }
      
      public void run() {
        JobProgressBar.this.setIndeterminate(indeterminate);
      }
    }
  }
  
}
