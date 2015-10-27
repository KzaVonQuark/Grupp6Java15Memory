package application;

import java.util.ArrayList;

public class GameBoard {
	
	FileManager fm = new FileManager();
	ArrayList<Player> players = new ArrayList<Player>();
	
	public GameBoard() {
		super();
	}
	
	public void addPlayers(String name) {
		Player newPlayer = new Player(name);
		this.players.add(newPlayer);
		fm.save(newPlayer);
	}
	
	public void addPlayers(Player player) {
		this.players.add(player);
	}
}
