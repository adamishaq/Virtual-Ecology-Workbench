/***************************************************************\
|*  File: TeXWriter.java                                       *|
|*  Created: July 2002 by Adrian Rogers (atr99)                *|
|*  Last Modified: 11th January 2003 by Adrian Rogers (atr99)  *|
|***************************************************************|
|*  Description:                                               *|
|*  This class allows the conversion of TeX mathematical       *|
|*  commands to be output to a specified location on a panel.  *|
\***************************************************************/

package VEW.Common;

//import java.text.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.HashMap;

public class TeXWriter 
{
	static public final int LTR = 0;					// Right to left, base of text on bottom (default)
	static public final int RTL = 1;					// Left to right, base of text on top
	static public final int BTT = 2;					// Bottom to Top, base of text on right
	static public final int TTB = 3;					// Top to bottom, base of text on left

	static public final int VERTICALUP = 0;				// Display text above the given point (default)
	static public final int VERTICALUPNEW = 4;			// Display text above the given point with a new line
	static public final int VERTICALDOWN = 8;			// Display below the given point
	static public final int VERTICALCENTER = 12;		// Display text horizontally centered on the given point

	static public final int HORIZONTALCENTER = 0;		// Center the text between the point, and the point + the width (default)
	static public final int HORIZONTALLEFT = 16;		// Start the text from the given point
	static public final int HORIZONTALRIGHT = 32;		// End the text at the given point + the width

	static protected HashMap symbolMap;

	public TeXWriter() 
	{
	}

