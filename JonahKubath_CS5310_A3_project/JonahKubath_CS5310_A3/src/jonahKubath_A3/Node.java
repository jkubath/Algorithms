package jonahKubath_A3;

public class Node implements Comparable<Node> {
	Node left = null;
	Node right = null;
	String name = null;
	double freq = 0;
	
	public Node() {
		left = null;
		right = null;
		name = null;
		freq = 0;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFreq() {
		return freq;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}
	
	public int compareTo(Node r) {
		//Higher frequencies should be towards the root of the minHeap
		if(this.getFreq() >= r.getFreq())
			return -1;
		else
			return 1;
	}
	

}
