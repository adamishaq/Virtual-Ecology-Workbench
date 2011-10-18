package VEW.Common;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MessageBox {
  public static void showMessage(String text, JComponent parent) {
    final JTextArea textArea = new JTextArea(text, 10, 40);
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    final JOptionPane optionPane = new JOptionPane();
    JScrollPane jsp = new JScrollPane(textArea);
    textArea.setLineWrap(false);
    jsp.setPreferredSize(new Dimension(400,100));
    Object msg[] = {new JScrollPane(textArea)};
    optionPane.setMessage(msg);
    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
    optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
    final JDialog dialog = optionPane.createDialog(parent, "Information");
    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);   //disable close window
    dialog.setVisible(true);
  }
}
