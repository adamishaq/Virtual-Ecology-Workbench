package VEW.Common.ParticleChooser;

public class Statistic implements Cloneable {

  private int variable;
  private String variableName;
  private int statistic;

  public Statistic(int _variable, String name, int _statistic) {
    variable = _variable;
    variableName = name;
    statistic = _statistic;
  }

  public Statistic() {
    variable = -1;
    variableName = "";
    statistic = -1;
  }

  public int getStatistic() {
    return statistic;
  }

  public void setStatistic(int _statistic) {
    statistic = _statistic;
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

  public String toString() {
    return variableName + " (" + ParticleStatistics.DESCRIPTIONS[statistic]
        + ")";
  }

  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

}
