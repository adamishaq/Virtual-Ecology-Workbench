package VEW.Common.ParticleChooser;

import gnu.trove.TLongArrayList;

public class Filter {

  private int variable;
  private int statistic;
  private String variableName;
  private int comparison;
  private double value;

  public static final int LESS = 0;
  public static final int LESS_EQUAL = 1;
  public static final int GREATER = 2;
  public static final int GREATER_EQUAL = 3;
  public static final int EQUAL = 4;

  public static final String[] DESCRIPTIONS = { "less than",
      "less than or equal to", "greater than", "greater than or equal to",
      "equal to" };

  private static final double EPSILON = 1e-10;

  public Filter(int _variable, String _variableName, int _statistic,
      int _comparison, double _value) {
    variable = _variable;
    variableName = _variableName;
    statistic = _statistic;
    comparison = _comparison;
    value = _value;
  }

  public Filter() {
    variable = -1;
    variableName = null;
    statistic = -1;
    comparison = -1;
    value = Double.NaN;
  }

  public long[] filter(long[] particles, ParticleStatistics statistics) {
    TLongArrayList newList = new TLongArrayList(particles.length);

    for (int i = 0; i < particles.length; i++)
      if (match(particles[i], statistics))
        newList.add(particles[i]);

    return newList.toNativeArray();
  }

  public boolean match(long particle, ParticleStatistics statistics) {
    double actual = statistics.getValue(particle, variable, statistic);

    switch (comparison) {
    case LESS:
      return actual < value;
    case LESS_EQUAL:
      return actual <= value;
    case GREATER:
      return actual > value;
    case GREATER_EQUAL:
      return actual >= value;
    case EQUAL:
      return Math.abs(actual - value) < EPSILON;
    default:
      System.err.println("Filter: Unexpected comparison - " + comparison);
      return false;
    }
  }

  public String toString() {
    return variableName + " (" + ParticleStatistics.DESCRIPTIONS[statistic] + ") is " + DESCRIPTIONS[comparison] + " " + value;
  }

  public int getComparison() {
    return comparison;
  }

  public void setComparison(int _comparison) {
    comparison = _comparison;
  }

  public int getStatistic() {
    return statistic;
  }

  public void setStatistic(int _statistic) {
    statistic = _statistic;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double _value) {
    value = _value;
  }

  public int getVariable() {
    return variable;
  }

  public void setVariable(int _variable) {
    variable = _variable;
  }

  public String getVariableName() {
    return variableName;
  }

  public void setVariableName(String _variableName) {
    variableName = _variableName;
  }

}
