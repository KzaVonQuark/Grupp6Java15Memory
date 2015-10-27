package application;

public class Player {
	
	private String name;
	
	private int points, moves, highestPoint, fastestGame;
	private Deck playerWinningHand;

	public Player(String name) {
		super();
		this.name = name;
		this.points = 0;
		this.moves = 0;
		this.highestPoint = 0;
		this.fastestGame = 0;
		this.playerWinningHand = new Deck();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public int getHighestPoint() {
		return highestPoint;
	}

	public void setHighestPoint(int highestPoint) {
		this.highestPoint = highestPoint;
	}

	public int getFastestGame() {
		return fastestGame;
	}

	public void setFastestGame(int fastestGame) {
		this.fastestGame = fastestGame;
	}

	public Deck getPlayerWinningHand() {
		return playerWinningHand;
	}

	public void setPlayerWinningHand(Deck playerWinningHand) {
		this.playerWinningHand = playerWinningHand;
	}
}
