package VEW.Common.Progress;

public interface CompletionListener {
  
  public void jobCompleted(Object data);
  public void jobCancelled();

}
