package ua.edu.ucu.tempseries;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

    static final int ZERO = 0;
    static final int LOW = -273;
    private static final int START_SIZE = 5;

    private double[] temperatures;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[START_SIZE];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkSeries(temperatureSeries);
        temperatures = temperatureSeries;
        isEmpty();
    }

    public double[] getTemperatures() {
        return temperatures;
    }

    public int getStartSize()
    {
        return START_SIZE;
    }

    public void checkSeries(double[] tempArr)
    {
        for (double temperature: tempArr)
        {
            if (temperature < LOW)
            {
                throw new InputMismatchException();
            }
        }
    }

    public double countSum(double[] temperatureArray)
    {
        double summ = 0;
        for (double temperature : temperatureArray)
        {
            summ += temperature;
        }
        return summ;
    }

    public double countMean(double[] temperatureArray)
    {
        int arrayLength = temperatureArray.length;
        return countSum(temperatureArray)/arrayLength;
    }

    public void isEmpty()
    {
        if (temperatures.length == 0)
        {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        isEmpty();
        int tempLen = temperatures.length;
        double summ = countSum(temperatures);
        return summ/tempLen;
    }

    public double deviation() {
        isEmpty();
        int tempLen = temperatures.length;
        double mean = countMean(temperatures);
        double[] devTempArr = new double[tempLen];
        for (int i = 0; i < tempLen; i++)
        {
            double x = temperatures[i] - mean;
            devTempArr[i] = x*x;
        }
        double devSum = countSum(devTempArr);
        return Math.pow(devSum/tempLen, 0.5);
    }

    public double min() {
        isEmpty();
        double curMin = temperatures[0];
        for (double temperature: temperatures)
        {
            if (temperature < curMin)
            {
                curMin = temperature;
            }
        }
        return curMin;
    }

    public double max() {
        isEmpty();
        double curMax = temperatures[0];
        for (double temperature: temperatures)
        {
            if (temperature > curMax)
            {
                curMax = temperature;
            }
        }
        return curMax;
    }

    public double findTempClosestToZero()
    {
        return findTempClosestToValue(ZERO);
    }

    public double findTempClosestToValue(double tempValue) {
        isEmpty();
        double minDiff = Math.abs(tempValue - temperatures[0]);
        double [] closestValues = new double[] {minDiff, minDiff};
        double newDiff;

        for (double temperature: temperatures)
        {
            newDiff = Math.abs(tempValue - temperature);
            if (newDiff == minDiff)
            {
                closestValues[1] = temperature;
            }
            else if (newDiff < minDiff)
            {
                closestValues[0] = temperature;
                closestValues[1] = temperature;
                minDiff = newDiff;
            }
        }
        return Math.max(closestValues[0], closestValues[1]);
    }

    public double[] findTempsLessThen(double tempValue) {
        isEmpty();
        double[] lessVals = new double[temperatures.length];
        int i = 0;
        for (double temperature: temperatures)
        {
            if (temperature < tempValue)
            {
                lessVals[i] = temperature;
                i++;
            }
        }
        return lessVals;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        isEmpty();
        double[] greaterVals = new double[temperatures.length];
        int i = 0;
        for (double temperature: temperatures)
        {
            if (temperature > tempValue)
            {
                greaterVals[i] = temperature;
                i++;
            }
        }
        return greaterVals;
    }


    public TempSummaryStatistics summaryStatistics()
    {
        isEmpty();
        return new TempSummaryStatistics(new
                TemperatureSeriesAnalysis(temperatures));
    }

    public int addTemps(double... temps) {
        checkSeries(temps);
        int tempLen = temperatures.length;
        int tempsLen = temps.length;
        int newLen = tempLen + tempsLen;
        if (newLen > tempLen)
        {
            double[] newTemps = new double[newLen*2];
            for (int i = 0; i < tempLen; i++)
            {
                newTemps[i] = temperatures[i];
            }
            for (int i = 0; i < tempsLen; i++)
            {
                newTemps[tempLen+i] = temps[i];
            }
            temperatures = newTemps;
        }
        return (int) Math.floor(countSum(temperatures));
    }
}
