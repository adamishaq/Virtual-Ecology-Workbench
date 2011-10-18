/***************************************************************\
|*  File: Constants.java                                       *|
|*  Created: 11th January 2003 by Adrian Rogers (atr99)        *|
|*  Last Modified: 11th January 2003 by Adrian Rogers (atr99)  *|
|***************************************************************|
|*  Description:                                               *|
|*  This class contains constants required by TrackGen.        *|
\***************************************************************/

package VEW.Scenario2;

import java.util.HashMap;

public final class Constants 
{
	final public static double[] RungeKuttaConstant = new double[24]; // Array used for the Runge-Kutta Integration.
	final public static int StandardAnnualLevels  = 33;	// The number of standard vertical levels for annual and seasonal data (KLMT in the original).
	final public static int StandardMonthlyLevels = 19; // The number of standard vertical levels for monthly data (KLM in the original).
	final public static double[] LevitusConstant  = new double[7];	// Array used for the constants needed for the calculation of density from the temperature and the salinity
	final public static int[] StandardLevelDepth  = new int[40];	// Array used for the depths of the standard levels.
	final public static int UXDimension = 126; // This and the next 3 variables will be eliminated
	final public static int UYDimension = 103; // Since they are related to the velocity data set
	final public static int VXDimension = 125; // And we'll want to introduce more velocity data sets.
	final public static int VYDimension = 104;
   final public static int[] Scale = {6,5};

	static protected HashMap DataCodes;

	static
	{
		// Initialise the Runge-Kutta constants
		RungeKuttaConstant[0]  = 0.25;
		RungeKuttaConstant[1]  = 0.09375;
		RungeKuttaConstant[2]  = 0.28125;
		RungeKuttaConstant[3]  = 1932.0 / 2197.0;
		RungeKuttaConstant[4]  = 7200.0 / 2197.0;
		RungeKuttaConstant[5]  = 7296.0 / 2197.0;
		RungeKuttaConstant[6]  = 439.0 / 216.0;
		RungeKuttaConstant[7]  = 8.0;
		RungeKuttaConstant[8]  = 3680.0 / 513.0;
		RungeKuttaConstant[9]  = 845.0 / 4104.0;
		RungeKuttaConstant[10] = 8.0 / 27.0;
		RungeKuttaConstant[11] = 2.0;
		RungeKuttaConstant[12] = 3544.0 / 2565.0;
		RungeKuttaConstant[13] = 1859.0 / 4104.0;
		RungeKuttaConstant[14] = 11.0 / 40.0;
		RungeKuttaConstant[15] = 1.0 / 360.0;
		RungeKuttaConstant[16] = 128.0 / 4275.0;
		RungeKuttaConstant[17] = 2197.0 / 75240.0;
		RungeKuttaConstant[18] = 1.0 / 50.0;
		RungeKuttaConstant[19] = 2.0 / 55.0;
		RungeKuttaConstant[20] = 25.0 / 216.0;
		RungeKuttaConstant[21] = 1408.0 / 2565.0;
		RungeKuttaConstant[22] = 2197.0 / 4104.0;
		RungeKuttaConstant[23] = 0.2;

		// Initialise the Levitus calculation constants
		LevitusConstant[0] = -0.072169;
		LevitusConstant[1] = -0.049726;
		LevitusConstant[2] = 0.8056;
		LevitusConstant[3] = -0.0075911;
		LevitusConstant[4] = -0.0030063;
		LevitusConstant[5] = 0.000035187;
		LevitusConstant[6] = 0.000037297;

		// Initialise the standard level depths (m)
		StandardLevelDepth[0]  = 0;
		StandardLevelDepth[1]  = 10;
		StandardLevelDepth[2]  = 20;
		StandardLevelDepth[3]  = 30;
		StandardLevelDepth[4]  = 50;
		StandardLevelDepth[5]  = 75;
		StandardLevelDepth[6]  = 100;
		StandardLevelDepth[7]  = 125;
		StandardLevelDepth[8]  = 150;
		StandardLevelDepth[9]  = 200;
		StandardLevelDepth[10] = 250;
		StandardLevelDepth[11] = 300;
		StandardLevelDepth[12] = 400;
		StandardLevelDepth[13] = 500;
		StandardLevelDepth[14] = 600;
		StandardLevelDepth[15] = 700;
		StandardLevelDepth[16] = 800;
		StandardLevelDepth[17] = 900;
		StandardLevelDepth[18] = 1000;
		StandardLevelDepth[19] = 1100;
		StandardLevelDepth[20] = 1200;
		StandardLevelDepth[21] = 1300;
		StandardLevelDepth[22] = 1400;
		StandardLevelDepth[23] = 1500;
		StandardLevelDepth[24] = 1750;
		StandardLevelDepth[25] = 2000;
		StandardLevelDepth[26] = 2500;
		StandardLevelDepth[27] = 3000;
		StandardLevelDepth[28] = 3500;
		StandardLevelDepth[29] = 4000;
		StandardLevelDepth[30] = 4500;
		StandardLevelDepth[31] = 5000;
		StandardLevelDepth[32] = 5500;
		StandardLevelDepth[33] = 6000;
		StandardLevelDepth[34] = 6500;
		StandardLevelDepth[35] = 7000;
		StandardLevelDepth[36] = 7500;
		StandardLevelDepth[37] = 8000;
		StandardLevelDepth[38] = 8500;
		StandardLevelDepth[39] = 9000;


		// Initialise the data codes
		DataCodes = new HashMap();
		DataCodes.put("Temperature","T");
		DataCodes.put("Salinity","S");
		DataCodes.put("Disolved Oxygen","O");
		DataCodes.put("Percent Oxygen Saturation","X");
		DataCodes.put("Apparent Oxygen Utilization","A");
		DataCodes.put("Phosphate","P");
		DataCodes.put("Nitrate","N");
		DataCodes.put("Silicate","I");
		DataCodes.put("Chlorophyll","C");
		DataCodes.put("Zooplankton Biomass","Z");
      
	}
}