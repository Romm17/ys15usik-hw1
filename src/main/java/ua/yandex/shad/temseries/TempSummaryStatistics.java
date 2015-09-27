package ua.yandex.shad.tempseries;

public class TempSummaryStatistics {

private double avgTemp;
private double devTemp;
private double minTemp;
private double maxTemp;

public TempSummaryStatistics(
double avg, double dev, double min, double max) {
avgTemp = avg;
devTemp = dev;
minTemp = min;
maxTemp = max;
}

public double getAvgTemp() {
return avgTemp;
}

public double getDevTemp() {
return devTemp;
}

public double getMinTemp() {
return minTemp;
}

public double getMaxTemp() {
return maxTemp;
}

}