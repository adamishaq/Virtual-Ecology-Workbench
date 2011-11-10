package VEW.Planktonica2;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import VEW.Planktonica2.ControllerStructure.Function;
import VEW.Planktonica2.ControllerStructure.FunctionalGroup;
import VEW.Planktonica2.ControllerStructure.Stage;
import VEW.Planktonica2.ControllerStructure.VEWController;

public class StageEditorPanel extends JPanel {

	private static final long serialVersionUID = -4354313346035286536L;
	
	private VEWController controller;

	private ColumnModel cols;
	
	public StageEditorPanel (VEWController controller) {
		super();
		this.controller = controller;
		
		initializeGUI();
		
		
	}

	private void initializeGUI() {
		// JTable
		StageEditorTable tableModel = new StageEditorTable (this.controller);
		
		
		
		
		JTable table = new JTable (tableModel);
		
		this.cols = new ColumnModel(controller);
		
		table.setTableHeader(new JTableHeader(cols));
		table.setFillsViewportHeight(true);
		
		JScrollPane scroller = new JScrollPane (table);
		
		
		
		this.add(scroller);
		
		// 3 button
		
	}

	
	private class ColumnModel extends DefaultTableColumnModel implements Observer {

		private static final long serialVersionUID = -1025098191670924655L;
		
		private VEWController controller;
		
		public ColumnModel(VEWController controller) {
			super();
			this.controller = controller;
			controller.addObserver(this);
			fillColumns();
		}

		private void fillColumns() {
			
			// remove all columns.
			for (TableColumn c : this.tableColumns) {
				this.removeColumn(c);
			}
			
			
			FunctionalGroup g = controller.getSelectedFunctionalGroup();
			
			if (g != null) {
			
				Collection<Stage> stages = g.getStages();
				int modelIndex = 1;

				for (Stage s : stages) {
					TableColumn c = new TableColumn(modelIndex);
					c.setHeaderValue(s.getName());
					this.addColumn(c);
					modelIndex++;
				}
			}
			
		}

		@Override
		public void update(Observable obs, Object arg) {
			
			if (obs == controller) {
				
				fillColumns();
				
			}
			
		}
		
		
		
	}
	
	private class StageEditorTable extends AbstractTableModel {

		private static final long serialVersionUID = 9005405339776933763L;
		private VEWController controller;
		
		public StageEditorTable (VEWController controller) {
			this.controller = controller;
		}
		
		@Override
		public int getColumnCount() {
			int colCount = this.controller.getNoStages() + 1; 
			return (colCount < 2) ? 2 : colCount;
		}

		@Override
		public int getRowCount() {
			int rowCount = this.controller.getNoFunctions(); 
			return (rowCount < 1) ? 1 : rowCount;
		}

		
		@Override
		/**
		 * 
		 * if x < 2 it returns the function name at y
		 *
		 * 
		 * else it returns whether the function(y) is called in the stage(x)
		 * 
		 * @param x
		 * @param y
		 * return correct value at cell (x, y)
		 */
		public Object getValueAt(int x, int y) {
			
			if (controller.getSelectedFunctionalGroup() == null) {
				
				return "fail";
				
			} else if (x < 2) {
				
				Function selected = this.controller.getFunctionAtIndex(y);
				
				return selected.getName();
				
			} else {
				
				Stage selected = this.controller.getStageAtIndex(x - 1);
				Function f = this.controller.getFunctionAtIndex(y);
				
				// called in holds a referrence to the origional stage
				return f.isCalledIn(selected);
			}
		}
		
		@Override
		public void setValueAt(Object aValue, int x, int y) {
			if (aValue instanceof Boolean) {
				boolean isCalled = (Boolean) aValue;
				
				
				Stage selected = controller.getStageAtIndex(x);
				Function f = controller.getFunctionAtIndex(y);
				
				if (isCalled) {
					// add to list of CalledIn
					f.addToCalledIn(selected);
					
				} else if (!isCalled) {
					// remove from list
					f.removeFromCalledIn(selected);
					
				}
			}
			
			this.fireTableCellUpdated(x, y);
		}
		
		@Override
		public Class<?> getColumnClass(int column) {
			return (column > 1) ? Boolean.class : String.class;
		}
		
		public boolean isCellEditable(int row, int col) {
			
			return col > 1;
			
		}
		
	}
	
}
