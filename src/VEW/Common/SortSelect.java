package VEW.Common;

class SortSelect {
  int MAXPARTICLESIZE = 27;
  SortableItem[] SortArray = new SortableItem[MAXPARTICLESIZE];
  
  final static int TopDistribution = 0;
  final static int BottomDistribution = 1;
  final static int EqualDistribution = 2;
  final static int ProportionalDistribution = 3;
  final static int RandomDistribution = 4;
  
  /*
   * Particles is an array of the particle data, with the first index pointing to a particle, and the second to the variable of that particle.
   * CriteriaIndex is the index of the variable to sort by.
   * Ascending is whether or not to sort in Ascending order.
   * ParticlesToBeSelected is the number of particles to select.
   * HistogramBins is the number of bins for distributional selections.
   * Distribution is the style of selection required.
   */
  
  
  /*
  private void DoIt(double[][] Particles, int CriteriaIndex, boolean Ascending, int ParticlesToBeSelected, int HistogramBins, int Distribution)
  {
    int TotalParticles = Particles.length;
    double MinValue;
    double MaxValue;
    for(int i = 0; i < TotalParticles; i++)
    {
      SortArray[i].SetValues(i, Particles[i][CriteriaIndex] * (Ascending ? 1 : -1));
    }
    Arrays.sort(SortArray,0, TotalParticles);
    if(Ascending)
    {
      MinValue = SortArray[0].Value;
      MaxValue = SortArray[TotalParticles - 1].Value;
    }
    else
    {
      MinValue = -SortArray[TotalParticles - 1].Value;
      MaxValue = -SortArray[0].Value;
    }
    
    if(ParticlesToBeSelected > TotalParticles)
    {
      ParticlesToBeSelected = TotalParticles;
    }
    
    int[] SelectionIndices = new int[ParticlesToBeSelected];
    if(Distribution == TopDistribution)
    {
        for(int i = 0; i < ParticlesToBeSelected; i++)
        {
          SelectionIndices[i] = i;
        }
    }
    else if(Distribution == BottomDistribution)
    {
      for(int i = 1; i <= ParticlesToBeSelected; i++)
      {
        SelectionIndices[i - 1] = TotalParticles - i;
      }
    }
    else if(Distribution == RandomDistribution)
    {
    }
    else
    {
      // START of histogram bit.
      double BinWidth = (MaxValue - MinValue) / HistogramBins;
      int[] BinHeights = new int[HistogramBins];
      int CurrentBin = 0;
      double TempDouble;
      Arrays.fill(BinHeights, 0);
      for(int i = 0; i < SortArray.length; i++)
      {
        TempDouble = SortArray[i].Value - MinValue;
        TempDouble /= BinWidth;
        CurrentBin = (int)Math.floor(TempDouble);
        BinHeights[CurrentBin]++;
      }
      // END of histogram bit.
      if(Distribution == EqualDistribution)
      {
        int selectionSize = ParticlesToBeSelected / HistogramBins;
        int spareParticles = ParticlesToBeSelected % HistogramBins;
        int[] AllocatedParticles = new int[HistogramBins];
        Arrays.fill(AllocatedParticles, 0);
        for(int i = 0; i < HistogramBins; i++)
        {
          if(selectionSize > BinHeights[i])
          {
            AllocatedParticles[i] = BinHeights[i];
            spareParticles += (selectionSize - BinHeights[i]);
          }
          else
          {
            AllocatedParticles[i] = selectionSize;
          }
        }
        int Pointer = 0;
        int Counter = 0;
        while(spareParticles > 0)
        {
          if(AllocatedParticles[Counter] < BinHeights[Counter])
          {
            AllocatedParticles[Counter]++;
            spareParticles--;
          }
          Counter++;
          if(Counter == HistogramBins)
          {
            Counter = 0;
          }
        }
        Counter = 0;
        for(int i = 0; i < HistogramBins; i++)
        {
          for(int j = 0; j < AllocatedParticles[i]; j++)
          {
            SelectionIndices[Counter] = Pointer + j;
            Counter++;
          }
          Pointer += BinHeights[i];
        }
      }
      else if(Distribution == ProportionalDistribution)
      {
        double selectionPercentage = (double)ParticlesToBeSelected / (double)TotalParticles;
        int spareParticles = ParticlesToBeSelected;
        int[] AllocatedParticles = new int[HistogramBins];
        Arrays.fill(AllocatedParticles, 0);
        for(int i = 0; i < HistogramBins; i++)
        {
          AllocatedParticles[i] = (int)Math.floor(BinHeights[i] * selectionPercentage);
          spareParticles -= AllocatedParticles[i];
        }
        int Pointer = 0;
        int Counter = 0;
        while(spareParticles > 0)
        {
          if(AllocatedParticles[Counter] < BinHeights[Counter])
          {
            AllocatedParticles[Counter]++;
            spareParticles--;
          }
          Counter++;
          if(Counter == HistogramBins)
          {
            Counter = 0;
          }
        }
        Counter = 0;
        for(int i = 0; i < HistogramBins; i++)
        {
          for(int j = 0; j < AllocatedParticles[i]; j++)
          {
            SelectionIndices[Counter] = Pointer + j;
            Counter++;
          }
          Pointer += BinHeights[i];
        }
      }
    }
  }
  */
  private class SortableItem implements Comparable
  { 
    public double Value;
    public int TagID;
    
    public SortableItem(int _TagID, double _Value)
    {
      TagID = _TagID;
      Value = _Value;
    }
    
    public void SetValues(int _TagID, double _Value)
    {
      TagID = _TagID;
      Value = _Value;
    }
    
    public int compareTo(Object obj)
    {
      SortableItem OtherOne = (SortableItem)obj;
      if(Value == OtherOne.Value)
      {
        return 0;
      }
      else if(Value > OtherOne.Value)
      {
        return 1;
      }
      else
      {
        return -1;
      }
    }
  }
}