	static public float write(String outputString, Graphics2D outputWriter, Point2D.Double outputLocation, double outputWidth, Font outputStyle, int options)
	{
		FontRenderContext context = outputWriter.getFontRenderContext();
		TextLayout tl;
		float xCoord, yCoord;
		float[] tempArray = {0, -1};
		int superIndex = outputString.indexOf("^{");
		int startIndex = 0;
		int symbolIndex = outputString.indexOf("\\");
		Point2D.Double superStartPoint = outputLocation;
		Font superFont = outputStyle.deriveFont(outputStyle.getSize2D() * (float)0.8);
		AffineTransform oldTransform = outputWriter.getTransform();
		tl = new TextLayout(outputString.replace('\\','\u200B').replace('^','\u200B').replace('{','\u200B').replace('}','\u200B'), outputStyle, context);
		if((options & HORIZONTALRIGHT) == HORIZONTALRIGHT)
		{
			xCoord = (float)outputLocation.getX() - (float)tl.getBounds().getWidth();
		}
		else if((options & HORIZONTALLEFT) == HORIZONTALLEFT)
		{
			xCoord = (float)outputLocation.getX();
		}
		else
		{
			xCoord = (float)outputLocation.getX() - (float)(tl.getBounds().getWidth() / 2);
		}
		if((options & VERTICALCENTER) == VERTICALCENTER)
		{
			yCoord = (float)outputLocation.getY() + (float)(tl.getBounds().getHeight() / 2);
		}
		else if((options & VERTICALDOWN) == VERTICALDOWN)
		{
			yCoord = (float)outputLocation.getY();
		}
		else if((options & VERTICALUPNEW) == VERTICALUPNEW)
		{
			yCoord = (float)tl.getBounds().getHeight() + (float)outputLocation.getY() + (float)(new TextLayout("fjyITS", outputStyle, context)).getBounds().getHeight();
		}
		else
		{
			yCoord = (float)tl.getBounds().getHeight() + (float)outputLocation.getY();
		}
		if((options & TTB) == TTB)
		{
			outputWriter.rotate((Math.PI / 2), outputLocation.getX(), outputLocation.getY());
		}
		else if((options & BTT) == BTT)
		{
			outputWriter.rotate((-1) * (Math.PI / 2), outputLocation.getX(), outputLocation.getY());
		}
		else if((options & RTL) == RTL)
		{
			outputWriter.rotate(Math.PI, outputLocation.getX(), outputLocation.getY());
		}
		float returnVal = (float)tl.getBounds().getWidth();
		while(superIndex != -1)
		{
			while(symbolIndex != -1 && symbolIndex < superIndex)
			{
				if(!outputString.substring(startIndex, symbolIndex).equals(""))
				{
					tl = new TextLayout(outputString.substring(startIndex, symbolIndex), outputStyle, context);
					tl.draw(outputWriter, xCoord, yCoord);
					xCoord += tl.getBounds().getWidth() + 1;
				}
				else
				{
					xCoord++;
				}
				superStartPoint.setLocation(xCoord, yCoord);
				tempArray = writeSymbol(outputString.substring(symbolIndex + 1), outputWriter, superStartPoint, outputWidth, outputStyle);
				startIndex = symbolIndex + (int)tempArray[1] + 1;
				symbolIndex = outputString.indexOf("\\", startIndex);
				xCoord += tempArray[0] + 1;
			}
			if(!outputString.substring(startIndex, superIndex).equals(""))
			{
				tl = new TextLayout(outputString.substring(startIndex, superIndex), outputStyle, context);
				tl.draw(outputWriter, xCoord, yCoord);
				xCoord += tl.getBounds().getWidth() + 2;
			}
			else
			{
				xCoord++;
			}
			superStartPoint.setLocation(xCoord, yCoord - (float)((new TextLayout("fjyITS", outputStyle, context)).getBounds().getHeight() * 0.5));
			tempArray = writesuper(outputString.substring(superIndex + 2), outputWriter, superStartPoint, outputWidth, superFont);
			startIndex = superIndex + (int)tempArray[1] + 3;
			superIndex = outputString.indexOf("^{", startIndex);
			symbolIndex = outputString.indexOf("\\", startIndex);
			xCoord += tempArray[0] + 1;
		}
		while(symbolIndex != -1)
		{
			if(!outputString.substring(startIndex, symbolIndex).equals(""))
			{
				tl = new TextLayout(outputString.substring(startIndex, symbolIndex), outputStyle, context);
				tl.draw(outputWriter, xCoord, yCoord);
				xCoord += tl.getBounds().getWidth() + 1;
			}
			else
			{
				xCoord++;
			}
			superStartPoint.setLocation(xCoord, yCoord);
			tempArray = writeSymbol(outputString.substring(symbolIndex + 1), outputWriter, superStartPoint, outputWidth, outputStyle);
			startIndex = symbolIndex + (int)tempArray[1] + 1;
			symbolIndex = outputString.indexOf("\\", startIndex);
			xCoord += tempArray[0] + 1;
		}
		if(!outputString.substring(startIndex).equals(""))
		{
			tl = new TextLayout(outputString.substring(startIndex), outputStyle, context);
			tl.draw(outputWriter, xCoord, yCoord);
			xCoord += tl.getBounds().getWidth() + 2;
		}
		outputWriter.setTransform(oldTransform);
		return returnVal;
	}

