package co.grandcircus;

public class Club {
	private String name;
	private String address;

	public Club() {
		super();
	}

	public Club(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return name + "," + address;
	}

}
