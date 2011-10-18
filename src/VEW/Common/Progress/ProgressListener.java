package VEW.Common.Progress;

public interface ProgressListener {

  public void setMinimum(int minimum);
  
  public void setMaximum(int maximum);
 
  public void setProgress(int progress);
  
  public void setMessage(String message);
  
  public void setNote(String note);
  
  public void setIndeterminate(boolean indeterminate);
  
}
