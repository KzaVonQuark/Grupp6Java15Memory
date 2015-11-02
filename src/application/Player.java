package application;

public class Player {

	private String name;
	private int points, moves, time, highestPoint, leastMoves, fastestGame, streak;
	private Deck playerWinningHand;

	public Player(String name) {
		this.name = name;
		this.points = 0;
		this.moves = 0;
		this.time = 0;
		this.highestPoint = 0;
		this.leastMoves = 0;
		this.fastestGame = 0;
		this.playerWinningHand = new Deck();
	}

	public int getStreak() {
		return streak;
	}

	public void setStreak(int streak) {
		this.streak = streak;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getLeastMoves() {
		return leastMoves;
	}

	public void setLeastMoves(int leastMoves) {
		this.leastMoves = leastMoves;
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

	@Override
	public String toString() {
		return this.getName() + " Points " + this.getHighestPoint() + " Moves " + this.getLeastMoves();
	}
}
