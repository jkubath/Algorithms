package jonahKubath_A4;

public class Edge {
	int l = -1;
	int r = -1;
	int cost = -1;
	
	public Edge() {
		
	}
	
	public Edge(int l, int r, int cost) {
		this.l = l;
		this.r = r;
		this.cost = cost;
	}
	
	public int getL() {
		return l;
	}
	public void setL(int l) {
		this.l = l;
	}
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	

}
