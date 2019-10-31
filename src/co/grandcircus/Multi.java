package co.grandcircus;

import java.util.ArrayList;
import java.util.List;

public class Multi extends Members {

	private int points;
	
	private List<Club> clubs;
	
	public Multi() {
	}

	public Multi(String id, String name, boolean checkedIn, double monthlyFee, int points) {
		super(id, name, checkedIn, monthlyFee);
		this.points = points;
		List<Club> clubs = new ArrayList<>();
	}

	@Override
	public boolean checkIn(Club club) {
		return false;
	}

}
