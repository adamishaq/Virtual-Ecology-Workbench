package VEW.Lifespan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import gnu.trove.TIntArrayList;
import gnu.trove.TIntIntHashMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StageEditor extends JDialog {
  
  private static final int LIVE = 0;
  private static final int DEAD = 1;
  private static final int OTHERS = 2;
  
  private StageModel miscStages;
  private StageModel liveStages;
  private StageModel deadStages;
  
  private JList miscStageList;
  private JList liveStageList;
  private JList deadStageList;
  
  private JLabel statusLabel;
  
  private boolean accepted = false;
  private boolean[][] transitions;
  private List allStages;
  
  public StageEditor(Dialog owner, List stages, boolean[][] _transitions, int[] live, int[] dead) {
    super(owner);
    miscStages = new StageModel(stages);
    liveStages = new StageModel(stages);
    deadStages = new StageModel(stages);
    
    this.allStages = stages;
    transitions = _transitions;
    
    setSelected(live, dead);
    createComponents();
    checkStages();
  }

  private void setSelected(int[] live, int[] dead) {
    miscStages.addAll();
    
    if (live != null)
      for (int i = 0; i < live.length; i++) {
        miscStages.removeElement(live[i]);
        liveStages.addElement(live[i]);
      }
    
    if (dead != null)
      for (int i = 0; i < dead.length; i++) {
        miscStages.removeElement(dead[i]);
        deadStages.addElement(dead[i]);
      }
  }

  private void createComponents() {
    JPanel panel = new JPanel(new BorderLayout(5, 5));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    panel.add(createMainPanel(), BorderLayout.CENTER);
    panel.add(createButtonPanel(), BorderLayout.SOUTH);
    
    setContentPane(panel);
    pack();
    
    Dimension size = getSize();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
  }

  private Component createMainPanel() {
    JPanel panel = new JPanel(new GridBagLayout());
    
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.BOTH;
    
    miscStageList = new JList(miscStages);
    miscStageList.setPrototypeCellValue("This is a fairly long string");
    c.gridx = 0;
    c.gridy = 0;
    c.gridheight = 4;
    c.weightx = 1;
    c.weighty = 1;
    panel.add(new JScrollPane(miscStageList), c);
    
    liveStageList = new JList(liveStages);
    liveStageList.setPrototypeCellValue("This is a fairly long string");
    c.gridx = 2;
    c.gridy = 0;
    c.gridheight = 2;
    c.insets.bottom = 2;
    panel.add(new JScrollPane(liveStageList), c);
    
    deadStageList = new JList(deadStages);
    deadStageList.setPrototypeCellValue("This is a fairly long string");
    c.gridx = 2;
    c.gridy = 2;
    c.gridheight = 2;
    c.insets.bottom = 0;
    c.insets.top = 2;
    panel.add(new JScrollPane(deadStageList), c);
    
    c.gridx = 1;
    c.gridy = 0;
    c.gridheight = 1;
    c.weightx = 0;
    c.weighty = 1;
    c.insets.top = 0;
    c.fill = GridBagConstraints.HORIZONTAL;
    panel.add(createMoveButton(miscStageList, miscStages, liveStages), c);
    
    c.gridy = 1;
    panel.add(createMoveButton(liveStageList, liveStages, miscStages), c);
    
    c.gridy = 2;
    panel.add(createMoveButton(miscStageList, miscStages, deadStages), c);
    
    c.gridy = 3;
    panel.add(createMoveButton(deadStageList, deadStages, miscStages), c);
    
    statusLabel = new JLabel();
    c.gridx = 0;
    c.gridy = 4;
    c.gridwidth = 3;
    panel.add(statusLabel, c);
    
    return panel;
  }
  
  private JButton createMoveButton(JList list1, StageModel model1, StageModel model2) {
    JButton button;
    String type;
    if (model1 == liveStages || model2 == liveStages)
      type = "live";
    else
      type = "dead";
    if (list1 == miscStageList)
      button = new JButton("Add " + type + " stage ->");
    else
      button = new JButton("<- Remove " + type + " stage");
    
    button.addActionListener(new MoveStage(list1, model1, model2));
    button.setEnabled(!list1.isSelectionEmpty());
    
    list1.addListSelectionListener(new ButtonSelectionListener(button, list1));
    return button;
  }

  private Component createButtonPanel() {
    Box buttonPanel = new Box(BoxLayout.X_AXIS);
    
    buttonPanel.add(Box.createHorizontalGlue());
    buttonPanel.add(new JButton(cancelAction));
    buttonPanel.add(new JButton(okAction));
    
    return buttonPanel;
  }
  
  private void checkStages() {
    if (liveStages.isEmpty())
      setStatusError("No live stages selected");
    else if (deadStages.isEmpty())
      setStatusError("No dead stages selected");
    else {
      int[] live = getLiveStages();
      int[] dead = getDeadStages();
      TIntIntHashMap stageMap = new TIntIntHashMap(transitions.length);
      
      // Combine live stages
      for (int i = 0; i < live.length; i++)
        stageMap.put(live[i], LIVE);
      
      // Combine dead stages
      for (int i = 0; i < dead.length; i++)
        stageMap.put(dead[i], DEAD);
      
      int n = OTHERS;
      for (int i = 0; i < allStages.size(); i++)
        if (!stageMap.contains(i))
          stageMap.put(i, n++);
      
      // Compute combined transition matrix
      boolean[][] newTransitions = new boolean[n][n];
      for (int i = 0; i < transitions.length; i++)
        for (int j = 0; j < transitions.length; j++)
          if (transitions[i][j])
            newTransitions[stageMap.get(i)][stageMap.get(j)] = true;
      
      // Compute transitive closure
      for (int i = 0; i < n; i++)
        for (int s = 0; s < n; s++)
          for (int t = 0; t < n; t++)
            if (newTransitions[s][i] && newTransitions[i][t])
              newTransitions[s][t] = true;
      
      if (newTransitions[DEAD][LIVE])
        setStatusError("Resurrection possible!");
      else
        setStatusOK();
    }
  }
  
  private void setStatusError(String reason) {
    statusLabel.setForeground(Color.RED);
    statusLabel.setText("Error in stage configuration - " + reason);
    okAction.setEnabled(false);
  }
  
  private void setStatusOK() {
    statusLabel.setForeground(Color.BLACK);
    statusLabel.setText("Stage configuration ok");
    okAction.setEnabled(true);
  }
  
  public boolean isAccepted() {
    return accepted;
  }
  
  public int[] getLiveStages() {
    return liveStages.getStages();
  }
  
  public int[] getDeadStages() {
    return deadStages.getStages();
  }

  private Action okAction = new AbstractAction("Ok") {
    public void actionPerformed(ActionEvent e) {
      accepted = true;
      setVisible(false);
    }
  };
  
  private Action cancelAction = new AbstractAction("Cancel") {
    public void actionPerformed(ActionEvent e) {
      accepted = false;
      setVisible(false);
    }
  };
  
  private class MoveStage implements ActionListener {

    JList list1;
    StageModel model1, model2;
    
    public MoveStage(JList _list1, StageModel _model1, StageModel _model2) {
      list1 = _list1;
      model1 = _model1;
      model2 = _model2;
    }
    
    public void actionPerformed(ActionEvent e) {
      int[] selection = list1.getSelectedIndices();
      for (int i=0; i<selection.length; i++) {
        String stage = ((String)model1.getElementAt(selection[i]));
        model2.addElement(stage);
      }
      for (int i=selection.length-1; i>=0; i--) 
        model1.removeElementAt(selection[i]);
      
      //int index = list1.getSelectedIndex();
      //int stage = model1.removeElementAt(index);
      //model2.addElement(stage);
      
      checkStages();
    }
    
  }
  
  private class ButtonSelectionListener implements ListSelectionListener {

    private JButton button;
    private JList list;
    
    public ButtonSelectionListener(JButton _button, JList _list) {
      button = _button;
      list = _list;
    }
    
    public void valueChanged(ListSelectionEvent e) {
      button.setEnabled(!list.isSelectionEmpty());
    }
    
  }

  private class StageModel extends AbstractListModel {

    private TIntArrayList contents;
    private List allStagesSM;
    
    public StageModel(List stages) {
      allStagesSM = new ArrayList(stages);
      contents = new TIntArrayList(allStagesSM.size());
    }
    
    public void addAll() {
      contents.clear(allStagesSM.size());
      for (int i = 0; i < allStagesSM.size(); i++)
        contents.add(i);
    }
    
    public void addElement(int i) {
      int pos = contents.binarySearch(i);
      if (pos < 0) {
        int insertionPoint = contents.size() + pos + 1;
        contents.insert(insertionPoint, i);
        fireIntervalAdded(this, insertionPoint, insertionPoint);
      }
    }
    
    public int removeElementAt(int index) {
      int stage = contents.remove(index);
      fireIntervalRemoved(this, index, index);
      return stage;
    }
    
    public void removeElement(int stage) {
      int index = contents.binarySearch(stage);
      if (index >= 0) {
        contents.remove(index);
        fireIntervalRemoved(this, index, index);
      }
    }
    
    public int getSize() {
      return contents.size();
    }

    public Object getElementAt(int index) {
      return allStagesSM.get(contents.get(index));
    }
    
    public void removeElement(String s) {
      int i = allStagesSM.indexOf(s);
      removeElement(i);
    }
    
    public void addElement(String s) {
      int i = allStagesSM.indexOf(s);
      addElement(i);
    }
    
    public int[] getStages() {
      return contents.toNativeArray();
    }
    
    public boolean isEmpty() {
      return contents.isEmpty();
    }
    
  }

}
