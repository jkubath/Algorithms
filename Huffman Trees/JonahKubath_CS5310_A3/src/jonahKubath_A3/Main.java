package jonahKubath_A3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.commons.collections.buffer.PriorityBuffer;

public class Main {
	static int total = 0;

	public static void main(String[] args) throws FileNotFoundException {
		/* fileChoice
		 * 0 = exerc4.txt 
		 * 1 = ms249.txt
		 * 2 = 10 randomly generated files from ./data folder
		 */
		int fileChoice = 1; 
		String filename = null;
		int fileCount = 0;
		int runs = 5; //Number of runs to create a time average
		double weightedLength = 0.0;
		
		if(fileChoice == 0) {
			fileCount = 1;
			filename = "exerc4.txt";
		}
		else if(fileChoice == 1) {
			fileCount = 1;
			filename = "ms249.txt";
		}
		else if(fileChoice == 2) {
			fileCount = 10;
			filename = "./data/data3/data_";
		}
		
		System.out.println("Building Huffman tree");
		
		for(int z = 0; z < fileCount; z++) {
			long time[] = new long[2];
			for(int j = 0; j < runs; j++) {
				total = 0; //Reset total nodes read
				String file = "";
				if(fileCount != 1) {
					file = filename + z + ".txt";
				}
				else {
					file = filename;
				}
				if(j == 0)
					System.out.println("File: " + file);
				
				
				Node list = scan(file); //Read the data
				Node list2 = duplicate(list); //Create a copy of the data
				//printNodesList(list);
				if(j == 0)
					System.out.println("Nodes - " + total);
				
				//Build the minHeap
				PriorityBuffer heap = new PriorityBuffer(false);
				long startHeap = System.nanoTime();
				/* Add the linked list to the PriorityQueue */
				Node front = list;
				Node temp = front;
				for(int i = 0; i < total; i++) {
					temp = front.getLeft();
					front.setLeft(null);
					heap.add(front);
					front = temp;
				}
				long endHeap = System.nanoTime();
				time[0]+= endHeap - startHeap;
				
				//Build the Huffman tree
				long startTree = System.nanoTime();
				Node tree = buildHuffmanTree(heap);
				long endTree = System.nanoTime();
				time[1]+= endTree - startTree;
				
				//Build each nodes message
				if((fileChoice == 0 || fileChoice == 1) && j == 0) {
					weightedLength = buildMessage(tree, list2, true);
				}
				else if(fileChoice == 0 || fileChoice == 1) {
					weightedLength = buildMessage(tree, list2, false);
				}
			}
			//Print average times
			if(fileChoice == 0 || fileChoice == 1) {
				System.out.println("\nSummary");
				System.out.printf("\tWeighted external path   - %.2f\n", weightedLength);
			}
			else {
				System.out.println("Summary");
			}
			System.out.println("\tBuild heap - " + ((double) time[0] / runs) / 1000 + " ms");
			System.out.println("\tBuild tree - " + (time[1] / runs) / 1000 + " ms");
			System.out.println();
		}
		
	}
	
	public static double buildMessage(Node tree, Node list, boolean print) {
		Node index = list;
		
		String message[] = new String[2];
		double weightedLength = 0.0;
		double weightedExternal = 0.0;
		//Generate a message for each node
		while(index != null) {
			message = searchTree(tree, index, 0);
			
			if(print) {
				System.out.println(index.getName() + " - " + message[0]);
				System.out.print("\tFrequency - " + index.getFreq() + "\n");
				System.out.print("\tDepth     - " + message[1] + "\n");
			}
			
			weightedLength = (index.getFreq() * message[0].length());
			if(print)
				System.out.printf("\tWeighted codeword length - %.2f\n", weightedLength);
			weightedExternal += weightedLength;
			
			
			index = index.getLeft();
		}
		
		return weightedExternal;
		
	}
	
	public static String[] searchTree(Node tree, Node find, int depth) {
		String val[] = new String[2];
		if(tree == null)
			return null;
		
		
		val[0] = "";
		val[1] = "" + depth;
		if(tree.getName() != null && tree.getName().compareTo(find.getName()) == 0)	
			return val;
		else {
			depth++;
			
			val = searchTree(tree.getLeft(), find, depth);
			if(val != null)
			{
				val[0] = "0" + val[0];
				return val;
			}
			else
			{
				val = searchTree(tree.getRight(), find, depth);
			}
			
			if(val != null) {
				val[0] = "1" + val[0];
				return val;
			}
				
			return null;
		}
	}
	
	public static Node duplicate(Node list) {
		Node newList = null;
		Node head = null;
		Node front = list;
		while(front != null) {
			Node temp = new Node();
			temp.setFreq(front.getFreq());
			temp.setName(front.getName());
			
			if(head == null) {
				head = temp;
				newList = head;
			}
			else {
				head.setLeft(temp);
				head = head.getLeft();
			}
			
			front = front.getLeft();
		}
		
		return newList;
	}
	
	public static Node buildHuffmanTree(PriorityBuffer heap) {
		Node left = null;
		Node right = null;
		
		boolean cont = true;
		while(cont) {
			try {
				left =(Node) heap.remove();
				right =(Node) heap.remove();
				
//				System.out.println("Combine");
//				System.out.println(left.getName() + "-" + left.getFreq());
//				System.out.println(right.getName() + "-" + right.getFreq());
				
				//Create the combined object
				Node combined = new Node();
//				if(left.getFreq() > right.getFreq())
//				{
//					Node temp = left;
//					left = right;
//					right = temp;
//				}
				combined.setLeft(left);
				combined.setRight(right);
				combined.setFreq(left.getFreq() + right.getFreq());
				//Add it back to the available nodes to combine
				heap.add(combined);
			}
			catch(NoSuchElementException e) {
				cont = false;
			}
			
		}
		
		return left;
	}
	
	public static Node scan(String filename) throws FileNotFoundException {
		File f = new File(filename);
		Scanner scan = new Scanner(f);
		Node list = null;
		Node front = list;
		
		String line = "";
		String split[] = new String[2];
		boolean cont = true;
		while(cont) {
			try {
				line = scan.nextLine();
				//System.out.println(line);
				split = line.split(",");
				if(front == null) {
					front = new Node();
					list = front;
				}
				else {
					front.setLeft(new Node());
					front = front.left;
				}
				total++;
				front.setName(split[0]);
				front.setFreq(Double.parseDouble(split[1]));
				front.left = null;
			}
			catch(NoSuchElementException e) {
				cont = false;
			}
		}
		
		scan.close();
		
		return list;
	}
	
	public static void printNodesList(Node list) {
		Node front = list;
		while(front != null) {
			System.out.println(front.getName() + " " + front.getFreq());
			front = front.getLeft();
		}
	}

	public static void printNodeTree(Node tree) {
		System.out.println(tree.getName() + "-" + tree.getFreq());
		if(tree.getLeft() != null) {
			System.out.println("Left");
			printNodeTree(tree.getLeft());
		}
		if(tree.getRight() != null) {
			printNodeTree(tree.getRight());
		}
		
	}
}
