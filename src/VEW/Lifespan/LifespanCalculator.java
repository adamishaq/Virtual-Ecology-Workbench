package VEW.Lifespan;

import gnu.trove.TLongObjectHashMap;
import gnu.trove.TObjectProcedure;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import VEW.Common.RealDataInputStream;
import VEW.Common.RealDataOutputStream;
import VEW.Common.StringTools;
import VEW.Common.Caching.ObjectCache;
import VEW.Common.ModelRun.FGDataSource;
import VEW.Common.ModelRun.ModelRun;
import VEW.Common.Progress.CompletionListener;
import VEW.Common.Progress.Job;
import VEW.Common.Progress.ProgressListener;
//import VEW.Common.XML.XMLTag;

public class LifespanCalculator {

  public static final int CREATE = 0;
  public static final int PCHANGEPARENT = 1;
  public static final int PCHANGECHILD = 2;
  public static final int SPLIT = 3;
  public static final int MERGE = 4;
  public static final int REMOVAL = 5;
  public static final int INGEST = 6;
  public static final int DIVIDE = 7;
  public static final int CHANGE = 8;
  public static final int DEATH_BY_INGESTION = 0;
  public static final int DEATH_BY_REMOVAL = 1;
  private static final int PROBALIVE_FUTURE = 100;
  
  private static final DocumentFactory df = DocumentFactory.getInstance();
  
  private int dayLength;
  private boolean useFloats;
  private int recordSkip;
  private boolean cancelled;
  private ModelRun theModelRun;
  private final CopyThings copyThings = new CopyThings();
  private final SumPopulations sumPopulations = new SumPopulations();

  public LifespanCalculator(ModelRun modelRun) {
    theModelRun = modelRun;
//    int secondsPerTimestep = Integer.parseInt(theModelRun.getModel().getValue(
//    "scenario/column/steplength"));
    int secondsPerTimestep = theModelRun.getModel().numberValueOf("/model/scenario/column/steplength").intValue();
    dayLength = 86400 / secondsPerTimestep;
    useFloats = theModelRun.getDataFormats().valueOf("/dataformat/format").equals("float");
    recordSkip = useFloats ? 29 : 37;
  }

  public LifespanCalculator(File directory) {
    this(new ModelRun(directory));
  }

  public LifespanCalculator(String directory) {
    this(new File(directory));
  }

  public void calculateLifespan(String functionalGroup, int startTimestep,
      int endTimestep, boolean[] liveStages, boolean[] deadStages) {
    calculateLifespan(theModelRun.getFunctionalGroupByName(functionalGroup),
        startTimestep, endTimestep, liveStages, deadStages, null, null);
  }

