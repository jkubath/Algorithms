package jonahKubath_A4;

import java.util.Comparator;

public class SortEdge implements Comparator<Edge>{
	// Overriding the compare method to sort the age 
	public int compare(Edge l, Edge r) {
		return l.getCost() - r.getCost();
	}
}
