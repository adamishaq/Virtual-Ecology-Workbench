package VEW.Planktonica2.ControllerStructure;

import java.awt.Color;

public interface GraphModel {
	
	/**
	 * 
	 * @return the number of markers to go on the xAxis
	 */
	public int xAxisSize();
	
	
	/**
	 * 
	 * @return the number of markers to go on the yAxis
	 */
	public int yAxisSize();
	
	/**
	 * 
	 * @param x the index onto the x axis. Must be between 0 and xAxisSize().
	 * @return the value on the x axis at index x.
	 */
	public String getXAxisValue(int x);
	
	/**
	 * 
	 * @param y the index onto the y axis. Must be between 0 and yAxisSize().
	 * @return the value on the y axis at index y.
	 */
	public String getYAxisValue(int y);
	
	
	/**
	 * Gets the y value on a certain value x
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double getValue(int x);
	
	/**
	 * Gets the color value for column x
	 * 
	 * @param x the index of the column
	 * @return the associated color.
	 */
	public Color getColumnColor(int x);
	
}
