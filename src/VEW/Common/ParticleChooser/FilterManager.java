package VEW.Common.ParticleChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;

import VEW.Common.ObjectList.ObjectEditListener;
import VEW.Common.ObjectList.ObjectManager;

public class FilterManager implements ObjectManager {

  private String[] variables;

  public FilterManager(String[] _variables) {
    variables = _variables;
  }

  public Object createNew() {
    return new Filter();
  }

  public void editObject(Object obj, ObjectEditListener listener) {
    FilterDialog dialog = new FilterDialog((Filter) obj, listener);
    dialog.setModal(true);
    dialog.setVisible(true);
  }

  public class FilterDialog extends JDialog {

    private JComboBox variable;
    private JComboBox comparison;
    private JComboBox statistic;
    private JFormattedTextField value;
    private ObjectEditListener listener;

    public FilterDialog(ObjectEditListener _listener) {
      super();
      listener = _listener;
      createComponents();
    }

    public FilterDialog(Filter filter, ObjectEditListener _listener) {
      this(_listener);

      if (filter.getVariable() != -1) {
        variable.setSelectedIndex(filter.getVariable());
        comparison.setSelectedIndex(filter.getComparison());
        statistic.setSelectedIndex(filter.getStatistic());
        // value.setText(Double.toString(filter.getValue()));
        value.setValue(new Double(filter.getValue()));
      } else {
        value.setValue(new Double(0.0));
      }
    }

    private void createComponents() {
      getContentPane().setLayout(new BorderLayout());

      JPanel comboPanel = new JPanel(new GridBagLayout());
      comboPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      getContentPane().add(comboPanel, BorderLayout.CENTER);

      GridBagConstraints c = new GridBagConstraints();
      c.fill = GridBagConstraints.HORIZONTAL;
      c.weightx = 0.0;

      variable = new JComboBox(variables);
      comboPanel.add(variable, c);

      statistic = new JComboBox(ParticleStatistics.DESCRIPTIONS);
      comboPanel.add(statistic, c);

      comparison = new JComboBox(Filter.DESCRIPTIONS);
      comboPanel.add(comparison, c);

      NumberFormat format = NumberFormat.getNumberInstance();
      value = new JFormattedTextField(format);
      value.setText("A long padding string");
      c.weightx = 1.0;
      comboPanel.add(value, c);

      JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      getContentPane().add(buttonPanel, BorderLayout.SOUTH);

      JButton okButton = new JButton("OK");
      okButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          setVisible(false);
          System.out.println(value.getValue());
          System.out.println(value.getValue().getClass());
          listener.onEditFinished(getFilter());
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
      
      pack();
      value.setText("0");
    }

    public Filter getFilter() {
      Number v = (Number) value.getValue();
      return new Filter(variable.getSelectedIndex(), (String) variable
          .getSelectedItem(), statistic.getSelectedIndex(), comparison
          .getSelectedIndex(), v.doubleValue());
    }

  }

}
