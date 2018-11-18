package jonahKubath_A5;

public class Main {
	
	static int[] x;

	public static void main(String[] args) {
		int n = 8;
		x = new int[n];
		
		for(int i = 0; i < n; i++) {
			x[i] = -20;
		}
		
		NQueens(0, n);
		
		//printArray();

	}

	
	public static void NQueens(int k, int n) {
		//System.out.println("Place: " + k);
		for(int i = 0; i < n; i++) {
			//System.out.println("\t" + i);
			//Can node k go into column i
			if(Place(k, i)) {
				x[k] = i;
				if(k == n-1) {
					printArray();
					System.exit(0);
				}
				else {
					NQueens(k + 1, n);
				}
			}
		}
	}
	
	public static boolean Place(int k, int i) {
		//System.out.println("Can " + k + " node go " + i + " column");
		for(int j = 0; j < k; j++) {
			//System.out.println("x - " + x[j] + " i " + i);
			if(x[j] == i || Math.abs(x[j] - i) == Math.abs(j - k)) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void printArray() {
		
		for(int i = 0 ; i < x.length; i++) {
			for(int j = 0; j < x.length; j++) {
				if(j == x[i]) {
					System.out.printf(" %d ", x[i]);
				}
				else 
					System.out.print(" - ");
			}
			System.out.println();
		}
	}
	
}
