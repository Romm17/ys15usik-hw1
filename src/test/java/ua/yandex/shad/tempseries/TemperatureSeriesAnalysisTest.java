package ua.yandex.shad.tempseries;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

	@Test
	public void testTemperatureSeriesAnalysis(){
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis();
		TempSummaryStatistics tss = temperatureSeriesAnalysis.summaryStatistics();
		assertTrue(tss.getAvgTemp() == tss.getMinTemp() 
			&& tss.getAvgTemp() == tss.getMaxTemp()
			&& tss.getAvgTemp() == tss.getDevTemp());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAverage_FailOnEmptyList(){
		double arr[] = {};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		temperatureSeriesAnalysis.average();
	}

	@Test
	public void testAverage(){
		double arr[] = {-5.5, 0, 10.5, 3};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = 2.0;
		double actual = temperatureSeriesAnalysis.average();
		assertEquals(expected, actual, 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeviation_FailOnEmptyList(){
		double arr[] = {};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		temperatureSeriesAnalysis.deviation();
	}

	@Test
	public void testDeviation(){
		double arr[] = {-1.5, 0, 2.5, 100, -50.2};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = 49.015124196517;
		double actual = temperatureSeriesAnalysis.deviation();
		assertEquals(expected, actual, 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMin_FailOnEmptyList(){
		double arr[] = {};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		temperatureSeriesAnalysis.min();
	}

	@Test
	public void testMin(){
		double arr[] = {-1.5, 0, 2.5, 100, -50.2};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = -50.2;
		double actual = temperatureSeriesAnalysis.min();
		assertEquals(expected, actual, 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMax_FailOnEmptyList(){
		double arr[] = {};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		temperatureSeriesAnalysis.max();
	}

	@Test
	public void testMax(){
		double arr[] = {-1.5, 0, 2.5, 100, -50.2};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = 100;
		double actual = temperatureSeriesAnalysis.max();
		assertEquals(expected, actual, 0.0001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindTempClosestToZero_FailOnEmptyList(){
		double arr[] = {};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		temperatureSeriesAnalysis.findTempClosestToZero();
	}

	@Test
	public void testFindTempClosestToZero_WithSingleElement(){
		double arr[] = {1};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = 1;
		double result = temperatureSeriesAnalysis.findTempClosestToZero();
		assertEquals(result, expected, 0.001);
	}

	@Test(expected = InputMismatchException.class)
	public void testFindTempClosestToZero_FailTempOutOfRange(){
		double arr[] = {-10, 0, 100, -274};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		temperatureSeriesAnalysis.findTempClosestToZero();
	}

	@Test
	public void testFindTempClosestToZero_IsPositiveTempsArrayList(){
		double arr[] = {3, 10, 20, 100, 5, 1, 200};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = 1;
		double result = temperatureSeriesAnalysis.findTempClosestToZero();
		assertEquals(expected, result, 0.001);
	}

	@Test
	public void testFindTempClosestToZero_WithZeroElement(){
		double arr[] = {3, 10, 0, 100, 5, 1, 200};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = 0;
		double result = temperatureSeriesAnalysis.findTempClosestToZero();
		assertEquals(expected, result, 0.001);
	}

	@Test
	public void testFindTempClosestToZero_WithPositiveAndNegativeTempsArrayList(){
		double arr[] = {3.0, 10.2, -20.4, 100.0, -5.3, 1.0, -200.9};
		TemperatureSeriesAnalysis temperatureSeriesAnalysis = new TemperatureSeriesAnalysis(arr);
		double expected = 1;
		double result = temperatureSeriesAnalysis.findTempClosestToZero();
		assertEquals(expected, result, 0.001);
	}

	@Test
	public void testFindTempClosestToZero_WithEquallyCloseTempsArrayList(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -200.9};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		double expected = 1.0;
		double result = tsa.findTempClosestToZero();
		assertEquals(expected, result, 0.001);
	}

	@Test
	public void testFindTempClosestToValue(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		double expected = -5.1;
		double result = tsa.findTempClosestToValue(-5.2);
		assertEquals(expected, result, 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindTempsLessThen_onEmptyArray(){
		double arr[] = {};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		tsa.findTempsLessThen(-5.1);
	}

	@Test
	public void testFindTempsLessThen(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		double[] expected = {-20.4, -5.3};
		double[] result = tsa.findTempsLessThen(-5.1);
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++)
			assertEquals(expected[i], result[i], 0.001);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindTempsGreaterThen_onEmptyArray(){
		double arr[] = {};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		tsa.findTempsGreaterThen(-5.1);
	}

	@Test
	public void testFindTempsGreaterThen(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		double[] expected = {3.0, 10.2};
		double[] result = tsa.findTempsGreaterThen(1.0);
		assertEquals(expected.length, result.length);
		for(int i = 0; i < expected.length; i++)
			assertEquals(expected[i], result[i], 0.001);
	}

	@Test
	public void testAddTemps_onEmptyTemps(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
		double temps[] = {};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		int expected = 7;
		int actual = tsa.addTemps(temps);
		assertEquals(expected, actual);
	}

	@Test
	public void testAddTemps_increaseX2(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
		double temps[] = {3.3, -10.4, 33.89};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		int expected = 10;
		int actual = tsa.addTemps(temps);
		assertEquals(expected, actual);
	}

	@Test
	public void testAddTemps_increaseMoreThanX2(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
		double temps[] = {3.3, -10.4, 33.89, 4.4, -2.3, 8.9, 4.2, 10.10};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		int expected = 15;
		int actual = tsa.addTemps(temps);
		assertEquals(expected, actual);
	}

	@Test
	public void testSummaryStatistics(){
		double arr[] = {3.0, 10.2, -20.4, -1.0, -5.3, 1.0, -5.1};
		TemperatureSeriesAnalysis tsa = new TemperatureSeriesAnalysis(arr);
		TempSummaryStatistics tss = tsa.summaryStatistics();
		double average = tsa.average();
		assertEquals(average, tss.getAvgTemp(), 0.001);
	}

}