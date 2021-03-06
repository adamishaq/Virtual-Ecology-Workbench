package VEW.Planktonica2.PigmentGraph;

import java.awt.Color;
import java.text.DecimalFormat;
import VEW.Common.Graph.GraphModel;
import VEW.Planktonica2.Model.Spectrum;
import VEW.Planktonica2.Model.WaveLengthIntensityPair;

public class SpectrumGraph implements GraphModel {
	
	private Spectrum spec;
	private int noYPoints;

	/**
	 * Creates a graph with an attached spectrum.
	 * 
	 * @param spectrum the spectrum to display on the graph.
	 * @param noYPoints the number of markers to put on the y axis of the graph.
	 */
	public SpectrumGraph(Spectrum spectrum, int noYPoints) {
		
		this.spec = spectrum;
		this.noYPoints = noYPoints;
		
	}
	
	/**
	 * Creates a graph with an attached spectrum.
	 * 
	 * @param spectrum the spectrum to display on the graph.
	 */
	public SpectrumGraph(Spectrum spectrum) {
		this.spec = spectrum;
		this.noYPoints = 10;
	}

	@Override
	public int xAxisSize() {
		return WaveLengthIntensityPair.wavelengths.length;
	}

	@Override
	public int yAxisSize() {
		return this.noYPoints;
	}

	@Override
	public String getXAxisValue(int x) {
		if (x >= 0 && x < xAxisSize()) {
			return String.valueOf(spec.getSpectrumValue(x).getWavelength());
		} else {
			return "";
		}
		
	}

	@Override
	public String getYAxisValue(int y) {

		DecimalFormat format = new DecimalFormat("0.00E00");
		
		double markerSize = (maxDataValue() / ((double) yAxisSize()));
		markerSize *= y;
		return format.format(markerSize);
	}
	
	
	
	

	private double maxDataValue() {
		double max = Double.MIN_VALUE;
		for (WaveLengthIntensityPair p : spec) {
			if (p.getIntensity() > max) {
				max = p.getIntensity();
			}
		}
		
		return max;
	}

	@Override
	public double getValue(int x) {
		
		if (!spec.hasPigment()) {
			return 0.0;
		}
		
		try {
			double intensity = spec.getSpectrumValue(x).getIntensity();
			double max = maxDataValue();
			return (max > 0) ? intensity / max : 0.0;
		} catch (IndexOutOfBoundsException i) {
			return 0.0;
		}
	}

	@Override
	public Color getColumnColor(int x) {
		return WaveLengthIntensityPair.RGBForWaveLength(WaveLengthIntensityPair.wavelengths[x]);
	}

}
