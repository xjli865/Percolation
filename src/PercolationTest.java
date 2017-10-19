import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
/**
 * This Junit test is used for Percolation class
 * @author Jenny 
 *
 */
public class PercolationTest {
	Percolation example;
	
	/**
	 * create an example instance for Percolation class
	 */
	@Before
	 public void setUp() {
		example = new Percolation();// initialize the example here
	 }
	
	/**
	 * Junit test for ground method in Percolation class
	 */
	@Test
	public void testGround() {
		int[][] myArray_1 = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		int[][] myArray_2 = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		Assert.assertArrayEquals(myArray_1, example.ground(3, 1.0));
		Assert.assertArrayEquals(myArray_2, example.ground(3, 0.0));
		int n = 50;
		double p = 0.6;
		int[][] testArray = example.ground(n, p);
		int count = 0;
		int sumOftotal = 0;
		for (int i=0; i < n; i++){
			for(int j=0; j< n; j ++){
				if (testArray[i][j] == 1){
					count += 1;
				}
				sumOftotal += 1;		
				}	
			}
		double testP = (double)count/sumOftotal;
		Assert.assertEquals(p, testP, 0.1); 
	}
	
	/**
	 * Junit test for seep method in Percolation class
	 */
	@Test
	public void testSeep(){
		int n = 8;
		int[][] myArray_a = new int[][]{{0, 0, 1, 1, 0, 1, 0, 0}, 
									    {1, 0, 0, 0, 1, 0, 0, 1},
									    {0, 1, 1, 0, 1, 1, 1, 0},						  
									    {0, 1, 0, 0, 0, 1, 1, 0},
									    {1, 0, 0, 1, 1, 0, 0, 0},
									    {0, 0, 0, 1, 1, 1, 1, 0},
									    {1, 1, 0, 0, 1, 1, 1, 1},
									    {0, 1, 0, 0, 0, 1, 1, 0}};
		int[][] expectedResult_a = new int[][]{{2, 2, 1, 1, 2, 1, 2, 2},
											   {1, 2, 2, 2, 1, 2, 2, 1},
											   {0, 1, 1, 2, 1, 1, 1, 0},
											   {0, 1, 2, 2, 2, 1, 1, 0},
											   {1, 2, 2, 1, 1, 0, 0, 0},
											   {2, 2, 2, 1, 1, 1, 1, 0},
											   {1, 1, 2, 2, 1, 1, 1, 1},
											   {0, 1, 2, 2, 2, 1, 1, 0}};	
		for(int i=0; i < n; i++){
			example.seep(myArray_a, i);
		}
		Assert.assertArrayEquals(myArray_a, expectedResult_a);
		int m = 8; 
		int[][] myArray_b = new int[][]{{1, 0, 0, 0, 1, 1, 1, 0}, 
		    							{0, 0, 1, 1, 1, 0, 1, 0},
		    							{1, 0, 0, 0, 1, 1, 0, 1},						  
		    							{1, 1, 0, 0, 0, 1, 0, 1},
		    							{0, 1, 1, 1, 0, 0, 1, 1},
		    							{1, 0, 0, 1, 1, 1, 0, 0},
		    							{1, 0, 0, 0, 0, 1, 0, 0},
		    							{0, 1, 1, 0, 1, 1, 0, 1}};
		int[][] expectedResult_b = new int[][]{{1, 2, 2, 2, 1, 1, 1, 2},
											   {2, 2, 1, 1, 1, 0, 1, 2},
											   {1, 2, 2, 2, 1, 1, 0, 1},
											   {1, 1, 2, 2, 2, 1, 0, 1},
											   {0, 1, 1, 1, 2, 2, 1, 1},
											   {1, 0, 0, 1, 1, 1, 0, 0},
											   {1, 0, 0, 0, 0, 1, 0, 0},
											   {0, 1, 1, 0, 1, 1, 0, 1}};
		for(int i=0; i < n; i++){
			example.seep(myArray_b, i);
		}
		Assert.assertArrayEquals(myArray_b, expectedResult_b);
	}
	
	/**
	 * Junit test for percolate method in Percolate class
	 */
	@Test
	public void testPercolate(){
		int[][] result_a = new int[][]{{1, 2, 2, 2, 1, 1, 1, 2},
			   						   {2, 2, 1, 1, 1, 0, 1, 2},
			   						   {1, 2, 2, 2, 1, 1, 0, 1},
			   						   {1, 1, 2, 2, 2, 1, 0, 1},
			   						   {0, 1, 1, 1, 2, 2, 1, 1},
			   						   {1, 0, 0, 1, 1, 1, 0, 0},
			   						   {1, 0, 0, 0, 0, 1, 0, 0},
			   						   {0, 1, 1, 0, 1, 1, 0, 1}};
		assertFalse(example.percolate(result_a));
		int[][] result_b = new int[][]{{2, 2, 1, 1, 2, 1, 2, 2},
			   						   {1, 2, 2, 2, 1, 2, 2, 1},
			   						   {0, 1, 1, 2, 1, 1, 1, 0},
			   						   {0, 1, 2, 2, 2, 1, 1, 0},
			   						   {1, 2, 2, 1, 1, 0, 0, 0},
			   						   {2, 2, 2, 1, 1, 1, 1, 0},
			   						   {1, 1, 2, 2, 1, 1, 1, 1},
			   						   {0, 1, 2, 2, 2, 1, 1, 0}};
		assertTrue(example.percolate(result_b));
	}
}

