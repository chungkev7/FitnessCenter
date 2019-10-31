package co.grandcircus;

import java.util.ArrayList;
import java.util.List;

public class Multi extends Members {

	private int points;
	
	private List<Club> clubs;
	
	public Multi() {
	}

	public Multi(String id, String name, int points) {
		super(id, name);
		super.setMonthlyFee(99.99);
		this.points = points;
		List<Club> clubs = new ArrayList<>();
	}

	@Override
	public boolean checkIn(Club club) {
		return false;
	}

}
