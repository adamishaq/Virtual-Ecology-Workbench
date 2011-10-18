package VEW.Analyser4;


import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.ProgressMonitor;

public class ContourPlotter {
  private ContourPoint[][] results;
  

  public ContourPlotter() { }

  public void plotContours(Analyser4 a4, double[][] data, double minimum, 
    double maximum, int noOfContours, long startTime, long endTime, 
    int startDepth, int endDepth, boolean fillContours, boolean drawContours, GraphPanel gp, double[] Depths, boolean invert, Plotter thePlotter) {

    GeneralPath[] graphLines = new GeneralPath[noOfContours];
    long startStep = (startTime-a4.startSimMillis)/a4.timeStepMillis;
    long endStep = (endTime-a4.startSimMillis)/a4.timeStepMillis;
    double stepSize = ((double)(endStep-startStep)/(double)data.length);
    if (minimum==maximum) maximum=minimum+0.001;
    if(minimum < maximum) {
      GenerateContours(data, minimum, maximum, noOfContours, startStep, startDepth, stepSize, Depths, graphLines, thePlotter);
    }
    GeneralPath ScaledPath[] = new GeneralPath[graphLines.length];
    for (int i=0; i<graphLines.length; i++) {
      AffineTransform at = new AffineTransform();
      ScaledPath[i] = (GeneralPath) graphLines[i].clone();
      at.setToScale(gp.getGraphWidth()/(1.0*(endStep-startStep)),(1.0*gp.getGraphHeight())/(1.0*(Depths[endDepth]-Depths[startDepth])));
      at.translate(-(startStep),0);
      ScaledPath[i].transform(at);
      if (!invert) {
        AffineTransform at2 = new AffineTransform();
        at2.scale(1,-1);
        ScaledPath[i].transform(at2);        
        AffineTransform at3 = new AffineTransform();
        at3.translate(0,gp.getGraphHeight());
        ScaledPath[i].transform(at3);
      }
      AffineTransform at4 = new AffineTransform();
      at4.translate(gp.getAxisWidth(),gp.getAxisHeight());
      ScaledPath[i].transform(at4);
      
    }
    gp.setContourLines(ScaledPath,fillContours,drawContours);
    gp.repaint();
  }


  protected void GenerateContours(double[][] data, double Minimum, double Maximum, int Contours, long StartStep, int StartLayer, double StepSize, double[] Depths, GeneralPath[] graphLines, Plotter thePlotter) {
    
    boolean[] PathMoved = new boolean[Contours];
    double ContourGap = (Maximum - Minimum) / (Contours);// + 1);
    double ContourHeight[] = new double[Contours];
    ContourHeight[0] = Minimum;// + ContourGap;
    PathMoved[0] = false;
    for (int i=1; i<Contours; i++) {
      PathMoved[i] = false;
      ContourHeight[i] = ContourHeight[i - 1] + ContourGap;
    }
    int IBoundary = data.length - 1;
    int NumberOfBars = (data[1].length - 1) * data.length;
    NumberOfBars    += data[1].length * (data.length - 1);
    ProgressMonitor pm = new ProgressMonitor(thePlotter.a4,"Plotting contours","",0,1000);
    pm.setNote("Attempting to get memory - "+NumberOfBars*Contours+" points");
    results = new ContourPoint[NumberOfBars][Contours];
    ContourCalculator CC = new ContourCalculator();
    PathFinder PF = new PathFinder();
    
    pm.setMinimum(0);
    pm.setMaximum(IBoundary);
        
    for (int i=1; i<IBoundary; i++) {
      CC.calculate(data, i, data[i].length, Contours, i == (IBoundary - 1), ContourHeight[0], ContourGap);
      pm.setNote("Calculating "+i+"/"+IBoundary);
      pm.setProgress(i);
      if (pm.isCanceled()) {
        pm.close();
        Plotter.cancelPlot=true;
        return;
      }
    }
    pm.close();
    pm = new ProgressMonitor(thePlotter.a4,"Finding paths","",0,Contours);
    for (int i=0; i<Contours; i++) {
      PF.findPath(Depths, i, data[1].length, data.length - 1, StartStep, (float)StepSize, StartLayer, graphLines);
      pm.setNote("Finding Paths "+i+"/"+Contours);
      pm.setProgress(i);
      if (pm.isCanceled()) {
        pm.close();
        Plotter.cancelPlot=true;
        return;
      }
    }
    pm.close();
  }

  private class ContourCalculator {
    int col,colLength,numContours;
    double lowContour,contourGap;
    boolean complete = false;
    boolean lastCol;
    int dataFormat;
    double[][] data;

