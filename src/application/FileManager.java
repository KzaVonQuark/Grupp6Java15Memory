package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

	// Load methods
	public void load() {
		playerMap = new TreeMap<String, Player>();

		try (BufferedReader br = new BufferedReader(new FileReader(pathName + "Players.txt"))) {

			String temp;
			while ((temp = br.readLine()) != null) {
				String tempArray[] = temp.split("[ ]");
				player = new Player(tempArray[0]);
				player.setHighestPoint(Integer.parseInt(tempArray[1]));
				player.setFastestGame(Integer.parseInt(tempArray[2]));
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
				String tempArray[] = temp.split("[ ]");
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
				br = new BufferedReader(new FileReader(pathName + "HighScoreSmall.txt"));
			else if (boardSize.equals("Normal"))
				br = new BufferedReader(new FileReader(pathName + "HighScoreMedium.txt"));
			else if (boardSize.equals("Hard"))
				br = new BufferedReader(new FileReader(pathName + "HighScoreLarge.txt"));

			String temp;
			while ((temp = br.readLine()) != null) {
				String tempSplit[] = temp.split("[ ]");
				Player player = new Player(tempSplit[0] + "\n");
				player.setHighestPoint(Integer.parseInt(tempSplit[1]));
				player.setLeastMoves(Integer.parseInt(tempSplit[2]));
				highScore.add(player);
			}

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

		return highScore.subList(0, 4);
	}

	// Save methods.

	public void save(Player player) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName + "Players.txt"))) {
			bw.append(player.getName() + " " + player.getHighestPoint() + " " + player.getFastestGame());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveHighScore(Player player) {

	}
}
