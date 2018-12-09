package sort;

import java.util.Random;

public class Sort {
	/*
	 * Quicksort algorithm taken from: Computer Algorithms (Course Book)
	 * rQuicksort algorithm taken from: Computer Algorithms (Course Book)
	 * MergeSort algorithm taken from: Computer Algorithms (Course Book)
	*/
	/**
	 * QuickSort algorithm is done by splitting the array and sorting values in place.
	 * The partition index is the value in the first position of the range to be sorted.
	 * Big-Oh(n log(n) )
	 * @param array The data to sort
	 * @param left The start index
	 * @param right The end index
	 * @return The sorted array
	 */
	public static int[] quickSort(int[] array,int left, int right) {
		if(left < right) {
			//Divide the problem into two parts
			int partition = partition(array, left, right + 1);
			
			//Solve the sub problems
			quickSort(array, left, partition - 1);
			quickSort(array, partition + 1, right);
		}
		
		return array;
	}
	
	/**
	 * Randomized QuickSort algorithm is done by splitting the array 
	 * and sorting values in place.
	 * The partition index is randomly chosen with Random nextInt()
	 * Big-Oh(n log(n) )
	 * @param array The data to sort
	 * @param left The start index
	 * @param right The end index
	 * @return The sorted array
	 */
	public static int[] rQuickSort(int[] array,int left, int right) {
		Random rand = new Random();
		
		if(left < right) {
			if((right - left) > 5) {
				int newLeft = (rand.nextInt(right - left) + left);
				//System.out.println("NewLeft - " + newLeft);
				swap(array, left, newLeft);
				//printArray(array);
			}
			
			
			//Divide the problem into two parts
			int partition = partition(array, left, right + 1);
			
			//Solve the sub problems
			rQuickSort(array, left, partition - 1);
			rQuickSort(array, partition + 1, right);
		}
		
		//printArray(array);
		
		return array;
	}
	
	/**
	 * Partition is used to pseudo sort the data in the given range.  A pivot is chosen
	 * and the array is sorted so that values to the left of the pivot value are less than
	 * the pivot and values to the right of the pivot are greater than the pivot value.
	 * @param array The data to sort
	 * @param left The start index
	 * @param right The end index
	 * @return The index of the pivot
	 */
	public static int partition(int[] array, int left, int right) {
		int i = left;
		int j = right;
		int pivot = array[left];
		
		/* Swap values until every left of the pivot are less than the pivot
		 * and values to the right of the pivot are greater than or equal to
		 * pivot
		 */
		do {
			//Iterate as long as the values are >
			//than the pivot value
			do {
				i++;
			} while(i < array.length && array[i] < pivot);
			
			do {
				j--;
			} while(j > 0 && array[j] > pivot);
			
			/* Swap values so everything to the left
			 * of the pivot is < pivot and everything
			 * to the right is > pivot
			 */
			if(i < j)
				swap(array, i, j);
			
		} while(i < j);
		
		//place the pivot in between the two sorted ranges
		array[left] = array[j];
		array[j] = pivot;

		return j;
	}
	
	/**
	 * Swap two elements in an array
	 * @param array The array of data
	 * @param left The first index
	 * @param right The second index
	 */
	public static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	/**
	 * The merge sort algorithm is done by splitting the array until
	 * ranges of length 1 are returned.  These arrays are then merged
	 * recursively until the entire array has been merged back, sorted.
	 * @param array  The array of data
	 * @param low The left index range
	 * @param high The right index range
	 */
	public static void mergeSort(int[] array, int low, int high){
		if(low < high){
			int middle = (low + high) / 2;
			mergeSort(array, low, middle);
			mergeSort(array, middle+1, high);
			merge(array, low, middle, high);
		}	
	}
	
