package VEW.Scenario2;

import java.awt.geom.Point2D;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class TrackPosition implements java.io.Serializable {
  private double dLat, dLon;

  public TrackPosition(double Latitude, double Longitude) {
    dLon = Longitude;
    dLat = Latitude;
  }

  public TrackPosition(Point2D.Double Coordinates) {
    this(Coordinates.getY(), Coordinates.getX());//, false);
  }

  public Point2D.Double getCoordinates() { return new Point2D.Double(dLon, dLat); }
  public double getLat() { return dLat; }
  public double getLon() { return dLon; }
	public TrackPosition Replicate() { return new TrackPosition(dLat, dLon); }
  public String toString() { return "" + dLat + "," + dLon; }

  public static void WriteToFile(TrackPosition[] Track, String FilePath) {
    try {
      ObjectOutputStream OS = new ObjectOutputStream(new BufferedOutputStream(
                              new FileOutputStream(FilePath + "/Track.vso")));
      for(int i = 0; i < Track.length; i++) {
        OS.writeObject(Track[i]);
      }
      OS.flush();
      OS.close();
    }
    catch(Exception e) {
      System.err.println("Error writing track file");
      e.printStackTrace();
    }
  }
}