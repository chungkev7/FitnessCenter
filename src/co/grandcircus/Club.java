package co.grandcircus;

import java.util.ArrayList;
import java.util.List;

public class Club {
	private String name;
	private String address;
	List<Members> members = new ArrayList<>();

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