  public void calculateLifespan(FGDataSource functionalGroup,
      int startTimestep, int endTimestep, boolean[] liveStages,
      boolean[] deadStages, ProgressListener progress,
      CompletionListener completion) {
    long startTime = System.currentTimeMillis();
    System.out.println("Starting lifespan calculation at " + new Date(startTime));

    RealDataInputStream in;
    try {
      in = functionalGroup.getLifespanInputStream();
    } catch (IOException e) {
      e.printStackTrace();
      if (completion != null)
        completion.jobCancelled();
      return;
    }

    int days = ((endTimestep - startTimestep) / dayLength) + 1;
    endTimestep = startTimestep + (days - 1) * dayLength;
    int currentTimestep = 0, lastTimestep = -1;
    int endOfDay;
    int today = 0;
    int k = 0;
    boolean eofOK = true;

    ObjectCache particleCache;
    try {
      particleCache = new ObjectCache(ParticleInfo.class,
          new Class[] { Integer.TYPE }, new Object[] { new Integer(days) });
    } catch (SecurityException e2) {
      e2.printStackTrace();
      if (completion != null)
        completion.jobCancelled();
      return;
    } catch (NoSuchMethodException e2) {
      e2.printStackTrace();
      if (completion != null)
        completion.jobCancelled();
      return;
    }

    String[] reasons = theModelRun.getDeathReasons();

    // Outputs and other variables used in the calculation
    TLongObjectHashMap particleMap = new TLongObjectHashMap(12000);
    double[] births = new double[days];
    double[][] deaths = new double[days][days];
    double[] actualDeaths = new double[days];
    double[] cells = new double[days];
    double[][] probdead = new double[days][days];
    boolean[][] dead = new boolean[days][days];
    double[][] allDeaths = new double[days][days];
    double[] lifespan = new double[days];
    double[][] deathsByReason = new double[days][reasons.length + 2];

    // Temporary variables to reduce unnecessary array accesses
    double cellsToday;
    double actualDeathsToday;
    double birthsToday;
    double[] deathsToday;
    boolean[] deadDay;
    double lifespanDay;
    double allDeathsDayToday;
    double[] deathsByReasonToday;

    // Parameters read from file
    byte eventType;
    int reason;
    long id1, id2;
    double cellCount1, cellCount2;
    int stage;

    // Temporary variables
    ParticleInfo particle2, particle1;
    double interestingDeaths, scaledDeaths;
    double size1, size2, interestingSize, totalSize;

    try {
      // Find starting timestep
      if (progress != null) {
        progress.setMessage("Calculating lifespan for " + functionalGroup);
        progress.setNote("Initializing...");
        progress.setMinimum(0);
        progress.setMaximum(endTimestep);
      }
      currentTimestep = in.readInt();
      lastTimestep = currentTimestep;
      while (currentTimestep < startTimestep) {
        in.skipBytes(recordSkip);
        currentTimestep = in.readInt();
        if (currentTimestep != lastTimestep && progress != null) {
          progress.setProgress(currentTimestep);
          lastTimestep = currentTimestep;
        }
        k++;
      }

      if (progress != null) {
        //progress.setMinimum(startTimestep);
        //progress.setMaximum(endTimestep);
        progress.setProgress(startTimestep);
        progress.setNote("Day " + today + " of " + (days - 1) + "; timestep "
            + currentTimestep + " of {" + startTimestep + ".." + endTimestep
            + "}");
      }

      // Initialize with first timestep's ingestion events
      cellsToday = 0;
      while (currentTimestep <= startTimestep) {
        eventType = in.readByte();
        if (eventType == INGEST) {
          in.readInt(); // Reason
          id1 = in.readLong(); // ID 1
          in.readLong(); // ID 2
          cellCount1 = in.readReal();
          cellCount2 = in.readReal();
          cellsToday += cellCount1 - cellCount2;

          particle1 = (ParticleInfo) particleCache.getInstance();
          particle1.birthday = 0;
          particle1.birthFactor[0] = 1;
          particle1.population = cellCount1;
          particle1.propOfInterest = 1;

          particleMap.put(id1, particle1);
        } else {
          in.skipBytes(recordSkip - 1);
        }

        currentTimestep = in.readInt();
        k++;
      }
      cells[0] = cellsToday;

      today = 1;
      endOfDay = startTimestep + dayLength;

      // Loop over every day in time period
      while (currentTimestep <= endTimestep) {
        if (currentTimestep < lastTimestep)
          System.err.println("Current timestep: " + currentTimestep
              + "; last timestep: " + lastTimestep
              + "; record no.: " + k);
        if (progress != null)
          progress.setNote("Day " + today + " of " + (days - 1) + "; timestep "
              + currentTimestep + " of {" + startTimestep + ".." + endTimestep
              + "}");

        copyThings.setToday(today);
        particleMap.forEachValue(copyThings);

        // Temporary - to reduce array access
        actualDeathsToday = 0;
        birthsToday = 0;
        deathsToday = deaths[today];
        deathsByReasonToday = deathsByReason[today];

        // Loop over every event today
        while (currentTimestep <= endOfDay) {
          synchronized (this) {
            if (cancelled) {
              completion.jobCancelled();
              return;
            }
          }

          // Read event
          eofOK = false;
          eventType = in.readByte();
          reason = in.readInt();
          id1 = in.readLong();
          id2 = in.readLong();
          cellCount1 = in.readReal();
          cellCount2 = in.readReal();
          eofOK = true;

          // Process event
          switch (eventType) {
          case INGEST:
            particle1 = (ParticleInfo) particleMap.get(id1);
            if (particle1 == null)
              break;

            interestingDeaths = cellCount2 * particle1.propOfInterest;
            scaledDeaths = interestingDeaths / particle1.birthFactor[today];
            for (int day = particle1.birthday; day <= today; day++)
              deathsToday[day] += scaledDeaths * particle1.birthFactor[day];
            actualDeathsToday += interestingDeaths;
            deathsByReasonToday[DEATH_BY_INGESTION] += interestingDeaths;
            particle1.population = cellCount1 - cellCount2;
            break;

          case DIVIDE:
            particle1 = (ParticleInfo) particleMap.get(id1);
            if (particle1 == null)
              break;

            birthsToday += (cellCount2 - cellCount1) * particle1.propOfInterest;
            particle1.birthFactor[today] *= cellCount2 / cellCount1;
            break;

          case PCHANGEPARENT:
            stage = (int) cellCount2;

            if (deadStages[stage]) {
              particle1 = (ParticleInfo) particleMap.get(id1);
              if (particle1 == null)
                break;

              interestingDeaths = cellCount1 * particle1.propOfInterest;
              scaledDeaths = interestingDeaths / particle1.birthFactor[today];
              for (int day = particle1.birthday; day <= today; day++)
                deathsToday[day] += scaledDeaths * particle1.birthFactor[day];
              actualDeathsToday += interestingDeaths;
              deathsByReasonToday[reason + 2] += interestingDeaths;

              particle1.population -= cellCount1;
            }
            break;

          case PCHANGECHILD:
            stage = (int) cellCount2;
            if (liveStages[stage]) {
              particle1 = (ParticleInfo) particleMap.get(id1);
              if (particle1 == null)
                break;

              particle2 = (ParticleInfo) particleCache.getInstance();
              particle2.population = cellCount1;
              particle2.propOfInterest = particle1.propOfInterest;
              System.arraycopy(particle1.birthFactor, particle1.birthday,
                  particle2.birthFactor, particle1.birthday, today
                      - particle1.birthday + 1);
              particle2.birthday = particle1.birthday;

              particleMap.put(id2, particle2);
            }
            break;

          case CHANGE:
            stage = (int) cellCount2;
            if (deadStages[stage]) {
              particle1 = (ParticleInfo) particleMap.remove(id1);
              if (particle1 == null)
                break;

              interestingDeaths = cellCount1 * particle1.propOfInterest;
              scaledDeaths = interestingDeaths / particle1.birthFactor[today];
              for (int day = particle1.birthday; day <= today; day++)
                deathsToday[day] += scaledDeaths * particle1.birthFactor[day];
              actualDeathsToday += interestingDeaths;
              deathsByReasonToday[reason + 2] += interestingDeaths;

              particleCache.finishedWith(particle1);
            }
            break;

          case SPLIT:
            particle1 = (ParticleInfo) particleMap.get(id1);
            if (particle1 == null)
              break;

            particle2 = (ParticleInfo) particleCache.getInstance();
            particle1.population = cellCount1;
            particle2.population = cellCount1;
            particle2.birthday = particle1.birthday;
            particle2.propOfInterest = particle1.propOfInterest;
            System.arraycopy(particle1.birthFactor, particle1.birthday,
                particle2.birthFactor, particle1.birthday, today
                    - particle1.birthday + 1);

            particleMap.put(id2, particle2);
            break;

          case MERGE:
            particle1 = (ParticleInfo) particleMap.remove(id1);
            particle2 = (ParticleInfo) particleMap.get(id2);

            if (particle1 != null) {
              if (particle2 != null) {

                size1 = cellCount1 * particle1.propOfInterest;
                size2 = cellCount2 * particle2.propOfInterest;
                interestingSize = size1 + size2;
                totalSize = cellCount1 + cellCount2;

                if (particle2.birthday < particle1.birthday) {
                  for (int day = particle1.birthday; day <= today; day++)
                    particle2.birthFactor[day] = (size1
                        * particle1.birthFactor[day] + size2
                        * particle2.birthFactor[day])
                        / interestingSize;
                } else {
                  System.arraycopy(particle1.birthFactor, particle1.birthday,
                      particle2.birthFactor, particle1.birthday,
                      particle2.birthday - particle1.birthday);
                  for (int day = particle2.birthday; day <= today; day++)
                    particle2.birthFactor[day] = (size1
                        * particle1.birthFactor[day] + size2
                        * particle2.birthFactor[day])
                        / interestingSize;
                  particle2.birthday = particle1.birthday;
                }
                particle2.population = totalSize;
                particle2.propOfInterest = interestingSize / totalSize;
              } else {
                particle2 = (ParticleInfo) particleCache.getInstance();
                particle2.population = cellCount1 + cellCount2;
                particle2.birthday = particle1.birthday;
                particle2.birthFactor = particle1.birthFactor;
                particle2.propOfInterest = cellCount1 / particle2.population;
                particleMap.put(id2, particle2);
              }

              particleCache.finishedWith(particle1);
            } else if (particle2 != null) {
              // Only particle 2 in set of interest
              particle2.population = cellCount1 + cellCount2;
              particle2.propOfInterest = (cellCount2 * particle2.propOfInterest)
                  / particle2.population;
            }
            break;

          case CREATE:
            stage = (int) cellCount2;
            if (!particleMap.containsKey(id1))
              break;

            if (liveStages[stage]) {
              particle2 = (ParticleInfo) particleCache.getInstance();
              particle2.population = cellCount1;
              particle2.propOfInterest = 1;
              particle2.birthFactor[today] = 1;
              particle2.birthday = today;
              particleMap.put(id2, particle2);

              birthsToday += cellCount1;
            }
            break;

          case REMOVAL:
            particle1 = (ParticleInfo) particleMap.remove(id1);
            if (particle1 == null)
              break;

            interestingDeaths = cellCount1 * particle1.propOfInterest;
            scaledDeaths = interestingDeaths / particle1.birthFactor[today];
            for (int day = particle1.birthday; day <= today; day++)
              deathsToday[day] += scaledDeaths * particle1.birthFactor[day];
            actualDeathsToday += interestingDeaths;
            deathsByReasonToday[DEATH_BY_REMOVAL] += interestingDeaths;

            particleCache.finishedWith(particle1);
            break;

          default:
            System.err.println("Unexpected event type: " + eventType);
          }

          if (currentTimestep != lastTimestep) {
            lastTimestep = currentTimestep;
            if (progress != null) {
              // progress.setNote("Day " + today + " of " + (days - 1) + ";
              // timestep " + currentTimestep + " of {" + startTimestep + ".." +
              // endTimestep + "}");
              progress.setProgress(currentTimestep);
            }
          }

          currentTimestep = in.readInt();
          k++;
        } // End of loop over each event today

        sumPopulations.sum = 0;
        particleMap.forEachValue(sumPopulations);
        cells[today] = sumPopulations.sum;

        // Temporary - to reduce array access
        actualDeaths[today] = actualDeathsToday;
        births[today] = birthsToday;

        // Backtrack and update probdead and lifespan from start of time
        // up to today
        for (int day = 1; day <= today; day++) {
          allDeathsDayToday = 0;
          lifespanDay = lifespan[day];

          if (cells[day - 1] == 0) {
            for (int i = day; i <= today; i++)
              probdead[i][today] = 1;
            break;
          } else if (dead[day][today]) {
            probdead[day][today] = 1;
            continue;
          } else {
            for (int i = day; i <= today; i++)
              allDeathsDayToday += deaths[i][day];

            if (allDeathsDayToday < cells[day - 1]) {
              lifespanDay += (today - day) * deathsToday[day] / cells[day - 1];
              probdead[day][today] = Math.min(allDeathsDayToday
                  / cells[day - 1], 1);
            } else {
              lifespanDay += (today - day)
                  * (cells[day - 1] - allDeaths[day][today - 1])
                  / cells[day - 1];
              probdead[day][today] = 1;
              deadDay = dead[day];
              for (int i = today; i < days; i++)
                deadDay[i] = true;
            }

          }

          allDeaths[day][today] = allDeathsDayToday;
          lifespan[day] = lifespanDay;
        }

        today++;
        endOfDay += dayLength;
      }

      System.out.println("Finished at timestep " + currentTimestep
          + "; record " + k);
      long endTime = System.currentTimeMillis();
      System.out.println("Finish time: " + new Date(endTime));
      System.out.println("Execution took " + ((endTime - startTime) / 1000f)
          + " seconds");

      // writeToFileTSV(functionalGroup, lifespan, actualDeaths, births, cells,
      // probdead);
      writeToFileAnalyser(functionalGroup, lifespan, actualDeaths, births,
          cells, deathsByReason, probdead, startTimestep, endTimestep);

      if (completion != null)
        completion.jobCompleted(null);
    } catch (EOFException e) {
      if (eofOK) {
        System.out.println("Finished at timestep " + currentTimestep
            + "; record " + k);
        long endTime = System.currentTimeMillis();
        System.out.println("Finish time: " + new Date(endTime));
        System.out.println("Execution took " + ((endTime - startTime) / 1000f)
            + " seconds");

        // writeToFileTSV(functionalGroup, lifespan, actualDeaths, births,
        // cells, probdead);
        writeToFileAnalyser(functionalGroup, lifespan, actualDeaths, births,
            cells, deathsByReason, probdead, startTimestep, endTimestep);

        if (completion != null)
          completion.jobCompleted(null);
      } else {
        e.printStackTrace();
        if (completion != null)
          completion.jobCancelled();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  // private void writeToFileTSV(FGDataSource fg, double[] lifespan, double[]
  // actualDeaths, double[] births, double[] cells, double[][] probdead) {
  // int days = lifespan.length;
  // System.out.println("Writing data for " + (days - 1) + " days");
  //    
  // File directory = theModelRun.getDirectory();
  // try {
  // // Write time series to file
  // File timeSeriesFile = new File(directory, "lifespan-series_" + fg.getName()
  // + ".tsv");
  // PrintWriter out = new PrintWriter(new FileOutputStream(timeSeriesFile));
  //      
  // for (int i = 1; i < days; i++)
  // out.println(i + "\t" + lifespan[i] + "\t" + actualDeaths[i] + "\t" +
  // births[i] + "\t" + cells[i]
  // + "\t" + (actualDeaths[i] / cells[i]) + "\t" + (births[i] / cells[i]));
  //      
  // out.close();
  //      
  // // Write probdead surface to file
  // File probdeadFile = new File(directory, "lifespan-probdead_" + fg.getName()
  // + ".tsv");
  // out = new PrintWriter(new FileOutputStream(probdeadFile));
  //      
  // for (int i = 1; i < days; i++) {
  // for (int j = 1; j < days; j++)
  // out.println(i + "\t" + j + "\t" + probdead[i][j]);
  // out.println();
  // }
  //      
  // out.close();
  //      
  // // Write probalive surface to file
  // File probaliveFile = new File(directory, "lifespan-probalive_" +
  // fg.getName() + ".tsv");
  // out = new PrintWriter(new FileOutputStream(probaliveFile));
  //      
  // for (int i = 1; i < days - PROBALIVE_FUTURE; i++) {
  // out.println(i + "\t0\t1.0");
  // for (int j = 0; j <= PROBALIVE_FUTURE - 1; j++)
  // out.println(i + "\t" + (j+1) + "\t" + (1-probdead[i][i+j]));
  // out.println();
  // }
  //      
  // out.close();
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

  public void writeToFileAnalyser(FGDataSource fg, double[] lifespan,
      double[] actualDeaths, double[] births, double[] cells,
      double[][] deathsByReason, double[][] probdead, int startTimeStep,
      int endTimeStep) {
    double[] deathsByReasonDay;
    String[] reasons = theModelRun.getDeathReasons();
    int nReasons = reasons.length;

    try {
      File directory = theModelRun.getDirectory();

      // Write time series to file
      final String fgName = StringTools.nonSpace(fg.getName());
      File seriesFile = new File(directory, "lifespan-series_" + fgName + ".bin");
      OutputStream stream = new BufferedOutputStream(new FileOutputStream(seriesFile));
      if (fg.isZipped()) stream = new BufferedOutputStream(new GZIPOutputStream(stream));
      RealDataOutputStream out = RealDataOutputStream.getInstance(stream, fg.isUsingFloats());

      int n = lifespan.length;
      for (int i = 0; i < n; i++) {
        out.writeReal(lifespan[i]);
        out.writeReal(cells[i]);
        out.writeReal(actualDeaths[i]);
        out.writeReal(births[i]);
        out.writeReal(actualDeaths[i] / cells[i]);
        out.writeReal(births[i] / cells[i]);

        deathsByReasonDay = deathsByReason[i];
        for (int j = 0; j < nReasons + 2; j++)
          out.writeReal(deathsByReasonDay[j]);
      }
      out.flush();
      out.close();

      // Write probalive field to file
      File probaliveFile = new File(directory, "lifespan-probalive_" + fgName
          + ".bin");
      stream = new BufferedOutputStream(new FileOutputStream(probaliveFile));
      if (fg.isZipped()) stream = new BufferedOutputStream(new GZIPOutputStream(stream));
      out = RealDataOutputStream.getInstance(stream, fg.isUsingFloats());

      for (int i = 1; i < n - PROBALIVE_FUTURE; i++)
        for (int j = 0; j < PROBALIVE_FUTURE; j++)
          out.writeReal(1 - probdead[i][i + j]);

      out.flush();
      out.close();

      // Describe time series
      Document dataFormats = theModelRun.getDataFormats();
      String environmentName = "Lifespan data for " + fg.getName();
      //      XMLTag seriesTag = theModelRun.getDataFormats().getTagWhere(
//          "dataformat/environment", "name", environmentName);
      Element seriesTag = (Element) dataFormats.selectSingleNode("/dataformat/environment[name='" + environmentName + "']");
      if (seriesTag == null) seriesTag = dataFormats.getRootElement().addElement("environment");
      else seriesTag.clearContent();
      seriesTag.addElement("name").setText(environmentName);
      seriesTag.addElement("data").setText(seriesFile.getName());
      seriesTag.addElement("layers").setText("Single");
      seriesTag.addElement("zip").setText(Boolean.toString(fg.isZipped()));

//      XMLTag outputTag = seriesTag.addTag("output");
      Element outputTag = seriesTag.addElement("output");
      outputTag.addElement("after").setText(Integer.toString(startTimeStep + 48));
      outputTag.addElement("freq").setText("48");
      outputTag.addElement("until").setText(Integer.toString(endTimeStep));

      int k = 1;
      seriesTag.add(makeVariableTag(k++, "Lifespan", "Life expectancy","days"));
      seriesTag.add(makeVariableTag(k++, "Cells", "Cells", "cells"));
      seriesTag.add(makeVariableTag(k++, "Deaths", "Deaths per day", "cells"));
      seriesTag.add(makeVariableTag(k++, "Births", "Births per day", "cells"));
      seriesTag.add(makeVariableTag(k++, "DeathRate", "Death rate", ""));
      seriesTag.add(makeVariableTag(k++, "BirthRate", "Birth rate", ""));
      seriesTag.add(makeDeathReasonTag(k++, "Ingestion"));
      seriesTag.add(makeDeathReasonTag(k++, "Removal"));
      for (int i = 0; i < nReasons; i++) seriesTag.add(makeDeathReasonTag(k++, reasons[i]));

      // Describe probalive field
      String field = "Survival rates for " + fg.getName();
//      XMLTag fieldTag = theModelRun.getDataFormats().getTagWhere(
//          "dataformat/environment", "name", field);
      Element fieldTag = (Element) dataFormats.selectSingleNode("/dataformat/field[name='" + field + "']");
      if (fieldTag == null) fieldTag = dataFormats.getRootElement().addElement("field");
      else fieldTag.clearContent();

      fieldTag.addElement("name").setText(field);
      fieldTag.addElement("data").setText(probaliveFile.getName());
      fieldTag.addElement("zip").setText(Boolean.toString(fg.isZipped()));

//      XMLTag dimensionsTag = fieldTag.addTag("dimensions");
      Element dimensionsTag = fieldTag.addElement("dimensions");
      dimensionsTag.add(makeDimensionTag(startTimeStep, endTimeStep, 48,"Timestep", "timesteps"));
      dimensionsTag.add(makeDimensionTag(0, PROBALIVE_FUTURE-1, 1,"Days later", "days"));

      fieldTag.add(makeVariableTag(0, "Probalive", "Survival rates", ""));

//      theModelRun.getDataFormats().write();
      XMLWriter writer = new XMLWriter(new FileWriter(theModelRun.getDataFormatsFile()), OutputFormat.createPrettyPrint());
      writer.write(dataFormats);
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

  private Element makeVariableTag(int id, String name, String desc, String units) {
//    XMLTag var = new XMLTag("var");
//    var.addTag("id", Integer.toString(id));
//    var.addTag("name", name);
//    var.addTag("desc", desc);
//    var.addTag("units", units);
//    var.addTag("depth", "false");

    Element var = df.createElement("var");
    var.addElement("id").addText(Integer.toString(id));
    var.addElement("name").addText(name);
    var.addElement("desc").addText(desc);
    var.addElement("units").addText(units);
    var.addElement("depth").addText("false");

    return var;
  }

  private Element makeDimensionTag(int start, int end, int step, String label,
      String units) {
//    XMLTag tag = new XMLTag("dim");
//    tag.setAttribute("start", Integer.toString(start));
//    tag.setAttribute("end", Integer.toString(end));
//    tag.setAttribute("step", Integer.toString(step));
//    tag.setAttribute("label", label);
//    tag.setAttribute("units", units);
    
    Element tag = df.createElement("dim");
    tag.addAttribute("start", Integer.toString(start));
    tag.addAttribute("end", Integer.toString(end));
    tag.addAttribute("step", Integer.toString(step));
    tag.addAttribute("label", label);
    tag.addAttribute("units", units);

    return tag;
  }

  private Element makeDeathReasonTag(int id, String reason) {
    return makeVariableTag(id, reason, "Deaths by " + reason, "cells");
  }

  public Job createJob(String functionalGroup, int startTimestep,
      int endTimestep, boolean[] liveStages, boolean[] deadStages) {
    return new LifespanJob(functionalGroup, startTimestep, endTimestep,
        liveStages, deadStages);
  }

  public Job createJob(FGDataSource functionalGroup, int startTimestep,
      int endTimestep, boolean[] liveStages, boolean[] deadStages) {
    return new LifespanJob(functionalGroup, startTimestep, endTimestep,
        liveStages, deadStages);
  }

  private class LifespanJob implements Job {

    private FGDataSource functionalGroup;

    private int startTimestep, endTimestep;

    private boolean[] liveStages, deadStages;

    public LifespanJob(String _functionalGroup, int _startTimestep,
        int _endTimestep, boolean[] _liveStages, boolean[] _deadStages) {
      this(theModelRun.getFunctionalGroupByName(_functionalGroup),
          _startTimestep, _endTimestep, _liveStages, _deadStages);
    }

    public LifespanJob(FGDataSource _functionalGroup, int _startTimestep,
        int _endTimestep, boolean[] _liveStages, boolean[] _deadStages) {
      functionalGroup = _functionalGroup;
      startTimestep = _startTimestep;
      endTimestep = _endTimestep;
      liveStages = _liveStages;
      deadStages = _deadStages;
    }

    public void start(ProgressListener progress, CompletionListener completion) {
      calculateLifespan(functionalGroup, startTimestep, endTimestep,
          liveStages, deadStages, progress, completion);
    }

    public void cancel() {
      synchronized (LifespanCalculator.this) {
        cancelled = true;
      }
    }

  }

  private static class CopyThings implements TObjectProcedure {

    private int today;

    public boolean execute(Object object) {
      ((ParticleInfo) object).copyThings(today - 1, today);
      return true;
    }

    public void setToday(int _today) {
      today = _today;
    }

  }

  public class SumPopulations implements TObjectProcedure {

    public double sum;

    public boolean execute(Object object) {
      sum += ((ParticleInfo) object).population;
      return true;
    }

  }
}
