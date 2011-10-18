package VEW.Common.Progress;

public class JobRunner extends Thread {

  private Job job;
  private ProgressListener progressListener;
  private CompletionListener completionListener;
  
  public JobRunner(Job _job, ProgressListener _progressListener, CompletionListener _completionListener) {
    super();
    job = _job;
    progressListener = _progressListener;
    completionListener = _completionListener;
  }

  public void run() {
    job.start(progressListener, completionListener);
  }
  
  public void cancel() {
    job.cancel();
  }
  
}
