package VEW.Planktonica2.Model;

import java.util.ArrayList;


public class NullSpectrum extends Spectrum {

	public NullSpectrum () {
		this.name = "No associated spectrum";
		this.values = new ArrayList<WaveLengthIntensityPair> ();
		for (int i = 0; i < WaveLengthIntensityPair.wavelengths.length; i++) {
			values.add(new WaveLengthIntensityPair(i, 0.0));
		}
	}
	
}
