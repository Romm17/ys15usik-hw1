package ua.yandex.shad;

public class TemperatureSeriesAnalysis {

	private TemperatureSeriesAnalysis(){}

	public static final int MIN_TEMPERATURE = -273;
	
	public static double average(double... arr){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		double avg = 0;
		for(double elem : arr)
			avg += elem;
		avg /= arr.length;
		return avg;
	}

	public static int findTempClosestToZero(int ... arr){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		int min = arr[0];
		for(int val : arr){
			if(val <= MIN_TEMPERATURE){
				throw new IllegalArgumentException();
			}
			min = val < min ? val : min;
		}
		return min;
	}

}
