package application;

public class Player {

	private String name;
	private int points, moves, time, highestPoint, leastMoves, wonGames, streak, sortType;
	private Deck playerWinningHand;

	public Player(String name) {
		this.name = name;
		this.points = 0;
		this.moves = 500;
		this.time = 0;
		this.highestPoint = 0;
		this.leastMoves = 0;
		this.wonGames = 0;
		this.playerWinningHand = new Deck();
	}
	
	public Player(String name, int highestPoint, int leastMoves, int wonGames) {
		this.name = name;
		this.points = 0;
		this.moves = 500;
		this.time = 0;
		this.highestPoint = highestPoint;
		this.leastMoves = leastMoves;
		this.wonGames = wonGames;
		this.playerWinningHand = new Deck();
	}

	public int getWonGames() {
		return wonGames;
	}

	public void setWonGames(int wonGames) {
		this.wonGames = wonGames;
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

	public Deck getPlayerWinningHand() {
		return playerWinningHand;
	}

	public void setPlayerWinningHand(Deck playerWinningHand) {
		this.playerWinningHand = playerWinningHand;
	}

	public int getSortType() {
		return sortType;
	}

	public void setSortType(int sortType) {
		this.sortType = sortType;
	}

	@Override
	public String toString() {
		
		if (sortType == 1)
			return this.getName() + " Moves " + this.getLeastMoves();
		else if (sortType == 2)
			return this.getName() + " Won games " + this.getWonGames();
		else
			return this.getName() + " Points " + this.getHighestPoint();
	}
}