    public ContourCalculator() {}
    
    public void calculate(double[][] _data, int _col, int _colLength, int _numContours, boolean _lastCol, double _lowContour, double _contourGap) {
      //super(tg, "Column " + col);
      data = _data;
      col = _col;
      colLength = _colLength;
      lowContour = _lowContour;
      contourGap = _contourGap;
      lastCol = _lastCol;
      numContours = _numContours;
      run();
    }

    public static final int lPos = 0;
    public static final int rPos = 1;

    public static final int HitTop = 0;
    public static final int HitBottom = 1;
    public static final int HitSaddleTop = 2;
    public static final int HitSaddleBottom = 3;


    public void run() {
      
      int elementAccessor = (colLength * 2) - 1;
      int startAccessor = elementAccessor * (col - 1);
      if (lastCol) elementAccessor--;

      double topLeft  = data[col][0];
      double topRight = data[col + 1][0];
      double bottomLeft, bottomRight;
      
    
      for(int i = 1; i < colLength; i++) {
        bottomLeft  = data[col][i];
        bottomRight = data[col + 1][i];
        int TopFlag,BottomFlag;
        if (((topLeft>topRight) && (topLeft>bottomLeft) && (bottomRight>topRight) && (bottomRight>bottomLeft)) ||
            ((topLeft<topRight) && (topLeft<bottomLeft) && (bottomRight<topRight) && (bottomRight<bottomLeft))) {
          TopFlag = HitSaddleTop; 
          BottomFlag = HitSaddleBottom;
        } else {
          TopFlag = HitTop;
          BottomFlag = HitBottom;
        }
        getBar(topRight,topLeft,startAccessor,TopFlag,bottomRight,bottomLeft);
        getBar(topLeft,bottomLeft,startAccessor+1,TopFlag,topRight,bottomRight);
        getBar(bottomRight,topRight,startAccessor+elementAccessor+1,BottomFlag,bottomLeft,topLeft);
        getBar(bottomLeft,bottomRight,startAccessor+2,BottomFlag,topLeft,topRight);

        topLeft = bottomLeft;
        topRight = bottomRight;
        startAccessor += 2;
        if(lastCol) elementAccessor--;
      }
    }

