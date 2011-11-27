package VEW.Planktonica2.PigmentGraph;

import javax.swing.table.DefaultTableModel;

import VEW.Planktonica2.Model.Spectrum;
import VEW.Planktonica2.Model.WaveLengthIntensityPair;

public class PigmentTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 6904052847987207776L;
	private final String minColName = "Min W";
	private final String maxColName = "Max W";
	private final String valueColName = "Value";
	private final String[] colHeaders = new String[] {minColName, maxColName, valueColName};
	private Spectrum spectrum;
	
	public PigmentTableModel() {
		super();
		this.setColumnCount(colHeaders.length);
		this.setColumnIdentifiers(colHeaders);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		
		return (colHeaders[col].equals(valueColName));
			
	}

	public Spectrum getSpectrum() {
		return this.spectrum;
	}
	
	public void setSpectrum (Spectrum s) {
		this.spectrum = s;
	}
	
	@Override
	public int getColumnCount() {
		return colHeaders.length;
	}
	
	@Override
	public int getRowCount() {
		if (spectrum == null) {
			return 0;
		}
		return this.spectrum.getNumberValues();
	}
	
	@Override
	public void setValueAt(Object o, int row, int col) {
		
		Double d = null;
		if (o instanceof Double) {
			d = (Double) o;
		} else if (o instanceof Integer) {
			d = ((Integer) o) + 0.0;
		} else if (o instanceof String) {
			try {
				d = Double.valueOf((String) o);
			} catch (NumberFormatException n) {
				d = null;
			}
		}
		
		if (d == null) {
			this.setValueAt(0.0, row, col);
			return;
		} 
		if (colHeaders[col].equals(valueColName)) {
			this.spectrum.setValue(row, d);
		}
		
		this.fireTableDataChanged();
		
	}
	
	@Override
	public Object getValueAt (int row, int col) {
		
		if (spectrum == null) {
			return 0.0;
		}
		
		WaveLengthIntensityPair p = spectrum.getSpectrumValue(row);
		
		if (p == null) {
			return 0.0;
		}
		
		if (colHeaders[col].equals(minColName)) {
			return p.getWavelength();
		} else if (colHeaders[col].equals(maxColName)) {
			return p.getNextWavelength();
		} else if (colHeaders[col].equals(valueColName)) {
			if (spectrum.hasPigment()) {
				return p.getIntensity();
			} else {
				return 0.0;
			}
		} else {
			return 0.0;
		}
	}

}
