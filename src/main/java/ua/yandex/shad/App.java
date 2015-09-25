package ua.yandex.shad;

public class App {
	
	public static double avg(Double x, Double y){
		return (x + y) / 2;
	}

	public static int findTempClosestToZero(int ... arr) throws IllegalArgumentException{
		if(arr.length == 0)
			throw new IllegalArgumentException();
		int min = arr[0];
		for(int val : arr){
			if(val <= -274){
				throw new IllegalArgumentException();
			}
			if(val == 0)
				return val;
			min = val < min ? val : min;
		}
		return min;
	}

}
