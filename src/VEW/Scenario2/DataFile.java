package VEW.Scenario2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.jgrib.GribFile;
import net.sourceforge.jgrib.GribRecord;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;

public class DataFile {
    
  public static double[][] ReadGrib(InputStream entryStream, int XDimension, int YDimension) {

    double[][] data = new double[1][1];

    try {

      GribFile gribFile = new GribFile(entryStream);
      gribFile.getGrids();

      GribRecord grecord; 
      grecord = gribFile.getRecord(1);
      data = new double[YDimension][XDimension];
      for (int lon = 0; lon < YDimension; lon++)
        for (int lat = 0; lat < XDimension; lat++)
          data[lon][lat] = grecord.getValue(lon, lat);
    } catch (NoValidGribException noGrib) {
      System.err.println("NoValidGribException : " + noGrib);
      noGrib.printStackTrace();
    } catch (NotSupportedException noSupport) {
      System.err.println("NotSupportedException : " + noSupport);
      noSupport.printStackTrace();
    } catch (FileNotFoundException noFileError) {
      System.err.println("FileNotFoundException : " + noFileError);
      noFileError.printStackTrace();
    } catch (IOException ioError) {
      System.err.println("IOException : " + ioError);
      ioError.printStackTrace();
    }

    return data;
  }
}
