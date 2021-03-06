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

	String pathName = "src/files/";
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
				player = new Player(temp);
				playerMap.put(player.getName(), player);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
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
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return names;
	}

	public List<Player> loadHighScore(String sortType, String boardSize) {
		ArrayList<Player> highScore = new ArrayList<Player>();
		highScore.clear();

		if (boardSize.equals("Easy"))
			read(pathName + "HighScoreEasy.txt", highScore);
		else if (boardSize.equals("Normal"))
			read(pathName + "HighScoreNormal.txt", highScore);
		else if (boardSize.equals("Hard"))
			read(pathName + "HighScoreHard.txt", highScore);

		changeSortType(sortType, highScore);

		Compare comp = new Compare();
		comp.setSortType(sortType);
		Collections.sort(highScore, comp);

		return highScore;
	}

	// Save methods.
	public void save(Player player) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName + "Players.txt", true))) {
			bw.append("\r\n" + player.getName());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TreeMap<String, Player> setNewData(List<Player> oldScore, List<Player> gamePlayers) {
		TreeMap<String, Player> highScoreMap = new TreeMap<String, Player>();
		
		for (Player player : oldScore) {
			highScoreMap.put(player.getName(), player);
		}

		for (Player gamer : gamePlayers) {
			if (highScoreMap.containsKey(gamer.getName()))
				highScoreMap.replace(gamer.getName(), (compareHighScore(gamer, highScoreMap)));
			else {
				gamer.setHighestPoint(gamer.getPoints());
				highScoreMap.put(gamer.getName(), gamer);
			}
		}
		return highScoreMap;
	}
	
	private Player compareHighScore(Player gamer, TreeMap<String,Player> highScore) {

			if (gamer.getMoves() < highScore.get(gamer.getName()).getLeastMoves())
				gamer.setLeastMoves(gamer.getMoves());
			else
				gamer.setLeastMoves(highScore.get(gamer.getName()).getLeastMoves());

			if (gamer.getPoints() > highScore.get(gamer.getName()).getHighestPoint())
				gamer.setHighestPoint(highScore.get(gamer.getName()).getPoints());
			else
				gamer.setHighestPoint(highScore.get(gamer.getName()).getHighestPoint());
			
			gamer.setWonGames(highScore.get(gamer.getName()).getWonGames() + gamer.getWonGames());
					
			return gamer;
		}

	public void saveHighScore(List<Player> highScore, String gameMode) {

		if (gameMode.equals("Easy"))
			writer(pathName += "HighScoreEasy.txt", highScore);

		else if (gameMode.equals("Normal"))
			writer(pathName += "HighScoreNormal.txt", highScore);

		else if (gameMode.equals("Hard"))
			writer(pathName += "HighScoreHard.txt", highScore);
	}

	public void clearHighScore(String boardSize) {

		List<Player> clearScore = new ArrayList<Player>();

		if (boardSize.equals("Easy")) {
			clearScore.add(new Player("Zaher", 1, 30, 0));
			clearScore.add(new Player("Owen", 2, 25, 0));
			clearScore.add(new Player("Tomas", 3, 40, 0));
			clearScore.add(new Player("Masih", 4, 35, 0));
			writer(pathName + "HighScoreEasy.txt", clearScore);

		} else if (boardSize.equals("Normal")) {
			clearScore.add(new Player("Zaher", 2, 50, 0));
			clearScore.add(new Player("Owen", 4, 60, 0));
			clearScore.add(new Player("Tomas", 6, 55, 0));
			clearScore.add(new Player("Masih", 8, 45, 0));
			writer(pathName + "HighScoreNormal.txt", clearScore);
		}

		else if (boardSize.equals("Hard")) {
			clearScore.add(new Player("Zaher", 6, 60, 0));
			clearScore.add(new Player("Owen", 7, 65, 0));
			clearScore.add(new Player("Tomas", 8, 70, 0));
			clearScore.add(new Player("Masih", 9, 80, 0));
			writer(pathName + "HighScoreHard.txt", clearScore);
		}

	}

	private void read(String pathName, ArrayList<Player> highScore) {

		try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {

			String temp;
			while ((temp = br.readLine()) != null) {
				String tempSplit[] = temp.split("[ ]");
				Player player = new Player(tempSplit[0]);
				player.setHighestPoint(Integer.parseInt(tempSplit[1]));
				player.setLeastMoves(Integer.parseInt(tempSplit[2]));
				player.setWonGames(Integer.parseInt(tempSplit[3]));
				highScore.add(player);
			}

		} catch (FileNotFoundException | NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void changeSortType(String sortType, ArrayList<Player> players) {

		for (Player player : players) {

			if (sortType.equals("Least moves"))
				player.setSortType(1);
			else if (sortType.equals("Won games"))
				player.setSortType(2);
			else
				player.setSortType(3);
		}
	}

	public void writer(String pathName, List<Player> players) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName))) {
			for (Player player : players) {
				bw.write(player.getName() + " " + player.getHighestPoint() + " " + player.getLeastMoves() + " "
						+ player.getWonGames() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
