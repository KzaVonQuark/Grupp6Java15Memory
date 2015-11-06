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
	private String gameMode;

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

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
				Player player = new Player(tempSplit[0]);
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

	public void saveHighScore(List<Player> players, String sortType, String gameMode) {

		TreeMap<String, Player> highScoreMap = new TreeMap<String, Player>();

		for (Player player : loadHighScore(sortType, gameMode)) {
			highScoreMap.put(player.getName(), player);
		}

		for (Player player2 : players) {
			if (highScoreMap.isEmpty())
				highScoreMap.put(player2.getName(), player2);
			else {
				if (highScoreMap.containsKey(player2.getName()))
					highScoreMap.replace(player2.getName(), player2);
				else
					highScoreMap.put(player2.getName(), player2);
			}
		}

		if (gameMode.equals("Easy"))
			writer(pathName += "HighScoreEasy.txt", highScoreMap);

		else if (gameMode.equals("Normal"))
			writer(pathName += "HighScoreNormal.txt", highScoreMap);

		else if (gameMode.equals("Hard"))
			writer(pathName += "HighScoreHard.txt", highScoreMap);
	}

	public void clearHighScore(String boardSize) {

		TreeMap<String, Player> clearScore = new TreeMap<String, Player>();

			if (boardSize.equals("Easy")) {
				clearScore.put("Zaher", defaultPlayers("Zaher", 2, 16));
				clearScore.put("Owen", defaultPlayers("Owen", 4, 18));
				clearScore.put("Tomas", defaultPlayers("Tomas", 6, 20));
				clearScore.put("Masih", defaultPlayers("Masih", 8, 22));
				writer(pathName+"HighScoreEasy.txt", clearScore);
				
			} else if (boardSize.equals("Normal")) {
				clearScore.put("Zaher", defaultPlayers("Zaher", 11, 25));
				clearScore.put("Owen", defaultPlayers("Owen", 10, 30));
				clearScore.put("Tomas", defaultPlayers("Tomas", 9, 35));
				clearScore.put("Masih", defaultPlayers("Masih", 8, 40));
				writer(pathName+"HighScoreNormal.txt", clearScore);
			}

			else if (boardSize.equals("Hard")) {
				clearScore.put("Zaher", defaultPlayers("Zaher", 12, 16));
				clearScore.put("Owen", defaultPlayers("Owen", 14, 18));
				clearScore.put("Tomas", defaultPlayers("Tomas", 16, 20));
				clearScore.put("Masih", defaultPlayers("Masih", 18, 22));
				writer(pathName+"HighScoreHard.txt", clearScore);
			}

	}

	public void writer(String pathName, TreeMap<String, Player> highScoreMap) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName))) {
			for (Player player : highScoreMap.values()) {
				bw.write(player.getName() + " " + player.getHighestPoint() + " " + player.getLeastMoves() + " "
						+ player.getWonGames() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Player defaultPlayers(String name, int points, int moves) {
		Player player = new Player(name, points, moves);
		return player;
	}

}
