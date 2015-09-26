package ua.yandex.shad.tempseries;

public class TempSummaryStatistics {
    
	public final double avgTemp;
	public final double devTemp;
	public final double minTemp;
	public final double maxTemp;

	public TempSummaryStatistics(){
		avgTemp = 0;
		devTemp = 0;
		minTemp = 0;
		maxTemp = 0;

	}

	public TempSummaryStatistics(double avg, double dev, double min, double max){
		avgTemp = avg;
		devTemp = dev;
		minTemp = min;
		maxTemp = max;
	}

}