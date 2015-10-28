package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	String pathName = "/Files/";
	Player player;

	// Load methods
	public Player load(String name) {

		try (BufferedReader br = new BufferedReader(new FileReader(pathName + "Players.txt"))) {

			String temp;
			while ((temp = br.readLine()) != null) {

				if (temp.contains(name)) {
					String tempArray[] = temp.split("[ ]");
					player.setName(tempArray[0]);
					player.setHighestPoint(Integer.parseInt(tempArray[1]));
					player.setFastestGame(Integer.parseInt(tempArray[2]));
					return player;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

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

	public void loadHighScore(String[] names) {
		ArrayList<Player> highScore = new ArrayList<Player>();
		for (String name : names) {

			try (BufferedReader br = new BufferedReader(new FileReader(pathName + "HighScore.txt"))) {
				String temp[] = name.split("[ ]");
				player.setName(temp[0]);
				player.setHighestPoint(Integer.parseInt(temp[4]));
				player.setLeastMoves(Integer.parseInt(temp[5]));
				highScore.add(player);

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