	static protected float[] writesuper(String outputString, Graphics2D outputWriter, Point2D.Double outputLocation, double outputWidth, Font outputStyle)
	{
		FontRenderContext context = outputWriter.getFontRenderContext();
		TextLayout tl;
		float xCoord = (float)outputLocation.getX();
		float yCoord = (float)outputLocation.getY();
		int superIndex = outputString.indexOf("^{");
		int closeIndex = outputString.indexOf("}");
		int symbolIndex = outputString.indexOf("\\");
		int startIndex = 0;
		Font superFont = outputStyle.deriveFont(outputStyle.getSize2D() * (float)0.8);
		float[] tempReturn = {0, -1};
		Point2D.Double superStartPoint = outputLocation;
		while(superIndex != -1 && superIndex < closeIndex)
		{
			while(symbolIndex != -1 && symbolIndex < superIndex)
			{
				if(!outputString.substring(startIndex, symbolIndex).equals(""))
				{
					tl = new TextLayout(outputString.substring(startIndex, symbolIndex), outputStyle, context);
					tl.draw(outputWriter, xCoord, yCoord);
					xCoord += tl.getBounds().getWidth() + 1;
				}
				else
				{
					xCoord++;
				}
				superStartPoint.setLocation(xCoord, yCoord);
				tempReturn = writeSymbol(outputString.substring(symbolIndex + 1), outputWriter, superStartPoint, outputWidth, outputStyle);
				startIndex = symbolIndex + (int)tempReturn[1] + 1;
				symbolIndex = outputString.indexOf("\\", startIndex);
				xCoord += tempReturn[0] + 1;
			}
			if(!outputString.substring(startIndex, symbolIndex).equals(""))
			{
				tl = new TextLayout(outputString.substring(startIndex, symbolIndex), outputStyle, context);
				tl.draw(outputWriter, xCoord, yCoord);
				xCoord += tl.getBounds().getWidth() + 1;
			}
			else
			{
				xCoord++;
			}
			superStartPoint.setLocation(xCoord, yCoord - (float)((new TextLayout("fjyITS", outputStyle, context)).getBounds().getHeight() * 0.5));
			tempReturn = writesuper(outputString.substring(superIndex + 2), outputWriter, superStartPoint, outputWidth, superFont);
			startIndex = superIndex + (int)tempReturn[1] + 3;
			superIndex = outputString.indexOf("^{", startIndex);
			closeIndex = outputString.indexOf("}", startIndex);
			symbolIndex = outputString.indexOf("\\", startIndex);
			xCoord += tempReturn[0] + 2;
		}
		while(symbolIndex != -1 && symbolIndex < closeIndex)
		{
			if(!outputString.substring(startIndex, superIndex).equals(""))
			{
				tl = new TextLayout(outputString.substring(startIndex, superIndex), outputStyle, context);
				tl.draw(outputWriter, xCoord, yCoord);
				xCoord += tl.getBounds().getWidth() + 1;
			}
			else
			{
				xCoord++;
			}
			superStartPoint.setLocation(xCoord, yCoord);
			tempReturn = writeSymbol(outputString.substring(symbolIndex + 1), outputWriter, superStartPoint, outputWidth, outputStyle);
			startIndex = symbolIndex + (int)tempReturn[1] + 1;
			symbolIndex = outputString.indexOf("\\", startIndex);
			xCoord += tempReturn[0] + 1;
		}
		if(!outputString.substring(startIndex, closeIndex).equals(""))
		{
			tl = new TextLayout(outputString.substring(startIndex, closeIndex), outputStyle, context);
			tl.draw(outputWriter, xCoord, yCoord);
			tempReturn[0] = (float)tl.getBounds().getWidth();
		}
		tempReturn[1] = closeIndex;
		return tempReturn;
	}

	static protected float[] writeSymbol(String outputString, Graphics2D outputWriter, Point2D outputLocation, double outputWidth, Font outputStyle)
	{
		FontRenderContext context = outputWriter.getFontRenderContext();
		float[] tempReturn = {0, 0};
		String toWrite = null;
		int i = 0;
		while((i < 14) && (i < outputString.length()) && (toWrite == null))
		{
			toWrite = (String)symbolMap.get(outputString.substring(0,i));
			i++;
		}
		if(toWrite != null)
		{
			i--;
			TextLayout tl = new TextLayout(toWrite, outputStyle, context);
			tl.draw(outputWriter, (float)outputLocation.getX() + 3, (float)outputLocation.getY());
			tempReturn[0] = (float)tl.getBounds().getWidth() + 3;
			tempReturn[1] = i;
		}
		return tempReturn;
	}

