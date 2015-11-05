package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class FileManager {

	String pathName = "src/Files/";
	Player player;
	TreeMap<String, Player> playerMap;
	static String gameMode;

	/*
	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}
	*/

	// Load methods
	public void loadPlayer() {
		playerMap = new TreeMap<String, Player>();

		try (BufferedReader br = new BufferedReader(new FileReader(pathName + "Players.txt"))) {

			String temp;
			while ((temp = br.readLine()) != null) {
				String[] tempArray = temp.split("[ ]");
				player = new Player(tempArray[0]);
				player.setWonGames(Integer.parseInt(tempArray[1]));
				playerMap.put(player.getName(), player);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public ArrayList<String> loadNames() {

		ArrayList<String> names = new ArrayList<String>();
		names.add("Guest");

		try (BufferedReader br = new BufferedReader(new FileReader(pathName + "Players.txt"))) {
			String temp;
			while ((temp = br.readLine()) != null) {
				String[] tempArray = temp.split("[ ]");
				names.add(tempArray[0]);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return names;
	}

	public List<Player> loadHighScore(String sortType, String boardSize) {
		ArrayList<Player> highScore = new ArrayList<Player>();
		highScore.clear();
		try {
			BufferedReader br = null;
			if (boardSize.equals("Easy"))
				br = new BufferedReader(new FileReader(pathName + "HighScoreEasy.txt"));
			else if (boardSize.equals("Normal"))
				br = new BufferedReader(new FileReader(pathName + "HighScoreNormal.txt"));
			else if (boardSize.equals("Hard"))
				br = new BufferedReader(new FileReader(pathName + "HighScoreHard.txt"));

			String temp;
			while ((temp = br.readLine()) != null) {
				String tempSplit[] = temp.split("[ ]");
				Player player = new Player(tempSplit[0] + "\n");
				player.setHighestPoint(Integer.parseInt(tempSplit[1]));
				player.setLeastMoves(Integer.parseInt(tempSplit[2]));
				player.setWonGames(Integer.parseInt(tempSplit[3]));
				highScore.add(player);

				if (sortType.equals("Least moves"))
					player.setSortType(1);
				else if (sortType.equals("Won games"))
					player.setSortType(2);
				else
					player.setSortType(3);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Compare comp = new Compare();
		comp.setSortType(sortType);
		Collections.sort(highScore, comp);
		if (highScore.size() > 4)
			return highScore.subList(0, 4);
		
		return highScore;
	}

	// Save methods.

	public void save(Player player) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName + "Players.txt", true))) {
			bw.append("\r\n" + player.getName() + " " + player.getWonGames());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveHighScore(List<Player> players, String sortType) {
		
		playerMap = new TreeMap<String, Player>();
		
		for (Player player : loadHighScore(sortType, gameMode)) {
			this.playerMap.put(player.getName(), player);
		}
		
		for (Player player : players) {
			if (this.playerMap.isEmpty())
				this.playerMap.put(player.getName(), player);
			else {
				if (this.playerMap.containsKey(player.getName()))
					this.playerMap.replace(this.playerMap.get(player).getName(), player);
				else
					this.playerMap.put(player.getName(), player);
			}
		}
		
		try {
			BufferedWriter bw = null;
			if (gameMode.equals("Easy")) {
				bw = new BufferedWriter(new FileWriter(pathName + "HighScoreEasy.txt", true));
				for (Player player : this.playerMap.values()) {
					bw.write(player.getName() + " " + player.getHighestPoint()
						+ " " + player.getLeastMoves() + " " + player.getWonGames() + "\n");
				}
			}
			else if (gameMode.equals("Normal")){
				bw = new BufferedWriter(new FileWriter(pathName + "HighScoreNormal.txt", true));
				for (Player player : this.playerMap.values()) {
					bw.write(player.getName() + " " + player.getHighestPoint()
						+ " " + player.getLeastMoves() + " " + player.getWonGames() + "\n");
				}
			}
			else if (gameMode.equals("Hard")){
				bw = new BufferedWriter(new FileWriter(pathName + "HighScoreHard.txt", true));
				for (Player player : this.playerMap.values()) {
					bw.write(player.getName() + " " + player.getHighestPoint()
						+ " " + player.getLeastMoves() + " " + player.getWonGames() + "\n");
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clearHighScore(String boardSize) {
		try {
			BufferedWriter bw;
			if (boardSize.equals("Easy"))
				bw = new BufferedWriter(new FileWriter(pathName + "HighScoreEasy.txt"));
			else if (boardSize.equals("Normal"))
				bw = new BufferedWriter(new FileWriter(pathName + "HighScoreNormal.txt"));
			else if (boardSize.equals("Hard"))
				bw = new BufferedWriter(new FileWriter(pathName + "HighScoreHard.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
