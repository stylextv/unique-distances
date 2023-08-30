package io.uniquedistances.board;

public class DistanceSet {
	
	private static final int MAXIMAL_DISTANCE_AMOUNT = 1000;
	
	private final boolean[] distances = new boolean[MAXIMAL_DISTANCE_AMOUNT];
	
	public void addDistance(int squaredDistance) {
		distances[squaredDistance] = true;
	}
	
	public void removeDistance(int squaredDistance) {
		distances[squaredDistance] = false;
	}
	
	public boolean containsDistance(int squaredDistance) {
		return distances[squaredDistance];
	}
	
}
