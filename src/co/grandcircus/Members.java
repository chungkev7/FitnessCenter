package co.grandcircus;

public abstract class Members {
	private String id;
	private String name;
	boolean checkedIn;
	double monthlyFee;

	public Members() {
		super();
	}

	public Members(String id, String name, boolean checkedIn, double monthlyFee) {
		super();
		this.id = id;
		this.name = name;
		this.checkedIn = checkedIn;
		this.monthlyFee = monthlyFee;
	}

	public abstract boolean checkIn(Club club);
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public double getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	@Override
	public String toString() {
		return id + "," + name + "," + checkedIn + "," + monthlyFee;
	}

}
