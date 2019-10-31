package co.grandcircus;

public class Single extends Members {

	private Club club;
	
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
		return false;
	}



}
