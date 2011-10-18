/***************************************************************\
|*  File: VEWUtilities.java                                    *|
|*  Created: 11th January 2003 by Adrian Rogers (atr99)        *|
|*  Last Modified: 11th January 2003 by Adrian Rogers (atr99)  *|
|***************************************************************|
|*  Description:                                               *|
|*  This class contains useful functions and constants for     *|
|*  use in VEW applications.                                   *|
\***************************************************************/

package VEW.Common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.SimpleTimeZone;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.SpringLayout;

import VEW.Controller2.VEWController2;

public class VEWUtilities
{

   // Mathematical constants.
	final public static double PI                 = 3.1415926536;   	// The value of PI to be used by the program.
	final public static double Radian             = 57.295779513;     // The number of degrees per radian to be used by the program.
	final public static double PIby2              = PI / 2.0;	      // Pi divided by 2.
	final public static double PIby4              = PI / 4.0;	      // Pi divided by 4.
	final public static double RootTwoPi          = Math.sqrt(PI * 2);// Square root of 2 * Pi.

   // Date related constants.
	final public static int[] StandardMonthLength = new int[] // Array to store the standard month lengths.
    {31,28,31,30,31,30,31,31,30,31,30,31};
  final public static int[] StandardMonthStart  = new int[] // Array to store the standard day of the year a month begins.
    {1,32,60,91,121,152,182,213,244,274,305,335};                                                      
	final public static int[] StandardMonthMiddle = new int[] // Array to store the standard day of the year the middle of the month falls on. 
    {16,46,75,106,136,167,197,228,259,289,320,350};		
	final public static int[] LeapMonthLength     = new int[] // Array to store the month lengths in a leap year.
    {31,29,31,30,31,30,31,31,30,31,30,31};
  final public static int[] LeapMonthStart      = new int[] // Array to store the day of the year a month begins ina leap year.
    {1,32,61,92,122,153,183,214,245,275,306,336};
  final public static int[] LeapMonthMiddle     = new int[] // Array to store the day of the year the middle of the month falls on in a leap year.
    {16,46,76,107,137,168,198,229,260,290,321,351};
	final public static String[] LongMonthName    = new String[12];	// Array to store the full names of each month.
  final public static String[] MonthDays        = new String[31]; // Array used to hold the days in the form 1st, 2nd, 3rd ...
	final public static String[] ShortMonthName   = new String[12];	// Array to store the shortened name of each month.
  final public static String[] HalfHourSteps    = new String[48];

  private static HashMap ExponentMap = new HashMap();
  private static HashMap ExponentFullMap = new HashMap();

	/*\
	 *  Static functions begin here.
	 *  showWarningMessage(String) - displays a suitable message box for issuing warnings to the user.
	 *  showInfoMessage(String)    - displays a suitable message box for providing information to the user.
	 *  showErrorMessage(String)   - displays a suitable message box for notifying the user of an error.
	\*/

	public static void showWarningMessage(Component parentComponent, String application, String message)
	{
		JOptionPane.showMessageDialog(parentComponent, message, VEWController2.ApplicationName + application, JOptionPane.WARNING_MESSAGE);
	}

