package jonahKubath_a2;

import java.util.Random;
import sort.Sort;

public class Main {

	public static void main(String[] args) {
		int numberOfRuns = 5; // Run xTimes to calculate an average
		int arrayLength = 10; // How many numbers to sort
		int randomNumbCeiling = 100; // Generate random numbers from 0 - 99
		long time[] = new long[3]; // 0 = quicksort, 1 = mergesort, 2 = selection
		long start, end = 0; // Variables for run time
		boolean firstRun = true; // Only print once and for the length of 10 numbers
		
		/* Generate the original array */
		int data[] =new int[arrayLength];
		
		Random rand = new Random();
		for(int i = 0; i < data.length; i++) {
			data[i] = rand.nextInt(randomNumbCeiling);
		}
		
		/* Run the sorts multiple times for an average time */
		for(int i = 0; i < numberOfRuns; i++) {
			/* Copy the original array so each sort is sorting
			 * the same data
			 */
			int temp[] = new int[arrayLength];
			
			/* Quicksort */
			temp = copyArray(data, temp);
			if(firstRun && temp.length == 10) {
				System.out.println("Quicksort");
				System.out.print("Old - ");
				printArray(temp);
			}
			start = end = 0; // Set time back to 0
			start = System.nanoTime();
			Sort.quickSort(temp, 0, temp.length - 1);
			end = System.nanoTime();
			time[0] += (end - start);
			if(firstRun && temp.length == 10) {
				System.out.print("New - ");
				printArray(temp);
			}
			
			/* Mergesort */
			temp = copyArray(data, temp);
			if(firstRun && temp.length == 10) {
				System.out.println("Mergesort");
				System.out.print("Old - ");
				printArray(temp);
			}
			start = end = 0; // Set time back to 0
			start = System.nanoTime();
			Sort.mergeSort(temp, 0, temp.length - 1);
			end = System.nanoTime();
			time[1] += (end - start);
			if(firstRun && temp.length == 10) {
				System.out.print("New - ");
				printArray(temp);
			}
			
			/* selection sort */
			temp = copyArray(data, temp);
			if(firstRun && temp.length == 10) {
				System.out.println("Selection sort");
				System.out.print("Old - ");
				printArray(temp);
			}
			start = end = 0; // Set time back to 0
			start = System.nanoTime();
			Sort.selection(temp);
			end = System.nanoTime();
			time[2] += (end - start);
			if(firstRun && temp.length == 10) {
				System.out.print("New - ");
				printArray(temp);
			}
			
			if(firstRun && temp.length == 10)
				firstRun = false;
		}
		
		//Print the time data
		printAverageTime(time, numberOfRuns);
		
		

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
		System.out.println("Quicksort - " + (times[0] / runs) + " ms");
		System.out.println("Mergesort - " + (times[1] / runs) + " ms");
		System.out.println("Selection - " + (times[2] / runs) + " ms");
	}

}
