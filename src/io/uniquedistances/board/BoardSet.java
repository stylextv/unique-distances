package io.uniquedistances.board;

import java.util.LinkedList;
import java.util.List;

public class BoardSet {
	
	private static final int ROTATIONAL_SYMMETRIES_AMOUNT = 4;
	
	private final List<Board> boards = new LinkedList<>();
	
	public void addBoard(Board board) {
		for(int i = 0; i < ROTATIONAL_SYMMETRIES_AMOUNT; i++) {
			
			board = board.rotate();
			boards.add(board);
		}
		
		board = board.mirror();
		
		for(int i = 0; i < ROTATIONAL_SYMMETRIES_AMOUNT; i++) {

			board = board.rotate();
			boards.add(board);
		}
	}
	
	public boolean containsBoard(Board board) {
		for(Board b : boards) {
			
			if(board.equals(b)) return true;
		}
		
		return false;
	}
	
}
