package co.grandcircus;

public class Single extends Members {

	private Club club;
	
	
	//Constructors
	public Single() {
	}

	public Single(String id, String name) {
		super(id, name);
		super.setMonthlyFee(15.05);
	}

	//Getters and Setters
	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public boolean checkIn(Club club) {
		if (getClub().equals(club)) {
			super.setCheckedIn(true);
		} else {
			System.out.println("Unable to check in.");
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "," + club;
	}
	
	

	
	
	



}
