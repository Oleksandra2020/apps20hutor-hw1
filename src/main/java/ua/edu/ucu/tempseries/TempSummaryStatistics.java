package ua.edu.ucu.tempseries;

final public class TempSummaryStatistics {
    final private double avgTemp;
    final private double devTemp;
    final private double minTemp;
    final private double maxTemp;

    public TempSummaryStatistics(TemperatureSeriesAnalysis tempSeries)
    {
        this.avgTemp = tempSeries.average();
        this.devTemp = tempSeries.deviation();
        this.minTemp = tempSeries.min();
        this.maxTemp = tempSeries.max();
    }


}
