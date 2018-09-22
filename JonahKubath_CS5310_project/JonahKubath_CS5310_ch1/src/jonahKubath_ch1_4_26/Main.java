/*
 * Author: Jonah Kubath
 * Class: CS5310
 * Assignment: 1
 * Date: 09/09/2018
 */
package jonahKubath_ch1_4_26;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		/* Initialization */
		int[] arrayLengths = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 
				130, 140, 150, 160, 170, 180, 190, 200};
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
				int[][] a = new int[arrayLengths[index]][arrayLengths[index]];
				int[][] b = new int[arrayLengths[index]][arrayLengths[index]];
				int[][] c = new int[arrayLengths[index]][arrayLengths[index]];
				for(int i = 0; i < a.length; i++) {
					for(int j = 0; j < a[i].length; j++) {
						a[i][j] = randomInt.nextInt(randomIntBoundary);
						b[i][j] = randomInt.nextInt(randomIntBoundary);
						c[i][j] = randomInt.nextInt(randomIntBoundary);
					}
				}
				
				
				long startTime = System.nanoTime();
				//call the algorithm
				//mult_timed(a, b, c, a.length, a[0].length, b.length);
				count = mult(a, b, c, a.length, a[0].length, b.length);
				//count = mult_simplified(a, b, c, a.length, a[0].length, b.length);
				
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
	
	public static int mult(int[][] a, int[][] b, int[][] c, int m, int n, int p) {
		int count = 0;
		
		for(int i = 0; i < m; i++) { count++;
		
			for(int j = 0; j < p; j++) { count++;
				c[i][j] = 0; count++;
				
				for(int k = 0; k < n; k++) { count++;
					c[i][j] = c[i][j] + a[i][j] * b[j][i]; count++;
				}
				count++; //final k for loop check
			}
			count++; // final j for loop check
		} count++;// final i for loop check
		
		
		return count;
	}
	
	public static int mult_simplified(int[][] a, int[][] b, int[][] c, int m, int n, int p) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			
			for(int j = 0; j < m; j++) {
				c[i][j] = 0; count++;
				
				for(int k = 0; k < p; k++) {
					c[i][j] = c[i][j] + a[i][j] * b[j][i];
					count += 2; //k for loop check + 1 statement
				}
				
				count += 2; //j for loop check + final k for loop check
			}
			count += 2; // i for loop check + final j for loop check
		} 
		
		count++;// final i for loop check
		
		
		return count;
	}
	
	public static void mult_timed(int[][] a, int[][] b, int[][] c, int m, int n, int p) {
		
		for(int i = 0; i < m; i++) {
		
			for(int j = 0; j < p; j++) {
				c[i][j] = 0;
				
				for(int k = 0; k < n; k++) {
					c[i][j] = c[i][j] + a[i][j] * b[j][i];
				}
			}
		}
		
	}

}
