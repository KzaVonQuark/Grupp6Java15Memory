package application;

public class Test {
	
	Rules rules = new Rules();
	GameBoard gb = new GameBoard();
	Player p = new Player("Gurra");

	void playerTurn(boolean gotPair) {
		
		// Reads first element in queue.
		if (gotPair == true) {
			gb.q.peek().setPoints(gb.q.peek().getPoints() + 1);
			gb.q.peek().setMoves(gb.q.peek().getMoves() + 1);
		}
		
		// Reads, removes and put element last in queue
		else {
			gb.q.peek().setMoves(gb.q.peek().getMoves() + 1);
			gb.q.add(gb.q.poll());
		}
}
}
