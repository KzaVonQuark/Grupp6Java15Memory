package application;

import java.util.TreeMap;

public class GameBoard {
	
	FileManager fileManager = new FileManager();
	TreeMap<Integer, Player> players = new TreeMap<Integer, Player>();
	Player player;

	
	private int index;;
	
	public GameBoard() {
		super();
		this.index = 1;
	}
	
	public void addPlayers(String name) {
		this.players.put(this.index, new Player(name));
		this.index++;
	}
	
	public void loadPlayers(String fileName) {
		fileManager.load(this.players, fileName);
		this.players.put(this.index, player);
		this.index++;
	}
	
	public void loadHighScore(String[] oldPlayers) {
		
	}
	
	public void savePlayers() {
		
	}
}
