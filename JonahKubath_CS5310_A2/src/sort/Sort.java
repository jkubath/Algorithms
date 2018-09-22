package sort;

public class Sort {
	/*
	 * Quicksort algorithm taken from: Computer Algorithms (Course Book)
	 * Mergesort algorithm taken from: https://gist.github.com/cocodrips/5937371
	 */
	public static int[] quickSort(int[] array,int left, int right) {
		if(left < right) {
			//Divide the problem into two parts
			int partition = partition(array, left, right);
			
			//Solve the sub problems
			quickSort(array, left, partition - 1);
			quickSort(array, partition + 1, right);
		}
		
		return array;
	}
	
	public static int partition(int[] array, int left, int right) {
		int begin = left;
		int pivot = array[left];
		left++; // Start sorting with the value +1 of the pivot
		
		while(left < right) {
			//Iterate as long as the values are >
			//than the pivot value
			while(left < array.length && array[left] < pivot) {
				left++;
			}
			
			while(right > 0 && array[right] > pivot) {
				right--;
			}
			
			/* Swap values so everything to the left
			 * of the pivot is < pivot and everything
			 * to the right is > pivot
			 */
			if(left < right)
				swap(array, left, right);
			
		}
		
		array[begin] = array[right];
		array[right] = pivot;
		
		return right;
	}
	
	public static void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
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
	
}
