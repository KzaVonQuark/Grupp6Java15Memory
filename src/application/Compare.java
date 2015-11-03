package application;

import java.util.Comparator;

public class Compare implements Comparator<Player> {

	private String sortType;

	@Override
	public int compare(Player p1, Player p2) {
		
		if (sortType.equals("Points")){
				if (p1.getPoints() >= p2.getPoints())
					return -1;
				else
					return 1;
		}

		if (sortType.equals("Highest point")) {
			if (p1.getHighestPoint() >= p2.getHighestPoint())
				return -1;
			else
				return 1;
		}

		if (sortType.equals("Least moves")) {
			if (p1.getLeastMoves() <= p2.getLeastMoves())
				return 1;
			else
				return -1;
		}

		if (sortType.equals("Fastest time")) {
			if (p1.getFastestGame() <= p2.getFastestGame())
				return -1;
			else
				return 1;
		}
		return 0;

	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}
