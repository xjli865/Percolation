import java.util.Random;

/**
 * This is a program to calculate probability p such that
 * water has exactly a 50% probability of seeping all the
 * way to the bottom row.
 * @author Jenny
 *
 */
public class Percolation {
	double p = 0.5; // setup probability to be 0.5
	double delta = 0.5; //setup initial delta value;
	int count = 0; // count for number of repeated cycles
	
	 /** A random number generator. */
	Random random = new Random();
		
	/**
	 * The main method just creates a Percolation object and calls
	 * @param args Not used
	 */
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Percolation example = new Percolation();
		example.findProbability(50);
		example.findProbability(100);
		example.findProbability(200);
		long endTime = System.currentTimeMillis();
		System.out.println("\n Total time " + (endTime - startTime)/1000 + " sec");
	}
		
	/**
	 * The getRandom method create weighted probability between 1 and 0
	 * @param p, probability
	 * @return integer 1 or 0, which depends on given probability
	 */
	int getRandom(double p) {
		int[] probability = new int[100];
		for (int a=0; a < p*100; a++){
			probability[a] = 1;
		}
		for (int a=0; a > p*100 && a < 100; a++){
			probability[a] = 0;
		}
		int selectedIndex = new Random().nextInt(probability.length);
		return probability[selectedIndex];
	}
	
	/** 
	 * ground(n, p) returns an array of n arrays of integer, 
	 * where each array is of length n, and each integer has 
	 * probability p of being a sand grain, (1-p) of being empty (and dry). 
	 * Use the encoding 0 = empty space, 1 = sand grain, 2 = water.
	 * @param n, size of the array
	 * @param p, probability of being a sand grain
	 * @return  an array with given size
	 */
	 int[][] ground(int n, double p){
		int[][] myArray = new int[n][n]; 
		for (int i=0; i < n; i++){
			for(int j=0; j<n; j ++){
				myArray[i][j] = getRandom(p);
			}
		}
		return myArray;
	}
	 
	 /**
	  * seep(array, row) causes water to flow from row into row+1,
	  * modifying the array. In other words, this function 
	  * performs one step of the simulation.
	  * @param ground, an array of n arrays of integer
	  * @param row, the row of the array
	  */
	 void seep(int[][] ground, int row){
		 if (row == 0){
			 for (int i=0; i < ground.length; i++){
				 if (ground[row][i] == 0){
					 ground[row][i] = 2; 
				 }
			 }
		 }else{
			 for (int i=0; i < ground.length; i++){
				 if (ground[row-1][i] == 2 && ground[row][i] == 0){
					 ground[row][i] = 2;
				 }
			 }		 
		 }
		 for (int i=0; i < ground.length; i++){
			 if(ground[row][i] == 2){
				 for (int a=i; a < ground.length; a++){
					 if( ground[row][a] == 0){
						 ground[row][a] = 2;
					 }else if(ground[row][a] == 1){
						 break;
					 }
				 }
				 for (int b=i; b >= 0; b--){
					 if (ground[row][b] == 0){
						 ground[row][b] = 2;
					 }else if(ground[row][b] == 1){
						 break;
					 }
				 }
			 }
		 }
	 }
	 
	 /**
	  * Returns true if, after water has "seeped" as far as it can,
	  * water has reached the bottom row, and false otherwise. 
	  * For the example above, the result would be true.
	  * @param ground, an array of n arrays of integer
	  * @return a boolean result
	  */
	boolean percolate(int[][] ground){
		 for (int i=0; i < ground.length; i++){
			 if (ground[ground.length-1][i] == 2){
				 return true;
			 }else{
				 continue;
			 }
		 }
		 return false;
	 }
	
	/**
	 * For an n by n array, determines the packing probability p that 
	 * causes the array to have a 50% probability of water seeping 
	 * all the way to the bottom.
	 * @param n, size of the array
	 * @return packing probability p
	 */
	double findProbability(int n){
		double calculatedP = calucalteProbability(n, p);
		if (count < 6){
			if (calculatedP > 0.54){
				// if estimated probability greater than 0.54
				if (calculatedP - p > 0){
					delta/=2;
				}
				p = p - delta;
				calucalteProbability(n,p);
				findProbability(n);
				count += 1;
			}else if (calculatedP < 0.46){
				// if estimated probability less than 0.46
				if (calculatedP - p <0){
					delta/=2;
				}
				p = p + delta;
				calucalteProbability(n,p);
				findProbability(n);
				count += 1;
			}else{
				System.out.println("\nFor an "+n +" by "+ n +" array");
				System.out.printf( "The probability is %-2.2f", p );
				return p;
			}
		}
		else{
			System.out.println("\nFor an "+n +" by "+ n +" array");
			System.out.printf( "The probability is %-2.2f", p );
		}
		return p;
	}
	
	/**
	 * The calucalteProbability method is used to calculate the probability 
	 * that water seep to bottom with given probability via boolean array 
	 * @param n, the size of array 
	 * @param p, the given probability 
	 * @return a double number
	 */
	double calucalteProbability (int n, double p){
		int countTrue = 0;
		int countFalse = 0;
		boolean[] booleanList = new boolean[100];
		for (int i=0; i < booleanList.length; i++){
			int[][] myArray = ground(n, p);
			for(int j=0; j < n; j++){	
				seep(myArray, j);
			}
			booleanList[i] = percolate(myArray);
		}
		for (int a=0; a < booleanList.length; a++){
			if (booleanList[a] == true){
				countFalse += 1;
			}else if(booleanList[a] == false){
				countTrue += 1;
			}
		}
		double calculatedP = (double)countTrue/(countTrue + countFalse);	
		return calculatedP;
	}
}