	/**
	 * Merge combines the two ranges of the sorted array and then copies it back
	 * to the original array.
	 * @param array  The array with two sorted ranges
	 * @param low The low index range
	 * @param mid The middle index range
	 * @param high The high index range
	 */
	public static void merge(int[] array, int low, int mid, int high) {
		int b[] = new int[array.length];
		int h = low;
		int i = low;
		int j = mid + 1;
		
		while((h <= mid) && (j <= high)) {
			if(array[h] <= array[j]) {
				b[i] = array[h];
				h++;
			}
			else {
				b[i] = array[j];
				j++;
			}
			i++;
		}
		
		//If one array has values left, merge them all
		if( h > mid) {
			for(int k = j; k <= high; k++) {
				b[i] = array[k];
				i++;
			}
		}
		else {
			for(int k = h; k <= mid; k++) {
				b[i] = array[k];
				i++;
			}
		}
		
		//Copy values from helper array
		for(int k = low; k <= high; k++) {
			array[k] = b[k];
		}
		
		
	}
	
	//Second merge sort that does perform in n log(n) time
//	// Places the elements of the given array into sorted order
//    // using the merge sort algorithm.
//    // post: array is in sorted (nondecreasing) order
//    public static void mergeSort(int[] array) {
//        if (array.length > 1) {
//            // split array into two halves
//            int[] left = leftHalf(array);
//            int[] right = rightHalf(array);
//            
//            // recursively sort the two halves
//            mergeSort(left);
//            mergeSort(right);
//            
//            // merge the sorted halves into a sorted whole
//            merge(array, left, right);
//        }
//    }
//    
//    // Returns the first half of the given array.
//    public static int[] leftHalf(int[] array) {
//        int size1 = array.length / 2;
//        int[] left = new int[size1];
//        for (int i = 0; i < size1; i++) {
//            left[i] = array[i];
//        }
//        return left;
//    }
//    
//    // Returns the second half of the given array.
//    public static int[] rightHalf(int[] array) {
//        int size1 = array.length / 2;
//        int size2 = array.length - size1;
//        int[] right = new int[size2];
//        for (int i = 0; i < size2; i++) {
//            right[i] = array[i + size1];
//        }
//        return right;
//    }
//    
//    // Merges the given left and right arrays into the given 
//    // result array.  Second, working version.
//    // pre : result is empty; left/right are sorted
//    // post: result contains result of merging sorted lists;
//    public static void merge(int[] result, 
//                             int[] left, int[] right) {
//        int i1 = 0;   // index into left array
//        int i2 = 0;   // index into right array
//        
//        for (int i = 0; i < result.length; i++) {
//            if (i2 >= right.length || (i1 < left.length && 
//                    left[i1] <= right[i2])) {
//                result[i] = left[i1];    // take from left
//                i1++;
//            } else {
//                result[i] = right[i2];   // take from right
//                i2++;
//            }
//        }
//    }
	
	/**
	 * Selection sort chooses the next smallest element and places in index i
	 * @param array The array to sort
	 */
	public static void selection(int[] array) {
		int smallest = 0;
		
		for(int i = 0; i < array.length; i++) {
			smallest = i;
			//Find the smallest value
			for(int j = i; j < array.length; j++) {
				if(array[j] < array[smallest]) {
					smallest = j;
				}
			}
			
			//place it at i
			if(smallest != i) {
				int temp = array[i];
				array[i] = array[smallest];
				array[smallest] = temp;
			}
		}
	}
	
	/**
	 * Insertion sort starts at index 1 and shifts the values until the index is found
	 * to place the new value in a sorted order.
	 * @param array  The array to sort
	 */
	public static void insertion(int[] array) {
		
		for(int i = 1; i < array.length; i++) {
			int value = array[i];
			int index = i - 1;
			
			while(index >= 0 && array[index] > value) {
				array[index + 1] = array[index];
				index--;
			}
			index++;
			array[index] = value;
		}
		
		
	}
	
	/**
	 * Print the values in the array
	 * @param arr The array of data
	 */
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	
}
