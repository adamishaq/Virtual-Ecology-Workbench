/**
 * 
 */
package VEW.Lifespan;

class ParticleInfo {
  public double population;

  public double[] birthFactor;

  public double propOfInterest = 1;

  public int birthday;

  public ParticleInfo(int n) {
    birthFactor = new double[n];
    birthFactor[0] = 1;
  }
  
  public void copyThings(int day1, int day2) {
    birthFactor[day2] = birthFactor[day1];
  }
}