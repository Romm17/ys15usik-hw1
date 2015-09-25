package ua.yandex.shad;

import org.junit.Test;
import static org.junit.Assert.*;

public class TemperatureSeriesAnalysisTest {

	@Test(expected = IllegalArgumentException.class)
	public void testAverage_FailOnEmptyList(){
		double arr[] = {};
		TemperatureSeriesAnalysis.average(arr);
	}

	@Test
	public void testAverage(){
		double arr[] = {-5.5, 0, 10.5, 3};
		double expected = 2.0;
		double actual = TemperatureSeriesAnalysis.average(arr);
		assertEquals(expected, actual, 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindTempClosestToZero_FailOnEmptyList(){
		int arr[] = {};
		TemperatureSeriesAnalysis.findTempClosestToZero(arr);
		fail("Fail");
	}

	@Test
	public void testFindTempClosestToZero_WithSingleElement(){
		int arr[] = {1};
		int expected = 1;
		int result = TemperatureSeriesAnalysis.findTempClosestToZero(arr);
		assertEquals(result, expected);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindTempClosestToZero_FailTempOutOfRange(){
		int arr[] = {-10, 0, 100, -274};
		TemperatureSeriesAnalysis.findTempClosestToZero(arr);
		fail("Fail");
	}

	@Test
	public void testIsPositiveTempsArrayList(){
		int arr[] = {3, 10, 20, 100, 5, 1, 200};
		int expected = 1;
		int result = TemperatureSeriesAnalysis.findTempClosestToZero(arr);
		assertEquals(expected, result);
	}

	@Test
	public void testWithZeroElement(){
		int arr[] = {3, 10, 0, 100, 5, 1, 200};
		int expected = 0;
		int result = TemperatureSeriesAnalysis.findTempClosestToZero(arr);
		assertEquals(expected, result);
	}

}