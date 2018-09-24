package jonahKubath_a2;

import java.util.Arrays;
import java.util.Random;
import sort.Sort;

public class Main {

	public static void main(String[] args) {
		int numberOfRuns = 5; // Run xTimes to calculate an average
		int printLength = 10;
		int randomNumbCeiling = 100; // Generate random numbers from 0 - 99
		long time[] = new long[4]; // 0 = quicksort, 1 = mergesort, 2 = selection
		long start, end = 0; // Variables for run time
		boolean firstRun = true; // Only print once and for the length of 10 numbers
		boolean verify = true; // Verify the sort algorithms with known methods
		int testOffset = 1000;
		int numberOfTests = 10 * testOffset;

		
		for(int j = 1000; j <= numberOfTests; j += testOffset) {
			/* Generate the original array */
			int data[] = new int[j];
			Random rand = new Random();
			for(int i = 0; i < data.length; i++) {
				data[i] = rand.nextInt(randomNumbCeiling);
			}
			
			time[0] = time[1] = time[2] = time[3] = 0; // Reset time
			
			/* Generate a sorted array for testing */
			int sorted[] = new int[j];
			if(verify) {
				sorted = copyArray(data, sorted);
				Arrays.sort(sorted);
			}
			
			/* Run the sorts multiple times for an average time */
			for(int i = 0; i < numberOfRuns; i++) {
				/* Copy the original array so each sort is sorting
				 * the same data
				 */
				int temp[] = new int[j];
				
				System.nanoTime();
				
				/* Quicksort */
				temp = copyArray(data, temp);
				if(firstRun && temp.length == printLength) {
					System.out.println("Quicksort");
					System.out.print("Old - ");
					printArray(temp);
				}
				start = end = 0; // Set time back to 0
				start = System.nanoTime();
				Sort.quickSort(temp, 0, temp.length - 1);
				end = System.nanoTime();
				time[0] += (end - start);
				if(firstRun && temp.length == printLength) {
					System.out.print("New - ");
					printArray(temp);
				}
				
				if(verify) {
					verifyArray(sorted, temp, "Quicksort");
				}
				
				/* rQuickSort */
				temp = copyArray(data, temp);
				if(firstRun && temp.length == printLength) {
					System.out.println("rQuickSort");
					System.out.print("Old - ");
					printArray(temp);
				}
				start = end = 0; // Set time back to 0
				start = System.nanoTime();
				Sort.rQuickSort(temp, 0, temp.length - 1);
				end = System.nanoTime();
				time[1] += (end - start);
				if(firstRun && temp.length == printLength) {
					System.out.print("New - ");
					printArray(temp);
				}
				
				if(verify) {
					verifyArray(sorted, temp, "rQuicksort");
				}
				
				/* Mergesort */
				temp = copyArray(data, temp);
				if(firstRun && temp.length == printLength) {
					System.out.println("Mergesort");
					System.out.print("Old - ");
					printArray(temp);
				}
				start = end = 0; // Set time back to 0
				start = System.nanoTime();
				Sort.mergeSort(temp, 0, temp.length - 1);
				end = System.nanoTime();
				time[2] += (end - start);
				if(firstRun && temp.length == printLength) {
					System.out.print("New - ");
					printArray(temp);
				}
				
				if(verify) {
					verifyArray(sorted, temp, "Mergesort");
				}
				
				/* insertion sort */
				temp = copyArray(data, temp);
				if(firstRun && temp.length == printLength) {
					System.out.println("Insertion sort");
					System.out.print("Old - ");
					printArray(temp);
				}
				start = end = 0; // Set time back to 0
				start = System.nanoTime();
				Sort.insertion(temp);
				end = System.nanoTime();
				time[3] += (end - start);
				if(firstRun && temp.length == printLength) {
					System.out.print("New - ");
					printArray(temp);
					System.out.println();
				}
				
				if(verify) {
					verifyArray(sorted, temp, "Insertion");
				}
				
				if(firstRun && temp.length == printLength)
					firstRun = false;
			}
			
			//Print the time data
			System.out.println("--------- " + j + " ---------");
			printAverageTime(time, numberOfRuns);
			
		}
	}
	
	public static int[] copyArray(int[] old, int[] copy) {
		//Error
		if(old.length != copy.length)
			return null;
		
		for(int i = 0; i < old.length; i++) { 
			copy[i] = old[i];
		}
		
		return copy;
	}
	
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	
	public static void printAverageTime(long[] times, int runs) {
		System.out.println("Quicksort - " + (times[0] / runs / 1000) + " ms");
		System.out.println("rQuickSort - " + (times[1] / runs / 1000) + " ms");
		System.out.println("Mergesort - " + (times[2] / runs / 1000) + " ms");
		System.out.println("Insertion - " + (times[3] / runs / 1000) + " ms");
		System.out.println();
	}
	
	public static void verifyArray(int sorted[], int test[], String name) {
		boolean equal = true;
		
		if(sorted.length != test.length)
			return;
		
		for(int i = 0; i < sorted.length; i++) {
			if(sorted[i] != test[i]) {
				equal = false;
			}
		}
		
		if(!equal) {
			System.out.println("Error: " + name);
			System.exit(1);
		}
	}

}
