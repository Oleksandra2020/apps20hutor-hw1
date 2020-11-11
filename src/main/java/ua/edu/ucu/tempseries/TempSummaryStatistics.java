package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public TempSummaryStatistics(TemperatureSeriesAnalysis tempSeries)
    {
        this.avgTemp = tempSeries.average();
        this.devTemp = tempSeries.deviation();
        this.minTemp = tempSeries.min();
        this.maxTemp = tempSeries.max();
    }


}
