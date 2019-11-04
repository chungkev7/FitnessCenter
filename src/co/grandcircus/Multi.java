package co.grandcircus;

import java.util.List;

public class Multi extends Members {

	private int points;

	private List<Club> clubs;

	// Constructors
	public Multi() {
	}

	public Multi(String id, String name, int points) {
		super(id, name);
		super.setMonthlyFee(99.99);
		this.points = points;
	}

	// Getters and setters
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
	public void checkIn(Club club) {
		if (super.isCheckedIn()) {
			System.out.println();
			System.out.println("You have already checked in.");
			System.out.println();
		} else {
			super.setCheckedIn(true);
			points++;
			System.out.println();
			System.out.println("Welcome " + super.getName());
			System.out.println();
		}
	}

	// Overrided toString()
	@Override
	public String toString() {
		return super.toString() + "," + points + "," + clubs;
	}
}
