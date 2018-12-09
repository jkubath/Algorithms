/*
 * Author: Jonah Kubath
 * Class: CS5310
 * Assignment: 1
 * Date: 09/09/2018
 */
package jonahKubath_ch1_4_24;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		/* Initialization */
		//int[] arrayLengths = {5, 10, 50, 100, 1000, 10000};
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
				int[][] array = new int[arrayLengths[index]][arrayLengths[index]];
				for(int i = 0; i < array.length; i++) {
					for(int j = 0; j < array[i].length; j++) {
						array[i][j] = randomInt.nextInt(randomIntBoundary);
					}
				}
				
				long startTime = System.nanoTime();
				//call the algorithm
				//transpose_timed(array, array.length);
				count = transpose(array, array.length);
				//count = transpose_simplified(array, array.length);
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
	
	public static void transpose_timed(int[][] a, int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i + 1; j++) {
				int temp = a[i][j];
				a[i][j] = a[j][i];
				a[j][i] = temp;
			}
		}
	}
	
	public static int transpose(int[][] a, int n) {
		int count = 0;
		
		for(int i = 0; i < n; i++) { count++;
			for(int j = 0; j < i + 1; j++) { count++;
				int temp = a[i][j]; count++;
				a[i][j] = a[j][i]; count++;
				a[j][i] = temp; count++;
			}
			count++; // final for loop check
		} count++;// final for loop check
		
		return count;
	}
	
	public static int transpose_simplified(int[][] a, int n) {
		int count = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i + 1; j++) {
				int temp = a[i][j];
				a[i][j] = a[j][i];
				a[j][i] = temp;
				
				count += 4; //For loop check + 3 statements
			}
			count+= 2; // outer for loop + final inner for loop check
		} 
		
		count++;// final for loop check
		
		return count;
		
	}
	
	public static void printMatrix(int[][] a) {
		int n = a.length;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
