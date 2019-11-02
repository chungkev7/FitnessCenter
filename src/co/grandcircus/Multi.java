package co.grandcircus;

import java.util.List;

public class Multi extends Members {

	private int points;
	
	private List<Club> clubs;
	
	//Constructors
	public Multi() {
	}

	public Multi(String id, String name, int points) {
		super(id, name);
		super.setMonthlyFee(99.99);
		this.points = points;
	}
	
	//Getters and setters
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public List<Club> getClubs() {
		return clubs;
	}
	
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}
	
	@Override
	public boolean checkIn(Club club) {
		super.setCheckedIn(true);
		points++;
		return true;
	}

	//Overrided toString()
	@Override
	public String toString() {
		return super.toString() + "," + points + "," + clubs;
	}
	

}
