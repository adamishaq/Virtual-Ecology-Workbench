package VEW.Planktonica2.Model;

import java.awt.Color;

/**
 * Java bean containing wavelength and intensity values.
 * 
 * @author Chris Bates
 *
 */
public class WaveLengthIntensityPair {

	public static final double[] wavelengths = new double[] {300,357.5,387.5,412.5,437.5,462.5,487.5,512.5,537.5,562.5,587.5,612.5,
			637.5,662.5,687.5,712.5,737.5,787.5,900,1100,1300,1500,1700,1900,2100};
	public static final double maxWavelength = 2300;
	
	private int wavelengthIndex;
	private double intensity;
	
	public WaveLengthIntensityPair() {
		this.wavelengthIndex = 0;
		this.intensity = 0;
	}

	public WaveLengthIntensityPair(int wavelengthIndex, double intensity) {
		this.wavelengthIndex = wavelengthIndex;
		this.intensity = intensity;
	}
	
	public WaveLengthIntensityPair(WaveLengthIntensityPair w) {
		this.wavelengthIndex = w.getWavelengthIndex();
		this.intensity = w.getIntensity();
	}

	/**
	 * Sets the wavelength to a given index of wavelength.
	 * @param index the index in the WaveLengthIntensityPair.wavelengths array of the wavelength
	 * @throws IndexOutOfBoundsException thrown if the index is not in the wavelngths array.
	 */
	public void setWavelength(int index) {
		
		if (index < 0 || index >= wavelengths.length) {
			throw new IndexOutOfBoundsException("No wavelength at index " + index + ".");
		}
		
		this.wavelengthIndex = index;
	}
	
	/**
	 * Sets the index to the corresponding wavelength value.
	 * @param wavelength the wavelength to set to.
	 * @throws IndexOutOfBoundsException thrown if there is no matching wavelength.
	 */
	public void setWavelength(double wavelength) {
		
		for (int i = 0; i < wavelengths.length; i++) {
			if (wavelengths[i] == wavelength) {
				this.wavelengthIndex = i;
				return;
			}
		}
		
		throw new IndexOutOfBoundsException("No equivilent wavelength in the wavelength table.");
		
	}

	public int getWavelengthIndex() {
		return this.wavelengthIndex;
	}
	
	public double getWavelength() {
		return wavelengths[this.wavelengthIndex];
	}
	
	public double getIntensity() {
		return intensity;
	}

	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}
	
	/**
	 * 
	 * @param wavelength the double wavelength to convert.
	 * @return the Color associated with the given wavelenth 
	 */
	public static Color RGBForWaveLength(double wavelength) {
	      double r=0;
	      double b=0;
	      double g=0;
	      if ((wavelength>=380) && (wavelength<440)) { r=(440-wavelength)/60; g=0; b=1; }
	      else if ((wavelength>=440) && (wavelength<490)) { r=0; g=(wavelength-440)/50; b=1; }
	      else if ((wavelength>=490) && (wavelength<510)) { b=(510-wavelength)/20; r=0; g=1; }
	      else if ((wavelength>=510) && (wavelength<580)) { b=0; g=1; r=(wavelength-510)/70; }
	      else if ((wavelength>=580) && (wavelength<645)) { r=1; g=(645-wavelength)/65; b=0; }
	      else if ((wavelength>=645) && (wavelength<780)) { r=1; g=0; b=0; }
	      if (wavelength>700) r=r*(0.3+(0.7*((780-wavelength)/80)));
	      if ((wavelength<420) && (wavelength>=380)) { r = r*(0.3+(0.7*(wavelength-380)/40)); b = (0.3+(0.7*(wavelength-380)/40)); }
	      r = r*255;
	      b = b*255;
	      g = g*255;
	      return new Color((int)r,(int)g,(int)b);
	    }

	public double getNextWavelength() {
		
		int nextLength = this.getWavelengthIndex()+1;
		if (nextLength >= wavelengths.length) {
			return WaveLengthIntensityPair.maxWavelength;
		} else {
			return wavelengths[nextLength];
		}
	}
	
}
