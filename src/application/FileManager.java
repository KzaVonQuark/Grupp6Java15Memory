package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

public class FileManager {

	String pathName = "/Files/";
	Player player;

	public void load(TreeMap<Integer, Player> players, String name) {

		try (BufferedReader br = new BufferedReader(new FileReader(pathName + "players.txt"))) {
			int key = players.lastKey() + 1;
			String temp = br.readLine();
			if (temp.contains(name)) {
				String tempArray[] = temp.split("[ ]");
				player.setName(tempArray[0]);
				player.setPoints(Integer.parseInt(tempArray[1]));
				player.setMoves(Integer.parseInt(tempArray[2]));
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void save(Player player) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathName + "players.txt"))) {

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void highScore(String[] names) {
		ArrayList<Player> highScore = new ArrayList<Player>();
		for (String name : names) {

			try (BufferedReader br = new BufferedReader(new FileReader(pathName + "HighScore.txt"))) {
				String temp[] = name.split("[ ]");
				player.setName(temp[0]);
				player.setPoints(Integer.parseInt(temp[1]));
				player.setMoves(Integer.parseInt(temp[2]));
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
}