	public static void showInfoMessage(Component parentComponent, String application, String message)
	{
		JOptionPane.showMessageDialog(parentComponent, message, VEWController2.ApplicationName + application, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showErrorMessage(Component parentComponent, String application, String message)
	{
		JOptionPane.showMessageDialog(parentComponent, message, VEWController2.ApplicationName + application, JOptionPane.ERROR_MESSAGE);
	}

	public static boolean showOKCancelMessage(Component parentComponent, String application, String message)
	{
		return JOptionPane.showConfirmDialog(parentComponent, message, VEWController2.ApplicationName + application, JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.OK_OPTION;
    
	}

	public static boolean showYesNoMessage(Component parentComponent, String application, String message)
	{
		return JOptionPane.showConfirmDialog(parentComponent, message, VEWController2.ApplicationName + application, JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION;
    
	}

  
  public static double[] CalculateNumberAxis(double Start, double Finish, double Width)
  {
    /*\
     *  Start represents one end of the axis, Finish the other.
     *  Width represents the Width to be covered by the axis.
     *
     *  Values returned will be:
     *  0: The gap required at the start of the axis
     *  1: The gap between steps
     *  2: The exponent (if the numbers have been adjusted).
    \*/
    double StartGap = 0;
    double StepGap  = 0;
    double Exponent = 0;

    // Begin by multiplying / dividing the numbers until they are between 10^3 and 10^{-2} setting the exponent as we go.
    double TempValue = Math.abs(Finish - Start);
    while(TempValue > 1000)
    {
      TempValue /= 1000;
      Exponent += 3;
    }
    while(TempValue < 0.01)
    {
      TempValue *= 1000;
      Exponent -= 3;
    }

    // Now we can calculate the gap to use between the steps.
    TempValue /= 10;
    if(TempValue > 50)
    {
      StepGap = 100;
    }
    else if(TempValue > 20)
    {
      StepGap = 50;
    }
    else if(TempValue > 10)
    {
      StepGap = 20;
    }
    else if(TempValue > 5)
    {
      StepGap = 10;
    }
    else if(TempValue > 2)
    {
      StepGap = 5; 
    }
    else if(TempValue > 1)
    {
      StepGap = 2;
    }
    else if(TempValue > 0.5)
    {
      StepGap = 1;
    }
    else if(TempValue > 0.2)
    {
      StepGap = 0.5;
    }
    else if(TempValue > 0.1)
    {
      StepGap = 0.2;
    }
    else if(TempValue > 0.05)
    {
      StepGap = 0.1;
    }
    else if(TempValue > 0.01)
    {
      StepGap = 0.05;
    }
    else
    {
      StepGap = 0.01;
    }

    // Now we work out the gap to include at the start of the axis.
    StartGap = Start / Math.pow(10, Exponent);
    if(Start > Finish)
    {
      StartGap = (StartGap / StepGap) - Math.floor(StartGap / StepGap);
    }
    else
    {
      StartGap = Math.ceil(StartGap / StepGap) - (StartGap / StepGap);
    }
    StartGap *= StepGap;

    // Now scale to the axis width.
    TempValue *= 10;
    TempValue = Width / TempValue;
    double StartAxisGap = StartGap * TempValue;
    double StepAxisGap  = StepGap  * TempValue;
    if(Start > Finish)
    {
      StartGap *= -1;
      StepGap  *= -1;
    }
    double[] ReturnValues = {StartGap, StepGap, Exponent, StartAxisGap, StepAxisGap};
    return ReturnValues;
  }

   /*\
    *  These functions are for aiding in layout management.
   \*/
   // Procedure for adding buttons with text to the panel.
	public static SpringLayout.Constraints addButton(String buttonText, ActionListener eventMonitor, Container sprungPanel, SpringLayout layoutManager, Dimension size, String tipText, int mnemonic)
	{
		JButton tempButton = new JButton(buttonText); // Create a new button with the text provided.
		tempButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Set the border of the button.
		tempButton.setPreferredSize(size); // Set the default size.
		tempButton.addActionListener(eventMonitor); // Add the listener for the button.
    tempButton.setToolTipText(tipText); // Add tool tip text to the button.
    tempButton.setMnemonic(mnemonic); // Set the mnemonic for the button.
		sprungPanel.add(tempButton); // Add the button to the panel.
		return layoutManager.getConstraints(tempButton); // Return the constraints of the button.
	}

   // Procedure for adding pre created buttons to the panel.
	public static SpringLayout.Constraints addButton(JButton tempButton, ActionListener eventMonitor, Container sprungPanel, SpringLayout layoutManager, Dimension size, String tipText, int mnemonic)
	{
		tempButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Set the border of the button.
		tempButton.setPreferredSize(size); // Set the default size.
		tempButton.addActionListener(eventMonitor); // Add the listener for the button.
    tempButton.setToolTipText(tipText); // Add tool tip text to the button.
    tempButton.setMnemonic(mnemonic); // Set the mnemonic for the button.
		sprungPanel.add(tempButton); // Add the button to the panel.
		return layoutManager.getConstraints(tempButton); // Return the constraints of the button.
	}
   
   // Procedure for adding buttons with icons to the panel.
	public static SpringLayout.Constraints addToggleButton(JToggleButton tempButton, ActionListener eventMonitor, Container sprungPanel, SpringLayout layoutManager, ButtonGroup toggleGroup, Dimension size, String tipText, int mnemonic)
	{
		tempButton.setBorder(BorderFactory.createRaisedBevelBorder()); // Set the border of the button.
		tempButton.setPreferredSize(size); // Set the default size.
		tempButton.addActionListener(eventMonitor); // Add the listener for the button.
    tempButton.setToolTipText(tipText); // Add tool tip text to the button.
    tempButton.setMnemonic(mnemonic); // Set the mnemonic for the button.
		sprungPanel.add(tempButton); // Add the button to the panel.
    toggleGroup.add(tempButton);
    if(toggleGroup.getButtonCount() == 1)
    {
       tempButton.setSelected(true);
    }
		return layoutManager.getConstraints(tempButton); // Return the constraints of the button.
	}

   // Procedure for adding items other than labels or buttons to the panel.
	public static SpringLayout.Constraints addItem(JComponent compon, String tipText, Container sprungPanel, SpringLayout layoutManager)
	{
		sprungPanel.add(compon);                     // Add the component to the panel.
    compon.setToolTipText(tipText);              // Add tool tip text to the component.
		return layoutManager.getConstraints(compon); // Return the constraints of the component.
	}

   // Procedure for adding labels to the panel.
	public static SpringLayout.Constraints addLabel(String labelText, Container sprungPanel, SpringLayout layoutManager)
	{
		JLabel label = new JLabel(labelText);        // Create a new Label with the desired text.
		sprungPanel.add(label);                      // Add the label to the panel.
		return layoutManager.getConstraints(label);  // Return the label's constraints.
	}

   // Changes the background colour of a component and all it's sub components
   public static void ChangeComponentColour(Container c, Color newBackground)
   {
      Component[] Cs = c.getComponents();
      c.setBackground(newBackground);
      for(int i = 0; i < Cs.length; i++)
      {
         Class compType = Cs[i].getClass();
         if(compType.isInstance(c))
         {
            ChangeComponentColour((Container)Cs[i], newBackground);
         }
         else
         {
            Cs[i].setBackground(newBackground);
         }
      }
   }

   // Changes the font size of a component and all it's sub components
   public static void ChangeFontSize(Container c, float newSize)
   {
      Component[] Cs = c.getComponents();
      c.setFont(c.getFont().deriveFont(newSize));
      for(int i = 0; i < Cs.length; i++)
      {
         Class compType = Cs[i].getClass();
         if(compType.isInstance(c))
         {
            ChangeFontSize((Container)Cs[i], newSize);
         }
         else
         {
            Cs[i].setFont(Cs[i].getFont().deriveFont(newSize));
         }
      }
   }
        
  public static int GMTTime(int LocalTime, double LocalLongitude)
  {      
    int HoursDifference = (int)(LocalLongitude/15) * -2;
    return LocalTime + HoursDifference;
  }

  public static int DaysBetween(GregorianCalendar lower, GregorianCalendar upper)
  {
    SimpleTimeZone TZ = new SimpleTimeZone(0, "GMT"){
      public boolean useDaylightTime()
      {
        return false;
      }
    };
    lower.setTimeZone(TZ);
    long Duration = 0 - lower.getTimeInMillis();
    upper.setTimeZone(TZ);
    Duration += upper.getTimeInMillis();
    Duration /= (1000 * 60 * 60 * 24);
    return (int)Duration;
  }
  
  public static String getExponent(String s)
  {
      String exp = (String)ExponentMap.get(s);
      if (exp == null)
      {
          exp = "10^{" + s + "}";
      }
      return exp;
  }

  public static String getExponentSymbol(String s) {
    return (String)ExponentMap.get(s);
  }
  
  public static String getExponentForName(String s) {
    return (String)ExponentFullMap.get(s);
  }


  // Stuff to be initialised at the first use of this class.
  static
  {
		// Initialise month info
      // Collect the month names according to the current locale.
    java.text.DateFormatSymbols dfs = new java.text.DateFormatSymbols(java.util.Locale.ENGLISH);
    String[] Temp1 = dfs.getMonths();
    dfs = new java.text.DateFormatSymbols(java.util.Locale.ENGLISH);
    String[] Temp2 = dfs.getShortMonths();
    for(int i = 0; i < 12; i ++)
    {
      LongMonthName[i]  = Temp1[i];
      ShortMonthName[i] = Temp2[i];
    }
    int timeIndex = 0;
    for(int i = 0; i < 24; i++)
    {
       HalfHourSteps[timeIndex] = i + ":00";
       timeIndex++;
       HalfHourSteps[timeIndex] = i + ":30";
       timeIndex++;
    }

	

    MonthDays[0]  = "1st";
    MonthDays[1]  = "2nd";
    MonthDays[2]  = "3rd";
    MonthDays[3]  = "4th";
    MonthDays[4]  = "5th";
    MonthDays[5]  = "6th";
    MonthDays[6]  = "7th";
    MonthDays[7]  = "8th";
    MonthDays[8]  = "9th";
    MonthDays[9]  = "10th";
    MonthDays[10] = "11th";
    MonthDays[11] = "12th";
    MonthDays[12] = "13th";
    MonthDays[13] = "14th";
    MonthDays[14] = "15th";
    MonthDays[15] = "16th";
    MonthDays[16] = "17th";
    MonthDays[17] = "18th";
    MonthDays[18] = "19th";
    MonthDays[19] = "20th";
    MonthDays[20] = "21st";
    MonthDays[21] = "22nd";
    MonthDays[22] = "23rd";
    MonthDays[23] = "24th";
    MonthDays[24] = "25th";
    MonthDays[25] = "26th";
    MonthDays[26] = "27th";
    MonthDays[27] = "28th";
    MonthDays[28] = "29th";
    MonthDays[29] = "30th";
    MonthDays[30] = "31st";

    ExponentMap.put("-21", "z");
		ExponentMap.put("-18","a");
		ExponentMap.put("-15","f");
		ExponentMap.put("-12","p");
		ExponentMap.put("-9" ,"n");
		ExponentMap.put("-6" ,"&#0956;");
		ExponentMap.put("-3" ,"m");
		ExponentMap.put("-2" ,"c");
		ExponentMap.put("-1" ,"d");
		ExponentMap.put("0"  ,"");
		ExponentMap.put("1"  ,"da");
		ExponentMap.put("2"  ,"h");
		ExponentMap.put("3"  ,"k");
		ExponentMap.put("6"  ,"M");
		ExponentMap.put("9"  ,"G");
		ExponentMap.put("12" ,"T");
		ExponentMap.put("15" ,"P");
		ExponentMap.put("18" ,"E");
    ExponentMap.put("21" ,"Z");

    ExponentFullMap.put("zepto","-21");
    ExponentFullMap.put("atto","-18");
    ExponentFullMap.put("femto","-15");
    ExponentFullMap.put("pico","-12");
    ExponentFullMap.put("nano","-9");
    ExponentFullMap.put("micro","-6");
    ExponentFullMap.put("milli","-3");
    ExponentFullMap.put("centi","-2");
    ExponentFullMap.put("deci","-1");
    ExponentFullMap.put("-","0");
    ExponentFullMap.put("","0");
    ExponentFullMap.put("deca","1");
    ExponentFullMap.put("hecto","2");
    ExponentFullMap.put("kilo","3");
    ExponentFullMap.put("Mega","6");
    ExponentFullMap.put("Giga","9");
    ExponentFullMap.put("Terra","12");
    ExponentFullMap.put("Peta","15");
    ExponentFullMap.put("Exa","18");
    ExponentFullMap.put("Zetta","21");
   }
}
