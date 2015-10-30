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

public class FileManager {

	String pathName = "src/Files/";
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

	public ArrayList<Player> loadHighScore(String sortType) {
		ArrayList<Player> highScore = new ArrayList<Player>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(pathName + "HighScore.txt"))) {
			String temp;
			while ((temp= br.readLine()) != null) {
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
			
			return highScore;
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
