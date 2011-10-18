package VEW.Common;

import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class WorkingTable extends JTable {
  public void changeSelection( int rowIndex, int columnIndex, boolean toggle,
      boolean extend) {
  ListSelectionModel rsm = getSelectionModel();
  ListSelectionModel csm = getColumnModel().getSelectionModel();
 
  changeSelectionModel( csm, columnIndex, false, extend);

  changeSelectionModel( rsm, rowIndex, toggle, extend);
 
  if ( getAutoscrolls()) {
      Rectangle cellRect = getCellRect( rowIndex, columnIndex, false);
      if ( cellRect != null) {
          scrollRectToVisible( cellRect);
      }
  }
}
private void changeSelectionModel( ListSelectionModel sm, int index,
      boolean toggle, boolean extend) {
  if ( extend) {
      if ( toggle) {
          sm.setAnchorSelectionIndex( index);
      } else {
          sm.setLeadSelectionIndex( index);
      }
  } else {
      if ( toggle) {
          if ( sm.isSelectedIndex( index)) {
              sm.removeSelectionInterval( index, index);
          } else {
              sm.addSelectionInterval( index, index);
          }
      } else {
          sm.setSelectionInterval( index, index);
      }
  }
}

}
