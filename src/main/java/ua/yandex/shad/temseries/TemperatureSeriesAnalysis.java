package ua.yandex.shad.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

	public static final int MIN_TEMPERATURE = -273;

	private double arr[];

	private int size;

	private TempSummaryStatistics tempSummaryStatistics;

	public TemperatureSeriesAnalysis() {
        this.tempSummaryStatistics = new TempSummaryStatistics();
    }
    
    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
    	for(double val : temperatureSeries)
    		if(val < MIN_TEMPERATURE)
    			throw new InputMismatchException();
        this.arr = temperatureSeries;
        size = this.arr.length;
        double avg = avg();
        double dev = dev(avg);
        double min = minTemp();
        double max = maxTemp();
        this.tempSummaryStatistics = new TempSummaryStatistics(avg, dev, min, max);
    }

    public static void main(String... args){
    	double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
    	//double arr[] = {};
    	TemperatureSeriesAnalysis tsa = null;
    	try{
	    	tsa = new TemperatureSeriesAnalysis(arr);
	    	try{
		    	System.out.println(tsa.findTempClosestToValue(-5.2));
		    }catch(IllegalArgumentException e){
		    	System.out.println("Empty array");
		    	e.printStackTrace();
		    }
	    }catch(InputMismatchException e){
	    	System.out.println("Constructor was broken");
	    }
    }
	
    private double avg(){
    	if(arr.length == 0)
			return 0;
		double avg = 0;
		for(double elem : arr)
			avg += elem;
		avg /= arr.length;
		return avg;
    }

	public double average(){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		return this.tempSummaryStatistics.avgTemp;
	}

	private double dev(double avg){
		double dev = 0;
		for(double elem : arr)
			dev += Math.pow(elem - avg, 2);
		dev = Math.sqrt(dev / arr.length);
		return dev;
	}

	public double deviation(){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		return this.tempSummaryStatistics.devTemp;
	}

	private double minTemp(){
		if(arr.length == 0)
			return 0;
		double min = arr[0];
		for(int i = 1; i < arr.length; i++)
			min = 
				arr[i] < min ? 
					arr[i] : 
					min;
		return min;
	}

	public double min(){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		return this.tempSummaryStatistics.minTemp;
	}

	private double maxTemp(){
		if(arr.length == 0)
			return 0;
		double max = arr[0];
		for(int i = 1; i < arr.length; i++)
			max = 
				arr[i] > max ? 
					arr[i] : 
					max;
		return max;
	}

	public double max(){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		return this.tempSummaryStatistics.maxTemp;
	}

	public double findTempClosestToZero(){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		double min = arr[0];
		for(int i = 1; i < arr.length; i++){
			double minAbs = Math.abs(min);
			double valAbs = Math.abs(arr[i]);
			if(valAbs < minAbs || (Math.abs(minAbs - valAbs) < 0.000001 && arr[i] > 0))
				min = arr[i];
		}
		return min;
	}

	public double findTempClosestToValue(double tempValue){
        if(arr.length == 0)
			throw new IllegalArgumentException();
		double min = arr[0];
		for(int i = 1; i < arr.length; i++){
			double minAbs = Math.abs(min - tempValue);
			double valAbs = Math.abs(arr[i] - tempValue);
			if(valAbs < minAbs || (Math.abs(minAbs - valAbs) < 0.000001 && arr[i] - tempValue > 0))
				min = arr[i];
		}
		return min;
	}
    
    public double[] findTempsLessThen(double tempValue){
        if(arr.length == 0)
			throw new IllegalArgumentException();
		int count = 0;
		for(double elem : arr){
			if(elem < tempValue)
				count++; 
		}
		double res[] = new double[count];
		count = 0;
		for(double elem : arr){
			if(elem < tempValue)
				res[count++] = elem; 
		}
		return res;
    }
    
    public double[] findTempsGreaterThen(double tempValue){
		if(arr.length == 0)
			throw new IllegalArgumentException();
		int count = 0;
		for(double elem : arr){
			if(elem > tempValue)
				count++; 
		}
		double res[] = new double[count];
		count = 0;
		for(double elem : arr){
			if(elem > tempValue)
				res[count++] = elem; 
		}
		return res;
    }
    
    public TempSummaryStatistics summaryStatistics(){
        return this.tempSummaryStatistics;
    }
    
    public int addTemps(double ... temps){
    	int newSize = -1;
    	if(size + temps.length > arr.length){
    		if(2 * arr.length < temps.length){
    			newSize = 2 * arr.length;
    		}
    		else
    			newSize = size + temps.length + 10;
    	}
    	if(newSize != -1){
	    	double newTemps[] = new double[newSize];
	    	System.arraycopy(arr, 0, newTemps, 0, size);
	    	System.arraycopy(temps, 0, newTemps, size, temps.length);
	    	size += temps.length;
	    	arr = newTemps;
	    }
	    return size;
    }

}