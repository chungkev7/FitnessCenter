package co.grandcircus;

import java.util.LinkedList;
import java.util.List;

public class Club {
	private String name;
	private String address;
	List<Members> members = new LinkedList<>();

	// constructors
	public Club() {
		super();
	}

	public Club(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public List<Members> getMembers() {
		return members;
	}

	public void setMembers(List<Members> members) {
		this.members = members;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return name + "," + address;
	}

}
