package ua.yandex.shad;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

	@Test
	public void testAvg(){
		Double x = 10.2;
		Double y = 10.8;
		double expRes = 10.5;
		assertEquals(App.avg(x, y), expRes, 0.0001);
		try{
			App.avg(x, null);
			fail("Test failed");
		}catch(NullPointerException e){
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFindTempClosestToZero_FailOnEmptyList(){
		int arr[] = {};
		App.findTempClosestToZero(arr);
		fail("Fail");
	}

	@Test
	public void testFindTempClosestToZero_WithSingleElement(){
		int arr[] = {1};
		int expected = 1;
		int result = App.findTempClosestToZero(arr);
		assertEquals(result, expected);
	}

	/*@Test(expected = IllegalArgumentException.class)
	@Ignore
	public void testFindTempClosestToZero_FailTempOutOfRange(){
		int arr[] = {-10, 0, 100, -274};
		App.findTempClosestToZero(arr);
		fail("Fail");
	}*/

	@Test
	public void testIsPositiveTempsArrayList(){
		int arr[] = {3, 10, 20, 100, 5, 1, 200};
		int expected = 1;
		int result = App.findTempClosestToZero(arr);
		assertEquals(expected, result);
	}

	@Test
	public void testWithZeroElement(){
		int arr[] = {3, 10, 0, 100, 5, 1, 200};
		int expected = 0;
		int result = App.findTempClosestToZero(arr);
		assertEquals(expected, result);
	}

}