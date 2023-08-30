package io.uniquedistances;

import io.uniquedistances.board.Board;
import io.uniquedistances.board.BoardSet;

public class Main {
	
	private static final int SMALLEST_BOARD_SIZE = 1;
	private static final int LARGEST_BOARD_SIZE = 15;
	private static final int DEFAULT_START_X = 0;
	private static final int DEFAULT_START_Y = 0;
	
	public static void main(String[] args) {
		for(int size = SMALLEST_BOARD_SIZE; size <= LARGEST_BOARD_SIZE; size++) {
			
			String message = String.format("size = %s", size);
			System.out.println(message);
			
			long startTime = System.currentTimeMillis();
			generateSolutions(size);
			long passedDuration = System.currentTimeMillis() - startTime;
			
			message = String.format("passed time duration: %s", passedDuration);
			System.out.println(message);
		}
	}
	
	private static void generateSolutions(int size) {
		Board board = new Board(size);
		BoardSet foundSolutions = new BoardSet();
		generateSolutions(board, foundSolutions, DEFAULT_START_X, DEFAULT_START_Y);
	}
	
	private static void generateSolutions(Board board, BoardSet foundSolutions, int startX, int startY) {
		if(board.getTileAmount() == board.getSize()) {
			if(foundSolutions.containsBoard(board)) return;
			foundSolutions.addBoard(board);
			
			System.out.println(board.asString());
			return;
		}
		
		int x = startX;
		int y = startY;
		
		while(y < board.getSize()) {
			while(x < board.getSize()) {
				if(board.addTile(x, y)) {
					
					generateSolutions(board, foundSolutions, x, y);
					
					board.removeTile(x, y);
				}
				
				x++;
			}
			
			x = DEFAULT_START_X;
			y++;
		}
	}
	
}
