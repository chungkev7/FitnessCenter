package co.grandcircus;

public class Single extends Members {

	private Club club;

	// Constructors
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
	public void checkIn(Club club) {
		try {
			if (super.isCheckedIn()) {
				System.out.println();
				System.out.println("You have already checked in.");
				System.out.println();
			} else {
				if (club.getMembers().contains(Single.this)) {
					super.setCheckedIn(true);
					System.out.println();
					System.out.println("Welcome " + super.getName());
					System.out.println();
				} else if (!club.getMembers().contains(Single.this)) {
					System.out.println();
					System.out.println("Sorry, you're not in our system.");
					throw new IndexOutOfBoundsException();
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Unable to check in.");
			System.out.println();
		}
	}

	@Override
	public String toString() {
		return super.toString() + "," + club;
	}
}