    private void AddOrNull(double axisOffset, int hitDir, int moveDir, int stopDir, int bn, int j, boolean top) {
      if (results[bn][j]==null) results[bn][j]=new ContourPoint(axisOffset, hitDir, moveDir, stopDir);
      else if (top) results[bn][j].addHitTop(moveDir);
      else results[bn][j].addHitBottom(moveDir);
    }
    
    
    private void getBar(double leftHeight, double rightHeight, int barNumber, int hitFrom,double spareHeight, double spareHeight2) {
      int j = 0;
      if(leftHeight == rightHeight) {
        double i = lowContour + contourGap;
        while(i < rightHeight) {
          i += contourGap;
          j ++;
        }
        
        if (i==rightHeight) {
          if (hitFrom==HitSaddleTop || hitFrom==HitTop)
            AddOrNull(0,ContourPoint.HitTop, ContourPoint.MoveLeft, ContourPoint.StopClock,barNumber,j,true);
          else
            AddOrNull(0,ContourPoint.HitBottom,ContourPoint.MoveRight,ContourPoint.StopAnti,barNumber,j,false);
        }
      } else if (leftHeight>rightHeight) {
        double i = lowContour + contourGap;
        double heightGap = leftHeight - rightHeight;
        while (i<rightHeight) {
          i += contourGap;
          j ++;
        }
        double offset = (i - rightHeight) / heightGap;
        double gapOffset = contourGap / heightGap;
        // add offset to return vector
        if (hitFrom==HitSaddleTop) {
          while (i<=leftHeight && j<numContours-1) {
            if (i<=spareHeight2)
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveRight,ContourPoint.StopClock,barNumber,j,true);
            else
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveLeft,ContourPoint.StopClock,barNumber,j,true);
            i += contourGap;
            j++;
            offset += gapOffset;
          }
        } else if (hitFrom==HitSaddleBottom) {
          while (i<=leftHeight && j<numContours-1) {
            if (i<=spareHeight2)
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveRight,ContourPoint.StopClock,barNumber,j,false);
            else
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveLeft,ContourPoint.StopClock,barNumber,j,false);
            i += contourGap;
            j++;
            offset += gapOffset;
          }
        } else if (hitFrom==HitTop) {
          while (i<=leftHeight && j<numContours-1) {
            if (i>=spareHeight)
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveLeft,ContourPoint.StopClock,barNumber,j,true);
            else if (i<=spareHeight2)
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveRight,ContourPoint.StopClock,barNumber,j,true);
            else
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveAcross,ContourPoint.StopClock,barNumber,j,true);
            i += contourGap;
            j++;
            offset += gapOffset;
          }
        } else if (hitFrom==HitBottom) {
          while(i<=leftHeight && j<numContours-1) {
            if (i>=spareHeight)
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveLeft,ContourPoint.StopClock,barNumber,j,false);
            else if (i<=spareHeight2)
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveRight,ContourPoint.StopClock,barNumber,j,false);
            else
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveAcross,ContourPoint.StopClock,barNumber,j,false);
            i += contourGap;
            j++;
            offset += gapOffset;
          }
        }
      } else {
        double i = lowContour + contourGap;
        double heightGap = rightHeight - leftHeight;
        while (i<leftHeight) {
          i += contourGap;
          j++;
        }
        double offset = 1 - ((i - leftHeight) / heightGap);
        double gapOffset = contourGap / heightGap;
        // add offset to return vector
        if (hitFrom==HitSaddleTop) {
          while (i<=rightHeight && j<numContours-1) {
            if (i<=spareHeight)
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveLeft,ContourPoint.StopAnti,barNumber,j,true);
            else
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveRight,ContourPoint.StopAnti,barNumber,j,true);
            i += contourGap;
            j++;
            offset -= gapOffset;
          }
        } else if (hitFrom==HitSaddleBottom) {
          while (i<=rightHeight && j<numContours-1) {
            if (i<=spareHeight)
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveLeft,ContourPoint.StopAnti,barNumber,j,false);
            else
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveRight,ContourPoint.StopAnti,barNumber,j,false);
            i += contourGap;
            j++;
            offset -= gapOffset;
          }
        } else if (hitFrom==HitTop) {
          while (i<=rightHeight && j<numContours-1) {
            if (i<=spareHeight)
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveLeft,ContourPoint.StopAnti,barNumber,j,true);
            else if (i>=spareHeight2)
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveRight,ContourPoint.StopAnti,barNumber,j,true);
            else 
              AddOrNull(offset,ContourPoint.HitTop,ContourPoint.MoveAcross,ContourPoint.StopAnti,barNumber,j,true);
            i += contourGap;
            j++;
            offset -= gapOffset;
          }
        } else if (hitFrom==HitBottom) {
          while (i<=rightHeight && j<numContours-1) {
            if (i<=spareHeight)
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveLeft,ContourPoint.StopAnti,barNumber,j,false);
            else if (i>=spareHeight2)
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveRight,ContourPoint.StopAnti,barNumber,j,false);
            else
              AddOrNull(1 - offset,ContourPoint.HitBottom,ContourPoint.MoveAcross,ContourPoint.StopAnti,barNumber,j,false);
            i += contourGap;
            j++;
            offset -= gapOffset;
          }
        }
      }
    }
  }

  private class ContourPoint {
    
    private float axisOffset;
    private int upDirection = -1;
    private int downDirection = -1;
  //  private int dirs = 1;

    public static final int HitTop = 0;
    public static final int HitBottom = 1;

    public static final int StopClock = -3;
    public static final int StopAnti = -2;
    public static final int MoveNowhere = -1;
    public static final int MoveLeft = 0;
    public static final int MoveRight = 1;
    public static final int MoveAcross = 2;

    public ContourPoint(double _axisOffset, int hitDirection, int moveDirection, int stopDirection) {
      axisOffset = (float)_axisOffset;
      if (hitDirection==HitTop) {
        downDirection = moveDirection;
        upDirection = stopDirection;
      } else {
        upDirection = moveDirection;
        downDirection = stopDirection;
      }
    }

    public int hitBottom(boolean inRun) {
      int tempDir = upDirection;
      upDirection = MoveNowhere;
      if (inRun) downDirection = MoveNowhere;
      return tempDir;
    }

    public int hitTop(boolean inRun) {
      int tempDir = downDirection;
      downDirection = MoveNowhere;
      if (inRun) upDirection = MoveNowhere;
      return tempDir;
    }

    public void addHitTop(int dir) {
      downDirection = dir;
     // dirs++;
    }

    public void addHitBottom(int dir) {
      upDirection = dir;
    //  dirs++;
    }

    public float getOffset() {
      return axisOffset;
    }

    public String toString() {
      return "Offset: " + axisOffset + "\nUp direction: " + upDirection + "\nDown direction: " + downDirection;
    }
  }

  private class PathFinder extends Thread {
    boolean facingForward, facingDown;
    int numberOfBars, numberOfRows, numberOfColumns;
    int contourNumber, lastBarChecked, nextBar, currentBar;
    int lastColumn, columnBars, nextDir;
    int lastDoubleColumn;
    float minX, minY, xStep, yStep;
    GeneralPath contourPath = new GeneralPath();
    double[] Depths;
    GeneralPath[] graphLines;
    
    public PathFinder() {}
    
    public void findPath(double[] _Depths, int _contourNumber, int _numberOfRows, int _numberOfColumns, float _minX, float _xStep, float _minY, GeneralPath[] _g) {
      lastBarChecked = 0;
      nextDir = 0;
      nextBar = 0;
      currentBar = 0;
      facingForward = false;
      facingDown = false;
      Depths = _Depths;
      contourNumber = _contourNumber;
      numberOfColumns = _numberOfColumns;
      numberOfRows = _numberOfRows;
      minX = _minX;
      minY = _minY;
      xStep = _xStep;
      yStep = 0;
      numberOfBars = (2 * numberOfColumns * numberOfRows) - numberOfColumns - numberOfRows;
      columnBars = (numberOfRows * 2) - 1;
      lastColumn = numberOfBars - numberOfRows + 1;
      lastDoubleColumn = lastColumn - columnBars;
      graphLines = _g;
      contourPath = new GeneralPath();      
      run();
    }

    public void run() {
      for (nextBar=0; nextBar<numberOfBars; nextBar++) {
        int i = nextBar % columnBars;
        i %= 2;
        if (results[nextBar][contourNumber] != null) {
          facingDown = (i == 0);
          facingForward = true;
          followPath();
        }
      }
      graphLines[contourNumber] = contourPath;
    }

    // Starts at the current point and follows the contour to it's conclusion
    private void followPath() {
      currentBar = nextBar;
      //int rowBars = (numberOfColumns * 2) - 1;
      double xCoord = minX + (((1.0*currentBar) / (1.0*columnBars)) * xStep);
      double yCoord;
      int yOffset;
      if(currentBar < lastColumn) 
        yOffset = (int)(minY + ((currentBar % columnBars) / 2));
      else
        yOffset = (int)(minY + (currentBar - numberOfBars + numberOfRows - 1));
      yCoord = Depths[yOffset];
      if(facingDown)
        xCoord += results[currentBar][contourNumber].getOffset() * xStep;
      else
        yCoord += (1 - results[currentBar][contourNumber].getOffset()) * (Depths[yOffset + 1] - Depths[yOffset]);
      contourPath.moveTo((float)xCoord, (float)yCoord);
      if(facingForward)
        nextDir = results[currentBar][contourNumber].hitTop(false);
      else
        nextDir = results[currentBar][contourNumber].hitBottom(false);
      while(nextDir!=ContourPoint.MoveNowhere) {
        if (nextDir==ContourPoint.StopClock || nextDir==ContourPoint.StopAnti)
          closePath();
        else if (facingDown && facingForward) {
          if (nextDir == ContourPoint.MoveLeft) {
            if(currentBar < lastDoubleColumn) {
              currentBar += columnBars + 1;
            } else 
              currentBar = (currentBar + numberOfBars - numberOfRows + 2 + columnBars) / 2;
            facingDown = false;
          } else if (nextDir == ContourPoint.MoveAcross)
            currentBar += 2;
          else {
            currentBar ++;
            facingDown = false;
            facingForward = false;
          }
        } else if (!facingDown && facingForward) {
          if (nextDir==ContourPoint.MoveLeft) {
            currentBar--;
            facingDown = true;
            facingForward = false;
          } else if (nextDir == ContourPoint.MoveAcross) {
            if (currentBar<lastDoubleColumn)
              currentBar += columnBars;
            else
              currentBar = (currentBar + numberOfBars - numberOfRows + 1 + columnBars) / 2;
            
          }
          else {
            currentBar++;
            facingDown = true;
          }
        }
        else if (facingDown && !facingForward) {
          if (nextDir == ContourPoint.MoveLeft) {
            currentBar--;
            facingDown = false;
          } else if(nextDir == ContourPoint.MoveAcross)
            currentBar -= 2;
          else {
            if(currentBar < lastDoubleColumn)
              currentBar += columnBars - 1;
            else
              currentBar = (currentBar + numberOfBars - numberOfRows + columnBars) / 2;
            facingDown = false;
            facingForward = true;
          }
        } else {
          int columnShift = 0;
          if (currentBar < lastColumn)
            columnShift = columnBars;
          else
            currentBar = (2 * currentBar) - numberOfBars + numberOfRows - columnBars; //+ (3 * numberOfRows) + 2 + columnBars - numberOfBars;
          if(nextDir == ContourPoint.MoveLeft) {
            currentBar -= (columnShift - 1);
            facingDown = true;
            facingForward = true;
          } else if(nextDir == ContourPoint.MoveAcross)
            currentBar -= columnShift;
          else {
            currentBar -= (columnShift + 1);
            facingDown = true;
          }
        }
        xCoord = minX + (((1.0*currentBar) / (1.0*columnBars)) * xStep);
        if(currentBar < lastColumn) yOffset = (int)(minY + ((currentBar % columnBars) / 2));
        else yOffset = (int)(minY + currentBar - numberOfBars + numberOfRows - 1);
        yCoord = Depths[yOffset];
        if (currentBar >= numberOfBars) System.out.println("ARRAY OUT OF BOUNDS ON CONTOUR " + contourNumber + " " + currentBar);
        if (results[currentBar][contourNumber] == null) System.out.println("NULL EXCEPTION OCCURED ON CONTOUR " + contourNumber);
        else {
          if (facingDown) xCoord += results[currentBar][contourNumber].getOffset() * xStep;
          else yCoord += (1 - results[currentBar][contourNumber].getOffset()) * (Depths[yOffset + 1] - Depths[yOffset]);
          contourPath.lineTo((float)xCoord, (float)yCoord);
          if (facingForward) nextDir = results[currentBar][contourNumber].hitTop(true);
          else nextDir = results[currentBar][contourNumber].hitBottom(true);
        }
      }
      contourPath.closePath();
    }

    public void closePath() {
      int side;
      int[] turnAt = new int[4];
      int[] turnTo = new int[4];
      int[] moveBy = new int[4];
      double xCoord;
      double yCoord;
      moveBy[0] = columnBars;
      moveBy[1] = 1;
      moveBy[2] = (-1) * columnBars;
      moveBy[3] = -2;
      turnAt[0] = numberOfBars - numberOfRows - columnBars + 1;
      turnAt[1] = numberOfBars - 1;
      turnAt[2] = columnBars - 1;
      turnAt[3] = 1;
      turnTo[0] = 0;
      turnTo[1] = numberOfBars - numberOfRows + 1;
      turnTo[2] = numberOfBars - numberOfRows;
      turnTo[3] = columnBars - 2;
      if(currentBar >= lastColumn) side = 1;
      else if(currentBar % columnBars == 0) side = 0;
      else if(facingForward) side = 2;
      else side = 3;
      if(nextDir == ContourPoint.StopClock) {
        while(true) {
          if(currentBar == turnAt[side]) {
            if (side==0) {
              yCoord = Depths[(int)minY];
              xCoord = minX + (numberOfColumns - 1) * xStep;
            } else if (side==1) {
              yCoord = Depths[(int)minY + numberOfRows - 1];
              xCoord = minX + (numberOfColumns - 1) * xStep;
            } else if (side==2) {
              yCoord = Depths[(int)minY + numberOfRows - 1];
              xCoord = minX;
            } else {
              yCoord = Depths[(int)minY];
              xCoord = minX;
            }
            contourPath.lineTo((float)xCoord, (float)yCoord);
            side = (side + 1) % 4;
            currentBar = turnTo[side];
          } else 
            currentBar += moveBy[side];
          if (results[currentBar][contourNumber] != null) break;
        }
      } else {
        while(true) {
          if(currentBar == turnTo[side]) {
            if (side==0) {
              yCoord = Depths[(int)minY];
              xCoord = minX;
            } else if (side==1) {
              yCoord = Depths[(int)minY];
              xCoord = minX + (numberOfColumns - 1) * xStep;
            } else if (side==2) {
              yCoord = Depths[(int)minY + numberOfRows - 1];
              xCoord = minX + (numberOfColumns - 1) * xStep;
            } else {
              yCoord = Depths[(int)minY + numberOfRows - 1];
              xCoord = minX;
            }
            contourPath.lineTo((float)xCoord, (float)yCoord);
            side = (side + 3) % 4;
            currentBar = turnAt[side];
          } else
            currentBar -= moveBy[side];
          if (results[currentBar][contourNumber] != null) break;
        }
      }
      
      if (side==0) {
        facingForward = true;
        facingDown = true;
      } else if (side==1) {
        facingForward = false;
        facingDown = false;
      } else if (side==2) {
        facingForward = false;
        facingDown = true;
      } else if (side==3) {
        facingForward = true;
        facingDown = false;
      }
    }

    // Returns the path containing all the contours
    public GeneralPath getPath() {
      return contourPath;
    }
  }
}