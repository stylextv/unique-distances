package io.uniquedistances.board;

public class Board {
	
	private static final String NON_EMPTY_TILE = "X";
	private static final String EMPTY_TILE = ".";
	private static final String LINE_SEPARATOR = System.lineSeparator();
	
	private final int size;
	private final boolean[][] tiles;
	private final int[] tilePositionXs;
	private final int[] tilePositionYs;
	
	private int tileAmount;
	private final DistanceSet usedDistances = new DistanceSet();
	
	public Board(int size) {
		this.size = size;
		this.tiles = new boolean[size][size];
		this.tilePositionXs = new int[size];
		this.tilePositionYs = new int[size];
	}
	
	public String asString() {
		StringBuilder builder = new StringBuilder();
		
		for(int y = 0; y < size; y++) {
			for(int x = 0; x < size; x++) {
				
				if(tiles[x][y]) builder.append(NON_EMPTY_TILE);
				else builder.append(EMPTY_TILE);
			}
			
			builder.append(LINE_SEPARATOR);
		}
		
		return builder.toString();
	}
	
	public Board rotate() {
		Board board = new Board(size);

		for(int tileIndex = 0; tileIndex < tileAmount; tileIndex++) {
			
			int x = tilePositionXs[tileIndex];
			int y = tilePositionYs[tileIndex];
			
			board.addTile(y, size - 1 - x);
		}
		
		return board;
	}
	
	public Board mirror() {
		Board board = new Board(size);
		
		for(int tileIndex = 0; tileIndex < tileAmount; tileIndex++) {
			
			int x = tilePositionXs[tileIndex];
			int y = tilePositionYs[tileIndex];
			
			board.addTile(size - 1 - x, y);
		}
		
		return board;
	}
	
	public boolean equals(Board board) {
		if(board.getSize() != size || board.getTileAmount() != tileAmount) return false;
		
		for(int tileIndex = 0; tileIndex < tileAmount; tileIndex++) {
			
			int x = tilePositionXs[tileIndex];
			int y = tilePositionYs[tileIndex];
			
			if(!board.containsTile(x, y)) return false;
		}
		
		return true;
	}
	
	public boolean addTile(int x, int y) {
		if(tiles[x][y]) return false;
		
		int tileIndex = 0;
		boolean remove = false;
		
		while(tileIndex < tileAmount) {
			
			int x2 = tilePositionXs[tileIndex];
			int y2 = tilePositionYs[tileIndex];
			
			int squaredDistance = (x - x2) * (x - x2) + (y - y2) * (y - y2);
			if(usedDistances.containsDistance(squaredDistance)) {
				
				remove = true;
				break;
			}
			
			usedDistances.addDistance(squaredDistance);
			tileIndex++;
		}
		
		if(remove) {
			
			tileIndex--;
			while(tileIndex >= 0) {
				
				int x2 = tilePositionXs[tileIndex];
				int y2 = tilePositionYs[tileIndex];
				
				int squaredDistance = (x - x2) * (x - x2) + (y - y2) * (y - y2);
				usedDistances.removeDistance(squaredDistance);
				
				tileIndex--;
			}
			
			return false;
		}
		
		tiles[x][y] = true;
		tilePositionXs[tileAmount] = x;
		tilePositionYs[tileAmount] = y;
		tileAmount++;
		
		return true;
	}
	
	public void removeTile(int x, int y) {
		tiles[x][y] = false;
		tileAmount--;
		
		for(int tileIndex = 0; tileIndex < tileAmount; tileIndex++) {
			
			int x2 = tilePositionXs[tileIndex];
			int y2 = tilePositionYs[tileIndex];
			
			int squaredDistance = (x - x2) * (x - x2) + (y - y2) * (y - y2);
			usedDistances.removeDistance(squaredDistance);
		}
	}
	
	public boolean containsTile(int x, int y) {
		return tiles[x][y];
	}
	
	public int getTileAmount() {
		return tileAmount;
	}
	
	public int getSize() {
		return size;
	}
	
}
