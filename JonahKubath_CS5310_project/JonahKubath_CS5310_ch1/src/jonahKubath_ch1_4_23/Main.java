/*
 * Author: Jonah Kubath
 * Class: CS5310
 * Assignment: 1
 * Date: 09/09/2018
 */
package jonahKubath_ch1_4_23;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		/* Initialization */
		//int[] arrayLengths = {100, 200, 400, 800, 1600, 3200, 6400 , 12800, 25600, 51200};
		int[] arrayLengths = {100, 300, 500, 700, 900, 1100, 1300, 1500, 1700, 1900, 2100, 2300, 2500, 2700, 2900
				, 3100, 3300, 3500, 3700, 3900, 4100, 4300, 4500, 4700, 4900};
		int totalRuns = 5;
		Random randomInt = new Random();
		int randomIntBoundary = 100;
		
		//Run multiple test cases
		for(int index = 0; index < arrayLengths.length; index++) {
			long averageTime = 0;
			int count = -1;
			
			//Run each test case xtotalRuns to find an average time 
			for( int runCount = 0; runCount < totalRuns; runCount++) {
				//Initialize an array with random numbers
				int[] array = new int[arrayLengths[index]];
				for(int i = 0; i < array.length; i++) {
					array[i] = randomInt.nextInt(randomIntBoundary);
				}
				
				long startTime = System.nanoTime();
				//call the algorithm
				//algorithm_d_timed(array, array.length);
				//count = algorithm_d(array, array.length);
				count = algorithm_d_simplified(array, array.length);
				long endTime = System.nanoTime();
				
				
				long totalTime = endTime - startTime;
				averageTime += totalTime;
				
				//System.out.println("Total time: " + (totalTime / 1000) + " ms");
				
			} //Done with average runs
			
			System.out.println("Run Length: " + arrayLengths[index]);
			if(count != -1)
				System.out.println("Statement count: " + count);
			System.out.println("Average time: " + (averageTime / (totalRuns * 1000)) + " ms");
			System.out.println();
		}
	}
	
	public static void algorithm_d_timed(int[] x, int n) {
		int i = 0;
		
		while(i < n) {
			x[i] = x[i] + 2;
			i += 2;
		}
		
		i = 0;
		
		while( i < n / 2) {
			x[i] = x[i] + x[i + 1];
			
			i += 1;
		}
	}
	
	public static int algorithm_d(int[] x, int n) {
		int count = 0;
		int i = 0; count++;
		
		while(i < n) { count++;
			x[i] = x[i] + 2; count++;
			i += 2; count++;
		} count++; //for final while loop check
		
		i = 0; count++;
		
		while( i < n / 2) {count++;
			x[i] = x[i] + x[i + 1]; count++;
			
			i += 1; count++;
		} count++; //for final while loop check
		
		return count;
	}
	
	public static int algorithm_d_simplified(int[] x, int n) {
		int count = 0;
		int i = 0; count++;
		
		while(i < n) {
			x[i] = x[i] + 2;
			i += 2;
			count += 3; //while check + 2 statements
		}
		
		i = 0; 
		count += 2; //Final while loop check + 1 statement
		
		while( i < n / 2) {
			x[i] = x[i] + x[i + 1];
			
			i += 1;
			count += 3; //while check + 2 statements
		}
		count++; //for final while loop check
		
		return count;
		
	}
	

}
