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

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	@Override
	public boolean checkIn(Club club) {
		try {
		if (getClub().equals(club)) {
			super.setCheckedIn(true);
		} else {
			throw new Exception();
		}
		} catch (Exception e) {
			System.out.println("\nUnable to check in.");
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "," + club;
	}
	
	

	
	
	



}