	static
	{
		symbolMap = new HashMap();
		// Capital Greek Letters
		symbolMap.put("Gamma","\u0393");
		symbolMap.put("Delta","\u0394");
		symbolMap.put("Theta","\u0398");
		symbolMap.put("Lambda","\u039B");
		symbolMap.put("Xi","\u039E");
		symbolMap.put("Pi","\u03A0");
		symbolMap.put("Sigma","\u03A3");
		symbolMap.put("Upsilon","\u03A5");
		symbolMap.put("Phi","\u03A6");
		symbolMap.put("Psi","\u03A8");
		symbolMap.put("Omega","\u03A9");

		// Lower case Greek Letters
		symbolMap.put("alpha","\u03B1");
		symbolMap.put("beta","\u03B2");
		symbolMap.put("gamma","\u03B3");
		symbolMap.put("delta","\u03B4");
		symbolMap.put("epsilon","\u03B5");
		symbolMap.put("zeta","\u03B6");
		symbolMap.put("eta","\u03B7");
		symbolMap.put("theta","\u03B8");
		symbolMap.put("iota","\u03B9");
		symbolMap.put("kappa","\u03BA");
		symbolMap.put("lambda","\u03BB");
		symbolMap.put("mu","\u03BC");
		symbolMap.put("nu","\u03BD");
		symbolMap.put("xi","\u03BE");
		symbolMap.put("o","\u03BF");
		symbolMap.put("pi","\u03C0");
		symbolMap.put("rho","\u03C1");
		symbolMap.put("varsigma","\u03C2");
		symbolMap.put("sigma","\u03C3");
		symbolMap.put("tau","\u03C4");
		symbolMap.put("upsilon","\u03C5");
		symbolMap.put("phi","\u03C6");
		symbolMap.put("chi","\u03C7");
		symbolMap.put("psi","\u03C8");
		symbolMap.put("omega","\u03C9");
		symbolMap.put("vartheta","\u03D1");
		symbolMap.put("varpi","\u03D6");

		// Equation Symbols
		symbolMap.put("approx","\u2248");
		symbolMap.put("neq","\u2260");
		symbolMap.put("equiv","\u2261");
		symbolMap.put("infty","\u221E");
		symbolMap.put("int","\u222B");
		symbolMap.put("ldots","\u2026");
		symbolMap.put("cdot","\u2219");
		symbolMap.put("surd","\u221A");
		symbolMap.put("leftarrow","\u2190");
		symbolMap.put("uparrow","\u2191");
		symbolMap.put("rightarrow","\u2192");
		symbolMap.put("downarrow","\u2193");
		symbolMap.put("leftrightarrow","\u2194");
		symbolMap.put("pm","\u00B1");
		symbolMap.put("wedge","\u2227");
		symbolMap.put("vee","\u2228");
		symbolMap.put("neg","\u00AC");
		symbolMap.put("div","\u00F7");
		symbolMap.put("copyright","\u00A9");
		symbolMap.put("leq","\u2264");
		symbolMap.put("geq","\u2265");
		symbolMap.put("nabla","\u2207");
		symbolMap.put("exists","\u2203");
		symbolMap.put("forall","\u2200");
		symbolMap.put("in","\u220A");
		symbolMap.put("ni","\u220D");
		symbolMap.put("propto","\u221D");
		symbolMap.put("prime","\u0384");
		symbolMap.put("Im","\u2111");
		symbolMap.put("Re","\u211C");
		symbolMap.put("oplus","\u2295");
		symbolMap.put("otimes","\u2297");
		symbolMap.put("sim","\u223C");
		symbolMap.put("subset","\u2282");
		symbolMap.put("supset","\u2283");
		symbolMap.put("subseteq","\u2286");
		symbolMap.put("supseteq","\u2287");
		symbolMap.put("cong","\u2245");
		symbolMap.put("times","\u00D7");
		symbolMap.put("oslash","\u00D8");
		symbolMap.put("0","\u00D8");
		symbolMap.put("wp","\u2118");
		symbolMap.put("partial","\u2202");

		// Other Symbols
		symbolMap.put("circ","\u00B0");
		symbolMap.put("bullet","\u2022");
		symbolMap.put("spadesuit","\u2660");
		symbolMap.put("clubsuit","\u2663");
		symbolMap.put("heartsuit","\u2665");
		symbolMap.put("diamondsuit","\u2666");
		symbolMap.put("lceil","\u250C");
		symbolMap.put("rceil","\u250D");
		symbolMap.put("lfloor","\u250E");
		symbolMap.put("rfloor","\u250F");
		symbolMap.put("cap","\u2229");
		symbolMap.put("cup","\u222A");
		symbolMap.put("perp","\u2534");
		symbolMap.put("langle","\u27E8");
		symbolMap.put("rangle","\u27E9");
		symbolMap.put("mid","\u2502");
		symbolMap.put("aleph","\uFB30");
		symbolMap.put("$","$");
		symbolMap.put("{","{");
		symbolMap.put("}","}");
	}
}