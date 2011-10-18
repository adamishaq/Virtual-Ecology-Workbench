package VEW.Common.Progress;

public interface Job {

  public void start(ProgressListener progress, CompletionListener completion);
  
  public void cancel();
  
}
