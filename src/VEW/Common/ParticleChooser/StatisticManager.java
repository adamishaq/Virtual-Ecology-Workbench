package VEW.Common.ParticleChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import VEW.Common.ObjectList.ObjectEditListener;
import VEW.Common.ObjectList.ObjectManager;

public class StatisticManager implements ObjectManager {

  private String[] variables;

  public StatisticManager(String[] _variables) {
    variables = _variables;
  }

  public Object createNew() {
    return new Statistic();
  }

  public void editObject(Object obj, ObjectEditListener listener) {
    EditDialog dialog = new EditDialog((Statistic) obj, listener);
    dialog.setModal(true);
    dialog.setVisible(true);
  }

  private class EditDialog extends JDialog {

    private Statistic statistic;
    private ObjectEditListener listener;

    private JComboBox variableCombo;
    private JComboBox statisticCombo;

    public EditDialog(Statistic _statistic, ObjectEditListener _listener) {
      super();

      try {
        statistic = (Statistic) _statistic.clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
      }
      listener = _listener;

      createComponents();
      setDefaults();
    }

    private void setDefaults() {
      if (statistic.getVariable() != -1)
        variableCombo.setSelectedIndex(statistic.getVariable());

      if (statistic.getStatistic() != -1)
        statisticCombo.setSelectedIndex(statistic.getStatistic());
    }

    private void createComponents() {
      JPanel mainPanel = new JPanel(new GridBagLayout());
      mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.HORIZONTAL;

      c.gridx = 0;
      mainPanel.add(new JLabel("Variable:", SwingConstants.RIGHT), c);

      variableCombo = new JComboBox(variables);
      c.gridx = 1;
      mainPanel.add(variableCombo, c);

      c.gridx = 0;
      mainPanel.add(new JLabel("Statistic:", SwingConstants.RIGHT), c);

      statisticCombo = new JComboBox(ParticleStatistics.DESCRIPTIONS);
      c.gridx = 1;
      mainPanel.add(statisticCombo, c);

      getContentPane().add(mainPanel, BorderLayout.CENTER);

      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      JButton okButton = new JButton("OK");
      okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          setVisible(false);
          finishUp();
        }
      });
      buttonPanel.add(okButton);

      JButton cancelButton = new JButton("Cancel");
      cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          setVisible(false);
        }
      });
      buttonPanel.add(cancelButton);

      getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      pack();
    }

    protected void finishUp() {
      statistic.setVariable(variableCombo.getSelectedIndex());
      statistic.setVariableName((String) variableCombo.getSelectedItem());
      statistic.setStatistic(statisticCombo.getSelectedIndex());

      listener.onEditFinished(statistic);
    }

  }

}
