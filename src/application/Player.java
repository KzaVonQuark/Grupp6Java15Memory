package application;

public class Player {
	
	private String name;
<<<<<<< HEAD
	private int points, moves, highestPoint, fastestGame;
=======
	private Deck playerWinningHand;
>>>>>>> branch 'master' of https://github.com/KzaVonQuark/Grupp6Java15Memory
	
	public Player(String name) {
		super();
		this.name = name;
		this.points = 0;
		this.moves = 0;
		this.playerWinningHand = new Deck();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @return the moves
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}

	public Deck getPlayerWinningHand() {
		return playerWinningHand;
	}

	public void setPlayerWinningHand(Deck playerWinningHand) {
		this.playerWinningHand = playerWinningHand;
	}
}
