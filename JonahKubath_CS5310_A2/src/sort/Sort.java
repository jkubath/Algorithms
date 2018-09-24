package sort;

import java.util.Random;

public class Sort {
	/*
	 * Quicksort algorithm taken from: Computer Algorithms (Course Book)
	 * rQuicksort algorithm taken from: Computer Algorithms (Course Book)
	 * Mergesort algorithm taken from: https://gist.github.com/cocodrips/5937371
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
	
	public static int partition(int[] array, int left, int right) {
		//int begin = left;
		int i = left;
		int j = right;
		int pivot = array[left];
//		System.out.println("L- " + i + " R- " + j);
//		System.out.println(pivot);
		
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
			
//			System.out.println("2L- " + i + " R- " + j);
//			printArray(array);
			
		} while(i < j);
//		System.out.println("3L- " + i + " R- " + j);
		
		array[left] = array[j];
		array[j] = pivot;

//		printArray(array);
		
		return j;
	}
	
	public static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	public static void mergeSort(int[] array, int low, int high){
		if(low < high){
			int middle = (low + high) / 2;
			mergeSort(array, low, middle);
			mergeSort(array, middle+1, high);
			merge(array, low, middle, high);
		}	
	}

	public static void merge(int[] array, int low, int middle, int high){
		int[] helper = new int[array.length];
		
		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}
		
		int helperLeft = low;
		int helperRight = middle+1;
		int current = low;
		
		while (helperLeft <= middle && helperRight <=high) {
			if(helper[helperLeft] <= helper[helperRight]){
				array[current] = helper[helperLeft];
				helperLeft++;
				
			}else{
				array[current] = helper[helperRight];
				helperRight++;
			}
			current ++;		
		}
		
		int remaining = middle - helperLeft;
		for (int i = 0; i <= remaining; i++) {
			array[current+i] = helper[helperLeft+ i];
		}
	}
	
	public static void merge1(int[] array, int low, int mid, int high) {
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
		
		
		for(int k = low; k <= high; k++) {
			array[k] = b[k];
		}
		
		
	}
	
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
	
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
	
}
