package VEW.Common.Progress;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class JobProgressDialog extends JDialog {

  private Job job;
  private JobRunner runner;
  private JobProgressListener progressListener = new JobProgressListener();
  private JLabel messageLabel = new JLabel("Experience is the name that everyone gives to their mistakes."); // Oscar Wilde
  private JLabel noteLabel = new JLabel("Being idle is very hard work because everyone is against you."); // Also Oscar Wilde
  private JProgressBar progressBar = new JProgressBar();
  private JButton cancelButton = new JButton("Cancel");
  
  public JobProgressDialog(JFrame parent, Job _job) {
    super(parent);
    job = _job;
    createComponents();
  }

  public JobProgressDialog(Dialog parent, Job _job) {
    super(parent);
    job = _job;
    createComponents();
  }
  
  public JobProgressDialog(Job _job) {
    super();
    job = _job;
    createComponents();
  }
  
  private void createComponents() {
    setTitle("Progress");
    
    JPanel panel = new JPanel(new GridBagLayout());
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = GridBagConstraints.RELATIVE;
    c.weightx = 1;
    c.weighty = 1;
    
    panel.add(messageLabel, c);
    c.insets.top = 10;
    panel.add(noteLabel, c);
    panel.add(progressBar, c);
    progressBar.setStringPainted(true);
    
    cancelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        runner.cancel();
        setVisible(false);
      }
    });
    c.fill = GridBagConstraints.NONE;
    panel.add(cancelButton, c);
    
    getContentPane().add(panel);
    pack();
    
    messageLabel.setText("");
    noteLabel.setText("");
    
    int width = getWidth();
    int height = getHeight();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screenSize.width / 2) - (width / 2), (screenSize.height / 2) - (height / 2));
  }
  
  public void show(CompletionListener completionListener) {
    runner = new JobRunner(job, progressListener, new JobCompletionListener(completionListener));
    runner.start();
    super.show();
  }
  
  public void show() {
    show(null);
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
      SwingUtilities.invokeLater(new SetMessage(message));
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
        progressBar.setMinimum(minimum);
      }
    }

    private class SetMaximum implements Runnable {
      
      private int maximum;
      
      public SetMaximum(int _maximum) {
        maximum = _maximum;
      }
      
      public void run() {
        progressBar.setMaximum(maximum);
      }
    }
    
    private class SetProgress implements Runnable {
      private int progress;
      
      public SetProgress(int _progress) {
        progress = _progress;
      }
      
      public void run() {
        progressBar.setValue(progress);
        progressBar.setString(String.valueOf((int)Math.floor((100.0f*progress)/(1.0f*progressBar.getMaximum()))+" %"));
        
      }
    }
    
    private class SetMessage implements Runnable {
      private String message;
      
      public SetMessage(String _message) {
        message = _message;
      }
      
      public void run() {
        messageLabel.setText(message);
      }
    }
  
    private class SetNote implements Runnable {
      private String note;
      
      public SetNote(String _note) {
        note = _note;
      }
      
      public void run() {
        noteLabel.setText(note);
      }
    }
    
    private class SetIndeterminate implements Runnable {
      private boolean indeterminate;
      
      public SetIndeterminate(boolean _indeterminate) {
        indeterminate = _indeterminate;
      }
      
      public void run() {
        progressBar.setIndeterminate(indeterminate);
      }
    }
  }

  private class JobCompletionListener implements CompletionListener {
    
    private CompletionListener delegate;
    
    public JobCompletionListener(CompletionListener _delegate) {
      delegate = _delegate;
    }
    
    public void jobCompleted(Object data) {
      SwingUtilities.invokeLater(new JobCompleted(data));
    }
    
    public void jobCancelled() {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          setVisible(false);
          if (delegate != null)
            delegate.jobCancelled();
        }
      });
    }
    
    private class JobCompleted implements Runnable {
      
      private Object data;
      
      public JobCompleted(Object _data) {
        data = _data;
      }
      
      public void run() {
        setVisible(false);
        if (delegate != null)
          delegate.jobCompleted(data);
      }
    }
  }
}
